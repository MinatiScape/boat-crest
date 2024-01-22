package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.formatter.LeonardoFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.AutoActivityDetectionModel;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.FirmwareCapabilityData;
import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.bleabstract.models.VibrationModel;
import com.coveiot.android.bleabstract.models.WatchFace;
import com.coveiot.android.bleabstract.models.WatchFaceBackgroundInfo;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.ActivityDetailsDataRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ChangeWatchFaceRequest;
import com.coveiot.android.bleabstract.request.ConfigureActivityListRequest;
import com.coveiot.android.bleabstract.request.CustomMessageRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.DeleteImageRequest;
import com.coveiot.android.bleabstract.request.DeleteWatchFaceRequest;
import com.coveiot.android.bleabstract.request.ExitRemoteCameraRequest;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.GetAlarmDataRequest;
import com.coveiot.android.bleabstract.request.GetAutoActivityDetectionSettingsRequest;
import com.coveiot.android.bleabstract.request.GetConfiguredActivityListRequest;
import com.coveiot.android.bleabstract.request.GetDNDModeSettingsRequest;
import com.coveiot.android.bleabstract.request.GetFirmwareCapabilityRequest;
import com.coveiot.android.bleabstract.request.GetImageIdListRequest;
import com.coveiot.android.bleabstract.request.GetLatestHealthDataRequest;
import com.coveiot.android.bleabstract.request.GetLiftWristSettingsRequest;
import com.coveiot.android.bleabstract.request.GetQRCodeRequest;
import com.coveiot.android.bleabstract.request.GetSOSConfigRequest;
import com.coveiot.android.bleabstract.request.GetSOSRecordRequest;
import com.coveiot.android.bleabstract.request.GetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.request.GetWatchFaceListRequest;
import com.coveiot.android.bleabstract.request.GetWatchFacePositionRequest;
import com.coveiot.android.bleabstract.request.GetWatchfaceBackgroundRequest;
import com.coveiot.android.bleabstract.request.GetWatchfaceBackgroundResponse;
import com.coveiot.android.bleabstract.request.LiveRawPPGControlRequest;
import com.coveiot.android.bleabstract.request.LiveTemperatureControlRequest;
import com.coveiot.android.bleabstract.request.MotorVibrationRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.PairingFlowRequest;
import com.coveiot.android.bleabstract.request.PeriodicSPO2BleRequest;
import com.coveiot.android.bleabstract.request.PersonalizedMessageRequest;
import com.coveiot.android.bleabstract.request.QRTrayCodeRequest;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ReadRawAccelerometerDataRequest;
import com.coveiot.android.bleabstract.request.ReadRawPPGHistoryDataRequest;
import com.coveiot.android.bleabstract.request.ReadSedentaryDataRequest;
import com.coveiot.android.bleabstract.request.SendFileRequest;
import com.coveiot.android.bleabstract.request.SendImageRequest;
import com.coveiot.android.bleabstract.request.SetAutoActivityDetectionSettingsRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataRequest;
import com.coveiot.android.bleabstract.request.SetMusicPlaybackStateChangedRequest;
import com.coveiot.android.bleabstract.request.SetMusicVolumePercentageChangedRequest;
import com.coveiot.android.bleabstract.request.SetNonSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetSOSConfigRequest;
import com.coveiot.android.bleabstract.request.SetScreenTimeOutRequest;
import com.coveiot.android.bleabstract.request.SetSleepTargetRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SetWatchFaceImageIdRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.Spo2TimeIntervalRequest;
import com.coveiot.android.bleabstract.request.SportNotificationControlRequest;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StopFindMyWatchRequest;
import com.coveiot.android.bleabstract.request.StressDataRequest;
import com.coveiot.android.bleabstract.request.StressTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.TemperatureDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.GetAutoActivityDetectionResponse;
import com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse;
import com.coveiot.android.bleabstract.response.GetFirmwareCapabilityResponse;
import com.coveiot.android.bleabstract.response.GetImageListResponse;
import com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse;
import com.coveiot.android.bleabstract.response.GetSOSConfigResponse;
import com.coveiot.android.bleabstract.response.GetSOSRecordsResponse;
import com.coveiot.android.bleabstract.response.GetSmartAlertConfigResponse;
import com.coveiot.android.bleabstract.response.GetWatchFaceListResponse;
import com.coveiot.android.bleabstract.response.HealthData;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.response.PeriodicSpo2Response;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ReadRawAccelerometerDataResponse;
import com.coveiot.android.bleabstract.response.ReadRawPPGDataResponse;
import com.coveiot.android.bleabstract.response.ReadSedentaryResponse;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.response.WatchFacePositionResponse;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.AppAutoActivityDetectionModel;
import com.coveiot.sdk.ble.api.model.AppNotificationType;
import com.coveiot.sdk.ble.api.model.BleActivityDataHolder;
import com.coveiot.sdk.ble.api.model.BleActivityDetailData;
import com.coveiot.sdk.ble.api.model.BleActivitySummaryData;
import com.coveiot.sdk.ble.api.model.BleAlarmInfo;
import com.coveiot.sdk.ble.api.model.BleGetSOSConfig;
import com.coveiot.sdk.ble.api.model.BleGetSOSRecords;
import com.coveiot.sdk.ble.api.model.BleGetSmartAlertConfigData;
import com.coveiot.sdk.ble.api.model.BleHealthData;
import com.coveiot.sdk.ble.api.model.BleSmartAlertAppData;
import com.coveiot.sdk.ble.api.model.BleVibrationModel;
import com.coveiot.sdk.ble.api.model.BleWatchFaceBackgroundInfo;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.api.model.MusicPlayBackState;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.ProgressType;
import com.coveiot.sdk.ble.api.request.ActivityDetailsDataReq;
import com.coveiot.sdk.ble.api.request.CZ2MessageContentReq;
import com.coveiot.sdk.ble.api.request.ChangeTemperatureUnitReq;
import com.coveiot.sdk.ble.api.request.ChangeWatchFaceBGReq;
import com.coveiot.sdk.ble.api.request.ConfigureActivityListReq;
import com.coveiot.sdk.ble.api.request.CustomMessageReq;
import com.coveiot.sdk.ble.api.request.DNDModeReq;
import com.coveiot.sdk.ble.api.request.DeleteImageReq;
import com.coveiot.sdk.ble.api.request.DeleteWatchFaceReq;
import com.coveiot.sdk.ble.api.request.FindMyWatchReq;
import com.coveiot.sdk.ble.api.request.GetActivitySummaryReq;
import com.coveiot.sdk.ble.api.request.GetAlarmListReq;
import com.coveiot.sdk.ble.api.request.GetAutoActivityDetectionSettingsReq;
import com.coveiot.sdk.ble.api.request.GetConfiguredActivityListReq;
import com.coveiot.sdk.ble.api.request.GetCurrentWatchFaceReq;
import com.coveiot.sdk.ble.api.request.GetDNDModeSettingsReq;
import com.coveiot.sdk.ble.api.request.GetFirmwareCapabilityReq;
import com.coveiot.sdk.ble.api.request.GetImageIdListReq;
import com.coveiot.sdk.ble.api.request.GetLatestHealthDataReq;
import com.coveiot.sdk.ble.api.request.GetLiftWristViewReq;
import com.coveiot.sdk.ble.api.request.GetQRCodeReq;
import com.coveiot.sdk.ble.api.request.GetSOSConfigReq;
import com.coveiot.sdk.ble.api.request.GetSOSRecordsReq;
import com.coveiot.sdk.ble.api.request.GetSmartAlertConfigReq;
import com.coveiot.sdk.ble.api.request.GetWatchFaceListReq;
import com.coveiot.sdk.ble.api.request.GetWatchfaceBackgroundReq;
import com.coveiot.sdk.ble.api.request.LiftWristViewReq;
import com.coveiot.sdk.ble.api.request.PairingFlowCmdReq;
import com.coveiot.sdk.ble.api.request.PeriodicSPO2BaseReq;
import com.coveiot.sdk.ble.api.request.PersonalizedMessageReq;
import com.coveiot.sdk.ble.api.request.ReadRawAccelerometerDataReq;
import com.coveiot.sdk.ble.api.request.ReadRawPPGDataReq;
import com.coveiot.sdk.ble.api.request.ReadSedentaryDataReq;
import com.coveiot.sdk.ble.api.request.SPO2DataReq;
import com.coveiot.sdk.ble.api.request.SaveFitnessProfileReq;
import com.coveiot.sdk.ble.api.request.SendFileReq;
import com.coveiot.sdk.ble.api.request.SendImageReq;
import com.coveiot.sdk.ble.api.request.SetAutoActivityDetectionSettingsReq;
import com.coveiot.sdk.ble.api.request.SetCameraStatusReq;
import com.coveiot.sdk.ble.api.request.SetCurrentWatchFaceReq;
import com.coveiot.sdk.ble.api.request.SetMediaInfoReq;
import com.coveiot.sdk.ble.api.request.SetMusicPlayBackStatusReq;
import com.coveiot.sdk.ble.api.request.SetMusicVolumePercentageReq;
import com.coveiot.sdk.ble.api.request.SetNonSmartAlertApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetQRTrayCodeReq;
import com.coveiot.sdk.ble.api.request.SetSOSConfigReq;
import com.coveiot.sdk.ble.api.request.SetScreenTimeOutReq;
import com.coveiot.sdk.ble.api.request.SetSleepTargetReq;
import com.coveiot.sdk.ble.api.request.SetSmartAlertApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetSmartAlertConfigReq;
import com.coveiot.sdk.ble.api.request.SetSportNotificationReq;
import com.coveiot.sdk.ble.api.request.SetStressIntervalControlReq;
import com.coveiot.sdk.ble.api.request.SetVibrationReq;
import com.coveiot.sdk.ble.api.request.SleepDataReq;
import com.coveiot.sdk.ble.api.request.SportsNotificationReq;
import com.coveiot.sdk.ble.api.request.StressDataDataReq;
import com.coveiot.sdk.ble.api.request.TemperatureDataDataReq;
import com.coveiot.sdk.ble.api.request.TemperatureMeasurementReq;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticSpo2Req;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticTemperatureReq;
import com.coveiot.sdk.ble.api.request.V7PPGControlReq;
import com.coveiot.sdk.ble.api.request.WatchFaceUploadReq;
import com.coveiot.sdk.ble.api.response.ActivityDetailsDataRes;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.CallMuteRes;
import com.coveiot.sdk.ble.api.response.CallRejectRes;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.ChangeTemperatureUnitRes;
import com.coveiot.sdk.ble.api.response.ConfigureActivityListRes;
import com.coveiot.sdk.ble.api.response.DeleteImageRes;
import com.coveiot.sdk.ble.api.response.DeleteWatchFaceRes;
import com.coveiot.sdk.ble.api.response.FindMyWatchRes;
import com.coveiot.sdk.ble.api.response.GetActivitySummaryRes;
import com.coveiot.sdk.ble.api.response.GetAlarmListRes;
import com.coveiot.sdk.ble.api.response.GetAutoActivityDetectionSettingsRes;
import com.coveiot.sdk.ble.api.response.GetConfiguredActivityListRes;
import com.coveiot.sdk.ble.api.response.GetCurrentWatchFaceRes;
import com.coveiot.sdk.ble.api.response.GetDNDModeSettingsRes;
import com.coveiot.sdk.ble.api.response.GetFirmwareCapabilityRes;
import com.coveiot.sdk.ble.api.response.GetImageIdListRes;
import com.coveiot.sdk.ble.api.response.GetLatestHealthDataRes;
import com.coveiot.sdk.ble.api.response.GetQRCodeRes;
import com.coveiot.sdk.ble.api.response.GetSOSConfigRes;
import com.coveiot.sdk.ble.api.response.GetSOSRecordsRes;
import com.coveiot.sdk.ble.api.response.GetSmartAlertConfigRes;
import com.coveiot.sdk.ble.api.response.GetWatchFaceListRes;
import com.coveiot.sdk.ble.api.response.GetWatchfaceBackgroundRes;
import com.coveiot.sdk.ble.api.response.LiftWristViewRes;
import com.coveiot.sdk.ble.api.response.LiveTemperatureRes;
import com.coveiot.sdk.ble.api.response.MusicStatusNotificationRes;
import com.coveiot.sdk.ble.api.response.PairingFlowCmdRes;
import com.coveiot.sdk.ble.api.response.ReadRawAccelerometerDataRes;
import com.coveiot.sdk.ble.api.response.ReadRawPPGDataRes;
import com.coveiot.sdk.ble.api.response.ReadSedentaryDataRes;
import com.coveiot.sdk.ble.api.response.SendPersonalizedMessageRes;
import com.coveiot.sdk.ble.api.response.SendSOSRes;
import com.coveiot.sdk.ble.api.response.SetAutoActivityDetectionRes;
import com.coveiot.sdk.ble.api.response.SetCameraStatusRes;
import com.coveiot.sdk.ble.api.response.SetCurrentWatchFaceRes;
import com.coveiot.sdk.ble.api.response.SetDNDModeRes;
import com.coveiot.sdk.ble.api.response.SetNonSmartAlertApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetQRTrayCodeRes;
import com.coveiot.sdk.ble.api.response.SetSOSConfigRes;
import com.coveiot.sdk.ble.api.response.SetScreenTimeOutRes;
import com.coveiot.sdk.ble.api.response.SetSleepTargetRes;
import com.coveiot.sdk.ble.api.response.SetSmartAlertApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetSmartAlertConfigRes;
import com.coveiot.sdk.ble.api.response.SetSportNotificationRes;
import com.coveiot.sdk.ble.api.response.SetStressIntervalControlRes;
import com.coveiot.sdk.ble.api.response.SetVibrationRes;
import com.coveiot.sdk.ble.api.response.Spo2DataRes;
import com.coveiot.sdk.ble.api.response.Spo2PeriodicDataRes;
import com.coveiot.sdk.ble.api.response.StressDataRes;
import com.coveiot.sdk.ble.api.response.TemperatureDataRes;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticSpo2Res;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticTemperatureRes;
import com.coveiot.sdk.ble.api.response.WatchFaceChange;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.FirmwareCapabilityInfo;
import com.coveiot.sdk.ble.model.HealthDataType;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public class CZ0LeonardoBleApiImpl extends LeonardoBleApiImpl {
    public static final String p = "CZ0LeonardoBleApiImpl";
    public static CZ0LeonardoBleApiImpl q;
    public MutableLiveData<LiveTemperatureData> liveTemperatureDataMutableLiveData;
    public int rawAccelerometerHistorySize;
    public int rawPPGHistorySize;
    public int spo2DataSize;
    public int stressDataSizde;
    public int temperatureDataSizde;
    public HashMap<Long, BleActivityDataHolder> activityDataHolderHashMap = new HashMap<>();
    public LinkedList<BleVibrationModel> o = new LinkedList<>();

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl$5  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2934a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            NotificationType.values();
            int[] iArr = new int[65];
            c = iArr;
            try {
                NotificationType notificationType = NotificationType.SMS;
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = c;
                NotificationType notificationType2 = NotificationType.CALENDAR;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = c;
                NotificationType notificationType3 = NotificationType.CALL;
                iArr3[0] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = c;
                NotificationType notificationType4 = NotificationType.QQ;
                iArr4[10] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = c;
                NotificationType notificationType5 = NotificationType.LINE;
                iArr5[13] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = c;
                NotificationType notificationType6 = NotificationType.EMAIL;
                iArr6[3] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr7 = c;
                NotificationType notificationType7 = NotificationType.QZONE;
                iArr7[11] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr8 = c;
                NotificationType notificationType8 = NotificationType.SKYPE;
                iArr8[14] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr9 = c;
                NotificationType notificationType9 = NotificationType.WECHAT;
                iArr9[5] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr10 = c;
                NotificationType notificationType10 = NotificationType.TWITTER;
                iArr10[8] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr11 = c;
                NotificationType notificationType11 = NotificationType.FACEBOOK;
                iArr11[6] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr12 = c;
                NotificationType notificationType12 = NotificationType.SNAPCHAT;
                iArr12[12] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                int[] iArr13 = c;
                NotificationType notificationType13 = NotificationType.WHATSAPP;
                iArr13[4] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                int[] iArr14 = c;
                NotificationType notificationType14 = NotificationType.WHATSAPP_BUSINESS;
                iArr14[23] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                int[] iArr15 = c;
                NotificationType notificationType15 = NotificationType.INSTAGRAM;
                iArr15[7] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                int[] iArr16 = c;
                NotificationType notificationType16 = NotificationType.MESSENGER;
                iArr16[9] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                int[] iArr17 = c;
                NotificationType notificationType17 = NotificationType.TELEGRAM;
                iArr17[17] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                int[] iArr18 = c;
                NotificationType notificationType18 = NotificationType.LINKEDIN;
                iArr18[15] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                int[] iArr19 = c;
                NotificationType notificationType19 = NotificationType.CUSTOM_EVENT;
                iArr19[19] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                int[] iArr20 = c;
                NotificationType notificationType20 = NotificationType.OTHER_APPS;
                iArr20[18] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                int[] iArr21 = c;
                NotificationType notificationType21 = NotificationType.GMAIL;
                iArr21[26] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                int[] iArr22 = c;
                NotificationType notificationType22 = NotificationType.KAKAO_TALK;
                iArr22[28] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                int[] iArr23 = c;
                NotificationType notificationType23 = NotificationType.YOUTUBE;
                iArr23[27] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                int[] iArr24 = c;
                NotificationType notificationType24 = NotificationType.NEWS;
                iArr24[29] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                int[] iArr25 = c;
                NotificationType notificationType25 = NotificationType.MISSED_CALL;
                iArr25[20] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            int[] iArr26 = new int[ResponseType.values().length];
            b = iArr26;
            try {
                iArr26[ResponseType.CAMERA.ordinal()] = 1;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                b[ResponseType.CALL_REJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                b[ResponseType.CALL_MUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                b[ResponseType.GET_LIVE_TEMPERATURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                b[ResponseType.PLAY_MUSIC.ordinal()] = 5;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                b[ResponseType.PAUSE_MUSIC.ordinal()] = 6;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                b[ResponseType.NEXT_MUSIC.ordinal()] = 7;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                b[ResponseType.PREVIOUS_MUSIC.ordinal()] = 8;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                b[ResponseType.VOLUME_DOWN.ordinal()] = 9;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                b[ResponseType.VOLUME_UP.ordinal()] = 10;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                b[ResponseType.WATCH_FACE_CHANGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                b[ResponseType.PERSONALIZATION_BUTTON_EVENT.ordinal()] = 12;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                b[ResponseType.SEND_SOS_DATA.ordinal()] = 13;
            } catch (NoSuchFieldError unused38) {
            }
            int[] iArr27 = new int[CommandType.values().length];
            f2934a = iArr27;
            try {
                iArr27[CommandType.TIME_INTERVAL_FOR_AUTOMATIC_TEMPERATURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                f2934a[CommandType.SET_STRESS_INTERVAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                f2934a[CommandType.SET_TEMPERATURE_UNIT.ordinal()] = 3;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                f2934a[CommandType.GET_WATCH_FACE_LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                f2934a[CommandType.GET_WATCH_FACE_BG_LIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                f2934a[CommandType.GET_CONFIGURED_ACTIVITIES.ordinal()] = 6;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                f2934a[CommandType.GET_IMAGES_LIST.ordinal()] = 7;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                f2934a[CommandType.GET_LATEST_HEALTH_DATA.ordinal()] = 8;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                f2934a[CommandType.GET_CURRENT_WATCH_FACE.ordinal()] = 9;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                f2934a[CommandType.SET_CURRENT_WATCH_FACE.ordinal()] = 10;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                f2934a[CommandType.CONFIGURE_ACTIVITIES.ordinal()] = 11;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                f2934a[CommandType.DELETE_WATCH_FACE.ordinal()] = 12;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                f2934a[CommandType.TEMPERATURE.ordinal()] = 13;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                f2934a[CommandType.GET_STRESS_DATA.ordinal()] = 14;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                f2934a[CommandType.READ_MANUAL_SPO2.ordinal()] = 15;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                f2934a[CommandType.READ_SEDENTARY_HISTORY.ordinal()] = 16;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                f2934a[CommandType.READ_RAW_PPG_HISTORY.ordinal()] = 17;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                f2934a[CommandType.READ_RAW_ACCELEROMETER_HISTORY.ordinal()] = 18;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                f2934a[CommandType.GET_ACTIVITY_SUMMARY.ordinal()] = 19;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                f2934a[CommandType.GET_ACTIVITY_DETAILS.ordinal()] = 20;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                f2934a[CommandType.WATCH_FACE.ordinal()] = 21;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                f2934a[CommandType.SPORTS_NOTIFICATION.ordinal()] = 22;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                f2934a[CommandType.CUSTOM_MESSAGE.ordinal()] = 23;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                f2934a[CommandType.SEND_IMAGE.ordinal()] = 24;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                f2934a[CommandType.SEND_FILE.ordinal()] = 25;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                f2934a[CommandType.SET_LIFT_WRIST.ordinal()] = 26;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                f2934a[CommandType.SET_WATCH_FACE_BG.ordinal()] = 27;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                f2934a[CommandType.SET_DND_MODE.ordinal()] = 28;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                f2934a[CommandType.GET_DND_MODE_SETTINGS.ordinal()] = 29;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                f2934a[CommandType.SPORTS_NOTIFICATION_CONTROL.ordinal()] = 30;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                f2934a[CommandType.DELETE_IMAGE.ordinal()] = 31;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                f2934a[CommandType.SET_SCREEN_TIMEOUT.ordinal()] = 32;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                f2934a[CommandType.SET_SLEEP_TARGET.ordinal()] = 33;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                f2934a[CommandType.GET_PERIODIC_SPO2.ordinal()] = 34;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                f2934a[CommandType.TIME_INTERVAL_FOR_AUTOMATIC_SPO2.ordinal()] = 35;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                f2934a[CommandType.GET_ALARM_LIST.ordinal()] = 36;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                f2934a[CommandType.SET_MUSIC_STATUS.ordinal()] = 37;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                f2934a[CommandType.SET_MUSIC_VOLUME_PERCENTAGE.ordinal()] = 38;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                f2934a[CommandType.SET_CAMERA_STATUS.ordinal()] = 39;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                f2934a[CommandType.SET_VIBRATION.ordinal()] = 40;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                f2934a[CommandType.PAIRING_FLOW.ordinal()] = 41;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                f2934a[CommandType.SET_AUTO_ACTIVITY_DETECTION_SETTINGS.ordinal()] = 42;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                f2934a[CommandType.GET_FIRMWARE_CAPABILITY.ordinal()] = 43;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                f2934a[CommandType.GET_AUTO_ACTIVITY_DETECTION_SETTINGS.ordinal()] = 44;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                f2934a[CommandType.SET_QR_TRAY_CODE.ordinal()] = 45;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                f2934a[CommandType.GET_QR_TRAY_CODE.ordinal()] = 46;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                f2934a[CommandType.SEND_PERSONALIZED_MESSAGE.ordinal()] = 47;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                f2934a[CommandType.SET_SOS_CONFIG.ordinal()] = 48;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                f2934a[CommandType.GET_SOS_CONFIG.ordinal()] = 49;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                f2934a[CommandType.GET_SOS_RECORDS.ordinal()] = 50;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                f2934a[CommandType.FIND_MY_WATCH.ordinal()] = 51;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                f2934a[CommandType.SET_SMART_ALERT_COMMON_APPLICATION_CONTENT.ordinal()] = 52;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                f2934a[CommandType.SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT.ordinal()] = 53;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                f2934a[CommandType.SET_SMART_ALERT_CONFIG.ordinal()] = 54;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                f2934a[CommandType.GET_SMART_ALERT_CONFIG.ordinal()] = 55;
            } catch (NoSuchFieldError unused93) {
            }
        }
    }

    public static CZ0LeonardoBleApiImpl getInstance(Context context) {
        if (q == null) {
            LeonardoBleApiImpl.context = context.getApplicationContext();
            q = new CZ0LeonardoBleApiImpl();
        }
        LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
        if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
            LogHelper.d(p, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
            try {
                LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
                if (Build.VERSION.SDK_INT >= 26) {
                    LeonardoBleApiImpl.context.startForegroundService(LeonardoBleApiImpl.intent);
                } else {
                    LeonardoBleApiImpl.context.startService(LeonardoBleApiImpl.intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, LeonardoBleApiImpl.context);
            }
        } else if (LeonardoBleApiImpl.bleService == null) {
            LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
        }
        return q;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(final BleBaseRequest bleBaseRequest, final DataResultListener dataResultListener) {
        this.uiHandler.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
                if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    int i = Calendar.getInstance().get(11);
                    if (Calendar.getInstance().get(12) > 0) {
                        i++;
                    }
                    BleBaseRequest bleBaseRequest2 = bleBaseRequest;
                    int i2 = 0;
                    long j = 6;
                    if (bleBaseRequest2 instanceof TemperatureDataRequest) {
                        CZ0LeonardoBleApiImpl.this.temperatureDataSizde = 0;
                        long findDateDifference = BleApiUtils.findDateDifference(((TemperatureDataRequest) bleBaseRequest2).getStartDate(), ((TemperatureDataRequest) bleBaseRequest).getEndDate());
                        Date startDate = ((TemperatureDataRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference > 6) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(startDate);
                            calendar.setTimeInMillis(calendar.getTimeInMillis() + ((findDateDifference - 6) * 86400000));
                            startDate = calendar.getTime();
                        } else {
                            j = findDateDifference;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand = BleCommand.GET_TEMPERATURE_DATA;
                        if (cZ0LeonardoBleApiImpl.getSameCommandCount(bleCommand) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand);
                        }
                        int i3 = (int) ((567 * j) + (i * 24));
                        for (int i4 = 0; i4 <= j; i4++) {
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.setTime(startDate);
                            calendar2.setTimeInMillis(calendar2.getTimeInMillis() + (i4 * TimeConstants.DAY));
                            Date time = calendar2.getTime();
                            int startHour = ((TemperatureDataRequest) bleBaseRequest).getStartHour();
                            int endHour = ((TemperatureDataRequest) bleBaseRequest).getEndHour();
                            if (startHour == -1) {
                                startHour = 0;
                            }
                            if (endHour == -1) {
                                endHour = 23;
                            }
                            TemperatureDataDataReq build = new TemperatureDataDataReq.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setId(new ExpectedDataSize(i3)).setEndHour(endHour).build();
                            TemperatureDataRequest build2 = new TemperatureDataRequest.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setEndHour(endHour).build();
                            build2.setBleCommand(BleCommand.GET_TEMPERATURE_DATA);
                            build2.setResponseListener(dataResultListener);
                            build2.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build2);
                            build.setReqId(build2.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build, CZ0LeonardoBleApiImpl.this);
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof ReadManualSpo2Request) {
                        CZ0LeonardoBleApiImpl.this.spo2DataSize = 0;
                        long findDateDifference2 = BleApiUtils.findDateDifference(((ReadManualSpo2Request) bleBaseRequest2).getStartDate(), ((ReadManualSpo2Request) bleBaseRequest).getEndDate());
                        Date startDate2 = ((ReadManualSpo2Request) bleBaseRequest).getStartDate();
                        if (findDateDifference2 > 6) {
                            Calendar calendar3 = Calendar.getInstance();
                            calendar3.setTime(startDate2);
                            calendar3.setTimeInMillis(calendar3.getTimeInMillis() + ((findDateDifference2 - 6) * 86400000));
                            startDate2 = calendar3.getTime();
                        } else {
                            j = findDateDifference2;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl2 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand2 = BleCommand.GET_MANUAL_SPO2_DATA;
                        if (cZ0LeonardoBleApiImpl2.getSameCommandCount(bleCommand2) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand2);
                        }
                        int i5 = (int) ((567 * j) + (i * 24));
                        for (int i6 = 0; i6 <= j; i6++) {
                            Calendar calendar4 = Calendar.getInstance();
                            calendar4.setTime(startDate2);
                            calendar4.setTimeInMillis(calendar4.getTimeInMillis() + (i6 * TimeConstants.DAY));
                            Date time2 = calendar4.getTime();
                            int startHour2 = ((ReadManualSpo2Request) bleBaseRequest).getStartHour();
                            int endHour2 = ((ReadManualSpo2Request) bleBaseRequest).getEndHour();
                            if (startHour2 == -1) {
                                startHour2 = 0;
                            }
                            if (endHour2 == -1) {
                                endHour2 = 23;
                            }
                            SPO2DataReq build3 = new SPO2DataReq.Builder().setStartDate(time2).setEndDate(time2).setStartHour(startHour2).setId(new ExpectedDataSize(i5)).setEndHour(endHour2).build();
                            ReadManualSpo2Request build4 = new ReadManualSpo2Request.Builder().setStartDate(time2).setEndDate(time2).setStartHour(startHour2).setEndHour(endHour2).build();
                            build4.setBleCommand(BleCommand.GET_MANUAL_SPO2_DATA);
                            build4.setResponseListener(dataResultListener);
                            build4.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build4);
                            build3.setReqId(build4.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build3, CZ0LeonardoBleApiImpl.this);
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof ReadSedentaryDataRequest) {
                        CZ0LeonardoBleApiImpl.this.spo2DataSize = 0;
                        long findDateDifference3 = BleApiUtils.findDateDifference(((ReadSedentaryDataRequest) bleBaseRequest2).getStartDate(), ((ReadSedentaryDataRequest) bleBaseRequest).getEndDate());
                        Date startDate3 = ((ReadSedentaryDataRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference3 > 6) {
                            Calendar calendar5 = Calendar.getInstance();
                            calendar5.setTime(startDate3);
                            calendar5.setTimeInMillis(calendar5.getTimeInMillis() + ((findDateDifference3 - 6) * 86400000));
                            startDate3 = calendar5.getTime();
                        } else {
                            j = findDateDifference3;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl3 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand3 = BleCommand.GET_SEDENTARY_DATA;
                        if (cZ0LeonardoBleApiImpl3.getSameCommandCount(bleCommand3) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand3);
                        }
                        int i7 = (int) ((567 * j) + (i * 24));
                        for (int i8 = 0; i8 <= j; i8++) {
                            Calendar calendar6 = Calendar.getInstance();
                            calendar6.setTime(startDate3);
                            calendar6.setTimeInMillis(calendar6.getTimeInMillis() + (i8 * TimeConstants.DAY));
                            Date time3 = calendar6.getTime();
                            int startHour3 = ((ReadSedentaryDataRequest) bleBaseRequest).getStartHour();
                            int endHour3 = ((ReadSedentaryDataRequest) bleBaseRequest).getEndHour();
                            if (startHour3 == -1) {
                                startHour3 = 0;
                            }
                            if (endHour3 == -1) {
                                endHour3 = 23;
                            }
                            ReadSedentaryDataReq build5 = new ReadSedentaryDataReq.Builder().setStartDate(time3).setEndDate(time3).setStartHour(startHour3).setId(new ExpectedDataSize(i7)).setEndHour(endHour3).build();
                            ReadSedentaryDataRequest build6 = new ReadSedentaryDataRequest.Builder().setStartDate(time3).setEndDate(time3).setStartHour(startHour3).setEndHour(endHour3).build();
                            build6.setBleCommand(BleCommand.GET_SEDENTARY_DATA);
                            build6.setResponseListener(dataResultListener);
                            build6.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build6);
                            build5.setReqId(build6.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build5, CZ0LeonardoBleApiImpl.this);
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof ReadRawPPGHistoryDataRequest) {
                        CZ0LeonardoBleApiImpl.this.rawPPGHistorySize = 0;
                        long findDateDifference4 = BleApiUtils.findDateDifference(((ReadRawPPGHistoryDataRequest) bleBaseRequest2).getStartDate(), ((ReadRawPPGHistoryDataRequest) bleBaseRequest).getEndDate());
                        Date startDate4 = ((ReadRawPPGHistoryDataRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference4 > 6) {
                            Calendar calendar7 = Calendar.getInstance();
                            calendar7.setTime(startDate4);
                            calendar7.setTimeInMillis(calendar7.getTimeInMillis() + ((findDateDifference4 - 6) * 86400000));
                            startDate4 = calendar7.getTime();
                        } else {
                            j = findDateDifference4;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl4 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand4 = BleCommand.GET_RAW_PPG_HISTORY;
                        if (cZ0LeonardoBleApiImpl4.getSameCommandCount(bleCommand4) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand4);
                        }
                        int i9 = (int) ((567 * j) + (i * 24));
                        for (int i10 = 0; i10 <= j; i10++) {
                            Calendar calendar8 = Calendar.getInstance();
                            calendar8.setTime(startDate4);
                            calendar8.setTimeInMillis(calendar8.getTimeInMillis() + (i10 * TimeConstants.DAY));
                            Date time4 = calendar8.getTime();
                            int startHour4 = ((ReadRawPPGHistoryDataRequest) bleBaseRequest).getStartHour();
                            int endHour4 = ((ReadRawPPGHistoryDataRequest) bleBaseRequest).getEndHour();
                            if (startHour4 == -1) {
                                startHour4 = 0;
                            }
                            if (endHour4 == -1) {
                                endHour4 = 23;
                            }
                            ReadRawPPGDataReq build7 = new ReadRawPPGDataReq.Builder().setStartDate(time4).setEndDate(time4).setStartHour(startHour4).setEndHour(endHour4).setTimeout(((ReadRawPPGHistoryDataRequest) bleBaseRequest).getTimeout()).setId(new ExpectedDataSize(i9)).build();
                            ReadRawPPGHistoryDataRequest build8 = new ReadRawPPGHistoryDataRequest.Builder().setStartDate(time4).setEndDate(time4).setStartHour(startHour4).setEndHour(endHour4).build();
                            build8.setBleCommand(BleCommand.GET_RAW_PPG_HISTORY);
                            build8.setResponseListener(dataResultListener);
                            build8.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build8);
                            build7.setReqId(build8.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build7, CZ0LeonardoBleApiImpl.this);
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof ReadRawAccelerometerDataRequest) {
                        CZ0LeonardoBleApiImpl.this.rawAccelerometerHistorySize = 0;
                        long findDateDifference5 = BleApiUtils.findDateDifference(((ReadRawAccelerometerDataRequest) bleBaseRequest2).getStartDate(), ((StepsDataRequest) bleBaseRequest).getEndDate());
                        Date startDate5 = ((ReadRawAccelerometerDataRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference5 > 6) {
                            Calendar calendar9 = Calendar.getInstance();
                            calendar9.setTime(startDate5);
                            calendar9.setTimeInMillis(calendar9.getTimeInMillis() + ((findDateDifference5 - 6) * 86400000));
                            startDate5 = calendar9.getTime();
                        } else {
                            j = findDateDifference5;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl5 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand5 = BleCommand.GET_RAW_ACCELEROMETER_HISTORY;
                        if (cZ0LeonardoBleApiImpl5.getSameCommandCount(bleCommand5) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand5);
                        }
                        int i11 = (int) ((567 * j) + (i * 24));
                        for (int i12 = 0; i12 <= j; i12++) {
                            Calendar calendar10 = Calendar.getInstance();
                            calendar10.setTime(startDate5);
                            calendar10.setTimeInMillis(calendar10.getTimeInMillis() + (i12 * TimeConstants.DAY));
                            Date time5 = calendar10.getTime();
                            int startHour5 = ((ReadRawAccelerometerDataRequest) bleBaseRequest).getStartHour();
                            int endHour5 = ((ReadRawAccelerometerDataRequest) bleBaseRequest).getEndHour();
                            if (startHour5 == -1) {
                                startHour5 = 0;
                            }
                            if (endHour5 == -1) {
                                endHour5 = 23;
                            }
                            ReadRawAccelerometerDataReq build9 = new ReadRawAccelerometerDataReq.Builder().setStartDate(time5).setEndDate(time5).setStartHour(startHour5).setEndHour(endHour5).setId(new ExpectedDataSize(i11)).build();
                            ReadRawAccelerometerDataRequest build10 = new ReadRawAccelerometerDataRequest.Builder().setStartDate(time5).setEndDate(time5).setStartHour(startHour5).setEndHour(endHour5).build();
                            build10.setBleCommand(BleCommand.GET_RAW_ACCELEROMETER_HISTORY);
                            build10.setResponseListener(dataResultListener);
                            build10.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build10);
                            build9.setReqId(build10.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build9, CZ0LeonardoBleApiImpl.this);
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof SleepDataRequest) {
                        long findDateDifference6 = BleApiUtils.findDateDifference(((SleepDataRequest) bleBaseRequest2).getStartDate(), ((SleepDataRequest) bleBaseRequest).getEndDate());
                        CZ0LeonardoBleApiImpl.this.sleepDataSize = 0;
                        Date startDate6 = ((SleepDataRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference6 > 6) {
                            Calendar calendar11 = Calendar.getInstance();
                            calendar11.setTime(startDate6);
                            calendar11.setTimeInMillis(calendar11.getTimeInMillis() + ((findDateDifference6 - 6) * 86400000));
                            startDate6 = calendar11.getTime();
                        } else {
                            j = findDateDifference6;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl6 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand6 = BleCommand.GET_SLEEP_DATA;
                        if (cZ0LeonardoBleApiImpl6.getSameCommandCount(bleCommand6) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand6);
                        }
                        int i13 = (int) ((1 + j) * 360);
                        while (i2 <= j) {
                            Calendar calendar12 = Calendar.getInstance();
                            calendar12.setTime(startDate6);
                            calendar12.setTimeInMillis(calendar12.getTimeInMillis() + (i2 * TimeConstants.DAY));
                            Date time6 = calendar12.getTime();
                            int startHour6 = ((SleepDataRequest) bleBaseRequest).getStartHour();
                            int endHour6 = ((SleepDataRequest) bleBaseRequest).getEndHour();
                            if (startHour6 == -1) {
                                startHour6 = 12;
                            }
                            if (endHour6 == -1) {
                                endHour6 = 11;
                            }
                            SleepDataReq build11 = new SleepDataReq.Builder().setStartDate(time6).setEndDate(time6).setDeviceType("CZ0").setStartHour(startHour6 < 12 ? 12 : startHour6).setEndHour(endHour6 > 11 ? 11 : endHour6).setId(new ExpectedDataSize(i13)).build();
                            SleepDataRequest build12 = new SleepDataRequest.Builder().setStartDate(time6).setEndDate(time6).setStartHour(startHour6).setEndHour(endHour6).build();
                            build12.setResponseListener(dataResultListener);
                            build12.setBleCommand(BleCommand.GET_SLEEP_DATA);
                            build12.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build12);
                            build11.setReqId(build12.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build11, CZ0LeonardoBleApiImpl.this);
                            i2++;
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof GetDNDModeSettingsRequest) {
                        GetDNDModeSettingsReq build13 = new GetDNDModeSettingsReq.Builder().build();
                        build13.setResponseListener(CZ0LeonardoBleApiImpl.this);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_DND_MODE_SETTINGS);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        build13.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(build13, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) {
                        if (!AppUtils.isEmpty(((CustomWatchFaceFileImageRequest) bleBaseRequest2).watchFaceFilePath)) {
                            WatchFaceUploadReq watchFaceUploadReq = new WatchFaceUploadReq(null, new File(((CustomWatchFaceFileImageRequest) bleBaseRequest).watchFaceFilePath), ((CustomWatchFaceFileImageRequest) bleBaseRequest).watchFaceID);
                            bleBaseRequest.setResponseListener(dataResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.WATCH_FACE);
                            bleBaseRequest.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            watchFaceUploadReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(watchFaceUploadReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        }
                        throw new UnsupportedOperationException("Watch face file path is missing");
                    } else if (bleBaseRequest2 instanceof GetWatchFaceListRequest) {
                        GetWatchFaceListReq getWatchFaceListReq = new GetWatchFaceListReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_WATCH_FACE_LIST);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getWatchFaceListReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getWatchFaceListReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetWatchFacePositionRequest) {
                        GetCurrentWatchFaceReq getCurrentWatchFaceReq = new GetCurrentWatchFaceReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_CURRENT_WATCH_FACE);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getCurrentWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getCurrentWatchFaceReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetConfiguredActivityListRequest) {
                        GetConfiguredActivityListReq getConfiguredActivityListReq = new GetConfiguredActivityListReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_CONFIGURED_ACTIVITIES);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getConfiguredActivityListReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getConfiguredActivityListReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetImageIdListRequest) {
                        GetImageIdListReq getImageIdListReq = new GetImageIdListReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_IMAGES_LIST);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getImageIdListReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getImageIdListReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof ActivityModeWithSamplesRequest) {
                        long findDateDifference7 = BleApiUtils.findDateDifference(((ActivityModeWithSamplesRequest) bleBaseRequest2).getStartDate(), ((ActivityModeWithSamplesRequest) bleBaseRequest).getEndDate());
                        CZ0LeonardoBleApiImpl.this.activityDataHolderHashMap.clear();
                        Date startDate7 = ((ActivityModeWithSamplesRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference7 > 6) {
                            Calendar calendar13 = Calendar.getInstance();
                            calendar13.setTime(startDate7);
                            calendar13.setTimeInMillis(calendar13.getTimeInMillis() + ((findDateDifference7 - 6) * 86400000));
                            startDate7 = calendar13.getTime();
                        } else {
                            j = findDateDifference7;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl7 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand7 = BleCommand.GET_ACTIVITY_SUMMARY;
                        if (cZ0LeonardoBleApiImpl7.getSameCommandCount(bleCommand7) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand7);
                        }
                        while (i2 <= j) {
                            Calendar calendar14 = Calendar.getInstance();
                            calendar14.setTime(startDate7);
                            calendar14.setTimeInMillis(calendar14.getTimeInMillis() + (i2 * TimeConstants.DAY));
                            Date time7 = calendar14.getTime();
                            GetActivitySummaryReq getActivitySummaryReq = new GetActivitySummaryReq(null, time7);
                            String uuid = UUID.randomUUID().toString();
                            ActivityModeWithSamplesRequest activityModeWithSamplesRequest = new ActivityModeWithSamplesRequest();
                            activityModeWithSamplesRequest.setStartDate(time7);
                            activityModeWithSamplesRequest.setEndDate(time7);
                            activityModeWithSamplesRequest.setBleCommand(BleCommand.GET_ACTIVITY_SUMMARY);
                            activityModeWithSamplesRequest.setResponseListener(dataResultListener);
                            activityModeWithSamplesRequest.setRequId(uuid);
                            CZ0LeonardoBleApiImpl.this.addToQueue(activityModeWithSamplesRequest);
                            getActivitySummaryReq.setReqId(activityModeWithSamplesRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(getActivitySummaryReq, CZ0LeonardoBleApiImpl.this);
                            i2++;
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof SendImageRequest) {
                        SendImageRequest sendImageRequest = (SendImageRequest) bleBaseRequest2;
                        SendImageReq sendImageReq = new SendImageReq(null, sendImageRequest.getImageId(), sendImageRequest.getImageFile(), sendImageRequest.getCompression(), sendImageRequest.getTransparentChannel(), sendImageRequest.getxCoordinate(), sendImageRequest.getyCoordinate(), sendImageRequest.getHeight(), sendImageRequest.getWidth(), DevicePlatformEnum.Nordic);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.SEND_IMAGE);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        sendImageReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(sendImageReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest) {
                        bleBaseRequest2.setBleCommand(BleCommand.CHANGE_WATCH_FACE_BG);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        DeleteImageReq deleteImageReq = new DeleteImageReq(null, ((CustomWatchFaceBackgroundChangeRequest) bleBaseRequest).getImageId());
                        deleteImageReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(deleteImageReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof SendFileRequest) {
                        SendFileRequest sendFileRequest = (SendFileRequest) bleBaseRequest2;
                        SendFileReq sendFileReq = new SendFileReq(null, sendFileRequest.getFileType(), sendFileRequest.getFile());
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.SEND_FILE);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        sendFileReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(sendFileReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof SportsNotificationRequest) {
                        SportsNotificationReq sportsNotificationReq = new SportsNotificationReq(null, LeonardoFormatter.getBleDynamicFieldData(((SportsNotificationRequest) bleBaseRequest2).getDynamicSportsFieldList()));
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.SPORTS_NOTIFICATION);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        sportsNotificationReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(sportsNotificationReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof CustomMessageRequest) {
                        CustomMessageReq customMessageReq = new CustomMessageReq(null, LeonardoFormatter.getBleDynamicFieldData(((CustomMessageRequest) bleBaseRequest).getDynamicSportsFieldList()), ((CustomMessageRequest) bleBaseRequest).getMessageType(), (short) (((CustomMessageRequest) bleBaseRequest2).getVibrationDuration() / 100), (short) (((CustomMessageRequest) bleBaseRequest).getDisplayTime() / 100), ((CustomMessageRequest) bleBaseRequest).getEnterDirection(), ((CustomMessageRequest) bleBaseRequest).getExitDirection());
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.CUSTOM_MESSAGE);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        customMessageReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(customMessageReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof PeriodicSPO2BleRequest) {
                        long findDateDifference8 = BleApiUtils.findDateDifference(((PeriodicSPO2BleRequest) bleBaseRequest2).getStartDate(), ((PeriodicSPO2BleRequest) bleBaseRequest).getEndDate());
                        Date startDate8 = ((PeriodicSPO2BleRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference8 > 6) {
                            Calendar calendar15 = Calendar.getInstance();
                            calendar15.setTime(startDate8);
                            calendar15.setTimeInMillis(calendar15.getTimeInMillis() + ((findDateDifference8 - 6) * 86400000));
                            startDate8 = calendar15.getTime();
                        } else {
                            j = findDateDifference8;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl8 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand8 = BleCommand.GET_PERIODIC_SPO2;
                        if (cZ0LeonardoBleApiImpl8.getSameCommandCount(bleCommand8) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand8);
                        }
                        for (int i14 = 0; i14 <= j; i14++) {
                            Calendar calendar16 = Calendar.getInstance();
                            calendar16.setTime(startDate8);
                            calendar16.setTimeInMillis(calendar16.getTimeInMillis() + (i14 * TimeConstants.DAY));
                            Date time8 = calendar16.getTime();
                            int startHour7 = ((PeriodicSPO2BleRequest) bleBaseRequest).getStartHour();
                            int endHour7 = ((PeriodicSPO2BleRequest) bleBaseRequest).getEndHour();
                            if (startHour7 == -1) {
                                startHour7 = 0;
                            }
                            if (endHour7 == -1) {
                                endHour7 = 23;
                            }
                            PeriodicSPO2BaseReq build14 = new PeriodicSPO2BaseReq.Builder().setStartDate(time8).setEndDate(time8).setStartHour(startHour7).setEndHour(endHour7).build();
                            PeriodicSPO2BleRequest build15 = new PeriodicSPO2BleRequest.Builder().setStartDate(time8).setEndDate(time8).setStartHour(startHour7).setEndHour(endHour7).build();
                            build15.setBleCommand(BleCommand.GET_PERIODIC_SPO2);
                            build15.setResponseListener(dataResultListener);
                            build15.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build15);
                            build14.setReqId(build15.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build14, CZ0LeonardoBleApiImpl.this);
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof GetLatestHealthDataRequest) {
                        GetLatestHealthDataReq getLatestHealthDataReq = new GetLatestHealthDataReq(null, CZ0LeonardoBleApiImpl.this.getHealthDataType(((GetLatestHealthDataRequest) bleBaseRequest2).getHealthVitalsType()));
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_LATEST_HEALTH_DATA);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getLatestHealthDataReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getLatestHealthDataReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetAlarmDataRequest) {
                        GetAlarmListReq getAlarmListReq = new GetAlarmListReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_ALARM_LIST);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getAlarmListReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getAlarmListReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetWatchfaceBackgroundRequest) {
                        GetWatchfaceBackgroundReq getWatchfaceBackgroundReq = new GetWatchfaceBackgroundReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_WATCH_FACE_BG_LIST);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getWatchfaceBackgroundReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getWatchfaceBackgroundReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof StressDataRequest) {
                        CZ0LeonardoBleApiImpl.this.stressDataSizde = 0;
                        long findDateDifference9 = BleApiUtils.findDateDifference(((StressDataRequest) bleBaseRequest2).getStartDate(), ((StressDataRequest) bleBaseRequest).getEndDate());
                        Date startDate9 = ((StressDataRequest) bleBaseRequest).getStartDate();
                        if (findDateDifference9 > 6) {
                            Calendar calendar17 = Calendar.getInstance();
                            calendar17.setTime(startDate9);
                            calendar17.setTimeInMillis(calendar17.getTimeInMillis() + ((findDateDifference9 - 6) * 86400000));
                            startDate9 = calendar17.getTime();
                        } else {
                            j = findDateDifference9;
                        }
                        CZ0LeonardoBleApiImpl cZ0LeonardoBleApiImpl9 = CZ0LeonardoBleApiImpl.this;
                        BleCommand bleCommand9 = BleCommand.GET_STRESS_DATA;
                        if (cZ0LeonardoBleApiImpl9.getSameCommandCount(bleCommand9) > 0) {
                            CZ0LeonardoBleApiImpl.this.removeSimilarCommand(bleCommand9);
                        }
                        int i15 = (int) ((567 * j) + (i * 24));
                        for (int i16 = 0; i16 <= j; i16++) {
                            Calendar calendar18 = Calendar.getInstance();
                            calendar18.setTime(startDate9);
                            calendar18.setTimeInMillis(calendar18.getTimeInMillis() + (i16 * TimeConstants.DAY));
                            Date time9 = calendar18.getTime();
                            int startHour8 = ((StressDataRequest) bleBaseRequest).getStartHour();
                            int endHour8 = ((StressDataRequest) bleBaseRequest).getEndHour();
                            if (startHour8 == -1) {
                                startHour8 = 0;
                            }
                            if (endHour8 == -1) {
                                endHour8 = 23;
                            }
                            StressDataDataReq build16 = new StressDataDataReq.Builder().setStartDate(time9).setEndDate(time9).setStartHour(startHour8).setId(new ExpectedDataSize(i15)).setEndHour(endHour8).build();
                            StressDataRequest build17 = new StressDataRequest.Builder().setStartDate(time9).setEndDate(time9).setStartHour(startHour8).setEndHour(endHour8).build();
                            build17.setBleCommand(BleCommand.GET_STRESS_DATA);
                            build17.setResponseListener(dataResultListener);
                            build17.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(build17);
                            build16.setReqId(build17.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build16, CZ0LeonardoBleApiImpl.this);
                        }
                        return;
                    } else if (bleBaseRequest2 instanceof GetFirmwareCapabilityRequest) {
                        GetFirmwareCapabilityReq getFirmwareCapabilityReq = new GetFirmwareCapabilityReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_FIRMWARE_CAPABILITY);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getFirmwareCapabilityReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getFirmwareCapabilityReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetAutoActivityDetectionSettingsRequest) {
                        GetAutoActivityDetectionSettingsReq getAutoActivityDetectionSettingsReq = new GetAutoActivityDetectionSettingsReq(null);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_AUTO_ACTIVITY_DETECTION_SETTINGS);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getAutoActivityDetectionSettingsReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getAutoActivityDetectionSettingsReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetQRCodeRequest) {
                        GetQRCodeReq build18 = new GetQRCodeReq.Builder().build();
                        build18.setResponseListener(CZ0LeonardoBleApiImpl.this);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_QR_TRAY_CODE);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        build18.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(build18, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof PersonalizedMessageRequest) {
                        PersonalizedMessageReq personalizedMessageReq = new PersonalizedMessageReq(null, LeonardoFormatter.getBleDynamicFieldData(((PersonalizedMessageRequest) bleBaseRequest).getDynamicSportsFieldList()), ((PersonalizedMessageRequest) bleBaseRequest).getMessageType(), (short) (((PersonalizedMessageRequest) bleBaseRequest2).getVibrationDuration() / 100), (short) (((PersonalizedMessageRequest) bleBaseRequest).getDisplayTime() / 100), ((PersonalizedMessageRequest) bleBaseRequest).getEnterDirection(), ((PersonalizedMessageRequest) bleBaseRequest).getExitDirection());
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.SEND_PERSONALIZED_MESSAGE);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        personalizedMessageReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(personalizedMessageReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetSOSConfigRequest) {
                        GetSOSConfigReq build19 = new GetSOSConfigReq.Builder().build();
                        build19.setResponseListener(CZ0LeonardoBleApiImpl.this);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_SOS_CONFIG);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        build19.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(build19, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (bleBaseRequest2 instanceof GetSOSRecordRequest) {
                        GetSOSRecordsReq build20 = new GetSOSRecordsReq.Builder().build();
                        build20.setResponseListener(CZ0LeonardoBleApiImpl.this);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_SOS_RECORDS);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        build20.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(build20, CZ0LeonardoBleApiImpl.this);
                        return;
                    } else if (!(bleBaseRequest2 instanceof GetSmartAlertConfigRequest)) {
                        CZ0LeonardoBleApiImpl.super.getData(bleBaseRequest2, dataResultListener);
                        return;
                    } else {
                        GetSmartAlertConfigReq getSmartAlertConfigReq = new GetSmartAlertConfigReq(null);
                        getSmartAlertConfigReq.setResponseListener(CZ0LeonardoBleApiImpl.this);
                        bleBaseRequest.setResponseListener(dataResultListener);
                        bleBaseRequest.setBleCommand(BleCommand.GET_SMART_ALERT_CONFIG);
                        bleBaseRequest.setRequId(UUID.randomUUID().toString());
                        CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                        getSmartAlertConfigReq.setReqId(bleBaseRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(getSmartAlertConfigReq, CZ0LeonardoBleApiImpl.this);
                        return;
                    }
                }
                BleBaseRequest bleBaseRequest3 = bleBaseRequest;
                if (bleBaseRequest3 != null) {
                    CZ0LeonardoBleApiImpl.this.onDataError(dataResultListener, new BleBaseError("Band not connected", bleBaseRequest3.getBleCommand()));
                }
            }
        }, 100L);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfSleepDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfBpDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRunDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfHeartRateDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRrDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfCyclingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfSwimmingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfEcgRrDataOnBand(0);
        deviceSupportedFeatures.setScheduledDndSupported(true);
        deviceSupportedFeatures.setMaxDaysOfSpo2DataOnBand(7);
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setREMSupportedInSleep(true);
        deviceSupportedFeatures.setRunSupported(false);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setBpSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setTemparatureHistorySupported(true);
        deviceSupportedFeatures.setRrSupported(false);
        deviceSupportedFeatures.setEcgSupported(false);
        deviceSupportedFeatures.setSwimmingSupported(false);
        deviceSupportedFeatures.setCyclingSupported(false);
        deviceSupportedFeatures.setShouldKeepDeviceConnectedAlways(true);
        deviceSupportedFeatures.setSportModeSupportedFromApp(false);
        deviceSupportedFeatures.setGpsSupported(false);
        deviceSupportedFeatures.setCalendarSyncSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setSosSupported(false);
        deviceSupportedFeatures.setSedentaryReminderSupported(true);
        deviceSupportedFeatures.setVibrationAlarmSupported(true);
        deviceSupportedFeatures.setBandDisplaySupported(true);
        deviceSupportedFeatures.setHandSettingsSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setPhoneTypeCommandSupported(false);
        deviceSupportedFeatures.setTimeFormartCommandSupported(true);
        deviceSupportedFeatures.setTitleSupportedInNotification(true);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setProbeFeatureSupported(true);
        deviceSupportedFeatures.setManualSpo2SupportedOnBand(false);
        deviceSupportedFeatures.setBPCalibrationSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setAutoTemperatureSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setMusicMetaDataChangeFromAppSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(true);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(12);
        deviceSupportedFeatures.setRawPPGHistaoryDataSupported(true);
        deviceSupportedFeatures.setPeriodicSpO2Supported(true);
        deviceSupportedFeatures.setMusicPlaybackStateChangeFromAppSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setSleepTargetSupported(true);
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(LeonardoBleApiImpl.context, getMacAddress(), deviceSupportedFeatures);
    }

    public HealthData getHealthData(BleHealthData bleHealthData) {
        return new HealthData(getHealthDataType(bleHealthData.getHealthDataType()), bleHealthData.getTimestamp(), bleHealthData.getValue());
    }

    public HealthDataType getHealthDataType(HealthVitalsType healthVitalsType) {
        if (healthVitalsType == HealthVitalsType.HEART_RATE) {
            return HealthDataType.HEART_RATE;
        }
        if (healthVitalsType == HealthVitalsType.SPO2) {
            return HealthDataType.SPO2;
        }
        if (healthVitalsType == HealthVitalsType.TEMPERATURE) {
            return HealthDataType.TEMPERATURE;
        }
        if (healthVitalsType == HealthVitalsType.BP) {
            return HealthDataType.BP;
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(CloveBleState cloveBleState) {
        super.onConnectionStateChanged(cloveBleState);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(Error error) {
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
        BleBaseRequest bleBaseRequest;
        BleBaseRequest bleBaseRequest2;
        BleBaseRequest bleBaseRequest3;
        if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.GET_5MIN_TEMPERATURE_DATA) {
            LeonardoBleApiImpl.QueueObject fromQueueWith = getFromQueueWith(progressDataBean.getBaseRequest());
            DataResultListener dataResultListener = (DataResultListener) fromQueueWith.f3181a.getResponseListener();
            if (progressDataBean.getProgressType() == ProgressType.DATA_BYTES) {
                this.temperatureDataSizde += progressDataBean.getValue();
            }
            dataResultListener.onProgressUpdate(new ProgressData(com.coveiot.android.bleabstract.models.ProgressType.DETERMINATE, (int) ((this.temperatureDataSizde / ((ExpectedDataSize) progressDataBean.getBaseRequest().getId()).getSize()) * 100.0f), fromQueueWith.f3181a));
        } else if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.WATCH_FACE) {
            LeonardoBleApiImpl.QueueObject fromQueueWith2 = getFromQueueWith(progressDataBean.getBaseRequest());
            if (fromQueueWith2 == null || (bleBaseRequest3 = fromQueueWith2.f3181a) == null) {
                return;
            }
            ((DataResultListener) bleBaseRequest3.getResponseListener()).onProgressUpdate(new ProgressData(com.coveiot.android.bleabstract.models.ProgressType.DETERMINATE, progressDataBean.getValue(), fromQueueWith2.f3181a));
        } else if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.SEND_IMAGE) {
            LeonardoBleApiImpl.QueueObject fromQueueWith3 = getFromQueueWith(progressDataBean.getBaseRequest());
            if (fromQueueWith3 == null || (bleBaseRequest2 = fromQueueWith3.f3181a) == null) {
                return;
            }
            ((DataResultListener) bleBaseRequest2.getResponseListener()).onProgressUpdate(new ProgressData(com.coveiot.android.bleabstract.models.ProgressType.DETERMINATE, progressDataBean.getValue(), fromQueueWith3.f3181a));
        } else if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.SEND_FILE) {
            LeonardoBleApiImpl.QueueObject fromQueueWith4 = getFromQueueWith(progressDataBean.getBaseRequest());
            if (fromQueueWith4 == null || (bleBaseRequest = fromQueueWith4.f3181a) == null) {
                return;
            }
            ((DataResultListener) bleBaseRequest.getResponseListener()).onProgressUpdate(new ProgressData(com.coveiot.android.bleabstract.models.ProgressType.DETERMINATE, progressDataBean.getValue(), fromQueueWith4.f3181a));
        } else {
            super.onProgressUpdate(progressDataBean);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(final BaseResponse baseResponse) {
        BleCommand bleCommand;
        LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate;
        BleCommand bleCommand2;
        LeonardoBleApiImpl.QueueObject fromQueue;
        String str = p;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "On Response " + baseResponse.getActivityType(), moduleNames.getModuleName());
        int i = 0;
        switch (AnonymousClass5.f2934a[baseResponse.getActivityType().ordinal()]) {
            case 1:
                BleCommand bleCommand3 = BleCommand.SET_TEMPERATURE_TIME_INTERVAL;
                LeonardoBleApiImpl.QueueObject fromQueue2 = getFromQueue(bleCommand3, baseResponse);
                if (fromQueue2 != null) {
                    SettingsResultListener settingsResultListener = (SettingsResultListener) fromQueue2.f3181a.getResponseListener();
                    if (((TimeIntervalForAutomaticTemperatureRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener, new BleBaseResponse(fromQueue2.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener, new BleBaseError("Temperature time interval setting failed", bleCommand3));
                        return;
                    }
                }
                return;
            case 2:
                BleCommand bleCommand4 = BleCommand.SET_STRESS_INTERVAL;
                LeonardoBleApiImpl.QueueObject fromQueue3 = getFromQueue(bleCommand4, baseResponse);
                if (fromQueue3 != null) {
                    SettingsResultListener settingsResultListener2 = (SettingsResultListener) fromQueue3.f3181a.getResponseListener();
                    if (((SetStressIntervalControlRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener2, new BleBaseResponse(fromQueue3.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener2, new BleBaseError("Stress time interval setting failed", bleCommand4));
                        return;
                    }
                }
                return;
            case 3:
                LogHelper.d(str, "SET_TEMPERATURE_UNIT command response received from parser!!!", moduleNames.getModuleName());
                BleCommand bleCommand5 = BleCommand.SET_TEMPERATURE_UNIT;
                LeonardoBleApiImpl.QueueObject fromQueue4 = getFromQueue(bleCommand5, baseResponse);
                if (fromQueue4 != null) {
                    SettingsResultListener settingsResultListener3 = (SettingsResultListener) fromQueue4.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse = new BleBaseResponse(fromQueue4.f3181a);
                    StringBuilder sb = new StringBuilder();
                    sb.append("((ChangeTemperatureUnitRes) response).isSuccess(): ");
                    ChangeTemperatureUnitRes changeTemperatureUnitRes = (ChangeTemperatureUnitRes) baseResponse;
                    sb.append(changeTemperatureUnitRes.isSuccess());
                    LogHelper.d(str, sb.toString(), moduleNames.getModuleName());
                    if (changeTemperatureUnitRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener3, bleBaseResponse);
                        return;
                    } else {
                        onSettingsError(settingsResultListener3, new BleBaseError("ChangeTemperatureUnitRes setting failed", bleCommand5));
                        return;
                    }
                }
                return;
            case 4:
                LogHelper.d(str, "GET_WATCH_FACE_LIST response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue5 = getFromQueue(BleCommand.GET_WATCH_FACE_LIST, baseResponse);
                if (fromQueue5 != null) {
                    DataResultListener dataResultListener = (DataResultListener) fromQueue5.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse2 = new BleBaseResponse(fromQueue5.f3181a);
                    GetWatchFaceListResponse getWatchFaceListResponse = new GetWatchFaceListResponse();
                    ArrayList<WatchFace> arrayList = new ArrayList<>();
                    if (baseResponse instanceof GetWatchFaceListRes) {
                        Iterator<Integer> it = ((GetWatchFaceListRes) baseResponse).getWatchFaceIds().iterator();
                        while (it.hasNext()) {
                            arrayList.add(new WatchFace(it.next().intValue()));
                        }
                    }
                    getWatchFaceListResponse.setWatchFaceList(arrayList);
                    bleBaseResponse2.setResponseData(getWatchFaceListResponse);
                    onDataResponse(bleBaseResponse2, dataResultListener);
                    return;
                }
                return;
            case 5:
                LogHelper.d(str, "GET_WATCH_FACE_BG_LIST response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue6 = getFromQueue(BleCommand.GET_WATCH_FACE_BG_LIST, baseResponse);
                if (fromQueue6 != null) {
                    DataResultListener dataResultListener2 = (DataResultListener) fromQueue6.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse3 = new BleBaseResponse(fromQueue6.f3181a);
                    ArrayList arrayList2 = new ArrayList();
                    if (baseResponse instanceof GetWatchfaceBackgroundRes) {
                        Iterator<BleWatchFaceBackgroundInfo> it2 = ((GetWatchfaceBackgroundRes) baseResponse).getBleWatchFaceBackgroundInfos().iterator();
                        while (it2.hasNext()) {
                            BleWatchFaceBackgroundInfo next = it2.next();
                            arrayList2.add(new WatchFaceBackgroundInfo(next.getWatchFaceId(), next.getImageId()));
                        }
                    }
                    bleBaseResponse3.setResponseData(new GetWatchfaceBackgroundResponse(bleBaseResponse3.getBleBaseRequest(), arrayList2));
                    onDataResponse(bleBaseResponse3, dataResultListener2);
                    return;
                }
                return;
            case 6:
                LogHelper.d(str, "GET_CONFIGURED_ACTIVITIES response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue7 = getFromQueue(BleCommand.GET_CONFIGURED_ACTIVITIES, baseResponse);
                if (fromQueue7 != null) {
                    BleBaseResponse bleBaseResponse4 = new BleBaseResponse(fromQueue7.f3181a);
                    bleBaseResponse4.setResponseData(new GetConfiguredActivityListResponse(LeonardoFormatter.getConfiguredActivities(((GetConfiguredActivityListRes) baseResponse).getBleConfiguredActivities())));
                    onDataResponse(bleBaseResponse4, (DataResultListener) fromQueue7.f3181a.getResponseListener());
                    return;
                }
                return;
            case 7:
                LogHelper.d(str, "GET_IMAGES_LIST response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue8 = getFromQueue(BleCommand.GET_IMAGES_LIST, baseResponse);
                if (fromQueue8 != null) {
                    BleBaseResponse bleBaseResponse5 = new BleBaseResponse(fromQueue8.f3181a);
                    bleBaseResponse5.setResponseData(new GetImageListResponse(((GetImageIdListRes) baseResponse).getImageIdList()));
                    onDataResponse(bleBaseResponse5, (DataResultListener) fromQueue8.f3181a.getResponseListener());
                    return;
                }
                return;
            case 8:
                LogHelper.d(str, "GET_LATEST_HEALTH_DATA response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue9 = getFromQueue(BleCommand.GET_LATEST_HEALTH_DATA, baseResponse);
                if (fromQueue9 != null) {
                    BleBaseResponse bleBaseResponse6 = new BleBaseResponse(fromQueue9.f3181a);
                    bleBaseResponse6.setResponseData(new GetLatestHealthDataResponse(getHealthData(((GetLatestHealthDataRes) baseResponse).getData())));
                    onDataResponse(bleBaseResponse6, (DataResultListener) fromQueue9.f3181a.getResponseListener());
                    return;
                }
                return;
            case 9:
                LogHelper.d(str, "GET_CURRENT_WATCH_FACE response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue10 = getFromQueue(BleCommand.GET_CURRENT_WATCH_FACE, baseResponse);
                if (fromQueue10 != null) {
                    DataResultListener dataResultListener3 = (DataResultListener) fromQueue10.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse7 = new BleBaseResponse(fromQueue10.f3181a);
                    WatchFacePositionResponse watchFacePositionResponse = new WatchFacePositionResponse();
                    if (baseResponse instanceof GetCurrentWatchFaceRes) {
                        watchFacePositionResponse.setWatchFacePosition(((GetCurrentWatchFaceRes) baseResponse).getCurrentWatchFaceId());
                    }
                    bleBaseResponse7.setResponseData(watchFacePositionResponse);
                    onDataResponse(bleBaseResponse7, dataResultListener3);
                    return;
                }
                return;
            case 10:
                LogHelper.d(str, "SET_CURRENT_WATCH_FACE response received from parser");
                BleCommand bleCommand6 = BleCommand.SET_CURRENT_WATCH_FACE;
                LeonardoBleApiImpl.QueueObject fromQueue11 = getFromQueue(bleCommand6, baseResponse);
                if (fromQueue11 != null) {
                    SettingsResultListener settingsResultListener4 = (SettingsResultListener) fromQueue11.f3181a.getResponseListener();
                    if (((SetCurrentWatchFaceRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener4, new BleBaseResponse(fromQueue11.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener4, new BleBaseError("SET_CURRENT_WATCH_FACE setting failed", bleCommand6));
                        return;
                    }
                }
                return;
            case 11:
                LogHelper.d(str, "CONFIGURE_ACTIVITIES response received from parser");
                BleCommand bleCommand7 = BleCommand.CONFIGURE_ACTIVITIES;
                LeonardoBleApiImpl.QueueObject fromQueue12 = getFromQueue(bleCommand7, baseResponse);
                if (fromQueue12 != null) {
                    SettingsResultListener settingsResultListener5 = (SettingsResultListener) fromQueue12.f3181a.getResponseListener();
                    if (((ConfigureActivityListRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener5, new BleBaseResponse(fromQueue12.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener5, new BleBaseError("CONFIGURE_ACTIVITIES setting failed", bleCommand7));
                        return;
                    }
                }
                return;
            case 12:
                LogHelper.d(str, "DELETE_WATCH_FACE response received from parser");
                BleCommand bleCommand8 = BleCommand.DELETE_WATCH_FACE;
                LeonardoBleApiImpl.QueueObject fromQueue13 = getFromQueue(bleCommand8, baseResponse);
                if (fromQueue13 != null) {
                    SettingsResultListener settingsResultListener6 = (SettingsResultListener) fromQueue13.f3181a.getResponseListener();
                    if (((DeleteWatchFaceRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener6, new BleBaseResponse(fromQueue13.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener6, new BleBaseError("DELETE_WATCH_FACE setting failed", bleCommand8));
                        return;
                    }
                }
                return;
            case 13:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand9 = BleCommand.GET_TEMPERATURE_DATA;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate2 = getFromQueuebasedOnDate(bleCommand9, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate2 != null) {
                    LogHelper.d(str, "Temperature Request is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount = getSameCommandCount(bleCommand9);
                        LogHelper.d(str, "Temperature queue size is ++ " + sameCommandCount, moduleNames.getModuleName());
                        TemperatureResponse temperatureResponse = LeonardoFormatter.getTemperatureResponse(((TemperatureDataRes) baseResponse).getTemperatureData());
                        temperatureResponse.setComplete(sameCommandCount == 0);
                        LogHelper.d(str, "Temperature isComplete ++ " + temperatureResponse.isComplete(), moduleNames.getModuleName());
                        BleBaseResponse bleBaseResponse8 = new BleBaseResponse(fromQueuebasedOnDate2.f3181a);
                        bleBaseResponse8.setResponseData(temperatureResponse);
                        onDataResponse(bleBaseResponse8, (DataResultListener) fromQueuebasedOnDate2.f3181a.getResponseListener());
                        LogHelper.d(str, "Temperature Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogHelper.e(p, e.getMessage(), e, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate2.f3181a.getResponseListener(), new BleBaseError(e.getMessage(), fromQueuebasedOnDate2.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "Temperature Request is no in queue", moduleNames.getModuleName());
                return;
            case 14:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand10 = BleCommand.GET_STRESS_DATA;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate3 = getFromQueuebasedOnDate(bleCommand10, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate3 != null) {
                    LogHelper.d(str, "Stress Request is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount2 = getSameCommandCount(bleCommand10);
                        LogHelper.d(str, "Stress queue size is ++ " + sameCommandCount2, moduleNames.getModuleName());
                        StressResponse stressResponse = LeonardoFormatter.getStressResponse(((StressDataRes) baseResponse).getStressData());
                        if (stressResponse != null) {
                            stressResponse.setComplete(sameCommandCount2 == 0);
                            LogHelper.d(str, "Stress isComplete ++ " + stressResponse.isComplete(), moduleNames.getModuleName());
                        }
                        BleBaseResponse bleBaseResponse9 = new BleBaseResponse(fromQueuebasedOnDate3.f3181a);
                        bleBaseResponse9.setResponseData(stressResponse);
                        onDataResponse(bleBaseResponse9, (DataResultListener) fromQueuebasedOnDate3.f3181a.getResponseListener());
                        LogHelper.d(str, "Stress Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        LogHelper.e(p, e2.getMessage(), e2, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate3.f3181a.getResponseListener(), new BleBaseError(e2.getMessage(), fromQueuebasedOnDate3.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "Stress Request is no in queue", moduleNames.getModuleName());
                return;
            case 15:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand11 = BleCommand.GET_MANUAL_SPO2_DATA;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate4 = getFromQueuebasedOnDate(bleCommand11, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate4 != null) {
                    LogHelper.d(str, "READ_MANUAL_SPO2 is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount3 = getSameCommandCount(bleCommand11);
                        LogHelper.d(str, "READ_MANUAL_SPO2 queue size is ++ " + sameCommandCount3, moduleNames.getModuleName());
                        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(LeonardoFormatter.getManualSpo2Response(((Spo2DataRes) baseResponse).getManualSpo2Data()));
                        readManualSpo2Response.setComplete(sameCommandCount3 == 0);
                        LogHelper.d(str, "READ_MANUAL_SPO2 isComplete ++ " + readManualSpo2Response.isComplete(), moduleNames.getModuleName());
                        BleBaseResponse bleBaseResponse10 = new BleBaseResponse(fromQueuebasedOnDate4.f3181a);
                        bleBaseResponse10.setResponseData(readManualSpo2Response);
                        onDataResponse(bleBaseResponse10, (DataResultListener) fromQueuebasedOnDate4.f3181a.getResponseListener());
                        LogHelper.d(str, "READ_MANUAL_SPO2 Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        LogHelper.e(p, e3.getMessage(), e3, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate4.f3181a.getResponseListener(), new BleBaseError(e3.getMessage(), fromQueuebasedOnDate4.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "READ_MANUAL_SPO2 Request is no in queue", moduleNames.getModuleName());
                return;
            case 16:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand12 = BleCommand.GET_SEDENTARY_DATA;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate5 = getFromQueuebasedOnDate(bleCommand12, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate5 != null) {
                    LogHelper.d(str, "Sedentary Request is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount4 = getSameCommandCount(bleCommand12);
                        LogHelper.d(str, "Sedentary queue size is ++ " + sameCommandCount4, moduleNames.getModuleName());
                        ReadSedentaryResponse readSedentaryResponse = new ReadSedentaryResponse(LeonardoFormatter.getSedentaryResponse(((ReadSedentaryDataRes) baseResponse).getRawSendentaryData()));
                        readSedentaryResponse.setComplete(sameCommandCount4 == 0);
                        LogHelper.d(str, "Sedentary isComplete ++ " + readSedentaryResponse.isComplete(), moduleNames.getModuleName());
                        BleBaseResponse bleBaseResponse11 = new BleBaseResponse(fromQueuebasedOnDate5.f3181a);
                        bleBaseResponse11.setResponseData(readSedentaryResponse);
                        onDataResponse(bleBaseResponse11, (DataResultListener) fromQueuebasedOnDate5.f3181a.getResponseListener());
                        LogHelper.d(str, "Sedentary Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        LogHelper.e(p, e4.getMessage(), e4, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate5.f3181a.getResponseListener(), new BleBaseError(e4.getMessage(), fromQueuebasedOnDate5.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "Sedentary Request is no in queue", moduleNames.getModuleName());
                return;
            case 17:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand13 = BleCommand.GET_RAW_PPG_HISTORY;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate6 = getFromQueuebasedOnDate(bleCommand13, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate6 != null) {
                    LogHelper.d(str, "RAW PPG Request is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount5 = getSameCommandCount(bleCommand13);
                        LogHelper.d(str, "RAW PPG queue size is ++ " + sameCommandCount5, moduleNames.getModuleName());
                        ReadRawPPGDataResponse readRawPPGDataResponse = new ReadRawPPGDataResponse(fromQueuebasedOnDate6.f3181a, LeonardoFormatter.getRawPPGHistoryData(((ReadRawPPGDataRes) baseResponse).getRawPPGHistoryData()));
                        readRawPPGDataResponse.setComplete(sameCommandCount5 == 0);
                        LogHelper.d(str, "RAW PPG isComplete ++ " + readRawPPGDataResponse.isComplete(), moduleNames.getModuleName());
                        BleBaseResponse bleBaseResponse12 = new BleBaseResponse(fromQueuebasedOnDate6.f3181a);
                        bleBaseResponse12.setResponseData(readRawPPGDataResponse);
                        onDataResponse(bleBaseResponse12, (DataResultListener) fromQueuebasedOnDate6.f3181a.getResponseListener());
                        LogHelper.d(str, "RAW PPG Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        LogHelper.e(p, e5.getMessage(), e5, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate6.f3181a.getResponseListener(), new BleBaseError(e5.getMessage(), fromQueuebasedOnDate6.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "RAW PPG Request Request is no in queue", moduleNames.getModuleName());
                return;
            case 18:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand14 = BleCommand.GET_RAW_ACCELEROMETER_HISTORY;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate7 = getFromQueuebasedOnDate(bleCommand14, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate7 != null) {
                    LogHelper.d(str, "Accelerometer Request is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount6 = getSameCommandCount(bleCommand14);
                        LogHelper.d(str, "Accelerometer queue size is ++ " + sameCommandCount6, moduleNames.getModuleName());
                        ReadRawAccelerometerDataResponse readRawAccelerometerDataResponse = new ReadRawAccelerometerDataResponse(fromQueuebasedOnDate7.f3181a, LeonardoFormatter.getRawAccelerometerHistoryData(((ReadRawAccelerometerDataRes) baseResponse).getRawAccelerometerHistoryData()));
                        readRawAccelerometerDataResponse.setComplete(sameCommandCount6 == 0);
                        LogHelper.d(str, "Accelerometer isComplete ++ " + readRawAccelerometerDataResponse.isComplete(), moduleNames.getModuleName());
                        BleBaseResponse bleBaseResponse13 = new BleBaseResponse(fromQueuebasedOnDate7.f3181a);
                        bleBaseResponse13.setResponseData(readRawAccelerometerDataResponse);
                        onDataResponse(bleBaseResponse13, (DataResultListener) fromQueuebasedOnDate7.f3181a.getResponseListener());
                        LogHelper.d(str, "Accelerometer Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e6) {
                        e6.printStackTrace();
                        LogHelper.e(p, e6.getMessage(), e6, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate7.f3181a.getResponseListener(), new BleBaseError(e6.getMessage(), fromQueuebasedOnDate7.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "Accelerometer Request is no in queue", moduleNames.getModuleName());
                return;
            case 19:
                if (!(baseResponse instanceof GetActivitySummaryRes) || (fromQueuebasedOnDate = getFromQueuebasedOnDate((bleCommand = BleCommand.GET_ACTIVITY_SUMMARY), baseResponse.getBaseRequest())) == null) {
                    return;
                }
                int sameCommandCount7 = getSameCommandCount(bleCommand);
                List<BleActivitySummaryData> bleActivitySummaryDataList = ((GetActivitySummaryRes) baseResponse).getBleActivitySummaryDataList();
                if (!AppUtils.isEmpty(bleActivitySummaryDataList)) {
                    for (BleActivitySummaryData bleActivitySummaryData : bleActivitySummaryDataList) {
                        if (!this.activityDataHolderHashMap.containsKey(Long.valueOf(bleActivitySummaryData.getSessionId()))) {
                            this.activityDataHolderHashMap.put(Long.valueOf(bleActivitySummaryData.getSessionId()), new BleActivityDataHolder(bleActivitySummaryData.getSessionId(), bleActivitySummaryData));
                        }
                    }
                }
                if (AppUtils.isEmpty(bleActivitySummaryDataList)) {
                    DataResultListener dataResultListener4 = (DataResultListener) fromQueuebasedOnDate.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse14 = new BleBaseResponse(fromQueuebasedOnDate.f3181a);
                    bleBaseResponse14.setResponseData(null);
                    if (sameCommandCount7 == 0 && this.activityDataHolderHashMap.isEmpty()) {
                        bleBaseResponse14.setCompleted(true);
                    }
                    dataResultListener4.onDataResponse(bleBaseResponse14);
                }
                if (BleApiUtils.getBleEnableActivitydetails_CZ2()) {
                    if (sameCommandCount7 != 0 || this.activityDataHolderHashMap.isEmpty()) {
                        return;
                    }
                    for (Long l : this.activityDataHolderHashMap.keySet()) {
                        sendActivityDetailCommand(l.longValue(), fromQueuebasedOnDate.f3181a.getResponseListener());
                    }
                    return;
                }
                List<ActivityModeSummaryResponse> activitySummaryResponse = LeonardoFormatter.getActivitySummaryResponse(BleApiManager.getInstance(LeonardoBleApiImpl.context).getBleApi().getMacAddress(), this.activityDataHolderHashMap);
                if (AppUtils.isEmpty(activitySummaryResponse)) {
                    return;
                }
                for (ActivityModeSummaryResponse activityModeSummaryResponse : activitySummaryResponse) {
                    BleBaseResponse bleBaseResponse15 = new BleBaseResponse(fromQueuebasedOnDate.f3181a);
                    bleBaseResponse15.setResponseData(activityModeSummaryResponse);
                    ((DataResultListener) fromQueuebasedOnDate.f3181a.getResponseListener()).onDataResponse(bleBaseResponse15);
                }
                return;
            case 20:
                if (!(baseResponse instanceof ActivityDetailsDataRes) || (fromQueue = getFromQueue((bleCommand2 = BleCommand.GET_ACTIVITY_DETAILS), baseResponse)) == null) {
                    return;
                }
                int sameCommandCount8 = getSameCommandCount(bleCommand2);
                BleActivityDetailData activityDetailData = ((ActivityDetailsDataRes) baseResponse).getActivityDetailData();
                if (activityDetailData != null) {
                    BleActivityDataHolder bleActivityDataHolder = this.activityDataHolderHashMap.get(Long.valueOf(activityDetailData.getSessionId()));
                    if (bleActivityDataHolder != null) {
                        bleActivityDataHolder.setBleActivityDetailData(activityDetailData);
                    }
                } else {
                    this.activityDataHolderHashMap.remove(Long.valueOf(((ActivityDetailsDataReq) baseResponse.getBaseRequest()).getSessionId()));
                }
                if (sameCommandCount8 == 0) {
                    List<ActivityModeSummaryResponse> activitySummaryResponse2 = LeonardoFormatter.getActivitySummaryResponse(BleApiManager.getInstance(LeonardoBleApiImpl.context).getBleApi().getMacAddress(), this.activityDataHolderHashMap);
                    for (ActivityModeSummaryResponse activityModeSummaryResponse2 : activitySummaryResponse2) {
                        DataResultListener dataResultListener5 = (DataResultListener) fromQueue.f3181a.getResponseListener();
                        BleBaseResponse bleBaseResponse16 = new BleBaseResponse(fromQueue.f3181a);
                        bleBaseResponse16.setResponseData(activityModeSummaryResponse2);
                        i++;
                        if (i == activitySummaryResponse2.size()) {
                            bleBaseResponse16.setCompleted(true);
                            this.activityDataHolderHashMap.clear();
                        }
                        dataResultListener5.onDataResponse(bleBaseResponse16);
                    }
                    return;
                }
                return;
            case 21:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                LeonardoBleApiImpl.QueueObject fromQueue14 = getFromQueue(BleCommand.WATCH_FACE, baseResponse);
                if (fromQueue14 != null) {
                    BleBaseResponse bleBaseResponse17 = new BleBaseResponse(fromQueue14.f3181a);
                    bleBaseResponse17.setResponseData(baseResponse.getResponseData());
                    onDataResponse(bleBaseResponse17, (DataResultListener) fromQueue14.f3181a.getResponseListener());
                    return;
                }
                return;
            case 22:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                LeonardoBleApiImpl.QueueObject fromQueue15 = getFromQueue(BleCommand.SPORTS_NOTIFICATION, baseResponse);
                if (fromQueue15 != null) {
                    BleBaseResponse bleBaseResponse18 = new BleBaseResponse(fromQueue15.f3181a);
                    bleBaseResponse18.setResponseData(baseResponse.getResponseData());
                    onDataResponse(bleBaseResponse18, (DataResultListener) fromQueue15.f3181a.getResponseListener());
                    return;
                }
                return;
            case 23:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                LeonardoBleApiImpl.QueueObject fromQueue16 = getFromQueue(BleCommand.CUSTOM_MESSAGE, baseResponse);
                if (fromQueue16 != null) {
                    BleBaseResponse bleBaseResponse19 = new BleBaseResponse(fromQueue16.f3181a);
                    bleBaseResponse19.setResponseData(baseResponse.getResponseData());
                    onDataResponse(bleBaseResponse19, (DataResultListener) fromQueue16.f3181a.getResponseListener());
                    return;
                }
                return;
            case 24:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                LeonardoBleApiImpl.QueueObject fromQueue17 = getFromQueue(BleCommand.SEND_IMAGE, baseResponse);
                if (fromQueue17 != null) {
                    BleBaseResponse bleBaseResponse20 = new BleBaseResponse(fromQueue17.f3181a);
                    bleBaseResponse20.setResponseData(baseResponse.getResponseData());
                    onDataResponse(bleBaseResponse20, (DataResultListener) fromQueue17.f3181a.getResponseListener());
                    return;
                }
                BleCommand bleCommand15 = BleCommand.CHANGE_WATCH_FACE_BG;
                LeonardoBleApiImpl.QueueObject fromQueue18 = getFromQueue(bleCommand15, baseResponse);
                if (fromQueue18 != null) {
                    BleBaseRequest bleBaseRequest = fromQueue18.f3181a;
                    if (bleBaseRequest instanceof CustomWatchFaceBackgroundChangeRequest) {
                        CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest = (CustomWatchFaceBackgroundChangeRequest) bleBaseRequest;
                        customWatchFaceBackgroundChangeRequest.setBleCommand(bleCommand15);
                        customWatchFaceBackgroundChangeRequest.setRequId(UUID.randomUUID().toString());
                        addToQueue(customWatchFaceBackgroundChangeRequest);
                        ChangeWatchFaceBGReq changeWatchFaceBGReq = new ChangeWatchFaceBGReq(null, customWatchFaceBackgroundChangeRequest.getWatchFaceId(), customWatchFaceBackgroundChangeRequest.getImageId());
                        changeWatchFaceBGReq.setReqId(customWatchFaceBackgroundChangeRequest.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(changeWatchFaceBGReq, this);
                        return;
                    }
                    return;
                }
                return;
            case 25:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                LeonardoBleApiImpl.QueueObject fromQueue19 = getFromQueue(BleCommand.SEND_FILE, baseResponse);
                if (fromQueue19 != null) {
                    BleBaseResponse bleBaseResponse21 = new BleBaseResponse(fromQueue19.f3181a);
                    bleBaseResponse21.setResponseData(baseResponse.getResponseData());
                    onDataResponse(bleBaseResponse21, (DataResultListener) fromQueue19.f3181a.getResponseListener());
                    return;
                }
                return;
            case 26:
                BleCommand bleCommand16 = BleCommand.SET_LIFT_WRIST;
                LeonardoBleApiImpl.QueueObject fromQueue20 = getFromQueue(bleCommand16, baseResponse);
                if (fromQueue20 != null) {
                    SettingsResultListener settingsResultListener7 = (SettingsResultListener) fromQueue20.f3181a.getResponseListener();
                    if (((LiftWristViewRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener7, new BleBaseResponse(fromQueue20.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener7, new BleBaseError("LiftWrist setting failed", bleCommand16));
                        return;
                    }
                }
                return;
            case 27:
                LeonardoBleApiImpl.QueueObject fromQueue21 = getFromQueue(BleCommand.CHANGE_WATCH_FACE_BG, baseResponse);
                if (fromQueue21 != null) {
                    SetCurrentWatchFaceReq setCurrentWatchFaceReq = new SetCurrentWatchFaceReq(null, ((CustomWatchFaceBackgroundChangeRequest) fromQueue21.f3181a).getWatchFaceId());
                    setCurrentWatchFaceReq.setReqId(fromQueue21.f3181a.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setCurrentWatchFaceReq, this);
                    BleBaseResponse bleBaseResponse22 = new BleBaseResponse(fromQueue21.f3181a);
                    bleBaseResponse22.setResponseData(baseResponse.getResponseData());
                    if (fromQueue21.f3181a.getResponseListener() instanceof DataResultListener) {
                        onDataResponse(bleBaseResponse22, (DataResultListener) fromQueue21.f3181a.getResponseListener());
                        return;
                    } else if (fromQueue21.f3181a.getResponseListener() instanceof SettingsResultListener) {
                        onSettingsResponse((SettingsResultListener) fromQueue21.f3181a.getResponseListener(), bleBaseResponse22);
                        return;
                    } else {
                        return;
                    }
                }
                LeonardoBleApiImpl.QueueObject fromQueue22 = getFromQueue(BleCommand.SET_WATCH_FACE_BG, baseResponse);
                if (fromQueue22 != null) {
                    BleBaseResponse bleBaseResponse23 = new BleBaseResponse(fromQueue22.f3181a);
                    bleBaseResponse23.setResponseData(baseResponse.getResponseData());
                    if (fromQueue22.f3181a.getResponseListener() instanceof DataResultListener) {
                        onDataResponse(bleBaseResponse23, (DataResultListener) fromQueue22.f3181a.getResponseListener());
                        return;
                    } else if (fromQueue22.f3181a.getResponseListener() instanceof SettingsResultListener) {
                        onSettingsResponse((SettingsResultListener) fromQueue22.f3181a.getResponseListener(), bleBaseResponse23);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 28:
                BleCommand bleCommand17 = BleCommand.SET_DND_MODE;
                LeonardoBleApiImpl.QueueObject fromQueue23 = getFromQueue(bleCommand17, baseResponse);
                if (fromQueue23 != null) {
                    SettingsResultListener settingsResultListener8 = (SettingsResultListener) fromQueue23.f3181a.getResponseListener();
                    if (((SetDNDModeRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener8, new BleBaseResponse(fromQueue23.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener8, new BleBaseError("DND setting failed", bleCommand17));
                        return;
                    }
                }
                return;
            case 29:
                LeonardoBleApiImpl.QueueObject fromQueue24 = getFromQueue(BleCommand.GET_DND_MODE_SETTINGS, baseResponse);
                if (fromQueue24 == null || !(baseResponse instanceof GetDNDModeSettingsRes)) {
                    return;
                }
                BleBaseResponse bleBaseResponse24 = new BleBaseResponse(fromQueue24.f3181a);
                bleBaseResponse24.setResponseData(((GetDNDModeSettingsRes) baseResponse).getDNDModeSettingsData());
                onDataResponse(bleBaseResponse24, (DataResultListener) fromQueue24.f3181a.getResponseListener());
                return;
            case 30:
                BleCommand bleCommand18 = BleCommand.SPORTS_NOTIFICATION_CONTROL;
                LeonardoBleApiImpl.QueueObject fromQueue25 = getFromQueue(bleCommand18, baseResponse);
                if (fromQueue25 != null) {
                    SettingsResultListener settingsResultListener9 = (SettingsResultListener) fromQueue25.f3181a.getResponseListener();
                    if (((SetSportNotificationRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener9, new BleBaseResponse(fromQueue25.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener9, new BleBaseError("SetSportNotificationRes setting failed", bleCommand18));
                        return;
                    }
                }
                return;
            case 31:
                BleCommand bleCommand19 = BleCommand.DELETE_IMAGE;
                LeonardoBleApiImpl.QueueObject fromQueue26 = getFromQueue(bleCommand19, baseResponse);
                if (fromQueue26 != null) {
                    SettingsResultListener settingsResultListener10 = (SettingsResultListener) fromQueue26.f3181a.getResponseListener();
                    LogHelper.d(str, "DeleteImageRes is not empty");
                    if (((DeleteImageRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener10, new BleBaseResponse(fromQueue26.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener10, new BleBaseError("DeleteImageRes setting failed", bleCommand19));
                        return;
                    }
                }
                LeonardoBleApiImpl.QueueObject fromQueue27 = getFromQueue(BleCommand.CHANGE_WATCH_FACE_BG, baseResponse);
                if (fromQueue27 != null) {
                    if (fromQueue27.f3181a instanceof CustomWatchFaceBackgroundChangeRequest) {
                        LogHelper.d(str, "CustomWatchFaceBackgroundChangeRequest is non empty");
                        CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest2 = (CustomWatchFaceBackgroundChangeRequest) fromQueue27.f3181a;
                        final SendImageReq sendImageReq = new SendImageReq(null, customWatchFaceBackgroundChangeRequest2.getImageId(), customWatchFaceBackgroundChangeRequest2.getImageFile(), 0, 0, 0, 0, customWatchFaceBackgroundChangeRequest2.getHeight(), customWatchFaceBackgroundChangeRequest2.getWidth(), DevicePlatformEnum.Nordic);
                        addToQueue(customWatchFaceBackgroundChangeRequest2);
                        customWatchFaceBackgroundChangeRequest2.setResponseListener(customWatchFaceBackgroundChangeRequest2.getResponseListener());
                        sendImageReq.setReqId(customWatchFaceBackgroundChangeRequest2.getRequId());
                        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LeonardoBleApiImpl.bleService.sendRequest(sendImageReq, CZ0LeonardoBleApiImpl.this);
                            }
                        }, 500L);
                        return;
                    }
                    LogHelper.d(str, "CustomWatchFaceBackgroundChangeRequest is ins");
                    return;
                }
                LogHelper.d(str, "CustomWatchFaceBackgroundChangeRequest is empty");
                return;
            case 32:
                BleCommand bleCommand20 = BleCommand.SET_SCREEN_TIMEOUT;
                LeonardoBleApiImpl.QueueObject fromQueue28 = getFromQueue(bleCommand20, baseResponse);
                if (fromQueue28 != null) {
                    SettingsResultListener settingsResultListener11 = (SettingsResultListener) fromQueue28.f3181a.getResponseListener();
                    if (((SetScreenTimeOutRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener11, new BleBaseResponse(fromQueue28.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener11, new BleBaseError("SET_SCREEN_TIMEOUT setting failed", bleCommand20));
                        return;
                    }
                }
                return;
            case 33:
                BleCommand bleCommand21 = BleCommand.SET_SLEEP_TARGET;
                LeonardoBleApiImpl.QueueObject fromQueue29 = getFromQueue(bleCommand21, baseResponse);
                if (fromQueue29 != null) {
                    SettingsResultListener settingsResultListener12 = (SettingsResultListener) fromQueue29.f3181a.getResponseListener();
                    if (((SetSleepTargetRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener12, new BleBaseResponse(fromQueue29.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener12, new BleBaseError("SET_SLEEP_TARGET setting failed", bleCommand21));
                        return;
                    }
                }
                return;
            case 34:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand22 = BleCommand.GET_PERIODIC_SPO2;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate8 = getFromQueuebasedOnDate(bleCommand22, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate8 != null) {
                    LogHelper.d(str, "periodicSpo2Req is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount9 = getSameCommandCount(bleCommand22);
                        LogHelper.d(str, "periodicSpo2Req queue size is ++ " + sameCommandCount9, moduleNames.getModuleName());
                        PeriodicSpo2Response periodicSpo2Response = LeonardoFormatter.getPeriodicSpo2Response(((Spo2PeriodicDataRes) baseResponse).getPeriodicSpo2Data());
                        periodicSpo2Response.setComplete(sameCommandCount9 == 0);
                        LogHelper.d(str, "periodicSpo2Req isComplete ++ " + periodicSpo2Response.isComplete(), moduleNames.getModuleName());
                        BleBaseResponse bleBaseResponse25 = new BleBaseResponse(fromQueuebasedOnDate8.f3181a);
                        bleBaseResponse25.setResponseData(periodicSpo2Response);
                        onDataResponse(bleBaseResponse25, (DataResultListener) fromQueuebasedOnDate8.f3181a.getResponseListener());
                        LogHelper.d(str, "GET_PERIODIC_SPO2 Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e7) {
                        e7.printStackTrace();
                        LogHelper.e(p, e7.getMessage(), e7, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate8.f3181a.getResponseListener(), new BleBaseError(e7.getMessage(), fromQueuebasedOnDate8.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "periodicSpo2Req Request is no in queue", moduleNames.getModuleName());
                return;
            case 35:
                BleCommand bleCommand23 = BleCommand.SET_SPO2_TIME_INTERVAL;
                LeonardoBleApiImpl.QueueObject fromQueue30 = getFromQueue(bleCommand23, baseResponse);
                if (fromQueue30 != null) {
                    SettingsResultListener settingsResultListener13 = (SettingsResultListener) fromQueue30.f3181a.getResponseListener();
                    if (((TimeIntervalForAutomaticSpo2Res) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener13, new BleBaseResponse(fromQueue30.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener13, new BleBaseError("spo2 time interval setting failed", bleCommand23));
                        return;
                    }
                }
                return;
            case 36:
                LeonardoBleApiImpl.QueueObject fromQueue31 = getFromQueue(BleCommand.GET_ALARM_LIST, baseResponse);
                if (fromQueue31 == null || !(baseResponse instanceof GetAlarmListRes)) {
                    return;
                }
                GetAlarmListRes getAlarmListRes = (GetAlarmListRes) baseResponse;
                ArrayList arrayList3 = new ArrayList();
                if (!AppUtils.isEmpty(getAlarmListRes.getAlarmInfoList())) {
                    for (BleAlarmInfo bleAlarmInfo : getAlarmListRes.getAlarmInfoList()) {
                        arrayList3.add(new Alarm(bleAlarmInfo.isAlarmOn(), bleAlarmInfo.getAlarmId(), bleAlarmInfo.getHour(), bleAlarmInfo.getMinute(), 0, bleAlarmInfo.getEventName(), bleAlarmInfo.isSunday() || bleAlarmInfo.isMonday() || bleAlarmInfo.isTuesday() || bleAlarmInfo.isWednesday() || bleAlarmInfo.isThursday() || bleAlarmInfo.isFriday() || bleAlarmInfo.isSaturday(), bleAlarmInfo.isSunday(), bleAlarmInfo.isMonday(), bleAlarmInfo.isTuesday(), bleAlarmInfo.isWednesday(), bleAlarmInfo.isThursday(), bleAlarmInfo.isFriday(), bleAlarmInfo.isSaturday()));
                    }
                }
                BleBaseResponse bleBaseResponse26 = new BleBaseResponse(fromQueue31.f3181a);
                bleBaseResponse26.setResponseData(arrayList3);
                onDataResponse(bleBaseResponse26, (DataResultListener) fromQueue31.f3181a.getResponseListener());
                return;
            case 37:
                BleCommand bleCommand24 = BleCommand.SET_MUSIC_PLAYBACK_STATUS;
                LeonardoBleApiImpl.QueueObject fromQueue32 = getFromQueue(bleCommand24, baseResponse);
                if (fromQueue32 != null) {
                    SettingsResultListener settingsResultListener14 = (SettingsResultListener) fromQueue32.f3181a.getResponseListener();
                    if (((MusicStatusNotificationRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener14, new BleBaseResponse(fromQueue32.f3181a));
                        break;
                    } else {
                        onSettingsError(settingsResultListener14, new BleBaseError("DND setting failed", bleCommand24));
                        break;
                    }
                }
                break;
            case 38:
                break;
            case 39:
                BleCommand bleCommand25 = BleCommand.SET_CAMERA_STATUS;
                LeonardoBleApiImpl.QueueObject fromQueue33 = getFromQueue(bleCommand25, baseResponse);
                if (fromQueue33 != null) {
                    SettingsResultListener settingsResultListener15 = (SettingsResultListener) fromQueue33.f3181a.getResponseListener();
                    if (((SetCameraStatusRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener15, new BleBaseResponse(fromQueue33.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener15, new BleBaseError("DND setting failed", bleCommand25));
                        return;
                    }
                }
                return;
            case 40:
                LogHelper.d(str, "SET_VIBRATION command response received from parser!!!", moduleNames.getModuleName());
                BleCommand bleCommand26 = BleCommand.SET_VIBRATION;
                LeonardoBleApiImpl.QueueObject fromQueue34 = getFromQueue(bleCommand26, baseResponse);
                if (fromQueue34 != null) {
                    SettingsResultListener settingsResultListener16 = (SettingsResultListener) fromQueue34.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse27 = new BleBaseResponse(fromQueue34.f3181a);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("((SetVibrationRes) response).isSuccess(): ");
                    SetVibrationRes setVibrationRes = (SetVibrationRes) baseResponse;
                    sb2.append(setVibrationRes.isSuccess());
                    LogHelper.d(str, sb2.toString(), moduleNames.getModuleName());
                    if (setVibrationRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener16, bleBaseResponse27);
                        return;
                    } else {
                        onSettingsError(settingsResultListener16, new BleBaseError("SetVibrationRes setting failed", bleCommand26));
                        return;
                    }
                }
                final LeonardoBleApiImpl.QueueObject fromQueue35 = getFromQueue(BleCommand.FIND_MY_WATCH, baseResponse);
                if (fromQueue35 != null) {
                    final SettingsResultListener settingsResultListener17 = (SettingsResultListener) fromQueue35.f3181a.getResponseListener();
                    final BleBaseResponse bleBaseResponse28 = new BleBaseResponse(fromQueue35.f3181a);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("((SetVibrationRes) response).isSuccess(): ");
                    SetVibrationRes setVibrationRes2 = (SetVibrationRes) baseResponse;
                    sb3.append(setVibrationRes2.isSuccess());
                    LogHelper.d(str, sb3.toString(), moduleNames.getModuleName());
                    if (this.o.size() > 0) {
                        this.uiHandler.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (CZ0LeonardoBleApiImpl.this.o.size() > 0) {
                                    ArrayList arrayList4 = new ArrayList();
                                    arrayList4.add(CZ0LeonardoBleApiImpl.this.o.remove());
                                    SetVibrationReq setVibrationReq = new SetVibrationReq(null, arrayList4);
                                    setVibrationReq.setReqId(fromQueue35.f3181a.getRequId());
                                    LeonardoBleApiImpl.bleService.sendRequest(setVibrationReq, CZ0LeonardoBleApiImpl.this);
                                    CZ0LeonardoBleApiImpl.this.addToQueue(fromQueue35.f3181a);
                                } else if (((SetVibrationRes) baseResponse).isSuccess()) {
                                    CZ0LeonardoBleApiImpl.this.onSettingsResponse(settingsResultListener17, bleBaseResponse28);
                                } else {
                                    CZ0LeonardoBleApiImpl.this.onSettingsError(settingsResultListener17, new BleBaseError("SetVibrationRes setting failed", BleCommand.SET_VIBRATION));
                                }
                            }
                        }, 3000L);
                        return;
                    } else if (setVibrationRes2.isSuccess()) {
                        onSettingsResponse(settingsResultListener17, bleBaseResponse28);
                        return;
                    } else {
                        onSettingsError(settingsResultListener17, new BleBaseError("SetVibrationRes setting failed", bleCommand26));
                        return;
                    }
                }
                return;
            case 41:
                BleCommand bleCommand27 = BleCommand.PAIRING_FLOW;
                LeonardoBleApiImpl.QueueObject fromQueue36 = getFromQueue(bleCommand27, baseResponse);
                if (fromQueue36 != null) {
                    SettingsResultListener settingsResultListener18 = (SettingsResultListener) fromQueue36.f3181a.getResponseListener();
                    if (((PairingFlowCmdRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener18, new BleBaseResponse(fromQueue36.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener18, new BleBaseError("Pairing Flow  setting failed", bleCommand27));
                        return;
                    }
                }
                return;
            case 42:
                BleCommand bleCommand28 = BleCommand.SET_AUTO_ACTIVITY_DETECTION_SETTINGS;
                LeonardoBleApiImpl.QueueObject fromQueue37 = getFromQueue(bleCommand28, baseResponse);
                if (fromQueue37 != null) {
                    SettingsResultListener settingsResultListener19 = (SettingsResultListener) fromQueue37.f3181a.getResponseListener();
                    if (((SetAutoActivityDetectionRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener19, new BleBaseResponse(fromQueue37.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener19, new BleBaseError("Auto Activity Detection setting failed", bleCommand28));
                        return;
                    }
                }
                return;
            case 43:
                LogHelper.d(str, "GET_FIRMWARE_CAPABILITY response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue38 = getFromQueue(BleCommand.GET_FIRMWARE_CAPABILITY, baseResponse);
                if (fromQueue38 != null) {
                    BleBaseResponse bleBaseResponse29 = new BleBaseResponse(fromQueue38.f3181a);
                    byte[] capabilities = ((GetFirmwareCapabilityRes) baseResponse).getCapabilities();
                    FirmwareCapabilityInfo firmwareCapabilityInfo = new FirmwareCapabilityInfo();
                    firmwareCapabilityInfo.setCapabilities(capabilities);
                    BlePreferenceManager.saveString(LeonardoBleApiImpl.context, CommonPreference.FIRMWARE_CAPABILITY.getName() + getMacAddress(), new Gson().toJson(firmwareCapabilityInfo));
                    FirmwareCapabilityData firmwareCapabilityData = new FirmwareCapabilityData();
                    firmwareCapabilityData.setCapabilities(capabilities);
                    PreferenceManagerAbstract.getInstance(LeonardoBleApiImpl.context).saveFirmwareCapability(getMacAddress(), firmwareCapabilityData);
                    bleBaseResponse29.setResponseData(new GetFirmwareCapabilityResponse(capabilities));
                    bleBaseResponse29.setCompleted(true);
                    onDataResponse(bleBaseResponse29, (DataResultListener) fromQueue38.f3181a.getResponseListener());
                    return;
                }
                return;
            case 44:
                LogHelper.d(str, "GET_AUTO_ACTIVITY_DETECTION_SETTINGS response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue39 = getFromQueue(BleCommand.GET_AUTO_ACTIVITY_DETECTION_SETTINGS, baseResponse);
                if (fromQueue39 != null) {
                    DataResultListener dataResultListener6 = (DataResultListener) fromQueue39.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse30 = new BleBaseResponse(fromQueue39.f3181a);
                    AppAutoActivityDetectionModel autoActivityDetectionModel = ((GetAutoActivityDetectionSettingsRes) baseResponse).getAutoActivityDetectionModel();
                    ArrayList arrayList4 = new ArrayList();
                    if (autoActivityDetectionModel.getPeriods() != null && !autoActivityDetectionModel.getPeriods().isEmpty()) {
                        for (int i2 = 0; i2 < autoActivityDetectionModel.getPeriods().size(); i2++) {
                            arrayList4.add(new AutoActivityDetectionModel.Period(autoActivityDetectionModel.getPeriods().get(i2).getStartTime().intValue(), autoActivityDetectionModel.getPeriods().get(i2).getEndTime().intValue()));
                        }
                    }
                    bleBaseResponse30.setResponseData(new GetAutoActivityDetectionResponse(new AutoActivityDetectionModel(autoActivityDetectionModel.getActivities(), autoActivityDetectionModel.getSundayEnabled().booleanValue(), autoActivityDetectionModel.getMondayEnabled().booleanValue(), autoActivityDetectionModel.getTuesdayEnabled().booleanValue(), autoActivityDetectionModel.getWednesdayEnabled().booleanValue(), autoActivityDetectionModel.getThursdayEnabled().booleanValue(), autoActivityDetectionModel.getFridayEnabled().booleanValue(), autoActivityDetectionModel.getSaturdayEnabled().booleanValue(), autoActivityDetectionModel.getCountDownValue().intValue(), autoActivityDetectionModel.getVibrationEnabled().booleanValue(), arrayList4)));
                    bleBaseResponse30.setCompleted(true);
                    onDataResponse(bleBaseResponse30, dataResultListener6);
                    return;
                }
                return;
            case 45:
                BleCommand bleCommand29 = BleCommand.SET_QR_TRAY_CODE;
                LeonardoBleApiImpl.QueueObject fromQueue40 = getFromQueue(bleCommand29, baseResponse);
                if (fromQueue40 != null) {
                    SettingsResultListener settingsResultListener20 = (SettingsResultListener) fromQueue40.f3181a.getResponseListener();
                    if (((SetQRTrayCodeRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener20, new BleBaseResponse(fromQueue40.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener20, new BleBaseError("QRCODE Set QR Code Failed", bleCommand29));
                        return;
                    }
                }
                return;
            case 46:
                LeonardoBleApiImpl.QueueObject fromQueue41 = getFromQueue(BleCommand.GET_QR_TRAY_CODE, baseResponse);
                if (fromQueue41 != null) {
                    DataResultListener dataResultListener7 = (DataResultListener) fromQueue41.f3181a.getResponseListener();
                    if (baseResponse instanceof GetQRCodeRes) {
                        Object qrCodes = ((GetQRCodeRes) baseResponse).getQrCodes();
                        if (qrCodes != null) {
                            LogHelper.d(str, "qrCheck onResponse: getQRCodeData: " + new Gson().toJson(qrCodes));
                        } else {
                            LogHelper.d(str, "qrCheck onResponse: getQRCodeData: NULL");
                        }
                        BleBaseResponse bleBaseResponse31 = new BleBaseResponse(fromQueue41.f3181a);
                        bleBaseResponse31.setResponseData(qrCodes);
                        bleBaseResponse31.setCompleted(true);
                        onDataResponse(bleBaseResponse31, dataResultListener7);
                        return;
                    }
                    return;
                }
                return;
            case 47:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                LeonardoBleApiImpl.QueueObject fromQueue42 = getFromQueue(BleCommand.SEND_PERSONALIZED_MESSAGE, baseResponse);
                if (fromQueue42 != null) {
                    BleBaseResponse bleBaseResponse32 = new BleBaseResponse(fromQueue42.f3181a);
                    bleBaseResponse32.setResponseData(baseResponse.getResponseData());
                    onDataResponse(bleBaseResponse32, (DataResultListener) fromQueue42.f3181a.getResponseListener());
                    return;
                }
                return;
            case 48:
                LogHelper.d("Set SOS config Response", "On Response " + baseResponse);
                BleCommand bleCommand30 = BleCommand.SET_SOS_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue43 = getFromQueue(bleCommand30, baseResponse);
                if (fromQueue43 != null) {
                    SettingsResultListener settingsResultListener21 = (SettingsResultListener) fromQueue43.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse33 = new BleBaseResponse(fromQueue43.f3181a);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("((SET_SOS_CONFIG) response).isSuccess(): ");
                    SetSOSConfigRes setSOSConfigRes = (SetSOSConfigRes) baseResponse;
                    sb4.append(setSOSConfigRes.isSuccess());
                    LogHelper.d("Set SOS Config", sb4.toString(), moduleNames.getModuleName());
                    if (setSOSConfigRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener21, bleBaseResponse33);
                        return;
                    } else {
                        onSettingsError(settingsResultListener21, new BleBaseError("SET_SOS_CONFIG setting failed", bleCommand30));
                        return;
                    }
                }
                return;
            case 49:
                BleCommand bleCommand31 = BleCommand.GET_SOS_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue44 = getFromQueue(bleCommand31, baseResponse);
                if (fromQueue44 != null) {
                    DataResultListener dataResultListener8 = (DataResultListener) fromQueue44.f3181a.getResponseListener();
                    if (!(baseResponse instanceof GetSOSConfigRes)) {
                        if (dataResultListener8 != null) {
                            onDataError(dataResultListener8, new BleBaseError("GetSOSConfigReq failed", bleCommand31));
                            return;
                        }
                        return;
                    }
                    BleGetSOSConfig bleGetSOSConfig = ((GetSOSConfigRes) baseResponse).getBleGetSOSConfig();
                    if (bleGetSOSConfig != null) {
                        GetSOSConfigResponse getSOSConfigResponse = new GetSOSConfigResponse();
                        getSOSConfigResponse.setSOSEnable(bleGetSOSConfig.isSOSEnabled());
                        getSOSConfigResponse.setTimer(bleGetSOSConfig.getTimer());
                        getSOSConfigResponse.setContactName(bleGetSOSConfig.getContactName());
                        getSOSConfigResponse.setContactNumber(bleGetSOSConfig.getContactNumber());
                        BleBaseResponse bleBaseResponse34 = new BleBaseResponse(fromQueue44.f3181a);
                        bleBaseResponse34.setResponseData(getSOSConfigResponse);
                        bleBaseResponse34.setCompleted(true);
                        onDataResponse(bleBaseResponse34, dataResultListener8);
                        return;
                    }
                    return;
                }
                return;
            case 50:
                BleCommand bleCommand32 = BleCommand.GET_SOS_RECORDS;
                LeonardoBleApiImpl.QueueObject fromQueue45 = getFromQueue(bleCommand32, baseResponse);
                if (fromQueue45 != null) {
                    DataResultListener dataResultListener9 = (DataResultListener) fromQueue45.f3181a.getResponseListener();
                    if (!(baseResponse instanceof GetSOSRecordsRes)) {
                        if (dataResultListener9 != null) {
                            onDataError(dataResultListener9, new BleBaseError("GetSOSRecordsReq failed", bleCommand32));
                            return;
                        }
                        return;
                    }
                    BleGetSOSRecords bleGetSOSRecords = ((GetSOSRecordsRes) baseResponse).getBleGetSOSRecords();
                    if (bleGetSOSRecords != null) {
                        GetSOSRecordsResponse getSOSRecordsResponse = new GetSOSRecordsResponse();
                        getSOSRecordsResponse.setNumberOfRecords(bleGetSOSRecords.getNumberCount());
                        getSOSRecordsResponse.setRecordsItem(LeonardoFormatter.getSOSRecordsItemList((ArrayList) bleGetSOSRecords.getSosRecordItemList()));
                        BleBaseResponse bleBaseResponse35 = new BleBaseResponse(fromQueue45.f3181a);
                        bleBaseResponse35.setResponseData(getSOSRecordsResponse);
                        bleBaseResponse35.setCompleted(true);
                        onDataResponse(bleBaseResponse35, dataResultListener9);
                        return;
                    }
                    return;
                }
                return;
            case 51:
                BleCommand bleCommand33 = BleCommand.FIND_MY_WATCH;
                LeonardoBleApiImpl.QueueObject fromQueue46 = getFromQueue(bleCommand33, baseResponse);
                if (fromQueue46 != null) {
                    SettingsResultListener settingsResultListener22 = (SettingsResultListener) fromQueue46.f3181a.getResponseListener();
                    if (((FindMyWatchRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener22, new BleBaseResponse(fromQueue46.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener22, new BleBaseError("Find my watch setting failed", bleCommand33));
                        return;
                    }
                }
                return;
            case 52:
                BleCommand bleCommand34 = BleCommand.SET_SMART_ALERT_COMMON_APPLICATION_CONTENT;
                LeonardoBleApiImpl.QueueObject fromQueue47 = getFromQueue(bleCommand34, baseResponse);
                if (fromQueue47 != null) {
                    SettingsResultListener settingsResultListener23 = (SettingsResultListener) fromQueue47.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse36 = new BleBaseResponse(fromQueue47.f3181a);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("((SET_SMART_ALERT_COMMON_APPLICATION_CONTENT) response).isSuccess(): ");
                    SetSmartAlertApplicationContentRes setSmartAlertApplicationContentRes = (SetSmartAlertApplicationContentRes) baseResponse;
                    sb5.append(setSmartAlertApplicationContentRes.isSuccess());
                    LogHelper.d(str, sb5.toString(), moduleNames.getModuleName());
                    if (setSmartAlertApplicationContentRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener23, bleBaseResponse36);
                        return;
                    } else {
                        onSettingsError(settingsResultListener23, new BleBaseError("SET_SMART_ALERT_COMMON_APPLICATION_CONTENT setting failed", bleCommand34));
                        return;
                    }
                }
                return;
            case 53:
                BleCommand bleCommand35 = BleCommand.SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT;
                LeonardoBleApiImpl.QueueObject fromQueue48 = getFromQueue(bleCommand35, baseResponse);
                if (fromQueue48 != null) {
                    SettingsResultListener settingsResultListener24 = (SettingsResultListener) fromQueue48.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse37 = new BleBaseResponse(fromQueue48.f3181a);
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("((SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT) response).isSuccess(): ");
                    SetNonSmartAlertApplicationContentRes setNonSmartAlertApplicationContentRes = (SetNonSmartAlertApplicationContentRes) baseResponse;
                    sb6.append(setNonSmartAlertApplicationContentRes.isSuccess());
                    LogHelper.d(str, sb6.toString(), moduleNames.getModuleName());
                    if (setNonSmartAlertApplicationContentRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener24, bleBaseResponse37);
                        return;
                    } else {
                        onSettingsError(settingsResultListener24, new BleBaseError("SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT setting failed", bleCommand35));
                        return;
                    }
                }
                return;
            case 54:
                BleCommand bleCommand36 = BleCommand.SET_SMART_ALERT_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue49 = getFromQueue(bleCommand36, baseResponse);
                if (fromQueue49 != null) {
                    SettingsResultListener settingsResultListener25 = (SettingsResultListener) fromQueue49.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse38 = new BleBaseResponse(fromQueue49.f3181a);
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("((SET_SMART_ALERT_CONFIG) response).isSuccess(): ");
                    SetSmartAlertConfigRes setSmartAlertConfigRes = (SetSmartAlertConfigRes) baseResponse;
                    sb7.append(setSmartAlertConfigRes.isSuccess());
                    LogHelper.d(str, sb7.toString(), moduleNames.getModuleName());
                    if (setSmartAlertConfigRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener25, bleBaseResponse38);
                        return;
                    } else {
                        onSettingsError(settingsResultListener25, new BleBaseError("SET_SMART_ALERT_CONFIG setting failed", bleCommand36));
                        return;
                    }
                }
                return;
            case 55:
                BleCommand bleCommand37 = BleCommand.GET_SMART_ALERT_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue50 = getFromQueue(bleCommand37, baseResponse);
                if (fromQueue50 != null) {
                    DataResultListener dataResultListener10 = (DataResultListener) fromQueue50.f3181a.getResponseListener();
                    if (!(baseResponse instanceof GetSmartAlertConfigRes)) {
                        if (dataResultListener10 != null) {
                            onDataError(dataResultListener10, new BleBaseError("GetSmartAlertConfigRes failed", bleCommand37));
                            return;
                        }
                        return;
                    }
                    BleGetSmartAlertConfigData smartAlertConfigData = ((GetSmartAlertConfigRes) baseResponse).getSmartAlertConfigData();
                    if (smartAlertConfigData != null) {
                        GetSmartAlertConfigResponse getSmartAlertConfigResponse = new GetSmartAlertConfigResponse();
                        getSmartAlertConfigResponse.setEnabled(smartAlertConfigData.isEnabled());
                        getSmartAlertConfigResponse.setSmartAlertAppDataList(new ArrayList());
                        if (smartAlertConfigData.getBleSmartAlertAppDataList() != null && !smartAlertConfigData.getBleSmartAlertAppDataList().isEmpty()) {
                            for (int i3 = 0; i3 < smartAlertConfigData.getBleSmartAlertAppDataList().size(); i3++) {
                                getSmartAlertConfigResponse.getSmartAlertAppDataList().add(new SmartAlertAppData(smartAlertConfigData.getBleSmartAlertAppDataList().get(i3).getId(), smartAlertConfigData.getBleSmartAlertAppDataList().get(i3).getName(), smartAlertConfigData.getBleSmartAlertAppDataList().get(i3).getLogoSize(), smartAlertConfigData.getBleSmartAlertAppDataList().get(i3).getLogoInfo()));
                            }
                        }
                        BleBaseResponse bleBaseResponse39 = new BleBaseResponse(fromQueue50.f3181a);
                        bleBaseResponse39.setResponseData(getSmartAlertConfigResponse);
                        bleBaseResponse39.setCompleted(true);
                        onDataResponse(bleBaseResponse39, dataResultListener10);
                        return;
                    }
                    return;
                }
                return;
            default:
                super.onResponse(baseResponse);
                return;
        }
        LeonardoBleApiImpl.QueueObject fromQueue51 = getFromQueue(BleCommand.SET_MUSIC_VOLUME_PERCENTAGE, baseResponse);
        if (fromQueue51 != null) {
            SettingsResultListener settingsResultListener26 = (SettingsResultListener) fromQueue51.f3181a.getResponseListener();
            if (((MusicStatusNotificationRes) baseResponse).isSuccess()) {
                onSettingsResponse(settingsResultListener26, new BleBaseResponse(fromQueue51.f3181a));
            } else {
                onSettingsError(settingsResultListener26, new BleBaseError("DND setting failed", BleCommand.SET_MUSIC_PLAYBACK_STATUS));
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(ResponseEvent responseEvent) {
        switch (AnonymousClass5.b[responseEvent.getType().ordinal()]) {
            case 1:
                CameraEventRes cameraEventRes = (CameraEventRes) responseEvent.getData();
                if (cameraEventRes != null) {
                    Intent intent = new Intent(Constants.CAMERA_BROADCAST_INTENT);
                    intent.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, cameraEventRes);
                    LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent);
                    return;
                }
                return;
            case 2:
                if (((CallRejectRes) responseEvent.getData()) != null) {
                    com.coveiot.android.bleabstract.response.CallRejectRes callRejectRes = new com.coveiot.android.bleabstract.response.CallRejectRes(true);
                    callRejectRes.shouldReject = true;
                    Intent intent2 = new Intent(Constants.CALL_REJECT_BROADCAST_INTENT);
                    intent2.putExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, callRejectRes);
                    LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent2);
                    return;
                }
                return;
            case 3:
                if (((CallMuteRes) responseEvent.getData()) != null) {
                    LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(new Intent(Constants.CALL_MUTE_BROADCAST_INTENT));
                    return;
                }
                return;
            case 4:
                LiveTemperatureRes liveTemperatureRes = (LiveTemperatureRes) responseEvent.getData();
                if (this.liveTemperatureDataMutableLiveData == null || liveTemperatureRes == null || liveTemperatureRes.getLiveTemperatureData() == null) {
                    return;
                }
                this.liveTemperatureDataMutableLiveData.setValue(Mapper.a(liveTemperatureRes.getLiveTemperatureData()));
                this.liveTemperatureDataMutableLiveData.postValue(Mapper.a(liveTemperatureRes.getLiveTemperatureData()));
                String str = p;
                LogHelper.d(str, liveTemperatureRes.getLiveTemperatureData().getTemperature() + "", ModuleNames.BLEABSTRACT.getModuleName());
                return;
            case 5:
                LiveMusicControlRes liveMusicControlRes = new LiveMusicControlRes(MusicControlState.PLAY);
                Intent intent3 = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                intent3.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, liveMusicControlRes);
                LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent3);
                return;
            case 6:
                LiveMusicControlRes liveMusicControlRes2 = new LiveMusicControlRes(MusicControlState.PAUSE);
                Intent intent4 = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                intent4.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, liveMusicControlRes2);
                LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent4);
                return;
            case 7:
                LiveMusicControlRes liveMusicControlRes3 = new LiveMusicControlRes(MusicControlState.NEXT);
                Intent intent5 = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                intent5.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, liveMusicControlRes3);
                LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent5);
                return;
            case 8:
                LiveMusicControlRes liveMusicControlRes4 = new LiveMusicControlRes(MusicControlState.PREVIOUS);
                Intent intent6 = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                intent6.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, liveMusicControlRes4);
                LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent6);
                return;
            case 9:
                LiveMusicControlRes liveMusicControlRes5 = new LiveMusicControlRes(MusicControlState.VOLUME_DOWN);
                Intent intent7 = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                intent7.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, liveMusicControlRes5);
                LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent7);
                return;
            case 10:
                LiveMusicControlRes liveMusicControlRes6 = new LiveMusicControlRes(MusicControlState.VOLUME_UP);
                Intent intent8 = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                intent8.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, liveMusicControlRes6);
                LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent8);
                return;
            case 11:
                WatchFaceChange watchFaceChange = (WatchFaceChange) responseEvent.getData();
                if (watchFaceChange != null) {
                    Intent intent9 = new Intent(Constants.WATCH_FACE_CHANGE_BROADCAST_INTENT);
                    intent9.putExtra(Constants.WATCH_FACE_CHANGE_BROADCAST_EXTRA, watchFaceChange);
                    LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent9);
                    return;
                }
                return;
            case 12:
                SendPersonalizedMessageRes sendPersonalizedMessageRes = (SendPersonalizedMessageRes) responseEvent.getData();
                if (sendPersonalizedMessageRes != null) {
                    Intent intent10 = new Intent(Constants.PERSONALIZED_MESSAGE_BROADCAST_INTENT);
                    intent10.putExtra(Constants.PERSONALIZED_MESSAGE_BROADCAST_INTENT_EXTRA, sendPersonalizedMessageRes);
                    LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent10);
                    return;
                }
                return;
            case 13:
                SendSOSRes sendSOSRes = (SendSOSRes) responseEvent.getData();
                if (sendSOSRes != null) {
                    Intent intent11 = new Intent(Constants.NOTIFY_SOS_BROADCAST_INTENT_EXTRA);
                    intent11.putExtra(Constants.NOTIFY_SOS_BROADCAST_INTENT_EXTRA, sendSOSRes);
                    LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent11);
                    return;
                }
                return;
            default:
                super.onResponseEventReceivved(responseEvent);
                return;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        if (this.liveTemperatureDataMutableLiveData == null) {
            this.liveTemperatureDataMutableLiveData = new MutableLiveData<>();
        }
        return this.liveTemperatureDataMutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    public void sendActivityDetailCommand(long j, BaseListener baseListener) {
        ActivityDetailsDataRequest activityDetailsDataRequest = new ActivityDetailsDataRequest(j);
        activityDetailsDataRequest.setResponseListener(baseListener);
        activityDetailsDataRequest.setRequId(UUID.randomUUID().toString());
        activityDetailsDataRequest.setBleCommand(BleCommand.GET_ACTIVITY_DETAILS);
        addToQueue(activityDetailsDataRequest);
        ActivityDetailsDataReq activityDetailsDataReq = new ActivityDetailsDataReq(null, j);
        activityDetailsDataReq.setReqId(activityDetailsDataRequest.getRequId());
        LeonardoBleApiImpl.bleService.sendRequest(activityDetailsDataReq, this);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(final BleBaseRequest bleBaseRequest, final SettingsResultListener settingsResultListener) {
        this.uiHandler.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl.3
            @Override // java.lang.Runnable
            public void run() {
                AppNotificationType appNotificationType;
                LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
                if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                    BleBaseRequest bleBaseRequest2 = bleBaseRequest;
                    if (bleBaseRequest2 != null) {
                        bleBaseRequest2.setRequId(UUID.randomUUID().toString());
                        BleBaseRequest bleBaseRequest3 = bleBaseRequest;
                        boolean z = false;
                        if (bleBaseRequest3 instanceof TemperatureTimeIntervalRequest) {
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_TEMPERATURE_TIME_INTERVAL);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            TimeIntervalForAutomaticTemperatureReq timeIntervalForAutomaticTemperatureReq = new TimeIntervalForAutomaticTemperatureReq(((TemperatureTimeIntervalRequest) bleBaseRequest).getTimeInterval(), (((TemperatureTimeIntervalRequest) bleBaseRequest).getOpen() == 1 || ((TemperatureTimeIntervalRequest) bleBaseRequest).getOpen() == 2) ? true : true);
                            BlePreferenceManager.savePreference(LeonardoBleApiImpl.context, CommonPreference.TEMPERATURE_INTERVAL_VALUE, Integer.valueOf(timeIntervalForAutomaticTemperatureReq.getTimeInterval()));
                            timeIntervalForAutomaticTemperatureReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(timeIntervalForAutomaticTemperatureReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetTemperatureUnitRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_TEMPERATURE_UNIT);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            ChangeTemperatureUnitReq build = new ChangeTemperatureUnitReq.Builder().setFahrenheit(!((SetTemperatureUnitRequest) bleBaseRequest).isTemperatureInCelsius()).build();
                            build.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof LiveTemperatureControlRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_TEMPERATURE_CONTROL);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            TemperatureMeasurementReq build2 = new TemperatureMeasurementReq.Builder(((LiveTemperatureControlRequest) bleBaseRequest).isEnabled()).build();
                            build2.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build2, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof LiveRawPPGControlRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.RAW_PPG_CONTROL);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            V7PPGControlReq build3 = new V7PPGControlReq.Builder().setEnabled(((LiveRawPPGControlRequest) bleBaseRequest).isEnabled()).build();
                            build3.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build3, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetLiftWristRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_LIFT_WRIST);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            LiftWristViewReq build4 = new LiftWristViewReq.Builder(((SetLiftWristRequest) bleBaseRequest).isLiftWristEnabled()).build();
                            build4.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build4, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof GetLiftWristSettingsRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.GET_LIFT_WRIST);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            GetLiftWristViewReq getLiftWristViewReq = new GetLiftWristViewReq(null);
                            getLiftWristViewReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(getLiftWristViewReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetDNDModeRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_DND_MODE);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            DNDModeReq build5 = new DNDModeReq.Builder(((SetDNDModeRequest) bleBaseRequest).isDNDEnabled(), ((SetDNDModeRequest) bleBaseRequest).getStartHour(), ((SetDNDModeRequest) bleBaseRequest).getStartMin(), ((SetDNDModeRequest) bleBaseRequest).getEndHour(), ((SetDNDModeRequest) bleBaseRequest).getEndMin()).setNotificationEnabled(((SetDNDModeRequest) bleBaseRequest).isNotificationEnabled()).setLiftWristEnabled(((SetDNDModeRequest) bleBaseRequest).isLiftWristEnabled()).build();
                            build5.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build5, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof ChangeWatchFaceRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_CURRENT_WATCH_FACE);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetCurrentWatchFaceReq setCurrentWatchFaceReq = new SetCurrentWatchFaceReq(null, ((ChangeWatchFaceRequest) bleBaseRequest).getWatchFacePosition());
                            setCurrentWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setCurrentWatchFaceReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof DeleteWatchFaceRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.DELETE_WATCH_FACE);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            DeleteWatchFaceReq deleteWatchFaceReq = new DeleteWatchFaceReq(null);
                            deleteWatchFaceReq.setWatchFaceId(((DeleteWatchFaceRequest) bleBaseRequest).getWatchFaceId());
                            deleteWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(deleteWatchFaceReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof ConfigureActivityListRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.CONFIGURE_ACTIVITIES);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            ConfigureActivityListReq configureActivityListReq = new ConfigureActivityListReq(null, LeonardoFormatter.convertActivityConfigMetaDataToBleActivityConfigMetaData(((ConfigureActivityListRequest) bleBaseRequest).getActivityConfigMetaDataList()));
                            configureActivityListReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(configureActivityListReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SportNotificationControlRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SPORTS_NOTIFICATION_CONTROL);
                            BleBaseRequest bleBaseRequest4 = bleBaseRequest;
                            bleBaseRequest4.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetSportNotificationReq build6 = new SetSportNotificationReq.Builder(((SportNotificationControlRequest) bleBaseRequest4).isEnabled()).build();
                            build6.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build6, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof DeleteImageRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.DELETE_IMAGE);
                            BleBaseRequest bleBaseRequest5 = bleBaseRequest;
                            bleBaseRequest5.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            DeleteImageReq deleteImageReq = new DeleteImageReq(null, ((DeleteImageRequest) bleBaseRequest5).getImageId());
                            deleteImageReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(deleteImageReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetScreenTimeOutRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_SCREEN_TIMEOUT);
                            BleBaseRequest bleBaseRequest6 = bleBaseRequest;
                            bleBaseRequest6.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetScreenTimeOutReq setScreenTimeOutReq = new SetScreenTimeOutReq(((SetScreenTimeOutRequest) bleBaseRequest6).getScreenTimeOut());
                            setScreenTimeOutReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setScreenTimeOutReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof Spo2TimeIntervalRequest) {
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_SPO2_TIME_INTERVAL);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            TimeIntervalForAutomaticSpo2Req timeIntervalForAutomaticSpo2Req = new TimeIntervalForAutomaticSpo2Req(((Spo2TimeIntervalRequest) bleBaseRequest).getTimeInterval(), ((Spo2TimeIntervalRequest) bleBaseRequest).getOpen() == 1, ((Spo2TimeIntervalRequest) bleBaseRequest).getStartHour(), ((Spo2TimeIntervalRequest) bleBaseRequest).getStartMinute(), ((Spo2TimeIntervalRequest) bleBaseRequest).getEndHour(), ((Spo2TimeIntervalRequest) bleBaseRequest).getEndMinute());
                            BlePreferenceManager.savePreference(LeonardoBleApiImpl.context, CommonPreference.SPO2_INTERVAL_VALUE, Integer.valueOf(timeIntervalForAutomaticSpo2Req.getTimeInterval()));
                            timeIntervalForAutomaticSpo2Req.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(timeIntervalForAutomaticSpo2Req, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetMessageContentRequest) {
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_MESSAGE_CONTENT);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            switch (((SetMessageContentRequest) bleBaseRequest).getAppNotificationType().ordinal()) {
                                case 0:
                                    appNotificationType = AppNotificationType.CALL;
                                    break;
                                case 1:
                                    appNotificationType = AppNotificationType.CALENDAR;
                                    break;
                                case 2:
                                    appNotificationType = AppNotificationType.SMS;
                                    break;
                                case 3:
                                    appNotificationType = AppNotificationType.EMAIL;
                                    break;
                                case 4:
                                    appNotificationType = AppNotificationType.WHATSAPP;
                                    break;
                                case 5:
                                    appNotificationType = AppNotificationType.WECHAT;
                                    break;
                                case 6:
                                    appNotificationType = AppNotificationType.FACEBOOK;
                                    break;
                                case 7:
                                    appNotificationType = AppNotificationType.INSTAGRAM;
                                    break;
                                case 8:
                                    appNotificationType = AppNotificationType.TWITTER;
                                    break;
                                case 9:
                                    appNotificationType = AppNotificationType.MESSENGER;
                                    break;
                                case 10:
                                    appNotificationType = AppNotificationType.QQ;
                                    break;
                                case 11:
                                    appNotificationType = AppNotificationType.QZONE;
                                    break;
                                case 12:
                                    appNotificationType = AppNotificationType.SNAPCHAT;
                                    break;
                                case 13:
                                    appNotificationType = AppNotificationType.LINE;
                                    break;
                                case 14:
                                    appNotificationType = AppNotificationType.SKYPE;
                                    break;
                                case 15:
                                    appNotificationType = AppNotificationType.LINKEDIN;
                                    break;
                                case 16:
                                case 21:
                                case 22:
                                case 24:
                                case 25:
                                default:
                                    appNotificationType = AppNotificationType.OTHER_APPS;
                                    break;
                                case 17:
                                    appNotificationType = AppNotificationType.TELEGRAM;
                                    break;
                                case 18:
                                    appNotificationType = AppNotificationType.OTHER_APPS;
                                    break;
                                case 19:
                                    appNotificationType = AppNotificationType.CUSTOM_EVENT;
                                    break;
                                case 20:
                                    appNotificationType = AppNotificationType.SMS;
                                    break;
                                case 23:
                                    appNotificationType = AppNotificationType.WHATSAPP;
                                    break;
                                case 26:
                                    appNotificationType = AppNotificationType.GMAIL;
                                    break;
                                case 27:
                                    appNotificationType = AppNotificationType.YOUTUBE;
                                    break;
                                case 28:
                                    appNotificationType = AppNotificationType.KAKAO_TALK;
                                    break;
                                case 29:
                                    appNotificationType = AppNotificationType.NEWS;
                                    break;
                            }
                            SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) bleBaseRequest;
                            CZ2MessageContentReq build7 = new CZ2MessageContentReq.Builder(appNotificationType, setMessageContentRequest.title, setMessageContentRequest.message).build();
                            build7.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build7, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetMusicMetaDataRequest) {
                            SetMusicMetaDataRequest setMusicMetaDataRequest = (SetMusicMetaDataRequest) bleBaseRequest3;
                            LeonardoBleApiImpl.bleService.sendRequest(new SetMediaInfoReq(null, setMusicMetaDataRequest.title, setMusicMetaDataRequest.album), CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetSleepTargetRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_SLEEP_TARGET);
                            BleBaseRequest bleBaseRequest7 = bleBaseRequest;
                            bleBaseRequest7.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetSleepTargetReq setSleepTargetReq = new SetSleepTargetReq(null, ((SetSleepTargetRequest) bleBaseRequest7).getTarget());
                            setSleepTargetReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setSleepTargetReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetMusicPlaybackStateChangedRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_MUSIC_PLAYBACK_STATUS);
                            BleBaseRequest bleBaseRequest8 = bleBaseRequest;
                            SetMusicPlaybackStateChangedRequest setMusicPlaybackStateChangedRequest = (SetMusicPlaybackStateChangedRequest) bleBaseRequest8;
                            bleBaseRequest8.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            MusicPlayBackState musicPlayBackState = MusicPlayBackState.PAUSE;
                            if (setMusicPlaybackStateChangedRequest.getMusicControlState() == MusicControlState.PLAY) {
                                musicPlayBackState = MusicPlayBackState.PLAY;
                            } else {
                                setMusicPlaybackStateChangedRequest.getMusicControlState();
                                MusicControlState musicControlState = MusicControlState.PAUSE;
                            }
                            SetMusicPlayBackStatusReq setMusicPlayBackStatusReq = new SetMusicPlayBackStatusReq(null, musicPlayBackState);
                            setMusicPlayBackStatusReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setMusicPlayBackStatusReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetMusicVolumePercentageChangedRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_MUSIC_VOLUME_PERCENTAGE);
                            BleBaseRequest bleBaseRequest9 = bleBaseRequest;
                            bleBaseRequest9.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetMusicVolumePercentageReq setMusicVolumePercentageReq = new SetMusicVolumePercentageReq(null, ((SetMusicVolumePercentageChangedRequest) bleBaseRequest9).getVolumePercentage());
                            setMusicVolumePercentageReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setMusicVolumePercentageReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof ExitRemoteCameraRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_CAMERA_STATUS);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetCameraStatusReq setCameraStatusReq = new SetCameraStatusReq(null, CameraState.EXIT);
                            setCameraStatusReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setCameraStatusReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetWatchFaceImageIdRequest) {
                            SetWatchFaceImageIdRequest setWatchFaceImageIdRequest = (SetWatchFaceImageIdRequest) bleBaseRequest3;
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_WATCH_FACE_BG);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            ChangeWatchFaceBGReq changeWatchFaceBGReq = new ChangeWatchFaceBGReq(null, setWatchFaceImageIdRequest.getWatchFaceId(), setWatchFaceImageIdRequest.getImageId());
                            changeWatchFaceBGReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(changeWatchFaceBGReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof MotorVibrationRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.SET_VIBRATION);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            ArrayList arrayList = new ArrayList();
                            for (VibrationModel vibrationModel : ((MotorVibrationRequest) bleBaseRequest).getVibrationModelList()) {
                                arrayList.add(new BleVibrationModel(vibrationModel.getType(), vibrationModel.getStrength(), vibrationModel.getDuration()));
                            }
                            SetVibrationReq setVibrationReq = new SetVibrationReq(null, arrayList);
                            setVibrationReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setVibrationReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof FindMyWatchRequest) {
                            bleBaseRequest3.setBleCommand(BleCommand.FIND_MY_WATCH);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            if (BleApiManager.getInstance(LeonardoBleApiImpl.context).getBleApi().getDeviceSupportedFeatures().isNewCommandSupportedForFindMyWatch()) {
                                FindMyWatchReq findMyWatchReq = new FindMyWatchReq(null, FindMyWatchReq.Event.START, 0);
                                findMyWatchReq.setReqId(bleBaseRequest.getRequId());
                                LeonardoBleApiImpl.bleService.sendRequest(findMyWatchReq, CZ0LeonardoBleApiImpl.this);
                                return;
                            }
                            new ArrayList();
                            for (int i = 0; i < 5; i++) {
                                CZ0LeonardoBleApiImpl.this.o.add(new BleVibrationModel(1, 80, 40));
                            }
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(CZ0LeonardoBleApiImpl.this.o.remove());
                            SetVibrationReq setVibrationReq2 = new SetVibrationReq(null, arrayList2);
                            setVibrationReq2.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setVibrationReq2, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof StopFindMyWatchRequest) {
                            if (BleApiManager.getInstance(LeonardoBleApiImpl.context).getBleApi().getDeviceSupportedFeatures().isNewCommandSupportedForFindMyWatch()) {
                                FindMyWatchReq findMyWatchReq2 = new FindMyWatchReq(null, FindMyWatchReq.Event.STOP, 5);
                                findMyWatchReq2.setReqId(bleBaseRequest.getRequId());
                                LeonardoBleApiImpl.bleService.sendRequest(findMyWatchReq2, CZ0LeonardoBleApiImpl.this);
                                CZ0LeonardoBleApiImpl.this.onSettingsResponse(settingsResultListener, new BleBaseResponse(bleBaseRequest));
                                return;
                            }
                            if (CZ0LeonardoBleApiImpl.this.o.size() > 0) {
                                CZ0LeonardoBleApiImpl.this.o.clear();
                            }
                            CZ0LeonardoBleApiImpl.this.onSettingsResponse(settingsResultListener, new BleBaseResponse(bleBaseRequest));
                            return;
                        } else if (bleBaseRequest3 instanceof SetFitnessInfoRequest) {
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_FITNESS_PERSONAL_INFO);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SaveFitnessProfileReq build8 = new SaveFitnessProfileReq.Builder(((SetFitnessInfoRequest) bleBaseRequest).getHeight(), (int) ((SetFitnessInfoRequest) bleBaseRequest).getWeight(), (int) ((SetFitnessInfoRequest) bleBaseRequest).getStride(), (int) ((SetFitnessInfoRequest) bleBaseRequest).getRunstride(), ((SetFitnessInfoRequest) bleBaseRequest).isMale()).setAge(((SetFitnessInfoRequest) bleBaseRequest).getAge()).build();
                            build8.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(build8, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof PairingFlowRequest) {
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.PAIRING_FLOW);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            PairingFlowCmdReq pairingFlowCmdReq = new PairingFlowCmdReq(((PairingFlowRequest) bleBaseRequest).getFlowStatus(), null);
                            pairingFlowCmdReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(pairingFlowCmdReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof StressTimeIntervalRequest) {
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_STRESS_INTERVAL);
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetStressIntervalControlReq setStressIntervalControlReq = new SetStressIntervalControlReq(null, (short) 1, ((StressTimeIntervalRequest) bleBaseRequest).getTimeInterval(), ((StressTimeIntervalRequest) bleBaseRequest).isEnabled(), ((StressTimeIntervalRequest) bleBaseRequest).getBaseLineTime(), ((StressTimeIntervalRequest) bleBaseRequest).getReadinessTime(), ((StressTimeIntervalRequest) bleBaseRequest).getHighStressThreshold(), ((StressTimeIntervalRequest) bleBaseRequest).getLimit());
                            setStressIntervalControlReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setStressIntervalControlReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetAutoActivityDetectionSettingsRequest) {
                            AutoActivityDetectionModel autoActivityDetectionModel = ((SetAutoActivityDetectionSettingsRequest) bleBaseRequest3).getAutoActivityDetectionModel();
                            ArrayList arrayList3 = new ArrayList();
                            if (autoActivityDetectionModel.getPeriods() != null && autoActivityDetectionModel.getPeriods().size() > 0) {
                                for (int i2 = 0; i2 < autoActivityDetectionModel.getPeriods().size(); i2++) {
                                    AutoActivityDetectionModel.Period period = autoActivityDetectionModel.getPeriods().get(i2);
                                    arrayList3.add(new AppAutoActivityDetectionModel.Period(Integer.valueOf(period.getStartTime()), Integer.valueOf(period.getEndTime())));
                                }
                            }
                            SetAutoActivityDetectionSettingsReq setAutoActivityDetectionSettingsReq = new SetAutoActivityDetectionSettingsReq(null, (short) 1, new AppAutoActivityDetectionModel(autoActivityDetectionModel.getActivities(), Boolean.valueOf(autoActivityDetectionModel.isSundayEnabled()), Boolean.valueOf(autoActivityDetectionModel.isMondayEnabled()), Boolean.valueOf(autoActivityDetectionModel.isTuesdayEnabled()), Boolean.valueOf(autoActivityDetectionModel.isWednesdayEnabled()), Boolean.valueOf(autoActivityDetectionModel.isThursdayEnabled()), Boolean.valueOf(autoActivityDetectionModel.isFridayEnabled()), Boolean.valueOf(autoActivityDetectionModel.isSaturdayEnabled()), Integer.valueOf(autoActivityDetectionModel.getCountDownValue()), Boolean.valueOf(autoActivityDetectionModel.isVibrationEnabled()), arrayList3));
                            setAutoActivityDetectionSettingsReq.setResponseListener(CZ0LeonardoBleApiImpl.this);
                            bleBaseRequest.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_AUTO_ACTIVITY_DETECTION_SETTINGS);
                            bleBaseRequest.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            setAutoActivityDetectionSettingsReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setAutoActivityDetectionSettingsReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof QRTrayCodeRequest) {
                            QRTrayCodeRequest qRTrayCodeRequest = (QRTrayCodeRequest) bleBaseRequest3;
                            qRTrayCodeRequest.setResponseListener(settingsResultListener);
                            qRTrayCodeRequest.setBleCommand(BleCommand.SET_QR_TRAY_CODE);
                            qRTrayCodeRequest.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(qRTrayCodeRequest);
                            LogHelper.d(CZ0LeonardoBleApiImpl.p, "qrCheck: qrCodeData: " + new Gson().toJson(qRTrayCodeRequest.getQrCodeData()));
                            SetQRTrayCodeReq setQRTrayCodeReq = new SetQRTrayCodeReq(null, qRTrayCodeRequest.getQrCodeData());
                            setQRTrayCodeReq.setReqId(qRTrayCodeRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setQRTrayCodeReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetSOSConfigRequest) {
                            SetSOSConfigRequest setSOSConfigRequest = (SetSOSConfigRequest) bleBaseRequest3;
                            bleBaseRequest3.setResponseListener(settingsResultListener);
                            bleBaseRequest.setBleCommand(BleCommand.SET_SOS_CONFIG);
                            bleBaseRequest.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                            SetSOSConfigReq setSOSConfigReq = new SetSOSConfigReq(null, (short) 1, setSOSConfigRequest.isSOSConfigControl(), setSOSConfigRequest.getTimer(), setSOSConfigRequest.getContactName(), setSOSConfigRequest.getContactNumber());
                            setSOSConfigReq.setReqId(bleBaseRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setSOSConfigReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetSmartAlertApplicationContentRequest) {
                            SetSmartAlertApplicationContentRequest setSmartAlertApplicationContentRequest = (SetSmartAlertApplicationContentRequest) bleBaseRequest3;
                            setSmartAlertApplicationContentRequest.setResponseListener(settingsResultListener);
                            setSmartAlertApplicationContentRequest.setBleCommand(BleCommand.SET_SMART_ALERT_COMMON_APPLICATION_CONTENT);
                            setSmartAlertApplicationContentRequest.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(setSmartAlertApplicationContentRequest);
                            ArrayList arrayList4 = new ArrayList();
                            if (setSmartAlertApplicationContentRequest.getDynamicSportFields() != null && !setSmartAlertApplicationContentRequest.getDynamicSportFields().isEmpty()) {
                                for (int i3 = 0; i3 < setSmartAlertApplicationContentRequest.getDynamicSportFields().size(); i3++) {
                                    arrayList4.add(setSmartAlertApplicationContentRequest.getDynamicSportFields().get(i3));
                                }
                            }
                            SetSmartAlertApplicationContentReq setSmartAlertApplicationContentReq = new SetSmartAlertApplicationContentReq(null, setSmartAlertApplicationContentRequest.getAppId(), setSmartAlertApplicationContentRequest.getDisplayPosition(), LeonardoFormatter.getBleDynamicFieldData(arrayList4));
                            setSmartAlertApplicationContentReq.setDevicePlatformEnum(CZ0LeonardoBleApiImpl.this.getDeviceSupportedFeatures().getPlatform());
                            setSmartAlertApplicationContentReq.setReqId(setSmartAlertApplicationContentRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setSmartAlertApplicationContentReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (bleBaseRequest3 instanceof SetNonSmartAlertApplicationContentRequest) {
                            SetNonSmartAlertApplicationContentRequest setNonSmartAlertApplicationContentRequest = (SetNonSmartAlertApplicationContentRequest) bleBaseRequest3;
                            setNonSmartAlertApplicationContentRequest.setResponseListener(settingsResultListener);
                            setNonSmartAlertApplicationContentRequest.setBleCommand(BleCommand.SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT);
                            bleBaseRequest.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(setNonSmartAlertApplicationContentRequest);
                            SetNonSmartAlertApplicationContentReq setNonSmartAlertApplicationContentReq = new SetNonSmartAlertApplicationContentReq(null, setNonSmartAlertApplicationContentRequest.getAppId(), setNonSmartAlertApplicationContentRequest.getDisplayPosition(), setNonSmartAlertApplicationContentRequest.getTitle(), setNonSmartAlertApplicationContentRequest.getContent());
                            setNonSmartAlertApplicationContentReq.setDevicePlatformEnum(CZ0LeonardoBleApiImpl.this.getDeviceSupportedFeatures().getPlatform());
                            setNonSmartAlertApplicationContentReq.setReqId(setNonSmartAlertApplicationContentRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setNonSmartAlertApplicationContentReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        } else if (!(bleBaseRequest3 instanceof SetSmartAlertConfigRequest)) {
                            CZ0LeonardoBleApiImpl.super.setUserSettings(bleBaseRequest3, settingsResultListener);
                            return;
                        } else {
                            SetSmartAlertConfigRequest setSmartAlertConfigRequest = (SetSmartAlertConfigRequest) bleBaseRequest3;
                            setSmartAlertConfigRequest.setResponseListener(settingsResultListener);
                            setSmartAlertConfigRequest.setBleCommand(BleCommand.SET_SMART_ALERT_CONFIG);
                            setSmartAlertConfigRequest.setRequId(UUID.randomUUID().toString());
                            CZ0LeonardoBleApiImpl.this.addToQueue(setSmartAlertConfigRequest);
                            ArrayList arrayList5 = new ArrayList();
                            if (setSmartAlertConfigRequest.getAppList() != null && !setSmartAlertConfigRequest.getAppList().isEmpty()) {
                                for (int i4 = 0; i4 < setSmartAlertConfigRequest.getAppList().size(); i4++) {
                                    arrayList5.add(new BleSmartAlertAppData(setSmartAlertConfigRequest.getAppList().get(i4).getId(), setSmartAlertConfigRequest.getAppList().get(i4).getName(), setSmartAlertConfigRequest.getAppList().get(i4).getLogoSize(), setSmartAlertConfigRequest.getAppList().get(i4).getLogoInfo()));
                                }
                            }
                            SetSmartAlertConfigReq setSmartAlertConfigReq = new SetSmartAlertConfigReq(null, (short) 1, setSmartAlertConfigRequest.isEnabled(), arrayList5);
                            setSmartAlertConfigReq.setReqId(setSmartAlertConfigRequest.getRequId());
                            LeonardoBleApiImpl.bleService.sendRequest(setSmartAlertConfigReq, CZ0LeonardoBleApiImpl.this);
                            return;
                        }
                    }
                    return;
                }
                BleBaseRequest bleBaseRequest10 = bleBaseRequest;
                if (bleBaseRequest10 != null && bleBaseRequest10.getBleCommand() != null) {
                    CZ0LeonardoBleApiImpl.this.onSettingsError(settingsResultListener, new BleBaseError("Band is not connected", bleBaseRequest.getBleCommand()));
                } else {
                    CZ0LeonardoBleApiImpl.this.onSettingsError(settingsResultListener, new BleBaseError("Band is not connected"));
                }
            }
        }, 100L);
    }

    public HealthVitalsType getHealthDataType(HealthDataType healthDataType) {
        if (healthDataType == HealthDataType.HEART_RATE) {
            return HealthVitalsType.HEART_RATE;
        }
        if (healthDataType == HealthDataType.SPO2) {
            return HealthVitalsType.SPO2;
        }
        if (healthDataType == HealthDataType.TEMPERATURE) {
            return HealthVitalsType.TEMPERATURE;
        }
        if (healthDataType == HealthDataType.BP) {
            return HealthVitalsType.BP;
        }
        return null;
    }
}
