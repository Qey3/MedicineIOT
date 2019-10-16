package IOT.medicine.sugar.pojo;

public class CreateTestsCmd {

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
