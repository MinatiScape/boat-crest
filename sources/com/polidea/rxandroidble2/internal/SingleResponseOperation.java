package com.polidea.rxandroidble2.internal;

import android.bluetooth.BluetoothGatt;
import android.os.DeadObjectException;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble2.exceptions.BleGattCannotStartException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.QueueReleasingEmitterWrapper;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public abstract class SingleResponseOperation<T> extends QueueOperation<T> {
    public final BluetoothGatt h;
    public final RxBleGattCallback i;
    public final BleGattOperationType j;
    public final TimeoutConfiguration k;

    public SingleResponseOperation(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, BleGattOperationType bleGattOperationType, TimeoutConfiguration timeoutConfiguration) {
        this.h = bluetoothGatt;
        this.i = rxBleGattCallback;
        this.j = bleGattOperationType;
        this.k = timeoutConfiguration;
    }

    public abstract Single<T> getCallback(RxBleGattCallback rxBleGattCallback);

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public final void protectedRun(ObservableEmitter<T> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        QueueReleasingEmitterWrapper queueReleasingEmitterWrapper = new QueueReleasingEmitterWrapper(observableEmitter, queueReleaseInterface);
        Single<T> callback = getCallback(this.i);
        TimeoutConfiguration timeoutConfiguration = this.k;
        long j = timeoutConfiguration.timeout;
        TimeUnit timeUnit = timeoutConfiguration.timeoutTimeUnit;
        Scheduler scheduler = timeoutConfiguration.timeoutScheduler;
        callback.timeout(j, timeUnit, scheduler, timeoutFallbackProcedure(this.h, this.i, scheduler)).toObservable().subscribe(queueReleasingEmitterWrapper);
        if (startOperation(this.h)) {
            return;
        }
        queueReleasingEmitterWrapper.cancel();
        queueReleasingEmitterWrapper.onError(new BleGattCannotStartException(this.h, this.j));
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.h.getDevice().getAddress(), -1);
    }

    public abstract boolean startOperation(BluetoothGatt bluetoothGatt);

    public Single<T> timeoutFallbackProcedure(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) {
        return Single.error(new BleGattCallbackTimeoutException(this.h, this.j));
    }

    public String toString() {
        return LoggerUtil.commonMacMessage(this.h);
    }
}
