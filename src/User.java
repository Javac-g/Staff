import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class User implements UserPattern{

    private int age;
    private String firstName, lastName, email;
    private UUID id;


    public User(){
        Supplier<UUID> randomSupplier = UUID ::randomUUID;
        Stream<UUID> infiniteStream = Stream.generate(randomSupplier);
        id = infiniteStream.findFirst().orElseThrow(()->new IllegalStateException("No UUID generated"));
    }

    public int getAge(){
        return age;
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

    @Override
    public int hashCode(){
        int hash = 7;
        hash = hash * 3 + Objects.hashCode(age);
        hash = hash * 3 + Objects.hashCode(id);
        hash = hash * 3 + Objects.hashCode(firstName);
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
                Objects.equals(this.id, user.getId());

    }


}
