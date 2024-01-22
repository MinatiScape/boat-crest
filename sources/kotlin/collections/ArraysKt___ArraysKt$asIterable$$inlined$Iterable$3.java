package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$3 implements Iterable<Short>, KMappedMarker {
    public final /* synthetic */ short[] h;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$3(short[] sArr) {
        this.h = sArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Short> iterator() {
        return ArrayIteratorsKt.iterator(this.h);
    }
}
