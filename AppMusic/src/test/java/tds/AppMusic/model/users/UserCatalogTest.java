package tds.AppMusic.model.users;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserCatalogTest {
     @Test
     public void getFirstUser(){
          UserRepository.INSTANCE.addUser("Pepe", "Pepito123", false, "123456",
                   "pepito@gmail.com", null);

          User user = UserRepository.INSTANCE.getUsers().get(0);

          // Expected user
          User expected = new User("Pepe", "Pepito123", false, "123456",
                  "pepito@gmail.com", null);

          // Test
          assertEquals(user, expected);
     }

     @After
     public void tearDown(){
          UserRepository.INSTANCE.getUsers().clear();
     }
}
