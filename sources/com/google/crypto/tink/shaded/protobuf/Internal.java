package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
/* loaded from: classes10.dex */
public final class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final ByteBuffer EMPTY_BYTE_BUFFER;
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM;

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f10957a = Charset.forName("UTF-8");
    public static final Charset b = Charset.forName("ISO-8859-1");

    /* loaded from: classes10.dex */
    public interface BooleanList extends ProtobufList<Boolean> {
        void addBoolean(boolean z);

        boolean getBoolean(int i);

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
        ProtobufList<Boolean> mutableCopyWithCapacity(int i);

        boolean setBoolean(int i, boolean z);
    }

    /* loaded from: classes10.dex */
    public interface DoubleList extends ProtobufList<Double> {
        void addDouble(double d);

        double getDouble(int i);

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
        ProtobufList<Double> mutableCopyWithCapacity(int i);

        double setDouble(int i, double d);
    }

    /* loaded from: classes10.dex */
    public interface EnumLite {
        int getNumber();
    }

    /* loaded from: classes10.dex */
    public interface EnumLiteMap<T extends EnumLite> {
        T findValueByNumber(int i);
    }

    /* loaded from: classes10.dex */
    public interface EnumVerifier {
        boolean isInRange(int i);
    }

    /* loaded from: classes10.dex */
    public interface FloatList extends ProtobufList<Float> {
        void addFloat(float f);

        float getFloat(int i);

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
        ProtobufList<Float> mutableCopyWithCapacity(int i);

        float setFloat(int i, float f);
    }

    /* loaded from: classes10.dex */
    public interface IntList extends ProtobufList<Integer> {
        void addInt(int i);

        int getInt(int i);

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
        ProtobufList<Integer> mutableCopyWithCapacity(int i);

        int setInt(int i, int i2);
    }

    /* loaded from: classes10.dex */
    public static class ListAdapter<F, T> extends AbstractList<T> {
        public final List<F> h;
        public final Converter<F, T> i;

        /* loaded from: classes10.dex */
        public interface Converter<F, T> {
            T convert(F f);
        }

        public ListAdapter(List<F> list, Converter<F, T> converter) {
            this.h = list;
            this.i = converter;
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int i) {
            return this.i.convert(this.h.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.h.size();
        }
    }

    /* loaded from: classes10.dex */
    public interface LongList extends ProtobufList<Long> {
        void addLong(long j);

        long getLong(int i);

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
        ProtobufList<Long> mutableCopyWithCapacity(int i);

        long setLong(int i, long j);
    }

    /* loaded from: classes10.dex */
    public static class MapAdapter<K, V, RealValue> extends AbstractMap<K, V> {
        public final Map<K, RealValue> h;
        public final Converter<RealValue, V> i;

        /* loaded from: classes10.dex */
        public interface Converter<A, B> {
            A doBackward(B b);

            B doForward(A a2);
        }

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* loaded from: classes10.dex */
        public class a<T> implements Converter<Integer, T> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ EnumLiteMap f10958a;
            public final /* synthetic */ EnumLite b;

            public a(EnumLiteMap enumLiteMap, EnumLite enumLite) {
                this.f10958a = enumLiteMap;
                this.b = enumLite;
            }

            /* JADX WARN: Incorrect types in method signature: (TT;)Ljava/lang/Integer; */
            @Override // com.google.crypto.tink.shaded.protobuf.Internal.MapAdapter.Converter
            /* renamed from: a */
            public Integer doBackward(EnumLite enumLite) {
                return Integer.valueOf(enumLite.getNumber());
            }

            /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Integer;)TT; */
            @Override // com.google.crypto.tink.shaded.protobuf.Internal.MapAdapter.Converter
            /* renamed from: b */
            public EnumLite doForward(Integer num) {
                EnumLite findValueByNumber = this.f10958a.findValueByNumber(num.intValue());
                return findValueByNumber == null ? this.b : findValueByNumber;
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Map.Entry<K, V> {
            public final Map.Entry<K, RealValue> h;

            public b(Map.Entry<K, RealValue> entry) {
                this.h = entry;
            }

            @Override // java.util.Map.Entry
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Map.Entry) {
                    return getKey().equals(((Map.Entry) obj).getKey()) && getValue().equals(getValue());
                }
                return false;
            }

            @Override // java.util.Map.Entry
            public K getKey() {
                return this.h.getKey();
            }

            @Override // java.util.Map.Entry
            public V getValue() {
                return (V) MapAdapter.this.i.doForward(this.h.getValue());
            }

            @Override // java.util.Map.Entry
            public int hashCode() {
                return this.h.hashCode();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Map.Entry
            public V setValue(V v) {
                Object value = this.h.setValue(MapAdapter.this.i.doBackward(v));
                if (value == null) {
                    return null;
                }
                return (V) MapAdapter.this.i.doForward(value);
            }
        }

        /* loaded from: classes10.dex */
        public class c implements Iterator<Map.Entry<K, V>> {
            public final Iterator<Map.Entry<K, RealValue>> h;

            public c(Iterator<Map.Entry<K, RealValue>> it) {
                this.h = it;
            }

            @Override // java.util.Iterator
            /* renamed from: a */
            public Map.Entry<K, V> next() {
                return new b(this.h.next());
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.h.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.h.remove();
            }
        }

        /* loaded from: classes10.dex */
        public class d extends AbstractSet<Map.Entry<K, V>> {
            public final Set<Map.Entry<K, RealValue>> h;

            public d(Set<Map.Entry<K, RealValue>> set) {
                this.h = set;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return new c(this.h.iterator());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return this.h.size();
            }
        }

        public MapAdapter(Map<K, RealValue> map, Converter<RealValue, V> converter) {
            this.h = map;
            this.i = converter;
        }

        public static <T extends EnumLite> Converter<Integer, T> newEnumConverter(EnumLiteMap<T> enumLiteMap, T t) {
            return new a(enumLiteMap, t);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return new d(this.h.entrySet());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            RealValue realvalue = this.h.get(obj);
            if (realvalue == null) {
                return null;
            }
            return this.i.doForward(realvalue);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            RealValue put = this.h.put(k, this.i.doBackward(v));
            if (put == null) {
                return null;
            }
            return this.i.doForward(put);
        }
    }

    /* loaded from: classes10.dex */
    public interface ProtobufList<E> extends List<E>, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList<E> mutableCopyWithCapacity(int i);
    }

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_BYTE_BUFFER = ByteBuffer.wrap(bArr);
        EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(bArr);
    }

    public static <T> T a(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static <T> T b(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static byte[] byteArrayDefaultValue(String str) {
        return str.getBytes(b);
    }

    public static ByteBuffer byteBufferDefaultValue(String str) {
        return ByteBuffer.wrap(byteArrayDefaultValue(str));
    }

    public static ByteString bytesDefaultValue(String str) {
        return ByteString.copyFrom(str.getBytes(b));
    }

    public static int c(byte[] bArr, int i, int i2) {
        int e = e(i2, bArr, i, i2);
        if (e == 0) {
            return 1;
        }
        return e;
    }

    public static ByteBuffer copyByteBuffer(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.clear();
        ByteBuffer allocate = ByteBuffer.allocate(duplicate.capacity());
        allocate.put(duplicate);
        allocate.clear();
        return allocate;
    }

    public static Object d(Object obj, Object obj2) {
        return ((MessageLite) obj).toBuilder().mergeFrom((MessageLite) obj2).buildPartial();
    }

    public static int e(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static boolean equals(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsByteBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (byteBuffer.capacity() != byteBuffer2.capacity()) {
            return false;
        }
        return byteBuffer.duplicate().clear().equals(byteBuffer2.duplicate().clear());
    }

    public static <T extends MessageLite> T getDefaultInstance(Class<T> cls) {
        try {
            Method method = cls.getMethod("getDefaultInstance", new Class[0]);
            return (T) method.invoke(method, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get default instance for " + cls, e);
        }
    }

    public static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int hashCode(List<byte[]> list) {
        int i = 1;
        for (byte[] bArr : list) {
            i = (i * 31) + hashCode(bArr);
        }
        return i;
    }

    public static int hashCodeByteBuffer(List<ByteBuffer> list) {
        int i = 1;
        for (ByteBuffer byteBuffer : list) {
            i = (i * 31) + hashCodeByteBuffer(byteBuffer);
        }
        return i;
    }

    public static int hashEnum(EnumLite enumLite) {
        return enumLite.getNumber();
    }

    public static int hashEnumList(List<? extends EnumLite> list) {
        int i = 1;
        for (EnumLite enumLite : list) {
            i = (i * 31) + hashEnum(enumLite);
        }
        return i;
    }

    public static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static boolean isValidUtf8(ByteString byteString) {
        return byteString.isValidUtf8();
    }

    public static String stringDefaultValue(String str) {
        return new String(str.getBytes(b), f10957a);
    }

    public static byte[] toByteArray(String str) {
        return str.getBytes(f10957a);
    }

    public static String toStringUtf8(byte[] bArr) {
        return new String(bArr, f10957a);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return v0.t(bArr);
    }

    public static boolean equalsByteBuffer(List<ByteBuffer> list, List<ByteBuffer> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!equalsByteBuffer(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int hashCode(byte[] bArr) {
        return c(bArr, 0, bArr.length);
    }

    public static int hashCodeByteBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            int e = e(byteBuffer.capacity(), byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
            if (e == 0) {
                return 1;
            }
            return e;
        }
        int capacity = byteBuffer.capacity() <= 4096 ? byteBuffer.capacity() : 4096;
        byte[] bArr = new byte[capacity];
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.clear();
        int capacity2 = byteBuffer.capacity();
        while (duplicate.remaining() > 0) {
            int remaining = duplicate.remaining() <= capacity ? duplicate.remaining() : capacity;
            duplicate.get(bArr, 0, remaining);
            capacity2 = e(capacity2, bArr, 0, remaining);
        }
        if (capacity2 == 0) {
            return 1;
        }
        return capacity2;
    }
}
