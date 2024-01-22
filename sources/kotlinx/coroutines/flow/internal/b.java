package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class b implements Continuation<Object> {
    @NotNull
    public static final b h = new b();
    @NotNull
    public static final CoroutineContext i = EmptyCoroutineContext.INSTANCE;

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return i;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
    }
}
