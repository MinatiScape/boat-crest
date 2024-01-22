package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface CoroutineScope {
    @NotNull
    CoroutineContext getCoroutineContext();
}
