package net.obs.services;

import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import net.obs.enums.GenderEnum;
import net.obs.models.User;
import net.obs.repositories.UserRepository;
import net.obs.utils.ReturnMessage;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    // Build-in spring security function
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(account);
        // Check if user account exist
        if (user == null) {
            throw new UsernameNotFoundException("Wrong account!");
        }
        return new org.springframework.security.core.userdetails.User(user.getAccount(),
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }

    // Service to get login user
    public ObjectNode getLoginUser() {
        ObjectNode returnMessage = null;
        User user = getLoginUserObj();
        // If user exist, return message with user json
        if (user == null) {
            returnMessage = new ReturnMessage(400, "Please login!").toJson();
        } else {
            returnMessage = new ReturnMessage(200, "Get user Success!", user).toJson();
        }

        return returnMessage;
    }

    // Service to register
    public ObjectNode register(User userObj, Integer verifyCode, HttpSession session) {
        // Validate password, account exist and email exist
        ObjectNode returnMessage = null;
        String password = userObj.getPassword();
        String matchPassword = userObj.getMatchPassword();
        String email = userObj.getEmail();
        String pwdRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{6,20}$";
        String codeInSession = session.getAttribute("verifyCode").toString();
        String verifyEmail = session.getAttribute("verifyEmail").toString();

        if (!password.matches(pwdRegex)) {
            returnMessage = new ReturnMessage(400, "Password format wrong!").toJson();
        } else if (!matchPassword.matches(pwdRegex)) {
            returnMessage = new ReturnMessage(400, "Match password format wrong!").toJson();
        } else if (!password.equals(matchPassword)) {
            returnMessage = new ReturnMessage(400, "Password not match!").toJson();
        } else if (userRepository.findByAccount(userObj.getAccount()) != null) {
            returnMessage = new ReturnMessage(400, "Email exist!").toJson();
        } else if (userRepository.findByEmail(userObj.getEmail()) != null) {
            returnMessage = new ReturnMessage(400, "Email exist!").toJson();
        } else if (!codeInSession.equals(verifyCode.toString())) {
            returnMessage = new ReturnMessage(400, "Wrong verify code!").toJson();
        } else if (!email.equals(verifyEmail)) {
            returnMessage = new ReturnMessage(400, "Wrong verify email!").toJson();
        } else {
            // Use BCrypt encoder to set password
            String encodedPassword = passwordEncoder.encode(userObj.getPassword());
            userObj.setPassword(encodedPassword);
            userObj.setMatchPassword(encodedPassword);
            userRepository.save(userObj);
            returnMessage = new ReturnMessage(200, "Register success!").toJson();
        }

        return returnMessage;
    }

    // Service to change password
    public ObjectNode changePassword(String newPassword, String nowPassword) {
        // Set variables and regular expressions
        ObjectNode returnMessage = null;
        String pwdRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{6,20}$";
        User user = getLoginUserObj();
        String encodedPassword = user.getPassword();
        String encodedNewPass = passwordEncoder.encode(newPassword);

        // Check and return results
        if (!newPassword.matches(pwdRegex)) {
            returnMessage = new ReturnMessage(400, "New password format wrong!").toJson();
        } else if (!nowPassword.matches(pwdRegex)) {
            returnMessage = new ReturnMessage(400, "Now password format wrong!").toJson();
        } else if (newPassword.equals(nowPassword)) {
            returnMessage = new ReturnMessage(400, "Please enter different password!").toJson();
        } else if (!passwordEncoder.matches(nowPassword, encodedPassword)) {
            returnMessage = new ReturnMessage(400, "Please enter correct now password!").toJson();
        } else {
            User userObj = getLoginUserObj();
            userObj.setPassword(encodedNewPass);
            userObj.setMatchPassword(encodedNewPass);
            userRepository.save(userObj);
            returnMessage = new ReturnMessage(200, "Change password success!").toJson();
        }
        return returnMessage;
    }

    // Service to change login user profile
    public ObjectNode changeProfile(String firstname, String lastname, Integer age, String gender,
            String phone) {
        // Set variables and regular expressions
        ObjectNode returnMessage = null;
        String nameReg = "^[A-Za-z]{1,10}$";
        String phoneReg = "^04[0-9]{8}$";
        User user = getLoginUserObj();

        // Check and return results
        if (!firstname.matches(nameReg)) {
            returnMessage = new ReturnMessage(400, "Please enter correct firstname!").toJson();
        } else if (!lastname.matches(nameReg)) {
            returnMessage = new ReturnMessage(400, "Please enter correct lastname!").toJson();
        } else if (age < 1 || age > 120) {
            returnMessage = new ReturnMessage(400, "Please enter correct age!").toJson();
        } else if (!gender.equals("male") || gender.equals("female")) {
            returnMessage = new ReturnMessage(400, "Please enter correct gender!").toJson();
        } else if (!phone.matches(phoneReg)) {
            returnMessage = new ReturnMessage(400, "Please enter correct phone!").toJson();
        } else {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setAge(age);
            user.setPhone(phone);
            if (gender.equals("male")) {
                user.setGender(GenderEnum.male);
            } else {
                user.setGender(GenderEnum.female);
            }
            userRepository.save(user);
            returnMessage = new ReturnMessage(200, "Change profile success!").toJson();
        }

        return returnMessage;
    }

    // Function to get login user object
    public User getLoginUserObj() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        User user = userRepository.findByAccount(account);
        return user;
    }
}
