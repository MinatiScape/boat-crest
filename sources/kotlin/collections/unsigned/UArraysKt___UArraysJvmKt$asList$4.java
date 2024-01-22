package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes12.dex */
public final class UArraysKt___UArraysJvmKt$asList$4 extends AbstractList<UShort> implements RandomAccess {
    public final /* synthetic */ short[] h;

    public UArraysKt___UArraysJvmKt$asList$4(short[] sArr) {
        this.h = sArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return m289containsxj2QHRw(((UShort) obj).m211unboximpl());
        }
        return false;
    }

    /* renamed from: contains-xj2QHRw  reason: not valid java name */
    public boolean m289containsxj2QHRw(short s) {
        return UShortArray.m215containsxj2QHRw(this.h, s);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i) {
        return UShort.m205boximpl(m290getMh2AYeg(i));
    }

    /* renamed from: get-Mh2AYeg  reason: not valid java name */
    public short m290getMh2AYeg(int i) {
        return UShortArray.m219getMh2AYeg(this.h, i);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UShortArray.m220getSizeimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UShort) {
            return m291indexOfxj2QHRw(((UShort) obj).m211unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-xj2QHRw  reason: not valid java name */
    public int m291indexOfxj2QHRw(short s) {
        return ArraysKt___ArraysKt.indexOf(this.h, s);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UShortArray.m222isEmptyimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UShort) {
            return m292lastIndexOfxj2QHRw(((UShort) obj).m211unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-xj2QHRw  reason: not valid java name */
    public int m292lastIndexOfxj2QHRw(short s) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, s);
    }
}
