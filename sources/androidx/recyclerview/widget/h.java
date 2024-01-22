package androidx.recyclerview.widget;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class h<T> implements ThreadUtil<T> {

    /* loaded from: classes.dex */
    public class a implements ThreadUtil.MainThreadCallback<T> {

        /* renamed from: a  reason: collision with root package name */
        public final c f1645a = new c();
        public final Handler b = new Handler(Looper.getMainLooper());
        public Runnable c = new RunnableC0177a();
        public final /* synthetic */ ThreadUtil.MainThreadCallback d;

        /* renamed from: androidx.recyclerview.widget.h$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0177a implements Runnable {
            public RunnableC0177a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d a2 = a.this.f1645a.a();
                while (a2 != null) {
                    int i = a2.b;
                    if (i == 1) {
                        a.this.d.updateItemCount(a2.c, a2.d);
                    } else if (i == 2) {
                        a.this.d.addTile(a2.c, (TileList.Tile) a2.h);
                    } else if (i != 3) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a2.b);
                    } else {
                        a.this.d.removeTile(a2.c, a2.d);
                    }
                    a2 = a.this.f1645a.a();
                }
            }
        }

        public a(h hVar, ThreadUtil.MainThreadCallback mainThreadCallback) {
            this.d = mainThreadCallback;
        }

        public final void a(d dVar) {
            this.f1645a.c(dVar);
            this.b.post(this.c);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void addTile(int i, TileList.Tile<T> tile) {
            a(d.c(2, i, tile));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void removeTile(int i, int i2) {
            a(d.a(3, i, i2));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void updateItemCount(int i, int i2) {
            a(d.a(1, i, i2));
        }
    }

    /* loaded from: classes.dex */
    public class b implements ThreadUtil.BackgroundCallback<T> {

        /* renamed from: a  reason: collision with root package name */
        public final c f1646a = new c();
        public final Executor b = AsyncTask.THREAD_POOL_EXECUTOR;
        public AtomicBoolean c = new AtomicBoolean(false);
        public Runnable d = new a();
        public final /* synthetic */ ThreadUtil.BackgroundCallback e;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    d a2 = b.this.f1646a.a();
                    if (a2 == null) {
                        b.this.c.set(false);
                        return;
                    }
                    int i = a2.b;
                    if (i == 1) {
                        b.this.f1646a.b(1);
                        b.this.e.refresh(a2.c);
                    } else if (i == 2) {
                        b.this.f1646a.b(2);
                        b.this.f1646a.b(3);
                        b.this.e.updateRange(a2.c, a2.d, a2.e, a2.f, a2.g);
                    } else if (i == 3) {
                        b.this.e.loadTile(a2.c, a2.d);
                    } else if (i != 4) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a2.b);
                    } else {
                        b.this.e.recycleTile((TileList.Tile) a2.h);
                    }
                }
            }
        }

        public b(h hVar, ThreadUtil.BackgroundCallback backgroundCallback) {
            this.e = backgroundCallback;
        }

        public final void a() {
            if (this.c.compareAndSet(false, true)) {
                this.b.execute(this.d);
            }
        }

        public final void b(d dVar) {
            this.f1646a.c(dVar);
            a();
        }

        public final void c(d dVar) {
            this.f1646a.d(dVar);
            a();
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void loadTile(int i, int i2) {
            b(d.a(3, i, i2));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<T> tile) {
            b(d.c(4, 0, tile));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void refresh(int i) {
            c(d.c(1, i, null));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void updateRange(int i, int i2, int i3, int i4, int i5) {
            c(d.b(2, i, i2, i3, i4, i5, null));
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public d f1647a;

        public synchronized d a() {
            d dVar = this.f1647a;
            if (dVar == null) {
                return null;
            }
            this.f1647a = dVar.f1648a;
            return dVar;
        }

        public synchronized void b(int i) {
            d dVar;
            while (true) {
                dVar = this.f1647a;
                if (dVar == null || dVar.b != i) {
                    break;
                }
                this.f1647a = dVar.f1648a;
                dVar.d();
            }
            if (dVar != null) {
                d dVar2 = dVar.f1648a;
                while (dVar2 != null) {
                    d dVar3 = dVar2.f1648a;
                    if (dVar2.b == i) {
                        dVar.f1648a = dVar3;
                        dVar2.d();
                    } else {
                        dVar = dVar2;
                    }
                    dVar2 = dVar3;
                }
            }
        }

        public synchronized void c(d dVar) {
            d dVar2 = this.f1647a;
            if (dVar2 == null) {
                this.f1647a = dVar;
                return;
            }
            while (true) {
                d dVar3 = dVar2.f1648a;
                if (dVar3 == null) {
                    dVar2.f1648a = dVar;
                    return;
                }
                dVar2 = dVar3;
            }
        }

        public synchronized void d(d dVar) {
            dVar.f1648a = this.f1647a;
            this.f1647a = dVar;
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        public static d i;
        public static final Object j = new Object();

        /* renamed from: a  reason: collision with root package name */
        public d f1648a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public Object h;

        public static d a(int i2, int i3, int i4) {
            return b(i2, i3, i4, 0, 0, 0, null);
        }

        public static d b(int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
            d dVar;
            synchronized (j) {
                dVar = i;
                if (dVar == null) {
                    dVar = new d();
                } else {
                    i = dVar.f1648a;
                    dVar.f1648a = null;
                }
                dVar.b = i2;
                dVar.c = i3;
                dVar.d = i4;
                dVar.e = i5;
                dVar.f = i6;
                dVar.g = i7;
                dVar.h = obj;
            }
            return dVar;
        }

        public static d c(int i2, int i3, Object obj) {
            return b(i2, i3, 0, 0, 0, 0, obj);
        }

        public void d() {
            this.f1648a = null;
            this.g = 0;
            this.f = 0;
            this.e = 0;
            this.d = 0;
            this.c = 0;
            this.b = 0;
            this.h = null;
            synchronized (j) {
                d dVar = i;
                if (dVar != null) {
                    this.f1648a = dVar;
                }
                i = this;
            }
        }
    }

    @Override // androidx.recyclerview.widget.ThreadUtil
    public ThreadUtil.BackgroundCallback<T> a(ThreadUtil.BackgroundCallback<T> backgroundCallback) {
        return new b(this, backgroundCallback);
    }

    @Override // androidx.recyclerview.widget.ThreadUtil
    public ThreadUtil.MainThreadCallback<T> b(ThreadUtil.MainThreadCallback<T> mainThreadCallback) {
        return new a(this, mainThreadCallback);
    }
}
