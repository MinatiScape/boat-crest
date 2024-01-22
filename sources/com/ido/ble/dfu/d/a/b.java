package com.ido.ble.dfu.d.a;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes11.dex */
public class b implements d {

    /* renamed from: a  reason: collision with root package name */
    private long f12168a = 0;
    private Timer b;
    private TimerTask c;
    private InterfaceC0583b d;

    /* loaded from: classes11.dex */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            b.this.c();
        }
    }

    /* renamed from: com.ido.ble.dfu.d.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0583b {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[check no response state]------------check-----------");
        if (System.currentTimeMillis() - this.f12168a > 45000) {
            LogTool.b(com.ido.ble.dfu.a.f12157a, "[check no response state]--------------time--out-----------");
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
        this.f12168a = 0L;
        e();
    }

    private void g() {
        a aVar = new a();
        this.c = aVar;
        this.b.schedule(aVar, 0L, 1000L);
    }

    @Override // com.ido.ble.dfu.d.a.d
    public void a() {
        this.f12168a = System.currentTimeMillis();
    }

    @Override // com.ido.ble.dfu.d.a.d
    public void a(InterfaceC0583b interfaceC0583b) {
        d();
        this.b = new Timer();
        this.d = interfaceC0583b;
        this.f12168a = System.currentTimeMillis();
        g();
    }

    @Override // com.ido.ble.dfu.d.a.d
    public void b() {
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[check no response state]------------end-----------");
        f();
    }
}
