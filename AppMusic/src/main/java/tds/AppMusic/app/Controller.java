package tds.AppMusic.app;

import tds.AppMusic.model.users.User;

import javax.swing.*;
import java.time.LocalDate;

public enum Controller { //TODO whole class
    INSTANCE;
    private PersistenceManager persistenceManager = new PersistenceManager();
    private User currentUser;

    private Controller(){
        currentUser = new User("Evangeline", "Evangeline", false,
                "123345","pepitaEmail", LocalDate.of(2020, 1,1 ));
    }

    public String getCurrentUser(){
        return currentUser.getName();
    }

    public DefaultListModel filterSongs(String interpreter, String title, String genre){ //TODO
        DefaultListModel model = new DefaultListModel();
        model.add(model.size(), interpreter + ": " + title);

        for(int i =0; i<40; i++)
            model.add(model.size(), genre + ": adgilla");

        return model;
    }


    public void play() { //TODO

    }

    public void createPlayList(String name){ //TODO
        this.currentUser.createPlayList(name);
    }


    public void cargarCanciones(){

    }

    public boolean login(String username, String password){
        return persistenceManager.login(username, password);
    }
}
