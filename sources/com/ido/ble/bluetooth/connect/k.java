package com.ido.ble.bluetooth.connect;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
class k {
    private static k e = null;
    private static final int f = Integer.MAX_VALUE;
    private b c;

    /* renamed from: a  reason: collision with root package name */
    private int f12073a = 0;
    private boolean b = false;
    private Handler d = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (k.e == null) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ReconnectTask] mTask = null");
            } else if (k.this.b) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ReconnectTask] isStopTask = true");
            } else {
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ReconnectTask] to try");
                k.this.c.a(k.this.f12073a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a();

        void a(int i);
    }

    private k() {
    }

    private void a(b bVar) {
        this.c = bVar;
    }

    public static void b(b bVar) {
        if (e == null) {
            e = new k();
        }
        e.a(bVar);
        e.d();
    }

    public static boolean b() {
        k kVar = e;
        if (kVar != null) {
            kVar.c();
            return true;
        }
        return false;
    }

    private void c() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ReconnectTask] cancelDelayTimer. ");
        this.d.removeCallbacksAndMessages(null);
        b bVar = this.c;
        if (bVar != null) {
            bVar.a(this.f12073a);
        }
    }

    private void d() {
        this.d.removeCallbacksAndMessages(null);
        this.f12073a++;
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ReconnectTask] startTask(), mReTryTimes = " + this.f12073a);
        int i = this.f12073a;
        long j = i < 10 ? 5000L : i < 15 ? 15000L : 30000L;
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ReconnectTask] will start after " + j + "ms ");
        this.d.postDelayed(new a(), j);
    }

    public static void e() {
        k kVar = e;
        if (kVar != null) {
            kVar.f();
            e = null;
        }
    }

    private void f() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ReconnectTask] stopTask()");
        this.b = true;
        this.f12073a = 0;
        this.d.removeCallbacksAndMessages(null);
    }
}
