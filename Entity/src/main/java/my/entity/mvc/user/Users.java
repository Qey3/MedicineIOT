package my.entity.mvc.user;

import lombok.AllArgsConstructor;
import my.entity.mvc.device.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    private String password;
    private String email;
    private String birthday;
    @OneToMany(mappedBy = "user")
    private List<Device> usersDevice;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Roles> roles;
}
