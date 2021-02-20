package tds.AppMusic.internal;

import tds.AppMusic.internal.users.User;

import java.time.LocalDate;

public class Controller { //TODO whole class
    private User currentUser;

    public Controller(){
        currentUser = new User("Evangeline", "Evangeline", false,
                "123345","pepitaEmail", LocalDate.of(2020, 1,1 ));
    }
    public void a() {
        return;
    }

    public String getCurrentUser(){
        return currentUser.getName();
    }
}
