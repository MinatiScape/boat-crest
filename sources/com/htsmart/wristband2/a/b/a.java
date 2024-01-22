package com.htsmart.wristband2.a.b;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.bean.ConnectionError;
import com.htsmart.wristband2.bean.ConnectionState;
import com.htsmart.wristband2.utils.WristbandLog;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
public abstract class a {
    public ObservableEmitter<ConnectionState> d;
    public ObservableEmitter<ConnectionError> e;
    public volatile RxBleDevice g;
    public volatile Disposable h;
    public volatile RxBleConnection i;
    public volatile BleScanException j;
    public volatile ConnectionState f = ConnectionState.DISCONNECTED;
    public volatile boolean k = false;
    public final o l = new o(this, null);
    public final AtomicInteger m = new AtomicInteger(0);
    public final AtomicLong n = new AtomicLong(0);

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothAdapter f11942a = BluetoothAdapter.getDefaultAdapter();
    public final BluetoothManager b = (BluetoothManager) WristbandApplication.getContext().getSystemService("bluetooth");
    public final Set<String> c = Collections.synchronizedSet(new HashSet(5));

    /* renamed from: com.htsmart.wristband2.a.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class CallableC0555a implements Callable<ObservableSource<RxBleConnection>> {
        public final /* synthetic */ RxBleDevice h;

        /* renamed from: com.htsmart.wristband2.a.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0556a implements Function<RxBleDevice, ObservableSource<? extends RxBleConnection>> {
            public C0556a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public ObservableSource<? extends RxBleConnection> apply(@NonNull RxBleDevice rxBleDevice) {
                return a.this.o(rxBleDevice);
            }
        }

        /* renamed from: com.htsmart.wristband2.a.b.a$a$b */
        /* loaded from: classes11.dex */
        public class b implements Function<Boolean, SingleSource<RxBleDevice>> {
            public b() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public SingleSource<RxBleDevice> apply(@NonNull Boolean bool) {
                if (bool.booleanValue()) {
                    WristbandLog.i("doConnect and have cached device,connect directly", new Object[0]);
                    return Single.just(CallableC0555a.this.h);
                }
                WristbandLog.i("doConnect but don't have cached device,scan before connect", new Object[0]);
                CallableC0555a callableC0555a = CallableC0555a.this;
                return a.this.q(callableC0555a.h);
            }
        }

        public CallableC0555a(RxBleDevice rxBleDevice) {
            this.h = rxBleDevice;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.reactivex.ObservableSource<com.polidea.rxandroidble2.RxBleConnection> call() {
            /*
                Method dump skipped, instructions count: 310
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.a.b.a.CallableC0555a.call():io.reactivex.ObservableSource");
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Consumer<Throwable> {
        public b() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) {
            if (th instanceof BleScanException) {
                a.this.j = (BleScanException) th;
            }
            WristbandLog.w(th, "rxScanDevice scanError", new Object[0]);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Function<ScanResult, RxBleDevice> {
        public c() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public RxBleDevice apply(@NonNull ScanResult scanResult) {
            WristbandLog.i("rxScanDevice found device will be connect:%s", scanResult.getBleDevice().getMacAddress());
            a.this.c.add(scanResult.getBleDevice().getMacAddress());
            return scanResult.getBleDevice();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Predicate<ScanResult> {
        public final /* synthetic */ RxBleDevice h;

        public d(a aVar, RxBleDevice rxBleDevice) {
            this.h = rxBleDevice;
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(@NonNull ScanResult scanResult) {
            RxBleDevice bleDevice = scanResult.getBleDevice();
            if (bleDevice == null) {
                return false;
            }
            return TextUtils.equals(bleDevice.getMacAddress(), this.h.getMacAddress());
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Function<RxBleConnection, ObservableSource<RxBleConnection>> {

        /* renamed from: com.htsmart.wristband2.a.b.a$e$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0557a implements Function<Object, RxBleConnection> {
            public final /* synthetic */ RxBleConnection h;

            public C0557a(e eVar, RxBleConnection rxBleConnection) {
                this.h = rxBleConnection;
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public RxBleConnection apply(@NonNull Object obj) {
                return this.h;
            }
        }

        public e() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<RxBleConnection> apply(@NonNull RxBleConnection rxBleConnection) {
            return a.this.a(rxBleConnection).map(new C0557a(this, rxBleConnection));
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Consumer<RxBleAdapterStateObservable.BleAdapterState> {
        public f() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
            if (bleAdapterState == RxBleAdapterStateObservable.BleAdapterState.STATE_OFF) {
                a.this.c.clear();
            } else if (bleAdapterState != RxBleAdapterStateObservable.BleAdapterState.STATE_ON || a.this.g == null) {
            } else {
                a aVar = a.this;
                aVar.a(aVar.g);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Consumer<ConnectionState> {
        public g() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(@NonNull ConnectionState connectionState) {
            if (connectionState != a.this.f) {
                a.this.f = connectionState;
                WristbandLog.i("onConnectionStateChanged(%s)", connectionState.toString());
                a.this.a(connectionState);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class h implements ObservableOnSubscribe<ConnectionState> {
        public h() {
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(@NonNull ObservableEmitter<ConnectionState> observableEmitter) {
            a.this.d = observableEmitter.serialize();
        }
    }

    /* loaded from: classes11.dex */
    public class i implements Consumer<ConnectionError> {
        public i() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(@NonNull ConnectionError connectionError) {
            WristbandLog.w(connectionError.getThrowable(), "onConnectionErrorOccur(retry:%s)", Boolean.valueOf(connectionError.isRetry()));
            a.this.a(connectionError);
        }
    }

    /* loaded from: classes11.dex */
    public class j implements ObservableOnSubscribe<ConnectionError> {
        public j() {
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(@NonNull ObservableEmitter<ConnectionError> observableEmitter) {
            a.this.e = observableEmitter.serialize();
        }
    }

    /* loaded from: classes11.dex */
    public class k implements Consumer<RxBleConnection> {
        public k() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(RxBleConnection rxBleConnection) {
            a.this.i = rxBleConnection;
            a.this.d.onNext(ConnectionState.CONNECTED);
            a.this.w();
        }
    }

    /* loaded from: classes11.dex */
    public class l implements Consumer<Throwable> {
        public l() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) {
            a.this.e.onNext(a.this.a(th, false));
            a.this.d.onNext(ConnectionState.DISCONNECTED);
        }
    }

    /* loaded from: classes11.dex */
    public class m implements Action {
        public m() {
        }

        @Override // io.reactivex.functions.Action
        public void run() {
            a.this.d.onNext(ConnectionState.DISCONNECTED);
        }
    }

    /* loaded from: classes11.dex */
    public class n implements Consumer<Disposable> {
        public n() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Disposable disposable) {
            a.this.d.onNext(ConnectionState.CONNECTING);
        }
    }

    /* loaded from: classes11.dex */
    public class o implements Function<Observable<? extends Throwable>, Observable<?>> {

        /* renamed from: com.htsmart.wristband2.a.b.a$o$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0558a implements Function<Throwable, ObservableSource<?>> {

            /* renamed from: com.htsmart.wristband2.a.b.a$o$a$a  reason: collision with other inner class name */
            /* loaded from: classes11.dex */
            public class C0559a implements Action {
                public C0559a() {
                }

                @Override // io.reactivex.functions.Action
                public void run() {
                    if (WristbandApplication.distributeConnectEventEvertTry || a.this.f != ConnectionState.CONNECTING) {
                        a.this.d.onNext(ConnectionState.CONNECTING);
                    }
                }
            }

            public C0558a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public ObservableSource<?> apply(@NonNull Throwable th) {
                WristbandLog.w("doConnect retry", new Object[0]);
                BluetoothAdapter bluetoothAdapter = a.this.f11942a;
                if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
                    return Observable.error(th);
                }
                int incrementAndGet = a.this.m.incrementAndGet();
                long j = (!WristbandApplication.isForeground() && incrementAndGet > 5) ? incrementAndGet <= 10 ? Constants.ONE_MIN_IN_MILLIS : incrementAndGet <= 30 ? incrementAndGet * 1000 * 10 : 600000L : 5000L;
                a.this.n.set(System.currentTimeMillis() + j);
                a.this.e.onNext(a.this.a(th, true));
                if (WristbandApplication.distributeConnectEventEvertTry || a.this.f == ConnectionState.CONNECTED) {
                    a.this.d.onNext(ConnectionState.DISCONNECTED);
                }
                return Observable.timer(j, TimeUnit.MILLISECONDS).doOnComplete(new C0559a());
            }
        }

        public o() {
        }

        public /* synthetic */ o(a aVar, f fVar) {
            this();
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
            return observable.flatMap(new C0558a());
        }
    }

    @SuppressLint({"CheckResult"})
    public a() {
        new RxBleAdapterStateObservable(WristbandApplication.getContext()).subscribe(new f());
        Observable.create(new h()).observeOn(AndroidSchedulers.mainThread()).subscribe(new g());
        Observable.create(new j()).observeOn(AndroidSchedulers.mainThread()).subscribe(new i());
    }

    public ConnectionError a(@NonNull Throwable th, boolean z) {
        return new ConnectionError(th, z, this.m.get(), this.j);
    }

    @NonNull
    public abstract Observable<?> a(RxBleConnection rxBleConnection);

    public void a(@NonNull BluetoothDevice bluetoothDevice) {
        RxBleDevice rxBleDevice;
        try {
            rxBleDevice = WristbandApplication.getRxBleClient().getBleDevice(bluetoothDevice.getAddress());
        } catch (Exception e2) {
            e2.printStackTrace();
            rxBleDevice = null;
        }
        t(rxBleDevice);
    }

    public abstract void a(ConnectionError connectionError);

    public abstract void a(ConnectionState connectionState);

    public void a(@NonNull RxBleDevice rxBleDevice) {
        t(rxBleDevice);
    }

    public void a(@NonNull String str) {
        RxBleDevice rxBleDevice;
        try {
            rxBleDevice = WristbandApplication.getRxBleClient().getBleDevice(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            rxBleDevice = null;
        }
        t(rxBleDevice);
    }

    @Nullable
    public final RxBleConnection b() {
        if (d()) {
            return this.i;
        }
        return null;
    }

    public void close() {
        t(null);
    }

    public final boolean d() {
        return (this.f != ConnectionState.CONNECTED || this.g == null || this.i == null) ? false : true;
    }

    public final void g() {
        WristbandLog.i("doClose", new Object[0]);
        this.g = null;
        if (this.h != null) {
            this.h.dispose();
            this.h = null;
        }
        this.i = null;
    }

    @Nullable
    public final String getConnectedAddress() {
        BluetoothDevice connectedDevice = getConnectedDevice();
        if (connectedDevice != null) {
            return connectedDevice.getAddress();
        }
        return null;
    }

    @Nullable
    public final BluetoothDevice getConnectedDevice() {
        RxBleDevice rxBleDevice = this.g;
        if (rxBleDevice == null || !d()) {
            return null;
        }
        return rxBleDevice.getBluetoothDevice();
    }

    @Nullable
    public final RxBleDevice getRxBleDevice() {
        return this.g;
    }

    public final void l(@NonNull RxBleDevice rxBleDevice) {
        WristbandLog.i("doConnect", new Object[0]);
        w();
        this.g = rxBleDevice;
        this.k = false;
        this.h = Observable.defer(new CallableC0555a(rxBleDevice)).retryWhen(this.l).doOnSubscribe(new n()).doOnDispose(new m()).subscribe(new k(), new l());
    }

    public final ScanSettings n() {
        return new ScanSettings.Builder().setScanMode(WristbandApplication.isForeground() ? 2 : 0).setCallbackType(2).build();
    }

    public final Observable<RxBleConnection> o(RxBleDevice rxBleDevice) {
        WristbandLog.i("rxConnect establishConnection(Thread:%s)", Thread.currentThread().getName());
        BluetoothAdapter bluetoothAdapter = this.f11942a;
        return (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) ? Observable.error(BleDisconnectedException.adapterDisabled(rxBleDevice.getMacAddress())) : rxBleDevice.establishConnection(false).delay(500L, TimeUnit.MILLISECONDS).flatMap(new e());
    }

    public final Single<RxBleDevice> q(RxBleDevice rxBleDevice) {
        return WristbandApplication.getRxBleClient().scanBleDevices(n(), new ScanFilter[0]).filter(new d(this, rxBleDevice)).firstElement().toSingle().timeout(1L, TimeUnit.MINUTES).map(new c()).doOnError(new b()).onErrorReturnItem(rxBleDevice);
    }

    public final void t(@Nullable RxBleDevice rxBleDevice) {
        RxBleDevice rxBleDevice2 = this.g;
        if (rxBleDevice == null) {
            if (rxBleDevice2 == null) {
                return;
            }
            g();
            return;
        }
        if (rxBleDevice2 != null) {
            if (rxBleDevice2.getMacAddress().equals(rxBleDevice.getMacAddress())) {
                if (this.f == ConnectionState.CONNECTED) {
                    WristbandLog.w("device %s already connected!!!", rxBleDevice.getMacAddress());
                    return;
                } else if (this.f == ConnectionState.CONNECTING && u()) {
                    w();
                    return;
                }
            }
            g();
        }
        l(rxBleDevice);
    }

    public final boolean u() {
        long currentTimeMillis = this.n.get() - System.currentTimeMillis();
        return currentTimeMillis > 0 && currentTimeMillis < 3000;
    }

    public final void w() {
        WristbandLog.i("doConnect resetTryTimes", new Object[0]);
        this.m.set(0);
    }
}
