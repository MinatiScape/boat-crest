package com.coveiot.android.bleabstract.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.crpsdk.model.KhCRPHeartRateInfo;
import com.coveiot.android.crpsdk.model.KhCRPStepInfo;
import com.coveiot.android.crpsdk.model.KhCRPWorkoutInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
/* loaded from: classes2.dex */
public class PreferenceManagerCRP {

    /* renamed from: a  reason: collision with root package name */
    public static PreferenceManagerCRP f3467a;
    public static SharedPreferences b;
    public static SharedPreferences.Editor c;

    public static PreferenceManagerCRP getInstance(Context context) {
        if (f3467a == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("PREFERENCE_MANAGER_CRP", 0);
            b = sharedPreferences;
            c = sharedPreferences.edit();
            f3467a = new PreferenceManagerCRP();
        }
        return f3467a;
    }

    public String getConnectedDeviceFwVersion() {
        return b.getString("fw_version", "");
    }

    public String getConnectedDeviceMacAddress() {
        return b.getString("ble_address", "");
    }

    public String getConnectionType() {
        return b.getString("ble_connection_type", "");
    }

    public KhCRPWorkoutInfo getKhCRPWorkoutInfo(Long l, Long l2) {
        List<KhCRPWorkoutInfo> workoutData = getWorkoutData();
        if (workoutData != null && workoutData.size() > 0) {
            for (int i = 0; i < workoutData.size(); i++) {
                if (workoutData.get(i).getStartTime().longValue() == l.longValue() && workoutData.get(i).getEndTime().longValue() == l2.longValue()) {
                    return workoutData.get(i);
                }
            }
        }
        return null;
    }

    public List<KhCRPHeartRateInfo> getPartHeartRateData() {
        String string = b.getString("part_heart_rate_data", null);
        if (string != null) {
            return (List) new Gson().fromJson(string, new TypeToken<List<KhCRPHeartRateInfo>>(this) { // from class: com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP.1
            }.getType());
        }
        return null;
    }

    public KhCRPStepInfo getPastDayStepsData() {
        return (KhCRPStepInfo) new Gson().fromJson(b.getString("past_steps_data", ""), (Class<Object>) KhCRPStepInfo.class);
    }

    public KhCRPStepInfo getTodayStepsData() {
        return (KhCRPStepInfo) new Gson().fromJson(b.getString("today_steps_data", ""), (Class<Object>) KhCRPStepInfo.class);
    }

    public List<KhCRPWorkoutInfo> getWorkoutData() {
        String string = b.getString("workout_data", null);
        if (string != null) {
            return (List) new Gson().fromJson(string, new TypeToken<List<KhCRPWorkoutInfo>>(this) { // from class: com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP.2
            }.getType());
        }
        return null;
    }

    public KhCRPStepInfo getYesterdayStepsData() {
        return (KhCRPStepInfo) new Gson().fromJson(b.getString("yesterday_steps_data", ""), (Class<Object>) KhCRPStepInfo.class);
    }

    public Boolean isBandUnpaired() {
        return Boolean.valueOf(b.getBoolean("is_band_unpaired", false));
    }

    public void saveBandUnpaired(boolean z) {
        c.putBoolean("is_band_unpaired", z).commit();
    }

    public void saveConnectedDeviceFwVersion(String str) {
        c.putString("fw_version", str).commit();
    }

    public void saveConnectedDeviceMAcAddress(String str) {
        c.putString("ble_address", str).commit();
    }

    public void saveConnectionType(String str) {
        c.putString("ble_connection_type", str).commit();
    }

    public void savePartHeartRateData(List<KhCRPHeartRateInfo> list) {
        c.putString("part_heart_rate_data", new Gson().toJson(list)).commit();
    }

    public void savePastDayStepsData(KhCRPStepInfo khCRPStepInfo) {
        c.putString("past_steps_data", new Gson().toJson(khCRPStepInfo)).commit();
    }

    public void saveTodayStepsData(KhCRPStepInfo khCRPStepInfo) {
        c.putString("today_steps_data", new Gson().toJson(khCRPStepInfo)).commit();
    }

    public void saveWorkoutData(List<KhCRPWorkoutInfo> list) {
        c.putString("workout_data", new Gson().toJson(list)).commit();
    }

    public void saveYesterdayStepsData(KhCRPStepInfo khCRPStepInfo) {
        c.putString("yesterday_steps_data", new Gson().toJson(khCRPStepInfo)).commit();
    }

    public void updateKhCRPWorkoutInfo(KhCRPWorkoutInfo khCRPWorkoutInfo) {
        List<KhCRPWorkoutInfo> workoutData = getWorkoutData();
        if (workoutData == null || workoutData.size() <= 0) {
            return;
        }
        int i = 0;
        while (true) {
            if (i < workoutData.size()) {
                if (workoutData.get(i).getStartTime().longValue() == khCRPWorkoutInfo.getStartTime().longValue() && workoutData.get(i).getEndTime().longValue() == khCRPWorkoutInfo.getEndTime().longValue()) {
                    workoutData.set(i, khCRPWorkoutInfo);
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        saveWorkoutData(workoutData);
    }
}
