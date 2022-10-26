package net.obs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import net.obs.enums.AgencyTypeEnum;
import net.obs.enums.StateEnum;
import net.obs.models.Agency;
import net.obs.models.Record;
import net.obs.models.User;
import net.obs.repositories.AgencyRepository;
import net.obs.repositories.RecordRepository;
import net.obs.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
public class RecodRepTests {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    // Implement 4 CRDU test methods
    @Test
    public void createRecord() {
        // Create user for test
        User user = new User();
        String encodedPassword = passwordEncoder.encode("Wz123456789");
        user.setAccount("testacc");
        user.setPassword(encodedPassword);
        user.setMatchPassword(encodedPassword);
        user.setFirstname("test");
        user.setLastname("test");
        user.setEmail("test@outlook.com");
        userRepository.save(user);

        // Create agency for test
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

        // Create record now
        Record record = new Record();
        record.setUser(user);
        record.setAgency(agency);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date tempTime = null;
        try {
            tempTime = simpleDateFormat.parse("2021-10-30 00:00:00");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        record.setRecordDatetime(tempTime);
        record.setState(StateEnum.book);

        recordRepository.save(record);
        Record savedRecord = recordRepository.findByRecordDatetimeAndAgencyAndStateAndUser(tempTime,
                agency, StateEnum.book, user);
        assertEquals(savedRecord.getUser().getAccount(), user.getAccount());
    }

    @Test
    public void readRecord() {
        Record record = recordRepository.findTopByOrderByIdDesc();
        assertEquals(record.getUser().getAccount(), "testacc");
    }

    @Test
    public void updateRecord() {
        Record record = recordRepository.findTopByOrderByIdDesc();
        record.setState(StateEnum.complate);
        recordRepository.save(record);
        Record updatedRecord = recordRepository.findTopByOrderByIdDesc();
        assertEquals(updatedRecord.getState(), StateEnum.complate);
    }

    @Test
    public void deleteRecord() {
        Record record = recordRepository.findTopByOrderByIdDesc();
        recordRepository.delete(record);
        Record deletedRecord = recordRepository.findTopByOrderByIdDesc();
        if (deletedRecord == null) {
            assertNull(deletedRecord);
        } else {
            assertNotEquals(record.getRecordDatetime(), deletedRecord.getRecordDatetime());
        }

        // Delete user and agency
        userRepository.delete(record.getUser());
        agencyRepository.delete(record.getAgency());
    }

}
