package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface DfuDeviceSelector {
    boolean matches(@NonNull BluetoothDevice bluetoothDevice, int i, @NonNull byte[] bArr, @NonNull String str, @NonNull String str2);
}
