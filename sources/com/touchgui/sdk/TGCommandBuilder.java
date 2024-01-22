package com.touchgui.sdk;

import android.location.Location;
import androidx.annotation.NonNull;
import com.touchgui.sdk.bean.TGAlarm;
import com.touchgui.sdk.bean.TGAppMenu;
import com.touchgui.sdk.bean.TGAppMenuStyle;
import com.touchgui.sdk.bean.TGBatteryInfo;
import com.touchgui.sdk.bean.TGBindResult;
import com.touchgui.sdk.bean.TGBrightnessConfig;
import com.touchgui.sdk.bean.TGContacts;
import com.touchgui.sdk.bean.TGDeviceInfo;
import com.touchgui.sdk.bean.TGEventReminder;
import com.touchgui.sdk.bean.TGFindPhoneConfig;
import com.touchgui.sdk.bean.TGGpsInfo;
import com.touchgui.sdk.bean.TGGpsStatus;
import com.touchgui.sdk.bean.TGHeartRateMonitoringModeConfig;
import com.touchgui.sdk.bean.TGHeartRateRangeConfig;
import com.touchgui.sdk.bean.TGMessage;
import com.touchgui.sdk.bean.TGMtuInfo;
import com.touchgui.sdk.bean.TGMusicInfo;
import com.touchgui.sdk.bean.TGNightModeConfig;
import com.touchgui.sdk.bean.TGNotDisturbConfig;
import com.touchgui.sdk.bean.TGPhysiologicalCycle;
import com.touchgui.sdk.bean.TGProfile;
import com.touchgui.sdk.bean.TGQuickCard;
import com.touchgui.sdk.bean.TGQuickReply;
import com.touchgui.sdk.bean.TGRaiseWristConfig;
import com.touchgui.sdk.bean.TGRealTimeData;
import com.touchgui.sdk.bean.TGRemindDrinking;
import com.touchgui.sdk.bean.TGSedentaryConfig;
import com.touchgui.sdk.bean.TGSosConfig;
import com.touchgui.sdk.bean.TGSpo2Config;
import com.touchgui.sdk.bean.TGSportStatus;
import com.touchgui.sdk.bean.TGStock;
import com.touchgui.sdk.bean.TGStressConfig;
import com.touchgui.sdk.bean.TGTarget;
import com.touchgui.sdk.bean.TGTargetConfig;
import com.touchgui.sdk.bean.TGUnitConfig;
import com.touchgui.sdk.bean.TGVersionInfo;
import com.touchgui.sdk.bean.TGWashConfig;
import com.touchgui.sdk.bean.TGWeather;
import com.touchgui.sdk.bean.TGWorkoutHeartRateConfig;
import com.touchgui.sdk.bean.TGWorkoutMode;
import com.touchgui.sdk.bean.TGWorkoutSupportList;
import com.touchgui.sdk.bean.TGWorldClock;
import java.util.List;
/* loaded from: classes12.dex */
public interface TGCommandBuilder {
    TGCommand<Integer> auth(String str);

    TGCommand<TGBindResult> bind();

    TGCommand<Void> findDevice(boolean z, int i);

    TGCommand<TGAppMenuStyle> getAppMenuStyle();

    TGCommand<List<TGAppMenu>> getAppMenus();

    TGCommand<TGBatteryInfo> getBatteryInfo();

    TGCommand<TGBrightnessConfig> getBrightnessConfig();

    TGCommand<String> getBtMac();

    TGCommand<String> getBtName();

    TGCommand<Boolean> getCameraOnOff();

    TGCommand<List<TGContacts>> getContacts();

    TGCommand<TGDeviceInfo> getDeviceInfo();

    TGCommand<String> getDeviceMac();

    TGCommand<String> getDeviceSn();

    TGCommand<List<TGEventReminder>> getEventReminders();

    TGCommand<TGFindPhoneConfig> getFindPhoneConfig();

    TGCommand<TGGpsInfo> getGpsInfo();

    TGCommand<TGGpsStatus> getGpsStatus();

    TGCommand<TGHeartRateMonitoringModeConfig> getHeartRateMonitoringMode();

    TGCommand<TGHeartRateRangeConfig> getHeartRateRange();

    TGCommand<String> getModelName();

    TGCommand<TGMtuInfo> getMtuInfo();

    TGCommand<Boolean> getMusicOnOff();

    TGCommand<TGNightModeConfig> getNightMode();

    TGCommand<TGNotDisturbConfig> getNotDisturbMode();

    TGCommand<TGPhysiologicalCycle> getPhysiologicalCycle();

    TGCommand<TGProfile> getProfile();

    TGCommand<List<TGQuickCard>> getQuickCards();

    TGCommand<TGRaiseWristConfig> getRaiseWrist();

    TGCommand<TGRealTimeData> getRealTimeData();

    TGCommand<TGRemindDrinking> getRemindDrinking();

    TGCommand<TGSedentaryConfig> getSedentary();

    TGCommand<TGSosConfig> getSosConfig();

    TGCommand<TGSpo2Config> getSpo2Config();

    TGCommand<List<TGStock>> getStocks();

    TGCommand<TGStressConfig> getStressConfig();

    TGCommand<TGTargetConfig> getTarget();

    TGCommand<Boolean> getTargetOnOff();

    TGCommand<TGUnitConfig> getUnit();

    TGCommand<TGVersionInfo> getVersionInfo();

    TGCommand<TGWashConfig> getWashConfig();

    TGCommand<Boolean> getWeatherOnOff();

    TGCommand<TGWorkoutHeartRateConfig> getWorkoutHeartRateConfig();

    TGCommand<TGWorkoutMode> getWorkoutMode();

    TGCommand<TGWorkoutSupportList> getWorkoutSupportList();

    TGCommand<List<TGWorldClock>> getWorldClocks();

    @Deprecated
    TGCommand<Void> messageReminder(String str, String str2, String str3, int i);

    TGCommand<TGSportStatus> querySportStatus();

    TGCommand<Void> reboot();

    TGCommand<Void> remindCall(String str, String str2);

    TGCommand<Void> reset();

    TGCommand<Integer> setAlarms(List<TGAlarm> list);

    TGCommand<Void> setAppMenuStyle(TGAppMenuStyle tGAppMenuStyle);

    TGCommand<Void> setAppMenus(List<TGAppMenu> list);

    TGCommand<Void> setBrightnessConfig(TGBrightnessConfig tGBrightnessConfig);

    TGCommand<Void> setCameraOnOff(boolean z);

    TGCommand<Void> setCity(@NonNull String str);

    TGCommand<Integer> setContacts(List<TGContacts> list);

    TGCommand<Integer> setEventReminders(List<TGEventReminder> list);

    TGCommand<Void> setFindPhoneOnOff(boolean z, int i);

    TGCommand<Void> setHeartRateMonitoringMode(TGHeartRateMonitoringModeConfig tGHeartRateMonitoringModeConfig);

    TGCommand<Void> setHeartRateRange(TGHeartRateRangeConfig tGHeartRateRangeConfig);

    TGCommand<Void> setMusicOnOff(boolean z);

    TGCommand<Void> setNightMode(TGNightModeConfig tGNightModeConfig);

    TGCommand<Void> setNotDisturbMode(TGNotDisturbConfig tGNotDisturbConfig);

    TGCommand<Void> setPhysiologicalCycle(TGPhysiologicalCycle tGPhysiologicalCycle);

    TGCommand<Void> setProfile(TGProfile tGProfile);

    TGCommand<Void> setQuickCards(List<TGQuickCard> list);

    TGCommand<Void> setRaiseWrist(TGRaiseWristConfig tGRaiseWristConfig);

    TGCommand<Void> setRemindDrinking(TGRemindDrinking tGRemindDrinking);

    TGCommand<Void> setSedentary(TGSedentaryConfig tGSedentaryConfig);

    TGCommand<Void> setSosConfig(@NonNull TGSosConfig tGSosConfig);

    TGCommand<Void> setSpo2Config(TGSpo2Config tGSpo2Config);

    TGCommand<Integer> setStocks(@NonNull List<TGStock> list);

    TGCommand<Void> setStressConfig(TGStressConfig tGStressConfig);

    TGCommand<Void> setTarget(int i, int i2);

    @Deprecated
    TGCommand<Void> setTarget(int i, int i2, int i3, int i4, int i5);

    TGCommand<Void> setTarget(TGTarget tGTarget);

    TGCommand<Void> setTargetOnOff(boolean z);

    TGCommand<Void> setUnit(TGUnitConfig tGUnitConfig);

    TGCommand<Void> setWashConfig(@NonNull TGWashConfig tGWashConfig);

    TGCommand<Void> setWeather(TGWeather tGWeather);

    TGCommand<Void> setWeatherOnOff(boolean z);

    TGCommand<Void> setWorkoutHeartRateConfig(@NonNull TGWorkoutHeartRateConfig tGWorkoutHeartRateConfig);

    TGCommand<Integer> setWorkoutMode(TGWorkoutMode tGWorkoutMode);

    TGCommand<Integer> setWorldClocks(@NonNull List<TGWorldClock> list);

    TGCommand<Void> shutdown();

    TGCommand<Void> stopFindPhone();

    TGCommand<List<TGAlarm>> syncAlarms();

    TGCommand<Void> syncAppGpsData(Location location, int i);

    TGCommand<Void> syncAppGpsStatus(int i, int i2);

    TGCommand<Void> syncCallStatus(int i);

    TGCommand<Integer> syncMessage(TGMessage tGMessage);

    @Deprecated
    TGCommand<Integer> syncMessage(String str, String str2, String str3, int i);

    TGCommand<Integer> syncMusic(TGMusicInfo tGMusicInfo);

    @Deprecated
    TGCommand<Integer> syncMusic(String str, boolean z, int i, int i2);

    TGCommand<Void> syncQuickReply(List<TGQuickReply> list);

    TGCommand<Void> syncTime();

    TGCommand<Void> unbind(boolean z);

    TGCommand<Void> updateStock(@NonNull TGStock tGStock);
}
