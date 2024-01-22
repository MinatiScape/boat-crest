package com.coveiot.android.crpsdk;

import android.content.Context;
import android.text.TextUtils;
import com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.CRPBleClient;
import com.crrepa.ble.conn.CRPBleConnection;
import com.crrepa.ble.conn.CRPBleDevice;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public class BleConnectHelper {

    /* renamed from: a  reason: collision with root package name */
    public String f4101a;
    public CRPBleClient b;
    public CRPBleDevice c;
    public CRPBleConnection d;
    public f e = new f(this);
    public boolean f = false;
    public boolean g = false;
    public boolean h = false;
    public boolean i = true;

    /* loaded from: classes3.dex */
    public class a implements Consumer<String> {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0059 A[Catch: all -> 0x00cc, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000f, B:17:0x0059, B:19:0x0075, B:22:0x009d, B:24:0x00a1, B:25:0x00b9, B:21:0x007f, B:7:0x0017, B:9:0x001d, B:10:0x0036, B:12:0x003c), top: B:31:0x0005 }] */
        @Override // io.reactivex.rxjava3.functions.Consumer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void accept(java.lang.String r7) throws java.lang.Throwable {
            /*
                r6 = this;
                java.lang.String r7 = (java.lang.String) r7
                com.coveiot.android.crpsdk.BleConnectHelper r0 = com.coveiot.android.crpsdk.BleConnectHelper.this
                monitor-enter(r0)
                com.crrepa.ble.CRPBleClient r1 = r0.b     // Catch: java.lang.Throwable -> Lcc
                boolean r1 = r1.isBluetoothEnable()     // Catch: java.lang.Throwable -> Lcc
                r2 = 0
                r3 = 1
                if (r1 != 0) goto L17
                java.lang.String r1 = "BleConnectHelper"
                java.lang.String r4 = "Bluetooth disable"
                com.coveiot.utils.utility.LogHelper.d(r1, r4)     // Catch: java.lang.Throwable -> Lcc
                goto L53
            L17:
                boolean r1 = r0.isConnected()     // Catch: java.lang.Throwable -> Lcc
                if (r1 == 0) goto L36
                java.lang.String r1 = "BleConnectHelper"
                java.lang.String r4 = "BLE connected"
                com.coveiot.utils.utility.LogHelper.d(r1, r4)     // Catch: java.lang.Throwable -> Lcc
                com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager r1 = com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager.getInstance()     // Catch: java.lang.Throwable -> Lcc
                com.squareup.otto.Bus r1 = r1.getEventBus()     // Catch: java.lang.Throwable -> Lcc
                com.coveiot.android.crpsdk.BleConnectStateChangeEvent r4 = new com.coveiot.android.crpsdk.BleConnectStateChangeEvent     // Catch: java.lang.Throwable -> Lcc
                r5 = 2
                r4.<init>(r5)     // Catch: java.lang.Throwable -> Lcc
                r1.post(r4)     // Catch: java.lang.Throwable -> Lcc
                goto L53
            L36:
                boolean r1 = r0.isConnecting()     // Catch: java.lang.Throwable -> Lcc
                if (r1 == 0) goto L55
                java.lang.String r1 = "BleConnectHelper"
                java.lang.String r4 = "BLE connecting..."
                com.coveiot.utils.utility.LogHelper.d(r1, r4)     // Catch: java.lang.Throwable -> Lcc
                com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager r1 = com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager.getInstance()     // Catch: java.lang.Throwable -> Lcc
                com.squareup.otto.Bus r1 = r1.getEventBus()     // Catch: java.lang.Throwable -> Lcc
                com.coveiot.android.crpsdk.BleConnectStateChangeEvent r4 = new com.coveiot.android.crpsdk.BleConnectStateChangeEvent     // Catch: java.lang.Throwable -> Lcc
                r4.<init>(r3)     // Catch: java.lang.Throwable -> Lcc
                r1.post(r4)     // Catch: java.lang.Throwable -> Lcc
            L53:
                r1 = r2
                goto L56
            L55:
                r1 = r3
            L56:
                if (r1 != 0) goto L59
                goto Lca
            L59:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcc
                r1.<init>()     // Catch: java.lang.Throwable -> Lcc
                java.lang.String r4 = "connect: "
                r1.append(r4)     // Catch: java.lang.Throwable -> Lcc
                r1.append(r7)     // Catch: java.lang.Throwable -> Lcc
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lcc
                java.lang.String r4 = "BleConnectHelper"
                com.coveiot.utils.utility.LogHelper.d(r4, r1)     // Catch: java.lang.Throwable -> Lcc
                r0.i = r3     // Catch: java.lang.Throwable -> Lcc
                com.crrepa.ble.conn.CRPBleDevice r1 = r0.c     // Catch: java.lang.Throwable -> Lcc
                if (r1 == 0) goto L7f
                java.lang.String r1 = r1.getMacAddress()     // Catch: java.lang.Throwable -> Lcc
                boolean r1 = android.text.TextUtils.equals(r7, r1)     // Catch: java.lang.Throwable -> Lcc
                if (r1 != 0) goto L9d
            L7f:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcc
                r1.<init>()     // Catch: java.lang.Throwable -> Lcc
                java.lang.String r4 = "getBleDevice: "
                r1.append(r4)     // Catch: java.lang.Throwable -> Lcc
                r1.append(r7)     // Catch: java.lang.Throwable -> Lcc
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lcc
                java.lang.String r4 = "BleConnectHelper"
                com.coveiot.utils.utility.LogHelper.d(r4, r1)     // Catch: java.lang.Throwable -> Lcc
                com.crrepa.ble.CRPBleClient r1 = r0.b     // Catch: java.lang.Throwable -> Lcc
                com.crrepa.ble.conn.CRPBleDevice r1 = r1.getBleDevice(r7)     // Catch: java.lang.Throwable -> Lcc
                r0.c = r1     // Catch: java.lang.Throwable -> Lcc
            L9d:
                com.crrepa.ble.conn.CRPBleDevice r1 = r0.c     // Catch: java.lang.Throwable -> Lcc
                if (r1 != 0) goto Lb9
                java.lang.String r7 = "BleConnectHelper"
                java.lang.String r1 = "bleDevice is null reply with disconnect event"
                com.coveiot.utils.utility.LogHelper.d(r7, r1)     // Catch: java.lang.Throwable -> Lcc
                com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager r7 = com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager.getInstance()     // Catch: java.lang.Throwable -> Lcc
                com.squareup.otto.Bus r7 = r7.getEventBus()     // Catch: java.lang.Throwable -> Lcc
                com.coveiot.android.crpsdk.BleConnectStateChangeEvent r1 = new com.coveiot.android.crpsdk.BleConnectStateChangeEvent     // Catch: java.lang.Throwable -> Lcc
                r1.<init>(r2)     // Catch: java.lang.Throwable -> Lcc
                r7.post(r1)     // Catch: java.lang.Throwable -> Lcc
                goto Lca
            Lb9:
                r0.f4101a = r7     // Catch: java.lang.Throwable -> Lcc
                r0.h = r3     // Catch: java.lang.Throwable -> Lcc
                r0.f = r3     // Catch: java.lang.Throwable -> Lcc
                com.crrepa.ble.conn.CRPBleConnection r7 = r1.connect()     // Catch: java.lang.Throwable -> Lcc
                r0.d = r7     // Catch: java.lang.Throwable -> Lcc
                com.coveiot.android.crpsdk.BleConnectHelper$f r1 = r0.e     // Catch: java.lang.Throwable -> Lcc
                r7.setConnectionStateListener(r1)     // Catch: java.lang.Throwable -> Lcc
            Lca:
                monitor-exit(r0)
                return
            Lcc:
                r7 = move-exception
                monitor-exit(r0)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.crpsdk.BleConnectHelper.a.accept(java.lang.Object):void");
        }
    }

    /* loaded from: classes3.dex */
    public class b implements ObservableOnSubscribe<String> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f4103a;

        public b(BleConnectHelper bleConnectHelper, String str) {
            this.f4103a = str;
        }

        @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
            if (!TextUtils.isEmpty(this.f4103a)) {
                observableEmitter.onNext(this.f4103a);
            } else {
                LogHelper.e("BleConnectHelper", "address is null!");
            }
            observableEmitter.onComplete();
        }
    }

    /* loaded from: classes3.dex */
    public class c implements Consumer<Long> {
        public c() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Long l) throws Throwable {
            BleConnectHelper bleConnectHelper = BleConnectHelper.this;
            bleConnectHelper.establishConnection(bleConnectHelper.f4101a);
        }
    }

    /* loaded from: classes3.dex */
    public class d implements Consumer<CRPBleDevice> {
        public d() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(CRPBleDevice cRPBleDevice) throws Throwable {
            cRPBleDevice.disconnect();
            BleConnectHelper bleConnectHelper = BleConnectHelper.this;
            bleConnectHelper.f = false;
            bleConnectHelper.g = false;
        }
    }

    /* loaded from: classes3.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public static BleConnectHelper f4106a;
    }

    /* loaded from: classes3.dex */
    public static class f implements CRPBleConnectionStateListener {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<BleConnectHelper> f4107a;

        public f(BleConnectHelper bleConnectHelper) {
            this.f4107a = new WeakReference<>(bleConnectHelper);
        }

        @Override // com.crrepa.ble.conn.listener.CRPBleConnectionStateListener
        public void onConnectionStateChange(int i) {
            LogHelper.d("BleConnectHelper", "onConnectionStateChange: " + i);
            BleConnectHelper bleConnectHelper = this.f4107a.get();
            if (i == 0) {
                bleConnectHelper.f = false;
                bleConnectHelper.g = false;
                if (bleConnectHelper.i && bleConnectHelper.d != null) {
                    LogHelper.d("BleConnectHelper", "closeGatt");
                    bleConnectHelper.d.close();
                    bleConnectHelper.c = null;
                    bleConnectHelper.d = null;
                }
                LogHelper.d("BleConnectHelper", "reconnection1: " + bleConnectHelper.h);
                if (bleConnectHelper.h) {
                    Observable.timer(3L, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new a.a.a.a.a(bleConnectHelper));
                }
            } else if (i == 2) {
                bleConnectHelper.f = false;
                bleConnectHelper.g = true;
            }
            CRPBleEventBusManager.getInstance().getEventBus().post(new BleConnectStateChangeEvent(i));
        }
    }

    public BleConnectHelper(Context context) {
        this.b = CRPBleClient.create(context.getApplicationContext());
    }

    public static BleConnectHelper getInstance(Context context) {
        if (e.f4106a == null) {
            e.f4106a = new BleConnectHelper(context);
        }
        return e.f4106a;
    }

    public void delayConnect() {
        Observable.timer(3L, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new c());
    }

    public void disconnect(boolean z) {
        LogHelper.d("BleConnectHelper", "disconnect: " + z);
        this.h = z;
        CRPBleDevice cRPBleDevice = this.c;
        if (cRPBleDevice == null) {
            return;
        }
        Observable.just(cRPBleDevice).observeOn(AndroidSchedulers.mainThread()).subscribe(new d());
    }

    public void enableOTA() {
        this.i = false;
        this.h = false;
    }

    public void establishConnection(String str) {
        LogHelper.d("BleConnectHelper", "establishConnection");
        Observable.create(new b(this, str)).observeOn(AndroidSchedulers.mainThread()).subscribe(new a());
    }

    public CRPBleConnection getBleConnection() {
        return this.d;
    }

    public CRPBleDevice getBleDevice() {
        return this.c;
    }

    public boolean isConnected() {
        CRPBleDevice cRPBleDevice = this.c;
        if (cRPBleDevice == null || this.d == null || !cRPBleDevice.isConnected()) {
            return false;
        }
        return this.g;
    }

    public boolean isConnecting() {
        return this.f;
    }
}
