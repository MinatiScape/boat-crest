package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$5 implements Iterable<Long>, KMappedMarker {
    public final /* synthetic */ long[] h;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$5(long[] jArr) {
        this.h = jArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Long> iterator() {
        return ArrayIteratorsKt.iterator(this.h);
    }
}
