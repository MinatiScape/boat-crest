package kotlin.collections;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public class y<T> extends AbstractList<T> {
    @NotNull
    public final List<T> h;

    /* JADX WARN: Multi-variable type inference failed */
    public y(@NotNull List<? extends T> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.h = delegate;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public T get(int i) {
        int e;
        List<T> list = this.h;
        e = j.e(this, i);
        return list.get(e);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h.size();
    }
}
