package com.coveiot.android.bleabstract.services;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.CloveMatrixBleState;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.coveiot.android.matrixsdk.api.MatrixBaseReq;
import com.coveiot.android.matrixsdk.api.MatrixCloudWatchFaceReq;
import com.coveiot.android.matrixsdk.api.MatrixDIYWatchFaceReq;
import com.coveiot.android.matrixsdk.api.MatrixSportsPushReq;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.bean.ConnectionState;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes2.dex */
public final class MatrixBleService$observeConnectionStatus$1$1 extends Lambda implements Function1<ConnectionState, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MatrixBleService f3862a;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionState.values().length];
            try {
                iArr[ConnectionState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConnectionState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConnectionState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatrixBleService$observeConnectionStatus$1$1(MatrixBleService matrixBleService) {
        super(1);
        this.f3862a = matrixBleService;
    }

    public final void a(ConnectionState connectionState) {
        WristbandManager wristbandManager;
        String str;
        String str2;
        WristbandManager wristbandManager2;
        String str3;
        String str4;
        String str5;
        BluetoothAdapter bluetoothAdapter;
        Handler handler;
        Handler handler2;
        long j;
        MatrixBleService matrixBleService = this.f3862a;
        CloveMatrixBleState.BleState bleState = CloveMatrixBleState.BleState.DISCONNECTED;
        matrixBleService.setState(bleState);
        this.f3862a.j = System.currentTimeMillis();
        int i = connectionState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[connectionState.ordinal()];
        if (i == 1) {
            this.f3862a.i = null;
            this.f3862a.setState(CloveMatrixBleState.BleState.CONNECTED);
            if (BleApiManager.getInstance(this.f3862a) != null && BleApiManager.getInstance(this.f3862a).getDeviceType() != null && BleApiManager.getInstance(this.f3862a).getDeviceType() != DeviceType.matrix) {
                PreferenceManagerMatrix.getInstance(this.f3862a).saveBindOrLogin(false);
            }
            wristbandManager = this.f3862a.h;
            Intrinsics.checkNotNull(wristbandManager);
            if (wristbandManager.isBindOrLogin()) {
                str2 = this.f3862a.f3858a;
                LogHelper.i(str2, "observeConnectionStatus connect with bind mode");
                if (BleApiManager.getInstance(this.f3862a) != null && BleApiManager.getInstance(this.f3862a).getDeviceType() != null && BleApiManager.getInstance(this.f3862a).getDeviceType() == DeviceType.matrix) {
                    PreferenceManagerMatrix.getInstance(this.f3862a).saveBindOrLogin(true);
                }
            } else {
                str = this.f3862a.f3858a;
                LogHelper.e(str, "observeConnectionStatus Connect with Login Mode");
            }
            PreferenceManagerMatrix.getInstance(this.f3862a.getApplicationContext()).saveConnectedDeviceMAcAddress(this.f3862a.getMacAddress());
        } else if (i == 2) {
            this.f3862a.setState(CloveMatrixBleState.BleState.CONNECTING);
        } else if (i == 3) {
            this.f3862a.setState(bleState);
            this.f3862a.a();
            if (Intrinsics.areEqual(PreferenceManagerMatrix.getInstance(this.f3862a.getApplicationContext()).getConnectionType(), ConnectionType.RECONNECT_ON_DISCONNECT.name())) {
                bluetoothAdapter = this.f3862a.f;
                Intrinsics.checkNotNull(bluetoothAdapter);
                if (bluetoothAdapter.isEnabled()) {
                    handler = this.f3862a.d;
                    if (handler != null) {
                        handler.removeCallbacksAndMessages(null);
                    }
                    handler2 = this.f3862a.d;
                    if (handler2 != null) {
                        final MatrixBleService matrixBleService2 = this.f3862a;
                        Runnable runnable = new Runnable() { // from class: com.coveiot.android.bleabstract.services.q3
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixBleService$observeConnectionStatus$1$1.a(MatrixBleService.this);
                            }
                        };
                        j = this.f3862a.g;
                        handler2.postDelayed(runnable, j);
                    }
                }
            }
            wristbandManager2 = this.f3862a.h;
            Intrinsics.checkNotNull(wristbandManager2);
            if (wristbandManager2.getRxBleDevice() == null) {
                str5 = this.f3862a.f3858a;
                LogHelper.e(str5, "observeConnectionStatus Active disconnect");
                MatrixBaseReq khCurrentCommand = this.f3862a.getKhCurrentCommand();
                if (!(khCurrentCommand instanceof MatrixSportsPushReq ? true : khCurrentCommand instanceof MatrixDIYWatchFaceReq ? true : khCurrentCommand instanceof MatrixCloudWatchFaceReq)) {
                    PreferenceManagerMatrix.getInstance(this.f3862a).saveBindOrLogin(true);
                }
            } else if (connectionState == ConnectionState.CONNECTED) {
                str4 = this.f3862a.f3858a;
                LogHelper.e(str4, "observeConnectionStatus Passive disconnect");
            } else {
                str3 = this.f3862a.f3858a;
                LogHelper.e(str3, "observeConnectionStatus Connect Failed");
            }
        }
        MatrixBleService matrixBleService3 = this.f3862a;
        matrixBleService3.updateConnectionState(matrixBleService3.getState(), true);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ConnectionState connectionState) {
        a(connectionState);
        return Unit.INSTANCE;
    }

    public static final void a(MatrixBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d();
    }
}
