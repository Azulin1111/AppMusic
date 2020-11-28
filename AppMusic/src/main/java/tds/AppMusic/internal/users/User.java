package tds.AppMusic.internal.users;

import java.time.LocalDate;
import java.util.Date;

// TODO add documentation
public class User {

    /**
     * Default prize for Premium upgrade. Subject to change (TODO)
     */
    public static final double PREMIUM_PRIZE = 20;

    private final String name;
    private boolean premium;

    private final String nickname;
    private final String password;
    private final LocalDate birthday;
    private final String email;

    public User(String name, String nickname, boolean premium, String password, String email, LocalDate birthday) {
        this.name = name;
        this.nickname = nickname;
        this.premium = premium;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isPremium() {
        return premium;
    }

    public static double getPremiumPrize() {
        return PREMIUM_PRIZE;
    }

    public String getName() {
        return name;
    }

    public boolean premiumPayment() {
        return premium;
    }
}
