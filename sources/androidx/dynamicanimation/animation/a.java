package androidx.dynamicanimation.animation;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class a {
    public static final ThreadLocal<a> g = new ThreadLocal<>();
    public c d;

    /* renamed from: a  reason: collision with root package name */
    public final SimpleArrayMap<b, Long> f1247a = new SimpleArrayMap<>();
    public final ArrayList<b> b = new ArrayList<>();
    public final C0140a c = new C0140a();
    public long e = 0;
    public boolean f = false;

    /* renamed from: androidx.dynamicanimation.animation.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0140a {
        public C0140a() {
        }

        public void a() {
            a.this.e = SystemClock.uptimeMillis();
            a aVar = a.this;
            aVar.c(aVar.e);
            if (a.this.b.size() > 0) {
                a.this.e().a();
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean doAnimationFrame(long j);
    }

    /* loaded from: classes.dex */
    public static abstract class c {

        /* renamed from: a  reason: collision with root package name */
        public final C0140a f1249a;

        public c(C0140a c0140a) {
            this.f1249a = c0140a;
        }

        public abstract void a();
    }

    /* loaded from: classes.dex */
    public static class d extends c {
        public final Runnable b;
        public final Handler c;
        public long d;

        /* renamed from: androidx.dynamicanimation.animation.a$d$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0141a implements Runnable {
            public RunnableC0141a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.d = SystemClock.uptimeMillis();
                d.this.f1249a.a();
            }
        }

        public d(C0140a c0140a) {
            super(c0140a);
            this.d = -1L;
            this.b = new RunnableC0141a();
            this.c = new Handler(Looper.myLooper());
        }

        @Override // androidx.dynamicanimation.animation.a.c
        public void a() {
            this.c.postDelayed(this.b, Math.max(10 - (SystemClock.uptimeMillis() - this.d), 0L));
        }
    }

    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static class e extends c {
        public final Choreographer b;
        public final Choreographer.FrameCallback c;

        /* renamed from: androidx.dynamicanimation.animation.a$e$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class Choreographer$FrameCallbackC0142a implements Choreographer.FrameCallback {
            public Choreographer$FrameCallbackC0142a() {
            }

            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                e.this.f1249a.a();
            }
        }

        public e(C0140a c0140a) {
            super(c0140a);
            this.b = Choreographer.getInstance();
            this.c = new Choreographer$FrameCallbackC0142a();
        }

        @Override // androidx.dynamicanimation.animation.a.c
        public void a() {
            this.b.postFrameCallback(this.c);
        }
    }

    public static a d() {
        ThreadLocal<a> threadLocal = g;
        if (threadLocal.get() == null) {
            threadLocal.set(new a());
        }
        return threadLocal.get();
    }

    public void a(b bVar, long j) {
        if (this.b.size() == 0) {
            e().a();
        }
        if (!this.b.contains(bVar)) {
            this.b.add(bVar);
        }
        if (j > 0) {
            this.f1247a.put(bVar, Long.valueOf(SystemClock.uptimeMillis() + j));
        }
    }

    public final void b() {
        if (this.f) {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                if (this.b.get(size) == null) {
                    this.b.remove(size);
                }
            }
            this.f = false;
        }
    }

    public void c(long j) {
        long uptimeMillis = SystemClock.uptimeMillis();
        for (int i = 0; i < this.b.size(); i++) {
            b bVar = this.b.get(i);
            if (bVar != null && f(bVar, uptimeMillis)) {
                bVar.doAnimationFrame(j);
            }
        }
        b();
    }

    public c e() {
        if (this.d == null) {
            if (Build.VERSION.SDK_INT >= 16) {
                this.d = new e(this.c);
            } else {
                this.d = new d(this.c);
            }
        }
        return this.d;
    }

    public final boolean f(b bVar, long j) {
        Long l = this.f1247a.get(bVar);
        if (l == null) {
            return true;
        }
        if (l.longValue() < j) {
            this.f1247a.remove(bVar);
            return true;
        }
        return false;
    }

    public void g(b bVar) {
        this.f1247a.remove(bVar);
        int indexOf = this.b.indexOf(bVar);
        if (indexOf >= 0) {
            this.b.set(indexOf, null);
            this.f = true;
        }
    }
}
