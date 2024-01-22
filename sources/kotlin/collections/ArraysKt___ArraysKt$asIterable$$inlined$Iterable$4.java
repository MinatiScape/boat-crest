package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$4 implements Iterable<Integer>, KMappedMarker {
    public final /* synthetic */ int[] h;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$4(int[] iArr) {
        this.h = iArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Integer> iterator() {
        return ArrayIteratorsKt.iterator(this.h);
    }
}
