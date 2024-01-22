package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes12.dex */
public final class UArraysKt___UArraysJvmKt$asList$2 extends AbstractList<ULong> implements RandomAccess {
    public final /* synthetic */ long[] h;

    public UArraysKt___UArraysJvmKt$asList$2(long[] jArr) {
        this.h = jArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return m281containsVKZWuLQ(((ULong) obj).m187unboximpl());
        }
        return false;
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m281containsVKZWuLQ(long j) {
        return ULongArray.m191containsVKZWuLQ(this.h, j);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i) {
        return ULong.m181boximpl(m282getsVKNKU(i));
    }

    /* renamed from: get-s-VKNKU  reason: not valid java name */
    public long m282getsVKNKU(int i) {
        return ULongArray.m195getsVKNKU(this.h, i);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return ULongArray.m196getSizeimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ULong) {
            return m283indexOfVKZWuLQ(((ULong) obj).m187unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-VKZWuLQ  reason: not valid java name */
    public int m283indexOfVKZWuLQ(long j) {
        return ArraysKt___ArraysKt.indexOf(this.h, j);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return ULongArray.m198isEmptyimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ULong) {
            return m284lastIndexOfVKZWuLQ(((ULong) obj).m187unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-VKZWuLQ  reason: not valid java name */
    public int m284lastIndexOfVKZWuLQ(long j) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, j);
    }
}
