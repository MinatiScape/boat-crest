package kotlin.sequences;

import java.util.Collection;
import java.util.Iterator;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.3")
@RestrictsSuspension
/* loaded from: classes12.dex */
public abstract class SequenceScope<T> {
    @Nullable
    public abstract Object yield(T t, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public final Object yieldAll(@NotNull Iterable<? extends T> iterable, @NotNull Continuation<? super Unit> continuation) {
        Object yieldAll;
        return (!((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) && (yieldAll = yieldAll(iterable.iterator(), continuation)) == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) ? yieldAll : Unit.INSTANCE;
    }

    @Nullable
    public abstract Object yieldAll(@NotNull Iterator<? extends T> it, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public final Object yieldAll(@NotNull Sequence<? extends T> sequence, @NotNull Continuation<? super Unit> continuation) {
        Object yieldAll = yieldAll(sequence.iterator(), continuation);
        return yieldAll == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? yieldAll : Unit.INSTANCE;
    }
}
