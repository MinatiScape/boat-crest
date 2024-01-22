package com.coveiot.coveaccess.temperature;

import com.coveiot.coveaccess.temperature.model.TemperatureData;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveTemperatureDataReq {
    private List<TemperatureData> temperatureDataList;

    public SaveTemperatureDataReq(List<TemperatureData> list) {
        this.temperatureDataList = list;
    }

    public List<TemperatureData> getTemperatureDataList() {
        return this.temperatureDataList;
    }
}
