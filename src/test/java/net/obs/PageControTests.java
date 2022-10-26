package net.obs;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class PageControTests {
    // Test all page controllers
    @Test
    public void testMainPage() {
        int res = callContro("/").getStatusCodeValue();
        assertEquals(200, res);
    }

    @Test
    public void testProfilePage() {
        int res = callContro("/profile").getStatusCodeValue();
        assertEquals(200, res);
    }

    @Test
    public void testClinicPage() {
        int res = callContro("/clinic").getStatusCodeValue();
        assertEquals(200, res);
    }

    @Test
    public void testHospitalPage() {
        int res = callContro("/hospital").getStatusCodeValue();
        assertEquals(200, res);
    }

    @Test
    public void testDetailPage() {
        int res = callContro("/aboutPage").getStatusCodeValue();
        assertEquals(200, res);
    }

    @Test
    public void testErrorPage() {
        int res = callContro("/error").getStatusCodeValue();
        assertEquals(200, res);
    }

    @Test
    public void testRegisterPage() {
        int res = callContro("/register").getStatusCodeValue();
        assertEquals(200, res);
    }

    @Test
    public void testReservePage() {
        int res = callContro("/reserve").getStatusCodeValue();
        assertEquals(200, res);
    }

    // Function to call controller and get result
    private ResponseEntity<String> callContro(String url) {
        // Use restTemplate to send get
        RestTemplate restTemplate = new RestTemplate();
        String callUrl = "http://localhost:8080" + url;
        ResponseEntity<String> res = restTemplate.getForEntity(callUrl, String.class);
        return res;
    }

}
