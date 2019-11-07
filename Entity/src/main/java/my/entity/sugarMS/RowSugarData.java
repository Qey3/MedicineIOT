package my.entity.sugarMS;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

public class RowSugarData {

    private Long deviceId;

    private Double glucose;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Double getGlucose() {
        return glucose;
    }

    public void setGlucose(Double glucose) {
        this.glucose = glucose;
    }
}
