package com.ido.ble.callback;

import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.ble.protocol.model.AllHealthMonitorSwitch;
import com.ido.ble.protocol.model.BtA2dpHfpStatus;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DeviceUpgradeState;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.PressCalibrationValue;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SupportSportInfoV3;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.WalkReminder;
import java.util.List;
/* loaded from: classes11.dex */
public class GetDeviceParaCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onGetActivitySwitch(ActivitySwitch activitySwitch);

        void onGetAlarmV3(List<AlarmV3> list);

        void onGetAllHealthMonitorSwitch(AllHealthMonitorSwitch allHealthMonitorSwitch);

        void onGetBtA2dpHfpStatus(BtA2dpHfpStatus btA2dpHfpStatus);

        void onGetContactReceiveTime(boolean z);

        void onGetDeviceUpgradeState(DeviceUpgradeState deviceUpgradeState);

        void onGetDoNotDisturbPara(NotDisturbPara notDisturbPara);

        void onGetFirmwareAndBt3Version(FirmwareAndBt3Version firmwareAndBt3Version);

        void onGetMenuList(MenuList.DeviceReturnInfo deviceReturnInfo);

        void onGetPressCalibrationValue(PressCalibrationValue pressCalibrationValue);

        void onGetScheduleReminderV3(List<ScheduleReminderV3> list);

        void onGetScreenBrightness(ScreenBrightness screenBrightness);

        void onGetSportThreeCircleGoal(CalorieAndDistanceGoal calorieAndDistanceGoal, String str);

        void onGetSupportSportInfoV3(SupportSportInfoV3 supportSportInfoV3);

        void onGetUpHandGesture(UpHandGesture upHandGesture);

        void onGetWalkReminder(WalkReminder walkReminder);
    }

    public static void onGetActivitySwitch(final ActivitySwitch activitySwitch) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetActivitySwitch(ActivitySwitch.this);
                    }
                }
            }
        });
    }

    public static void onGetAlarmV3(final List<AlarmV3> list) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.14
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetAlarmV3(list);
                    }
                }
            }
        });
    }

    public static void onGetAllHealthMonitorSwitch(final AllHealthMonitorSwitch allHealthMonitorSwitch) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetAllHealthMonitorSwitch(AllHealthMonitorSwitch.this);
                    }
                }
            }
        });
    }

    public static void onGetBtA2dpHfpStatus(final BtA2dpHfpStatus btA2dpHfpStatus) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetBtA2dpHfpStatus(BtA2dpHfpStatus.this);
                    }
                }
            }
        });
    }

    public static void onGetContactReceiveTime(final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    iCallBack.onGetContactReceiveTime(z);
                }
            }
        });
    }

    public static void onGetDeviceUpgradeState(final DeviceUpgradeState deviceUpgradeState) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.10
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetDeviceUpgradeState(DeviceUpgradeState.this);
                    }
                }
            }
        });
    }

    public static void onGetDoNotDisturbPara(final NotDisturbPara notDisturbPara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.13
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetDoNotDisturbPara(NotDisturbPara.this);
                    }
                }
            }
        });
    }

    public static void onGetFirmwareAndBt3Version(final FirmwareAndBt3Version firmwareAndBt3Version) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetFirmwareAndBt3Version(FirmwareAndBt3Version.this);
                    }
                }
            }
        });
    }

    public static void onGetMenuList(final MenuList.DeviceReturnInfo deviceReturnInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetMenuList(MenuList.DeviceReturnInfo.this);
                    }
                }
            }
        });
    }

    public static void onGetPressCalibrationValue(final PressCalibrationValue pressCalibrationValue) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetPressCalibrationValue(PressCalibrationValue.this);
                    }
                }
            }
        });
    }

    public static void onGetScheduleReminderV3(final List<ScheduleReminderV3> list) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.15
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetScheduleReminderV3(list);
                    }
                }
            }
        });
    }

    public static void onGetScreenBrightness(final ScreenBrightness screenBrightness) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.12
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetScreenBrightness(ScreenBrightness.this);
                    }
                }
            }
        });
    }

    public static void onGetSportThreeCircleGoal(final CalorieAndDistanceGoal calorieAndDistanceGoal, final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetSportThreeCircleGoal(CalorieAndDistanceGoal.this, str);
                    }
                }
            }
        });
    }

    public static void onGetSupportSportInfoV3(final SupportSportInfoV3 supportSportInfoV3) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.16
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetSupportSportInfoV3(SupportSportInfoV3.this);
                    }
                }
            }
        });
    }

    public static void onGetUpHandGesture(final UpHandGesture upHandGesture) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.11
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetUpHandGesture(UpHandGesture.this);
                    }
                }
            }
        });
    }

    public static void onGetWalkReminder(final WalkReminder walkReminder) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceParaCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().s()) {
                    if (iCallBack != null) {
                        iCallBack.onGetWalkReminder(WalkReminder.this);
                    }
                }
            }
        });
    }
}
