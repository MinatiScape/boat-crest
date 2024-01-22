package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;
/* loaded from: classes10.dex */
public class a {
    public static a e;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Object f10373a = new Object();
    @NonNull
    public final Handler b = new Handler(Looper.getMainLooper(), new C0433a());
    @Nullable
    public c c;
    @Nullable
    public c d;

    /* renamed from: com.google.android.material.snackbar.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0433a implements Handler.Callback {
        public C0433a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            if (message.what != 0) {
                return false;
            }
            a.this.d((c) message.obj);
            return true;
        }
    }

    /* loaded from: classes10.dex */
    public interface b {
        void a(int i);

        void show();
    }

    /* loaded from: classes10.dex */
    public static class c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<b> f10374a;
        public int b;
        public boolean c;

        public c(int i, b bVar) {
            this.f10374a = new WeakReference<>(bVar);
            this.b = i;
        }

        public boolean a(@Nullable b bVar) {
            return bVar != null && this.f10374a.get() == bVar;
        }
    }

    public static a c() {
        if (e == null) {
            e = new a();
        }
        return e;
    }

    public final boolean a(@NonNull c cVar, int i) {
        b bVar = cVar.f10374a.get();
        if (bVar != null) {
            this.b.removeCallbacksAndMessages(cVar);
            bVar.a(i);
            return true;
        }
        return false;
    }

    public void b(b bVar, int i) {
        synchronized (this.f10373a) {
            if (g(bVar)) {
                a(this.c, i);
            } else if (h(bVar)) {
                a(this.d, i);
            }
        }
    }

    public void d(@NonNull c cVar) {
        synchronized (this.f10373a) {
            if (this.c == cVar || this.d == cVar) {
                a(cVar, 2);
            }
        }
    }

    public boolean e(b bVar) {
        boolean g;
        synchronized (this.f10373a) {
            g = g(bVar);
        }
        return g;
    }

    public boolean f(b bVar) {
        boolean z;
        synchronized (this.f10373a) {
            z = g(bVar) || h(bVar);
        }
        return z;
    }

    public final boolean g(b bVar) {
        c cVar = this.c;
        return cVar != null && cVar.a(bVar);
    }

    public final boolean h(b bVar) {
        c cVar = this.d;
        return cVar != null && cVar.a(bVar);
    }

    public void i(b bVar) {
        synchronized (this.f10373a) {
            if (g(bVar)) {
                this.c = null;
                if (this.d != null) {
                    o();
                }
            }
        }
    }

    public void j(b bVar) {
        synchronized (this.f10373a) {
            if (g(bVar)) {
                m(this.c);
            }
        }
    }

    public void k(b bVar) {
        synchronized (this.f10373a) {
            if (g(bVar)) {
                c cVar = this.c;
                if (!cVar.c) {
                    cVar.c = true;
                    this.b.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void l(b bVar) {
        synchronized (this.f10373a) {
            if (g(bVar)) {
                c cVar = this.c;
                if (cVar.c) {
                    cVar.c = false;
                    m(cVar);
                }
            }
        }
    }

    public final void m(@NonNull c cVar) {
        int i = cVar.b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED : 2750;
        }
        this.b.removeCallbacksAndMessages(cVar);
        Handler handler = this.b;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    public void n(int i, b bVar) {
        synchronized (this.f10373a) {
            if (g(bVar)) {
                c cVar = this.c;
                cVar.b = i;
                this.b.removeCallbacksAndMessages(cVar);
                m(this.c);
                return;
            }
            if (h(bVar)) {
                this.d.b = i;
            } else {
                this.d = new c(i, bVar);
            }
            c cVar2 = this.c;
            if (cVar2 == null || !a(cVar2, 4)) {
                this.c = null;
                o();
            }
        }
    }

    public final void o() {
        c cVar = this.d;
        if (cVar != null) {
            this.c = cVar;
            this.d = null;
            b bVar = cVar.f10374a.get();
            if (bVar != null) {
                bVar.show();
            } else {
                this.c = null;
            }
        }
    }
}
