package main.java.com.system.Model;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Model {

    private final ArrayList<UserPattern> userList = new ArrayList<>();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Logger logger = LoggerFactory.getLogger(Model.class);


    public static void log(String type, UserPattern user){

        logger.info("Action: {}, Details: {}", type,user.toString());
        try(FileOutputStream fileOutputStream = new FileOutputStream("info.log", true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream)){

            dataOutputStream.writeUTF("Type: " + type + "\n");
            dataOutputStream.writeUTF("ID: " + user.getID() + "\n");
            dataOutputStream.writeUTF("Name: " + user.getFirstName() + " " + user.getLastName() + "\n");
            dataOutputStream.writeUTF("Email: " + user.getEmail() + "\n");
            dataOutputStream.writeUTF("Age: " + user.getAge() + "\n");
            dataOutputStream.writeUTF("Date: " + user.getDate() + "\n");
            byteArrayOutputStream.writeTo(fileOutputStream);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean ValidEmail(String email) {
        try {

            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            return !pattern.matcher(email).matches();

        } catch (Exception e) {
            System.err.println("Error validating email: " + e.getMessage());
            return true;
        }
    }

    public UserPattern addUser(String firstName, String lastName, int age, String email) {
        try {
            User user = new User();
            for (UserPattern x : userList) {
                if (x.getEmail().equals(email)) {
                    throw new IllegalArgumentException("Email already in system");
                }
            }
            user.setEmail(email);

            if (age < 18) {
                throw new IllegalArgumentException("Model - User is underage");
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
            return User.getUnknown(email);
        }
    }

    public UserPattern findUser(String email) {
        try {
            UserPattern foundUser = userList.stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .orElseGet(() -> {
                        User unknownUser = User.getUnknown(email);
                        log("Searched, Not found: ", unknownUser);
                        return unknownUser;
                    });



            log("Searched,founded: ",foundUser);
            return foundUser;
        } catch (Exception e) {
            log("Error,Not found: ",User.getUnknown(email));
            System.err.println("Error finding user: " + e.getMessage());
            return  User.getUnknown(email);
        }
    }



    public UserPattern updateUser(String email,String newFirstName, String newLastName, String updated_email , int updated_age) {
        try {
            UserPattern x = findUser(email);
            if (x == null) {
                throw new IllegalArgumentException("Mogel - User not found for email: " + email);
            }


            log("Searched,To Update: ", x);

            if (newFirstName != null && !newFirstName.isEmpty()) {
                x.setFirstName(newFirstName);
            }

            if (newLastName != null && !newLastName.isEmpty()) {
                x.setLastName(newLastName);
            }
            if (updated_email != null && !updated_email.isEmpty()) {
                if (ValidEmail(updated_email)) {
                    throw new IllegalArgumentException("Invalid email format: " + email);
                }
                for (UserPattern y : userList) {
                    if (y.getEmail().equals(updated_email)) {
                        throw new IllegalArgumentException("Email is already in system");
                    }
                }
                x.setEmail(updated_email);
            }

            if (updated_age < 18 ) {
                throw new IllegalArgumentException("main.java.com.system.Model.User is underage");
            } x.setAge(updated_age);

            log("Updated: ",x);
            return x;
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            return User.getUnknown(email);
        }
    }

    public Integer deleteUser(String email) {
        try {
            // Find the index of the user
            int index = IntStream.range(0, userList.size())
                    .filter(i -> userList.get(i).getEmail().equals(email))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Model - delete.User not found in list"));

            // Log and remove the user
            log("Deleted: ", userList.get(index));
            userList.remove(index);
            return index;

        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return 0;
        }
    }
}
