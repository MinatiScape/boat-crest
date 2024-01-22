package kotlinx.coroutines.channels;

import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class Send extends LockFreeLinkedListNode {
    public abstract void completeResumeSend();

    @Nullable
    public abstract Object getPollResult();

    public abstract void resumeSendClosed(@NotNull Closed<?> closed);

    @Nullable
    public abstract Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp);

    public void undeliveredElement() {
    }
}
