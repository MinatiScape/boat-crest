package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import com.google.protobuf.c;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes11.dex */
public final class d0<T> implements s0<T> {
    public static final int[] r = new int[0];
    public static final Unsafe s = a1.J();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f11725a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final MessageLite e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final int[] j;
    public final int k;
    public final int l;
    public final g0 m;
    public final t n;
    public final x0<?, ?> o;
    public final l<?> p;
    public final w q;

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11726a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f11726a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11726a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11726a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11726a[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11726a[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11726a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f11726a[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f11726a[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f11726a[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f11726a[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f11726a[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f11726a[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f11726a[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f11726a[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f11726a[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f11726a[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f11726a[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public d0(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, boolean z2, int[] iArr2, int i3, int i4, g0 g0Var, t tVar, x0<?, ?> x0Var, l<?> lVar, w wVar) {
        this.f11725a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.g = messageLite instanceof GeneratedMessageLite;
        this.h = z;
        this.f = lVar != null && lVar.e(messageLite);
        this.i = z2;
        this.j = iArr2;
        this.k = i3;
        this.l = i4;
        this.m = g0Var;
        this.n = tVar;
        this.o = x0Var;
        this.p = lVar;
        this.e = messageLite;
        this.q = wVar;
    }

    public static boolean A(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean D(Object obj, int i, s0 s0Var) {
        return s0Var.e(a1.H(obj, U(i)));
    }

    public static boolean I(int i) {
        return (i & 268435456) != 0;
    }

    public static List<?> J(Object obj, long j) {
        return (List) a1.H(obj, j);
    }

    public static <T> long K(T t, long j) {
        return a1.F(t, j);
    }

    public static <T> d0<T> Q(Class<T> cls, a0 a0Var, g0 g0Var, t tVar, x0<?, ?> x0Var, l<?> lVar, w wVar) {
        if (a0Var instanceof p0) {
            return S((p0) a0Var, g0Var, tVar, x0Var, lVar, wVar);
        }
        return R((StructuralMessageInfo) a0Var, g0Var, tVar, x0Var, lVar, wVar);
    }

    public static <T> d0<T> R(StructuralMessageInfo structuralMessageInfo, g0 g0Var, t tVar, x0<?, ?> x0Var, l<?> lVar, w wVar) {
        int o;
        int o2;
        int i;
        boolean z = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] d = structuralMessageInfo.d();
        if (d.length == 0) {
            o = 0;
            o2 = 0;
        } else {
            o = d[0].o();
            o2 = d[d.length - 1].o();
        }
        int length = d.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i2 = 0;
        int i3 = 0;
        for (FieldInfo fieldInfo : d) {
            if (fieldInfo.u() == FieldType.MAP) {
                i2++;
            } else if (fieldInfo.u().id() >= 18 && fieldInfo.u().id() <= 49) {
                i3++;
            }
        }
        int[] iArr2 = i2 > 0 ? new int[i2] : null;
        int[] iArr3 = i3 > 0 ? new int[i3] : null;
        int[] c = structuralMessageInfo.c();
        if (c == null) {
            c = r;
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < d.length) {
            FieldInfo fieldInfo2 = d[i4];
            int o3 = fieldInfo2.o();
            q0(fieldInfo2, iArr, i5, objArr);
            if (i6 < c.length && c[i6] == o3) {
                c[i6] = i5;
                i6++;
            }
            if (fieldInfo2.u() == FieldType.MAP) {
                iArr2[i7] = i5;
                i7++;
            } else if (fieldInfo2.u().id() >= 18 && fieldInfo2.u().id() <= 49) {
                i = i5;
                iArr3[i8] = (int) a1.N(fieldInfo2.n());
                i8++;
                i4++;
                i5 = i + 3;
            }
            i = i5;
            i4++;
            i5 = i + 3;
        }
        if (iArr2 == null) {
            iArr2 = r;
        }
        if (iArr3 == null) {
            iArr3 = r;
        }
        int[] iArr4 = new int[c.length + iArr2.length + iArr3.length];
        System.arraycopy(c, 0, iArr4, 0, c.length);
        System.arraycopy(iArr2, 0, iArr4, c.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, c.length + iArr2.length, iArr3.length);
        return new d0<>(iArr, objArr, o, o2, structuralMessageInfo.b(), z, true, iArr4, c.length, c.length + iArr2.length, g0Var, tVar, x0Var, lVar, wVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x037a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.google.protobuf.d0<T> S(com.google.protobuf.p0 r34, com.google.protobuf.g0 r35, com.google.protobuf.t r36, com.google.protobuf.x0<?, ?> r37, com.google.protobuf.l<?> r38, com.google.protobuf.w r39) {
        /*
            Method dump skipped, instructions count: 996
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d0.S(com.google.protobuf.p0, com.google.protobuf.g0, com.google.protobuf.t, com.google.protobuf.x0, com.google.protobuf.l, com.google.protobuf.w):com.google.protobuf.d0");
    }

    public static long U(int i) {
        return i & ErrorCode.ERR_UNKNOWN;
    }

    public static <T> boolean V(T t, long j) {
        return ((Boolean) a1.H(t, j)).booleanValue();
    }

    public static <T> double W(T t, long j) {
        return ((Double) a1.H(t, j)).doubleValue();
    }

    public static <T> float X(T t, long j) {
        return ((Float) a1.H(t, j)).floatValue();
    }

    public static <T> int Y(T t, long j) {
        return ((Integer) a1.H(t, j)).intValue();
    }

    public static <T> long Z(T t, long j) {
        return ((Long) a1.H(t, j)).longValue();
    }

    public static <T> boolean k(T t, long j) {
        return a1.u(t, j);
    }

    public static java.lang.reflect.Field m0(Class<?> cls, String str) {
        java.lang.reflect.Field[] declaredFields;
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            for (java.lang.reflect.Field field : cls.getDeclaredFields()) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    public static <T> double n(T t, long j) {
        return a1.B(t, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void q0(com.google.protobuf.FieldInfo r8, int[] r9, int r10, java.lang.Object[] r11) {
        /*
            com.google.protobuf.l0 r0 = r8.r()
            r1 = 0
            if (r0 == 0) goto L25
            com.google.protobuf.FieldType r2 = r8.u()
            int r2 = r2.id()
            int r2 = r2 + 51
            java.lang.reflect.Field r3 = r0.b()
            long r3 = com.google.protobuf.a1.N(r3)
            int r3 = (int) r3
            java.lang.reflect.Field r0 = r0.a()
            long r4 = com.google.protobuf.a1.N(r0)
        L22:
            int r0 = (int) r4
            r4 = r1
            goto L6c
        L25:
            com.google.protobuf.FieldType r0 = r8.u()
            java.lang.reflect.Field r2 = r8.n()
            long r2 = com.google.protobuf.a1.N(r2)
            int r3 = (int) r2
            int r2 = r0.id()
            boolean r4 = r0.isList()
            if (r4 != 0) goto L5a
            boolean r0 = r0.isMap()
            if (r0 != 0) goto L5a
            java.lang.reflect.Field r0 = r8.s()
            if (r0 != 0) goto L4c
            r0 = 1048575(0xfffff, float:1.469367E-39)
            goto L51
        L4c:
            long r4 = com.google.protobuf.a1.N(r0)
            int r0 = (int) r4
        L51:
            int r4 = r8.t()
            int r4 = java.lang.Integer.numberOfTrailingZeros(r4)
            goto L6c
        L5a:
            java.lang.reflect.Field r0 = r8.l()
            if (r0 != 0) goto L63
            r0 = r1
            r4 = r0
            goto L6c
        L63:
            java.lang.reflect.Field r0 = r8.l()
            long r4 = com.google.protobuf.a1.N(r0)
            goto L22
        L6c:
            int r5 = r8.o()
            r9[r10] = r5
            int r5 = r10 + 1
            boolean r6 = r8.v()
            if (r6 == 0) goto L7d
            r6 = 536870912(0x20000000, float:1.0842022E-19)
            goto L7e
        L7d:
            r6 = r1
        L7e:
            boolean r7 = r8.x()
            if (r7 == 0) goto L86
            r1 = 268435456(0x10000000, float:2.5243549E-29)
        L86:
            r1 = r1 | r6
            int r2 = r2 << 20
            r1 = r1 | r2
            r1 = r1 | r3
            r9[r5] = r1
            int r1 = r10 + 2
            int r2 = r4 << 20
            r0 = r0 | r2
            r9[r1] = r0
            java.lang.Class r9 = r8.q()
            java.lang.Object r0 = r8.p()
            if (r0 == 0) goto Lbe
            int r10 = r10 / 3
            int r10 = r10 * 2
            java.lang.Object r0 = r8.p()
            r11[r10] = r0
            if (r9 == 0) goto Laf
            int r10 = r10 + 1
            r11[r10] = r9
            goto Ldb
        Laf:
            com.google.protobuf.Internal$EnumVerifier r9 = r8.m()
            if (r9 == 0) goto Ldb
            int r10 = r10 + 1
            com.google.protobuf.Internal$EnumVerifier r8 = r8.m()
            r11[r10] = r8
            goto Ldb
        Lbe:
            if (r9 == 0) goto Lc9
            int r10 = r10 / 3
            int r10 = r10 * 2
            int r10 = r10 + 1
            r11[r10] = r9
            goto Ldb
        Lc9:
            com.google.protobuf.Internal$EnumVerifier r9 = r8.m()
            if (r9 == 0) goto Ldb
            int r10 = r10 / 3
            int r10 = r10 * 2
            int r10 = r10 + 1
            com.google.protobuf.Internal$EnumVerifier r8 = r8.m()
            r11[r10] = r8
        Ldb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d0.q0(com.google.protobuf.FieldInfo, int[], int, java.lang.Object[]):void");
    }

    public static <T> float r(T t, long j) {
        return a1.C(t, j);
    }

    public static int r0(int i) {
        return (i & 267386880) >>> 20;
    }

    public static UnknownFieldSetLite v(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
            generatedMessageLite.unknownFields = newInstance;
            return newInstance;
        }
        return unknownFieldSetLite;
    }

    public static <T> int z(T t, long j) {
        return a1.D(t, j);
    }

    public final boolean B(T t, int i) {
        int h0 = h0(i);
        long j = 1048575 & h0;
        if (j != 1048575) {
            return (a1.D(t, j) & (1 << (h0 >>> 20))) != 0;
        }
        int s0 = s0(i);
        long U = U(s0);
        switch (r0(s0)) {
            case 0:
                return a1.B(t, U) != 0.0d;
            case 1:
                return a1.C(t, U) != 0.0f;
            case 2:
                return a1.F(t, U) != 0;
            case 3:
                return a1.F(t, U) != 0;
            case 4:
                return a1.D(t, U) != 0;
            case 5:
                return a1.F(t, U) != 0;
            case 6:
                return a1.D(t, U) != 0;
            case 7:
                return a1.u(t, U);
            case 8:
                Object H = a1.H(t, U);
                if (H instanceof String) {
                    return !((String) H).isEmpty();
                }
                if (H instanceof ByteString) {
                    return !ByteString.EMPTY.equals(H);
                }
                throw new IllegalArgumentException();
            case 9:
                return a1.H(t, U) != null;
            case 10:
                return !ByteString.EMPTY.equals(a1.H(t, U));
            case 11:
                return a1.D(t, U) != 0;
            case 12:
                return a1.D(t, U) != 0;
            case 13:
                return a1.D(t, U) != 0;
            case 14:
                return a1.F(t, U) != 0;
            case 15:
                return a1.D(t, U) != 0;
            case 16:
                return a1.F(t, U) != 0;
            case 17:
                return a1.H(t, U) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean C(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return B(t, i);
        }
        return (i3 & i4) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N> boolean E(Object obj, int i, int i2) {
        List list = (List) a1.H(obj, U(i));
        if (list.isEmpty()) {
            return true;
        }
        s0 u = u(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!u.e(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.protobuf.s0] */
    public final boolean F(T t, int i, int i2) {
        Map<?, ?> g = this.q.g(a1.H(t, U(i)));
        if (g.isEmpty()) {
            return true;
        }
        if (this.q.d(t(i2)).c.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        s0<T> s0Var = 0;
        for (Object obj : g.values()) {
            if (s0Var == null) {
                s0Var = n0.a().d(obj.getClass());
            }
            boolean e = s0Var.e(obj);
            s0Var = s0Var;
            if (!e) {
                return false;
            }
        }
        return true;
    }

    public final boolean G(T t, T t2, int i) {
        long h0 = h0(i) & ErrorCode.ERR_UNKNOWN;
        return a1.D(t, h0) == a1.D(t2, h0);
    }

    public final boolean H(T t, int i, int i2) {
        return a1.D(t, (long) (h0(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0077, code lost:
        r0 = r16.k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007b, code lost:
        if (r0 >= r16.l) goto L328;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007d, code lost:
        r13 = p(r19, r16.j[r0], r13, r17);
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0088, code lost:
        if (r13 == null) goto L332;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008a, code lost:
        r17.o(r19, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008d, code lost:
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void L(com.google.protobuf.x0<UT, UB> r17, com.google.protobuf.l<ET> r18, T r19, com.google.protobuf.q0 r20, com.google.protobuf.ExtensionRegistryLite r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1720
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d0.L(com.google.protobuf.x0, com.google.protobuf.l, java.lang.Object, com.google.protobuf.q0, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public final <K, V> void M(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, q0 q0Var) throws IOException {
        long U = U(s0(i));
        Object H = a1.H(obj, U);
        if (H == null) {
            H = this.q.f(obj2);
            a1.Z(obj, U, H);
        } else if (this.q.j(H)) {
            Object f = this.q.f(obj2);
            this.q.c(f, H);
            a1.Z(obj, U, f);
            H = f;
        }
        q0Var.L(this.q.e(H), this.q.d(obj2), extensionRegistryLite);
    }

    public final void N(T t, T t2, int i) {
        long U = U(s0(i));
        if (B(t2, i)) {
            Object H = a1.H(t, U);
            Object H2 = a1.H(t2, U);
            if (H != null && H2 != null) {
                a1.Z(t, U, Internal.mergeMessage(H, H2));
                n0(t, i);
            } else if (H2 != null) {
                a1.Z(t, U, H2);
                n0(t, i);
            }
        }
    }

    public final void O(T t, T t2, int i) {
        int s0 = s0(i);
        int T = T(i);
        long U = U(s0);
        if (H(t2, T, i)) {
            Object H = H(t, T, i) ? a1.H(t, U) : null;
            Object H2 = a1.H(t2, U);
            if (H != null && H2 != null) {
                a1.Z(t, U, Internal.mergeMessage(H, H2));
                o0(t, T, i);
            } else if (H2 != null) {
                a1.Z(t, U, H2);
                o0(t, T, i);
            }
        }
    }

    public final void P(T t, T t2, int i) {
        int s0 = s0(i);
        long U = U(s0);
        int T = T(i);
        switch (r0(s0)) {
            case 0:
                if (B(t2, i)) {
                    a1.V(t, U, a1.B(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 1:
                if (B(t2, i)) {
                    a1.W(t, U, a1.C(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 2:
                if (B(t2, i)) {
                    a1.Y(t, U, a1.F(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 3:
                if (B(t2, i)) {
                    a1.Y(t, U, a1.F(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 4:
                if (B(t2, i)) {
                    a1.X(t, U, a1.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 5:
                if (B(t2, i)) {
                    a1.Y(t, U, a1.F(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 6:
                if (B(t2, i)) {
                    a1.X(t, U, a1.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 7:
                if (B(t2, i)) {
                    a1.O(t, U, a1.u(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 8:
                if (B(t2, i)) {
                    a1.Z(t, U, a1.H(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 9:
                N(t, t2, i);
                return;
            case 10:
                if (B(t2, i)) {
                    a1.Z(t, U, a1.H(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 11:
                if (B(t2, i)) {
                    a1.X(t, U, a1.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 12:
                if (B(t2, i)) {
                    a1.X(t, U, a1.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 13:
                if (B(t2, i)) {
                    a1.X(t, U, a1.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 14:
                if (B(t2, i)) {
                    a1.Y(t, U, a1.F(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 15:
                if (B(t2, i)) {
                    a1.X(t, U, a1.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 16:
                if (B(t2, i)) {
                    a1.Y(t, U, a1.F(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 17:
                N(t, t2, i);
                return;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.n.d(t, t2, U);
                return;
            case 50:
                u0.G(this.q, t, t2, U);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (H(t2, T, i)) {
                    a1.Z(t, U, a1.H(t2, U));
                    o0(t, T, i);
                    return;
                }
                return;
            case 60:
                O(t, t2, i);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (H(t2, T, i)) {
                    a1.Z(t, U, a1.H(t2, U));
                    o0(t, T, i);
                    return;
                }
                return;
            case 68:
                O(t, t2, i);
                return;
            default:
                return;
        }
    }

    public final int T(int i) {
        return this.f11725a[i];
    }

    @Override // com.google.protobuf.s0
    public int a(T t) {
        int i;
        int hashLong;
        int length = this.f11725a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int s0 = s0(i3);
            int T = T(i3);
            long U = U(s0);
            int i4 = 37;
            switch (r0(s0)) {
                case 0:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(Double.doubleToLongBits(a1.B(t, U)));
                    i2 = i + hashLong;
                    break;
                case 1:
                    i = i2 * 53;
                    hashLong = Float.floatToIntBits(a1.C(t, U));
                    i2 = i + hashLong;
                    break;
                case 2:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(a1.F(t, U));
                    i2 = i + hashLong;
                    break;
                case 3:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(a1.F(t, U));
                    i2 = i + hashLong;
                    break;
                case 4:
                    i = i2 * 53;
                    hashLong = a1.D(t, U);
                    i2 = i + hashLong;
                    break;
                case 5:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(a1.F(t, U));
                    i2 = i + hashLong;
                    break;
                case 6:
                    i = i2 * 53;
                    hashLong = a1.D(t, U);
                    i2 = i + hashLong;
                    break;
                case 7:
                    i = i2 * 53;
                    hashLong = Internal.hashBoolean(a1.u(t, U));
                    i2 = i + hashLong;
                    break;
                case 8:
                    i = i2 * 53;
                    hashLong = ((String) a1.H(t, U)).hashCode();
                    i2 = i + hashLong;
                    break;
                case 9:
                    Object H = a1.H(t, U);
                    if (H != null) {
                        i4 = H.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 10:
                    i = i2 * 53;
                    hashLong = a1.H(t, U).hashCode();
                    i2 = i + hashLong;
                    break;
                case 11:
                    i = i2 * 53;
                    hashLong = a1.D(t, U);
                    i2 = i + hashLong;
                    break;
                case 12:
                    i = i2 * 53;
                    hashLong = a1.D(t, U);
                    i2 = i + hashLong;
                    break;
                case 13:
                    i = i2 * 53;
                    hashLong = a1.D(t, U);
                    i2 = i + hashLong;
                    break;
                case 14:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(a1.F(t, U));
                    i2 = i + hashLong;
                    break;
                case 15:
                    i = i2 * 53;
                    hashLong = a1.D(t, U);
                    i2 = i + hashLong;
                    break;
                case 16:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(a1.F(t, U));
                    i2 = i + hashLong;
                    break;
                case 17:
                    Object H2 = a1.H(t, U);
                    if (H2 != null) {
                        i4 = H2.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    hashLong = a1.H(t, U).hashCode();
                    i2 = i + hashLong;
                    break;
                case 50:
                    i = i2 * 53;
                    hashLong = a1.H(t, U).hashCode();
                    i2 = i + hashLong;
                    break;
                case 51:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Double.doubleToLongBits(W(t, U)));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Float.floatToIntBits(X(t, U));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Z(t, U));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Z(t, U));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Y(t, U);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Z(t, U));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Y(t, U);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashBoolean(V(t, U));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = ((String) a1.H(t, U)).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = a1.H(t, U).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = a1.H(t, U).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Y(t, U);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Y(t, U);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Y(t, U);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Z(t, U));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Y(t, U);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Z(t, U));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = a1.H(t, U).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.o.g(t).hashCode();
        return this.f ? (hashCode * 53) + this.p.c(t).hashCode() : hashCode;
    }

    public final <K, V> int a0(T t, byte[] bArr, int i, int i2, int i3, long j, c.b bVar) throws IOException {
        Unsafe unsafe = s;
        Object t2 = t(i3);
        Object object = unsafe.getObject(t, j);
        if (this.q.j(object)) {
            Object f = this.q.f(t2);
            this.q.c(f, object);
            unsafe.putObject(t, j, f);
            object = f;
        }
        return l(bArr, i, i2, this.q.d(t2), this.q.e(object), bVar);
    }

    @Override // com.google.protobuf.s0
    public boolean b(T t, T t2) {
        int length = this.f11725a.length;
        for (int i = 0; i < length; i += 3) {
            if (!o(t, t2, i)) {
                return false;
            }
        }
        if (this.o.g(t).equals(this.o.g(t2))) {
            if (this.f) {
                return this.p.c(t).equals(this.p.c(t2));
            }
            return true;
        }
        return false;
    }

    public final int b0(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, c.b bVar) throws IOException {
        Unsafe unsafe = s;
        long j2 = this.f11725a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(c.d(bArr, i)));
                    int i9 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(c.l(bArr, i)));
                    int i10 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i10;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int L = c.L(bArr, i, bVar);
                    unsafe.putObject(t, j, Long.valueOf(bVar.b));
                    unsafe.putInt(t, j2, i4);
                    return L;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int I = c.I(bArr, i, bVar);
                    unsafe.putObject(t, j, Integer.valueOf(bVar.f11721a));
                    unsafe.putInt(t, j2, i4);
                    return I;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(c.j(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i11;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(c.h(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i12;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int L2 = c.L(bArr, i, bVar);
                    unsafe.putObject(t, j, Boolean.valueOf(bVar.b != 0));
                    unsafe.putInt(t, j2, i4);
                    return L2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int I2 = c.I(bArr, i, bVar);
                    int i13 = bVar.f11721a;
                    if (i13 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !b1.u(bArr, I2, I2 + i13)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    } else {
                        unsafe.putObject(t, j, new String(bArr, I2, i13, Internal.UTF_8));
                        I2 += i13;
                    }
                    unsafe.putInt(t, j2, i4);
                    return I2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int p = c.p(u(i8), bArr, i, i2, bVar);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, bVar.c);
                    } else {
                        unsafe.putObject(t, j, Internal.mergeMessage(object, bVar.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return p;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int b = c.b(bArr, i, bVar);
                    unsafe.putObject(t, j, bVar.c);
                    unsafe.putInt(t, j2, i4);
                    return b;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int I3 = c.I(bArr, i, bVar);
                    int i14 = bVar.f11721a;
                    Internal.EnumVerifier s2 = s(i8);
                    if (s2 != null && !s2.isInRange(i14)) {
                        v(t).storeField(i3, Long.valueOf(i14));
                    } else {
                        unsafe.putObject(t, j, Integer.valueOf(i14));
                        unsafe.putInt(t, j2, i4);
                    }
                    return I3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int I4 = c.I(bArr, i, bVar);
                    unsafe.putObject(t, j, Integer.valueOf(CodedInputStream.decodeZigZag32(bVar.f11721a)));
                    unsafe.putInt(t, j2, i4);
                    return I4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int L3 = c.L(bArr, i, bVar);
                    unsafe.putObject(t, j, Long.valueOf(CodedInputStream.decodeZigZag64(bVar.b)));
                    unsafe.putInt(t, j2, i4);
                    return L3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int n = c.n(u(i8), bArr, i, i2, (i3 & (-8)) | 4, bVar);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, bVar.c);
                    } else {
                        unsafe.putObject(t, j, Internal.mergeMessage(object2, bVar.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return n;
                }
                break;
        }
        return i;
    }

    @Override // com.google.protobuf.s0
    public void c(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.f11725a.length; i += 3) {
            P(t, t2, i);
        }
        u0.H(this.o, t, t2);
        if (this.f) {
            u0.F(this.p, t, t2);
        }
    }

    public int c0(T t, byte[] bArr, int i, int i2, int i3, c.b bVar) throws IOException {
        Unsafe unsafe;
        int i4;
        d0<T> d0Var;
        int i5;
        T t2;
        byte b;
        int f0;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        T t3;
        T t4;
        int i14;
        T t5;
        int i15;
        int i16;
        d0<T> d0Var2 = this;
        T t6 = t;
        byte[] bArr2 = bArr;
        int i17 = i2;
        int i18 = i3;
        c.b bVar2 = bVar;
        Unsafe unsafe2 = s;
        int i19 = i;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = -1;
        int i24 = ErrorCode.ERR_UNKNOWN;
        while (true) {
            if (i19 < i17) {
                int i25 = i19 + 1;
                byte b2 = bArr2[i19];
                if (b2 < 0) {
                    int H = c.H(b2, bArr2, i25, bVar2);
                    b = bVar2.f11721a;
                    i25 = H;
                } else {
                    b = b2;
                }
                int i26 = b >>> 3;
                int i27 = b & 7;
                if (i26 > i23) {
                    f0 = d0Var2.g0(i26, i20 / 3);
                } else {
                    f0 = d0Var2.f0(i26);
                }
                int i28 = f0;
                if (i28 == -1) {
                    i6 = i26;
                    i7 = i25;
                    i8 = b;
                    i9 = i22;
                    i10 = i24;
                    unsafe = unsafe2;
                    i4 = i18;
                    i11 = 0;
                } else {
                    int i29 = d0Var2.f11725a[i28 + 1];
                    int r0 = r0(i29);
                    long U = U(i29);
                    int i30 = b;
                    if (r0 <= 17) {
                        int i31 = d0Var2.f11725a[i28 + 2];
                        int i32 = 1 << (i31 >>> 20);
                        int i33 = i31 & ErrorCode.ERR_UNKNOWN;
                        if (i33 != i24) {
                            if (i24 != 1048575) {
                                unsafe2.putInt(t6, i24, i22);
                            }
                            i22 = unsafe2.getInt(t6, i33);
                            i10 = i33;
                        } else {
                            i10 = i24;
                        }
                        int i34 = i22;
                        switch (r0) {
                            case 0:
                                t3 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 1) {
                                    a1.V(t3, U, c.d(bArr2, i25));
                                    i19 = i25 + 8;
                                    i22 = i34 | i32;
                                    i17 = i2;
                                    t6 = t3;
                                    i20 = i12;
                                    i21 = i13;
                                    i23 = i6;
                                    i24 = i10;
                                    i18 = i3;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 1:
                                t3 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 5) {
                                    a1.W(t3, U, c.l(bArr2, i25));
                                    i19 = i25 + 4;
                                    i22 = i34 | i32;
                                    i17 = i2;
                                    t6 = t3;
                                    i20 = i12;
                                    i21 = i13;
                                    i23 = i6;
                                    i24 = i10;
                                    i18 = i3;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 2:
                            case 3:
                                T t7 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 0) {
                                    int L = c.L(bArr2, i25, bVar2);
                                    t4 = t7;
                                    unsafe2.putLong(t, U, bVar2.b);
                                    i22 = i34 | i32;
                                    i19 = L;
                                    i20 = i12;
                                    i21 = i13;
                                    t6 = t4;
                                    i23 = i6;
                                    i24 = i10;
                                    i17 = i2;
                                    i18 = i3;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 4:
                            case 11:
                                t3 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 0) {
                                    i19 = c.I(bArr2, i25, bVar2);
                                    unsafe2.putInt(t3, U, bVar2.f11721a);
                                    i22 = i34 | i32;
                                    i17 = i2;
                                    t6 = t3;
                                    i20 = i12;
                                    i21 = i13;
                                    i23 = i6;
                                    i24 = i10;
                                    i18 = i3;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 5:
                            case 14:
                                T t8 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 1) {
                                    t4 = t8;
                                    unsafe2.putLong(t, U, c.j(bArr2, i25));
                                    i19 = i25 + 8;
                                    i22 = i34 | i32;
                                    i20 = i12;
                                    i21 = i13;
                                    t6 = t4;
                                    i23 = i6;
                                    i24 = i10;
                                    i17 = i2;
                                    i18 = i3;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 6:
                            case 13:
                                i14 = i2;
                                t5 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 5) {
                                    unsafe2.putInt(t5, U, c.h(bArr2, i25));
                                    i19 = i25 + 4;
                                    int i35 = i34 | i32;
                                    t6 = t5;
                                    i17 = i14;
                                    i20 = i12;
                                    i21 = i13;
                                    i24 = i10;
                                    i18 = i3;
                                    i22 = i35;
                                    i23 = i6;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 7:
                                i14 = i2;
                                t5 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 0) {
                                    i19 = c.L(bArr2, i25, bVar2);
                                    a1.O(t5, U, bVar2.b != 0);
                                    int i352 = i34 | i32;
                                    t6 = t5;
                                    i17 = i14;
                                    i20 = i12;
                                    i21 = i13;
                                    i24 = i10;
                                    i18 = i3;
                                    i22 = i352;
                                    i23 = i6;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 8:
                                i14 = i2;
                                t5 = t6;
                                i12 = i28;
                                i6 = i26;
                                bArr2 = bArr;
                                i13 = i30;
                                if (i27 == 2) {
                                    if ((i29 & PKIFailureInfo.duplicateCertReq) == 0) {
                                        i19 = c.C(bArr2, i25, bVar2);
                                    } else {
                                        i19 = c.F(bArr2, i25, bVar2);
                                    }
                                    unsafe2.putObject(t5, U, bVar2.c);
                                    int i3522 = i34 | i32;
                                    t6 = t5;
                                    i17 = i14;
                                    i20 = i12;
                                    i21 = i13;
                                    i24 = i10;
                                    i18 = i3;
                                    i22 = i3522;
                                    i23 = i6;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 9:
                                t5 = t6;
                                i12 = i28;
                                i6 = i26;
                                i13 = i30;
                                bArr2 = bArr;
                                if (i27 == 2) {
                                    i14 = i2;
                                    i19 = c.p(d0Var2.u(i12), bArr2, i25, i14, bVar2);
                                    if ((i34 & i32) == 0) {
                                        unsafe2.putObject(t5, U, bVar2.c);
                                    } else {
                                        unsafe2.putObject(t5, U, Internal.mergeMessage(unsafe2.getObject(t5, U), bVar2.c));
                                    }
                                    int i35222 = i34 | i32;
                                    t6 = t5;
                                    i17 = i14;
                                    i20 = i12;
                                    i21 = i13;
                                    i24 = i10;
                                    i18 = i3;
                                    i22 = i35222;
                                    i23 = i6;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 10:
                                t3 = t6;
                                i12 = i28;
                                i6 = i26;
                                i13 = i30;
                                bArr2 = bArr;
                                if (i27 == 2) {
                                    i19 = c.b(bArr2, i25, bVar2);
                                    unsafe2.putObject(t3, U, bVar2.c);
                                    i22 = i34 | i32;
                                    i17 = i2;
                                    t6 = t3;
                                    i20 = i12;
                                    i21 = i13;
                                    i23 = i6;
                                    i24 = i10;
                                    i18 = i3;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 12:
                                t3 = t6;
                                i12 = i28;
                                i6 = i26;
                                i13 = i30;
                                bArr2 = bArr;
                                if (i27 == 0) {
                                    i19 = c.I(bArr2, i25, bVar2);
                                    int i36 = bVar2.f11721a;
                                    Internal.EnumVerifier s2 = d0Var2.s(i12);
                                    if (s2 != null && !s2.isInRange(i36)) {
                                        v(t).storeField(i13, Long.valueOf(i36));
                                        i17 = i2;
                                        t6 = t3;
                                        i22 = i34;
                                        i20 = i12;
                                        i21 = i13;
                                        i23 = i6;
                                        i24 = i10;
                                        i18 = i3;
                                    } else {
                                        unsafe2.putInt(t3, U, i36);
                                        i22 = i34 | i32;
                                        i17 = i2;
                                        t6 = t3;
                                        i20 = i12;
                                        i21 = i13;
                                        i23 = i6;
                                        i24 = i10;
                                        i18 = i3;
                                        break;
                                    }
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                                break;
                            case 15:
                                i12 = i28;
                                i13 = i30;
                                bArr2 = bArr;
                                i6 = i26;
                                if (i27 == 0) {
                                    i19 = c.I(bArr2, i25, bVar2);
                                    t3 = t;
                                    unsafe2.putInt(t3, U, CodedInputStream.decodeZigZag32(bVar2.f11721a));
                                    i22 = i34 | i32;
                                    i17 = i2;
                                    t6 = t3;
                                    i20 = i12;
                                    i21 = i13;
                                    i23 = i6;
                                    i24 = i10;
                                    i18 = i3;
                                    break;
                                } else {
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 16:
                                i12 = i28;
                                i6 = i26;
                                if (i27 == 0) {
                                    bArr2 = bArr;
                                    int L2 = c.L(bArr2, i25, bVar2);
                                    i13 = i30;
                                    unsafe2.putLong(t, U, CodedInputStream.decodeZigZag64(bVar2.b));
                                    i22 = i34 | i32;
                                    t6 = t;
                                    i17 = i2;
                                    i19 = L2;
                                    i20 = i12;
                                    i21 = i13;
                                    i23 = i6;
                                    i24 = i10;
                                    i18 = i3;
                                    break;
                                } else {
                                    i13 = i30;
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            case 17:
                                if (i27 == 3) {
                                    i19 = c.n(d0Var2.u(i28), bArr, i25, i2, (i26 << 3) | 4, bVar);
                                    if ((i34 & i32) == 0) {
                                        unsafe2.putObject(t6, U, bVar2.c);
                                    } else {
                                        unsafe2.putObject(t6, U, Internal.mergeMessage(unsafe2.getObject(t6, U), bVar2.c));
                                    }
                                    i22 = i34 | i32;
                                    bArr2 = bArr;
                                    i17 = i2;
                                    i18 = i3;
                                    i21 = i30;
                                    i20 = i28;
                                    i23 = i26;
                                    i24 = i10;
                                    break;
                                } else {
                                    i12 = i28;
                                    i6 = i26;
                                    i13 = i30;
                                    i7 = i25;
                                    i9 = i34;
                                    i11 = i12;
                                    unsafe = unsafe2;
                                    i8 = i13;
                                    i4 = i3;
                                    break;
                                }
                            default:
                                i12 = i28;
                                i13 = i30;
                                i6 = i26;
                                i7 = i25;
                                i9 = i34;
                                i11 = i12;
                                unsafe = unsafe2;
                                i8 = i13;
                                i4 = i3;
                                break;
                        }
                    } else {
                        i6 = i26;
                        T t9 = t6;
                        bArr2 = bArr;
                        if (r0 != 27) {
                            i11 = i28;
                            i9 = i22;
                            i10 = i24;
                            if (r0 <= 49) {
                                int i37 = i25;
                                unsafe = unsafe2;
                                i16 = i30;
                                i19 = e0(t, bArr, i25, i2, i30, i6, i27, i11, i29, r0, U, bVar);
                                if (i19 != i37) {
                                    d0Var2 = this;
                                    t6 = t;
                                    bArr2 = bArr;
                                    i17 = i2;
                                    i18 = i3;
                                    bVar2 = bVar;
                                    i23 = i6;
                                    i21 = i16;
                                    i20 = i11;
                                    i22 = i9;
                                    i24 = i10;
                                    unsafe2 = unsafe;
                                } else {
                                    i4 = i3;
                                    i7 = i19;
                                    i8 = i16;
                                }
                            } else {
                                i15 = i25;
                                unsafe = unsafe2;
                                i16 = i30;
                                if (r0 != 50) {
                                    i19 = b0(t, bArr, i15, i2, i16, i6, i27, i29, r0, U, i11, bVar);
                                    if (i19 != i15) {
                                        d0Var2 = this;
                                        t6 = t;
                                        bArr2 = bArr;
                                        i17 = i2;
                                        i18 = i3;
                                        bVar2 = bVar;
                                        i23 = i6;
                                        i21 = i16;
                                        i20 = i11;
                                        i22 = i9;
                                        i24 = i10;
                                        unsafe2 = unsafe;
                                    } else {
                                        i4 = i3;
                                        i7 = i19;
                                        i8 = i16;
                                    }
                                } else if (i27 == 2) {
                                    i19 = a0(t, bArr, i15, i2, i11, U, bVar);
                                    if (i19 != i15) {
                                        d0Var2 = this;
                                        t6 = t;
                                        bArr2 = bArr;
                                        i17 = i2;
                                        i18 = i3;
                                        bVar2 = bVar;
                                        i23 = i6;
                                        i21 = i16;
                                        i20 = i11;
                                        i22 = i9;
                                        i24 = i10;
                                        unsafe2 = unsafe;
                                    } else {
                                        i4 = i3;
                                        i7 = i19;
                                        i8 = i16;
                                    }
                                }
                            }
                        } else if (i27 == 2) {
                            Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(t9, U);
                            if (!protobufList.isModifiable()) {
                                int size = protobufList.size();
                                protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
                                unsafe2.putObject(t9, U, protobufList);
                            }
                            i10 = i24;
                            i19 = c.q(d0Var2.u(i28), i30, bArr, i25, i2, protobufList, bVar);
                            t6 = t;
                            i17 = i2;
                            i21 = i30;
                            i23 = i6;
                            i20 = i28;
                            i22 = i22;
                            i24 = i10;
                            i18 = i3;
                        } else {
                            i11 = i28;
                            i9 = i22;
                            i10 = i24;
                            i15 = i25;
                            unsafe = unsafe2;
                            i16 = i30;
                        }
                        i4 = i3;
                        i7 = i15;
                        i8 = i16;
                    }
                }
                if (i8 != i4 || i4 == 0) {
                    if (this.f && bVar.d != ExtensionRegistryLite.getEmptyRegistry()) {
                        i19 = c.g(i8, bArr, i7, i2, t, this.e, this.o, bVar);
                    } else {
                        i19 = c.G(i8, bArr, i7, i2, v(t), bVar);
                    }
                    t6 = t;
                    bArr2 = bArr;
                    i17 = i2;
                    i21 = i8;
                    d0Var2 = this;
                    bVar2 = bVar;
                    i23 = i6;
                    i20 = i11;
                    i22 = i9;
                    i24 = i10;
                    unsafe2 = unsafe;
                    i18 = i4;
                } else {
                    i5 = ErrorCode.ERR_UNKNOWN;
                    d0Var = this;
                    i19 = i7;
                    i21 = i8;
                    i22 = i9;
                    i24 = i10;
                }
            } else {
                unsafe = unsafe2;
                i4 = i18;
                d0Var = d0Var2;
                i5 = ErrorCode.ERR_UNKNOWN;
            }
        }
        if (i24 != i5) {
            t2 = t;
            unsafe.putInt(t2, i24, i22);
        } else {
            t2 = t;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i38 = d0Var.k; i38 < d0Var.l; i38++) {
            unknownFieldSetLite = (UnknownFieldSetLite) d0Var.p(t2, d0Var.j[i38], unknownFieldSetLite, d0Var.o);
        }
        if (unknownFieldSetLite != null) {
            d0Var.o.o(t2, unknownFieldSetLite);
        }
        if (i4 == 0) {
            if (i19 != i2) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i19 > i2 || i21 != i4) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i19;
    }

    @Override // com.google.protobuf.s0
    public void d(T t) {
        int i;
        int i2 = this.k;
        while (true) {
            i = this.l;
            if (i2 >= i) {
                break;
            }
            long U = U(s0(this.j[i2]));
            Object H = a1.H(t, U);
            if (H != null) {
                a1.Z(t, U, this.q.h(H));
            }
            i2++;
        }
        int length = this.j.length;
        while (i < length) {
            this.n.c(t, this.j[i]);
            i++;
        }
        this.o.j(t);
        if (this.f) {
            this.p.f(t);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x028e, code lost:
        if (r0 != r15) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0290, code lost:
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r10 = r18;
        r1 = r19;
        r2 = r20;
        r6 = r24;
        r7 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02d7, code lost:
        if (r0 != r15) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x02fa, code lost:
        if (r0 != r15) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02fd, code lost:
        r2 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int d0(T r31, byte[] r32, int r33, int r34, com.google.protobuf.c.b r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 870
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d0.d0(java.lang.Object, byte[], int, int, com.google.protobuf.c$b):int");
    }

    @Override // com.google.protobuf.s0
    public final boolean e(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.k) {
            int i6 = this.j[i5];
            int T = T(i6);
            int s0 = s0(i6);
            int i7 = this.f11725a[i6 + 2];
            int i8 = i7 & ErrorCode.ERR_UNKNOWN;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i4 = s.getInt(t, i8);
                }
                i2 = i4;
                i = i8;
            } else {
                i = i3;
                i2 = i4;
            }
            if (I(s0) && !C(t, i6, i, i2, i9)) {
                return false;
            }
            int r0 = r0(s0);
            if (r0 != 9 && r0 != 17) {
                if (r0 != 27) {
                    if (r0 == 60 || r0 == 68) {
                        if (H(t, T, i6) && !D(t, s0, u(i6))) {
                            return false;
                        }
                    } else if (r0 != 49) {
                        if (r0 == 50 && !F(t, s0, i6)) {
                            return false;
                        }
                    }
                }
                if (!E(t, s0, i6)) {
                    return false;
                }
            } else if (C(t, i6, i, i2, i9) && !D(t, s0, u(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.f || this.p.c(t).E();
    }

    public final int e0(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, c.b bVar) throws IOException {
        int J;
        Unsafe unsafe = s;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(t, j2);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            unsafe.putObject(t, j2, protobufList);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    return c.s(bArr, i, protobufList, bVar);
                }
                if (i5 == 1) {
                    return c.e(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    return c.v(bArr, i, protobufList, bVar);
                }
                if (i5 == 5) {
                    return c.m(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    return c.z(bArr, i, protobufList, bVar);
                }
                if (i5 == 0) {
                    return c.M(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return c.y(bArr, i, protobufList, bVar);
                }
                if (i5 == 0) {
                    return c.J(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    return c.u(bArr, i, protobufList, bVar);
                }
                if (i5 == 1) {
                    return c.k(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    return c.t(bArr, i, protobufList, bVar);
                }
                if (i5 == 5) {
                    return c.i(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    return c.r(bArr, i, protobufList, bVar);
                }
                if (i5 == 0) {
                    return c.a(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        return c.D(i3, bArr, i, i2, protobufList, bVar);
                    }
                    return c.E(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 27:
                if (i5 == 2) {
                    return c.q(u(i6), i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 28:
                if (i5 == 2) {
                    return c.c(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    J = c.y(bArr, i, protobufList, bVar);
                } else if (i5 == 0) {
                    J = c.J(i3, bArr, i, i2, protobufList, bVar);
                }
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t;
                UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
                if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFieldSetLite = null;
                }
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) u0.A(i4, protobufList, s(i6), unknownFieldSetLite, this.o);
                if (unknownFieldSetLite2 != null) {
                    generatedMessageLite.unknownFields = unknownFieldSetLite2;
                }
                return J;
            case 33:
            case 47:
                if (i5 == 2) {
                    return c.w(bArr, i, protobufList, bVar);
                }
                if (i5 == 0) {
                    return c.A(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 34:
            case 48:
                if (i5 == 2) {
                    return c.x(bArr, i, protobufList, bVar);
                }
                if (i5 == 0) {
                    return c.B(i3, bArr, i, i2, protobufList, bVar);
                }
                break;
            case 49:
                if (i5 == 3) {
                    return c.o(u(i6), i3, bArr, i, i2, protobufList, bVar);
                }
                break;
        }
        return i;
    }

    @Override // com.google.protobuf.s0
    public int f(T t) {
        return this.h ? x(t) : w(t);
    }

    public final int f0(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return p0(i, 0);
    }

    @Override // com.google.protobuf.s0
    public void g(T t, Writer writer) throws IOException {
        if (writer.t() == Writer.FieldOrder.DESCENDING) {
            v0(t, writer);
        } else if (this.h) {
            u0(t, writer);
        } else {
            t0(t, writer);
        }
    }

    public final int g0(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return p0(i, i2);
    }

    @Override // com.google.protobuf.s0
    public void h(T t, q0 q0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Objects.requireNonNull(extensionRegistryLite);
        L(this.o, this.p, t, q0Var, extensionRegistryLite);
    }

    public final int h0(int i) {
        return this.f11725a[i + 2];
    }

    @Override // com.google.protobuf.s0
    public void i(T t, byte[] bArr, int i, int i2, c.b bVar) throws IOException {
        if (this.h) {
            d0(t, bArr, i, i2, bVar);
        } else {
            c0(t, bArr, i, i2, 0, bVar);
        }
    }

    public final <E> void i0(Object obj, long j, q0 q0Var, s0<E> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        q0Var.I(this.n.e(obj, j), s0Var, extensionRegistryLite);
    }

    public final boolean j(T t, T t2, int i) {
        return B(t, i) == B(t2, i);
    }

    public final <E> void j0(Object obj, int i, q0 q0Var, s0<E> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        q0Var.J(this.n.e(obj, U(i)), s0Var, extensionRegistryLite);
    }

    public final void k0(Object obj, int i, q0 q0Var) throws IOException {
        if (A(i)) {
            a1.Z(obj, U(i), q0Var.H());
        } else if (this.g) {
            a1.Z(obj, U(i), q0Var.a());
        } else {
            a1.Z(obj, U(i), q0Var.p());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.util.Map, java.util.Map<K, V>] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    public final <K, V> int l(byte[] bArr, int i, int i2, MapEntryLite.b<K, V> bVar, Map<K, V> map, c.b bVar2) throws IOException {
        int i3;
        int I = c.I(bArr, i, bVar2);
        int i4 = bVar2.f11721a;
        if (i4 >= 0 && i4 <= i2 - I) {
            int i5 = I + i4;
            K k = bVar.b;
            V v = bVar.d;
            while (I < i5) {
                int i6 = I + 1;
                byte b = bArr[I];
                if (b < 0) {
                    i3 = c.H(b, bArr, i6, bVar2);
                    b = bVar2.f11721a;
                } else {
                    i3 = i6;
                }
                int i7 = b >>> 3;
                int i8 = b & 7;
                if (i7 != 1) {
                    if (i7 == 2 && i8 == bVar.c.getWireType()) {
                        I = m(bArr, i3, i2, bVar.c, bVar.d.getClass(), bVar2);
                        v = bVar2.c;
                    }
                    I = c.N(b, bArr, i3, i2, bVar2);
                } else if (i8 == bVar.f11700a.getWireType()) {
                    I = m(bArr, i3, i2, bVar.f11700a, null, bVar2);
                    k = bVar2.c;
                } else {
                    I = c.N(b, bArr, i3, i2, bVar2);
                }
            }
            if (I == i5) {
                map.put(k, v);
                return i5;
            }
            throw InvalidProtocolBufferException.parseFailure();
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public final void l0(Object obj, int i, q0 q0Var) throws IOException {
        if (A(i)) {
            q0Var.o(this.n.e(obj, U(i)));
        } else {
            q0Var.b(this.n.e(obj, U(i)));
        }
    }

    public final int m(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class<?> cls, c.b bVar) throws IOException {
        switch (a.f11726a[fieldType.ordinal()]) {
            case 1:
                int L = c.L(bArr, i, bVar);
                bVar.c = Boolean.valueOf(bVar.b != 0);
                return L;
            case 2:
                return c.b(bArr, i, bVar);
            case 3:
                bVar.c = Double.valueOf(c.d(bArr, i));
                return i + 8;
            case 4:
            case 5:
                bVar.c = Integer.valueOf(c.h(bArr, i));
                return i + 4;
            case 6:
            case 7:
                bVar.c = Long.valueOf(c.j(bArr, i));
                return i + 8;
            case 8:
                bVar.c = Float.valueOf(c.l(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int I = c.I(bArr, i, bVar);
                bVar.c = Integer.valueOf(bVar.f11721a);
                return I;
            case 12:
            case 13:
                int L2 = c.L(bArr, i, bVar);
                bVar.c = Long.valueOf(bVar.b);
                return L2;
            case 14:
                return c.p(n0.a().d(cls), bArr, i, i2, bVar);
            case 15:
                int I2 = c.I(bArr, i, bVar);
                bVar.c = Integer.valueOf(CodedInputStream.decodeZigZag32(bVar.f11721a));
                return I2;
            case 16:
                int L3 = c.L(bArr, i, bVar);
                bVar.c = Long.valueOf(CodedInputStream.decodeZigZag64(bVar.b));
                return L3;
            case 17:
                return c.F(bArr, i, bVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    public final void n0(T t, int i) {
        int h0 = h0(i);
        long j = 1048575 & h0;
        if (j == 1048575) {
            return;
        }
        a1.X(t, j, (1 << (h0 >>> 20)) | a1.D(t, j));
    }

    @Override // com.google.protobuf.s0
    public T newInstance() {
        return (T) this.m.a(this.e);
    }

    public final boolean o(T t, T t2, int i) {
        int s0 = s0(i);
        long U = U(s0);
        switch (r0(s0)) {
            case 0:
                return j(t, t2, i) && Double.doubleToLongBits(a1.B(t, U)) == Double.doubleToLongBits(a1.B(t2, U));
            case 1:
                return j(t, t2, i) && Float.floatToIntBits(a1.C(t, U)) == Float.floatToIntBits(a1.C(t2, U));
            case 2:
                return j(t, t2, i) && a1.F(t, U) == a1.F(t2, U);
            case 3:
                return j(t, t2, i) && a1.F(t, U) == a1.F(t2, U);
            case 4:
                return j(t, t2, i) && a1.D(t, U) == a1.D(t2, U);
            case 5:
                return j(t, t2, i) && a1.F(t, U) == a1.F(t2, U);
            case 6:
                return j(t, t2, i) && a1.D(t, U) == a1.D(t2, U);
            case 7:
                return j(t, t2, i) && a1.u(t, U) == a1.u(t2, U);
            case 8:
                return j(t, t2, i) && u0.L(a1.H(t, U), a1.H(t2, U));
            case 9:
                return j(t, t2, i) && u0.L(a1.H(t, U), a1.H(t2, U));
            case 10:
                return j(t, t2, i) && u0.L(a1.H(t, U), a1.H(t2, U));
            case 11:
                return j(t, t2, i) && a1.D(t, U) == a1.D(t2, U);
            case 12:
                return j(t, t2, i) && a1.D(t, U) == a1.D(t2, U);
            case 13:
                return j(t, t2, i) && a1.D(t, U) == a1.D(t2, U);
            case 14:
                return j(t, t2, i) && a1.F(t, U) == a1.F(t2, U);
            case 15:
                return j(t, t2, i) && a1.D(t, U) == a1.D(t2, U);
            case 16:
                return j(t, t2, i) && a1.F(t, U) == a1.F(t2, U);
            case 17:
                return j(t, t2, i) && u0.L(a1.H(t, U), a1.H(t2, U));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return u0.L(a1.H(t, U), a1.H(t2, U));
            case 50:
                return u0.L(a1.H(t, U), a1.H(t2, U));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return G(t, t2, i) && u0.L(a1.H(t, U), a1.H(t2, U));
            default:
                return true;
        }
    }

    public final void o0(T t, int i, int i2) {
        a1.X(t, h0(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    public final <UT, UB> UB p(Object obj, int i, UB ub, x0<UT, UB> x0Var) {
        Internal.EnumVerifier s2;
        int T = T(i);
        Object H = a1.H(obj, U(s0(i)));
        return (H == null || (s2 = s(i)) == null) ? ub : (UB) q(i, T, this.q.e(H), s2, ub, x0Var);
    }

    public final int p0(int i, int i2) {
        int length = (this.f11725a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int T = T(i4);
            if (i == T) {
                return i4;
            }
            if (i < T) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public final <K, V, UT, UB> UB q(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, x0<UT, UB> x0Var) {
        MapEntryLite.b<?, ?> d = this.q.d(t(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = x0Var.n();
                }
                ByteString.g newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(d, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.b(), d, next.getKey(), next.getValue());
                    x0Var.d(ub, i2, newCodedBuilder.a());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final Internal.EnumVerifier s(int i) {
        return (Internal.EnumVerifier) this.b[((i / 3) * 2) + 1];
    }

    public final int s0(int i) {
        return this.f11725a[i + 1];
    }

    public final Object t(int i) {
        return this.b[(i / 3) * 2];
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x048f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void t0(T r18, com.google.protobuf.Writer r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d0.t0(java.lang.Object, com.google.protobuf.Writer):void");
    }

    public final s0 u(int i) {
        int i2 = (i / 3) * 2;
        s0 s0Var = (s0) this.b[i2];
        if (s0Var != null) {
            return s0Var;
        }
        s0<T> d = n0.a().d((Class) this.b[i2 + 1]);
        this.b[i2] = d;
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0588  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u0(T r13, com.google.protobuf.Writer r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1584
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d0.u0(java.lang.Object, com.google.protobuf.Writer):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x058e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void v0(T r11, com.google.protobuf.Writer r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d0.v0(java.lang.Object, com.google.protobuf.Writer):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int w(T t) {
        int i;
        int i2;
        int computeDoubleSize;
        int computeBoolSize;
        int computeSFixed32Size;
        boolean z;
        int f;
        int i3;
        int computeTagSize;
        int computeUInt32SizeNoTag;
        Unsafe unsafe = s;
        int i4 = ErrorCode.ERR_UNKNOWN;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < this.f11725a.length) {
            int s0 = s0(i6);
            int T = T(i6);
            int r0 = r0(s0);
            if (r0 <= 17) {
                i = this.f11725a[i6 + 2];
                int i9 = i & i4;
                i2 = 1 << (i >>> 20);
                if (i9 != i5) {
                    i8 = unsafe.getInt(t, i9);
                    i5 = i9;
                }
            } else {
                i = (!this.i || r0 < FieldType.DOUBLE_LIST_PACKED.id() || r0 > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.f11725a[i6 + 2] & i4;
                i2 = 0;
            }
            long U = U(s0);
            switch (r0) {
                case 0:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(T, 0.0d);
                        i7 += computeDoubleSize;
                        break;
                    }
                case 1:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(T, 0.0f);
                        i7 += computeDoubleSize;
                        break;
                    }
                case 2:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(T, unsafe.getLong(t, U));
                        i7 += computeDoubleSize;
                        break;
                    }
                case 3:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(T, unsafe.getLong(t, U));
                        i7 += computeDoubleSize;
                        break;
                    }
                case 4:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(T, unsafe.getInt(t, U));
                        i7 += computeDoubleSize;
                        break;
                    }
                case 5:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(T, 0L);
                        i7 += computeDoubleSize;
                        break;
                    }
                case 6:
                    if ((i8 & i2) != 0) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(T, 0);
                        i7 += computeDoubleSize;
                        break;
                    }
                    break;
                case 7:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(T, true);
                        i7 += computeBoolSize;
                    }
                    break;
                case 8:
                    if ((i8 & i2) != 0) {
                        Object object = unsafe.getObject(t, U);
                        if (object instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) object);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(T, (String) object);
                        }
                        i7 += computeBoolSize;
                    }
                    break;
                case 9:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = u0.o(T, unsafe.getObject(t, U), u(i6));
                        i7 += computeBoolSize;
                    }
                    break;
                case 10:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) unsafe.getObject(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 11:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(T, unsafe.getInt(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 12:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(T, unsafe.getInt(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 13:
                    if ((i8 & i2) != 0) {
                        computeSFixed32Size = CodedOutputStream.computeSFixed32Size(T, 0);
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 14:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(T, 0L);
                        i7 += computeBoolSize;
                    }
                    break;
                case 15:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(T, unsafe.getInt(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 16:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(T, unsafe.getLong(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 17:
                    if ((i8 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeGroupSize(T, (MessageLite) unsafe.getObject(t, U), u(i6));
                        i7 += computeBoolSize;
                    }
                    break;
                case 18:
                    computeBoolSize = u0.h(T, (List) unsafe.getObject(t, U), false);
                    i7 += computeBoolSize;
                    break;
                case 19:
                    z = false;
                    f = u0.f(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 20:
                    z = false;
                    f = u0.m(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 21:
                    z = false;
                    f = u0.x(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 22:
                    z = false;
                    f = u0.k(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 23:
                    z = false;
                    f = u0.h(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 24:
                    z = false;
                    f = u0.f(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 25:
                    z = false;
                    f = u0.a(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 26:
                    computeBoolSize = u0.u(T, (List) unsafe.getObject(t, U));
                    i7 += computeBoolSize;
                    break;
                case 27:
                    computeBoolSize = u0.p(T, (List) unsafe.getObject(t, U), u(i6));
                    i7 += computeBoolSize;
                    break;
                case 28:
                    computeBoolSize = u0.c(T, (List) unsafe.getObject(t, U));
                    i7 += computeBoolSize;
                    break;
                case 29:
                    computeBoolSize = u0.v(T, (List) unsafe.getObject(t, U), false);
                    i7 += computeBoolSize;
                    break;
                case 30:
                    z = false;
                    f = u0.d(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 31:
                    z = false;
                    f = u0.f(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 32:
                    z = false;
                    f = u0.h(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 33:
                    z = false;
                    f = u0.q(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 34:
                    z = false;
                    f = u0.s(T, (List) unsafe.getObject(t, U), false);
                    i7 += f;
                    break;
                case 35:
                    i3 = u0.i((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 36:
                    i3 = u0.g((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 37:
                    i3 = u0.n((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 38:
                    i3 = u0.y((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 39:
                    i3 = u0.l((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 40:
                    i3 = u0.i((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 41:
                    i3 = u0.g((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 42:
                    i3 = u0.b((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 43:
                    i3 = u0.w((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 44:
                    i3 = u0.e((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 45:
                    i3 = u0.g((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 46:
                    i3 = u0.i((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 47:
                    i3 = u0.r((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 48:
                    i3 = u0.t((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 49:
                    computeBoolSize = u0.j(T, (List) unsafe.getObject(t, U), u(i6));
                    i7 += computeBoolSize;
                    break;
                case 50:
                    computeBoolSize = this.q.i(T, unsafe.getObject(t, U), t(i6));
                    i7 += computeBoolSize;
                    break;
                case 51:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeDoubleSize(T, 0.0d);
                        i7 += computeBoolSize;
                    }
                    break;
                case 52:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeFloatSize(T, 0.0f);
                        i7 += computeBoolSize;
                    }
                    break;
                case 53:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeInt64Size(T, Z(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 54:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeUInt64Size(T, Z(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 55:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeInt32Size(T, Y(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 56:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeFixed64Size(T, 0L);
                        i7 += computeBoolSize;
                    }
                    break;
                case 57:
                    if (H(t, T, i6)) {
                        computeSFixed32Size = CodedOutputStream.computeFixed32Size(T, 0);
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 58:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(T, true);
                        i7 += computeBoolSize;
                    }
                    break;
                case 59:
                    if (H(t, T, i6)) {
                        Object object2 = unsafe.getObject(t, U);
                        if (object2 instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) object2);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(T, (String) object2);
                        }
                        i7 += computeBoolSize;
                    }
                    break;
                case 60:
                    if (H(t, T, i6)) {
                        computeBoolSize = u0.o(T, unsafe.getObject(t, U), u(i6));
                        i7 += computeBoolSize;
                    }
                    break;
                case 61:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) unsafe.getObject(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 62:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(T, Y(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 63:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(T, Y(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 64:
                    if (H(t, T, i6)) {
                        computeSFixed32Size = CodedOutputStream.computeSFixed32Size(T, 0);
                        i7 += computeSFixed32Size;
                    }
                    break;
                case 65:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(T, 0L);
                        i7 += computeBoolSize;
                    }
                    break;
                case 66:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(T, Y(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 67:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(T, Z(t, U));
                        i7 += computeBoolSize;
                    }
                    break;
                case 68:
                    if (H(t, T, i6)) {
                        computeBoolSize = CodedOutputStream.computeGroupSize(T, (MessageLite) unsafe.getObject(t, U), u(i6));
                        i7 += computeBoolSize;
                    }
                    break;
            }
            i6 += 3;
            i4 = ErrorCode.ERR_UNKNOWN;
        }
        int y = i7 + y(this.o, t);
        return this.f ? y + this.p.c(t).z() : y;
    }

    public final <K, V> void w0(Writer writer, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            writer.S(i, this.q.d(t(i2)), this.q.g(obj));
        }
    }

    public final int x(T t) {
        int computeDoubleSize;
        int i;
        int computeTagSize;
        int computeUInt32SizeNoTag;
        Unsafe unsafe = s;
        int i2 = 0;
        for (int i3 = 0; i3 < this.f11725a.length; i3 += 3) {
            int s0 = s0(i3);
            int r0 = r0(s0);
            int T = T(i3);
            long U = U(s0);
            int i4 = (r0 < FieldType.DOUBLE_LIST_PACKED.id() || r0 > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.f11725a[i3 + 2] & ErrorCode.ERR_UNKNOWN;
            switch (r0) {
                case 0:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(T, 0.0d);
                        break;
                    } else {
                        continue;
                    }
                case 1:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(T, 0.0f);
                        break;
                    } else {
                        continue;
                    }
                case 2:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(T, a1.F(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 3:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(T, a1.F(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 4:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(T, a1.D(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 5:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(T, 0L);
                        break;
                    } else {
                        continue;
                    }
                case 6:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(T, 0);
                        break;
                    } else {
                        continue;
                    }
                case 7:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(T, true);
                        break;
                    } else {
                        continue;
                    }
                case 8:
                    if (B(t, i3)) {
                        Object H = a1.H(t, U);
                        if (H instanceof ByteString) {
                            computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) H);
                            break;
                        } else {
                            computeDoubleSize = CodedOutputStream.computeStringSize(T, (String) H);
                            break;
                        }
                    } else {
                        continue;
                    }
                case 9:
                    if (B(t, i3)) {
                        computeDoubleSize = u0.o(T, a1.H(t, U), u(i3));
                        break;
                    } else {
                        continue;
                    }
                case 10:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) a1.H(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 11:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(T, a1.D(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 12:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(T, a1.D(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 13:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(T, 0);
                        break;
                    } else {
                        continue;
                    }
                case 14:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(T, 0L);
                        break;
                    } else {
                        continue;
                    }
                case 15:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(T, a1.D(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 16:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(T, a1.F(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 17:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(T, (MessageLite) a1.H(t, U), u(i3));
                        break;
                    } else {
                        continue;
                    }
                case 18:
                    computeDoubleSize = u0.h(T, J(t, U), false);
                    break;
                case 19:
                    computeDoubleSize = u0.f(T, J(t, U), false);
                    break;
                case 20:
                    computeDoubleSize = u0.m(T, J(t, U), false);
                    break;
                case 21:
                    computeDoubleSize = u0.x(T, J(t, U), false);
                    break;
                case 22:
                    computeDoubleSize = u0.k(T, J(t, U), false);
                    break;
                case 23:
                    computeDoubleSize = u0.h(T, J(t, U), false);
                    break;
                case 24:
                    computeDoubleSize = u0.f(T, J(t, U), false);
                    break;
                case 25:
                    computeDoubleSize = u0.a(T, J(t, U), false);
                    break;
                case 26:
                    computeDoubleSize = u0.u(T, J(t, U));
                    break;
                case 27:
                    computeDoubleSize = u0.p(T, J(t, U), u(i3));
                    break;
                case 28:
                    computeDoubleSize = u0.c(T, J(t, U));
                    break;
                case 29:
                    computeDoubleSize = u0.v(T, J(t, U), false);
                    break;
                case 30:
                    computeDoubleSize = u0.d(T, J(t, U), false);
                    break;
                case 31:
                    computeDoubleSize = u0.f(T, J(t, U), false);
                    break;
                case 32:
                    computeDoubleSize = u0.h(T, J(t, U), false);
                    break;
                case 33:
                    computeDoubleSize = u0.q(T, J(t, U), false);
                    break;
                case 34:
                    computeDoubleSize = u0.s(T, J(t, U), false);
                    break;
                case 35:
                    i = u0.i((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 36:
                    i = u0.g((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 37:
                    i = u0.n((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 38:
                    i = u0.y((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 39:
                    i = u0.l((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 40:
                    i = u0.i((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 41:
                    i = u0.g((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 42:
                    i = u0.b((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 43:
                    i = u0.w((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 44:
                    i = u0.e((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 45:
                    i = u0.g((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 46:
                    i = u0.i((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 47:
                    i = u0.r((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 48:
                    i = u0.t((List) unsafe.getObject(t, U));
                    if (i > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i4, i);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + i;
                        break;
                    } else {
                        continue;
                    }
                case 49:
                    computeDoubleSize = u0.j(T, J(t, U), u(i3));
                    break;
                case 50:
                    computeDoubleSize = this.q.i(T, a1.H(t, U), t(i3));
                    break;
                case 51:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(T, 0.0d);
                        break;
                    } else {
                        continue;
                    }
                case 52:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(T, 0.0f);
                        break;
                    } else {
                        continue;
                    }
                case 53:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(T, Z(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 54:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(T, Z(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 55:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(T, Y(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 56:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(T, 0L);
                        break;
                    } else {
                        continue;
                    }
                case 57:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(T, 0);
                        break;
                    } else {
                        continue;
                    }
                case 58:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(T, true);
                        break;
                    } else {
                        continue;
                    }
                case 59:
                    if (H(t, T, i3)) {
                        Object H2 = a1.H(t, U);
                        if (H2 instanceof ByteString) {
                            computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) H2);
                            break;
                        } else {
                            computeDoubleSize = CodedOutputStream.computeStringSize(T, (String) H2);
                            break;
                        }
                    } else {
                        continue;
                    }
                case 60:
                    if (H(t, T, i3)) {
                        computeDoubleSize = u0.o(T, a1.H(t, U), u(i3));
                        break;
                    } else {
                        continue;
                    }
                case 61:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) a1.H(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 62:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(T, Y(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 63:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(T, Y(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 64:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(T, 0);
                        break;
                    } else {
                        continue;
                    }
                case 65:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(T, 0L);
                        break;
                    } else {
                        continue;
                    }
                case 66:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(T, Y(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 67:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(T, Z(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 68:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(T, (MessageLite) a1.H(t, U), u(i3));
                        break;
                    } else {
                        continue;
                    }
                default:
            }
            i2 += computeDoubleSize;
        }
        return i2 + y(this.o, t);
    }

    public final void x0(int i, Object obj, Writer writer) throws IOException {
        if (obj instanceof String) {
            writer.e(i, (String) obj);
        } else {
            writer.M(i, (ByteString) obj);
        }
    }

    public final <UT, UB> int y(x0<UT, UB> x0Var, T t) {
        return x0Var.h(x0Var.g(t));
    }

    public final <UT, UB> void y0(x0<UT, UB> x0Var, T t, Writer writer) throws IOException {
        x0Var.t(x0Var.g(t), writer);
    }
}
