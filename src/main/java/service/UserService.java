package service;

import dao.UserDao;
import dto.UserDto;
import exception.UserNotCreatedException;
import model.User;

import java.util.Objects;

public class UserService {
    private final UserDao userDao = UserDao.getInstance();

    public UserDto createUser(String name, String email, String password) throws UserNotCreatedException {
        long id = userDao.getUsers().size();
        UserDto userDto = userDao.createUser(new User(id, name, email, password));
        if (Objects.nonNull(userDto)) {
            return userDto;
        } else {
            throw new UserNotCreatedException("User not created");
        }
    }
}
