package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import androidx.annotation.RequiresApi;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.internal.connection.PayloadSizeLimitProvider;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtilBluetoothServices;
import io.reactivex.Scheduler;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class OperationsProviderImpl implements OperationsProvider {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleGattCallback f13455a;
    public final BluetoothGatt b;
    public final LoggerUtilBluetoothServices c;
    public final TimeoutConfiguration d;
    public final Scheduler e;
    public final Scheduler f;
    public final Provider<ReadRssiOperation> g;

    @Inject
    public OperationsProviderImpl(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, LoggerUtilBluetoothServices loggerUtilBluetoothServices, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, @Named("bluetooth_interaction") Scheduler scheduler, @Named("timeout") Scheduler scheduler2, Provider<ReadRssiOperation> provider) {
        this.f13455a = rxBleGattCallback;
        this.b = bluetoothGatt;
        this.c = loggerUtilBluetoothServices;
        this.d = timeoutConfiguration;
        this.e = scheduler;
        this.f = scheduler2;
        this.g = provider;
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    @RequiresApi(21)
    public ConnectionPriorityChangeOperation provideConnectionPriorityChangeOperation(int i, long j, TimeUnit timeUnit) {
        return new ConnectionPriorityChangeOperation(this.f13455a, this.b, this.d, i, new TimeoutConfiguration(j, timeUnit, this.f));
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    public CharacteristicLongWriteOperation provideLongWriteOperation(BluetoothGattCharacteristic bluetoothGattCharacteristic, RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, PayloadSizeLimitProvider payloadSizeLimitProvider, byte[] bArr) {
        return new CharacteristicLongWriteOperation(this.b, this.f13455a, this.e, this.d, bluetoothGattCharacteristic, payloadSizeLimitProvider, writeOperationAckStrategy, writeOperationRetryStrategy, bArr);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    @RequiresApi(21)
    public MtuRequestOperation provideMtuChangeOperation(int i) {
        return new MtuRequestOperation(this.f13455a, this.b, this.d, i);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    public CharacteristicReadOperation provideReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new CharacteristicReadOperation(this.f13455a, this.b, this.d, bluetoothGattCharacteristic);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    public DescriptorReadOperation provideReadDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new DescriptorReadOperation(this.f13455a, this.b, this.d, bluetoothGattDescriptor);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    public ReadRssiOperation provideRssiReadOperation() {
        return this.g.get();
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    public ServiceDiscoveryOperation provideServiceDiscoveryOperation(long j, TimeUnit timeUnit) {
        return new ServiceDiscoveryOperation(this.f13455a, this.b, this.c, new TimeoutConfiguration(j, timeUnit, this.f));
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    public CharacteristicWriteOperation provideWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return new CharacteristicWriteOperation(this.f13455a, this.b, this.d, bluetoothGattCharacteristic, bArr);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.OperationsProvider
    public DescriptorWriteOperation provideWriteDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return new DescriptorWriteOperation(this.f13455a, this.b, this.d, 2, bluetoothGattDescriptor, bArr);
    }
}
