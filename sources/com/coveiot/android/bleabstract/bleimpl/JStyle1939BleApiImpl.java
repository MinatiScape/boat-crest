package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
/* loaded from: classes2.dex */
public class JStyle1939BleApiImpl extends JStyleCommonBleApiImpl {
    public static JStyle1939BleApiImpl m;
    public static final String n = JStyle1790BleApiImpl.class.getSimpleName();

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1939BleApiImpl$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3064a;

        static {
            int[] iArr = new int[CloveBleState.BleState.values().length];
            f3064a = iArr;
            try {
                iArr[CloveBleState.BleState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3064a[CloveBleState.BleState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3064a[CloveBleState.BleState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public JStyle1939BleApiImpl() {
        super.registerEvenBus();
    }

    public static JStyle1939BleApiImpl getInstance(Context context) {
        if (m == null) {
            JStyleCommonBleApiImpl.context = context.getApplicationContext();
            m = new JStyle1939BleApiImpl();
        }
        if (!JStyleCommonBleApiImpl.checkIfServiceIsRunning()) {
            LogHelper.d(n, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
            JStyleCommonBleApiImpl.startBleService();
        }
        return m;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl
    public void clearParameters() {
        super.clearParameters();
        m = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @com.squareup.otto.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onConnectionStateChanged(com.coveiot.sdk.ble.CloveBleState r3) {
        /*
            r2 = this;
            com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
            if (r3 == 0) goto L1d
            int[] r1 = com.coveiot.android.bleabstract.bleimpl.JStyle1939BleApiImpl.AnonymousClass1.f3064a
            com.coveiot.sdk.ble.CloveBleState$BleState r3 = r3.getmState()
            int r3 = r3.ordinal()
            r3 = r1[r3]
            r1 = 1
            if (r3 == r1) goto L1a
            r1 = 2
            if (r3 == r1) goto L17
            goto L1d
        L17:
            com.coveiot.android.bleabstract.models.ConnectionStatus r3 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTING
            goto L1e
        L1a:
            com.coveiot.android.bleabstract.models.ConnectionStatus r3 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTED
            goto L1e
        L1d:
            r3 = r0
        L1e:
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            if (r1 == 0) goto L2a
            r1.setValue(r3)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            r1.postValue(r3)
        L2a:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r1 = r2.h
            if (r1 == 0) goto L31
            r1.onConnectionResponse(r3)
        L31:
            if (r3 != r0) goto L3f
            com.coveiot.android.jstylesdk.error.JstyleError r3 = new com.coveiot.android.jstylesdk.error.JstyleError
            com.coveiot.android.jstylesdk.error.JstyleErrorType r0 = com.coveiot.android.jstylesdk.error.JstyleErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r1 = "Device disconnected"
            r3.<init>(r0, r1)
            r2.sendErrorAndClearQueue(r3)
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1939BleApiImpl.onConnectionStateChanged(com.coveiot.sdk.ble.CloveBleState):void");
    }
}
