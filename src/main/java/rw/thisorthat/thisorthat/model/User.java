package rw.thisorthat.thisorthat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User implements Serializable {
    
    private static final long serialVersionUID = -774669304270373473L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
}
