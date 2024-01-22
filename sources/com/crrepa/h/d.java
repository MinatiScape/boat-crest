package com.crrepa.h;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.crrepa.ble.conn.callback.CRPMtuChangeCallback;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.listener.CRPDeviceRssiListener;
import com.crrepa.m.e;
import com.crrepa.m.f;
import com.crrepa.m.g;
/* loaded from: classes9.dex */
public class d extends BluetoothGattCallback {
    public CRPBleConnectionStateListener d;
    public CRPDeviceRssiListener e;
    public CRPMtuChangeCallback f;

    /* renamed from: a  reason: collision with root package name */
    public g f7725a = new g();
    public e b = new e();
    public com.crrepa.m.a c = new com.crrepa.m.a();
    public boolean g = false;

    public com.crrepa.m.a a() {
        return this.c;
    }

    public void a(CRPMtuChangeCallback cRPMtuChangeCallback) {
        this.f = cRPMtuChangeCallback;
    }

    public void a(CRPBleConnectionStateListener cRPBleConnectionStateListener) {
        this.d = cRPBleConnectionStateListener;
        b(1);
    }

    public void a(CRPDeviceRssiListener cRPDeviceRssiListener) {
        this.e = cRPDeviceRssiListener;
    }

    public e b() {
        return this.b;
    }

    public final void b(int i) {
        CRPBleConnectionStateListener cRPBleConnectionStateListener = this.d;
        if (cRPBleConnectionStateListener != null) {
            cRPBleConnectionStateListener.onConnectionStateChange(i);
        }
    }

    public final void c() {
        com.crrepa.i0.c.a("readProtocolVersion");
        this.g = true;
        com.crrepa.d.e.a().a(new com.crrepa.d.d(this.d));
        this.b.f();
    }

    public final void d() {
        this.g = false;
        a((CRPMtuChangeCallback) null);
    }

    public final void e() {
        com.crrepa.p.c.b().g();
        com.crrepa.p.c.b().a();
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        com.crrepa.i0.c.c("onCharacteristicChanged: " + bluetoothGattCharacteristic.getUuid().toString());
        com.crrepa.i0.c.c("onCharacteristicChanged: " + com.crrepa.i0.e.c(bluetoothGattCharacteristic.getValue()));
        this.c.a(bluetoothGattCharacteristic);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        com.crrepa.i0.c.c("onCharacteristicRead: " + bluetoothGattCharacteristic.getUuid().toString());
        com.crrepa.i0.c.c("onCharacteristicRead: " + com.crrepa.i0.e.c(bluetoothGattCharacteristic.getValue()));
        this.b.a(bluetoothGattCharacteristic);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        com.crrepa.i0.c.c("onCharacteristicWrite: " + bluetoothGattCharacteristic.getUuid().toString());
        com.crrepa.i0.c.c("onCharacteristicWrite: " + com.crrepa.i0.e.c(bluetoothGattCharacteristic.getValue()));
        f.d().g();
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onConnectionStateChange(bluetoothGatt, i, i2);
        com.crrepa.i0.c.c("BleGattCallback：onConnectionStateChange \nstatus: " + i + "\nnewState: " + i2);
        if (i2 == 2) {
            com.crrepa.q.b.c().a(bluetoothGatt);
            e();
        } else if (i2 == 0) {
            b(i2);
            d();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        com.crrepa.i0.c.c("onDescriptorWrite: " + bluetoothGattDescriptor.getCharacteristic().getUuid().toString());
        com.crrepa.i0.c.c("onDescriptorWrite: " + com.crrepa.i0.e.c(bluetoothGattDescriptor.getValue()));
        com.crrepa.i0.c.c("onDescriptorWrite: " + this.f7725a.c());
        if (this.f7725a.c()) {
            com.crrepa.m.d.c().a(bluetoothGattDescriptor);
            return;
        }
        this.f7725a.a(bluetoothGattDescriptor.getCharacteristic());
        if (this.f7725a.a(bluetoothGatt)) {
            this.f7725a.a(true);
            c();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onMtuChanged(bluetoothGatt, i, i2);
        com.crrepa.i0.c.c("onMtuChanged: " + i);
        com.crrepa.p.c.b().f();
        com.crrepa.l.a.b().a(i);
        CRPMtuChangeCallback cRPMtuChangeCallback = this.f;
        if (cRPMtuChangeCallback != null) {
            cRPMtuChangeCallback.onMtuChange(i);
        } else if (this.g) {
            com.crrepa.i0.c.c("STATE_CONNECTED");
            b(2);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onReadRemoteRssi(bluetoothGatt, i, i2);
        CRPDeviceRssiListener cRPDeviceRssiListener = this.e;
        if (cRPDeviceRssiListener != null) {
            cRPDeviceRssiListener.onDeviceRssi(i);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        super.onServicesDiscovered(bluetoothGatt, i);
        com.crrepa.q.b c = com.crrepa.q.b.c();
        if (c.b(bluetoothGatt.getServices())) {
            this.f7725a.a(c.a().g());
            this.f7725a.a(bluetoothGatt);
        }
    }
}
