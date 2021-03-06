package Services;

import DAO.UserDAO;
import Models.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public boolean addUser(User user) {
        boolean result = false;
        if(!userDAO.isUserExist(user.getName())){
            userDAO.addUser(user);
            result = true;
        }
        return result;
    }

    public void deleteUser(String name) {
        userDAO.deleteUser(name);
    }

    public void changeUserName(String name, String newName) {
        userDAO.changeUserName(name, newName);
    }

    public void changeUserPassword(String name, String password) {
        userDAO.changeUserPassword(name, password);
    }

    public boolean validateUser(User user){
        return userDAO.validateUser(user);
    }

    public boolean isUserExist(String name) {
        return userDAO.isUserExist(name);
    }
}
