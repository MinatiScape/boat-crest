package kotlin.collections;

import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysJvmKt$asList$2 extends AbstractList<Short> implements RandomAccess {
    public final /* synthetic */ short[] h;

    public ArraysKt___ArraysJvmKt$asList$2(short[] sArr) {
        this.h = sArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Short) {
            return contains(((Number) obj).shortValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Short) {
            return indexOf(((Number) obj).shortValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.h.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Short) {
            return lastIndexOf(((Number) obj).shortValue());
        }
        return -1;
    }

    public boolean contains(short s) {
        return ArraysKt___ArraysKt.contains(this.h, s);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public Short get(int i) {
        return Short.valueOf(this.h[i]);
    }

    public int indexOf(short s) {
        return ArraysKt___ArraysKt.indexOf(this.h, s);
    }

    public int lastIndexOf(short s) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, s);
    }
}
