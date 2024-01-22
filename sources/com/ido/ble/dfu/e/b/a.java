package com.ido.ble.dfu.e.b;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.dfu.e.b.c;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class a {
    private static final int g = 2;
    private static final int h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 3;

    /* renamed from: a  reason: collision with root package name */
    private b f12193a;
    private String b;
    private int c = 0;
    private boolean d = false;
    private Handler e = new Handler(Looper.getMainLooper());
    private int f = 0;

    /* renamed from: com.ido.ble.dfu.e.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0590a implements c.d {

        /* renamed from: com.ido.ble.dfu.e.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0591a implements Runnable {
            public RunnableC0591a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.e();
            }
        }

        /* renamed from: com.ido.ble.dfu.e.b.a$a$b */
        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.e();
            }
        }

        public C0590a() {
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a() {
            a.this.f = 3;
            a.this.d().postDelayed(new RunnableC0591a(), 5000L);
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a(BLEDevice bLEDevice) {
            a.this.f = 1;
            a.this.c();
            if (a.this.f12193a != null) {
                a.this.f12193a.c();
            }
            a.this.f12193a = null;
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void b(BLEDevice bLEDevice) {
            a.this.f = 2;
            a.this.d().postDelayed(new b(), 5000L);
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void c(BLEDevice bLEDevice) {
            a.this.f = 1;
            a.this.c();
            if (a.this.f12193a != null) {
                a.this.f12193a.c();
            }
            a.this.f12193a = null;
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a();

        void b();

        void c();
    }

    private void b() {
        new c().a(new C0590a(), this.b, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.b, "[CheckDFUResultTask] finished");
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler d() {
        if (this.e == null) {
            this.e = new Handler(Looper.getMainLooper());
        }
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.c < 2) {
            LogTool.b(com.ido.ble.dfu.a.b, "[CheckDFUResultTask] rechecking...");
            this.c++;
            b();
            return;
        }
        LogTool.b(com.ido.ble.dfu.a.b, "[CheckDFUResultTask] out of max retry times, failed!");
        c();
        b bVar = this.f12193a;
        if (bVar != null) {
            int i2 = this.f;
            if (3 == i2) {
                bVar.b();
            } else if (2 == i2) {
                bVar.a();
            }
        }
        this.f12193a = null;
    }

    private void f() {
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.e = null;
        this.c = 0;
        this.d = false;
    }

    public void a() {
        if (this.d) {
            LogTool.d(com.ido.ble.dfu.a.b, "[CheckDFUResultTask] stop task");
            f();
        }
    }

    public void a(b bVar, String str) {
        if (this.d) {
            LogTool.b(com.ido.ble.dfu.a.b, "[CheckDFUResultTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.b, "[CheckDFUResultTask] start");
        this.f12193a = bVar;
        this.b = str;
        b();
        this.d = true;
    }
}
