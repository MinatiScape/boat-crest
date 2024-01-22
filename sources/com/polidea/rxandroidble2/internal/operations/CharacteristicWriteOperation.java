package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.SingleResponseOperation;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.util.ByteAssociationUtil;
import io.reactivex.Single;
/* loaded from: classes12.dex */
public class CharacteristicWriteOperation extends SingleResponseOperation<byte[]> {
    public final BluetoothGattCharacteristic l;
    public final byte[] m;

    public CharacteristicWriteOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.CHARACTERISTIC_WRITE, timeoutConfiguration);
        this.l = bluetoothGattCharacteristic;
        this.m = bArr;
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public Single<byte[]> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnCharacteristicWrite().filter(ByteAssociationUtil.characteristicUUIDPredicate(this.l.getUuid())).firstOrError().map(ByteAssociationUtil.getBytesFromAssociation());
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public boolean startOperation(BluetoothGatt bluetoothGatt) {
        this.l.setValue(this.m);
        return bluetoothGatt.writeCharacteristic(this.l);
    }

    @Override // com.polidea.rxandroidble2.internal.SingleResponseOperation
    public String toString() {
        return "CharacteristicWriteOperation{" + super.toString() + ", characteristic=" + new LoggerUtil.AttributeLogWrapper(this.l.getUuid(), this.m, true) + '}';
    }
}
