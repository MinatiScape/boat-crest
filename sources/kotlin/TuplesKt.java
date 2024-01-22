package kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "TuplesKt")
/* loaded from: classes12.dex */
public final class TuplesKt {
    @NotNull
    public static final <A, B> Pair<A, B> to(A a2, B b) {
        return new Pair<>(a2, b);
    }

    @NotNull
    public static final <T> List<T> toList(@NotNull Pair<? extends T, ? extends T> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return CollectionsKt__CollectionsKt.listOf(pair.getFirst(), pair.getSecond());
    }

    @NotNull
    public static final <T> List<T> toList(@NotNull Triple<? extends T, ? extends T, ? extends T> triple) {
        Intrinsics.checkNotNullParameter(triple, "<this>");
        return CollectionsKt__CollectionsKt.listOf(triple.getFirst(), triple.getSecond(), triple.getThird());
    }
}
