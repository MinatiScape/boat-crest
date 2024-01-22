package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public abstract class CtorCache {
    @NotNull
    public abstract Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls);
}
