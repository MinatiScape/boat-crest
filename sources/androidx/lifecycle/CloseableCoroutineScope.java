package androidx.lifecycle;

import java.io.Closeable;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.u;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class CloseableCoroutineScope implements Closeable, CoroutineScope {
    @NotNull
    public final CoroutineContext h;

    public CloseableCoroutineScope(@NotNull CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.h = context;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        u.i(getCoroutineContext(), null, 1, null);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.h;
    }
}
