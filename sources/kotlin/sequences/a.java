package kotlin.sequences;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class a<T, K> extends AbstractIterator<T> {
    @NotNull
    public final Iterator<T> j;
    @NotNull
    public final Function1<T, K> k;
    @NotNull
    public final HashSet<K> l;

    /* JADX WARN: Multi-variable type inference failed */
    public a(@NotNull Iterator<? extends T> source, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        this.j = source;
        this.k = keySelector;
        this.l = new HashSet<>();
    }

    @Override // kotlin.collections.AbstractIterator
    public void computeNext() {
        while (this.j.hasNext()) {
            T next = this.j.next();
            if (this.l.add(this.k.invoke(next))) {
                setNext(next);
                return;
            }
        }
        done();
    }
}
