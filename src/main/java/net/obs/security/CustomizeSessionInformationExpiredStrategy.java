package net.obs.security;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import net.obs.utils.ReturnMessage;

@Component
public class CustomizeSessionInformationExpiredStrategy
        implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent expiredEvent)
            throws IOException {
        HttpServletResponse response = expiredEvent.getResponse();
        ObjectNode returnMessage =
                new ReturnMessage(200, "Landing timeout or remote landing!").toJson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(returnMessage.toString());
    }
}
