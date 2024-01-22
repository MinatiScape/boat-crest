package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes12.dex */
public final class UArraysKt___UArraysJvmKt$asList$3 extends AbstractList<UByte> implements RandomAccess {
    public final /* synthetic */ byte[] h;

    public UArraysKt___UArraysJvmKt$asList$3(byte[] bArr) {
        this.h = bArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return m285contains7apg3OU(((UByte) obj).m139unboximpl());
        }
        return false;
    }

    /* renamed from: contains-7apg3OU  reason: not valid java name */
    public boolean m285contains7apg3OU(byte b) {
        return UByteArray.m143contains7apg3OU(this.h, b);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i) {
        return UByte.m133boximpl(m286getw2LRezQ(i));
    }

    /* renamed from: get-w2LRezQ  reason: not valid java name */
    public byte m286getw2LRezQ(int i) {
        return UByteArray.m147getw2LRezQ(this.h, i);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UByteArray.m148getSizeimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UByte) {
            return m287indexOf7apg3OU(((UByte) obj).m139unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-7apg3OU  reason: not valid java name */
    public int m287indexOf7apg3OU(byte b) {
        return ArraysKt___ArraysKt.indexOf(this.h, b);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UByteArray.m150isEmptyimpl(this.h);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UByte) {
            return m288lastIndexOf7apg3OU(((UByte) obj).m139unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-7apg3OU  reason: not valid java name */
    public int m288lastIndexOf7apg3OU(byte b) {
        return ArraysKt___ArraysKt.lastIndexOf(this.h, b);
    }
}
