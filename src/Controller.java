public class Controller {

    public void initController(){


        Model model = new Model();
        View view = new View();
        boolean loop = true;
        while (loop){


            view.print_menu();
            int choose = view.getNum("ENTER: ");


            switch (choose){
                case 1 ->{
                    view.print_message("Adding new user");
                    User user = model.addUser(view.getStr("Enter Email: "),
                                              view.getStr("Enter first name:"),
                                              view.getStr("Enter last name:"),
                                              view.getNum("Enter age: "));

                    view.print_message("User added: ");
                    view.print_data(user);

                }
                case 2 ->{
                    view.print_message("Search menu:");
                    UserPattern user = model.findUser(view.getStr("Enter Email of users to find: "));
                    if (user != null){
                        view.print_data(user);
                    }else {
                        view.print_message("User not found");
                    }
                }
                case 3 ->{
                    view.print_message("Update menu:");
                    UserPattern user = model.updateUser(view.getStr("Enter Email of user to update: "),
                                                 view.getNum("Enter new Age:"),
                                                 view.getStr("Enter new First Name: "),
                                                 view.getStr("Enter new Last Name: "),
                                                  view.getStr("Enter new Last Name: ")

                    );

                    if (user != null){
                        view.print_data(user);
                    }else {
                        view.print_message("Not Found");
                    }

                }
                case 4 ->{
                    view.print_message("Delete menu: ");
                    Integer index = model.deleteUser(view.getStr("Enter name to delete"));
                    if(index != -1){
                        view.print_message("Deleted user id: " + index);
                    }
                }
                case 5 -> {
                    view.print_message(" * Bye * Bye * ");
                    loop = false;
                }
                default -> view.print_message("Wrong menu number");
            }




        }















    }

}
