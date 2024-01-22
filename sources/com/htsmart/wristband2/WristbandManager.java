package com.htsmart.wristband2;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.htsmart.wristband2.bean.BatteryStatus;
import com.htsmart.wristband2.bean.ConnectionError;
import com.htsmart.wristband2.bean.ConnectionState;
import com.htsmart.wristband2.bean.DialBinInfo;
import com.htsmart.wristband2.bean.DialUiInfo;
import com.htsmart.wristband2.bean.ExerciseTarget;
import com.htsmart.wristband2.bean.GameRankingTrend;
import com.htsmart.wristband2.bean.GameSpace;
import com.htsmart.wristband2.bean.HealthyDataResult;
import com.htsmart.wristband2.bean.LcdShape;
import com.htsmart.wristband2.bean.PhotovoltaicStation;
import com.htsmart.wristband2.bean.PowerSaveMode;
import com.htsmart.wristband2.bean.SportPush;
import com.htsmart.wristband2.bean.SyncDataRaw;
import com.htsmart.wristband2.bean.WristbandAlarm;
import com.htsmart.wristband2.bean.WristbandConfig;
import com.htsmart.wristband2.bean.WristbandContacts;
import com.htsmart.wristband2.bean.WristbandHabit;
import com.htsmart.wristband2.bean.WristbandNotification;
import com.htsmart.wristband2.bean.WristbandSchedule;
import com.htsmart.wristband2.bean.assist.AssistInfo;
import com.htsmart.wristband2.bean.config.BloodPressureConfig;
import com.htsmart.wristband2.bean.config.BrightnessVibrateConfig;
import com.htsmart.wristband2.bean.config.DrinkWaterConfig;
import com.htsmart.wristband2.bean.config.FunctionConfig;
import com.htsmart.wristband2.bean.config.HandWashingReminderConfig;
import com.htsmart.wristband2.bean.config.HealthyConfig;
import com.htsmart.wristband2.bean.config.NotDisturbConfig;
import com.htsmart.wristband2.bean.config.NotificationConfig;
import com.htsmart.wristband2.bean.config.PageConfig;
import com.htsmart.wristband2.bean.config.ProtectionReminderConfig;
import com.htsmart.wristband2.bean.config.SedentaryConfig;
import com.htsmart.wristband2.bean.config.TurnWristLightingConfig;
import com.htsmart.wristband2.bean.config.WarnBloodPressureConfig;
import com.htsmart.wristband2.bean.config.WarnHeartRateConfig;
import com.htsmart.wristband2.bean.config.WomenHealthyConfig;
import com.htsmart.wristband2.bean.data.GameData;
import com.htsmart.wristband2.bean.data.SportRealTimeData;
import com.htsmart.wristband2.bean.data.SportRealTimeStatus;
import com.htsmart.wristband2.bean.peripherals.PeripheralsData;
import com.htsmart.wristband2.bean.peripherals.PeripheralsRequest;
import com.htsmart.wristband2.bean.peripherals.PeripheralsResponse;
import com.htsmart.wristband2.bean.weather.WeatherForecast;
import com.htsmart.wristband2.bean.weather.WeatherToday;
import com.htsmart.wristband2.packet.SyncDataParser;
import com.polidea.rxandroidble2.RxBleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
/* loaded from: classes11.dex */
public interface WristbandManager {
    public static final int BUSINESS_CARD_ALIPAY = 34;
    public static final int BUSINESS_CARD_EMAIL = 44;
    public static final int BUSINESS_CARD_FACEBOOK = 36;
    public static final int BUSINESS_CARD_FACEBOOK_MESSENGER = 40;
    public static final int BUSINESS_CARD_INSTAGRAM = 39;
    public static final int BUSINESS_CARD_LINE = 41;
    public static final int BUSINESS_CARD_LINKEDIN = 46;
    public static final int BUSINESS_CARD_PHONE = 45;
    public static final int BUSINESS_CARD_QQ = 35;
    public static final int BUSINESS_CARD_SKYPE = 43;
    public static final int BUSINESS_CARD_SNAPCHAT = 42;
    public static final int BUSINESS_CARD_TWITTER = 38;
    public static final int BUSINESS_CARD_WECHAT = 33;
    public static final int BUSINESS_CARD_WHATSAPP = 37;
    public static final int COLLECTION_CODE_ALIPAY = 2;
    public static final int COLLECTION_CODE_BHIM = 8;
    public static final int COLLECTION_CODE_GPAY = 7;
    public static final int COLLECTION_CODE_PAYPAL = 3;
    public static final int COLLECTION_CODE_PAYTM = 5;
    public static final int COLLECTION_CODE_PHONE_PE = 6;
    public static final int COLLECTION_CODE_QQ = 4;
    public static final int COLLECTION_CODE_WECHAT = 1;
    public static final int HEALTHY_TYPE_BLOOD_PRESSURE = 4;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int HEALTHY_TYPE_ECG = 16;
    public static final int HEALTHY_TYPE_HEART_RATE = 1;
    public static final int HEALTHY_TYPE_OXYGEN = 2;
    public static final int HEALTHY_TYPE_PRESSURE = 64;
    public static final int HEALTHY_TYPE_RESPIRATORY_RATE = 8;
    public static final int HEALTHY_TYPE_TEMPERATURE = 32;
    public static final int MSG_ALI_AGENT_WAKE_UP = 41;
    public static final int MSG_CAMERA_EXIT = 5;
    public static final int MSG_CAMERA_TAKE_PHOTO = 3;
    public static final int MSG_CAMERA_WAKE_UP = 4;
    public static final int MSG_CHANGE_ALARM = 22;
    public static final int MSG_CHANGE_CONFIG_ITSELF = 21;
    public static final int MSG_CHANGE_SCHEDULE = 23;
    public static final int MSG_FIND_PHONE = 1;
    public static final int MSG_HUNG_UP_PHONE = 2;
    public static final int MSG_MEDIA_NEXT = 12;
    public static final int MSG_MEDIA_PLAY_PAUSE = 11;
    public static final int MSG_MEDIA_PREVIOUS = 13;
    public static final int MSG_MEDIA_SET_SILENT_MODE = 16;
    public static final int MSG_MEDIA_VOLUME_DOWN = 15;
    public static final int MSG_MEDIA_VOLUME_UP = 14;
    public static final int MSG_MUSIC_INFO = 18;
    public static final int MSG_MUSIC_STATE = 17;
    public static final int MSG_REQUEST_PHOTOVOLTAIC_STATION = 81;
    public static final int MSG_SOS = 31;
    public static final int MSG_STOP_FIND_PHONE = 6;
    public static final int MSG_WEATHER = 0;
    public static final int NUCLEIC_ACID_CODE = 128;
    public static final int SPORT_CLIMB_APP_DEVICE = 20;
    public static final int SPORT_OD_APP_DEVICE = 8;
    public static final int SPORT_RIDE_APP_DEVICE = 4;
    public static final int SPORT_WALK_APP_DEVICE = 16;
    public static final int SYNC_STATE_FAILED_CHECKING_ECG = -2;
    public static final int SYNC_STATE_FAILED_DISCONNECTED = -1;
    public static final int SYNC_STATE_FAILED_SAVING_ECG = -3;
    public static final int SYNC_STATE_FAILED_UNKNOWN = -128;
    public static final int SYNC_STATE_START = 0;
    public static final int SYNC_STATE_SUCCESS = 127;
    public static final int VIBRATION_REPEAT = 2;
    public static final int VIBRATION_SINGLE_LONG = 1;
    public static final int VIBRATION_SINGLE_SHORT = 0;
    public static final byte WEATHER_EXCEPTION_LOCATION_DISABLED = 2;
    public static final byte WEATHER_EXCEPTION_LOCATION_NO_PERMISSION = 1;
    public static final byte WEATHER_EXCEPTION_NONE = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface QrCodeType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface SportTypeAppDevice {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface VibrationMode {
    }

    Completable clearAudioDevice();

    void close();

    void connect(@NonNull BluetoothDevice bluetoothDevice, @NonNull String str, boolean z, boolean z2, int i, float f, float f2);

    void connect(@NonNull RxBleDevice rxBleDevice, @NonNull String str, boolean z, boolean z2, int i, float f, float f2);

    void connect(@NonNull String str, @NonNull String str2, boolean z, boolean z2, int i, float f, float f2);

    Completable deleteWristbandNotification(boolean z, int i);

    Completable dismissWristbandNotification();

    Completable exitSleepMonitor();

    Completable findWristband();

    String getConnectedAddress();

    BluetoothDevice getConnectedDevice();

    @Nullable
    RxBleDevice getRxBleDevice();

    @Nullable
    WristbandConfig getWristbandConfig();

    boolean isBindOrLogin();

    boolean isConnected();

    boolean isSyncingData();

    Observable<byte[]> observerAliAgentData();

    Observable<ConnectionError> observerConnectionError();

    Observable<ConnectionState> observerConnectionState();

    Observable<String> observerHangUpSms();

    Observable<PeripheralsRequest> observerPeripheralsRequest();

    Observable<SportRealTimeData> observerSportRealTimeData();

    Observable<SportRealTimeStatus> observerSportRealTimeStatus();

    Observable<Integer> observerSyncDataState();

    Observable<Integer> observerWristbandMessage();

    Observable<int[]> openEcgRealTimeData();

    Observable<byte[]> openGSensorRealTimeDataForTest();

    Observable<HealthyDataResult> openHealthyRealTimeData(int i);

    Observable<HealthyDataResult> openHealthyRealTimeData(int i, int i2);

    Single<Boolean> pauseSportRealTime(int i, int i2);

    Completable replayHangUpSms(boolean z);

    Completable reportSportRealTimeData(int i, float f, int i2, int i3, int i4);

    Single<List<WristbandAlarm>> requestAlarmList();

    @VisibleForTesting
    Single<AssistInfo> requestAssistInfoForTest();

    Single<BatteryStatus> requestBattery();

    Single<List<WristbandContacts>> requestContactsList();

    Single<DialBinInfo> requestDialBinInfo();

    Single<DialUiInfo> requestDialUiInfo();

    Single<Integer> requestEnterOTA();

    Single<ExerciseTarget> requestExerciseTarget();

    Single<List<GameSpace>> requestGamePushInfo();

    Single<List<WristbandHabit>> requestHabitList();

    Single<List<GameData>> requestHighestGameRecords(@GameData.GameType int i);

    Single<Byte> requestLanguage();

    Single<HealthyDataResult> requestLatestHealthy();

    Single<LcdShape> requestLcdShape();

    Single<NotDisturbConfig> requestNotDisturbConfig();

    Single<PowerSaveMode> requestPowerSaveMode();

    Single<List<WristbandSchedule>> requestScheduleList();

    @VisibleForTesting
    Single<List<SyncDataParser.DataItem>> requestSleepRawForTest();

    Single<List<SportPush>> requestSportPush();

    Single<SportRealTimeStatus> requestSportRealTimeStatus();

    Single<int[]> requestSupportGameTypes();

    Single<List<Integer>> requestSupportQrCodes();

    Single<WarnBloodPressureConfig> requestWarnBloodPressureConfig();

    Single<WarnHeartRateConfig> requestWarnHeartRateConfig();

    Single<WristbandConfig> requestWristbandConfig();

    Completable resetWristband();

    Completable restartWristband();

    Single<Boolean> resumeSportRealTime(int i, int i2);

    Completable sendWristbandNotification(WristbandNotification wristbandNotification);

    Completable setAlarmList(@Nullable List<WristbandAlarm> list);

    Completable setAliAgentData(byte[] bArr);

    Completable setAllowWristbandChangeSchedule(boolean z);

    @VisibleForTesting
    Single<Boolean> setAudioDeviceNameForTest(int i, byte b, int i2, byte b2, String str);

    Completable setBloodPressureConfig(BloodPressureConfig bloodPressureConfig);

    Completable setBrightnessVibrateConfig(BrightnessVibrateConfig brightnessVibrateConfig);

    Completable setCameraStatus(boolean z);

    Completable setContactsList(@Nullable List<WristbandContacts> list);

    Completable setCustomAdvertising(@NonNull byte[] bArr);

    Completable setCustomLabels(String[] strArr);

    @VisibleForTesting
    Single<Boolean> setDeviceAddressForTest(long j, byte b);

    @VisibleForTesting
    Single<Boolean> setDeviceNameForTest(int i, byte b, boolean z, boolean z2, byte b2, String str);

    @VisibleForTesting
    Completable setDevicePageForTest(int i, int i2, byte b);

    Completable setDialComponents(int i, @Nullable byte[] bArr);

    Completable setDrinkWaterConfig(DrinkWaterConfig drinkWaterConfig);

    @VisibleForTesting
    Completable setEngineeringMode();

    Completable setExerciseTarget(int i, int i2, int i3);

    Completable setExerciseTarget(@NonNull ExerciseTarget exerciseTarget);

    Completable setFunctionConfig(FunctionConfig functionConfig);

    Completable setGameRankingTrends(@NonNull List<GameRankingTrend> list);

    Completable setHabitList(@Nullable List<WristbandHabit> list);

    Completable setHandWashingReminderConfig(HandWashingReminderConfig handWashingReminderConfig);

    Completable setHealthyConfig(HealthyConfig healthyConfig);

    Completable setLanguage(byte b);

    Single<Boolean> setLockGame(boolean z, byte[] bArr, int i, int i2);

    Single<Boolean> setLockScreen(boolean z, byte[] bArr);

    Completable setMusicInfo(String str, String str2, long j);

    Completable setMusicState(int i, long j, float f);

    Completable setNotDisturbConfig(NotDisturbConfig notDisturbConfig);

    Completable setNotificationConfig(NotificationConfig notificationConfig);

    Completable setPageConfig(PageConfig pageConfig);

    Completable setPeripheralsData(PeripheralsData peripheralsData);

    Completable setPeripheralsResponse(PeripheralsResponse peripheralsResponse);

    Completable setPhotovoltaicStations(@Nullable List<PhotovoltaicStation> list);

    Completable setPowerSaveMode(@NonNull PowerSaveMode powerSaveMode);

    Completable setProtectionReminderConfig(ProtectionReminderConfig protectionReminderConfig);

    Completable setScheduleList(@Nullable List<WristbandSchedule> list);

    Completable setSedentaryConfig(SedentaryConfig sedentaryConfig);

    @VisibleForTesting
    Single<Byte> setSubProjectNum(String str);

    Completable setTurnWristLightingConfig(TurnWristLightingConfig turnWristLightingConfig);

    Completable setUserInfo(boolean z, int i, float f, float f2);

    Completable setVibration(boolean z, int i);

    Completable setWarnBloodPressureConfig(WarnBloodPressureConfig warnBloodPressureConfig);

    Completable setWarnHeartRateConfig(WarnHeartRateConfig warnHeartRateConfig);

    @Deprecated
    Completable setWeather(int i, int i2, int i3, int i4, String str);

    Completable setWeather(String str, long j, @NonNull WeatherToday weatherToday, @Nullable List<WeatherForecast> list);

    Completable setWeatherException(byte b);

    Completable setWomenHealthyConfig(WomenHealthyConfig womenHealthyConfig);

    Completable settingQrCode(int i, String str);

    Single<Integer> specialGgSetUserData(@NonNull String str);

    Single<Integer> specialGgSetUserInfo(@NonNull String str);

    Completable startSportRealTime(int i, int i2);

    Completable stopFindPhone();

    Completable stopSportRealTime(int i);

    Observable<SyncDataRaw> syncData();

    Completable turnOffWristband();

    Single<Boolean> userUnBind();

    Single<Boolean> userUnBind(boolean z);
}
