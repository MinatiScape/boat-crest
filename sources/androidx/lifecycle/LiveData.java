package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class LiveData<T> {
    public static final Object k = new Object();

    /* renamed from: a  reason: collision with root package name */
    public final Object f1357a;
    public SafeIterableMap<Observer<? super T>, LiveData<T>.c> b;
    public int c;
    public boolean d;
    public volatile Object e;
    public volatile Object f;
    public int g;
    public boolean h;
    public boolean i;
    public final Runnable j;

    /* loaded from: classes.dex */
    public class LifecycleBoundObserver extends LiveData<T>.c implements LifecycleEventObserver {
        @NonNull
        public final LifecycleOwner l;

        public LifecycleBoundObserver(@NonNull LifecycleOwner lifecycleOwner, Observer<? super T> observer) {
            super(observer);
            this.l = lifecycleOwner;
        }

        @Override // androidx.lifecycle.LiveData.c
        public void b() {
            this.l.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.LiveData.c
        public boolean c(LifecycleOwner lifecycleOwner) {
            return this.l == lifecycleOwner;
        }

        @Override // androidx.lifecycle.LiveData.c
        public boolean d() {
            return this.l.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            Lifecycle.State currentState = this.l.getLifecycle().getCurrentState();
            if (currentState == Lifecycle.State.DESTROYED) {
                LiveData.this.removeObserver(this.h);
                return;
            }
            Lifecycle.State state = null;
            while (state != currentState) {
                a(d());
                state = currentState;
                currentState = this.l.getLifecycle().getCurrentState();
            }
        }
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1357a) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.k;
            }
            LiveData.this.setValue(obj);
        }
    }

    /* loaded from: classes.dex */
    public class b extends LiveData<T>.c {
        public b(LiveData liveData, Observer<? super T> observer) {
            super(observer);
        }

        @Override // androidx.lifecycle.LiveData.c
        public boolean d() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public abstract class c {
        public final Observer<? super T> h;
        public boolean i;
        public int j = -1;

        public c(Observer<? super T> observer) {
            this.h = observer;
        }

        public void a(boolean z) {
            if (z == this.i) {
                return;
            }
            this.i = z;
            LiveData.this.b(z ? 1 : -1);
            if (this.i) {
                LiveData.this.d(this);
            }
        }

        public void b() {
        }

        public boolean c(LifecycleOwner lifecycleOwner) {
            return false;
        }

        public abstract boolean d();
    }

    public LiveData(T t) {
        this.f1357a = new Object();
        this.b = new SafeIterableMap<>();
        this.c = 0;
        this.f = k;
        this.j = new a();
        this.e = t;
        this.g = 0;
    }

    public static void a(String str) {
        if (ArchTaskExecutor.getInstance().isMainThread()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    @MainThread
    public void b(int i) {
        int i2 = this.c;
        this.c = i + i2;
        if (this.d) {
            return;
        }
        this.d = true;
        while (true) {
            try {
                int i3 = this.c;
                if (i2 == i3) {
                    return;
                }
                boolean z = i2 == 0 && i3 > 0;
                boolean z2 = i2 > 0 && i3 == 0;
                if (z) {
                    onActive();
                } else if (z2) {
                    onInactive();
                }
                i2 = i3;
            } finally {
                this.d = false;
            }
        }
    }

    public final void c(LiveData<T>.c cVar) {
        if (cVar.i) {
            if (!cVar.d()) {
                cVar.a(false);
                return;
            }
            int i = cVar.j;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            cVar.j = i2;
            cVar.h.onChanged((Object) this.e);
        }
    }

    public void d(@Nullable LiveData<T>.c cVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (cVar != null) {
                c(cVar);
                cVar = null;
            } else {
                SafeIterableMap<Observer<? super T>, LiveData<T>.c>.d iteratorWithAdditions = this.b.iteratorWithAdditions();
                while (iteratorWithAdditions.hasNext()) {
                    c((c) iteratorWithAdditions.next().getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    public int e() {
        return this.g;
    }

    @Nullable
    public T getValue() {
        T t = (T) this.e;
        if (t != k) {
            return t;
        }
        return null;
    }

    public boolean hasActiveObservers() {
        return this.c > 0;
    }

    public boolean hasObservers() {
        return this.b.size() > 0;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull Observer<? super T> observer) {
        a("observe");
        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer);
        LiveData<T>.c putIfAbsent = this.b.putIfAbsent(observer, lifecycleBoundObserver);
        if (putIfAbsent != null && !putIfAbsent.c(lifecycleOwner)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (putIfAbsent != null) {
            return;
        }
        lifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
    }

    @MainThread
    public void observeForever(@NonNull Observer<? super T> observer) {
        a("observeForever");
        b bVar = new b(this, observer);
        LiveData<T>.c putIfAbsent = this.b.putIfAbsent(observer, bVar);
        if (putIfAbsent instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (putIfAbsent != null) {
            return;
        }
        bVar.a(true);
    }

    public void onActive() {
    }

    public void onInactive() {
    }

    public void postValue(T t) {
        boolean z;
        synchronized (this.f1357a) {
            z = this.f == k;
            this.f = t;
        }
        if (z) {
            ArchTaskExecutor.getInstance().postToMainThread(this.j);
        }
    }

    @MainThread
    public void removeObserver(@NonNull Observer<? super T> observer) {
        a("removeObserver");
        LiveData<T>.c remove = this.b.remove(observer);
        if (remove == null) {
            return;
        }
        remove.b();
        remove.a(false);
    }

    @MainThread
    public void removeObservers(@NonNull LifecycleOwner lifecycleOwner) {
        a("removeObservers");
        Iterator<Map.Entry<Observer<? super T>, LiveData<T>.c>> it = this.b.iterator();
        while (it.hasNext()) {
            Map.Entry<Observer<? super T>, LiveData<T>.c> next = it.next();
            if (next.getValue().c(lifecycleOwner)) {
                removeObserver(next.getKey());
            }
        }
    }

    @MainThread
    public void setValue(T t) {
        a("setValue");
        this.g++;
        this.e = t;
        d(null);
    }

    public LiveData() {
        this.f1357a = new Object();
        this.b = new SafeIterableMap<>();
        this.c = 0;
        Object obj = k;
        this.f = obj;
        this.j = new a();
        this.e = obj;
        this.g = -1;
    }
}
