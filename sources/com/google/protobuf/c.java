package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class c {

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11720a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f11720a = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11720a[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11720a[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11720a[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11720a[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11720a[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f11720a[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f11720a[WireFormat.FieldType.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f11720a[WireFormat.FieldType.FIXED32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f11720a[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f11720a[WireFormat.FieldType.BOOL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f11720a[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f11720a[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f11720a[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f11720a[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f11720a[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f11720a[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f11720a[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public int f11721a;
        public long b;
        public Object c;
        public final ExtensionRegistryLite d;

        public b(ExtensionRegistryLite extensionRegistryLite) {
            Objects.requireNonNull(extensionRegistryLite);
            this.d = extensionRegistryLite;
        }
    }

    public static int A(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        r rVar = (r) protobufList;
        int I = I(bArr, i2, bVar);
        rVar.addInt(CodedInputStream.decodeZigZag32(bVar.f11721a));
        while (I < i3) {
            int I2 = I(bArr, I, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            I = I(bArr, I2, bVar);
            rVar.addInt(CodedInputStream.decodeZigZag32(bVar.f11721a));
        }
        return I;
    }

    public static int B(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        u uVar = (u) protobufList;
        int L = L(bArr, i2, bVar);
        uVar.addLong(CodedInputStream.decodeZigZag64(bVar.b));
        while (L < i3) {
            int I = I(bArr, L, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            L = L(bArr, I, bVar);
            uVar.addLong(CodedInputStream.decodeZigZag64(bVar.b));
        }
        return L;
    }

    public static int C(byte[] bArr, int i, b bVar) throws InvalidProtocolBufferException {
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a;
        if (i2 >= 0) {
            if (i2 == 0) {
                bVar.c = "";
                return I;
            }
            bVar.c = new String(bArr, I, i2, Internal.UTF_8);
            return I + i2;
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x001a -> B:9:0x001b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int D(int r4, byte[] r5, int r6, int r7, com.google.protobuf.Internal.ProtobufList<?> r8, com.google.protobuf.c.b r9) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            int r6 = I(r5, r6, r9)
            int r0 = r9.f11721a
            if (r0 < 0) goto L45
            java.lang.String r1 = ""
            if (r0 != 0) goto L10
            r8.add(r1)
            goto L1b
        L10:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = com.google.protobuf.Internal.UTF_8
            r2.<init>(r5, r6, r0, r3)
            r8.add(r2)
        L1a:
            int r6 = r6 + r0
        L1b:
            if (r6 >= r7) goto L44
            int r0 = I(r5, r6, r9)
            int r2 = r9.f11721a
            if (r4 == r2) goto L26
            goto L44
        L26:
            int r6 = I(r5, r0, r9)
            int r0 = r9.f11721a
            if (r0 < 0) goto L3f
            if (r0 != 0) goto L34
            r8.add(r1)
            goto L1b
        L34:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = com.google.protobuf.Internal.UTF_8
            r2.<init>(r5, r6, r0, r3)
            r8.add(r2)
            goto L1a
        L3f:
            com.google.protobuf.InvalidProtocolBufferException r4 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r4
        L44:
            return r6
        L45:
            com.google.protobuf.InvalidProtocolBufferException r4 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.c.D(int, byte[], int, int, com.google.protobuf.Internal$ProtobufList, com.google.protobuf.c$b):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0022 -> B:11:0x0023). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int E(int r5, byte[] r6, int r7, int r8, com.google.protobuf.Internal.ProtobufList<?> r9, com.google.protobuf.c.b r10) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            int r7 = I(r6, r7, r10)
            int r0 = r10.f11721a
            if (r0 < 0) goto L5f
            java.lang.String r1 = ""
            if (r0 != 0) goto L10
            r9.add(r1)
            goto L23
        L10:
            int r2 = r7 + r0
            boolean r3 = com.google.protobuf.b1.u(r6, r7, r2)
            if (r3 == 0) goto L5a
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.protobuf.Internal.UTF_8
            r3.<init>(r6, r7, r0, r4)
            r9.add(r3)
        L22:
            r7 = r2
        L23:
            if (r7 >= r8) goto L59
            int r0 = I(r6, r7, r10)
            int r2 = r10.f11721a
            if (r5 == r2) goto L2e
            goto L59
        L2e:
            int r7 = I(r6, r0, r10)
            int r0 = r10.f11721a
            if (r0 < 0) goto L54
            if (r0 != 0) goto L3c
            r9.add(r1)
            goto L23
        L3c:
            int r2 = r7 + r0
            boolean r3 = com.google.protobuf.b1.u(r6, r7, r2)
            if (r3 == 0) goto L4f
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.protobuf.Internal.UTF_8
            r3.<init>(r6, r7, r0, r4)
            r9.add(r3)
            goto L22
        L4f:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.invalidUtf8()
            throw r5
        L54:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r5
        L59:
            return r7
        L5a:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.invalidUtf8()
            throw r5
        L5f:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.c.E(int, byte[], int, int, com.google.protobuf.Internal$ProtobufList, com.google.protobuf.c$b):int");
    }

    public static int F(byte[] bArr, int i, b bVar) throws InvalidProtocolBufferException {
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a;
        if (i2 >= 0) {
            if (i2 == 0) {
                bVar.c = "";
                return I;
            }
            bVar.c = b1.h(bArr, I, i2);
            return I + i2;
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int G(int i, byte[] bArr, int i2, int i3, UnknownFieldSetLite unknownFieldSetLite, b bVar) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(i) != 0) {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                int L = L(bArr, i2, bVar);
                unknownFieldSetLite.storeField(i, Long.valueOf(bVar.b));
                return L;
            } else if (tagWireType == 1) {
                unknownFieldSetLite.storeField(i, Long.valueOf(j(bArr, i2)));
                return i2 + 8;
            } else if (tagWireType == 2) {
                int I = I(bArr, i2, bVar);
                int i4 = bVar.f11721a;
                if (i4 >= 0) {
                    if (i4 <= bArr.length - I) {
                        if (i4 == 0) {
                            unknownFieldSetLite.storeField(i, ByteString.EMPTY);
                        } else {
                            unknownFieldSetLite.storeField(i, ByteString.copyFrom(bArr, I, i4));
                        }
                        return I + i4;
                    }
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.negativeSize();
            } else if (tagWireType != 3) {
                if (tagWireType == 5) {
                    unknownFieldSetLite.storeField(i, Integer.valueOf(h(bArr, i2)));
                    return i2 + 4;
                }
                throw InvalidProtocolBufferException.invalidTag();
            } else {
                UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
                int i5 = (i & (-8)) | 4;
                int i6 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int I2 = I(bArr, i2, bVar);
                    int i7 = bVar.f11721a;
                    if (i7 == i5) {
                        i6 = i7;
                        i2 = I2;
                        break;
                    }
                    i6 = i7;
                    i2 = G(i7, bArr, I2, i3, newInstance, bVar);
                }
                if (i2 <= i3 && i6 == i5) {
                    unknownFieldSetLite.storeField(i, newInstance);
                    return i2;
                }
                throw InvalidProtocolBufferException.parseFailure();
            }
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public static int H(int i, byte[] bArr, int i2, b bVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            bVar.f11721a = i3 | (b2 << 7);
            return i4;
        }
        int i5 = i3 | ((b2 & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            bVar.f11721a = i5 | (b3 << 14);
            return i6;
        }
        int i7 = i5 | ((b3 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            bVar.f11721a = i7 | (b4 << 21);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            bVar.f11721a = i9 | (b5 << 28);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                bVar.f11721a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int I(byte[] bArr, int i, b bVar) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        if (b2 >= 0) {
            bVar.f11721a = b2;
            return i2;
        }
        return H(b2, bArr, i2, bVar);
    }

    public static int J(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        r rVar = (r) protobufList;
        int I = I(bArr, i2, bVar);
        rVar.addInt(bVar.f11721a);
        while (I < i3) {
            int I2 = I(bArr, I, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            I = I(bArr, I2, bVar);
            rVar.addInt(bVar.f11721a);
        }
        return I;
    }

    public static int K(long j, byte[] bArr, int i, b bVar) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        long j2 = (j & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int i3 = 7;
        while (b2 < 0) {
            int i4 = i2 + 1;
            byte b3 = bArr[i2];
            i3 += 7;
            j2 |= (b3 & Byte.MAX_VALUE) << i3;
            i2 = i4;
            b2 = b3;
        }
        bVar.b = j2;
        return i2;
    }

    public static int L(byte[] bArr, int i, b bVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            bVar.b = j;
            return i2;
        }
        return K(j, bArr, i2, bVar);
    }

    public static int M(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        u uVar = (u) protobufList;
        int L = L(bArr, i2, bVar);
        uVar.addLong(bVar.b);
        while (L < i3) {
            int I = I(bArr, L, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            L = L(bArr, I, bVar);
            uVar.addLong(bVar.b);
        }
        return L;
    }

    public static int N(int i, byte[] bArr, int i2, int i3, b bVar) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(i) != 0) {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 5) {
                                return i2 + 4;
                            }
                            throw InvalidProtocolBufferException.invalidTag();
                        }
                        int i4 = (i & (-8)) | 4;
                        int i5 = 0;
                        while (i2 < i3) {
                            i2 = I(bArr, i2, bVar);
                            i5 = bVar.f11721a;
                            if (i5 == i4) {
                                break;
                            }
                            i2 = N(i5, bArr, i2, i3, bVar);
                        }
                        if (i2 > i3 || i5 != i4) {
                            throw InvalidProtocolBufferException.parseFailure();
                        }
                        return i2;
                    }
                    return I(bArr, i2, bVar) + bVar.f11721a;
                }
                return i2 + 8;
            }
            return L(bArr, i2, bVar);
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public static int a(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        e eVar = (e) protobufList;
        int L = L(bArr, i2, bVar);
        eVar.addBoolean(bVar.b != 0);
        while (L < i3) {
            int I = I(bArr, L, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            L = L(bArr, I, bVar);
            eVar.addBoolean(bVar.b != 0);
        }
        return L;
    }

    public static int b(byte[] bArr, int i, b bVar) throws InvalidProtocolBufferException {
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a;
        if (i2 >= 0) {
            if (i2 <= bArr.length - I) {
                if (i2 == 0) {
                    bVar.c = ByteString.EMPTY;
                    return I;
                }
                bVar.c = ByteString.copyFrom(bArr, I, i2);
                return I + i2;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x001b -> B:10:0x001c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int c(int r2, byte[] r3, int r4, int r5, com.google.protobuf.Internal.ProtobufList<?> r6, com.google.protobuf.c.b r7) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            int r4 = I(r3, r4, r7)
            int r0 = r7.f11721a
            if (r0 < 0) goto L53
            int r1 = r3.length
            int r1 = r1 - r4
            if (r0 > r1) goto L4e
            if (r0 != 0) goto L14
            com.google.protobuf.ByteString r0 = com.google.protobuf.ByteString.EMPTY
            r6.add(r0)
            goto L1c
        L14:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFrom(r3, r4, r0)
            r6.add(r1)
        L1b:
            int r4 = r4 + r0
        L1c:
            if (r4 >= r5) goto L4d
            int r0 = I(r3, r4, r7)
            int r1 = r7.f11721a
            if (r2 == r1) goto L27
            goto L4d
        L27:
            int r4 = I(r3, r0, r7)
            int r0 = r7.f11721a
            if (r0 < 0) goto L48
            int r1 = r3.length
            int r1 = r1 - r4
            if (r0 > r1) goto L43
            if (r0 != 0) goto L3b
            com.google.protobuf.ByteString r0 = com.google.protobuf.ByteString.EMPTY
            r6.add(r0)
            goto L1c
        L3b:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFrom(r3, r4, r0)
            r6.add(r1)
            goto L1b
        L43:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r2
        L48:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r2
        L4d:
            return r4
        L4e:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r2
        L53:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.c.c(int, byte[], int, int, com.google.protobuf.Internal$ProtobufList, com.google.protobuf.c$b):int");
    }

    public static double d(byte[] bArr, int i) {
        return Double.longBitsToDouble(j(bArr, i));
    }

    public static int e(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        j jVar = (j) protobufList;
        jVar.addDouble(d(bArr, i2));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int I = I(bArr, i4, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            jVar.addDouble(d(bArr, I));
            i4 = I + 8;
        }
        return i4;
    }

    public static int f(int i, byte[] bArr, int i2, int i3, GeneratedMessageLite.ExtendableMessage<?, ?> extendableMessage, GeneratedMessageLite.GeneratedExtension<?, ?> generatedExtension, x0<UnknownFieldSetLite, UnknownFieldSetLite> x0Var, b bVar) throws IOException {
        Object u;
        FieldSet<GeneratedMessageLite.b> fieldSet = extendableMessage.extensions;
        int i4 = i >>> 3;
        if (generatedExtension.descriptor.isRepeated() && generatedExtension.descriptor.isPacked()) {
            switch (a.f11720a[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    j jVar = new j();
                    int s = s(bArr, i2, jVar, bVar);
                    fieldSet.O(generatedExtension.descriptor, jVar);
                    return s;
                case 2:
                    p pVar = new p();
                    int v = v(bArr, i2, pVar, bVar);
                    fieldSet.O(generatedExtension.descriptor, pVar);
                    return v;
                case 3:
                case 4:
                    u uVar = new u();
                    int z = z(bArr, i2, uVar, bVar);
                    fieldSet.O(generatedExtension.descriptor, uVar);
                    return z;
                case 5:
                case 6:
                    r rVar = new r();
                    int y = y(bArr, i2, rVar, bVar);
                    fieldSet.O(generatedExtension.descriptor, rVar);
                    return y;
                case 7:
                case 8:
                    u uVar2 = new u();
                    int u2 = u(bArr, i2, uVar2, bVar);
                    fieldSet.O(generatedExtension.descriptor, uVar2);
                    return u2;
                case 9:
                case 10:
                    r rVar2 = new r();
                    int t = t(bArr, i2, rVar2, bVar);
                    fieldSet.O(generatedExtension.descriptor, rVar2);
                    return t;
                case 11:
                    e eVar = new e();
                    int r = r(bArr, i2, eVar, bVar);
                    fieldSet.O(generatedExtension.descriptor, eVar);
                    return r;
                case 12:
                    r rVar3 = new r();
                    int w = w(bArr, i2, rVar3, bVar);
                    fieldSet.O(generatedExtension.descriptor, rVar3);
                    return w;
                case 13:
                    u uVar3 = new u();
                    int x = x(bArr, i2, uVar3, bVar);
                    fieldSet.O(generatedExtension.descriptor, uVar3);
                    return x;
                case 14:
                    r rVar4 = new r();
                    int y2 = y(bArr, i2, rVar4, bVar);
                    UnknownFieldSetLite unknownFieldSetLite = extendableMessage.unknownFields;
                    UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) u0.z(i4, rVar4, generatedExtension.descriptor.getEnumType(), unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance() ? unknownFieldSetLite : null, x0Var);
                    if (unknownFieldSetLite2 != null) {
                        extendableMessage.unknownFields = unknownFieldSetLite2;
                    }
                    fieldSet.O(generatedExtension.descriptor, rVar4);
                    return y2;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + generatedExtension.descriptor.getLiteType());
            }
        }
        if (generatedExtension.getLiteType() == WireFormat.FieldType.ENUM) {
            i2 = I(bArr, i2, bVar);
            if (generatedExtension.descriptor.getEnumType().findValueByNumber(bVar.f11721a) == null) {
                UnknownFieldSetLite unknownFieldSetLite3 = extendableMessage.unknownFields;
                if (unknownFieldSetLite3 == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFieldSetLite3 = UnknownFieldSetLite.newInstance();
                    extendableMessage.unknownFields = unknownFieldSetLite3;
                }
                u0.M(i4, bVar.f11721a, unknownFieldSetLite3, x0Var);
                return i2;
            }
            r2 = Integer.valueOf(bVar.f11721a);
        } else {
            switch (a.f11720a[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    r2 = Double.valueOf(d(bArr, i2));
                    i2 += 8;
                    break;
                case 2:
                    r2 = Float.valueOf(l(bArr, i2));
                    i2 += 4;
                    break;
                case 3:
                case 4:
                    i2 = L(bArr, i2, bVar);
                    r2 = Long.valueOf(bVar.b);
                    break;
                case 5:
                case 6:
                    i2 = I(bArr, i2, bVar);
                    r2 = Integer.valueOf(bVar.f11721a);
                    break;
                case 7:
                case 8:
                    r2 = Long.valueOf(j(bArr, i2));
                    i2 += 8;
                    break;
                case 9:
                case 10:
                    r2 = Integer.valueOf(h(bArr, i2));
                    i2 += 4;
                    break;
                case 11:
                    i2 = L(bArr, i2, bVar);
                    r2 = Boolean.valueOf(bVar.b != 0);
                    break;
                case 12:
                    i2 = I(bArr, i2, bVar);
                    r2 = Integer.valueOf(CodedInputStream.decodeZigZag32(bVar.f11721a));
                    break;
                case 13:
                    i2 = L(bArr, i2, bVar);
                    r2 = Long.valueOf(CodedInputStream.decodeZigZag64(bVar.b));
                    break;
                case 14:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 15:
                    i2 = b(bArr, i2, bVar);
                    r2 = bVar.c;
                    break;
                case 16:
                    i2 = C(bArr, i2, bVar);
                    r2 = bVar.c;
                    break;
                case 17:
                    i2 = n(n0.a().d(generatedExtension.getMessageDefaultInstance().getClass()), bArr, i2, i3, (i4 << 3) | 4, bVar);
                    r2 = bVar.c;
                    break;
                case 18:
                    i2 = p(n0.a().d(generatedExtension.getMessageDefaultInstance().getClass()), bArr, i2, i3, bVar);
                    r2 = bVar.c;
                    break;
            }
        }
        if (generatedExtension.isRepeated()) {
            fieldSet.h(generatedExtension.descriptor, r2);
        } else {
            int i5 = a.f11720a[generatedExtension.getLiteType().ordinal()];
            if ((i5 == 17 || i5 == 18) && (u = fieldSet.u(generatedExtension.descriptor)) != null) {
                r2 = Internal.mergeMessage(u, r2);
            }
            fieldSet.O(generatedExtension.descriptor, r2);
        }
        return i2;
    }

    public static int g(int i, byte[] bArr, int i2, int i3, Object obj, MessageLite messageLite, x0<UnknownFieldSetLite, UnknownFieldSetLite> x0Var, b bVar) throws IOException {
        GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber = bVar.d.findLiteExtensionByNumber(messageLite, i >>> 3);
        if (findLiteExtensionByNumber == null) {
            return G(i, bArr, i2, i3, d0.v(obj), bVar);
        }
        GeneratedMessageLite.ExtendableMessage extendableMessage = (GeneratedMessageLite.ExtendableMessage) obj;
        extendableMessage.ensureExtensionsAreMutable();
        return f(i, bArr, i2, i3, extendableMessage, findLiteExtensionByNumber, x0Var, bVar);
    }

    public static int h(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int i(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        r rVar = (r) protobufList;
        rVar.addInt(h(bArr, i2));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int I = I(bArr, i4, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            rVar.addInt(h(bArr, I));
            i4 = I + 4;
        }
        return i4;
    }

    public static long j(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    public static int k(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        u uVar = (u) protobufList;
        uVar.addLong(j(bArr, i2));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int I = I(bArr, i4, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            uVar.addLong(j(bArr, I));
            i4 = I + 8;
        }
        return i4;
    }

    public static float l(byte[] bArr, int i) {
        return Float.intBitsToFloat(h(bArr, i));
    }

    public static int m(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) {
        p pVar = (p) protobufList;
        pVar.addFloat(l(bArr, i2));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int I = I(bArr, i4, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            pVar.addFloat(l(bArr, I));
            i4 = I + 4;
        }
        return i4;
    }

    public static int n(s0 s0Var, byte[] bArr, int i, int i2, int i3, b bVar) throws IOException {
        d0 d0Var = (d0) s0Var;
        Object newInstance = d0Var.newInstance();
        int c0 = d0Var.c0(newInstance, bArr, i, i2, i3, bVar);
        d0Var.d(newInstance);
        bVar.c = newInstance;
        return c0;
    }

    public static int o(s0 s0Var, int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        int i4 = (i & (-8)) | 4;
        int n = n(s0Var, bArr, i2, i3, i4, bVar);
        protobufList.add(bVar.c);
        while (n < i3) {
            int I = I(bArr, n, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            n = n(s0Var, bArr, I, i3, i4, bVar);
            protobufList.add(bVar.c);
        }
        return n;
    }

    public static int p(s0 s0Var, byte[] bArr, int i, int i2, b bVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = H(i4, bArr, i3, bVar);
            i4 = bVar.f11721a;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            Object newInstance = s0Var.newInstance();
            int i6 = i4 + i5;
            s0Var.i(newInstance, bArr, i5, i6, bVar);
            s0Var.d(newInstance);
            bVar.c = newInstance;
            return i6;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int q(s0<?> s0Var, int i, byte[] bArr, int i2, int i3, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        int p = p(s0Var, bArr, i2, i3, bVar);
        protobufList.add(bVar.c);
        while (p < i3) {
            int I = I(bArr, p, bVar);
            if (i != bVar.f11721a) {
                break;
            }
            p = p(s0Var, bArr, I, i3, bVar);
            protobufList.add(bVar.c);
        }
        return p;
    }

    public static int r(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        e eVar = (e) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            I = L(bArr, I, bVar);
            eVar.addBoolean(bVar.b != 0);
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int s(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        j jVar = (j) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            jVar.addDouble(d(bArr, I));
            I += 8;
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int t(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        r rVar = (r) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            rVar.addInt(h(bArr, I));
            I += 4;
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int u(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        u uVar = (u) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            uVar.addLong(j(bArr, I));
            I += 8;
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int v(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        p pVar = (p) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            pVar.addFloat(l(bArr, I));
            I += 4;
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int w(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        r rVar = (r) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            I = I(bArr, I, bVar);
            rVar.addInt(CodedInputStream.decodeZigZag32(bVar.f11721a));
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int x(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        u uVar = (u) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            I = L(bArr, I, bVar);
            uVar.addLong(CodedInputStream.decodeZigZag64(bVar.b));
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int y(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        r rVar = (r) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            I = I(bArr, I, bVar);
            rVar.addInt(bVar.f11721a);
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int z(byte[] bArr, int i, Internal.ProtobufList<?> protobufList, b bVar) throws IOException {
        u uVar = (u) protobufList;
        int I = I(bArr, i, bVar);
        int i2 = bVar.f11721a + I;
        while (I < i2) {
            I = L(bArr, I, bVar);
            uVar.addLong(bVar.b);
        }
        if (I == i2) {
            return I;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }
}
