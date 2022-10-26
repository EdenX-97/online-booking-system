package net.obs;

import static org.junit.Assert.assertEquals;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class AgencyControTests {
    @Test
    public void testGetAgency() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/getAgency";
        ObjectNode res = restTemplate.getForObject(url, ObjectNode.class);
        assertEquals(200, res.get("code").asInt());
    }

    @Test
    public void testGetClinic() {
        RestTemplate restTemplate = new RestTemplate();
        String url =
                "http://localhost:8080/api/getClinic?longitude={longitude}&latitude={latitude}";
        ObjectNode res = restTemplate.getForObject(url, ObjectNode.class, 0, 0);
        assertEquals(200, res.get("code").asInt());
    }

    @Test
    public void testGetHospital() {
        RestTemplate restTemplate = new RestTemplate();
        String url =
                "http://localhost:8080/api/getHospital?longitude={longitude}&latitude={latitude}";
        ObjectNode res = restTemplate.getForObject(url, ObjectNode.class, 0, 0);
        assertEquals(200, res.get("code").asInt());
    }
}
