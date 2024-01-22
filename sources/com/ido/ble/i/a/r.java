package com.ido.ble.i.a;

import com.ido.ble.callback.BindCallBack;
import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes11.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private static final int f12300a = 1;
    private static final long b = 0;
    private static final long c = 20000;
    private static boolean d = false;
    private static b e;
    private static BindCallBack.ICallBack f = new a();

    /* loaded from: classes11.dex */
    public static class a implements BindCallBack.ICallBack {
        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onCancel() {
            boolean unused = r.d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onFailed(BindCallBack.BindFailedError bindFailedError) {
            boolean unused = r.d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onNeedAuth(int i) {
            boolean unused = r.d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onReject() {
            boolean unused = r.d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onSuccess() {
            boolean unused = r.d = true;
        }
    }

    /* loaded from: classes11.dex */
    public static class b extends TimerTask {

        /* renamed from: a  reason: collision with root package name */
        private Timer f12301a;
        private int b = 0;
        private boolean c = true;

        public b(Timer timer) {
            this.f12301a = timer;
        }

        private void b() {
            BindCallBack.b();
            com.ido.ble.event.stat.one.c.a("error:13");
            LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] out of time, bind failed");
        }

        private void c() {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] (BindTimerTask) task canceled.");
            this.c = false;
            this.f12301a.cancel();
            this.f12301a = null;
            com.ido.ble.callback.b.N().b(r.f);
        }

        private void d() {
            com.ido.ble.i.a.a.l0();
        }

        public Timer a() {
            return this.f12301a;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (!this.c) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] (BindTimerTask) isDoing is false.");
            } else if (!com.ido.ble.bluetooth.a.h()) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] (BindTimerTask) disconnect.");
                c();
            } else if (r.d) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] (BindTimerTask) isRespond is true.");
                c();
            } else {
                int i = this.b;
                if (i < 1) {
                    this.b = i + 1;
                    d();
                    return;
                }
                LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] (BindTimerTask) out of max retry times.");
                c();
                b();
            }
        }
    }

    public static void c() {
        b bVar = e;
        if (bVar != null && bVar.a() != null) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] (BindTimerTask ing) ...");
            return;
        }
        d = false;
        com.ido.ble.callback.b.N().a(f);
        Timer timer = new Timer();
        b bVar2 = new b(timer);
        e = bVar2;
        timer.schedule(bVar2, 0L, c);
    }
}
