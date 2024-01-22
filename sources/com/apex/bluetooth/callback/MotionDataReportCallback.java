package com.apex.bluetooth.callback;

import com.apex.bluetooth.enumeration.CommonFlag;
import com.apex.bluetooth.model.EABleBloodOxygen;
import com.apex.bluetooth.model.EABleDailyData;
import com.apex.bluetooth.model.EABleGpsData;
import com.apex.bluetooth.model.EABleHabitRecord;
import com.apex.bluetooth.model.EABleHeartData;
import com.apex.bluetooth.model.EABleMultiData;
import com.apex.bluetooth.model.EABlePaceData;
import com.apex.bluetooth.model.EABlePressureData;
import com.apex.bluetooth.model.EABleRestingRateData;
import com.apex.bluetooth.model.EABleSleepData;
import com.apex.bluetooth.model.EABleStepFrequencyData;
import java.util.List;
/* loaded from: classes.dex */
public interface MotionDataReportCallback extends EABleCallback {
    void bloodOxygenData(List<EABleBloodOxygen> list, CommonFlag commonFlag);

    void dailyExerciseData(List<EABleDailyData> list, CommonFlag commonFlag);

    void getHabitData(List<EABleHabitRecord> list, CommonFlag commonFlag);

    void gpsData(List<EABleGpsData> list, CommonFlag commonFlag);

    void heartData(List<EABleHeartData> list, CommonFlag commonFlag);

    void multiMotionData(List<EABleMultiData> list, CommonFlag commonFlag);

    void pressureData(List<EABlePressureData> list, CommonFlag commonFlag);

    void restingHeartRateData(List<EABleRestingRateData> list, CommonFlag commonFlag);

    void sleepData(List<EABleSleepData> list, CommonFlag commonFlag);

    void speedData(List<EABlePaceData> list, CommonFlag commonFlag);

    void stepFrequencyData(List<EABleStepFrequencyData> list, CommonFlag commonFlag);
}
