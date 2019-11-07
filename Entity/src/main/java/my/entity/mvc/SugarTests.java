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
@Entity
public class SugarTests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date analysisTime;

    @ManyToOne
    private Device device;

    private Double glucose;


}
