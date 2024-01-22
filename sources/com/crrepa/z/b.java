package com.crrepa.z;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.crrepa.ble.scan.bean.CRPScanDevice;
/* loaded from: classes9.dex */
public class b implements BluetoothAdapter.LeScanCallback {
    public static Handler c = new Handler(Looper.getMainLooper());

    /* renamed from: a  reason: collision with root package name */
    public long f7861a;
    public com.crrepa.y.a b;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.b.a(b.this);
            b.this.b.c();
        }
    }

    public b(long j) {
        this.f7861a = j;
    }

    public b a(long j) {
        this.f7861a = j;
        return this;
    }

    public b a(com.crrepa.y.a aVar) {
        this.b = aVar;
        return this;
    }

    public void a() {
        c.removeCallbacksAndMessages(null);
    }

    public com.crrepa.y.a b() {
        return this.b;
    }

    public long c() {
        return this.f7861a;
    }

    public void d() {
        this.b.a(this);
        this.b.b();
    }

    public void e() {
        if (this.f7861a > 0) {
            a();
            c.postDelayed(new a(), this.f7861a);
        }
    }

    @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (bluetoothDevice == null) {
            return;
        }
        this.b.a(new CRPScanDevice(bluetoothDevice, i, bArr));
    }
}
