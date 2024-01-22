package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function2;
/* loaded from: classes12.dex */
public final class ComparisonsKt__ComparisonsKt$thenComparator$1<T> implements Comparator {
    public final /* synthetic */ Comparator<T> h;
    public final /* synthetic */ Function2<T, T, Integer> i;

    /* JADX WARN: Multi-variable type inference failed */
    public ComparisonsKt__ComparisonsKt$thenComparator$1(Comparator<T> comparator, Function2<? super T, ? super T, Integer> function2) {
        this.h = comparator;
        this.i = function2;
    }

    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        int compare = this.h.compare(t, t2);
        return compare != 0 ? compare : this.i.invoke(t, t2).intValue();
    }
}
