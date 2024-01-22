package com.coveiot.sdk.ble.helper;

import android.content.Context;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.model.SedentaryReminderInfo;
import com.coveiot.sdk.ble.model.SleepInterval;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommandClass;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.google.gson.Gson;
/* loaded from: classes9.dex */
public class BleCmdManager {
    public static BleCmdManager b;

    /* renamed from: a  reason: collision with root package name */
    public Gson f7571a = new Gson();

    public static BleCmdManager getInstance() {
        if (b == null) {
            b = new BleCmdManager();
            BleEventBusManager.getInstance().getEventBus().register(b);
        }
        return b;
    }

    public void finalize() throws Throwable {
        super.finalize();
        BleEventBusManager.getInstance().getEventBus().unregister(b);
    }

    public SleepInterval getSleepIntervalTime(Context context) {
        String str = (String) BlePreferenceManager.getPreference(context, CommonPreference.SLEEP_TRACK_INTERVAL, "");
        try {
            if (!BleUtils.isEmpty(str)) {
                return (SleepInterval) this.f7571a.fromJson(str, (Class<Object>) SleepInterval.class);
            }
            SleepInterval sleepInterval = new SleepInterval();
            sleepInterval.setStartHr1(0);
            sleepInterval.setEndHr1(12);
            sleepInterval.setStartHr2(13);
            sleepInterval.setEndHr2(23);
            return sleepInterval;
        } catch (Exception unused) {
            SleepInterval sleepInterval2 = new SleepInterval();
            sleepInterval2.setStartHr1(0);
            sleepInterval2.setEndHr1(12);
            sleepInterval2.setStartHr2(13);
            sleepInterval2.setEndHr2(23);
            return sleepInterval2;
        }
    }

    public void setCallNotify(int i, String str) {
        CommandClass commandClass = new CommandClass(CommandNames.SEND_CALL_ALERT_INFO);
        commandClass.setCallAlert(i, str);
        BleEventBusManager.getInstance().getEventBus().post(commandClass);
    }

    public void setSedentarySettings(SedentaryReminderInfo sedentaryReminderInfo) {
        CommandClass commandClass = new CommandClass(CommandNames.SET_SEDENTARY_ALERT_INFO);
        commandClass.setSedentaryReminder(sedentaryReminderInfo);
        BleEventBusManager.getInstance().getEventBus().post(commandClass);
    }

    public void setSleepIntervalTime(SleepInterval sleepInterval, Context context) {
        BlePreferenceManager.savePreference(context, CommonPreference.SLEEP_TRACK_INTERVAL, this.f7571a.toJson(sleepInterval));
    }

    public void setSmsNotify(int i, String str) {
        CommandClass commandClass = new CommandClass(CommandNames.SEND_SMS_ALERT_INFO);
        commandClass.setSmsAlert(i, str);
        BleEventBusManager.getInstance().getEventBus().post(commandClass);
    }
}
