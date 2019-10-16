package iot.medicine.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SugarTests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date time;

    private Long device_id;

    private Double glucose;

    public SugarTests() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }

    public Double getGlucose() {
        return glucose;
    }

    public void setGlucose(Double glucose) {
        this.glucose = glucose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SugarTests)) return false;

        SugarTests that = (SugarTests) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (device_id != null ? !device_id.equals(that.device_id) : that.device_id != null) return false;
        return glucose != null ? glucose.equals(that.glucose) : that.glucose == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (device_id != null ? device_id.hashCode() : 0);
        result = 31 * result + (glucose != null ? glucose.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SugarTests{" +
                "id=" + id +
                ", time=" + time +
                ", device_id=" + device_id +
                ", glucose=" + glucose +
                '}';
    }
}
