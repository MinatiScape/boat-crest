package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.response.DeviceSettingsInfoResponse;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.models.AlarmUpdated;
import com.coveiot.android.leonardo.more.models.DndUpdated;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.CoveEventBusManager;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DeviceSettingsInfoReceiver extends BroadcastReceiver {
    public final String a(Alarm alarm) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (alarm.isSundayEnabled()) {
            str = "S";
        } else {
            str = "" + AppConstants.EMPTY_HYPHEN.getValue();
        }
        if (alarm.isMondayEnabled()) {
            str2 = str + 'M';
        } else {
            str2 = str + AppConstants.EMPTY_HYPHEN.getValue();
        }
        if (alarm.isTuesdayEnabled()) {
            str3 = str2 + 'T';
        } else {
            str3 = str2 + AppConstants.EMPTY_HYPHEN.getValue();
        }
        if (alarm.isWednesdayEnabled()) {
            str4 = str3 + 'W';
        } else {
            str4 = str3 + AppConstants.EMPTY_HYPHEN.getValue();
        }
        if (alarm.isThursdayEnabled()) {
            str5 = str4 + 'T';
        } else {
            str5 = str4 + AppConstants.EMPTY_HYPHEN.getValue();
        }
        if (alarm.isFridayEnabled()) {
            str6 = str5 + 'F';
        } else {
            str6 = str5 + AppConstants.EMPTY_HYPHEN.getValue();
        }
        if (alarm.isSaturdayEnabled()) {
            return str6 + 'S';
        }
        return str6 + AppConstants.EMPTY_HYPHEN.getValue();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.hasExtra(Constants.DEVICE_SETTINGS_BROADCAST_INTENT_EXTRA)) {
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Serializable serializable = extras.getSerializable(Constants.DEVICE_SETTINGS_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceSettingsInfoResponse");
            DeviceSettingsInfoResponse deviceSettingsInfoResponse = (DeviceSettingsInfoResponse) serializable;
            LogsHelper.d("deviceSettingsInfoResponse", deviceSettingsInfoResponse.toString());
            UserDataManager.getInstance(context).saveDistanceUnitPref(Boolean.valueOf(deviceSettingsInfoResponse.isDistanceUnitInMile()));
            UserDataManager.getInstance(context).saveTemperatureUnitPref(Boolean.valueOf(deviceSettingsInfoResponse.isTemperatureUnitInFahrenheit()));
            UserDataManager.getInstance(context).saveLiftWristPref(Boolean.valueOf(deviceSettingsInfoResponse.isLiftWristON()));
            UserDataManager.getInstance(context).saveHourFormatPref(Boolean.valueOf(deviceSettingsInfoResponse.isTimeIn12HRFormat()));
        } else if (intent.hasExtra(Constants.ALARM_SETTINGS_BROADCAST_INTENT_EXTRA)) {
            Bundle extras2 = intent.getExtras();
            Intrinsics.checkNotNull(extras2);
            Serializable serializable2 = extras2.getSerializable(Constants.ALARM_SETTINGS_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.models.Alarm");
            Alarm alarm = (Alarm) serializable2;
            List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(context).getVibrationAlarmData();
            int size = vibrationAlarmData.size();
            boolean z = false;
            for (int i = 0; i < size; i++) {
                if (vibrationAlarmData.get(i).getAlarmId().equals(String.valueOf(alarm.getAlarmId()))) {
                    VibrationAlarmData vibrationAlarmData2 = vibrationAlarmData.get(i);
                    vibrationAlarmData2.setEvent_name(alarm.getEventName());
                    vibrationAlarmData2.setAlarmType(alarm.getAlarmType());
                    vibrationAlarmData2.setEvent_time_hour(alarm.getHour());
                    vibrationAlarmData2.setEvent_time_minutes(alarm.getMinute());
                    vibrationAlarmData2.setSwitch_on_off(alarm.isEnabled());
                    vibrationAlarmData2.setWeeks(a(alarm));
                    vibrationAlarmData2.setMonday(alarm.isMondayEnabled());
                    vibrationAlarmData2.setTuesday(alarm.isTuesdayEnabled());
                    vibrationAlarmData2.setWednesday(alarm.isWednesdayEnabled());
                    vibrationAlarmData2.setThrusday(alarm.isThursdayEnabled());
                    vibrationAlarmData2.setFriday(alarm.isFridayEnabled());
                    vibrationAlarmData2.setSaturday(alarm.isSaturdayEnabled());
                    vibrationAlarmData2.setSunday(alarm.isSundayEnabled());
                    vibrationAlarmData2.setSnoozeDuration(alarm.getSnoozeDuration());
                    vibrationAlarmData2.setSnoozeRepeatCount(alarm.getSnoozeRepeatTimes());
                    vibrationAlarmData.set(i, vibrationAlarmData2);
                    z = true;
                }
            }
            if (!z) {
                vibrationAlarmData.add(SettingsSyncHelper.Companion.getInstance(context).getVibrationAlarmData(alarm));
            }
            UserDataManager.getInstance(context).saveVibrationAlarmSettings(vibrationAlarmData);
            SettingsSyncHelper.Companion companion = SettingsSyncHelper.Companion;
            companion.getInstance(context).saveAlarmsToServer(context, companion.getInstance(context).ConvertFromVibrateToAlarminfo());
            CoveEventBusManager.getInstance().getEventBus().post(new AlarmUpdated());
        } else if (intent.hasExtra(Constants.DND_SETTINGS_BROADCAST_INTENT_EXTRA)) {
            Bundle extras3 = intent.getExtras();
            Intrinsics.checkNotNull(extras3);
            Serializable serializable3 = extras3.getSerializable(Constants.DND_SETTINGS_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.models.DNDData");
            DNDData dNDData = (DNDData) serializable3;
            DoNotDisturbData doNotDisturbData = DoNotDisturbData.getInstance();
            doNotDisturbData.setBeggining_time_hour(dNDData.getStartHour());
            doNotDisturbData.setBeggining_time_minutes(dNDData.getStartMin());
            doNotDisturbData.setEnd_time_hour(dNDData.getEndHour());
            doNotDisturbData.setEnd_time_minutes(dNDData.getEndMin());
            doNotDisturbData.setSchedule_on_off(dNDData.isDNDEnabled());
            if (DeviceUtils.Companion.isSmaDevice(context)) {
                doNotDisturbData.setDnd_on_off(dNDData.isDNDEnabled());
            }
            UserDataManager.getInstance(context).saveDoNotDisturbSettings(doNotDisturbData);
            UserDataManager.getInstance(context).saveScheuleDnd(dNDData.isDNDEnabled());
            SettingsSyncHelper.Companion.getInstance(context).uploadDndToServer(dNDData.getStartHour(), dNDData.getStartMin(), dNDData.getEndHour(), dNDData.getEndMin(), doNotDisturbData.isDnd_on_off(), dNDData.isDNDEnabled());
            CoveEventBusManager.getInstance().getEventBus().post(new DndUpdated());
        }
    }
}
