package kotlinx.coroutines;

import kotlin.Unit;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class x extends BeforeResumeCancelHandler {
    @NotNull
    public final LockFreeLinkedListNode h;

    public x(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.h = lockFreeLinkedListNode;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @NotNull
    public String toString() {
        return "RemoveOnCancel[" + this.h + ']';
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        this.h.mo775remove();
    }
}
