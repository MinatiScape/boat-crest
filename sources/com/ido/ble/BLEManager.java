package com.ido.ble;

import android.app.Application;
import com.ido.ble.bluetooth.d.f;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.bluetooth.f.e;
import com.ido.ble.business.multidevice.ICommonListener;
import com.ido.ble.business.sync.SyncPara;
import com.ido.ble.callback.AppControlDeviceCallBack;
import com.ido.ble.callback.AppExchangeDataCallBack;
import com.ido.ble.callback.AppSendAllPhoneContactsCallBack;
import com.ido.ble.callback.AppSendDataCallBack;
import com.ido.ble.callback.AutoConnectErrorHappenListener;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.callback.BloodPressureMeasureCallBack;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.callback.DeviceExchangeDataCallBack;
import com.ido.ble.callback.DeviceLogCallBack;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.callback.NoticeSportActionToggleCallBack;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.PhoneMsgNoticeCallBack;
import com.ido.ble.callback.QueryStatusCallBack;
import com.ido.ble.callback.RebootCallback;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.callback.SetPressCalibrationCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.callback.SportPlanCallBack;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.callback.UnbindCallBack;
import com.ido.ble.callback.UserHabitCallBack;
import com.ido.ble.callback.V3AppExchangeDataCallBack;
import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.callback.c;
import com.ido.ble.callback.e;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.custom.MakeGpsFileConfig;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.BleDFUState;
import com.ido.ble.event.stat.one.IEventStatCallBack;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.spp.SPPFileTransferConfig;
import com.ido.ble.firmware.log.ICollectDeviceRebootLogListener;
import com.ido.ble.firmware.log.flash.ICollectFlashLogListener;
import com.ido.ble.gps.agps.AgpsFileTransConfig;
import com.ido.ble.gps.agps.IAGpsTranslateStateListener;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ControlGps;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.icon.transfer.IIconTransferListener;
import com.ido.ble.icon.transfer.IconTranConfig;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.ble.protocol.model.AllPhoneContacts;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.AppExchangeDataIngPara;
import com.ido.ble.protocol.model.AppExchangeDataPausePara;
import com.ido.ble.protocol.model.AppExchangeDataResumePara;
import com.ido.ble.protocol.model.AppExchangeDataStartPara;
import com.ido.ble.protocol.model.AppExchangeDataStopPara;
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
import com.ido.ble.protocol.model.MusicControlInfo;
import com.ido.ble.protocol.model.MusicOperate;
import com.ido.ble.protocol.model.NewMessageInfo;
import com.ido.ble.protocol.model.NightTemperatureMonitoringPara;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.NoticeDevicePermmsion;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.NoticeSportActionToggle;
import com.ido.ble.protocol.model.NotificationPara;
import com.ido.ble.protocol.model.PhoneVoice;
import com.ido.ble.protocol.model.PressCalibration;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickReplyInfo;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.RespiratoryRate;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScheduleReminderSwitch;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SmallQuickModule;
import com.ido.ble.protocol.model.SosSwitch;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SportPlan;
import com.ido.ble.protocol.model.StopFindPhone;
import com.ido.ble.protocol.model.SystemTime;
import com.ido.ble.protocol.model.Units;
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
import com.ido.ble.watch.custom.WatchPlateSetConfig;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import java.util.List;
/* loaded from: classes11.dex */
public final class BLEManager {
    public static void AppControlAllConfigSync() {
        com.ido.ble.i.a.a.a();
    }

    public static void addAutoConnectErrorOccurredListener(AutoConnectErrorHappenListener.IListener iListener) {
        com.ido.ble.callback.b.N().a(iListener);
    }

    public static void addDFUStateListener(BleDFUState.IListener iListener) {
        com.ido.ble.dfu.b.f().a(iListener);
    }

    public static void addDeviceUpgradeEventListener(DeviceUpgradeEventListener.IListener iListener) {
        com.ido.ble.callback.b.N().a(iListener);
    }

    public static void addFrequentContactsV3(List<FrequentContactsV3> list) {
        com.ido.ble.i.a.a.a(list);
    }

    public static void addMessageNotifyState(List<MessageNotifyState> list, int i, int i2) {
        com.ido.ble.i.a.a.a(list, i, i2);
    }

    public static void addMusicFile(MusicOperate.MusicFile musicFile) {
        com.ido.ble.i.a.a.a(musicFile);
    }

    public static void addMusicFolder(MusicOperate.MusicFolder musicFolder) {
        com.ido.ble.i.a.a.a(musicFolder);
    }

    public static void addScheduleReminderV3(List<ScheduleReminderV3> list) {
        com.ido.ble.i.a.a.b(list);
    }

    public static void appExchangeDataIng(AppExchangeDataIngPara appExchangeDataIngPara) {
        com.ido.ble.i.a.a.a(appExchangeDataIngPara);
    }

    public static void appExchangeDataPause(AppExchangeDataPausePara appExchangeDataPausePara) {
        com.ido.ble.i.a.a.a(appExchangeDataPausePara);
    }

    public static void appExchangeDataResume(AppExchangeDataResumePara appExchangeDataResumePara) {
        com.ido.ble.i.a.a.a(appExchangeDataResumePara);
    }

    public static void appExchangeDataStart(AppExchangeDataStartPara appExchangeDataStartPara) {
        com.ido.ble.i.a.a.a(appExchangeDataStartPara);
    }

    public static void appExchangeDataStop(AppExchangeDataStopPara appExchangeDataStopPara) {
        com.ido.ble.i.a.a.a(appExchangeDataStopPara);
    }

    public static void autoConnect() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->autoConnect.");
        com.ido.ble.bluetooth.a.a();
    }

    public static void autoConnect(String str) {
        String str2 = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str2, "BLEManager->autoConnect. macAddress is " + str);
        com.ido.ble.bluetooth.a.b(str);
    }

    public static void bind() {
        com.ido.ble.i.a.a.q0();
    }

    public static void bindWithNoRetry() {
        com.ido.ble.i.a.a.l0();
    }

    public static void cancelDFU() {
        com.ido.ble.dfu.c.a();
    }

    public static void clearBtMacAddress() {
        com.ido.ble.f.a.f.a.g0().b();
    }

    public static void clearDeviceHeatLog() {
        com.ido.ble.i.a.a.c();
    }

    public static void clearDeviceLog(DeviceLogClearPara deviceLogClearPara) {
        com.ido.ble.i.a.a.a(deviceLogClearPara);
    }

    public static void collectDeviceFlashLog(int i, String str, int i2, ICollectFlashLogListener iCollectFlashLogListener) {
        com.ido.ble.firmware.log.flash.a.b().a(i, str, i2, iCollectFlashLogListener);
    }

    public static void collectDeviceFlashLog(String str, int i, ICollectFlashLogListener iCollectFlashLogListener) {
        com.ido.ble.firmware.log.flash.a.b().a(0, str, i, iCollectFlashLogListener);
    }

    public static void collectDeviceFlashLogSecondChip(int i, String str, int i2, ICollectFlashLogListener iCollectFlashLogListener) {
        com.ido.ble.firmware.log.flash.a.b().b(i, str, i2, iCollectFlashLogListener);
    }

    public static void collectDeviceFlashLogSecondChip(String str, int i, ICollectFlashLogListener iCollectFlashLogListener) {
        com.ido.ble.firmware.log.flash.a.b().b(0, str, i, iCollectFlashLogListener);
    }

    public static void collectDeviceRebootLog(ICollectDeviceRebootLogListener iCollectDeviceRebootLogListener) {
        com.ido.ble.firmware.log.d.a(iCollectDeviceRebootLogListener);
    }

    public static void connect(BLEDevice bLEDevice) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->connect1.");
        stopScanDevices();
        com.ido.ble.bluetooth.a.b(bLEDevice);
    }

    public static void connect(BLEDevice bLEDevice, long j) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->connect2.");
        com.ido.ble.bluetooth.a.a(bLEDevice, j);
    }

    public static void connectBT() {
        com.ido.ble.bluetooth.c.b();
    }

    public static void createPlateWallpaperFile(WallpaperFileCreateConfig wallpaperFileCreateConfig) {
        com.ido.ble.i.a.a.a(wallpaperFileCreateConfig);
    }

    public static void deleteFrequentContactsV3(List<FrequentContactsV3> list) {
        com.ido.ble.i.a.a.c(list);
    }

    public static void deleteMusicFile(MusicOperate.MusicFile musicFile) {
        com.ido.ble.i.a.a.b(musicFile);
    }

    public static void deleteMusicFolder(MusicOperate.MusicFolder musicFolder) {
        com.ido.ble.i.a.a.b(musicFolder);
    }

    public static void deleteScheduleReminderV3(List<ScheduleReminderV3> list) {
        com.ido.ble.i.a.a.d(list);
    }

    public static void deleteWatchPlate(String str) {
        com.ido.ble.i.a.a.a(str);
    }

    public static void disConnect() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->to disConnect");
        com.ido.ble.bluetooth.a.b();
    }

    public static void disConnect(Runnable runnable) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->to disConnect(runnable)");
        com.ido.ble.bluetooth.a.a(runnable);
    }

    public static void enterCameraMode() {
        com.ido.ble.i.a.a.e();
    }

    public static void enterDfuMode() {
        com.ido.ble.i.a.a.f();
    }

    public static void enterMusicMode() {
        com.ido.ble.i.a.a.g();
    }

    public static void exitCameraMode() {
        com.ido.ble.i.a.a.i();
    }

    public static void exitMusicMode() {
        com.ido.ble.i.a.a.j();
    }

    public static void forceUnbind() {
        com.ido.ble.i.a.a.l();
    }

    public static void forceUnbind(String str) {
        com.ido.ble.i.a.a.b(str);
    }

    public static int funcTableOutputOnJsonFile(String str) {
        return u.a(str);
    }

    public static void getActivityCount() {
        com.ido.ble.i.a.a.m();
    }

    public static void getActivitySwitch() {
        com.ido.ble.i.a.a.n();
    }

    public static void getAlarmV3() {
        com.ido.ble.i.a.a.o();
    }

    public static void getAllHealthMonitorSwitch() {
        com.ido.ble.i.a.a.p();
    }

    public static void getBasicInfo() {
        com.ido.ble.i.a.a.q();
    }

    public static void getBatteryInfo() {
        com.ido.ble.i.a.a.r();
    }

    public static void getBloodPressureData() {
        com.ido.ble.i.a.a.s();
    }

    public static void getBtA2dpHfpStatus() {
        com.ido.ble.i.a.a.t();
    }

    public static String getBtMacAddress() {
        return com.ido.ble.f.a.f.a.g0().m();
    }

    public static void getCanDownloadLangInfo() {
        com.ido.ble.i.a.a.u();
    }

    public static void getCanDownloadLangInfoV3() {
        com.ido.ble.i.a.a.v();
    }

    public static void getContactReceiveTime() {
        com.ido.ble.i.a.a.w();
    }

    public static void getCurrentWatchPlate() {
        com.ido.ble.i.a.a.x();
    }

    public static void getDeviceHeatLog() {
        com.ido.ble.i.a.a.y();
    }

    public static void getDeviceSummarySoftVersionInfo() {
        com.ido.ble.i.a.a.z();
    }

    public static long getDialPlatSize(String str, int i) {
        return com.ido.ble.watch.custom.a.j().a(str, i);
    }

    public static void getDialPlateParam() {
        com.ido.ble.i.a.a.A();
    }

    public static void getDoNotDisturbPara() {
        com.ido.ble.i.a.a.B();
    }

    public static void getExChangeDataHeartRateInterval(DeviceExchangeDataStopPara deviceExchangeDataStopPara) {
        com.ido.ble.i.a.a.a(deviceExchangeDataStopPara);
    }

    public static void getFirmwareAndBt3Version() {
        com.ido.ble.i.a.a.D();
    }

    public static void getFlashBinInfo() {
        com.ido.ble.i.a.a.E();
    }

    public static void getFunctionTables() {
        com.ido.ble.f.a.c.h().g();
        com.ido.ble.i.a.a.T();
    }

    public static void getGpsHotPara() {
        com.ido.ble.h.a.b();
    }

    public static void getGpsInfo() {
        com.ido.ble.h.a.c();
    }

    public static void getGpsStatus() {
        com.ido.ble.h.a.d();
    }

    public static void getHIDInfo() {
        com.ido.ble.i.a.a.F();
    }

    public static void getHabitInfo() {
        com.ido.ble.i.a.a.G();
    }

    public static void getInfoFromDeviceByType(int i) {
        com.ido.ble.i.a.a.a(i);
    }

    public static void getLiveData() {
        com.ido.ble.i.a.a.H();
    }

    public static void getMacAddress() {
        com.ido.ble.i.a.a.I();
    }

    public static void getMenuList() {
        com.ido.ble.i.a.a.J();
    }

    public static void getNoticeReminderSwitchStatus() {
        com.ido.ble.i.a.a.K();
    }

    public static void getPressCalibrationValue() {
        com.ido.ble.i.a.a.M();
    }

    public static void getScreenBrightness() {
        com.ido.ble.i.a.a.O();
    }

    public static void getSportPlan(SportPlan sportPlan) {
        com.ido.ble.i.a.a.a(sportPlan);
    }

    public static void getSportThreeCircleGoal() {
        com.ido.ble.i.a.a.P();
    }

    public static void getSupportSportInfoV3() {
        com.ido.ble.i.a.a.Q();
    }

    public static void getUpHandGesture() {
        com.ido.ble.i.a.a.S();
    }

    public static int getV3HealthCount() {
        return com.ido.ble.i.a.a.U();
    }

    public static void getVoiceDefaultLang() {
        com.ido.ble.i.a.a.V();
    }

    public static void getWalkReminder() {
        com.ido.ble.i.a.a.W();
    }

    public static void getWatchPlateList() {
        com.ido.ble.i.a.a.X();
    }

    public static void getWatchPlateScreenInfo() {
        com.ido.ble.i.a.a.Y();
    }

    public static void init() {
        b.c();
    }

    public static void init(InitParam initParam) {
        b.b(initParam);
    }

    public static boolean isBind() {
        return com.ido.ble.bluetooth.a.g();
    }

    public static boolean isBondWithPhoneBluetooth(String str) {
        return e.c(str);
    }

    public static boolean isConnectBt() {
        return com.ido.ble.bluetooth.c.a();
    }

    public static boolean isConnected() {
        return com.ido.ble.bluetooth.a.h();
    }

    public static boolean isConnected(String str) {
        return com.ido.ble.bluetooth.a.c(str);
    }

    public static boolean isSyncAllDataIng() {
        return com.ido.ble.business.sync.b.d().a();
    }

    public static boolean isSyncingActivityData() {
        return com.ido.ble.i.a.a.Z();
    }

    public static boolean isSyncingConfig() {
        return com.ido.ble.i.a.a.a0();
    }

    public static boolean isSyncingHealthData() {
        return com.ido.ble.i.a.a.b0();
    }

    public static void makGpsFile(MakeGpsFileConfig makeGpsFileConfig) {
        com.ido.ble.h.a.a(makeGpsFileConfig);
    }

    public static void missedInComingCall() {
        com.ido.ble.i.a.a.c0();
    }

    public static void modifyFrequentContactsV3(List<FrequentContactsV3> list) {
        com.ido.ble.i.a.a.e(list);
    }

    public static void modifyMessageNotifyState(List<MessageNotifyState> list, int i, int i2) {
        com.ido.ble.i.a.a.b(list, i, i2);
    }

    public static void modifyScheduleReminderV3(List<ScheduleReminderV3> list) {
        com.ido.ble.i.a.a.f(list);
    }

    public static void moveMusicIntoFolder(MusicOperate.MusicFolder musicFolder) {
        com.ido.ble.i.a.a.c(musicFolder);
    }

    public static void onApplicationCreate(Application application) {
        b.a(application);
    }

    public static void photoWallpaperOperate(PhotoWallpaperOperation.SetParams setParams) {
        com.ido.ble.i.a.a.a(setParams);
    }

    public static void playAlexaVoice(String str) {
        com.ido.ble.e.c.a.f().a(str);
    }

    public static int png2Bmp(String str, String str2, int i) {
        return u.a(str, str2, i);
    }

    public static void queryBloodPressureAdjustResult(BloodPressureQueryAdjustResultPara bloodPressureQueryAdjustResultPara) {
        com.ido.ble.i.a.a.a(bloodPressureQueryAdjustResultPara);
    }

    public static void queryFrequentContactsV3() {
        com.ido.ble.i.a.a.e0();
    }

    public static void queryMessageNotifyState() {
        com.ido.ble.i.a.a.f0();
    }

    public static void queryMusicAndFolderInfo() {
        com.ido.ble.i.a.a.g0();
    }

    public static void queryScheduleReminderV3() {
        com.ido.ble.i.a.a.h0();
    }

    public static void querySmallQuickModule() {
        com.ido.ble.i.a.a.i0();
    }

    public static void querySport100TypeSort() {
        com.ido.ble.i.a.a.j0();
    }

    public static void querySportSubItemParaSort(int i) {
        com.ido.ble.i.a.a.b(i);
    }

    public static void reBoot() {
        com.ido.ble.i.a.a.k0();
    }

    public static void registerAppControlDeviceCallBack(AppControlDeviceCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerAppExchangeDataCallBack(AppExchangeDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerAppSendAllPhoneContactsCallBack(AppSendAllPhoneContactsCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerAppSendDataCallBack(AppSendDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerBindCallBack(BindCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerBloodPressureMeasureCallBack(BloodPressureMeasureCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerConnectCallBack(ConnectCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerDeviceControlAppCallBack(DeviceControlAppCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerDeviceExchangeDataCallBack(DeviceExchangeDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerDeviceLogCallBack(DeviceLogCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerDeviceParaChangedCallBack(DeviceParaChangedCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerDeviceReplySetGpsCallBack(GpsCallBack.IDeviceReplySetGpsCallBack iDeviceReplySetGpsCallBack) {
        com.ido.ble.gps.callback.a.h().a(iDeviceReplySetGpsCallBack);
    }

    public static void registerDeviceResponseCommonCallBack(DeviceResponseCommonCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerDeviceVoiceChangedCallBack(c.b bVar) {
        com.ido.ble.callback.b.N().a(bVar);
    }

    public static void registerEnterDfuModeCallBack(EnterDfuModeCallback.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerGetDeviceInfoCallBack(GetDeviceInfoCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerGetDeviceParaCallBack(GetDeviceParaCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerGetGpsInfoCallBack(GpsCallBack.IGetGpsInfoCallBack iGetGpsInfoCallBack) {
        com.ido.ble.gps.callback.a.h().a(iGetGpsInfoCallBack);
    }

    public static void registerNoticeSportActionToggleCallBack(NoticeSportActionToggleCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerOperateCallBack(OperateCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerOperateMusicCallBack(OperateCallBack.IMusicCallBack iMusicCallBack) {
        com.ido.ble.callback.b.N().a(iMusicCallBack);
    }

    public static void registerOtherProtocolCallBack(OtherProtocolCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerPhoneMsgNoticeCallBack(PhoneMsgNoticeCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerPhotoWallpaperOperateCallBack(PhotoWallpaperOperateCallBack.ICallBack iCallBack) {
        com.ido.ble.watch.custom.callback.a.c().a(iCallBack);
    }

    public static void registerQueryStatusCallBack(QueryStatusCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerRebootCallBack(RebootCallback.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerScanCallBack(ScanCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerSetPressCalibrationCallBack(SetPressCalibrationCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerSettingCallBack(SettingCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerSportPlanCallBack(SportPlanCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerSyncActivityCallBack(SyncCallBack.IActivityCallBack iActivityCallBack) {
        com.ido.ble.callback.b.N().a(iActivityCallBack);
    }

    public static void registerSyncConfigCallBack(SyncCallBack.IConfigCallBack iConfigCallBack) {
        com.ido.ble.callback.b.N().a(iConfigCallBack);
    }

    public static void registerSyncGpsDataCallBack(GpsCallBack.ISyncGpsDataCallBack iSyncGpsDataCallBack) {
        com.ido.ble.gps.callback.a.h().a(iSyncGpsDataCallBack);
    }

    public static void registerSyncHealthCallBack(SyncCallBack.IHealthCallBack iHealthCallBack) {
        com.ido.ble.callback.b.N().a(iHealthCallBack);
    }

    public static void registerSyncV3CallBack(SyncV3CallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerTranAgpsFileCallBack(GpsCallBack.ITranAgpsFileCallBack iTranAgpsFileCallBack) {
        com.ido.ble.gps.callback.a.h().a(iTranAgpsFileCallBack);
    }

    public static void registerUnbindCallBack(UnbindCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerUserCloseBluetoothCallBack(e.b bVar) {
        com.ido.ble.callback.b.N().a(bVar);
    }

    public static void registerUserHabitInfoCallBack(UserHabitCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerV3AppExchangeDataCallBack(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerVoiceCallBack(VoiceCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void registerWatchErrorCallBack(GpsCallBack.ITranAgpsWatchErrorCallBack iTranAgpsWatchErrorCallBack) {
        com.ido.ble.gps.callback.a.h().a(iTranAgpsWatchErrorCallBack);
    }

    public static void registerWatchOperateCallBack(WatchPlateCallBack.IOperateCallBack iOperateCallBack) {
        com.ido.ble.watch.custom.callback.a.c().a(iOperateCallBack);
    }

    public static void removeAutoConnectErrorOccurredListener(AutoConnectErrorHappenListener.IListener iListener) {
        com.ido.ble.callback.b.N().b(iListener);
    }

    public static boolean removeBondStatusFromPhoneBluetoothPairedList(String str) {
        return com.ido.ble.bluetooth.f.e.d(str);
    }

    public static void removeDFUStateListener(BleDFUState.IListener iListener) {
        com.ido.ble.dfu.b.f().b(iListener);
    }

    public static void removeDeviceUpgradeEventListener(DeviceUpgradeEventListener.IListener iListener) {
        com.ido.ble.callback.b.N().b(iListener);
    }

    public static void removeMusicFromFolder(MusicOperate.MusicFolder musicFolder) {
        com.ido.ble.i.a.a.d(musicFolder);
    }

    public static void replyDeviceExchangeDataIng(DeviceExchangeDataIngAppReplyPara deviceExchangeDataIngAppReplyPara) {
        com.ido.ble.i.a.a.a(deviceExchangeDataIngAppReplyPara);
    }

    public static void replyDeviceExchangeDataPause(DeviceExchangeDataPauseAppReplyData deviceExchangeDataPauseAppReplyData) {
        com.ido.ble.i.a.a.a(deviceExchangeDataPauseAppReplyData);
    }

    public static void replyDeviceExchangeDataResume(DeviceExchangeDataResumeAppReplyData deviceExchangeDataResumeAppReplyData) {
        com.ido.ble.i.a.a.a(deviceExchangeDataResumeAppReplyData);
    }

    public static void replyDeviceExchangeDataStart(DeviceExchangeDataStartAppReplyData deviceExchangeDataStartAppReplyData) {
        com.ido.ble.i.a.a.a(deviceExchangeDataStartAppReplyData);
    }

    public static void replyDeviceExchangeDataStop(DeviceExchangeDataStopAppReplyData deviceExchangeDataStopAppReplyData) {
        com.ido.ble.i.a.a.a(deviceExchangeDataStopAppReplyData);
    }

    public static void replyDeviceNoticeAppExchangeDataPause(DeviceNoticeAppExchangeDataPauseAppReplyData deviceNoticeAppExchangeDataPauseAppReplyData) {
        com.ido.ble.i.a.a.a(deviceNoticeAppExchangeDataPauseAppReplyData);
    }

    public static void replyDeviceNoticeAppExchangeDataResume(DeviceNoticeAppExchangeDataResumeAppReplyData deviceNoticeAppExchangeDataResumeAppReplyData) {
        com.ido.ble.i.a.a.a(deviceNoticeAppExchangeDataResumeAppReplyData);
    }

    public static void replyDeviceNoticeAppExchangeDataStop(DeviceNoticeAppExchangeDataStopAppReplyData deviceNoticeAppExchangeDataStopAppReplyData) {
        com.ido.ble.i.a.a.a(deviceNoticeAppExchangeDataStopAppReplyData);
    }

    public static void scanAndConnect(String str) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->scanAndConnect.");
        com.ido.ble.bluetooth.a.f(str);
    }

    public static void sendCustomBytesDataToDevice(String str) {
        com.ido.ble.bluetooth.a.a(str, "-");
    }

    public static void sendNotification(NotificationPara notificationPara) {
        com.ido.ble.i.a.a.a(notificationPara);
    }

    public static void setActivitySwitch(ActivitySwitch activitySwitch) {
        com.ido.ble.i.a.a.a(activitySwitch);
    }

    public static void setActivitySwitchPending(ActivitySwitch activitySwitch) {
        com.ido.ble.f.a.f.a.g0().a(activitySwitch);
    }

    public static void setAlarm(List<Alarm> list) {
        com.ido.ble.i.a.a.g(list);
    }

    public static void setAlarmPending(List<Alarm> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(list);
    }

    public static void setAlarmV3(List<AlarmV3> list) {
        com.ido.ble.i.a.a.h(list);
    }

    public static void setAllPhoneContacts(AllPhoneContacts allPhoneContacts) {
        com.ido.ble.i.a.a.a(allPhoneContacts);
    }

    public static void setAntiLostMode(AntiLostMode antiLostMode) {
        com.ido.ble.i.a.a.a(antiLostMode);
    }

    public static void setAntiLostModePending(AntiLostMode antiLostMode) {
        if (antiLostMode != null) {
            com.ido.ble.f.a.f.a.g0().a(antiLostMode);
        }
    }

    public static void setBindAuth(int[] iArr) {
        com.ido.ble.i.a.a.a(iArr);
    }

    public static void setBloodPressureAdjustPara(BloodPressureAdjustPara bloodPressureAdjustPara) {
        com.ido.ble.i.a.a.a(bloodPressureAdjustPara);
    }

    public static void setBloodPressureAdjustParaPending(BloodPressureAdjustPara bloodPressureAdjustPara) {
        if (bloodPressureAdjustPara != null) {
            com.ido.ble.f.a.f.a.g0().a(bloodPressureAdjustPara);
        }
    }

    public static void setBodyPowerSwitch(BodyPowerSwitch bodyPowerSwitch) {
        com.ido.ble.i.a.a.a(bodyPowerSwitch);
    }

    public static void setBodyPowerSwitchPending(BodyPowerSwitch bodyPowerSwitch) {
        com.ido.ble.f.a.f.a.g0().a(bodyPowerSwitch);
    }

    public static void setBreatheTrain(BreatheTrain breatheTrain) {
        com.ido.ble.i.a.a.a(breatheTrain);
    }

    public static void setBreatheTrainPending(BreatheTrain breatheTrain) {
        com.ido.ble.f.a.f.a.g0().a(breatheTrain);
    }

    public static void setCalorieAndDistanceGoal(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        com.ido.ble.i.a.a.a(calorieAndDistanceGoal);
    }

    public static void setCalorieAndDistanceGoalPending(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        com.ido.ble.f.a.f.a.g0().a(calorieAndDistanceGoal);
    }

    public static void setConnParam(ConnParam connParam) {
        com.ido.ble.h.a.a(connParam);
    }

    public static void setControlGps(ControlGps controlGps) {
        com.ido.ble.h.a.a(controlGps);
    }

    public static void setDeviceUnreadReminder(DeviceUnreadReminder deviceUnreadReminder) {
        com.ido.ble.i.a.a.a(deviceUnreadReminder);
    }

    public static void setDialPlate(DialPlate dialPlate) {
        com.ido.ble.i.a.a.a(dialPlate);
    }

    public static void setDialPlatePending(DialPlate dialPlate) {
        if (dialPlate != null) {
            com.ido.ble.f.a.f.a.g0().a(dialPlate);
        }
    }

    public static void setDisplayMode(DisplayMode displayMode) {
        com.ido.ble.i.a.a.a(displayMode);
    }

    public static void setDisplayModePending(DisplayMode displayMode) {
        if (displayMode != null) {
            com.ido.ble.f.a.f.a.g0().a(displayMode);
        }
    }

    public static void setDrinkWaterReminder(DrinkWaterReminder drinkWaterReminder) {
        com.ido.ble.i.a.a.a(drinkWaterReminder);
    }

    public static void setDrinkWaterReminderPending(DrinkWaterReminder drinkWaterReminder) {
        com.ido.ble.f.a.f.a.g0().a(drinkWaterReminder);
    }

    public static void setFindPhoneSwitch(boolean z) {
        com.ido.ble.i.a.a.a(z);
    }

    public static void setFindPhoneSwitchPending(boolean z) {
        com.ido.ble.f.a.f.a.g0().a(z);
    }

    public static void setFitnessGuidance(FitnessGuidance fitnessGuidance) {
        com.ido.ble.i.a.a.a(fitnessGuidance);
    }

    public static void setFitnessGuidancePending(FitnessGuidance fitnessGuidance) {
        if (fitnessGuidance != null) {
            com.ido.ble.f.a.f.a.g0().a(fitnessGuidance);
        }
    }

    public static void setFrequentContactsV3(List<FrequentContactsV3> list) {
        com.ido.ble.i.a.a.i(list);
    }

    public static void setGoal(Goal goal) {
        com.ido.ble.i.a.a.a(goal);
    }

    public static void setGoalPending(Goal goal) {
        if (goal != null) {
            com.ido.ble.f.a.f.a.g0().a(goal);
        }
    }

    public static void setGpsHotPara(GpsHotStartParam gpsHotStartParam) {
        com.ido.ble.h.a.a(gpsHotStartParam);
    }

    public static void setGpsParas(ConfigGPS configGPS) {
        com.ido.ble.h.a.a(configGPS);
    }

    public static void setHandWearMode(HandWearMode handWearMode) {
        com.ido.ble.i.a.a.a(handWearMode);
    }

    public static void setHandWearModePending(HandWearMode handWearMode) {
        if (handWearMode != null) {
            com.ido.ble.f.a.f.a.g0().a(handWearMode);
        }
    }

    public static void setHeartRateInterval(HeartRateInterval heartRateInterval) {
        com.ido.ble.i.a.a.a(heartRateInterval);
    }

    public static void setHeartRateIntervalPending(HeartRateInterval heartRateInterval) {
        if (heartRateInterval != null) {
            com.ido.ble.f.a.f.a.g0().a(heartRateInterval);
        }
    }

    public static void setHeartRateMeasureMode(HeartRateMeasureMode heartRateMeasureMode) {
        com.ido.ble.i.a.a.a(heartRateMeasureMode);
    }

    public static void setHeartRateMeasureModePending(HeartRateMeasureMode heartRateMeasureMode) {
        if (heartRateMeasureMode != null) {
            com.ido.ble.f.a.f.a.g0().a(heartRateMeasureMode);
        }
    }

    public static void setHeartRateMeasureModeV3(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        com.ido.ble.i.a.a.a(heartRateMeasureModeV3);
    }

    public static void setHeartRateMeasureModeV3Pending(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        if (heartRateMeasureModeV3 != null) {
            com.ido.ble.f.a.f.a.g0().a(heartRateMeasureModeV3);
        }
    }

    public static void setHeartRateSmart(HeartRateSmartMode heartRateSmartMode) {
        com.ido.ble.i.a.a.a(heartRateSmartMode);
    }

    public static void setHeartRateSmartPending(HeartRateSmartMode heartRateSmartMode) {
        if (heartRateSmartMode != null) {
            com.ido.ble.f.a.f.a.g0().a(heartRateSmartMode);
        }
    }

    public static void setIncomingCallInfo(IncomingCallInfo incomingCallInfo) {
        com.ido.ble.i.a.a.a(incomingCallInfo);
    }

    public static void setIsNeedRemoveBondBeforeConnect(boolean z) {
        CustomConfig.getConfig().setNeedRemoveBondBeforeConnect(z);
    }

    public static void setLogListener(LogTool.LogListener logListener) {
        LogTool.a(logListener);
    }

    public static void setLongSit(LongSit longSit) {
        com.ido.ble.i.a.a.a(longSit);
    }

    public static void setLongSitPending(LongSit longSit) {
        if (longSit != null) {
            com.ido.ble.f.a.f.a.g0().a(longSit);
        }
    }

    public static void setMedicineReminder(MedicineReminder medicineReminder) {
        com.ido.ble.i.a.a.a(medicineReminder);
    }

    public static void setMenstrual(Menstrual menstrual) {
        com.ido.ble.i.a.a.a(menstrual);
    }

    public static void setMenstrualPending(Menstrual menstrual) {
        com.ido.ble.f.a.f.a.g0().a(menstrual);
    }

    public static void setMenstrualRemind(MenstrualRemind menstrualRemind) {
        com.ido.ble.i.a.a.a(menstrualRemind);
    }

    public static void setMenstrualRemindPending(MenstrualRemind menstrualRemind) {
        com.ido.ble.f.a.f.a.g0().a(menstrualRemind);
    }

    public static void setMenstruationHistoricalData(MenstruationHistoricalData menstruationHistoricalData) {
        com.ido.ble.i.a.a.a(menstruationHistoricalData);
    }

    public static void setMenuList(MenuList menuList) {
        com.ido.ble.i.a.a.a(menuList);
    }

    public static void setMenuListPending(MenuList menuList) {
        com.ido.ble.f.a.f.a.g0().a(menuList);
    }

    public static void setMusicControlInfo(MusicControlInfo musicControlInfo) {
        com.ido.ble.i.a.a.a(musicControlInfo);
    }

    public static void setMusicSwitch(boolean z) {
        com.ido.ble.i.a.a.c(z);
    }

    public static void setMusicSwitchPending(boolean z) {
        com.ido.ble.f.a.f.a.g0().b(z);
    }

    public static void setNewMessageDetailInfo(NewMessageInfo newMessageInfo) {
        com.ido.ble.i.a.a.a(newMessageInfo);
    }

    public static void setNightTemperatureMonitoringPara(NightTemperatureMonitoringPara nightTemperatureMonitoringPara) {
        com.ido.ble.i.a.a.a(nightTemperatureMonitoringPara);
    }

    public static void setNightTemperatureMonitoringParaPending(NightTemperatureMonitoringPara nightTemperatureMonitoringPara) {
        if (nightTemperatureMonitoringPara != null) {
            com.ido.ble.f.a.f.a.g0().a(nightTemperatureMonitoringPara);
        }
    }

    public static void setNoisePara(NoisePara noisePara) {
        com.ido.ble.i.a.a.a(noisePara);
    }

    public static void setNoiseParaPending(NoisePara noisePara) {
        if (noisePara != null) {
            com.ido.ble.f.a.f.a.g0().a(noisePara);
        }
    }

    public static void setNotDisturbPara(NotDisturbPara notDisturbPara) {
        com.ido.ble.i.a.a.a(notDisturbPara);
    }

    public static void setNotDisturbParaPending(NotDisturbPara notDisturbPara) {
        if (notDisturbPara != null) {
            com.ido.ble.f.a.f.a.g0().a(notDisturbPara);
        }
    }

    public static void setNoticeDeviceDiableFunc(NoticeDevicePermmsion noticeDevicePermmsion) {
        com.ido.ble.i.a.a.a(noticeDevicePermmsion);
    }

    public static void setNoticeReminderSwitchPending(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        if (noticeReminderSwitchStatus != null) {
            com.ido.ble.f.a.f.a.g0().b(noticeReminderSwitchStatus);
        }
    }

    public static void setNoticeReminderSwitchStatus(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        com.ido.ble.i.a.a.a(noticeReminderSwitchStatus);
    }

    public static void setNoticeReply(DeviceChangedPara deviceChangedPara) {
        com.ido.ble.i.a.a.a(deviceChangedPara);
    }

    public static void setNoticeSportActionToggle(NoticeSportActionToggle noticeSportActionToggle) {
        com.ido.ble.i.a.a.a(noticeSportActionToggle);
    }

    public static void setOneKeyReset() {
        com.ido.ble.i.a.a.m0();
    }

    public static void setOneKeySOSSwitch(SosSwitch sosSwitch) {
        if (sosSwitch == null) {
            return;
        }
        com.ido.ble.i.a.a.a(sosSwitch);
    }

    public static void setOneKeySOSSwitch(boolean z) {
        com.ido.ble.i.a.a.d(z);
    }

    public static void setOneKeySOSSwitchPending(boolean z) {
        com.ido.ble.f.a.f.a.g0().c(z);
    }

    public static void setParaToDeviceByType(int i) {
        com.ido.ble.i.a.a.d(i);
    }

    public static void setParaToDeviceByTypeAndJson(int i, String str) {
        com.ido.ble.i.a.a.b(i, str);
    }

    public static void setPhoneVoice(PhoneVoice phoneVoice) {
        com.ido.ble.i.a.a.a(phoneVoice);
    }

    public static void setPressCalibration(PressCalibration pressCalibration) {
        com.ido.ble.i.a.a.a(pressCalibration);
    }

    public static void setPressureParam(PressureParam pressureParam) {
        com.ido.ble.i.a.a.a(pressureParam);
    }

    public static void setPressureParamPending(PressureParam pressureParam) {
        com.ido.ble.f.a.f.a.g0().a(pressureParam);
    }

    public static void setQuickReplyInfo(QuickReplyInfo quickReplyInfo) {
        com.ido.ble.i.a.a.a(quickReplyInfo);
    }

    public static void setQuickSportMode(QuickSportMode quickSportMode) {
        com.ido.ble.i.a.a.a(quickSportMode);
    }

    public static void setQuickSportModePending(QuickSportMode quickSportMode) {
        if (quickSportMode != null) {
            com.ido.ble.f.a.f.a.g0().a(quickSportMode);
        }
    }

    public static void setRespiratoryRate(RespiratoryRate respiratoryRate) {
        com.ido.ble.i.a.a.a(respiratoryRate);
    }

    public static void setRespiratoryRatePending(RespiratoryRate respiratoryRate) {
        com.ido.ble.f.a.f.a.g0().a(respiratoryRate);
    }

    public static void setRestoreFactory() {
        com.ido.ble.i.a.a.n0();
    }

    public static void setSCreenBrightnessConfig(ScreenBrightness screenBrightness) {
        com.ido.ble.i.a.a.a(screenBrightness);
    }

    public static void setSCreenBrightnessConfigPending(ScreenBrightness screenBrightness) {
        com.ido.ble.f.a.f.a.g0().a(screenBrightness);
    }

    public static void setSPO2Param(SPO2Param sPO2Param) {
        com.ido.ble.i.a.a.a(sPO2Param);
    }

    public static void setSPO2ParamPending(SPO2Param sPO2Param) {
        com.ido.ble.f.a.f.a.g0().a(sPO2Param);
    }

    public static void setScheduleReminderSwitch(ScheduleReminderSwitch scheduleReminderSwitch) {
        com.ido.ble.i.a.a.a(scheduleReminderSwitch);
    }

    @Deprecated
    public static void setScreenBrightness(int i) {
        setScreenBrightnessLevel(i);
    }

    public static void setScreenBrightnessAutoAdjustNight(boolean z) {
        com.ido.ble.i.a.a.e(z);
    }

    public static void setScreenBrightnessLevel(int i) {
        com.ido.ble.i.a.a.e(i);
    }

    public static void setScreenBrightnessLevelPending(int i) {
        com.ido.ble.f.a.f.a.g0().c(i);
    }

    @Deprecated
    public static void setScreenBrightnessPending(int i) {
        setScreenBrightnessLevelPending(i);
    }

    public static void setShortCut(ShortCut shortCut) {
        com.ido.ble.i.a.a.a(shortCut);
    }

    public static void setShortCutPending(ShortCut shortCut) {
        if (shortCut != null) {
            com.ido.ble.f.a.f.a.g0().a(shortCut);
        }
    }

    public static void setSleepMonitoringPara(SleepMonitoringPara sleepMonitoringPara) {
        com.ido.ble.i.a.a.a(sleepMonitoringPara);
    }

    public static void setSleepMonitoringParaPending(SleepMonitoringPara sleepMonitoringPara) {
        if (sleepMonitoringPara != null) {
            com.ido.ble.f.a.f.a.g0().a(sleepMonitoringPara);
        }
    }

    public static void setSmallQuickModule(List<SmallQuickModule> list) {
        com.ido.ble.i.a.a.j(list);
    }

    public static void setSport100TypeSort(List<Integer> list, int i) {
        com.ido.ble.i.a.a.a(list, i);
    }

    public static void setSportModeSortInfo(SportModeSort sportModeSort) {
        com.ido.ble.i.a.a.a(sportModeSort);
    }

    public static void setSportModeSortInfoPending(SportModeSort sportModeSort) {
        com.ido.ble.f.a.f.a.g0().a(sportModeSort);
    }

    public static void setSportModeSortInfoV3(SportModeSortV3 sportModeSortV3) {
        com.ido.ble.i.a.a.a(sportModeSortV3);
    }

    public static void setSportModeSortInfoV3Pending(SportModeSortV3 sportModeSortV3) {
        com.ido.ble.f.a.f.a.g0().a(sportModeSortV3);
    }

    public static void setSportPlanDataSend(SportPlan sportPlan) {
        com.ido.ble.i.a.a.b(sportPlan);
    }

    public static void setSportPlanEnd(SportPlan sportPlan) {
        com.ido.ble.i.a.a.c(sportPlan);
    }

    public static void setSportSubItemParaSort(List<Integer> list, int i, int i2) {
        com.ido.ble.i.a.a.c(list, i, i2);
    }

    public static void setStartSportPlan(SportPlan sportPlan) {
        com.ido.ble.i.a.a.d(sportPlan);
    }

    public static void setStopFindPhone(StopFindPhone stopFindPhone) {
        com.ido.ble.i.a.a.a(stopFindPhone);
    }

    public static void setStopInComingCall() {
        com.ido.ble.i.a.a.o0();
    }

    public static void setTime() {
        com.ido.ble.i.a.a.p0();
    }

    public static void setTime(SystemTime systemTime) {
        com.ido.ble.i.a.a.a(systemTime);
    }

    public static void setTranSportIconInformation(IconTransInformation iconTransInformation) {
        com.ido.ble.i.a.a.a(iconTransInformation);
    }

    public static void setUnit(Units units) {
        com.ido.ble.i.a.a.a(units);
    }

    public static void setUnitPending(Units units) {
        if (units != null) {
            com.ido.ble.f.a.f.a.g0().a(units);
        }
    }

    public static void setUpHandGesture(UpHandGesture upHandGesture) {
        com.ido.ble.i.a.a.a(upHandGesture);
    }

    public static void setUpHandGesturePending(UpHandGesture upHandGesture) {
        if (upHandGesture != null) {
            com.ido.ble.f.a.f.a.g0().a(upHandGesture);
        }
    }

    public static void setUserInfo(UserInfo userInfo) {
        com.ido.ble.i.a.a.a(userInfo);
    }

    public static void setUserInfoPending(UserInfo userInfo) {
        if (userInfo != null) {
            com.ido.ble.f.a.f.a.g0().a(userInfo);
        }
    }

    @Deprecated
    public static void setV3MessageNotice(V3MessageNotice v3MessageNotice) {
        com.ido.ble.i.a.a.a(v3MessageNotice);
    }

    public static void setVoiceLoginState(VoiceLoginState voiceLoginState) {
        com.ido.ble.i.a.a.a(voiceLoginState);
    }

    public static void setVoiceNotifyState(VoiceNotifyState voiceNotifyState) {
        com.ido.ble.i.a.a.a(voiceNotifyState);
    }

    public static void setVoiceRecognizeState(VoiceRecognizeState voiceRecognizeState) {
        com.ido.ble.i.a.a.a(voiceRecognizeState);
    }

    public static void setVoiceToText(VoiceToText voiceToText) {
        com.ido.ble.i.a.a.a(voiceToText);
    }

    public static void setWalkRealTimeReminder(WalkRealTimeReminder walkRealTimeReminder) {
        com.ido.ble.i.a.a.a(walkRealTimeReminder);
    }

    public static void setWalkReminder(WalkReminder walkReminder) {
        com.ido.ble.i.a.a.a(walkReminder);
    }

    public static void setWalkReminderPending(WalkReminder walkReminder) {
        com.ido.ble.f.a.f.a.g0().a(walkReminder);
    }

    public static void setWashHandReminder(WashHandReminder washHandReminder) {
        com.ido.ble.i.a.a.a(washHandReminder);
    }

    public static void setWatchDialOrder(WatchDialOrder watchDialOrder) {
        com.ido.ble.i.a.a.a(watchDialOrder);
    }

    public static void setWatchPlate(String str) {
        com.ido.ble.i.a.a.c(str);
    }

    public static void setWeatherCityName(String str) {
        com.ido.ble.i.a.a.d(str);
    }

    public static void setWeatherData(WeatherInfo weatherInfo) {
        com.ido.ble.i.a.a.a(weatherInfo);
    }

    public static void setWeatherDataV3(WeatherInfoV3 weatherInfoV3) {
        com.ido.ble.i.a.a.a(weatherInfoV3);
    }

    public static void setWeatherSunTime(WeatherSunTime weatherSunTime) {
        com.ido.ble.i.a.a.a(weatherSunTime);
    }

    public static void setWeatherSwitch(boolean z) {
        com.ido.ble.i.a.a.f(z);
    }

    public static void setWeatherSwitchPending(boolean z) {
        com.ido.ble.f.a.f.a.g0().d(z);
    }

    public static void setWorldTime(List<WorldTime.Item> list) {
        com.ido.ble.i.a.a.k(list);
    }

    public static void startDFU(BleDFUConfig bleDFUConfig) {
        com.ido.ble.dfu.c.a(bleDFUConfig);
    }

    public static void startFindDevice() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "BLEManager->startFindDevice()");
        com.ido.ble.i.a.a.r0();
    }

    public static void startMeasureBloodPressure() {
        com.ido.ble.i.a.a.s0();
    }

    public static void startScanDevices() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->startScanDevices.");
        com.ido.ble.bluetooth.a.m();
    }

    public static void startScanDevicesByName(String str) {
        com.ido.ble.bluetooth.a.j(str);
    }

    public static void startSetPlateFileToWatch(WatchPlateSetConfig watchPlateSetConfig) {
        com.ido.ble.watch.custom.a.j().a(watchPlateSetConfig);
    }

    public static void startSppTranFile(SPPFileTransferConfig sPPFileTransferConfig) {
        com.ido.ble.file.transfer.spp.a.e().b(sPPFileTransferConfig);
    }

    public static void startSppTranFile(SPPFileTransferConfig sPPFileTransferConfig, f fVar) {
        com.ido.ble.file.transfer.spp.a.e().b(sPPFileTransferConfig, fVar);
    }

    public static void startSyncActivityData() {
        com.ido.ble.i.a.a.t0();
    }

    public static void startSyncConfigInfo() {
        com.ido.ble.i.a.a.u0();
    }

    public static void startSyncGpsData() {
        com.ido.ble.h.a.g();
    }

    public static void startSyncHealthData() {
        com.ido.ble.i.a.a.v0();
    }

    public static int startSyncV3Health() {
        return com.ido.ble.i.a.a.w0();
    }

    public static void startTranAgpsFile(AgpsFileTransConfig agpsFileTransConfig) {
        com.ido.ble.gps.agps.a.f().a(agpsFileTransConfig);
    }

    @Deprecated
    public static void startTranAgpsFile(String str, IAGpsTranslateStateListener iAGpsTranslateStateListener) {
        com.ido.ble.gps.agps.a.f().a(str, iAGpsTranslateStateListener);
    }

    public static void startTranCommonFile(FileTransferConfig fileTransferConfig) {
        com.ido.ble.file.transfer.b.g().b(fileTransferConfig);
    }

    public static void startTranIcon(IconTranConfig iconTranConfig, IIconTransferListener iIconTransferListener) {
        com.ido.ble.icon.transfer.a.f().a(iconTranConfig, iIconTransferListener);
    }

    public static void startVoiceRecognize() {
        com.ido.ble.i.a.a.x0();
    }

    public static void stopFindDevice() {
        com.ido.ble.i.a.a.y0();
    }

    public static void stopMeasureBloodPressure() {
        com.ido.ble.i.a.a.z0();
    }

    public static void stopScanDevices() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "BLEManager->stopScanDevices.");
        com.ido.ble.bluetooth.a.n();
    }

    public static void stopSetPlate() {
        com.ido.ble.watch.custom.a.j().a();
    }

    public static void stopSppTranFile() {
        com.ido.ble.file.transfer.spp.a.e().a();
    }

    public static void stopSyncActivityData() {
        com.ido.ble.i.a.a.A0();
    }

    public static void stopSyncAllData() {
        com.ido.ble.business.sync.b.d().b();
    }

    public static void stopSyncConfigInfo() {
        com.ido.ble.i.a.a.B0();
    }

    public static void stopSyncHealthData() {
        com.ido.ble.i.a.a.C0();
    }

    public static int stopSyncV3Health() {
        return com.ido.ble.i.a.a.D0();
    }

    public static void stopTranAgpsFile() {
        com.ido.ble.gps.agps.a.f().a();
    }

    public static void stopTranCommonFile() {
        com.ido.ble.file.transfer.b.g().a();
    }

    public static void stopTranIcon() {
        com.ido.ble.icon.transfer.a.f().a();
    }

    public static void stopVoiceRecognize() {
        com.ido.ble.i.a.a.E0();
    }

    public static void switchToDevice(String str, ICommonListener iCommonListener) {
        String str2 = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str2, "BLEManager->switchToDevice." + str);
        com.ido.ble.business.multidevice.c.b().a(str, iCommonListener);
    }

    public static boolean syncAllData(SyncPara syncPara) {
        return com.ido.ble.business.sync.b.d().a(syncPara);
    }

    public static void unbind() {
        com.ido.ble.i.a.a.F0();
    }

    public static void unbind(String str, ICommonListener iCommonListener) {
        com.ido.ble.business.multidevice.b.a(str, iCommonListener);
    }

    public static void unregisterAppControlDeviceCallBack(AppControlDeviceCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterAppExchangeDataCallBack(AppExchangeDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterAppSendAllPhoneContactsCallBack(AppSendAllPhoneContactsCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterAppSendDataCallBack(AppSendDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterBindCallBack(BindCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterBloodPressureMeasureCallBack(BloodPressureMeasureCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterConnectCallBack(ConnectCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterDeviceControlAppCallBack(DeviceControlAppCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterDeviceExchangeDataCallBack(DeviceExchangeDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterDeviceLogCallBack(DeviceLogCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterDeviceParaChangedCallBack(DeviceParaChangedCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterDeviceReplySetGpsCallBack(GpsCallBack.IDeviceReplySetGpsCallBack iDeviceReplySetGpsCallBack) {
        com.ido.ble.gps.callback.a.h().b(iDeviceReplySetGpsCallBack);
    }

    public static void unregisterDeviceResponseCommonCallBack(DeviceResponseCommonCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterDeviceVoiceChangedCallBack(c.b bVar) {
        com.ido.ble.callback.b.N().b(bVar);
    }

    public static void unregisterEnterDfuModeCallBack(EnterDfuModeCallback.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterGetDeviceInfoCallBack(GetDeviceInfoCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterGetDeviceParaCallBack(GetDeviceParaCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterGetGpsInfoCallBack(GpsCallBack.IGetGpsInfoCallBack iGetGpsInfoCallBack) {
        com.ido.ble.gps.callback.a.h().b(iGetGpsInfoCallBack);
    }

    public static void unregisterNoticeSportActionToggleCallBack(NoticeSportActionToggleCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterOperateCallBack(OperateCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterOperateMusicCallBack(OperateCallBack.IMusicCallBack iMusicCallBack) {
        com.ido.ble.callback.b.N().b(iMusicCallBack);
    }

    public static void unregisterOtherProtocolCallBack(OtherProtocolCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterPhoneMsgNoticeCallBack(PhoneMsgNoticeCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterPhotoWallpaperOperateCallBack(PhotoWallpaperOperateCallBack.ICallBack iCallBack) {
        com.ido.ble.watch.custom.callback.a.c().b(iCallBack);
    }

    public static void unregisterQueryStatusCallBack(QueryStatusCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterRebootCallBack(RebootCallback.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterScanCallBack(ScanCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterSetPressCalibrationCallBack(SetPressCalibrationCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterSettingCallBack(SettingCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterSportPlanCallBack(SportPlanCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterSyncActivityCallBack(SyncCallBack.IActivityCallBack iActivityCallBack) {
        com.ido.ble.callback.b.N().b(iActivityCallBack);
    }

    public static void unregisterSyncConfigCallBack(SyncCallBack.IConfigCallBack iConfigCallBack) {
        com.ido.ble.callback.b.N().b(iConfigCallBack);
    }

    public static void unregisterSyncGpsDataCallBack(GpsCallBack.ISyncGpsDataCallBack iSyncGpsDataCallBack) {
        com.ido.ble.gps.callback.a.h().b(iSyncGpsDataCallBack);
    }

    public static void unregisterSyncHealthCallBack(SyncCallBack.IHealthCallBack iHealthCallBack) {
        com.ido.ble.callback.b.N().b(iHealthCallBack);
    }

    public static void unregisterSyncV3CallBack(SyncV3CallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterTranAgpsFileCallBack(GpsCallBack.ITranAgpsFileCallBack iTranAgpsFileCallBack) {
        com.ido.ble.gps.callback.a.h().b(iTranAgpsFileCallBack);
    }

    public static void unregisterUnbindCallBack(UnbindCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterUserCloseBluetoothCallBack(e.b bVar) {
        com.ido.ble.callback.b.N().b(bVar);
    }

    public static void unregisterUserHabitInfoCallBack(UserHabitCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterV3AppExchangeDataCallBack(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterVoiceCallBack(VoiceCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }

    public static void unregisterWatchErrorCallBack(GpsCallBack.ITranAgpsWatchErrorCallBack iTranAgpsWatchErrorCallBack) {
        com.ido.ble.gps.callback.a.h().b(iTranAgpsWatchErrorCallBack);
    }

    public static void unregisterWatchOperateCallBack(WatchPlateCallBack.IOperateCallBack iOperateCallBack) {
        com.ido.ble.watch.custom.callback.a.c().b(iOperateCallBack);
    }

    public static void updateMusicFolder(MusicOperate.MusicFolder musicFolder) {
        com.ido.ble.i.a.a.e(musicFolder);
    }

    public static void userFeedback(String str, String str2, IEventStatCallBack iEventStatCallBack) {
        com.ido.ble.event.stat.one.c.a(str, str2, iEventStatCallBack);
    }

    public static void v3AppExchangeDataGetHeartRate() {
        com.ido.ble.i.a.a.G0();
    }

    public static void v3AppExchangeDataIng(V3AppExchangeDataIngPara v3AppExchangeDataIngPara) {
        com.ido.ble.i.a.a.a(v3AppExchangeDataIngPara);
    }

    public static void v3getEndActivityData() {
        com.ido.ble.i.a.a.H0();
    }

    public static void voiceControlAlarm(VoiceControlAlarm voiceControlAlarm) {
        com.ido.ble.i.a.a.a(voiceControlAlarm);
    }

    public static void voiceControlBrightnessLevel(VoiceControlBrightnessLevel voiceControlBrightnessLevel) {
        com.ido.ble.i.a.a.a(voiceControlBrightnessLevel);
    }

    public static void voiceControlCountDown(VoiceCountDown voiceCountDown) {
        com.ido.ble.i.a.a.a(voiceCountDown);
    }

    public static void voiceControlFuncWithNoPara(VoiceControlFuncWithNoPara voiceControlFuncWithNoPara) {
        com.ido.ble.i.a.a.a(voiceControlFuncWithNoPara);
    }

    public static void voiceControlMusic(VoiceControlMusic voiceControlMusic) {
        com.ido.ble.i.a.a.a(voiceControlMusic);
    }

    public static void voiceControlReminder(VoiceControlReminder voiceControlReminder) {
        com.ido.ble.i.a.a.a(voiceControlReminder);
    }

    public static void voiceControlSports(VoiceControlSports voiceControlSports) {
        com.ido.ble.i.a.a.a(voiceControlSports);
    }

    public static void voiceControlStopWatch(VoiceStopWatch voiceStopWatch) {
        com.ido.ble.i.a.a.a(voiceStopWatch);
    }

    public static void voiceControlSwitchFunc(VoiceControlSwitchFunc voiceControlSwitchFunc) {
        com.ido.ble.i.a.a.a(voiceControlSwitchFunc);
    }
}
