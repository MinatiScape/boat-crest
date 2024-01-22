package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
/* loaded from: classes.dex */
public class ProcessLifecycleOwner implements LifecycleOwner {
    public static final ProcessLifecycleOwner p = new ProcessLifecycleOwner();
    public Handler l;
    public int h = 0;
    public int i = 0;
    public boolean j = true;
    public boolean k = true;
    public final LifecycleRegistry m = new LifecycleRegistry(this);
    public Runnable n = new a();
    public ReportFragment.a o = new b();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ProcessLifecycleOwner.this.f();
            ProcessLifecycleOwner.this.g();
        }
    }

    /* loaded from: classes.dex */
    public class b implements ReportFragment.a {
        public b() {
        }

        @Override // androidx.lifecycle.ReportFragment.a
        public void a() {
        }

        @Override // androidx.lifecycle.ReportFragment.a
        public void onResume() {
            ProcessLifecycleOwner.this.b();
        }

        @Override // androidx.lifecycle.ReportFragment.a
        public void onStart() {
            ProcessLifecycleOwner.this.c();
        }
    }

    /* loaded from: classes.dex */
    public class c extends androidx.lifecycle.c {

        /* loaded from: classes.dex */
        public class a extends androidx.lifecycle.c {
            public a() {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPostResumed(@NonNull Activity activity) {
                ProcessLifecycleOwner.this.b();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPostStarted(@NonNull Activity activity) {
                ProcessLifecycleOwner.this.c();
            }
        }

        public c() {
        }

        @Override // androidx.lifecycle.c, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (Build.VERSION.SDK_INT < 29) {
                ReportFragment.f(activity).g(ProcessLifecycleOwner.this.o);
            }
        }

        @Override // androidx.lifecycle.c, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            ProcessLifecycleOwner.this.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        @RequiresApi(29)
        public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // androidx.lifecycle.c, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            ProcessLifecycleOwner.this.d();
        }
    }

    @NonNull
    public static LifecycleOwner get() {
        return p;
    }

    public static void h(Context context) {
        p.e(context);
    }

    public void a() {
        int i = this.i - 1;
        this.i = i;
        if (i == 0) {
            this.l.postDelayed(this.n, 700L);
        }
    }

    public void b() {
        int i = this.i + 1;
        this.i = i;
        if (i == 1) {
            if (this.j) {
                this.m.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
                this.j = false;
                return;
            }
            this.l.removeCallbacks(this.n);
        }
    }

    public void c() {
        int i = this.h + 1;
        this.h = i;
        if (i == 1 && this.k) {
            this.m.handleLifecycleEvent(Lifecycle.Event.ON_START);
            this.k = false;
        }
    }

    public void d() {
        this.h--;
        g();
    }

    public void e(Context context) {
        this.l = new Handler();
        this.m.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new c());
    }

    public void f() {
        if (this.i == 0) {
            this.j = true;
            this.m.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
    }

    public void g() {
        if (this.h == 0 && this.j) {
            this.m.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
            this.k = true;
        }
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.m;
    }
}
