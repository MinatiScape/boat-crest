package kotlinx.coroutines;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.ScopeCoroutine;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class i0<U, T extends U> extends ScopeCoroutine<T> implements Runnable {
    @JvmField
    public final long j;

    public i0(long j, @NotNull Continuation<? super U> continuation) {
        super(continuation.getContext(), continuation);
        this.j = j;
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport
    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        return super.nameString$kotlinx_coroutines_core() + "(timeMillis=" + this.j + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // java.lang.Runnable
    public void run() {
        cancelCoroutine(TimeoutKt.TimeoutCancellationException(this.j, this));
    }
}
