package kotlin.collections;

import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysJvmKt$asList$7 extends AbstractList<Boolean> implements RandomAccess {
    public final /* synthetic */ boolean[] h;

    public ArraysKt___ArraysJvmKt$asList$7(boolean[] zArr) {
        this.h = zArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Boolean) {
            return contains(((Boolean) obj).booleanValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Boolean) {
            return indexOf(((Boolean) obj).booleanValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.h.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Boolean) {
            return lastIndexOf(((Boolean) obj).booleanValue());
        }
        return -1;
    }

    public boolean contains(boolean z) {
        return ArraysKt___ArraysKt.contains(this.h, z);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public Boolean get(int i) {
        return Boolean.valueOf(this.h[i]);
    }

    public int indexOf(boolean z) {
        return ArraysKt___ArraysKt.indexOf(this.h, z);
    }

    public int lastIndexOf(boolean z) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, z);
    }
}
