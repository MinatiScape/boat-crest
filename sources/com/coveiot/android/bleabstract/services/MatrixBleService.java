package com.coveiot.android.bleabstract.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.formatter.matrix.MatrixStepsFormatter;
import com.coveiot.android.bleabstract.models.CloveMatrixBleState;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.khmatrixdb.steps.KhMatrixStepsRepository;
import com.coveiot.android.matrixsdk.MatrixResponseListener;
import com.coveiot.android.matrixsdk.api.MatrixBaseReq;
import com.coveiot.android.matrixsdk.api.MatrixBaseRes;
import com.coveiot.android.matrixsdk.api.MatrixCloudWatchFaceReq;
import com.coveiot.android.matrixsdk.api.MatrixDIYWatchFaceReq;
import com.coveiot.android.matrixsdk.api.MatrixSportsPushReq;
import com.coveiot.android.matrixsdk.error.MatrixError;
import com.coveiot.android.matrixsdk.error.MatrixErrorType;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.bean.ConnectionState;
import com.htsmart.wristband2.dfu.DfuCallback;
import com.htsmart.wristband2.dfu.DfuManager;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.polidea.rxandroidble2.RxBleDevice;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class MatrixBleService extends Service {
    @Nullable
    public Disposable A;
    @Nullable
    public Disposable B;
    @Nullable
    public Disposable C;
    @Nullable
    public Disposable D;
    @Nullable
    public Disposable E;
    @Nullable
    public Disposable F;
    @Nullable
    public Disposable G;
    @Nullable
    public Disposable H;
    @Nullable
    public Disposable I;
    @Nullable
    public Disposable J;
    @Nullable
    public Disposable K;
    @Nullable
    public Disposable L;
    public DfuManager M;
    @Nullable
    public CloveMatrixBleState.BleState N;
    @NotNull
    public CloveMatrixBleState.BleState O;
    @NotNull
    public final BroadcastReceiver P;
    @NotNull
    public final DfuCallback Q;
    @Nullable
    public BluetoothDevice b;
    @Nullable
    public BluetoothManager e;
    @Nullable
    public BluetoothAdapter f;
    @Nullable
    public WristbandManager h;
    @Nullable
    public ConnectionError i;
    @Nullable
    public MatrixBaseReq k;
    @Nullable
    public Disposable l;
    @Nullable
    public Disposable m;
    public String macAddress;
    @Nullable
    public Disposable n;
    @Nullable
    public Disposable o;
    @Nullable
    public Disposable p;
    @Nullable
    public Disposable q;
    @Nullable
    public Disposable r;
    @Nullable
    public Disposable s;
    public IBinder serviceBinder;
    @Nullable
    public Disposable t;
    @Nullable
    public Disposable u;
    @Nullable
    public Disposable v;
    @Nullable
    public Disposable w;
    @Nullable
    public Disposable x;
    @Nullable
    public Disposable y;
    @Nullable
    public Disposable z;

    /* renamed from: a  reason: collision with root package name */
    public final String f3858a = MatrixBleService.class.getSimpleName();
    @Nullable
    public Handler c = new Handler();
    @Nullable
    public Handler d = new Handler();
    public final long g = 3000;
    public long j = -1;

    /* loaded from: classes2.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final MatrixBleService getService() {
            return MatrixBleService.this;
        }
    }

    public MatrixBleService() {
        CloveMatrixBleState.BleState bleState = CloveMatrixBleState.BleState.DISCONNECTED;
        this.N = bleState;
        setServiceBinder(new BtServiceBinder());
        this.h = WristbandApplication.getWristbandManager();
        e();
        f();
        this.O = bleState;
        this.P = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$mBluetoothStatusReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NotNull Context context, @NotNull Intent intent) {
                String str;
                String str2;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                String action = intent.getAction();
                if (Intrinsics.areEqual(action, "android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == 3 || intExtra == 10) {
                        str = MatrixBleService.this.f3858a;
                        LogHelper.d(str, "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                        MatrixBleService.this.c();
                        MatrixBleService.this.updateConnectionState(CloveMatrixBleState.BleState.DISCONNECTED, true);
                    } else if (intExtra != 12) {
                    } else {
                        str2 = MatrixBleService.this.f3858a;
                        LogHelper.d(str2, "Bluetooth state BluetoothAdapter.STATE_ON", ModuleNames.BLEABSTRACT.getModuleName());
                        if (Intrinsics.areEqual(PreferenceManagerMatrix.getInstance(MatrixBleService.this.getApplicationContext()).getConnectionType(), ConnectionType.RECONNECT_ON_DISCONNECT.name())) {
                            MatrixBleService.this.d();
                        }
                    }
                } else if (kotlin.text.m.equals(action, "action_stop_service", true)) {
                    MatrixBleService.this.updateConnectionState(CloveMatrixBleState.BleState.DISCONNECTED, true);
                }
            }
        };
        this.Q = new DfuCallback() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$dfuCallback$1
            @Override // com.htsmart.wristband2.dfu.DfuCallback
            public void onError(int i, int i2) {
                String str;
                MatrixResponseListener responseListener;
                str = MatrixBleService.this.f3858a;
                LogHelper.d(str, "dfuCallback--onError- --errorType--  " + i + "  --errorCode--  " + i2 + "----");
                MatrixBaseReq khCurrentCommand = MatrixBleService.this.getKhCurrentCommand();
                if (khCurrentCommand == null || (responseListener = khCurrentCommand.getResponseListener()) == null) {
                    return;
                }
                responseListener.onFailure(new MatrixError(MatrixErrorType.COMMAND_REQUEST_ERROR, "COMMAND_REQUEST_ERROR"));
            }

            @Override // com.htsmart.wristband2.dfu.DfuCallback
            public void onProgressChanged(int i) {
                String str;
                str = MatrixBleService.this.f3858a;
                LogHelper.d(str, "dfuCallback onProgressChanged -- " + i);
                if (MatrixBleService.this.getKhCurrentCommand() != null) {
                    if ((MatrixBleService.this.getKhCurrentCommand() instanceof MatrixDIYWatchFaceReq) || (MatrixBleService.this.getKhCurrentCommand() instanceof MatrixCloudWatchFaceReq) || (MatrixBleService.this.getKhCurrentCommand() instanceof MatrixSportsPushReq)) {
                        MatrixBaseRes matrixBaseRes = new MatrixBaseRes();
                        MatrixBaseReq khCurrentCommand = MatrixBleService.this.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand);
                        matrixBaseRes.setBaseReq(khCurrentCommand);
                        LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                        liveWatchFaceUploadPercentage.setPercentage(i);
                        matrixBaseRes.setObj(liveWatchFaceUploadPercentage);
                        MatrixBaseReq khCurrentCommand2 = MatrixBleService.this.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        MatrixResponseListener responseListener = khCurrentCommand2.getResponseListener();
                        Intrinsics.checkNotNull(responseListener);
                        responseListener.onResponse(matrixBaseRes);
                    }
                }
            }

            @Override // com.htsmart.wristband2.dfu.DfuCallback
            public void onStateChanged(int i, boolean z) {
                String str;
                str = MatrixBleService.this.f3858a;
                LogHelper.d(str, "dfuCallback onStateChanged -- " + i);
            }

            @Override // com.htsmart.wristband2.dfu.DfuCallback
            public void onSuccess() {
                String str;
                str = MatrixBleService.this.f3858a;
                LogHelper.d(str, "dfuCallback onSuccess");
                if (MatrixBleService.this.getKhCurrentCommand() != null) {
                    if ((MatrixBleService.this.getKhCurrentCommand() instanceof MatrixDIYWatchFaceReq) || (MatrixBleService.this.getKhCurrentCommand() instanceof MatrixCloudWatchFaceReq) || (MatrixBleService.this.getKhCurrentCommand() instanceof MatrixSportsPushReq)) {
                        MatrixBaseRes matrixBaseRes = new MatrixBaseRes();
                        MatrixBaseReq khCurrentCommand = MatrixBleService.this.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand);
                        matrixBaseRes.setBaseReq(khCurrentCommand);
                        MatrixBaseReq khCurrentCommand2 = MatrixBleService.this.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        MatrixResponseListener responseListener = khCurrentCommand2.getResponseListener();
                        Intrinsics.checkNotNull(responseListener);
                        responseListener.onResponse(matrixBaseRes);
                        MatrixBleService.this.setKhCurrentCommand(null);
                    }
                }
            }
        };
    }

    public static final void A(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void C(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void D(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void G(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void H(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void a(com.htsmart.wristband2.bean.ConnectionError connectionError) {
    }

    public static final void a(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void c(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final CompletableSource f(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return (CompletableSource) tmp0.invoke(obj);
    }

    public static final void h(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void i(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void j(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void k(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void l(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void m(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void n(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void o(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void p(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void r(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void s(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void t(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void u(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void v(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void w(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void x(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void b() {
        Notification build;
        try {
            NotificationInfo data = BleApiUtils.getData();
            if (data.getAppColor() == 0) {
                BleApiManager.getInstance(this);
                data = BleApiUtils.getData();
                if (data.getAppColor() == 0) {
                    BleApiUtils.getMetadata(this);
                    data = BleApiUtils.getData();
                }
            }
            PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(data.getAppLauncherActivity()), 67108864);
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                Object systemService = getSystemService("notification");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                NotificationChannel notificationChannel = new NotificationChannel("101", data.getAppDesc(), 2);
                notificationChannel.enableLights(false);
                ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
                build = new Notification.Builder(this, "101").setContentTitle(data.getAppDesc()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
                Intrinsics.checkNotNullExpressionValue(build, "{\n                val no…   .build()\n            }");
            } else {
                if (i >= 21) {
                    build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
                } else {
                    build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
                }
                Intrinsics.checkNotNullExpressionValue(build, "{\n                if (Bu…          }\n            }");
            }
            startForeground(101, build);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this);
        }
    }

    public final void connect(@NotNull String deviceAddress) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        BleDeviceInfo.clearInstance();
        PreferenceManagerMatrix.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(deviceAddress);
        PreferenceManagerMatrix.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String connectedDeviceMacAddress = PreferenceManagerMatrix.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
        setMacAddress(connectedDeviceMacAddress);
        String str = this.f3858a;
        LogHelper.i(str, "connection type ++ " + BleUtils.getConnectionType(this));
        d();
    }

    public final void d() {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter != null) {
            Intrinsics.checkNotNull(bluetoothAdapter);
            if (bluetoothAdapter.isEnabled()) {
                String str = this.f3858a;
                LogHelper.i(str, "Obtained device Address is " + getMacAddress());
                try {
                    WristbandManager wristbandManager = this.h;
                    if (wristbandManager != null) {
                        Intrinsics.checkNotNull(wristbandManager);
                        if (wristbandManager.isConnected()) {
                            updateConnectionState(CloveMatrixBleState.BleState.CONNECTED, true);
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                WristbandManager wristbandManager2 = this.h;
                if (wristbandManager2 != null) {
                    if (Boolean.valueOf(wristbandManager2.isBindOrLogin()) != null) {
                        String str2 = this.f3858a;
                        StringBuilder sb = new StringBuilder();
                        sb.append("isBindOrLogin -- ");
                        WristbandManager wristbandManager3 = this.h;
                        Boolean valueOf = wristbandManager3 != null ? Boolean.valueOf(wristbandManager3.isBindOrLogin()) : null;
                        Intrinsics.checkNotNull(valueOf);
                        sb.append(valueOf.booleanValue());
                        LogHelper.i(str2, sb.toString());
                    } else {
                        LogHelper.i(this.f3858a, "isBindOrLogin -- null");
                    }
                    WristbandManager wristbandManager4 = this.h;
                    if ((wristbandManager4 != null ? wristbandManager4.getRxBleDevice() : null) != null) {
                        String str3 = this.f3858a;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("rxBleDevice -- ");
                        WristbandManager wristbandManager5 = this.h;
                        RxBleDevice rxBleDevice = wristbandManager5 != null ? wristbandManager5.getRxBleDevice() : null;
                        Intrinsics.checkNotNull(rxBleDevice);
                        sb2.append(rxBleDevice);
                        LogHelper.i(str3, sb2.toString());
                    } else {
                        LogHelper.i(this.f3858a, "rxBleDevice -- null ");
                    }
                    Boolean isBind = PreferenceManagerMatrix.getInstance(this).isBindOrLogin();
                    String str4 = this.f3858a;
                    LogHelper.i(str4, "isBind -- " + isBind);
                    int age = PreferenceManagerAbstract.getInstance(this).getUserDetails().getAge();
                    int gender = PreferenceManagerAbstract.getInstance(this).getUserDetails().getGender();
                    int height = PreferenceManagerAbstract.getInstance(this).getUserDetails().getHeight();
                    double weight = PreferenceManagerAbstract.getInstance(this).getUserDetails().getWeight();
                    String replace = new Regex(":").replace(getMacAddress(), "");
                    boolean z = gender == 1;
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        WristbandManager wristbandManager6 = this.h;
                        Intrinsics.checkNotNull(wristbandManager6);
                        String macAddress = getMacAddress();
                        Intrinsics.checkNotNullExpressionValue(isBind, "isBind");
                        wristbandManager6.connect(macAddress, replace, isBind.booleanValue(), z, age, height, (float) weight);
                    }
                }
            }
        }
    }

    public void disconnectAndForget() {
        LogHelper.i(this.f3858a, "disconnectAndForget called");
        PreferenceManagerMatrix.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManagerMatrix.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        c();
        updateConnectionState(CloveMatrixBleState.BleState.DISCONNECTED, false);
        unregisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    public void disconnectAndRetainMacAddress() {
        PreferenceManagerMatrix.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        c();
        unregisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    public final void e() {
        WristbandManager wristbandManager = this.h;
        if (wristbandManager != null) {
            Intrinsics.checkNotNull(wristbandManager);
            Observable<ConnectionState> observerConnectionState = wristbandManager.observerConnectionState();
            final MatrixBleService$observeConnectionStatus$1$1 matrixBleService$observeConnectionStatus$1$1 = new MatrixBleService$observeConnectionStatus$1$1(this);
            Consumer<? super ConnectionState> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.h2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MatrixBleService.a(Function1.this, obj);
                }
            };
            final Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$observeConnectionStatus$1$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    String str;
                    str = MatrixBleService.this.f3858a;
                    LogHelper.i(str, "Error while observerConnectionState");
                    return Unit.INSTANCE;
                }
            };
            this.m = observerConnectionState.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.i2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MatrixBleService.b(Function1.this, obj);
                }
            });
            WristbandManager wristbandManager2 = this.h;
            Intrinsics.checkNotNull(wristbandManager2);
            Observable<com.htsmart.wristband2.bean.ConnectionError> observerConnectionError = wristbandManager2.observerConnectionError();
            i3 i3Var = new Consumer() { // from class: com.coveiot.android.bleabstract.services.i3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MatrixBleService.a((com.htsmart.wristband2.bean.ConnectionError) obj);
                }
            };
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$observeConnectionStatus$1$4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    String str;
                    str = MatrixBleService.this.f3858a;
                    LogHelper.i(str, "Error while catching observerConnectionError");
                    return Unit.INSTANCE;
                }
            };
            this.n = observerConnectionError.subscribe(i3Var, new Consumer() { // from class: com.coveiot.android.bleabstract.services.j2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MatrixBleService.c(Function1.this, obj);
                }
            });
        }
    }

    public final void g() {
        MatrixBaseRes matrixBaseRes = new MatrixBaseRes();
        MatrixBaseReq matrixBaseReq = this.k;
        Intrinsics.checkNotNull(matrixBaseReq);
        matrixBaseRes.setBaseReq(matrixBaseReq);
        MatrixBaseReq matrixBaseReq2 = this.k;
        Intrinsics.checkNotNull(matrixBaseReq2);
        MatrixResponseListener responseListener = matrixBaseReq2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(matrixBaseRes);
        String str = this.f3858a;
        LogHelper.d(str, "sendCommandReply->Set khCurrentCommand to null->" + this.k);
        this.k = null;
    }

    @Nullable
    public final ConnectionError getConnectionError() {
        return this.i;
    }

    public final long getConnectionStageChangeTimeStamp() {
        return this.j;
    }

    @Nullable
    public final CloveMatrixBleState.BleState getConnectionState() {
        return this.N;
    }

    @Nullable
    public final MatrixBaseReq getKhCurrentCommand() {
        return this.k;
    }

    @Nullable
    public final BluetoothDevice getMBluetoothDevice() {
        return this.b;
    }

    @NotNull
    public final String getMacAddress() {
        String str = this.macAddress;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DeviceKey.MacAddress);
        return null;
    }

    @NotNull
    public final IBinder getServiceBinder() {
        IBinder iBinder = this.serviceBinder;
        if (iBinder != null) {
            return iBinder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("serviceBinder");
        return null;
    }

    @NotNull
    public final CloveMatrixBleState.BleState getState() {
        return this.O;
    }

    @Override // android.app.Service
    @NotNull
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return getServiceBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        b();
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothManager bluetoothManager = (BluetoothManager) systemService;
        this.e = bluetoothManager;
        Intrinsics.checkNotNull(bluetoothManager);
        this.f = bluetoothManager.getAdapter();
        DfuManager dfuManager = new DfuManager(this);
        this.M = dfuManager;
        dfuManager.setDfuCallback(this.Q);
        DfuManager dfuManager2 = this.M;
        if (dfuManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dfuManager");
            dfuManager2 = null;
        }
        dfuManager2.init();
    }

    @Override // android.app.Service
    public void onDestroy() {
        Disposable disposable = this.l;
        if (disposable != null) {
            Intrinsics.checkNotNull(disposable);
            if (!disposable.isDisposed()) {
                Disposable disposable2 = this.l;
                Intrinsics.checkNotNull(disposable2);
                disposable2.dispose();
            }
        }
        Disposable disposable3 = this.m;
        if (disposable3 != null) {
            Intrinsics.checkNotNull(disposable3);
            if (!disposable3.isDisposed()) {
                Disposable disposable4 = this.m;
                Intrinsics.checkNotNull(disposable4);
                disposable4.dispose();
            }
        }
        Disposable disposable5 = this.n;
        if (disposable5 != null) {
            Intrinsics.checkNotNull(disposable5);
            if (!disposable5.isDisposed()) {
                Disposable disposable6 = this.n;
                Intrinsics.checkNotNull(disposable6);
                disposable6.dispose();
            }
        }
        Disposable disposable7 = this.o;
        if (disposable7 != null) {
            Intrinsics.checkNotNull(disposable7);
            if (!disposable7.isDisposed()) {
                Disposable disposable8 = this.o;
                Intrinsics.checkNotNull(disposable8);
                disposable8.dispose();
            }
        }
        Disposable disposable9 = this.p;
        if (disposable9 != null) {
            Intrinsics.checkNotNull(disposable9);
            if (!disposable9.isDisposed()) {
                Disposable disposable10 = this.p;
                Intrinsics.checkNotNull(disposable10);
                disposable10.dispose();
            }
        }
        Disposable disposable11 = this.q;
        if (disposable11 != null) {
            Intrinsics.checkNotNull(disposable11);
            if (!disposable11.isDisposed()) {
                Disposable disposable12 = this.q;
                Intrinsics.checkNotNull(disposable12);
                disposable12.dispose();
            }
        }
        Disposable disposable13 = this.r;
        if (disposable13 != null) {
            Intrinsics.checkNotNull(disposable13);
            if (!disposable13.isDisposed()) {
                Disposable disposable14 = this.r;
                Intrinsics.checkNotNull(disposable14);
                disposable14.dispose();
            }
        }
        Disposable disposable15 = this.s;
        if (disposable15 != null) {
            Intrinsics.checkNotNull(disposable15);
            if (!disposable15.isDisposed()) {
                Disposable disposable16 = this.s;
                Intrinsics.checkNotNull(disposable16);
                disposable16.dispose();
            }
        }
        Disposable disposable17 = this.t;
        if (disposable17 != null) {
            Intrinsics.checkNotNull(disposable17);
            if (!disposable17.isDisposed()) {
                Disposable disposable18 = this.t;
                Intrinsics.checkNotNull(disposable18);
                disposable18.dispose();
            }
        }
        Disposable disposable19 = this.w;
        if (disposable19 != null) {
            Intrinsics.checkNotNull(disposable19);
            if (!disposable19.isDisposed()) {
                Disposable disposable20 = this.w;
                Intrinsics.checkNotNull(disposable20);
                disposable20.dispose();
            }
        }
        Disposable disposable21 = this.x;
        if (disposable21 != null) {
            Intrinsics.checkNotNull(disposable21);
            if (!disposable21.isDisposed()) {
                Disposable disposable22 = this.x;
                Intrinsics.checkNotNull(disposable22);
                disposable22.dispose();
            }
        }
        Disposable disposable23 = this.y;
        if (disposable23 != null) {
            Intrinsics.checkNotNull(disposable23);
            if (!disposable23.isDisposed()) {
                Disposable disposable24 = this.y;
                Intrinsics.checkNotNull(disposable24);
                disposable24.dispose();
            }
        }
        Disposable disposable25 = this.u;
        if (disposable25 != null) {
            Intrinsics.checkNotNull(disposable25);
            if (!disposable25.isDisposed()) {
                Disposable disposable26 = this.u;
                Intrinsics.checkNotNull(disposable26);
                disposable26.dispose();
            }
        }
        Disposable disposable27 = this.v;
        if (disposable27 != null) {
            Intrinsics.checkNotNull(disposable27);
            if (!disposable27.isDisposed()) {
                Disposable disposable28 = this.v;
                Intrinsics.checkNotNull(disposable28);
                disposable28.dispose();
            }
        }
        Disposable disposable29 = this.z;
        if (disposable29 != null) {
            Intrinsics.checkNotNull(disposable29);
            if (!disposable29.isDisposed()) {
                Disposable disposable30 = this.z;
                Intrinsics.checkNotNull(disposable30);
                disposable30.dispose();
            }
        }
        Disposable disposable31 = this.A;
        if (disposable31 != null) {
            Intrinsics.checkNotNull(disposable31);
            if (!disposable31.isDisposed()) {
                Disposable disposable32 = this.A;
                Intrinsics.checkNotNull(disposable32);
                disposable32.dispose();
            }
        }
        Disposable disposable33 = this.D;
        if (disposable33 != null) {
            Intrinsics.checkNotNull(disposable33);
            if (!disposable33.isDisposed()) {
                Disposable disposable34 = this.D;
                Intrinsics.checkNotNull(disposable34);
                disposable34.dispose();
            }
        }
        Disposable disposable35 = this.E;
        if (disposable35 != null) {
            Intrinsics.checkNotNull(disposable35);
            if (!disposable35.isDisposed()) {
                Disposable disposable36 = this.E;
                Intrinsics.checkNotNull(disposable36);
                disposable36.dispose();
            }
        }
        Disposable disposable37 = this.F;
        if (disposable37 != null) {
            Intrinsics.checkNotNull(disposable37);
            if (!disposable37.isDisposed()) {
                Disposable disposable38 = this.F;
                Intrinsics.checkNotNull(disposable38);
                disposable38.dispose();
            }
        }
        Disposable disposable39 = this.B;
        if (disposable39 != null) {
            Intrinsics.checkNotNull(disposable39);
            if (!disposable39.isDisposed()) {
                Disposable disposable40 = this.B;
                Intrinsics.checkNotNull(disposable40);
                disposable40.dispose();
            }
        }
        Disposable disposable41 = this.C;
        if (disposable41 != null) {
            Intrinsics.checkNotNull(disposable41);
            if (!disposable41.isDisposed()) {
                Disposable disposable42 = this.C;
                Intrinsics.checkNotNull(disposable42);
                disposable42.dispose();
            }
        }
        Disposable disposable43 = this.G;
        if (disposable43 != null) {
            Intrinsics.checkNotNull(disposable43);
            if (!disposable43.isDisposed()) {
                Disposable disposable44 = this.G;
                Intrinsics.checkNotNull(disposable44);
                disposable44.dispose();
            }
        }
        Disposable disposable45 = this.H;
        if (disposable45 != null) {
            Intrinsics.checkNotNull(disposable45);
            if (!disposable45.isDisposed()) {
                Disposable disposable46 = this.H;
                Intrinsics.checkNotNull(disposable46);
                disposable46.dispose();
            }
        }
        Disposable disposable47 = this.I;
        if (disposable47 != null) {
            Intrinsics.checkNotNull(disposable47);
            if (!disposable47.isDisposed()) {
                Disposable disposable48 = this.I;
                Intrinsics.checkNotNull(disposable48);
                disposable48.dispose();
            }
        }
        Disposable disposable49 = this.J;
        if (disposable49 != null) {
            Intrinsics.checkNotNull(disposable49);
            if (!disposable49.isDisposed()) {
                Disposable disposable50 = this.J;
                Intrinsics.checkNotNull(disposable50);
                disposable50.dispose();
            }
        }
        Disposable disposable51 = this.K;
        if (disposable51 != null) {
            Intrinsics.checkNotNull(disposable51);
            if (!disposable51.isDisposed()) {
                Disposable disposable52 = this.K;
                Intrinsics.checkNotNull(disposable52);
                disposable52.dispose();
            }
        }
        Disposable disposable53 = this.L;
        if (disposable53 != null) {
            Intrinsics.checkNotNull(disposable53);
            if (!disposable53.isDisposed()) {
                Disposable disposable54 = this.L;
                Intrinsics.checkNotNull(disposable54);
                disposable54.dispose();
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, MatrixBleService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent2);
                } else {
                    startService(intent2);
                }
                return 3;
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, this);
            }
        }
        b();
        String connectedDeviceMacAddress = PreferenceManagerMatrix.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
        setMacAddress(connectedDeviceMacAddress);
        BleUtils.getConnectionType(getApplicationContext());
        registerReceivers();
        if (!BleUtils.isEmpty(getMacAddress())) {
            d();
        }
        return 3;
    }

    @Override // android.app.Service
    public boolean onUnbind(@Nullable Intent intent) {
        return super.onUnbind(intent);
    }

    public void registerReceivers() {
        try {
            BleEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getApplicationContext().registerReceiver(this.P, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            getApplicationContext().registerReceiver(this.P, new IntentFilter("action_stop_service"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void restartService() {
        PreferenceManagerMatrix.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        c();
        a();
        unregisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00f5, code lost:
        r14.setObj(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0177, code lost:
        r14.setObj(com.coveiot.android.bleabstract.formatter.matrix.MatrixHeartRateFormatter.Companion.getInstance(r13).convertData(r15));
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x020a, code lost:
        r0 = com.coveiot.android.bleabstract.formatter.matrix.MatrixSportDataFormatter.Companion;
        r14.setObj(r0.getInstance(r13).convertSportData(r15));
        r15 = r0.getInstance(r13).convertSportData(r15);
        com.coveiot.utils.utility.LogHelper.e(r13.f3858a, "SportsData: " + r15);
     */
    /* JADX WARN: Removed duplicated region for block: B:214:0x00c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0099 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendRequest(@org.jetbrains.annotations.NotNull com.coveiot.android.matrixsdk.api.MatrixBaseReq r14, @org.jetbrains.annotations.NotNull com.coveiot.android.matrixsdk.MatrixResponseListener r15) {
        /*
            Method dump skipped, instructions count: 2255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.MatrixBleService.sendRequest(com.coveiot.android.matrixsdk.api.MatrixBaseReq, com.coveiot.android.matrixsdk.MatrixResponseListener):void");
    }

    public final void setKhCurrentCommand(@Nullable MatrixBaseReq matrixBaseReq) {
        this.k = matrixBaseReq;
    }

    public final void setMBluetoothDevice(@Nullable BluetoothDevice bluetoothDevice) {
        this.b = bluetoothDevice;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }

    public final void setServiceBinder(@NotNull IBinder iBinder) {
        Intrinsics.checkNotNullParameter(iBinder, "<set-?>");
        this.serviceBinder = iBinder;
    }

    public final void setState(@NotNull CloveMatrixBleState.BleState bleState) {
        Intrinsics.checkNotNullParameter(bleState, "<set-?>");
        this.O = bleState;
    }

    public void stopService() {
        disconnectAndForget();
    }

    public void stopServiceRetainMacAddress() {
        disconnectAndRetainMacAddress();
    }

    public void unregisterReceivers() {
        if (this.P != null) {
            try {
                getApplicationContext().unregisterReceiver(this.P);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                BleEventBusManager.getInstance().getEventBus().unregister(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void updateConnectionState(@NotNull final CloveMatrixBleState.BleState state, boolean z) {
        Handler handler;
        Intrinsics.checkNotNullParameter(state, "state");
        this.N = state;
        if (!z || (handler = this.c) == null) {
            return;
        }
        Intrinsics.checkNotNull(handler);
        handler.removeCallbacksAndMessages(null);
        Handler handler2 = this.c;
        Intrinsics.checkNotNull(handler2);
        handler2.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.k3
            @Override // java.lang.Runnable
            public final void run() {
                MatrixBleService.a(CloveMatrixBleState.BleState.this);
            }
        });
    }

    public static final void a(CloveMatrixBleState.BleState state) {
        Intrinsics.checkNotNullParameter(state, "$state");
        BleEventBusManager.getInstance().getEventBus().post(new CloveMatrixBleState(state));
    }

    public static final void f(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixHourFormatReq success");
        this$0.g();
    }

    public static final void h(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixTempUnitReq success");
        this$0.g();
    }

    public static final void i(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixNotificationReq success");
        this$0.g();
    }

    public static final void j(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixFindBandReq success");
        this$0.g();
    }

    public static final void k(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixWristWearReq success");
        this$0.g();
    }

    public static final void l(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixDNDReq success");
        this$0.g();
    }

    public static final void m(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixSetSportGoalReq success");
        this$0.g();
    }

    public static final void n(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixUserInfoReq success");
        this$0.g();
    }

    public static final void o(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixLiftWristReq success");
        this$0.g();
    }

    public static final void p(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixDrinkReminderReq success");
        this$0.g();
    }

    public static final void q(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixWomenWellnessReq success");
        this$0.g();
    }

    public static final void r(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixWeatherReq success");
        this$0.g();
    }

    public static final void s(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixContactReq settingContact success");
        this$0.g();
    }

    public static final void t(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixNotificationConfigReq success");
        this$0.g();
    }

    public static final void u(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixCameraRequest success");
        this$0.g();
    }

    public static final void v(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixDIYComponentReq success");
        this$0.g();
    }

    public static final void w(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixSetQRCodeReq success");
        this$0.g();
    }

    public final void c() {
        WristbandManager wristbandManager = this.h;
        if (wristbandManager != null) {
            Intrinsics.checkNotNull(wristbandManager);
            if (wristbandManager.isConnected()) {
                WristbandManager wristbandManager2 = this.h;
                Intrinsics.checkNotNull(wristbandManager2);
                wristbandManager2.close();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.coveiot.android.bleabstract.response.LiveMusicControlRes] */
    public final void f() {
        if (this.h != null) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new LiveMusicControlRes(MusicControlState.UNKNOWN);
            final Intent intent = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
            WristbandManager wristbandManager = this.h;
            Intrinsics.checkNotNull(wristbandManager);
            Observable<Integer> observeOn = wristbandManager.observerWristbandMessage().observeOn(AndroidSchedulers.mainThread());
            final Function1<Integer, Unit> function1 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$observeWristbandMessage$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Type inference failed for: r0v10, types: [T, com.coveiot.android.bleabstract.response.LiveMusicControlRes] */
                /* JADX WARN: Type inference failed for: r0v15, types: [T, com.coveiot.android.bleabstract.response.LiveMusicControlRes] */
                /* JADX WARN: Type inference failed for: r0v20, types: [T, com.coveiot.android.bleabstract.response.LiveMusicControlRes] */
                /* JADX WARN: Type inference failed for: r0v25, types: [T, com.coveiot.android.bleabstract.response.LiveMusicControlRes] */
                /* JADX WARN: Type inference failed for: r0v30, types: [T, com.coveiot.android.bleabstract.response.LiveMusicControlRes] */
                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Integer num) {
                    Integer num2 = num;
                    LogHelper.d("WristbandMessage", "-----" + num2);
                    if (num2 != null && num2.intValue() == 1) {
                        FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                        Intent intent2 = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                        intent2.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent2);
                    } else if (num2 != null && num2.intValue() == 12) {
                        objectRef.element = new LiveMusicControlRes(MusicControlState.NEXT);
                        intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, objectRef.element);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent);
                    } else if (num2 != null && num2.intValue() == 11) {
                        objectRef.element = new LiveMusicControlRes(MusicControlState.TOGGLE);
                        intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, objectRef.element);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent);
                    } else if (num2 != null && num2.intValue() == 13) {
                        objectRef.element = new LiveMusicControlRes(MusicControlState.PREVIOUS);
                        intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, objectRef.element);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent);
                    } else if (num2 != null && num2.intValue() == 15) {
                        objectRef.element = new LiveMusicControlRes(MusicControlState.VOLUME_DOWN);
                        intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, objectRef.element);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent);
                    } else if (num2 != null && num2.intValue() == 14) {
                        objectRef.element = new LiveMusicControlRes(MusicControlState.VOLUME_UP);
                        intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, objectRef.element);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent);
                    } else if (num2 != null && num2.intValue() == 3) {
                        CameraEventRes cameraEventRes = new CameraEventRes(CameraState.CAPTURE);
                        Intent intent3 = new Intent(Constants.CAMERA_BROADCAST_INTENT);
                        intent3.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, cameraEventRes);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent3);
                    } else if (num2 != null && num2.intValue() == 4) {
                        LogHelper.i("WristbandMessage", "--MSG_CAMERA_WAKE_UP---" + num2);
                    } else if (num2 != null && num2.intValue() == 5) {
                        LogHelper.i("WristbandMessage", "--MSG_CAMERA_EXIT---" + num2);
                        CameraEventRes cameraEventRes2 = new CameraEventRes(CameraState.EXIT);
                        Intent intent4 = new Intent(Constants.CAMERA_BROADCAST_INTENT);
                        intent4.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, cameraEventRes2);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent4);
                    } else if (num2 != null && num2.intValue() == 2) {
                        LogHelper.i("WristbandMessage", "--MSG_HUNG_UP_PHONE---" + num2);
                        CallRejectRes callRejectRes = new CallRejectRes(true);
                        callRejectRes.shouldReject = true;
                        Intent intent5 = new Intent(Constants.CALL_REJECT_BROADCAST_INTENT);
                        intent5.putExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, callRejectRes);
                        LocalBroadcastManager.getInstance(MatrixBleService.this).sendBroadcast(intent5);
                    }
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super Integer> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.k2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MatrixBleService.d(Function1.this, obj);
                }
            };
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.MatrixBleService$observeWristbandMessage$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    String str;
                    str = MatrixBleService.this.f3858a;
                    LogHelper.d(str, "Error while observing observerWristbandMessage");
                    return Unit.INSTANCE;
                }
            };
            this.l = observeOn.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.l2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MatrixBleService.e(Function1.this, obj);
                }
            });
        }
    }

    public static final void c(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixVibrationAlarmReq success");
        this$0.g();
    }

    public final void a() {
        LogHelper.i(this.f3858a, "closeGatt called");
        WristbandManager wristbandManager = this.h;
        if (wristbandManager != null) {
            Intrinsics.checkNotNull(wristbandManager);
            if (wristbandManager.isConnected()) {
                WristbandManager wristbandManager2 = this.h;
                Intrinsics.checkNotNull(wristbandManager2);
                wristbandManager2.close();
            }
        }
    }

    public static final void g(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void g(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixLengthUnitReq success");
        this$0.g();
    }

    public static final void a(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.e(this$0.f3858a, "Sync doOnComplete Data Success");
        try {
            if (this$0.k != null) {
                MatrixBaseRes matrixBaseRes = new MatrixBaseRes();
                MatrixBaseReq matrixBaseReq = this$0.k;
                Intrinsics.checkNotNull(matrixBaseReq);
                matrixBaseRes.setBaseReq(matrixBaseReq);
                matrixBaseRes.setObj(MatrixStepsFormatter.Companion.getInstance(this$0).convertData(KhMatrixStepsRepository.Companion.getInstance(this$0).getAllUnProcessedStepsData(this$0.getMacAddress())));
                MatrixBaseReq matrixBaseReq2 = this$0.k;
                Intrinsics.checkNotNull(matrixBaseReq2);
                MatrixResponseListener responseListener = matrixBaseReq2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(matrixBaseRes);
                this$0.k = null;
            } else {
                LogsHelper.d(this$0.f3858a, "khCurrent command is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void e(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixHealthReminderReq success");
        this$0.g();
    }

    public static final void e(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void d(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "MatrixSedentaryReminderReq success");
        this$0.g();
    }

    public static final void d(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void b(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void b(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3858a, "Sync Data Success");
    }
}
