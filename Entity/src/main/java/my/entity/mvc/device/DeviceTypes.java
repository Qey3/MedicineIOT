package my.entity.mvc.device;

import lombok.Data;
import lombok.NoArgsConstructor;
import my.entity.mvc.user.RoleName;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DeviceTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeviceTypeName deviceTypeName;
}
