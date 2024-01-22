package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class h0 implements Function1<Throwable, Unit> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater k = AtomicIntegerFieldUpdater.newUpdater(h0.class, "_state");
    @NotNull
    public final Job h;
    @Nullable
    public DisposableHandle j;
    @NotNull
    private volatile /* synthetic */ int _state = 0;
    public final Thread i = Thread.currentThread();

    public h0(@NotNull Job job) {
        this.h = job;
    }

    public final void a() {
        while (true) {
            int i = this._state;
            if (i != 0) {
                if (i != 2) {
                    if (i == 3) {
                        Thread.interrupted();
                        return;
                    } else {
                        b(i);
                        throw new KotlinNothingValueException();
                    }
                }
            } else if (k.compareAndSet(this, i, 1)) {
                DisposableHandle disposableHandle = this.j;
                if (disposableHandle == null) {
                    return;
                }
                disposableHandle.dispose();
                return;
            }
        }
    }

    public final Void b(int i) {
        throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", Integer.valueOf(i)).toString());
    }

    public void c(@Nullable Throwable th) {
        int i;
        do {
            i = this._state;
            if (i != 0) {
                if (i == 1 || i == 2 || i == 3) {
                    return;
                }
                b(i);
                throw new KotlinNothingValueException();
            }
        } while (!k.compareAndSet(this, i, 2));
        this.i.interrupt();
        this._state = 3;
    }

    public final void d() {
        int i;
        this.j = this.h.invokeOnCompletion(true, true, this);
        do {
            i = this._state;
            if (i != 0) {
                if (i == 2 || i == 3) {
                    return;
                }
                b(i);
                throw new KotlinNothingValueException();
            }
        } while (!k.compareAndSet(this, i, 0));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        c(th);
        return Unit.INSTANCE;
    }
}
