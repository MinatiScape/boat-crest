package kotlinx.coroutines.debug.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ConcurrentWeakMapKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Symbol f14153a = new Symbol("REHASH");
    @NotNull
    public static final b b = new b(null);
    @NotNull
    public static final b c = new b(Boolean.TRUE);

    public static final b a(Object obj) {
        if (obj == null) {
            return b;
        }
        return Intrinsics.areEqual(obj, Boolean.TRUE) ? c : new b(obj);
    }

    public static final Void b() {
        throw new UnsupportedOperationException("not implemented");
    }
}
