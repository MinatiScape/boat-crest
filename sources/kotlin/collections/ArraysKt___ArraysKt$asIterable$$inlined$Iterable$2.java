package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$2 implements Iterable<Byte>, KMappedMarker {
    public final /* synthetic */ byte[] h;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$2(byte[] bArr) {
        this.h = bArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Byte> iterator() {
        return ArrayIteratorsKt.iterator(this.h);
    }
}
