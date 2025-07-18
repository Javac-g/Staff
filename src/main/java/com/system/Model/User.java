package main.java.com.system.Model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class User implements UserPattern{

    private int age;
    private String firstName, lastName, email;
    private final UUID id;
    private final LocalDateTime creationDate;


    public User(){
        Supplier<UUID> randomSupplier = UUID ::randomUUID;
        Stream<UUID> infiniteStream = Stream.generate(randomSupplier);
        id = infiniteStream.findFirst().orElseThrow(()->new IllegalStateException("No UUID generated"));
        this.creationDate = LocalDateTime.now();
    }

    private User(String email) {
        this.email = email;
        this.firstName = "John|Jane";
        this.lastName = "Dou";
        this.age = 0;
        id = null;
        this.creationDate = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return creationDate;
    }

    public int getAge(){
        return age;
    }

    @Override
    public UUID getID() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User getUnknown(String email){
        return new User(email);
    }
    @Override
    public int hashCode(){
        int hash = 7;
        hash = hash * 3 + Objects.hashCode(age);
        hash = hash * 3 + Objects.hashCode(id);
        hash = hash * 3 + Objects.hashCode(firstName);
        hash = hash * 3 + Objects.hashCode(email);
        hash = hash * 3 + Objects.hashCode(lastName);
        return hash;
        /* return Objects.hash(age, id, name)  / more compact realisation */
    }
    @Override
    public boolean equals(Object object){
        if (object == this)return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        User user = (User)object;
        return  Objects.equals(this.firstName, user.getFirstName()) &&
                Objects.equals(this.lastName, user.getLastName()) &&
                Objects.equals(this.age, user.getAge()) &&
                Objects.equals(this.id, user.getID()) &&
                Objects.equals(this.email, user.getEmail());

    }
    @Override
    public String toString(){
        return  String.format(
                "ID: %s%nName: %s %s%nEmail: %s%nAge: %d%nDate: %s%n",
                id != null ? id : "null",
                firstName != null ? firstName : "Unknown",
                lastName != null ? lastName : "Unknown",
                email != null ? email : "Unknown",
                age,
                creationDate != null ? creationDate : "Unknown"
        );

    }

}
