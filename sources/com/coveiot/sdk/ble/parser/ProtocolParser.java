package com.coveiot.sdk.ble.parser;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.error.Error;
import com.coveiot.sdk.ble.api.error.Type;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.ProgressType;
import com.coveiot.sdk.ble.api.request.ActivityPauseResumetReq;
import com.coveiot.sdk.ble.api.request.BTCallControlReq;
import com.coveiot.sdk.ble.api.request.BandDisplaySettingsReq;
import com.coveiot.sdk.ble.api.request.BloodPressureMeasurementReq;
import com.coveiot.sdk.ble.api.request.CZ2MessageContentReq;
import com.coveiot.sdk.ble.api.request.ChangeDistanceUnitReq;
import com.coveiot.sdk.ble.api.request.ChangeTemperatureUnitReq;
import com.coveiot.sdk.ble.api.request.ChangeWatchFaceBGReq;
import com.coveiot.sdk.ble.api.request.ChangeWearingHandReq;
import com.coveiot.sdk.ble.api.request.ConfigureActivityListReq;
import com.coveiot.sdk.ble.api.request.CurrentSportModeReq;
import com.coveiot.sdk.ble.api.request.CustomMessageReq;
import com.coveiot.sdk.ble.api.request.DNDModeReq;
import com.coveiot.sdk.ble.api.request.DeleteImageReq;
import com.coveiot.sdk.ble.api.request.DeleteWatchFaceReq;
import com.coveiot.sdk.ble.api.request.FindMyWatchReq;
import com.coveiot.sdk.ble.api.request.GetActivitySummaryReq;
import com.coveiot.sdk.ble.api.request.GetAlarmListReq;
import com.coveiot.sdk.ble.api.request.GetAutoActivityDetectionSettingsReq;
import com.coveiot.sdk.ble.api.request.GetBatterySaverConfigReq;
import com.coveiot.sdk.ble.api.request.GetButtonFunctionReq;
import com.coveiot.sdk.ble.api.request.GetCalorieDataReq;
import com.coveiot.sdk.ble.api.request.GetCurrentWatchFaceReq;
import com.coveiot.sdk.ble.api.request.GetDNDModeSettingsReq;
import com.coveiot.sdk.ble.api.request.GetDiagnosticFeatureTestReq;
import com.coveiot.sdk.ble.api.request.GetDistanceDataReq;
import com.coveiot.sdk.ble.api.request.GetFirmwareCapabilityReq;
import com.coveiot.sdk.ble.api.request.GetNavigationConfigurationReq;
import com.coveiot.sdk.ble.api.request.GetNavigationDisclaimerReq;
import com.coveiot.sdk.ble.api.request.GetNavigationFavouriteLocationReq;
import com.coveiot.sdk.ble.api.request.GetNavigationWatchStateReq;
import com.coveiot.sdk.ble.api.request.GetNearbyDeviceListReq;
import com.coveiot.sdk.ble.api.request.GetQRCodeReq;
import com.coveiot.sdk.ble.api.request.GetQuickReplyListReq;
import com.coveiot.sdk.ble.api.request.GetSOSConfigReq;
import com.coveiot.sdk.ble.api.request.GetSOSRecordsReq;
import com.coveiot.sdk.ble.api.request.GetSensAIActivityConfigReq;
import com.coveiot.sdk.ble.api.request.GetSensAISummaryDataReq;
import com.coveiot.sdk.ble.api.request.GetSilentModeConfigReq;
import com.coveiot.sdk.ble.api.request.GetSmartAlertConfigReq;
import com.coveiot.sdk.ble.api.request.GetSocialDistanceScanSettingsReq;
import com.coveiot.sdk.ble.api.request.GetWatchFaceListReq;
import com.coveiot.sdk.ble.api.request.GetWatchfaceBackgroundReq;
import com.coveiot.sdk.ble.api.request.HeartRateMeasurementReq;
import com.coveiot.sdk.ble.api.request.HeartRateZoneReq;
import com.coveiot.sdk.ble.api.request.LiftWristViewReq;
import com.coveiot.sdk.ble.api.request.LiveStepsControlReq;
import com.coveiot.sdk.ble.api.request.MessageAlertSwitchesReq;
import com.coveiot.sdk.ble.api.request.MessageContentReq;
import com.coveiot.sdk.ble.api.request.PairingFlowCmdReq;
import com.coveiot.sdk.ble.api.request.PersonalizedMessageReq;
import com.coveiot.sdk.ble.api.request.ReadRawPPGDataReq;
import com.coveiot.sdk.ble.api.request.SaveBpCalibrationReq;
import com.coveiot.sdk.ble.api.request.SaveFitnessProfileReq;
import com.coveiot.sdk.ble.api.request.SaveFitnessProfileReqOld;
import com.coveiot.sdk.ble.api.request.SaveProbeReq;
import com.coveiot.sdk.ble.api.request.SedentaryReminderReq;
import com.coveiot.sdk.ble.api.request.SendBTCallInfoReq;
import com.coveiot.sdk.ble.api.request.SendUnbindBTCallReq;
import com.coveiot.sdk.ble.api.request.SendWeatherDataRequest;
import com.coveiot.sdk.ble.api.request.SendWomenWellnessReq;
import com.coveiot.sdk.ble.api.request.SensAISetActivityConfigReq;
import com.coveiot.sdk.ble.api.request.SetAlarmReq;
import com.coveiot.sdk.ble.api.request.SetAutoActivityDetectionSettingsReq;
import com.coveiot.sdk.ble.api.request.SetAutomaticPPGConfigReq;
import com.coveiot.sdk.ble.api.request.SetButtonFunctionReq;
import com.coveiot.sdk.ble.api.request.SetCameraStatusReq;
import com.coveiot.sdk.ble.api.request.SetCurrentWatchFaceReq;
import com.coveiot.sdk.ble.api.request.SetCustomReminderReq;
import com.coveiot.sdk.ble.api.request.SetDiagnosticControlReq;
import com.coveiot.sdk.ble.api.request.SetMusicPlayBackStatusReq;
import com.coveiot.sdk.ble.api.request.SetMusicVolumePercentageReq;
import com.coveiot.sdk.ble.api.request.SetNavigationAliveTimerReq;
import com.coveiot.sdk.ble.api.request.SetNavigationApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetNavigationConfigurationReq;
import com.coveiot.sdk.ble.api.request.SetNavigationDisclaimerReq;
import com.coveiot.sdk.ble.api.request.SetNavigationEventReq;
import com.coveiot.sdk.ble.api.request.SetNavigationFavouriteLocationReq;
import com.coveiot.sdk.ble.api.request.SetNavigationStatusReq;
import com.coveiot.sdk.ble.api.request.SetNonSmartAlertApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetPhoneBookReq;
import com.coveiot.sdk.ble.api.request.SetQRTrayCodeReq;
import com.coveiot.sdk.ble.api.request.SetQuickReplyReq;
import com.coveiot.sdk.ble.api.request.SetSOSConfigReq;
import com.coveiot.sdk.ble.api.request.SetScreenTimeOutReq;
import com.coveiot.sdk.ble.api.request.SetSleepTargetReq;
import com.coveiot.sdk.ble.api.request.SetSmartAlertApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetSmartAlertConfigReq;
import com.coveiot.sdk.ble.api.request.SetSocialDistanceScanSettingsReq;
import com.coveiot.sdk.ble.api.request.SetSportNotificationReq;
import com.coveiot.sdk.ble.api.request.SetStressIntervalControlReq;
import com.coveiot.sdk.ble.api.request.SetTimeFormatReq;
import com.coveiot.sdk.ble.api.request.SetVibrationReq;
import com.coveiot.sdk.ble.api.request.SetWalkTargetReq;
import com.coveiot.sdk.ble.api.request.SetWeatherConfigInfoReq;
import com.coveiot.sdk.ble.api.request.SetWeatherTemperatureUnitReq;
import com.coveiot.sdk.ble.api.request.SportsNotificationReq;
import com.coveiot.sdk.ble.api.request.StopMessageNotificationReq;
import com.coveiot.sdk.ble.api.request.StressDataDataReq;
import com.coveiot.sdk.ble.api.request.TemperatureMeasurementReq;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticHeartRateReq;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticSpo2Req;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticTemperatureReq;
import com.coveiot.sdk.ble.api.request.TodaysFitnessDataReq;
import com.coveiot.sdk.ble.api.request.TodaysStepsDataReq;
import com.coveiot.sdk.ble.api.request.Vo2MaxReq;
import com.coveiot.sdk.ble.api.request.WatchFaceUploadReq;
import com.coveiot.sdk.ble.api.response.ActivityDetailsDataRes;
import com.coveiot.sdk.ble.api.response.ActivityPauseResumeRes;
import com.coveiot.sdk.ble.api.response.BTCallControlRes;
import com.coveiot.sdk.ble.api.response.BandDisplaySettingsRes;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.BloodPressureMeasurementRes;
import com.coveiot.sdk.ble.api.response.CallMuteRes;
import com.coveiot.sdk.ble.api.response.CallRejectRes;
import com.coveiot.sdk.ble.api.response.CalorieDataRes;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.ChangeDistanceUnitRes;
import com.coveiot.sdk.ble.api.response.ChangeTemperatureUnitRes;
import com.coveiot.sdk.ble.api.response.ChangeWatchFaceBGRes;
import com.coveiot.sdk.ble.api.response.ChangeWearingHandRes;
import com.coveiot.sdk.ble.api.response.ConfigureActivityListRes;
import com.coveiot.sdk.ble.api.response.CurrentSportModesRes;
import com.coveiot.sdk.ble.api.response.CustomMessageRes;
import com.coveiot.sdk.ble.api.response.DeleteImageRes;
import com.coveiot.sdk.ble.api.response.DeleteNearbyDevicesRes;
import com.coveiot.sdk.ble.api.response.DeleteWatchFaceRes;
import com.coveiot.sdk.ble.api.response.DistanceDataRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.api.response.FindMyWatchRes;
import com.coveiot.sdk.ble.api.response.GetActivitySummaryRes;
import com.coveiot.sdk.ble.api.response.GetAlarmListRes;
import com.coveiot.sdk.ble.api.response.GetAutoActivityDetectionSettingsRes;
import com.coveiot.sdk.ble.api.response.GetBatterySaverConfigRes;
import com.coveiot.sdk.ble.api.response.GetButtonFunctionRes;
import com.coveiot.sdk.ble.api.response.GetConfiguredActivityListRes;
import com.coveiot.sdk.ble.api.response.GetCurrentWatchFaceRes;
import com.coveiot.sdk.ble.api.response.GetDNDModeSettingsRes;
import com.coveiot.sdk.ble.api.response.GetDiagnosticFeatureTestRes;
import com.coveiot.sdk.ble.api.response.GetFirmwareCapabilityRes;
import com.coveiot.sdk.ble.api.response.GetImageIdListRes;
import com.coveiot.sdk.ble.api.response.GetLatestHealthDataRes;
import com.coveiot.sdk.ble.api.response.GetLiftWristViewRes;
import com.coveiot.sdk.ble.api.response.GetNavigationConfigurationRes;
import com.coveiot.sdk.ble.api.response.GetNavigationDisclaimerRes;
import com.coveiot.sdk.ble.api.response.GetNavigationFavouriteLocationRes;
import com.coveiot.sdk.ble.api.response.GetNavigationWatchStateRes;
import com.coveiot.sdk.ble.api.response.GetNearbyDevicesMacRes;
import com.coveiot.sdk.ble.api.response.GetNearbyDevicesRes;
import com.coveiot.sdk.ble.api.response.GetQRCodeRes;
import com.coveiot.sdk.ble.api.response.GetQuickReplyListRes;
import com.coveiot.sdk.ble.api.response.GetSOSConfigRes;
import com.coveiot.sdk.ble.api.response.GetSOSRecordsRes;
import com.coveiot.sdk.ble.api.response.GetSensAIActivityConfigRes;
import com.coveiot.sdk.ble.api.response.GetSensAISummaryDataRes;
import com.coveiot.sdk.ble.api.response.GetSensAISummaryDetailsDataRes;
import com.coveiot.sdk.ble.api.response.GetSilentModeConfigRes;
import com.coveiot.sdk.ble.api.response.GetSmartAlertConfigRes;
import com.coveiot.sdk.ble.api.response.GetSocialDistanceScanSettingsRes;
import com.coveiot.sdk.ble.api.response.GetTimeRes;
import com.coveiot.sdk.ble.api.response.GetWatchFaceListRes;
import com.coveiot.sdk.ble.api.response.GetWatchfaceBackgroundRes;
import com.coveiot.sdk.ble.api.response.HeartRateMeasurementRes;
import com.coveiot.sdk.ble.api.response.HeartRateZoneRes;
import com.coveiot.sdk.ble.api.response.HrBpDataRes;
import com.coveiot.sdk.ble.api.response.LiftWristViewRes;
import com.coveiot.sdk.ble.api.response.LiveHealthRes;
import com.coveiot.sdk.ble.api.response.LiveStepsControlRes;
import com.coveiot.sdk.ble.api.response.LiveStepsRes;
import com.coveiot.sdk.ble.api.response.LiveTemperatureRes;
import com.coveiot.sdk.ble.api.response.MessageAlertSwitchesRes;
import com.coveiot.sdk.ble.api.response.MessageContentRes;
import com.coveiot.sdk.ble.api.response.MusicStatusNotificationRes;
import com.coveiot.sdk.ble.api.response.NotifyNavigationEventRes;
import com.coveiot.sdk.ble.api.response.PairingFlowCmdRes;
import com.coveiot.sdk.ble.api.response.PersonalizedMessageRes;
import com.coveiot.sdk.ble.api.response.RawEcgRes;
import com.coveiot.sdk.ble.api.response.ReadBatteryLevelRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceFirmwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceHardwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceInfoRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceManufacturerRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceModelRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceNameRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSerialNumberRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSoftwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadManualBpRes;
import com.coveiot.sdk.ble.api.response.ReadRawAccelerometerDataRes;
import com.coveiot.sdk.ble.api.response.ReadRawPPGDataRes;
import com.coveiot.sdk.ble.api.response.ReadSedentaryDataRes;
import com.coveiot.sdk.ble.api.response.RrDataRes;
import com.coveiot.sdk.ble.api.response.RunningDataRes;
import com.coveiot.sdk.ble.api.response.SaveBpCalibrationRes;
import com.coveiot.sdk.ble.api.response.SaveFitnessProfileRes;
import com.coveiot.sdk.ble.api.response.SaveProbeRes;
import com.coveiot.sdk.ble.api.response.SendBTCallInfoRes;
import com.coveiot.sdk.ble.api.response.SendDiagnosticsRes;
import com.coveiot.sdk.ble.api.response.SendFileRes;
import com.coveiot.sdk.ble.api.response.SendImageRes;
import com.coveiot.sdk.ble.api.response.SendPersonalizedMessageRes;
import com.coveiot.sdk.ble.api.response.SendQuickReplyRes;
import com.coveiot.sdk.ble.api.response.SendSOSRes;
import com.coveiot.sdk.ble.api.response.SendUnbindBTCallRes;
import com.coveiot.sdk.ble.api.response.SendWeatherRes;
import com.coveiot.sdk.ble.api.response.SetAlarmRes;
import com.coveiot.sdk.ble.api.response.SetAutoActivityDetectionRes;
import com.coveiot.sdk.ble.api.response.SetAutomaticPPGConfigRes;
import com.coveiot.sdk.ble.api.response.SetButtonFunctionRes;
import com.coveiot.sdk.ble.api.response.SetCameraStatusRes;
import com.coveiot.sdk.ble.api.response.SetCurrentWatchFaceRes;
import com.coveiot.sdk.ble.api.response.SetCustomReminderRes;
import com.coveiot.sdk.ble.api.response.SetDNDModeRes;
import com.coveiot.sdk.ble.api.response.SetDiagnosticControlRes;
import com.coveiot.sdk.ble.api.response.SetNavigationAliveTimerRes;
import com.coveiot.sdk.ble.api.response.SetNavigationApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetNavigationConfigurationRes;
import com.coveiot.sdk.ble.api.response.SetNavigationDisclaimerRes;
import com.coveiot.sdk.ble.api.response.SetNavigationEventRes;
import com.coveiot.sdk.ble.api.response.SetNavigationFavouriteLocationRes;
import com.coveiot.sdk.ble.api.response.SetNavigationStatusRes;
import com.coveiot.sdk.ble.api.response.SetNonSmartAlertApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetPhoneBookRes;
import com.coveiot.sdk.ble.api.response.SetQRTrayCodeRes;
import com.coveiot.sdk.ble.api.response.SetQuickReplyRes;
import com.coveiot.sdk.ble.api.response.SetSOSConfigRes;
import com.coveiot.sdk.ble.api.response.SetScreenTimeOutRes;
import com.coveiot.sdk.ble.api.response.SetSedentaryReminderRes;
import com.coveiot.sdk.ble.api.response.SetSensAIActivityConfigRes;
import com.coveiot.sdk.ble.api.response.SetSleepTargetRes;
import com.coveiot.sdk.ble.api.response.SetSmartAlertApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetSmartAlertConfigRes;
import com.coveiot.sdk.ble.api.response.SetSocialDistanceScanSettingsRes;
import com.coveiot.sdk.ble.api.response.SetSportNotificationRes;
import com.coveiot.sdk.ble.api.response.SetStressIntervalControlRes;
import com.coveiot.sdk.ble.api.response.SetTimeFormatRes;
import com.coveiot.sdk.ble.api.response.SetVibrationRes;
import com.coveiot.sdk.ble.api.response.SetWalkTargetRes;
import com.coveiot.sdk.ble.api.response.SetWeatherConfigInfoRes;
import com.coveiot.sdk.ble.api.response.SetWeatherTemperatureUnitRes;
import com.coveiot.sdk.ble.api.response.SleepDataRes;
import com.coveiot.sdk.ble.api.response.Spo2DataRes;
import com.coveiot.sdk.ble.api.response.Spo2PeriodicDataRes;
import com.coveiot.sdk.ble.api.response.SportsNotificationRes;
import com.coveiot.sdk.ble.api.response.StopMessageNotificationRes;
import com.coveiot.sdk.ble.api.response.StressDataRes;
import com.coveiot.sdk.ble.api.response.TemperatureDataRes;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticHeartRateRes;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticSpo2Res;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticTemperatureRes;
import com.coveiot.sdk.ble.api.response.TodaysFitnessDataRes;
import com.coveiot.sdk.ble.api.response.TodaysStepsDataRes;
import com.coveiot.sdk.ble.api.response.Vo2MaxRes;
import com.coveiot.sdk.ble.api.response.WalkDataRes;
import com.coveiot.sdk.ble.api.response.WatchFaceChange;
import com.coveiot.sdk.ble.api.response.WatchFaceUploadFaceRes;
import com.coveiot.sdk.ble.api.response.WomenWellnessSettingsRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.BleInfoEvent;
import com.coveiot.sdk.ble.events.ProcessNextItemEvent;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseStatus;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.FirmwareCapabilityInfo;
import com.coveiot.sdk.ble.model.RawData;
import com.coveiot.sdk.ble.model.RequestPayload;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.htsmart.wristband2.WristbandManager;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
/* loaded from: classes9.dex */
public class ProtocolParser {
    public static final String g = "ProtocolParser";
    public static ProtocolParser h;

    /* renamed from: a  reason: collision with root package name */
    public String f7576a;
    public int c;
    public ArrayList<String> d;
    public Handler b = new Handler();
    public int e = -1;
    public final Charset f = StandardCharsets.UTF_8;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class a0 implements Runnable {
        public a0(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public b(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class b0 implements Runnable {
        public b0(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public c(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class c0 implements Runnable {
        public c0(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Runnable {
        public d(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class d0 implements Runnable {
        public final /* synthetic */ CommandObject h;

        public d0(ProtocolParser protocolParser, CommandObject commandObject) {
            this.h = commandObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.getResponseListener().retryCommand(this.h.getBaseRequest());
        }
    }

    /* loaded from: classes9.dex */
    public class e implements Runnable {
        public e(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class f implements Runnable {
        public f(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class g implements Runnable {
        public g(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class h implements Runnable {
        public h(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class i implements Runnable {
        public i(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class j implements Runnable {
        public j(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class k implements Runnable {
        public k(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class l implements Runnable {
        public l(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class m implements Runnable {
        public m(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class n implements Runnable {
        public n(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class o implements Runnable {
        public o(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class p implements Runnable {
        public p(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class q implements Runnable {
        public q(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class r implements Runnable {
        public r(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class s implements Runnable {
        public s(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class t implements Runnable {
        public t(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class u implements Runnable {
        public u(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class v implements Runnable {
        public v(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class w {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7577a;

        static {
            int[] iArr = new int[CommandNames.values().length];
            f7577a = iArr;
            try {
                iArr[CommandNames.GET_QUICK_REPLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7577a[CommandNames.GET_5MIN_WALK_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7577a[CommandNames.GET_15MIN_STRESS_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7577a[CommandNames.GET_5MIN_TEMPERATURE_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7577a[CommandNames.GET_MANUAL_SPO2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7577a[CommandNames.GET_RAW_PPG_DATA_HISTORY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7577a[CommandNames.GET_SEDENTARY_DATA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7577a[CommandNames.GET_RAW_ACCELEROMETER_DATA_HISTORY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7577a[CommandNames.GET_CONFIGURED_ACTIVITIES.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7577a[CommandNames.GET_ACTIVITY_SUMMARY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f7577a[CommandNames.GET_SENS_AI_SUMMARY_DATA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f7577a[CommandNames.GET_DISTANCE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f7577a[CommandNames.GET_CALORIE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f7577a[CommandNames.GET_ACTIVITY_DETAILS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f7577a[CommandNames.GET_SENS_AI_SUMMARY_DETAILS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f7577a[CommandNames.GET_SMART_ALERT_CONFIG.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f7577a[CommandNames.READ_MANUAL_BP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f7577a[CommandNames.GET_HISTORY_DATA_FOR_AUTOMATIC_HR_BP_INTERVAL.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f7577a[CommandNames.GET_HISTORY_DATA_FOR_AUTOMATIC_RR_INTERVAL.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f7577a[CommandNames.GET_5MIN_RUNNING_DATA.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f7577a[CommandNames.GET_HOURLY_WALK_DATA.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f7577a[CommandNames.GET_10MIN_SLEEP_DATA.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f7577a[CommandNames.GET_1MIN_SLEEP_DATA.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f7577a[CommandNames.GET_1MIN_UV_DATA.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f7577a[CommandNames.GET_NEARBY_DEVICE_LIST.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f7577a[CommandNames.GET_NEARBY_DEVICE_LIST_MAC.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f7577a[CommandNames.GET_PERIODIC_SPO2.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f7577a[CommandNames.GET_NAVIGATION_FAVOURITE_LOCATION.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f7577a[CommandNames.GET_QR_TRAY_CODE.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public class x implements Runnable {
        public x(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class y implements Runnable {
        public y(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    /* loaded from: classes9.dex */
    public class z implements Runnable {
        public z(ProtocolParser protocolParser) {
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
        }
    }

    public static ProtocolParser getInstance() {
        if (h == null) {
            h = new ProtocolParser();
        }
        return h;
    }

    public final String a(byte[] bArr, Charset charset) {
        if (charset.equals(this.f)) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (bArr[i2] == 0) {
                    bArr[i2] = 32;
                }
            }
        }
        return new String(bArr, charset).trim();
    }

    public final void b(Context context, CommandObject commandObject) {
        RequestPayload requestPayload;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            int size = this.d.size();
            String str2 = g;
            LogHelper.d(str2, "currentPacketSize:" + size + " packetSize:" + this.c, ModuleNames.BLEABSTRACT.getModuleName());
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) ((((float) size) / ((float) this.c)) * 100.0f), ProgressType.PERCENTAGE));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            if (this.d.size() > 0) {
                responseData.setDataList(new ArrayList<>(this.d));
                ArrayList<String> arrayList2 = this.d;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    this.d.clear();
                }
                responseData.setResponseType(ResponseType.READ_NEARBY_DEVICE_LIST);
                if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                    int day = requestPayload.getDay();
                    int startHour = requestPayload.getStartHour();
                    int endHour = requestPayload.getEndHour();
                    responseData.setDay(day);
                    responseData.setStartTime(startHour);
                    responseData.setEndTime(endHour);
                }
                String str3 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                this.f7576a = str3;
                if (BleUtils.isEmpty(str3)) {
                    return;
                }
                CommandType commandType = CommandType.GET_NEARBY_DEVICE_LIST;
                BaseResponse getNearbyDevicesMacRes = new GetNearbyDevicesMacRes(commandType, commandObject.getBaseRequest());
                if (commandObject.getBaseRequest() instanceof GetNearbyDeviceListReq) {
                    if (commandObject.getBaseRequest().getCommandName() == CommandNames.GET_NEARBY_DEVICE_LIST) {
                        getNearbyDevicesMacRes = new GetNearbyDevicesRes(commandType, commandObject.getBaseRequest());
                    } else if (commandObject.getBaseRequest().getCommandName() == CommandNames.GET_NEARBY_DEVICE_LIST_MAC) {
                        getNearbyDevicesMacRes = new GetNearbyDevicesMacRes(commandType, commandObject.getBaseRequest());
                    }
                }
                getNearbyDevicesMacRes.setResponseData(responseData);
                commandObject.setCompleted(true);
                commandObject.getResponseListener().onResponse(getNearbyDevicesMacRes);
            }
            this.b.postDelayed(new u(this), 1000L);
        }
    }

    public final void c(byte[] bArr) {
        if (this.d == null) {
            this.d = new ArrayList<>();
        }
        String arrays = Arrays.toString(bArr);
        if (bArr[0] == Byte.MAX_VALUE) {
            String[] split = arrays.substring(1, arrays.length() - 1).split(Constants.SEPARATOR_COMMA);
            if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                ArrayList<String> arrayList = new ArrayList<>();
                this.d = arrayList;
                arrayList.add(Arrays.toString(bArr));
                return;
            }
            this.d.add(Arrays.toString(bArr));
            return;
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.d = arrayList2;
        arrayList2.add(Arrays.toString(bArr));
    }

    public final boolean d(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                responseData.setDataList(new ArrayList<>(this.d));
                ArrayList<String> arrayList2 = this.d;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    this.d.clear();
                }
                responseData.setResponseType(ResponseType.FIFTEEN_MIN_STRESS_DATA);
                if (commandObject != null && commandObject.getRequestPayload() != null) {
                    RequestPayload requestPayload = commandObject.getRequestPayload();
                    if (requestPayload != null) {
                        int day = requestPayload.getDay();
                        int startHour = requestPayload.getStartHour();
                        int endHour = requestPayload.getEndHour();
                        responseData.setDay(day);
                        responseData.setStartTime(startHour);
                        responseData.setEndTime(endHour);
                    }
                    this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                }
                responseData.setMacAddress(this.f7576a);
                if (BleUtils.isEmpty(this.f7576a)) {
                    return true;
                }
                StressDataRes stressDataRes = new StressDataRes(CommandType.GET_STRESS_DATA, commandObject.getBaseRequest());
                stressDataRes.setResponseData(responseData);
                commandObject.setCompleted(true);
                commandObject.getResponseListener().onResponse(stressDataRes);
                LogHelper.d(g, "StressDataRes response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
                this.b.post(new o(this));
            }
        }
        return false;
    }

    public final boolean e(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (this.c == 1) {
                    commandObject.setCompleted(true);
                    commandObject.getResponseListener().retryCommand(commandObject.getBaseRequest());
                    this.b.postDelayed(new s(this), 200L);
                    return true;
                }
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                ArrayList<String> arrayList2 = this.d;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    responseData.setDataList(new ArrayList<>(this.d));
                    ArrayList<String> arrayList3 = this.d;
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        this.d.clear();
                    }
                    responseData.setResponseType(ResponseType.ONE_MIN_SLEEP_DATA);
                    if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                        int day = requestPayload.getDay();
                        int startHour = requestPayload.getStartHour();
                        int endHour = requestPayload.getEndHour();
                        responseData.setDay(day);
                        responseData.setStartTime(startHour);
                        responseData.setEndTime(endHour);
                    }
                    String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                    this.f7576a = str2;
                    responseData.setMacAddress(str2);
                    if (BleUtils.isEmpty(this.f7576a)) {
                        return true;
                    }
                    SleepDataRes sleepDataRes = new SleepDataRes(CommandType.SLEEP, commandObject.getBaseRequest());
                    sleepDataRes.setResponseData(responseData);
                    commandObject.setCompleted(true);
                    commandObject.getResponseListener().onResponse(sleepDataRes);
                }
                this.b.post(new t(this));
            }
        }
        return false;
    }

    public final boolean f(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                responseData.setDataList(new ArrayList<>(this.d));
                ArrayList<String> arrayList2 = this.d;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    this.d.clear();
                }
                responseData.setResponseType(ResponseType.FIVE_MIN_WALK_DATA);
                if (commandObject != null && commandObject.getRequestPayload() != null) {
                    RequestPayload requestPayload = commandObject.getRequestPayload();
                    if (requestPayload != null) {
                        int day = requestPayload.getDay();
                        int startHour = requestPayload.getStartHour();
                        int endHour = requestPayload.getEndHour();
                        responseData.setDay(day);
                        responseData.setStartTime(startHour);
                        responseData.setStrideLength(requestPayload.getStrideLength());
                        responseData.setRunStrideLength(requestPayload.getRunStrideLength());
                        responseData.setEndTime(endHour);
                    }
                    this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                }
                responseData.setMacAddress(this.f7576a);
                if (BleUtils.isEmpty(this.f7576a)) {
                    return true;
                }
                int intValue = ((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_WEIGHT, 55)).intValue();
                responseData.setHeight(((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_HEIGHT, 165)).intValue());
                responseData.setWeight(intValue);
                WalkDataRes walkDataRes = new WalkDataRes(CommandType.WALK, commandObject.getBaseRequest());
                walkDataRes.setResponseData(responseData);
                commandObject.setCompleted(true);
                commandObject.getResponseListener().onResponse(walkDataRes);
                this.b.post(new q(this));
            }
        }
        return false;
    }

    public final boolean g(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                responseData.setDataList(new ArrayList<>(this.d));
                ArrayList<String> arrayList2 = this.d;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    this.d.clear();
                }
                responseData.setResponseType(ResponseType.FIVE_MIN_TEMPERATURE_DATA);
                if (commandObject != null && commandObject.getRequestPayload() != null) {
                    RequestPayload requestPayload = commandObject.getRequestPayload();
                    if (requestPayload != null) {
                        int day = requestPayload.getDay();
                        int startHour = requestPayload.getStartHour();
                        int endHour = requestPayload.getEndHour();
                        responseData.setDay(day);
                        responseData.setStartTime(startHour);
                        responseData.setEndTime(endHour);
                    }
                    this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                }
                responseData.setMacAddress(this.f7576a);
                if (BleUtils.isEmpty(this.f7576a)) {
                    return true;
                }
                TemperatureDataRes temperatureDataRes = new TemperatureDataRes(CommandType.TEMPERATURE, commandObject.getBaseRequest());
                temperatureDataRes.setResponseData(responseData);
                commandObject.setCompleted(true);
                commandObject.getResponseListener().onResponse(temperatureDataRes);
                LogHelper.d(g, "Temperature response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
                this.b.post(new n(this));
            }
        }
        return false;
    }

    public final void h(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.GET_ACTIVITY_DETAILS);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            ActivityDetailsDataRes activityDetailsDataRes = new ActivityDetailsDataRes(CommandType.GET_ACTIVITY_DETAILS, commandObject.getBaseRequest());
            activityDetailsDataRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(activityDetailsDataRes);
            LogHelper.d(g, "GET_ACTIVITY_DETAILS response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new e(this));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void handleDeviceInput(Context context, CommandObject commandObject, byte[] bArr, char[] cArr, LinkedList<CommandObject> linkedList) {
        NotifyNavigationEventRes notifyNavigationEventRes;
        FindMyPhoneRes findMyPhoneRes;
        RequestPayload requestPayload;
        RequestPayload requestPayload2;
        RequestPayload requestPayload3;
        BaseResponse setWeatherConfigInfoRes;
        if (!this.b.getLooper().getThread().isAlive()) {
            this.b = new Handler(Looper.getMainLooper());
        }
        try {
            if (new String(cArr).equalsIgnoreCase(BleUUID.UART_READ_CHARATERISTICS_UUID)) {
                String arrays = Arrays.toString(bArr);
                if (bArr[0] == Byte.MIN_VALUE) {
                    BleDeviceInfo bleDeviceInfo = BleDeviceInfo.getInstance();
                    byte b2 = bArr[1];
                    if (b2 == -112) {
                        String str = g;
                        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str, "Set Prob CMD_ID_90 response in Protocol Parser!!!", moduleNames.getModuleName());
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SaveProbeReq)) {
                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add(Arrays.toString(bArr));
                            SaveProbeRes saveProbeRes = new SaveProbeRes(CommandType.SET_PROBE, commandObject.getBaseRequest());
                            ResponseData responseData = new ResponseData();
                            responseData.setDataList(arrayList);
                            saveProbeRes.setResponseData(responseData);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(saveProbeRes);
                            LogHelper.d(str, "Set Prob CMD_ID_90 response send from parser to impl!!!", moduleNames.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b2 == -111) {
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof PairingFlowCmdReq)) {
                            ArrayList<String> arrayList2 = new ArrayList<>();
                            arrayList2.add(Arrays.toString(bArr));
                            PairingFlowCmdRes pairingFlowCmdRes = new PairingFlowCmdRes(CommandType.PAIRING_FLOW, commandObject.getBaseRequest());
                            ResponseData responseData2 = new ResponseData();
                            responseData2.setDataList(arrayList2);
                            pairingFlowCmdRes.setResponseData(responseData2);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(pairingFlowCmdRes);
                            LogHelper.d(g, "PairingFlowCmdRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b2 == 13) {
                        if (commandObject != null) {
                            GetLiftWristViewRes getLiftWristViewRes = new GetLiftWristViewRes(CommandType.GET_LIFT_WRIST, commandObject.getBaseRequest());
                            ArrayList<String> arrayList3 = new ArrayList<>();
                            arrayList3.add(Arrays.toString(bArr));
                            ResponseData responseData3 = new ResponseData();
                            responseData3.setDataList(arrayList3);
                            getLiftWristViewRes.setResponseData(responseData3);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getLiftWristViewRes);
                        }
                    } else {
                        switch (b2) {
                            case -127:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SaveFitnessProfileReq)) {
                                    if (bArr[4] == 1) {
                                        SaveFitnessProfileReq saveFitnessProfileReq = (SaveFitnessProfileReq) commandObject.getBaseRequest();
                                        BlePreferenceManager.savePreference(context, CommonPreference.USER_WEIGHT, Integer.valueOf(saveFitnessProfileReq.getWeight()));
                                        BlePreferenceManager.savePreference(context, CommonPreference.USER_HEIGHT, Integer.valueOf(saveFitnessProfileReq.getHeight()));
                                        ArrayList<String> arrayList4 = new ArrayList<>();
                                        arrayList4.add(Arrays.toString(bArr));
                                        SaveFitnessProfileRes saveFitnessProfileRes = new SaveFitnessProfileRes(CommandType.SAVE_HEIGHT_WEIGHT, commandObject.getBaseRequest());
                                        ResponseData responseData4 = new ResponseData();
                                        responseData4.setDataList(arrayList4);
                                        saveFitnessProfileRes.setResponseData(responseData4);
                                        commandObject.setCompleted(true);
                                        commandObject.getResponseListener().onResponse(saveFitnessProfileRes);
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    }
                                    return;
                                } else if (commandObject != null && (commandObject.getBaseRequest() instanceof SaveFitnessProfileReqOld) && bArr[4] == 1) {
                                    SaveFitnessProfileReqOld saveFitnessProfileReqOld = (SaveFitnessProfileReqOld) commandObject.getBaseRequest();
                                    BlePreferenceManager.savePreference(context, CommonPreference.USER_WEIGHT, Integer.valueOf(saveFitnessProfileReqOld.getWeight()));
                                    BlePreferenceManager.savePreference(context, CommonPreference.USER_HEIGHT, Integer.valueOf(saveFitnessProfileReqOld.getHeight()));
                                    ArrayList<String> arrayList5 = new ArrayList<>();
                                    arrayList5.add(Arrays.toString(bArr));
                                    SaveFitnessProfileRes saveFitnessProfileRes2 = new SaveFitnessProfileRes(CommandType.SAVE_HEIGHT_WEIGHT, commandObject.getBaseRequest());
                                    ResponseData responseData5 = new ResponseData();
                                    responseData5.setDataList(arrayList5);
                                    saveFitnessProfileRes2.setResponseData(responseData5);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(saveFitnessProfileRes2);
                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                    return;
                                } else {
                                    return;
                                }
                            case -126:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetTimeFormatReq)) {
                                    ArrayList<String> arrayList6 = new ArrayList<>();
                                    arrayList6.add(Arrays.toString(bArr));
                                    SetTimeFormatRes setTimeFormatRes = new SetTimeFormatRes(CommandType.HOUR_FORMAT, commandObject.getBaseRequest());
                                    ResponseData responseData6 = new ResponseData();
                                    responseData6.setDataList(arrayList6);
                                    setTimeFormatRes.setResponseData(responseData6);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setTimeFormatRes);
                                    LogHelper.d(g, "SSetTimeFormatRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -125:
                                byte b3 = bArr[4];
                                if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -124:
                                byte b4 = bArr[4];
                                if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -123:
                                if (bArr[4] != 1 && bArr[4] != 0) {
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_DEVICE_MODE, ResponseStatus.RESPONSE_STATUS_FAILURE));
                                    return;
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_DEVICE_MODE, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                return;
                            case -122:
                                if (bArr[4] == 1) {
                                    if (commandObject != null) {
                                        commandObject.setCompleted(true);
                                        if (commandObject.getResponseListener() != null) {
                                            commandObject.getResponseListener().onResponse(new BaseResponse(CommandType.SET_PAIRING_PHONE_TYPE, commandObject.getBaseRequest()));
                                        }
                                    }
                                } else if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                    if (commandObject.getResponseListener() != null) {
                                        commandObject.getResponseListener().onFailure(new Error(Type.COMMAND_REQUEST_ERROR, "Command failed"));
                                    }
                                }
                                if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -121:
                                if (bArr[4] == 1) {
                                    if (commandObject != null) {
                                        commandObject.setCompleted(true);
                                        if (commandObject.getResponseListener() != null) {
                                            commandObject.getResponseListener().onResponse(new BaseResponse(CommandType.SET_DEVICE_TIME, commandObject.getBaseRequest()));
                                        }
                                    }
                                } else if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                    if (commandObject.getResponseListener() != null) {
                                        commandObject.getResponseListener().onFailure(new Error(Type.COMMAND_REQUEST_ERROR, "Command failed"));
                                    }
                                }
                                if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -120:
                                byte b5 = bArr[4];
                                return;
                            case -119:
                                byte b6 = bArr[4];
                                return;
                            case -118:
                                byte b7 = bArr[4];
                                return;
                            case -117:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof BandDisplaySettingsReq)) {
                                    ArrayList<String> arrayList7 = new ArrayList<>();
                                    arrayList7.add(Arrays.toString(bArr));
                                    BandDisplaySettingsRes bandDisplaySettingsRes = new BandDisplaySettingsRes(CommandType.SET_BAND_DISPLAY_SETTINGS, commandObject.getBaseRequest());
                                    ResponseData responseData7 = new ResponseData();
                                    responseData7.setDataList(arrayList7);
                                    bandDisplaySettingsRes.setResponseData(responseData7);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(bandDisplaySettingsRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -116:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof ChangeWearingHandReq)) {
                                    ArrayList<String> arrayList8 = new ArrayList<>();
                                    arrayList8.add(Arrays.toString(bArr));
                                    ChangeWearingHandRes changeWearingHandRes = new ChangeWearingHandRes(CommandType.SET_HAND_PREFERENCE, commandObject.getBaseRequest());
                                    ResponseData responseData8 = new ResponseData();
                                    responseData8.setDataList(arrayList8);
                                    changeWearingHandRes.setResponseData(responseData8);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(changeWearingHandRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -115:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof LiftWristViewReq)) {
                                    ArrayList<String> arrayList9 = new ArrayList<>();
                                    arrayList9.add(Arrays.toString(bArr));
                                    LiftWristViewRes liftWristViewRes = new LiftWristViewRes(CommandType.SET_LIFT_WRIST, commandObject.getBaseRequest());
                                    ResponseData responseData9 = new ResponseData();
                                    responseData9.setDataList(arrayList9);
                                    liftWristViewRes.setResponseData(responseData9);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(liftWristViewRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            default:
                                switch (b2) {
                                    case -96:
                                        String str2 = g;
                                        ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
                                        LogHelper.d(str2, "Set Temperature Unit CMD_ID_A0 response in Protocol Parser!!!", moduleNames2.getModuleName());
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof ChangeTemperatureUnitReq)) {
                                            ArrayList<String> arrayList10 = new ArrayList<>();
                                            arrayList10.add(Arrays.toString(bArr));
                                            ChangeTemperatureUnitRes changeTemperatureUnitRes = new ChangeTemperatureUnitRes(CommandType.SET_TEMPERATURE_UNIT, commandObject.getBaseRequest());
                                            ResponseData responseData10 = new ResponseData();
                                            responseData10.setDataList(arrayList10);
                                            changeTemperatureUnitRes.setResponseData(responseData10);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(changeTemperatureUnitRes);
                                            LogHelper.d(str2, "Set Prob CMD_ID_90 response send from parser to impl!!!", moduleNames2.getModuleName());
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -95:
                                        String str3 = g;
                                        ModuleNames moduleNames3 = ModuleNames.BLEABSTRACT;
                                        LogHelper.d(str3, "Set Prob CMD_ID_90 response in Protocol Parser!!!", moduleNames3.getModuleName());
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof TimeIntervalForAutomaticTemperatureReq)) {
                                            ArrayList<String> arrayList11 = new ArrayList<>();
                                            arrayList11.add(Arrays.toString(bArr));
                                            TimeIntervalForAutomaticTemperatureRes timeIntervalForAutomaticTemperatureRes = new TimeIntervalForAutomaticTemperatureRes(CommandType.TIME_INTERVAL_FOR_AUTOMATIC_TEMPERATURE, commandObject.getBaseRequest());
                                            ResponseData responseData11 = new ResponseData();
                                            responseData11.setDataList(arrayList11);
                                            timeIntervalForAutomaticTemperatureRes.setResponseData(responseData11);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(timeIntervalForAutomaticTemperatureRes);
                                            LogHelper.d(str3, "Set Prob CMD_ID_90 response send from parser to impl!!!", moduleNames3.getModuleName());
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -94:
                                        String str4 = g;
                                        ModuleNames moduleNames4 = ModuleNames.BLEABSTRACT;
                                        LogHelper.d(str4, "Set Temperature Unit CMD_ID_A0 response in Protocol Parser!!!", moduleNames4.getModuleName());
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof ChangeDistanceUnitReq)) {
                                            ArrayList<String> arrayList12 = new ArrayList<>();
                                            arrayList12.add(Arrays.toString(bArr));
                                            ChangeDistanceUnitRes changeDistanceUnitRes = new ChangeDistanceUnitRes(CommandType.SET_DISTANCE_UNIT, commandObject.getBaseRequest());
                                            ResponseData responseData12 = new ResponseData();
                                            responseData12.setDataList(arrayList12);
                                            changeDistanceUnitRes.setResponseData(responseData12);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(changeDistanceUnitRes);
                                            LogHelper.d(str4, "Set Prob CMD_ID_90 response send from parser to impl!!!", moduleNames4.getModuleName());
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -93:
                                        String str5 = g;
                                        ModuleNames moduleNames5 = ModuleNames.BLEABSTRACT;
                                        LogHelper.d(str5, "Set Prob CMD_ID_A3 response in Protocol Parser!!!", moduleNames5.getModuleName());
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof TimeIntervalForAutomaticSpo2Req)) {
                                            ArrayList<String> arrayList13 = new ArrayList<>();
                                            arrayList13.add(Arrays.toString(bArr));
                                            TimeIntervalForAutomaticSpo2Res timeIntervalForAutomaticSpo2Res = new TimeIntervalForAutomaticSpo2Res(CommandType.TIME_INTERVAL_FOR_AUTOMATIC_SPO2, commandObject.getBaseRequest());
                                            ResponseData responseData13 = new ResponseData();
                                            responseData13.setDataList(arrayList13);
                                            timeIntervalForAutomaticSpo2Res.setResponseData(responseData13);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(timeIntervalForAutomaticSpo2Res);
                                            LogHelper.d(str5, "Set Prob CMD_ID_90 response send from parser to impl!!!", moduleNames5.getModuleName());
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        break;
                                    default:
                                        switch (b2) {
                                            case -89:
                                                ArrayList<String> arrayList14 = new ArrayList<>();
                                                arrayList14.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetMusicVolumePercentageReq)) {
                                                    MusicStatusNotificationRes musicStatusNotificationRes = new MusicStatusNotificationRes(CommandType.SET_MUSIC_VOLUME_PERCENTAGE, commandObject.getBaseRequest());
                                                    ResponseData responseData14 = new ResponseData();
                                                    responseData14.setDataList(arrayList14);
                                                    musicStatusNotificationRes.setResponseData(responseData14);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(musicStatusNotificationRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -88:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetPhoneBookReq)) {
                                                    ArrayList<String> arrayList15 = new ArrayList<>();
                                                    arrayList15.add(Arrays.toString(bArr));
                                                    SetPhoneBookRes setPhoneBookRes = new SetPhoneBookRes(CommandType.SET_PHONE_BOOK, commandObject.getBaseRequest());
                                                    ResponseData responseData15 = new ResponseData();
                                                    responseData15.setDataList(arrayList15);
                                                    setPhoneBookRes.setResponseData(responseData15);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setPhoneBookRes);
                                                    LogHelper.d(g, "SetPhoneBookRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -87:
                                                if (commandObject != null && ((commandObject.getBaseRequest() instanceof SetWeatherTemperatureUnitReq) || (commandObject.getBaseRequest() instanceof SetWeatherConfigInfoReq))) {
                                                    if (commandObject.getBaseRequest() instanceof SetWeatherTemperatureUnitReq) {
                                                        setWeatherConfigInfoRes = new SetWeatherTemperatureUnitRes(CommandType.SET_WEATHER_UNIT, commandObject.getBaseRequest());
                                                    } else {
                                                        setWeatherConfigInfoRes = commandObject.getBaseRequest() instanceof SetWeatherConfigInfoReq ? new SetWeatherConfigInfoRes(CommandType.SET_WEATHER_CONFIG_INFO, commandObject.getBaseRequest()) : null;
                                                    }
                                                    ArrayList<String> arrayList16 = new ArrayList<>();
                                                    arrayList16.add(Arrays.toString(bArr));
                                                    ResponseData responseData16 = new ResponseData();
                                                    responseData16.setDataList(arrayList16);
                                                    setWeatherConfigInfoRes.setResponseData(responseData16);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setWeatherConfigInfoRes);
                                                    LogHelper.d(g, "SetWeatherTemperatureUnitRes or SetWeatherConfigInfoRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -86:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof BTCallControlReq)) {
                                                    ArrayList<String> arrayList17 = new ArrayList<>();
                                                    arrayList17.add(Arrays.toString(bArr));
                                                    BTCallControlRes bTCallControlRes = new BTCallControlRes(CommandType.BT_CALL_CONTROL, commandObject.getBaseRequest());
                                                    ResponseData responseData17 = new ResponseData();
                                                    responseData17.setDataList(arrayList17);
                                                    bTCallControlRes.setResponseData(responseData17);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(bTCallControlRes);
                                                    LogHelper.d(g, "BTCallControlRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -85:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SendUnbindBTCallReq)) {
                                                    ArrayList<String> arrayList18 = new ArrayList<>();
                                                    arrayList18.add(Arrays.toString(bArr));
                                                    SendUnbindBTCallRes sendUnbindBTCallRes = new SendUnbindBTCallRes(CommandType.SEND_UNBIND_BT_CALL, commandObject.getBaseRequest());
                                                    ResponseData responseData18 = new ResponseData();
                                                    responseData18.setDataList(arrayList18);
                                                    sendUnbindBTCallRes.setResponseData(responseData18);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(sendUnbindBTCallRes);
                                                    LogHelper.d(g, "SendUnbindBTCallRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -84:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SendBTCallInfoReq)) {
                                                    ArrayList<String> arrayList19 = new ArrayList<>();
                                                    arrayList19.add(Arrays.toString(bArr));
                                                    SendBTCallInfoRes sendBTCallInfoRes = new SendBTCallInfoRes(CommandType.SEND_BT_CALL_INFO, commandObject.getBaseRequest());
                                                    ResponseData responseData19 = new ResponseData();
                                                    responseData19.setDataList(arrayList19);
                                                    sendBTCallInfoRes.setResponseData(responseData19);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(sendBTCallInfoRes);
                                                    LogHelper.d(g, "SendBTCallInfoRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            default:
                                                switch (b2) {
                                                    case -78:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNavigationDisclaimerReq)) {
                                                            ArrayList<String> arrayList20 = new ArrayList<>();
                                                            arrayList20.add(Arrays.toString(bArr));
                                                            SetNavigationDisclaimerRes setNavigationDisclaimerRes = new SetNavigationDisclaimerRes(CommandType.SET_NAVIGATION_DISCLAIMER, commandObject.getBaseRequest());
                                                            ResponseData responseData20 = new ResponseData();
                                                            responseData20.setDataList(arrayList20);
                                                            setNavigationDisclaimerRes.setResponseData(responseData20);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setNavigationDisclaimerRes);
                                                            LogHelper.d(g, "Set Navigation Disclaimer response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof GetNavigationDisclaimerReq)) {
                                                            ArrayList<String> arrayList21 = new ArrayList<>();
                                                            arrayList21.add(Arrays.toString(bArr));
                                                            ResponseData responseData21 = new ResponseData();
                                                            responseData21.setDataList(arrayList21);
                                                            GetNavigationDisclaimerRes getNavigationDisclaimerRes = new GetNavigationDisclaimerRes(CommandType.GET_NAVIGATION_DISCLAIMER_INFO, commandObject.getBaseRequest());
                                                            getNavigationDisclaimerRes.setResponseData(responseData21);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(getNavigationDisclaimerRes);
                                                            LogHelper.d(g, "Get Navigation Disclaimer info response send from parser to impl!!! " + arrayList21.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null) {
                                                            LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else {
                                                            LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -77:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNavigationAliveTimerReq)) {
                                                            ArrayList<String> arrayList22 = new ArrayList<>();
                                                            arrayList22.add(Arrays.toString(bArr));
                                                            SetNavigationAliveTimerRes setNavigationAliveTimerRes = new SetNavigationAliveTimerRes(CommandType.SET_NAVIGATION_ALIVE_TIMER, commandObject.getBaseRequest());
                                                            ResponseData responseData22 = new ResponseData();
                                                            responseData22.setDataList(arrayList22);
                                                            setNavigationAliveTimerRes.setResponseData(responseData22);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setNavigationAliveTimerRes);
                                                            LogHelper.d(g, "Set Navigation alive timer response send from parser to impl!!! ", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -76:
                                                        break;
                                                    case -75:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNavigationConfigurationReq)) {
                                                            ArrayList<String> arrayList23 = new ArrayList<>();
                                                            arrayList23.add(Arrays.toString(bArr));
                                                            SetNavigationConfigurationRes setNavigationConfigurationRes = new SetNavigationConfigurationRes(CommandType.SET_NAVIGATION_CONFIGURATION, commandObject.getBaseRequest());
                                                            ResponseData responseData23 = new ResponseData();
                                                            responseData23.setDataList(arrayList23);
                                                            setNavigationConfigurationRes.setResponseData(responseData23);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setNavigationConfigurationRes);
                                                            LogHelper.d(g, "Set Navigation configuration response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof GetNavigationConfigurationReq)) {
                                                            ArrayList<String> arrayList24 = new ArrayList<>();
                                                            arrayList24.add(Arrays.toString(bArr));
                                                            ResponseData responseData24 = new ResponseData();
                                                            responseData24.setDataList(arrayList24);
                                                            GetNavigationConfigurationRes getNavigationConfigurationRes = new GetNavigationConfigurationRes(CommandType.GET_NAVIGATION_CONFIGURATION, commandObject.getBaseRequest());
                                                            getNavigationConfigurationRes.setResponseData(responseData24);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(getNavigationConfigurationRes);
                                                            LogHelper.d(g, "Get Navigation configuration response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null) {
                                                            LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else {
                                                            LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -74:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNavigationFavouriteLocationReq)) {
                                                            ArrayList<String> arrayList25 = new ArrayList<>();
                                                            arrayList25.add(Arrays.toString(bArr));
                                                            SetNavigationFavouriteLocationRes setNavigationFavouriteLocationRes = new SetNavigationFavouriteLocationRes(CommandType.SET_NAVIGATION_FAVOURITE_LOCATION, commandObject.getBaseRequest());
                                                            ResponseData responseData25 = new ResponseData();
                                                            responseData25.setDataList(arrayList25);
                                                            setNavigationFavouriteLocationRes.setResponseData(responseData25);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setNavigationFavouriteLocationRes);
                                                            LogHelper.d(g, "Set Navigation favourite location response send from parser to impl!!! ", ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof GetNavigationFavouriteLocationReq)) {
                                                            c(bArr);
                                                            p(context, commandObject, bArr);
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -73:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetSOSConfigReq)) {
                                                            ArrayList<String> arrayList26 = new ArrayList<>();
                                                            arrayList26.add(Arrays.toString(bArr));
                                                            SetSOSConfigRes setSOSConfigRes = new SetSOSConfigRes(CommandType.SET_SOS_CONFIG, commandObject.getBaseRequest());
                                                            ResponseData responseData26 = new ResponseData();
                                                            responseData26.setDataList(arrayList26);
                                                            setSOSConfigRes.setResponseData(responseData26);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setSOSConfigRes);
                                                            LogHelper.d(g, "Set SOS config response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof GetSOSConfigReq)) {
                                                            ArrayList<String> arrayList27 = new ArrayList<>();
                                                            arrayList27.add(Arrays.toString(bArr));
                                                            ResponseData responseData27 = new ResponseData();
                                                            responseData27.setDataList(arrayList27);
                                                            GetSOSConfigRes getSOSConfigRes = new GetSOSConfigRes(CommandType.GET_SOS_CONFIG, commandObject.getBaseRequest());
                                                            getSOSConfigRes.setResponseData(responseData27);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(getSOSConfigRes);
                                                            LogHelper.d(g, "Get SOS config response send from parser to impl!!! " + arrayList27.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null) {
                                                            LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else {
                                                            LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -72:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetSOSRecordsReq)) {
                                                            ArrayList<String> arrayList28 = new ArrayList<>();
                                                            arrayList28.add(Arrays.toString(bArr));
                                                            GetSOSRecordsRes getSOSRecordsRes = new GetSOSRecordsRes(CommandType.GET_SOS_RECORDS, commandObject.getBaseRequest());
                                                            ResponseData responseData28 = new ResponseData();
                                                            responseData28.setDataList(arrayList28);
                                                            getSOSRecordsRes.setResponseData(responseData28);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(getSOSRecordsRes);
                                                            LogHelper.d(g, "Get SOS records response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    default:
                                                        switch (b2) {
                                                            case 0:
                                                                arrays.substring(4, arrays.length() - 1);
                                                                return;
                                                            case 1:
                                                                bleDeviceInfo.setHwRevision(arrays.substring(4, arrays.length() - 1));
                                                                BleEventBusManager.getInstance().getEventBus().post(new BleInfoEvent());
                                                                return;
                                                            case 2:
                                                                bleDeviceInfo.setFirmwareRevision(arrays.substring(4, arrays.length() - 1), context);
                                                                BleEventBusManager.getInstance().getEventBus().post(new BleInfoEvent());
                                                                return;
                                                            case 3:
                                                                arrays.substring(4, arrays.length() - 1);
                                                                return;
                                                            case 4:
                                                                arrays.substring(4, arrays.length() - 1);
                                                                return;
                                                            case 5:
                                                                arrays.substring(4, arrays.length() - 1);
                                                                return;
                                                            case 6:
                                                                arrays.substring(4, arrays.length() - 1);
                                                                if (commandObject == null || commandObject.getCmdName() != CommandNames.GET_DEVICE_TIME) {
                                                                    return;
                                                                }
                                                                GetTimeRes getTimeRes = new GetTimeRes(CommandType.GET_TIME, commandObject.getBaseRequest());
                                                                ArrayList<String> arrayList29 = new ArrayList<>();
                                                                arrayList29.add(Arrays.toString(bArr));
                                                                ResponseData responseData29 = new ResponseData();
                                                                responseData29.setDataList(arrayList29);
                                                                getTimeRes.setResponseData(responseData29);
                                                                commandObject.setCompleted(true);
                                                                commandObject.getResponseListener().onResponse(getTimeRes);
                                                                return;
                                                            case 7:
                                                                arrays.substring(4, arrays.length() - 1);
                                                                return;
                                                            case 8:
                                                                byte b8 = bArr[4];
                                                                ReadBatteryLevelRes readBatteryLevelRes = new ReadBatteryLevelRes(CommandType.WALK, commandObject.getBaseRequest());
                                                                readBatteryLevelRes.setBatteryLevel(b8);
                                                                commandObject.getResponseListener().onResponse(readBatteryLevelRes);
                                                                return;
                                                            case 9:
                                                                arrays.substring(4, arrays.length() - 1);
                                                                return;
                                                            default:
                                                                return;
                                                        }
                                                }
                                        }
                                }
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNavigationStatusReq)) {
                                    ArrayList<String> arrayList30 = new ArrayList<>();
                                    arrayList30.add(Arrays.toString(bArr));
                                    SetNavigationStatusRes setNavigationStatusRes = new SetNavigationStatusRes(CommandType.SET_NAVIGATION_STATUS, commandObject.getBaseRequest());
                                    ResponseData responseData30 = new ResponseData();
                                    responseData30.setDataList(arrayList30);
                                    setNavigationStatusRes.setResponseData(responseData30);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setNavigationStatusRes);
                                    LogHelper.d(g, "Set Navigation Status response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                        }
                    }
                } else if (bArr[0] == -127) {
                    byte b9 = bArr[1];
                    if (b9 == -118) {
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof BloodPressureMeasurementReq)) {
                            ArrayList<String> arrayList31 = new ArrayList<>();
                            arrayList31.add(Arrays.toString(bArr));
                            BloodPressureMeasurementRes bloodPressureMeasurementRes = new BloodPressureMeasurementRes(CommandType.BLOOD_PRESSURE_CONTROL, commandObject.getBaseRequest());
                            ResponseData responseData31 = new ResponseData();
                            responseData31.setDataList(arrayList31);
                            bloodPressureMeasurementRes.setResponseData(responseData31);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(bloodPressureMeasurementRes);
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b9 == -117) {
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof CurrentSportModeReq)) {
                            ArrayList<String> arrayList32 = new ArrayList<>();
                            arrayList32.add(Arrays.toString(bArr));
                            CurrentSportModesRes currentSportModesRes = new CurrentSportModesRes(CommandType.CURRENT_SPORT_MODE, commandObject.getBaseRequest());
                            ResponseData responseData32 = new ResponseData();
                            responseData32.setDataList(arrayList32);
                            currentSportModesRes.setResponseData(responseData32);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(currentSportModesRes);
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b9 == -105) {
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof ActivityPauseResumetReq)) {
                            ArrayList<String> arrayList33 = new ArrayList<>();
                            arrayList33.add(Arrays.toString(bArr));
                            ActivityPauseResumeRes activityPauseResumeRes = new ActivityPauseResumeRes(CommandType.ACTIVITY_PAUSE, commandObject.getBaseRequest());
                            ResponseData responseData33 = new ResponseData();
                            responseData33.setDataList(arrayList33);
                            activityPauseResumeRes.setResponseData(responseData33);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(activityPauseResumeRes);
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b9 == -104) {
                        String str6 = g;
                        ModuleNames moduleNames6 = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str6, "BP Calibration in Protocol Parser!!!", moduleNames6.getModuleName());
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SaveBpCalibrationReq)) {
                            ArrayList<String> arrayList34 = new ArrayList<>();
                            arrayList34.add(Arrays.toString(bArr));
                            SaveBpCalibrationRes saveBpCalibrationRes = new SaveBpCalibrationRes(CommandType.SET_BP_CALIBRATION, commandObject.getBaseRequest());
                            ResponseData responseData34 = new ResponseData();
                            responseData34.setDataList(arrayList34);
                            saveBpCalibrationRes.setResponseData(responseData34);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(saveBpCalibrationRes);
                            LogHelper.d(str6, "BP Calibration response send from parser to impl!!!", moduleNames6.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b9 == 26) {
                        if ((bArr[2] == 4 && bArr[3] == 0) || (bArr[2] == 5 && bArr[3] == 0)) {
                            ResponseData responseData35 = new ResponseData();
                            responseData35.setDataList(new ArrayList<>());
                            GetNearbyDevicesRes getNearbyDevicesRes = new GetNearbyDevicesRes(CommandType.GET_NEARBY_DEVICE_LIST, commandObject.getBaseRequest());
                            responseData35.setResponseType(ResponseType.READ_NEARBY_DEVICE_LIST);
                            getNearbyDevicesRes.setResponseData(responseData35);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getNearbyDevicesRes);
                            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                            return;
                        }
                        c(bArr);
                        b(context, commandObject);
                    } else if (b9 == 27) {
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof TemperatureMeasurementReq)) {
                            commandObject.setCompleted(true);
                            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                        }
                        ResponseData responseData36 = new ResponseData();
                        ArrayList<String> arrayList35 = new ArrayList<>();
                        arrayList35.add(Arrays.toString(bArr));
                        responseData36.setDataList(arrayList35);
                        LiveTemperatureRes liveTemperatureRes = new LiveTemperatureRes(CommandType.LIVE_TEMPERATURE, null);
                        ResponseType responseType = ResponseType.GET_LIVE_TEMPERATURE;
                        responseData36.setResponseType(responseType);
                        liveTemperatureRes.setResponseData(responseData36);
                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(responseType, liveTemperatureRes, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                    } else if (b9 == 32) {
                        c(bArr);
                        v(context, commandObject, bArr);
                    } else if (b9 == 33) {
                        c(bArr);
                        k(context, commandObject, bArr);
                    } else if (b9 == 35) {
                        if (commandObject == null || !(commandObject.getBaseRequest() instanceof GetActivitySummaryReq)) {
                            return;
                        }
                        c(bArr);
                        i(context, commandObject, bArr);
                    } else if (b9 != 36) {
                        switch (b9) {
                            case WristbandManager.SYNC_STATE_FAILED_UNKNOWN /* -128 */:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetWalkTargetReq)) {
                                    ArrayList<String> arrayList36 = new ArrayList<>();
                                    arrayList36.add(Arrays.toString(bArr));
                                    SetWalkTargetRes setWalkTargetRes = new SetWalkTargetRes(CommandType.SET_WALK_TARGET, commandObject.getBaseRequest());
                                    ResponseData responseData37 = new ResponseData();
                                    responseData37.setDataList(arrayList36);
                                    setWalkTargetRes.setResponseData(responseData37);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setWalkTargetRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -127:
                                if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -126:
                                if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -125:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof TimeIntervalForAutomaticHeartRateReq)) {
                                    commandObject.setCompleted(true);
                                    ArrayList<String> arrayList37 = new ArrayList<>();
                                    arrayList37.add(Arrays.toString(bArr));
                                    TimeIntervalForAutomaticHeartRateRes timeIntervalForAutomaticHeartRateRes = new TimeIntervalForAutomaticHeartRateRes(CommandType.TIME_INTERVAL_FOR_AUTOMATIC_HEART_RATE, commandObject.getBaseRequest());
                                    ResponseData responseData38 = new ResponseData();
                                    responseData38.setDataList(arrayList37);
                                    timeIntervalForAutomaticHeartRateRes.setResponseData(responseData38);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(timeIntervalForAutomaticHeartRateRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -124:
                            case -123:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof HeartRateMeasurementReq)) {
                                    ArrayList<String> arrayList38 = new ArrayList<>();
                                    arrayList38.add(Arrays.toString(bArr));
                                    HeartRateMeasurementRes heartRateMeasurementRes = new HeartRateMeasurementRes(CommandType.HEART_RATE_CONTROL, commandObject.getBaseRequest());
                                    ResponseData responseData39 = new ResponseData();
                                    responseData39.setDataList(arrayList38);
                                    heartRateMeasurementRes.setResponseData(responseData39);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(heartRateMeasurementRes);
                                    LogHelper.d(g, "CURRENT COMMAND IS Not NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                } else {
                                    LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -122:
                                if (commandObject != null) {
                                    commandObject.setCompleted(true);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -120:
                                byte b10 = bArr[4];
                                return;
                            case -102:
                                ArrayList<String> arrayList39 = new ArrayList<>();
                                arrayList39.add(Arrays.toString(bArr));
                                DeleteNearbyDevicesRes deleteNearbyDevicesRes = new DeleteNearbyDevicesRes(CommandType.DELETE_NEARBY_DEVICE_LIST, commandObject.getBaseRequest());
                                ResponseData responseData40 = new ResponseData();
                                responseData40.setDataList(arrayList39);
                                deleteNearbyDevicesRes.setResponseData(responseData40);
                                commandObject.setCompleted(true);
                                commandObject.getResponseListener().onResponse(deleteNearbyDevicesRes);
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -98:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetAutomaticPPGConfigReq)) {
                                    SetAutomaticPPGConfigRes setAutomaticPPGConfigRes = new SetAutomaticPPGConfigRes(CommandType.SET_AUTOMATIC_PPG_CONFIG, commandObject.getBaseRequest());
                                    ArrayList<String> arrayList40 = new ArrayList<>();
                                    arrayList40.add(Arrays.toString(bArr));
                                    ResponseData responseData41 = new ResponseData();
                                    responseData41.setDataList(arrayList40);
                                    setAutomaticPPGConfigRes.setResponseData(responseData41);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setAutomaticPPGConfigRes);
                                    LogHelper.d(g, "SetAutomaticPPGConfigRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -95:
                                if (commandObject == null || commandObject.getResponseListener() == null || !(commandObject.getBaseRequest() instanceof ConfigureActivityListReq)) {
                                    return;
                                }
                                if (bArr[bArr.length - 1] == 1) {
                                    if (linkedList.size() > 0) {
                                        commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) (((commandObject.getBaseRequest().getCommandBytes().size() - linkedList.size()) / commandObject.getBaseRequest().getCommandBytes().size()) * 100.0f), ProgressType.PERCENTAGE));
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    }
                                    ArrayList<String> arrayList41 = new ArrayList<>();
                                    arrayList41.clear();
                                    arrayList41.add(Arrays.toString(bArr));
                                    ConfigureActivityListRes configureActivityListRes = new ConfigureActivityListRes(CommandType.CONFIGURE_ACTIVITIES, commandObject.getBaseRequest());
                                    ResponseData responseData42 = new ResponseData();
                                    responseData42.setDataList(arrayList41);
                                    configureActivityListRes.setResponseData(responseData42);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
                                    commandObject.getResponseListener().onResponse(configureActivityListRes);
                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                    return;
                                } else if (bArr[bArr.length - 1] == 0) {
                                    commandObject.getResponseListener().onFailure(new Error(Type.WATCH_BUSY, "Watch is busy, Please try again"));
                                    return;
                                } else {
                                    commandObject.getResponseListener().onFailure(new Error(Type.WATCH_RESPONSE_FAILED, "Watch is busy, Please try again"));
                                    return;
                                }
                            case -81:
                                break;
                            case 30:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof ReadRawPPGDataReq) && bArr[4] != 1) {
                                    commandObject.getResponseListener().onResponse(new ReadRawPPGDataRes(CommandType.READ_RAW_PPG_HISTORY, commandObject.getBaseRequest()));
                                    commandObject.setCompleted(true);
                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                    return;
                                }
                                return;
                            case 38:
                                c(bArr);
                                q(context, commandObject, bArr);
                                return;
                            case 42:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof GetSensAIActivityConfigReq)) {
                                    String arrays2 = Arrays.toString(bArr);
                                    LogHelper.d(g + "Get SensAI Activity Config Resp is +++ ", arrays2, ModuleNames.BLEABSTRACT.getModuleName());
                                    ArrayList<String> arrayList42 = new ArrayList<>();
                                    arrayList42.add(arrays2);
                                    ResponseData responseData43 = new ResponseData();
                                    responseData43.setDataList(arrayList42);
                                    GetSensAIActivityConfigRes getSensAIActivityConfigRes = new GetSensAIActivityConfigRes(CommandType.GET_SENS_AI_ACTIVITY_CONFIG, commandObject.getBaseRequest());
                                    getSensAIActivityConfigRes.setResponseData(responseData43);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(getSensAIActivityConfigRes);
                                } else if (commandObject != null) {
                                    LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                } else {
                                    LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case 43:
                                if (commandObject == null || !(commandObject.getBaseRequest() instanceof GetSensAISummaryDataReq)) {
                                    return;
                                }
                                c(bArr);
                                w(context, commandObject, bArr);
                                return;
                            case 44:
                                c(bArr);
                                x(context, commandObject, bArr);
                                return;
                            case 45:
                                if (commandObject == null || !(commandObject.getBaseRequest() instanceof GetDistanceDataReq)) {
                                    return;
                                }
                                c(bArr);
                                l(context, commandObject, bArr);
                                return;
                            case 46:
                                if (commandObject == null || !(commandObject.getBaseRequest() instanceof GetCalorieDataReq)) {
                                    return;
                                }
                                c(bArr);
                                j(context, commandObject, bArr);
                                return;
                            case 47:
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof TodaysFitnessDataReq)) {
                                    String arrays3 = Arrays.toString(bArr);
                                    LogHelper.d(g + "Fitness Resp is +++ ", arrays3, ModuleNames.BLEABSTRACT.getModuleName());
                                    ArrayList<String> arrayList43 = new ArrayList<>();
                                    arrayList43.add(arrays3);
                                    ResponseData responseData44 = new ResponseData();
                                    responseData44.setDataList(arrayList43);
                                    TodaysFitnessDataRes todaysFitnessDataRes = new TodaysFitnessDataRes(CommandType.GET_TODAY_FITNESS_VALUE, commandObject.getBaseRequest());
                                    todaysFitnessDataRes.setResponseData(responseData44);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(todaysFitnessDataRes);
                                } else if (commandObject != null) {
                                    LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                } else {
                                    LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            default:
                                switch (b9) {
                                    case -111:
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof Vo2MaxReq)) {
                                            ArrayList<String> arrayList44 = new ArrayList<>();
                                            arrayList44.add(Arrays.toString(bArr));
                                            Vo2MaxRes vo2MaxRes = new Vo2MaxRes(CommandType.VO2MAX, commandObject.getBaseRequest());
                                            ResponseData responseData45 = new ResponseData();
                                            responseData45.setDataList(arrayList44);
                                            vo2MaxRes.setResponseData(responseData45);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(vo2MaxRes);
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -110:
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof HeartRateZoneReq)) {
                                            ArrayList<String> arrayList45 = new ArrayList<>();
                                            arrayList45.add(Arrays.toString(bArr));
                                            HeartRateZoneRes heartRateZoneRes = new HeartRateZoneRes(CommandType.HEART_RATE_ZONE, commandObject.getBaseRequest());
                                            ResponseData responseData46 = new ResponseData();
                                            responseData46.setDataList(arrayList45);
                                            heartRateZoneRes.setResponseData(responseData46);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(heartRateZoneRes);
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -109:
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof LiveStepsControlReq)) {
                                            ArrayList<String> arrayList46 = new ArrayList<>();
                                            arrayList46.add(Arrays.toString(bArr));
                                            LiveStepsControlRes liveStepsControlRes = new LiveStepsControlRes(CommandType.LIVE_STEPS_CONTROL, commandObject.getBaseRequest());
                                            ResponseData responseData47 = new ResponseData();
                                            responseData47.setDataList(arrayList46);
                                            liveStepsControlRes.setResponseData(responseData47);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(liveStepsControlRes);
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    default:
                                        switch (b9) {
                                            case -88:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetSleepTargetReq)) {
                                                    ArrayList<String> arrayList47 = new ArrayList<>();
                                                    arrayList47.add(Arrays.toString(bArr));
                                                    SetSleepTargetRes setSleepTargetRes = new SetSleepTargetRes(CommandType.SET_SLEEP_TARGET, commandObject.getBaseRequest());
                                                    ResponseData responseData48 = new ResponseData();
                                                    responseData48.setDataList(arrayList47);
                                                    setSleepTargetRes.setResponseData(responseData48);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setSleepTargetRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -87:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetStressIntervalControlReq)) {
                                                    SetStressIntervalControlRes setStressIntervalControlRes = new SetStressIntervalControlRes(CommandType.SET_STRESS_INTERVAL, commandObject.getBaseRequest());
                                                    ArrayList<String> arrayList48 = new ArrayList<>();
                                                    arrayList48.add(Arrays.toString(bArr));
                                                    ResponseData responseData49 = new ResponseData();
                                                    responseData49.setDataList(arrayList48);
                                                    setStressIntervalControlRes.setResponseData(responseData49);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setStressIntervalControlRes);
                                                    LogHelper.d(g, "SetStressIntervalControlReq response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -86:
                                                if (commandObject == null || !(commandObject.getBaseRequest() instanceof StressDataDataReq)) {
                                                    return;
                                                }
                                                c(bArr);
                                                d(context, commandObject, bArr);
                                                return;
                                            case -85:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetAutoActivityDetectionSettingsReq)) {
                                                    SetAutoActivityDetectionRes setAutoActivityDetectionRes = new SetAutoActivityDetectionRes(CommandType.SET_AUTO_ACTIVITY_DETECTION_SETTINGS, commandObject.getBaseRequest());
                                                    ArrayList<String> arrayList49 = new ArrayList<>();
                                                    arrayList49.add(Arrays.toString(bArr));
                                                    ResponseData responseData50 = new ResponseData();
                                                    responseData50.setDataList(arrayList49);
                                                    setAutoActivityDetectionRes.setResponseData(responseData50);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setAutoActivityDetectionRes);
                                                } else if (commandObject != null && (commandObject.getBaseRequest() instanceof GetAutoActivityDetectionSettingsReq)) {
                                                    GetAutoActivityDetectionSettingsRes getAutoActivityDetectionSettingsRes = new GetAutoActivityDetectionSettingsRes(CommandType.GET_AUTO_ACTIVITY_DETECTION_SETTINGS, commandObject.getBaseRequest());
                                                    ArrayList<String> arrayList50 = new ArrayList<>();
                                                    arrayList50.add(Arrays.toString(bArr));
                                                    ResponseData responseData51 = new ResponseData();
                                                    responseData51.setDataList(arrayList50);
                                                    getAutoActivityDetectionSettingsRes.setResponseData(responseData51);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(getAutoActivityDetectionSettingsRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -84:
                                                if (commandObject == null || commandObject.getResponseListener() == null || !(commandObject.getBaseRequest() instanceof GetFirmwareCapabilityReq)) {
                                                    return;
                                                }
                                                if (bArr.length == 9) {
                                                    GetFirmwareCapabilityRes getFirmwareCapabilityRes = new GetFirmwareCapabilityRes(CommandType.GET_FIRMWARE_CAPABILITY, commandObject.getBaseRequest());
                                                    ArrayList<String> arrayList51 = new ArrayList<>();
                                                    arrayList51.add(Arrays.toString(bArr));
                                                    ResponseData responseData52 = new ResponseData();
                                                    responseData52.setDataList(arrayList51);
                                                    getFirmwareCapabilityRes.setResponseData(responseData52);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(getFirmwareCapabilityRes);
                                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                    return;
                                                }
                                                FirmwareCapabilityInfo firmwareCapabilityInfo = new FirmwareCapabilityInfo();
                                                firmwareCapabilityInfo.setCapabilities(new byte[32]);
                                                BlePreferenceManager.saveString(context, CommonPreference.FIRMWARE_CAPABILITY.getName() + this.f7576a, new Gson().toJson(firmwareCapabilityInfo));
                                                commandObject.getResponseListener().onFailure(new Error(Type.GET_FIRMWARE_CAPABILITY_FAILED, "Get Firmware Capability Not Supported."));
                                                return;
                                            case -83:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SensAISetActivityConfigReq)) {
                                                    ArrayList<String> arrayList52 = new ArrayList<>();
                                                    arrayList52.add(Arrays.toString(bArr));
                                                    SetSensAIActivityConfigRes setSensAIActivityConfigRes = new SetSensAIActivityConfigRes(CommandType.SET_SENS_AI_ACTIVITY_CONFIG, commandObject.getBaseRequest());
                                                    ResponseData responseData53 = new ResponseData();
                                                    responseData53.setDataList(arrayList52);
                                                    setSensAIActivityConfigRes.setResponseData(responseData53);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setSensAIActivityConfigRes);
                                                    LogHelper.d(g, "Set SensAI Activity Config response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                    break;
                                                }
                                                break;
                                            default:
                                                switch (b9) {
                                                    case 0:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof TodaysStepsDataReq)) {
                                                            String arrays4 = Arrays.toString(bArr);
                                                            LogHelper.d(g + "Fitness Resp is +++ ", arrays4, ModuleNames.BLEABSTRACT.getModuleName());
                                                            ArrayList<String> arrayList53 = new ArrayList<>();
                                                            arrayList53.add(arrays4);
                                                            ResponseData responseData54 = new ResponseData();
                                                            responseData54.setDataList(arrayList53);
                                                            TodaysStepsDataRes todaysStepsDataRes = new TodaysStepsDataRes(CommandType.GET_TODAYS_WALK_DATA, commandObject.getBaseRequest());
                                                            todaysStepsDataRes.setResponseData(responseData54);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(todaysStepsDataRes);
                                                        } else if (commandObject != null) {
                                                            LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else {
                                                            LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case 1:
                                                        LogHelper.d(g + "walk target Resp +++ ", Arrays.toString(bArr), ModuleNames.BLEABSTRACT.getModuleName());
                                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.READ_STEPS_TARGET, Integer.valueOf((bArr[3] & 255) | ((bArr[4] & 255) << 8)), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                                        if (commandObject != null) {
                                                            commandObject.setCompleted(true);
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case 2:
                                                        c(bArr);
                                                        m(context, commandObject, bArr);
                                                        return;
                                                    case 3:
                                                        if (commandObject.getCmdName() == CommandNames.READ_MANUAL_BP) {
                                                            ReadManualBpRes readManualBpRes = new ReadManualBpRes(CommandType.READ_MANUAL_BP, commandObject.getBaseRequest());
                                                            readManualBpRes.setResponseData(null);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(readManualBpRes);
                                                            this.b.post(new k(this));
                                                            return;
                                                        }
                                                        return;
                                                    case 4:
                                                        LogHelper.d(g + "walk target Resp +++ ", Arrays.toString(bArr), ModuleNames.BLEABSTRACT.getModuleName());
                                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.READ_BIKE_TARGET, Integer.valueOf((bArr[3] & 255) | ((bArr[4] & 255) << 8)), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                                        if (commandObject != null) {
                                                            commandObject.setCompleted(true);
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case 5:
                                                        c(bArr);
                                                        g(context, commandObject, bArr);
                                                        return;
                                                    case 6:
                                                        c(bArr);
                                                        f(context, commandObject, bArr);
                                                        return;
                                                    default:
                                                        switch (b9) {
                                                            case 10:
                                                                if (commandObject == null || commandObject.getCmdName() != CommandNames.GET_LATEST_HEALTH_DATA) {
                                                                    return;
                                                                }
                                                                ArrayList<String> arrayList54 = new ArrayList<>();
                                                                arrayList54.add(Arrays.toString(bArr));
                                                                GetLatestHealthDataRes getLatestHealthDataRes = new GetLatestHealthDataRes(CommandType.GET_LATEST_HEALTH_DATA, commandObject.getBaseRequest());
                                                                ResponseData responseData55 = new ResponseData();
                                                                responseData55.setDataList(arrayList54);
                                                                getLatestHealthDataRes.setResponseData(responseData55);
                                                                commandObject.setCompleted(true);
                                                                commandObject.getResponseListener().onResponse(getLatestHealthDataRes);
                                                                this.b.post(new v(this));
                                                                return;
                                                            case 11:
                                                                ArrayList arrayList55 = new ArrayList();
                                                                arrayList55.add(Arrays.toString(bArr));
                                                                BleEventBusManager.getInstance().getEventBus().post(new RawData(arrayList55, ResponseType.GET_SEVEN_DAYS_WALK_DATA));
                                                                this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                                                return;
                                                            case 12:
                                                                c(bArr);
                                                                e(context, commandObject, bArr);
                                                                return;
                                                            default:
                                                                return;
                                                        }
                                                }
                                        }
                                }
                        }
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetButtonFunctionReq)) {
                            ArrayList<String> arrayList56 = new ArrayList<>();
                            arrayList56.add(Arrays.toString(bArr));
                            ResponseData responseData56 = new ResponseData();
                            responseData56.setDataList(arrayList56);
                            GetButtonFunctionRes getButtonFunctionRes = new GetButtonFunctionRes(CommandType.GET_BUTTON_FUNCTION, commandObject.getBaseRequest());
                            getButtonFunctionRes.setResponseData(responseData56);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getButtonFunctionRes);
                            LogHelper.d(g, "Get 4H Button function response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof SetButtonFunctionReq)) {
                            ArrayList<String> arrayList57 = new ArrayList<>();
                            arrayList57.add(Arrays.toString(bArr));
                            SetButtonFunctionRes setButtonFunctionRes = new SetButtonFunctionRes(CommandType.SET_BUTTON_FUNCTION, commandObject.getBaseRequest());
                            ResponseData responseData57 = new ResponseData();
                            responseData57.setDataList(arrayList57);
                            setButtonFunctionRes.setResponseData(responseData57);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(setButtonFunctionRes);
                            LogHelper.d(g, "Set 4H Button function response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                        } else if (commandObject != null) {
                            LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                        } else {
                            LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else {
                        c(bArr);
                        h(context, commandObject, bArr);
                    }
                } else if (bArr[0] == Byte.MAX_VALUE) {
                    c(bArr);
                    if (commandObject != null) {
                        switch (w.f7577a[commandObject.getCmdName().ordinal()]) {
                            case 1:
                                s(context, commandObject, bArr);
                                return;
                            case 2:
                                f(context, commandObject, bArr);
                                return;
                            case 3:
                                d(context, commandObject, bArr);
                                return;
                            case 4:
                                g(context, commandObject, bArr);
                                return;
                            case 5:
                                o(context, commandObject, bArr);
                                return;
                            case 6:
                                u(context, commandObject, bArr);
                                return;
                            case 7:
                                v(context, commandObject, bArr);
                                return;
                            case 8:
                                t(context, commandObject, bArr);
                                return;
                            case 9:
                                k(context, commandObject, bArr);
                                return;
                            case 10:
                                i(context, commandObject, bArr);
                                break;
                            case 11:
                                break;
                            case 12:
                                l(context, commandObject, bArr);
                                return;
                            case 13:
                                j(context, commandObject, bArr);
                                return;
                            case 14:
                                h(context, commandObject, bArr);
                                return;
                            case 15:
                                x(context, commandObject, bArr);
                                return;
                            case 16:
                                y(context, commandObject, bArr);
                                return;
                            case 17:
                                ResponseData responseData58 = new ResponseData();
                                ArrayList<String> arrayList58 = this.d;
                                if (arrayList58 == null || arrayList58.size() <= 0) {
                                    return;
                                }
                                String str7 = this.d.get(0);
                                String[] split = str7.substring(1, str7.length() - 1).split(Constants.SEPARATOR_COMMA);
                                this.c = 0;
                                this.c = ((Byte.parseByte(split[5].trim()) & 255) << 8) | (Byte.parseByte(split[4].trim()) & 255);
                                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                                if (this.d.size() == this.c) {
                                    responseData58.setDataList(new ArrayList<>(this.d));
                                    ArrayList<String> arrayList59 = this.d;
                                    if (arrayList59 != null && arrayList59.size() > 0) {
                                        this.d.clear();
                                    }
                                    responseData58.setResponseType(ResponseType.READ_MANUAL_BP);
                                    if (commandObject.getRequestPayload() != null) {
                                        RequestPayload requestPayload4 = commandObject.getRequestPayload();
                                        if (requestPayload4 != null) {
                                            int day = requestPayload4.getDay();
                                            int startHour = requestPayload4.getStartHour();
                                            int endHour = requestPayload4.getEndHour();
                                            responseData58.setDay(day);
                                            responseData58.setStartTime(startHour);
                                            responseData58.setEndTime(endHour);
                                        }
                                        this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                    }
                                    responseData58.setMacAddress(this.f7576a);
                                    ReadManualBpRes readManualBpRes2 = new ReadManualBpRes(CommandType.READ_MANUAL_BP, commandObject.getBaseRequest());
                                    readManualBpRes2.setResponseData(responseData58);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(readManualBpRes2);
                                    this.b.post(new x(this));
                                    return;
                                }
                                return;
                            case 18:
                                m(context, commandObject, bArr);
                                return;
                            case 19:
                                ResponseData responseData59 = new ResponseData();
                                ArrayList<String> arrayList60 = this.d;
                                if (arrayList60 == null || arrayList60.size() <= 0) {
                                    return;
                                }
                                String str8 = this.d.get(0);
                                String[] split2 = str8.substring(1, str8.length() - 1).split(Constants.SEPARATOR_COMMA);
                                this.c = 0;
                                this.c = ((Byte.parseByte(split2[5].trim()) & 255) << 8) | (Byte.parseByte(split2[4].trim()) & 255);
                                if (this.d.size() == this.c) {
                                    responseData59.setDataList(new ArrayList<>(this.d));
                                    ArrayList<String> arrayList61 = this.d;
                                    if (arrayList61 != null && arrayList61.size() > 0) {
                                        this.d.clear();
                                    }
                                    responseData59.setResponseType(ResponseType.FIVE_MIN_RR_DATA);
                                    if (commandObject.getRequestPayload() != null) {
                                        RequestPayload requestPayload5 = commandObject.getRequestPayload();
                                        if (requestPayload5 != null) {
                                            int day2 = requestPayload5.getDay();
                                            int startHour2 = requestPayload5.getStartHour();
                                            int endHour2 = requestPayload5.getEndHour();
                                            responseData59.setDay(day2);
                                            responseData59.setStartTime(startHour2);
                                            responseData59.setEndTime(endHour2);
                                        }
                                        this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                    }
                                    responseData59.setMacAddress(this.f7576a);
                                    if (BleUtils.isEmpty(this.f7576a)) {
                                        return;
                                    }
                                    RrDataRes rrDataRes = new RrDataRes(CommandType.RR, commandObject.getBaseRequest());
                                    rrDataRes.setResponseData(responseData59);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(rrDataRes);
                                    this.b.post(new y(this));
                                    return;
                                }
                                return;
                            case 20:
                                ResponseData responseData60 = new ResponseData();
                                ArrayList<String> arrayList62 = this.d;
                                if (arrayList62 == null || arrayList62.size() <= 0) {
                                    return;
                                }
                                String str9 = this.d.get(0);
                                String[] split3 = str9.substring(1, str9.length() - 1).split(Constants.SEPARATOR_COMMA);
                                this.c = 0;
                                this.c = ((Byte.parseByte(split3[5].trim()) & 255) << 8) | (Byte.parseByte(split3[4].trim()) & 255);
                                if (this.d.size() == this.c) {
                                    responseData60.setDataList(new ArrayList<>(this.d));
                                    ArrayList<String> arrayList63 = this.d;
                                    if (arrayList63 != null && arrayList63.size() > 0) {
                                        this.d.clear();
                                    }
                                    responseData60.setResponseType(ResponseType.FIVE_MIN_RUN_DATA);
                                    if (commandObject.getRequestPayload() != null) {
                                        RequestPayload requestPayload6 = commandObject.getRequestPayload();
                                        if (requestPayload6 != null) {
                                            int day3 = requestPayload6.getDay();
                                            int startHour3 = requestPayload6.getStartHour();
                                            int endHour3 = requestPayload6.getEndHour();
                                            responseData60.setDay(day3);
                                            responseData60.setStartTime(startHour3);
                                            responseData60.setEndTime(endHour3);
                                        }
                                        this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                    }
                                    if (BleUtils.isEmpty(this.f7576a)) {
                                        return;
                                    }
                                    responseData60.setMacAddress(this.f7576a);
                                    int intValue = ((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_WEIGHT, 55)).intValue();
                                    responseData60.setHeight(((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_HEIGHT, 165)).intValue());
                                    responseData60.setWeight(intValue);
                                    RunningDataRes runningDataRes = new RunningDataRes(CommandType.RUN, commandObject.getBaseRequest());
                                    runningDataRes.setResponseData(responseData60);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(runningDataRes);
                                    this.b.post(new z(this));
                                    return;
                                }
                                return;
                            case 21:
                                ResponseData responseData61 = new ResponseData();
                                ArrayList<String> arrayList64 = this.d;
                                if (arrayList64 == null || arrayList64.size() <= 0) {
                                    return;
                                }
                                String str10 = this.d.get(0);
                                String[] split4 = str10.substring(1, str10.length() - 1).split(Constants.SEPARATOR_COMMA);
                                this.c = 0;
                                this.c = ((Byte.parseByte(split4[5].trim()) & 255) << 8) | (Byte.parseByte(split4[4].trim()) & 255);
                                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                                if (this.d.size() == this.c) {
                                    ArrayList<String> arrayList65 = this.d;
                                    if (arrayList65 != null && arrayList65.size() > 0) {
                                        responseData61.setDataList(new ArrayList<>(this.d));
                                        ArrayList<String> arrayList66 = this.d;
                                        if (arrayList66 != null && arrayList66.size() > 0) {
                                            this.d.clear();
                                        }
                                        responseData61.setResponseType(ResponseType.HOURLY_WALK_DATA);
                                        if (commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                                            int day4 = requestPayload.getDay();
                                            int startHour4 = requestPayload.getStartHour();
                                            int endHour4 = requestPayload.getEndHour();
                                            responseData61.setDay(day4);
                                            responseData61.setStartTime(startHour4);
                                            responseData61.setEndTime(endHour4);
                                            responseData61.setStrideLength(requestPayload.getStrideLength());
                                            responseData61.setRunStrideLength(requestPayload.getRunStrideLength());
                                        }
                                        String str11 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                        this.f7576a = str11;
                                        if (BleUtils.isEmpty(str11)) {
                                            return;
                                        }
                                        responseData61.setMacAddress(this.f7576a);
                                        int intValue2 = ((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_WEIGHT, 55)).intValue();
                                        responseData61.setHeight(((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_HEIGHT, 165)).intValue());
                                        responseData61.setWeight(intValue2);
                                        WalkDataRes walkDataRes = new WalkDataRes(CommandType.WALK, commandObject.getBaseRequest());
                                        walkDataRes.setResponseData(responseData61);
                                        commandObject.setCompleted(true);
                                        commandObject.getResponseListener().onResponse(walkDataRes);
                                    }
                                    this.b.post(new a0(this));
                                    return;
                                }
                                return;
                            case 22:
                                ResponseData responseData62 = new ResponseData();
                                ArrayList<String> arrayList67 = this.d;
                                if (arrayList67 == null || arrayList67.size() <= 0) {
                                    return;
                                }
                                String str12 = this.d.get(0);
                                String[] split5 = str12.substring(1, str12.length() - 1).split(Constants.SEPARATOR_COMMA);
                                this.c = 0;
                                this.c = ((Byte.parseByte(split5[5].trim()) & 255) << 8) | (Byte.parseByte(split5[4].trim()) & 255);
                                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                                if (this.d.size() == this.c) {
                                    ArrayList<String> arrayList68 = this.d;
                                    if (arrayList68 != null && arrayList68.size() > 0) {
                                        responseData62.setDataList(new ArrayList<>(this.d));
                                        ArrayList<String> arrayList69 = this.d;
                                        if (arrayList69 != null && arrayList69.size() > 0) {
                                            this.d.clear();
                                        }
                                        responseData62.setResponseType(ResponseType.TEN_MIN_SLEEP_DATA);
                                        if (commandObject.getRequestPayload() != null && (requestPayload2 = commandObject.getRequestPayload()) != null) {
                                            int day5 = requestPayload2.getDay();
                                            int startHour5 = requestPayload2.getStartHour();
                                            int endHour5 = requestPayload2.getEndHour();
                                            responseData62.setDay(day5);
                                            responseData62.setStartTime(startHour5);
                                            responseData62.setEndTime(endHour5);
                                        }
                                        String str13 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                        this.f7576a = str13;
                                        responseData62.setMacAddress(str13);
                                        if (BleUtils.isEmpty(this.f7576a)) {
                                            return;
                                        }
                                    }
                                    this.b.post(new b0(this));
                                    return;
                                }
                                return;
                            case 23:
                                e(context, commandObject, bArr);
                                return;
                            case 24:
                                ResponseData responseData63 = new ResponseData();
                                ArrayList<String> arrayList70 = this.d;
                                if (arrayList70 == null || arrayList70.size() <= 0) {
                                    return;
                                }
                                String str14 = this.d.get(0);
                                String[] split6 = str14.substring(1, str14.length() - 1).split(Constants.SEPARATOR_COMMA);
                                this.c = 0;
                                this.c = ((Byte.parseByte(split6[5].trim()) & 255) << 8) | (Byte.parseByte(split6[4].trim()) & 255);
                                if (this.d.size() == this.c) {
                                    ArrayList<String> arrayList71 = this.d;
                                    if (arrayList71 != null && arrayList71.size() > 0) {
                                        responseData63.setDataList(this.d);
                                        ArrayList<String> arrayList72 = this.d;
                                        if (arrayList72 != null && arrayList72.size() > 0) {
                                            this.d.clear();
                                        }
                                        responseData63.setResponseType(ResponseType.ONE_MIN_UV_DATA);
                                        if (commandObject.getRequestPayload() != null && (requestPayload3 = commandObject.getRequestPayload()) != null) {
                                            int day6 = requestPayload3.getDay();
                                            int startHour6 = requestPayload3.getStartHour();
                                            int endHour6 = requestPayload3.getEndHour();
                                            responseData63.setDay(day6);
                                            responseData63.setStartTime(startHour6);
                                            responseData63.setEndTime(endHour6);
                                        }
                                        String str15 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                        this.f7576a = str15;
                                        if (BleUtils.isEmpty(str15)) {
                                            return;
                                        }
                                    }
                                    this.b.post(new c0(this));
                                    return;
                                }
                                return;
                            case 25:
                            case 26:
                                b(context, commandObject);
                                return;
                            case 27:
                                q(context, commandObject, bArr);
                                return;
                            case 28:
                                p(context, commandObject, bArr);
                                return;
                            case 29:
                                r(context, commandObject, bArr);
                                return;
                            default:
                                return;
                        }
                        w(context, commandObject, bArr);
                    }
                } else if (bArr[0] == -126) {
                    byte b11 = bArr[1];
                    if (b11 == 0) {
                        byte b12 = bArr[4];
                        byte b13 = bArr[5];
                        byte b14 = bArr[6];
                        byte b15 = bArr[7];
                        byte b16 = bArr[8];
                        byte b17 = bArr[9];
                    } else if (b11 == 3) {
                        byte b18 = bArr[5];
                    } else if (b11 == 5) {
                        ArrayList<String> arrayList73 = new ArrayList<>();
                        arrayList73.add(Arrays.toString(bArr));
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetAlarmListReq)) {
                            GetAlarmListRes getAlarmListRes = new GetAlarmListRes(CommandType.GET_ALARM_LIST, commandObject.getBaseRequest());
                            ResponseData responseData64 = new ResponseData();
                            responseData64.setDataList(arrayList73);
                            getAlarmListRes.setResponseData(responseData64);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getAlarmListRes);
                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                        } else {
                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b11 == 9) {
                        ArrayList<String> arrayList74 = new ArrayList<>();
                        arrayList74.add(Arrays.toString(bArr));
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetDNDModeSettingsReq)) {
                            GetDNDModeSettingsRes getDNDModeSettingsRes = new GetDNDModeSettingsRes(CommandType.GET_DND_MODE_SETTINGS, commandObject.getBaseRequest());
                            ResponseData responseData65 = new ResponseData();
                            responseData65.setDataList(arrayList74);
                            getDNDModeSettingsRes.setResponseData(responseData65);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getDNDModeSettingsRes);
                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                        } else {
                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b11 == 13) {
                        ArrayList<String> arrayList75 = new ArrayList<>();
                        arrayList75.add(Arrays.toString(bArr));
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetWatchFaceListReq)) {
                            GetWatchFaceListRes getWatchFaceListRes = new GetWatchFaceListRes(CommandType.GET_WATCH_FACE_LIST, commandObject.getBaseRequest());
                            ResponseData responseData66 = new ResponseData();
                            responseData66.setDataList(arrayList75);
                            getWatchFaceListRes.setResponseData(responseData66);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getWatchFaceListRes);
                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                        } else {
                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b11 == 15) {
                        ArrayList<String> arrayList76 = new ArrayList<>();
                        arrayList76.add(Arrays.toString(bArr));
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetCurrentWatchFaceReq)) {
                            GetCurrentWatchFaceRes getCurrentWatchFaceRes = new GetCurrentWatchFaceRes(CommandType.GET_CURRENT_WATCH_FACE, commandObject.getBaseRequest());
                            ResponseData responseData67 = new ResponseData();
                            responseData67.setDataList(arrayList76);
                            getCurrentWatchFaceRes.setResponseData(responseData67);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getCurrentWatchFaceRes);
                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                        } else {
                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b11 == 18) {
                        ArrayList<String> arrayList77 = new ArrayList<>();
                        arrayList77.add(Arrays.toString(bArr));
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetCameraStatusReq)) {
                            SetCameraStatusRes setCameraStatusRes = new SetCameraStatusRes(CommandType.SET_CAMERA_STATUS, commandObject.getBaseRequest());
                            ResponseData responseData68 = new ResponseData();
                            responseData68.setDataList(arrayList77);
                            setCameraStatusRes.setResponseData(responseData68);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(setCameraStatusRes);
                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                        } else {
                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b11 == 19) {
                        c(bArr);
                        n(context, commandObject, bArr);
                    } else if (b11 == 22) {
                        ArrayList<String> arrayList78 = new ArrayList<>();
                        arrayList78.add(Arrays.toString(bArr));
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetWatchfaceBackgroundReq)) {
                            GetWatchfaceBackgroundRes getWatchfaceBackgroundRes = new GetWatchfaceBackgroundRes(CommandType.GET_WATCH_FACE_BG_LIST, commandObject.getBaseRequest());
                            ResponseData responseData69 = new ResponseData();
                            responseData69.setDataList(arrayList78);
                            getWatchfaceBackgroundRes.setResponseData(responseData69);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(getWatchfaceBackgroundRes);
                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                        } else {
                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b11 != 23) {
                        switch (b11) {
                            case WristbandManager.SYNC_STATE_FAILED_UNKNOWN /* -128 */:
                                ArrayList<String> arrayList79 = new ArrayList<>();
                                arrayList79.add(Arrays.toString(bArr));
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SedentaryReminderReq)) {
                                    SetSedentaryReminderRes setSedentaryReminderRes = new SetSedentaryReminderRes(CommandType.SET_SEDENTARY_REMINDER, commandObject.getBaseRequest());
                                    ResponseData responseData70 = new ResponseData();
                                    responseData70.setDataList(arrayList79);
                                    setSedentaryReminderRes.setResponseData(responseData70);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setSedentaryReminderRes);
                                    LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                } else {
                                    LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -127:
                                ArrayList<String> arrayList80 = new ArrayList<>();
                                arrayList80.add(Arrays.toString(bArr));
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetMusicPlayBackStatusReq)) {
                                    MusicStatusNotificationRes musicStatusNotificationRes2 = new MusicStatusNotificationRes(CommandType.SET_MUSIC_STATUS, commandObject.getBaseRequest());
                                    ResponseData responseData71 = new ResponseData();
                                    responseData71.setDataList(arrayList80);
                                    musicStatusNotificationRes2.setResponseData(responseData71);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(musicStatusNotificationRes2);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -126:
                                ArrayList<String> arrayList81 = new ArrayList<>();
                                arrayList81.add(Arrays.toString(bArr));
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof MessageAlertSwitchesReq)) {
                                    MessageAlertSwitchesRes messageAlertSwitchesRes = new MessageAlertSwitchesRes(CommandType.SET_MESSGAE_ALERT, commandObject.getBaseRequest());
                                    ResponseData responseData72 = new ResponseData();
                                    responseData72.setDataList(arrayList81);
                                    messageAlertSwitchesRes.setResponseData(responseData72);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(messageAlertSwitchesRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -125:
                                ArrayList<String> arrayList82 = new ArrayList<>();
                                arrayList82.add(Arrays.toString(bArr));
                                if (commandObject != null && ((commandObject.getBaseRequest() instanceof MessageContentReq) || (commandObject.getBaseRequest() instanceof CZ2MessageContentReq))) {
                                    MessageContentRes messageContentRes = new MessageContentRes(CommandType.SEND_MESSAGE_CONTENT, commandObject.getBaseRequest());
                                    ResponseData responseData73 = new ResponseData();
                                    responseData73.setDataList(arrayList82);
                                    messageContentRes.setResponseData(responseData73);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(messageContentRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            case -124:
                                if (bArr.length > 4 && bArr[4] == 1) {
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_SMS_ALERT, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                    return;
                                } else {
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_SMS_ALERT, ResponseStatus.RESPONSE_STATUS_FAILURE));
                                    return;
                                }
                            case -123:
                                ArrayList<String> arrayList83 = new ArrayList<>();
                                arrayList83.add(Arrays.toString(bArr));
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetAlarmReq)) {
                                    SetAlarmRes setAlarmRes = new SetAlarmRes(CommandType.SET_ALARM, commandObject.getBaseRequest());
                                    ResponseData responseData74 = new ResponseData();
                                    responseData74.setDataList(arrayList83);
                                    setAlarmRes.setResponseData(responseData74);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setAlarmRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                return;
                            default:
                                switch (b11) {
                                    case -119:
                                        ArrayList<String> arrayList84 = new ArrayList<>();
                                        arrayList84.add(Arrays.toString(bArr));
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof DNDModeReq)) {
                                            SetDNDModeRes setDNDModeRes = new SetDNDModeRes(CommandType.SET_DND_MODE, commandObject.getBaseRequest());
                                            ResponseData responseData75 = new ResponseData();
                                            responseData75.setDataList(arrayList84);
                                            setDNDModeRes.setResponseData(responseData75);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(setDNDModeRes);
                                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                        } else {
                                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -118:
                                        if (commandObject != null) {
                                            commandObject.setCompleted(true);
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -117:
                                        if (commandObject != null) {
                                            commandObject.setCompleted(true);
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    case -116:
                                        ArrayList<String> arrayList85 = new ArrayList<>();
                                        arrayList85.add(Arrays.toString(bArr));
                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof StopMessageNotificationReq)) {
                                            StopMessageNotificationRes stopMessageNotificationRes = new StopMessageNotificationRes(CommandType.STOP_MESSAGE_NOTIFICATION, commandObject.getBaseRequest());
                                            ResponseData responseData76 = new ResponseData();
                                            responseData76.setDataList(arrayList85);
                                            stopMessageNotificationRes.setResponseData(responseData76);
                                            commandObject.setCompleted(true);
                                            commandObject.getResponseListener().onResponse(stopMessageNotificationRes);
                                        }
                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                        return;
                                    default:
                                        switch (b11) {
                                            case -114:
                                                ArrayList<String> arrayList86 = new ArrayList<>();
                                                arrayList86.add(Arrays.toString(bArr));
                                                if (commandObject == null || commandObject.getResponseListener() == null || !(commandObject.getBaseRequest() instanceof WatchFaceUploadReq)) {
                                                    return;
                                                }
                                                if (bArr[bArr.length - 1] == 1) {
                                                    if (linkedList.size() > 0) {
                                                        commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) (((commandObject.getBaseRequest().getCommandBytes().size() - linkedList.size()) / commandObject.getBaseRequest().getCommandBytes().size()) * 100.0f), ProgressType.PERCENTAGE));
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    }
                                                    WatchFaceUploadFaceRes watchFaceUploadFaceRes = new WatchFaceUploadFaceRes(CommandType.WATCH_FACE, commandObject.getBaseRequest());
                                                    ResponseData responseData77 = new ResponseData();
                                                    responseData77.setDataList(arrayList86);
                                                    watchFaceUploadFaceRes.setResponseData(responseData77);
                                                    commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(watchFaceUploadFaceRes);
                                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                    return;
                                                } else if (bArr[bArr.length - 1] == 0) {
                                                    commandObject.getResponseListener().onFailure(new Error(Type.WATCH_BUSY, "Watch is busy, Please try again"));
                                                    return;
                                                } else {
                                                    commandObject.getResponseListener().onFailure(new Error(Type.WATCH_RESPONSE_FAILED, "Watch memory exceeded"));
                                                    ProcessNextItemEvent processNextItemEvent = new ProcessNextItemEvent();
                                                    processNextItemEvent.setHasStreamError(true);
                                                    BleEventBusManager.getInstance().getEventBus().post(processNextItemEvent);
                                                    return;
                                                }
                                            case -113:
                                                ArrayList<String> arrayList87 = new ArrayList<>();
                                                arrayList87.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetCurrentWatchFaceReq)) {
                                                    SetCurrentWatchFaceRes setCurrentWatchFaceRes = new SetCurrentWatchFaceRes(CommandType.SET_CURRENT_WATCH_FACE, commandObject.getBaseRequest());
                                                    ResponseData responseData78 = new ResponseData();
                                                    responseData78.setDataList(arrayList87);
                                                    setCurrentWatchFaceRes.setResponseData(responseData78);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setCurrentWatchFaceRes);
                                                    LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                                } else {
                                                    LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -112:
                                                ArrayList<String> arrayList88 = new ArrayList<>();
                                                arrayList88.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof DeleteWatchFaceReq)) {
                                                    DeleteWatchFaceRes deleteWatchFaceRes = new DeleteWatchFaceRes(CommandType.DELETE_WATCH_FACE, commandObject.getBaseRequest());
                                                    ResponseData responseData79 = new ResponseData();
                                                    responseData79.setDataList(arrayList88);
                                                    deleteWatchFaceRes.setResponseData(responseData79);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(deleteWatchFaceRes);
                                                    LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                                } else {
                                                    LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -111:
                                                ArrayList<String> arrayList89 = new ArrayList<>();
                                                arrayList89.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetSportNotificationReq)) {
                                                    SetSportNotificationRes setSportNotificationRes = new SetSportNotificationRes(CommandType.SPORTS_NOTIFICATION_CONTROL, commandObject.getBaseRequest());
                                                    ResponseData responseData80 = new ResponseData();
                                                    responseData80.setDataList(arrayList89);
                                                    setSportNotificationRes.setResponseData(responseData80);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setSportNotificationRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -110:
                                                ArrayList<String> arrayList90 = new ArrayList<>();
                                                arrayList90.add(Arrays.toString(bArr));
                                                if (commandObject == null || !(commandObject.getBaseRequest() instanceof SportsNotificationReq)) {
                                                    return;
                                                }
                                                if (linkedList.size() > 0) {
                                                    commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) (((commandObject.getBaseRequest().getCommandBytes().size() - linkedList.size()) / commandObject.getBaseRequest().getCommandBytes().size()) * 100.0f), ProgressType.PERCENTAGE));
                                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                    return;
                                                }
                                                SportsNotificationRes sportsNotificationRes = new SportsNotificationRes(CommandType.SPORTS_NOTIFICATION, commandObject.getBaseRequest());
                                                ResponseData responseData81 = new ResponseData();
                                                responseData81.setDataList(arrayList90);
                                                sportsNotificationRes.setResponseData(responseData81);
                                                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
                                                commandObject.setCompleted(true);
                                                commandObject.getResponseListener().onResponse(sportsNotificationRes);
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -109:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SendWeatherDataRequest)) {
                                                    ArrayList<String> arrayList91 = new ArrayList<>();
                                                    arrayList91.add(Arrays.toString(bArr));
                                                    SendWeatherRes sendWeatherRes = new SendWeatherRes(CommandType.SEND_WEATHER_DATA, commandObject.getBaseRequest());
                                                    ResponseData responseData82 = new ResponseData();
                                                    responseData82.setDataList(arrayList91);
                                                    sendWeatherRes.setResponseData(responseData82);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(sendWeatherRes);
                                                    LogHelper.d(g, "SendWeather response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -108:
                                                ArrayList<String> arrayList92 = new ArrayList<>();
                                                arrayList92.add(Arrays.toString(bArr));
                                                if (commandObject == null || commandObject.getResponseListener() == null) {
                                                    return;
                                                }
                                                if (bArr[bArr.length - 1] == 1) {
                                                    if (linkedList.size() > 0) {
                                                        commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) (((commandObject.getBaseRequest().getCommandBytes().size() - linkedList.size()) / commandObject.getBaseRequest().getCommandBytes().size()) * 100.0f), ProgressType.PERCENTAGE));
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    }
                                                    SendImageRes sendImageRes = new SendImageRes(CommandType.SEND_IMAGE, commandObject.getBaseRequest());
                                                    ResponseData responseData83 = new ResponseData();
                                                    responseData83.setDataList(arrayList92);
                                                    sendImageRes.setResponseData(responseData83);
                                                    commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(sendImageRes);
                                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                    return;
                                                } else if (bArr[bArr.length - 1] == 0) {
                                                    commandObject.getResponseListener().onFailure(new Error(Type.WATCH_BUSY, "Watch is busy, Please try again"));
                                                    return;
                                                } else {
                                                    commandObject.getResponseListener().onFailure(new Error(Type.WATCH_RESPONSE_FAILED, "Watch memory exceeded"));
                                                    ProcessNextItemEvent processNextItemEvent2 = new ProcessNextItemEvent();
                                                    processNextItemEvent2.setHasStreamError(true);
                                                    BleEventBusManager.getInstance().getEventBus().post(processNextItemEvent2);
                                                    return;
                                                }
                                            case -107:
                                                ArrayList<String> arrayList93 = new ArrayList<>();
                                                arrayList93.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof DeleteImageReq)) {
                                                    DeleteImageRes deleteImageRes = new DeleteImageRes(CommandType.DELETE_IMAGE, commandObject.getBaseRequest());
                                                    ResponseData responseData84 = new ResponseData();
                                                    responseData84.setDataList(arrayList93);
                                                    deleteImageRes.setResponseData(responseData84);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(deleteImageRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -106:
                                                ArrayList<String> arrayList94 = new ArrayList<>();
                                                arrayList94.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof ChangeWatchFaceBGReq)) {
                                                    ChangeWatchFaceBGRes changeWatchFaceBGRes = new ChangeWatchFaceBGRes(CommandType.SET_WATCH_FACE_BG, commandObject.getBaseRequest());
                                                    ResponseData responseData85 = new ResponseData();
                                                    responseData85.setDataList(arrayList94);
                                                    changeWatchFaceBGRes.setResponseData(responseData85);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(changeWatchFaceBGRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -105:
                                                ArrayList<String> arrayList95 = new ArrayList<>();
                                                arrayList95.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetQuickReplyReq)) {
                                                    SetQuickReplyRes setQuickReplyRes = new SetQuickReplyRes(CommandType.SET_QUICK_REPLY, commandObject.getBaseRequest());
                                                    ResponseData responseData86 = new ResponseData();
                                                    responseData86.setDataList(arrayList95);
                                                    setQuickReplyRes.setResponseData(responseData86);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setQuickReplyRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -104:
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SendWomenWellnessReq)) {
                                                    ArrayList<String> arrayList96 = new ArrayList<>();
                                                    arrayList96.add(Arrays.toString(bArr));
                                                    WomenWellnessSettingsRes womenWellnessSettingsRes = new WomenWellnessSettingsRes(CommandType.SET_WOMEN_WELLNESS, commandObject.getBaseRequest());
                                                    ResponseData responseData87 = new ResponseData();
                                                    responseData87.setDataList(arrayList96);
                                                    womenWellnessSettingsRes.setResponseData(responseData87);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(womenWellnessSettingsRes);
                                                    LogHelper.d(g, "womenWellnessSettingsRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -103:
                                                ArrayList<String> arrayList97 = new ArrayList<>();
                                                arrayList97.add(Arrays.toString(bArr));
                                                if (commandObject == null || !(commandObject.getBaseRequest() instanceof CustomMessageReq)) {
                                                    return;
                                                }
                                                if (linkedList.size() > 0) {
                                                    commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) (((commandObject.getBaseRequest().getCommandBytes().size() - linkedList.size()) / commandObject.getBaseRequest().getCommandBytes().size()) * 100.0f), ProgressType.PERCENTAGE));
                                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                    return;
                                                }
                                                CustomMessageRes customMessageRes = new CustomMessageRes(CommandType.CUSTOM_MESSAGE, commandObject.getBaseRequest());
                                                ResponseData responseData88 = new ResponseData();
                                                responseData88.setDataList(arrayList97);
                                                customMessageRes.setResponseData(responseData88);
                                                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
                                                commandObject.setCompleted(true);
                                                commandObject.getResponseListener().onResponse(customMessageRes);
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            case -102:
                                                ArrayList<String> arrayList98 = new ArrayList<>();
                                                arrayList98.add(Arrays.toString(bArr));
                                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetCustomReminderReq)) {
                                                    SetCustomReminderRes setCustomReminderRes = new SetCustomReminderRes(CommandType.SET_CUSTOM_REMINDERS, commandObject.getBaseRequest());
                                                    ResponseData responseData89 = new ResponseData();
                                                    responseData89.setDataList(arrayList98);
                                                    setCustomReminderRes.setResponseData(responseData89);
                                                    commandObject.setCompleted(true);
                                                    commandObject.getResponseListener().onResponse(setCustomReminderRes);
                                                }
                                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                return;
                                            default:
                                                switch (b11) {
                                                    case -92:
                                                        ArrayList<String> arrayList99 = new ArrayList<>();
                                                        arrayList99.add(Arrays.toString(bArr));
                                                        if (commandObject == null || !(commandObject.getBaseRequest() instanceof PersonalizedMessageReq)) {
                                                            return;
                                                        }
                                                        if (linkedList.size() > 0) {
                                                            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) (((commandObject.getBaseRequest().getCommandBytes().size() - linkedList.size()) / commandObject.getBaseRequest().getCommandBytes().size()) * 100.0f), ProgressType.PERCENTAGE));
                                                            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                            return;
                                                        }
                                                        PersonalizedMessageRes personalizedMessageRes = new PersonalizedMessageRes(CommandType.SEND_PERSONALIZED_MESSAGE, commandObject.getBaseRequest());
                                                        ResponseData responseData90 = new ResponseData();
                                                        responseData90.setDataList(arrayList99);
                                                        personalizedMessageRes.setResponseData(responseData90);
                                                        commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
                                                        commandObject.setCompleted(true);
                                                        commandObject.getResponseListener().onResponse(personalizedMessageRes);
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -91:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof FindMyWatchReq)) {
                                                            ArrayList<String> arrayList100 = new ArrayList<>();
                                                            arrayList100.add(Arrays.toString(bArr));
                                                            FindMyWatchRes findMyWatchRes = new FindMyWatchRes(CommandType.FIND_MY_WATCH, commandObject.getBaseRequest());
                                                            ResponseData responseData91 = new ResponseData();
                                                            responseData91.setDataList(arrayList100);
                                                            findMyWatchRes.setResponseData(responseData91);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(findMyWatchRes);
                                                            LogHelper.d(g, "FindMyWatchRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -90:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNavigationEventReq)) {
                                                            ArrayList<String> arrayList101 = new ArrayList<>();
                                                            arrayList101.add(Arrays.toString(bArr));
                                                            SetNavigationEventRes setNavigationEventRes = new SetNavigationEventRes(CommandType.SET_NAVIGATION_EVENT, commandObject.getBaseRequest());
                                                            ResponseData responseData92 = new ResponseData();
                                                            responseData92.setDataList(arrayList101);
                                                            setNavigationEventRes.setResponseData(responseData92);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setNavigationEventRes);
                                                            LogHelper.d(g, "SetNavigationEventRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof GetNavigationWatchStateReq)) {
                                                            ArrayList<String> arrayList102 = new ArrayList<>();
                                                            arrayList102.add(Arrays.toString(bArr));
                                                            ResponseData responseData93 = new ResponseData();
                                                            responseData93.setDataList(arrayList102);
                                                            GetNavigationWatchStateRes getNavigationWatchStateRes = new GetNavigationWatchStateRes(CommandType.GET_NAVIGATION_WATCH_STATE, commandObject.getBaseRequest());
                                                            getNavigationWatchStateRes.setResponseData(responseData93);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(getNavigationWatchStateRes);
                                                            LogHelper.d(g, "Get Navigation Watch State response send from parser to impl!!! " + arrayList102.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else {
                                                            LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -89:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetSmartAlertConfigReq)) {
                                                            ArrayList<String> arrayList103 = new ArrayList<>();
                                                            arrayList103.add(Arrays.toString(bArr));
                                                            SetSmartAlertConfigRes setSmartAlertConfigRes = new SetSmartAlertConfigRes(CommandType.SET_SMART_ALERT_CONFIG, commandObject.getBaseRequest());
                                                            ResponseData responseData94 = new ResponseData();
                                                            responseData94.setDataList(arrayList103);
                                                            setSmartAlertConfigRes.setResponseData(responseData94);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setSmartAlertConfigRes);
                                                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                                            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                            return;
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof GetSmartAlertConfigReq)) {
                                                            c(bArr);
                                                            y(context, commandObject, bArr);
                                                            return;
                                                        } else {
                                                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                                            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                            return;
                                                        }
                                                    case -88:
                                                        ArrayList<String> arrayList104 = new ArrayList<>();
                                                        arrayList104.add(Arrays.toString(bArr));
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetSmartAlertApplicationContentReq)) {
                                                            SetSmartAlertApplicationContentRes setSmartAlertApplicationContentRes = new SetSmartAlertApplicationContentRes(CommandType.SET_SMART_ALERT_COMMON_APPLICATION_CONTENT, commandObject.getBaseRequest());
                                                            ResponseData responseData95 = new ResponseData();
                                                            responseData95.setDataList(arrayList104);
                                                            setSmartAlertApplicationContentRes.setResponseData(responseData95);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setSmartAlertApplicationContentRes);
                                                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNonSmartAlertApplicationContentReq)) {
                                                            SetNonSmartAlertApplicationContentRes setNonSmartAlertApplicationContentRes = new SetNonSmartAlertApplicationContentRes(CommandType.SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT, commandObject.getBaseRequest());
                                                            ResponseData responseData96 = new ResponseData();
                                                            responseData96.setDataList(arrayList104);
                                                            setNonSmartAlertApplicationContentRes.setResponseData(responseData96);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setNonSmartAlertApplicationContentRes);
                                                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else if (commandObject != null && (commandObject.getBaseRequest() instanceof SetNavigationApplicationContentReq)) {
                                                            SetNavigationApplicationContentRes setNavigationApplicationContentRes = new SetNavigationApplicationContentRes(CommandType.SET_NAVIGATION_COMMON_APPLICATION_CONTENT, commandObject.getBaseRequest());
                                                            ResponseData responseData97 = new ResponseData();
                                                            responseData97.setDataList(arrayList104);
                                                            setNavigationApplicationContentRes.setResponseData(responseData97);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setNavigationApplicationContentRes);
                                                            LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                                                        } else {
                                                            LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                                                        }
                                                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                                        return;
                                                    case -87:
                                                        if (commandObject != null && (commandObject.getBaseRequest() instanceof GetQRCodeReq)) {
                                                            c(bArr);
                                                            r(context, commandObject, bArr);
                                                            return;
                                                        } else if (commandObject == null || !(commandObject.getBaseRequest() instanceof SetQRTrayCodeReq)) {
                                                            return;
                                                        } else {
                                                            ArrayList<String> arrayList105 = new ArrayList<>();
                                                            arrayList105.add(Arrays.toString(bArr));
                                                            SetQRTrayCodeRes setQRTrayCodeRes = new SetQRTrayCodeRes(CommandType.SET_QR_TRAY_CODE, commandObject.getBaseRequest());
                                                            ResponseData responseData98 = new ResponseData();
                                                            responseData98.setDataList(arrayList105);
                                                            setQRTrayCodeRes.setResponseData(responseData98);
                                                            commandObject.setCompleted(true);
                                                            commandObject.getResponseListener().onResponse(setQRTrayCodeRes);
                                                            LogHelper.d(g, "setQRTrayCodeRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                                            return;
                                                        }
                                                    default:
                                                        return;
                                                }
                                        }
                                }
                        }
                    } else {
                        c(bArr);
                        s(context, commandObject, bArr);
                    }
                } else if (bArr[0] == -125) {
                    byte b19 = bArr[1];
                    if (b19 == Byte.MIN_VALUE) {
                        if (bArr.length >= 5 && bArr[4] == 1) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_ALARM, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        } else {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_ALARM, ResponseStatus.RESPONSE_STATUS_FAILURE));
                        }
                        if (commandObject != null) {
                            commandObject.setCompleted(true);
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    } else if (b19 != -126) {
                        if (b19 == -125 && bArr.length >= 5) {
                            byte b20 = bArr[4];
                        }
                    } else if (bArr.length >= 5 && bArr[4] == 1) {
                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_MESSGAE_ALERT, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                    } else {
                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SET_MESSGAE_ALERT, ResponseStatus.RESPONSE_STATUS_FAILURE));
                    }
                } else if (bArr[0] == -124) {
                    if (bArr[1] == -127) {
                        if (commandObject != null && (commandObject.getBaseRequest() instanceof SetVibrationReq)) {
                            ArrayList<String> arrayList106 = new ArrayList<>();
                            arrayList106.add(Arrays.toString(bArr));
                            SetVibrationRes setVibrationRes = new SetVibrationRes(CommandType.SET_VIBRATION, commandObject.getBaseRequest());
                            ResponseData responseData99 = new ResponseData();
                            responseData99.setDataList(arrayList106);
                            setVibrationRes.setResponseData(responseData99);
                            commandObject.setCompleted(true);
                            commandObject.getResponseListener().onResponse(setVibrationRes);
                            LogHelper.d(g, "SetVibrationRes response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                        BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                    }
                } else if (bArr[0] == -123) {
                } else {
                    if (bArr[0] == -122) {
                        if (bArr.length >= 20 && bArr[1] == 3 && bArr[2] == 20 && bArr[3] == 0) {
                            if (commandObject != null && (commandObject.getBaseRequest() instanceof GetSocialDistanceScanSettingsReq)) {
                                ArrayList<String> arrayList107 = new ArrayList<>();
                                arrayList107.add(Arrays.toString(bArr));
                                GetSocialDistanceScanSettingsRes getSocialDistanceScanSettingsRes = new GetSocialDistanceScanSettingsRes(CommandType.GET_SOCIAL_DISTANCE_SETTINGS, commandObject.getBaseRequest());
                                ResponseData responseData100 = new ResponseData();
                                responseData100.setDataList(arrayList107);
                                getSocialDistanceScanSettingsRes.setResponseData(responseData100);
                                commandObject.setCompleted(true);
                                commandObject.getResponseListener().onResponse(getSocialDistanceScanSettingsRes);
                            }
                            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                        } else if (bArr.length >= 4 && bArr[1] == -125 && bArr[2] == 5 && bArr[3] == 0) {
                            if (commandObject != null && (commandObject.getBaseRequest() instanceof SetSocialDistanceScanSettingsReq)) {
                                ArrayList<String> arrayList108 = new ArrayList<>();
                                arrayList108.add(Arrays.toString(bArr));
                                SetSocialDistanceScanSettingsRes setSocialDistanceScanSettingsRes = new SetSocialDistanceScanSettingsRes(CommandType.SET_SOCIAL_DISTANCE_SETTINGS, commandObject.getBaseRequest());
                                ResponseData responseData101 = new ResponseData();
                                responseData101.setDataList(arrayList108);
                                setSocialDistanceScanSettingsRes.setResponseData(responseData101);
                                commandObject.setCompleted(true);
                                commandObject.getResponseListener().onResponse(setSocialDistanceScanSettingsRes);
                            }
                            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                        } else if (bArr.length >= 5 && bArr[1] == 7 && bArr[2] == 6 && bArr[3] == 0 && bArr[4] == -126) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SEND_DIAGNOSTICS_STATUS, new SendDiagnosticsRes(bArr[5] & 255)));
                        } else {
                            byte b21 = bArr[1];
                            if (b21 == -123) {
                                ArrayList<String> arrayList109 = new ArrayList<>();
                                arrayList109.add(Arrays.toString(bArr));
                                if (linkedList.size() > 0) {
                                    commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), (int) (((commandObject.getBaseRequest().getCommandBytes().size() - linkedList.size()) / commandObject.getBaseRequest().getCommandBytes().size()) * 100.0f), ProgressType.PERCENTAGE));
                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                    return;
                                }
                                SendFileRes sendFileRes = new SendFileRes(CommandType.SEND_IMAGE, commandObject.getBaseRequest());
                                ResponseData responseData102 = new ResponseData();
                                responseData102.setDataList(arrayList109);
                                sendFileRes.setResponseData(responseData102);
                                commandObject.setCompleted(true);
                                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), 100, ProgressType.PERCENTAGE));
                                commandObject.getResponseListener().onResponse(sendFileRes);
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                            } else if (b21 == -122) {
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetScreenTimeOutReq)) {
                                    commandObject.setCompleted(true);
                                    ArrayList<String> arrayList110 = new ArrayList<>();
                                    arrayList110.add(Arrays.toString(bArr));
                                    SetScreenTimeOutRes setScreenTimeOutRes = new SetScreenTimeOutRes(CommandType.SET_SCREEN_TIMEOUT, commandObject.getBaseRequest());
                                    ResponseData responseData103 = new ResponseData();
                                    responseData103.setDataList(arrayList110);
                                    setScreenTimeOutRes.setResponseData(responseData103);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setScreenTimeOutRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                            } else if (b21 == -120) {
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof GetBatterySaverConfigReq)) {
                                    commandObject.setCompleted(true);
                                    ArrayList<String> arrayList111 = new ArrayList<>();
                                    arrayList111.add(Arrays.toString(bArr));
                                    GetBatterySaverConfigRes getBatterySaverConfigRes = new GetBatterySaverConfigRes(CommandType.GET_BATTERY_SAVER_INFO, commandObject.getBaseRequest());
                                    ResponseData responseData104 = new ResponseData();
                                    responseData104.setDataList(arrayList111);
                                    getBatterySaverConfigRes.setResponseData(responseData104);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(getBatterySaverConfigRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                            } else if (b21 == -119) {
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof GetSilentModeConfigReq)) {
                                    commandObject.setCompleted(true);
                                    ArrayList<String> arrayList112 = new ArrayList<>();
                                    arrayList112.add(Arrays.toString(bArr));
                                    GetSilentModeConfigRes getSilentModeConfigRes = new GetSilentModeConfigRes(CommandType.GET_BATTERY_SAVER_INFO, commandObject.getBaseRequest());
                                    ResponseData responseData105 = new ResponseData();
                                    responseData105.setDataList(arrayList112);
                                    getSilentModeConfigRes.setResponseData(responseData105);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(getSilentModeConfigRes);
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                            } else if (b21 == 7) {
                                if (commandObject != null && (commandObject.getBaseRequest() instanceof SetDiagnosticControlReq)) {
                                    ArrayList<String> arrayList113 = new ArrayList<>();
                                    arrayList113.add(Arrays.toString(bArr));
                                    SetDiagnosticControlRes setDiagnosticControlRes = new SetDiagnosticControlRes(CommandType.SET_DIAGNOSTIC_CONTROL, commandObject.getBaseRequest());
                                    ResponseData responseData106 = new ResponseData();
                                    responseData106.setDataList(arrayList113);
                                    setDiagnosticControlRes.setResponseData(responseData106);
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(setDiagnosticControlRes);
                                    LogHelper.d(g, "Set Diagnostic control response send from parser to impl!!!", ModuleNames.BLEABSTRACT.getModuleName());
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                            } else if (b21 != 8) {
                            } else {
                                if (commandObject == null || !(commandObject.getBaseRequest() instanceof GetDiagnosticFeatureTestReq)) {
                                    if (commandObject != null) {
                                        LogHelper.d("Current command is ", commandObject.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                                        return;
                                    } else {
                                        LogHelper.d("Current command is ", "null", ModuleNames.BLEABSTRACT.getModuleName());
                                        return;
                                    }
                                }
                                String arrays5 = Arrays.toString(bArr);
                                LogHelper.d(g + "Get Diagnostic Feature Test Resp is +++ ", arrays5, ModuleNames.BLEABSTRACT.getModuleName());
                                ArrayList<String> arrayList114 = new ArrayList<>();
                                arrayList114.add(arrays5);
                                ResponseData responseData107 = new ResponseData();
                                responseData107.setDataList(arrayList114);
                                GetDiagnosticFeatureTestRes getDiagnosticFeatureTestRes = new GetDiagnosticFeatureTestRes(CommandType.GET_DIAGNOSTIC_FEATURE_TEST, commandObject.getBaseRequest());
                                getDiagnosticFeatureTestRes.setResponseData(responseData107);
                                if (bArr[6] == 0) {
                                    this.b.postDelayed(new d0(this, commandObject), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                                    return;
                                }
                                if (bArr[5] != 1 && bArr[5] != 4 && bArr[5] != 5 && bArr[5] != 6) {
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(getDiagnosticFeatureTestRes);
                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                    return;
                                }
                                if (bArr[4] == -126) {
                                    commandObject.setCompleted(true);
                                    commandObject.getResponseListener().onResponse(getDiagnosticFeatureTestRes);
                                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                                }
                            }
                        }
                    } else if (bArr[0] == 1) {
                        if (bArr[1] == 5) {
                            if (bArr.length >= 6) {
                                if (bArr[4] == 1) {
                                    if (bArr[5] == 1) {
                                        findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                                    } else {
                                        findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.OFF);
                                    }
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.FIND_MY_PHONE, findMyPhoneRes, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                } else if (bArr[4] == 2) {
                                    if (bArr[5] == 1) {
                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.CAMERA, new CameraEventRes(CameraState.ENTER), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                    } else {
                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.CAMERA, new CameraEventRes(CameraState.EXIT), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                    }
                                } else if (bArr[4] == 3) {
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.CAMERA, new CameraEventRes(CameraState.CAPTURE), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                } else if (bArr[4] == 4) {
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.CALL_REJECT, new CallRejectRes(true), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                } else if (bArr[4] == 5) {
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.CALL_MUTE, new CallMuteRes(), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                }
                            }
                        } else if (bArr[1] == 0) {
                            if (bArr[4] == 1) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.PLAY_MUSIC, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            } else if (bArr[4] == 2) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.PAUSE_MUSIC, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            } else if (bArr[4] == 3) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.NEXT_MUSIC, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            } else if (bArr[4] == 4) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.PREVIOUS_MUSIC, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            }
                        } else if (bArr[1] == 1) {
                            if (bArr[4] > 2) {
                                int i2 = this.e;
                                if (i2 == -1) {
                                    if (bArr[4] < 100) {
                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.VOLUME_DOWN, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                    } else {
                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.VOLUME_UP, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                    }
                                } else if (i2 != bArr[4]) {
                                    if (i2 < bArr[4]) {
                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.VOLUME_UP, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                    } else {
                                        BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.VOLUME_DOWN, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                                    }
                                }
                                this.e = bArr[4];
                            } else if (bArr[4] == 1) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.VOLUME_UP, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            } else if (bArr[4] == 2) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.VOLUME_DOWN, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            }
                        } else if (bArr[1] == 8) {
                            if (bArr.length >= 5) {
                                int i3 = bArr[4] & 255;
                                SendPersonalizedMessageRes sendPersonalizedMessageRes = new SendPersonalizedMessageRes(-1, i3);
                                if (bArr[4] != 0 && bArr[4] != 1) {
                                    if (bArr[4] != 2 || bArr.length < 6) {
                                        return;
                                    }
                                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.PERSONALIZATION_BUTTON_EVENT, new SendPersonalizedMessageRes(bArr[5] & 255, i3)));
                                    return;
                                }
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.PERSONALIZATION_BUTTON_EVENT, sendPersonalizedMessageRes));
                            }
                        } else if (bArr[1] == 9) {
                            int i4 = bArr[4] & 255;
                            Long valueOf = Long.valueOf((((bArr[8] & 255) << 24) + ((bArr[7] & 255) << 16) + ((bArr[6] & 255) << 8) + (bArr[5] & 255)) * 1000);
                            int i5 = bArr[9];
                            byte[] bArr2 = new byte[i5];
                            int i6 = 10;
                            int i7 = 0;
                            while (i7 < i5) {
                                bArr2[i7] = bArr[i6];
                                i7++;
                                i6++;
                            }
                            String str16 = new String(bArr2, StandardCharsets.UTF_8);
                            int i8 = i6 + 1;
                            int i9 = bArr[i6];
                            byte[] bArr3 = new byte[i9];
                            int i10 = i8;
                            int i11 = 0;
                            while (i11 < i9) {
                                bArr3[i11] = bArr[i10];
                                i11++;
                                i10++;
                            }
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SEND_SOS_DATA, new SendSOSRes(i4, valueOf, str16, new String(bArr3, StandardCharsets.UTF_8))));
                        }
                    } else if (bArr[0] == 3) {
                        if (bArr[1] == -111) {
                            if (bArr[4] == 1) {
                                notifyNavigationEventRes = new NotifyNavigationEventRes(1, bArr[5] & 255, bArr[6] & 255);
                            } else {
                                notifyNavigationEventRes = new NotifyNavigationEventRes(bArr[4] & 255);
                            }
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.NOTIFY_NAVIGATION_EVENT, notifyNavigationEventRes, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        }
                    } else if (bArr[0] == 4) {
                        if (bArr[1] == 3) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.WATCH_FACE_CHANGE, new WatchFaceChange((bArr[5] & 255) | ((bArr[6] & 255) << 8)), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        } else if (bArr[1] == 7) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.REQUEST_NAVIGATION_EVENT, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        } else if (bArr[1] == 8) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.REQUEST_NAVIGATION_STATUS, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        } else if (bArr[1] == 9) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.REQUEST_NAVIGATION_FEATURE_CONTENT, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        }
                    } else if (bArr[0] == 6) {
                        if (bArr[1] == -127) {
                            ArrayList<String> arrayList115 = new ArrayList<>();
                            arrayList115.add(Arrays.toString(bArr));
                            ResponseData responseData108 = new ResponseData();
                            responseData108.setDataList(arrayList115);
                            LiveStepsRes liveStepsRes = new LiveStepsRes(CommandType.LIVE_STEPS, null);
                            ResponseType responseType2 = ResponseType.GET_LIVE_STEPS;
                            responseData108.setResponseType(responseType2);
                            liveStepsRes.setResponseData(responseData108);
                            if (liveStepsRes.getLiveStepsData().getLiveSteps() < 70000) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(responseType2, liveStepsRes, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            }
                        } else if (bArr[1] == Byte.MIN_VALUE) {
                            ResponseData responseData109 = new ResponseData();
                            ArrayList<String> arrayList116 = new ArrayList<>();
                            arrayList116.add(Arrays.toString(bArr));
                            responseData109.setDataList(arrayList116);
                            LiveHealthRes liveHealthRes = new LiveHealthRes(CommandType.LIVE_HEALTH, null);
                            ResponseType responseType3 = ResponseType.GET_LIVE_HEALTH_DATA;
                            responseData109.setResponseType(responseType3);
                            liveHealthRes.setResponseData(responseData109);
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(responseType3, liveHealthRes, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        } else if (bArr[1] == -125) {
                            ResponseData responseData110 = new ResponseData();
                            ArrayList<String> arrayList117 = new ArrayList<>();
                            arrayList117.add(Arrays.toString(bArr));
                            responseData110.setDataList(arrayList117);
                            RawEcgRes rawEcgRes = new RawEcgRes(CommandType.LIVE_RAW_ECG, null);
                            rawEcgRes.setResponseData(responseData110);
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.GET_LIVE_RAW_ECG, rawEcgRes, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        }
                    } else if (bArr[0] == 0) {
                        if (bArr[1] == 0) {
                            if (bArr[4] == 1) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SOS, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            } else if (bArr[4] == 2) {
                                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.FALL_DOWN, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                            }
                        } else if (bArr[1] == 1) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.PROBE, ResponseStatus.RESPONSE_STATUS_SUCCESS));
                        } else if (bArr[1] == 2) {
                            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SEND_QUICK_REPLY, new SendQuickReplyRes(bArr[4] & 255)));
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (commandObject == null || commandObject.getResponseListener() == null) {
                return;
            }
            Error error = new Error(Type.COMMAND_REQUEST_ERROR, e2.getMessage());
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onFailure(error);
        }
    }

    public final void i(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.GET_ACTIVITY_SUMMARY);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            String string = BlePreferenceManager.getString(context, CommonPreference.FIRMWARE_CAPABILITY.getName() + this.f7576a, "");
            FirmwareCapabilityInfo firmwareCapabilityInfo = BleUtils.isEmpty(string) ? null : (FirmwareCapabilityInfo) new Gson().fromJson(string, (Class<Object>) FirmwareCapabilityInfo.class);
            GetActivitySummaryRes getActivitySummaryRes = new GetActivitySummaryRes(CommandType.GET_ACTIVITY_SUMMARY, commandObject.getBaseRequest());
            getActivitySummaryRes.setResponseData(responseData);
            getActivitySummaryRes.setFirmwareCapabilityInfo(firmwareCapabilityInfo);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(getActivitySummaryRes);
            LogHelper.d(g, "GET_ACTIVITY_SUMMARY response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new a(this));
        }
    }

    public final void j(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.CALORIE_DATA);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            String string = BlePreferenceManager.getString(context, CommonPreference.FIRMWARE_CAPABILITY.getName() + this.f7576a, "");
            FirmwareCapabilityInfo firmwareCapabilityInfo = BleUtils.isEmpty(string) ? null : (FirmwareCapabilityInfo) new Gson().fromJson(string, (Class<Object>) FirmwareCapabilityInfo.class);
            CalorieDataRes calorieDataRes = new CalorieDataRes(CommandType.GET_CALORIE, commandObject.getBaseRequest());
            calorieDataRes.setResponseData(responseData);
            calorieDataRes.setFirmwareCapabilityInfo(firmwareCapabilityInfo);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(calorieDataRes);
            LogHelper.d(g, "GET_CALORIE_DATA response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new d(this));
        }
    }

    public final void k(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.GET_CONFIGURED_ACTIVITIES);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            GetConfiguredActivityListRes getConfiguredActivityListRes = new GetConfiguredActivityListRes(CommandType.GET_CONFIGURED_ACTIVITIES, commandObject.getBaseRequest());
            getConfiguredActivityListRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(getConfiguredActivityListRes);
            LogHelper.d(g, "GET_CONFIGURED_ACTIVITIES response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new l(this));
        }
    }

    public final void l(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.DISTANCE_DATA);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            String string = BlePreferenceManager.getString(context, CommonPreference.FIRMWARE_CAPABILITY.getName() + this.f7576a, "");
            FirmwareCapabilityInfo firmwareCapabilityInfo = BleUtils.isEmpty(string) ? null : (FirmwareCapabilityInfo) new Gson().fromJson(string, (Class<Object>) FirmwareCapabilityInfo.class);
            DistanceDataRes distanceDataRes = new DistanceDataRes(CommandType.GET_DISTANCE, commandObject.getBaseRequest());
            distanceDataRes.setResponseData(responseData);
            distanceDataRes.setFirmwareCapabilityInfo(firmwareCapabilityInfo);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(distanceDataRes);
            LogHelper.d(g, "GET_DISTANCE_DATA response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new c(this));
        }
    }

    public final boolean m(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                responseData.setDataList(new ArrayList<>(this.d));
                ArrayList<String> arrayList2 = this.d;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    this.d.clear();
                }
                responseData.setResponseType(ResponseType.HISTORY_FOR_AUTOMATIC_HR_BP);
                if (commandObject != null && commandObject.getRequestPayload() != null) {
                    RequestPayload requestPayload = commandObject.getRequestPayload();
                    if (requestPayload != null) {
                        int day = requestPayload.getDay();
                        int startHour = requestPayload.getStartHour();
                        int endHour = requestPayload.getEndHour();
                        responseData.setDay(day);
                        responseData.setStartTime(startHour);
                        responseData.setEndTime(endHour);
                        responseData.setTimeInterval(requestPayload.getTimeInterval());
                    }
                    this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                }
                responseData.setMacAddress(this.f7576a);
                if (BleUtils.isEmpty(this.f7576a)) {
                    return true;
                }
                HrBpDataRes hrBpDataRes = new HrBpDataRes(CommandType.HR_BP, commandObject.getBaseRequest());
                hrBpDataRes.setResponseData(responseData);
                commandObject.setCompleted(true);
                commandObject.getResponseListener().onResponse(hrBpDataRes);
                this.b.post(new r(this));
            }
        }
        return false;
    }

    public final void n(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.GET_IMAGES_LIST);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            GetImageIdListRes getImageIdListRes = new GetImageIdListRes(CommandType.GET_IMAGES_LIST, commandObject.getBaseRequest());
            getImageIdListRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(getImageIdListRes);
            LogHelper.d(g, "GET_IMAGES_LIST response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.removeCallbacksAndMessages(null);
            this.b.post(new m(this));
        }
    }

    public final void o(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.MANUAL_SPO2_DATA);
            if (commandObject != null && commandObject.getRequestPayload() != null) {
                RequestPayload requestPayload = commandObject.getRequestPayload();
                if (requestPayload != null) {
                    int day = requestPayload.getDay();
                    int startHour = requestPayload.getStartHour();
                    int endHour = requestPayload.getEndHour();
                    responseData.setDay(day);
                    responseData.setStartTime(startHour);
                    responseData.setEndTime(endHour);
                }
                this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            }
            responseData.setMacAddress(this.f7576a);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            Spo2DataRes spo2DataRes = new Spo2DataRes(CommandType.READ_MANUAL_SPO2, commandObject.getBaseRequest());
            spo2DataRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(spo2DataRes);
            LogHelper.d(g, "Temperature response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new g(this));
        }
    }

    public void onReadFromService(Context context, BluetoothGattCharacteristic bluetoothGattCharacteristic, CommandObject commandObject) {
        if (bluetoothGattCharacteristic != null) {
            String str = g;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.d(str, "on characteristics read for UUID " + bluetoothGattCharacteristic.getUuid(), moduleNames.getModuleName());
            LogHelper.d(str, "UUID " + bluetoothGattCharacteristic.getUuid(), moduleNames.getModuleName());
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            BleDeviceInfo bleDeviceInfo = BleDeviceInfo.getInstance();
            String str2 = null;
            if (uuid.equalsIgnoreCase(BleUUID.BATTERY_LEVEL_CHAR_UUID)) {
                if (bluetoothGattCharacteristic.getValue() != null) {
                    byte b2 = bluetoothGattCharacteristic.getValue()[0];
                    LogHelper.d(str, "Battery: " + ((int) b2), moduleNames.getModuleName());
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadBatteryLevelRes readBatteryLevelRes = new ReadBatteryLevelRes(CommandType.READ_BATTERY_LEVEL, commandObject.getBaseRequest());
                        readBatteryLevelRes.setBatteryLevel(b2);
                        commandObject.getResponseListener().onResponse(readBatteryLevelRes);
                        commandObject.setCompleted(true);
                    }
                }
                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
            } else {
                try {
                    str2 = a(bluetoothGattCharacteristic.getValue(), this.f);
                } catch (Exception e2) {
                    LogHelper.d(g, e2.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
                if (uuid.equalsIgnoreCase(BleUUID.DEVICE_NAME_CHAR_UUID)) {
                    bleDeviceInfo.setmDeviceName(str2);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceNameRes readDeviceNameRes = new ReadDeviceNameRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceNameRes.setName(str2);
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceNameRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                } else if (uuid.equalsIgnoreCase(BleUUID.MANUFACTURER_NAME_CHAR_UUID)) {
                    bleDeviceInfo.setmManufacturerName(str2);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceManufacturerRes readDeviceManufacturerRes = new ReadDeviceManufacturerRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceManufacturerRes.setManufacturerName(str2);
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceManufacturerRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                } else if (uuid.equalsIgnoreCase(BleUUID.SERIAL_NUMBER_CHAR_UUID)) {
                    bleDeviceInfo.setSerialNumber(str2);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceSerialNumberRes readDeviceSerialNumberRes = new ReadDeviceSerialNumberRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceSerialNumberRes.setSerialNumber(str2);
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceSerialNumberRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                } else if (uuid.equalsIgnoreCase(BleUUID.MODEL_NUMBER_CHAR_UUID)) {
                    bleDeviceInfo.setmModelNumber(str2);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceModelRes readDeviceModelRes = new ReadDeviceModelRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceModelRes.setModelNumber(str2);
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceModelRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                } else if (uuid.equalsIgnoreCase(BleUUID.HW_REVISION_CHAR_UUID)) {
                    bleDeviceInfo.setHwRevision(str2);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceHardwareVersionRes readDeviceHardwareVersionRes = new ReadDeviceHardwareVersionRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceHardwareVersionRes.setHardwareVersion(str2);
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceHardwareVersionRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                } else if (uuid.equalsIgnoreCase(BleUUID.FW_REVISION_CHAR_UUID)) {
                    bleDeviceInfo.setFirmwareRevision(str2, context);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceFirmwareVersionRes readDeviceFirmwareVersionRes = new ReadDeviceFirmwareVersionRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceFirmwareVersionRes.setFirmwareVersion(str2);
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceFirmwareVersionRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                } else if (uuid.equalsIgnoreCase(BleUUID.SW_REVISION_CHAR_UUID)) {
                    bleDeviceInfo.setSoftwareRevision(str2);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceSoftwareVersionRes readDeviceSoftwareVersionRes = new ReadDeviceSoftwareVersionRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceSoftwareVersionRes.setSoftwareVersion(str2);
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceSoftwareVersionRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                } else if (uuid.equalsIgnoreCase(BleUUID.SYSTEM_ID_CHAR_UUID)) {
                    bleDeviceInfo.setmSystemId(str2);
                    if (commandObject != null && commandObject.getResponseListener() != null) {
                        ReadDeviceInfoRes readDeviceInfoRes = new ReadDeviceInfoRes(CommandType.READ_DEVICE_INFO, commandObject.getBaseRequest());
                        readDeviceInfoRes.setBleDeviceInfo(BleDeviceInfo.getInstance());
                        commandObject.setCompleted(true);
                        commandObject.getResponseListener().onResponse(readDeviceInfoRes);
                    }
                    BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                }
            }
            LogHelper.d(g, "Ble Info updates: " + BleDeviceInfo.getInstance().toString(), ModuleNames.BLEABSTRACT.getModuleName());
            BleEventBusManager.getInstance().getEventBus().post(new BleInfoEvent());
        }
        LogHelper.d(g, "inside read characteristics", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public final void p(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = ((Byte.parseByte(split[5].trim()) & 255) << 8) | (Byte.parseByte(split[4].trim()) & 255);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 5, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            ArrayList<String> arrayList2 = new ArrayList<>(this.d);
            if (commandObject != null && (commandObject.getBaseRequest() instanceof GetNavigationFavouriteLocationReq)) {
                GetNavigationFavouriteLocationRes getNavigationFavouriteLocationRes = new GetNavigationFavouriteLocationRes(CommandType.GET_NAVIGATION_FAVOURITE_LOCATION, commandObject.getBaseRequest());
                ResponseData responseData = new ResponseData();
                responseData.setDataList(arrayList2);
                getNavigationFavouriteLocationRes.setResponseData(responseData);
                commandObject.setCompleted(true);
                commandObject.getResponseListener().onResponse(getNavigationFavouriteLocationRes);
                LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
            } else {
                LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
            }
            BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
            ArrayList<String> arrayList3 = this.d;
            if (arrayList3 != null && arrayList3.size() > 0) {
                this.d.clear();
            }
            LogHelper.d(g, "GET_NAVIGATION_FAVOURITE_LOCATION response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
        }
    }

    public final boolean q(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                responseData.setDataList(new ArrayList<>(this.d));
                ArrayList<String> arrayList2 = this.d;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    this.d.clear();
                }
                responseData.setResponseType(ResponseType.GET_PERIODIC_SPO2);
                if (commandObject != null && commandObject.getRequestPayload() != null) {
                    RequestPayload requestPayload = commandObject.getRequestPayload();
                    if (requestPayload != null) {
                        int day = requestPayload.getDay();
                        int startHour = requestPayload.getStartHour();
                        int endHour = requestPayload.getEndHour();
                        responseData.setDay(day);
                        responseData.setStartTime(startHour);
                        responseData.setEndTime(endHour);
                    }
                    this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
                }
                responseData.setMacAddress(this.f7576a);
                if (BleUtils.isEmpty(this.f7576a)) {
                    return true;
                }
                Spo2PeriodicDataRes spo2PeriodicDataRes = new Spo2PeriodicDataRes(CommandType.GET_PERIODIC_SPO2, commandObject.getBaseRequest());
                spo2PeriodicDataRes.setResponseData(responseData);
                commandObject.setCompleted(true);
                commandObject.getResponseListener().onResponse(spo2PeriodicDataRes);
                LogHelper.d(g, "Temperature response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
                this.b.post(new p(this));
            }
        }
        return false;
    }

    public final boolean r(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() != this.c && Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                LogHelper.d(g, "parseQrCodeResponse: ELSE");
            } else {
                ArrayList<String> arrayList2 = new ArrayList<>(this.d);
                if (commandObject != null && (commandObject.getBaseRequest() instanceof GetQRCodeReq)) {
                    GetQRCodeRes getQRCodeRes = new GetQRCodeRes(CommandType.GET_QR_TRAY_CODE, commandObject.getBaseRequest());
                    ResponseData responseData = new ResponseData();
                    responseData.setDataList(arrayList2);
                    getQRCodeRes.setResponseData(responseData);
                    commandObject.setCompleted(true);
                    commandObject.getResponseListener().onResponse(getQRCodeRes);
                    LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                } else {
                    LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                }
                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                ArrayList<String> arrayList3 = this.d;
                if (arrayList3 != null && arrayList3.size() > 0) {
                    this.d.clear();
                }
            }
        }
        return false;
    }

    public final boolean s(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                ArrayList<String> arrayList2 = new ArrayList<>(this.d);
                if (commandObject != null && (commandObject.getBaseRequest() instanceof GetQuickReplyListReq)) {
                    GetQuickReplyListRes getQuickReplyListRes = new GetQuickReplyListRes(CommandType.GET_QUICK_REPLY, commandObject.getBaseRequest());
                    ResponseData responseData = new ResponseData();
                    responseData.setDataList(arrayList2);
                    getQuickReplyListRes.setResponseData(responseData);
                    commandObject.setCompleted(true);
                    commandObject.getResponseListener().onResponse(getQuickReplyListRes);
                    LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                } else {
                    LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                }
                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                ArrayList<String> arrayList3 = this.d;
                if (arrayList3 != null && arrayList3.size() > 0) {
                    this.d.clear();
                }
            }
        }
        return false;
    }

    public final void t(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.READ_RAW_ACCELEROMETER_HISTORY);
            if (commandObject != null && commandObject.getRequestPayload() != null) {
                RequestPayload requestPayload = commandObject.getRequestPayload();
                if (requestPayload != null) {
                    int day = requestPayload.getDay();
                    int startHour = requestPayload.getStartHour();
                    int endHour = requestPayload.getEndHour();
                    responseData.setDay(day);
                    responseData.setStartTime(startHour);
                    responseData.setEndTime(endHour);
                }
                this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            }
            responseData.setMacAddress(this.f7576a);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            ReadRawAccelerometerDataRes readRawAccelerometerDataRes = new ReadRawAccelerometerDataRes(CommandType.READ_RAW_ACCELEROMETER_HISTORY, commandObject.getBaseRequest());
            readRawAccelerometerDataRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(readRawAccelerometerDataRes);
            LogHelper.d(g, "Temperature response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new j(this));
        }
    }

    public final void u(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.READ_RAW_PPG_HISTORY);
            if (commandObject != null && commandObject.getRequestPayload() != null) {
                RequestPayload requestPayload = commandObject.getRequestPayload();
                if (requestPayload != null) {
                    int day = requestPayload.getDay();
                    int startHour = requestPayload.getStartHour();
                    int endHour = requestPayload.getEndHour();
                    responseData.setDay(day);
                    responseData.setStartTime(startHour);
                    responseData.setEndTime(endHour);
                }
                this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            }
            responseData.setMacAddress(this.f7576a);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            ReadRawPPGDataRes readRawPPGDataRes = new ReadRawPPGDataRes(CommandType.READ_RAW_PPG_HISTORY, commandObject.getBaseRequest());
            readRawPPGDataRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(readRawPPGDataRes);
            LogHelper.d(g, "Raw PPG Data response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new h(this));
        }
    }

    public final void v(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.READ_SEDENTARY_HISTORY);
            if (commandObject != null && commandObject.getRequestPayload() != null) {
                RequestPayload requestPayload = commandObject.getRequestPayload();
                if (requestPayload != null) {
                    int day = requestPayload.getDay();
                    int startHour = requestPayload.getStartHour();
                    int endHour = requestPayload.getEndHour();
                    responseData.setDay(day);
                    responseData.setStartTime(startHour);
                    responseData.setEndTime(endHour);
                }
                this.f7576a = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            }
            responseData.setMacAddress(this.f7576a);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            ReadSedentaryDataRes readSedentaryDataRes = new ReadSedentaryDataRes(CommandType.READ_SEDENTARY_HISTORY, commandObject.getBaseRequest());
            readSedentaryDataRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(readSedentaryDataRes);
            LogHelper.d(g, "Sedentary response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new i(this));
        }
    }

    public final void w(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.GET_SENS_AI_SUMMARY_DATA);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            String string = BlePreferenceManager.getString(context, CommonPreference.FIRMWARE_CAPABILITY.getName() + this.f7576a, "");
            FirmwareCapabilityInfo firmwareCapabilityInfo = BleUtils.isEmpty(string) ? null : (FirmwareCapabilityInfo) new Gson().fromJson(string, (Class<Object>) FirmwareCapabilityInfo.class);
            GetSensAISummaryDataRes getSensAISummaryDataRes = new GetSensAISummaryDataRes(CommandType.GET_SENS_AI_SUMMARY_DATA, commandObject.getBaseRequest());
            getSensAISummaryDataRes.setResponseData(responseData);
            getSensAISummaryDataRes.setFirmwareCapabilityInfo(firmwareCapabilityInfo);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(getSensAISummaryDataRes);
            LogHelper.d(g, "GET_SENS_AI_SUMMARY_DATA response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new b(this));
        }
    }

    public final void x(Context context, CommandObject commandObject, byte[] bArr) {
        RequestPayload requestPayload;
        int length;
        ResponseData responseData = new ResponseData();
        ArrayList<String> arrayList = this.d;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        String str = this.d.get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
            this.c = 0;
            this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
            LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
            if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                length = bArr.length - 12;
            } else {
                length = bArr.length - 4;
            }
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
        } else {
            commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
        }
        if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
            responseData.setDataList(new ArrayList<>(this.d));
            ArrayList<String> arrayList2 = this.d;
            if (arrayList2 != null && arrayList2.size() > 0) {
                this.d.clear();
            }
            responseData.setResponseType(ResponseType.GET_SENS_AI_SUMMARY_DETAILS);
            if (commandObject != null && commandObject.getRequestPayload() != null && (requestPayload = commandObject.getRequestPayload()) != null) {
                int day = requestPayload.getDay();
                int startHour = requestPayload.getStartHour();
                int endHour = requestPayload.getEndHour();
                responseData.setDay(day);
                responseData.setStartTime(startHour);
                responseData.setEndTime(endHour);
            }
            String str2 = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
            this.f7576a = str2;
            responseData.setMacAddress(str2);
            if (BleUtils.isEmpty(this.f7576a)) {
                return;
            }
            GetSensAISummaryDetailsDataRes getSensAISummaryDetailsDataRes = new GetSensAISummaryDetailsDataRes(CommandType.GET_SENS_AI_SUMMARY_DETAILS, commandObject.getBaseRequest());
            getSensAISummaryDetailsDataRes.setResponseData(responseData);
            commandObject.setCompleted(true);
            commandObject.getResponseListener().onResponse(getSensAISummaryDetailsDataRes);
            LogHelper.d(g, "GET_SENS_AI_SUMMARY_DETAILS response send to Impl", ModuleNames.BLEABSTRACT.getModuleName());
            this.b.post(new f(this));
        }
    }

    public final boolean y(Context context, CommandObject commandObject, byte[] bArr) {
        int length;
        ArrayList<String> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            String str = this.d.get(0);
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            if (Byte.parseByte(split[0]) == Byte.MAX_VALUE) {
                this.c = 0;
                this.c = (Byte.parseByte(split[4].trim()) & 255) | ((Byte.parseByte(split[5].trim()) & 255) << 8);
                LogHelper.d(g, "value of  packet size and size is ++" + this.c + " databytes" + this.d.size(), ModuleNames.BLEABSTRACT.getModuleName());
                if (((bArr[2] & 255) | ((bArr[3] & 255) << 8)) == 0) {
                    length = bArr.length - 12;
                } else {
                    length = bArr.length - 4;
                }
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), length, ProgressType.DATA_BYTES));
            } else {
                commandObject.getResponseListener().onProgressUpdate(new ProgressDataBean(commandObject.getBaseRequest(), bArr.length - 4, ProgressType.DATA_BYTES));
            }
            if (this.d.size() == this.c || Byte.parseByte(split[0]) != Byte.MAX_VALUE) {
                ArrayList<String> arrayList2 = new ArrayList<>(this.d);
                if (commandObject != null && (commandObject.getBaseRequest() instanceof GetSmartAlertConfigReq)) {
                    GetSmartAlertConfigRes getSmartAlertConfigRes = new GetSmartAlertConfigRes(CommandType.GET_SMART_ALERT_CONFIG, commandObject.getBaseRequest());
                    ResponseData responseData = new ResponseData();
                    responseData.setDataList(arrayList2);
                    getSmartAlertConfigRes.setResponseData(responseData);
                    commandObject.setCompleted(true);
                    commandObject.getResponseListener().onResponse(getSmartAlertConfigRes);
                    LogHelper.d(g, "CURRENT COMMAND IS Not NULL " + commandObject, ModuleNames.BLEABSTRACT.getModuleName());
                } else {
                    LogHelper.d(g, "CURRENT COMMAND IS NULL", ModuleNames.BLEABSTRACT.getModuleName());
                }
                BleEventBusManager.getInstance().getEventBus().post(new ProcessNextItemEvent());
                ArrayList<String> arrayList3 = this.d;
                if (arrayList3 != null && arrayList3.size() > 0) {
                    this.d.clear();
                }
            }
        }
        return false;
    }
}
