package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothDevice;
/* loaded from: classes12.dex */
public interface g8 {
    void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr);

    void onScanFailed(int i);

    void onScanFinished();
}
