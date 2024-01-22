package com.crrepa.q;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import com.crrepa.i0.c;
import com.crrepa.v.f;
import java.util.List;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public com.crrepa.q.a f7814a;
    public f b;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ BluetoothGatt h;

        public a(b bVar, BluetoothGatt bluetoothGatt) {
            this.h = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothGatt bluetoothGatt = this.h;
            boolean discoverServices = bluetoothGatt != null ? bluetoothGatt.discoverServices() : false;
            c.a("discoverServices: " + discoverServices);
            if (discoverServices) {
                return;
            }
            com.crrepa.m.c.a();
        }
    }

    /* renamed from: com.crrepa.q.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0353b {

        /* renamed from: a  reason: collision with root package name */
        public static final b f7815a = new b();
    }

    public static b c() {
        return C0353b.f7815a;
    }

    public com.crrepa.q.a a() {
        return this.f7814a;
    }

    public void a(BluetoothGatt bluetoothGatt) {
        com.crrepa.g.a.a(new a(this, bluetoothGatt), 500L);
    }

    public boolean a(List<BluetoothGattService> list) {
        boolean z;
        if (list != null) {
            f fVar = new f(list);
            this.b = fVar;
            z = fVar.h();
        } else {
            z = false;
        }
        if (!z) {
            com.crrepa.m.c.a();
        }
        return z;
    }

    public f b() {
        return this.b;
    }

    public boolean b(List<BluetoothGattService> list) {
        boolean z;
        if (list != null) {
            com.crrepa.q.a aVar = new com.crrepa.q.a(list);
            this.f7814a = aVar;
            z = aVar.n();
        } else {
            z = false;
        }
        if (!z) {
            com.crrepa.m.c.a();
        }
        return z;
    }
}
