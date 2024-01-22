package androidx.lifecycle;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
/* loaded from: classes.dex */
public class ServiceLifecycleDispatcher {

    /* renamed from: a  reason: collision with root package name */
    public final LifecycleRegistry f1366a;
    public final Handler b = new Handler();
    public a c;

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        public final LifecycleRegistry h;
        public final Lifecycle.Event i;
        public boolean j = false;

        public a(@NonNull LifecycleRegistry lifecycleRegistry, Lifecycle.Event event) {
            this.h = lifecycleRegistry;
            this.i = event;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.j) {
                return;
            }
            this.h.handleLifecycleEvent(this.i);
            this.j = true;
        }
    }

    public ServiceLifecycleDispatcher(@NonNull LifecycleOwner lifecycleOwner) {
        this.f1366a = new LifecycleRegistry(lifecycleOwner);
    }

    public final void a(Lifecycle.Event event) {
        a aVar = this.c;
        if (aVar != null) {
            aVar.run();
        }
        a aVar2 = new a(this.f1366a, event);
        this.c = aVar2;
        this.b.postAtFrontOfQueue(aVar2);
    }

    @NonNull
    public Lifecycle getLifecycle() {
        return this.f1366a;
    }

    public void onServicePreSuperOnBind() {
        a(Lifecycle.Event.ON_START);
    }

    public void onServicePreSuperOnCreate() {
        a(Lifecycle.Event.ON_CREATE);
    }

    public void onServicePreSuperOnDestroy() {
        a(Lifecycle.Event.ON_STOP);
        a(Lifecycle.Event.ON_DESTROY);
    }

    public void onServicePreSuperOnStart() {
        a(Lifecycle.Event.ON_START);
    }
}
