package com.ido.ble.business.sync;

import com.ido.ble.logs.LogTool;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes11.dex */
class e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12133a = "TimeOutTaskManager";
    private static int b;
    private static Map<Integer, c> c = new HashMap();

    /* loaded from: classes11.dex */
    public interface b {
        void a();
    }

    /* loaded from: classes11.dex */
    public static class c extends Timer {
        public static int f = 0;
        public static int g = 1;

        /* renamed from: a  reason: collision with root package name */
        private TimerTask f12134a;
        private b b;
        private int c;
        private long d;
        private int e;

        /* loaded from: classes11.dex */
        public class a extends TimerTask {
            public a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LogTool.a(e.f12133a, "task is fire, id = " + c.this.c);
                int i = c.this.e;
                int i2 = c.g;
                if (i == i2) {
                    return;
                }
                c.this.e = i2;
                if (c.this.b != null) {
                    c.this.b.a();
                }
                e.b();
            }
        }

        private c(b bVar, long j, int i) {
            this.e = f;
            this.b = bVar;
            this.d = j;
            this.c = i;
        }

        public int a() {
            return this.e;
        }

        public int b() {
            return this.c;
        }

        public void c() {
            LogTool.a(e.f12133a, "task start, id = " + this.c);
            a aVar = new a();
            this.f12134a = aVar;
            schedule(aVar, this.d);
        }

        public void d() {
            this.e = g;
            TimerTask timerTask = this.f12134a;
            if (timerTask != null) {
                timerTask.cancel();
                this.f12134a = null;
            }
            purge();
            cancel();
            LogTool.a(e.f12133a, "task stop, id = " + this.c);
        }
    }

    public static int a(b bVar, long j) {
        b();
        b++;
        c cVar = new c(bVar, j, b);
        c.put(Integer.valueOf(b), cVar);
        cVar.c();
        return b;
    }

    public static boolean a(int i) {
        c cVar;
        if (c.containsKey(Integer.valueOf(i)) && (cVar = c.get(Integer.valueOf(i))) != null) {
            cVar.d();
            c.remove(Integer.valueOf(i));
            LogTool.a(f12133a, "task queue size is " + c.size());
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.putAll(c);
            for (Map.Entry entry : hashMap.entrySet()) {
                c cVar = (c) entry.getValue();
                if (cVar != null && cVar.a() == c.g) {
                    c.remove(Integer.valueOf(cVar.b()));
                }
            }
            LogTool.a(f12133a, "after purge, task queue size is " + c.size());
        } catch (ConcurrentModificationException unused) {
            LogTool.b(f12133a, "purgeTask error, ignore, handle next.");
        }
    }
}