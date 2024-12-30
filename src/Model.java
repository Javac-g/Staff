import java.util.ArrayList;

public class Model {
    private ArrayList<User> userlist = new ArrayList<>();

    public User addUser(String name, int age, int id){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setId(id);
        userlist.add(user);
        return user;
    }

    public User findUser(int id){
        for(User x: userlist){
            if (x.getId() == id){
                return x;
            }
        }
        return null;
    }
    public User updateUser(int id, int newAge, String newName){
        User x = findUser(id);
        if (x != null){
            x.setName(newName);
            x.setAge(newAge);
            return x;

        }
        return null;
    }
    public Integer deleteUser(String name){
        int index = -1;
        for (int i = 0; i < userlist.size(); i++){
            if (userlist.get(i).getName().equals(name)){
                index = i;
            }
        }
        if (index != -1){
            userlist.remove(index);
        }
        return index;
    }

}
