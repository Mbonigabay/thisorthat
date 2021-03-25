package rw.thisorthat.thisorthat.model.request;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;  
}
