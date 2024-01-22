package kotlinx.coroutines.channels;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class SendElement<E> extends Send {
    @JvmField
    @NotNull
    public final CancellableContinuation<Unit> cont;
    public final E k;

    /* JADX WARN: Multi-variable type inference failed */
    public SendElement(E e, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        this.k = e;
        this.cont = cancellableContinuation;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
        this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
    }

    @Override // kotlinx.coroutines.channels.Send
    public E getPollResult() {
        return this.k;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(@NotNull Closed<?> closed) {
        CancellableContinuation<Unit> cancellableContinuation = this.cont;
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(closed.getSendException())));
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + HexStringBuilder.COMMENT_BEGIN_CHAR + getPollResult() + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // kotlinx.coroutines.channels.Send
    @Nullable
    public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
        Object tryResume = this.cont.tryResume(Unit.INSTANCE, prepareOp == null ? null : prepareOp.desc);
        if (tryResume == null) {
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(tryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        if (prepareOp != null) {
            prepareOp.finishPrepare();
        }
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }
}
