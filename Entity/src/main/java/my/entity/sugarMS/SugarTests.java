package my.entity.sugarMS;

import lombok.AllArgsConstructor;
import my.entity.mvc.device.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "sugartest")
public class SugarTests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "device")
    private Long device;
    @Column(name = "glucose")
    private Double glucose;

    @Column(name = "analysis_time")
    private Date analysisTime;

}
