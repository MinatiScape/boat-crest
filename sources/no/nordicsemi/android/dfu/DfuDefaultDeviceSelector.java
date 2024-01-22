package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
class DfuDefaultDeviceSelector implements DfuDeviceSelector {
    @Override // no.nordicsemi.android.dfu.DfuDeviceSelector
    public boolean matches(@NonNull BluetoothDevice bluetoothDevice, int i, @NonNull byte[] bArr, @NonNull String str, @NonNull String str2) {
        return str.equals(bluetoothDevice.getAddress()) || str2.equals(bluetoothDevice.getAddress());
    }
}
