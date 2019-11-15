package my.entity.mvc.device;

import lombok.AllArgsConstructor;
import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.user.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long serialNumber;
    private Date startUse;
    private Date endUse;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
    @ManyToOne(fetch = FetchType.LAZY)
    private DevicesDetails devicesDetails;
    @OneToMany(mappedBy = "device", cascade = CascadeType.MERGE)
    private List<SugarTestsMVC> sugarTestMVC;

}
