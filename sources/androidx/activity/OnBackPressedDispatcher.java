package androidx.activity;

import android.annotation.SuppressLint;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.annotation.DoNotInline;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.annotation.RequiresApi;
import androidx.core.os.BuildCompat;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f343a;
    public final ArrayDeque<OnBackPressedCallback> b;
    public Consumer<Boolean> c;
    public OnBackInvokedCallback d;
    public OnBackInvokedDispatcher e;
    public boolean f;

    /* loaded from: classes.dex */
    public class LifecycleOnBackPressedCancellable implements LifecycleEventObserver, androidx.activity.a {
        public final Lifecycle h;
        public final OnBackPressedCallback i;
        @Nullable
        public androidx.activity.a j;

        public LifecycleOnBackPressedCancellable(@NonNull Lifecycle lifecycle, @NonNull OnBackPressedCallback onBackPressedCallback) {
            this.h = lifecycle;
            this.i = onBackPressedCallback;
            lifecycle.addObserver(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.h.removeObserver(this);
            this.i.removeCancellable(this);
            androidx.activity.a aVar = this.j;
            if (aVar != null) {
                aVar.cancel();
                this.j = null;
            }
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.j = OnBackPressedDispatcher.this.b(this.i);
            } else if (event == Lifecycle.Event.ON_STOP) {
                androidx.activity.a aVar = this.j;
                if (aVar != null) {
                    aVar.cancel();
                }
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                cancel();
            }
        }
    }

    @RequiresApi(33)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static OnBackInvokedCallback a(Runnable runnable) {
            Objects.requireNonNull(runnable);
            return new h(runnable);
        }

        @DoNotInline
        public static void b(Object obj, int i, Object obj2) {
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) obj2);
        }

        @DoNotInline
        public static void c(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    /* loaded from: classes.dex */
    public class b implements androidx.activity.a {
        public final OnBackPressedCallback h;

        public b(OnBackPressedCallback onBackPressedCallback) {
            this.h = onBackPressedCallback;
        }

        @Override // androidx.activity.a
        @OptIn(markerClass = {BuildCompat.PrereleaseSdkCheck.class})
        public void cancel() {
            OnBackPressedDispatcher.this.b.remove(this.h);
            this.h.removeCancellable(this);
            if (BuildCompat.isAtLeastT()) {
                this.h.setIsEnabledConsumer(null);
                OnBackPressedDispatcher.this.d();
            }
        }
    }

    public OnBackPressedDispatcher() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Boolean bool) {
        if (BuildCompat.isAtLeastT()) {
            d();
        }
    }

    @MainThread
    public void addCallback(@NonNull OnBackPressedCallback onBackPressedCallback) {
        b(onBackPressedCallback);
    }

    @NonNull
    @OptIn(markerClass = {BuildCompat.PrereleaseSdkCheck.class})
    @MainThread
    public androidx.activity.a b(@NonNull OnBackPressedCallback onBackPressedCallback) {
        this.b.add(onBackPressedCallback);
        b bVar = new b(onBackPressedCallback);
        onBackPressedCallback.addCancellable(bVar);
        if (BuildCompat.isAtLeastT()) {
            d();
            onBackPressedCallback.setIsEnabledConsumer(this.c);
        }
        return bVar;
    }

    @RequiresApi(33)
    public void d() {
        boolean hasEnabledCallbacks = hasEnabledCallbacks();
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.e;
        if (onBackInvokedDispatcher != null) {
            if (hasEnabledCallbacks && !this.f) {
                a.b(onBackInvokedDispatcher, 0, this.d);
                this.f = true;
            } else if (hasEnabledCallbacks || !this.f) {
            } else {
                a.c(onBackInvokedDispatcher, this.d);
                this.f = false;
            }
        }
    }

    @MainThread
    public boolean hasEnabledCallbacks() {
        Iterator<OnBackPressedCallback> descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (descendingIterator.next().isEnabled()) {
                return true;
            }
        }
        return false;
    }

    @MainThread
    public void onBackPressed() {
        Iterator<OnBackPressedCallback> descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext()) {
            OnBackPressedCallback next = descendingIterator.next();
            if (next.isEnabled()) {
                next.handleOnBackPressed();
                return;
            }
        }
        Runnable runnable = this.f343a;
        if (runnable != null) {
            runnable.run();
        }
    }

    @RequiresApi(33)
    public void setOnBackInvokedDispatcher(@NonNull OnBackInvokedDispatcher onBackInvokedDispatcher) {
        this.e = onBackInvokedDispatcher;
        d();
    }

    @OptIn(markerClass = {BuildCompat.PrereleaseSdkCheck.class})
    public OnBackPressedDispatcher(@Nullable Runnable runnable) {
        this.b = new ArrayDeque<>();
        this.f = false;
        this.f343a = runnable;
        if (BuildCompat.isAtLeastT()) {
            this.c = new Consumer() { // from class: androidx.activity.f
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    OnBackPressedDispatcher.this.c((Boolean) obj);
                }
            };
            this.d = a.a(new Runnable() { // from class: androidx.activity.g
                @Override // java.lang.Runnable
                public final void run() {
                    OnBackPressedDispatcher.this.onBackPressed();
                }
            });
        }
    }

    @OptIn(markerClass = {BuildCompat.PrereleaseSdkCheck.class})
    @SuppressLint({"LambdaLast"})
    @MainThread
    public void addCallback(@NonNull LifecycleOwner lifecycleOwner, @NonNull OnBackPressedCallback onBackPressedCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        onBackPressedCallback.addCancellable(new LifecycleOnBackPressedCancellable(lifecycle, onBackPressedCallback));
        if (BuildCompat.isAtLeastT()) {
            d();
            onBackPressedCallback.setIsEnabledConsumer(this.c);
        }
    }
}
