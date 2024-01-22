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
public final class ULongArray implements Collection<ULong>, KMappedMarker {
    @NotNull
    public final long[] h;

    /* loaded from: classes12.dex */
    public static final class a implements Iterator<ULong>, KMappedMarker {
        @NotNull
        public final long[] h;
        public int i;

        public a(@NotNull long[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            this.h = array;
        }

        public long a() {
            int i = this.i;
            long[] jArr = this.h;
            if (i < jArr.length) {
                this.i = i + 1;
                return ULong.m182constructorimpl(jArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.i));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i < this.h.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ ULong next() {
            return ULong.m181boximpl(a());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @PublishedApi
    public /* synthetic */ ULongArray(long[] jArr) {
        this.h = jArr;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ULongArray m188boximpl(long[] jArr) {
        return new ULongArray(jArr);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static long[] m189constructorimpl(int i) {
        return m190constructorimpl(new long[i]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static long[] m190constructorimpl(@NotNull long[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        return storage;
    }

    /* renamed from: containsAll-impl  reason: not valid java name */
    public static boolean m192containsAllimpl(long[] jArr, @NotNull Collection<ULong> elements) {
        boolean z;
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (!elements.isEmpty()) {
            for (Object obj : elements) {
                if ((obj instanceof ULong) && ArraysKt___ArraysKt.contains(jArr, ((ULong) obj).m187unboximpl())) {
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
    public static boolean m193equalsimpl(long[] jArr, Object obj) {
        return (obj instanceof ULongArray) && Intrinsics.areEqual(jArr, ((ULongArray) obj).m204unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m194equalsimpl0(long[] jArr, long[] jArr2) {
        return Intrinsics.areEqual(jArr, jArr2);
    }

    /* renamed from: get-s-VKNKU  reason: not valid java name */
    public static final long m195getsVKNKU(long[] jArr, int i) {
        return ULong.m182constructorimpl(jArr[i]);
    }

    /* renamed from: getSize-impl  reason: not valid java name */
    public static int m196getSizeimpl(long[] jArr) {
        return jArr.length;
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m197hashCodeimpl(long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    /* renamed from: isEmpty-impl  reason: not valid java name */
    public static boolean m198isEmptyimpl(long[] jArr) {
        return jArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl  reason: not valid java name */
    public static Iterator<ULong> m199iteratorimpl(long[] jArr) {
        return new a(jArr);
    }

    /* renamed from: set-k8EXiF4  reason: not valid java name */
    public static final void m200setk8EXiF4(long[] jArr, int i, long j) {
        jArr[i] = j;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m201toStringimpl(long[] jArr) {
        return "ULongArray(storage=" + Arrays.toString(jArr) + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(ULong uLong) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-VKZWuLQ  reason: not valid java name */
    public boolean m202addVKZWuLQ(long j) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends ULong> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return m203containsVKZWuLQ(((ULong) obj).m187unboximpl());
        }
        return false;
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m203containsVKZWuLQ(long j) {
        return m191containsVKZWuLQ(this.h, j);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return m192containsAllimpl(this.h, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m193equalsimpl(this.h, obj);
    }

    @Override // java.util.Collection
    /* renamed from: getSize */
    public int size() {
        return m196getSizeimpl(this.h);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m197hashCodeimpl(this.h);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m198isEmptyimpl(this.h);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<ULong> iterator() {
        return m199iteratorimpl(this.h);
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
        return m201toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long[] m204unboximpl() {
        return this.h;
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public static boolean m191containsVKZWuLQ(long[] jArr, long j) {
        return ArraysKt___ArraysKt.contains(jArr, j);
    }
}
