package com.coveiot.android.bleabstract.bleimpl;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.formatter.CRPHRFormatter;
import com.coveiot.android.bleabstract.formatter.CRPStepsFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.CloveCRPBleState;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.CustomWatchFaceLayoutResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveFirmwareUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceBackgroundUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.WatchFacePositionResponse;
import com.coveiot.android.bleabstract.services.CRPBaseBleService;
import com.coveiot.android.bleabstract.services.CRPY20BleService;
import com.coveiot.android.crpsdk.BleConnectHelper;
import com.coveiot.android.crpsdk.CRPBleRepeat;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPBatteryLevelReq;
import com.coveiot.android.crpsdk.api.CRPCancelFirmwareUpgradeReq;
import com.coveiot.android.crpsdk.api.CRPCancelWatchFaceBackgroundUploadReq;
import com.coveiot.android.crpsdk.api.CRPCancelWatchFaceUploadReq;
import com.coveiot.android.crpsdk.api.CRPChangeWatchFaceReq;
import com.coveiot.android.crpsdk.api.CRPExitRemoteCameraReq;
import com.coveiot.android.crpsdk.api.CRPFirmwareUpgradeReq;
import com.coveiot.android.crpsdk.api.CRPGetAlarmsReq;
import com.coveiot.android.crpsdk.api.CRPGetCustomWatchFaceLayoutReq;
import com.coveiot.android.crpsdk.api.CRPGetFirmwareVersionReq;
import com.coveiot.android.crpsdk.api.CRPGetPastHeartRateDataReq;
import com.coveiot.android.crpsdk.api.CRPGetPastSleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetPastStepCategoryDataReq;
import com.coveiot.android.crpsdk.api.CRPGetPastStepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetSPO2HistoryDataReq;
import com.coveiot.android.crpsdk.api.CRPGetSessionHRDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayHeartRateDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodaySPO2DataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodaySleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayStepCategoryDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayStepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetWatchFacePositionRequest;
import com.coveiot.android.crpsdk.api.CRPGetWorkoutDataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdaySPO2DataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdaySleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdayStepDataReq;
import com.coveiot.android.crpsdk.api.CRPPushNotificationReq;
import com.coveiot.android.crpsdk.api.CRPSendCustomWatchFaceLayoutChangeReq;
import com.coveiot.android.crpsdk.api.CRPSendWatchFaceBackgroundReq;
import com.coveiot.android.crpsdk.api.CRPSendWatchFaceReq;
import com.coveiot.android.crpsdk.api.CRPSetCRPPhysiologcalPeriodReq;
import com.coveiot.android.crpsdk.api.CRPSetDNDReq;
import com.coveiot.android.crpsdk.api.CRPSetDistanceUnitReq;
import com.coveiot.android.crpsdk.api.CRPSetFutureWeatherReq;
import com.coveiot.android.crpsdk.api.CRPSetHeartRateIntervalReq;
import com.coveiot.android.crpsdk.api.CRPSetHourSystemReq;
import com.coveiot.android.crpsdk.api.CRPSetLiveHeartRateReq;
import com.coveiot.android.crpsdk.api.CRPSetLiveStepReq;
import com.coveiot.android.crpsdk.api.CRPSetMusicPlaybackStateChangedReq;
import com.coveiot.android.crpsdk.api.CRPSetMusicVolumeReq;
import com.coveiot.android.crpsdk.api.CRPSetQuickViewReq;
import com.coveiot.android.crpsdk.api.CRPSetScreenTimeOutReq;
import com.coveiot.android.crpsdk.api.CRPSetSedentaryReminderTimeReq;
import com.coveiot.android.crpsdk.api.CRPSetSongTitleReq;
import com.coveiot.android.crpsdk.api.CRPSetStepGoalReq;
import com.coveiot.android.crpsdk.api.CRPSetTodayWeatherReq;
import com.coveiot.android.crpsdk.api.CRPSetUserProfileReq;
import com.coveiot.android.crpsdk.api.CRPSetVibrationAlarmReq;
import com.coveiot.android.crpsdk.api.CRPStopNotificationReq;
import com.coveiot.android.crpsdk.error.CRPError;
import com.coveiot.android.crpsdk.error.CRPErrorType;
import com.coveiot.android.crpsdk.events.CRPResponseEvent;
import com.coveiot.android.crpsdk.events.CRPResponseType;
import com.coveiot.android.crpsdk.model.KhCRPWorkoutInfo;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.CRPBleDevice;
import com.crrepa.ble.conn.bean.CRPAlarmClockInfo;
import com.crrepa.ble.conn.bean.CRPStepInfo;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class CRPBaseBleApiImpl implements BleApi, CRPResponseListener {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2920a;
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
    public final int j;
    @NotNull
    public final Handler k;
    @Nullable
    public MutableLiveData<ConnectionStatus> l;
    @Nullable
    public MutableLiveData<LiveHealthData> m;
    @Nullable
    public MutableLiveData<LiveStepsData> n;
    @NotNull
    public final LinkedList<QueueObject> o;
    @Nullable
    public DeviceSupportedFeatures p;
    public final int q;
    @NotNull
    public final String r;
    @Nullable
    public CRPBaseBleService s;
    @Nullable
    public DeviceInfoData t;
    public boolean u;
    @NotNull
    public ServiceConnection v;
    @NotNull
    public final Runnable w;

    /* loaded from: classes2.dex */
    public static final class Contact {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public String f2921a;
        @NotNull
        public String b;

        public Contact(@NotNull String name, @NotNull String phone) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(phone, "phone");
            this.f2921a = name;
            this.b = phone;
        }

        public static /* synthetic */ Contact copy$default(Contact contact, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = contact.f2921a;
            }
            if ((i & 2) != 0) {
                str2 = contact.b;
            }
            return contact.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.f2921a;
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
                return Intrinsics.areEqual(this.f2921a, contact.f2921a) && Intrinsics.areEqual(this.b, contact.b);
            }
            return false;
        }

        @NotNull
        public final String getName() {
            return this.f2921a;
        }

        @NotNull
        public final String getPhone() {
            return this.b;
        }

        public int hashCode() {
            return (this.f2921a.hashCode() * 31) + this.b.hashCode();
        }

        public final void setName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.f2921a = str;
        }

        public final void setPhone(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.b = str;
        }

        @NotNull
        public String toString() {
            return "Contact(name=" + this.f2921a + ", phone=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f2922a;

        public QueueObject(@NotNull BleBaseRequest baseRequest) {
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f2922a = baseRequest;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.f2922a;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.f2922a = bleBaseRequest;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            CloveCRPBleState.BleState.values();
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
                NotificationType notificationType3 = NotificationType.SKYPE;
                iArr[14] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                NotificationType notificationType4 = NotificationType.FACEBOOK;
                iArr[6] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                NotificationType notificationType5 = NotificationType.WHATSAPP;
                iArr[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                NotificationType notificationType6 = NotificationType.LINE;
                iArr[13] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                NotificationType notificationType7 = NotificationType.INSTAGRAM;
                iArr[7] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                NotificationType notificationType8 = NotificationType.TWITTER;
                iArr[8] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                NotificationType notificationType9 = NotificationType.QQ;
                iArr[10] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                NotificationType notificationType10 = NotificationType.WECHAT;
                iArr[5] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$1 = iArr;
            int[] iArr2 = new int[CRPErrorType.values().length];
            try {
                iArr2[CRPErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[CRPErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            $EnumSwitchMapping$2 = iArr2;
            int[] iArr3 = new int[CRPResponseType.values().length];
            try {
                iArr3[CRPResponseType.FIND_MY_PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr3[CRPResponseType.CAMERA_CLICK.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr3[CRPResponseType.GET_LIVE_STEPS.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr3[CRPResponseType.GET_LIVE_HEALTH_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
            $EnumSwitchMapping$3 = iArr3;
        }
    }

    public CRPBaseBleApiImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2920a = context;
        this.b = new Handler();
        this.c = new Handler();
        this.e = new Handler();
        this.g = 300000;
        this.h = 600000;
        this.i = 60000;
        this.j = 30000;
        this.k = new Handler(Looper.getMainLooper());
        this.o = new LinkedList<>();
        this.q = 225;
        String simpleName = CRPBaseBleApiImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "CRPBaseBleApiImpl::class.java.simpleName");
        this.r = simpleName;
        this.v = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                if (service instanceof CRPY20BleService.BtServiceBinder) {
                    CRPBaseBleApiImpl.this.setBleService(((CRPY20BleService.BtServiceBinder) service).getService());
                    CRPBaseBleApiImpl.this.setBindToServiceInProgress(false);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(@NotNull ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                CRPBaseBleApiImpl.this.setBleService(null);
                CRPBaseBleApiImpl.this.setBindToServiceInProgress(false);
            }
        };
        this.w = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.z
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleApiImpl.d(CRPBaseBleApiImpl.this);
            }
        };
    }

    public static final void b(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new StepsResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void c(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void d(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void e(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public static final void f(DataResultListener dataResultListener, BleBaseResponse hrResponse) {
        Intrinsics.checkNotNullParameter(hrResponse, "$hrResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(hrResponse);
    }

    public static final void g(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void h(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(new ArrayList());
        readManualSpo2Response.setComplete(true);
        bleBaseResponse.setResponseData(readManualSpo2Response);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void i(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new HeartRateResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public final int a(int i) {
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    switch (i) {
                        case 8:
                            return 4;
                        case 9:
                            return 1;
                        case 10:
                            return 6;
                        case 11:
                            return 7;
                        default:
                            return 5;
                    }
                }
                return 3;
            }
            return 2;
        }
        return 0;
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.b.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f2920a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = CRPBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        CRPBaseBleApiImpl.this.scanResultRecieved(new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.b.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.b0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleApiImpl.a(CRPBaseBleApiImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.o) {
            CRPBaseReq cRPBleReq = getCRPBleReq(baseRequest);
            if (cRPBleReq != null) {
                if (cRPBleReq.isPriority()) {
                    this.o.addFirst(new QueueObject(baseRequest));
                    String str = this.r;
                    StringBuilder sb = new StringBuilder();
                    sb.append("addToQueue-> added ");
                    BleCommand bleCommand = baseRequest.getBleCommand();
                    sb.append(bleCommand != null ? bleCommand.name() : null);
                    LogHelper.d(str, sb.toString());
                } else {
                    this.o.add(new QueueObject(baseRequest));
                    String str2 = this.r;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("addToQueue-> added ");
                    BleCommand bleCommand2 = baseRequest.getBleCommand();
                    sb2.append(bleCommand2 != null ? bleCommand2.name() : null);
                    LogHelper.d(str2, sb2.toString());
                }
            } else if (!(baseRequest instanceof StepsDataRequest) && !(baseRequest instanceof SleepDataRequest) && !(baseRequest instanceof HeartRateDataRequest) && !(baseRequest instanceof ActivityModeWithSamplesRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public void bindToBleService() {
        LogHelper.d(this.r, "bindToBleService");
        this.f2920a.bindService(new Intent(this.f2920a, CRPY20BleService.class), this.v, 1);
        setBindToServiceInProgress(true);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    public final boolean checkIfServiceIsRunning() {
        Object systemService = this.f2920a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (((this instanceof CRPY20BleApiImpl) && Intrinsics.areEqual(CRPY20BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f2920a.getPackageName(), runningServiceInfo.service.getPackageName())) || ((this instanceof CRPGPF5BleApiImpl) && Intrinsics.areEqual(CRPY20BleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f2920a.getPackageName(), runningServiceInfo.service.getPackageName()))) {
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
        synchronized (this.o) {
            LinkedList<QueueObject> linkedList = this.o;
            if (linkedList != null && linkedList.size() > 0) {
                this.o.clear();
            }
        }
        this.e.removeCallbacksAndMessages(null);
        this.f = null;
    }

    public final void clearParameters() {
        this.s = null;
        unbindService();
        this.c.removeCallbacksAndMessages(null);
        this.b.removeCallbacksAndMessages(null);
        this.e.removeCallbacksAndMessages(null);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull final ConnectRequest request, @NotNull final ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.removeCallbacksAndMessages(null);
        this.d = listener;
        this.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.a0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleApiImpl.a(CRPBaseBleApiImpl.this, request, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        LogHelper.d(this.r, "disconnect called");
        clearCommandQueue();
        this.d = listener;
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            cRPBaseBleService.disconnectAndForget();
            clearParameters();
            return;
        }
        listener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Nullable
    public final CRPBaseBleService getBleService() {
        return this.s;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.IDLE;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03f0  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.coveiot.android.crpsdk.api.CRPBaseReq getCRPBleReq(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.request.BleBaseRequest r15) {
        /*
            Method dump skipped, instructions count: 2598
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl.getCRPBleReq(com.coveiot.android.bleabstract.request.BleBaseRequest):com.coveiot.android.crpsdk.api.CRPBaseReq");
    }

    @NotNull
    public final Handler getConnectHandler() {
        return this.c;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (this.s != null) {
            ConnectionStatus connectionStatus = getConnectionStatus();
            CRPBaseBleService cRPBaseBleService = this.s;
            Intrinsics.checkNotNull(cRPBaseBleService);
            ConnectionError connectionError = cRPBaseBleService.getConnectionError();
            CRPBaseBleService cRPBaseBleService2 = this.s;
            Intrinsics.checkNotNull(cRPBaseBleService2);
            return new ConnectionInfo(connectionStatus, connectionError, cRPBaseBleService2.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Nullable
    public final ConnectionResultListener getConnectionResultListener() {
        return this.d;
    }

    @Nullable
    public final MutableLiveData<ConnectionStatus> getConnectionStateLiveData() {
        return this.l;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            if (cRPBaseBleService.getConnectionState() == CloveCRPBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            CRPBaseBleService cRPBaseBleService2 = this.s;
            return (cRPBaseBleService2 != null ? cRPBaseBleService2.getConnectionState() : null) == CloveCRPBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @NotNull
    public final Context getContext() {
        return this.f2920a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            Intrinsics.checkNotNull(cRPBaseBleService);
            if (cRPBaseBleService.getConnectionState() == CloveCRPBleState.BleState.CONNECTED) {
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                sendCommandRequest();
                return;
            }
        }
        String string = this.f2920a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Nullable
    public final DeviceSupportedFeatures getDeviceSupportedFeat() {
        return this.p;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.p = deviceSupportedFeatures;
        deviceSupportedFeatures.setStepsSupported(true);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.p;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.p;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.p;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setTemparatureHistorySupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.p;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setManualBpSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.p;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.p;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.p;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.p;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.p;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.p;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.p;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.p;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setPhoneFinderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.p;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setLiveStepsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.p;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.p;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.p;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.p;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.p;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.p;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.p;
        if (deviceSupportedFeatures21 != null) {
            deviceSupportedFeatures21.setTemperatureUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.p;
        if (deviceSupportedFeatures22 != null) {
            deviceSupportedFeatures22.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.p;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.p;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.p;
        if (deviceSupportedFeatures25 != null) {
            deviceSupportedFeatures25.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.p;
        if (deviceSupportedFeatures26 != null) {
            deviceSupportedFeatures26.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.p;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.p;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setSyncBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.p;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.p;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.p;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setAppSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.p;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.p;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setMaxCharSupportedInNotification(60);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.p;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setAutoTemperatureSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.p;
        if (deviceSupportedFeatures35 != null) {
            deviceSupportedFeatures35.setScheduleReminderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.p;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.p;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setMaxDaysOfStepsDataOnBand(3);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.p;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setMaxDaysOfHeartRateDataOnBand(2);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.p;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setMaxDaysOfSleepDataOnBand(3);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.p;
        if (deviceSupportedFeatures40 != null) {
            deviceSupportedFeatures40.setScheduledDndSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures41 = this.p;
        if (deviceSupportedFeatures41 != null) {
            deviceSupportedFeatures41.setManualSpo2SupportedOnBand(isManualSPO2SupportedOnBand());
        }
        DeviceSupportedFeatures deviceSupportedFeatures42 = this.p;
        if (deviceSupportedFeatures42 != null) {
            deviceSupportedFeatures42.setMaxDaysOfSpo2DataOnBand(2);
        }
        DeviceSupportedFeatures deviceSupportedFeatures43 = this.p;
        if (deviceSupportedFeatures43 != null) {
            deviceSupportedFeatures43.setMusicMetaDataChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures44 = this.p;
        if (deviceSupportedFeatures44 != null) {
            deviceSupportedFeatures44.setMusicPlaybackStateChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures45 = this.p;
        if (deviceSupportedFeatures45 != null) {
            deviceSupportedFeatures45.setMusicVolumeChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures46 = this.p;
        if (deviceSupportedFeatures46 != null) {
            deviceSupportedFeatures46.setMaxAlarmSupportedOnBand(3);
        }
        DeviceSupportedFeatures deviceSupportedFeatures47 = this.p;
        if (deviceSupportedFeatures47 != null) {
            deviceSupportedFeatures47.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures48 = this.p;
        if (deviceSupportedFeatures48 != null) {
            deviceSupportedFeatures48.setSedentaryReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures49 = this.p;
        Intrinsics.checkNotNull(deviceSupportedFeatures49);
        return deviceSupportedFeatures49;
    }

    @Nullable
    public BleBaseRequest getFromQueue(@NotNull CRPBaseReq khBaseReq) {
        Intrinsics.checkNotNullParameter(khBaseReq, "khBaseReq");
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            if (kotlin.text.m.equals(this.o.get(i).getBaseRequest().getRequId(), khBaseReq.getReqId(), true)) {
                return this.o.get(i).getBaseRequest();
            }
        }
        return null;
    }

    public final int getIntFwVersion(@NotNull String fwVStr) {
        Intrinsics.checkNotNullParameter(fwVStr, "fwVStr");
        return Integer.parseInt(kotlin.text.m.replace$default(StringsKt__StringsKt.trim(kotlin.text.m.replace$default(fwVStr, CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "", false, 4, (Object) null)).toString(), ".", "", false, 4, (Object) null));
    }

    @Nullable
    public final BleBaseRequest getKhCurrentCommand() {
        return this.f;
    }

    @NotNull
    public final Handler getMDataResponseHandler() {
        return this.k;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManagerCRP.getInstance(this.f2920a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        return connectedDeviceMacAddress;
    }

    @NotNull
    public final LinkedList<QueueObject> getQueue() {
        return this.o;
    }

    @NotNull
    public final Handler getScanHandler() {
        return this.b;
    }

    @NotNull
    public final ServiceConnection getServiceConnection() {
        return this.v;
    }

    @NotNull
    public final Handler getSyncTimeOutHandler() {
        return this.e;
    }

    @NotNull
    public final String getTAG() {
        return this.r;
    }

    public boolean isBindToServiceInProgress() {
        return this.u;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return checkIfServiceIsRunning();
    }

    public boolean isHeartRateDataSyncRequired() {
        return true;
    }

    public boolean isManualSPO2SupportedOnBand() {
        String connectedDeviceFwVersion = PreferenceManagerCRP.getInstance(this.f2920a).getConnectedDeviceFwVersion();
        if (connectedDeviceFwVersion != null) {
            if (connectedDeviceFwVersion.length() > 0) {
                try {
                    if (getIntFwVersion(connectedDeviceFwVersion) < 230) {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    public boolean isStepsDataSyncRequired() {
        return true;
    }

    @Subscribe
    public final void onConnectionStateChangedHandler(@Nullable CloveCRPBleState cloveCRPBleState) {
        ConnectionStatus connectionStatus;
        ConnectionStatus connectionStatus2 = ConnectionStatus.DISCONNECTED;
        if (cloveCRPBleState != null) {
            CloveCRPBleState.BleState bleState = cloveCRPBleState.getmState();
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
        MutableLiveData<ConnectionStatus> mutableLiveData = this.l;
        if (mutableLiveData != null) {
            Intrinsics.checkNotNull(mutableLiveData);
            mutableLiveData.setValue(connectionStatus);
            MutableLiveData<ConnectionStatus> mutableLiveData2 = this.l;
            Intrinsics.checkNotNull(mutableLiveData2);
            mutableLiveData2.postValue(connectionStatus);
        }
        if (connectionStatus == connectionStatus2) {
            sendErrorAndClearQueue(new CRPError(CRPErrorType.DEVICE_NOT_CONNECTED, "Device disconnected"));
        }
        ConnectionResultListener connectionResultListener = this.d;
        if (connectionResultListener != null) {
            Intrinsics.checkNotNull(connectionResultListener);
            connectionResultListener.onConnectionResponse(connectionStatus);
        }
    }

    @Override // com.coveiot.android.crpsdk.CRPResponseListener
    public void onFailure(@NotNull CRPError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        CRPErrorType errorType = error.getErrorType();
        int i = errorType == null ? -1 : WhenMappings.$EnumSwitchMapping$2[errorType.ordinal()];
        if (i == 1 || i == 2) {
            sendErrorAndClearQueue(error);
        }
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [T, com.coveiot.android.bleabstract.request.BleBaseRequest] */
    @Override // com.coveiot.android.crpsdk.CRPResponseListener
    public void onResponse(@NotNull final CRPBaseRes baseRes) {
        Intrinsics.checkNotNullParameter(baseRes, "baseRes");
        try {
            String str = this.r;
            LogHelper.d(str, "onResponse(baseRes: CRPBaseRes-> " + baseRes.getBaseReq());
            if (baseRes.getBaseReq() != null && baseRes.getBaseReq().getReqId() != null) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                ?? fromQueue = getFromQueue(baseRes.getBaseReq());
                objectRef.element = fromQueue;
                if (fromQueue != 0) {
                    boolean z = true;
                    if (fromQueue.getResponseListener() instanceof DataResultListener) {
                        final DataResultListener dataResultListener = (DataResultListener) ((BleBaseRequest) objectRef.element).getResponseListener();
                        CRPBaseReq baseReq = baseRes.getBaseReq();
                        if (baseReq instanceof CRPSetStepGoalReq) {
                            this.e.removeCallbacksAndMessages(null);
                            setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                            return;
                        } else if (baseReq instanceof CRPBatteryLevelReq) {
                            this.e.removeCallbacksAndMessages(null);
                            String str2 = this.r;
                            LogHelper.d(str2, "GetDeviceBatteryLevel == " + baseRes.getObj());
                            Object obj = baseRes.getObj();
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                            int intValue = ((Integer) obj).intValue();
                            final DataResultListener dataResultListener2 = (DataResultListener) ((BleBaseRequest) objectRef.element).getResponseListener();
                            BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse();
                            batteryLevelResponse.setBatteryLevel(Integer.valueOf(intValue));
                            batteryLevelResponse.setComplete(true);
                            final BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) objectRef.element);
                            bleBaseResponse.setResponseData(batteryLevelResponse);
                            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.f0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CRPBaseBleApiImpl.d(DataResultListener.this, bleBaseResponse);
                                }
                            });
                            setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                            return;
                        } else if (baseReq instanceof CRPGetFirmwareVersionReq) {
                            this.e.removeCallbacksAndMessages(null);
                            String str3 = this.r;
                            LogHelper.d(str3, "GetDeviceVersion == " + baseRes.getObj());
                            if (this.t == null) {
                                this.t = new DeviceInfoData();
                            }
                            Object obj2 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                            String str4 = (String) obj2;
                            DeviceInfoData deviceInfoData = this.t;
                            Intrinsics.checkNotNull(deviceInfoData);
                            deviceInfoData.setFwVersion(str4);
                            DeviceInfoData deviceInfoData2 = this.t;
                            Intrinsics.checkNotNull(deviceInfoData2);
                            deviceInfoData2.setMacAddress(getMacAddress());
                            PreferenceManagerCRP.getInstance(this.f2920a).saveConnectedDeviceFwVersion(str4);
                            DeviceInfoData deviceInfoData3 = this.t;
                            Intrinsics.checkNotNull(deviceInfoData3);
                            CRPBleDevice bleDevice = BleConnectHelper.getInstance(this.f2920a.getApplicationContext()).getBleDevice();
                            deviceInfoData3.setModelNo(bleDevice != null ? bleDevice.getName() : null);
                            String replace = new Regex(":").replace(getMacAddress(), "");
                            DeviceInfoData deviceInfoData4 = this.t;
                            Intrinsics.checkNotNull(deviceInfoData4);
                            deviceInfoData4.setSerialNo(replace);
                            DeviceInfoData deviceInfoData5 = this.t;
                            Intrinsics.checkNotNull(deviceInfoData5);
                            deviceInfoData5.setHwVersion("");
                            DeviceInfoData deviceInfoData6 = this.t;
                            Intrinsics.checkNotNull(deviceInfoData6);
                            CRPBleDevice bleDevice2 = BleConnectHelper.getInstance(this.f2920a.getApplicationContext()).getBleDevice();
                            deviceInfoData6.setDeviceName(bleDevice2 != null ? bleDevice2.getName() : null);
                            DeviceInfoData deviceInfoData7 = this.t;
                            Intrinsics.checkNotNull(deviceInfoData7);
                            deviceInfoData7.setManufacturerName("moyang");
                            DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
                            deviceInfoResponse.setComplete(true);
                            deviceInfoResponse.setDeviceInfo(this.t);
                            final BleBaseResponse bleBaseResponse2 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                            bleBaseResponse2.setResponseData(deviceInfoResponse);
                            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.h
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CRPBaseBleApiImpl.e(DataResultListener.this, bleBaseResponse2);
                                }
                            });
                            setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                            return;
                        } else {
                            if (baseReq instanceof CRPGetPastHeartRateDataReq ? true : baseReq instanceof CRPGetTodayHeartRateDataReq) {
                                this.e.removeCallbacksAndMessages(null);
                                if (baseRes.getObj() != null) {
                                    Object obj3 = baseRes.getObj();
                                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                    for (Object obj4 : (List) obj3) {
                                        if (obj4 instanceof HeartRateResponse) {
                                            final BleBaseResponse bleBaseResponse3 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                                            bleBaseResponse3.setResponseData(obj4);
                                            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.i
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    CRPBaseBleApiImpl.f(DataResultListener.this, bleBaseResponse3);
                                                }
                                            });
                                        }
                                    }
                                } else if (baseRes.getBaseReq() instanceof CRPGetPastHeartRateDataReq) {
                                    LogHelper.d(this.r, "onResponse  == yday HEART RATE not available");
                                } else {
                                    this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.u
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            CRPBaseBleApiImpl.i(Ref.ObjectRef.this, dataResultListener);
                                        }
                                    });
                                }
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            }
                            if (baseReq instanceof CRPGetTodaySleepDataReq ? true : baseReq instanceof CRPGetYesterdaySleepDataReq ? true : baseReq instanceof CRPGetPastSleepDataReq) {
                                this.e.removeCallbacksAndMessages(null);
                                if (baseRes.getObj() != null) {
                                    Object obj5 = baseRes.getObj();
                                    Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                    for (Object obj6 : (List) obj5) {
                                        if (obj6 instanceof SleepResponse) {
                                            final BleBaseResponse bleBaseResponse4 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                                            bleBaseResponse4.setResponseData(obj6);
                                            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    CRPBaseBleApiImpl.g(DataResultListener.this, bleBaseResponse4);
                                                }
                                            });
                                        }
                                    }
                                } else {
                                    if (!(baseRes.getBaseReq() instanceof CRPGetYesterdaySleepDataReq) && !(baseRes.getBaseReq() instanceof CRPGetPastSleepDataReq)) {
                                        this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.l
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                CRPBaseBleApiImpl.a(Ref.ObjectRef.this, dataResultListener);
                                            }
                                        });
                                    }
                                    LogHelper.d(this.r, "onResponse  == yday or day befor yday SLEEP not available");
                                }
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            }
                            if (baseReq instanceof CRPGetTodayStepDataReq ? true : baseReq instanceof CRPGetYesterdayStepDataReq ? true : baseReq instanceof CRPGetPastStepDataReq) {
                                this.e.removeCallbacksAndMessages(null);
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            }
                            if (baseReq instanceof CRPGetTodayStepCategoryDataReq ? true : baseReq instanceof CRPGetPastStepCategoryDataReq) {
                                this.e.removeCallbacksAndMessages(null);
                                if (baseRes.getObj() != null) {
                                    Object obj7 = baseRes.getObj();
                                    Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                    for (Object obj8 : (List) obj7) {
                                        if (obj8 instanceof StepsResponse) {
                                            final BleBaseResponse bleBaseResponse5 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                                            bleBaseResponse5.setResponseData(obj8);
                                            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.c0
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    CRPBaseBleApiImpl.a(DataResultListener.this, bleBaseResponse5);
                                                }
                                            });
                                        }
                                    }
                                } else if (baseRes.getBaseReq() instanceof CRPGetPastStepCategoryDataReq) {
                                    LogHelper.d(this.r, "onResponse  == yday or day befor yday STEP not available");
                                } else {
                                    this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.m
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            CRPBaseBleApiImpl.b(Ref.ObjectRef.this, dataResultListener);
                                        }
                                    });
                                }
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPGetSessionHRDataReq) {
                                this.e.removeCallbacksAndMessages(null);
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPGetWorkoutDataReq) {
                                this.e.removeCallbacksAndMessages(null);
                                if (baseRes.getObj() != null) {
                                    Object obj9 = baseRes.getObj();
                                    Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                    if (!((List) obj9).isEmpty()) {
                                        Object obj10 = baseRes.getObj();
                                        Intrinsics.checkNotNull(obj10, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                        for (Object obj11 : (List) obj10) {
                                            if (obj11 instanceof ActivityModeSummaryResponse) {
                                                final BleBaseResponse bleBaseResponse6 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                                                bleBaseResponse6.setResponseData(obj11);
                                                PreferenceManagerCRP preferenceManagerCRP = PreferenceManagerCRP.getInstance(this.f2920a);
                                                Object responseData = bleBaseResponse6.getResponseData();
                                                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse");
                                                Long startDateTime = ((ActivityModeSummaryResponse) responseData).getStartDateTime();
                                                Object responseData2 = bleBaseResponse6.getResponseData();
                                                Intrinsics.checkNotNull(responseData2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse");
                                                KhCRPWorkoutInfo khCRPWorkoutInfo = preferenceManagerCRP.getKhCRPWorkoutInfo(startDateTime, ((ActivityModeSummaryResponse) responseData2).getEndDateTime());
                                                if (khCRPWorkoutInfo != null) {
                                                    khCRPWorkoutInfo.setSyncStatus(1);
                                                    PreferenceManagerCRP.getInstance(this.f2920a).updateKhCRPWorkoutInfo(khCRPWorkoutInfo);
                                                }
                                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.d0
                                                    @Override // java.lang.Runnable
                                                    public final void run() {
                                                        CRPBaseBleApiImpl.b(DataResultListener.this, bleBaseResponse6);
                                                    }
                                                });
                                            }
                                        }
                                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                        return;
                                    }
                                }
                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.n
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        CRPBaseBleApiImpl.c(Ref.ObjectRef.this, dataResultListener);
                                    }
                                });
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPGetAlarmsReq) {
                                this.e.removeCallbacksAndMessages(null);
                                if (baseRes.getObj() != null) {
                                    if (baseRes.getObj() instanceof List) {
                                        final ArrayList arrayList = new ArrayList();
                                        Object obj12 = baseRes.getObj();
                                        Intrinsics.checkNotNull(obj12, "null cannot be cast to non-null type kotlin.collections.List<com.crrepa.ble.conn.bean.CRPAlarmClockInfo>");
                                        for (CRPAlarmClockInfo cRPAlarmClockInfo : (List) obj12) {
                                            Set indices$default = CRPBleRepeat.toIndices$default(CRPBleRepeat.INSTANCE, cRPAlarmClockInfo.getRepeatMode(), null, 2, null);
                                            arrayList.add(new Alarm(cRPAlarmClockInfo.isEnable(), cRPAlarmClockInfo.getId(), cRPAlarmClockInfo.getHour(), cRPAlarmClockInfo.getMinute(), 0, null, cRPAlarmClockInfo.getRepeatMode() != 0, indices$default.contains(0), indices$default.contains(1), indices$default.contains(2), indices$default.contains(3), indices$default.contains(4), indices$default.contains(5), indices$default.contains(6), 16, null));
                                        }
                                        this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.x
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                CRPBaseBleApiImpl.a(Ref.ObjectRef.this, arrayList, dataResultListener);
                                            }
                                        });
                                    }
                                } else {
                                    this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.o
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            CRPBaseBleApiImpl.d(Ref.ObjectRef.this, dataResultListener);
                                        }
                                    });
                                }
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPSendWatchFaceReq) {
                                if (baseRes.getObj() != null && (baseRes.getObj() instanceof LiveWatchFaceUploadPercentage)) {
                                    ProgressType progressType = ProgressType.DETERMINATE;
                                    Object obj13 = baseRes.getObj();
                                    Intrinsics.checkNotNull(obj13, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage");
                                    ProgressData progressData = new ProgressData(progressType, ((LiveWatchFaceUploadPercentage) obj13).getPercentage(), (BleBaseRequest) objectRef.element);
                                    Intrinsics.checkNotNull(dataResultListener);
                                    dataResultListener.onProgressUpdate(progressData);
                                    return;
                                }
                                this.e.removeCallbacksAndMessages(null);
                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        CRPBaseBleApiImpl.e(Ref.ObjectRef.this, dataResultListener);
                                    }
                                });
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPSendWatchFaceBackgroundReq) {
                                if (baseRes.getObj() != null && (baseRes.getObj() instanceof LiveWatchFaceBackgroundUploadPercentage)) {
                                    ProgressType progressType2 = ProgressType.DETERMINATE;
                                    Object obj14 = baseRes.getObj();
                                    Intrinsics.checkNotNull(obj14, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveWatchFaceBackgroundUploadPercentage");
                                    ProgressData progressData2 = new ProgressData(progressType2, ((LiveWatchFaceBackgroundUploadPercentage) obj14).getPercentage(), (BleBaseRequest) objectRef.element);
                                    Intrinsics.checkNotNull(dataResultListener);
                                    dataResultListener.onProgressUpdate(progressData2);
                                    return;
                                }
                                this.e.removeCallbacksAndMessages(null);
                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.q
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        CRPBaseBleApiImpl.f(Ref.ObjectRef.this, dataResultListener);
                                    }
                                });
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPFirmwareUpgradeReq) {
                                if (baseRes.getObj() != null && (baseRes.getObj() instanceof LiveFirmwareUploadPercentage)) {
                                    ProgressType progressType3 = ProgressType.DETERMINATE;
                                    Object obj15 = baseRes.getObj();
                                    Intrinsics.checkNotNull(obj15, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveFirmwareUploadPercentage");
                                    ProgressData progressData3 = new ProgressData(progressType3, ((LiveFirmwareUploadPercentage) obj15).getPercentage(), (BleBaseRequest) objectRef.element);
                                    Intrinsics.checkNotNull(dataResultListener);
                                    dataResultListener.onProgressUpdate(progressData3);
                                    return;
                                }
                                this.e.removeCallbacksAndMessages(null);
                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.s
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        CRPBaseBleApiImpl.g(Ref.ObjectRef.this, dataResultListener);
                                    }
                                });
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPGetCustomWatchFaceLayoutReq) {
                                this.e.removeCallbacksAndMessages(null);
                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.v
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        CRPBaseBleApiImpl.a(Ref.ObjectRef.this, baseRes, dataResultListener);
                                    }
                                });
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else if (baseReq instanceof CRPGetWatchFacePositionRequest) {
                                this.e.removeCallbacksAndMessages(null);
                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.w
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        CRPBaseBleApiImpl.b(Ref.ObjectRef.this, baseRes, dataResultListener);
                                    }
                                });
                                setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                return;
                            } else {
                                if (!(baseReq instanceof CRPGetTodaySPO2DataReq ? true : baseReq instanceof CRPGetYesterdaySPO2DataReq)) {
                                    z = baseReq instanceof CRPGetSPO2HistoryDataReq;
                                }
                                if (z) {
                                    this.e.removeCallbacksAndMessages(null);
                                    if (baseRes.getObj() != null) {
                                        Object obj16 = baseRes.getObj();
                                        Intrinsics.checkNotNull(obj16, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                        for (Object obj17 : (List) obj16) {
                                            if (obj17 instanceof ReadManualSpo2Response) {
                                                final BleBaseResponse bleBaseResponse7 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                                                bleBaseResponse7.setResponseData(obj17);
                                                this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.e0
                                                    @Override // java.lang.Runnable
                                                    public final void run() {
                                                        CRPBaseBleApiImpl.c(DataResultListener.this, bleBaseResponse7);
                                                    }
                                                });
                                            }
                                        }
                                    } else if (baseRes.getBaseReq() instanceof CRPGetYesterdaySPO2DataReq) {
                                        LogHelper.d(this.r, "onResponse  == yday or day befor yday SPO2 not available");
                                    } else {
                                        this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.t
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                CRPBaseBleApiImpl.h(Ref.ObjectRef.this, dataResultListener);
                                            }
                                        });
                                    }
                                    setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                                    return;
                                }
                                return;
                            }
                        }
                    } else if (((BleBaseRequest) objectRef.element).getResponseListener() instanceof SettingsResultListener) {
                        CRPBaseReq baseReq2 = baseRes.getBaseReq();
                        if (!(baseReq2 instanceof CRPSetUserProfileReq ? true : baseReq2 instanceof CRPSetStepGoalReq ? true : baseReq2 instanceof CRPSetHeartRateIntervalReq ? true : baseReq2 instanceof CRPSetHourSystemReq ? true : baseReq2 instanceof CRPSetVibrationAlarmReq ? true : baseReq2 instanceof CRPSetSedentaryReminderTimeReq ? true : baseReq2 instanceof CRPPushNotificationReq ? true : baseReq2 instanceof CRPSetQuickViewReq ? true : baseReq2 instanceof CRPSetTodayWeatherReq ? true : baseReq2 instanceof CRPSetDNDReq ? true : baseReq2 instanceof CRPExitRemoteCameraReq ? true : baseReq2 instanceof CRPStopNotificationReq ? true : baseReq2 instanceof CRPSetDistanceUnitReq ? true : baseReq2 instanceof CRPSetLiveStepReq ? true : baseReq2 instanceof CRPSetLiveHeartRateReq ? true : baseReq2 instanceof CRPSetCRPPhysiologcalPeriodReq ? true : baseReq2 instanceof CRPChangeWatchFaceReq ? true : baseReq2 instanceof CRPSendCustomWatchFaceLayoutChangeReq ? true : baseReq2 instanceof CRPCancelWatchFaceBackgroundUploadReq ? true : baseReq2 instanceof CRPCancelWatchFaceUploadReq ? true : baseReq2 instanceof CRPCancelFirmwareUpgradeReq ? true : baseReq2 instanceof CRPSetScreenTimeOutReq ? true : baseReq2 instanceof CRPSetSongTitleReq ? true : baseReq2 instanceof CRPSetMusicPlaybackStateChangedReq)) {
                            z = baseReq2 instanceof CRPSetMusicVolumeReq;
                        }
                        if (z) {
                            this.e.removeCallbacksAndMessages(null);
                            String str5 = this.r;
                            LogHelper.d(str5, "onResponse  ==" + baseRes.getBaseReq().getCommandType());
                            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.k
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CRPBaseBleApiImpl.a(Ref.ObjectRef.this, this);
                                }
                            });
                            return;
                        } else if (baseReq2 instanceof CRPSetFutureWeatherReq) {
                            setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                LogHelper.d(this.r, "Request Object is Null");
                return;
            }
            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.g
                @Override // java.lang.Runnable
                public final void run() {
                    CRPBaseBleApiImpl.a(CRPBaseBleApiImpl.this);
                }
            });
        } catch (Exception e) {
            this.k.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.r
                @Override // java.lang.Runnable
                public final void run() {
                    CRPBaseBleApiImpl.b(CRPBaseBleApiImpl.this);
                }
            });
            e.printStackTrace();
        }
    }

    public final void postLiveHeartRateData(int i) {
        LiveHealthData convertToLiveHeartRateData = CRPHRFormatter.Companion.getInstance(this.f2920a).convertToLiveHeartRateData(i);
        MutableLiveData<LiveHealthData> mutableLiveData = this.m;
        if (mutableLiveData != null) {
            Intrinsics.checkNotNull(mutableLiveData);
            mutableLiveData.setValue(convertToLiveHeartRateData);
            MutableLiveData<LiveHealthData> mutableLiveData2 = this.m;
            Intrinsics.checkNotNull(mutableLiveData2);
            mutableLiveData2.postValue(convertToLiveHeartRateData);
        }
    }

    public final void postLiveStepsData(@NotNull CRPStepInfo crpStepInfo) {
        Intrinsics.checkNotNullParameter(crpStepInfo, "crpStepInfo");
        LiveStepsData convertToLiveStepsData = CRPStepsFormatter.Companion.getInstance(this.f2920a).convertToLiveStepsData(crpStepInfo);
        MutableLiveData<LiveStepsData> mutableLiveData = this.n;
        if (mutableLiveData != null) {
            Intrinsics.checkNotNull(mutableLiveData);
            mutableLiveData.setValue(convertToLiveStepsData);
            MutableLiveData<LiveStepsData> mutableLiveData2 = this.n;
            Intrinsics.checkNotNull(mutableLiveData2);
            mutableLiveData2.postValue(convertToLiveStepsData);
        }
    }

    public void processNextCommand() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        CRPBaseReq cRPBleReq = getCRPBleReq(this.o.get(0).getBaseRequest());
        if (cRPBleReq != null) {
            BleBaseRequest bleBaseRequest = this.f;
            if (bleBaseRequest != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                if (!bleBaseRequest.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(cRPBleReq);
            return;
        }
        sendCommandNotFoundError(this.o.get(0).getBaseRequest());
    }

    public final void processResponseEventReceived(@NotNull CRPResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        CRPResponseType type = responseEvent.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$3[type.ordinal()];
        if (i == 1) {
            Object data = responseEvent.getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.FindMyPhoneRes");
            Intent intent = new Intent(com.coveiot.android.bleabstract.Constants.FIND_MY_PHONE_BROADCAST_INTENT);
            intent.putExtra(com.coveiot.android.bleabstract.Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, (FindMyPhoneRes) data);
            LocalBroadcastManager.getInstance(this.f2920a).sendBroadcast(intent);
        } else if (i == 2) {
            Object data2 = responseEvent.getData();
            Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.CameraEventRes");
            Intent intent2 = new Intent(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT);
            intent2.putExtra(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT_EXTRA, (CameraEventRes) data2);
            LocalBroadcastManager.getInstance(this.f2920a).sendBroadcast(intent2);
        } else if (i == 3) {
            Object data3 = responseEvent.getData();
            Intrinsics.checkNotNull(data3, "null cannot be cast to non-null type com.crrepa.ble.conn.bean.CRPStepInfo");
            postLiveStepsData((CRPStepInfo) data3);
        } else if (i != 4) {
        } else {
            Object data4 = responseEvent.getData();
            Intrinsics.checkNotNull(data4, "null cannot be cast to non-null type kotlin.Int");
            postLiveHeartRateData(((Integer) data4).intValue());
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.l == null) {
            this.l = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        Intrinsics.checkNotNull(connectionStatus);
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            Intrinsics.checkNotNull(cRPBaseBleService);
            if (cRPBaseBleService.getConnectionState() == CloveCRPBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else {
                CRPBaseBleService cRPBaseBleService2 = this.s;
                Intrinsics.checkNotNull(cRPBaseBleService2);
                if (cRPBaseBleService2.getConnectionState() == CloveCRPBleState.BleState.CONNECTING) {
                    connectionStatus = ConnectionStatus.CONNECTING;
                } else {
                    CRPBaseBleService cRPBaseBleService3 = this.s;
                    Intrinsics.checkNotNull(cRPBaseBleService3);
                    if (cRPBaseBleService3.getConnectionState() == CloveCRPBleState.BleState.SCANNING) {
                        connectionStatus = ConnectionStatus.SCANNING;
                    } else {
                        CRPBaseBleService cRPBaseBleService4 = this.s;
                        Intrinsics.checkNotNull(cRPBaseBleService4);
                        if (cRPBaseBleService4.getConnectionState() == CloveCRPBleState.BleState.DISCOVERING_SERVICES) {
                            connectionStatus = ConnectionStatus.DISCOVERING_SERVICES;
                        }
                    }
                }
            }
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.l;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.postValue(connectionStatus);
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.l;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
    }

    public void registerEvenBus() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new CRPBaseBleApiImpl$registerEvenBus$1(this, null), 2, null);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveECGDataResponse> registerForLiveEcgData() {
        return new MutableLiveData();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        return new MutableLiveData();
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
        if (this.m == null) {
            this.m = new MutableLiveData<>();
        }
        MutableLiveData<LiveHealthData> mutableLiveData = this.m;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<PPGData> registerLivePPGData() {
        return null;
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
        if (this.n == null) {
            this.n = new MutableLiveData<>();
        }
        MutableLiveData<LiveStepsData> mutableLiveData = this.n;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
        r6.o.remove(r2).getBaseRequest();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void removeFromQueue(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.request.BleBaseRequest r7) {
        /*
            r6 = this;
            java.lang.String r0 = "bleBaseRequest"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$QueueObject> r0 = r6.o
            monitor-enter(r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$QueueObject> r1 = r6.o     // Catch: java.lang.Throwable -> L3d
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3d
            r2 = 0
        Lf:
            if (r2 >= r1) goto L3b
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$QueueObject> r3 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$QueueObject r3 = (com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl.QueueObject) r3     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r3 = r3.getRequId()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r7.getRequId()     // Catch: java.lang.Throwable -> L3d
            r5 = 1
            boolean r3 = kotlin.text.m.equals(r3, r4, r5)     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L38
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$QueueObject> r7 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r7 = r7.remove(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$QueueObject r7 = (com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl.QueueObject) r7     // Catch: java.lang.Throwable -> L3d
            r7.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            goto L3b
        L38:
            int r2 = r2 + 1
            goto Lf
        L3b:
            monitor-exit(r0)
            return
        L3d:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl.removeFromQueue(com.coveiot.android.bleabstract.request.BleBaseRequest):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        LogHelper.d(this.r, "restartService called");
        clearCommandQueue();
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            cRPBaseBleService.restartService();
            clearParameters();
            new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.y
                @Override // java.lang.Runnable
                public final void run() {
                    CRPBaseBleApiImpl.c(CRPBaseBleApiImpl.this);
                }
            }, 5000L);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(@NotNull ScanDeviceRequest scanDeviceReq, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(scanDeviceReq, "scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "listener");
        a(scanDeviceReq, listener);
    }

    public final void scanResultRecieved(@NotNull List<? extends BleDevice> devices, boolean z, @NotNull ScanDeviceRequest scanDeviceReq, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(devices, "devices");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "listener");
        ScanDeviceResponse scanDeviceResponse = new ScanDeviceResponse(scanDeviceReq);
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : devices) {
            arrayList.add(new com.coveiot.android.bleabstract.models.BleDevice(bleDevice.getmDevice(), bleDevice.getRssi()));
        }
        scanDeviceResponse.setBluetoothDevices(arrayList);
        scanDeviceResponse.setScanComplete(z);
        if (!scanDeviceReq.isShouldProvideBatchResult()) {
            listener.onResponse(scanDeviceResponse);
        } else if (z) {
            listener.onResponse(scanDeviceResponse);
        }
    }

    public void sendCommandNotFoundError(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        BaseListener responseListener = baseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            String string = this.f2920a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f2920a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f2920a.getString(R.string.command_not_found)));
        }
    }

    public void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0 || this.f != null) {
            return;
        }
        processNextCommand();
    }

    public void sendErrorAndClearQueue(@NotNull CRPError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.o) {
            LinkedList<QueueObject> linkedList = this.o;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.o.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl.QueueObject>");
                    Iterator it = ((LinkedList) clone).iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = ((QueueObject) it.next()).getBaseRequest().getResponseListener();
                        String message = error.getMessage();
                        Intrinsics.checkNotNull(message);
                        BleBaseError bleBaseError = new BleBaseError(message);
                        if (error.getErrorType() == CRPErrorType.COMMAND_TIME_OUT) {
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

    public void sendRequestToBleService(@NotNull CRPBaseReq cRPBaseReq) {
        Intrinsics.checkNotNullParameter(cRPBaseReq, "cRPBaseReq");
        this.f = this.o.get(0).getBaseRequest();
        if (cRPBaseReq.isMultiPacket()) {
            if (cRPBaseReq instanceof CRPSendWatchFaceReq) {
                this.e.postDelayed(this.w, this.g);
            } else if (cRPBaseReq instanceof CRPFirmwareUpgradeReq) {
                this.e.postDelayed(this.w, this.h);
            } else if (cRPBaseReq instanceof CRPSendWatchFaceBackgroundReq) {
                this.e.postDelayed(this.w, this.g);
            } else {
                this.e.postDelayed(this.w, this.i);
            }
        } else {
            this.e.postDelayed(this.w, this.j);
        }
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            cRPBaseBleService.sendRequest(cRPBaseReq, this);
        }
    }

    public void setBindToServiceInProgress(boolean z) {
        this.u = z;
    }

    public final void setBleService(@Nullable CRPBaseBleService cRPBaseBleService) {
        this.s = cRPBaseBleService;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public void setCompleteAndProcessNext(@Nullable BleBaseRequest bleBaseRequest) {
        if (bleBaseRequest != null) {
            removeFromQueue(bleBaseRequest);
            String str = this.r;
            LogHelper.d(str, "setCompleteAndProcessNext--> removed " + bleBaseRequest);
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
        this.l = mutableLiveData;
    }

    public final void setDeviceSupportedFeat(@Nullable DeviceSupportedFeatures deviceSupportedFeatures) {
        this.p = deviceSupportedFeatures;
    }

    public final void setKhCurrentCommand(@Nullable BleBaseRequest bleBaseRequest) {
        this.f = bleBaseRequest;
    }

    public final void setServiceConnection(@NotNull ServiceConnection serviceConnection) {
        Intrinsics.checkNotNullParameter(serviceConnection, "<set-?>");
        this.v = serviceConnection;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            Intrinsics.checkNotNull(cRPBaseBleService);
            if (cRPBaseBleService.getConnectionState() == CloveCRPBleState.BleState.CONNECTED) {
                if (a(request)) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
                String str = this.r;
                LogHelper.d(str, "setUserSettings->Ignore {" + request + '}');
                return;
            }
        }
        String string = this.f2920a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public void startBleService() {
        LogHelper.d(this.r, "startBleService");
        try {
            Intent intent = new Intent(this.f2920a, CRPY20BleService.class);
            this.f2920a.bindService(intent, this.v, 1);
            setBindToServiceInProgress(true);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f2920a.startForegroundService(intent);
            } else {
                this.f2920a.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f2920a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f2920a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManagerCRP.getInstance(this.f2920a).saveConnectedDeviceMAcAddress("");
        PreferenceManagerCRP.getInstance(this.f2920a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            Intrinsics.checkNotNull(cRPBaseBleService);
            cRPBaseBleService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        LogHelper.d(this.r, "stopServiceAndRetainMacAddress");
        clearCommandQueue();
        PreferenceManagerCRP.getInstance(this.f2920a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        CRPBaseBleService cRPBaseBleService = this.s;
        if (cRPBaseBleService != null) {
            cRPBaseBleService.stopServiceRetainMacAddress();
            clearParameters();
        }
    }

    public final void unbindService() {
        ServiceConnection serviceConnection = this.v;
        if (serviceConnection != null) {
            try {
                this.f2920a.unbindService(serviceConnection);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    public static final void d(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new ArrayList());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void e(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void f(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void g(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void b(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void c(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void b(Ref.ObjectRef bleBaseRequest, CRPBaseRes baseRes, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(baseRes, "$baseRes");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        if (baseRes.getObj() != null && (baseRes.getObj() instanceof WatchFacePositionResponse)) {
            Object obj = baseRes.getObj();
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.WatchFacePositionResponse");
            bleBaseResponse.setResponseData((WatchFacePositionResponse) obj);
        }
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void c(CRPBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startBleService();
    }

    public static final void d(CRPBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.r, "Command TimeOut,Failed");
        BleBaseRequest bleBaseRequest = this$0.f;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            CRPBaseReq cRPBleReq = this$0.getCRPBleReq(bleBaseRequest);
            if (cRPBleReq != null) {
                this$0.onFailure(new CRPError(CRPErrorType.COMMAND_TIME_OUT, this$0.f2920a.getString(R.string.command_time_out)));
                LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + cRPBleReq);
            } else {
                BleBaseRequest bleBaseRequest2 = this$0.f;
                Intrinsics.checkNotNull(bleBaseRequest2);
                this$0.sendCommandNotFoundError(bleBaseRequest2);
            }
        }
        this$0.f = null;
    }

    public static final void b(CRPBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new CRPError(CRPErrorType.COMMAND_REQUEST_ERROR, this$0.f2920a.getString(R.string.command_req_error)));
    }

    public static final void a(final CRPBaseBleApiImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f2920a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f2920a).scanAllDevices(this$0.f2920a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            CRPBaseBleApiImpl.this.scanResultRecieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = CRPBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f2920a).scanDevicesWithFilter(this$0.f2920a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            CRPBaseBleApiImpl.this.scanResultRecieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = CRPBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
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

    public static final void a(CRPBaseBleApiImpl this$0, ConnectRequest request, ConnectionResultListener listener) {
        BluetoothDevice mBluetoothDevice;
        BluetoothDevice mBluetoothDevice2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        CRPBaseBleService cRPBaseBleService = this$0.s;
        if (cRPBaseBleService == null) {
            if (cRPBaseBleService == null) {
                this$0.startBleService();
                listener.onError(new Error(Type.SERVICE_NOT_RUNNING, this$0.f2920a.getString(R.string.service_not_running)));
                return;
            }
            listener.onError(new Error(Type.COMMAND_REQUEST_ERROR, this$0.f2920a.getString(R.string.request_should_not_null)));
            return;
        }
        CloveCRPBleState.BleState connectionState = cRPBaseBleService.getConnectionState();
        CloveCRPBleState.BleState bleState = CloveCRPBleState.BleState.CONNECTED;
        if (connectionState == bleState && kotlin.text.m.equals(this$0.getMacAddress(), request.getMacAddress(), true)) {
            listener.onConnectionResponse(ConnectionStatus.CONNECTED);
            return;
        }
        CRPBaseBleService cRPBaseBleService2 = this$0.s;
        r2 = null;
        String str = null;
        r2 = null;
        String str2 = null;
        if ((cRPBaseBleService2 != null ? cRPBaseBleService2.getConnectionState() : null) == CloveCRPBleState.BleState.DISCONNECTED) {
            CRPBaseBleService cRPBaseBleService3 = this$0.s;
            if (cRPBaseBleService3 != null) {
                cRPBaseBleService3.connect(request.getMacAddress());
                return;
            }
            return;
        }
        CRPBaseBleService cRPBaseBleService4 = this$0.s;
        if ((cRPBaseBleService4 != null ? cRPBaseBleService4.getConnectionState() : null) == bleState) {
            CRPBaseBleService cRPBaseBleService5 = this$0.s;
            if ((cRPBaseBleService5 != null ? cRPBaseBleService5.getMBluetoothDevice() : null) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Connected to band ");
                CRPBaseBleService cRPBaseBleService6 = this$0.s;
                if (cRPBaseBleService6 != null && (mBluetoothDevice2 = cRPBaseBleService6.getMBluetoothDevice()) != null) {
                    str = mBluetoothDevice2.getAddress();
                }
                sb.append(str);
                listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, sb.toString()));
                return;
            }
            CRPBaseBleService cRPBaseBleService7 = this$0.s;
            CloveCRPBleState.BleState connectionState2 = cRPBaseBleService7 != null ? cRPBaseBleService7.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState2);
            listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, connectionState2.getStateAsString()));
            return;
        }
        CRPBaseBleService cRPBaseBleService8 = this$0.s;
        if ((cRPBaseBleService8 != null ? cRPBaseBleService8.getConnectionState() : null) == CloveCRPBleState.BleState.CONNECTING) {
            CRPBaseBleService cRPBaseBleService9 = this$0.s;
            if ((cRPBaseBleService9 != null ? cRPBaseBleService9.getMBluetoothDevice() : null) != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Connection in progress ");
                CRPBaseBleService cRPBaseBleService10 = this$0.s;
                if (cRPBaseBleService10 != null && (mBluetoothDevice = cRPBaseBleService10.getMBluetoothDevice()) != null) {
                    str2 = mBluetoothDevice.getAddress();
                }
                sb2.append(str2);
                listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, sb2.toString()));
                return;
            }
            CRPBaseBleService cRPBaseBleService11 = this$0.s;
            CloveCRPBleState.BleState connectionState3 = cRPBaseBleService11 != null ? cRPBaseBleService11.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState3);
            listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState3.getStateAsString()));
            return;
        }
        CRPBaseBleService cRPBaseBleService12 = this$0.s;
        CloveCRPBleState.BleState connectionState4 = cRPBaseBleService12 != null ? cRPBaseBleService12.getConnectionState() : null;
        Intrinsics.checkNotNull(connectionState4);
        listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState4.getStateAsString()));
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new SleepResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, ArrayList alarmsList, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(alarmsList, "$alarmsList");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(alarmsList);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, CRPBaseRes baseRes, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(baseRes, "$baseRes");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        if (baseRes.getObj() != null) {
            Object obj = baseRes.getObj();
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.CustomWatchFaceLayoutResponse");
            bleBaseResponse.setResponseData((CustomWatchFaceLayoutResponse) obj);
        }
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, CRPBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsResultListener settingsResultListener = (SettingsResultListener) ((BleBaseRequest) bleBaseRequest.element).getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse((BleBaseRequest) bleBaseRequest.element));
        this$0.setCompleteAndProcessNext((BleBaseRequest) bleBaseRequest.element);
    }

    public static final void a(CRPBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new CRPError(CRPErrorType.COMMAND_REQUEST_ERROR, this$0.f2920a.getString(R.string.command_req_error)));
    }

    public final boolean a(BleBaseRequest bleBaseRequest) {
        boolean z = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.f;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && bleBaseRequest != null && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).appNotificationType != null && ((SetMessageContentRequest) bleBaseRequest).appNotificationType == NotificationType.CALL) {
                    z = false;
                    LogHelper.d(this.r, "Ignore incoming call triggered during watch face upgrade");
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
