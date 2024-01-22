package androidx.work;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CancellableContinuation;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\n"}, d2 = {"R", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class ListenableFutureKt$await$2$1 implements Runnable {
    public final /* synthetic */ CancellableContinuation<R> h;
    public final /* synthetic */ ListenableFuture<R> i;

    /* JADX WARN: Multi-variable type inference failed */
    public ListenableFutureKt$await$2$1(CancellableContinuation<? super R> cancellableContinuation, ListenableFuture<R> listenableFuture) {
        this.h = cancellableContinuation;
        this.i = listenableFuture;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Continuation continuation = this.h;
            Object obj = this.i.get();
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m123constructorimpl(obj));
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause == null) {
                cause = th;
            }
            if (th instanceof CancellationException) {
                this.h.cancel(cause);
                return;
            }
            Continuation continuation2 = this.h;
            Result.Companion companion2 = Result.Companion;
            continuation2.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(cause)));
        }
    }
}
