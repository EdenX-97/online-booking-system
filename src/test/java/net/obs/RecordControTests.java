package net.obs;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
public class RecordControTests {
    @Test
    public void testGetRecord() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Login first
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);

        String url = "http://localhost:8080/api/getRecord";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        ObjectNode changeRes =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, ObjectNode.class).getBody();
        assertEquals(changeRes.get("code").asInt(), 200);
    }

    @Test
    public void testMakeAppointment() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Login first
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);

        String url =
                "http://localhost:8080/api/makeAppointment?timeOfAppointment={timeOfAppointment}&agency_id={agency_id}";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        ObjectNode changeRes = restTemplate
                .postForEntity(url, httpEntity, ObjectNode.class, "2021-11-01 13:00", 1).getBody();
        assertEquals(changeRes.get("code").asInt(), 200);
    }

    @Test
    public void testDeleteAppointment() {
        // Use restTemplate to send post
        RestTemplate restTemplate = new RestTemplate();

        // Login first
        ResponseEntity<ObjectNode> res = longinAndGetRes(restTemplate);

        String url =
                "http://localhost:8080/api/deleteAppointment?timeOfAppointment={timeOfAppointment}&agency_id={agency_id}";
        HttpEntity<JSONObject> httpEntity = new HttpEntity(getHeaders(res));
        ObjectNode changeRes = restTemplate
                .postForEntity(url, httpEntity, ObjectNode.class, "2021-11-01 13:00", 1).getBody();
        assertEquals(changeRes.get("code").asInt(), 200);
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

    // Function to get http entity from previous response
    private HttpHeaders getHeaders(ResponseEntity<ObjectNode> res) {
        String cookie = res.getHeaders().get("Set-Cookie").get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
