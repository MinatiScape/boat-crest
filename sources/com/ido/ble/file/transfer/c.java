package com.ido.ble.file.transfer;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes11.dex */
public class c implements d {

    /* renamed from: a  reason: collision with root package name */
    private long f12261a = 0;
    private Timer b;
    private TimerTask c;
    private b d;

    /* loaded from: classes11.dex */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.this.c();
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        long currentTimeMillis = System.currentTimeMillis() - this.f12261a;
        LogTool.d(com.ido.ble.file.transfer.a.f12248a, "[check no response state]------------check-----" + (currentTimeMillis / 1000));
        if (currentTimeMillis > 70000) {
            LogTool.b(com.ido.ble.file.transfer.a.f12248a, "[check no response state]--------------time--out-----------");
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
        this.f12261a = 0L;
        e();
    }

    private void g() {
        a aVar = new a();
        this.c = aVar;
        this.b.schedule(aVar, 0L, 1000L);
    }

    @Override // com.ido.ble.file.transfer.d
    public void a() {
        this.f12261a = System.currentTimeMillis();
    }

    @Override // com.ido.ble.file.transfer.d
    public void a(b bVar) {
        d();
        this.b = new Timer();
        this.d = bVar;
        this.f12261a = System.currentTimeMillis();
        g();
    }

    @Override // com.ido.ble.file.transfer.d
    public void b() {
        LogTool.d(com.ido.ble.file.transfer.a.f12248a, "[check no response state]------------end-----------");
        f();
    }
}
