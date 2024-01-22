package kotlin.collections;

import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysJvmKt$asList$3 extends AbstractList<Integer> implements RandomAccess {
    public final /* synthetic */ int[] h;

    public ArraysKt___ArraysJvmKt$asList$3(int[] iArr) {
        this.h = iArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Integer) {
            return contains(((Number) obj).intValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Integer) {
            return indexOf(((Number) obj).intValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.h.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Integer) {
            return lastIndexOf(((Number) obj).intValue());
        }
        return -1;
    }

    public boolean contains(int i) {
        return ArraysKt___ArraysKt.contains(this.h, i);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public Integer get(int i) {
        return Integer.valueOf(this.h[i]);
    }

    public int indexOf(int i) {
        return ArraysKt___ArraysKt.indexOf(this.h, i);
    }

    public int lastIndexOf(int i) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, i);
    }
}
