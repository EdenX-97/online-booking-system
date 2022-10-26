package net.obs.services;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import net.obs.models.User;
import net.obs.utils.ReturnMessage;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String sender;

    // Service to send verify email with verify code
    public ObjectNode sendVerifyEmail(String toEmailAddress, HttpSession session) {
        try {

            // Get random code and store them in session, the code will expire after session timeout
            Integer randomCode = (int) ((Math.random() * 9 + 1) * 100000); // set a dandom number of
            // six digit
            session.setAttribute("verifyCode", randomCode);
            session.setAttribute("verifyEmail", toEmailAddress);

            // Set and return the email
            String subject = "Verify code mail";
            String text = "Your verify code is: " + randomCode
                    + ", please complete the registration within an hour.";
            sendEmail(subject, toEmailAddress, text);

            // Return json message
            ObjectNode returnMessage =
                    new ReturnMessage(200, "Send verify email success!").toJson();
            return returnMessage;
        } catch (Exception e) {
            ObjectNode returnMessage = new ReturnMessage(400,
                    "Send verify email fail! Erroe message is: " + e.getMessage()).toJson();
            return returnMessage;
        }
    }

    // Service to send appointment information
    public ObjectNode sendAppointmentEmail(String appointmentDate) {
        try {
            // Set all appointment informations
            ObjectNode returnMessage = null;
            User user = userService.getLoginUserObj();
            if (user != null) {
                // Set and send the email
                String subject = "New appointment information";
                String toEmailAddress = "group19test@outlook.com";
                String text = "A new appointment, detailed information is as follows:\n"
                        + "Firstname: " + user.getFirstname() + "\n" + "Lastname: "
                        + user.getLastname() + "\n" + "Email: " + user.getEmail() + "\n";
                if (user.getPhone() != null) {
                    text += "Phone: " + user.getPhone() + "\n";
                }
                if (user.getAge() != null) {
                    text += "Age: " + user.getAge() + "\n";
                }
                if (user.getGender() != null) {
                    text += "Gender: " + user.getGender().toString() + "\n";
                }
                text += "Appointment date: " + appointmentDate;
                sendEmail(subject, toEmailAddress, text);

                // Return json message
                returnMessage = new ReturnMessage(200, "Send appointment email success!").toJson();
            } else {
                // Return json message
                returnMessage = new ReturnMessage(400, "Please login!").toJson();
            }

            return returnMessage;
        } catch (Exception e) {
            ObjectNode returnMessage = new ReturnMessage(400,
                    "Send appointment email fail! Erroe message is: " + e.getMessage()).toJson();
            return returnMessage;
        }
    }

    // Function to set and send email
    private void sendEmail(String subject, String toEmailAddress, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(sender);
        mail.setSubject(subject);
        mail.setTo(toEmailAddress);
        mail.setText(text);
        mailSender.send(mail);
    }
}
