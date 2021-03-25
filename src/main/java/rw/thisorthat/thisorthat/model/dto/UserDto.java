package rw.thisorthat.thisorthat.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable{
    private static final long serialVersionUID = 2882044496229312557L;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
}
