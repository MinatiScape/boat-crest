package com.htsmart.wristband2.dfu;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public interface h {

    /* loaded from: classes11.dex */
    public interface a {
        void a(int i);

        void a(@NonNull BluetoothDevice bluetoothDevice);
    }

    void a();

    void a(a aVar);

    void a(@Nullable String str, @Nullable String str2, int i);

    void cancel();
}
