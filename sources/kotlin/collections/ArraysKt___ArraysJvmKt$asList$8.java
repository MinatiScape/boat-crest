package kotlin.collections;

import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArraysKt___ArraysJvmKt$asList$8 extends AbstractList<Character> implements RandomAccess {
    public final /* synthetic */ char[] h;

    public ArraysKt___ArraysJvmKt$asList$8(char[] cArr) {
        this.h = cArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Character) {
            return contains(((Character) obj).charValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Character) {
            return indexOf(((Character) obj).charValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.h.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Character) {
            return lastIndexOf(((Character) obj).charValue());
        }
        return -1;
    }

    public boolean contains(char c) {
        return ArraysKt___ArraysKt.contains(this.h, c);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public Character get(int i) {
        return Character.valueOf(this.h[i]);
    }

    public int indexOf(char c) {
        return ArraysKt___ArraysKt.indexOf(this.h, c);
    }

    public int lastIndexOf(char c) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, c);
    }
}
