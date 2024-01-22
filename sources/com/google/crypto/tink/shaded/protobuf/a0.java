package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import com.google.crypto.tink.shaded.protobuf.c;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes10.dex */
public final class a0<T> implements n0<T> {
    public static final int[] r = new int[0];
    public static final Unsafe s = u0.G();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f10968a;
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
    public final c0 m;
    public final r n;
    public final s0<?, ?> o;
    public final k<?> p;
    public final u q;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10969a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f10969a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10969a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10969a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10969a[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10969a[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10969a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10969a[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10969a[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10969a[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10969a[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f10969a[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f10969a[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f10969a[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f10969a[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f10969a[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f10969a[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f10969a[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public a0(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, boolean z2, int[] iArr2, int i3, int i4, c0 c0Var, r rVar, s0<?, ?> s0Var, k<?> kVar, u uVar) {
        this.f10968a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.g = messageLite instanceof GeneratedMessageLite;
        this.h = z;
        this.f = kVar != null && kVar.e(messageLite);
        this.i = z2;
        this.j = iArr2;
        this.k = i3;
        this.l = i4;
        this.m = c0Var;
        this.n = rVar;
        this.o = s0Var;
        this.p = kVar;
        this.e = messageLite;
        this.q = uVar;
    }

    public static boolean A(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean D(Object obj, int i, n0 n0Var) {
        return n0Var.e(u0.F(obj, U(i)));
    }

    public static boolean I(int i) {
        return (i & 268435456) != 0;
    }

    public static List<?> J(Object obj, long j) {
        return (List) u0.F(obj, j);
    }

    public static <T> long K(T t, long j) {
        return u0.D(t, j);
    }

    public static <T> a0<T> Q(Class<T> cls, x xVar, c0 c0Var, r rVar, s0<?, ?> s0Var, k<?> kVar, u uVar) {
        if (xVar instanceof k0) {
            return S((k0) xVar, c0Var, rVar, s0Var, kVar, uVar);
        }
        return R((StructuralMessageInfo) xVar, c0Var, rVar, s0Var, kVar, uVar);
    }

    public static <T> a0<T> R(StructuralMessageInfo structuralMessageInfo, c0 c0Var, r rVar, s0<?, ?> s0Var, k<?> kVar, u uVar) {
        int m;
        int m2;
        int i;
        boolean z = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] d = structuralMessageInfo.d();
        if (d.length == 0) {
            m = 0;
            m2 = 0;
        } else {
            m = d[0].m();
            m2 = d[d.length - 1].m();
        }
        int length = d.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i2 = 0;
        int i3 = 0;
        for (FieldInfo fieldInfo : d) {
            if (fieldInfo.s() == FieldType.MAP) {
                i2++;
            } else if (fieldInfo.s().id() >= 18 && fieldInfo.s().id() <= 49) {
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
            int m3 = fieldInfo2.m();
            q0(fieldInfo2, iArr, i5, z, objArr);
            if (i6 < c.length && c[i6] == m3) {
                c[i6] = i5;
                i6++;
            }
            if (fieldInfo2.s() == FieldType.MAP) {
                iArr2[i7] = i5;
                i7++;
            } else if (fieldInfo2.s().id() >= 18 && fieldInfo2.s().id() <= 49) {
                i = i5;
                iArr3[i8] = (int) u0.J(fieldInfo2.l());
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
        return new a0<>(iArr, objArr, m, m2, structuralMessageInfo.b(), z, true, iArr4, c.length, c.length + iArr2.length, c0Var, rVar, s0Var, kVar, uVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x039e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.google.crypto.tink.shaded.protobuf.a0<T> S(com.google.crypto.tink.shaded.protobuf.k0 r36, com.google.crypto.tink.shaded.protobuf.c0 r37, com.google.crypto.tink.shaded.protobuf.r r38, com.google.crypto.tink.shaded.protobuf.s0<?, ?> r39, com.google.crypto.tink.shaded.protobuf.k<?> r40, com.google.crypto.tink.shaded.protobuf.u r41) {
        /*
            Method dump skipped, instructions count: 1041
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.a0.S(com.google.crypto.tink.shaded.protobuf.k0, com.google.crypto.tink.shaded.protobuf.c0, com.google.crypto.tink.shaded.protobuf.r, com.google.crypto.tink.shaded.protobuf.s0, com.google.crypto.tink.shaded.protobuf.k, com.google.crypto.tink.shaded.protobuf.u):com.google.crypto.tink.shaded.protobuf.a0");
    }

    public static long U(int i) {
        return i & ErrorCode.ERR_UNKNOWN;
    }

    public static <T> boolean V(T t, long j) {
        return ((Boolean) u0.F(t, j)).booleanValue();
    }

    public static <T> double W(T t, long j) {
        return ((Double) u0.F(t, j)).doubleValue();
    }

    public static <T> float X(T t, long j) {
        return ((Float) u0.F(t, j)).floatValue();
    }

    public static <T> int Y(T t, long j) {
        return ((Integer) u0.F(t, j)).intValue();
    }

    public static <T> long Z(T t, long j) {
        return ((Long) u0.F(t, j)).longValue();
    }

    public static <T> boolean k(T t, long j) {
        return u0.s(t, j);
    }

    public static Field m0(Class<?> cls, String str) {
        Field[] declaredFields;
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            for (Field field : cls.getDeclaredFields()) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    public static <T> double n(T t, long j) {
        return u0.z(t, j);
    }

    public static void q0(FieldInfo fieldInfo, int[] iArr, int i, boolean z, Object[] objArr) {
        int J;
        int i2;
        fieldInfo.p();
        FieldType s2 = fieldInfo.s();
        int J2 = (int) u0.J(fieldInfo.l());
        int id = s2.id();
        if (!z && !s2.isList() && !s2.isMap()) {
            J = (int) u0.J(fieldInfo.q());
            i2 = Integer.numberOfTrailingZeros(fieldInfo.r());
        } else if (fieldInfo.j() == null) {
            J = 0;
            i2 = 0;
        } else {
            J = (int) u0.J(fieldInfo.j());
            i2 = 0;
        }
        iArr[i] = fieldInfo.m();
        iArr[i + 1] = J2 | (id << 20) | (fieldInfo.v() ? 268435456 : 0) | (fieldInfo.t() ? PKIFailureInfo.duplicateCertReq : 0);
        iArr[i + 2] = J | (i2 << 20);
        Class<?> o = fieldInfo.o();
        if (fieldInfo.n() == null) {
            if (o != null) {
                objArr[((i / 3) * 2) + 1] = o;
                return;
            } else if (fieldInfo.k() != null) {
                objArr[((i / 3) * 2) + 1] = fieldInfo.k();
                return;
            } else {
                return;
            }
        }
        int i3 = (i / 3) * 2;
        objArr[i3] = fieldInfo.n();
        if (o != null) {
            objArr[i3 + 1] = o;
        } else if (fieldInfo.k() != null) {
            objArr[i3 + 1] = fieldInfo.k();
        }
    }

    public static <T> float r(T t, long j) {
        return u0.A(t, j);
    }

    public static int r0(int i) {
        return (i & 267386880) >>> 20;
    }

    public static UnknownFieldSetLite v(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite l = UnknownFieldSetLite.l();
            generatedMessageLite.unknownFields = l;
            return l;
        }
        return unknownFieldSetLite;
    }

    public static <T> int z(T t, long j) {
        return u0.B(t, j);
    }

    public final boolean B(T t, int i) {
        if (this.h) {
            int s0 = s0(i);
            long U = U(s0);
            switch (r0(s0)) {
                case 0:
                    return u0.z(t, U) != 0.0d;
                case 1:
                    return u0.A(t, U) != 0.0f;
                case 2:
                    return u0.D(t, U) != 0;
                case 3:
                    return u0.D(t, U) != 0;
                case 4:
                    return u0.B(t, U) != 0;
                case 5:
                    return u0.D(t, U) != 0;
                case 6:
                    return u0.B(t, U) != 0;
                case 7:
                    return u0.s(t, U);
                case 8:
                    Object F = u0.F(t, U);
                    if (F instanceof String) {
                        return !((String) F).isEmpty();
                    }
                    if (F instanceof ByteString) {
                        return !ByteString.EMPTY.equals(F);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return u0.F(t, U) != null;
                case 10:
                    return !ByteString.EMPTY.equals(u0.F(t, U));
                case 11:
                    return u0.B(t, U) != 0;
                case 12:
                    return u0.B(t, U) != 0;
                case 13:
                    return u0.B(t, U) != 0;
                case 14:
                    return u0.D(t, U) != 0;
                case 15:
                    return u0.B(t, U) != 0;
                case 16:
                    return u0.D(t, U) != 0;
                case 17:
                    return u0.F(t, U) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int h0 = h0(i);
        return (u0.B(t, (long) (h0 & ErrorCode.ERR_UNKNOWN)) & (1 << (h0 >>> 20))) != 0;
    }

    public final boolean C(T t, int i, int i2, int i3) {
        if (this.h) {
            return B(t, i);
        }
        return (i2 & i3) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N> boolean E(Object obj, int i, int i2) {
        List list = (List) u0.F(obj, U(i));
        if (list.isEmpty()) {
            return true;
        }
        n0 u = u(i2);
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
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.crypto.tink.shaded.protobuf.n0] */
    public final boolean F(T t, int i, int i2) {
        Map<?, ?> g = this.q.g(u0.F(t, U(i)));
        if (g.isEmpty()) {
            return true;
        }
        if (this.q.d(t(i2)).c.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        n0<T> n0Var = 0;
        for (Object obj : g.values()) {
            if (n0Var == null) {
                n0Var = i0.a().d(obj.getClass());
            }
            boolean e = n0Var.e(obj);
            n0Var = n0Var;
            if (!e) {
                return false;
            }
        }
        return true;
    }

    public final boolean G(T t, T t2, int i) {
        long h0 = h0(i) & ErrorCode.ERR_UNKNOWN;
        return u0.B(t, h0) == u0.B(t2, h0);
    }

    public final boolean H(T t, int i, int i2) {
        return u0.B(t, (long) (h0(i2) & ErrorCode.ERR_UNKNOWN)) == i;
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
    public final <UT, UB, ET extends com.google.crypto.tink.shaded.protobuf.FieldSet.FieldDescriptorLite<ET>> void L(com.google.crypto.tink.shaded.protobuf.s0<UT, UB> r17, com.google.crypto.tink.shaded.protobuf.k<ET> r18, T r19, com.google.crypto.tink.shaded.protobuf.l0 r20, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1720
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.a0.L(com.google.crypto.tink.shaded.protobuf.s0, com.google.crypto.tink.shaded.protobuf.k, java.lang.Object, com.google.crypto.tink.shaded.protobuf.l0, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite):void");
    }

    public final <K, V> void M(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, l0 l0Var) throws IOException {
        long U = U(s0(i));
        Object F = u0.F(obj, U);
        if (F == null) {
            F = this.q.f(obj2);
            u0.V(obj, U, F);
        } else if (this.q.j(F)) {
            Object f = this.q.f(obj2);
            this.q.c(f, F);
            u0.V(obj, U, f);
            F = f;
        }
        l0Var.K(this.q.e(F), this.q.d(obj2), extensionRegistryLite);
    }

    public final void N(T t, T t2, int i) {
        long U = U(s0(i));
        if (B(t2, i)) {
            Object F = u0.F(t, U);
            Object F2 = u0.F(t2, U);
            if (F != null && F2 != null) {
                u0.V(t, U, Internal.d(F, F2));
                n0(t, i);
            } else if (F2 != null) {
                u0.V(t, U, F2);
                n0(t, i);
            }
        }
    }

    public final void O(T t, T t2, int i) {
        int s0 = s0(i);
        int T = T(i);
        long U = U(s0);
        if (H(t2, T, i)) {
            Object F = u0.F(t, U);
            Object F2 = u0.F(t2, U);
            if (F != null && F2 != null) {
                u0.V(t, U, Internal.d(F, F2));
                o0(t, T, i);
            } else if (F2 != null) {
                u0.V(t, U, F2);
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
                    u0.R(t, U, u0.z(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 1:
                if (B(t2, i)) {
                    u0.S(t, U, u0.A(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 2:
                if (B(t2, i)) {
                    u0.U(t, U, u0.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 3:
                if (B(t2, i)) {
                    u0.U(t, U, u0.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 4:
                if (B(t2, i)) {
                    u0.T(t, U, u0.B(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 5:
                if (B(t2, i)) {
                    u0.U(t, U, u0.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 6:
                if (B(t2, i)) {
                    u0.T(t, U, u0.B(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 7:
                if (B(t2, i)) {
                    u0.K(t, U, u0.s(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 8:
                if (B(t2, i)) {
                    u0.V(t, U, u0.F(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 9:
                N(t, t2, i);
                return;
            case 10:
                if (B(t2, i)) {
                    u0.V(t, U, u0.F(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 11:
                if (B(t2, i)) {
                    u0.T(t, U, u0.B(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 12:
                if (B(t2, i)) {
                    u0.T(t, U, u0.B(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 13:
                if (B(t2, i)) {
                    u0.T(t, U, u0.B(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 14:
                if (B(t2, i)) {
                    u0.U(t, U, u0.D(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 15:
                if (B(t2, i)) {
                    u0.T(t, U, u0.B(t2, U));
                    n0(t, i);
                    return;
                }
                return;
            case 16:
                if (B(t2, i)) {
                    u0.U(t, U, u0.D(t2, U));
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
                p0.F(this.q, t, t2, U);
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
                    u0.V(t, U, u0.F(t2, U));
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
                    u0.V(t, U, u0.F(t2, U));
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
        return this.f10968a[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public int a(T t) {
        int i;
        int hashLong;
        int length = this.f10968a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int s0 = s0(i3);
            int T = T(i3);
            long U = U(s0);
            int i4 = 37;
            switch (r0(s0)) {
                case 0:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(Double.doubleToLongBits(u0.z(t, U)));
                    i2 = i + hashLong;
                    break;
                case 1:
                    i = i2 * 53;
                    hashLong = Float.floatToIntBits(u0.A(t, U));
                    i2 = i + hashLong;
                    break;
                case 2:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(u0.D(t, U));
                    i2 = i + hashLong;
                    break;
                case 3:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(u0.D(t, U));
                    i2 = i + hashLong;
                    break;
                case 4:
                    i = i2 * 53;
                    hashLong = u0.B(t, U);
                    i2 = i + hashLong;
                    break;
                case 5:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(u0.D(t, U));
                    i2 = i + hashLong;
                    break;
                case 6:
                    i = i2 * 53;
                    hashLong = u0.B(t, U);
                    i2 = i + hashLong;
                    break;
                case 7:
                    i = i2 * 53;
                    hashLong = Internal.hashBoolean(u0.s(t, U));
                    i2 = i + hashLong;
                    break;
                case 8:
                    i = i2 * 53;
                    hashLong = ((String) u0.F(t, U)).hashCode();
                    i2 = i + hashLong;
                    break;
                case 9:
                    Object F = u0.F(t, U);
                    if (F != null) {
                        i4 = F.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 10:
                    i = i2 * 53;
                    hashLong = u0.F(t, U).hashCode();
                    i2 = i + hashLong;
                    break;
                case 11:
                    i = i2 * 53;
                    hashLong = u0.B(t, U);
                    i2 = i + hashLong;
                    break;
                case 12:
                    i = i2 * 53;
                    hashLong = u0.B(t, U);
                    i2 = i + hashLong;
                    break;
                case 13:
                    i = i2 * 53;
                    hashLong = u0.B(t, U);
                    i2 = i + hashLong;
                    break;
                case 14:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(u0.D(t, U));
                    i2 = i + hashLong;
                    break;
                case 15:
                    i = i2 * 53;
                    hashLong = u0.B(t, U);
                    i2 = i + hashLong;
                    break;
                case 16:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(u0.D(t, U));
                    i2 = i + hashLong;
                    break;
                case 17:
                    Object F2 = u0.F(t, U);
                    if (F2 != null) {
                        i4 = F2.hashCode();
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
                    hashLong = u0.F(t, U).hashCode();
                    i2 = i + hashLong;
                    break;
                case 50:
                    i = i2 * 53;
                    hashLong = u0.F(t, U).hashCode();
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
                        hashLong = ((String) u0.F(t, U)).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = u0.F(t, U).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (H(t, T, i3)) {
                        i = i2 * 53;
                        hashLong = u0.F(t, U).hashCode();
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
                        hashLong = u0.F(t, U).hashCode();
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

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public boolean b(T t, T t2) {
        int length = this.f10968a.length;
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
        long j2 = this.f10968a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
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
                    unsafe.putObject(t, j, Integer.valueOf(bVar.f10973a));
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
                    int i13 = bVar.f10973a;
                    if (i13 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !v0.u(bArr, I2, I2 + i13)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    } else {
                        unsafe.putObject(t, j, new String(bArr, I2, i13, Internal.f10957a));
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
                        unsafe.putObject(t, j, Internal.d(object, bVar.c));
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
                    int i14 = bVar.f10973a;
                    Internal.EnumVerifier s2 = s(i8);
                    if (s2 != null && !s2.isInRange(i14)) {
                        v(t).n(i3, Long.valueOf(i14));
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
                    unsafe.putObject(t, j, Integer.valueOf(CodedInputStream.decodeZigZag32(bVar.f10973a)));
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
                        unsafe.putObject(t, j, Internal.d(object2, bVar.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return n;
                }
                break;
        }
        return i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void c(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.f10968a.length; i += 3) {
            P(t, t2, i);
        }
        p0.G(this.o, t, t2);
        if (this.f) {
            p0.E(this.p, t, t2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x0359, code lost:
        if (r0 != r11) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x035b, code lost:
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r9 = r36;
        r1 = r17;
        r3 = r18;
        r7 = r19;
        r2 = r20;
        r6 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x03a2, code lost:
        if (r0 != r15) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x03c5, code lost:
        if (r0 != r15) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x03c8, code lost:
        r2 = r0;
        r8 = r18;
        r0 = r35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int c0(T r31, byte[] r32, int r33, int r34, int r35, com.google.crypto.tink.shaded.protobuf.c.b r36) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1172
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.a0.c0(java.lang.Object, byte[], int, int, int, com.google.crypto.tink.shaded.protobuf.c$b):int");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void d(T t) {
        int i;
        int i2 = this.k;
        while (true) {
            i = this.l;
            if (i2 >= i) {
                break;
            }
            long U = U(s0(this.j[i2]));
            Object F = u0.F(t, U);
            if (F != null) {
                u0.V(t, U, this.q.h(F));
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

    /* JADX WARN: Code restructure failed: missing block: B:102:0x022b, code lost:
        if (r0 != r15) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x022e, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01de, code lost:
        if (r0 != r15) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x020c, code lost:
        if (r0 != r15) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int d0(T r28, byte[] r29, int r30, int r31, com.google.crypto.tink.shaded.protobuf.c.b r32) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.a0.d0(java.lang.Object, byte[], int, int, com.google.crypto.tink.shaded.protobuf.c$b):int");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public final boolean e(T t) {
        int i;
        int i2 = -1;
        int i3 = 0;
        for (int i4 = 0; i4 < this.k; i4++) {
            int i5 = this.j[i4];
            int T = T(i5);
            int s0 = s0(i5);
            if (this.h) {
                i = 0;
            } else {
                int i6 = this.f10968a[i5 + 2];
                int i7 = 1048575 & i6;
                i = 1 << (i6 >>> 20);
                if (i7 != i2) {
                    i3 = s.getInt(t, i7);
                    i2 = i7;
                }
            }
            if (I(s0) && !C(t, i5, i3, i)) {
                return false;
            }
            int r0 = r0(s0);
            if (r0 != 9 && r0 != 17) {
                if (r0 != 27) {
                    if (r0 == 60 || r0 == 68) {
                        if (H(t, T, i5) && !D(t, s0, u(i5))) {
                            return false;
                        }
                    } else if (r0 != 49) {
                        if (r0 == 50 && !F(t, s0, i5)) {
                            return false;
                        }
                    }
                }
                if (!E(t, s0, i5)) {
                    return false;
                }
            } else if (C(t, i5, i3, i) && !D(t, s0, u(i5))) {
                return false;
            }
        }
        return !this.f || this.p.c(t).t();
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
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) p0.A(i4, protobufList, s(i6), unknownFieldSetLite, this.o);
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

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public int f(T t) {
        return this.h ? x(t) : w(t);
    }

    public final int f0(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return p0(i, 0);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void g(T t, byte[] bArr, int i, int i2, c.b bVar) throws IOException {
        if (this.h) {
            d0(t, bArr, i, i2, bVar);
        } else {
            c0(t, bArr, i, i2, 0, bVar);
        }
    }

    public final int g0(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return p0(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void h(T t, l0 l0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Objects.requireNonNull(extensionRegistryLite);
        L(this.o, this.p, t, l0Var, extensionRegistryLite);
    }

    public final int h0(int i) {
        return this.f10968a[i + 2];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void i(T t, Writer writer) throws IOException {
        if (writer.t() == Writer.FieldOrder.DESCENDING) {
            v0(t, writer);
        } else if (this.h) {
            u0(t, writer);
        } else {
            t0(t, writer);
        }
    }

    public final <E> void i0(Object obj, long j, l0 l0Var, n0<E> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        l0Var.O(this.n.e(obj, j), n0Var, extensionRegistryLite);
    }

    public final boolean j(T t, T t2, int i) {
        return B(t, i) == B(t2, i);
    }

    public final <E> void j0(Object obj, int i, l0 l0Var, n0<E> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        l0Var.L(this.n.e(obj, U(i)), n0Var, extensionRegistryLite);
    }

    public final void k0(Object obj, int i, l0 l0Var) throws IOException {
        if (A(i)) {
            u0.V(obj, U(i), l0Var.H());
        } else if (this.g) {
            u0.V(obj, U(i), l0Var.a());
        } else {
            u0.V(obj, U(i), l0Var.p());
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
        int i4 = bVar2.f10973a;
        if (i4 >= 0 && i4 <= i2 - I) {
            int i5 = I + i4;
            K k = bVar.b;
            V v = bVar.d;
            while (I < i5) {
                int i6 = I + 1;
                byte b = bArr[I];
                if (b < 0) {
                    i3 = c.H(b, bArr, i6, bVar2);
                    b = bVar2.f10973a;
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
                } else if (i8 == bVar.f10962a.getWireType()) {
                    I = m(bArr, i3, i2, bVar.f10962a, null, bVar2);
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

    public final void l0(Object obj, int i, l0 l0Var) throws IOException {
        if (A(i)) {
            l0Var.o(this.n.e(obj, U(i)));
        } else {
            l0Var.b(this.n.e(obj, U(i)));
        }
    }

    public final int m(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class<?> cls, c.b bVar) throws IOException {
        switch (a.f10969a[fieldType.ordinal()]) {
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
                bVar.c = Integer.valueOf(bVar.f10973a);
                return I;
            case 12:
            case 13:
                int L2 = c.L(bArr, i, bVar);
                bVar.c = Long.valueOf(bVar.b);
                return L2;
            case 14:
                return c.p(i0.a().d(cls), bArr, i, i2, bVar);
            case 15:
                int I2 = c.I(bArr, i, bVar);
                bVar.c = Integer.valueOf(CodedInputStream.decodeZigZag32(bVar.f10973a));
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
        if (this.h) {
            return;
        }
        int h0 = h0(i);
        long j = h0 & ErrorCode.ERR_UNKNOWN;
        u0.T(t, j, u0.B(t, j) | (1 << (h0 >>> 20)));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public T newInstance() {
        return (T) this.m.a(this.e);
    }

    public final boolean o(T t, T t2, int i) {
        int s0 = s0(i);
        long U = U(s0);
        switch (r0(s0)) {
            case 0:
                return j(t, t2, i) && Double.doubleToLongBits(u0.z(t, U)) == Double.doubleToLongBits(u0.z(t2, U));
            case 1:
                return j(t, t2, i) && Float.floatToIntBits(u0.A(t, U)) == Float.floatToIntBits(u0.A(t2, U));
            case 2:
                return j(t, t2, i) && u0.D(t, U) == u0.D(t2, U);
            case 3:
                return j(t, t2, i) && u0.D(t, U) == u0.D(t2, U);
            case 4:
                return j(t, t2, i) && u0.B(t, U) == u0.B(t2, U);
            case 5:
                return j(t, t2, i) && u0.D(t, U) == u0.D(t2, U);
            case 6:
                return j(t, t2, i) && u0.B(t, U) == u0.B(t2, U);
            case 7:
                return j(t, t2, i) && u0.s(t, U) == u0.s(t2, U);
            case 8:
                return j(t, t2, i) && p0.K(u0.F(t, U), u0.F(t2, U));
            case 9:
                return j(t, t2, i) && p0.K(u0.F(t, U), u0.F(t2, U));
            case 10:
                return j(t, t2, i) && p0.K(u0.F(t, U), u0.F(t2, U));
            case 11:
                return j(t, t2, i) && u0.B(t, U) == u0.B(t2, U);
            case 12:
                return j(t, t2, i) && u0.B(t, U) == u0.B(t2, U);
            case 13:
                return j(t, t2, i) && u0.B(t, U) == u0.B(t2, U);
            case 14:
                return j(t, t2, i) && u0.D(t, U) == u0.D(t2, U);
            case 15:
                return j(t, t2, i) && u0.B(t, U) == u0.B(t2, U);
            case 16:
                return j(t, t2, i) && u0.D(t, U) == u0.D(t2, U);
            case 17:
                return j(t, t2, i) && p0.K(u0.F(t, U), u0.F(t2, U));
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
                return p0.K(u0.F(t, U), u0.F(t2, U));
            case 50:
                return p0.K(u0.F(t, U), u0.F(t2, U));
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
                return G(t, t2, i) && p0.K(u0.F(t, U), u0.F(t2, U));
            default:
                return true;
        }
    }

    public final void o0(T t, int i, int i2) {
        u0.T(t, h0(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    public final <UT, UB> UB p(Object obj, int i, UB ub, s0<UT, UB> s0Var) {
        Internal.EnumVerifier s2;
        int T = T(i);
        Object F = u0.F(obj, U(s0(i)));
        return (F == null || (s2 = s(i)) == null) ? ub : (UB) q(i, T, this.q.e(F), s2, ub, s0Var);
    }

    public final int p0(int i, int i2) {
        int length = (this.f10968a.length / 3) - 1;
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

    public final <K, V, UT, UB> UB q(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, s0<UT, UB> s0Var) {
        MapEntryLite.b<?, ?> d = this.q.d(t(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = s0Var.n();
                }
                ByteString.g newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.a(d, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.e(newCodedBuilder.b(), d, next.getKey(), next.getValue());
                    s0Var.d(ub, i2, newCodedBuilder.a());
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
        return this.f10968a[i + 1];
    }

    public final Object t(int i) {
        return this.b[(i / 3) * 2];
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x049e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void t0(T r18, com.google.crypto.tink.shaded.protobuf.Writer r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.a0.t0(java.lang.Object, com.google.crypto.tink.shaded.protobuf.Writer):void");
    }

    public final n0 u(int i) {
        int i2 = (i / 3) * 2;
        n0 n0Var = (n0) this.b[i2];
        if (n0Var != null) {
            return n0Var;
        }
        n0<T> d = i0.a().d((Class) this.b[i2 + 1]);
        this.b[i2] = d;
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0588  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u0(T r13, com.google.crypto.tink.shaded.protobuf.Writer r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1584
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.a0.u0(java.lang.Object, com.google.crypto.tink.shaded.protobuf.Writer):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x058e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void v0(T r11, com.google.crypto.tink.shaded.protobuf.Writer r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.a0.v0(java.lang.Object, com.google.crypto.tink.shaded.protobuf.Writer):void");
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
        int i4 = -1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < this.f10968a.length) {
            int s0 = s0(i5);
            int T = T(i5);
            int r0 = r0(s0);
            if (r0 <= 17) {
                i = this.f10968a[i5 + 2];
                int i8 = 1048575 & i;
                int i9 = 1 << (i >>> 20);
                if (i8 != i4) {
                    i7 = unsafe.getInt(t, i8);
                    i4 = i8;
                }
                i2 = i9;
            } else {
                i = (!this.i || r0 < FieldType.DOUBLE_LIST_PACKED.id() || r0 > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.f10968a[i5 + 2] & ErrorCode.ERR_UNKNOWN;
                i2 = 0;
            }
            long U = U(s0);
            int i10 = i4;
            switch (r0) {
                case 0:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(T, 0.0d);
                        i6 += computeDoubleSize;
                        break;
                    }
                case 1:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(T, 0.0f);
                        i6 += computeDoubleSize;
                        break;
                    }
                case 2:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(T, unsafe.getLong(t, U));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 3:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(T, unsafe.getLong(t, U));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 4:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(T, unsafe.getInt(t, U));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 5:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(T, 0L);
                        i6 += computeDoubleSize;
                        break;
                    }
                case 6:
                    if ((i7 & i2) != 0) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(T, 0);
                        i6 += computeDoubleSize;
                        break;
                    }
                    break;
                case 7:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(T, true);
                        i6 += computeBoolSize;
                    }
                    break;
                case 8:
                    if ((i7 & i2) != 0) {
                        Object object = unsafe.getObject(t, U);
                        if (object instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) object);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(T, (String) object);
                        }
                        i6 += computeBoolSize;
                    }
                    break;
                case 9:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = p0.o(T, unsafe.getObject(t, U), u(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 10:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) unsafe.getObject(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 11:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(T, unsafe.getInt(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 12:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(T, unsafe.getInt(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 13:
                    if ((i7 & i2) != 0) {
                        computeSFixed32Size = CodedOutputStream.computeSFixed32Size(T, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 14:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(T, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 15:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(T, unsafe.getInt(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 16:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(T, unsafe.getLong(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 17:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.b(T, (MessageLite) unsafe.getObject(t, U), u(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 18:
                    computeBoolSize = p0.h(T, (List) unsafe.getObject(t, U), false);
                    i6 += computeBoolSize;
                    break;
                case 19:
                    z = false;
                    f = p0.f(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 20:
                    z = false;
                    f = p0.m(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 21:
                    z = false;
                    f = p0.x(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 22:
                    z = false;
                    f = p0.k(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 23:
                    z = false;
                    f = p0.h(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 24:
                    z = false;
                    f = p0.f(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 25:
                    z = false;
                    f = p0.a(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 26:
                    computeBoolSize = p0.u(T, (List) unsafe.getObject(t, U));
                    i6 += computeBoolSize;
                    break;
                case 27:
                    computeBoolSize = p0.p(T, (List) unsafe.getObject(t, U), u(i5));
                    i6 += computeBoolSize;
                    break;
                case 28:
                    computeBoolSize = p0.c(T, (List) unsafe.getObject(t, U));
                    i6 += computeBoolSize;
                    break;
                case 29:
                    computeBoolSize = p0.v(T, (List) unsafe.getObject(t, U), false);
                    i6 += computeBoolSize;
                    break;
                case 30:
                    z = false;
                    f = p0.d(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 31:
                    z = false;
                    f = p0.f(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 32:
                    z = false;
                    f = p0.h(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 33:
                    z = false;
                    f = p0.q(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 34:
                    z = false;
                    f = p0.s(T, (List) unsafe.getObject(t, U), false);
                    i6 += f;
                    break;
                case 35:
                    i3 = p0.i((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 36:
                    i3 = p0.g((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 37:
                    i3 = p0.n((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 38:
                    i3 = p0.y((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 39:
                    i3 = p0.l((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 40:
                    i3 = p0.i((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 41:
                    i3 = p0.g((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 42:
                    i3 = p0.b((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 43:
                    i3 = p0.w((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 44:
                    i3 = p0.e((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 45:
                    i3 = p0.g((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 46:
                    i3 = p0.i((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 47:
                    i3 = p0.r((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 48:
                    i3 = p0.t((List) unsafe.getObject(t, U));
                    if (i3 > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, i3);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(T);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i3);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + i3;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 49:
                    computeBoolSize = p0.j(T, (List) unsafe.getObject(t, U), u(i5));
                    i6 += computeBoolSize;
                    break;
                case 50:
                    computeBoolSize = this.q.i(T, unsafe.getObject(t, U), t(i5));
                    i6 += computeBoolSize;
                    break;
                case 51:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeDoubleSize(T, 0.0d);
                        i6 += computeBoolSize;
                    }
                    break;
                case 52:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeFloatSize(T, 0.0f);
                        i6 += computeBoolSize;
                    }
                    break;
                case 53:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeInt64Size(T, Z(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 54:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeUInt64Size(T, Z(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 55:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeInt32Size(T, Y(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 56:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeFixed64Size(T, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 57:
                    if (H(t, T, i5)) {
                        computeSFixed32Size = CodedOutputStream.computeFixed32Size(T, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 58:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(T, true);
                        i6 += computeBoolSize;
                    }
                    break;
                case 59:
                    if (H(t, T, i5)) {
                        Object object2 = unsafe.getObject(t, U);
                        if (object2 instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) object2);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(T, (String) object2);
                        }
                        i6 += computeBoolSize;
                    }
                    break;
                case 60:
                    if (H(t, T, i5)) {
                        computeBoolSize = p0.o(T, unsafe.getObject(t, U), u(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 61:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(T, (ByteString) unsafe.getObject(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 62:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(T, Y(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 63:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(T, Y(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 64:
                    if (H(t, T, i5)) {
                        computeSFixed32Size = CodedOutputStream.computeSFixed32Size(T, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 65:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(T, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 66:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(T, Y(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 67:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(T, Z(t, U));
                        i6 += computeBoolSize;
                    }
                    break;
                case 68:
                    if (H(t, T, i5)) {
                        computeBoolSize = CodedOutputStream.b(T, (MessageLite) unsafe.getObject(t, U), u(i5));
                        i6 += computeBoolSize;
                    }
                    break;
            }
            i5 += 3;
            i4 = i10;
        }
        int y = i6 + y(this.o, t);
        return this.f ? y + this.p.c(t).o() : y;
    }

    public final <K, V> void w0(Writer writer, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            writer.J(i, this.q.d(t(i2)), this.q.g(obj));
        }
    }

    public final int x(T t) {
        int computeDoubleSize;
        int i;
        int computeTagSize;
        int computeUInt32SizeNoTag;
        Unsafe unsafe = s;
        int i2 = 0;
        for (int i3 = 0; i3 < this.f10968a.length; i3 += 3) {
            int s0 = s0(i3);
            int r0 = r0(s0);
            int T = T(i3);
            long U = U(s0);
            int i4 = (r0 < FieldType.DOUBLE_LIST_PACKED.id() || r0 > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.f10968a[i3 + 2] & ErrorCode.ERR_UNKNOWN;
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
                        computeDoubleSize = CodedOutputStream.computeInt64Size(T, u0.D(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 3:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(T, u0.D(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 4:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(T, u0.B(t, U));
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
                        Object F = u0.F(t, U);
                        if (F instanceof ByteString) {
                            computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) F);
                            break;
                        } else {
                            computeDoubleSize = CodedOutputStream.computeStringSize(T, (String) F);
                            break;
                        }
                    } else {
                        continue;
                    }
                case 9:
                    if (B(t, i3)) {
                        computeDoubleSize = p0.o(T, u0.F(t, U), u(i3));
                        break;
                    } else {
                        continue;
                    }
                case 10:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) u0.F(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 11:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(T, u0.B(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 12:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(T, u0.B(t, U));
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
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(T, u0.B(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 16:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(T, u0.D(t, U));
                        break;
                    } else {
                        continue;
                    }
                case 17:
                    if (B(t, i3)) {
                        computeDoubleSize = CodedOutputStream.b(T, (MessageLite) u0.F(t, U), u(i3));
                        break;
                    } else {
                        continue;
                    }
                case 18:
                    computeDoubleSize = p0.h(T, J(t, U), false);
                    break;
                case 19:
                    computeDoubleSize = p0.f(T, J(t, U), false);
                    break;
                case 20:
                    computeDoubleSize = p0.m(T, J(t, U), false);
                    break;
                case 21:
                    computeDoubleSize = p0.x(T, J(t, U), false);
                    break;
                case 22:
                    computeDoubleSize = p0.k(T, J(t, U), false);
                    break;
                case 23:
                    computeDoubleSize = p0.h(T, J(t, U), false);
                    break;
                case 24:
                    computeDoubleSize = p0.f(T, J(t, U), false);
                    break;
                case 25:
                    computeDoubleSize = p0.a(T, J(t, U), false);
                    break;
                case 26:
                    computeDoubleSize = p0.u(T, J(t, U));
                    break;
                case 27:
                    computeDoubleSize = p0.p(T, J(t, U), u(i3));
                    break;
                case 28:
                    computeDoubleSize = p0.c(T, J(t, U));
                    break;
                case 29:
                    computeDoubleSize = p0.v(T, J(t, U), false);
                    break;
                case 30:
                    computeDoubleSize = p0.d(T, J(t, U), false);
                    break;
                case 31:
                    computeDoubleSize = p0.f(T, J(t, U), false);
                    break;
                case 32:
                    computeDoubleSize = p0.h(T, J(t, U), false);
                    break;
                case 33:
                    computeDoubleSize = p0.q(T, J(t, U), false);
                    break;
                case 34:
                    computeDoubleSize = p0.s(T, J(t, U), false);
                    break;
                case 35:
                    i = p0.i((List) unsafe.getObject(t, U));
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
                    i = p0.g((List) unsafe.getObject(t, U));
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
                    i = p0.n((List) unsafe.getObject(t, U));
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
                    i = p0.y((List) unsafe.getObject(t, U));
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
                    i = p0.l((List) unsafe.getObject(t, U));
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
                    i = p0.i((List) unsafe.getObject(t, U));
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
                    i = p0.g((List) unsafe.getObject(t, U));
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
                    i = p0.b((List) unsafe.getObject(t, U));
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
                    i = p0.w((List) unsafe.getObject(t, U));
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
                    i = p0.e((List) unsafe.getObject(t, U));
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
                    i = p0.g((List) unsafe.getObject(t, U));
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
                    i = p0.i((List) unsafe.getObject(t, U));
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
                    i = p0.r((List) unsafe.getObject(t, U));
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
                    i = p0.t((List) unsafe.getObject(t, U));
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
                    computeDoubleSize = p0.j(T, J(t, U), u(i3));
                    break;
                case 50:
                    computeDoubleSize = this.q.i(T, u0.F(t, U), t(i3));
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
                        Object F2 = u0.F(t, U);
                        if (F2 instanceof ByteString) {
                            computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) F2);
                            break;
                        } else {
                            computeDoubleSize = CodedOutputStream.computeStringSize(T, (String) F2);
                            break;
                        }
                    } else {
                        continue;
                    }
                case 60:
                    if (H(t, T, i3)) {
                        computeDoubleSize = p0.o(T, u0.F(t, U), u(i3));
                        break;
                    } else {
                        continue;
                    }
                case 61:
                    if (H(t, T, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(T, (ByteString) u0.F(t, U));
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
                        computeDoubleSize = CodedOutputStream.b(T, (MessageLite) u0.F(t, U), u(i3));
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

    public final <UT, UB> int y(s0<UT, UB> s0Var, T t) {
        return s0Var.h(s0Var.g(t));
    }

    public final <UT, UB> void y0(s0<UT, UB> s0Var, T t, Writer writer) throws IOException {
        s0Var.t(s0Var.g(t), writer);
    }
}
