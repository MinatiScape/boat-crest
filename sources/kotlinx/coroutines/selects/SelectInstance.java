package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@InternalCoroutinesApi
/* loaded from: classes12.dex */
public interface SelectInstance<R> {
    void disposeOnSelect(@NotNull DisposableHandle disposableHandle);

    @NotNull
    Continuation<R> getCompletion();

    boolean isSelected();

    @Nullable
    Object performAtomicTrySelect(@NotNull AtomicDesc atomicDesc);

    void resumeSelectWithException(@NotNull Throwable th);

    boolean trySelect();

    @Nullable
    Object trySelectOther(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp);
}
