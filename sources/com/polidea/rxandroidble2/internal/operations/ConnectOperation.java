package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.os.DeadObjectException;
import androidx.annotation.NonNull;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.BleConnectionCompat;
import com.polidea.rxandroidble2.internal.util.DisposableUtil;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public class ConnectOperation extends QueueOperation<BluetoothGatt> {
    public final BluetoothDevice h;
    public final BleConnectionCompat i;
    public final RxBleGattCallback j;
    public final BluetoothGattProvider k;
    public final TimeoutConfiguration l;
    public final boolean m;
    public final ConnectionStateChangeListener n;

    /* loaded from: classes12.dex */
    public class a implements Action {
        public final /* synthetic */ QueueReleaseInterface h;

        public a(ConnectOperation connectOperation, QueueReleaseInterface queueReleaseInterface) {
            this.h = queueReleaseInterface;
        }

        @Override // io.reactivex.functions.Action
        public void run() {
            this.h.release();
        }
    }

    /* loaded from: classes12.dex */
    public class b implements SingleTransformer<BluetoothGatt, BluetoothGatt> {
        public b() {
        }

        @Override // io.reactivex.SingleTransformer
        /* renamed from: a */
        public Single<BluetoothGatt> apply(Single<BluetoothGatt> single) {
            ConnectOperation connectOperation = ConnectOperation.this;
            if (connectOperation.m) {
                return single;
            }
            TimeoutConfiguration timeoutConfiguration = connectOperation.l;
            return single.timeout(timeoutConfiguration.timeout, timeoutConfiguration.timeoutTimeUnit, timeoutConfiguration.timeoutScheduler, connectOperation.c());
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Callable<BluetoothGatt> {
        public c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public BluetoothGatt call() {
            throw new BleGattCallbackTimeoutException(ConnectOperation.this.k.getBluetoothGatt(), BleGattOperationType.CONNECTION_STATE);
        }
    }

    /* loaded from: classes12.dex */
    public class d implements SingleOnSubscribe<BluetoothGatt> {

        /* loaded from: classes12.dex */
        public class a implements Predicate<RxBleConnection.RxBleConnectionState> {
            public a(d dVar) {
            }

            @Override // io.reactivex.functions.Predicate
            /* renamed from: a */
            public boolean test(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                return rxBleConnectionState == RxBleConnection.RxBleConnectionState.CONNECTED;
            }
        }

        public d() {
        }

        @Override // io.reactivex.SingleOnSubscribe
        public void subscribe(SingleEmitter<BluetoothGatt> singleEmitter) {
            singleEmitter.setDisposable((DisposableSingleObserver) ConnectOperation.this.a().delaySubscription(ConnectOperation.this.j.getOnConnectionStateChange().filter(new a(this))).mergeWith(ConnectOperation.this.j.observeDisconnect().firstOrError()).firstOrError().subscribeWith(DisposableUtil.disposableSingleObserverFromEmitter(singleEmitter)));
            ConnectOperation.this.n.onConnectionStateChange(RxBleConnection.RxBleConnectionState.CONNECTING);
            ConnectOperation connectOperation = ConnectOperation.this;
            ConnectOperation.this.k.updateBluetoothGatt(connectOperation.i.connectGatt(connectOperation.h, connectOperation.m, connectOperation.j.getBluetoothGattCallback()));
        }
    }

    /* loaded from: classes12.dex */
    public class e implements Callable<BluetoothGatt> {
        public e() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public BluetoothGatt call() {
            ConnectOperation.this.n.onConnectionStateChange(RxBleConnection.RxBleConnectionState.CONNECTED);
            return ConnectOperation.this.k.getBluetoothGatt();
        }
    }

    @Inject
    public ConnectOperation(BluetoothDevice bluetoothDevice, BleConnectionCompat bleConnectionCompat, RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, @Named("connect-timeout") TimeoutConfiguration timeoutConfiguration, @Named("autoConnect") boolean z, ConnectionStateChangeListener connectionStateChangeListener) {
        this.h = bluetoothDevice;
        this.i = bleConnectionCompat;
        this.j = rxBleGattCallback;
        this.k = bluetoothGattProvider;
        this.l = timeoutConfiguration;
        this.m = z;
        this.n = connectionStateChangeListener;
    }

    public Single<BluetoothGatt> a() {
        return Single.fromCallable(new e());
    }

    @NonNull
    public final Single<BluetoothGatt> b() {
        return Single.create(new d());
    }

    @NonNull
    public Single<BluetoothGatt> c() {
        return Single.fromCallable(new c());
    }

    public final SingleTransformer<BluetoothGatt, BluetoothGatt> d() {
        return new b();
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public void protectedRun(ObservableEmitter<BluetoothGatt> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        observableEmitter.setDisposable((DisposableSingleObserver) b().compose(d()).doFinally(new a(this, queueReleaseInterface)).subscribeWith(DisposableUtil.disposableSingleObserverFromEmitter(observableEmitter)));
        if (this.m) {
            queueReleaseInterface.release();
        }
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.h.getAddress(), -1);
    }

    public String toString() {
        return "ConnectOperation{" + LoggerUtil.commonMacMessage(this.h.getAddress()) + ", autoConnect=" + this.m + '}';
    }
}
