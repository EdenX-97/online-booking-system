package net.obs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.obs.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomizeAuthenticationProvider authenticationProvider;

    @Autowired
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    CustomizeAuthenticationEnryPoint authenticationEnryPoint;

    @Autowired
    CustomizeAuthenticationLogout authenticationLogout;

    @Autowired
    CustomizeAccessDeny accessDeny;

    @Autowired
    CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity
            .authorizeRequests()
                .antMatchers("/profile")
                .hasRole("USER")
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/login")
                .successHandler(authenticationSuccessHandler) // handle login success
                .failureHandler(authenticationFailureHandler) // handle login failure
                .permitAll() // permit all to access login page
            .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(authenticationLogout)
                .permitAll().and().exceptionHandling().accessDeniedHandler(accessDeny) // handle user have no access
                .authenticationEntryPoint(authenticationEnryPoint) // handle when user(not login) want to open page need login
            .and()
                .sessionManagement()
                .maximumSessions(1) // one account can only login by one user
                .expiredSessionStrategy(sessionInformationExpiredStrategy) // handle when landing
        // timeout or remote
        // landing
        ;
    }
}
