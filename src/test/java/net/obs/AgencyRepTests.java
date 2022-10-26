package net.obs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import net.obs.enums.AgencyTypeEnum;
import net.obs.models.Agency;
import net.obs.repositories.AgencyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
public class AgencyRepTests {
    @Autowired
    AgencyRepository agencyRepository;

    // Implement 4 CRDU test methods
    @Test
    public void createAgency() {
        Agency agency = new Agency();
        agency.setType(AgencyTypeEnum.clinic);
        agency.setLongitude((float) 1.1);
        agency.setLatitude((float) 1.1);
        agency.setAgencyName("Test");
        agency.setAddress("Test");
        agency.setSuburb("Test");
        agency.setStates("Test");
        agency.setPostCode(1111);
        agencyRepository.save(agency);
        Agency savedAgency = agencyRepository.findByAgencyName("Test");
        assertEquals(savedAgency.getAddress(), "Test");
    }

    @Test
    public void readAgency() {
        Agency agency = agencyRepository.findByAgencyName("Test");
        assertEquals(agency.getStates(), "Test");
    }

    @Test
    public void updateAgency() {
        Agency agency = agencyRepository.findByAgencyName("Test");
        agency.setAgencyName("Test1");
        agency.setAddress("Test1");
        agencyRepository.save(agency);
        Agency updatedAgency = agencyRepository.findByAgencyName("Test1");
        assertEquals(updatedAgency.getAddress(), "Test1");
    }

    @Test
    public void deleteAgency() {
        Agency agency = agencyRepository.findByAgencyName("Test1");
        agencyRepository.delete(agency);
        Agency deletedAgency = agencyRepository.findByAgencyName("Test1");
        assertNull(deletedAgency);
    }
}
