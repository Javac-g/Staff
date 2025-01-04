public class Controller {

    public void initController() {

        Model model = new Model();
        View view = new View();
        boolean loop = true;

        while (loop) {

            view.print_menu();
            int choose = view.getNum("ENTER: ");

            try {
                switch (choose) {
                    case 1 -> {
                        view.print_message("Adding new user");
                        String firstName = null;
                        String lastName = null;
                        int age = 0;
                        String email = null;

                        boolean validInput = false;
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
                        view.print_message("User added: ");
                        view.print_data(user);
                    }
                    case 2 -> {
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
                    case 3 -> {
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
                                        view.getStr("Enter new Last Name: ")
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
                    case 4 -> {
                        view.print_message("Delete menu: ");
                        Integer index = null;
                        boolean validInput = false;
                        while (!validInput) {
                            try {
                                String nameToDelete = view.getStr("Enter name to delete");
                                index = model.deleteUser(nameToDelete);
                                validInput = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        if (index != -1) {
                            view.print_message("Deleted user id: " + index);
                        }
                    }
                    case 5 -> {
                        view.print_message(" * Bye * Bye * ");
                        loop = false;
                    }
                    default -> view.print_message("Wrong menu number");
                }

            } catch (IllegalStateException i) {
                System.out.println("UUID: " + i.getMessage());
            }
        }
    }
}
