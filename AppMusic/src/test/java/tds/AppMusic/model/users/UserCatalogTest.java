package tds.AppMusic.model.users;

import org.junit.After;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UserCatalogTest {
     @Test
     public void getFirstUser(){
          UserCatalog.INSTANCE.addUser("Pepe", "Pepito123", false, "123456",
                   "pepito@gmail.com", LocalDate.of(2020, 7, 7));

          User user = UserCatalog.INSTANCE.getUsers().get(0);

          // Expected user
          User expected = new User("Pepe", "Pepito123", false, "123456",
                  "pepito@gmail.com", LocalDate.of(2020, 7, 7));

          // Test
          assertEquals(user, expected);
     }

     @After
     public void tearDown(){
          UserCatalog.INSTANCE.getUsers().clear();
     }
}
