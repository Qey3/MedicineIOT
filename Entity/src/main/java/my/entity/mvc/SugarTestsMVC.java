package my.entity.mvc;

import lombok.AllArgsConstructor;
import my.entity.mvc.device.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sugartestsmvc")
public class SugarTestsMVC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date analysisTime;

    private Double glucose;

    @ManyToOne(fetch = FetchType.LAZY)//, cascade = CascadeType.MERGE)
    private Device device;

}
