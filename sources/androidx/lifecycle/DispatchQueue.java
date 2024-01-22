package androidx.lifecycle;

import android.annotation.SuppressLint;
import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import java.util.ArrayDeque;
import java.util.Queue;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class DispatchQueue {
    public boolean b;
    public boolean c;

    /* renamed from: a  reason: collision with root package name */
    public boolean f1350a = true;
    @NotNull
    public final Queue<Runnable> d = new ArrayDeque();

    public static final void b(DispatchQueue this$0, Runnable runnable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        this$0.c(runnable);
    }

    @MainThread
    public final void c(Runnable runnable) {
        if (this.d.offer(runnable)) {
            drainQueue();
            return;
        }
        throw new IllegalStateException("cannot enqueue any more runnables".toString());
    }

    @MainThread
    public final boolean canRun() {
        return this.b || !this.f1350a;
    }

    @AnyThread
    @SuppressLint({"WrongThread"})
    public final void dispatchAndEnqueue(@NotNull CoroutineContext context, @NotNull final Runnable runnable) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        if (!immediate.isDispatchNeeded(context) && !canRun()) {
            c(runnable);
        } else {
            immediate.dispatch(context, new Runnable() { // from class: androidx.lifecycle.b
                @Override // java.lang.Runnable
                public final void run() {
                    DispatchQueue.b(DispatchQueue.this, runnable);
                }
            });
        }
    }

    @MainThread
    public final void drainQueue() {
        if (this.c) {
            return;
        }
        try {
            this.c = true;
            while ((!this.d.isEmpty()) && canRun()) {
                Runnable poll = this.d.poll();
                if (poll != null) {
                    poll.run();
                }
            }
        } finally {
            this.c = false;
        }
    }

    @MainThread
    public final void finish() {
        this.b = true;
        drainQueue();
    }

    @MainThread
    public final void pause() {
        this.f1350a = true;
    }

    @MainThread
    public final void resume() {
        if (this.f1350a) {
            if (!this.b) {
                this.f1350a = false;
                drainQueue();
                return;
            }
            throw new IllegalStateException("Cannot resume a finished dispatcher".toString());
        }
    }
}
