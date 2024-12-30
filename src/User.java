import java.util.Objects;

public class User {

    private int age,id;
    private String name;

    public User(){

    }

    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode(){
        int hash = 7;
        hash = hash * 3 + Objects.hashCode(age);
        hash = hash * 3 + Objects.hashCode(id);
        hash = hash * 3 + Objects.hashCode(name);
        return hash;
        /* return Objects.hash(age, id, name)  / more compact realisation */
    }
    @Override
    public boolean equals(Object object){
        if (object == this)return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        User user = (User)object;
        return  Objects.equals(this.name, user.getName()) &&
                Objects.equals(this.age, user.getAge()) &&
                Objects.equals(this.id, user.getId());

    }


}
