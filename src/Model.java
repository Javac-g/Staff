import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class Model {
    private final ArrayList<UserPattern> userList = new ArrayList<>();  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static boolean isValidEmail(String email) {
        // Compile regex into a Pattern
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        // Match the input email against the pattern
        return pattern.matcher(email).matches();
    }

    public User addUser(String firstName, String lastName, int age, String email){
        User user = new User();
        for (UserPattern x : userList){
            if(x.getEmail().equals(email)){
                throw new IllegalArgumentException("Email already in system");
            }

        }
        user.setEmail(email);
        if (age < 18){
            throw new IllegalArgumentException("User is underage");
        }else{
            user.setAge(age);
        }


        user.setFirstName(firstName);
        user.setLastName(lastName);
        userList.add(user);
        return user;
    }

    public UserPattern findUser(String email){

        for (UserPattern z : userList){
            if (z.getEmail().equals(email)){
                return z;
            }
        }
        return null;
    }
    private UserPattern internalSearch(String email){
        UUID id = null;
        for (UserPattern z : userList){
            if (z.getEmail().equals(email)){
                id = z.getID();
            }
        }
        for(UserPattern x: userList){
            if (Objects.equals(x.getID(), id)){
                return x;
            }
        }
        return null;
    }
    public UserPattern updateUser(String email, int newAge,String newFirstName, String newLastName,String newEmail){

        UserPattern x = internalSearch(email);

        if (x != null ){
            if (newFirstName != null && !newFirstName.isEmpty()){
                x.setFirstName(newFirstName);
            }
            if (newLastName != null && !newLastName.isEmpty()){
                x.setFirstName(newFirstName);
            }
            if (newEmail != null && !newEmail.isEmpty()){
                for (UserPattern y : userList){
                    if (y.getEmail().equals(newEmail)){
                        throw new IllegalArgumentException("Email is already in system");
                    }
                }
                x.setFirstName(newFirstName);
            }
            if ( !isValidEmail(email) || !isValidEmail(newEmail) ) {

                throw new IllegalArgumentException("Invalid email format: " + email);
            }
            x.setEmail(newEmail);
            if (newAge < 18){
                throw new IllegalArgumentException("User is underage");
            }else{
                x.setAge(newAge);
            }

            return x;

        }
        return null;
    }
    public Integer deleteUser(String email){
        int index = -1;
        UserPattern user =internalSearch(email);
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getID().equals(user.getID())){
                index = i;
            }
        }
        if (index != -1){
            userList.remove(index);
        }
        return index;
    }

}
