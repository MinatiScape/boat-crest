package com.coveiot.android.bleabstract.bleimpl;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.CloveMatrixBleState;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ChangeWatchFaceRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.EnterRemoteCameraRequest;
import com.coveiot.android.bleabstract.request.ExitRemoteCameraRequest;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.GetAlarmDataRequest;
import com.coveiot.android.bleabstract.request.GetDNDModeSettingsRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ReminderType;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetSocialOrUpiQrCodeRequest;
import com.coveiot.android.bleabstract.request.SetSportsPushRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmListRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SetWomenWellnessSettingsRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.StopMessageNotificationRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.services.MatrixBleService;
import com.coveiot.android.bleabstract.utils.matrixUtils.MatrixUtils;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.matrixsdk.MatrixResponseListener;
import com.coveiot.android.matrixsdk.api.MatrixBaseReq;
import com.coveiot.android.matrixsdk.api.MatrixBaseRes;
import com.coveiot.android.matrixsdk.api.MatrixBatteryLevelReq;
import com.coveiot.android.matrixsdk.api.MatrixCameraRequest;
import com.coveiot.android.matrixsdk.api.MatrixCloudWatchFaceReq;
import com.coveiot.android.matrixsdk.api.MatrixDIYComponentReq;
import com.coveiot.android.matrixsdk.api.MatrixDIYWatchFaceReq;
import com.coveiot.android.matrixsdk.api.MatrixDNDReq;
import com.coveiot.android.matrixsdk.api.MatrixDeviceInfoReq;
import com.coveiot.android.matrixsdk.api.MatrixDrinkReminderReq;
import com.coveiot.android.matrixsdk.api.MatrixFindBandReq;
import com.coveiot.android.matrixsdk.api.MatrixGetAlarmListData;
import com.coveiot.android.matrixsdk.api.MatrixGetDNDConfig;
import com.coveiot.android.matrixsdk.api.MatrixHealthReminderReq;
import com.coveiot.android.matrixsdk.api.MatrixHeartRateReq;
import com.coveiot.android.matrixsdk.api.MatrixHourFormatReq;
import com.coveiot.android.matrixsdk.api.MatrixLengthUnitReq;
import com.coveiot.android.matrixsdk.api.MatrixLiftWristReq;
import com.coveiot.android.matrixsdk.api.MatrixNotificationConfigReq;
import com.coveiot.android.matrixsdk.api.MatrixNotificationReq;
import com.coveiot.android.matrixsdk.api.MatrixSPO2Request;
import com.coveiot.android.matrixsdk.api.MatrixSedentaryReminderReq;
import com.coveiot.android.matrixsdk.api.MatrixSetQRCodeReq;
import com.coveiot.android.matrixsdk.api.MatrixSetSportGoalReq;
import com.coveiot.android.matrixsdk.api.MatrixSleepReq;
import com.coveiot.android.matrixsdk.api.MatrixSportsDataReq;
import com.coveiot.android.matrixsdk.api.MatrixSportsPushReq;
import com.coveiot.android.matrixsdk.api.MatrixStepsReq;
import com.coveiot.android.matrixsdk.api.MatrixUserInfoReq;
import com.coveiot.android.matrixsdk.api.MatrixVibrationAlarmReq;
import com.coveiot.android.matrixsdk.api.MatrixWomenWellnessReq;
import com.coveiot.android.matrixsdk.error.MatrixError;
import com.coveiot.android.matrixsdk.error.MatrixErrorType;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.bean.WristbandAlarm;
import com.htsmart.wristband2.bean.WristbandConfig;
import com.htsmart.wristband2.bean.WristbandNotification;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.htsmart.wristband2.bean.config.DrinkWaterConfig;
import com.htsmart.wristband2.bean.config.HealthyConfig;
import com.htsmart.wristband2.bean.config.NotDisturbConfig;
import com.htsmart.wristband2.bean.config.NotificationConfig;
import com.htsmart.wristband2.bean.config.SedentaryConfig;
import com.htsmart.wristband2.bean.config.TurnWristLightingConfig;
import com.htsmart.wristband2.bean.config.WomenHealthyConfig;
import com.htsmart.wristband2.dial.DialDrawer;
import com.squareup.otto.Subscribe;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class MatrixBleImpl implements BleApi, MatrixResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3182a;
    @NotNull
    public final String b;
    @Nullable
    public MutableLiveData<ConnectionStatus> c;
    @Nullable
    public ConnectionResultListener d;
    @NotNull
    public final Handler e;
    @NotNull
    public final Handler f;
    @Nullable
    public MatrixBleService g;
    @NotNull
    public final Handler h;
    @Nullable
    public BleBaseRequest i;
    public final int j;
    public final int k;
    public final int l;
    @Nullable
    public WristbandManager m;
    @Nullable
    public ServiceConnection n;
    @NotNull
    public final LinkedList<QueueObject> o;
    @NotNull
    public final Handler p;
    @Nullable
    public DeviceSupportedFeatures q;
    @NotNull
    public final Runnable r;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixBleImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixBleImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3183a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixBleImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixBleImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixBleImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3183a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3184a;

        public QueueObject(@NotNull BleBaseRequest baseRequest) {
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f3184a = baseRequest;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.f3184a;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.f3184a = bleBaseRequest;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            CloveMatrixBleState.BleState.values();
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
            try {
                NotificationType notificationType11 = NotificationType.EMAIL;
                iArr[3] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                NotificationType notificationType12 = NotificationType.OTHER_APPS;
                iArr[18] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                NotificationType notificationType13 = NotificationType.SNAPCHAT;
                iArr[12] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                NotificationType notificationType14 = NotificationType.TELEGRAM;
                iArr[17] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                NotificationType notificationType15 = NotificationType.MISSED_CALL;
                iArr[20] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                NotificationType notificationType16 = NotificationType.LINKEDIN;
                iArr[15] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            $EnumSwitchMapping$1 = iArr;
            int[] iArr2 = new int[MatrixErrorType.values().length];
            try {
                iArr2[MatrixErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[MatrixErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            $EnumSwitchMapping$2 = iArr2;
        }
    }

    public MatrixBleImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f3182a = context;
        String simpleName = MatrixBleImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "MatrixBleImpl::class.java.simpleName");
        this.b = simpleName;
        this.e = new Handler();
        this.f = new Handler();
        this.h = new Handler();
        this.j = 180000;
        this.k = IDOConstants.MULTI_PACKET_CMD_MS_TIMER;
        this.l = 30000;
        this.o = new LinkedList<>();
        this.p = new Handler(Looper.getMainLooper());
        registerEventBus();
        initServiceConnection();
        checkAndStartService();
        this.m = WristbandApplication.getWristbandManager();
        PreferenceManagerMatrix.getInstance(context).saveBindOrLogin(true);
        this.r = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.w5
            @Override // java.lang.Runnable
            public final void run() {
                MatrixBleImpl.b(MatrixBleImpl.this);
            }
        };
    }

    public static final void b(MatrixBleImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.b, "Command TimeOut,Failed");
        BleBaseRequest bleBaseRequest = this$0.i;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            MatrixBaseReq matrixBleReq = this$0.getMatrixBleReq(bleBaseRequest);
            if (matrixBleReq != null) {
                this$0.onFailure(new MatrixError(MatrixErrorType.COMMAND_TIME_OUT, this$0.f3182a.getString(R.string.command_time_out)));
                LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + matrixBleReq);
            } else {
                BleBaseRequest bleBaseRequest2 = this$0.i;
                Intrinsics.checkNotNull(bleBaseRequest2);
                this$0.sendCommandNotFoundError(bleBaseRequest2);
            }
        }
        this$0.i = null;
    }

    public static final void c(DataResultListener dataResultListener, BleBaseResponse spO2Response) {
        Intrinsics.checkNotNullParameter(spO2Response, "$spO2Response");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spO2Response);
    }

    public static final void d(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        T t = bleBaseRequest.element;
        Intrinsics.checkNotNull(t);
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t);
        bleBaseResponse.setResponseData(null);
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
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new SleepResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.e.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f3182a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = MatrixBleImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        MatrixBleImpl.this.scanResultReceieved(new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.e.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.y5
            @Override // java.lang.Runnable
            public final void run() {
                MatrixBleImpl.a(MatrixBleImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.o) {
            MatrixBaseReq matrixBleReq = getMatrixBleReq(baseRequest);
            if (matrixBleReq != null) {
                if (matrixBleReq.isPriority()) {
                    this.o.addFirst(new QueueObject(baseRequest));
                } else {
                    this.o.add(new QueueObject(baseRequest));
                }
            } else if (!(baseRequest instanceof ActivityModeWithSamplesRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void bindBleService() {
        try {
            Intent intent = new Intent(this.f3182a, MatrixBleService.class);
            Context context = this.f3182a;
            ServiceConnection serviceConnection = this.n;
            Intrinsics.checkNotNull(serviceConnection);
            context.bindService(intent, serviceConnection, 1);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f3182a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    public final void checkAndStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(this.b, "checkAndStartService-> service is not running ++ ");
            startBleService();
            return;
        }
        bindBleService();
    }

    public final boolean checkIfServiceIsRunning() {
        Object systemService = this.f3182a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(MatrixBleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3182a.getPackageName(), runningServiceInfo.service.getPackageName())) {
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
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.i = null;
    }

    public final void clearParameters() {
        if (DeviceScanManager.getInstance(this.f3182a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f3182a).stopScan();
        }
        this.g = null;
        unbindService();
        clearCommandQueue();
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.e;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.h;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull final ConnectRequest request, @NotNull final ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
        this.f.removeCallbacksAndMessages(null);
        this.f.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.x5
            @Override // java.lang.Runnable
            public final void run() {
                MatrixBleImpl.a(MatrixBleImpl.this, request, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
        LogHelper.d(this.b, "disconnect called");
        this.d = listener;
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            matrixBleService.disconnectAndForget();
            clearParameters();
            return;
        }
        listener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Nullable
    public final MatrixBleService getBleService() {
        return this.g;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.IDLE;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (this.g != null) {
            ConnectionStatus connectionStatus = getConnectionStatus();
            MatrixBleService matrixBleService = this.g;
            Intrinsics.checkNotNull(matrixBleService);
            ConnectionError connectionError = matrixBleService.getConnectionError();
            MatrixBleService matrixBleService2 = this.g;
            Intrinsics.checkNotNull(matrixBleService2);
            return new ConnectionInfo(connectionStatus, connectionError, matrixBleService2.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Nullable
    public final ConnectionResultListener getConnectionResultListener() {
        return this.d;
    }

    @Nullable
    public final MutableLiveData<ConnectionStatus> getConnectionStateLiveData() {
        return this.c;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            if (matrixBleService.getConnectionState() == CloveMatrixBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            MatrixBleService matrixBleService2 = this.g;
            return (matrixBleService2 != null ? matrixBleService2.getConnectionState() : null) == CloveMatrixBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @NotNull
    public final Context getContext() {
        return this.f3182a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            Intrinsics.checkNotNull(matrixBleService);
            if (matrixBleService.getConnectionState() == CloveMatrixBleState.BleState.CONNECTED) {
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                sendCommandRequest();
                return;
            }
        }
        String string = this.f3182a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.q = deviceSupportedFeatures;
        deviceSupportedFeatures.setStepsSupported(true);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.q;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.q;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.q;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.q;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.q;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.q;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.q;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.q;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.q;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setPhoneFinderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.q;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.q;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.q;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.q;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.q;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.q;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.q;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.q;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setMultipleAlarmsSupportedAtATime(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.q;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.q;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.q;
        if (deviceSupportedFeatures21 != null) {
            deviceSupportedFeatures21.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.q;
        if (deviceSupportedFeatures22 != null) {
            deviceSupportedFeatures22.setSyncBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.q;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.q;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.q;
        if (deviceSupportedFeatures25 != null) {
            deviceSupportedFeatures25.setAutoTemperatureSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.q;
        if (deviceSupportedFeatures26 != null) {
            deviceSupportedFeatures26.setScheduleReminderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.q;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setREMSupportedInSleep(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.q;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setMaxDaysOfStepsDataOnBand(7);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.q;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setMaxDaysOfHeartRateDataOnBand(7);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.q;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setMaxDaysOfSleepDataOnBand(7);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.q;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setManualSpo2SupportedOnBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.q;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setScheduledDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.q;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setNotificationConfigurationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.q;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setFemaleWellnessSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.q;
        if (deviceSupportedFeatures35 != null) {
            deviceSupportedFeatures35.setCameraLaunchFromAppIsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.q;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setDrinkingReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.q;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setSedentaryReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.q;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.q;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setFindMyBandSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.q;
        Intrinsics.checkNotNull(deviceSupportedFeatures40);
        return deviceSupportedFeatures40;
    }

    @Nullable
    public final BleBaseRequest getFromQueue(@NotNull MatrixBaseReq matrixBaseReq) {
        Intrinsics.checkNotNullParameter(matrixBaseReq, "matrixBaseReq");
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            if (kotlin.text.m.equals(this.o.get(i).getBaseRequest().getRequId(), matrixBaseReq.getReqId(), true)) {
                return this.o.get(i).getBaseRequest();
            }
        }
        return null;
    }

    @Nullable
    public final BleBaseRequest getKhCurrentCommand() {
        return this.i;
    }

    @NotNull
    public final Handler getMDataResponseHandler() {
        return this.p;
    }

    @Nullable
    public final WristbandManager getMWristbandManager() {
        return this.m;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManagerMatrix.getInstance(this.f3182a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        return connectedDeviceMacAddress;
    }

    @Nullable
    public MatrixBaseReq getMatrixBleReq(@NotNull BleBaseRequest request) {
        MatrixBaseReq matrixBaseReq;
        MatrixBaseReq matrixBaseReq2;
        WomenHealthyConfig womenHealthyConfig;
        MatrixBaseReq matrixLiftWristReq;
        Intrinsics.checkNotNullParameter(request, "request");
        WristbandManager wristbandManager = this.m;
        if (wristbandManager != null) {
            Intrinsics.checkNotNull(wristbandManager);
            if (wristbandManager.getWristbandConfig() != null) {
                if (request instanceof StepsDataRequest) {
                    matrixBaseReq = new MatrixStepsReq();
                    matrixBaseReq.setReqId(request.getRequId());
                } else if (request instanceof SleepDataRequest) {
                    matrixBaseReq = new MatrixSleepReq();
                    matrixBaseReq.setReqId(request.getRequId());
                } else if (request instanceof HeartRateDataRequest) {
                    matrixBaseReq = new MatrixHeartRateReq();
                    matrixBaseReq.setReqId(request.getRequId());
                } else if (request instanceof SetHourFormatRequest) {
                    matrixBaseReq = new MatrixHourFormatReq(((SetHourFormatRequest) request).is12HourFormat());
                    matrixBaseReq.setReqId(request.getRequId());
                } else if (request instanceof SetDistanceUnitRequest) {
                    matrixBaseReq = new MatrixLengthUnitReq(((SetDistanceUnitRequest) request).isDistanceUnitinMile());
                    matrixBaseReq.setReqId(request.getRequId());
                } else if (request instanceof BatteryLevelRequest) {
                    matrixBaseReq = new MatrixBatteryLevelReq();
                    matrixBaseReq.setReqId(request.getRequId());
                } else if (request instanceof FindMyWatchRequest) {
                    matrixBaseReq = new MatrixFindBandReq();
                    matrixBaseReq.setReqId(request.getRequId());
                } else {
                    if (request instanceof HeartRateTimeIntervalRequest) {
                        WristbandManager wristbandManager2 = this.m;
                        Intrinsics.checkNotNull(wristbandManager2);
                        WristbandConfig wristbandConfig = wristbandManager2.getWristbandConfig();
                        Intrinsics.checkNotNull(wristbandConfig);
                        HealthyConfig healthyConfig = wristbandConfig.getHealthyConfig();
                        if (healthyConfig == null) {
                            healthyConfig = new HealthyConfig();
                        }
                        HeartRateTimeIntervalRequest heartRateTimeIntervalRequest = (HeartRateTimeIntervalRequest) request;
                        healthyConfig.setInterval(heartRateTimeIntervalRequest.getTimeInterval());
                        healthyConfig.setEnable(heartRateTimeIntervalRequest.getOpen() != 0);
                        MatrixUtils matrixUtils = MatrixUtils.INSTANCE;
                        healthyConfig.setStart(matrixUtils.getMinutes(heartRateTimeIntervalRequest.getStartHour(), heartRateTimeIntervalRequest.getStartMinute()));
                        healthyConfig.setEnd(matrixUtils.getMinutes(heartRateTimeIntervalRequest.getEndHour(), heartRateTimeIntervalRequest.getEndMinute()));
                        matrixLiftWristReq = new MatrixHealthReminderReq(healthyConfig);
                        matrixLiftWristReq.setReqId(request.getRequId());
                    } else {
                        char c = 3;
                        char c2 = 5;
                        char c3 = 4;
                        char c4 = 2;
                        int i = 7;
                        if (request instanceof SetVibrationAlarmListRequest) {
                            ArrayList arrayList = new ArrayList();
                            SetVibrationAlarmListRequest setVibrationAlarmListRequest = (SetVibrationAlarmListRequest) request;
                            int size = setVibrationAlarmListRequest.getVibrationAlarmRequests().size();
                            int i2 = 0;
                            while (i2 < size) {
                                SetVibrationAlarmRequest setVibrationAlarmRequest = setVibrationAlarmListRequest.getVibrationAlarmRequests().get(i2);
                                WristbandAlarm wristbandAlarm = new WristbandAlarm();
                                wristbandAlarm.setAlarmId(setVibrationAlarmRequest.getAlarmId());
                                int[] iArr = new int[i];
                                iArr[0] = setVibrationAlarmRequest.isMondayEnabled() ? 1 : 0;
                                iArr[1] = setVibrationAlarmRequest.isTuesdayEnabled() ? 1 : 0;
                                iArr[c4] = setVibrationAlarmRequest.isWednesdayEnabled() ? 1 : 0;
                                iArr[c] = setVibrationAlarmRequest.isThursdayEnabled() ? 1 : 0;
                                iArr[c3] = setVibrationAlarmRequest.isFridayEnabled() ? 1 : 0;
                                iArr[c2] = setVibrationAlarmRequest.isSaturdayEnabled() ? 1 : 0;
                                iArr[6] = setVibrationAlarmRequest.isSundayEnabled() ? 1 : 0;
                                int i3 = 0;
                                int i4 = 0;
                                while (i3 < i) {
                                    SetVibrationAlarmListRequest setVibrationAlarmListRequest2 = setVibrationAlarmListRequest;
                                    if (iArr[i3] == 1) {
                                        i4 += (int) Math.pow(2.0d, i3);
                                    }
                                    i3++;
                                    setVibrationAlarmListRequest = setVibrationAlarmListRequest2;
                                    i = 7;
                                }
                                wristbandAlarm.setRepeat(i4);
                                wristbandAlarm.setHour(setVibrationAlarmRequest.getHour());
                                wristbandAlarm.setMinute(setVibrationAlarmRequest.getMinute());
                                wristbandAlarm.setEnable(setVibrationAlarmRequest.isEnabled());
                                wristbandAlarm.setLabel(setVibrationAlarmRequest.getEventName());
                                arrayList.add(wristbandAlarm);
                                i2++;
                                c = 3;
                                c2 = 5;
                                c3 = 4;
                                c4 = 2;
                                i = 7;
                            }
                            matrixLiftWristReq = new MatrixVibrationAlarmReq(arrayList);
                            matrixLiftWristReq.setReqId(request.getRequId());
                        } else if (request instanceof SetReminderRequest) {
                            SetReminderRequest setReminderRequest = (SetReminderRequest) request;
                            if (setReminderRequest.getReminderType().equals(ReminderType.SEDENTARY_REMINDER)) {
                                WristbandManager wristbandManager3 = this.m;
                                Intrinsics.checkNotNull(wristbandManager3);
                                WristbandConfig wristbandConfig2 = wristbandManager3.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig2);
                                SedentaryConfig sedentaryConfig = wristbandConfig2.getSedentaryConfig();
                                if (sedentaryConfig == null) {
                                    sedentaryConfig = new SedentaryConfig();
                                }
                                MatrixUtils matrixUtils2 = MatrixUtils.INSTANCE;
                                sedentaryConfig.setStart(matrixUtils2.getMinutes(setReminderRequest.getStartHour1(), setReminderRequest.getStartMin1()));
                                sedentaryConfig.setEnd(matrixUtils2.getMinutes(setReminderRequest.getEndHour1(), setReminderRequest.getEndMin1()));
                                sedentaryConfig.setNotDisturbEnable(false);
                                sedentaryConfig.setInterval(setReminderRequest.getReminderInterval());
                                sedentaryConfig.setEnable(setReminderRequest.isEnabled());
                                matrixBaseReq = new MatrixSedentaryReminderReq(sedentaryConfig);
                                matrixBaseReq.setReqId(request.getRequId());
                            } else if (setReminderRequest.getReminderType().equals(ReminderType.DRINK_WATER_REMINDER)) {
                                WristbandManager wristbandManager4 = this.m;
                                Intrinsics.checkNotNull(wristbandManager4);
                                WristbandConfig wristbandConfig3 = wristbandManager4.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig3);
                                DrinkWaterConfig drinkWaterConfig = wristbandConfig3.getDrinkWaterConfig();
                                if (drinkWaterConfig == null) {
                                    drinkWaterConfig = new DrinkWaterConfig();
                                }
                                MatrixUtils matrixUtils3 = MatrixUtils.INSTANCE;
                                drinkWaterConfig.setStart(matrixUtils3.getMinutes(setReminderRequest.getStartHour1(), setReminderRequest.getStartMin1()));
                                drinkWaterConfig.setEnd(matrixUtils3.getMinutes(setReminderRequest.getEndHour1(), setReminderRequest.getEndMin1()));
                                drinkWaterConfig.setInterval(setReminderRequest.getReminderInterval());
                                drinkWaterConfig.setEnable(setReminderRequest.isEnabled());
                                matrixBaseReq = new MatrixDrinkReminderReq(drinkWaterConfig);
                                matrixBaseReq.setReqId(request.getRequId());
                            }
                        } else {
                            if (request instanceof SetMessageContentRequest) {
                                WristbandNotification wristbandNotification = new WristbandNotification();
                                SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) request;
                                wristbandNotification.setContent(setMessageContentRequest.message);
                                NotificationType appNotificationType = setMessageContentRequest.getAppNotificationType();
                                LogHelper.d(this.b, "appNotificationType from App" + appNotificationType);
                                switch (appNotificationType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[appNotificationType.ordinal()]) {
                                    case 1:
                                        wristbandNotification.setType((byte) 1);
                                        wristbandNotification.setName(setMessageContentRequest.message);
                                        break;
                                    case 2:
                                        wristbandNotification.setType((byte) 4);
                                        break;
                                    case 3:
                                        wristbandNotification.setType((byte) 16);
                                        break;
                                    case 4:
                                        wristbandNotification.setType((byte) 7);
                                        break;
                                    case 5:
                                        wristbandNotification.setType((byte) 12);
                                        break;
                                    case 6:
                                        wristbandNotification.setType((byte) 13);
                                        break;
                                    case 7:
                                        wristbandNotification.setType((byte) 10);
                                        break;
                                    case 8:
                                        wristbandNotification.setType((byte) 8);
                                        break;
                                    case 9:
                                        wristbandNotification.setType((byte) 5);
                                        break;
                                    case 10:
                                        wristbandNotification.setType((byte) 6);
                                        break;
                                    case 11:
                                        wristbandNotification.setType((byte) 17);
                                        break;
                                    case 12:
                                        wristbandNotification.setType((byte) -1);
                                        break;
                                    case 13:
                                        wristbandNotification.setType((byte) 21);
                                        break;
                                    case 14:
                                        wristbandNotification.setType((byte) 18);
                                        break;
                                    case 15:
                                        wristbandNotification.setType((byte) 25);
                                        break;
                                    case 16:
                                        wristbandNotification.setType((byte) 9);
                                        break;
                                    default:
                                        wristbandNotification.setType((byte) -1);
                                        break;
                                }
                                matrixBaseReq2 = new MatrixNotificationReq(wristbandNotification);
                                matrixBaseReq2.setReqId(request.getRequId());
                            } else if (request instanceof StopMessageNotificationRequest) {
                                WristbandNotification wristbandNotification2 = new WristbandNotification();
                                NotificationType appNotificationType2 = ((StopMessageNotificationRequest) request).getAppNotificationType();
                                LogHelper.d(this.b, "appNotificationType from App" + appNotificationType2);
                                if ((appNotificationType2 == null ? -1 : WhenMappings.$EnumSwitchMapping$1[appNotificationType2.ordinal()]) == 1) {
                                    wristbandNotification2.setType((byte) 2);
                                } else {
                                    wristbandNotification2.setType((byte) -1);
                                }
                                matrixBaseReq2 = new MatrixNotificationReq(wristbandNotification2);
                                matrixBaseReq2.setReqId(request.getRequId());
                            } else if (request instanceof SetDNDModeRequest) {
                                WristbandManager wristbandManager5 = this.m;
                                Intrinsics.checkNotNull(wristbandManager5);
                                WristbandConfig wristbandConfig4 = wristbandManager5.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig4);
                                if (wristbandConfig4.getWristbandVersion().isExtNotDisturb()) {
                                    WristbandManager wristbandManager6 = this.m;
                                    Intrinsics.checkNotNull(wristbandManager6);
                                    WristbandConfig wristbandConfig5 = wristbandManager6.getWristbandConfig();
                                    Intrinsics.checkNotNull(wristbandConfig5);
                                    NotDisturbConfig notDisturbConfig = wristbandConfig5.getNotDisturbConfig();
                                    if (notDisturbConfig == null) {
                                        notDisturbConfig = new NotDisturbConfig();
                                    }
                                    SetDNDModeRequest setDNDModeRequest = (SetDNDModeRequest) request;
                                    notDisturbConfig.setEnableAllDay(setDNDModeRequest.isDNDEnabled());
                                    notDisturbConfig.setEnablePeriodTime(setDNDModeRequest.isDNDEnabled());
                                    notDisturbConfig.setStart(0);
                                    notDisturbConfig.setEnd(1439);
                                    matrixLiftWristReq = new MatrixDNDReq(notDisturbConfig);
                                    matrixLiftWristReq.setReqId(request.getRequId());
                                }
                            } else if (request instanceof SetFitnessInfoRequest) {
                                SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) request;
                                matrixBaseReq = new MatrixUserInfoReq(setFitnessInfoRequest.isMale(), setFitnessInfoRequest.getAge(), setFitnessInfoRequest.getHeight(), (float) setFitnessInfoRequest.getWeight());
                                matrixBaseReq.setReqId(request.getRequId());
                            } else if (request instanceof StepsTargetRequest) {
                                matrixBaseReq = new MatrixSetSportGoalReq(((StepsTargetRequest) request).getTarget(), 0, 0);
                                matrixBaseReq.setReqId(request.getRequId());
                            } else if (request instanceof SetLiftWristRequest) {
                                WristbandManager wristbandManager7 = this.m;
                                Intrinsics.checkNotNull(wristbandManager7);
                                WristbandConfig wristbandConfig6 = wristbandManager7.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig6);
                                TurnWristLightingConfig turnWristLightingConfig = wristbandConfig6.getTurnWristLightingConfig();
                                if (turnWristLightingConfig == null) {
                                    turnWristLightingConfig = new TurnWristLightingConfig();
                                }
                                turnWristLightingConfig.setEnable(((SetLiftWristRequest) request).isLiftWristEnabled());
                                turnWristLightingConfig.setStart(0);
                                turnWristLightingConfig.setEnd(1439);
                                matrixLiftWristReq = new MatrixLiftWristReq(turnWristLightingConfig);
                                matrixLiftWristReq.setReqId(request.getRequId());
                            } else if (request instanceof DeviceInfoRequest) {
                                matrixBaseReq = new MatrixDeviceInfoReq();
                                matrixBaseReq.setReqId(request.getRequId());
                            } else if (request instanceof ReadManualSpo2Request) {
                                matrixBaseReq = new MatrixSPO2Request();
                                matrixBaseReq.setReqId(request.getRequId());
                            } else if (request instanceof ActivityModeWithSamplesRequest) {
                                matrixBaseReq = new MatrixSportsDataReq();
                                matrixBaseReq.setReqId(request.getRequId());
                            } else if (request instanceof SetWomenWellnessSettingsRequest) {
                                SetWomenWellnessSettingsRequest setWomenWellnessSettingsRequest = (SetWomenWellnessSettingsRequest) request;
                                if (setWomenWellnessSettingsRequest.getMEnabled()) {
                                    womenHealthyConfig = new WomenHealthyConfig();
                                    womenHealthyConfig.setRemindTime(MatrixUtils.INSTANCE.getMinutes(setWomenWellnessSettingsRequest.getMReminderHour(), setWomenWellnessSettingsRequest.getMReminderMinute()));
                                    womenHealthyConfig.setMenstruationAdvance(setWomenWellnessSettingsRequest.getMPeriodReminderAdvance());
                                    womenHealthyConfig.setMenstruationCycle(setWomenWellnessSettingsRequest.getMMenstruationCycleLength());
                                    womenHealthyConfig.setMenstruationDuration(setWomenWellnessSettingsRequest.getMMenstruationPeriodLength());
                                    womenHealthyConfig.setMode(1);
                                    womenHealthyConfig.setMenstruationLatest(BleApiUtils.INSTANCE.getDateFromString(setWomenWellnessSettingsRequest.getMLastPeriodDay(), setWomenWellnessSettingsRequest.getMLastPeriodMonth(), setWomenWellnessSettingsRequest.getMLastPeriodYear()));
                                } else {
                                    womenHealthyConfig = new WomenHealthyConfig();
                                }
                                matrixBaseReq2 = new MatrixWomenWellnessReq(womenHealthyConfig);
                                matrixBaseReq2.setReqId(request.getRequId());
                            } else if (request instanceof SetCallSmsSocialNotificationRequest) {
                                WristbandManager wristbandManager8 = this.m;
                                Intrinsics.checkNotNull(wristbandManager8);
                                WristbandConfig wristbandConfig7 = wristbandManager8.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig7);
                                NotificationConfig notificationConfig = wristbandConfig7.getNotificationConfig();
                                if (notificationConfig == null) {
                                    notificationConfig = new NotificationConfig();
                                }
                                SetCallSmsSocialNotificationRequest setCallSmsSocialNotificationRequest = (SetCallSmsSocialNotificationRequest) request;
                                notificationConfig.setFlagEnable(0, setCallSmsSocialNotificationRequest.isCallEnabled());
                                notificationConfig.setFlagEnable(1, setCallSmsSocialNotificationRequest.isSmsEnabled());
                                notificationConfig.setFlagEnable(2, setCallSmsSocialNotificationRequest.isQQEnabled());
                                notificationConfig.setFlagEnable(4, setCallSmsSocialNotificationRequest.isFacebookEnabled());
                                notificationConfig.setFlagEnable(9, setCallSmsSocialNotificationRequest.isWhatsAppEnabled());
                                notificationConfig.setFlagEnable(10, setCallSmsSocialNotificationRequest.isLineEnabled());
                                WristbandManager wristbandManager9 = this.m;
                                Intrinsics.checkNotNull(wristbandManager9);
                                WristbandConfig wristbandConfig8 = wristbandManager9.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig8);
                                if (wristbandConfig8.getWristbandVersion().isExtAncsEmail()) {
                                    notificationConfig.setFlagEnable(14, setCallSmsSocialNotificationRequest.isEmailEnabled());
                                }
                                WristbandManager wristbandManager10 = this.m;
                                Intrinsics.checkNotNull(wristbandManager10);
                                WristbandConfig wristbandConfig9 = wristbandManager10.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig9);
                                if (wristbandConfig9.getWristbandVersion().isExtAncsViberTelegram()) {
                                    notificationConfig.setFlagEnable(15, setCallSmsSocialNotificationRequest.isTelegramEnabled());
                                }
                                WristbandManager wristbandManager11 = this.m;
                                Intrinsics.checkNotNull(wristbandManager11);
                                WristbandConfig wristbandConfig10 = wristbandManager11.getWristbandConfig();
                                Intrinsics.checkNotNull(wristbandConfig10);
                                if (wristbandConfig10.getWristbandVersion().isExtAncsExtra1()) {
                                    notificationConfig.setFlagEnable(5, setCallSmsSocialNotificationRequest.isTwitterEnabled());
                                    notificationConfig.setFlagEnable(6, setCallSmsSocialNotificationRequest.isLinkedInEnabled());
                                    notificationConfig.setFlagEnable(7, setCallSmsSocialNotificationRequest.isInstagramEnabled());
                                    notificationConfig.setFlagEnable(11, setCallSmsSocialNotificationRequest.isMessengerEnabled());
                                    notificationConfig.setFlagEnable(13, setCallSmsSocialNotificationRequest.isSkypeEnabled());
                                    notificationConfig.setFlagEnable(18, setCallSmsSocialNotificationRequest.isSnapchatEnabled());
                                }
                                notificationConfig.setFlagEnable(31, setCallSmsSocialNotificationRequest.isOtherAppEnabled());
                                matrixBaseReq2 = new MatrixNotificationConfigReq(notificationConfig);
                                matrixBaseReq2.setReqId(request.getRequId());
                            } else if (request instanceof CustomWatchFaceFileImageRequest) {
                                matrixBaseReq2 = new MatrixCloudWatchFaceReq();
                                matrixBaseReq2.setReqId(request.getRequId());
                                matrixBaseReq2.setFilePath(((CustomWatchFaceFileImageRequest) request).watchFaceFilePath);
                            } else if (request instanceof CustomWatchFaceBackgroundChangeRequest) {
                                DialDrawer.Position position = DialDrawer.Position.BOTTOM;
                                CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest = (CustomWatchFaceBackgroundChangeRequest) request;
                                int position2 = customWatchFaceBackgroundChangeRequest.getCustomWatchFaceLayoutChangeRequest().getPosition();
                                if (position2 == 0) {
                                    position = DialDrawer.Position.TOP;
                                } else if (position2 != 1) {
                                    if (position2 == 2) {
                                        position = DialDrawer.Position.LEFT;
                                    } else if (position2 == 3) {
                                        position = DialDrawer.Position.RIGHT;
                                    }
                                }
                                File basicBinFile = customWatchFaceBackgroundChangeRequest.getCustomWatchFaceLayoutChangeRequest().getBasicBinFile();
                                Intrinsics.checkNotNullExpressionValue(basicBinFile, "request.customWatchFaceL…hangeRequest.basicBinFile");
                                Bitmap bitmap = customWatchFaceBackgroundChangeRequest.getBitmap();
                                Intrinsics.checkNotNullExpressionValue(bitmap, "request.bitmap");
                                Bitmap styleBitMap = customWatchFaceBackgroundChangeRequest.getCustomWatchFaceLayoutChangeRequest().getStyleBitMap();
                                Intrinsics.checkNotNullExpressionValue(styleBitMap, "request.customWatchFaceL…ChangeRequest.styleBitMap");
                                MatrixDIYWatchFaceReq matrixDIYWatchFaceReq = new MatrixDIYWatchFaceReq(basicBinFile, bitmap, styleBitMap, position, customWatchFaceBackgroundChangeRequest.getCustomWatchFaceLayoutChangeRequest().getStylePosition());
                                matrixDIYWatchFaceReq.setReqId(request.getRequId());
                                matrixDIYWatchFaceReq.setFilePath(((CustomWatchFaceBackgroundChangeRequest) request).getWatchFaceFilePath());
                                return matrixDIYWatchFaceReq;
                            } else if (request instanceof GetDNDModeSettingsRequest) {
                                MatrixGetDNDConfig matrixGetDNDConfig = new MatrixGetDNDConfig();
                                matrixGetDNDConfig.setReqId(request.getRequId());
                                return matrixGetDNDConfig;
                            } else if (request instanceof GetAlarmDataRequest) {
                                MatrixGetAlarmListData matrixGetAlarmListData = new MatrixGetAlarmListData();
                                matrixGetAlarmListData.setReqId(request.getRequId());
                                return matrixGetAlarmListData;
                            } else if (request instanceof EnterRemoteCameraRequest) {
                                MatrixCameraRequest matrixCameraRequest = new MatrixCameraRequest(true);
                                matrixCameraRequest.setReqId(request.getRequId());
                                return matrixCameraRequest;
                            } else if (request instanceof ExitRemoteCameraRequest) {
                                MatrixCameraRequest matrixCameraRequest2 = new MatrixCameraRequest(false);
                                matrixCameraRequest2.setReqId(request.getRequId());
                                return matrixCameraRequest2;
                            } else if (request instanceof ChangeWatchFaceRequest) {
                                MatrixDIYComponentReq matrixDIYComponentReq = new MatrixDIYComponentReq(((ChangeWatchFaceRequest) request).watchFacePosition);
                                matrixDIYComponentReq.setReqId(request.getRequId());
                                return matrixDIYComponentReq;
                            } else if (request instanceof SetSocialOrUpiQrCodeRequest) {
                                SetSocialOrUpiQrCodeRequest setSocialOrUpiQrCodeRequest = (SetSocialOrUpiQrCodeRequest) request;
                                MatrixSetQRCodeReq matrixSetQRCodeReq = new MatrixSetQRCodeReq(setSocialOrUpiQrCodeRequest.getAppType(), setSocialOrUpiQrCodeRequest.getQrCodeData());
                                matrixSetQRCodeReq.setReqId(request.getRequId());
                                return matrixSetQRCodeReq;
                            }
                            return matrixBaseReq2;
                        }
                    }
                    matrixBaseReq = matrixLiftWristReq;
                }
                matrixBaseReq2 = matrixBaseReq;
                return matrixBaseReq2;
            }
        }
        matrixBaseReq = null;
        matrixBaseReq2 = matrixBaseReq;
        return matrixBaseReq2;
    }

    @NotNull
    public final LinkedList<QueueObject> getQueue() {
        return this.o;
    }

    @Nullable
    public final ServiceConnection getServiceConnection() {
        return this.n;
    }

    @NotNull
    public final Handler getSyncTimeOutHandler() {
        return this.h;
    }

    public final void initServiceConnection() {
        this.n = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$initServiceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                if (service instanceof MatrixBleService.BtServiceBinder) {
                    MatrixBleImpl.this.setBleService(((MatrixBleService.BtServiceBinder) service).getService());
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(@NotNull ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                MatrixBleImpl.this.setBleService(null);
            }
        };
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return false;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Subscribe
    public void onConnectionStateChangedHandler(@Nullable CloveMatrixBleState cloveMatrixBleState) {
        ConnectionStatus connectionStatus;
        ConnectionStatus connectionStatus2 = ConnectionStatus.DISCONNECTED;
        if (cloveMatrixBleState != null) {
            CloveMatrixBleState.BleState bleState = cloveMatrixBleState.getmState();
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
        MutableLiveData<ConnectionStatus> mutableLiveData = this.c;
        if (mutableLiveData != null) {
            Intrinsics.checkNotNull(mutableLiveData);
            mutableLiveData.setValue(connectionStatus);
            MutableLiveData<ConnectionStatus> mutableLiveData2 = this.c;
            Intrinsics.checkNotNull(mutableLiveData2);
            mutableLiveData2.postValue(connectionStatus);
        }
        if (connectionStatus == connectionStatus2) {
            BleBaseRequest bleBaseRequest = this.i;
            if ((bleBaseRequest instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest instanceof CustomWatchFaceBackgroundChangeRequest) || (bleBaseRequest instanceof SetSportsPushRequest)) {
                return;
            }
            sendErrorAndClearQueue(new MatrixError(MatrixErrorType.DEVICE_NOT_CONNECTED, "Device disconnected"));
        }
        ConnectionResultListener connectionResultListener = this.d;
        if (connectionResultListener != null) {
            Intrinsics.checkNotNull(connectionResultListener);
            connectionResultListener.onConnectionResponse(connectionStatus);
        }
    }

    @Override // com.coveiot.android.matrixsdk.MatrixResponseListener
    public void onFailure(@NotNull MatrixError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        MatrixErrorType errorType = error.getErrorType();
        int i = errorType == null ? -1 : WhenMappings.$EnumSwitchMapping$2[errorType.ordinal()];
        if (i == 1 || i == 2) {
            sendErrorAndClearQueue(error);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object, com.coveiot.android.bleabstract.request.BleBaseRequest] */
    /* JADX WARN: Type inference failed for: r4v9, types: [com.coveiot.android.bleabstract.models.DNDData, T] */
    @Override // com.coveiot.android.matrixsdk.MatrixResponseListener
    public void onResponse(@NotNull MatrixBaseRes baseRes) {
        Intrinsics.checkNotNullParameter(baseRes, "baseRes");
        try {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? fromQueue = getFromQueue(baseRes.getBaseReq());
            objectRef.element = fromQueue;
            if (fromQueue != 0) {
                Intrinsics.checkNotNull(fromQueue);
                boolean z = true;
                if (fromQueue.getResponseListener() instanceof DataResultListener) {
                    T t = objectRef.element;
                    Intrinsics.checkNotNull(t);
                    final DataResultListener dataResultListener = (DataResultListener) ((BleBaseRequest) t).getResponseListener();
                    MatrixBaseReq baseReq = baseRes.getBaseReq();
                    if (baseReq instanceof MatrixStepsReq) {
                        Handler handler = this.h;
                        if (handler != null) {
                            handler.removeCallbacksAndMessages(null);
                        }
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MatrixBleImpl$onResponse$1(baseRes, objectRef, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.s5
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.e(Ref.ObjectRef.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixSleepReq) {
                        Handler handler2 = this.h;
                        if (handler2 != null) {
                            handler2.removeCallbacksAndMessages(null);
                        }
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MatrixBleImpl$onResponse$3(baseRes, objectRef, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.t5
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.f(Ref.ObjectRef.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixHeartRateReq) {
                        Handler handler3 = this.h;
                        if (handler3 != null) {
                            handler3.removeCallbacksAndMessages(null);
                        }
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MatrixBleImpl$onResponse$5(baseRes, objectRef, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.d6
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.a(Ref.ObjectRef.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixBatteryLevelReq) {
                        Handler handler4 = this.h;
                        if (handler4 != null) {
                            handler4.removeCallbacksAndMessages(null);
                        }
                        String str = this.b;
                        LogHelper.d(str, "GetDeviceBatteryLevel == " + baseRes.getObj());
                        Object obj = baseRes.getObj();
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                        int intValue = ((Integer) obj).intValue();
                        T t2 = objectRef.element;
                        Intrinsics.checkNotNull(t2);
                        final DataResultListener dataResultListener2 = (DataResultListener) ((BleBaseRequest) t2).getResponseListener();
                        BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse();
                        batteryLevelResponse.setBatteryLevel(Integer.valueOf(intValue));
                        batteryLevelResponse.setComplete(true);
                        T t3 = objectRef.element;
                        Intrinsics.checkNotNull(t3);
                        final BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t3);
                        bleBaseResponse.setResponseData(batteryLevelResponse);
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.z5
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.a(DataResultListener.this, bleBaseResponse);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixDeviceInfoReq) {
                        Handler handler5 = this.h;
                        if (handler5 != null) {
                            handler5.removeCallbacksAndMessages(null);
                        }
                        String str2 = this.b;
                        LogHelper.d(str2, "MatrixDeviceInfoReq == " + baseRes.getObj());
                        Object obj2 = baseRes.getObj();
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.htsmart.wristband2.bean.WristbandVersion");
                        WristbandVersion wristbandVersion = (WristbandVersion) obj2;
                        DeviceInfoData deviceInfoData = new DeviceInfoData();
                        deviceInfoData.setMacAddress(getMacAddress());
                        deviceInfoData.setHwVersion(wristbandVersion.getHardware());
                        deviceInfoData.setSerialNo(wristbandVersion.getSerial());
                        String app = wristbandVersion.getApp();
                        Intrinsics.checkNotNullExpressionValue(app, "version.app");
                        deviceInfoData.setFwVersion(new StringBuffer(String.valueOf(Integer.parseInt(app))).insert(1, '.').toString());
                        DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
                        deviceInfoResponse.setComplete(true);
                        deviceInfoResponse.setDeviceInfo(deviceInfoData);
                        final BleBaseResponse bleBaseResponse2 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                        bleBaseResponse2.setResponseData(deviceInfoResponse);
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.a6
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.b(DataResultListener.this, bleBaseResponse2);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixSPO2Request) {
                        Handler handler6 = this.h;
                        if (handler6 != null) {
                            handler6.removeCallbacksAndMessages(null);
                        }
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof ReadManualSpo2Response) {
                                final BleBaseResponse bleBaseResponse3 = new BleBaseResponse((BleBaseRequest) objectRef.element);
                                bleBaseResponse3.setResponseData(baseRes.getObj());
                                this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.b6
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        MatrixBleImpl.c(DataResultListener.this, bleBaseResponse3);
                                    }
                                });
                            }
                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MatrixBleImpl$onResponse$10(this, null), 2, null);
                            setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                            return;
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.e6
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.b(Ref.ObjectRef.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixSportsDataReq) {
                        Handler handler7 = this.h;
                        if (handler7 != null) {
                            handler7.removeCallbacksAndMessages(null);
                        }
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MatrixBleImpl$onResponse$12(baseRes, objectRef, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.q5
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.c(Ref.ObjectRef.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixDIYWatchFaceReq ? true : baseReq instanceof MatrixCloudWatchFaceReq ? true : baseReq instanceof MatrixSportsPushReq) {
                        String str3 = this.b;
                        LogHelper.d(str3, "MatrixDIYWatchFaceReq or MatrixCloudWatchFaceReq or MatrixSportsPushReq== " + baseRes.getObj());
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof LiveWatchFaceUploadPercentage)) {
                            ProgressType progressType = ProgressType.DETERMINATE;
                            Object obj3 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage");
                            int percentage = ((LiveWatchFaceUploadPercentage) obj3).getPercentage();
                            T t4 = objectRef.element;
                            Intrinsics.checkNotNull(t4);
                            ProgressData progressData = new ProgressData(progressType, percentage, (BleBaseRequest) t4);
                            Intrinsics.checkNotNull(dataResultListener);
                            dataResultListener.onProgressUpdate(progressData);
                            return;
                        }
                        Handler handler8 = this.h;
                        if (handler8 != null) {
                            handler8.removeCallbacksAndMessages(null);
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.r5
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.d(Ref.ObjectRef.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixGetDNDConfig) {
                        Handler handler9 = this.h;
                        if (handler9 != null) {
                            handler9.removeCallbacksAndMessages(null);
                        }
                        String str4 = this.b;
                        LogHelper.d(str4, "MatrixGetDNDConfig == " + baseRes.getObj());
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof NotDisturbConfig)) {
                            Object obj4 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type com.htsmart.wristband2.bean.config.NotDisturbConfig");
                            NotDisturbConfig notDisturbConfig = (NotDisturbConfig) obj4;
                            T t5 = objectRef.element;
                            Intrinsics.checkNotNull(t5);
                            final DataResultListener dataResultListener3 = (DataResultListener) ((BleBaseRequest) t5).getResponseListener();
                            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            if (!notDisturbConfig.isEnableAllDay()) {
                                notDisturbConfig.isEnablePeriodTime();
                            }
                            String str5 = this.b;
                            LogHelper.d(str5, "DNDData notDisturbConfig isEnableAllDay " + notDisturbConfig.isEnableAllDay() + " isEnablePeriodTime " + notDisturbConfig.isEnablePeriodTime() + " start " + notDisturbConfig.getStart() + " end " + notDisturbConfig.getEnd());
                            objectRef2.element = new DNDData(notDisturbConfig.isEnablePeriodTime(), 0, 0, 23, 59);
                            this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.v5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    MatrixBleImpl.a(Ref.ObjectRef.this, objectRef2, dataResultListener3);
                                }
                            });
                        }
                        setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                    } else if (baseReq instanceof MatrixGetAlarmListData) {
                        Handler handler10 = this.h;
                        if (handler10 != null) {
                            handler10.removeCallbacksAndMessages(null);
                        }
                        T t6 = objectRef.element;
                        Intrinsics.checkNotNull(t6);
                        final DataResultListener dataResultListener4 = (DataResultListener) ((BleBaseRequest) t6).getResponseListener();
                        if ((baseRes.getBaseReq() instanceof MatrixGetAlarmListData) && baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                            final ArrayList arrayList = new ArrayList();
                            Object obj5 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.collections.List<com.htsmart.wristband2.bean.WristbandAlarm>");
                            for (WristbandAlarm wristbandAlarm : (List) obj5) {
                                int repeat = wristbandAlarm.getRepeat();
                                arrayList.add(new Alarm(wristbandAlarm.isEnable(), wristbandAlarm.getAlarmId(), wristbandAlarm.getHour(), wristbandAlarm.getMinute(), 0, wristbandAlarm.getLabel(), wristbandAlarm.getRepeat() != 0, WristbandAlarm.isRepeatEnableIndex(repeat, 6), WristbandAlarm.isRepeatEnableIndex(repeat, 0), WristbandAlarm.isRepeatEnableIndex(repeat, 1), WristbandAlarm.isRepeatEnableIndex(repeat, 2), WristbandAlarm.isRepeatEnableIndex(repeat, 3), WristbandAlarm.isRepeatEnableIndex(repeat, 4), WristbandAlarm.isRepeatEnableIndex(repeat, 5), 16, null));
                            }
                            this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.u5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    MatrixBleImpl.a(Ref.ObjectRef.this, arrayList, dataResultListener4);
                                }
                            });
                            setCompleteAndProcessNext((BleBaseRequest) objectRef.element);
                        }
                    }
                } else if (((BleBaseRequest) objectRef.element).getResponseListener() instanceof SettingsResultListener) {
                    MatrixBaseReq baseReq2 = baseRes.getBaseReq();
                    if (!(baseReq2 instanceof MatrixVibrationAlarmReq ? true : baseReq2 instanceof MatrixLengthUnitReq ? true : baseReq2 instanceof MatrixHourFormatReq ? true : baseReq2 instanceof MatrixSedentaryReminderReq ? true : baseReq2 instanceof MatrixHealthReminderReq ? true : baseReq2 instanceof MatrixNotificationReq ? true : baseReq2 instanceof MatrixUserInfoReq ? true : baseReq2 instanceof MatrixFindBandReq ? true : baseReq2 instanceof MatrixDNDReq ? true : baseReq2 instanceof MatrixSetSportGoalReq ? true : baseReq2 instanceof MatrixLiftWristReq ? true : baseReq2 instanceof MatrixDrinkReminderReq ? true : baseReq2 instanceof MatrixWomenWellnessReq ? true : baseReq2 instanceof MatrixNotificationConfigReq ? true : baseReq2 instanceof MatrixCameraRequest ? true : baseReq2 instanceof MatrixDIYComponentReq)) {
                        z = baseReq2 instanceof MatrixSetQRCodeReq;
                    }
                    if (z) {
                        Handler handler11 = this.h;
                        if (handler11 != null) {
                            handler11.removeCallbacksAndMessages(null);
                        }
                        String str6 = this.b;
                        LogHelper.d(str6, "onResponse  ==" + baseRes.getBaseReq());
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.c6
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleImpl.a(Ref.ObjectRef.this, this);
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void processNextCommand() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        MatrixBaseReq matrixBleReq = getMatrixBleReq(this.o.get(0).getBaseRequest());
        if (matrixBleReq != null) {
            BleBaseRequest bleBaseRequest = this.i;
            if (bleBaseRequest != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                if (!bleBaseRequest.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(matrixBleReq);
            return;
        }
        sendCommandNotFoundError(this.o.get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.c == null) {
            this.c = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        Intrinsics.checkNotNull(connectionStatus);
        ConnectionStatus updateConnectionStatus = updateConnectionStatus(connectionStatus);
        MutableLiveData<ConnectionStatus> mutableLiveData = this.c;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.postValue(updateConnectionStatus);
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.c;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
    }

    public final void registerEventBus() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MatrixBleImpl$registerEventBus$1(this, null), 2, null);
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
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveHealthData> registerLiveHealthData() {
        return new MutableLiveData();
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
        return new MutableLiveData();
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
    public final void removeFromQueue(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.request.BleBaseRequest r7) {
        /*
            r6 = this;
            java.lang.String r0 = "bleBaseRequest"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$QueueObject> r0 = r6.o
            monitor-enter(r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$QueueObject> r1 = r6.o     // Catch: java.lang.Throwable -> L3d
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3d
            r2 = 0
        Lf:
            if (r2 >= r1) goto L3b
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$QueueObject> r3 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$QueueObject r3 = (com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl.QueueObject) r3     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r3 = r3.getRequId()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r7.getRequId()     // Catch: java.lang.Throwable -> L3d
            r5 = 1
            boolean r3 = kotlin.text.m.equals(r3, r4, r5)     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L38
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$QueueObject> r7 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r7 = r7.remove(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$QueueObject r7 = (com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl.QueueObject) r7     // Catch: java.lang.Throwable -> L3d
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl.removeFromQueue(com.coveiot.android.bleabstract.request.BleBaseRequest):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        clearParameters();
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            matrixBleService.restartService();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p5
            @Override // java.lang.Runnable
            public final void run() {
                MatrixBleImpl.a(MatrixBleImpl.this);
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(@NotNull ScanDeviceRequest request, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        a(request, listener);
    }

    public final void scanResultReceieved(@NotNull List<? extends BleDevice> devices, boolean z, @NotNull ScanDeviceRequest scanDeviceReq, @NotNull ScanResultListener listener) {
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

    public final void sendCommandNotFoundError(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        BaseListener responseListener = baseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            String string = this.f3182a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f3182a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f3182a.getString(R.string.command_not_found)));
        }
    }

    public final void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0 || this.i != null) {
            return;
        }
        processNextCommand();
    }

    public final void sendErrorAndClearQueue(@NotNull MatrixError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.o) {
            LinkedList<QueueObject> linkedList = this.o;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.o.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl.QueueObject>");
                    Iterator it = ((LinkedList) clone).iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = ((QueueObject) it.next()).getBaseRequest().getResponseListener();
                        String message = error.getMessage();
                        Intrinsics.checkNotNull(message);
                        BleBaseError bleBaseError = new BleBaseError(message);
                        if (error.getErrorType() == MatrixErrorType.COMMAND_TIME_OUT) {
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

    public final void sendRequestToBleService(@NotNull MatrixBaseReq matrixBaseReq) {
        Intrinsics.checkNotNullParameter(matrixBaseReq, "matrixBaseReq");
        this.i = this.o.get(0).getBaseRequest();
        if (matrixBaseReq.isMultiPacket()) {
            if (matrixBaseReq instanceof MatrixDIYWatchFaceReq) {
                this.h.postDelayed(this.r, this.j);
            } else if (matrixBaseReq instanceof MatrixCloudWatchFaceReq) {
                this.h.postDelayed(this.r, this.j);
            } else {
                this.h.postDelayed(this.r, this.k);
            }
        } else {
            this.h.postDelayed(this.r, this.l);
        }
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            matrixBleService.sendRequest(matrixBaseReq, this);
        }
    }

    public final void setBleService(@Nullable MatrixBleService matrixBleService) {
        this.g = matrixBleService;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public final void setCompleteAndProcessNext(@Nullable BleBaseRequest bleBaseRequest) {
        if (bleBaseRequest != null) {
            removeFromQueue(bleBaseRequest);
            String str = this.b;
            LogHelper.d(str, "setCompleteAndProcessNext--> removed " + bleBaseRequest);
        }
        if (this.i != null) {
            this.i = null;
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
        this.c = mutableLiveData;
    }

    public final void setKhCurrentCommand(@Nullable BleBaseRequest bleBaseRequest) {
        this.i = bleBaseRequest;
    }

    public final void setMWristbandManager(@Nullable WristbandManager wristbandManager) {
        this.m = wristbandManager;
    }

    public final void setServiceConnection(@Nullable ServiceConnection serviceConnection) {
        this.n = serviceConnection;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            Intrinsics.checkNotNull(matrixBleService);
            if (matrixBleService.getConnectionState() == CloveMatrixBleState.BleState.CONNECTED) {
                if (a(request)) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
                String str = this.b;
                LogHelper.d(str, "setUserSettings->Ignore {" + request + '}');
                return;
            }
        }
        String string = this.f3182a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public final void startBleService() {
        try {
            Intent intent = new Intent(this.f3182a, MatrixBleService.class);
            Context context = this.f3182a;
            ServiceConnection serviceConnection = this.n;
            Intrinsics.checkNotNull(serviceConnection);
            context.bindService(intent, serviceConnection, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f3182a.startForegroundService(intent);
            } else {
                this.f3182a.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f3182a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f3182a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManagerMatrix.getInstance(this.f3182a).saveConnectedDeviceMAcAddress("");
        PreferenceManagerMatrix.getInstance(this.f3182a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            Intrinsics.checkNotNull(matrixBleService);
            matrixBleService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        LogHelper.d(this.b, "stopServiceAndRetainMacAddress");
        clearCommandQueue();
        PreferenceManagerMatrix.getInstance(this.f3182a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            matrixBleService.stopServiceRetainMacAddress();
            clearParameters();
        }
    }

    public final void unbindService() {
        ServiceConnection serviceConnection = this.n;
        if (serviceConnection != null) {
            try {
                Context context = this.f3182a;
                Intrinsics.checkNotNull(serviceConnection);
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    @Nullable
    public final ConnectionStatus updateConnectionStatus(@NotNull ConnectionStatus state) {
        Intrinsics.checkNotNullParameter(state, "state");
        MatrixBleService matrixBleService = this.g;
        if (matrixBleService != null) {
            Intrinsics.checkNotNull(matrixBleService);
            if (matrixBleService.getConnectionState() == CloveMatrixBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            MatrixBleService matrixBleService2 = this.g;
            Intrinsics.checkNotNull(matrixBleService2);
            if (matrixBleService2.getConnectionState() == CloveMatrixBleState.BleState.CONNECTING) {
                return ConnectionStatus.CONNECTING;
            }
            MatrixBleService matrixBleService3 = this.g;
            Intrinsics.checkNotNull(matrixBleService3);
            if (matrixBleService3.getConnectionState() == CloveMatrixBleState.BleState.SCANNING) {
                return ConnectionStatus.SCANNING;
            }
            MatrixBleService matrixBleService4 = this.g;
            Intrinsics.checkNotNull(matrixBleService4);
            if (matrixBleService4.getConnectionState() == CloveMatrixBleState.BleState.DISCOVERING_SERVICES) {
                return ConnectionStatus.DISCOVERING_SERVICES;
            }
            return ConnectionStatus.DISCONNECTED;
        }
        return state;
    }

    public static final void c(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(final MatrixBleImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f3182a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f3182a).scanAllDevices(this$0.f3182a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            MatrixBleImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = MatrixBleImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f3182a).scanDevicesWithFilter(this$0.f3182a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            MatrixBleImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = MatrixBleImpl.this.getContext().getString(R.string.scan_failed);
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

    public static final void b(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public static final void b(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
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

    public static final void a(MatrixBleImpl this$0, ConnectRequest request, ConnectionResultListener listener) {
        BluetoothDevice mBluetoothDevice;
        BluetoothDevice mBluetoothDevice2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        MatrixBleService matrixBleService = this$0.g;
        if (matrixBleService == null) {
            if (matrixBleService == null) {
                this$0.startBleService();
                listener.onError(new Error(Type.SERVICE_NOT_RUNNING, this$0.f3182a.getString(R.string.service_not_running)));
                return;
            }
            listener.onError(new Error(Type.COMMAND_REQUEST_ERROR, this$0.f3182a.getString(R.string.request_should_not_null)));
            return;
        }
        CloveMatrixBleState.BleState connectionState = matrixBleService.getConnectionState();
        CloveMatrixBleState.BleState bleState = CloveMatrixBleState.BleState.CONNECTED;
        if (connectionState == bleState && kotlin.text.m.equals(this$0.getMacAddress(), request.getMacAddress(), true)) {
            listener.onConnectionResponse(ConnectionStatus.CONNECTED);
            return;
        }
        MatrixBleService matrixBleService2 = this$0.g;
        r2 = null;
        String str = null;
        r2 = null;
        String str2 = null;
        if ((matrixBleService2 != null ? matrixBleService2.getConnectionState() : null) == CloveMatrixBleState.BleState.DISCONNECTED) {
            MatrixBleService matrixBleService3 = this$0.g;
            if (matrixBleService3 != null) {
                matrixBleService3.connect(request.getMacAddress());
                return;
            }
            return;
        }
        MatrixBleService matrixBleService4 = this$0.g;
        if ((matrixBleService4 != null ? matrixBleService4.getConnectionState() : null) == bleState) {
            MatrixBleService matrixBleService5 = this$0.g;
            if ((matrixBleService5 != null ? matrixBleService5.getMBluetoothDevice() : null) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Connected to band ");
                MatrixBleService matrixBleService6 = this$0.g;
                if (matrixBleService6 != null && (mBluetoothDevice2 = matrixBleService6.getMBluetoothDevice()) != null) {
                    str = mBluetoothDevice2.getAddress();
                }
                sb.append(str);
                listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, sb.toString()));
                return;
            }
            MatrixBleService matrixBleService7 = this$0.g;
            CloveMatrixBleState.BleState connectionState2 = matrixBleService7 != null ? matrixBleService7.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState2);
            listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, connectionState2.getStateAsString()));
            return;
        }
        MatrixBleService matrixBleService8 = this$0.g;
        if ((matrixBleService8 != null ? matrixBleService8.getConnectionState() : null) == CloveMatrixBleState.BleState.CONNECTING) {
            MatrixBleService matrixBleService9 = this$0.g;
            if ((matrixBleService9 != null ? matrixBleService9.getMBluetoothDevice() : null) != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Connection in progress ");
                MatrixBleService matrixBleService10 = this$0.g;
                if (matrixBleService10 != null && (mBluetoothDevice = matrixBleService10.getMBluetoothDevice()) != null) {
                    str2 = mBluetoothDevice.getAddress();
                }
                sb2.append(str2);
                listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, sb2.toString()));
                return;
            }
            MatrixBleService matrixBleService11 = this$0.g;
            CloveMatrixBleState.BleState connectionState3 = matrixBleService11 != null ? matrixBleService11.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState3);
            listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState3.getStateAsString()));
            return;
        }
        MatrixBleService matrixBleService12 = this$0.g;
        CloveMatrixBleState.BleState connectionState4 = matrixBleService12 != null ? matrixBleService12.getConnectionState() : null;
        Intrinsics.checkNotNull(connectionState4);
        listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState4.getStateAsString()));
    }

    public static final void a(MatrixBleImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startBleService();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(new HeartRateResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, Ref.ObjectRef dndData, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(dndData, "$dndData");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(dndData.element);
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

    public static final void a(Ref.ObjectRef bleBaseRequest, MatrixBleImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsResultListener settingsResultListener = (SettingsResultListener) ((BleBaseRequest) bleBaseRequest.element).getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse((BleBaseRequest) bleBaseRequest.element));
        this$0.setCompleteAndProcessNext((BleBaseRequest) bleBaseRequest.element);
    }

    public final boolean a(BleBaseRequest bleBaseRequest) {
        boolean z = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.i;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && bleBaseRequest != null && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).appNotificationType != null && ((SetMessageContentRequest) bleBaseRequest).appNotificationType == NotificationType.CALL) {
                    z = false;
                    LogHelper.d(this.b, "Ignore incoming call triggered during watch face upgrade");
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
