package net.obs;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.mail.imap.IMAPFolder;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
public class UserControTests {
    // Test register() and sendVerifyEmail()
    @Test
    public void testSendVerifyEmail() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // send code
        String url = "http://localhost:8080/api/sendVerifyEmail?email={email}";
        Map<String, String> param = new HashMap<String, String>();
        param.put("email", "group19test@outlook.com");
        ResponseEntity<ObjectNode> res =
                restTemplate.postForEntity(url, param, ObjectNode.class, param);

        // Get verify code and build user
        Integer verifyCode = Integer.parseInt(getCode());
        url = "http://localhost:8080/api/register?verifyCode={verifyCode}";
        ObjectNode user = new ObjectMapper().createObjectNode();
        user.put("account", "testacct");
        user.put("firstname", "test");
        user.put("lastname", "test");
        user.put("email", "group19test@outlook.com");
        user.put("password", "Wz12345678");
        user.put("matchPassword", "Wz12345678");
        HttpHeaders headers = getHeaders(res);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> httpEntity = new HttpEntity(user.toString(), headers);
        Map<String, Integer> registerParam = new HashMap<String, Integer>();
        registerParam.put("verifyCode", verifyCode);

        // Send post and get results and check
        ObjectNode registerRes = restTemplate
                .postForEntity(url, httpEntity, ObjectNode.class, registerParam).getBody();
        assertEquals(registerRes.get("code").asInt(), 200);
    }

    // Test login and getLoginUser()
    @Test
    public void testLogin() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Create params, send post and check value
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);
        assertEquals(res.getBody().get("code").asInt(), 200);

        // Use cookie to get login user and check
        String url = "http://localhost:8080/api/getLoginUser";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        ObjectNode getRes =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, ObjectNode.class).getBody();
        assertEquals(getRes.get("data").get("account").toString().toString(), "\"testacct\"");
    }

    // Test sendAppointmentEmail()
    @Test
    public void testSendAppointment() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Login first
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);

        // Use cookie to get login user and check
        String url =
                "http://localhost:8080/api/sendAppointmentEmail?appointmentDate={appointmentDate}";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        Map<String, String> param = new HashMap<String, String>();
        param.put("appointmentDate", "2021-10-30 00:00");
        ObjectNode getRes =
                restTemplate.postForEntity(url, httpEntity, ObjectNode.class, param).getBody();
        assertEquals(getRes.get("code").asInt(), 200);
    }

    // Test changeProfile()
    @Test
    public void testChangeProfile() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Login first
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);

        // Change the profile
        String url =
                "http://localhost:8080/api/changeProfile?firstname={firstname}&lastname={lastname}&age={age}&gender={gender}&phone={phone}";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        ObjectNode changeRes = restTemplate.postForEntity(url, httpEntity, ObjectNode.class, "ttt",
                "ttt", 20, "male", "0426222222").getBody();
        assertEquals(changeRes.get("code").asInt(), 200);
    }

    // Test /api/logout
    @Test
    public void testLogout() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Login first
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);

        // Logout and check
        String url = "http://localhost:8080/api/logout";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        ObjectNode changeRes =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, ObjectNode.class).getBody();
        assertEquals(changeRes.get("code").asInt(), 200);
    }

    // Test changePassword()
    @Test
    public void testChangePass() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Login first
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);

        // change the password
        String url =
                "http://localhost:8080/api/changePassword?newPassword={newPassword}&nowPassword={nowPassword}";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        Map<String, String> param = new HashMap<String, String>();
        param.put("newPassword", "Wz123456789");
        param.put("nowPassword", "Wz12345678");
        ResponseEntity<ObjectNode> changeRes =
                restTemplate.postForEntity(url, httpEntity, ObjectNode.class, param);
        assertEquals(changeRes.getBody().get("code").asInt(), 200);
    }

    // Function to login and get response
    private ResponseEntity<ObjectNode> longinAndGetRes(RestTemplate restTemplate) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        params.add("username", "testacct");
        params.add("password", "Wz12345678");
        String url = "http://localhost:8080/api/login";
        ResponseEntity<ObjectNode> res = restTemplate.postForEntity(url, params, ObjectNode.class);
        return res;
    }

    // Function to read email and get verify code
    private String getCode() {
        try {
            TimeUnit.SECONDS.sleep(10);
            // Set imap property and read email from mailbox
            String protocol = "imap";
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", "smtp.office365.com"); // 发件人的邮箱的 SMTP服务器地址
            props.put("mail.imap.starttls.enable", "true");

            Session session = Session.getInstance(props);
            session.setDebug(false);

            Store store = session.getStore(protocol);
            store.connect("smtp.office365.com", "group19test@outlook.com", "Wz12345678");
            IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");

            inbox.open(Folder.READ_WRITE);

            int messageCount = inbox.getMessageCount();
            String message = inbox.getMessage(messageCount).getContent().toString().split(",")[0];
            String code = message.substring(message.length() - 6);
            return code;
        } catch (Exception e) {
            return e.toString();
        }
    }

    // Function to get http entity from previous response
    private HttpHeaders getHeaders(ResponseEntity<ObjectNode> res) {
        String cookie = res.getHeaders().get("Set-Cookie").get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
