package com.crrepa.h;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.crrepa.ble.conn.CRPBleConnection;
import com.crrepa.ble.conn.CRPBleDevice;
import com.crrepa.i0.f;
/* loaded from: classes9.dex */
public class c implements CRPBleDevice {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f7724a;
    public BluetoothManager b;
    public com.crrepa.b.a c;
    public d d = new d();

    public c(Context context, BluetoothDevice bluetoothDevice, BluetoothManager bluetoothManager) {
        this.f7724a = bluetoothDevice;
        this.b = bluetoothManager;
        this.c = new b(context, bluetoothDevice);
    }

    @Override // com.crrepa.ble.conn.CRPBleDevice
    public CRPBleConnection connect() {
        f.b(this.f7724a.getAddress());
        return this.c.a(this.d);
    }

    @Override // com.crrepa.ble.conn.CRPBleDevice
    public com.crrepa.v.b connectDfu() {
        f.b(this.f7724a.getAddress());
        return this.c.a(new com.crrepa.v.d());
    }

    @Override // com.crrepa.ble.conn.CRPBleDevice
    public void disconnect() {
        this.c.disconnect();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return this.f7724a.equals(((c) obj).getBluetoothDevice());
        }
        return false;
    }

    @Override // com.crrepa.ble.conn.CRPBleDevice
    public BluetoothDevice getBluetoothDevice() {
        return this.f7724a;
    }

    @Override // com.crrepa.ble.conn.CRPBleDevice
    public String getMacAddress() {
        return this.f7724a.getAddress();
    }

    @Override // com.crrepa.ble.conn.CRPBleDevice
    @SuppressLint({"MissingPermission"})
    public String getName() {
        return this.f7724a.getName();
    }

    public int hashCode() {
        return this.f7724a.hashCode();
    }

    @Override // com.crrepa.ble.conn.CRPBleDevice
    @SuppressLint({"MissingPermission"})
    public boolean isConnected() {
        return this.b.getConnectionState(this.f7724a, 7) == 2;
    }
}
