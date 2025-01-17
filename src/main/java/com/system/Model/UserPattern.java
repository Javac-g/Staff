package main.java.com.system.Model;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserPattern {
     int getAge();
     UUID getID();
    void setAge(int age);

    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setEmail(String email);

    String getFirstName();
    String getLastName();
    String getEmail();
    LocalDateTime getDate();
}
