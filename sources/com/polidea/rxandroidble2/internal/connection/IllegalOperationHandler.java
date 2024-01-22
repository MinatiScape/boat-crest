package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import androidx.annotation.Nullable;
import com.polidea.rxandroidble2.internal.BleIllegalOperationException;
/* loaded from: classes12.dex */
public abstract class IllegalOperationHandler {
    public final IllegalOperationMessageCreator messageCreator;

    public IllegalOperationHandler(IllegalOperationMessageCreator illegalOperationMessageCreator) {
        this.messageCreator = illegalOperationMessageCreator;
    }

    @Nullable
    public abstract BleIllegalOperationException handleMismatchData(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);
}
