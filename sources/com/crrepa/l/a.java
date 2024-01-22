package com.crrepa.l;

import android.bluetooth.BluetoothGatt;
import androidx.annotation.NonNull;
import com.crrepa.ble.conn.type.CRPProtocolVersion;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothGatt f7747a;
    public BluetoothGatt b;
    public int c;
    public CRPProtocolVersion d;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static a f7748a = new a();
    }

    public a() {
        this.c = 20;
    }

    public static a b() {
        return b.f7748a;
    }

    public BluetoothGatt a() {
        return this.f7747a;
    }

    public void a(int i) {
        int i2 = i - 3;
        this.c = i2 - (i2 % 4);
    }

    public void a(@NonNull BluetoothGatt bluetoothGatt) {
        this.f7747a = bluetoothGatt;
    }

    public void a(CRPProtocolVersion cRPProtocolVersion) {
        this.d = cRPProtocolVersion;
    }

    public void b(BluetoothGatt bluetoothGatt) {
        this.b = bluetoothGatt;
    }

    public int c() {
        return this.c;
    }

    public BluetoothGatt d() {
        return this.b;
    }

    public CRPProtocolVersion e() {
        return this.d;
    }

    public boolean f() {
        return this.d == CRPProtocolVersion.V1;
    }

    public boolean g() {
        return this.d == CRPProtocolVersion.V2;
    }

    public void h() {
        this.c = 20;
    }
}
