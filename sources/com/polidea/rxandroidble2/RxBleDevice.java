package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.polidea.rxandroidble2.RxBleConnection;
import io.reactivex.Observable;
/* loaded from: classes9.dex */
public interface RxBleDevice {
    Observable<RxBleConnection> establishConnection(boolean z);

    Observable<RxBleConnection> establishConnection(boolean z, @NonNull Timeout timeout);

    BluetoothDevice getBluetoothDevice();

    RxBleConnection.RxBleConnectionState getConnectionState();

    String getMacAddress();

    @Nullable
    String getName();

    Observable<RxBleConnection.RxBleConnectionState> observeConnectionStateChanges();
}
