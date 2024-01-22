package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;
/* loaded from: classes12.dex */
public final class ComparisonsKt__ComparisonsKt$compareBy$3<T> implements Comparator {
    public final /* synthetic */ Comparator<Object> h;
    public final /* synthetic */ Function1<T, Object> i;

    /* JADX WARN: Multi-variable type inference failed */
    public ComparisonsKt__ComparisonsKt$compareBy$3(Comparator<Object> comparator, Function1<? super T, Object> function1) {
        this.h = comparator;
        this.i = function1;
    }

    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        Comparator<Object> comparator = this.h;
        Function1<T, Object> function1 = this.i;
        return comparator.compare(function1.invoke(t), function1.invoke(t2));
    }
}
