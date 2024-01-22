package kotlinx.coroutines.internal;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ContextScope implements CoroutineScope {
    @NotNull
    public final CoroutineContext h;

    public ContextScope(@NotNull CoroutineContext coroutineContext) {
        this.h = coroutineContext;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.h;
    }

    @NotNull
    public String toString() {
        return "CoroutineScope(coroutineContext=" + getCoroutineContext() + HexStringBuilder.COMMENT_END_CHAR;
    }
}
