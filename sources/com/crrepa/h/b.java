package com.crrepa.h;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.os.Build;
import com.crrepa.ble.conn.CRPBleConnection;
/* loaded from: classes9.dex */
public class b implements com.crrepa.b.a {

    /* renamed from: a  reason: collision with root package name */
    public Context f7723a;
    public BluetoothDevice b;
    public a c = new a();

    public b(Context context, BluetoothDevice bluetoothDevice) {
        this.f7723a = context;
        this.b = bluetoothDevice;
    }

    public final BluetoothGatt a(BluetoothGattCallback bluetoothGattCallback) {
        return Build.VERSION.SDK_INT >= 23 ? this.b.connectGatt(this.f7723a, false, bluetoothGattCallback, 2) : this.b.connectGatt(this.f7723a, false, bluetoothGattCallback);
    }

    @Override // com.crrepa.b.a
    public CRPBleConnection a(d dVar) {
        com.crrepa.l.a.b().a(a((BluetoothGattCallback) dVar));
        this.c.a(dVar);
        return this.c;
    }

    @Override // com.crrepa.b.a
    public com.crrepa.v.b a(com.crrepa.v.d dVar) {
        com.crrepa.l.a.b().b(a((BluetoothGattCallback) dVar));
        return new com.crrepa.v.c(dVar);
    }

    @Override // com.crrepa.b.a
    public void disconnect() {
        com.crrepa.m.c.a();
    }
}
