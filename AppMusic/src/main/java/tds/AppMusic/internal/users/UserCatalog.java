package tds.AppMusic.internal.users;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public enum UserCatalog {
    INSTANCE; //Es un singleton
    private List<User> users;

    UserCatalog(){
        users = new LinkedList<>();
    }

    public void addUser(String name, String nickname, boolean premium, String password, String email, LocalDate birthday){
        User newUser = new User(name, nickname, premium, password, email, birthday);
        users.add(newUser);
    }

    public void removeUser(User user){ //TODO no se sabe si es hay que poner esta función
        users.remove(user);
    }

    public List<User> getUsers(){
        return users;
    }



}
