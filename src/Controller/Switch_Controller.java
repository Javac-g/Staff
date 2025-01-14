package Controller;

import Model.Model;
import Model.UserPattern;
import View.View;

public class Switch_Controller {


    private final Model model = new Model();
    private final View view = new View();



    public View getView() {
        return view;
    }


    public void case_one(){

        String firstName = null;
        String lastName = null;
        int age = 0;
        String email = null;
        boolean validInput = false;

        view.print_message("Adding new user");

        while (!validInput) {

            try {
                firstName = view.getStr("Enter first name:");
                lastName = view.getStr("Enter last name:");
                age = view.getNum("Enter age: ");
                email = view.getStr("Enter Email: ");

                if (Model.ValidEmail(email)) {
                    throw new IllegalArgumentException("Invalid email format: " + email);
                }

                validInput = true; // Exit loop if no exceptions
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        UserPattern user = model.addUser(firstName, lastName, age, email);
        if (user != null){
            view.print_message("Model.User added: ");
            view.print_data(user);
        }

    }

    public void case_two(){
        view.print_message("Search menu:");

        UserPattern user = null;

        boolean validInput = false;

        while (!validInput) {
            try {
                String email = view.getStr("Enter Email of users to find: ");

                if (Model.ValidEmail(email)) {
                    throw new IllegalArgumentException("Invalid email format: " + email);
                }

                user = model.findUser(email);
                validInput = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (user != null) {

            view.print_data(user);

        } else {

            view.print_message("Model.User not found");
        }
    }

    public void case_three(){

        view.print_message("Update menu:");

        boolean validInput = false;

        while (!validInput) {
            try {
                String email = view.getStr("Enter Email of user to update: ");
                if (Model.ValidEmail(email)) {
                    throw new IllegalArgumentException("Invalid email format: " + email);
                }
                UserPattern existingUser = model.findUser(email);
                if (existingUser == null) {
                    throw new IllegalArgumentException("Model.User not found for email: " + email);
                }
                // Gather update details
                String newFirstName = (view.getStr("Update first name? y/n").equalsIgnoreCase("y"))
                        ? view.getStr("Enter new first name: ") : existingUser.getFirstName();

                String newLastName = (view.getStr("Update last name? y/n").equalsIgnoreCase("y"))
                        ? view.getStr("Enter new last name: ") : existingUser.getLastName();

                String newEmail = (view.getStr("Update email? y/n").equalsIgnoreCase("y"))
                        ? view.getStr("Enter new email: ") : null;

                int newAge = (view.getStr("Update age? y/n").equalsIgnoreCase("y"))
                        ? view.getNum("Enter new age: ") : existingUser.getAge();

                // Call model's update method
                UserPattern updatedUser = model.updateUser(email, newFirstName, newLastName, newEmail, newAge);

                view.print_message("Model.User successfully updated:");
                view.print_data(updatedUser);

                view.print_message("Model.User updated successfully: " + updatedUser);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void case_four(){
        view.print_message("Delete menu: ");
        int index = -1;


            try {
                String email = view.getStr("Enter email to delete");
                index = model.deleteUser(email);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }


        if (index != -1) {
            view.print_message("Deleted user id: " + index);
        }
    }
}
