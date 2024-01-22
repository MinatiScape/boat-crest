package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class g extends JobNode {
    @NotNull
    public final Future<?> k;

    public g(@NotNull Future<?> future) {
        this.k = future;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        if (th != null) {
            this.k.cancel(false);
        }
    }
}
