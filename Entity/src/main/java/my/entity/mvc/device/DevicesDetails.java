package my.entity.mvc.device;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class DevicesDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Type(type = "text")
    private String description;
    @Column
    @Lob
    private byte[] picture;
    @OneToMany(mappedBy = "devicesDetails")
    private List<Device> device;

    @ManyToOne(cascade = CascadeType.ALL)
    private DeviceTypes deviceTypes;

}
