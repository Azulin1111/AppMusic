package tds.AppMusic.model.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.*;

public class UserCatalogTest {

     @Before
     public void setUp() { //TODO el clear() viola el experto
          UserRepository.INSTANCE.getAllUsers().clear();
     }

     @After
     public void tearDown(){
          UserRepository.INSTANCE.getAllUsers().clear();
     }

     @Test
     public void getFirstUser() {
          Date d = Date.from(Instant.now());
          User storedUser = new User("Pepe", "Requena Nieto", "Pepito123", false, "123456",
                  "pepito@gmail.com", d);
          UserRepository.INSTANCE.storeUser(storedUser);

          User user = UserRepository.INSTANCE.getAllUsers().iterator().next();

          // Expected user
          User expected = new User("Pepe", "Requena Nieto","Pepito123", false, "123456",
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
