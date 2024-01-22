package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;
/* loaded from: classes12.dex */
public final class ComparisonsKt__ComparisonsKt$thenByDescending$2<T> implements Comparator {
    public final /* synthetic */ Comparator<T> h;
    public final /* synthetic */ Comparator<Object> i;
    public final /* synthetic */ Function1<T, Object> j;

    /* JADX WARN: Multi-variable type inference failed */
    public ComparisonsKt__ComparisonsKt$thenByDescending$2(Comparator<T> comparator, Comparator<Object> comparator2, Function1<? super T, Object> function1) {
        this.h = comparator;
        this.i = comparator2;
        this.j = function1;
    }

    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        int compare = this.h.compare(t, t2);
        if (compare != 0) {
            return compare;
        }
        Comparator<Object> comparator = this.i;
        Function1<T, Object> function1 = this.j;
        return comparator.compare(function1.invoke(t2), function1.invoke(t));
    }
}
