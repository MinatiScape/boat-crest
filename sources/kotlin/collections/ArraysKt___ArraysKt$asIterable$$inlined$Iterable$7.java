package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$7 implements Iterable<Double>, KMappedMarker {
    public final /* synthetic */ double[] h;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$7(double[] dArr) {
        this.h = dArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Double> iterator() {
        return ArrayIteratorsKt.iterator(this.h);
    }
}
