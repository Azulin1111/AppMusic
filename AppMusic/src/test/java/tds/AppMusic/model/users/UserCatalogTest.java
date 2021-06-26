package tds.AppMusic.model.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.*;

public class UserCatalogTest {

     @Before
     public void setUp() {
          UserRepository.INSTANCE.getUsers().clear();
     }

     @After
     public void tearDown(){
          UserRepository.INSTANCE.getUsers().clear();
     }

     @Test
     public void getFirstUser() {
          Date d = Date.from(Instant.now());
          UserRepository.INSTANCE.addUser("Pepe", "Pepito123", false, "123456",
                   "pepito@gmail.com", d);

          User user = UserRepository.INSTANCE.getUsers().get(0);

          // Expected user
          User expected = new User("Pepe", "Pepito123", false, "123456",
                  "pepito@gmail.com", d);

          // Test
          assertEquals(user.getName(), expected.getName());
          assertEquals(user.getBirthday().toString(), expected.getBirthday().toString());
          assertEquals(user.getPassword(), expected.getPassword());
          assertEquals(user.getEmail(), expected.getEmail());
          assertEquals(user.getNickname(), expected.getNickname());
          assertEquals(user.isPremium(), expected.isPremium());
     }

}
