package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
/* loaded from: classes2.dex */
public class Mapper {
    public static LiveHealthData a(com.coveiot.sdk.ble.api.model.LiveHealthData liveHealthData) {
        LiveHealthData liveHealthData2 = new LiveHealthData();
        liveHealthData2.setHeartRate(liveHealthData.getHrValue());
        liveHealthData2.setSystolicBp(liveHealthData.getSbpValue());
        liveHealthData2.setDiastolicBp(liveHealthData.getDbpValue());
        liveHealthData2.setStress(liveHealthData.getStressValue());
        liveHealthData2.setRr(liveHealthData.getRrValue());
        return liveHealthData2;
    }

    public static LiveStepsData a(com.coveiot.sdk.ble.api.model.LiveStepsData liveStepsData) {
        LiveStepsData liveStepsData2 = new LiveStepsData();
        liveStepsData2.setLiveSteps(liveStepsData.getLiveSteps());
        liveStepsData2.setMeters((int) Math.floor(liveStepsData.getMeters()));
        liveStepsData2.setCalories(liveStepsData.getCalories());
        return liveStepsData2;
    }

    public static LiveTemperatureData a(com.coveiot.sdk.ble.api.model.LiveTemperatureData liveTemperatureData) {
        LiveTemperatureData liveTemperatureData2 = new LiveTemperatureData();
        liveTemperatureData2.setTemperature(liveTemperatureData.getTemperature());
        return liveTemperatureData2;
    }
}
