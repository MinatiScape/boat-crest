package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;
/* loaded from: classes12.dex */
public final class ComparisonsKt__ComparisonsKt$thenByDescending$1<T> implements Comparator {
    public final /* synthetic */ Comparator<T> h;
    public final /* synthetic */ Function1<T, Comparable<?>> i;

    /* JADX WARN: Multi-variable type inference failed */
    public ComparisonsKt__ComparisonsKt$thenByDescending$1(Comparator<T> comparator, Function1<? super T, ? extends Comparable<?>> function1) {
        this.h = comparator;
        this.i = function1;
    }

    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        int compare = this.h.compare(t, t2);
        if (compare != 0) {
            return compare;
        }
        Function1<T, Comparable<?>> function1 = this.i;
        return f.compareValues(function1.invoke(t2), function1.invoke(t));
    }
}
