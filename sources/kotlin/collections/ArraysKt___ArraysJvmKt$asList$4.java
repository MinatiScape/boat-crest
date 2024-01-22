package kotlin.collections;

import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysJvmKt$asList$4 extends AbstractList<Long> implements RandomAccess {
    public final /* synthetic */ long[] h;

    public ArraysKt___ArraysJvmKt$asList$4(long[] jArr) {
        this.h = jArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Long) {
            return contains(((Number) obj).longValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Long) {
            return indexOf(((Number) obj).longValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.h.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Long) {
            return lastIndexOf(((Number) obj).longValue());
        }
        return -1;
    }

    public boolean contains(long j) {
        return ArraysKt___ArraysKt.contains(this.h, j);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public Long get(int i) {
        return Long.valueOf(this.h[i]);
    }

    public int indexOf(long j) {
        return ArraysKt___ArraysKt.indexOf(this.h, j);
    }

    public int lastIndexOf(long j) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, j);
    }
}
