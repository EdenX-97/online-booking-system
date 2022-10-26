package net.obs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.concurrent.TimeUnit;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import net.obs.models.User;
import net.obs.repositories.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
public class UserRepTests {
	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// Implement 4 CRDU test methods
	@Test
	public void createUser() {
		User user = new User();
		String encodedPassword = passwordEncoder.encode("Wz123456789");
		user.setAccount("testacc");
		user.setPassword(encodedPassword);
		user.setMatchPassword(encodedPassword);
		user.setFirstname("test");
		user.setLastname("test");
		user.setEmail("test@outlook.com");
		userRepository.save(user);
		User savedUser = userRepository.findByAccount("testacc");
		assertEquals(savedUser.getEmail(), "test@outlook.com");
	}

	@Test
	public void readUser() {
		User userLogin = userRepository.findByEmail("test@outlook.com");
		assertEquals(userLogin.getAccount(), "testacc");
	}

	@Test
	public void updateUser() {
		User user = userRepository.findByAccount("testacc");
		user.setAccount("testaccup");
		userRepository.save(user);
		User updatedUser = userRepository.findByAccount("testaccup");
		assertEquals(updatedUser.getEmail(), user.getEmail());
	}

	@Test
	public void deleteUser() {
		User deletedUser = userRepository.findByAccount("testaccup");
		userRepository.delete(deletedUser);
		User user = userRepository.findByAccount("testaccup");
		assertNull(user);
	}

}
