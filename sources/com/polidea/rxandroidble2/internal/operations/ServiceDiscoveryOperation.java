package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.SingleResponseOperation;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtilBluetoothServices;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ServiceDiscoveryOperation extends SingleResponseOperation<RxBleDeviceServices> {
    public final BluetoothGatt l;
    public final LoggerUtilBluetoothServices m;

    public ServiceDiscoveryOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, LoggerUtilBluetoothServices loggerUtilBluetoothServices, TimeoutConfiguration timeoutConfiguration) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.SERVICE_DISCOVERY, timeoutConfiguration);
        this.l = bluetoothGatt;
        this.m = loggerUtilBluetoothServices;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(RxBleDeviceServices rxBleDeviceServices) throws Exception {
        this.m.log(rxBleDeviceServices, this.l.getDevice());
    }

    public static /* synthetic */ RxBleDeviceServices f(BluetoothGatt bluetoothGatt) throws Exception {
        return new RxBleDeviceServices(bluetoothGatt.getServices());
    }

    public static /* synthetic */ Single g(final BluetoothGatt bluetoothGatt, Long l) throws Exception {
        return Single.fromCallable(new Callable() { // from class: com.polidea.rxandroidble2.internal.operations.c
            @Override // java.util.concurrent.Callable
            public final Object call() {
                RxBleDeviceServices f;
                f = ServiceDiscoveryOperation.f(bluetoothGatt);
                return f;
            }
        });
    }

    public static /* synthetic */ SingleSource h(final BluetoothGatt bluetoothGatt, Scheduler scheduler) throws Exception {
        if (bluetoothGatt.getServices().size() == 0) {
            return Single.error(new BleGattCallbackTimeoutException(bluetoothGatt, BleGattOperationType.SERVICE_DISCOVERY));
        }
        return Single.timer(5L, TimeUnit.SECONDS, scheduler).flatMap(new Function() { // from class: com.polidea.rxandroidble2.internal.operations.b
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Single g;
                g = ServiceDiscoveryOperation.g(bluetoothGatt, (Long) obj);
                return g;
            }
        });
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public Single<RxBleDeviceServices> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnServicesDiscovered().firstOrError().doOnSuccess(new Consumer() { // from class: com.polidea.rxandroidble2.internal.operations.a
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ServiceDiscoveryOperation.this.e((RxBleDeviceServices) obj);
            }
        });
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public boolean startOperation(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.discoverServices();
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    @NonNull
    public Single<RxBleDeviceServices> timeoutFallbackProcedure(final BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, final Scheduler scheduler) {
        return Single.defer(new Callable() { // from class: com.polidea.rxandroidble2.internal.operations.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                SingleSource h;
                h = ServiceDiscoveryOperation.h(bluetoothGatt, scheduler);
                return h;
            }
        });
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public String toString() {
        return "ServiceDiscoveryOperation{" + super.toString() + '}';
    }
}
