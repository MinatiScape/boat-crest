package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class r extends JobCancellingNode {
    public static final /* synthetic */ AtomicIntegerFieldUpdater l = AtomicIntegerFieldUpdater.newUpdater(r.class, "_invoked");
    @NotNull
    private volatile /* synthetic */ int _invoked = 0;
    @NotNull
    public final Function1<Throwable, Unit> k;

    /* JADX WARN: Multi-variable type inference failed */
    public r(@NotNull Function1<? super Throwable, Unit> function1) {
        this.k = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        if (l.compareAndSet(this, 0, 1)) {
            this.k.invoke(th);
        }
    }
}
