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
public final class UIntArray implements Collection<UInt>, KMappedMarker {
    @NotNull
    public final int[] h;

    /* loaded from: classes12.dex */
    public static final class a implements Iterator<UInt>, KMappedMarker {
        @NotNull
        public final int[] h;
        public int i;

        public a(@NotNull int[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            this.h = array;
        }

        public int a() {
            int i = this.i;
            int[] iArr = this.h;
            if (i < iArr.length) {
                this.i = i + 1;
                return UInt.m158constructorimpl(iArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.i));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i < this.h.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ UInt next() {
            return UInt.m157boximpl(a());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @PublishedApi
    public /* synthetic */ UIntArray(int[] iArr) {
        this.h = iArr;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UIntArray m164boximpl(int[] iArr) {
        return new UIntArray(iArr);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static int[] m165constructorimpl(int i) {
        return m166constructorimpl(new int[i]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static int[] m166constructorimpl(@NotNull int[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        return storage;
    }

    /* renamed from: containsAll-impl  reason: not valid java name */
    public static boolean m168containsAllimpl(int[] iArr, @NotNull Collection<UInt> elements) {
        boolean z;
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (!elements.isEmpty()) {
            for (Object obj : elements) {
                if ((obj instanceof UInt) && ArraysKt___ArraysKt.contains(iArr, ((UInt) obj).m163unboximpl())) {
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
    public static boolean m169equalsimpl(int[] iArr, Object obj) {
        return (obj instanceof UIntArray) && Intrinsics.areEqual(iArr, ((UIntArray) obj).m180unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m170equalsimpl0(int[] iArr, int[] iArr2) {
        return Intrinsics.areEqual(iArr, iArr2);
    }

    /* renamed from: get-pVg5ArA  reason: not valid java name */
    public static final int m171getpVg5ArA(int[] iArr, int i) {
        return UInt.m158constructorimpl(iArr[i]);
    }

    /* renamed from: getSize-impl  reason: not valid java name */
    public static int m172getSizeimpl(int[] iArr) {
        return iArr.length;
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m173hashCodeimpl(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    /* renamed from: isEmpty-impl  reason: not valid java name */
    public static boolean m174isEmptyimpl(int[] iArr) {
        return iArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl  reason: not valid java name */
    public static Iterator<UInt> m175iteratorimpl(int[] iArr) {
        return new a(iArr);
    }

    /* renamed from: set-VXSXFK8  reason: not valid java name */
    public static final void m176setVXSXFK8(int[] iArr, int i, int i2) {
        iArr[i] = i2;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m177toStringimpl(int[] iArr) {
        return "UIntArray(storage=" + Arrays.toString(iArr) + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UInt uInt) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-WZ4Q5Ns  reason: not valid java name */
    public boolean m178addWZ4Q5Ns(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UInt> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return m179containsWZ4Q5Ns(((UInt) obj).m163unboximpl());
        }
        return false;
    }

    /* renamed from: contains-WZ4Q5Ns  reason: not valid java name */
    public boolean m179containsWZ4Q5Ns(int i) {
        return m167containsWZ4Q5Ns(this.h, i);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return m168containsAllimpl(this.h, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m169equalsimpl(this.h, obj);
    }

    @Override // java.util.Collection
    /* renamed from: getSize */
    public int size() {
        return m172getSizeimpl(this.h);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m173hashCodeimpl(this.h);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m174isEmptyimpl(this.h);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<UInt> iterator() {
        return m175iteratorimpl(this.h);
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
        return m177toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int[] m180unboximpl() {
        return this.h;
    }

    /* renamed from: contains-WZ4Q5Ns  reason: not valid java name */
    public static boolean m167containsWZ4Q5Ns(int[] iArr, int i) {
        return ArraysKt___ArraysKt.contains(iArr, i);
    }
}
