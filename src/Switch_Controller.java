


public class Switch_Controller {


    public Model model = new Model();
    public View view = new View();

    public Model getModel() {
        return model;
    }

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

                if (!Model.isValidEmail(email)) {
                    throw new IllegalArgumentException("Invalid email format: " + email);
                }

                validInput = true; // Exit loop if no exceptions
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        User user = model.addUser(firstName, lastName, age, email);
        if (user != null){
            view.print_message("User added: ");
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

                if (!Model.isValidEmail(email)) {
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

            view.print_message("User not found");
        }
    }

    public void case_three(){

        view.print_message("Update menu:");

        UserPattern user = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                String email = view.getStr("Enter Email of user to update: ");
                if (!Model.isValidEmail(email)) {
                    throw new IllegalArgumentException("Invalid email format: " + email);
                }
                user = model.updateUser(
                        email,
                        view.getNum("Enter new Age:"),
                        view.getStr("Enter new First Name: "),
                        view.getStr("Enter new Last Name: "),
                        view.getStr("Enter new Email: ")
                );
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (user != null) {
            view.print_data(user);
        } else {
            view.print_message("Not Found");
        }
    }
    public void case_four(){
        view.print_message("Delete menu: ");
        int index = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                String email = view.getStr("Enter email to delete");
                index = model.deleteUser(email);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (index != -1) {
            view.print_message("Deleted user id: " + index);
        }
    }
}
