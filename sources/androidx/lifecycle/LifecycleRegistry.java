package androidx.lifecycle;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public class LifecycleRegistry extends Lifecycle {
    public FastSafeIterableMap<LifecycleObserver, a> b;
    public Lifecycle.State c;
    public final WeakReference<LifecycleOwner> d;
    public int e;
    public boolean f;
    public boolean g;
    public ArrayList<Lifecycle.State> h;
    public final boolean i;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public Lifecycle.State f1355a;
        public LifecycleEventObserver b;

        public a(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            this.b = Lifecycling.e(lifecycleObserver);
            this.f1355a = state;
        }

        public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State targetState = event.getTargetState();
            this.f1355a = LifecycleRegistry.f(this.f1355a, targetState);
            this.b.onStateChanged(lifecycleOwner, event);
            this.f1355a = targetState;
        }
    }

    public LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner) {
        this(lifecycleOwner, true);
    }

    @NonNull
    @VisibleForTesting
    public static LifecycleRegistry createUnsafe(@NonNull LifecycleOwner lifecycleOwner) {
        return new LifecycleRegistry(lifecycleOwner, false);
    }

    public static Lifecycle.State f(@NonNull Lifecycle.State state, @Nullable Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    public final void a(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, a>> descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext() && !this.g) {
            Map.Entry<LifecycleObserver, a> next = descendingIterator.next();
            a value = next.getValue();
            while (value.f1355a.compareTo(this.c) > 0 && !this.g && this.b.contains(next.getKey())) {
                Lifecycle.Event downFrom = Lifecycle.Event.downFrom(value.f1355a);
                if (downFrom != null) {
                    i(downFrom.getTargetState());
                    value.a(lifecycleOwner, downFrom);
                    h();
                } else {
                    throw new IllegalStateException("no event down from " + value.f1355a);
                }
            }
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public void addObserver(@NonNull LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner;
        c("addObserver");
        Lifecycle.State state = this.c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        a aVar = new a(lifecycleObserver, state2);
        if (this.b.putIfAbsent(lifecycleObserver, aVar) == null && (lifecycleOwner = this.d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            Lifecycle.State b = b(lifecycleObserver);
            this.e++;
            while (aVar.f1355a.compareTo(b) < 0 && this.b.contains(lifecycleObserver)) {
                i(aVar.f1355a);
                Lifecycle.Event upFrom = Lifecycle.Event.upFrom(aVar.f1355a);
                if (upFrom != null) {
                    aVar.a(lifecycleOwner, upFrom);
                    h();
                    b = b(lifecycleObserver);
                } else {
                    throw new IllegalStateException("no event up from " + aVar.f1355a);
                }
            }
            if (!z) {
                j();
            }
            this.e--;
        }
    }

    public final Lifecycle.State b(LifecycleObserver lifecycleObserver) {
        Map.Entry<LifecycleObserver, a> ceil = this.b.ceil(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State state2 = ceil != null ? ceil.getValue().f1355a : null;
        if (!this.h.isEmpty()) {
            ArrayList<Lifecycle.State> arrayList = this.h;
            state = arrayList.get(arrayList.size() - 1);
        }
        return f(f(this.c, state2), state);
    }

    @SuppressLint({"RestrictedApi"})
    public final void c(String str) {
        if (!this.i || ArchTaskExecutor.getInstance().isMainThread()) {
            return;
        }
        throw new IllegalStateException("Method " + str + " must be called on the main thread");
    }

    public final void d(LifecycleOwner lifecycleOwner) {
        SafeIterableMap<LifecycleObserver, a>.d iteratorWithAdditions = this.b.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext() && !this.g) {
            Map.Entry next = iteratorWithAdditions.next();
            a aVar = (a) next.getValue();
            while (aVar.f1355a.compareTo(this.c) < 0 && !this.g && this.b.contains((LifecycleObserver) next.getKey())) {
                i(aVar.f1355a);
                Lifecycle.Event upFrom = Lifecycle.Event.upFrom(aVar.f1355a);
                if (upFrom != null) {
                    aVar.a(lifecycleOwner, upFrom);
                    h();
                } else {
                    throw new IllegalStateException("no event up from " + aVar.f1355a);
                }
            }
        }
    }

    public final boolean e() {
        if (this.b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.b.eldest().getValue().f1355a;
        Lifecycle.State state2 = this.b.newest().getValue().f1355a;
        return state == state2 && this.c == state2;
    }

    public final void g(Lifecycle.State state) {
        Lifecycle.State state2 = this.c;
        if (state2 == state) {
            return;
        }
        if (state2 == Lifecycle.State.INITIALIZED && state == Lifecycle.State.DESTROYED) {
            throw new IllegalStateException("no event down from " + this.c);
        }
        this.c = state;
        if (!this.f && this.e == 0) {
            this.f = true;
            j();
            this.f = false;
            if (this.c == Lifecycle.State.DESTROYED) {
                this.b = new FastSafeIterableMap<>();
                return;
            }
            return;
        }
        this.g = true;
    }

    @Override // androidx.lifecycle.Lifecycle
    @NonNull
    public Lifecycle.State getCurrentState() {
        return this.c;
    }

    public int getObserverCount() {
        c("getObserverCount");
        return this.b.size();
    }

    public final void h() {
        ArrayList<Lifecycle.State> arrayList = this.h;
        arrayList.remove(arrayList.size() - 1);
    }

    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        c("handleLifecycleEvent");
        g(event.getTargetState());
    }

    public final void i(Lifecycle.State state) {
        this.h.add(state);
    }

    public final void j() {
        LifecycleOwner lifecycleOwner = this.d.get();
        if (lifecycleOwner != null) {
            while (!e()) {
                this.g = false;
                if (this.c.compareTo(this.b.eldest().getValue().f1355a) < 0) {
                    a(lifecycleOwner);
                }
                Map.Entry<LifecycleObserver, a> newest = this.b.newest();
                if (!this.g && newest != null && this.c.compareTo(newest.getValue().f1355a) > 0) {
                    d(lifecycleOwner);
                }
            }
            this.g = false;
            return;
        }
        throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
    }

    @MainThread
    @Deprecated
    public void markState(@NonNull Lifecycle.State state) {
        c("markState");
        setCurrentState(state);
    }

    @Override // androidx.lifecycle.Lifecycle
    public void removeObserver(@NonNull LifecycleObserver lifecycleObserver) {
        c("removeObserver");
        this.b.remove(lifecycleObserver);
    }

    @MainThread
    public void setCurrentState(@NonNull Lifecycle.State state) {
        c("setCurrentState");
        g(state);
    }

    public LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner, boolean z) {
        this.b = new FastSafeIterableMap<>();
        this.e = 0;
        this.f = false;
        this.g = false;
        this.h = new ArrayList<>();
        this.d = new WeakReference<>(lifecycleOwner);
        this.c = Lifecycle.State.INITIALIZED;
        this.i = z;
    }
}
