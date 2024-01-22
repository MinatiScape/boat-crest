package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;
@DelicateCoroutinesApi
/* loaded from: classes12.dex */
public final class GlobalScope implements CoroutineScope {
    @NotNull
    public static final GlobalScope INSTANCE = new GlobalScope();

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
