package com.google.protobuf;

import com.google.protobuf.FieldSet.FieldDescriptorLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyField;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class FieldSet<T extends FieldDescriptorLite<T>> {
    public static final FieldSet d = new FieldSet(true);

    /* renamed from: a  reason: collision with root package name */
    public final v0<T, Object> f11675a;
    public boolean b;
    public boolean c;

    /* loaded from: classes11.dex */
    public interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
        Internal.EnumLiteMap<?> getEnumType();

        WireFormat.JavaType getLiteJavaType();

        WireFormat.FieldType getLiteType();

        int getNumber();

        MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite);

        boolean isPacked();

        boolean isRepeated();
    }

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11676a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            b = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[WireFormat.FieldType.GROUP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[WireFormat.FieldType.STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[WireFormat.FieldType.BYTES.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[WireFormat.FieldType.UINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[WireFormat.FieldType.SFIXED32.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[WireFormat.FieldType.SFIXED64.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[WireFormat.FieldType.SINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                b[WireFormat.FieldType.SINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                b[WireFormat.FieldType.ENUM.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr2 = new int[WireFormat.JavaType.values().length];
            f11676a = iArr2;
            try {
                iArr2[WireFormat.JavaType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f11676a[WireFormat.JavaType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f11676a[WireFormat.JavaType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f11676a[WireFormat.JavaType.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f11676a[WireFormat.JavaType.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f11676a[WireFormat.JavaType.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f11676a[WireFormat.JavaType.BYTE_STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f11676a[WireFormat.JavaType.ENUM.ordinal()] = 8;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f11676a[WireFormat.JavaType.MESSAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public static final class b<T extends FieldDescriptorLite<T>> {

        /* renamed from: a  reason: collision with root package name */
        public v0<T, Object> f11677a;
        public boolean b;
        public boolean c;
        public boolean d;

        public /* synthetic */ b(a aVar) {
            this();
        }

        public static <T extends FieldDescriptorLite<T>> b<T> e(FieldSet<T> fieldSet) {
            b<T> bVar = new b<>(FieldSet.l(fieldSet.f11675a, true));
            bVar.b = fieldSet.c;
            return bVar;
        }

        public static Object p(Object obj) {
            return obj instanceof MessageLite.Builder ? ((MessageLite.Builder) obj).build() : obj;
        }

        public static <T extends FieldDescriptorLite<T>> Object q(T t, Object obj) {
            if (obj != null && t.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
                if (t.isRepeated()) {
                    if (obj instanceof List) {
                        List list = (List) obj;
                        for (int i = 0; i < list.size(); i++) {
                            Object obj2 = list.get(i);
                            Object p = p(obj2);
                            if (p != obj2) {
                                if (list == obj) {
                                    list = new ArrayList(list);
                                }
                                list.set(i, p);
                            }
                        }
                        return list;
                    }
                    throw new IllegalStateException("Repeated field should contains a List but actually contains type: " + obj.getClass());
                }
                return p(obj);
            }
            return obj;
        }

        public static <T extends FieldDescriptorLite<T>> void r(v0<T, Object> v0Var) {
            for (int i = 0; i < v0Var.l(); i++) {
                s(v0Var.k(i));
            }
            for (Map.Entry<T, Object> entry : v0Var.n()) {
                s(entry);
            }
        }

        public static <T extends FieldDescriptorLite<T>> void s(Map.Entry<T, Object> entry) {
            entry.setValue(q(entry.getKey(), entry.getValue()));
        }

        public void a(T t, Object obj) {
            List list;
            d();
            if (t.isRepeated()) {
                this.d = this.d || (obj instanceof MessageLite.Builder);
                v(t, obj);
                Object g = g(t);
                if (g == null) {
                    list = new ArrayList();
                    this.f11677a.put(t, list);
                } else {
                    list = (List) g;
                }
                list.add(obj);
                return;
            }
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }

        public FieldSet<T> b() {
            if (this.f11677a.isEmpty()) {
                return FieldSet.s();
            }
            this.c = false;
            v0<T, Object> v0Var = this.f11677a;
            if (this.d) {
                v0Var = FieldSet.l(v0Var, false);
                r(v0Var);
            }
            FieldSet<T> fieldSet = new FieldSet<>(v0Var, null);
            fieldSet.c = this.b;
            return fieldSet;
        }

        public void c(T t) {
            d();
            this.f11677a.remove(t);
            if (this.f11677a.isEmpty()) {
                this.b = false;
            }
        }

        public final void d() {
            if (this.c) {
                return;
            }
            this.f11677a = FieldSet.l(this.f11677a, true);
            this.c = true;
        }

        public Map<T, Object> f() {
            if (!this.b) {
                return this.f11677a.p() ? this.f11677a : Collections.unmodifiableMap(this.f11677a);
            }
            v0 l = FieldSet.l(this.f11677a, false);
            if (this.f11677a.p()) {
                l.q();
            } else {
                r(l);
            }
            return l;
        }

        public Object g(T t) {
            return q(t, h(t));
        }

        public Object h(T t) {
            Object obj = this.f11677a.get(t);
            return obj instanceof LazyField ? ((LazyField) obj).getValue() : obj;
        }

        public Object i(T t, int i) {
            if (this.d) {
                d();
            }
            return p(j(t, i));
        }

        public Object j(T t, int i) {
            if (t.isRepeated()) {
                Object h = h(t);
                if (h != null) {
                    return ((List) h).get(i);
                }
                throw new IndexOutOfBoundsException();
            }
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }

        public int k(T t) {
            if (t.isRepeated()) {
                Object g = g(t);
                if (g == null) {
                    return 0;
                }
                return ((List) g).size();
            }
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }

        public boolean l(T t) {
            if (t.isRepeated()) {
                throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
            }
            return this.f11677a.get(t) != null;
        }

        public boolean m() {
            for (int i = 0; i < this.f11677a.l(); i++) {
                if (!FieldSet.F(this.f11677a.k(i))) {
                    return false;
                }
            }
            for (Map.Entry<T, Object> entry : this.f11677a.n()) {
                if (!FieldSet.F(entry)) {
                    return false;
                }
            }
            return true;
        }

        public void n(FieldSet<T> fieldSet) {
            d();
            for (int i = 0; i < fieldSet.f11675a.l(); i++) {
                o(fieldSet.f11675a.k(i));
            }
            for (Map.Entry<T, Object> entry : fieldSet.f11675a.n()) {
                o(entry);
            }
        }

        public final void o(Map.Entry<T, Object> entry) {
            T key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof LazyField) {
                value = ((LazyField) value).getValue();
            }
            if (key.isRepeated()) {
                Object g = g(key);
                if (g == null) {
                    g = new ArrayList();
                }
                for (Object obj : (List) value) {
                    ((List) g).add(FieldSet.n(obj));
                }
                this.f11677a.put(key, g);
            } else if (key.getLiteJavaType() != WireFormat.JavaType.MESSAGE) {
                this.f11677a.put(key, FieldSet.n(value));
            } else {
                Object g2 = g(key);
                if (g2 == null) {
                    this.f11677a.put(key, FieldSet.n(value));
                } else if (g2 instanceof MessageLite.Builder) {
                    key.internalMergeFrom((MessageLite.Builder) g2, (MessageLite) value);
                } else {
                    this.f11677a.put(key, key.internalMergeFrom(((MessageLite) g2).toBuilder(), (MessageLite) value).build());
                }
            }
        }

        public void t(T t, Object obj) {
            d();
            boolean z = false;
            if (t.isRepeated()) {
                if (obj instanceof List) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll((List) obj);
                    for (Object obj2 : arrayList) {
                        v(t, obj2);
                        this.d = this.d || (obj2 instanceof MessageLite.Builder);
                    }
                    obj = arrayList;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            } else {
                v(t, obj);
            }
            if (obj instanceof LazyField) {
                this.b = true;
            }
            this.d = (this.d || (obj instanceof MessageLite.Builder)) ? true : true;
            this.f11677a.put(t, obj);
        }

        public void u(T t, int i, Object obj) {
            d();
            if (t.isRepeated()) {
                this.d = this.d || (obj instanceof MessageLite.Builder);
                Object g = g(t);
                if (g != null) {
                    v(t, obj);
                    ((List) g).set(i, obj);
                    return;
                }
                throw new IndexOutOfBoundsException();
            }
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }

        public final void v(T t, Object obj) {
            if (FieldSet.G(t.getLiteType(), obj)) {
                return;
            }
            if (t.getLiteType().getJavaType() != WireFormat.JavaType.MESSAGE || !(obj instanceof MessageLite.Builder)) {
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(t.getNumber()), t.getLiteType().getJavaType(), obj.getClass().getName()));
            }
        }

        public b() {
            this(v0.r(16));
        }

        public b(v0<T, Object> v0Var) {
            this.f11677a = v0Var;
            this.c = true;
        }
    }

    public /* synthetic */ FieldSet(v0 v0Var, a aVar) {
        this(v0Var);
    }

    public static int A(WireFormat.FieldType fieldType, boolean z) {
        if (z) {
            return 2;
        }
        return fieldType.getWireType();
    }

    public static <T extends FieldDescriptorLite<T>> boolean F(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            if (key.isRepeated()) {
                for (MessageLite messageLite : (List) entry.getValue()) {
                    if (!messageLite.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof MessageLite) {
                    if (!((MessageLite) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof LazyField) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public static boolean G(WireFormat.FieldType fieldType, Object obj) {
        Internal.checkNotNull(obj);
        switch (a.f11676a[fieldType.getJavaType().ordinal()]) {
            case 1:
                return obj instanceof Integer;
            case 2:
                return obj instanceof Long;
            case 3:
                return obj instanceof Float;
            case 4:
                return obj instanceof Double;
            case 5:
                return obj instanceof Boolean;
            case 6:
                return obj instanceof String;
            case 7:
                return (obj instanceof ByteString) || (obj instanceof byte[]);
            case 8:
                return (obj instanceof Integer) || (obj instanceof Internal.EnumLite);
            case 9:
                return (obj instanceof MessageLite) || (obj instanceof LazyField);
            default:
                return false;
        }
    }

    public static <T extends FieldDescriptorLite<T>> b<T> L() {
        return new b<>((a) null);
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> M() {
        return new FieldSet<>();
    }

    public static Object N(CodedInputStream codedInputStream, WireFormat.FieldType fieldType, boolean z) throws IOException {
        if (z) {
            return WireFormat.readPrimitiveField(codedInputStream, fieldType, WireFormat.b.STRICT);
        }
        return WireFormat.readPrimitiveField(codedInputStream, fieldType, WireFormat.b.LOOSE);
    }

    public static void R(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, int i, Object obj) throws IOException {
        if (fieldType == WireFormat.FieldType.GROUP) {
            codedOutputStream.writeGroup(i, (MessageLite) obj);
            return;
        }
        codedOutputStream.writeTag(i, A(fieldType, false));
        S(codedOutputStream, fieldType, obj);
    }

    public static void S(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, Object obj) throws IOException {
        switch (a.b[fieldType.ordinal()]) {
            case 1:
                codedOutputStream.writeDoubleNoTag(((Double) obj).doubleValue());
                return;
            case 2:
                codedOutputStream.writeFloatNoTag(((Float) obj).floatValue());
                return;
            case 3:
                codedOutputStream.writeInt64NoTag(((Long) obj).longValue());
                return;
            case 4:
                codedOutputStream.writeUInt64NoTag(((Long) obj).longValue());
                return;
            case 5:
                codedOutputStream.writeInt32NoTag(((Integer) obj).intValue());
                return;
            case 6:
                codedOutputStream.writeFixed64NoTag(((Long) obj).longValue());
                return;
            case 7:
                codedOutputStream.writeFixed32NoTag(((Integer) obj).intValue());
                return;
            case 8:
                codedOutputStream.writeBoolNoTag(((Boolean) obj).booleanValue());
                return;
            case 9:
                codedOutputStream.writeGroupNoTag((MessageLite) obj);
                return;
            case 10:
                codedOutputStream.writeMessageNoTag((MessageLite) obj);
                return;
            case 11:
                if (obj instanceof ByteString) {
                    codedOutputStream.writeBytesNoTag((ByteString) obj);
                    return;
                } else {
                    codedOutputStream.writeStringNoTag((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof ByteString) {
                    codedOutputStream.writeBytesNoTag((ByteString) obj);
                    return;
                } else {
                    codedOutputStream.writeByteArrayNoTag((byte[]) obj);
                    return;
                }
            case 13:
                codedOutputStream.writeUInt32NoTag(((Integer) obj).intValue());
                return;
            case 14:
                codedOutputStream.writeSFixed32NoTag(((Integer) obj).intValue());
                return;
            case 15:
                codedOutputStream.writeSFixed64NoTag(((Long) obj).longValue());
                return;
            case 16:
                codedOutputStream.writeSInt32NoTag(((Integer) obj).intValue());
                return;
            case 17:
                codedOutputStream.writeSInt64NoTag(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof Internal.EnumLite) {
                    codedOutputStream.writeEnumNoTag(((Internal.EnumLite) obj).getNumber());
                    return;
                } else {
                    codedOutputStream.writeEnumNoTag(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public static void T(FieldDescriptorLite<?> fieldDescriptorLite, Object obj, CodedOutputStream codedOutputStream) throws IOException {
        WireFormat.FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (fieldDescriptorLite.isRepeated()) {
            List<Object> list = (List) obj;
            if (fieldDescriptorLite.isPacked()) {
                codedOutputStream.writeTag(number, 2);
                int i = 0;
                for (Object obj2 : list) {
                    i += p(liteType, obj2);
                }
                codedOutputStream.writeRawVarint32(i);
                for (Object obj3 : list) {
                    S(codedOutputStream, liteType, obj3);
                }
                return;
            }
            for (Object obj4 : list) {
                R(codedOutputStream, liteType, number, obj4);
            }
        } else if (obj instanceof LazyField) {
            R(codedOutputStream, liteType, number, ((LazyField) obj).getValue());
        } else {
            R(codedOutputStream, liteType, number, obj);
        }
    }

    public static <T extends FieldDescriptorLite<T>> v0<T, Object> l(v0<T, Object> v0Var, boolean z) {
        v0<T, Object> r = v0.r(16);
        for (int i = 0; i < v0Var.l(); i++) {
            m(r, v0Var.k(i), z);
        }
        for (Map.Entry<T, Object> entry : v0Var.n()) {
            m(r, entry, z);
        }
        return r;
    }

    public static <T extends FieldDescriptorLite<T>> void m(Map<T, Object> map, Map.Entry<T, Object> entry, boolean z) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            map.put(key, ((LazyField) value).getValue());
        } else if (z && (value instanceof List)) {
            map.put(key, new ArrayList((List) value));
        } else {
            map.put(key, value);
        }
    }

    public static Object n(Object obj) {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    public static int o(WireFormat.FieldType fieldType, int i, Object obj) {
        int computeTagSize = CodedOutputStream.computeTagSize(i);
        if (fieldType == WireFormat.FieldType.GROUP) {
            computeTagSize *= 2;
        }
        return computeTagSize + p(fieldType, obj);
    }

    public static int p(WireFormat.FieldType fieldType, Object obj) {
        switch (a.b[fieldType.ordinal()]) {
            case 1:
                return CodedOutputStream.computeDoubleSizeNoTag(((Double) obj).doubleValue());
            case 2:
                return CodedOutputStream.computeFloatSizeNoTag(((Float) obj).floatValue());
            case 3:
                return CodedOutputStream.computeInt64SizeNoTag(((Long) obj).longValue());
            case 4:
                return CodedOutputStream.computeUInt64SizeNoTag(((Long) obj).longValue());
            case 5:
                return CodedOutputStream.computeInt32SizeNoTag(((Integer) obj).intValue());
            case 6:
                return CodedOutputStream.computeFixed64SizeNoTag(((Long) obj).longValue());
            case 7:
                return CodedOutputStream.computeFixed32SizeNoTag(((Integer) obj).intValue());
            case 8:
                return CodedOutputStream.computeBoolSizeNoTag(((Boolean) obj).booleanValue());
            case 9:
                return CodedOutputStream.computeGroupSizeNoTag((MessageLite) obj);
            case 10:
                if (obj instanceof LazyField) {
                    return CodedOutputStream.computeLazyFieldSizeNoTag((LazyField) obj);
                }
                return CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj);
            case 11:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.computeBytesSizeNoTag((ByteString) obj);
                }
                return CodedOutputStream.computeStringSizeNoTag((String) obj);
            case 12:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.computeBytesSizeNoTag((ByteString) obj);
                }
                return CodedOutputStream.computeByteArraySizeNoTag((byte[]) obj);
            case 13:
                return CodedOutputStream.computeUInt32SizeNoTag(((Integer) obj).intValue());
            case 14:
                return CodedOutputStream.computeSFixed32SizeNoTag(((Integer) obj).intValue());
            case 15:
                return CodedOutputStream.computeSFixed64SizeNoTag(((Long) obj).longValue());
            case 16:
                return CodedOutputStream.computeSInt32SizeNoTag(((Integer) obj).intValue());
            case 17:
                return CodedOutputStream.computeSInt64SizeNoTag(((Long) obj).longValue());
            case 18:
                if (obj instanceof Internal.EnumLite) {
                    return CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite) obj).getNumber());
                }
                return CodedOutputStream.computeEnumSizeNoTag(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int q(FieldDescriptorLite<?> fieldDescriptorLite, Object obj) {
        WireFormat.FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (fieldDescriptorLite.isRepeated()) {
            int i = 0;
            if (fieldDescriptorLite.isPacked()) {
                for (Object obj2 : (List) obj) {
                    i += p(liteType, obj2);
                }
                return CodedOutputStream.computeTagSize(number) + i + CodedOutputStream.computeRawVarint32Size(i);
            }
            for (Object obj3 : (List) obj) {
                i += o(liteType, number, obj3);
            }
            return i;
        }
        return o(liteType, number, obj);
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> s() {
        return d;
    }

    public boolean B(T t) {
        if (t.isRepeated()) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return this.f11675a.get(t) != null;
    }

    public boolean C() {
        return this.f11675a.isEmpty();
    }

    public boolean D() {
        return this.b;
    }

    public boolean E() {
        for (int i = 0; i < this.f11675a.l(); i++) {
            if (!F(this.f11675a.k(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.f11675a.n()) {
            if (!F(entry)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<Map.Entry<T, Object>> H() {
        if (this.c) {
            return new LazyField.c(this.f11675a.entrySet().iterator());
        }
        return this.f11675a.entrySet().iterator();
    }

    public void I() {
        if (this.b) {
            return;
        }
        this.f11675a.q();
        this.b = true;
    }

    public void J(FieldSet<T> fieldSet) {
        for (int i = 0; i < fieldSet.f11675a.l(); i++) {
            K(fieldSet.f11675a.k(i));
        }
        for (Map.Entry<T, Object> entry : fieldSet.f11675a.n()) {
            K(entry);
        }
    }

    public final void K(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            value = ((LazyField) value).getValue();
        }
        if (key.isRepeated()) {
            Object u = u(key);
            if (u == null) {
                u = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) u).add(n(obj));
            }
            this.f11675a.put(key, u);
        } else if (key.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            Object u2 = u(key);
            if (u2 == null) {
                this.f11675a.put(key, n(value));
                return;
            }
            this.f11675a.put(key, key.internalMergeFrom(((MessageLite) u2).toBuilder(), (MessageLite) value).build());
        } else {
            this.f11675a.put(key, n(value));
        }
    }

    public void O(T t, Object obj) {
        if (t.isRepeated()) {
            if (obj instanceof List) {
                ArrayList<Object> arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                for (Object obj2 : arrayList) {
                    Q(t, obj2);
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            Q(t, obj);
        }
        if (obj instanceof LazyField) {
            this.c = true;
        }
        this.f11675a.put(t, obj);
    }

    public void P(T t, int i, Object obj) {
        if (t.isRepeated()) {
            Object u = u(t);
            if (u != null) {
                Q(t, obj);
                ((List) u).set(i, obj);
                return;
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public final void Q(T t, Object obj) {
        if (!G(t.getLiteType(), obj)) {
            throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(t.getNumber()), t.getLiteType().getJavaType(), obj.getClass().getName()));
        }
    }

    public void U(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.f11675a.l(); i++) {
            V(this.f11675a.k(i), codedOutputStream);
        }
        for (Map.Entry<T, Object> entry : this.f11675a.n()) {
            V(entry, codedOutputStream);
        }
    }

    public final void V(Map.Entry<T, Object> entry, CodedOutputStream codedOutputStream) throws IOException {
        T key = entry.getKey();
        if (key.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !key.isRepeated() && !key.isPacked()) {
            Object value = entry.getValue();
            if (value instanceof LazyField) {
                value = ((LazyField) value).getValue();
            }
            codedOutputStream.writeMessageSetExtension(entry.getKey().getNumber(), (MessageLite) value);
            return;
        }
        T(key, entry.getValue(), codedOutputStream);
    }

    public void W(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.f11675a.l(); i++) {
            Map.Entry<T, Object> k = this.f11675a.k(i);
            T(k.getKey(), k.getValue(), codedOutputStream);
        }
        for (Map.Entry<T, Object> entry : this.f11675a.n()) {
            T(entry.getKey(), entry.getValue(), codedOutputStream);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FieldSet) {
            return this.f11675a.equals(((FieldSet) obj).f11675a);
        }
        return false;
    }

    public void h(T t, Object obj) {
        List list;
        if (t.isRepeated()) {
            Q(t, obj);
            Object u = u(t);
            if (u == null) {
                list = new ArrayList();
                this.f11675a.put(t, list);
            } else {
                list = (List) u;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    public int hashCode() {
        return this.f11675a.hashCode();
    }

    public void i() {
        this.f11675a.clear();
        this.c = false;
    }

    public void j(T t) {
        this.f11675a.remove(t);
        if (this.f11675a.isEmpty()) {
            this.c = false;
        }
    }

    /* renamed from: k */
    public FieldSet<T> clone() {
        FieldSet<T> M = M();
        for (int i = 0; i < this.f11675a.l(); i++) {
            Map.Entry<T, Object> k = this.f11675a.k(i);
            M.O(k.getKey(), k.getValue());
        }
        for (Map.Entry<T, Object> entry : this.f11675a.n()) {
            M.O(entry.getKey(), entry.getValue());
        }
        M.c = this.c;
        return M;
    }

    public Iterator<Map.Entry<T, Object>> r() {
        if (this.c) {
            return new LazyField.c(this.f11675a.i().iterator());
        }
        return this.f11675a.i().iterator();
    }

    public Map<T, Object> t() {
        if (!this.c) {
            return this.f11675a.p() ? this.f11675a : Collections.unmodifiableMap(this.f11675a);
        }
        v0 l = l(this.f11675a, false);
        if (this.f11675a.p()) {
            l.q();
        }
        return l;
    }

    public Object u(T t) {
        Object obj = this.f11675a.get(t);
        return obj instanceof LazyField ? ((LazyField) obj).getValue() : obj;
    }

    public int v() {
        int i = 0;
        for (int i2 = 0; i2 < this.f11675a.l(); i2++) {
            i += w(this.f11675a.k(i2));
        }
        for (Map.Entry<T, Object> entry : this.f11675a.n()) {
            i += w(entry);
        }
        return i;
    }

    public final int w(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !key.isRepeated() && !key.isPacked()) {
            if (value instanceof LazyField) {
                return CodedOutputStream.computeLazyFieldMessageSetExtensionSize(entry.getKey().getNumber(), (LazyField) value);
            }
            return CodedOutputStream.computeMessageSetExtensionSize(entry.getKey().getNumber(), (MessageLite) value);
        }
        return q(key, value);
    }

    public Object x(T t, int i) {
        if (t.isRepeated()) {
            Object u = u(t);
            if (u != null) {
                return ((List) u).get(i);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public int y(T t) {
        if (t.isRepeated()) {
            Object u = u(t);
            if (u == null) {
                return 0;
            }
            return ((List) u).size();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public int z() {
        int i = 0;
        for (int i2 = 0; i2 < this.f11675a.l(); i2++) {
            Map.Entry<T, Object> k = this.f11675a.k(i2);
            i += q(k.getKey(), k.getValue());
        }
        for (Map.Entry<T, Object> entry : this.f11675a.n()) {
            i += q(entry.getKey(), entry.getValue());
        }
        return i;
    }

    public FieldSet() {
        this.f11675a = v0.r(16);
    }

    public FieldSet(boolean z) {
        this(v0.r(0));
        I();
    }

    public FieldSet(v0<T, Object> v0Var) {
        this.f11675a = v0Var;
        I();
    }
}
