package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class f implements Flow {
    @NotNull
    public static final f h = new f();

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<?> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
