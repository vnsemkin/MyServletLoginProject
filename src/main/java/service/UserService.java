package service;

import dao.UserDao;
import dto.UserDto;
import exception.UserNotFoundException;
import model.User;

import java.util.Objects;

public class UserService {
    private final UserDao userDao = UserDao.getInstance();

    public UserDto createUser(String name, String email, String password) throws UserNotFoundException {
        long id = userDao.getUsers().size();
        UserDto userDto =  userDao.createUser(new User(id, name, email, password));
        if(Objects.nonNull(userDto)){
            return userDto;
        }else{
            throw new UserNotFoundException("User didnt created");
        }
    }
}
