package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ExceptionsKt {
    @NotNull
    public static final CancellationException CancellationException(@Nullable String str, @Nullable Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    public static final void addSuppressedThrowable(@NotNull Throwable th, @NotNull Throwable th2) {
        kotlin.a.addSuppressed(th, th2);
    }
}
