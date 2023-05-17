package dao;

import dto.UserDto;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDao {
    private static UserDao INSTANCE;
    private static List<User> users;

    private UserDao() {
        users = new ArrayList<>(Arrays.asList(new User(1L, "First", "first@gmail.com", "123"),
                new User(2L, "Second", "second@gmail.com", "123")));
    }

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
            return INSTANCE;
        }
        return INSTANCE;
    }

    public UserDto readUser(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return new UserDto(user);
            }
        }
        return null;
    }

    public UserDto createUser(User user) {
        users.add(user);
        return new UserDto(user);
    }

    public UserDto updateUser(User user) {
        deleteUser(user);
        createUser(user);
        return new UserDto(user);
    }

    public UserDto deleteUser(User user) {
        boolean isRemoved = users.remove(user);
        if (isRemoved) {
            return new UserDto(user);
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
