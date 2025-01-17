package main.java.com.system.Controller;

public class Controller {

    boolean loop = true;
    Switch_Controller switchController = new Switch_Controller();


    public void initController() {

        while (loop) {

            switchController.getView().print_menu();
            int choose =  switchController.getView().getNum("ENTER: ");

            try {
                switch (choose) {
                    case 1 -> switchController.case_one();
                    case 2 -> switchController.case_two();
                    case 3 -> switchController.case_three();
                    case 4 -> switchController.case_four();
                    case 5 -> {
                        switchController.getView().print_message(" * Bye * Bye * ");
                        loop = false;
                    }
                    default -> switchController.getView().print_message("Wrong menu number");
                }
            } catch (IllegalStateException i) {
                System.out.println("UUID: " + i.getMessage());
            }
        }
    }
}
