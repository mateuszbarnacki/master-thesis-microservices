package agh.wfiis.weather.model.devicemetadata;

import java.util.Date;

public class StationaryDeviceMetadata implements DeviceMetadata {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
