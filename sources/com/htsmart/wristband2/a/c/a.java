package com.htsmart.wristband2.a.c;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.WristbandLog;
import com.polidea.rxandroidble2.NotificationSetupMode;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.exceptions.BleCharacteristicNotFoundException;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.UUID;
/* loaded from: classes11.dex */
public abstract class a extends com.htsmart.wristband2.a.b.a {
    public static final UUID q = UUID.fromString("000001ff-3c17-d293-8e48-14fe2e4da212");
    public static final UUID r = UUID.fromString("0000ff02-0000-1000-8000-00805f9b34fb");
    public static final UUID s = UUID.fromString("0000ff03-0000-1000-8000-00805f9b34fb");
    public volatile BluetoothGattCharacteristic o;
    public volatile int p = 20;

    /* renamed from: com.htsmart.wristband2.a.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0560a implements Function<BluetoothGattService, ObservableSource<?>> {
        public final /* synthetic */ RxBleConnection h;

        /* renamed from: com.htsmart.wristband2.a.c.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0561a implements Consumer<Observable<byte[]>> {
            public C0561a() {
            }

            @Override // io.reactivex.functions.Consumer
            /* renamed from: a */
            public void accept(Observable<byte[]> observable) throws Exception {
                WristbandLog.i("rxPrepare setupNotification", new Object[0]);
                a.this.F(observable);
            }
        }

        public C0560a(RxBleConnection rxBleConnection) {
            this.h = rxBleConnection;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<?> apply(BluetoothGattService bluetoothGattService) throws Exception {
            WristbandLog.i("rxPrepare getCharacteristic", new Object[0]);
            a.this.o = bluetoothGattService.getCharacteristic(a.r);
            if (a.this.o != null) {
                BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(a.s);
                if (characteristic != null) {
                    return this.h.setupNotification(characteristic, NotificationSetupMode.DEFAULT).doOnNext(new C0561a());
                }
                throw new BleCharacteristicNotFoundException(a.s);
            }
            throw new BleCharacteristicNotFoundException(a.r);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Function<RxBleDeviceServices, SingleSource<BluetoothGattService>> {
        public b(a aVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<BluetoothGattService> apply(RxBleDeviceServices rxBleDeviceServices) throws Exception {
            WristbandLog.i("rxPrepare getService", new Object[0]);
            return rxBleDeviceServices.getService(a.q);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Function<Integer, SingleSource<RxBleDeviceServices>> {
        public final /* synthetic */ RxBleConnection h;

        public c(RxBleConnection rxBleConnection) {
            this.h = rxBleConnection;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<RxBleDeviceServices> apply(Integer num) throws Exception {
            a.this.p = num.intValue() - 3;
            WristbandLog.i("rxPrepare mMtuSize:" + a.this.p, new Object[0]);
            return this.h.discoverServices();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Consumer<byte[]> {
        public d() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(byte[] bArr) throws Exception {
            WristbandLog.e("Receive Data:" + BytesUtil.bytes2HexStr(bArr), new Object[0]);
            a.this.a(bArr);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Consumer<Throwable> {
        public e(a aVar) {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            WristbandLog.w(th, "Receive Data error", new Object[0]);
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Action {
        public f(a aVar) {
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            WristbandLog.w("Receive Data completed", new Object[0]);
        }
    }

    @SuppressLint({"CheckResult"})
    public final void F(Observable<byte[]> observable) {
        observable.subscribe(new d(), new e(this), new f(this));
    }

    @Override // com.htsmart.wristband2.a.b.a
    @NonNull
    @CallSuper
    public Observable<?> a(RxBleConnection rxBleConnection) {
        return ((Build.VERSION.SDK_INT < 21 || !WristbandApplication.betaIsRequestMtuEnabled()) ? Single.just(23) : rxBleConnection.requestMtu(191).onErrorReturnItem(23)).flatMap(new c(rxBleConnection)).flatMap(new b(this)).toObservable().flatMap(new C0560a(rxBleConnection));
    }

    public abstract void a(@NonNull byte[] bArr);

    public final Completable b(@NonNull byte[] bArr) {
        RxBleConnection b2 = b();
        if (b2 == null) {
            RxBleDevice rxBleDevice = getRxBleDevice();
            return Completable.error(new BleDisconnectedException(rxBleDevice != null ? rxBleDevice.getMacAddress() : "Unknown"));
        } else if (this.o == null) {
            return Completable.error(new BleCharacteristicNotFoundException(r));
        } else {
            WristbandLog.e("Send Data:" + BytesUtil.bytes2HexStr(bArr), new Object[0]);
            if (bArr.length <= this.p) {
                return b2.writeCharacteristic(this.o, bArr).ignoreElement();
            }
            RxBleConnection.LongWriteOperationBuilder createNewLongWriteBuilder = b2.createNewLongWriteBuilder();
            createNewLongWriteBuilder.setBytes(bArr);
            createNewLongWriteBuilder.setCharacteristic(this.o);
            createNewLongWriteBuilder.setMaxBatchSize(this.p);
            return createNewLongWriteBuilder.build().ignoreElements();
        }
    }

    @SuppressLint({"CheckResult"})
    public final void c(@NonNull byte[] bArr) throws Exception {
        RxBleConnection b2 = b();
        if (b2 == null) {
            RxBleDevice rxBleDevice = getRxBleDevice();
            throw new BleDisconnectedException(rxBleDevice != null ? rxBleDevice.getMacAddress() : "Unknown");
        } else if (this.o == null) {
            throw new BleCharacteristicNotFoundException(r);
        } else {
            WristbandLog.e("Send Data:" + BytesUtil.bytes2HexStr(bArr), new Object[0]);
            if (bArr.length <= this.p) {
                b2.writeCharacteristic(this.o, bArr).blockingGet();
                return;
            }
            RxBleConnection.LongWriteOperationBuilder createNewLongWriteBuilder = b2.createNewLongWriteBuilder();
            createNewLongWriteBuilder.setBytes(bArr);
            createNewLongWriteBuilder.setCharacteristic(this.o);
            createNewLongWriteBuilder.setMaxBatchSize(this.p);
            createNewLongWriteBuilder.build().blockingSubscribe();
        }
    }
}
