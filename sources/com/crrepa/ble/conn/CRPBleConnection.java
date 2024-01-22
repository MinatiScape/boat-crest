package com.crrepa.ble.conn;

import android.graphics.Bitmap;
import com.crrepa.ble.conn.bean.CRPAlarmClockInfo;
import com.crrepa.ble.conn.bean.CRPContactInfo;
import com.crrepa.ble.conn.bean.CRPCustomizeWatchFaceInfo;
import com.crrepa.ble.conn.bean.CRPDrinkWaterPeriodInfo;
import com.crrepa.ble.conn.bean.CRPFunctionInfo;
import com.crrepa.ble.conn.bean.CRPFutureWeatherInfo;
import com.crrepa.ble.conn.bean.CRPHandWashingPeriodInfo;
import com.crrepa.ble.conn.bean.CRPMessageInfo;
import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo;
import com.crrepa.ble.conn.bean.CRPPhysiologcalPeriodInfo;
import com.crrepa.ble.conn.bean.CRPPillReminderInfo;
import com.crrepa.ble.conn.bean.CRPSedentaryReminderPeriodInfo;
import com.crrepa.ble.conn.bean.CRPSleepInfo;
import com.crrepa.ble.conn.bean.CRPTodayWeatherInfo;
import com.crrepa.ble.conn.bean.CRPUserInfo;
import com.crrepa.ble.conn.bean.CRPWatchFaceBackgroundInfo;
import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
import com.crrepa.ble.conn.callback.CRPBtAddressCallback;
import com.crrepa.ble.conn.callback.CRPContactConfigCallback;
import com.crrepa.ble.conn.callback.CRPContactCountCallback;
import com.crrepa.ble.conn.callback.CRPDeviceAlarmClockCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBondStateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBreathingLightCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBrightnessCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuAddressCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuStatusCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuTypeCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDisplayTimeCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDisplayWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDominantHandCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDrinkWaterPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceFirmwareVersionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceFunctionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceGoalStepCallback;
import com.crrepa.ble.conn.callback.CRPDeviceHandWashingPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceLanguageCallback;
import com.crrepa.ble.conn.callback.CRPDeviceMaxHeartRateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceMetricSystemCallback;
import com.crrepa.ble.conn.callback.CRPDeviceOtherMessageCallback;
import com.crrepa.ble.conn.callback.CRPDevicePeriodTimeCallback;
import com.crrepa.ble.conn.callback.CRPDevicePhysiologcalPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceQuickViewCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSedentaryReminderCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSedentaryReminderPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSupportWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceTimeSystemCallback;
import com.crrepa.ble.conn.callback.CRPDeviceTimingMeasureHeartRateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceVersionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceLayoutCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceListCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceStoreCallback;
import com.crrepa.ble.conn.callback.CRPMtuChangeCallback;
import com.crrepa.ble.conn.callback.CRPPillReminderCallback;
import com.crrepa.ble.conn.callback.CRPTapToWakeCallback;
import com.crrepa.ble.conn.callback.CRPTimingTempStateCallback;
import com.crrepa.ble.conn.listener.CRPA2DPConnectStateListener;
import com.crrepa.ble.conn.listener.CRPBatterySavingChangeListener;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.listener.CRPBleECGChangeListener;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener;
import com.crrepa.ble.conn.listener.CRPBloodPressureChangeListener;
import com.crrepa.ble.conn.listener.CRPCallNumberListener;
import com.crrepa.ble.conn.listener.CRPCameraOperationListener;
import com.crrepa.ble.conn.listener.CRPContactListener;
import com.crrepa.ble.conn.listener.CRPDeviceBatteryListener;
import com.crrepa.ble.conn.listener.CRPDeviceRssiListener;
import com.crrepa.ble.conn.listener.CRPFileTransListener;
import com.crrepa.ble.conn.listener.CRPFindPhoneListener;
import com.crrepa.ble.conn.listener.CRPHeartRateChangeListener;
import com.crrepa.ble.conn.listener.CRPHrvChangeListener;
import com.crrepa.ble.conn.listener.CRPMovementStateListener;
import com.crrepa.ble.conn.listener.CRPPhoneOperationListener;
import com.crrepa.ble.conn.listener.CRPSleepActionChangeListener;
import com.crrepa.ble.conn.listener.CRPSleepChangeListener;
import com.crrepa.ble.conn.listener.CRPSosChangeListener;
import com.crrepa.ble.conn.listener.CRPStepChangeListener;
import com.crrepa.ble.conn.listener.CRPStepsCategoryChangeListener;
import com.crrepa.ble.conn.listener.CRPTempChangeListener;
import com.crrepa.ble.conn.listener.CRPTrainingChangeListener;
import com.crrepa.ble.conn.listener.CRPWeatherChangeListener;
import com.crrepa.ble.conn.type.CRPBloodOxygenTimeType;
import com.crrepa.ble.conn.type.CRPEcgMeasureType;
import com.crrepa.ble.conn.type.CRPHistoryDynamicRateType;
import com.crrepa.ble.conn.type.CRPProtocolVersion;
import com.crrepa.ble.conn.type.CRPTempTimeType;
import java.io.File;
import java.util.List;
/* loaded from: classes9.dex */
public interface CRPBleConnection {
    void abortFirmwareUpgrade();

    void abortWatchFace();

    void abortWatchFaceBackground();

    void checkSupportQuickContact(CRPContactConfigCallback cRPContactConfigCallback);

    void clearContact();

    void clearPillReminder();

    void close();

    void closeMusicControl();

    boolean connect();

    void createBond(byte[] bArr, CRPDeviceBondStateCallback cRPDeviceBondStateCallback);

    void deleteContact(int i);

    void deleteContactAvatar(int i);

    void deletePillReminder(int i);

    void disableContinueBloodOxygen();

    void disableContinueBloodPressure();

    void disableContinueTemp();

    void disableDrinkWaterReminder();

    void disableHandWashingReminder();

    void disableHrvMeasure();

    void disableTimingMeasureBloodOxygen();

    void disableTimingMeasureHeartRate();

    void disableTimingMeasureTemp();

    void enableContinueBloodOxygen();

    void enableContinueBloodPressure();

    void enableContinueTemp();

    void enableDrinkWaterReminder(CRPDrinkWaterPeriodInfo cRPDrinkWaterPeriodInfo);

    void enableHandWashingReminder(CRPHandWashingPeriodInfo cRPHandWashingPeriodInfo);

    void enableHrvMeasure(int i);

    void enableHsDfu();

    void enableTimingMeasureBloodOxygen(int i);

    void enableTimingMeasureHeartRate(int i);

    void enableTimingMeasureTemp();

    void enterCameraView();

    void exitCameraView();

    void findDevice();

    CRPProtocolVersion getProtocolVersion();

    boolean isNewECGMeasurementVersion();

    CRPSleepInfo parseSleep(CRPSleepInfo cRPSleepInfo);

    void queryA2DPState();

    void queryAllAlarmClock(CRPDeviceAlarmClockCallback cRPDeviceAlarmClockCallback);

    void queryBatterySaving();

    void queryBreathingLight(CRPDeviceBreathingLightCallback cRPDeviceBreathingLightCallback);

    void queryBrightness(CRPDeviceBrightnessCallback cRPDeviceBrightnessCallback);

    void queryBtAddress(CRPBtAddressCallback cRPBtAddressCallback);

    void queryContactCount(CRPContactCountCallback cRPContactCountCallback);

    void queryContinueBloodOxygenState();

    void queryContinueBloodPressureState();

    void queryContinueTempState();

    void queryDeviceBattery();

    void queryDeviceDfuStatus(CRPDeviceDfuStatusCallback cRPDeviceDfuStatusCallback);

    void queryDeviceLanguage(CRPDeviceLanguageCallback cRPDeviceLanguageCallback);

    void queryDeviceSupportFunction(CRPDeviceFunctionCallback cRPDeviceFunctionCallback);

    void queryDeviceVersion(CRPDeviceVersionCallback cRPDeviceVersionCallback);

    void queryDfuType(CRPDeviceDfuTypeCallback cRPDeviceDfuTypeCallback);

    void queryDisplayDeviceFunction(CRPDeviceFunctionCallback cRPDeviceFunctionCallback);

    void queryDisplayTime(CRPDeviceDisplayTimeCallback cRPDeviceDisplayTimeCallback);

    void queryDisplayWatchFace(CRPDeviceDisplayWatchFaceCallback cRPDeviceDisplayWatchFaceCallback);

    void queryDoNotDistrubTime(CRPDevicePeriodTimeCallback cRPDevicePeriodTimeCallback);

    void queryDominantHand(CRPDeviceDominantHandCallback cRPDeviceDominantHandCallback);

    void queryDrinkWaterReminderPeriod(CRPDeviceDrinkWaterPeriodCallback cRPDeviceDrinkWaterPeriodCallback);

    void queryFrimwareVersion(CRPDeviceFirmwareVersionCallback cRPDeviceFirmwareVersionCallback);

    void queryGoalStep(CRPDeviceGoalStepCallback cRPDeviceGoalStepCallback);

    void queryHandWashingReminderPeriod(CRPDeviceHandWashingPeriodCallback cRPDeviceHandWashingPeriodCallback);

    void queryHistoryBloodOxygen();

    void queryHistoryBloodPressure();

    void queryHistoryHeartRate();

    void queryHistoryTraining();

    void queryHrv(int i, int i2);

    void queryHrvMeasureCount(int i);

    void queryHrvMeasureInterval();

    void queryHsDfuAddress(CRPDeviceDfuAddressCallback cRPDeviceDfuAddressCallback);

    void queryLast24HourBloodOxygen();

    void queryLast24HourBloodPressure();

    void queryLast24HourTemp();

    void queryLastDynamicRate(CRPHistoryDynamicRateType cRPHistoryDynamicRateType);

    void queryLastMeasureECGData();

    void queryMaxHeartRate(CRPDeviceMaxHeartRateCallback cRPDeviceMaxHeartRateCallback);

    void queryMetricSystem(CRPDeviceMetricSystemCallback cRPDeviceMetricSystemCallback);

    void queryMovementHeartRate();

    void queryOtherMessageState(CRPDeviceOtherMessageCallback cRPDeviceOtherMessageCallback);

    void queryPastHeartRate();

    void queryPhysiologcalPeriod(CRPDevicePhysiologcalPeriodCallback cRPDevicePhysiologcalPeriodCallback);

    void queryPillReminder(CRPPillReminderCallback cRPPillReminderCallback);

    void queryQuickView(CRPDeviceQuickViewCallback cRPDeviceQuickViewCallback);

    void queryQuickViewTime(CRPDevicePeriodTimeCallback cRPDevicePeriodTimeCallback);

    void querySedentaryReminder(CRPDeviceSedentaryReminderCallback cRPDeviceSedentaryReminderCallback);

    void querySedentaryReminderPeriod(CRPDeviceSedentaryReminderPeriodCallback cRPDeviceSedentaryReminderPeriodCallback);

    void querySleepAction(int i);

    void queryStepsCategory(int i);

    void querySupportWatchFace(CRPDeviceSupportWatchFaceCallback cRPDeviceSupportWatchFaceCallback);

    void queryTapToWakeState(CRPTapToWakeCallback cRPTapToWakeCallback);

    void queryTempUnit();

    void queryTimeSystem(CRPDeviceTimeSystemCallback cRPDeviceTimeSystemCallback);

    void queryTimingBloodOxygen(CRPBloodOxygenTimeType cRPBloodOxygenTimeType);

    void queryTimingBloodOxygenMeasureState();

    void queryTimingMeasureHeartRate(CRPDeviceTimingMeasureHeartRateCallback cRPDeviceTimingMeasureHeartRateCallback);

    void queryTimingMeasureTemp(CRPTempTimeType cRPTempTimeType);

    void queryTimingMeasureTempState(CRPTimingTempStateCallback cRPTimingTempStateCallback);

    void queryTodayHeartRate(int i);

    void queryTraining(int i);

    void queryWatchFaceLayout(CRPDeviceWatchFaceLayoutCallback cRPDeviceWatchFaceLayoutCallback);

    void queryWatchFaceList(CRPDeviceWatchFaceListCallback cRPDeviceWatchFaceListCallback);

    void queryWatchFaceOfID(int i, CRPDeviceWatchFaceCallback cRPDeviceWatchFaceCallback);

    void queryWatchFaceStore(List<Integer> list, String str, int i, int i2, CRPDeviceWatchFaceStoreCallback cRPDeviceWatchFaceStoreCallback);

    void readDeviceRssi();

    void reset();

    void restart();

    void sendA2DPState(CRPA2DPConnectStateListener.A2DPConnectState a2DPConnectState);

    void sendAlarmClock(CRPAlarmClockInfo cRPAlarmClockInfo);

    void sendBatterySaving(boolean z);

    void sendBoundVibration();

    void sendBreathingLight(boolean z);

    void sendBrightness(int i);

    void sendCall0ffHook();

    void sendCallContactName(String str);

    void sendContact(CRPContactInfo cRPContactInfo);

    void sendContactAvatar(int i, Bitmap bitmap, int i2, CRPFileTransListener cRPFileTransListener);

    void sendCurrentVolume(int i);

    void sendDeviceLanguage(byte b);

    void sendDeviceVersion(byte b);

    void sendDislpayDeviceFunction(CRPFunctionInfo cRPFunctionInfo);

    void sendDisplayTime(int i);

    void sendDisplayWatchFace(byte b);

    void sendDoNotDistrubTime(CRPPeriodTimeInfo cRPPeriodTimeInfo);

    void sendDominantHand(byte b);

    void sendECGHeartRate(int i);

    void sendFutureWeather(CRPFutureWeatherInfo cRPFutureWeatherInfo);

    void sendGoalSteps(int i);

    void sendGsensorCalibration();

    void sendLocalCity(String str);

    void sendLyrics(String str);

    void sendMaxVolume(int i);

    void sendMessage(CRPMessageInfo cRPMessageInfo);

    void sendMetricSystem(byte b);

    void sendOtherMessageState(boolean z);

    void sendPhysiologcalPeriod(CRPPhysiologcalPeriodInfo cRPPhysiologcalPeriodInfo);

    void sendPillReminder(CRPPillReminderInfo cRPPillReminderInfo);

    void sendQuickView(boolean z);

    void sendQuickViewTime(CRPPeriodTimeInfo cRPPeriodTimeInfo);

    void sendSedentaryReminder(boolean z);

    void sendSedentaryReminderPeriod(CRPSedentaryReminderPeriodInfo cRPSedentaryReminderPeriodInfo);

    void sendSongTitle(String str);

    void sendStepLength(byte b);

    void sendTapToWakeState(boolean z);

    void sendTempUnit(byte b);

    void sendTimeSystem(byte b);

    void sendTodayWeather(CRPTodayWeatherInfo cRPTodayWeatherInfo);

    void sendUserInfo(CRPUserInfo cRPUserInfo);

    void sendWatchFace(CRPCustomizeWatchFaceInfo cRPCustomizeWatchFaceInfo, CRPFileTransListener cRPFileTransListener, int i);

    void sendWatchFaceBackground(CRPWatchFaceBackgroundInfo cRPWatchFaceBackgroundInfo, CRPFileTransListener cRPFileTransListener);

    void sendWatchFaceLayout(CRPWatchFaceLayoutInfo cRPWatchFaceLayoutInfo);

    void setA2DPConnectStateListener(CRPA2DPConnectStateListener cRPA2DPConnectStateListener);

    void setBatterySavingListener(CRPBatterySavingChangeListener cRPBatterySavingChangeListener);

    void setBloodOxygenChangeListener(CRPBloodOxygenChangeListener cRPBloodOxygenChangeListener);

    void setBloodPressureChangeListener(CRPBloodPressureChangeListener cRPBloodPressureChangeListener);

    void setCallNumberListener(CRPCallNumberListener cRPCallNumberListener);

    void setCameraOperationListener(CRPCameraOperationListener cRPCameraOperationListener);

    void setConnectionStateListener(CRPBleConnectionStateListener cRPBleConnectionStateListener);

    void setContactListener(CRPContactListener cRPContactListener);

    void setDeviceBatteryListener(CRPDeviceBatteryListener cRPDeviceBatteryListener);

    void setDeviceRssiListener(CRPDeviceRssiListener cRPDeviceRssiListener);

    void setECGChangeListener(CRPBleECGChangeListener cRPBleECGChangeListener, CRPEcgMeasureType cRPEcgMeasureType);

    void setFindPhoneListener(CRPFindPhoneListener cRPFindPhoneListener);

    void setHeartRateChangeListener(CRPHeartRateChangeListener cRPHeartRateChangeListener);

    void setHrvChangeListener(CRPHrvChangeListener cRPHrvChangeListener);

    void setMaxHeartRate(byte b, boolean z);

    void setMovementState(byte b);

    void setMovementStateListener(CRPMovementStateListener cRPMovementStateListener);

    void setMtu(CRPMtuChangeCallback cRPMtuChangeCallback, int i);

    void setMusicPlayerState(byte b);

    void setPhoneOperationListener(CRPPhoneOperationListener cRPPhoneOperationListener);

    void setSleepActionChangeListener(CRPSleepActionChangeListener cRPSleepActionChangeListener);

    void setSleepChangeListener(CRPSleepChangeListener cRPSleepChangeListener);

    void setSosChangeListener(CRPSosChangeListener cRPSosChangeListener);

    void setStepChangeListener(CRPStepChangeListener cRPStepChangeListener);

    void setStepsCategoryListener(CRPStepsCategoryChangeListener cRPStepsCategoryChangeListener);

    void setTempChangeListener(CRPTempChangeListener cRPTempChangeListener);

    void setTrainingListener(CRPTrainingChangeListener cRPTrainingChangeListener);

    void setWeatherChangeListener(CRPWeatherChangeListener cRPWeatherChangeListener);

    void shutDown();

    void startECGMeasure();

    void startFindPhone();

    void startFirmwareUpgrade(boolean z, File file, String str, CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener);

    void startMeasureBloodOxygen();

    void startMeasureBloodPressure();

    void startMeasureDynamicRate();

    void startMeasureHrv();

    void startMeasureOnceHeartRate();

    void startMeasureTemp();

    void startMovement(byte b);

    void stopECGMeasure();

    void stopFindPhone();

    void stopMeasureBloodOxygen();

    void stopMeasureBloodPressure();

    void stopMeasureDynamicRtae();

    void stopMeasureHrv();

    void stopMeasureOnceHeartRate();

    void stopMeasureTemp();

    void subscribeDeviceBattery();

    void syncPastSleep(byte b);

    void syncPastStep(byte b);

    void syncRemSleep();

    void syncSleep();

    void syncStep();

    void syncTime();
}
