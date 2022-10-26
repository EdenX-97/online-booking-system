package net.obs.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.obs.services.UserService;

@Component
public class CustomizeAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        // Get information and user
        String account = authentication.getName();
        String password = authentication.getCredentials().toString();

        // check user exist
        UserDetails user = userService.loadUserByUsername(account);
        if (user == null) {
            throw new UsernameNotFoundException("User not exist!");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Password incorrect!");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
