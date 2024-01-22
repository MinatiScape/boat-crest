package com.ido.ble.dfu.e.b;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes11.dex */
public class d {
    private static d g;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12204a = false;
    private int b = 0;
    private int c = 0;
    private Timer d;
    private TimerTask e;
    private b f;

    /* loaded from: classes11.dex */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            d.this.f();
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(int i);
    }

    public static d b() {
        if (g == null) {
            g = new d();
        }
        return g;
    }

    private void c() {
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[TempProgressUpdateTask] release");
        this.f12204a = false;
        this.f = null;
        this.b = 0;
    }

    private void d() {
        e();
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[TempProgressUpdateTask] startTimer");
        this.d = new Timer();
        a aVar = new a();
        this.e = aVar;
        this.d.schedule(aVar, 0L, 1000L);
    }

    private void e() {
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[TempProgressUpdateTask] stopTimer");
        TimerTask timerTask = this.e;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.d;
        if (timer != null) {
            timer.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        int i = this.b + 1;
        this.b = i;
        int i2 = i != 5 ? i == 10 ? 2 : i == 20 ? 3 : i == 35 ? 4 : i == 55 ? 5 : i == 80 ? 6 : i == 110 ? 7 : i == 145 ? 8 : i == 185 ? 9 : i == 230 ? 10 : this.c : 1;
        b bVar = this.f;
        if (bVar == null || !this.f12204a || i2 == this.c) {
            return;
        }
        this.c = i2;
        bVar.a(i2);
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[TempProgressUpdateTask] updateProgress , secondCount = " + this.b + ", tmpProgress=" + this.c);
    }

    public void a() {
        if (this.f12204a) {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[TempProgressUpdateTask] stop");
            e();
            c();
        }
    }

    public void a(b bVar) {
        if (this.f12204a) {
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[TempProgressUpdateTask] start ,listener = " + bVar);
        this.b = 0;
        this.f12204a = true;
        this.f = bVar;
        d();
    }
}
