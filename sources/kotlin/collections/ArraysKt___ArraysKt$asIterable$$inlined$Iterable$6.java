package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$6 implements Iterable<Float>, KMappedMarker {
    public final /* synthetic */ float[] h;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$6(float[] fArr) {
        this.h = fArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Float> iterator() {
        return ArrayIteratorsKt.iterator(this.h);
    }
}
