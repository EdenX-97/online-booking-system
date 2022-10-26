package net.obs.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import net.obs.repositories.UserRepository;
import net.obs.utils.ReturnMessage;

@Component
public class CustomizeAuthenticationLogout implements LogoutSuccessHandler {
    @Autowired
    UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        ObjectNode returnMessage = null;
        if (authentication == null) {
            returnMessage =
                    new ReturnMessage(400, "You cannot logout because you do not login.").toJson();
        } else {
            returnMessage = new ReturnMessage(200, "Logout success!").toJson();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(returnMessage.toString());
    }
}
