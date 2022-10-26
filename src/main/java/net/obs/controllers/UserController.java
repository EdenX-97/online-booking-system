package net.obs.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.obs.models.User;
import net.obs.repositories.UserRepository;
import net.obs.services.MailService;
import net.obs.services.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Autowired
    UserRepository userRepository;

    // Controller function to get login user
    @GetMapping("/api/getLoginUser")
    public ObjectNode getLoginUser() {
        return userService.getLoginUser();
    }

    // Controller function to send verify email
    @PostMapping("/api/sendVerifyEmail")
    public ObjectNode sendVerifyEmail(@RequestParam(value = "email") @NotNull String toEmailAddress,
            HttpSession session) {
        return mailService.sendVerifyEmail(toEmailAddress, session);
    }

    // Controller function to register a new user
    @PostMapping("/api/register")
    public ObjectNode register(@RequestBody @Validated @NotNull User user,
            @RequestParam @NotNull int verifyCode, HttpSession session) {
        return userService.register(user, verifyCode, session);
    }

    // Controller function to send appointment email
    @PostMapping("/api/sendAppointmentEmail")
    public ObjectNode sendAppointmentEmail(@RequestParam @NotNull String appointmentDate) {
        return mailService.sendAppointmentEmail(appointmentDate);
    }

    // Controller function to change login user password
    @PostMapping("/api/changePassword")
    public ObjectNode changePassword(@RequestParam @NotNull String newPassword,
            @RequestParam @NotNull String nowPassword) {
        return userService.changePassword(newPassword, nowPassword);
    }

    // Controller function to change login user profile
    @PostMapping("/api/changeProfile")
    public ObjectNode changeProfile(@RequestParam @NotNull String firstname,
            @RequestParam @NotNull String lastname, @RequestParam @NotNull Integer age,
            @RequestParam @NotNull String gender, @RequestParam @NotNull String phone) {
        return userService.changeProfile(firstname, lastname, age, gender, phone);
    }
}
