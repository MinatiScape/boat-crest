package com.coveiot.coveaccess.temperature;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.coveiot.coveaccess.temperature.model.TemperatureData;
import java.util.List;
/* loaded from: classes8.dex */
public class GetTemperatureDataRes extends CoveApiResponseBaseModel {
    public List<TemperatureData> temperatureDataList;

    public GetTemperatureDataRes(int i) {
        super(i);
    }

    public List<TemperatureData> getTemperatureDataList() {
        return this.temperatureDataList;
    }

    public void setTemperatureDataList(List<TemperatureData> list) {
        this.temperatureDataList = list;
    }
}
