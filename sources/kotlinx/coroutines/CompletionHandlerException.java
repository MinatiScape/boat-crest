package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
@InternalCoroutinesApi
/* loaded from: classes12.dex */
public final class CompletionHandlerException extends RuntimeException {
    public CompletionHandlerException(@NotNull String str, @NotNull Throwable th) {
        super(str, th);
    }
}
