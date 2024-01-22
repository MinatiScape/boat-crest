package com.ido.ble.firmware.log;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes11.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private long f12273a = 0;
    private Timer b;
    private TimerTask c;
    private InterfaceC0602b d;
    private int e;

    /* loaded from: classes11.dex */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            b.this.c();
        }
    }

    /* renamed from: com.ido.ble.firmware.log.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0602b {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (System.currentTimeMillis() - this.f12273a > this.e * 1000) {
            LogTool.b(c.f12275a, "[check no response state]--------------time--out-----------");
            f();
            this.d.a();
        }
    }

    private void d() {
        e();
    }

    private void e() {
        TimerTask timerTask = this.c;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.b;
        if (timer != null) {
            timer.cancel();
        }
    }

    private void f() {
        this.f12273a = 0L;
        e();
    }

    private void g() {
        a aVar = new a();
        this.c = aVar;
        this.b.schedule(aVar, 0L, 1000L);
    }

    public void a() {
        f();
    }

    public void a(InterfaceC0602b interfaceC0602b, int i) {
        d();
        this.b = new Timer();
        this.d = interfaceC0602b;
        this.f12273a = System.currentTimeMillis();
        this.e = i;
        g();
    }

    public void b() {
        this.f12273a = System.currentTimeMillis();
    }
}
