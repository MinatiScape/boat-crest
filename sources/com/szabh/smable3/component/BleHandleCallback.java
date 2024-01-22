package com.szabh.smable3.component;

import android.bluetooth.BluetoothDevice;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.entity.BleActivity;
import com.szabh.smable3.entity.BleAlarm;
import com.szabh.smable3.entity.BleAlipaySettings;
import com.szabh.smable3.entity.BleAppSportState;
import com.szabh.smable3.entity.BleAvgHeartRate;
import com.szabh.smable3.entity.BleBAC;
import com.szabh.smable3.entity.BleBloodGlucose;
import com.szabh.smable3.entity.BleBloodOxygen;
import com.szabh.smable3.entity.BleBloodPressure;
import com.szabh.smable3.entity.BleBodyStatus;
import com.szabh.smable3.entity.BleCalorieIntake;
import com.szabh.smable3.entity.BleCoachingIds;
import com.szabh.smable3.entity.BleDeviceFile;
import com.szabh.smable3.entity.BleDeviceInfo;
import com.szabh.smable3.entity.BleDeviceInfo2;
import com.szabh.smable3.entity.BleDrinkWaterSettings;
import com.szabh.smable3.entity.BleFoodBalance;
import com.szabh.smable3.entity.BleGSensorMotion;
import com.szabh.smable3.entity.BleGSensorRaw;
import com.szabh.smable3.entity.BleGestureWake;
import com.szabh.smable3.entity.BleHRRaw;
import com.szabh.smable3.entity.BleHeartRate;
import com.szabh.smable3.entity.BleHrMonitoringSettings;
import com.szabh.smable3.entity.BleHrv;
import com.szabh.smable3.entity.BleLanguagePackVersion;
import com.szabh.smable3.entity.BleLocation;
import com.szabh.smable3.entity.BleLocationGga;
import com.szabh.smable3.entity.BleLocationGsv;
import com.szabh.smable3.entity.BleLogText;
import com.szabh.smable3.entity.BleLoveTap;
import com.szabh.smable3.entity.BleLoveTapUser;
import com.szabh.smable3.entity.BleMatchRecord;
import com.szabh.smable3.entity.BleMatchRecord2;
import com.szabh.smable3.entity.BleMedicationAlarm;
import com.szabh.smable3.entity.BleMedicationReminder;
import com.szabh.smable3.entity.BleMindStatus;
import com.szabh.smable3.entity.BleNaviInfo;
import com.szabh.smable3.entity.BleNoDisturbSettings;
import com.szabh.smable3.entity.BlePackageStatus;
import com.szabh.smable3.entity.BlePressure;
import com.szabh.smable3.entity.BleRealTimeMeasurement;
import com.szabh.smable3.entity.BleRealtimeLog;
import com.szabh.smable3.entity.BleRecordPacket;
import com.szabh.smable3.entity.BleSMSQuickReply;
import com.szabh.smable3.entity.BleSedentarinessSettings;
import com.szabh.smable3.entity.BleSleep;
import com.szabh.smable3.entity.BleSleepQuality;
import com.szabh.smable3.entity.BleStock;
import com.szabh.smable3.entity.BleTemperature;
import com.szabh.smable3.entity.BleThirdPartyData;
import com.szabh.smable3.entity.BleTuyaKey;
import com.szabh.smable3.entity.BleUserProfile;
import com.szabh.smable3.entity.BleWatchFaceId;
import com.szabh.smable3.entity.BleWorkout;
import com.szabh.smable3.entity.BleWorkout2;
import com.szabh.smable3.entity.BleWorldClock;
import com.szabh.smable3.entity.MusicCommand;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface BleHandleCallback {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static void onAlarmAdd(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleAlarm alarm) {
            Intrinsics.checkNotNullParameter(alarm, "alarm");
        }

        public static void onAlarmDelete(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onAlarmUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleAlarm alarm) {
            Intrinsics.checkNotNullParameter(alarm, "alarm");
        }

        public static void onAppSportDataResponse(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onBacklightUpdate(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onBleThirdPartyDataUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleThirdPartyData thirdPartyData) {
            Intrinsics.checkNotNullParameter(thirdPartyData, "thirdPartyData");
        }

        public static void onCameraResponse(@NotNull BleHandleCallback bleHandleCallback, boolean z, int i) {
        }

        public static void onCameraStateChange(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onClassicBluetoothStateChange(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onCommandReply(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, boolean z) {
            Intrinsics.checkNotNullParameter(bleKey, "bleKey");
            Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        }

        public static void onCommandSendTimeout(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag) {
            Intrinsics.checkNotNullParameter(bleKey, "bleKey");
            Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        }

        public static void onDeviceConnected(@NotNull BleHandleCallback bleHandleCallback, @NotNull BluetoothDevice device) {
            Intrinsics.checkNotNullParameter(device, "device");
        }

        public static void onDeviceConnecting(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onDeviceFileUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleDeviceFile deviceFile) {
            Intrinsics.checkNotNullParameter(deviceFile, "deviceFile");
        }

        public static void onDeviceRequestAGpsFile(@NotNull BleHandleCallback bleHandleCallback, @NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
        }

        public static void onDeviceSMSQuickReply(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleSMSQuickReply smsQuickReply) {
            Intrinsics.checkNotNullParameter(smsQuickReply, "smsQuickReply");
        }

        public static void onFindPhone(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onFollowSystemLanguage(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onGestureWakeUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleGestureWake gestureWake) {
            Intrinsics.checkNotNullParameter(gestureWake, "gestureWake");
        }

        public static void onHIDState(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onHIDValueChange(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onIdentityCreate(@NotNull BleHandleCallback bleHandleCallback, boolean z, @Nullable BleDeviceInfo bleDeviceInfo) {
        }

        public static /* synthetic */ void onIdentityCreate$default(BleHandleCallback bleHandleCallback, boolean z, BleDeviceInfo bleDeviceInfo, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onIdentityCreate");
            }
            if ((i & 2) != 0) {
                bleDeviceInfo = null;
            }
            bleHandleCallback.onIdentityCreate(z, bleDeviceInfo);
        }

        public static void onIdentityDelete(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onIdentityDeleteByDevice(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onIncomingCallStatus(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onLoveTapUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleLoveTap loveTap) {
            Intrinsics.checkNotNullParameter(loveTap, "loveTap");
        }

        public static void onLoveTapUserDelete(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onLoveTapUserUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleLoveTapUser loveTapUser) {
            Intrinsics.checkNotNullParameter(loveTapUser, "loveTapUser");
        }

        public static void onMedicationAlarmAdd(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleMedicationAlarm medicationAlarm) {
            Intrinsics.checkNotNullParameter(medicationAlarm, "medicationAlarm");
        }

        public static void onMedicationAlarmDelete(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onMedicationAlarmUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleMedicationAlarm medicationAlarm) {
            Intrinsics.checkNotNullParameter(medicationAlarm, "medicationAlarm");
        }

        public static void onMedicationReminderDelete(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onMedicationReminderUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleMedicationReminder medicationReminder) {
            Intrinsics.checkNotNullParameter(medicationReminder, "medicationReminder");
        }

        public static void onNaviInfoUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleNaviInfo naviInfo) {
            Intrinsics.checkNotNullParameter(naviInfo, "naviInfo");
        }

        public static void onNoDisturbUpdate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleNoDisturbSettings noDisturbSettings) {
            Intrinsics.checkNotNullParameter(noDisturbSettings, "noDisturbSettings");
        }

        public static void onOTA(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onPowerSaveModeState(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onPowerSaveModeStateChange(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadActivity(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleActivity> activities) {
            Intrinsics.checkNotNullParameter(activities, "activities");
        }

        public static void onReadAlarm(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleAlarm> alarms) {
            Intrinsics.checkNotNullParameter(alarms, "alarms");
        }

        public static void onReadAlipaySettings(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleAlipaySettings alipaySettings) {
            Intrinsics.checkNotNullParameter(alipaySettings, "alipaySettings");
        }

        public static void onReadAvgHeartRate(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleAvgHeartRate> heartRates) {
            Intrinsics.checkNotNullParameter(heartRates, "heartRates");
        }

        public static void onReadBAC(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleBAC> bacs) {
            Intrinsics.checkNotNullParameter(bacs, "bacs");
        }

        public static void onReadBacklight(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadBleAddress(@NotNull BleHandleCallback bleHandleCallback, @NotNull String address) {
            Intrinsics.checkNotNullParameter(address, "address");
        }

        public static void onReadBleHrv(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleHrv> hrv) {
            Intrinsics.checkNotNullParameter(hrv, "hrv");
        }

        public static void onReadBleLogText(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleLogText> logs) {
            Intrinsics.checkNotNullParameter(logs, "logs");
        }

        public static void onReadBloodGlucose(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleBloodGlucose> bloodGlucoses) {
            Intrinsics.checkNotNullParameter(bloodGlucoses, "bloodGlucoses");
        }

        public static void onReadBloodOxygen(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleBloodOxygen> bloodOxygen) {
            Intrinsics.checkNotNullParameter(bloodOxygen, "bloodOxygen");
        }

        public static void onReadBloodPressure(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleBloodPressure> bloodPressures) {
            Intrinsics.checkNotNullParameter(bloodPressures, "bloodPressures");
        }

        public static void onReadBodyStatus(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleBodyStatus> bodyData) {
            Intrinsics.checkNotNullParameter(bodyData, "bodyData");
        }

        public static void onReadCalorieIntake(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleCalorieIntake> calorieIntakes) {
            Intrinsics.checkNotNullParameter(calorieIntakes, "calorieIntakes");
        }

        public static void onReadCaloriesGoal(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadCoachingIds(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleCoachingIds bleCoachingIds) {
            Intrinsics.checkNotNullParameter(bleCoachingIds, "bleCoachingIds");
        }

        public static void onReadDateFormat(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadDeviceFile(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleDeviceFile deviceFile) {
            Intrinsics.checkNotNullParameter(deviceFile, "deviceFile");
        }

        public static void onReadDeviceInfo(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleDeviceInfo deviceInfo) {
            Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        }

        public static void onReadDeviceInfo2(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleDeviceInfo2 deviceInfo) {
            Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        }

        public static void onReadDistanceGoal(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadDrinkWater(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleDrinkWaterSettings drinkWaterSettings) {
            Intrinsics.checkNotNullParameter(drinkWaterSettings, "drinkWaterSettings");
        }

        public static void onReadFirmwareVersion(@NotNull BleHandleCallback bleHandleCallback, @NotNull String version) {
            Intrinsics.checkNotNullParameter(version, "version");
        }

        public static void onReadFoodBalance(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleFoodBalance> foodBalances) {
            Intrinsics.checkNotNullParameter(foodBalances, "foodBalances");
        }

        public static void onReadGestureWake(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleGestureWake gestureWake) {
            Intrinsics.checkNotNullParameter(gestureWake, "gestureWake");
        }

        public static void onReadHeartRate(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleHeartRate> heartRates) {
            Intrinsics.checkNotNullParameter(heartRates, "heartRates");
        }

        public static void onReadHourSystem(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadHrMonitoringSettings(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleHrMonitoringSettings hrMonitoringSettings) {
            Intrinsics.checkNotNullParameter(hrMonitoringSettings, "hrMonitoringSettings");
        }

        public static void onReadLanguagePackVersion(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleLanguagePackVersion version) {
            Intrinsics.checkNotNullParameter(version, "version");
        }

        public static void onReadLocation(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleLocation> locations) {
            Intrinsics.checkNotNullParameter(locations, "locations");
        }

        public static void onReadLoveTapUser(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleLoveTapUser> loveTapUsers) {
            Intrinsics.checkNotNullParameter(loveTapUsers, "loveTapUsers");
        }

        public static void onReadMatchRecord(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleMatchRecord> matchRecords) {
            Intrinsics.checkNotNullParameter(matchRecords, "matchRecords");
        }

        public static void onReadMatchRecord2(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleMatchRecord2> matchRecords) {
            Intrinsics.checkNotNullParameter(matchRecords, "matchRecords");
        }

        public static void onReadMedicationAlarm(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleMedicationAlarm> medicationAlarm) {
            Intrinsics.checkNotNullParameter(medicationAlarm, "medicationAlarm");
        }

        public static void onReadMedicationReminder(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleMedicationReminder> medicationReminders) {
            Intrinsics.checkNotNullParameter(medicationReminders, "medicationReminders");
        }

        public static void onReadMindStatus(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleMindStatus> feelingData) {
            Intrinsics.checkNotNullParameter(feelingData, "feelingData");
        }

        public static void onReadMtkOtaMeta(@NotNull BleHandleCallback bleHandleCallback) {
        }

        public static void onReadNoDisturb(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleNoDisturbSettings noDisturbSettings) {
            Intrinsics.checkNotNullParameter(noDisturbSettings, "noDisturbSettings");
        }

        public static void onReadPackageStatus(@NotNull BleHandleCallback bleHandleCallback, @NotNull BlePackageStatus packageStatus) {
            Intrinsics.checkNotNullParameter(packageStatus, "packageStatus");
        }

        public static void onReadPower(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadPressure(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BlePressure> pressures) {
            Intrinsics.checkNotNullParameter(pressures, "pressures");
        }

        public static void onReadSedentariness(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleSedentarinessSettings sedentarinessSettings) {
            Intrinsics.checkNotNullParameter(sedentarinessSettings, "sedentarinessSettings");
        }

        public static void onReadSleep(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleSleep> sleeps) {
            Intrinsics.checkNotNullParameter(sleeps, "sleeps");
        }

        public static void onReadSleepGoal(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadSleepQuality(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleSleepQuality sleepQuality) {
            Intrinsics.checkNotNullParameter(sleepQuality, "sleepQuality");
        }

        public static void onReadSleepRaw(@NotNull BleHandleCallback bleHandleCallback, @NotNull byte[] sleepRawData) {
            Intrinsics.checkNotNullParameter(sleepRawData, "sleepRawData");
        }

        public static void onReadStepGoal(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadStock(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleStock> stocks) {
            Intrinsics.checkNotNullParameter(stocks, "stocks");
        }

        public static void onReadStock(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onReadTemperature(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleTemperature> temperatures) {
            Intrinsics.checkNotNullParameter(temperatures, "temperatures");
        }

        public static void onReadTemperatureUnit(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadTuyaKey(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleTuyaKey tuyaKey) {
            Intrinsics.checkNotNullParameter(tuyaKey, "tuyaKey");
        }

        public static void onReadUiPackVersion(@NotNull BleHandleCallback bleHandleCallback, @NotNull String version) {
            Intrinsics.checkNotNullParameter(version, "version");
        }

        public static void onReadUnit(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadUserProfile(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleUserProfile userProfile) {
            Intrinsics.checkNotNullParameter(userProfile, "userProfile");
        }

        public static void onReadWatchFaceId(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleWatchFaceId watchFaceId) {
            Intrinsics.checkNotNullParameter(watchFaceId, "watchFaceId");
        }

        public static void onReadWatchFaceSwitch(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onReadWeatherRealTime(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onReadWorkout(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleWorkout> workouts) {
            Intrinsics.checkNotNullParameter(workouts, "workouts");
        }

        public static void onReadWorkout2(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleWorkout2> workouts) {
            Intrinsics.checkNotNullParameter(workouts, "workouts");
        }

        public static void onReadWorldClock(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleWorldClock> clocks) {
            Intrinsics.checkNotNullParameter(clocks, "clocks");
        }

        public static void onRealTimeMeasurement(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleRealTimeMeasurement realTimeMeasurement) {
            Intrinsics.checkNotNullParameter(realTimeMeasurement, "realTimeMeasurement");
        }

        public static void onReceiveGSensorMotion(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleGSensorMotion> gSensorMotions) {
            Intrinsics.checkNotNullParameter(gSensorMotions, "gSensorMotions");
        }

        public static void onReceiveGSensorRaw(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleGSensorRaw> gSensorRaws) {
            Intrinsics.checkNotNullParameter(gSensorRaws, "gSensorRaws");
        }

        public static void onReceiveHRRaw(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleHRRaw> hrRaw) {
            Intrinsics.checkNotNullParameter(hrRaw, "hrRaw");
        }

        public static void onReceiveLocationGga(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleLocationGga locationGga) {
            Intrinsics.checkNotNullParameter(locationGga, "locationGga");
        }

        public static void onReceiveLocationGsv(@NotNull BleHandleCallback bleHandleCallback, @NotNull List<BleLocationGsv> locationGsv) {
            Intrinsics.checkNotNullParameter(locationGsv, "locationGsv");
        }

        public static void onReceiveMusicCommand(@NotNull BleHandleCallback bleHandleCallback, @NotNull MusicCommand musicCommand) {
            Intrinsics.checkNotNullParameter(musicCommand, "musicCommand");
        }

        public static void onReceiveRealtimeLog(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleRealtimeLog realtimeLog) {
            Intrinsics.checkNotNullParameter(realtimeLog, "realtimeLog");
        }

        public static void onReceiveRecordPacket(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleRecordPacket recordPacket) {
            Intrinsics.checkNotNullParameter(recordPacket, "recordPacket");
        }

        public static void onRequestAgpsPrerequisite(@NotNull BleHandleCallback bleHandleCallback) {
        }

        public static void onRequestLocation(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onSessionStateChange(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onStockDelete(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onStreamProgress(@NotNull BleHandleCallback bleHandleCallback, boolean z, int i, int i2, int i3) {
        }

        public static void onSyncData(@NotNull BleHandleCallback bleHandleCallback, int i, @NotNull BleKey bleKey) {
            Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        }

        public static void onUpdateAppSportState(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleAppSportState appSportState) {
            Intrinsics.checkNotNullParameter(appSportState, "appSportState");
        }

        public static void onUpdateBAC(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleBAC bac) {
            Intrinsics.checkNotNullParameter(bac, "bac");
        }

        public static void onUpdateBloodPressure(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleBloodPressure bloodPressure) {
            Intrinsics.checkNotNullParameter(bloodPressure, "bloodPressure");
        }

        public static void onUpdateHeartRate(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleHeartRate heartRate) {
            Intrinsics.checkNotNullParameter(heartRate, "heartRate");
        }

        public static void onUpdateTemperature(@NotNull BleHandleCallback bleHandleCallback, @NotNull BleTemperature temperature) {
            Intrinsics.checkNotNullParameter(temperature, "temperature");
        }

        public static void onUpdateWatchFaceSwitch(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onVibrationUpdate(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onWatchFaceIdUpdate(@NotNull BleHandleCallback bleHandleCallback, boolean z) {
        }

        public static void onWorldClockDelete(@NotNull BleHandleCallback bleHandleCallback, int i) {
        }

        public static void onXModem(@NotNull BleHandleCallback bleHandleCallback, byte b) {
        }
    }

    void onAlarmAdd(@NotNull BleAlarm bleAlarm);

    void onAlarmDelete(int i);

    void onAlarmUpdate(@NotNull BleAlarm bleAlarm);

    void onAppSportDataResponse(boolean z);

    void onBacklightUpdate(int i);

    void onBleThirdPartyDataUpdate(@NotNull BleThirdPartyData bleThirdPartyData);

    void onCameraResponse(boolean z, int i);

    void onCameraStateChange(int i);

    void onClassicBluetoothStateChange(int i);

    void onCommandReply(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, boolean z);

    void onCommandSendTimeout(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag);

    void onDeviceConnected(@NotNull BluetoothDevice bluetoothDevice);

    void onDeviceConnecting(boolean z);

    void onDeviceFileUpdate(@NotNull BleDeviceFile bleDeviceFile);

    void onDeviceRequestAGpsFile(@NotNull String str);

    void onDeviceSMSQuickReply(@NotNull BleSMSQuickReply bleSMSQuickReply);

    void onFindPhone(boolean z);

    void onFollowSystemLanguage(boolean z);

    void onGestureWakeUpdate(@NotNull BleGestureWake bleGestureWake);

    void onHIDState(int i);

    void onHIDValueChange(int i);

    void onIdentityCreate(boolean z, @Nullable BleDeviceInfo bleDeviceInfo);

    void onIdentityDelete(boolean z);

    void onIdentityDeleteByDevice(boolean z);

    void onIncomingCallStatus(int i);

    void onLoveTapUpdate(@NotNull BleLoveTap bleLoveTap);

    void onLoveTapUserDelete(int i);

    void onLoveTapUserUpdate(@NotNull BleLoveTapUser bleLoveTapUser);

    void onMedicationAlarmAdd(@NotNull BleMedicationAlarm bleMedicationAlarm);

    void onMedicationAlarmDelete(int i);

    void onMedicationAlarmUpdate(@NotNull BleMedicationAlarm bleMedicationAlarm);

    void onMedicationReminderDelete(int i);

    void onMedicationReminderUpdate(@NotNull BleMedicationReminder bleMedicationReminder);

    void onNaviInfoUpdate(@NotNull BleNaviInfo bleNaviInfo);

    void onNoDisturbUpdate(@NotNull BleNoDisturbSettings bleNoDisturbSettings);

    void onOTA(boolean z);

    void onPowerSaveModeState(int i);

    void onPowerSaveModeStateChange(int i);

    void onReadActivity(@NotNull List<BleActivity> list);

    void onReadAlarm(@NotNull List<BleAlarm> list);

    void onReadAlipaySettings(@NotNull BleAlipaySettings bleAlipaySettings);

    void onReadAvgHeartRate(@NotNull List<BleAvgHeartRate> list);

    void onReadBAC(@NotNull List<BleBAC> list);

    void onReadBacklight(int i);

    void onReadBleAddress(@NotNull String str);

    void onReadBleHrv(@NotNull List<BleHrv> list);

    void onReadBleLogText(@NotNull List<BleLogText> list);

    void onReadBloodGlucose(@NotNull List<BleBloodGlucose> list);

    void onReadBloodOxygen(@NotNull List<BleBloodOxygen> list);

    void onReadBloodPressure(@NotNull List<BleBloodPressure> list);

    void onReadBodyStatus(@NotNull List<BleBodyStatus> list);

    void onReadCalorieIntake(@NotNull List<BleCalorieIntake> list);

    void onReadCaloriesGoal(int i);

    void onReadCoachingIds(@NotNull BleCoachingIds bleCoachingIds);

    void onReadDateFormat(int i);

    void onReadDeviceFile(@NotNull BleDeviceFile bleDeviceFile);

    void onReadDeviceInfo(@NotNull BleDeviceInfo bleDeviceInfo);

    void onReadDeviceInfo2(@NotNull BleDeviceInfo2 bleDeviceInfo2);

    void onReadDistanceGoal(int i);

    void onReadDrinkWater(@NotNull BleDrinkWaterSettings bleDrinkWaterSettings);

    void onReadFirmwareVersion(@NotNull String str);

    void onReadFoodBalance(@NotNull List<BleFoodBalance> list);

    void onReadGestureWake(@NotNull BleGestureWake bleGestureWake);

    void onReadHeartRate(@NotNull List<BleHeartRate> list);

    void onReadHourSystem(int i);

    void onReadHrMonitoringSettings(@NotNull BleHrMonitoringSettings bleHrMonitoringSettings);

    void onReadLanguagePackVersion(@NotNull BleLanguagePackVersion bleLanguagePackVersion);

    void onReadLocation(@NotNull List<BleLocation> list);

    void onReadLoveTapUser(@NotNull List<BleLoveTapUser> list);

    void onReadMatchRecord(@NotNull List<BleMatchRecord> list);

    void onReadMatchRecord2(@NotNull List<BleMatchRecord2> list);

    void onReadMedicationAlarm(@NotNull List<BleMedicationAlarm> list);

    void onReadMedicationReminder(@NotNull List<BleMedicationReminder> list);

    void onReadMindStatus(@NotNull List<BleMindStatus> list);

    void onReadMtkOtaMeta();

    void onReadNoDisturb(@NotNull BleNoDisturbSettings bleNoDisturbSettings);

    void onReadPackageStatus(@NotNull BlePackageStatus blePackageStatus);

    void onReadPower(int i);

    void onReadPressure(@NotNull List<BlePressure> list);

    void onReadSedentariness(@NotNull BleSedentarinessSettings bleSedentarinessSettings);

    void onReadSleep(@NotNull List<BleSleep> list);

    void onReadSleepGoal(int i);

    void onReadSleepQuality(@NotNull BleSleepQuality bleSleepQuality);

    void onReadSleepRaw(@NotNull byte[] bArr);

    void onReadStepGoal(int i);

    void onReadStock(@NotNull List<BleStock> list);

    void onReadStock(boolean z);

    void onReadTemperature(@NotNull List<BleTemperature> list);

    void onReadTemperatureUnit(int i);

    void onReadTuyaKey(@NotNull BleTuyaKey bleTuyaKey);

    void onReadUiPackVersion(@NotNull String str);

    void onReadUnit(int i);

    void onReadUserProfile(@NotNull BleUserProfile bleUserProfile);

    void onReadWatchFaceId(@NotNull BleWatchFaceId bleWatchFaceId);

    void onReadWatchFaceSwitch(int i);

    void onReadWeatherRealTime(boolean z);

    void onReadWorkout(@NotNull List<BleWorkout> list);

    void onReadWorkout2(@NotNull List<BleWorkout2> list);

    void onReadWorldClock(@NotNull List<BleWorldClock> list);

    void onRealTimeMeasurement(@NotNull BleRealTimeMeasurement bleRealTimeMeasurement);

    void onReceiveGSensorMotion(@NotNull List<BleGSensorMotion> list);

    void onReceiveGSensorRaw(@NotNull List<BleGSensorRaw> list);

    void onReceiveHRRaw(@NotNull List<BleHRRaw> list);

    void onReceiveLocationGga(@NotNull BleLocationGga bleLocationGga);

    void onReceiveLocationGsv(@NotNull List<BleLocationGsv> list);

    void onReceiveMusicCommand(@NotNull MusicCommand musicCommand);

    void onReceiveRealtimeLog(@NotNull BleRealtimeLog bleRealtimeLog);

    void onReceiveRecordPacket(@NotNull BleRecordPacket bleRecordPacket);

    void onRequestAgpsPrerequisite();

    void onRequestLocation(int i);

    void onSessionStateChange(boolean z);

    void onStockDelete(int i);

    void onStreamProgress(boolean z, int i, int i2, int i3);

    void onSyncData(int i, @NotNull BleKey bleKey);

    void onUpdateAppSportState(@NotNull BleAppSportState bleAppSportState);

    void onUpdateBAC(@NotNull BleBAC bleBAC);

    void onUpdateBloodPressure(@NotNull BleBloodPressure bleBloodPressure);

    void onUpdateHeartRate(@NotNull BleHeartRate bleHeartRate);

    void onUpdateTemperature(@NotNull BleTemperature bleTemperature);

    void onUpdateWatchFaceSwitch(boolean z);

    void onVibrationUpdate(int i);

    void onWatchFaceIdUpdate(boolean z);

    void onWorldClockDelete(int i);

    void onXModem(byte b);
}
