package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.h;
import kotlin.time.DurationKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.NonDisposableHandle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class HandlerContext extends HandlerDispatcher {
    @Nullable
    private volatile HandlerContext _immediate;
    @NotNull
    public final Handler h;
    @Nullable
    public final String i;
    public final boolean j;
    @NotNull
    public final HandlerContext k;

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ Runnable $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Runnable runnable) {
            super(1);
            this.$block = runnable;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            HandlerContext.this.h.removeCallbacks(this.$block);
        }
    }

    public HandlerContext(Handler handler, String str, boolean z) {
        super(null);
        this.h = handler;
        this.i = str;
        this.j = z;
        this._immediate = z ? this : null;
        HandlerContext handlerContext = this._immediate;
        if (handlerContext == null) {
            handlerContext = new HandlerContext(handler, str, true);
            this._immediate = handlerContext;
        }
        this.k = handlerContext;
    }

    public static final void c(HandlerContext handlerContext, Runnable runnable) {
        handlerContext.h.removeCallbacks(runnable);
    }

    public final void b(CoroutineContext coroutineContext, Runnable runnable) {
        JobKt.cancel(coroutineContext, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        Dispatchers.getIO().dispatch(coroutineContext, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        if (this.h.post(runnable)) {
            return;
        }
        b(coroutineContext, runnable);
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof HandlerContext) && ((HandlerContext) obj).h == this.h;
    }

    public int hashCode() {
        return System.identityHashCode(this.h);
    }

    @Override // kotlinx.coroutines.android.HandlerDispatcher, kotlinx.coroutines.Delay
    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull final Runnable runnable, @NotNull CoroutineContext coroutineContext) {
        if (this.h.postDelayed(runnable, h.coerceAtMost(j, (long) DurationKt.MAX_MILLIS))) {
            return new DisposableHandle() { // from class: kotlinx.coroutines.android.a
                @Override // kotlinx.coroutines.DisposableHandle
                public final void dispose() {
                    HandlerContext.c(HandlerContext.this, runnable);
                }
            };
        }
        b(coroutineContext, runnable);
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(@NotNull CoroutineContext coroutineContext) {
        return (this.j && Intrinsics.areEqual(Looper.myLooper(), this.h.getLooper())) ? false : true;
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long j, @NotNull final CancellableContinuation<? super Unit> cancellableContinuation) {
        Runnable runnable = new Runnable() { // from class: kotlinx.coroutines.android.HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                CancellableContinuation.this.resumeUndispatched(this, Unit.INSTANCE);
            }
        };
        if (this.h.postDelayed(runnable, h.coerceAtMost(j, (long) DurationKt.MAX_MILLIS))) {
            cancellableContinuation.invokeOnCancellation(new a(runnable));
        } else {
            b(cancellableContinuation.getContext(), runnable);
        }
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        String stringInternalImpl = toStringInternalImpl();
        if (stringInternalImpl == null) {
            String str = this.i;
            if (str == null) {
                str = this.h.toString();
            }
            return this.j ? Intrinsics.stringPlus(str, ".immediate") : str;
        }
        return stringInternalImpl;
    }

    @Override // kotlinx.coroutines.android.HandlerDispatcher, kotlinx.coroutines.MainCoroutineDispatcher
    @NotNull
    public HandlerContext getImmediate() {
        return this.k;
    }

    public /* synthetic */ HandlerContext(Handler handler, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(handler, (i & 2) != 0 ? null : str);
    }

    public HandlerContext(@NotNull Handler handler, @Nullable String str) {
        this(handler, str, false);
    }
}
