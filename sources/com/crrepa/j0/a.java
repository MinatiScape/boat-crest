package com.crrepa.j0;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import androidx.annotation.Nullable;
import java.util.Set;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothAdapter f7739a;

    public a(@Nullable BluetoothAdapter bluetoothAdapter) {
        this.f7739a = bluetoothAdapter;
    }

    public BluetoothDevice a(String str) {
        return this.f7739a.getRemoteDevice(str);
    }

    public Set<BluetoothDevice> a() {
        return this.f7739a.getBondedDevices();
    }

    public boolean b() {
        return this.f7739a != null;
    }

    public boolean c() {
        BluetoothAdapter bluetoothAdapter = this.f7739a;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }
}
