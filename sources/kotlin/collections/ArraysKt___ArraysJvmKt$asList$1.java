package kotlin.collections;

import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysJvmKt$asList$1 extends AbstractList<Byte> implements RandomAccess {
    public final /* synthetic */ byte[] h;

    public ArraysKt___ArraysJvmKt$asList$1(byte[] bArr) {
        this.h = bArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Byte) {
            return contains(((Number) obj).byteValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Byte) {
            return indexOf(((Number) obj).byteValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.h.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Byte) {
            return lastIndexOf(((Number) obj).byteValue());
        }
        return -1;
    }

    public boolean contains(byte b) {
        return ArraysKt___ArraysKt.contains(this.h, b);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public Byte get(int i) {
        return Byte.valueOf(this.h[i]);
    }

    public int indexOf(byte b) {
        return ArraysKt___ArraysKt.indexOf(this.h, b);
    }

    public int lastIndexOf(byte b) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, b);
    }
}
