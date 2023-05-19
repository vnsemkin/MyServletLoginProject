package dto;

import lombok.*;
import model.User;

@Data
public class UserDto {
    private String name;
    private String email;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
