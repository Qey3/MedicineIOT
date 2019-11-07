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
@Entity
public class SugarTests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long device;

    private Double glucose;

    private Date analysisTime;

}
