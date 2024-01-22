package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class d implements l0 {

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10974a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f10974a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10974a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10974a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10974a[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10974a[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10974a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10974a[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10974a[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10974a[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10974a[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f10974a[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f10974a[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f10974a[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f10974a[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f10974a[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f10974a[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f10974a[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class b extends d {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f10975a;
        public final byte[] b;
        public int c;
        public int d;
        public int e;
        public int f;

        public b(ByteBuffer byteBuffer, boolean z) {
            super(null);
            this.f10975a = z;
            this.b = byteBuffer.array();
            this.c = byteBuffer.arrayOffset() + byteBuffer.position();
            this.d = byteBuffer.arrayOffset() + byteBuffer.limit();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int A() throws IOException {
            if (Q()) {
                return Integer.MAX_VALUE;
            }
            int b0 = b0();
            this.e = b0;
            if (b0 == this.f) {
                return Integer.MAX_VALUE;
            }
            return WireFormat.getTagFieldNumber(b0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void B(List<Float> list) throws IOException {
            int i;
            int i2;
            if (list instanceof n) {
                n nVar = (n) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 2) {
                    int b0 = b0();
                    l0(b0);
                    int i3 = this.c + b0;
                    while (this.c < i3) {
                        nVar.addFloat(Float.intBitsToFloat(V()));
                    }
                    return;
                } else if (tagWireType == 5) {
                    do {
                        nVar.addFloat(readFloat());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 2) {
                int b02 = b0();
                l0(b02);
                int i4 = this.c + b02;
                while (this.c < i4) {
                    list.add(Float.valueOf(Float.intBitsToFloat(V())));
                }
            } else if (tagWireType2 == 5) {
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public boolean C() throws IOException {
            int i;
            if (Q() || (i = this.e) == this.f) {
                return false;
            }
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                j0();
                return true;
            } else if (tagWireType == 1) {
                h0(8);
                return true;
            } else if (tagWireType == 2) {
                h0(b0());
                return true;
            } else if (tagWireType == 3) {
                i0();
                return true;
            } else if (tagWireType == 5) {
                h0(4);
                return true;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int D() throws IOException {
            g0(5);
            return U();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void E(List<ByteString> list) throws IOException {
            int i;
            if (WireFormat.getTagWireType(this.e) == 2) {
                do {
                    list.add(p());
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void F(List<Double> list) throws IOException {
            int i;
            int i2;
            if (list instanceof i) {
                i iVar = (i) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 1) {
                    do {
                        iVar.addDouble(readDouble());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else if (tagWireType == 2) {
                    int b0 = b0();
                    m0(b0);
                    int i3 = this.c + b0;
                    while (this.c < i3) {
                        iVar.addDouble(Double.longBitsToDouble(X()));
                    }
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 1) {
                do {
                    list.add(Double.valueOf(readDouble()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else if (tagWireType2 == 2) {
                int b02 = b0();
                m0(b02);
                int i4 = this.c + b02;
                while (this.c < i4) {
                    list.add(Double.valueOf(Double.longBitsToDouble(X())));
                }
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public long G() throws IOException {
            g0(0);
            return c0();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public String H() throws IOException {
            return Z(true);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public <T> T I(n0<T> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            g0(3);
            return (T) T(n0Var, extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public <T> T J(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            g0(3);
            return (T) T(i0.a().d(cls), extensionRegistryLite);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public <K, V> void K(Map<K, V> map, MapEntryLite.b<K, V> bVar, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            g0(2);
            int b0 = b0();
            e0(b0);
            int i = this.d;
            this.d = this.c + b0;
            try {
                Object obj = bVar.b;
                Object obj2 = bVar.d;
                while (true) {
                    int A = A();
                    if (A == Integer.MAX_VALUE) {
                        map.put(obj, obj2);
                        return;
                    } else if (A == 1) {
                        obj = S(bVar.f10962a, null, null);
                    } else if (A != 2) {
                        try {
                            if (!C()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                                break;
                            }
                        } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                            if (!C()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                            }
                        }
                    } else {
                        obj2 = S(bVar.c, bVar.d.getClass(), extensionRegistryLite);
                    }
                }
            } finally {
                this.d = i;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public <T> void L(List<T> list, n0<T> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i;
            if (WireFormat.getTagWireType(this.e) == 2) {
                int i2 = this.e;
                do {
                    list.add(Y(n0Var, extensionRegistryLite));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == i2);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public <T> T M(n0<T> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            g0(2);
            return (T) Y(n0Var, extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public <T> T N(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            g0(2);
            return (T) Y(i0.a().d(cls), extensionRegistryLite);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public <T> void O(List<T> list, n0<T> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i;
            if (WireFormat.getTagWireType(this.e) == 3) {
                int i2 = this.e;
                do {
                    list.add(T(n0Var, extensionRegistryLite));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == i2);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        public final boolean Q() {
            return this.c == this.d;
        }

        public final byte R() throws IOException {
            int i = this.c;
            if (i != this.d) {
                byte[] bArr = this.b;
                this.c = i + 1;
                return bArr[i];
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public final Object S(WireFormat.FieldType fieldType, Class<?> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            switch (a.f10974a[fieldType.ordinal()]) {
                case 1:
                    return Boolean.valueOf(f());
                case 2:
                    return p();
                case 3:
                    return Double.valueOf(readDouble());
                case 4:
                    return Integer.valueOf(l());
                case 5:
                    return Integer.valueOf(v());
                case 6:
                    return Long.valueOf(c());
                case 7:
                    return Float.valueOf(readFloat());
                case 8:
                    return Integer.valueOf(q());
                case 9:
                    return Long.valueOf(G());
                case 10:
                    return N(cls, extensionRegistryLite);
                case 11:
                    return Integer.valueOf(D());
                case 12:
                    return Long.valueOf(g());
                case 13:
                    return Integer.valueOf(m());
                case 14:
                    return Long.valueOf(z());
                case 15:
                    return H();
                case 16:
                    return Integer.valueOf(i());
                case 17:
                    return Long.valueOf(t());
                default:
                    throw new RuntimeException("unsupported field type.");
            }
        }

        public final <T> T T(n0<T> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i = this.f;
            this.f = WireFormat.a(WireFormat.getTagFieldNumber(this.e), 4);
            try {
                T newInstance = n0Var.newInstance();
                n0Var.h(newInstance, this, extensionRegistryLite);
                n0Var.d(newInstance);
                if (this.e == this.f) {
                    return newInstance;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.f = i;
            }
        }

        public final int U() throws IOException {
            e0(4);
            return V();
        }

        public final int V() {
            int i = this.c;
            byte[] bArr = this.b;
            this.c = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        public final long W() throws IOException {
            e0(8);
            return X();
        }

        public final long X() {
            int i = this.c;
            byte[] bArr = this.b;
            this.c = i + 8;
            return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }

        public final <T> T Y(n0<T> n0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int b0 = b0();
            e0(b0);
            int i = this.d;
            int i2 = this.c + b0;
            this.d = i2;
            try {
                T newInstance = n0Var.newInstance();
                n0Var.h(newInstance, this, extensionRegistryLite);
                n0Var.d(newInstance);
                if (this.c == i2) {
                    return newInstance;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.d = i;
            }
        }

        public String Z(boolean z) throws IOException {
            g0(2);
            int b0 = b0();
            if (b0 == 0) {
                return "";
            }
            e0(b0);
            if (z) {
                byte[] bArr = this.b;
                int i = this.c;
                if (!v0.u(bArr, i, i + b0)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            String str = new String(this.b, this.c, b0, Internal.f10957a);
            this.c += b0;
            return str;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public String a() throws IOException {
            return Z(false);
        }

        public void a0(List<String> list, boolean z) throws IOException {
            int i;
            int i2;
            if (WireFormat.getTagWireType(this.e) == 2) {
                if ((list instanceof LazyStringList) && !z) {
                    LazyStringList lazyStringList = (LazyStringList) list;
                    do {
                        lazyStringList.add(p());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                }
                do {
                    list.add(Z(z));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void b(List<String> list) throws IOException {
            a0(list, false);
        }

        public final int b0() throws IOException {
            int i;
            int i2 = this.c;
            int i3 = this.d;
            if (i3 != i2) {
                byte[] bArr = this.b;
                int i4 = i2 + 1;
                byte b = bArr[i2];
                if (b >= 0) {
                    this.c = i4;
                    return b;
                } else if (i3 - i4 < 9) {
                    return (int) d0();
                } else {
                    int i5 = i4 + 1;
                    int i6 = b ^ (bArr[i4] << 7);
                    if (i6 < 0) {
                        i = i6 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                    } else {
                        int i7 = i5 + 1;
                        int i8 = i6 ^ (bArr[i5] << 14);
                        if (i8 >= 0) {
                            i = i8 ^ 16256;
                        } else {
                            i5 = i7 + 1;
                            int i9 = i8 ^ (bArr[i7] << 21);
                            if (i9 < 0) {
                                i = i9 ^ (-2080896);
                            } else {
                                i7 = i5 + 1;
                                byte b2 = bArr[i5];
                                i = (i9 ^ (b2 << 28)) ^ 266354560;
                                if (b2 < 0) {
                                    i5 = i7 + 1;
                                    if (bArr[i7] < 0) {
                                        i7 = i5 + 1;
                                        if (bArr[i5] < 0) {
                                            i5 = i7 + 1;
                                            if (bArr[i7] < 0) {
                                                i7 = i5 + 1;
                                                if (bArr[i5] < 0) {
                                                    i5 = i7 + 1;
                                                    if (bArr[i7] < 0) {
                                                        throw InvalidProtocolBufferException.malformedVarint();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i5 = i7;
                    }
                    this.c = i5;
                    return i;
                }
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public long c() throws IOException {
            g0(1);
            return W();
        }

        public long c0() throws IOException {
            long j;
            long j2;
            long j3;
            int i;
            int i2 = this.c;
            int i3 = this.d;
            if (i3 != i2) {
                byte[] bArr = this.b;
                int i4 = i2 + 1;
                byte b = bArr[i2];
                if (b >= 0) {
                    this.c = i4;
                    return b;
                } else if (i3 - i4 < 9) {
                    return d0();
                } else {
                    int i5 = i4 + 1;
                    int i6 = b ^ (bArr[i4] << 7);
                    if (i6 >= 0) {
                        int i7 = i5 + 1;
                        int i8 = i6 ^ (bArr[i5] << 14);
                        if (i8 >= 0) {
                            i5 = i7;
                            j = i8 ^ 16256;
                        } else {
                            i5 = i7 + 1;
                            int i9 = i8 ^ (bArr[i7] << 21);
                            if (i9 < 0) {
                                i = i9 ^ (-2080896);
                            } else {
                                long j4 = i9;
                                int i10 = i5 + 1;
                                long j5 = j4 ^ (bArr[i5] << 28);
                                if (j5 >= 0) {
                                    j3 = 266354560;
                                } else {
                                    i5 = i10 + 1;
                                    long j6 = j5 ^ (bArr[i10] << 35);
                                    if (j6 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        i10 = i5 + 1;
                                        j5 = j6 ^ (bArr[i5] << 42);
                                        if (j5 >= 0) {
                                            j3 = 4363953127296L;
                                        } else {
                                            i5 = i10 + 1;
                                            j6 = j5 ^ (bArr[i10] << 49);
                                            if (j6 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                int i11 = i5 + 1;
                                                long j7 = (j6 ^ (bArr[i5] << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    i5 = i11 + 1;
                                                    if (bArr[i11] < 0) {
                                                        throw InvalidProtocolBufferException.malformedVarint();
                                                    }
                                                } else {
                                                    i5 = i11;
                                                }
                                                j = j7;
                                            }
                                        }
                                    }
                                    j = j6 ^ j2;
                                }
                                j = j5 ^ j3;
                                i5 = i10;
                            }
                        }
                        this.c = i5;
                        return j;
                    }
                    i = i6 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                    j = i;
                    this.c = i5;
                    return j;
                }
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void d(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof p) {
                p pVar = (p) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 2) {
                    int b0 = b0();
                    l0(b0);
                    int i3 = this.c + b0;
                    while (this.c < i3) {
                        pVar.addInt(V());
                    }
                    return;
                } else if (tagWireType == 5) {
                    do {
                        pVar.addInt(D());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 2) {
                int b02 = b0();
                l0(b02);
                int i4 = this.c + b02;
                while (this.c < i4) {
                    list.add(Integer.valueOf(V()));
                }
            } else if (tagWireType2 == 5) {
                do {
                    list.add(Integer.valueOf(D()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public final long d0() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte R = R();
                j |= (R & Byte.MAX_VALUE) << i;
                if ((R & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void e(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof s) {
                s sVar = (s) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType != 0) {
                    if (tagWireType == 2) {
                        int b0 = this.c + b0();
                        while (this.c < b0) {
                            sVar.addLong(CodedInputStream.decodeZigZag64(c0()));
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    sVar.addLong(z());
                    if (Q()) {
                        return;
                    }
                    i2 = this.c;
                } while (b0() == this.e);
                this.c = i2;
                return;
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    int b02 = this.c + b0();
                    while (this.c < b02) {
                        list.add(Long.valueOf(CodedInputStream.decodeZigZag64(c0())));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Long.valueOf(z()));
                if (Q()) {
                    return;
                }
                i = this.c;
            } while (b0() == this.e);
            this.c = i;
        }

        public final void e0(int i) throws IOException {
            if (i < 0 || i > this.d - this.c) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public boolean f() throws IOException {
            g0(0);
            return b0() != 0;
        }

        public final void f0(int i) throws IOException {
            if (this.c != i) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public long g() throws IOException {
            g0(1);
            return W();
        }

        public final void g0(int i) throws IOException {
            if (WireFormat.getTagWireType(this.e) != i) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int getTag() {
            return this.e;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void h(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof s) {
                s sVar = (s) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 0) {
                    do {
                        sVar.addLong(t());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else if (tagWireType == 2) {
                    int b0 = this.c + b0();
                    while (this.c < b0) {
                        sVar.addLong(c0());
                    }
                    f0(b0);
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 0) {
                do {
                    list.add(Long.valueOf(t()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else if (tagWireType2 == 2) {
                int b02 = this.c + b0();
                while (this.c < b02) {
                    list.add(Long.valueOf(c0()));
                }
                f0(b02);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public final void h0(int i) throws IOException {
            e0(i);
            this.c += i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int i() throws IOException {
            g0(0);
            return b0();
        }

        public final void i0() throws IOException {
            int i = this.f;
            this.f = WireFormat.a(WireFormat.getTagFieldNumber(this.e), 4);
            while (A() != Integer.MAX_VALUE && C()) {
            }
            if (this.e == this.f) {
                this.f = i;
                return;
            }
            throw InvalidProtocolBufferException.parseFailure();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void j(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof s) {
                s sVar = (s) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 0) {
                    do {
                        sVar.addLong(G());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else if (tagWireType == 2) {
                    int b0 = this.c + b0();
                    while (this.c < b0) {
                        sVar.addLong(c0());
                    }
                    f0(b0);
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 0) {
                do {
                    list.add(Long.valueOf(G()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else if (tagWireType2 == 2) {
                int b02 = this.c + b0();
                while (this.c < b02) {
                    list.add(Long.valueOf(c0()));
                }
                f0(b02);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public final void j0() throws IOException {
            int i = this.d;
            int i2 = this.c;
            if (i - i2 >= 10) {
                byte[] bArr = this.b;
                int i3 = 0;
                while (i3 < 10) {
                    int i4 = i2 + 1;
                    if (bArr[i2] >= 0) {
                        this.c = i4;
                        return;
                    } else {
                        i3++;
                        i2 = i4;
                    }
                }
            }
            k0();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void k(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof p) {
                p pVar = (p) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType != 0) {
                    if (tagWireType == 2) {
                        int b0 = this.c + b0();
                        while (this.c < b0) {
                            pVar.addInt(b0());
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    pVar.addInt(l());
                    if (Q()) {
                        return;
                    }
                    i2 = this.c;
                } while (b0() == this.e);
                this.c = i2;
                return;
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    int b02 = this.c + b0();
                    while (this.c < b02) {
                        list.add(Integer.valueOf(b0()));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(l()));
                if (Q()) {
                    return;
                }
                i = this.c;
            } while (b0() == this.e);
            this.c = i;
        }

        public final void k0() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (R() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int l() throws IOException {
            g0(0);
            return b0();
        }

        public final void l0(int i) throws IOException {
            e0(i);
            if ((i & 3) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int m() throws IOException {
            g0(0);
            return CodedInputStream.decodeZigZag32(b0());
        }

        public final void m0(int i) throws IOException {
            e0(i);
            if ((i & 7) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void n(List<Boolean> list) throws IOException {
            int i;
            int i2;
            if (list instanceof e) {
                e eVar = (e) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType != 0) {
                    if (tagWireType == 2) {
                        int b0 = this.c + b0();
                        while (this.c < b0) {
                            eVar.addBoolean(b0() != 0);
                        }
                        f0(b0);
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    eVar.addBoolean(f());
                    if (Q()) {
                        return;
                    }
                    i2 = this.c;
                } while (b0() == this.e);
                this.c = i2;
                return;
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    int b02 = this.c + b0();
                    while (this.c < b02) {
                        list.add(Boolean.valueOf(b0() != 0));
                    }
                    f0(b02);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Boolean.valueOf(f()));
                if (Q()) {
                    return;
                }
                i = this.c;
            } while (b0() == this.e);
            this.c = i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void o(List<String> list) throws IOException {
            a0(list, true);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public ByteString p() throws IOException {
            ByteString copyFrom;
            g0(2);
            int b0 = b0();
            if (b0 == 0) {
                return ByteString.EMPTY;
            }
            e0(b0);
            if (this.f10975a) {
                copyFrom = ByteString.wrap(this.b, this.c, b0);
            } else {
                copyFrom = ByteString.copyFrom(this.b, this.c, b0);
            }
            this.c += b0;
            return copyFrom;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int q() throws IOException {
            g0(0);
            return b0();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void r(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof s) {
                s sVar = (s) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 1) {
                    do {
                        sVar.addLong(c());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else if (tagWireType == 2) {
                    int b0 = b0();
                    m0(b0);
                    int i3 = this.c + b0;
                    while (this.c < i3) {
                        sVar.addLong(X());
                    }
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 1) {
                do {
                    list.add(Long.valueOf(c()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else if (tagWireType2 == 2) {
                int b02 = b0();
                m0(b02);
                int i4 = this.c + b02;
                while (this.c < i4) {
                    list.add(Long.valueOf(X()));
                }
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public double readDouble() throws IOException {
            g0(1);
            return Double.longBitsToDouble(W());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public float readFloat() throws IOException {
            g0(5);
            return Float.intBitsToFloat(U());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void s(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof p) {
                p pVar = (p) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType != 0) {
                    if (tagWireType == 2) {
                        int b0 = this.c + b0();
                        while (this.c < b0) {
                            pVar.addInt(CodedInputStream.decodeZigZag32(b0()));
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    pVar.addInt(m());
                    if (Q()) {
                        return;
                    }
                    i2 = this.c;
                } while (b0() == this.e);
                this.c = i2;
                return;
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    int b02 = this.c + b0();
                    while (this.c < b02) {
                        list.add(Integer.valueOf(CodedInputStream.decodeZigZag32(b0())));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(m()));
                if (Q()) {
                    return;
                }
                i = this.c;
            } while (b0() == this.e);
            this.c = i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public long t() throws IOException {
            g0(0);
            return c0();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void u(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof p) {
                p pVar = (p) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType != 0) {
                    if (tagWireType == 2) {
                        int b0 = this.c + b0();
                        while (this.c < b0) {
                            pVar.addInt(b0());
                        }
                        return;
                    }
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    pVar.addInt(i());
                    if (Q()) {
                        return;
                    }
                    i2 = this.c;
                } while (b0() == this.e);
                this.c = i2;
                return;
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    int b02 = this.c + b0();
                    while (this.c < b02) {
                        list.add(Integer.valueOf(b0()));
                    }
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(i()));
                if (Q()) {
                    return;
                }
                i = this.c;
            } while (b0() == this.e);
            this.c = i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public int v() throws IOException {
            g0(5);
            return U();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void w(List<Long> list) throws IOException {
            int i;
            int i2;
            if (list instanceof s) {
                s sVar = (s) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 1) {
                    do {
                        sVar.addLong(g());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else if (tagWireType == 2) {
                    int b0 = b0();
                    m0(b0);
                    int i3 = this.c + b0;
                    while (this.c < i3) {
                        sVar.addLong(X());
                    }
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 1) {
                do {
                    list.add(Long.valueOf(g()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else if (tagWireType2 == 2) {
                int b02 = b0();
                m0(b02);
                int i4 = this.c + b02;
                while (this.c < i4) {
                    list.add(Long.valueOf(X()));
                }
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void x(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof p) {
                p pVar = (p) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 0) {
                    do {
                        pVar.addInt(q());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else if (tagWireType == 2) {
                    int b0 = this.c + b0();
                    while (this.c < b0) {
                        pVar.addInt(b0());
                    }
                    f0(b0);
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 0) {
                do {
                    list.add(Integer.valueOf(q()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else if (tagWireType2 == 2) {
                int b02 = this.c + b0();
                while (this.c < b02) {
                    list.add(Integer.valueOf(b0()));
                }
                f0(b02);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public void y(List<Integer> list) throws IOException {
            int i;
            int i2;
            if (list instanceof p) {
                p pVar = (p) list;
                int tagWireType = WireFormat.getTagWireType(this.e);
                if (tagWireType == 2) {
                    int b0 = b0();
                    l0(b0);
                    int i3 = this.c + b0;
                    while (this.c < i3) {
                        pVar.addInt(V());
                    }
                    return;
                } else if (tagWireType == 5) {
                    do {
                        pVar.addInt(v());
                        if (Q()) {
                            return;
                        }
                        i2 = this.c;
                    } while (b0() == this.e);
                    this.c = i2;
                    return;
                } else {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            int tagWireType2 = WireFormat.getTagWireType(this.e);
            if (tagWireType2 == 2) {
                int b02 = b0();
                l0(b02);
                int i4 = this.c + b02;
                while (this.c < i4) {
                    list.add(Integer.valueOf(V()));
                }
            } else if (tagWireType2 == 5) {
                do {
                    list.add(Integer.valueOf(v()));
                    if (Q()) {
                        return;
                    }
                    i = this.c;
                } while (b0() == this.e);
                this.c = i;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.l0
        public long z() throws IOException {
            g0(0);
            return CodedInputStream.decodeZigZag64(c0());
        }
    }

    public /* synthetic */ d(a aVar) {
        this();
    }

    public static d P(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return new b(byteBuffer, z);
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    public d() {
    }
}
