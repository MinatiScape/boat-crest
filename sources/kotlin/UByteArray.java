package kotlin;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
@ExperimentalUnsignedTypes
@JvmInline
/* loaded from: classes12.dex */
public final class UByteArray implements Collection<UByte>, KMappedMarker {
    @NotNull
    public final byte[] h;

    /* loaded from: classes12.dex */
    public static final class a implements Iterator<UByte>, KMappedMarker {
        @NotNull
        public final byte[] h;
        public int i;

        public a(@NotNull byte[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            this.h = array;
        }

        public byte a() {
            int i = this.i;
            byte[] bArr = this.h;
            if (i < bArr.length) {
                this.i = i + 1;
                return UByte.m134constructorimpl(bArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.i));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i < this.h.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ UByte next() {
            return UByte.m133boximpl(a());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @PublishedApi
    public /* synthetic */ UByteArray(byte[] bArr) {
        this.h = bArr;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UByteArray m140boximpl(byte[] bArr) {
        return new UByteArray(bArr);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte[] m141constructorimpl(int i) {
        return m142constructorimpl(new byte[i]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte[] m142constructorimpl(@NotNull byte[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        return storage;
    }

    /* renamed from: containsAll-impl  reason: not valid java name */
    public static boolean m144containsAllimpl(byte[] bArr, @NotNull Collection<UByte> elements) {
        boolean z;
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (!elements.isEmpty()) {
            for (Object obj : elements) {
                if ((obj instanceof UByte) && ArraysKt___ArraysKt.contains(bArr, ((UByte) obj).m139unboximpl())) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m145equalsimpl(byte[] bArr, Object obj) {
        return (obj instanceof UByteArray) && Intrinsics.areEqual(bArr, ((UByteArray) obj).m156unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m146equalsimpl0(byte[] bArr, byte[] bArr2) {
        return Intrinsics.areEqual(bArr, bArr2);
    }

    /* renamed from: get-w2LRezQ  reason: not valid java name */
    public static final byte m147getw2LRezQ(byte[] bArr, int i) {
        return UByte.m134constructorimpl(bArr[i]);
    }

    /* renamed from: getSize-impl  reason: not valid java name */
    public static int m148getSizeimpl(byte[] bArr) {
        return bArr.length;
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m149hashCodeimpl(byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    /* renamed from: isEmpty-impl  reason: not valid java name */
    public static boolean m150isEmptyimpl(byte[] bArr) {
        return bArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl  reason: not valid java name */
    public static Iterator<UByte> m151iteratorimpl(byte[] bArr) {
        return new a(bArr);
    }

    /* renamed from: set-VurrAj0  reason: not valid java name */
    public static final void m152setVurrAj0(byte[] bArr, int i, byte b) {
        bArr[i] = b;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m153toStringimpl(byte[] bArr) {
        return "UByteArray(storage=" + Arrays.toString(bArr) + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UByte uByte) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-7apg3OU  reason: not valid java name */
    public boolean m154add7apg3OU(byte b) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UByte> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return m155contains7apg3OU(((UByte) obj).m139unboximpl());
        }
        return false;
    }

    /* renamed from: contains-7apg3OU  reason: not valid java name */
    public boolean m155contains7apg3OU(byte b) {
        return m143contains7apg3OU(this.h, b);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return m144containsAllimpl(this.h, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m145equalsimpl(this.h, obj);
    }

    @Override // java.util.Collection
    /* renamed from: getSize */
    public int size() {
        return m148getSizeimpl(this.h);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m149hashCodeimpl(this.h);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m150isEmptyimpl(this.h);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<UByte> iterator() {
        return m151iteratorimpl(this.h);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    public String toString() {
        return m153toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte[] m156unboximpl() {
        return this.h;
    }

    /* renamed from: contains-7apg3OU  reason: not valid java name */
    public static boolean m143contains7apg3OU(byte[] bArr, byte b) {
        return ArraysKt___ArraysKt.contains(bArr, b);
    }
}
