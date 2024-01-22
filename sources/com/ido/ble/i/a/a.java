package com.ido.ble.i.a;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.callback.UnbindCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.ble.protocol.model.AlarmV3CmdParaWrapper;
import com.ido.ble.protocol.model.AllPhoneContacts;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.AntiLostPara;
import com.ido.ble.protocol.model.AppExchangeDataIngPara;
import com.ido.ble.protocol.model.AppExchangeDataPausePara;
import com.ido.ble.protocol.model.AppExchangeDataResumePara;
import com.ido.ble.protocol.model.AppExchangeDataStartPara;
import com.ido.ble.protocol.model.AppExchangeDataStopPara;
import com.ido.ble.protocol.model.BindAuth;
import com.ido.ble.protocol.model.BindEncrypted;
import com.ido.ble.protocol.model.BindPara;
import com.ido.ble.protocol.model.BloodPressureAdjustPara;
import com.ido.ble.protocol.model.BloodPressureQueryAdjustResultPara;
import com.ido.ble.protocol.model.BodyPowerSwitch;
import com.ido.ble.protocol.model.BreatheTrain;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.DeviceExchangeDataIngAppReplyPara;
import com.ido.ble.protocol.model.DeviceExchangeDataPauseAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataResumeAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataStartAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataStopAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataStopPara;
import com.ido.ble.protocol.model.DeviceLogClearPara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPauseAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumeAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopAppReplyData;
import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.FrequentContactsV3;
import com.ido.ble.protocol.model.FrequentContactsV3CmdParaWrapper;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HandWearMode;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import com.ido.ble.protocol.model.IconTransInformation;
import com.ido.ble.protocol.model.IncomingCallInfo;
import com.ido.ble.protocol.model.LongSit;
import com.ido.ble.protocol.model.MedicineReminder;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.MenstruationHistoricalData;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.MessageNotifyState;
import com.ido.ble.protocol.model.MessageNotifyStateCmdParaWrapper;
import com.ido.ble.protocol.model.MusicControlInfo;
import com.ido.ble.protocol.model.MusicOperate;
import com.ido.ble.protocol.model.NewMessageInfo;
import com.ido.ble.protocol.model.NightTemperatureMonitoringPara;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.NoticeDevicePermmsion;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.NoticeSportActionToggle;
import com.ido.ble.protocol.model.NoticeSwitchInfo;
import com.ido.ble.protocol.model.NotificationPara;
import com.ido.ble.protocol.model.PhoneSystemInfo;
import com.ido.ble.protocol.model.PhoneVoice;
import com.ido.ble.protocol.model.PressCalibration;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickReplyInfo;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.RespiratoryRate;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScheduleReminderSwitch;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.ble.protocol.model.ScheduleReminderV3CmdParaWrapper;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SmallQuickModule;
import com.ido.ble.protocol.model.SmallQuickModuleCmdParaWrapper;
import com.ido.ble.protocol.model.SosSwitch;
import com.ido.ble.protocol.model.Sport100TypeSort;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SportPlan;
import com.ido.ble.protocol.model.SportSubItemParaSort;
import com.ido.ble.protocol.model.StopFindPhone;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SystemTime;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UnreadMessageInfo;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import com.ido.ble.protocol.model.V3AppExchangeDataIngPara;
import com.ido.ble.protocol.model.V3MessageNotice;
import com.ido.ble.protocol.model.VoiceControlAlarm;
import com.ido.ble.protocol.model.VoiceControlBrightnessLevel;
import com.ido.ble.protocol.model.VoiceControlFuncWithNoPara;
import com.ido.ble.protocol.model.VoiceControlMusic;
import com.ido.ble.protocol.model.VoiceControlReminder;
import com.ido.ble.protocol.model.VoiceControlSports;
import com.ido.ble.protocol.model.VoiceControlSwitchFunc;
import com.ido.ble.protocol.model.VoiceCountDown;
import com.ido.ble.protocol.model.VoiceLoginState;
import com.ido.ble.protocol.model.VoiceNotifyState;
import com.ido.ble.protocol.model.VoiceRecognizeState;
import com.ido.ble.protocol.model.VoiceStopWatch;
import com.ido.ble.protocol.model.VoiceToText;
import com.ido.ble.protocol.model.WalkRealTimeReminder;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.ble.protocol.model.WallpaperFileCreateConfig;
import com.ido.ble.protocol.model.WashHandReminder;
import com.ido.ble.protocol.model.WatchDialOrder;
import com.ido.ble.protocol.model.WeatherInfo;
import com.ido.ble.protocol.model.WeatherInfoV3;
import com.ido.ble.protocol.model.WeatherSunTime;
import com.ido.ble.protocol.model.WorldTime;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.ble.watch.custom.model.WatchPlateFileMakeConfig;
import com.ido.ble.watch.custom.model.WatchPlateOperation;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
/* loaded from: classes11.dex */
public class a {
    public static void A() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  getDialPlateParam ");
        com.ido.ble.watch.custom.d.b();
    }

    public static void A0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] stop sync activity data!");
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(p.j())) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] stop sync activity data failed! so.lib check error.");
    }

    public static void B() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to get DoNotDisturbPara");
        i.e();
    }

    public static void B0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] stop sync config!");
        int k = p.k();
        Protocol.IS_SYNC_CONFIG = false;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(k)) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] stop sync config failed! so.lib check error.");
    }

    public static void C() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getExFunctionTables...");
        h.g();
    }

    public static void C0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] stop sync health data!");
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(p.l())) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] stop sync health data failed! so.lib check error.");
    }

    public static void D() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to  getFirmwareAndBt3Version");
        i.f();
    }

    public static int D0() {
        return p.m();
    }

    public static void E() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getFlashBinInfo...");
        h.h();
    }

    public static void E0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEto stop VoiceRecognize ");
        q.c();
    }

    public static void F() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getHIDInfo...");
        h.j();
    }

    public static void F0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to unbind...");
        if (!com.ido.ble.bluetooth.a.h()) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] not in connected state, use 'forceUnbind' ...");
            l();
            return;
        }
        int b = d.b();
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(b)) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] unbind failed! so check error. error =" + b);
            com.ido.ble.event.stat.one.c.j("error:" + b);
            UnbindCallBack.a();
        }
    }

    public static void G() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getHabitInfo...");
        h.k();
    }

    public static void G0() {
        LogTool.d(com.ido.ble.logs.a.o, "to v3AppExchangeDataGetHeartRate");
        g.a();
    }

    public static void H() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getLiveData...");
        h.l();
    }

    public static void H0() {
        LogTool.d(com.ido.ble.logs.a.o, "to v3getEndActivityData");
        g.b();
    }

    public static void I() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getMacAddress...");
        h.m();
    }

    public static void J() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  getMenuList ");
        i.g();
    }

    public static void K() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getNoticeReminderSwitchStatus...");
        h.n();
    }

    @Deprecated
    public static void L() {
        h.o();
    }

    public static void M() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to  getPressValue");
        i.h();
    }

    @Deprecated
    public static void N() {
        h.p();
    }

    public static void O() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to getScreenBrightness");
        i.i();
    }

    public static void P() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to getSportThreeCircleGoal");
        i.j();
    }

    public static void Q() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to getSupportSportInfoV3");
        i.k();
    }

    @Deprecated
    public static void R() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getTime...");
        h.q();
    }

    public static void S() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to getUpHandGesture");
        i.l();
    }

    public static void T() {
        com.ido.ble.f.a.c.h().b();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getUserFunctionTables...");
        h.r();
    }

    public static int U() {
        return p.b();
    }

    public static void V() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  getVoiceDefaultLang ");
        q.a();
    }

    public static void W() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to getWalkReminder");
        i.m();
    }

    public static void X() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  getWatchPlateList ");
        com.ido.ble.watch.custom.d.a();
    }

    public static void Y() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  getWatchPlateScreenInfo ");
        com.ido.ble.watch.custom.d.c();
    }

    public static boolean Z() {
        return p.c();
    }

    public static void a() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start AppControlAllConfigSync...");
        int a2 = p.a();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA]  AppControlAllConfigSync...error:" + a2);
    }

    public static void a(int i) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  getInfoFromDeviceByType, type= " + i);
        u.b("{}".getBytes(), i);
    }

    public static void a(int i, int i2) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] set health data offset, type=" + i + ", value=" + i2);
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(p.a(i, i2))) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] set health data offset failed! so.lib check error.");
    }

    public static void a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            u.b("{}".getBytes(), i);
        } else {
            u.b(com.ido.ble.common.c.b(str), i);
        }
    }

    @Deprecated
    public static void a(long j, long j2) {
        b.a(j, j2);
    }

    public static void a(DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType) {
        n.a(deviceControlEventType);
    }

    public static void a(DeviceControlAppCallBack.ReplyDeviceControlState replyDeviceControlState) {
        n.a(replyDeviceControlState);
    }

    public static void a(ActivitySwitch activitySwitch) {
        com.ido.ble.f.a.c.h().a(activitySwitch);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setActivitySwitch " + activitySwitch.toString());
        o.a(activitySwitch);
    }

    public static void a(AllPhoneContacts allPhoneContacts) {
        LogTool.d(com.ido.ble.logs.a.p, allPhoneContacts.toString());
        c.a(allPhoneContacts);
    }

    public static void a(AntiLostMode antiLostMode) {
        com.ido.ble.f.a.c.h().a(antiLostMode);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setAntiLostMode ," + antiLostMode.toString());
        o.a(antiLostMode);
    }

    @Deprecated
    public static void a(AntiLostPara antiLostPara) {
        o.a(antiLostPara);
    }

    public static void a(AppExchangeDataIngPara appExchangeDataIngPara) {
        LogTool.d(com.ido.ble.logs.a.o, appExchangeDataIngPara.toString());
        g.a(appExchangeDataIngPara);
    }

    public static void a(AppExchangeDataPausePara appExchangeDataPausePara) {
        LogTool.d(com.ido.ble.logs.a.o, appExchangeDataPausePara.toString());
        g.a(appExchangeDataPausePara);
    }

    public static void a(AppExchangeDataResumePara appExchangeDataResumePara) {
        LogTool.d(com.ido.ble.logs.a.o, appExchangeDataResumePara.toString());
        g.a(appExchangeDataResumePara);
    }

    public static void a(AppExchangeDataStartPara appExchangeDataStartPara) {
        LogTool.d(com.ido.ble.logs.a.o, appExchangeDataStartPara.toString());
        g.a(appExchangeDataStartPara);
    }

    public static void a(AppExchangeDataStopPara appExchangeDataStopPara) {
        LogTool.d(com.ido.ble.logs.a.o, appExchangeDataStopPara.toString());
        g.a(appExchangeDataStopPara);
    }

    public static void a(BindEncrypted bindEncrypted) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to setEncryptedData " + bindEncrypted.toString());
        int a2 = d.a(bindEncrypted);
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(a2)) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] bind failed! so check error. error =" + a2);
            BindCallBack.b();
        }
    }

    public static void a(BloodPressureAdjustPara bloodPressureAdjustPara) {
        com.ido.ble.f.a.c.h().a(bloodPressureAdjustPara);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setBloodPressureAdjustPara ," + bloodPressureAdjustPara.toString());
        o.a(bloodPressureAdjustPara);
    }

    public static void a(BloodPressureQueryAdjustResultPara bloodPressureQueryAdjustResultPara) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to queryBloodPressureAdjustResult ," + bloodPressureQueryAdjustResultPara.toString());
        o.a(bloodPressureQueryAdjustResultPara);
    }

    public static void a(BodyPowerSwitch bodyPowerSwitch) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setBodyPowerSwitch ," + bodyPowerSwitch.toString());
        o.a(bodyPowerSwitch);
    }

    public static void a(BreatheTrain breatheTrain) {
        com.ido.ble.f.a.c.h().a(breatheTrain);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setBreatheTrain " + breatheTrain.toString());
        o.a(breatheTrain);
    }

    public static void a(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setCalorieAndDistanceGoal " + calorieAndDistanceGoal.toString());
        com.ido.ble.f.a.c.h().a(calorieAndDistanceGoal);
        k.a(calorieAndDistanceGoal);
    }

    public static void a(DeviceChangedPara deviceChangedPara) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setNoticeReply ," + deviceChangedPara.toString());
        o.a(deviceChangedPara);
    }

    public static void a(DeviceExchangeDataIngAppReplyPara deviceExchangeDataIngAppReplyPara) {
        LogTool.d(com.ido.ble.logs.a.o, deviceExchangeDataIngAppReplyPara.toString());
        g.a(deviceExchangeDataIngAppReplyPara);
    }

    public static void a(DeviceExchangeDataPauseAppReplyData deviceExchangeDataPauseAppReplyData) {
        LogTool.d(com.ido.ble.logs.a.o, deviceExchangeDataPauseAppReplyData.toString());
        g.a(deviceExchangeDataPauseAppReplyData);
    }

    public static void a(DeviceExchangeDataResumeAppReplyData deviceExchangeDataResumeAppReplyData) {
        LogTool.d(com.ido.ble.logs.a.o, deviceExchangeDataResumeAppReplyData.toString());
        g.a(deviceExchangeDataResumeAppReplyData);
    }

    public static void a(DeviceExchangeDataStartAppReplyData deviceExchangeDataStartAppReplyData) {
        LogTool.d(com.ido.ble.logs.a.o, deviceExchangeDataStartAppReplyData.toString());
        g.a(deviceExchangeDataStartAppReplyData);
    }

    public static void a(DeviceExchangeDataStopAppReplyData deviceExchangeDataStopAppReplyData) {
        LogTool.d(com.ido.ble.logs.a.o, deviceExchangeDataStopAppReplyData.toString());
        g.a(deviceExchangeDataStopAppReplyData);
    }

    public static void a(DeviceExchangeDataStopPara deviceExchangeDataStopPara) {
        LogTool.d(com.ido.ble.logs.a.o, deviceExchangeDataStopPara.toString());
        g.a(deviceExchangeDataStopPara);
    }

    public static void a(DeviceLogClearPara deviceLogClearPara) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  clearDeviceLog " + deviceLogClearPara.toString());
        e.a(deviceLogClearPara);
    }

    public static void a(DeviceNoticeAppExchangeDataPauseAppReplyData deviceNoticeAppExchangeDataPauseAppReplyData) {
        LogTool.d(com.ido.ble.logs.a.o, deviceNoticeAppExchangeDataPauseAppReplyData.toString());
        g.a(deviceNoticeAppExchangeDataPauseAppReplyData);
    }

    public static void a(DeviceNoticeAppExchangeDataResumeAppReplyData deviceNoticeAppExchangeDataResumeAppReplyData) {
        LogTool.d(com.ido.ble.logs.a.o, deviceNoticeAppExchangeDataResumeAppReplyData.toString());
        g.a(deviceNoticeAppExchangeDataResumeAppReplyData);
    }

    public static void a(DeviceNoticeAppExchangeDataStopAppReplyData deviceNoticeAppExchangeDataStopAppReplyData) {
        LogTool.d(com.ido.ble.logs.a.o, deviceNoticeAppExchangeDataStopAppReplyData.toString());
        g.a(deviceNoticeAppExchangeDataStopAppReplyData);
    }

    public static void a(DeviceUnreadReminder deviceUnreadReminder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setDeviceUnreadReminder ," + deviceUnreadReminder.toString());
        o.a(deviceUnreadReminder);
    }

    public static void a(DialPlate dialPlate) {
        com.ido.ble.f.a.c.h().a(dialPlate);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setDialPlate ," + dialPlate.toString());
        o.a(dialPlate);
    }

    public static void a(DisplayMode displayMode) {
        com.ido.ble.f.a.c.h().a(displayMode);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setDisplayMode ," + displayMode.toString());
        o.a(displayMode);
    }

    public static void a(DrinkWaterReminder drinkWaterReminder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setDrinkWaterReminder, =" + drinkWaterReminder.toString());
        com.ido.ble.f.a.c.h().a(drinkWaterReminder);
        o.a(drinkWaterReminder);
    }

    public static void a(FitnessGuidance fitnessGuidance) {
        com.ido.ble.f.a.f.a.g0().a(fitnessGuidance);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setFitnessGuidance ," + fitnessGuidance.toString());
        o.a(fitnessGuidance);
    }

    public static void a(Goal goal) {
        com.ido.ble.f.a.c.h().a(goal);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setGoal ," + goal.toString());
        o.a(goal);
    }

    public static void a(HandWearMode handWearMode) {
        com.ido.ble.f.a.c.h().a(handWearMode);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setHandMode ," + handWearMode.toString());
        o.a(handWearMode);
    }

    public static void a(HeartRateInterval heartRateInterval) {
        com.ido.ble.f.a.c.h().a(heartRateInterval);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setHeartRateInterval ," + heartRateInterval.toString());
        o.a(heartRateInterval);
    }

    public static void a(HeartRateMeasureMode heartRateMeasureMode) {
        com.ido.ble.f.a.c.h().a(heartRateMeasureMode);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setHeartRateMeasureMode ," + heartRateMeasureMode.toString());
        o.a(heartRateMeasureMode);
    }

    public static void a(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setHeartRateMeasureModeV3 ," + heartRateMeasureModeV3.toString());
        o.a(heartRateMeasureModeV3);
    }

    public static void a(HeartRateSmartMode heartRateSmartMode) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setHeartRateSmart, =" + heartRateSmartMode.toString());
        o.a(heartRateSmartMode);
    }

    public static void a(IconTransInformation iconTransInformation) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setTranSportIconInformation");
        o.a(iconTransInformation);
    }

    public static void a(IncomingCallInfo incomingCallInfo) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "setIncomingCallInfo " + incomingCallInfo);
        l.a(incomingCallInfo);
    }

    public static void a(LongSit longSit) {
        com.ido.ble.f.a.c.h().a(longSit);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setLongSit ," + longSit.toString());
        o.a(longSit);
    }

    public static void a(MedicineReminder medicineReminder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setMedicineReminder, " + medicineReminder.toString());
        o.a(medicineReminder);
    }

    public static void a(Menstrual menstrual) {
        com.ido.ble.f.a.c.h().a(menstrual);
        k.a(menstrual);
    }

    public static void a(MenstrualRemind menstrualRemind) {
        com.ido.ble.f.a.c.h().a(menstrualRemind);
        k.a(menstrualRemind);
    }

    public static void a(MenstruationHistoricalData menstruationHistoricalData) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setMenstruationHistoricalData");
        o.a(menstruationHistoricalData);
    }

    public static void a(MenuList menuList) {
        com.ido.ble.f.a.c.h().a(menuList);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setMenuList " + menuList.toString());
        o.a(menuList);
    }

    public static void a(MusicControlInfo musicControlInfo) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setMusicControlInfo " + musicControlInfo.toString());
        o.a(musicControlInfo);
    }

    public static void a(MusicOperate.MusicFile musicFile) {
        MusicOperate musicOperate = new MusicOperate();
        musicOperate.music_operate = 2;
        musicOperate.music_items = musicFile;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  addMusicFile, " + musicOperate.toString());
        j.a(musicOperate);
    }

    public static void a(MusicOperate.MusicFolder musicFolder) {
        MusicOperate musicOperate = new MusicOperate();
        musicOperate.folder_operate = 2;
        musicOperate.folder_items = musicFolder;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  addMusicFolder, " + musicFolder.toString());
        j.a(musicOperate);
    }

    public static void a(NewMessageInfo newMessageInfo) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "setNewMessageDetailInfo :" + newMessageInfo.type);
        newMessageInfo.number = com.ido.ble.common.i.b(newMessageInfo.number);
        newMessageInfo.name = com.ido.ble.common.i.c(newMessageInfo.name);
        newMessageInfo.content = com.ido.ble.common.i.a(newMessageInfo.content);
        l.a(newMessageInfo);
    }

    public static void a(NightTemperatureMonitoringPara nightTemperatureMonitoringPara) {
        com.ido.ble.f.a.f.a.g0().a(nightTemperatureMonitoringPara);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setNightTemperatureMonitoringPara ," + nightTemperatureMonitoringPara.toString());
        o.a(nightTemperatureMonitoringPara);
    }

    public static void a(NoisePara noisePara) {
        com.ido.ble.f.a.f.a.g0().a(noisePara);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setNoisePara, =" + noisePara.toString());
        o.a(noisePara);
    }

    public static void a(NotDisturbPara notDisturbPara) {
        com.ido.ble.f.a.c.h().a(notDisturbPara);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setNotDisturbPara ," + notDisturbPara.toString());
        o.a(notDisturbPara);
    }

    public static void a(NoticeDevicePermmsion noticeDevicePermmsion) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setNoticeDeviceDiableFunc");
        o.a(noticeDevicePermmsion);
    }

    public static void a(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        com.ido.ble.f.a.c.h().a(noticeReminderSwitchStatus);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setNoticeReminderSwitchStatus ," + noticeReminderSwitchStatus.toString());
        o.a(noticeReminderSwitchStatus);
    }

    public static void a(NoticeSportActionToggle noticeSportActionToggle) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setNoticeSportActionToggle, " + noticeSportActionToggle.toString());
        o.a(noticeSportActionToggle);
    }

    @Deprecated
    public static void a(NoticeSwitchInfo noticeSwitchInfo) {
        o.a(noticeSwitchInfo);
    }

    public static void a(NotificationPara notificationPara) {
        List<NotificationPara.AppNames> list = notificationPara.items;
        if (list != null) {
            notificationPara.app_items_len = list.size();
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "sendNotification ：" + notificationPara.toString());
        l.a(notificationPara);
    }

    @Deprecated
    public static void a(PhoneSystemInfo phoneSystemInfo) {
        o.a(phoneSystemInfo);
    }

    public static void a(PhoneVoice phoneVoice) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setPhoneVoice, " + phoneVoice.toString());
        o.a(phoneVoice);
    }

    public static void a(PressCalibration pressCalibration) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setPressCalibration");
        o.a(pressCalibration);
    }

    public static void a(PressureParam pressureParam) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setPressureParam " + pressureParam.toString());
        com.ido.ble.f.a.c.h().a(pressureParam);
        k.a(pressureParam);
    }

    public static void a(QuickReplyInfo quickReplyInfo) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setPhoneVoice, " + quickReplyInfo.toString());
        o.a(quickReplyInfo);
    }

    public static void a(QuickSportMode quickSportMode) {
        com.ido.ble.f.a.c.h().a(quickSportMode);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setQuickSportMode ," + quickSportMode.toString());
        o.a(quickSportMode);
    }

    public static void a(RespiratoryRate respiratoryRate) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setRespiratoryRate ," + respiratoryRate.toString());
        o.a(respiratoryRate);
    }

    public static void a(SPO2Param sPO2Param) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setSPO2Param " + sPO2Param.toString());
        com.ido.ble.f.a.c.h().a(sPO2Param);
        k.a(sPO2Param);
    }

    public static void a(ScheduleReminderSwitch scheduleReminderSwitch) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setScheduleReminderSwitch ," + scheduleReminderSwitch.toString());
        o.a(scheduleReminderSwitch);
    }

    public static void a(ScreenBrightness screenBrightness) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setScreenBrightness, =" + screenBrightness.toString());
        com.ido.ble.f.a.c.h().a(screenBrightness);
        o.a(screenBrightness);
    }

    public static void a(ShortCut shortCut) {
        com.ido.ble.f.a.c.h().a(shortCut);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setShortCut ," + shortCut.toString());
        o.a(shortCut);
    }

    public static void a(SleepMonitoringPara sleepMonitoringPara) {
        com.ido.ble.f.a.f.a.g0().a(sleepMonitoringPara);
        o.a(sleepMonitoringPara);
    }

    public static void a(SosSwitch sosSwitch) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setOneKeySOSSwitch ," + sosSwitch.toString());
        o.a(sosSwitch);
    }

    public static void a(SportModeSort sportModeSort) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setSportModeSortInfo " + sportModeSort.toString());
        com.ido.ble.f.a.c.h().a(sportModeSort);
        k.a(sportModeSort);
    }

    public static void a(SportModeSortV3 sportModeSortV3) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setSportModeSortInfoV3 " + sportModeSortV3.toString());
        com.ido.ble.f.a.c.h().a(sportModeSortV3);
        o.a(sportModeSortV3);
    }

    public static void a(SportPlan sportPlan) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  getSportPlan, " + sportPlan.toString());
        sportPlan.operate = 4;
        o.a(sportPlan);
    }

    public static void a(StopFindPhone stopFindPhone) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setStopFindPhone ," + stopFindPhone.toString());
        o.a(stopFindPhone);
    }

    public static void a(SystemTime systemTime) {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null && supportFunctionInfo.v2_support_set_time_zone_float) {
            systemTime.time_zone = TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 36000;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setTime ," + systemTime.toString());
        o.a(systemTime);
    }

    public static void a(Units units) {
        com.ido.ble.f.a.c.h().a(units);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setUnit ," + units.toString());
        o.a(units);
    }

    @Deprecated
    public static void a(UnreadMessageInfo unreadMessageInfo) {
        l.a(unreadMessageInfo);
    }

    public static void a(UpHandGesture upHandGesture) {
        com.ido.ble.f.a.c.h().a(upHandGesture);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setUpHandGesture ," + upHandGesture.toString());
        o.a(upHandGesture);
    }

    public static void a(UserInfo userInfo) {
        com.ido.ble.f.a.c.h().a(userInfo);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setUserInfo ," + userInfo.toString());
        UserInfo cloneNew = userInfo.cloneNew();
        cloneNew.weight = cloneNew.weight * 100;
        o.a(cloneNew);
        com.ido.ble.f.a.f.a.g0().a(userInfo);
    }

    public static void a(V3AppExchangeDataIngPara v3AppExchangeDataIngPara) {
        LogTool.d(com.ido.ble.logs.a.o, v3AppExchangeDataIngPara.toString());
        g.a(v3AppExchangeDataIngPara);
    }

    public static void a(V3MessageNotice v3MessageNotice) {
        v3MessageNotice.phoneNumber = com.ido.ble.common.i.b(v3MessageNotice.phoneNumber);
        v3MessageNotice.contact = com.ido.ble.common.i.c(v3MessageNotice.contact);
        v3MessageNotice.dataText = com.ido.ble.common.i.a(v3MessageNotice.dataText);
        LogTool.d(com.ido.ble.logs.a.f12307a, "setV3MessageNotice ：" + v3MessageNotice.evtType);
        l.a(v3MessageNotice);
    }

    public static void a(VoiceControlAlarm voiceControlAlarm) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  voiceControlAlarm " + voiceControlAlarm.toString());
        q.a(voiceControlAlarm);
    }

    public static void a(VoiceControlBrightnessLevel voiceControlBrightnessLevel) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  controlBrightnessLevel " + voiceControlBrightnessLevel.toString());
        q.a(voiceControlBrightnessLevel);
    }

    public static void a(VoiceControlFuncWithNoPara voiceControlFuncWithNoPara) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  controlFuncWithNoPara " + voiceControlFuncWithNoPara.toString());
        q.a(voiceControlFuncWithNoPara);
    }

    public static void a(VoiceControlMusic voiceControlMusic) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  controlMusic " + voiceControlMusic.toString());
        q.a(voiceControlMusic);
    }

    public static void a(VoiceControlReminder voiceControlReminder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  voiceControlReminder " + voiceControlReminder.toString());
        q.a(voiceControlReminder);
    }

    public static void a(VoiceControlSports voiceControlSports) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  controlSports " + voiceControlSports.toString());
        q.a(voiceControlSports);
    }

    public static void a(VoiceControlSwitchFunc voiceControlSwitchFunc) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  controlSwitchFunc " + voiceControlSwitchFunc.toString());
        q.a(voiceControlSwitchFunc);
    }

    public static void a(VoiceCountDown voiceCountDown) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  controlCountDown " + voiceCountDown.toString());
        q.a(voiceCountDown);
    }

    public static void a(VoiceLoginState voiceLoginState) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  setVoiceLoginState " + voiceLoginState.toString());
        q.a(voiceLoginState);
    }

    public static void a(VoiceNotifyState voiceNotifyState) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  setVoiceNotifyState " + voiceNotifyState.toString());
        q.a(voiceNotifyState);
    }

    public static void a(VoiceRecognizeState voiceRecognizeState) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  setVoiceRecognizeState " + voiceRecognizeState.toString());
        q.a(voiceRecognizeState);
    }

    public static void a(VoiceStopWatch voiceStopWatch) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to  controlStopWatch " + voiceStopWatch.toString());
        q.a(voiceStopWatch);
    }

    public static void a(VoiceToText voiceToText) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setVoiceToText, " + voiceToText.toString());
        o.a(voiceToText);
    }

    public static void a(WalkRealTimeReminder walkRealTimeReminder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setWalkRealTimeReminder ," + walkRealTimeReminder.toString());
        o.a(walkRealTimeReminder);
    }

    public static void a(WalkReminder walkReminder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setWalkReminder " + walkReminder.toString());
        com.ido.ble.f.a.c.h().a(walkReminder);
        o.a(walkReminder);
    }

    public static void a(WallpaperFileCreateConfig wallpaperFileCreateConfig) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  createPlateWallpaperFile " + wallpaperFileCreateConfig.toString());
        b(5500, com.ido.ble.common.k.a(wallpaperFileCreateConfig));
    }

    public static void a(WashHandReminder washHandReminder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setWashHandReminder, " + washHandReminder.toString());
        o.a(washHandReminder);
    }

    public static void a(WatchDialOrder watchDialOrder) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setWatchDialOrder");
        o.a(watchDialOrder);
    }

    public static void a(WeatherInfo weatherInfo) {
        LogTool.d(com.ido.ble.logs.a.p, weatherInfo.toString());
        c.a(weatherInfo);
    }

    public static void a(WeatherInfoV3 weatherInfoV3) {
        LogTool.d(com.ido.ble.logs.a.p, weatherInfoV3.toString());
        c.a(weatherInfoV3);
    }

    public static void a(WeatherSunTime weatherSunTime) {
        LogTool.d(com.ido.ble.logs.a.p, weatherSunTime.toString());
        c.a(weatherSunTime);
    }

    public static void a(PhotoWallpaperOperation.SetParams setParams) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  photoWallpaperOperate " + setParams.toString());
        com.ido.ble.watch.custom.b.a(setParams);
    }

    public static void a(WatchPlateFileMakeConfig watchPlateFileMakeConfig) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  makeWatchPlateFile " + watchPlateFileMakeConfig.toString());
        com.ido.ble.watch.custom.d.a(watchPlateFileMakeConfig);
    }

    private static void a(WatchPlateOperation watchPlateOperation) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  watchPlateOperate " + watchPlateOperation.toString());
        com.ido.ble.watch.custom.d.a(watchPlateOperation);
    }

    public static void a(String str) {
        WatchPlateOperation watchPlateOperation = new WatchPlateOperation();
        watchPlateOperation.operate = 2;
        watchPlateOperation.fileName = str;
        a(watchPlateOperation);
    }

    public static void a(List<FrequentContactsV3> list) {
        FrequentContactsV3CmdParaWrapper.Add add = new FrequentContactsV3CmdParaWrapper.Add();
        add.items_num = list.size();
        ArrayList arrayList = new ArrayList();
        add.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to add FrequentContactsV3 ," + new Gson().toJson(add));
        j.a(add);
    }

    public static void a(List<Integer> list, int i) {
        Sport100TypeSort sport100TypeSort = new Sport100TypeSort();
        sport100TypeSort.operate = 2;
        sport100TypeSort.all_num = list.size();
        sport100TypeSort.now_user_location = i;
        ArrayList arrayList = new ArrayList();
        sport100TypeSort.items_set = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  setSport100TypeSort, " + sport100TypeSort.toString());
        j.b(sport100TypeSort);
    }

    public static void a(List<MessageNotifyState> list, int i, int i2) {
        MessageNotifyStateCmdParaWrapper.Add add = new MessageNotifyStateCmdParaWrapper.Add();
        add.items_num = list.size();
        add.all_send_num = i;
        add.now_send_index = i2;
        ArrayList arrayList = new ArrayList();
        add.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to add MessageNotifyState ," + new Gson().toJson(add));
        j.a(add);
    }

    public static void a(boolean z) {
        com.ido.ble.f.a.c.h().a(z);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setFindPhoneSwitch ," + z);
        o.a(z);
    }

    public static void a(int[] iArr) {
        BindAuth bindAuth = new BindAuth();
        bindAuth.auth_code = iArr;
        bindAuth.auth_length = (iArr == null || iArr.length <= 0) ? 0 : iArr.length;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to setBindAuth " + bindAuth.toString());
        com.ido.ble.bluetooth.a.g(com.ido.ble.common.k.a(bindAuth));
        int a2 = d.a(bindAuth);
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(a2)) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] bind failed! so check error. error =" + a2);
            com.ido.ble.event.stat.one.c.a("error:" + a2);
            BindCallBack.b();
        }
    }

    public static boolean a0() {
        return Protocol.IS_SYNC_CONFIG;
    }

    public static void b() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start cancelBind...");
        d.a();
    }

    public static void b(int i) {
        SportSubItemParaSort sportSubItemParaSort = new SportSubItemParaSort();
        sportSubItemParaSort.operate = 1;
        sportSubItemParaSort.sport_type = i;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  querySportSubItemParaSort, " + sportSubItemParaSort.toString());
        j.a(sportSubItemParaSort);
    }

    public static void b(int i, String str) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  setParaToDeviceByTypeAndJson, type= " + i + ",jsonPara=" + str);
        u.b(com.ido.ble.common.c.b(str), i);
    }

    public static void b(DeviceControlAppCallBack.ReplyDeviceControlState replyDeviceControlState) {
        n.b(replyDeviceControlState);
    }

    public static void b(MusicOperate.MusicFile musicFile) {
        MusicOperate musicOperate = new MusicOperate();
        musicOperate.music_operate = 1;
        musicOperate.music_items = musicFile;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  deleteMusicFile, " + musicFile.toString());
        j.a(musicOperate);
    }

    public static void b(MusicOperate.MusicFolder musicFolder) {
        MusicOperate musicOperate = new MusicOperate();
        musicOperate.folder_operate = 1;
        musicOperate.folder_items = musicFolder;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  deleteMusicFolder, " + musicFolder.toString());
        j.a(musicOperate);
    }

    public static void b(SportPlan sportPlan) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setSportPlanDataSend, " + sportPlan.toString());
        sportPlan.operate = 2;
        o.a(sportPlan);
    }

    public static void b(String str) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to forceUnbind..." + str);
        com.ido.ble.bluetooth.b.a(str);
        UnbindCallBack.b();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] force unbind ok!" + str);
    }

    public static void b(List<ScheduleReminderV3> list) {
        ScheduleReminderV3CmdParaWrapper.Add add = new ScheduleReminderV3CmdParaWrapper.Add();
        add.num = list.size();
        ArrayList arrayList = new ArrayList();
        add.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to add ScheduleReminderV3 ," + new Gson().toJson(add));
        j.a(add);
    }

    public static void b(List<MessageNotifyState> list, int i, int i2) {
        MessageNotifyStateCmdParaWrapper.Modify modify = new MessageNotifyStateCmdParaWrapper.Modify();
        modify.items_num = list.size();
        modify.all_send_num = i;
        modify.now_send_index = i2;
        ArrayList arrayList = new ArrayList();
        modify.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to modify MessageNotifyState ," + new Gson().toJson(modify));
        j.a(modify);
    }

    public static void b(boolean z) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] setIsAutoSyncConfigIfReboot, " + z);
        o.b(z);
    }

    public static boolean b0() {
        return p.e();
    }

    public static void c() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  clearDeviceHeatLog ");
        DeviceLogClearPara deviceLogClearPara = new DeviceLogClearPara();
        deviceLogClearPara.type = 1;
        e.a(deviceLogClearPara);
    }

    public static void c(int i) {
        u.a((int) com.veryfit.multi.nativeprotocol.b.u5, i, 0, 0);
    }

    public static void c(DeviceControlAppCallBack.ReplyDeviceControlState replyDeviceControlState) {
        n.c(replyDeviceControlState);
    }

    public static void c(MusicOperate.MusicFolder musicFolder) {
        MusicOperate musicOperate = new MusicOperate();
        musicOperate.folder_operate = 4;
        musicOperate.folder_items = musicFolder;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  moveMusicIntoFolder, " + musicOperate.toString());
        j.a(musicOperate);
    }

    public static void c(SportPlan sportPlan) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setSportPlanEnd, " + sportPlan.toString());
        sportPlan.operate = 3;
        o.a(sportPlan);
    }

    public static void c(String str) {
        WatchPlateOperation watchPlateOperation = new WatchPlateOperation();
        watchPlateOperation.operate = 1;
        watchPlateOperation.fileName = str;
        a(watchPlateOperation);
    }

    public static void c(List<FrequentContactsV3> list) {
        FrequentContactsV3CmdParaWrapper.Delete delete = new FrequentContactsV3CmdParaWrapper.Delete();
        delete.items_num = list.size();
        ArrayList arrayList = new ArrayList();
        delete.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to delete FrequentContactsV3 ," + new Gson().toJson(delete));
        j.a(delete);
    }

    public static void c(List<Integer> list, int i, int i2) {
        SportSubItemParaSort sportSubItemParaSort = new SportSubItemParaSort();
        sportSubItemParaSort.operate = 2;
        sportSubItemParaSort.all_num = list.size();
        sportSubItemParaSort.now_user_location = i;
        sportSubItemParaSort.sport_type = i2;
        ArrayList arrayList = new ArrayList();
        sportSubItemParaSort.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  setSportSubItemParaSort, " + sportSubItemParaSort.toString());
        j.b(sportSubItemParaSort);
    }

    public static void c(boolean z) {
        com.ido.ble.f.a.c.h().b(z);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setMusicSwitch ," + z);
        o.c(z);
        com.ido.ble.f.a.f.a.g0().b(z);
    }

    public static void c0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "missedInComingCall ");
        l.a();
    }

    @Deprecated
    public static void d() {
        b.a();
    }

    public static void d(int i) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  setParaToDeviceByType, type= " + i);
        u.b("{}".getBytes(), i);
    }

    public static void d(MusicOperate.MusicFolder musicFolder) {
        MusicOperate musicOperate = new MusicOperate();
        musicOperate.folder_operate = 5;
        musicOperate.folder_items = musicFolder;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  removeMusicFromFolder, " + musicOperate.toString());
        j.a(musicOperate);
    }

    public static void d(SportPlan sportPlan) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setStartSportPlan, " + sportPlan.toString());
        sportPlan.operate = 1;
        o.a(sportPlan);
    }

    public static void d(String str) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setWeatherCityName, " + str);
        o.a(str);
    }

    public static void d(List<ScheduleReminderV3> list) {
        ScheduleReminderV3CmdParaWrapper.Delete delete = new ScheduleReminderV3CmdParaWrapper.Delete();
        delete.num = list.size();
        ArrayList arrayList = new ArrayList();
        delete.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to delete ScheduleReminderV3 ," + new Gson().toJson(delete));
        j.a(delete);
    }

    public static void d(boolean z) {
        com.ido.ble.f.a.c.h().c(z);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setOneKeySOSSwitch ," + z);
        o.d(z);
    }

    @Deprecated
    public static void d0() {
        b.i();
    }

    public static void e() {
        b.b();
    }

    public static void e(int i) {
        ScreenBrightness S = com.ido.ble.f.a.f.a.g0().S();
        if (S == null) {
            S = new ScreenBrightness();
        }
        S.level = i;
        S.opera = 1;
        a(S);
    }

    public static void e(MusicOperate.MusicFolder musicFolder) {
        MusicOperate musicOperate = new MusicOperate();
        musicOperate.folder_operate = 3;
        musicOperate.folder_items = musicFolder;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  updateMusicFolder, " + musicFolder.toString());
        j.a(musicOperate);
    }

    public static void e(List<FrequentContactsV3> list) {
        FrequentContactsV3CmdParaWrapper.Modify modify = new FrequentContactsV3CmdParaWrapper.Modify();
        modify.items_num = list.size();
        ArrayList arrayList = new ArrayList();
        modify.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to modify FrequentContactsV3 ," + new Gson().toJson(modify));
        j.a(modify);
    }

    public static void e(boolean z) {
        ScreenBrightness S = com.ido.ble.f.a.f.a.g0().S();
        if (S == null) {
            S = new ScreenBrightness();
            S.level = 100;
        }
        S.mode = z ? 1 : 0;
        S.autoAdjustNight = 2;
        S.opera = 1;
        a(S);
    }

    public static void e0() {
        FrequentContactsV3CmdParaWrapper.Query query = new FrequentContactsV3CmdParaWrapper.Query();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to query FrequentContactsV3 ," + new Gson().toJson(query));
        j.a(query);
    }

    public static void f() {
        f.a();
    }

    public static void f(List<ScheduleReminderV3> list) {
        ScheduleReminderV3CmdParaWrapper.Modify modify = new ScheduleReminderV3CmdParaWrapper.Modify();
        modify.num = list.size();
        ArrayList arrayList = new ArrayList();
        modify.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to modify ScheduleReminderV3 ," + new Gson().toJson(modify));
        j.a(modify);
    }

    public static void f(boolean z) {
        com.ido.ble.f.a.c.h().d(z);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setWeatherSwitch ," + z);
        o.e(z);
    }

    public static void f0() {
        MessageNotifyStateCmdParaWrapper.Query query = new MessageNotifyStateCmdParaWrapper.Query();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to query MessageNotifyState ," + new Gson().toJson(query));
        j.a(query);
    }

    public static void g() {
        b.c();
    }

    public static void g(List<Alarm> list) {
        ArrayList arrayList = new ArrayList();
        u.m();
        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
            boolean[] zArr = new boolean[7];
            for (int i = 0; i < 7; i++) {
                zArr[i] = false;
            }
            for (int i2 = 1; i2 <= 10; i2++) {
                Alarm alarm = new Alarm();
                alarm.setAlarmId(i2);
                alarm.setAlarmHour(8);
                alarm.setAlarmMinute(30);
                alarm.setAlarmStatus(170);
                alarm.setAlarmType(7);
                alarm.setAlarmSnoozeDuration(0);
                alarm.setWeekRepeat(zArr);
                alarm.setOn_off(false);
                list.add(alarm);
                o.a(alarm);
            }
        } else {
            Map<Integer, Alarm> f = com.ido.ble.f.a.f.a.g0().f();
            for (Alarm alarm2 : list) {
                if (f.containsKey(Integer.valueOf(alarm2.getAlarmId()))) {
                    f.remove(Integer.valueOf(alarm2.getAlarmId()));
                }
            }
            if (f.size() != 0) {
                for (Map.Entry<Integer, Alarm> entry : f.entrySet()) {
                    Alarm value = entry.getValue();
                    value.setOn_off(false);
                    value.setAlarmStatus(170);
                    value.setWeekRepeat(new boolean[]{false, false, false, false, false, false, false});
                    arrayList.add(value);
                    o.a(value);
                }
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] remove Alarms ," + new Gson().toJson(f));
            }
            for (Alarm alarm3 : list) {
                o.a(alarm3);
            }
        }
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setAlarm ," + new Gson().toJson(list));
        com.ido.ble.f.a.c.h().a((List<Alarm>) arrayList);
        u.k();
    }

    public static void g0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  queryMusicAndFolderInfo");
        j.a();
    }

    @Deprecated
    public static void h() {
        b.d();
    }

    public static void h(List<AlarmV3> list) {
        AlarmV3CmdParaWrapper.AlarmSet alarmSet = new AlarmV3CmdParaWrapper.AlarmSet();
        alarmSet.version = 0;
        alarmSet.num = list.size();
        ArrayList arrayList = new ArrayList();
        alarmSet.item = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to set AlarmsV3 ," + new Gson().toJson(alarmSet));
        o.a(alarmSet);
    }

    public static void h0() {
        ScheduleReminderV3CmdParaWrapper.Query query = new ScheduleReminderV3CmdParaWrapper.Query();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to query ScheduleReminderV3 ," + new Gson().toJson(query));
        j.a(query);
    }

    public static void i() {
        b.e();
    }

    public static void i(List<FrequentContactsV3> list) {
        FrequentContactsV3CmdParaWrapper.Set set = new FrequentContactsV3CmdParaWrapper.Set();
        set.items_num = list.size();
        ArrayList arrayList = new ArrayList();
        set.items = arrayList;
        arrayList.addAll(list);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to set FrequentContactsV3 ," + new Gson().toJson(set));
        j.a(set);
    }

    public static void i0() {
        SmallQuickModuleCmdParaWrapper.Query query = new SmallQuickModuleCmdParaWrapper.Query();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to get querySmallQuickModule ," + new Gson().toJson(query));
        j.a(query);
    }

    public static void j() {
        b.f();
    }

    public static void j(List<SmallQuickModule> list) {
        SmallQuickModuleCmdParaWrapper.Set set = new SmallQuickModuleCmdParaWrapper.Set();
        ArrayList arrayList = new ArrayList();
        set.items = arrayList;
        arrayList.addAll(list);
        set.all_num = list.size();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to addSmallQuickModule ," + new Gson().toJson(set));
        j.a(set);
    }

    public static void j0() {
        Sport100TypeSort sport100TypeSort = new Sport100TypeSort();
        sport100TypeSort.operate = 1;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] start to  querySport100TypeSort, " + sport100TypeSort.toString());
        j.a(sport100TypeSort);
    }

    @Deprecated
    public static void k() {
        b.g();
    }

    public static void k(List<WorldTime.Item> list) {
        WorldTime worldTime = new WorldTime();
        ArrayList<WorldTime.Item> arrayList = new ArrayList<>();
        worldTime.items = arrayList;
        arrayList.addAll(list);
        worldTime.items_num = list.size();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to set worldTime, =" + worldTime.toString());
        o.a(worldTime);
    }

    public static void k0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "to reboot");
        m.a();
    }

    public static void l() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to forceUnbind...");
        com.ido.ble.bluetooth.b.b();
        UnbindCallBack.b();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] force unbind ok!");
    }

    public static void l0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to send bind cmd...");
        BindPara bindPara = new BindPara();
        bindPara.os_type = 2;
        bindPara.os_version = 2;
        bindPara.is_clean_data = 1;
        bindPara.bind_version = 1;
        int a2 = d.a(bindPara);
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(a2)) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to send bind cmd failed! .so check error. error =" + a2);
            com.ido.ble.event.stat.one.c.a("error:" + a2);
            BindCallBack.b();
        }
    }

    public static void m() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getActivityCount...");
        h.a();
    }

    public static void m0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to setOneKeyReset");
        o.a();
    }

    public static void n() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to getActivitySwitch");
        i.a();
    }

    public static void n0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] start to  setRestoreFactory ");
        o.b();
    }

    public static void o() {
        AlarmV3CmdParaWrapper.AlarmGet alarmGet = new AlarmV3CmdParaWrapper.AlarmGet();
        alarmGet.flag = 0;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] start to get AlarmsV3 ," + new Gson().toJson(alarmGet));
        i.a(alarmGet);
    }

    public static void o0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "setStopInComingCall ");
        l.b();
    }

    public static void p() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to  getAllHealthMonitorSwitch");
        i.b();
    }

    public static void p0() {
        a(com.ido.ble.common.e.a(true));
    }

    public static void q() {
        com.ido.ble.f.a.c.h().b();
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getBasicInfo...");
        h.b();
    }

    public static void q0() {
        r.c();
    }

    public static void r() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getBatteryInfo...");
        h.c();
    }

    public static void r0() {
        b.j();
    }

    public static void s() {
        b.h();
    }

    public static void s0() {
        b.k();
    }

    public static void t() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to  getBtA2dpHfpStatus");
        i.c();
    }

    public static void t0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start sync activity data...");
        int f = p.f();
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(f)) {
            SyncCallBack.b();
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start sync activity data failed! so.lib check error.");
        com.ido.ble.event.stat.one.c.a("sync_activity", "error:" + f);
        SyncCallBack.a();
    }

    public static void u() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getCanDownloadLangInfo...");
        h.d();
    }

    public static void u0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start sync config...");
        int g = p.g();
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(g)) {
            SyncCallBack.f();
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start sync config failed! so check error.");
        com.ido.ble.event.stat.one.c.a("sync_config", "error:" + g);
        SyncCallBack.e();
    }

    public static void v() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getCanDownloadLangInfoV3...");
        h.e();
    }

    public static void v0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start sync health data...");
        com.ido.ble.common.g.b();
        int h = p.h();
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(h)) {
            SyncCallBack.j();
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start sync health data failed! so.lib check error.");
        com.ido.ble.event.stat.one.c.a("sync_health", "error:" + h);
        SyncCallBack.i();
    }

    public static void w() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to  getContactReceiveTime");
        i.d();
    }

    public static int w0() {
        int i = p.i();
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i)) {
            SyncV3CallBack.onSyncV3HealthStart();
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] start sync v3 data failed! so.lib check error.");
            SyncV3CallBack.onSyncV3HealthFailed();
        }
        return i;
    }

    public static void x() {
        WatchPlateOperation watchPlateOperation = new WatchPlateOperation();
        watchPlateOperation.operate = 0;
        a(watchPlateOperation);
    }

    public static void x0() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICEstart to VoiceRecognize ");
        q.b();
    }

    public static void y() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] start to  getDeviceHeatLog ");
        e.a();
    }

    public static void y0() {
        b.l();
    }

    public static void z() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] start to getDeviceSummarySoftVersionInfo...");
        h.f();
    }

    public static void z0() {
        b.m();
    }
}
