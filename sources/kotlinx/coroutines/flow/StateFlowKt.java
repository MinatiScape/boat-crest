package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class StateFlowKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Symbol f14160a = new Symbol("NONE");
    @NotNull
    public static final Symbol b = new Symbol("PENDING");

    @NotNull
    public static final <T> MutableStateFlow<T> MutableStateFlow(T t) {
        if (t == null) {
            t = (T) NullSurrogateKt.NULL;
        }
        return new s(t);
    }

    @NotNull
    public static final <T> Flow<T> fuseStateFlow(@NotNull StateFlow<? extends T> stateFlow, @NotNull CoroutineContext coroutineContext, int i, @NotNull BufferOverflow bufferOverflow) {
        boolean z = true;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(i != -1)) {
                throw new AssertionError();
            }
        }
        if (i < 0 || i >= 2) {
            z = false;
        }
        return ((z || i == -2) && bufferOverflow == BufferOverflow.DROP_OLDEST) ? stateFlow : SharedFlowKt.fuseSharedFlow(stateFlow, coroutineContext, i, bufferOverflow);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.Object] */
    public static final <T> T getAndUpdate(@NotNull MutableStateFlow<T> mutableStateFlow, @NotNull Function1<? super T, ? extends T> function1) {
        ?? r0;
        do {
            r0 = (Object) mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(r0, function1.invoke(r0)));
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void update(@NotNull MutableStateFlow<T> mutableStateFlow, @NotNull Function1<? super T, ? extends T> function1) {
        Object obj;
        do {
            obj = (Object) mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(obj, function1.invoke(obj)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T updateAndGet(@NotNull MutableStateFlow<T> mutableStateFlow, @NotNull Function1<? super T, ? extends T> function1) {
        Object obj;
        T invoke;
        do {
            obj = (Object) mutableStateFlow.getValue();
            invoke = function1.invoke(obj);
        } while (!mutableStateFlow.compareAndSet(obj, invoke));
        return invoke;
    }
}
