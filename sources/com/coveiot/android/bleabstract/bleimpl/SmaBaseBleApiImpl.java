package com.coveiot.android.bleabstract.bleimpl;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.ContactsContract;
import android.text.format.DateUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bestmafen.baseble.data.ByteArrayExtKt;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.CloveSmaBleState;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.GoalType;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.models.SedentaryReminderData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerSma;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.DeleteScheduleRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.ExitRemoteCameraRequest;
import com.coveiot.android.bleabstract.request.GetAlarmDataRequest;
import com.coveiot.android.bleabstract.request.GetCalorieDistanceGoalRequest;
import com.coveiot.android.bleabstract.request.GetDNDModeSettingsRequest;
import com.coveiot.android.bleabstract.request.GetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.GetLiftWristSettingsRequest;
import com.coveiot.android.bleabstract.request.GetSedentaryDataRequest;
import com.coveiot.android.bleabstract.request.GetWorldClockDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.OTAModeRequest;
import com.coveiot.android.bleabstract.request.ReadManualBpRequest;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ReadStepTargetRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SendSleepDataRequest;
import com.coveiot.android.bleabstract.request.SendWeatherRequest;
import com.coveiot.android.bleabstract.request.SessionHeartRateRequest;
import com.coveiot.android.bleabstract.request.SessionStepsDataRequest;
import com.coveiot.android.bleabstract.request.SetCalorieTargetRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetDistanceTargetRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetScheduleRequest;
import com.coveiot.android.bleabstract.request.SetScreenTimeOutRequest;
import com.coveiot.android.bleabstract.request.SetSleepTargetRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SetWomenWellnessSettingsRequest;
import com.coveiot.android.bleabstract.request.SetWorldClockListRequest;
import com.coveiot.android.bleabstract.request.SetWorldClockRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.StopMessageNotificationRequest;
import com.coveiot.android.bleabstract.request.TemperatureDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.UploadContactRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse;
import com.coveiot.android.bleabstract.response.GetStepGoalResponse;
import com.coveiot.android.bleabstract.response.GetWorldClockDataResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.ReadManualBpResponse;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StreamProgressResponse;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.services.SmaBaseBleService;
import com.coveiot.android.bleabstract.services.SmaF2BleService;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.android.smasdk.SmaResponseListener;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.android.smasdk.api.SmaCalorieGoalReq;
import com.coveiot.android.smasdk.api.SmaCurrentWeatherReq;
import com.coveiot.android.smasdk.api.SmaDeleteScheduleReq;
import com.coveiot.android.smasdk.api.SmaDistanceGoalReq;
import com.coveiot.android.smasdk.api.SmaExitRemoteCameraReq;
import com.coveiot.android.smasdk.api.SmaGestureWakeReq;
import com.coveiot.android.smasdk.api.SmaGetAlarmsReq;
import com.coveiot.android.smasdk.api.SmaGetBatteryReq;
import com.coveiot.android.smasdk.api.SmaGetDNDDataReq;
import com.coveiot.android.smasdk.api.SmaGetFirmwareVersionReq;
import com.coveiot.android.smasdk.api.SmaGetSedentaryDataReq;
import com.coveiot.android.smasdk.api.SmaGetUserProfileReq;
import com.coveiot.android.smasdk.api.SmaGetWakeGestureDataReq;
import com.coveiot.android.smasdk.api.SmaGetWorldClockReq;
import com.coveiot.android.smasdk.api.SmaGirlCareSettingsReq;
import com.coveiot.android.smasdk.api.SmaHRDataReq;
import com.coveiot.android.smasdk.api.SmaManualBPDataReq;
import com.coveiot.android.smasdk.api.SmaNotificationReq;
import com.coveiot.android.smasdk.api.SmaOtaReq;
import com.coveiot.android.smasdk.api.SmaScreenTimeOutReq;
import com.coveiot.android.smasdk.api.SmaSessionHRDataReq;
import com.coveiot.android.smasdk.api.SmaSessionStepDataReq;
import com.coveiot.android.smasdk.api.SmaSetDNDReq;
import com.coveiot.android.smasdk.api.SmaSetHourSystemReq;
import com.coveiot.android.smasdk.api.SmaSetHrMonitoringReq;
import com.coveiot.android.smasdk.api.SmaSetScheduleReq;
import com.coveiot.android.smasdk.api.SmaSetSedentaryReminderReq;
import com.coveiot.android.smasdk.api.SmaSetTemperatureDetectingReq;
import com.coveiot.android.smasdk.api.SmaSetTemperatureUnitReq;
import com.coveiot.android.smasdk.api.SmaSetUserProfileReq;
import com.coveiot.android.smasdk.api.SmaSetVibrationAlarmReq;
import com.coveiot.android.smasdk.api.SmaSetWorldClockReq;
import com.coveiot.android.smasdk.api.SmaSleepDataReq;
import com.coveiot.android.smasdk.api.SmaSleepGoalReq;
import com.coveiot.android.smasdk.api.SmaSleepQualityReq;
import com.coveiot.android.smasdk.api.SmaSpO2DataReq;
import com.coveiot.android.smasdk.api.SmaStepDataReq;
import com.coveiot.android.smasdk.api.SmaStepGoalReq;
import com.coveiot.android.smasdk.api.SmaStopNotificationReq;
import com.coveiot.android.smasdk.api.SmaTemperatureDataReq;
import com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq;
import com.coveiot.android.smasdk.api.SmaWorkoutDataReq;
import com.coveiot.android.smasdk.error.SmaError;
import com.coveiot.android.smasdk.error.SmaErrorType;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfo;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoRepository;
import com.coveiot.sdk.ble.api.model.SendWeatherModel;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.net.HttpHeaders;
import com.squareup.otto.Subscribe;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.component.BleCache;
import com.szabh.smable3.entity.BleAlarm;
import com.szabh.smable3.entity.BleDeviceInfo;
import com.szabh.smable3.entity.BleGestureWake;
import com.szabh.smable3.entity.BleGirlCareSettings;
import com.szabh.smable3.entity.BleHrMonitoringSettings;
import com.szabh.smable3.entity.BleNoDisturbSettings;
import com.szabh.smable3.entity.BleNotification;
import com.szabh.smable3.entity.BleSchedule;
import com.szabh.smable3.entity.BleSedentarinessSettings;
import com.szabh.smable3.entity.BleSleepQuality;
import com.szabh.smable3.entity.BleTemperatureDetecting;
import com.szabh.smable3.entity.BleTimeRange;
import com.szabh.smable3.entity.BleUserProfile;
import com.szabh.smable3.entity.BleWeather;
import com.szabh.smable3.entity.BleWeatherForecast;
import com.szabh.smable3.entity.BleWeatherRealtime;
import com.szabh.smable3.entity.BleWorldClock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class SmaBaseBleApiImpl implements BleApi, SmaResponseListener {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3213a;
    @NotNull
    public final Handler b;
    @NotNull
    public final Handler c;
    @Nullable
    public ConnectionResultListener d;
    @NotNull
    public final Handler e;
    @Nullable
    public BleBaseRequest f;
    public final int g;
    public final int h;
    public final int i;
    @NotNull
    public final Handler j;
    @Nullable
    public MutableLiveData<ConnectionStatus> k;
    @Nullable
    public MutableLiveData<LiveHealthData> l;
    @Nullable
    public MutableLiveData<LiveStepsData> m;
    @Nullable
    public MutableLiveData<PPGData> n;
    @Nullable
    public MutableLiveData<LiveECGDataResponse> o;
    @Nullable
    public MutableLiveData<LiveTemperatureData> p;
    @NotNull
    public final LinkedList<QueueObject> q;
    @Nullable
    public DeviceSupportedFeatures r;
    @NotNull
    public String s;
    @Nullable
    public SmaBaseBleService t;
    @Nullable
    public DeviceInfoData u;
    @NotNull
    public ServiceConnection v;
    @Nullable
    public SmaBaseRes w;
    @NotNull
    public final Runnable x;

    /* loaded from: classes2.dex */
    public static final class Contact {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public String f3214a;
        @NotNull
        public String b;

        public Contact(@NotNull String name, @NotNull String phone) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(phone, "phone");
            this.f3214a = name;
            this.b = phone;
        }

        public static /* synthetic */ Contact copy$default(Contact contact, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = contact.f3214a;
            }
            if ((i & 2) != 0) {
                str2 = contact.b;
            }
            return contact.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.f3214a;
        }

        @NotNull
        public final String component2() {
            return this.b;
        }

        @NotNull
        public final Contact copy(@NotNull String name, @NotNull String phone) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(phone, "phone");
            return new Contact(name, phone);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Contact) {
                Contact contact = (Contact) obj;
                return Intrinsics.areEqual(this.f3214a, contact.f3214a) && Intrinsics.areEqual(this.b, contact.b);
            }
            return false;
        }

        @NotNull
        public final String getName() {
            return this.f3214a;
        }

        @NotNull
        public final String getPhone() {
            return this.b;
        }

        public int hashCode() {
            return (this.f3214a.hashCode() * 31) + this.b.hashCode();
        }

        public final void setName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.f3214a = str;
        }

        public final void setPhone(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.b = str;
        }

        @NotNull
        public String toString() {
            return "Contact(name=" + this.f3214a + ", phone=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3215a;

        public QueueObject(@NotNull BleBaseRequest baseRequest) {
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f3215a = baseRequest;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.f3215a;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.f3215a = bleBaseRequest;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            CloveSmaBleState.BleState.values();
            $EnumSwitchMapping$0 = new int[]{1, 2, 3, 4, 5};
            NotificationType.values();
            int[] iArr = new int[65];
            try {
                NotificationType notificationType = NotificationType.CALL;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                NotificationType notificationType2 = NotificationType.SMS;
                iArr[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                NotificationType notificationType3 = NotificationType.EMAIL;
                iArr[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                NotificationType notificationType4 = NotificationType.SKYPE;
                iArr[14] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                NotificationType notificationType5 = NotificationType.MESSENGER;
                iArr[9] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                NotificationType notificationType6 = NotificationType.FACEBOOK;
                iArr[6] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                NotificationType notificationType7 = NotificationType.WHATSAPP;
                iArr[4] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                NotificationType notificationType8 = NotificationType.LINE;
                iArr[13] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                NotificationType notificationType9 = NotificationType.INSTAGRAM;
                iArr[7] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                NotificationType notificationType10 = NotificationType.TWITTER;
                iArr[8] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                NotificationType notificationType11 = NotificationType.LINKEDIN;
                iArr[15] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                NotificationType notificationType12 = NotificationType.QQ;
                iArr[10] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                NotificationType notificationType13 = NotificationType.WECHAT;
                iArr[5] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                NotificationType notificationType14 = NotificationType.CUSTOM_EVENT;
                iArr[19] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                NotificationType notificationType15 = NotificationType.TELEGRAM;
                iArr[17] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                NotificationType notificationType16 = NotificationType.MISSED_CALL;
                iArr[20] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                NotificationType notificationType17 = NotificationType.FACEBOOK_LITE;
                iArr[21] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                NotificationType notificationType18 = NotificationType.MESSENGER_LITE;
                iArr[22] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                NotificationType notificationType19 = NotificationType.OTHER_APPS;
                iArr[18] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            $EnumSwitchMapping$1 = iArr;
            int[] iArr2 = new int[BleKey.values().length];
            try {
                iArr2[BleKey.POWER.ordinal()] = 1;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr2[BleKey.USER_PROFILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr2[BleKey.FIRMWARE_VERSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr2[BleKey.HEART_RATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr2[BleKey.TEMPERATURE.ordinal()] = 5;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr2[BleKey.BLOOD_PRESSURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr2[BleKey.WORKOUT.ordinal()] = 7;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr2[BleKey.WORKOUT2.ordinal()] = 8;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr2[BleKey.ACTIVITY_REALTIME.ordinal()] = 9;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr2[BleKey.STEP_GOAL.ordinal()] = 10;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr2[BleKey.DISTANCE_GOAL.ordinal()] = 11;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr2[BleKey.CALORIES_GOAL.ordinal()] = 12;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr2[BleKey.SLEEP.ordinal()] = 13;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr2[BleKey.ACTIVITY.ordinal()] = 14;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr2[BleKey.ALARM.ordinal()] = 15;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr2[BleKey.WORLD_CLOCK.ordinal()] = 16;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr2[BleKey.SEDENTARINESS.ordinal()] = 17;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr2[BleKey.NO_DISTURB_RANGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr2[BleKey.GESTURE_WAKE.ordinal()] = 19;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr2[BleKey.CONTACT.ordinal()] = 20;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr2[BleKey.WATCH_FACE.ordinal()] = 21;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr2[BleKey.BLOOD_OXYGEN.ordinal()] = 22;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr2[BleKey.HR_MONITORING.ordinal()] = 23;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr2[BleKey.TEMPERATURE_DETECTING.ordinal()] = 24;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr2[BleKey.HOUR_SYSTEM.ordinal()] = 25;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr2[BleKey.BACK_LIGHT.ordinal()] = 26;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                iArr2[BleKey.SLEEP_QUALITY.ordinal()] = 27;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                iArr2[BleKey.NOTIFICATION.ordinal()] = 28;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                iArr2[BleKey.WEATHER_REALTIME.ordinal()] = 29;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                iArr2[BleKey.WEATHER_REALTIME2.ordinal()] = 30;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                iArr2[BleKey.SCHEDULE.ordinal()] = 31;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                iArr2[BleKey.CAMERA.ordinal()] = 32;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                iArr2[BleKey.TEMPERATURE_UNIT.ordinal()] = 33;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                iArr2[BleKey.GIRL_CARE.ordinal()] = 34;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                iArr2[BleKey.SLEEP_GOAL.ordinal()] = 35;
            } catch (NoSuchFieldError unused54) {
            }
            $EnumSwitchMapping$2 = iArr2;
            int[] iArr3 = new int[SmaErrorType.values().length];
            try {
                iArr3[SmaErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                iArr3[SmaErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused56) {
            }
            $EnumSwitchMapping$3 = iArr3;
            int[] iArr4 = new int[ResponseType.values().length];
            try {
                iArr4[ResponseType.FIND_MY_PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                iArr4[ResponseType.CAMERA_CLICK.ordinal()] = 2;
            } catch (NoSuchFieldError unused58) {
            }
            $EnumSwitchMapping$4 = iArr4;
        }
    }

    public SmaBaseBleApiImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f3213a = context;
        this.b = new Handler();
        this.c = new Handler();
        this.e = new Handler();
        this.g = 300000;
        this.h = 60000;
        this.i = 30000;
        this.j = new Handler(Looper.getMainLooper());
        this.q = new LinkedList<>();
        String simpleName = SmaBaseBleApiImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SmaBaseBleApiImpl::class.java.simpleName");
        this.s = simpleName;
        this.v = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                if (service instanceof SmaF2BleService.BtServiceBinder) {
                    SmaBaseBleApiImpl.this.setBleService(((SmaF2BleService.BtServiceBinder) service).getService());
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(@NotNull ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                SmaBaseBleApiImpl.this.setBleService(null);
            }
        };
        this.x = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.s7
            @Override // java.lang.Runnable
            public final void run() {
                SmaBaseBleApiImpl.d(SmaBaseBleApiImpl.this);
            }
        };
    }

    public static final void access$scanResultRecieved(SmaBaseBleApiImpl smaBaseBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        smaBaseBleApiImpl.getClass();
        ScanDeviceResponse scanDeviceResponse = new ScanDeviceResponse(scanDeviceRequest);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            BleDevice bleDevice = (BleDevice) it.next();
            arrayList.add(new com.coveiot.android.bleabstract.models.BleDevice(bleDevice.getmDevice(), bleDevice.getRssi()));
        }
        scanDeviceResponse.setBluetoothDevices(arrayList);
        scanDeviceResponse.setScanComplete(z);
        if (!scanDeviceRequest.isShouldProvideBatchResult()) {
            scanResultListener.onResponse(scanDeviceResponse);
        } else if (z) {
            scanResultListener.onResponse(scanDeviceResponse);
        }
    }

    public static final void b(DataResultListener dataResultListener, BleBaseResponse tempResponse) {
        Intrinsics.checkNotNullParameter(tempResponse, "$tempResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(tempResponse);
    }

    public static final void c(DataResultListener dataResultListener, BleBaseResponse bpResponse) {
        Intrinsics.checkNotNullParameter(bpResponse, "$bpResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bpResponse);
    }

    public static final void d(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void e(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void f(DataResultListener dataResultListener, BleBaseResponse spO2Response) {
        Intrinsics.checkNotNullParameter(spO2Response, "$spO2Response");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spO2Response);
    }

    public static final void g(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void h(DataResultListener dataResultListener, BleBaseResponse profileResponse) {
        Intrinsics.checkNotNullParameter(profileResponse, "$profileResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(profileResponse);
    }

    public static final void i(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r1 = r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        /*
            r0 = this;
            if (r2 == 0) goto L4
            r1 = r1 | 2
        L4:
            if (r3 == 0) goto L8
            r1 = r1 | 4
        L8:
            if (r4 == 0) goto Lc
            r1 = r1 | 8
        Lc:
            if (r5 == 0) goto L10
            r1 = r1 | 16
        L10:
            if (r6 == 0) goto L14
            r1 = r1 | 32
        L14:
            if (r7 == 0) goto L18
            r1 = r1 | 64
        L18:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl.a(boolean, boolean, boolean, boolean, boolean, boolean, boolean):int");
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.b.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f3213a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = SmaBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        SmaBaseBleApiImpl.access$scanResultRecieved(SmaBaseBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.b.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.u7
            @Override // java.lang.Runnable
            public final void run() {
                SmaBaseBleApiImpl.a(SmaBaseBleApiImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.q) {
            SmaBaseReq smaBleReq = getSmaBleReq(baseRequest);
            if (smaBleReq != null) {
                if (smaBleReq.isPriority()) {
                    this.q.addFirst(new QueueObject(baseRequest));
                    String tag = getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("addToQueue-> added ");
                    BleCommand bleCommand = baseRequest.getBleCommand();
                    sb.append(bleCommand != null ? bleCommand.name() : null);
                    LogHelper.d(tag, sb.toString());
                } else {
                    this.q.add(new QueueObject(baseRequest));
                    String tag2 = getTAG();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("addToQueue-> added ");
                    BleCommand bleCommand2 = baseRequest.getBleCommand();
                    sb2.append(bleCommand2 != null ? bleCommand2.name() : null);
                    LogHelper.d(tag2, sb2.toString());
                }
            } else if (!(baseRequest instanceof ActivityModeWithSamplesRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public void bindBleService() {
        try {
            this.f3213a.bindService(new Intent(this.f3213a, SmaF2BleService.class), this.v, 1);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f3213a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    public final boolean checkIfServiceIsRunning() {
        Object systemService = this.f3213a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (((this instanceof SmaF2BleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaS10BleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaS12BleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaV2BleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaWaveGenesisProBleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaWaveElevateProBleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaWaveGloryProBleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaUltimaVogueBleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaLunarCometBleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || (((this instanceof SmaLunarVelocityBleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())) || ((this instanceof SmaLunarSeekBleApiImpl) && Intrinsics.areEqual(SmaF2BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3213a.getPackageName(), runningServiceInfo.service.getPackageName())))))))))))) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (this.q) {
            LinkedList<QueueObject> linkedList = this.q;
            if (linkedList != null && linkedList.size() > 0) {
                this.q.clear();
            }
        }
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.f = null;
    }

    public final void clearParameters() {
        if (DeviceScanManager.getInstance(this.f3213a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f3213a).stopScan();
        }
        this.t = null;
        unbindService();
        clearCommandQueue();
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.b;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.e;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull final ConnectRequest request, @NotNull final ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.removeCallbacksAndMessages(null);
        this.d = listener;
        this.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.t7
            @Override // java.lang.Runnable
            public final void run() {
                SmaBaseBleApiImpl.a(SmaBaseBleApiImpl.this, request, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        LogHelper.d(getTAG(), "disconnect called");
        this.d = listener;
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            smaBaseBleService.disconnectAndForget();
            clearParameters();
            return;
        }
        listener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Nullable
    public final SmaBaseBleService getBleService() {
        return this.t;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.IDLE;
    }

    @NotNull
    public final Handler getConnectHandler() {
        return this.c;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (this.t != null) {
            ConnectionStatus connectionStatus = getConnectionStatus();
            SmaBaseBleService smaBaseBleService = this.t;
            Intrinsics.checkNotNull(smaBaseBleService);
            ConnectionError connectionError = smaBaseBleService.getConnectionError();
            SmaBaseBleService smaBaseBleService2 = this.t;
            Intrinsics.checkNotNull(smaBaseBleService2);
            return new ConnectionInfo(connectionStatus, connectionError, smaBaseBleService2.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Nullable
    public final ConnectionResultListener getConnectionResultListener() {
        return this.d;
    }

    @Nullable
    public final MutableLiveData<ConnectionStatus> getConnectionStateLiveData() {
        return this.k;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            if (smaBaseBleService.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            SmaBaseBleService smaBaseBleService2 = this.t;
            return (smaBaseBleService2 != null ? smaBaseBleService2.getConnectionState() : null) == CloveSmaBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @SuppressLint({HttpHeaders.RANGE})
    @Nullable
    public final Object getContactBytes(@NotNull Continuation<? super byte[]> continuation) {
        Cursor query = this.f3213a.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, "display_name COLLATE LOCALIZED ASC");
        ArrayList arrayList = new ArrayList();
        if (query != null && query.moveToFirst()) {
            do {
                int columnIndex = query.getColumnIndex("_id");
                int columnIndex2 = query.getColumnIndex("display_name");
                String string = query.getString(columnIndex);
                Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(idColumn)");
                String string2 = query.getString(columnIndex2);
                Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(displayNameColumn)");
                Cursor query2 = this.f3213a.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + string, null, null);
                if (query2 != null && query2.moveToFirst()) {
                    String string3 = query2.getString(query2.getColumnIndex("data1"));
                    Intrinsics.checkNotNullExpressionValue(string3, "phones.getString(\n      …                        )");
                    arrayList.add(new Contact(string2, kotlin.text.m.replace$default(kotlin.text.m.replace$default(string3, "-", "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null)));
                    query2.close();
                }
            } while (query.moveToNext());
            query.close();
        }
        LogHelper.d(getTAG(), arrayList.toString());
        byte[] bArr = new byte[arrayList.size() * 40];
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            byte[] bytes = ((Contact) arrayList.get(i)).getName().getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            int length = bytes.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (i2 < 24) {
                    bArr[(i * 40) + i2] = bytes[i2];
                }
            }
            byte[] bytes2 = ((Contact) arrayList.get(i)).getPhone().getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            int length2 = bytes2.length;
            for (int i3 = 0; i3 < length2; i3++) {
                if (i3 < 16) {
                    bArr[(i * 40) + 24 + i3] = bytes2[i3];
                }
            }
        }
        LogHelper.d(getTAG(), ByteArrayExtKt.getMHexString(bArr));
        return bArr;
    }

    @NotNull
    public final Context getContext() {
        return this.f3213a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            Intrinsics.checkNotNull(smaBaseBleService);
            if (smaBaseBleService.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                sendCommandRequest();
                return;
            }
        }
        String string = this.f3213a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Nullable
    public final DeviceSupportedFeatures getDeviceSupportedFeat() {
        return this.r;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.r = deviceSupportedFeatures;
        BleCache bleCache = BleCache.INSTANCE;
        deviceSupportedFeatures.setStepsSupported(bleCache.getMDataKeys().contains(1282));
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.r;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setSleepSupported(bleCache.getMDataKeys().contains(1285));
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.r;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setHeartRateSupported(bleCache.getMDataKeys().contains(1283));
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.r;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setTemparatureHistorySupported(bleCache.getMDataKeys().contains(1288));
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.r;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setManualBpSupported(bleCache.getMDataKeys().contains(1284));
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.r;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.r;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.r;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.r;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.r;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.r;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.r;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.r;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setPhoneFinderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.r;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.r;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.r;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.r;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.r;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.r;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.r;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.r;
        if (deviceSupportedFeatures21 != null) {
            deviceSupportedFeatures21.setTemperatureUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.r;
        if (deviceSupportedFeatures22 != null) {
            deviceSupportedFeatures22.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.r;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.r;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.r;
        if (deviceSupportedFeatures25 != null) {
            deviceSupportedFeatures25.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.r;
        if (deviceSupportedFeatures26 != null) {
            deviceSupportedFeatures26.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.r;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.r;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setSyncBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.r;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.r;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.r;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setAppSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.r;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.r;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setMaxCharSupportedInNotification(60);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.r;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setAutoTemperatureSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.r;
        if (deviceSupportedFeatures35 != null) {
            deviceSupportedFeatures35.setScheduleReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.r;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.r;
        Intrinsics.checkNotNull(deviceSupportedFeatures37);
        return deviceSupportedFeatures37;
    }

    @Nullable
    public BleBaseRequest getFromQueue(@NotNull SmaBaseReq khBaseReq) {
        Intrinsics.checkNotNullParameter(khBaseReq, "khBaseReq");
        LinkedList<QueueObject> linkedList = this.q;
        if (linkedList != null) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                if (kotlin.text.m.equals(linkedList.get(i).getBaseRequest().getRequId(), khBaseReq.getReqId(), true)) {
                    return linkedList.get(i).getBaseRequest();
                }
            }
            return null;
        }
        return null;
    }

    @Nullable
    public final BleBaseRequest getKhCurrentCommand() {
        return this.f;
    }

    @NotNull
    public final Handler getMDataResponseHandler() {
        return this.j;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManagerSma.getInstance(this.f3213a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        return connectedDeviceMacAddress;
    }

    @NotNull
    public final LinkedList<QueueObject> getQueue() {
        return this.q;
    }

    @NotNull
    public final Handler getScanHandler() {
        return this.b;
    }

    @NotNull
    public final ServiceConnection getServiceConnection() {
        return this.v;
    }

    @Nullable
    public final SmaBaseRes getSmaBaseRes() {
        return this.w;
    }

    /* JADX WARN: Type inference failed for: r1v136, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, java.lang.Object, com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq] */
    /* JADX WARN: Type inference failed for: r1v139, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, java.lang.Object, com.coveiot.android.smasdk.api.SmaDeleteScheduleReq] */
    /* JADX WARN: Type inference failed for: r1v148, types: [com.coveiot.android.smasdk.api.SmaGetWakeGestureDataReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v151, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaGetDNDDataReq] */
    /* JADX WARN: Type inference failed for: r1v154, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetDNDReq] */
    /* JADX WARN: Type inference failed for: r1v161, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaCurrentWeatherReq] */
    /* JADX WARN: Type inference failed for: r1v165, types: [com.coveiot.android.smasdk.api.SmaGetSedentaryDataReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v168, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaGetWorldClockReq] */
    /* JADX WARN: Type inference failed for: r1v171, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaGetAlarmsReq] */
    /* JADX WARN: Type inference failed for: r1v179, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaOtaReq] */
    /* JADX WARN: Type inference failed for: r1v182, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSessionStepDataReq] */
    /* JADX WARN: Type inference failed for: r1v185, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaStepDataReq] */
    /* JADX WARN: Type inference failed for: r1v188, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSleepDataReq] */
    /* JADX WARN: Type inference failed for: r1v191, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaGestureWakeReq] */
    /* JADX WARN: Type inference failed for: r1v197, types: [com.coveiot.android.smasdk.api.SmaBaseReq, com.coveiot.android.smasdk.api.SmaNotificationReq, T] */
    /* JADX WARN: Type inference failed for: r1v205, types: [com.coveiot.android.smasdk.api.SmaWorkoutDataReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v208, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetWorldClockReq] */
    /* JADX WARN: Type inference failed for: r1v225, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetVibrationAlarmReq] */
    /* JADX WARN: Type inference failed for: r1v236, types: [com.coveiot.android.smasdk.api.SmaBaseReq, com.coveiot.android.smasdk.api.SmaSetTemperatureDetectingReq, T] */
    /* JADX WARN: Type inference failed for: r1v242, types: [com.coveiot.android.smasdk.api.SmaManualBPDataReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v245, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaTemperatureDataReq] */
    /* JADX WARN: Type inference failed for: r1v248, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSessionHRDataReq] */
    /* JADX WARN: Type inference failed for: r1v251, types: [com.coveiot.android.smasdk.api.SmaHRDataReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v254, types: [com.coveiot.android.smasdk.api.SmaSetHrMonitoringReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v260, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetHourSystemReq] */
    /* JADX WARN: Type inference failed for: r1v267, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetSedentaryReminderReq] */
    /* JADX WARN: Type inference failed for: r1v277, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaCalorieGoalReq] */
    /* JADX WARN: Type inference failed for: r1v280, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaCalorieGoalReq] */
    /* JADX WARN: Type inference failed for: r1v287, types: [com.coveiot.android.smasdk.api.SmaDistanceGoalReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v290, types: [com.coveiot.android.smasdk.api.SmaDistanceGoalReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v297, types: [com.coveiot.android.smasdk.api.SmaStepGoalReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v300, types: [com.coveiot.android.smasdk.api.SmaStepGoalReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v307, types: [com.coveiot.android.smasdk.api.SmaSleepGoalReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v314, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetUserProfileReq] */
    /* JADX WARN: Type inference failed for: r1v319, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaGetUserProfileReq] */
    /* JADX WARN: Type inference failed for: r1v325, types: [com.coveiot.android.smasdk.api.SmaGetFirmwareVersionReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r1v328, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaGetBatteryReq] */
    /* JADX WARN: Type inference failed for: r1v48, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, java.lang.Object, com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq] */
    /* JADX WARN: Type inference failed for: r1v51, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaScreenTimeOutReq] */
    /* JADX WARN: Type inference failed for: r1v64, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSpO2DataReq, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v67, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetTemperatureUnitReq, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v74, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaExitRemoteCameraReq, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v77, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, java.lang.Object, com.coveiot.android.smasdk.api.SmaStopNotificationReq] */
    /* JADX WARN: Type inference failed for: r2v10, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSleepQualityReq] */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.coveiot.android.smasdk.api.SmaGirlCareSettingsReq, com.coveiot.android.smasdk.api.SmaBaseReq, T] */
    /* JADX WARN: Type inference failed for: r4v26, types: [com.coveiot.android.smasdk.api.SmaBaseReq, T, com.coveiot.android.smasdk.api.SmaSetScheduleReq, java.lang.Object] */
    @Nullable
    public SmaBaseReq getSmaBleReq(@NotNull BleBaseRequest request) {
        Ref.ObjectRef objectRef;
        BleKeyFlag bleKeyFlag;
        BleWeather bleWeather;
        BleWeather bleWeather2;
        String str;
        BleAlarm bleAlarm;
        Intrinsics.checkNotNullParameter(request, "request");
        LogHelper.d(getTAG(), "getSmaBleReq " + request);
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        if (request instanceof BatteryLevelRequest) {
            ?? smaGetBatteryReq = new SmaGetBatteryReq();
            objectRef2.element = smaGetBatteryReq;
            smaGetBatteryReq.setReqId(request.getRequId());
            ((SmaGetBatteryReq) objectRef2.element).setKey(BleKey.POWER);
            ((SmaGetBatteryReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
        } else {
            BleSchedule bleSchedule = null;
            r2 = null;
            BleAlarm bleAlarm2 = null;
            r2 = null;
            BleWeather bleWeather3 = null;
            bleSchedule = null;
            if (request instanceof DeviceInfoRequest) {
                this.u = null;
                DeviceInfoRequest deviceInfoRequest = (DeviceInfoRequest) request;
                if (deviceInfoRequest.isMacAddress()) {
                    if (this.u == null) {
                        this.u = new DeviceInfoData();
                    }
                    DeviceInfoData deviceInfoData = this.u;
                    Intrinsics.checkNotNull(deviceInfoData);
                    deviceInfoData.setMacAddress(getMacAddress());
                }
                if (deviceInfoRequest.isFwVersion()) {
                    ?? smaGetFirmwareVersionReq = new SmaGetFirmwareVersionReq();
                    objectRef2.element = smaGetFirmwareVersionReq;
                    smaGetFirmwareVersionReq.setReqId(request.getRequId());
                    ((SmaGetFirmwareVersionReq) objectRef2.element).setKey(BleKey.FIRMWARE_VERSION);
                    ((SmaGetFirmwareVersionReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                }
            } else if (request instanceof GetFitnessInfoRequest) {
                ?? smaGetUserProfileReq = new SmaGetUserProfileReq();
                objectRef2.element = smaGetUserProfileReq;
                smaGetUserProfileReq.setReqId(request.getRequId());
                ((SmaGetUserProfileReq) objectRef2.element).setKey(BleKey.USER_PROFILE);
                ((SmaGetUserProfileReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
            } else if (request instanceof SetFitnessInfoRequest) {
                ?? smaSetUserProfileReq = new SmaSetUserProfileReq();
                objectRef2.element = smaSetUserProfileReq;
                smaSetUserProfileReq.setReqId(request.getRequId());
                ((SmaSetUserProfileReq) objectRef2.element).setKey(BleKey.USER_PROFILE);
                SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) request;
                ((SmaSetUserProfileReq) objectRef2.element).setUserProfile(new BleUserProfile(setFitnessInfoRequest.getUnitType().getValue(), setFitnessInfoRequest.isMale() ? 1 : 0, setFitnessInfoRequest.getAge(), setFitnessInfoRequest.getHeight(), (float) setFitnessInfoRequest.getWeight()));
                ((SmaSetUserProfileReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
            } else if (request instanceof SetSleepTargetRequest) {
                ?? smaSleepGoalReq = new SmaSleepGoalReq();
                objectRef2.element = smaSleepGoalReq;
                smaSleepGoalReq.setReqId(request.getRequId());
                ((SmaSleepGoalReq) objectRef2.element).setKey(BleKey.SLEEP_GOAL);
                ((SmaSleepGoalReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                ((SmaSleepGoalReq) objectRef2.element).setGoal(((SetSleepTargetRequest) request).getTarget$bleabstract_release());
            } else if (request instanceof StepsTargetRequest) {
                ?? smaStepGoalReq = new SmaStepGoalReq();
                objectRef2.element = smaStepGoalReq;
                smaStepGoalReq.setReqId(request.getRequId());
                ((SmaStepGoalReq) objectRef2.element).setKey(BleKey.STEP_GOAL);
                ((SmaStepGoalReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                ((SmaStepGoalReq) objectRef2.element).setGoal(((StepsTargetRequest) request).getTarget());
            } else if (request instanceof ReadStepTargetRequest) {
                ?? smaStepGoalReq2 = new SmaStepGoalReq();
                objectRef2.element = smaStepGoalReq2;
                smaStepGoalReq2.setReqId(request.getRequId());
                ((SmaStepGoalReq) objectRef2.element).setKey(BleKey.STEP_GOAL);
                ((SmaStepGoalReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
            } else if (request instanceof SetDistanceTargetRequest) {
                ?? smaDistanceGoalReq = new SmaDistanceGoalReq();
                objectRef2.element = smaDistanceGoalReq;
                smaDistanceGoalReq.setReqId(request.getRequId());
                ((SmaDistanceGoalReq) objectRef2.element).setKey(BleKey.DISTANCE_GOAL);
                ((SmaDistanceGoalReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                ((SmaDistanceGoalReq) objectRef2.element).setGoal(((SetDistanceTargetRequest) request).getTarget$bleabstract_release());
            } else {
                boolean z = request instanceof GetCalorieDistanceGoalRequest;
                if (z && ((GetCalorieDistanceGoalRequest) request).getGoalType() == GoalType.DISTANCE) {
                    ?? smaDistanceGoalReq2 = new SmaDistanceGoalReq();
                    objectRef2.element = smaDistanceGoalReq2;
                    smaDistanceGoalReq2.setReqId(request.getRequId());
                    ((SmaDistanceGoalReq) objectRef2.element).setKey(BleKey.DISTANCE_GOAL);
                    ((SmaDistanceGoalReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                } else if (request instanceof SetCalorieTargetRequest) {
                    ?? smaCalorieGoalReq = new SmaCalorieGoalReq();
                    objectRef2.element = smaCalorieGoalReq;
                    smaCalorieGoalReq.setReqId(request.getRequId());
                    ((SmaCalorieGoalReq) objectRef2.element).setKey(BleKey.CALORIES_GOAL);
                    ((SmaCalorieGoalReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                    ((SmaCalorieGoalReq) objectRef2.element).setGoal(((SetCalorieTargetRequest) request).getTarget$bleabstract_release() * 1000);
                } else if (z && ((GetCalorieDistanceGoalRequest) request).getGoalType() == GoalType.CALORIE) {
                    ?? smaCalorieGoalReq2 = new SmaCalorieGoalReq();
                    objectRef2.element = smaCalorieGoalReq2;
                    smaCalorieGoalReq2.setReqId(request.getRequId());
                    ((SmaCalorieGoalReq) objectRef2.element).setKey(BleKey.CALORIES_GOAL);
                    ((SmaCalorieGoalReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                } else if (request instanceof SetReminderRequest) {
                    ?? smaSetSedentaryReminderReq = new SmaSetSedentaryReminderReq();
                    objectRef2.element = smaSetSedentaryReminderReq;
                    smaSetSedentaryReminderReq.setReqId(request.getRequId());
                    ((SmaSetSedentaryReminderReq) objectRef2.element).setKey(BleKey.SEDENTARINESS);
                    ((SmaSetSedentaryReminderReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                    SetReminderRequest setReminderRequest = (SetReminderRequest) request;
                    ((SmaSetSedentaryReminderReq) objectRef2.element).setSedentarinessSettings(new BleSedentarinessSettings(setReminderRequest.isEnabled() ? 1 : 0, a(setReminderRequest.isMondayEnabled(), setReminderRequest.isTuesdayEnabled(), setReminderRequest.isWednesdayEnabled(), setReminderRequest.isThursdayEnabled(), setReminderRequest.isFridayEnabled(), setReminderRequest.isSaturdayEnabled(), setReminderRequest.isSundayEnabled()), setReminderRequest.getStartHour1(), setReminderRequest.getStartMin1(), setReminderRequest.getEndHour1(), setReminderRequest.getEndMin1(), setReminderRequest.getReminderInterval()));
                } else if (request instanceof SetHourFormatRequest) {
                    ?? smaSetHourSystemReq = new SmaSetHourSystemReq();
                    objectRef2.element = smaSetHourSystemReq;
                    smaSetHourSystemReq.setReqId(request.getRequId());
                    ((SmaSetHourSystemReq) objectRef2.element).setKey(BleKey.HOUR_SYSTEM);
                    ((SmaSetHourSystemReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                    ((SmaSetHourSystemReq) objectRef2.element).set12HourFormat(((SetHourFormatRequest) request).is12HourFormat());
                } else {
                    boolean z2 = false;
                    boolean z3 = true;
                    if (request instanceof HeartRateTimeIntervalRequest) {
                        ?? smaSetHrMonitoringReq = new SmaSetHrMonitoringReq();
                        objectRef2.element = smaSetHrMonitoringReq;
                        smaSetHrMonitoringReq.setReqId(request.getRequId());
                        ((SmaSetHrMonitoringReq) objectRef2.element).setKey(BleKey.HR_MONITORING);
                        ((SmaSetHrMonitoringReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                        BleHrMonitoringSettings bleHrMonitoringSettings = new BleHrMonitoringSettings(null, 0, 3, null);
                        HeartRateTimeIntervalRequest heartRateTimeIntervalRequest = (HeartRateTimeIntervalRequest) request;
                        bleHrMonitoringSettings.setMInterval(heartRateTimeIntervalRequest.getTimeInterval());
                        bleHrMonitoringSettings.setMBleTimeRange(new BleTimeRange(heartRateTimeIntervalRequest.getTimeInterval() > 0 ? 1 : 0, heartRateTimeIntervalRequest.getStartHour(), heartRateTimeIntervalRequest.getStartMinute(), heartRateTimeIntervalRequest.getEndHour(), heartRateTimeIntervalRequest.getEndMinute()));
                        ((SmaSetHrMonitoringReq) objectRef2.element).setHrMonitoringSettins(bleHrMonitoringSettings);
                    } else if (request instanceof HeartRateDataRequest) {
                        ?? smaHRDataReq = new SmaHRDataReq();
                        objectRef2.element = smaHRDataReq;
                        smaHRDataReq.setReqId(request.getRequId());
                        ((SmaHRDataReq) objectRef2.element).setKey(BleKey.HEART_RATE);
                        ((SmaHRDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                    } else if (request instanceof SessionHeartRateRequest) {
                        ?? smaSessionHRDataReq = new SmaSessionHRDataReq();
                        objectRef2.element = smaSessionHRDataReq;
                        smaSessionHRDataReq.setReqId(request.getRequId());
                        ((SmaSessionHRDataReq) objectRef2.element).setKey(BleKey.HEART_RATE);
                        ((SmaSessionHRDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                    } else if (request instanceof TemperatureDataRequest) {
                        ?? smaTemperatureDataReq = new SmaTemperatureDataReq();
                        objectRef2.element = smaTemperatureDataReq;
                        smaTemperatureDataReq.setReqId(request.getRequId());
                        ((SmaTemperatureDataReq) objectRef2.element).setKey(BleKey.TEMPERATURE);
                        ((SmaTemperatureDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                    } else if (request instanceof ReadManualBpRequest) {
                        ?? smaManualBPDataReq = new SmaManualBPDataReq();
                        objectRef2.element = smaManualBPDataReq;
                        smaManualBPDataReq.setReqId(request.getRequId());
                        ((SmaManualBPDataReq) objectRef2.element).setKey(BleKey.BLOOD_PRESSURE);
                        ((SmaManualBPDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                    } else if (request instanceof TemperatureTimeIntervalRequest) {
                        ?? smaSetTemperatureDetectingReq = new SmaSetTemperatureDetectingReq();
                        objectRef2.element = smaSetTemperatureDetectingReq;
                        smaSetTemperatureDetectingReq.setReqId(request.getRequId());
                        ((SmaSetTemperatureDetectingReq) objectRef2.element).setKey(BleKey.TEMPERATURE_DETECTING);
                        ((SmaSetTemperatureDetectingReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                        BleTemperatureDetecting bleTemperatureDetecting = new BleTemperatureDetecting(null, 0, 3, null);
                        TemperatureTimeIntervalRequest temperatureTimeIntervalRequest = (TemperatureTimeIntervalRequest) request;
                        bleTemperatureDetecting.setMInterval(temperatureTimeIntervalRequest.getTimeInterval());
                        bleTemperatureDetecting.setMBleTimeRange(new BleTimeRange(temperatureTimeIntervalRequest.getTimeInterval() > 0 ? 1 : 0, temperatureTimeIntervalRequest.getStartHour(), temperatureTimeIntervalRequest.getStartMinute(), temperatureTimeIntervalRequest.getEndHour(), temperatureTimeIntervalRequest.getEndMinute()));
                        ((SmaSetTemperatureDetectingReq) objectRef2.element).setBleTemperatureDetectingReq(bleTemperatureDetecting);
                    } else if (request instanceof SetVibrationAlarmRequest) {
                        ?? smaSetVibrationAlarmReq = new SmaSetVibrationAlarmReq();
                        objectRef2.element = smaSetVibrationAlarmReq;
                        smaSetVibrationAlarmReq.setReqId(request.getRequId());
                        BleKey bleKey = BleKey.ALARM;
                        ((SmaSetVibrationAlarmReq) objectRef2.element).setKey(bleKey);
                        Calendar calendar = Calendar.getInstance();
                        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                        List list$default = BleCache.getList$default(BleCache.INSTANCE, bleKey, BleAlarm.class, null, 4, null);
                        if (!list$default.isEmpty()) {
                            Iterator it = list$default.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                BleAlarm bleAlarm3 = (BleAlarm) it.next();
                                bleAlarm3.getMId();
                                SetVibrationAlarmRequest setVibrationAlarmRequest = (SetVibrationAlarmRequest) request;
                                setVibrationAlarmRequest.getAlarmId();
                                if (bleAlarm3.getMId() == setVibrationAlarmRequest.getAlarmId()) {
                                    bleAlarm2 = bleAlarm3;
                                    break;
                                }
                            }
                        }
                        if (bleAlarm2 != null) {
                            ((SmaSetVibrationAlarmReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                            bleAlarm = bleAlarm2;
                        } else {
                            ((SmaSetVibrationAlarmReq) objectRef2.element).setKeyFlag(BleKeyFlag.CREATE);
                            bleAlarm = new BleAlarm(0, 0, 0, 0, 0, 0, 0, null, 255, null);
                        }
                        SetVibrationAlarmRequest setVibrationAlarmRequest2 = (SetVibrationAlarmRequest) request;
                        bleAlarm.setMEnabled(setVibrationAlarmRequest2.isEnabled() ? 1 : 0);
                        bleAlarm.setMRepeat(a(setVibrationAlarmRequest2.isMondayEnabled(), setVibrationAlarmRequest2.isTuesdayEnabled(), setVibrationAlarmRequest2.isWednesdayEnabled(), setVibrationAlarmRequest2.isThursdayEnabled(), setVibrationAlarmRequest2.isFridayEnabled(), setVibrationAlarmRequest2.isSaturdayEnabled(), setVibrationAlarmRequest2.isSundayEnabled()));
                        ((SmaSetVibrationAlarmReq) objectRef2.element).setBleAlarm(bleAlarm);
                        bleAlarm.setMYear(calendar.get(1));
                        bleAlarm.setMMonth(calendar.get(2) + 1);
                        bleAlarm.setMDay(calendar.get(5));
                        bleAlarm.setMHour(setVibrationAlarmRequest2.getHour());
                        bleAlarm.setMMinute(setVibrationAlarmRequest2.getMinute());
                        bleAlarm.setMTag(setVibrationAlarmRequest2.getEventName().toString());
                        ((SmaSetVibrationAlarmReq) objectRef2.element).setBleAlarm(bleAlarm);
                    } else {
                        String str2 = "";
                        if (request instanceof SetWorldClockListRequest) {
                            ?? smaSetWorldClockReq = new SmaSetWorldClockReq();
                            objectRef2.element = smaSetWorldClockReq;
                            smaSetWorldClockReq.setReqId(request.getRequId());
                            ((SmaSetWorldClockReq) objectRef2.element).setKey(BleKey.WORLD_CLOCK);
                            ((SmaSetWorldClockReq) objectRef2.element).setBleWorldClocks(new ArrayList());
                            ((SmaSetWorldClockReq) objectRef2.element).setKeyFlag(BleKeyFlag.CREATE);
                            SetWorldClockListRequest setWorldClockListRequest = (SetWorldClockListRequest) request;
                            List<SetWorldClockRequest> worldClockRequests = setWorldClockListRequest.getWorldClockRequests();
                            if (worldClockRequests != null && !worldClockRequests.isEmpty()) {
                                z3 = false;
                            }
                            if (!z3) {
                                List<SetWorldClockRequest> worldClockRequests2 = setWorldClockListRequest.getWorldClockRequests();
                                Intrinsics.checkNotNull(worldClockRequests2);
                                for (SetWorldClockRequest setWorldClockRequest : worldClockRequests2) {
                                    BleWorldClock bleWorldClock = new BleWorldClock(0, 0, 0, null, 15, null);
                                    bleWorldClock.setLocal(0);
                                    if (setWorldClockRequest.getCityName() != null) {
                                        str = setWorldClockRequest.getCityName();
                                        Intrinsics.checkNotNull(str);
                                    } else {
                                        str = "";
                                    }
                                    bleWorldClock.setMCityName(str);
                                    bleWorldClock.setMTimeZoneOffset(setWorldClockRequest.getTimeZoneOffsetMinutes() / 15);
                                    List<BleWorldClock> bleWorldClocks = ((SmaSetWorldClockReq) objectRef2.element).getBleWorldClocks();
                                    Intrinsics.checkNotNull(bleWorldClocks);
                                    bleWorldClocks.add(bleWorldClock);
                                }
                            }
                        } else if (request instanceof ActivityModeSummaryRequest) {
                            ?? smaWorkoutDataReq = new SmaWorkoutDataReq();
                            objectRef2.element = smaWorkoutDataReq;
                            smaWorkoutDataReq.setReqId(request.getRequId());
                            ((SmaWorkoutDataReq) objectRef2.element).setKey(BleKey.ACTIVITY);
                            ((SmaWorkoutDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                        } else if (request instanceof SetMessageContentRequest) {
                            ?? smaNotificationReq = new SmaNotificationReq();
                            objectRef2.element = smaNotificationReq;
                            smaNotificationReq.setReqId(request.getRequId());
                            ((SmaNotificationReq) objectRef2.element).setKey(BleKey.NOTIFICATION);
                            ((SmaNotificationReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                            SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) request;
                            NotificationType appNotificationType = setMessageContentRequest.getAppNotificationType();
                            LogHelper.d(getTAG(), "appNotificationType from App" + appNotificationType);
                            String str3 = setMessageContentRequest.title;
                            if (str3 != null) {
                                Intrinsics.checkNotNullExpressionValue(str3, "request.title");
                                if ((str3.length() > 0 ? 1 : null) != null) {
                                    str2 = setMessageContentRequest.title;
                                    Intrinsics.checkNotNullExpressionValue(str2, "request.title");
                                }
                            }
                            String str4 = str2;
                            switch (appNotificationType != null ? WhenMappings.$EnumSwitchMapping$1[appNotificationType.ordinal()] : -1) {
                                case 1:
                                    long time = new Date().getTime();
                                    String str5 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str5, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(1, time, null, str5, "Incoming call", 4, null));
                                    break;
                                case 2:
                                    long time2 = new Date().getTime();
                                    String str6 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str6, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time2, "com.android.mms", str4, str6));
                                    break;
                                case 3:
                                    long time3 = new Date().getTime();
                                    String str7 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str7, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time3, BleNotification.GMAIL, str4, str7));
                                    break;
                                case 4:
                                    long time4 = new Date().getTime();
                                    String str8 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str8, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time4, BleNotification.SKYPE, str4, str8));
                                    break;
                                case 5:
                                    long time5 = new Date().getTime();
                                    String str9 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str9, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time5, BleNotification.FACEBOOK_MESSENGER, str4, str9));
                                    break;
                                case 6:
                                    long time6 = new Date().getTime();
                                    String str10 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str10, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time6, BleNotification.FACEBOOK, str4, str10));
                                    break;
                                case 7:
                                    long time7 = new Date().getTime();
                                    String str11 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str11, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time7, BleNotification.WHATS_APP, str4, str11));
                                    break;
                                case 8:
                                    long time8 = new Date().getTime();
                                    String str12 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str12, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time8, BleNotification.LINE, str4, str12));
                                    break;
                                case 9:
                                    long time9 = new Date().getTime();
                                    String str13 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str13, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time9, BleNotification.INSTAGRAM, str4, str13));
                                    break;
                                case 10:
                                    long time10 = new Date().getTime();
                                    String str14 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str14, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time10, BleNotification.TWITTER, str4, str14));
                                    break;
                                case 11:
                                    long time11 = new Date().getTime();
                                    String str15 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str15, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time11, BleNotification.LINKED_IN, str4, str15));
                                    break;
                                case 12:
                                    long time12 = new Date().getTime();
                                    String str16 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str16, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time12, BleNotification.QQ, str4, str16));
                                    break;
                                case 13:
                                    long time13 = new Date().getTime();
                                    String str17 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str17, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time13, BleNotification.WE_CHAT, str4, str17));
                                    break;
                                case 14:
                                    long time14 = new Date().getTime();
                                    String str18 = setMessageContentRequest.customPackage;
                                    Intrinsics.checkNotNullExpressionValue(str18, "request.customPackage");
                                    String str19 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str19, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time14, str18, str4, str19));
                                    break;
                                case 15:
                                    long time15 = new Date().getTime();
                                    String str20 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str20, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time15, BleNotification.TELEGRAM, str4, str20));
                                    break;
                                case 16:
                                    long time16 = new Date().getTime();
                                    String str21 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str21, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time16, BleNotification.PACKAGE_MISSED_CALL, str4, str21));
                                    break;
                                case 17:
                                    long time17 = new Date().getTime();
                                    String str22 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str22, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time17, BleNotification.FACEBOOK_LITE, str4, str22));
                                    break;
                                case 18:
                                    long time18 = new Date().getTime();
                                    String str23 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str23, "request.message");
                                    ((SmaNotificationReq) objectRef2.element).setBleNotification(new BleNotification(127, time18, BleNotification.FACEBOOK_MESSENGER_LITE, str4, str23));
                                    break;
                                case 19:
                                    SmaNotificationReq smaNotificationReq2 = (SmaNotificationReq) objectRef2.element;
                                    long time19 = new Date().getTime();
                                    String str24 = setMessageContentRequest.customPackage;
                                    String packageName = (str24 == null || str24.length() == 0) ? true : true ? this.f3213a.getPackageName() : setMessageContentRequest.customPackage;
                                    Intrinsics.checkNotNullExpressionValue(packageName, "if(request.customPackage…lse request.customPackage");
                                    String str25 = setMessageContentRequest.message;
                                    Intrinsics.checkNotNullExpressionValue(str25, "request.message");
                                    smaNotificationReq2.setBleNotification(new BleNotification(127, time19, packageName, str4, str25));
                                    break;
                            }
                        } else if (request instanceof SetLiftWristRequest) {
                            ?? smaGestureWakeReq = new SmaGestureWakeReq();
                            objectRef2.element = smaGestureWakeReq;
                            smaGestureWakeReq.setReqId(request.getRequId());
                            ((SmaGestureWakeReq) objectRef2.element).setKey(BleKey.GESTURE_WAKE);
                            ((SmaGestureWakeReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                            BleGestureWake bleGestureWake = new BleGestureWake(null, 1, null);
                            SetLiftWristRequest setLiftWristRequest = (SetLiftWristRequest) request;
                            bleGestureWake.setMBleTimeRange(new BleTimeRange(setLiftWristRequest.isLiftWristEnabled() ? 1 : 0, setLiftWristRequest.getStartHour(), setLiftWristRequest.getStartMinute(), setLiftWristRequest.getEndHour(), setLiftWristRequest.getEndMinute()));
                            ((SmaGestureWakeReq) objectRef2.element).setGestureWake(bleGestureWake);
                        } else if (request instanceof SleepDataRequest) {
                            ?? smaSleepDataReq = new SmaSleepDataReq();
                            objectRef2.element = smaSleepDataReq;
                            smaSleepDataReq.setReqId(request.getRequId());
                            ((SmaSleepDataReq) objectRef2.element).setKey(BleKey.SLEEP);
                            ((SmaSleepDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                        } else if (request instanceof StepsDataRequest) {
                            ?? smaStepDataReq = new SmaStepDataReq();
                            objectRef2.element = smaStepDataReq;
                            smaStepDataReq.setReqId(request.getRequId());
                            ((SmaStepDataReq) objectRef2.element).setKey(BleKey.ACTIVITY);
                            ((SmaStepDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                        } else if (request instanceof SessionStepsDataRequest) {
                            ?? smaSessionStepDataReq = new SmaSessionStepDataReq();
                            objectRef2.element = smaSessionStepDataReq;
                            smaSessionStepDataReq.setReqId(request.getRequId());
                            ((SmaSessionStepDataReq) objectRef2.element).setKey(BleKey.ACTIVITY);
                            ((SmaSessionStepDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                        } else if (request instanceof OTAModeRequest) {
                            ?? smaOtaReq = new SmaOtaReq();
                            objectRef2.element = smaOtaReq;
                            smaOtaReq.setReqId(request.getRequId());
                            ((SmaOtaReq) objectRef2.element).setKey(BleKey.OTA);
                            ((SmaOtaReq) objectRef2.element).setKeyFlag(BleKeyFlag.UPDATE);
                        } else if (request instanceof ActivityModeWithSamplesRequest) {
                            if (isHeartRateDataSyncRequired()) {
                                SessionHeartRateRequest sessionHeartRateRequest = new SessionHeartRateRequest();
                                BaseListener responseListener = request.getResponseListener();
                                Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                                getData(sessionHeartRateRequest, (DataResultListener) responseListener);
                            }
                            ActivityModeSummaryRequest activityModeSummaryRequest = new ActivityModeSummaryRequest();
                            BaseListener responseListener2 = request.getResponseListener();
                            Intrinsics.checkNotNull(responseListener2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                            getData(activityModeSummaryRequest, (DataResultListener) responseListener2);
                            if (isStepsDataSyncRequired()) {
                                SessionStepsDataRequest sessionStepsDataRequest = new SessionStepsDataRequest();
                                BaseListener responseListener3 = request.getResponseListener();
                                Intrinsics.checkNotNull(responseListener3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                                getData(sessionStepsDataRequest, (DataResultListener) responseListener3);
                            }
                        } else if (request instanceof GetAlarmDataRequest) {
                            ?? smaGetAlarmsReq = new SmaGetAlarmsReq();
                            objectRef2.element = smaGetAlarmsReq;
                            smaGetAlarmsReq.setReqId(request.getRequId());
                            ((SmaGetAlarmsReq) objectRef2.element).setKey(BleKey.ALARM);
                            ((SmaGetAlarmsReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                        } else if (request instanceof GetWorldClockDataRequest) {
                            ?? smaGetWorldClockReq = new SmaGetWorldClockReq();
                            objectRef2.element = smaGetWorldClockReq;
                            smaGetWorldClockReq.setReqId(request.getRequId());
                            ((SmaGetWorldClockReq) objectRef2.element).setKey(BleKey.WORLD_CLOCK);
                            ((SmaGetWorldClockReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                        } else if (request instanceof GetSedentaryDataRequest) {
                            ?? smaGetSedentaryDataReq = new SmaGetSedentaryDataReq();
                            objectRef2.element = smaGetSedentaryDataReq;
                            smaGetSedentaryDataReq.setReqId(request.getRequId());
                            ((SmaGetSedentaryDataReq) objectRef2.element).setKey(BleKey.SEDENTARINESS);
                            ((SmaGetSedentaryDataReq) objectRef2.element).setKeyFlag(BleKeyFlag.READ);
                        } else {
                            if (request instanceof SendWeatherRequest) {
                                long j = 1000;
                                int currentTimeMillis = (int) (System.currentTimeMillis() / j);
                                SendWeatherRequest sendWeatherRequest = (SendWeatherRequest) request;
                                List<SendWeatherModel> listSendWeatherModel = sendWeatherRequest.getListSendWeatherModel();
                                if (((listSendWeatherModel == null || listSendWeatherModel.isEmpty()) ? 1 : null) == null) {
                                    List<SendWeatherModel> listSendWeatherModel2 = sendWeatherRequest.getListSendWeatherModel();
                                    Intrinsics.checkNotNull(listSendWeatherModel2);
                                    bleWeather = null;
                                    bleWeather2 = null;
                                    for (SendWeatherModel sendWeatherModel : listSendWeatherModel2) {
                                        BleWeather bleWeather4 = new BleWeather(0, 0, 0, 0, 0, 0, 0, 0, 0, 511, null);
                                        Intrinsics.checkNotNull(sendWeatherModel);
                                        bleWeather4.setMCurrentTemperature(sendWeatherModel.getTemp());
                                        bleWeather4.setMHumidity(sendWeatherModel.getHumidity());
                                        bleWeather4.setMMaxTemperature(sendWeatherModel.getTempMax());
                                        bleWeather4.setMMinTemperature(sendWeatherModel.getTempMin());
                                        SmaUtils smaUtils = SmaUtils.INSTANCE;
                                        String weatherText = sendWeatherModel.getWeatherText();
                                        Intrinsics.checkNotNullExpressionValue(weatherText, "value!!.weatherText");
                                        bleWeather4.setMWeatherCode(smaUtils.getWeatherType(weatherText));
                                        Ref.ObjectRef objectRef3 = objectRef2;
                                        bleWeather4.setMWindSpeed((int) ((sendWeatherModel.getWindSpeed() / 100) * 3.6d));
                                        bleWeather4.setMVisibility(sendWeatherModel.getVisibility() / 1000);
                                        if (bleWeather3 == null && DateUtils.isToday(sendWeatherModel.getTime().longValue() * j)) {
                                            bleWeather3 = bleWeather4;
                                        } else if (bleWeather == null && bleWeather3 != null) {
                                            bleWeather = bleWeather4;
                                        } else if (bleWeather2 == null && bleWeather3 != null && bleWeather != null) {
                                            bleWeather2 = bleWeather4;
                                        }
                                        objectRef2 = objectRef3;
                                    }
                                    objectRef = objectRef2;
                                } else {
                                    objectRef = objectRef2;
                                    bleWeather = null;
                                    bleWeather2 = null;
                                }
                                ?? smaCurrentWeatherReq = new SmaCurrentWeatherReq(sendWeatherRequest.getTempUnitType() == SendWeatherRequest.TemperatureUnitType.CENTIGRADE ? 0 : 1, new BleWeatherRealtime(currentTimeMillis, bleWeather3), new BleWeatherForecast(currentTimeMillis, bleWeather3, bleWeather, bleWeather2));
                                objectRef.element = smaCurrentWeatherReq;
                                smaCurrentWeatherReq.setKey(BleKey.WEATHER_REALTIME);
                                ((SmaCurrentWeatherReq) objectRef.element).setReqId(request.getRequId());
                                ((SmaCurrentWeatherReq) objectRef.element).setKeyFlag(BleKeyFlag.UPDATE);
                            } else {
                                objectRef = objectRef2;
                                if (request instanceof SetDNDModeRequest) {
                                    ?? smaSetDNDReq = new SmaSetDNDReq();
                                    objectRef.element = smaSetDNDReq;
                                    smaSetDNDReq.setReqId(request.getRequId());
                                    ((SmaSetDNDReq) objectRef.element).setKey(BleKey.NO_DISTURB_RANGE);
                                    ((SmaSetDNDReq) objectRef.element).setKeyFlag(BleKeyFlag.UPDATE);
                                    BleNoDisturbSettings bleNoDisturbSettings = new BleNoDisturbSettings(0, null, null, null, 15, null);
                                    BleCache bleCache = BleCache.INSTANCE;
                                    if (bleCache.getMDeviceInfo() != null) {
                                        BleDeviceInfo mDeviceInfo = bleCache.getMDeviceInfo();
                                        Intrinsics.checkNotNull(mDeviceInfo);
                                        mDeviceInfo.getMSupportNoDisturbSet();
                                        BleDeviceInfo mDeviceInfo2 = bleCache.getMDeviceInfo();
                                        Intrinsics.checkNotNull(mDeviceInfo2);
                                        if (mDeviceInfo2.getMSupportNoDisturbSet() == 1) {
                                            SetDNDModeRequest setDNDModeRequest = (SetDNDModeRequest) request;
                                            bleNoDisturbSettings.setMEnabled((setDNDModeRequest.isDNDEnabled() && setDNDModeRequest.getStartHour() == 0 && setDNDModeRequest.getStartMin() == 0 && setDNDModeRequest.getEndHour() == 0 && setDNDModeRequest.getEndMin() == 0) ? 1 : 0);
                                            SetDNDModeRequest setDNDModeRequest2 = (SetDNDModeRequest) request;
                                            bleNoDisturbSettings.setMBleTimeRange1(new BleTimeRange(setDNDModeRequest2.isDNDEnabled() ? 1 : 0, setDNDModeRequest2.getStartHour(), setDNDModeRequest2.getStartMin(), setDNDModeRequest2.getEndHour(), setDNDModeRequest2.getEndMin()));
                                            ((SmaSetDNDReq) objectRef.element).setBleDndReq(bleNoDisturbSettings);
                                        }
                                    }
                                    bleNoDisturbSettings.setMEnabled(((SetDNDModeRequest) request).isDNDEnabled() ? 1 : 0);
                                    SetDNDModeRequest setDNDModeRequest22 = (SetDNDModeRequest) request;
                                    bleNoDisturbSettings.setMBleTimeRange1(new BleTimeRange(setDNDModeRequest22.isDNDEnabled() ? 1 : 0, setDNDModeRequest22.getStartHour(), setDNDModeRequest22.getStartMin(), setDNDModeRequest22.getEndHour(), setDNDModeRequest22.getEndMin()));
                                    ((SmaSetDNDReq) objectRef.element).setBleDndReq(bleNoDisturbSettings);
                                } else if (request instanceof GetDNDModeSettingsRequest) {
                                    ?? smaGetDNDDataReq = new SmaGetDNDDataReq();
                                    objectRef.element = smaGetDNDDataReq;
                                    smaGetDNDDataReq.setReqId(request.getRequId());
                                    ((SmaGetDNDDataReq) objectRef.element).setKey(BleKey.NO_DISTURB_RANGE);
                                    ((SmaGetDNDDataReq) objectRef.element).setKeyFlag(BleKeyFlag.READ);
                                } else if (request instanceof GetLiftWristSettingsRequest) {
                                    ?? smaGetWakeGestureDataReq = new SmaGetWakeGestureDataReq();
                                    objectRef.element = smaGetWakeGestureDataReq;
                                    smaGetWakeGestureDataReq.setReqId(request.getRequId());
                                    ((SmaGetWakeGestureDataReq) objectRef.element).setKey(BleKey.GESTURE_WAKE);
                                    ((SmaGetWakeGestureDataReq) objectRef.element).setKeyFlag(BleKeyFlag.READ);
                                } else {
                                    if (request instanceof UploadContactRequest) {
                                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new SmaBaseBleApiImpl$getSmaBleReq$2(this, objectRef, request, null), 3, null);
                                    } else if (request instanceof SetScheduleRequest) {
                                        List list$default2 = BleCache.getList$default(BleCache.INSTANCE, BleKey.SCHEDULE, BleSchedule.class, null, 4, null);
                                        LogHelper.d(getTAG(), "schedules-> " + list$default2);
                                        if (!list$default2.isEmpty()) {
                                            Iterator it2 = list$default2.iterator();
                                            while (true) {
                                                if (!it2.hasNext()) {
                                                    break;
                                                }
                                                BleSchedule bleSchedule2 = (BleSchedule) it2.next();
                                                bleSchedule2.getMId();
                                                SetScheduleRequest setScheduleRequest = (SetScheduleRequest) request;
                                                setScheduleRequest.getScheduleId();
                                                if (bleSchedule2.getMId() == setScheduleRequest.getScheduleId()) {
                                                    bleSchedule = bleSchedule2;
                                                    break;
                                                }
                                            }
                                        }
                                        if (bleSchedule != null) {
                                            bleKeyFlag = BleKeyFlag.UPDATE;
                                        } else {
                                            bleKeyFlag = BleKeyFlag.CREATE;
                                            bleSchedule = new BleSchedule(0, 0, 0, 0, 0, 0, null, null, 255, null);
                                        }
                                        SetScheduleRequest setScheduleRequest2 = (SetScheduleRequest) request;
                                        bleSchedule.setMYear(setScheduleRequest2.getYear());
                                        bleSchedule.setMMonth(setScheduleRequest2.getMonth() + 1);
                                        bleSchedule.setMDay(setScheduleRequest2.getDay());
                                        bleSchedule.setMHour(setScheduleRequest2.getHour());
                                        bleSchedule.setMMinute(setScheduleRequest2.getMinute());
                                        bleSchedule.setMAdvance(setScheduleRequest2.getAdvance());
                                        String title = setScheduleRequest2.getTitle();
                                        Intrinsics.checkNotNullExpressionValue(title, "request.title");
                                        bleSchedule.setMTitle(title);
                                        String content = setScheduleRequest2.getContent();
                                        Intrinsics.checkNotNullExpressionValue(content, "request.content");
                                        bleSchedule.setMContent(content);
                                        ?? smaSetScheduleReq = new SmaSetScheduleReq(bleSchedule);
                                        objectRef.element = smaSetScheduleReq;
                                        Intrinsics.checkNotNull(smaSetScheduleReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSetScheduleReq");
                                        smaSetScheduleReq.setReqId(request.getRequId());
                                        T t = objectRef.element;
                                        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSetScheduleReq");
                                        ((SmaSetScheduleReq) t).setKey(BleKey.SCHEDULE);
                                        T t2 = objectRef.element;
                                        Intrinsics.checkNotNull(t2, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSetScheduleReq");
                                        ((SmaSetScheduleReq) t2).setKeyFlag(bleKeyFlag);
                                    } else if (request instanceof DeleteScheduleRequest) {
                                        ?? smaDeleteScheduleReq = new SmaDeleteScheduleReq(((DeleteScheduleRequest) request).getScheduleId());
                                        objectRef.element = smaDeleteScheduleReq;
                                        Intrinsics.checkNotNull(smaDeleteScheduleReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaDeleteScheduleReq");
                                        smaDeleteScheduleReq.setReqId(request.getRequId());
                                        T t3 = objectRef.element;
                                        Intrinsics.checkNotNull(t3, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaDeleteScheduleReq");
                                        ((SmaDeleteScheduleReq) t3).setKey(BleKey.SCHEDULE);
                                        T t4 = objectRef.element;
                                        Intrinsics.checkNotNull(t4, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaDeleteScheduleReq");
                                        ((SmaDeleteScheduleReq) t4).setKeyFlag(BleKeyFlag.DELETE);
                                    } else if (request instanceof CustomWatchFaceFileImageRequest) {
                                        CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest = (CustomWatchFaceFileImageRequest) request;
                                        ?? smaUploadWatchFaceReq = new SmaUploadWatchFaceReq(Integer.valueOf(customWatchFaceFileImageRequest.watchFaceResourceID), customWatchFaceFileImageRequest.watchFaceFilePath, null);
                                        objectRef.element = smaUploadWatchFaceReq;
                                        Intrinsics.checkNotNull(smaUploadWatchFaceReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq");
                                        smaUploadWatchFaceReq.setReqId(request.getRequId());
                                        T t5 = objectRef.element;
                                        Intrinsics.checkNotNull(t5, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq");
                                        ((SmaUploadWatchFaceReq) t5).setKey(BleKey.WATCH_FACE);
                                        T t6 = objectRef.element;
                                        Intrinsics.checkNotNull(t6, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq");
                                        ((SmaUploadWatchFaceReq) t6).setKeyFlag(BleKeyFlag.UPDATE);
                                    } else if (request instanceof StopMessageNotificationRequest) {
                                        ?? smaStopNotificationReq = new SmaStopNotificationReq(new BleNotification(0, 0L, null, null, null, 31, null));
                                        objectRef.element = smaStopNotificationReq;
                                        Intrinsics.checkNotNull(smaStopNotificationReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                        smaStopNotificationReq.setReqId(request.getRequId());
                                        T t7 = objectRef.element;
                                        Intrinsics.checkNotNull(t7, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                        ((SmaStopNotificationReq) t7).setKey(BleKey.NOTIFICATION);
                                        T t8 = objectRef.element;
                                        Intrinsics.checkNotNull(t8, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                        ((SmaStopNotificationReq) t8).setKeyFlag(BleKeyFlag.DELETE);
                                        NotificationType appNotificationType2 = ((StopMessageNotificationRequest) request).getAppNotificationType();
                                        LogHelper.d(getTAG(), "appNotificationType from App" + appNotificationType2);
                                        switch (appNotificationType2 != null ? WhenMappings.$EnumSwitchMapping$1[appNotificationType2.ordinal()] : -1) {
                                            case 1:
                                                T t9 = objectRef.element;
                                                Intrinsics.checkNotNull(t9, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t9).getBleNotification().setMCategory(1);
                                                break;
                                            case 2:
                                                T t10 = objectRef.element;
                                                Intrinsics.checkNotNull(t10, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t10).getBleNotification().setMCategory(127);
                                                break;
                                            case 3:
                                                T t11 = objectRef.element;
                                                Intrinsics.checkNotNull(t11, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t11).getBleNotification().setMCategory(127);
                                                T t12 = objectRef.element;
                                                Intrinsics.checkNotNull(t12, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t12).getBleNotification().setMPackage(BleNotification.ANDROID_EMAIL);
                                                break;
                                            case 4:
                                                T t13 = objectRef.element;
                                                Intrinsics.checkNotNull(t13, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t13).getBleNotification().setMCategory(127);
                                                T t14 = objectRef.element;
                                                Intrinsics.checkNotNull(t14, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t14).getBleNotification().setMPackage(BleNotification.SKYPE);
                                                break;
                                            case 5:
                                                T t15 = objectRef.element;
                                                Intrinsics.checkNotNull(t15, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t15).getBleNotification().setMCategory(127);
                                                T t16 = objectRef.element;
                                                Intrinsics.checkNotNull(t16, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t16).getBleNotification().setMPackage(BleNotification.FACEBOOK_MESSENGER);
                                                break;
                                            case 6:
                                                T t17 = objectRef.element;
                                                Intrinsics.checkNotNull(t17, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t17).getBleNotification().setMCategory(127);
                                                T t18 = objectRef.element;
                                                Intrinsics.checkNotNull(t18, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t18).getBleNotification().setMPackage(BleNotification.FACEBOOK);
                                                break;
                                            case 7:
                                                T t19 = objectRef.element;
                                                Intrinsics.checkNotNull(t19, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t19).getBleNotification().setMCategory(127);
                                                T t20 = objectRef.element;
                                                Intrinsics.checkNotNull(t20, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t20).getBleNotification().setMPackage(BleNotification.WHATS_APP);
                                                break;
                                            case 8:
                                                T t21 = objectRef.element;
                                                Intrinsics.checkNotNull(t21, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t21).getBleNotification().setMCategory(127);
                                                T t22 = objectRef.element;
                                                Intrinsics.checkNotNull(t22, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t22).getBleNotification().setMPackage(BleNotification.LINE);
                                                break;
                                            case 9:
                                                T t23 = objectRef.element;
                                                Intrinsics.checkNotNull(t23, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t23).getBleNotification().setMCategory(127);
                                                T t24 = objectRef.element;
                                                Intrinsics.checkNotNull(t24, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t24).getBleNotification().setMPackage(BleNotification.INSTAGRAM);
                                                break;
                                            case 10:
                                                T t25 = objectRef.element;
                                                Intrinsics.checkNotNull(t25, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t25).getBleNotification().setMCategory(127);
                                                T t26 = objectRef.element;
                                                Intrinsics.checkNotNull(t26, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t26).getBleNotification().setMPackage(BleNotification.TWITTER);
                                                break;
                                            case 11:
                                                T t27 = objectRef.element;
                                                Intrinsics.checkNotNull(t27, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t27).getBleNotification().setMCategory(127);
                                                T t28 = objectRef.element;
                                                Intrinsics.checkNotNull(t28, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t28).getBleNotification().setMPackage(BleNotification.LINKED_IN);
                                                break;
                                            case 12:
                                                T t29 = objectRef.element;
                                                Intrinsics.checkNotNull(t29, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t29).getBleNotification().setMCategory(127);
                                                T t30 = objectRef.element;
                                                Intrinsics.checkNotNull(t30, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t30).getBleNotification().setMPackage(BleNotification.QQ);
                                                break;
                                            case 13:
                                                T t31 = objectRef.element;
                                                Intrinsics.checkNotNull(t31, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t31).getBleNotification().setMCategory(127);
                                                T t32 = objectRef.element;
                                                Intrinsics.checkNotNull(t32, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t32).getBleNotification().setMPackage(BleNotification.WE_CHAT);
                                                break;
                                            case 15:
                                                T t33 = objectRef.element;
                                                Intrinsics.checkNotNull(t33, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t33).getBleNotification().setMCategory(127);
                                                T t34 = objectRef.element;
                                                Intrinsics.checkNotNull(t34, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaStopNotificationReq");
                                                ((SmaStopNotificationReq) t34).getBleNotification().setMPackage(BleNotification.TELEGRAM);
                                                break;
                                        }
                                    } else if (request instanceof ExitRemoteCameraRequest) {
                                        ?? smaExitRemoteCameraReq = new SmaExitRemoteCameraReq();
                                        objectRef.element = smaExitRemoteCameraReq;
                                        Intrinsics.checkNotNull(smaExitRemoteCameraReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaExitRemoteCameraReq");
                                        smaExitRemoteCameraReq.setReqId(request.getRequId());
                                        T t35 = objectRef.element;
                                        Intrinsics.checkNotNull(t35, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaExitRemoteCameraReq");
                                        ((SmaExitRemoteCameraReq) t35).setKey(BleKey.CAMERA);
                                        T t36 = objectRef.element;
                                        Intrinsics.checkNotNull(t36, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaExitRemoteCameraReq");
                                        ((SmaExitRemoteCameraReq) t36).setKeyFlag(BleKeyFlag.UPDATE);
                                    } else if (request instanceof SetTemperatureUnitRequest) {
                                        ?? smaSetTemperatureUnitReq = new SmaSetTemperatureUnitReq();
                                        objectRef.element = smaSetTemperatureUnitReq;
                                        Intrinsics.checkNotNull(smaSetTemperatureUnitReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSetTemperatureUnitReq");
                                        smaSetTemperatureUnitReq.setReqId(request.getRequId());
                                        T t37 = objectRef.element;
                                        Intrinsics.checkNotNull(t37, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSetTemperatureUnitReq");
                                        ((SmaSetTemperatureUnitReq) t37).setKey(BleKey.TEMPERATURE_UNIT);
                                        T t38 = objectRef.element;
                                        Intrinsics.checkNotNull(t38, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSetTemperatureUnitReq");
                                        ((SmaSetTemperatureUnitReq) t38).setKeyFlag(BleKeyFlag.UPDATE);
                                        T t39 = objectRef.element;
                                        Intrinsics.checkNotNull(t39, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSetTemperatureUnitReq");
                                        ((SmaSetTemperatureUnitReq) t39).setUnit(!((SetTemperatureUnitRequest) request).isTemperatureInCelsius());
                                    } else if (request instanceof ReadManualSpo2Request) {
                                        ?? smaSpO2DataReq = new SmaSpO2DataReq();
                                        objectRef.element = smaSpO2DataReq;
                                        Intrinsics.checkNotNull(smaSpO2DataReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSpO2DataReq");
                                        smaSpO2DataReq.setReqId(request.getRequId());
                                        T t40 = objectRef.element;
                                        Intrinsics.checkNotNull(t40, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSpO2DataReq");
                                        ((SmaSpO2DataReq) t40).setKey(BleKey.BLOOD_OXYGEN);
                                        T t41 = objectRef.element;
                                        Intrinsics.checkNotNull(t41, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaSpO2DataReq");
                                        ((SmaSpO2DataReq) t41).setKeyFlag(BleKeyFlag.READ);
                                    } else if (request instanceof SetWomenWellnessSettingsRequest) {
                                        BleGirlCareSettings bleGirlCareSettings = new BleGirlCareSettings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1023, null);
                                        SetWomenWellnessSettingsRequest setWomenWellnessSettingsRequest = (SetWomenWellnessSettingsRequest) request;
                                        bleGirlCareSettings.setMEnabled(setWomenWellnessSettingsRequest.getMEnabled() ? 1 : 0);
                                        bleGirlCareSettings.setMReminderHour(setWomenWellnessSettingsRequest.getMReminderHour());
                                        bleGirlCareSettings.setMReminderMinute(setWomenWellnessSettingsRequest.getMReminderMinute());
                                        bleGirlCareSettings.setMMenstruationReminderAdvance(setWomenWellnessSettingsRequest.getMPeriodReminderAdvance());
                                        bleGirlCareSettings.setMOvulationReminderAdvance(setWomenWellnessSettingsRequest.getMOvulationReminderAdvance());
                                        bleGirlCareSettings.setMLatestYear(setWomenWellnessSettingsRequest.getMLastPeriodYear());
                                        bleGirlCareSettings.setMLatestMonth(setWomenWellnessSettingsRequest.getMLastPeriodMonth());
                                        bleGirlCareSettings.setMLatestDay(setWomenWellnessSettingsRequest.getMLastPeriodDay());
                                        bleGirlCareSettings.setMMenstruationPeriod(setWomenWellnessSettingsRequest.getMMenstruationCycleLength());
                                        bleGirlCareSettings.setMMenstruationDuration(setWomenWellnessSettingsRequest.getMMenstruationPeriodLength());
                                        ?? smaGirlCareSettingsReq = new SmaGirlCareSettingsReq(bleGirlCareSettings);
                                        objectRef.element = smaGirlCareSettingsReq;
                                        smaGirlCareSettingsReq.setReqId(request.getRequId());
                                        SmaBaseReq smaBaseReq = (SmaBaseReq) objectRef.element;
                                        if (smaBaseReq != null) {
                                            smaBaseReq.setKey(BleKey.GIRL_CARE);
                                        }
                                        SmaBaseReq smaBaseReq2 = (SmaBaseReq) objectRef.element;
                                        if (smaBaseReq2 != null) {
                                            smaBaseReq2.setKeyFlag(BleKeyFlag.UPDATE);
                                        }
                                    } else if (request instanceof SendSleepDataRequest) {
                                        SendSleepDataRequest sendSleepDataRequest = (SendSleepDataRequest) request;
                                        ?? smaSleepQualityReq = new SmaSleepQualityReq(new BleSleepQuality(sendSleepDataRequest.getTotalLightSleep(), sendSleepDataRequest.getTotalDeepSleep(), sendSleepDataRequest.getTotalSleep()));
                                        objectRef.element = smaSleepQualityReq;
                                        smaSleepQualityReq.setReqId(request.getRequId());
                                        SmaBaseReq smaBaseReq3 = (SmaBaseReq) objectRef.element;
                                        if (smaBaseReq3 != null) {
                                            smaBaseReq3.setKey(BleKey.SLEEP_QUALITY);
                                        }
                                        SmaBaseReq smaBaseReq4 = (SmaBaseReq) objectRef.element;
                                        if (smaBaseReq4 != null) {
                                            smaBaseReq4.setKeyFlag(BleKeyFlag.UPDATE);
                                        }
                                    } else if (request instanceof SetScreenTimeOutRequest) {
                                        ?? smaScreenTimeOutReq = new SmaScreenTimeOutReq();
                                        objectRef.element = smaScreenTimeOutReq;
                                        smaScreenTimeOutReq.setReqId(request.getRequId());
                                        SmaBaseReq smaBaseReq5 = (SmaBaseReq) objectRef.element;
                                        if (smaBaseReq5 != null) {
                                            smaBaseReq5.setKey(BleKey.BACK_LIGHT);
                                        }
                                        SmaBaseReq smaBaseReq6 = (SmaBaseReq) objectRef.element;
                                        if (smaBaseReq6 != null) {
                                            smaBaseReq6.setKeyFlag(BleKeyFlag.UPDATE);
                                        }
                                        T t42 = objectRef.element;
                                        Intrinsics.checkNotNull(t42, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaScreenTimeOutReq");
                                        ((SmaScreenTimeOutReq) t42).setScreenTimeOut(((SetScreenTimeOutRequest) request).getScreenTimeOut());
                                    } else if (request instanceof CustomWatchFaceBackgroundChangeRequest) {
                                        ?? smaUploadWatchFaceReq2 = new SmaUploadWatchFaceReq(0, null, ((CustomWatchFaceBackgroundChangeRequest) request).getElementArrayList());
                                        objectRef.element = smaUploadWatchFaceReq2;
                                        Intrinsics.checkNotNull(smaUploadWatchFaceReq2, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq");
                                        smaUploadWatchFaceReq2.setReqId(request.getRequId());
                                        T t43 = objectRef.element;
                                        Intrinsics.checkNotNull(t43, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq");
                                        ((SmaUploadWatchFaceReq) t43).setKey(BleKey.WATCH_FACE);
                                        T t44 = objectRef.element;
                                        Intrinsics.checkNotNull(t44, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq");
                                        ((SmaUploadWatchFaceReq) t44).setKeyFlag(BleKeyFlag.UPDATE);
                                    }
                                    return (SmaBaseReq) objectRef.element;
                                }
                            }
                            return (SmaBaseReq) objectRef.element;
                        }
                    }
                }
            }
        }
        objectRef = objectRef2;
        return (SmaBaseReq) objectRef.element;
    }

    @NotNull
    public final Handler getSyncTimeOutHandler() {
        return this.e;
    }

    @NotNull
    public String getTAG() {
        return this.s;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return checkIfServiceIsRunning();
    }

    public boolean isHeartRateDataSyncRequired() {
        KhSmaDeviceInfo deviceInfo = KhSmaDeviceInfoRepository.Companion.getInstance(this.f3213a).getDeviceInfo(getMacAddress());
        if ((deviceInfo != null ? deviceInfo.getHrDataLastSyncTime() : null) != null) {
            Calendar calendar = Calendar.getInstance();
            Long hrDataLastSyncTime = deviceInfo.getHrDataLastSyncTime();
            Intrinsics.checkNotNull(hrDataLastSyncTime);
            calendar.setTimeInMillis(hrDataLastSyncTime.longValue());
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            Intrinsics.checkNotNullExpressionValue(calendar2, "calendar2");
            return SmaUtils.getMinutesDifference(calendar, calendar2) > 1;
        }
        return true;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    public boolean isStepsDataSyncRequired() {
        KhSmaDeviceInfo deviceInfo = KhSmaDeviceInfoRepository.Companion.getInstance(this.f3213a).getDeviceInfo(getMacAddress());
        if ((deviceInfo != null ? deviceInfo.getStepDataLastSyncTime() : null) != null) {
            Calendar calendar = Calendar.getInstance();
            Long stepDataLastSyncTime = deviceInfo.getStepDataLastSyncTime();
            Intrinsics.checkNotNull(stepDataLastSyncTime);
            calendar.setTimeInMillis(stepDataLastSyncTime.longValue());
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            Intrinsics.checkNotNullExpressionValue(calendar2, "calendar2");
            return SmaUtils.getMinutesDifference(calendar, calendar2) > 1;
        }
        return true;
    }

    @Subscribe
    public final void onConnectionStateChangedHandler(@Nullable CloveSmaBleState cloveSmaBleState) {
        ConnectionStatus connectionStatus;
        ConnectionStatus connectionStatus2 = ConnectionStatus.DISCONNECTED;
        if (cloveSmaBleState != null) {
            CloveSmaBleState.BleState bleState = cloveSmaBleState.getmState();
            int i = bleState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[bleState.ordinal()];
            if (i == 1) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (i == 2) {
                connectionStatus = ConnectionStatus.CONNECTING;
            } else if (i != 4) {
                connectionStatus = i != 5 ? connectionStatus2 : ConnectionStatus.DISCOVERING_SERVICES;
            } else {
                connectionStatus = ConnectionStatus.SCANNING;
            }
        } else {
            connectionStatus = ConnectionStatus.CONNECTING;
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.k;
        if (mutableLiveData != null) {
            Intrinsics.checkNotNull(mutableLiveData);
            mutableLiveData.setValue(connectionStatus);
            MutableLiveData<ConnectionStatus> mutableLiveData2 = this.k;
            Intrinsics.checkNotNull(mutableLiveData2);
            mutableLiveData2.postValue(connectionStatus);
        }
        if (connectionStatus == connectionStatus2) {
            sendErrorAndClearQueue(new SmaError(SmaErrorType.DEVICE_NOT_CONNECTED, "Device disconnected"));
        }
        ConnectionResultListener connectionResultListener = this.d;
        if (connectionResultListener != null) {
            Intrinsics.checkNotNull(connectionResultListener);
            connectionResultListener.onConnectionResponse(connectionStatus);
        }
        LogHelper.d("connectionstatus", "checking for ble on/off   " + connectionStatus);
    }

    @Override // com.coveiot.android.smasdk.SmaResponseListener
    public void onFailure(@NotNull SmaError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        SmaErrorType errorType = error.getErrorType();
        int i = errorType == null ? -1 : WhenMappings.$EnumSwitchMapping$3[errorType.ordinal()];
        if (i == 1 || i == 2) {
            sendErrorAndClearQueue(error);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:184:0x05f9 A[Catch: Exception -> 0x0a76, TryCatch #0 {Exception -> 0x0a76, blocks: (B:3:0x0009, B:5:0x0047, B:7:0x0051, B:9:0x0064, B:11:0x0071, B:15:0x009f, B:17:0x00a3, B:18:0x00a6, B:20:0x00ac, B:22:0x00b4, B:23:0x00ce, B:24:0x0104, B:25:0x0124, B:26:0x0130, B:28:0x0134, B:29:0x0137, B:30:0x0143, B:32:0x0147, B:33:0x014a, B:35:0x0152, B:37:0x0168, B:41:0x0177, B:43:0x0187, B:45:0x0194, B:47:0x019d, B:49:0x01aa, B:51:0x01b3, B:53:0x01c0, B:55:0x01c9, B:56:0x01d4, B:57:0x01d7, B:58:0x01e3, B:60:0x01e7, B:61:0x01ea, B:63:0x01f2, B:65:0x0208, B:69:0x0217, B:70:0x023d, B:71:0x0249, B:73:0x024d, B:74:0x0250, B:76:0x0258, B:80:0x0278, B:84:0x0297, B:85:0x02f2, B:87:0x02f6, B:88:0x02f9, B:90:0x0301, B:92:0x0309, B:93:0x031d, B:95:0x0323, B:96:0x034b, B:97:0x0357, B:99:0x035b, B:100:0x035e, B:102:0x0366, B:104:0x036e, B:105:0x0382, B:107:0x0388, B:111:0x03a3, B:115:0x03be, B:116:0x0409, B:117:0x0415, B:119:0x0422, B:120:0x0425, B:122:0x042d, B:125:0x0436, B:127:0x043e, B:128:0x045a, B:130:0x0462, B:131:0x047e, B:133:0x048d, B:135:0x049e, B:136:0x04c2, B:138:0x04c8, B:139:0x04cd, B:140:0x04ed, B:142:0x04f1, B:143:0x04f4, B:145:0x04fa, B:147:0x0502, B:148:0x051e, B:149:0x053e, B:151:0x0542, B:152:0x0545, B:154:0x054d, B:155:0x056d, B:157:0x0571, B:158:0x0574, B:160:0x057c, B:161:0x0595, B:163:0x0599, B:164:0x059c, B:166:0x05a4, B:167:0x05bd, B:169:0x05c1, B:170:0x05c6, B:172:0x05d3, B:173:0x05d6, B:175:0x05de, B:178:0x05ed, B:184:0x05f9, B:186:0x0616, B:188:0x0620, B:192:0x0630, B:193:0x064f, B:194:0x0654, B:196:0x0658, B:197:0x065b, B:199:0x0661, B:201:0x0669, B:202:0x0683, B:203:0x06b9, B:204:0x06d9, B:206:0x06dd, B:207:0x06e0, B:209:0x06e6, B:211:0x06ee, B:212:0x06fb, B:214:0x0701, B:216:0x0709, B:218:0x072d, B:219:0x0767, B:220:0x0787, B:222:0x078b, B:223:0x078e, B:225:0x0794, B:227:0x079c, B:228:0x07a9, B:230:0x07af, B:232:0x07b7, B:234:0x07bd, B:236:0x07c5, B:237:0x07c9, B:239:0x07eb, B:240:0x0825, B:242:0x082b, B:243:0x0830, B:244:0x0850, B:246:0x0854, B:247:0x0857, B:249:0x0877, B:250:0x087e, B:251:0x0911, B:253:0x0915, B:254:0x0918, B:255:0x0996, B:257:0x099a, B:258:0x099d, B:259:0x0a00, B:261:0x0a0c, B:263:0x0a1e, B:264:0x0a21, B:265:0x0a24, B:267:0x0a28, B:269:0x0a2c, B:270:0x0a2f, B:271:0x0a61, B:272:0x0a6b), top: B:277:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0616 A[Catch: Exception -> 0x0a76, TryCatch #0 {Exception -> 0x0a76, blocks: (B:3:0x0009, B:5:0x0047, B:7:0x0051, B:9:0x0064, B:11:0x0071, B:15:0x009f, B:17:0x00a3, B:18:0x00a6, B:20:0x00ac, B:22:0x00b4, B:23:0x00ce, B:24:0x0104, B:25:0x0124, B:26:0x0130, B:28:0x0134, B:29:0x0137, B:30:0x0143, B:32:0x0147, B:33:0x014a, B:35:0x0152, B:37:0x0168, B:41:0x0177, B:43:0x0187, B:45:0x0194, B:47:0x019d, B:49:0x01aa, B:51:0x01b3, B:53:0x01c0, B:55:0x01c9, B:56:0x01d4, B:57:0x01d7, B:58:0x01e3, B:60:0x01e7, B:61:0x01ea, B:63:0x01f2, B:65:0x0208, B:69:0x0217, B:70:0x023d, B:71:0x0249, B:73:0x024d, B:74:0x0250, B:76:0x0258, B:80:0x0278, B:84:0x0297, B:85:0x02f2, B:87:0x02f6, B:88:0x02f9, B:90:0x0301, B:92:0x0309, B:93:0x031d, B:95:0x0323, B:96:0x034b, B:97:0x0357, B:99:0x035b, B:100:0x035e, B:102:0x0366, B:104:0x036e, B:105:0x0382, B:107:0x0388, B:111:0x03a3, B:115:0x03be, B:116:0x0409, B:117:0x0415, B:119:0x0422, B:120:0x0425, B:122:0x042d, B:125:0x0436, B:127:0x043e, B:128:0x045a, B:130:0x0462, B:131:0x047e, B:133:0x048d, B:135:0x049e, B:136:0x04c2, B:138:0x04c8, B:139:0x04cd, B:140:0x04ed, B:142:0x04f1, B:143:0x04f4, B:145:0x04fa, B:147:0x0502, B:148:0x051e, B:149:0x053e, B:151:0x0542, B:152:0x0545, B:154:0x054d, B:155:0x056d, B:157:0x0571, B:158:0x0574, B:160:0x057c, B:161:0x0595, B:163:0x0599, B:164:0x059c, B:166:0x05a4, B:167:0x05bd, B:169:0x05c1, B:170:0x05c6, B:172:0x05d3, B:173:0x05d6, B:175:0x05de, B:178:0x05ed, B:184:0x05f9, B:186:0x0616, B:188:0x0620, B:192:0x0630, B:193:0x064f, B:194:0x0654, B:196:0x0658, B:197:0x065b, B:199:0x0661, B:201:0x0669, B:202:0x0683, B:203:0x06b9, B:204:0x06d9, B:206:0x06dd, B:207:0x06e0, B:209:0x06e6, B:211:0x06ee, B:212:0x06fb, B:214:0x0701, B:216:0x0709, B:218:0x072d, B:219:0x0767, B:220:0x0787, B:222:0x078b, B:223:0x078e, B:225:0x0794, B:227:0x079c, B:228:0x07a9, B:230:0x07af, B:232:0x07b7, B:234:0x07bd, B:236:0x07c5, B:237:0x07c9, B:239:0x07eb, B:240:0x0825, B:242:0x082b, B:243:0x0830, B:244:0x0850, B:246:0x0854, B:247:0x0857, B:249:0x0877, B:250:0x087e, B:251:0x0911, B:253:0x0915, B:254:0x0918, B:255:0x0996, B:257:0x099a, B:258:0x099d, B:259:0x0a00, B:261:0x0a0c, B:263:0x0a1e, B:264:0x0a21, B:265:0x0a24, B:267:0x0a28, B:269:0x0a2c, B:270:0x0a2f, B:271:0x0a61, B:272:0x0a6b), top: B:277:0x0009 }] */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, java.lang.Object, com.coveiot.android.bleabstract.request.BleBaseRequest] */
    /* JADX WARN: Type inference failed for: r3v57, types: [com.coveiot.android.bleabstract.models.DNDData, T] */
    /* JADX WARN: Type inference failed for: r3v62, types: [T, com.coveiot.android.bleabstract.response.GetLiftWristResponse] */
    @Override // com.coveiot.android.smasdk.SmaResponseListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull final com.coveiot.android.smasdk.api.SmaBaseRes r40) {
        /*
            Method dump skipped, instructions count: 2796
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl.onResponse(com.coveiot.android.smasdk.api.SmaBaseRes):void");
    }

    public void processNextCommand() {
        LinkedList<QueueObject> linkedList = this.q;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        SmaBaseReq smaBleReq = getSmaBleReq(this.q.get(0).getBaseRequest());
        if (smaBleReq != null) {
            BleBaseRequest bleBaseRequest = this.f;
            if (bleBaseRequest != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                if (!bleBaseRequest.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(smaBleReq);
            return;
        }
        sendCommandNotFoundError(this.q.get(0).getBaseRequest());
    }

    public final void processResponseEventReceived(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        ResponseType type = responseEvent.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$4[type.ordinal()];
        if (i == 1) {
            Object data = responseEvent.getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.FindMyPhoneRes");
            Intent intent = new Intent(com.coveiot.android.bleabstract.Constants.FIND_MY_PHONE_BROADCAST_INTENT);
            intent.putExtra(com.coveiot.android.bleabstract.Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, (FindMyPhoneRes) data);
            LocalBroadcastManager.getInstance(this.f3213a).sendBroadcast(intent);
        } else if (i != 2) {
        } else {
            Object data2 = responseEvent.getData();
            Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.CameraEventRes");
            Intent intent2 = new Intent(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT);
            intent2.putExtra(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT_EXTRA, (CameraEventRes) data2);
            LocalBroadcastManager.getInstance(this.f3213a).sendBroadcast(intent2);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.k == null) {
            this.k = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        Intrinsics.checkNotNull(connectionStatus);
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            Intrinsics.checkNotNull(smaBaseBleService);
            if (smaBaseBleService.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else {
                SmaBaseBleService smaBaseBleService2 = this.t;
                Intrinsics.checkNotNull(smaBaseBleService2);
                if (smaBaseBleService2.getConnectionState() == CloveSmaBleState.BleState.CONNECTING) {
                    connectionStatus = ConnectionStatus.CONNECTING;
                } else {
                    SmaBaseBleService smaBaseBleService3 = this.t;
                    Intrinsics.checkNotNull(smaBaseBleService3);
                    if (smaBaseBleService3.getConnectionState() == CloveSmaBleState.BleState.SCANNING) {
                        connectionStatus = ConnectionStatus.SCANNING;
                    } else {
                        SmaBaseBleService smaBaseBleService4 = this.t;
                        Intrinsics.checkNotNull(smaBaseBleService4);
                        if (smaBaseBleService4.getConnectionState() == CloveSmaBleState.BleState.DISCOVERING_SERVICES) {
                            connectionStatus = ConnectionStatus.DISCOVERING_SERVICES;
                        }
                    }
                }
            }
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.k;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.postValue(connectionStatus);
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.k;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
    }

    public void registerEvenBus() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SmaBaseBleApiImpl$registerEvenBus$1(this, null), 2, null);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveECGDataResponse> registerForLiveEcgData() {
        if (this.o == null) {
            this.o = new MutableLiveData<>();
        }
        MutableLiveData<LiveECGDataResponse> mutableLiveData = this.o;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        if (this.p == null) {
            this.p = new MutableLiveData<>();
        }
        MutableLiveData<LiveTemperatureData> mutableLiveData = this.p;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveAGPSUploadPercentage> registerLiveAGPSUploadData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return new MutableLiveData<>();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveHealthData> registerLiveHealthData() {
        if (this.l == null) {
            this.l = new MutableLiveData<>();
        }
        MutableLiveData<LiveHealthData> mutableLiveData = this.l;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<PPGData> registerLivePPGData() {
        if (this.n == null) {
            this.n = new MutableLiveData<>();
        }
        return this.n;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<Spo2Response> registerLiveSpo2Data() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<Spo2WaveResponse> registerLiveSpo2WaveData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public LiveData<LiveSportData> registerLiveSportsData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveStepsData> registerLiveStepsData() {
        if (this.m == null) {
            this.m = new MutableLiveData<>();
        }
        MutableLiveData<LiveStepsData> mutableLiveData = this.m;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
        r0.remove(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void removeFromQueue(@org.jetbrains.annotations.NotNull com.coveiot.android.smasdk.api.SmaBaseReq r7) {
        /*
            r6 = this;
            java.lang.String r0 = "khBaseReq"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$QueueObject> r0 = r6.q
            if (r0 == 0) goto L3d
            monitor-enter(r0)
            boolean r1 = r0.isEmpty()     // Catch: java.lang.Throwable -> L3a
            r2 = 1
            r1 = r1 ^ r2
            if (r1 == 0) goto L38
            r1 = 0
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L3a
        L17:
            if (r1 >= r3) goto L38
            java.lang.Object r4 = r0.get(r1)     // Catch: java.lang.Throwable -> L3a
            com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$QueueObject r4 = (com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl.QueueObject) r4     // Catch: java.lang.Throwable -> L3a
            com.coveiot.android.bleabstract.request.BleBaseRequest r4 = r4.getBaseRequest()     // Catch: java.lang.Throwable -> L3a
            java.lang.String r4 = r4.getRequId()     // Catch: java.lang.Throwable -> L3a
            java.lang.String r5 = r7.getReqId()     // Catch: java.lang.Throwable -> L3a
            boolean r4 = kotlin.text.m.equals(r4, r5, r2)     // Catch: java.lang.Throwable -> L3a
            if (r4 == 0) goto L35
            r0.remove(r1)     // Catch: java.lang.Throwable -> L3a
            goto L38
        L35:
            int r1 = r1 + 1
            goto L17
        L38:
            monitor-exit(r0)
            goto L3d
        L3a:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl.removeFromQueue(com.coveiot.android.smasdk.api.SmaBaseReq):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        clearParameters();
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            smaBaseBleService.restartService();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p7
            @Override // java.lang.Runnable
            public final void run() {
                SmaBaseBleApiImpl.c(SmaBaseBleApiImpl.this);
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(@NotNull ScanDeviceRequest scanDeviceReq, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(scanDeviceReq, "scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "listener");
        a(scanDeviceReq, listener);
    }

    public void sendCommandNotFoundError(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        BaseListener responseListener = baseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            String string = this.f3213a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f3213a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f3213a.getString(R.string.command_not_found)));
        }
    }

    public void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.q;
        if (linkedList == null || linkedList.size() <= 0 || this.f != null) {
            return;
        }
        processNextCommand();
    }

    public void sendErrorAndClearQueue(@NotNull SmaError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.q) {
            LinkedList<QueueObject> linkedList = this.q;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.q.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl.QueueObject>");
                    Iterator it = ((LinkedList) clone).iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = ((QueueObject) it.next()).getBaseRequest().getResponseListener();
                        String message = error.getMessage();
                        Intrinsics.checkNotNull(message);
                        BleBaseError bleBaseError = new BleBaseError(message);
                        if (error.getErrorType() == SmaErrorType.COMMAND_TIME_OUT) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
                        }
                        if (responseListener instanceof DataResultListener) {
                            ((DataResultListener) responseListener).onDataError(bleBaseError);
                        } else if (responseListener instanceof SettingsResultListener) {
                            ((SettingsResultListener) responseListener).onSettingsError(bleBaseError);
                        } else if (responseListener instanceof ConnectionResultListener) {
                            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, error.getMessage()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clearCommandQueue();
            }
        }
    }

    public void sendRequestToBleService(@NotNull SmaBaseReq smaBaseReq) {
        Intrinsics.checkNotNullParameter(smaBaseReq, "smaBaseReq");
        this.f = this.q.get(0).getBaseRequest();
        if (smaBaseReq.isMultiPacket()) {
            if (smaBaseReq instanceof SmaUploadWatchFaceReq) {
                this.e.postDelayed(this.x, this.g);
            } else {
                this.e.postDelayed(this.x, this.h);
            }
        } else {
            this.e.postDelayed(this.x, this.i);
        }
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            smaBaseBleService.sendRequest(smaBaseReq, this);
        }
    }

    public final void setBleService(@Nullable SmaBaseBleService smaBaseBleService) {
        this.t = smaBaseBleService;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public void setCompleteAndProcessNext() {
        SmaBaseReq baseReq;
        SmaBaseRes smaBaseRes = this.w;
        if (smaBaseRes != null && (baseReq = smaBaseRes.getBaseReq()) != null) {
            removeFromQueue(baseReq);
            String tag = getTAG();
            LogHelper.d(tag, "setCompleteAndProcessNext--> removed " + baseReq + ", " + baseReq.getKey());
        }
        if (this.f != null) {
            this.f = null;
        }
        processNextCommand();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
    }

    public final void setConnectionResultListener(@Nullable ConnectionResultListener connectionResultListener) {
        this.d = connectionResultListener;
    }

    public final void setConnectionStateLiveData(@Nullable MutableLiveData<ConnectionStatus> mutableLiveData) {
        this.k = mutableLiveData;
    }

    public final void setDeviceSupportedFeat(@Nullable DeviceSupportedFeatures deviceSupportedFeatures) {
        this.r = deviceSupportedFeatures;
    }

    public final void setKhCurrentCommand(@Nullable BleBaseRequest bleBaseRequest) {
        this.f = bleBaseRequest;
    }

    public final void setServiceConnection(@NotNull ServiceConnection serviceConnection) {
        Intrinsics.checkNotNullParameter(serviceConnection, "<set-?>");
        this.v = serviceConnection;
    }

    public final void setSmaBaseRes(@Nullable SmaBaseRes smaBaseRes) {
        this.w = smaBaseRes;
    }

    public void setTAG(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.s = str;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            Intrinsics.checkNotNull(smaBaseBleService);
            if (smaBaseBleService.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                if (a(request)) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
                String tag = getTAG();
                LogHelper.d(tag, "setUserSettings->Ignore {" + request + '}');
                return;
            }
        }
        String string = this.f3213a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public void startBleService() {
        try {
            Intent intent = new Intent(this.f3213a, SmaF2BleService.class);
            this.f3213a.bindService(intent, this.v, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f3213a.startForegroundService(intent);
            } else {
                this.f3213a.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f3213a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f3213a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManagerSma.getInstance(this.f3213a).saveConnectedDeviceMAcAddress("");
        PreferenceManagerSma.getInstance(this.f3213a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            Intrinsics.checkNotNull(smaBaseBleService);
            smaBaseBleService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        LogHelper.d(getTAG(), "stopServiceAndRetainMacAddress");
        clearCommandQueue();
        PreferenceManagerSma.getInstance(this.f3213a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        SmaBaseBleService smaBaseBleService = this.t;
        if (smaBaseBleService != null) {
            smaBaseBleService.stopServiceRetainMacAddress();
            clearParameters();
        }
    }

    public final void unbindService() {
        ServiceConnection serviceConnection = this.v;
        if (serviceConnection != null) {
            try {
                this.f3213a.unbindService(serviceConnection);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    public static final void b(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        T t = bleBaseRequest.element;
        Intrinsics.checkNotNull(t);
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t);
        bleBaseResponse.setResponseData(new TemperatureResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void c(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        T t = bleBaseRequest.element;
        Intrinsics.checkNotNull(t);
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t);
        bleBaseResponse.setResponseData(new ReadManualBpResponse(new ArrayList()));
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void d(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new SleepResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void e(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new StepsResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void f(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        T t = bleBaseRequest.element;
        Intrinsics.checkNotNull(t);
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t);
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(new ArrayList());
        readManualSpo2Response.setComplete(true);
        bleBaseResponse.setResponseData(readManualSpo2Response);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void b(Ref.ObjectRef bleBaseRequest, int i, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        GetCalorieDistanceGoalResponse getCalorieDistanceGoalResponse = new GetCalorieDistanceGoalResponse();
        getCalorieDistanceGoalResponse.setDistanceGoal(i);
        bleBaseResponse.setResponseData(getCalorieDistanceGoalResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void d(SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d("Command TimeOut", "Failed");
        BleBaseRequest bleBaseRequest = this$0.f;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            SmaBaseReq smaBleReq = this$0.getSmaBleReq(bleBaseRequest);
            if (smaBleReq != null) {
                this$0.onFailure(new SmaError(SmaErrorType.COMMAND_TIME_OUT, this$0.f3213a.getString(R.string.command_time_out)));
                LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + smaBleReq.getKey());
            } else {
                BleBaseRequest bleBaseRequest2 = this$0.f;
                Intrinsics.checkNotNull(bleBaseRequest2);
                this$0.sendCommandNotFoundError(bleBaseRequest2);
            }
        }
        this$0.f = null;
    }

    public static final void c(SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startBleService();
    }

    public static final void b(Ref.ObjectRef bleBaseRequest, ArrayList worldClockList, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(worldClockList, "$worldClockList");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new GetWorldClockDataResponse(worldClockList));
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(final SmaBaseBleApiImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f3213a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f3213a).scanAllDevices(this$0.f3213a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            SmaBaseBleApiImpl.access$scanResultRecieved(SmaBaseBleApiImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = SmaBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f3213a).scanDevicesWithFilter(this$0.f3213a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            SmaBaseBleApiImpl.access$scanResultRecieved(SmaBaseBleApiImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = SmaBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                            scanResultListener.onError(string);
                        }
                    });
                }
            } else {
                listener.onError("Device scan already started");
            }
        } catch (RuntimeException e) {
            String message = e.getMessage();
            Intrinsics.checkNotNull(message);
            String str = null;
            if (StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) "Enable Location permission for bluetooth scan", false, 2, (Object) null)) {
                str = e.getMessage();
            } else if (kotlin.text.m.equals(e.getMessage(), "Enable Bluetooth before starting scan", true)) {
                str = e.getMessage();
            }
            Intrinsics.checkNotNull(str);
            listener.onError(str);
            e.printStackTrace();
        }
    }

    public static final void b(Ref.ObjectRef bleBaseRequest, Ref.ObjectRef lwvData, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(lwvData, "$lwvData");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(lwvData.element);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void b(SmaBaseRes baseRes, Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(baseRes, "$baseRes");
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object obj = baseRes.getObj();
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.StreamProgressResponse");
        StreamProgressResponse streamProgressResponse = (StreamProgressResponse) obj;
        if (streamProgressResponse.getErrorCode() == 0) {
            if (streamProgressResponse.getTotal() == streamProgressResponse.getCompleted()) {
                BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
                bleBaseResponse.setResponseData(baseRes.getObj());
                Intrinsics.checkNotNull(dataResultListener);
                dataResultListener.onDataResponse(bleBaseResponse);
                Handler handler = this$0.e;
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
                this$0.setCompleteAndProcessNext();
                return;
            }
            T t = bleBaseRequest.element;
            Intrinsics.checkNotNull(t);
            ProgressData progressData = new ProgressData(ProgressType.DETERMINATE, (streamProgressResponse.getCompleted() * 100) / streamProgressResponse.getTotal(), (BleBaseRequest) t);
            Intrinsics.checkNotNull(dataResultListener);
            dataResultListener.onProgressUpdate(progressData);
            return;
        }
        Handler handler2 = this$0.e;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataError(new BleBaseError(String.valueOf(streamProgressResponse.getErrorCode())));
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(SmaBaseBleApiImpl this$0, ConnectRequest request, ConnectionResultListener listener) {
        BluetoothDevice mBluetoothDevice;
        BluetoothDevice mBluetoothDevice2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        SmaBaseBleService smaBaseBleService = this$0.t;
        if (smaBaseBleService == null) {
            if (smaBaseBleService == null) {
                this$0.startBleService();
                listener.onError(new Error(Type.SERVICE_NOT_RUNNING, this$0.f3213a.getString(R.string.service_not_running)));
                return;
            }
            listener.onError(new Error(Type.COMMAND_REQUEST_ERROR, this$0.f3213a.getString(R.string.request_should_not_null)));
            return;
        }
        CloveSmaBleState.BleState connectionState = smaBaseBleService.getConnectionState();
        CloveSmaBleState.BleState bleState = CloveSmaBleState.BleState.CONNECTED;
        if (connectionState == bleState && kotlin.text.m.equals(this$0.getMacAddress(), request.getMacAddress(), true)) {
            listener.onConnectionResponse(ConnectionStatus.CONNECTED);
            return;
        }
        SmaBaseBleService smaBaseBleService2 = this$0.t;
        r2 = null;
        String str = null;
        r2 = null;
        String str2 = null;
        if ((smaBaseBleService2 != null ? smaBaseBleService2.getConnectionState() : null) == CloveSmaBleState.BleState.DISCONNECTED) {
            SmaBaseBleService smaBaseBleService3 = this$0.t;
            if (smaBaseBleService3 != null) {
                smaBaseBleService3.connect(request.getMacAddress());
                return;
            }
            return;
        }
        SmaBaseBleService smaBaseBleService4 = this$0.t;
        if ((smaBaseBleService4 != null ? smaBaseBleService4.getConnectionState() : null) == bleState) {
            SmaBaseBleService smaBaseBleService5 = this$0.t;
            if ((smaBaseBleService5 != null ? smaBaseBleService5.getMBluetoothDevice() : null) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Connected to band ");
                SmaBaseBleService smaBaseBleService6 = this$0.t;
                if (smaBaseBleService6 != null && (mBluetoothDevice2 = smaBaseBleService6.getMBluetoothDevice()) != null) {
                    str = mBluetoothDevice2.getAddress();
                }
                sb.append(str);
                listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, sb.toString()));
                return;
            }
            SmaBaseBleService smaBaseBleService7 = this$0.t;
            CloveSmaBleState.BleState connectionState2 = smaBaseBleService7 != null ? smaBaseBleService7.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState2);
            listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, connectionState2.getStateAsString()));
            return;
        }
        SmaBaseBleService smaBaseBleService8 = this$0.t;
        if ((smaBaseBleService8 != null ? smaBaseBleService8.getConnectionState() : null) == CloveSmaBleState.BleState.CONNECTING) {
            SmaBaseBleService smaBaseBleService9 = this$0.t;
            if ((smaBaseBleService9 != null ? smaBaseBleService9.getMBluetoothDevice() : null) != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Connection in progress ");
                SmaBaseBleService smaBaseBleService10 = this$0.t;
                if (smaBaseBleService10 != null && (mBluetoothDevice = smaBaseBleService10.getMBluetoothDevice()) != null) {
                    str2 = mBluetoothDevice.getAddress();
                }
                sb2.append(str2);
                listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, sb2.toString()));
                return;
            }
            SmaBaseBleService smaBaseBleService11 = this$0.t;
            CloveSmaBleState.BleState connectionState3 = smaBaseBleService11 != null ? smaBaseBleService11.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState3);
            listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState3.getStateAsString()));
            return;
        }
        SmaBaseBleService smaBaseBleService12 = this$0.t;
        CloveSmaBleState.BleState connectionState4 = smaBaseBleService12 != null ? smaBaseBleService12.getConnectionState() : null;
        Intrinsics.checkNotNull(connectionState4);
        listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState4.getStateAsString()));
    }

    public static final void b(SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new SmaError(SmaErrorType.COMMAND_REQUEST_ERROR, this$0.f3213a.getString(R.string.command_req_error)));
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse hrResponse) {
        Intrinsics.checkNotNullParameter(hrResponse, "$hrResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(hrResponse);
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        T t = bleBaseRequest.element;
        Intrinsics.checkNotNull(t);
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t);
        bleBaseResponse.setResponseData(new HeartRateResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, int i, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        GetStepGoalResponse getStepGoalResponse = new GetStepGoalResponse();
        getStepGoalResponse.setGoal(i);
        bleBaseResponse.setResponseData(getStepGoalResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, Ref.IntRef goal, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(goal, "$goal");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        GetCalorieDistanceGoalResponse getCalorieDistanceGoalResponse = new GetCalorieDistanceGoalResponse();
        getCalorieDistanceGoalResponse.setCalorieGoal(goal.element / 1000);
        bleBaseResponse.setResponseData(getCalorieDistanceGoalResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, ArrayList alarmsList, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(alarmsList, "$alarmsList");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(alarmsList);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, SedentaryReminderData sedentaryReminderData, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(sedentaryReminderData, "$sedentaryReminderData");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(sedentaryReminderData);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, Ref.ObjectRef dndData, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(dndData, "$dndData");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(dndData.element);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(SmaBaseRes baseRes, Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(baseRes, "$baseRes");
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object obj = baseRes.getObj();
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.StreamProgressResponse");
        StreamProgressResponse streamProgressResponse = (StreamProgressResponse) obj;
        if (streamProgressResponse.getErrorCode() == 0) {
            if (streamProgressResponse.getTotal() == streamProgressResponse.getCompleted()) {
                BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
                bleBaseResponse.setResponseData(baseRes.getObj());
                Intrinsics.checkNotNull(dataResultListener);
                dataResultListener.onDataResponse(bleBaseResponse);
                this$0.setCompleteAndProcessNext();
                return;
            }
            T t = bleBaseRequest.element;
            Intrinsics.checkNotNull(t);
            ProgressData progressData = new ProgressData(ProgressType.DETERMINATE, (streamProgressResponse.getCompleted() * 100) / streamProgressResponse.getTotal(), (BleBaseRequest) t);
            Intrinsics.checkNotNull(dataResultListener);
            dataResultListener.onProgressUpdate(progressData);
            return;
        }
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataError(new BleBaseError(String.valueOf(streamProgressResponse.getErrorCode())));
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsResultListener settingsResultListener = (SettingsResultListener) ((BleBaseRequest) bleBaseRequest.element).getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse((BleBaseRequest) bleBaseRequest.element));
        this$0.setCompleteAndProcessNext();
    }

    public static final void a(SmaBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new SmaError(SmaErrorType.COMMAND_REQUEST_ERROR, this$0.f3213a.getString(R.string.command_req_error)));
    }

    public final boolean a(BleBaseRequest bleBaseRequest) {
        boolean z = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.f;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && bleBaseRequest != null && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).appNotificationType != null && ((SetMessageContentRequest) bleBaseRequest).appNotificationType == NotificationType.CALL) {
                    z = false;
                    LogHelper.d(getTAG(), "Ignore incoming call triggered during watch face upgrade");
                    return false;
                }
                return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }
}
