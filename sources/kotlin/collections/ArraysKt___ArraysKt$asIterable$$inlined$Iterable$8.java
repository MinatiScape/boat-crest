package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$8 implements Iterable<Boolean>, KMappedMarker {
    public final /* synthetic */ boolean[] h;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$8(boolean[] zArr) {
        this.h = zArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Boolean> iterator() {
        return ArrayIteratorsKt.iterator(this.h);
    }
}
