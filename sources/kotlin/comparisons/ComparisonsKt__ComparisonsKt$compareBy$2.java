package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;
/* loaded from: classes12.dex */
public final class ComparisonsKt__ComparisonsKt$compareBy$2<T> implements Comparator {
    public final /* synthetic */ Function1<T, Comparable<?>> h;

    /* JADX WARN: Multi-variable type inference failed */
    public ComparisonsKt__ComparisonsKt$compareBy$2(Function1<? super T, ? extends Comparable<?>> function1) {
        this.h = function1;
    }

    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        Function1<T, Comparable<?>> function1 = this.h;
        return f.compareValues(function1.invoke(t), function1.invoke(t2));
    }
}
