package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface FlowCollector<T> {
    @Nullable
    Object emit(T t, @NotNull Continuation<? super Unit> continuation);
}
