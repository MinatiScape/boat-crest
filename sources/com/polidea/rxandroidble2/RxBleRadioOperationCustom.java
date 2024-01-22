package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothGatt;
import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
/* loaded from: classes9.dex */
public interface RxBleRadioOperationCustom<T> extends RxBleCustomOperation<T> {
    @Override // com.polidea.rxandroidble2.RxBleCustomOperation
    @NonNull
    Observable<T> asObservable(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) throws Throwable;
}
