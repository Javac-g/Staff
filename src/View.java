import java.util.Scanner;
import java.util.regex.Pattern;

public class View {

    private static final Scanner scanner = new Scanner(System.in);

    public void print_menu(){
        System.out.println("_____________CHOOSE MENU OPTION_____________");
        System.out.println("1 - add user");
        System.out.println("2 - find user");
        System.out.println("3 - update user");
        System.out.println("4 - delete user");
        System.out.println("5 - exit");
    }
    public void print_message(String msg){
        System.out.println(msg);
    }
    public void print_data(UserPattern user){
        System.out.println("User first name: " + user.getFirstName());
        System.out.println("User last name: " + user.getLastName());
        System.out.println("User age: " + user.getAge());
        System.out.println("User Email: " + user.getEmail());

    }

    public Integer getNum(String msg){
        System.out.println(msg);
        return scanner.nextInt();
    }
    public String getStr(String msg){
        System.out.println(msg);
        return scanner.next();
    }


}
