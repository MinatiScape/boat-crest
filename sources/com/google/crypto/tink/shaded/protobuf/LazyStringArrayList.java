package com.google.crypto.tink.shaded.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public class LazyStringArrayList extends com.google.crypto.tink.shaded.protobuf.a<String> implements LazyStringList, RandomAccess {
    public static final LazyStringList EMPTY;
    public static final LazyStringArrayList j;
    public final List<Object> i;

    /* loaded from: classes10.dex */
    public static class a extends AbstractList<byte[]> implements RandomAccess {
        public final LazyStringArrayList h;

        public a(LazyStringArrayList lazyStringArrayList) {
            this.h = lazyStringArrayList;
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: a */
        public void add(int i, byte[] bArr) {
            this.h.h(i, bArr);
            ((AbstractList) this).modCount++;
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: b */
        public byte[] get(int i) {
            return this.h.getByteArray(i);
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: c */
        public byte[] remove(int i) {
            String remove = this.h.remove(i);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.i(remove);
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: d */
        public byte[] set(int i, byte[] bArr) {
            Object m = this.h.m(i, bArr);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.i(m);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.h.size();
        }
    }

    /* loaded from: classes10.dex */
    public static class b extends AbstractList<ByteString> implements RandomAccess {
        public final LazyStringArrayList h;

        public b(LazyStringArrayList lazyStringArrayList) {
            this.h = lazyStringArrayList;
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: a */
        public void add(int i, ByteString byteString) {
            this.h.g(i, byteString);
            ((AbstractList) this).modCount++;
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: b */
        public ByteString get(int i) {
            return this.h.getByteString(i);
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: c */
        public ByteString remove(int i) {
            String remove = this.h.remove(i);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.j(remove);
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: d */
        public ByteString set(int i, ByteString byteString) {
            Object l = this.h.l(i, byteString);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.j(l);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.h.size();
        }
    }

    static {
        LazyStringArrayList lazyStringArrayList = new LazyStringArrayList();
        j = lazyStringArrayList;
        lazyStringArrayList.makeImmutable();
        EMPTY = lazyStringArrayList;
    }

    public LazyStringArrayList() {
        this(10);
    }

    public static byte[] i(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return Internal.toByteArray((String) obj);
        }
        return ((ByteString) obj).toByteArray();
    }

    public static ByteString j(Object obj) {
        if (obj instanceof ByteString) {
            return (ByteString) obj;
        }
        if (obj instanceof String) {
            return ByteString.copyFromUtf8((String) obj);
        }
        return ByteString.copyFrom((byte[]) obj);
    }

    public static String k(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            return ((ByteString) obj).toStringUtf8();
        }
        return Internal.toStringUtf8((byte[]) obj);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public boolean addAllByteArray(Collection<byte[]> collection) {
        ensureIsMutable();
        boolean addAll = this.i.addAll(collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public boolean addAllByteString(Collection<? extends ByteString> collection) {
        ensureIsMutable();
        boolean addAll = this.i.addAll(collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public List<byte[]> asByteArrayList() {
        return new a(this);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ProtocolStringList
    public List<ByteString> asByteStringList() {
        return new b(this);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        ensureIsMutable();
        this.i.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final void g(int i, ByteString byteString) {
        ensureIsMutable();
        this.i.add(i, byteString);
        ((AbstractList) this).modCount++;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public byte[] getByteArray(int i) {
        Object obj = this.i.get(i);
        byte[] i2 = i(obj);
        if (i2 != obj) {
            this.i.set(i, i2);
        }
        return i2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public ByteString getByteString(int i) {
        Object obj = this.i.get(i);
        ByteString j2 = j(obj);
        if (j2 != obj) {
            this.i.set(i, j2);
        }
        return j2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public Object getRaw(int i) {
        return this.i.get(i);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public List<?> getUnderlyingElements() {
        return Collections.unmodifiableList(this.i);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return isModifiable() ? new UnmodifiableLazyStringList(this) : this;
    }

    public final void h(int i, byte[] bArr) {
        ensureIsMutable();
        this.i.add(i, bArr);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    public /* bridge */ /* synthetic */ boolean isModifiable() {
        return super.isModifiable();
    }

    public final Object l(int i, ByteString byteString) {
        ensureIsMutable();
        return this.i.set(i, byteString);
    }

    public final Object m(int i, byte[] bArr) {
        ensureIsMutable();
        return this.i.set(i, bArr);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void mergeFrom(LazyStringList lazyStringList) {
        ensureIsMutable();
        for (Object obj : lazyStringList.getUnderlyingElements()) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                this.i.add(Arrays.copyOf(bArr, bArr.length));
            } else {
                this.i.add(obj);
            }
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.i.size();
    }

    public LazyStringArrayList(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add((LazyStringArrayList) obj);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends String> collection) {
        ensureIsMutable();
        if (collection instanceof LazyStringList) {
            collection = ((LazyStringList) collection).getUnderlyingElements();
        }
        boolean addAll = this.i.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int i) {
        Object obj = this.i.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.i.set(i, stringUtf8);
            }
            return stringUtf8;
        }
        byte[] bArr = (byte[]) obj;
        String stringUtf82 = Internal.toStringUtf8(bArr);
        if (Internal.isValidUtf8(bArr)) {
            this.i.set(i, stringUtf82);
        }
        return stringUtf82;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    public LazyStringArrayList mutableCopyWithCapacity(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.i);
            return new LazyStringArrayList((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    public String set(int i, String str) {
        ensureIsMutable();
        return k(this.i.set(i, str));
    }

    public LazyStringArrayList(LazyStringList lazyStringList) {
        this.i = new ArrayList(lazyStringList.size());
        addAll(lazyStringList);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    public void add(int i, String str) {
        ensureIsMutable();
        this.i.add(i, str);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a, java.util.AbstractList, java.util.List
    public String remove(int i) {
        ensureIsMutable();
        Object remove = this.i.remove(i);
        ((AbstractList) this).modCount++;
        return k(remove);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void set(int i, ByteString byteString) {
        l(i, byteString);
    }

    public LazyStringArrayList(List<String> list) {
        this((ArrayList<Object>) new ArrayList(list));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void add(ByteString byteString) {
        ensureIsMutable();
        this.i.add(byteString);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void set(int i, byte[] bArr) {
        m(i, bArr);
    }

    public LazyStringArrayList(ArrayList<Object> arrayList) {
        this.i = arrayList;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void add(byte[] bArr) {
        ensureIsMutable();
        this.i.add(bArr);
        ((AbstractList) this).modCount++;
    }
}
