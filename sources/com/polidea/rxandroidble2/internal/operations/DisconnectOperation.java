package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.os.DeadObjectException;
import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import io.reactivex.Emitter;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
/* loaded from: classes12.dex */
public class DisconnectOperation extends QueueOperation<Void> {
    public final RxBleGattCallback h;
    public final BluetoothGattProvider i;
    public final String j;
    public final BluetoothManager k;
    public final Scheduler l;
    public final TimeoutConfiguration m;
    public final ConnectionStateChangeListener n;

    /* loaded from: classes12.dex */
    public class a implements SingleObserver<BluetoothGatt> {
        public final /* synthetic */ ObservableEmitter h;
        public final /* synthetic */ QueueReleaseInterface i;

        public a(ObservableEmitter observableEmitter, QueueReleaseInterface queueReleaseInterface) {
            this.h = observableEmitter;
            this.i = queueReleaseInterface;
        }

        @Override // io.reactivex.SingleObserver
        /* renamed from: a */
        public void onSuccess(BluetoothGatt bluetoothGatt) {
            bluetoothGatt.close();
            DisconnectOperation.this.a(this.h, this.i);
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            RxBleLog.w(th, "Disconnect operation has been executed but finished with an error - considering disconnected.", new Object[0]);
            DisconnectOperation.this.a(this.h, this.i);
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
        }
    }

    /* loaded from: classes12.dex */
    public static class b extends Single<BluetoothGatt> {
        public final BluetoothGatt h;
        public final RxBleGattCallback i;
        public final Scheduler j;

        /* loaded from: classes12.dex */
        public class a implements Function<RxBleConnection.RxBleConnectionState, BluetoothGatt> {
            public a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public BluetoothGatt apply(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                return b.this.h;
            }
        }

        /* renamed from: com.polidea.rxandroidble2.internal.operations.DisconnectOperation$b$b  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public class C0711b implements Predicate<RxBleConnection.RxBleConnectionState> {
            public C0711b(b bVar) {
            }

            @Override // io.reactivex.functions.Predicate
            /* renamed from: a */
            public boolean test(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                return rxBleConnectionState == RxBleConnection.RxBleConnectionState.DISCONNECTED;
            }
        }

        /* loaded from: classes12.dex */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.h.disconnect();
            }
        }

        public b(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) {
            this.h = bluetoothGatt;
            this.i = rxBleGattCallback;
            this.j = scheduler;
        }

        @Override // io.reactivex.Single
        public void subscribeActual(SingleObserver<? super BluetoothGatt> singleObserver) {
            this.i.getOnConnectionStateChange().filter(new C0711b(this)).firstOrError().map(new a()).subscribe(singleObserver);
            this.j.createWorker().schedule(new c());
        }
    }

    @Inject
    public DisconnectOperation(RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, @Named("mac-address") String str, BluetoothManager bluetoothManager, @Named("bluetooth_interaction") Scheduler scheduler, @Named("disconnect-timeout") TimeoutConfiguration timeoutConfiguration, ConnectionStateChangeListener connectionStateChangeListener) {
        this.h = rxBleGattCallback;
        this.i = bluetoothGattProvider;
        this.j = str;
        this.k = bluetoothManager;
        this.l = scheduler;
        this.m = timeoutConfiguration;
        this.n = connectionStateChangeListener;
    }

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public void a(Emitter<Void> emitter, QueueReleaseInterface queueReleaseInterface) {
        this.n.onConnectionStateChange(RxBleConnection.RxBleConnectionState.DISCONNECTED);
        queueReleaseInterface.release();
        emitter.onComplete();
    }

    public final Single<BluetoothGatt> b(BluetoothGatt bluetoothGatt) {
        b bVar = new b(bluetoothGatt, this.h, this.l);
        TimeoutConfiguration timeoutConfiguration = this.m;
        return bVar.timeout(timeoutConfiguration.timeout, timeoutConfiguration.timeoutTimeUnit, timeoutConfiguration.timeoutScheduler, Single.just(bluetoothGatt));
    }

    public final Single<BluetoothGatt> c(BluetoothGatt bluetoothGatt) {
        if (d(bluetoothGatt)) {
            return Single.just(bluetoothGatt);
        }
        return b(bluetoothGatt);
    }

    public final boolean d(BluetoothGatt bluetoothGatt) {
        return this.k.getConnectionState(bluetoothGatt.getDevice(), 7) == 0;
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public void protectedRun(ObservableEmitter<Void> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        this.n.onConnectionStateChange(RxBleConnection.RxBleConnectionState.DISCONNECTING);
        BluetoothGatt bluetoothGatt = this.i.getBluetoothGatt();
        if (bluetoothGatt == null) {
            RxBleLog.w("Disconnect operation has been executed but GATT instance was null - considering disconnected.", new Object[0]);
            a(observableEmitter, queueReleaseInterface);
            return;
        }
        c(bluetoothGatt).observeOn(this.l).subscribe(new a(observableEmitter, queueReleaseInterface));
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.j, -1);
    }

    public String toString() {
        return "DisconnectOperation{" + LoggerUtil.commonMacMessage(this.j) + '}';
    }
}
