package kotlinx.coroutines.internal;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@PublishedApi
/* loaded from: classes12.dex */
public final class ThreadLocalKey implements CoroutineContext.Key<ThreadLocalElement<?>> {
    @NotNull
    public final ThreadLocal<?> h;

    public ThreadLocalKey(@NotNull ThreadLocal<?> threadLocal) {
        this.h = threadLocal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ThreadLocalKey copy$default(ThreadLocalKey threadLocalKey, ThreadLocal threadLocal, int i, Object obj) {
        if ((i & 1) != 0) {
            threadLocal = threadLocalKey.h;
        }
        return threadLocalKey.copy(threadLocal);
    }

    @NotNull
    public final ThreadLocalKey copy(@NotNull ThreadLocal<?> threadLocal) {
        return new ThreadLocalKey(threadLocal);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ThreadLocalKey) && Intrinsics.areEqual(this.h, ((ThreadLocalKey) obj).h);
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @NotNull
    public String toString() {
        return "ThreadLocalKey(threadLocal=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }
}
