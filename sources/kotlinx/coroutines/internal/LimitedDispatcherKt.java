package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes12.dex */
public final class LimitedDispatcherKt {
    public static final void checkParallelism(int i) {
        if (!(i >= 1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Expected positive parallelism level, but got ", Integer.valueOf(i)).toString());
        }
    }
}
