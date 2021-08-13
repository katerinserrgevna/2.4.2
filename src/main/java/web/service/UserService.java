package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(int id);
    User getUserByUserName(String userName);
    void saveUser(User user);
    User update(User user);
    void delete(int id);
}
