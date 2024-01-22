package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.StructuralMessageInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes11.dex */
public final class i implements b0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Set<String> f11733a;
    public static d b;

    /* loaded from: classes11.dex */
    public static class a implements Internal.EnumVerifier {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Descriptors.FieldDescriptor f11734a;

        public a(Descriptors.FieldDescriptor fieldDescriptor) {
            this.f11734a = fieldDescriptor;
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return this.f11734a.getEnumType().findValueByNumber(i) != null;
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements Internal.EnumVerifier {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Descriptors.FieldDescriptor f11735a;

        public b(Descriptors.FieldDescriptor fieldDescriptor) {
            this.f11735a = fieldDescriptor;
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return this.f11735a.getEnumType().findValueByNumber(i) != null;
        }
    }

    /* loaded from: classes11.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11736a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            c = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                c[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr2 = new int[JavaType.values().length];
            b = iArr2;
            try {
                iArr2[JavaType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                b[JavaType.BYTE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                b[JavaType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                b[JavaType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                b[JavaType.ENUM.ordinal()] = 5;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                b[JavaType.INT.ordinal()] = 6;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                b[JavaType.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                b[JavaType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                b[JavaType.MESSAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused27) {
            }
            int[] iArr3 = new int[Descriptors.FileDescriptor.Syntax.values().length];
            f11736a = iArr3;
            try {
                iArr3[Descriptors.FileDescriptor.Syntax.PROTO2.ordinal()] = 1;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f11736a[Descriptors.FileDescriptor.Syntax.PROTO3.ordinal()] = 2;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    static {
        new i();
        f11733a = new HashSet(Arrays.asList("cached_size", "serialized_size", "class"));
        b = new d();
    }

    public static java.lang.reflect.Field e(Class<?> cls, int i) {
        return m(cls, "bitField" + i + "_");
    }

    public static FieldInfo f(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor, e eVar, boolean z, Internal.EnumVerifier enumVerifier) {
        l0 a2 = eVar.a(cls, fieldDescriptor.getContainingOneof());
        FieldType q = q(fieldDescriptor);
        return FieldInfo.f(fieldDescriptor.getNumber(), q, a2, r(cls, fieldDescriptor, q), z, enumVerifier);
    }

    public static java.lang.reflect.Field g(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        return m(cls, n(fieldDescriptor));
    }

    public static a0 h(Class<?> cls, Descriptors.Descriptor descriptor) {
        int i = c.f11736a[descriptor.getFile().getSyntax().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return j(cls, descriptor);
            }
            throw new IllegalArgumentException("Unsupported syntax: " + descriptor.getFile().getSyntax());
        }
        return i(cls, descriptor);
    }

    public static StructuralMessageInfo i(Class<?> cls, Descriptors.Descriptor descriptor) {
        List<Descriptors.FieldDescriptor> fields = descriptor.getFields();
        StructuralMessageInfo.Builder e2 = StructuralMessageInfo.e(fields.size());
        e2.withDefaultInstance(o(cls));
        e2.withSyntax(ProtoSyntax.PROTO2);
        e2.withMessageSetWireFormat(descriptor.getOptions().getMessageSetWireFormat());
        Internal.EnumVerifier enumVerifier = null;
        e eVar = new e(null);
        java.lang.reflect.Field field = null;
        int i = 0;
        int i2 = 0;
        int i3 = 1;
        while (i < fields.size()) {
            Descriptors.FieldDescriptor fieldDescriptor = fields.get(i);
            boolean javaStringCheckUtf8 = fieldDescriptor.getFile().getOptions().getJavaStringCheckUtf8();
            Descriptors.FieldDescriptor.JavaType javaType = fieldDescriptor.getJavaType();
            Descriptors.FieldDescriptor.JavaType javaType2 = Descriptors.FieldDescriptor.JavaType.ENUM;
            Internal.EnumVerifier aVar = javaType == javaType2 ? new a(fieldDescriptor) : enumVerifier;
            if (fieldDescriptor.getContainingOneof() != null) {
                e2.withField(f(cls, fieldDescriptor, eVar, javaStringCheckUtf8, aVar));
            } else {
                java.lang.reflect.Field l = l(cls, fieldDescriptor);
                int number = fieldDescriptor.getNumber();
                FieldType q = q(fieldDescriptor);
                if (fieldDescriptor.isMapField()) {
                    Descriptors.FieldDescriptor findFieldByNumber = fieldDescriptor.getMessageType().findFieldByNumber(2);
                    if (findFieldByNumber.getJavaType() == javaType2) {
                        aVar = new b(findFieldByNumber);
                    }
                    e2.withField(FieldInfo.e(l, number, u0.C(cls, fieldDescriptor.getName()), aVar));
                } else if (!fieldDescriptor.isRepeated()) {
                    if (field == null) {
                        field = e(cls, i2);
                    }
                    if (fieldDescriptor.isRequired()) {
                        e2.withField(FieldInfo.j(l, number, q, field, i3, javaStringCheckUtf8, aVar));
                    } else {
                        e2.withField(FieldInfo.i(l, number, q, field, i3, javaStringCheckUtf8, aVar));
                    }
                } else if (aVar != null) {
                    if (fieldDescriptor.isPacked()) {
                        e2.withField(FieldInfo.h(l, number, q, aVar, g(cls, fieldDescriptor)));
                    } else {
                        e2.withField(FieldInfo.d(l, number, q, aVar));
                    }
                } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    e2.withField(FieldInfo.k(l, number, q, t(cls, fieldDescriptor)));
                } else if (fieldDescriptor.isPacked()) {
                    e2.withField(FieldInfo.g(l, number, q, g(cls, fieldDescriptor)));
                } else {
                    e2.withField(FieldInfo.c(l, number, q, javaStringCheckUtf8));
                }
                i++;
                enumVerifier = null;
            }
            i3 <<= 1;
            if (i3 == 0) {
                i2++;
                i3 = 1;
                field = null;
            }
            i++;
            enumVerifier = null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < fields.size(); i4++) {
            Descriptors.FieldDescriptor fieldDescriptor2 = fields.get(i4);
            if (fieldDescriptor2.isRequired() || (fieldDescriptor2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE && v(fieldDescriptor2.getMessageType()))) {
                arrayList.add(Integer.valueOf(fieldDescriptor2.getNumber()));
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            iArr[i5] = ((Integer) arrayList.get(i5)).intValue();
        }
        e2.withCheckInitialized(iArr);
        return e2.build();
    }

    public static StructuralMessageInfo j(Class<?> cls, Descriptors.Descriptor descriptor) {
        List<Descriptors.FieldDescriptor> fields = descriptor.getFields();
        StructuralMessageInfo.Builder e2 = StructuralMessageInfo.e(fields.size());
        e2.withDefaultInstance(o(cls));
        e2.withSyntax(ProtoSyntax.PROTO3);
        e eVar = new e(null);
        for (int i = 0; i < fields.size(); i++) {
            Descriptors.FieldDescriptor fieldDescriptor = fields.get(i);
            if (fieldDescriptor.getContainingOneof() != null) {
                e2.withField(f(cls, fieldDescriptor, eVar, true, null));
            } else if (fieldDescriptor.isMapField()) {
                e2.withField(FieldInfo.e(l(cls, fieldDescriptor), fieldDescriptor.getNumber(), u0.C(cls, fieldDescriptor.getName()), null));
            } else if (fieldDescriptor.isRepeated() && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                e2.withField(FieldInfo.k(l(cls, fieldDescriptor), fieldDescriptor.getNumber(), q(fieldDescriptor), t(cls, fieldDescriptor)));
            } else if (fieldDescriptor.isPacked()) {
                e2.withField(FieldInfo.g(l(cls, fieldDescriptor), fieldDescriptor.getNumber(), q(fieldDescriptor), g(cls, fieldDescriptor)));
            } else {
                e2.withField(FieldInfo.c(l(cls, fieldDescriptor), fieldDescriptor.getNumber(), q(fieldDescriptor), true));
            }
        }
        return e2.build();
    }

    public static Descriptors.Descriptor k(Class<?> cls) {
        return o(cls).getDescriptorForType();
    }

    public static java.lang.reflect.Field l(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        return m(cls, p(fieldDescriptor));
    }

    public static java.lang.reflect.Field m(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Unable to find field " + str + " in message class " + cls.getName());
        }
    }

    public static String n(Descriptors.FieldDescriptor fieldDescriptor) {
        return w(fieldDescriptor.getName()) + "MemoizedSerializedSize";
    }

    public static Message o(Class<?> cls) {
        try {
            return (Message) cls.getDeclaredMethod("getDefaultInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            throw new IllegalArgumentException("Unable to get default instance for message class " + cls.getName(), e2);
        }
    }

    public static String p(Descriptors.FieldDescriptor fieldDescriptor) {
        String name;
        if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
            name = fieldDescriptor.getMessageType().getName();
        } else {
            name = fieldDescriptor.getName();
        }
        String str = f11733a.contains(name) ? "__" : "_";
        return w(name) + str;
    }

    public static FieldType q(Descriptors.FieldDescriptor fieldDescriptor) {
        switch (c.c[fieldDescriptor.getType().ordinal()]) {
            case 1:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.BOOL_LIST_PACKED : FieldType.BOOL_LIST;
                }
                return FieldType.BOOL;
            case 2:
                return fieldDescriptor.isRepeated() ? FieldType.BYTES_LIST : FieldType.BYTES;
            case 3:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.DOUBLE_LIST_PACKED : FieldType.DOUBLE_LIST;
                }
                return FieldType.DOUBLE;
            case 4:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.ENUM_LIST_PACKED : FieldType.ENUM_LIST;
                }
                return FieldType.ENUM;
            case 5:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.FIXED32_LIST_PACKED : FieldType.FIXED32_LIST;
                }
                return FieldType.FIXED32;
            case 6:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.FIXED64_LIST_PACKED : FieldType.FIXED64_LIST;
                }
                return FieldType.FIXED64;
            case 7:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.FLOAT_LIST_PACKED : FieldType.FLOAT_LIST;
                }
                return FieldType.FLOAT;
            case 8:
                return fieldDescriptor.isRepeated() ? FieldType.GROUP_LIST : FieldType.GROUP;
            case 9:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.INT32_LIST_PACKED : FieldType.INT32_LIST;
                }
                return FieldType.INT32;
            case 10:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.INT64_LIST_PACKED : FieldType.INT64_LIST;
                }
                return FieldType.INT64;
            case 11:
                if (fieldDescriptor.isMapField()) {
                    return FieldType.MAP;
                }
                return fieldDescriptor.isRepeated() ? FieldType.MESSAGE_LIST : FieldType.MESSAGE;
            case 12:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SFIXED32_LIST_PACKED : FieldType.SFIXED32_LIST;
                }
                return FieldType.SFIXED32;
            case 13:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SFIXED64_LIST_PACKED : FieldType.SFIXED64_LIST;
                }
                return FieldType.SFIXED64;
            case 14:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SINT32_LIST_PACKED : FieldType.SINT32_LIST;
                }
                return FieldType.SINT32;
            case 15:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SINT64_LIST_PACKED : FieldType.SINT64_LIST;
                }
                return FieldType.SINT64;
            case 16:
                return fieldDescriptor.isRepeated() ? FieldType.STRING_LIST : FieldType.STRING;
            case 17:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.UINT32_LIST_PACKED : FieldType.UINT32_LIST;
                }
                return FieldType.UINT32;
            case 18:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.UINT64_LIST_PACKED : FieldType.UINT64_LIST;
                }
                return FieldType.UINT64;
            default:
                throw new IllegalArgumentException("Unsupported field type: " + fieldDescriptor.getType());
        }
    }

    public static Class<?> r(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor, FieldType fieldType) {
        switch (c.b[fieldType.getJavaType().ordinal()]) {
            case 1:
                return Boolean.class;
            case 2:
                return ByteString.class;
            case 3:
                return Double.class;
            case 4:
                return Float.class;
            case 5:
            case 6:
                return Integer.class;
            case 7:
                return Long.class;
            case 8:
                return String.class;
            case 9:
                return s(cls, fieldDescriptor);
            default:
                throw new IllegalArgumentException("Invalid type for oneof: " + fieldType);
        }
    }

    public static Class<?> s(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        try {
            return cls.getDeclaredMethod(u(fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fieldDescriptor.getMessageType().getName() : fieldDescriptor.getName()), new Class[0]).getReturnType();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Class<?> t(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        try {
            return cls.getDeclaredMethod(u(fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fieldDescriptor.getMessageType().getName() : fieldDescriptor.getName()), Integer.TYPE).getReturnType();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String u(String str) {
        String w = w(str);
        return "get" + Character.toUpperCase(w.charAt(0)) + w.substring(1, w.length());
    }

    public static boolean v(Descriptors.Descriptor descriptor) {
        return b.c(descriptor);
    }

    public static String w(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 1);
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '_') {
                if (Character.isDigit(charAt)) {
                    sb.append(charAt);
                } else {
                    if (z) {
                        sb.append(Character.toUpperCase(charAt));
                        z = false;
                    } else if (i == 0) {
                        sb.append(Character.toLowerCase(charAt));
                    } else {
                        sb.append(charAt);
                    }
                }
            }
            z = true;
        }
        return sb.toString();
    }

    @Override // com.google.protobuf.b0
    public a0 a(Class<?> cls) {
        if (GeneratedMessageV3.class.isAssignableFrom(cls)) {
            return h(cls, k(cls));
        }
        throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
    }

    @Override // com.google.protobuf.b0
    public boolean b(Class<?> cls) {
        return GeneratedMessageV3.class.isAssignableFrom(cls);
    }

    /* loaded from: classes11.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final Map<Descriptors.Descriptor, Boolean> f11737a = new ConcurrentHashMap();
        public int b = 0;
        public final Stack<a> c = new Stack<>();
        public final Map<Descriptors.Descriptor, a> d = new HashMap();

        /* loaded from: classes11.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            public final Descriptors.Descriptor f11738a;
            public final int b;
            public int c;
            public b d = null;

            public a(Descriptors.Descriptor descriptor, int i) {
                this.f11738a = descriptor;
                this.b = i;
                this.c = i;
            }
        }

        public final void a(b bVar) {
            boolean z;
            b bVar2;
            Iterator<Descriptors.Descriptor> it = bVar.f11739a.iterator();
            loop0: while (true) {
                z = true;
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Descriptors.Descriptor next = it.next();
                if (next.isExtendable()) {
                    break;
                }
                for (Descriptors.FieldDescriptor fieldDescriptor : next.getFields()) {
                    if (fieldDescriptor.isRequired() || (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE && (bVar2 = this.d.get(fieldDescriptor.getMessageType()).d) != bVar && bVar2.b)) {
                        break loop0;
                    }
                }
            }
            bVar.b = z;
            for (Descriptors.Descriptor descriptor : bVar.f11739a) {
                this.f11737a.put(descriptor, Boolean.valueOf(bVar.b));
            }
        }

        public final a b(Descriptors.Descriptor descriptor) {
            a pop;
            int i = this.b;
            this.b = i + 1;
            a aVar = new a(descriptor, i);
            this.c.push(aVar);
            this.d.put(descriptor, aVar);
            for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
                if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    a aVar2 = this.d.get(fieldDescriptor.getMessageType());
                    if (aVar2 == null) {
                        aVar.c = Math.min(aVar.c, b(fieldDescriptor.getMessageType()).c);
                    } else if (aVar2.d == null) {
                        aVar.c = Math.min(aVar.c, aVar2.c);
                    }
                }
            }
            if (aVar.b == aVar.c) {
                b bVar = new b(null);
                do {
                    pop = this.c.pop();
                    pop.d = bVar;
                    bVar.f11739a.add(pop.f11738a);
                } while (pop != aVar);
                a(bVar);
            }
            return aVar;
        }

        public boolean c(Descriptors.Descriptor descriptor) {
            Boolean bool = this.f11737a.get(descriptor);
            if (bool != null) {
                return bool.booleanValue();
            }
            synchronized (this) {
                Boolean bool2 = this.f11737a.get(descriptor);
                if (bool2 != null) {
                    return bool2.booleanValue();
                }
                return b(descriptor).d.b;
            }
        }

        /* loaded from: classes11.dex */
        public static class b {

            /* renamed from: a  reason: collision with root package name */
            public final List<Descriptors.Descriptor> f11739a;
            public boolean b;

            public b() {
                this.f11739a = new ArrayList();
                this.b = false;
            }

            public /* synthetic */ b(a aVar) {
                this();
            }
        }
    }

    /* loaded from: classes11.dex */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        public l0[] f11740a;

        public e() {
            this.f11740a = new l0[2];
        }

        public static l0 b(Class<?> cls, Descriptors.OneofDescriptor oneofDescriptor) {
            String w = i.w(oneofDescriptor.getName());
            return new l0(oneofDescriptor.getIndex(), i.m(cls, w + "Case_"), i.m(cls, w + "_"));
        }

        public l0 a(Class<?> cls, Descriptors.OneofDescriptor oneofDescriptor) {
            int index = oneofDescriptor.getIndex();
            l0[] l0VarArr = this.f11740a;
            if (index >= l0VarArr.length) {
                this.f11740a = (l0[]) Arrays.copyOf(l0VarArr, index * 2);
            }
            l0 l0Var = this.f11740a[index];
            if (l0Var == null) {
                l0 b = b(cls, oneofDescriptor);
                this.f11740a[index] = b;
                return b;
            }
            return l0Var;
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }
}
