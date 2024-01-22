package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.AlarmV3CmdParaWrapper;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.AntiLostPara;
import com.ido.ble.protocol.model.BloodPressureAdjustPara;
import com.ido.ble.protocol.model.BloodPressureQueryAdjustResultPara;
import com.ido.ble.protocol.model.BodyPowerSwitch;
import com.ido.ble.protocol.model.BreatheTrain;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HandWearMode;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import com.ido.ble.protocol.model.IconTransInformation;
import com.ido.ble.protocol.model.LongSit;
import com.ido.ble.protocol.model.MedicineReminder;
import com.ido.ble.protocol.model.MenstruationHistoricalData;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.MusicControlInfo;
import com.ido.ble.protocol.model.NightTemperatureMonitoringPara;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.NoticeDevicePermmsion;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.NoticeSportActionToggle;
import com.ido.ble.protocol.model.NoticeSwitchInfo;
import com.ido.ble.protocol.model.PhoneSystemInfo;
import com.ido.ble.protocol.model.PhoneVoice;
import com.ido.ble.protocol.model.PressCalibration;
import com.ido.ble.protocol.model.QuickReplyInfo;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.RespiratoryRate;
import com.ido.ble.protocol.model.ScheduleReminderSwitch;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SoAutomaticSyncConfigIfDeviceReboot;
import com.ido.ble.protocol.model.SosSwitch;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SportPlan;
import com.ido.ble.protocol.model.StopFindPhone;
import com.ido.ble.protocol.model.SystemTime;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import com.ido.ble.protocol.model.VoiceToText;
import com.ido.ble.protocol.model.WalkRealTimeReminder;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.ble.protocol.model.WashHandReminder;
import com.ido.ble.protocol.model.WatchDialOrder;
import com.ido.ble.protocol.model.WorldTime;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class o {
    public static void a() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 115, 0, 0);
    }

    public static void a(ActivitySwitch activitySwitch) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(activitySwitch)), 167);
    }

    public static void a(Alarm alarm) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(alarm)), 5000);
    }

    public static void a(AlarmV3CmdParaWrapper.AlarmSet alarmSet) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(alarmSet)), (int) com.veryfit.multi.nativeprotocol.b.x3);
    }

    public static void a(AntiLostMode antiLostMode) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(antiLostMode)), 102);
    }

    @Deprecated
    public static void a(AntiLostPara antiLostPara) {
    }

    public static void a(BloodPressureAdjustPara bloodPressureAdjustPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(bloodPressureAdjustPara)), 126);
    }

    public static void a(BloodPressureQueryAdjustResultPara bloodPressureQueryAdjustResultPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(bloodPressureQueryAdjustResultPara)), 126);
    }

    public static void a(BodyPowerSwitch bodyPowerSwitch) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(bodyPowerSwitch)), 192);
    }

    public static void a(BreatheTrain breatheTrain) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(breatheTrain)), 166);
    }

    public static void a(DeviceChangedPara deviceChangedPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceChangedPara)), (int) com.veryfit.multi.nativeprotocol.b.s2);
    }

    public static void a(DeviceUnreadReminder deviceUnreadReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceUnreadReminder)), 188);
    }

    public static void a(DialPlate dialPlate) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(dialPlate)), 124);
    }

    public static void a(DisplayMode displayMode) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(displayMode)), 118);
    }

    public static void a(DrinkWaterReminder drinkWaterReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(drinkWaterReminder)), 168);
    }

    public static void a(FitnessGuidance fitnessGuidance) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(fitnessGuidance)), 186);
    }

    public static void a(Goal goal) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(goal)), 105);
    }

    public static void a(HandWearMode handWearMode) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(handWearMode)), 109);
    }

    public static void a(HeartRateInterval heartRateInterval) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(heartRateInterval)), 112);
    }

    public static void a(HeartRateMeasureMode heartRateMeasureMode) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(heartRateMeasureMode)), 113);
    }

    public static void a(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(heartRateMeasureModeV3)), 5010);
    }

    public static void a(HeartRateSmartMode heartRateSmartMode) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(heartRateSmartMode)), 182);
    }

    public static void a(IconTransInformation iconTransInformation) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(iconTransInformation)), 511);
    }

    public static void a(LongSit longSit) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(longSit)), 101);
    }

    public static void a(MedicineReminder medicineReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(medicineReminder)), 177);
    }

    public static void a(MenstruationHistoricalData menstruationHistoricalData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(menstruationHistoricalData)), (int) com.veryfit.multi.nativeprotocol.b.X3);
    }

    public static void a(MenuList menuList) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(menuList)), 171);
    }

    public static void a(MusicControlInfo musicControlInfo) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(musicControlInfo)), 5011);
    }

    public static void a(NightTemperatureMonitoringPara nightTemperatureMonitoringPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(nightTemperatureMonitoringPara)), 185);
    }

    public static void a(NoisePara noisePara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(noisePara)), 183);
    }

    public static void a(NotDisturbPara notDisturbPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(notDisturbPara)), 116);
    }

    public static void a(NoticeDevicePermmsion noticeDevicePermmsion) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(noticeDevicePermmsion)), 513);
    }

    public static void a(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(noticeReminderSwitchStatus)), 111);
    }

    public static void a(NoticeSportActionToggle noticeSportActionToggle) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(noticeSportActionToggle)), (int) com.veryfit.multi.nativeprotocol.b.b4);
    }

    @Deprecated
    public static void a(NoticeSwitchInfo noticeSwitchInfo) {
    }

    @Deprecated
    public static void a(PhoneSystemInfo phoneSystemInfo) {
    }

    public static void a(PhoneVoice phoneVoice) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(phoneVoice)), 174);
    }

    public static void a(PressCalibration pressCalibration) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(pressCalibration)), 128);
    }

    public static void a(QuickReplyInfo quickReplyInfo) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(quickReplyInfo)), 5020);
    }

    public static void a(QuickSportMode quickSportMode) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(quickSportMode)), 151);
    }

    public static void a(RespiratoryRate respiratoryRate) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(respiratoryRate)), 191);
    }

    public static void a(ScheduleReminderSwitch scheduleReminderSwitch) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(scheduleReminderSwitch)), 190);
    }

    public static void a(ScreenBrightness screenBrightness) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(screenBrightness)), 154);
    }

    public static void a(ShortCut shortCut) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(shortCut)), 125);
    }

    public static void a(SleepMonitoringPara sleepMonitoringPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sleepMonitoringPara)), 184);
    }

    public static void a(SosSwitch sosSwitch) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sosSwitch)), 119);
    }

    public static void a(SportModeSortV3 sportModeSortV3) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sportModeSortV3)), 5013);
    }

    public static void a(SportPlan sportPlan) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sportPlan)), (int) com.veryfit.multi.nativeprotocol.b.Z3);
    }

    public static void a(StopFindPhone stopFindPhone) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(stopFindPhone)), 129);
    }

    public static void a(SystemTime systemTime) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(systemTime)), 104);
    }

    public static void a(Units units) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(units)), 108);
    }

    public static void a(UpHandGesture upHandGesture) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(upHandGesture)), 114);
    }

    public static void a(UserInfo userInfo) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(userInfo)), 107);
    }

    public static void a(VoiceToText voiceToText) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceToText)), 5025);
    }

    public static void a(WalkRealTimeReminder walkRealTimeReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(walkRealTimeReminder)), (int) com.veryfit.multi.nativeprotocol.b.T3);
    }

    public static void a(WalkReminder walkReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(walkReminder)), 165);
    }

    public static void a(WashHandReminder washHandReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(washHandReminder)), 175);
    }

    public static void a(WatchDialOrder watchDialOrder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(watchDialOrder)), (int) com.veryfit.multi.nativeprotocol.b.Y3);
    }

    public static void a(WorldTime worldTime) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(worldTime)), (int) com.veryfit.multi.nativeprotocol.b.S3);
    }

    public static void a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("city_name", str);
            u.b(com.ido.ble.common.c.b(jSONObject.toString()), (int) com.veryfit.multi.nativeprotocol.b.s4);
        } catch (JSONException unused) {
        }
    }

    public static void a(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("onOff", z);
            u.b(com.ido.ble.common.c.b(jSONObject.toString()), 103);
        } catch (JSONException unused) {
        }
    }

    public static void b() {
        u.b((int) com.veryfit.multi.nativeprotocol.b.t5, (int) com.veryfit.multi.nativeprotocol.b.B1);
    }

    public static void b(boolean z) {
        SoAutomaticSyncConfigIfDeviceReboot soAutomaticSyncConfigIfDeviceReboot = new SoAutomaticSyncConfigIfDeviceReboot();
        soAutomaticSyncConfigIfDeviceReboot.to_sync = z;
        u.b(com.ido.ble.common.c.b(new Gson().toJson(soAutomaticSyncConfigIfDeviceReboot)), 180);
    }

    public static void c(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("onOff", z);
            u.b(com.ido.ble.common.c.b(jSONObject.toString()), 117);
        } catch (JSONException unused) {
        }
    }

    public static void d(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("onOff", z);
            u.b(com.ido.ble.common.c.b(jSONObject.toString()), 119);
        } catch (JSONException unused) {
        }
    }

    public static void e(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("on_off", z);
            u.b(com.ido.ble.common.c.b(jSONObject.toString()), 150);
        } catch (JSONException unused) {
        }
    }
}
