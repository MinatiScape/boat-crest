package com.crrepa.z;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Handler;
import android.os.Looper;
import com.crrepa.ble.scan.bean.CRPScanDevice;
import java.util.List;
/* loaded from: classes9.dex */
public class a extends ScanCallback {
    public static Handler c = new Handler(Looper.getMainLooper());

    /* renamed from: a  reason: collision with root package name */
    public long f7860a;
    public com.crrepa.y.a b;

    /* renamed from: com.crrepa.z.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class RunnableC0360a implements Runnable {
        public RunnableC0360a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.b.a(a.this);
            a.this.b.c();
        }
    }

    public a(long j) {
        this.f7860a = j;
    }

    public a a(long j) {
        this.f7860a = j;
        return this;
    }

    public a a(com.crrepa.y.a aVar) {
        this.b = aVar;
        return this;
    }

    public void a() {
        c.removeCallbacksAndMessages(null);
    }

    public com.crrepa.y.a b() {
        return this.b;
    }

    public final void b(ScanResult scanResult) {
        this.b.a(new CRPScanDevice(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes()));
    }

    public long c() {
        return this.f7860a;
    }

    public void d() {
        this.b.a(this);
        this.b.b();
    }

    public void e() {
        if (this.f7860a > 0) {
            a();
            c.postDelayed(new RunnableC0360a(), this.f7860a);
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onBatchScanResults(List<ScanResult> list) {
        super.onBatchScanResults(list);
        for (ScanResult scanResult : list) {
            b(scanResult);
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanFailed(int i) {
        super.onScanFailed(i);
        d();
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        super.onScanResult(i, scanResult);
        b(scanResult);
    }
}
