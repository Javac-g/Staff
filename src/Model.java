import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class Model {

    private final ArrayList<UserPattern> userList = new ArrayList<>();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    public static void log(String type, UserPattern user){
        byte[] data = ("Type: " + type + "/nID: " + user.getID() + "Name: " + user.getFirstName() + " " + user.getLastName()).getBytes();

        try(FileOutputStream fileInputStream = new FileOutputStream("log.dat", true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream)){

            byteArrayOutputStream.write(data);
            byteArrayOutputStream.writeTo(fileInputStream);
            dataOutputStream.writeUTF("Email: " + user.getEmail() + "/nAge: " + user.getAge());



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidEmail(String email) {
        try {

            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            return pattern.matcher(email).matches();

        } catch (Exception e) {
            System.err.println("Error validating email: " + e.getMessage());
            return false;
        }
    }

    public User addUser(String firstName, String lastName, int age, String email) {
        try {
            User user = new User();
            for (UserPattern x : userList) {
                if (x.getEmail().equals(email)) {
                    throw new IllegalArgumentException("Email already in system");
                }
            }
            user.setEmail(email);

            if (age < 18) {
                throw new IllegalArgumentException("User is underage");
            } else {
                user.setAge(age);
            }
            if(firstName.isEmpty() && lastName.isEmpty()){
                throw new IllegalArgumentException("Name parameters should be filled");
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userList.add(user);
            log("Added: ",user);
            return user;
        } catch (Exception e) {
            System.err.println("Error adding user: " + e.getMessage());
            return null;
        }
    }

    public UserPattern findUser(String email) {
        try {
            for (UserPattern z : userList) {
                if (z.getEmail().equals(email)) {
                    log("Searched,Founded: ",z);
                    return z;
                }
            }
            Person person = new Person(0,"John","Dou",email);
            log("Searched,Not found: ",person);
            return null;
        } catch (Exception e) {
            System.err.println("Error finding user: " + e.getMessage());
            return null;
        }
    }



    public UserPattern updateUser(String email, int newAge, String newFirstName, String newLastName, String newEmail) {
        try {
            UserPattern x = findUser(email);

            if (x != null) {
                log("Searched,To Update: ",x);
                if (newFirstName != null && !newFirstName.isEmpty()) {
                    x.setFirstName(newFirstName);
                }
                if (newLastName != null && !newLastName.isEmpty()) {
                    x.setLastName(newLastName);
                }
                if (newEmail != null && !newEmail.isEmpty()) {
                    for (UserPattern y : userList) {
                        if (y.getEmail().equals(newEmail)) {
                            throw new IllegalArgumentException("Email is already in system");
                        }
                    }
                }
                if (!isValidEmail(email) || !isValidEmail(newEmail)) {
                    throw new IllegalArgumentException("Invalid email format: " + email);
                }
                x.setEmail(newEmail);

                if (newAge < 18) {
                    throw new IllegalArgumentException("User is underage");
                } else {
                    x.setAge(newAge);
                }
                log("Updated: ",x);
                return x;
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            return null;
        }
    }

    public Integer deleteUser(String email) {
        try {

            int index = -1;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getEmail().equals(email)) {
                    index = i;
                    break;
                }
            }

            log("Deleted: ",userList.get(index));
            if (index != -1) {
                userList.remove(index);
                return index;
            } else {
                throw new IllegalArgumentException("User not found in list");
            }
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return null;
        }
    }
}
