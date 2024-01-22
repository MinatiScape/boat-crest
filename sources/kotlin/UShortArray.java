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
public final class UShortArray implements Collection<UShort>, KMappedMarker {
    @NotNull
    public final short[] h;

    /* loaded from: classes12.dex */
    public static final class a implements Iterator<UShort>, KMappedMarker {
        @NotNull
        public final short[] h;
        public int i;

        public a(@NotNull short[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            this.h = array;
        }

        public short a() {
            int i = this.i;
            short[] sArr = this.h;
            if (i < sArr.length) {
                this.i = i + 1;
                return UShort.m206constructorimpl(sArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.i));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i < this.h.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ UShort next() {
            return UShort.m205boximpl(a());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @PublishedApi
    public /* synthetic */ UShortArray(short[] sArr) {
        this.h = sArr;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UShortArray m212boximpl(short[] sArr) {
        return new UShortArray(sArr);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static short[] m213constructorimpl(int i) {
        return m214constructorimpl(new short[i]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static short[] m214constructorimpl(@NotNull short[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        return storage;
    }

    /* renamed from: containsAll-impl  reason: not valid java name */
    public static boolean m216containsAllimpl(short[] sArr, @NotNull Collection<UShort> elements) {
        boolean z;
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (!elements.isEmpty()) {
            for (Object obj : elements) {
                if ((obj instanceof UShort) && ArraysKt___ArraysKt.contains(sArr, ((UShort) obj).m211unboximpl())) {
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
    public static boolean m217equalsimpl(short[] sArr, Object obj) {
        return (obj instanceof UShortArray) && Intrinsics.areEqual(sArr, ((UShortArray) obj).m228unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m218equalsimpl0(short[] sArr, short[] sArr2) {
        return Intrinsics.areEqual(sArr, sArr2);
    }

    /* renamed from: get-Mh2AYeg  reason: not valid java name */
    public static final short m219getMh2AYeg(short[] sArr, int i) {
        return UShort.m206constructorimpl(sArr[i]);
    }

    /* renamed from: getSize-impl  reason: not valid java name */
    public static int m220getSizeimpl(short[] sArr) {
        return sArr.length;
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m221hashCodeimpl(short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    /* renamed from: isEmpty-impl  reason: not valid java name */
    public static boolean m222isEmptyimpl(short[] sArr) {
        return sArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl  reason: not valid java name */
    public static Iterator<UShort> m223iteratorimpl(short[] sArr) {
        return new a(sArr);
    }

    /* renamed from: set-01HTLdE  reason: not valid java name */
    public static final void m224set01HTLdE(short[] sArr, int i, short s) {
        sArr[i] = s;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m225toStringimpl(short[] sArr) {
        return "UShortArray(storage=" + Arrays.toString(sArr) + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UShort uShort) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-xj2QHRw  reason: not valid java name */
    public boolean m226addxj2QHRw(short s) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UShort> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return m227containsxj2QHRw(((UShort) obj).m211unboximpl());
        }
        return false;
    }

    /* renamed from: contains-xj2QHRw  reason: not valid java name */
    public boolean m227containsxj2QHRw(short s) {
        return m215containsxj2QHRw(this.h, s);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return m216containsAllimpl(this.h, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m217equalsimpl(this.h, obj);
    }

    @Override // java.util.Collection
    /* renamed from: getSize */
    public int size() {
        return m220getSizeimpl(this.h);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m221hashCodeimpl(this.h);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m222isEmptyimpl(this.h);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<UShort> iterator() {
        return m223iteratorimpl(this.h);
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
        return m225toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ short[] m228unboximpl() {
        return this.h;
    }

    /* renamed from: contains-xj2QHRw  reason: not valid java name */
    public static boolean m215containsxj2QHRw(short[] sArr, short s) {
        return ArraysKt___ArraysKt.contains(sArr, s);
    }
}
