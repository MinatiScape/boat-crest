package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes12.dex */
public final class UArraysKt___UArraysJvmKt$asList$1 extends AbstractList<UInt> implements RandomAccess {
    public final /* synthetic */ int[] h;

    public UArraysKt___UArraysJvmKt$asList$1(int[] iArr) {
        this.h = iArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return m277containsWZ4Q5Ns(((UInt) obj).m163unboximpl());
        }
        return false;
    }

    /* renamed from: contains-WZ4Q5Ns  reason: not valid java name */
    public boolean m277containsWZ4Q5Ns(int i) {
        return UIntArray.m167containsWZ4Q5Ns(this.h, i);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i) {
        return UInt.m157boximpl(m278getpVg5ArA(i));
    }

    /* renamed from: get-pVg5ArA  reason: not valid java name */
    public int m278getpVg5ArA(int i) {
        return UIntArray.m171getpVg5ArA(this.h, i);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UIntArray.m172getSizeimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UInt) {
            return m279indexOfWZ4Q5Ns(((UInt) obj).m163unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-WZ4Q5Ns  reason: not valid java name */
    public int m279indexOfWZ4Q5Ns(int i) {
        return ArraysKt___ArraysKt.indexOf(this.h, i);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UIntArray.m174isEmptyimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UInt) {
            return m280lastIndexOfWZ4Q5Ns(((UInt) obj).m163unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-WZ4Q5Ns  reason: not valid java name */
    public int m280lastIndexOfWZ4Q5Ns(int i) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, i);
    }
}
