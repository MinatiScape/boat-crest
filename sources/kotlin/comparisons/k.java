package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class k<T> implements Comparator<T> {
    @NotNull
    public final Comparator<T> h;

    public k(@NotNull Comparator<T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        this.h = comparator;
    }

    @NotNull
    public final Comparator<T> a() {
        return this.h;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        return this.h.compare(t2, t);
    }

    @Override // java.util.Comparator
    @NotNull
    public final Comparator<T> reversed() {
        return this.h;
    }
}
