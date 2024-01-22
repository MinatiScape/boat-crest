package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.SingleResponseOperation;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.util.ByteAssociationUtil;
import io.reactivex.Single;
/* loaded from: classes12.dex */
public class DescriptorWriteOperation extends SingleResponseOperation<byte[]> {
    public final BluetoothGattDescriptor l;
    public final byte[] m;
    public final int n;

    public DescriptorWriteOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, int i, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.DESCRIPTOR_WRITE, timeoutConfiguration);
        this.n = i;
        this.l = bluetoothGattDescriptor;
        this.m = bArr;
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public Single<byte[]> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnDescriptorWrite().filter(ByteAssociationUtil.descriptorPredicate(this.l)).firstOrError().map(ByteAssociationUtil.getBytesFromAssociation());
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public boolean startOperation(BluetoothGatt bluetoothGatt) {
        this.l.setValue(this.m);
        BluetoothGattCharacteristic characteristic = this.l.getCharacteristic();
        int writeType = characteristic.getWriteType();
        characteristic.setWriteType(this.n);
        boolean writeDescriptor = bluetoothGatt.writeDescriptor(this.l);
        characteristic.setWriteType(writeType);
        return writeDescriptor;
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public String toString() {
        return "DescriptorWriteOperation{" + super.toString() + ", descriptor=" + new LoggerUtil.AttributeLogWrapper(this.l.getUuid(), this.m, true) + '}';
    }
}
