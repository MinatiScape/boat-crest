package com.crrepa.v;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
/* loaded from: classes9.dex */
public class d extends BluetoothGattCallback {

    /* renamed from: a  reason: collision with root package name */
    public i f7847a = new i();
    public com.crrepa.m.g b = new com.crrepa.m.g();
    public e c = new e(this.f7847a);
    public CRPBleConnectionStateListener d;

    public final void a() {
        com.crrepa.m.b.b(com.crrepa.l.a.b().d());
    }

    public void a(CRPBleConnectionStateListener cRPBleConnectionStateListener) {
        this.d = cRPBleConnectionStateListener;
        b(1);
    }

    public i b() {
        return this.f7847a;
    }

    public final void b(int i) {
        CRPBleConnectionStateListener cRPBleConnectionStateListener = this.d;
        if (cRPBleConnectionStateListener != null) {
            cRPBleConnectionStateListener.onConnectionStateChange(i);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        com.crrepa.i0.c.c("HS onCharacteristicChanged: " + com.crrepa.i0.e.c(bluetoothGattCharacteristic.getValue()));
        this.c.a(bluetoothGattCharacteristic);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        a.d().f();
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onConnectionStateChange(bluetoothGatt, i, i2);
        com.crrepa.i0.c.c("HS BleGattCallback：onConnectionStateChange \nstatus: " + i + "\nnewState: " + i2);
        if (i2 == 2) {
            com.crrepa.q.b.c().a(bluetoothGatt);
        } else if (i2 == 0) {
            b(i2);
            a();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        com.crrepa.i0.c.c("HS onDescriptorWrite: " + com.crrepa.i0.e.c(bluetoothGattDescriptor.getValue()));
        this.b.a(bluetoothGattDescriptor.getCharacteristic());
        if (this.b.a(bluetoothGatt)) {
            b(2);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        super.onServicesDiscovered(bluetoothGatt, i);
        com.crrepa.q.b c = com.crrepa.q.b.c();
        if (c.a(bluetoothGatt.getServices())) {
            this.b.a(c.b().a());
            this.b.a(bluetoothGatt);
        }
    }
}
