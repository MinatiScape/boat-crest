package com.google.protobuf;

import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class g implements q0 {

    /* renamed from: a  reason: collision with root package name */
    public final CodedInputStream f11729a;
    public int b;
    public int c;
    public int d = 0;

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11730a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f11730a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11730a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11730a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11730a[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11730a[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11730a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f11730a[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f11730a[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f11730a[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f11730a[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f11730a[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f11730a[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f11730a[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f11730a[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f11730a[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f11730a[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f11730a[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public g(CodedInputStream codedInputStream) {
        CodedInputStream codedInputStream2 = (CodedInputStream) Internal.checkNotNull(codedInputStream, "input");
        this.f11729a = codedInputStream2;
        codedInputStream2.wrapper = this;
    }

    public static g Q(CodedInputStream codedInputStream) {
        g gVar = codedInputStream.wrapper;
        return gVar != null ? gVar : new g(codedInputStream);
    }

    @Override // com.google.protobuf.q0
    public int A() throws IOException {
        int i = this.d;
        if (i != 0) {
            this.b = i;
            this.d = 0;
        } else {
            this.b = this.f11729a.readTag();
        }
        int i2 = this.b;
        if (i2 == 0 || i2 == this.c) {
            return Integer.MAX_VALUE;
        }
        return WireFormat.getTagFieldNumber(i2);
    }

    @Override // com.google.protobuf.q0
    public void B(List<Float> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof p) {
            p pVar = (p) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 2) {
                int readUInt32 = this.f11729a.readUInt32();
                X(readUInt32);
                int totalBytesRead = this.f11729a.getTotalBytesRead() + readUInt32;
                do {
                    pVar.addFloat(this.f11729a.readFloat());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                return;
            } else if (tagWireType == 5) {
                do {
                    pVar.addFloat(this.f11729a.readFloat());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 2) {
            int readUInt322 = this.f11729a.readUInt32();
            X(readUInt322);
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + readUInt322;
            do {
                list.add(Float.valueOf(this.f11729a.readFloat()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
        } else if (tagWireType2 == 5) {
            do {
                list.add(Float.valueOf(this.f11729a.readFloat()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public boolean C() throws IOException {
        int i;
        if (this.f11729a.isAtEnd() || (i = this.b) == this.c) {
            return false;
        }
        return this.f11729a.skipField(i);
    }

    @Override // com.google.protobuf.q0
    public int D() throws IOException {
        W(5);
        return this.f11729a.readSFixed32();
    }

    @Override // com.google.protobuf.q0
    public void E(List<ByteString> list) throws IOException {
        int readTag;
        if (WireFormat.getTagWireType(this.b) == 2) {
            do {
                list.add(p());
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.protobuf.q0
    public void F(List<Double> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof j) {
            j jVar = (j) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 1) {
                do {
                    jVar.addDouble(this.f11729a.readDouble());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int readUInt32 = this.f11729a.readUInt32();
                Y(readUInt32);
                int totalBytesRead = this.f11729a.getTotalBytesRead() + readUInt32;
                do {
                    jVar.addDouble(this.f11729a.readDouble());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 1) {
            do {
                list.add(Double.valueOf(this.f11729a.readDouble()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int readUInt322 = this.f11729a.readUInt32();
            Y(readUInt322);
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + readUInt322;
            do {
                list.add(Double.valueOf(this.f11729a.readDouble()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public long G() throws IOException {
        W(0);
        return this.f11729a.readInt64();
    }

    @Override // com.google.protobuf.q0
    public String H() throws IOException {
        W(2);
        return this.f11729a.readStringRequireUtf8();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.q0
    public <T> void I(List<T> list, s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readTag;
        if (WireFormat.getTagWireType(this.b) == 3) {
            int i = this.b;
            do {
                list.add(S(s0Var, extensionRegistryLite));
                if (this.f11729a.isAtEnd() || this.d != 0) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == i);
            this.d = readTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.q0
    public <T> void J(List<T> list, s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readTag;
        if (WireFormat.getTagWireType(this.b) == 2) {
            int i = this.b;
            do {
                list.add(T(s0Var, extensionRegistryLite));
                if (this.f11729a.isAtEnd() || this.d != 0) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == i);
            this.d = readTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.protobuf.q0
    public <T> T K(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        W(3);
        return (T) S(n0.a().d(cls), extensionRegistryLite);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005c, code lost:
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0064, code lost:
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.q0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <K, V> void L(java.util.Map<K, V> r8, com.google.protobuf.MapEntryLite.b<K, V> r9, com.google.protobuf.ExtensionRegistryLite r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.W(r0)
            com.google.protobuf.CodedInputStream r1 = r7.f11729a
            int r1 = r1.readUInt32()
            com.google.protobuf.CodedInputStream r2 = r7.f11729a
            int r1 = r2.pushLimit(r1)
            K r2 = r9.b
            V r3 = r9.d
        L14:
            int r4 = r7.A()     // Catch: java.lang.Throwable -> L65
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L5c
            com.google.protobuf.CodedInputStream r5 = r7.f11729a     // Catch: java.lang.Throwable -> L65
            boolean r5 = r5.isAtEnd()     // Catch: java.lang.Throwable -> L65
            if (r5 == 0) goto L26
            goto L5c
        L26:
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L47
            if (r4 == r0) goto L3a
            boolean r4 = r7.C()     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            if (r4 == 0) goto L34
            goto L14
        L34:
            com.google.protobuf.InvalidProtocolBufferException r4 = new com.google.protobuf.InvalidProtocolBufferException     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            r4.<init>(r6)     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            throw r4     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
        L3a:
            com.google.protobuf.WireFormat$FieldType r4 = r9.c     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            V r5 = r9.d     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            java.lang.Class r5 = r5.getClass()     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            java.lang.Object r3 = r7.R(r4, r5, r10)     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            goto L14
        L47:
            com.google.protobuf.WireFormat$FieldType r4 = r9.f11700a     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            r5 = 0
            java.lang.Object r2 = r7.R(r4, r5, r5)     // Catch: com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L4f java.lang.Throwable -> L65
            goto L14
        L4f:
            boolean r4 = r7.C()     // Catch: java.lang.Throwable -> L65
            if (r4 == 0) goto L56
            goto L14
        L56:
            com.google.protobuf.InvalidProtocolBufferException r8 = new com.google.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L65
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L65
            throw r8     // Catch: java.lang.Throwable -> L65
        L5c:
            r8.put(r2, r3)     // Catch: java.lang.Throwable -> L65
            com.google.protobuf.CodedInputStream r8 = r7.f11729a
            r8.popLimit(r1)
            return
        L65:
            r8 = move-exception
            com.google.protobuf.CodedInputStream r9 = r7.f11729a
            r9.popLimit(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.g.L(java.util.Map, com.google.protobuf.MapEntryLite$b, com.google.protobuf.ExtensionRegistryLite):void");
    }

    @Override // com.google.protobuf.q0
    public <T> T M(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        W(2);
        return (T) T(n0.a().d(cls), extensionRegistryLite);
    }

    @Override // com.google.protobuf.q0
    public <T> T N(s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        W(3);
        return (T) S(s0Var, extensionRegistryLite);
    }

    @Override // com.google.protobuf.q0
    public <T> T O(s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        W(2);
        return (T) T(s0Var, extensionRegistryLite);
    }

    @Override // com.google.protobuf.q0
    public boolean P() {
        return this.f11729a.shouldDiscardUnknownFields();
    }

    public final Object R(WireFormat.FieldType fieldType, Class<?> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        switch (a.f11730a[fieldType.ordinal()]) {
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
                return M(cls, extensionRegistryLite);
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

    public final <T> T S(s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int i = this.c;
        this.c = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.b), 4);
        try {
            T newInstance = s0Var.newInstance();
            s0Var.h(newInstance, this, extensionRegistryLite);
            s0Var.d(newInstance);
            if (this.b == this.c) {
                return newInstance;
            }
            throw InvalidProtocolBufferException.parseFailure();
        } finally {
            this.c = i;
        }
    }

    public final <T> T T(s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        CodedInputStream codedInputStream;
        int readUInt32 = this.f11729a.readUInt32();
        CodedInputStream codedInputStream2 = this.f11729a;
        if (codedInputStream2.recursionDepth < codedInputStream2.recursionLimit) {
            int pushLimit = codedInputStream2.pushLimit(readUInt32);
            T newInstance = s0Var.newInstance();
            this.f11729a.recursionDepth++;
            s0Var.h(newInstance, this, extensionRegistryLite);
            s0Var.d(newInstance);
            this.f11729a.checkLastTagWas(0);
            codedInputStream.recursionDepth--;
            this.f11729a.popLimit(pushLimit);
            return newInstance;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public void U(List<String> list, boolean z) throws IOException {
        int readTag;
        int readTag2;
        if (WireFormat.getTagWireType(this.b) == 2) {
            if ((list instanceof LazyStringList) && !z) {
                LazyStringList lazyStringList = (LazyStringList) list;
                do {
                    lazyStringList.add(p());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            }
            do {
                list.add(z ? H() : a());
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public final void V(int i) throws IOException {
        if (this.f11729a.getTotalBytesRead() != i) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public final void W(int i) throws IOException {
        if (WireFormat.getTagWireType(this.b) != i) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public final void X(int i) throws IOException {
        if ((i & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    public final void Y(int i) throws IOException {
        if ((i & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    @Override // com.google.protobuf.q0
    public String a() throws IOException {
        W(2);
        return this.f11729a.readString();
    }

    @Override // com.google.protobuf.q0
    public void b(List<String> list) throws IOException {
        U(list, false);
    }

    @Override // com.google.protobuf.q0
    public long c() throws IOException {
        W(1);
        return this.f11729a.readFixed64();
    }

    @Override // com.google.protobuf.q0
    public void d(List<Integer> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof r) {
            r rVar = (r) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 2) {
                int readUInt32 = this.f11729a.readUInt32();
                X(readUInt32);
                int totalBytesRead = this.f11729a.getTotalBytesRead() + readUInt32;
                do {
                    rVar.addInt(this.f11729a.readSFixed32());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                return;
            } else if (tagWireType == 5) {
                do {
                    rVar.addInt(this.f11729a.readSFixed32());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 2) {
            int readUInt322 = this.f11729a.readUInt32();
            X(readUInt322);
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + readUInt322;
            do {
                list.add(Integer.valueOf(this.f11729a.readSFixed32()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
        } else if (tagWireType2 == 5) {
            do {
                list.add(Integer.valueOf(this.f11729a.readSFixed32()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public void e(List<Long> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof u) {
            u uVar = (u) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    uVar.addLong(this.f11729a.readSInt64());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    uVar.addLong(this.f11729a.readSInt64());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Long.valueOf(this.f11729a.readSInt64()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Long.valueOf(this.f11729a.readSInt64()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public boolean f() throws IOException {
        W(0);
        return this.f11729a.readBool();
    }

    @Override // com.google.protobuf.q0
    public long g() throws IOException {
        W(1);
        return this.f11729a.readSFixed64();
    }

    @Override // com.google.protobuf.q0
    public int getTag() {
        return this.b;
    }

    @Override // com.google.protobuf.q0
    public void h(List<Long> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof u) {
            u uVar = (u) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    uVar.addLong(this.f11729a.readUInt64());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    uVar.addLong(this.f11729a.readUInt64());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Long.valueOf(this.f11729a.readUInt64()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Long.valueOf(this.f11729a.readUInt64()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public int i() throws IOException {
        W(0);
        return this.f11729a.readUInt32();
    }

    @Override // com.google.protobuf.q0
    public void j(List<Long> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof u) {
            u uVar = (u) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    uVar.addLong(this.f11729a.readInt64());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    uVar.addLong(this.f11729a.readInt64());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Long.valueOf(this.f11729a.readInt64()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Long.valueOf(this.f11729a.readInt64()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public void k(List<Integer> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof r) {
            r rVar = (r) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    rVar.addInt(this.f11729a.readEnum());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    rVar.addInt(this.f11729a.readEnum());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Integer.valueOf(this.f11729a.readEnum()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Integer.valueOf(this.f11729a.readEnum()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public int l() throws IOException {
        W(0);
        return this.f11729a.readEnum();
    }

    @Override // com.google.protobuf.q0
    public int m() throws IOException {
        W(0);
        return this.f11729a.readSInt32();
    }

    @Override // com.google.protobuf.q0
    public void n(List<Boolean> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof e) {
            e eVar = (e) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    eVar.addBoolean(this.f11729a.readBool());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    eVar.addBoolean(this.f11729a.readBool());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Boolean.valueOf(this.f11729a.readBool()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Boolean.valueOf(this.f11729a.readBool()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public void o(List<String> list) throws IOException {
        U(list, true);
    }

    @Override // com.google.protobuf.q0
    public ByteString p() throws IOException {
        W(2);
        return this.f11729a.readBytes();
    }

    @Override // com.google.protobuf.q0
    public int q() throws IOException {
        W(0);
        return this.f11729a.readInt32();
    }

    @Override // com.google.protobuf.q0
    public void r(List<Long> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof u) {
            u uVar = (u) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 1) {
                do {
                    uVar.addLong(this.f11729a.readFixed64());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int readUInt32 = this.f11729a.readUInt32();
                Y(readUInt32);
                int totalBytesRead = this.f11729a.getTotalBytesRead() + readUInt32;
                do {
                    uVar.addLong(this.f11729a.readFixed64());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 1) {
            do {
                list.add(Long.valueOf(this.f11729a.readFixed64()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int readUInt322 = this.f11729a.readUInt32();
            Y(readUInt322);
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + readUInt322;
            do {
                list.add(Long.valueOf(this.f11729a.readFixed64()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public double readDouble() throws IOException {
        W(1);
        return this.f11729a.readDouble();
    }

    @Override // com.google.protobuf.q0
    public float readFloat() throws IOException {
        W(5);
        return this.f11729a.readFloat();
    }

    @Override // com.google.protobuf.q0
    public void s(List<Integer> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof r) {
            r rVar = (r) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    rVar.addInt(this.f11729a.readSInt32());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    rVar.addInt(this.f11729a.readSInt32());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Integer.valueOf(this.f11729a.readSInt32()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Integer.valueOf(this.f11729a.readSInt32()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public long t() throws IOException {
        W(0);
        return this.f11729a.readUInt64();
    }

    @Override // com.google.protobuf.q0
    public void u(List<Integer> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof r) {
            r rVar = (r) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    rVar.addInt(this.f11729a.readUInt32());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    rVar.addInt(this.f11729a.readUInt32());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Integer.valueOf(this.f11729a.readUInt32()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Integer.valueOf(this.f11729a.readUInt32()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public int v() throws IOException {
        W(5);
        return this.f11729a.readFixed32();
    }

    @Override // com.google.protobuf.q0
    public void w(List<Long> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof u) {
            u uVar = (u) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 1) {
                do {
                    uVar.addLong(this.f11729a.readSFixed64());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int readUInt32 = this.f11729a.readUInt32();
                Y(readUInt32);
                int totalBytesRead = this.f11729a.getTotalBytesRead() + readUInt32;
                do {
                    uVar.addLong(this.f11729a.readSFixed64());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 1) {
            do {
                list.add(Long.valueOf(this.f11729a.readSFixed64()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int readUInt322 = this.f11729a.readUInt32();
            Y(readUInt322);
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + readUInt322;
            do {
                list.add(Long.valueOf(this.f11729a.readSFixed64()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public void x(List<Integer> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof r) {
            r rVar = (r) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 0) {
                do {
                    rVar.addInt(this.f11729a.readInt32());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
                do {
                    rVar.addInt(this.f11729a.readInt32());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                V(totalBytesRead);
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 0) {
            do {
                list.add(Integer.valueOf(this.f11729a.readInt32()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else if (tagWireType2 == 2) {
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + this.f11729a.readUInt32();
            do {
                list.add(Integer.valueOf(this.f11729a.readInt32()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
            V(totalBytesRead2);
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public void y(List<Integer> list) throws IOException {
        int readTag;
        int readTag2;
        if (list instanceof r) {
            r rVar = (r) list;
            int tagWireType = WireFormat.getTagWireType(this.b);
            if (tagWireType == 2) {
                int readUInt32 = this.f11729a.readUInt32();
                X(readUInt32);
                int totalBytesRead = this.f11729a.getTotalBytesRead() + readUInt32;
                do {
                    rVar.addInt(this.f11729a.readFixed32());
                } while (this.f11729a.getTotalBytesRead() < totalBytesRead);
                return;
            } else if (tagWireType == 5) {
                do {
                    rVar.addInt(this.f11729a.readFixed32());
                    if (this.f11729a.isAtEnd()) {
                        return;
                    }
                    readTag2 = this.f11729a.readTag();
                } while (readTag2 == this.b);
                this.d = readTag2;
                return;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        int tagWireType2 = WireFormat.getTagWireType(this.b);
        if (tagWireType2 == 2) {
            int readUInt322 = this.f11729a.readUInt32();
            X(readUInt322);
            int totalBytesRead2 = this.f11729a.getTotalBytesRead() + readUInt322;
            do {
                list.add(Integer.valueOf(this.f11729a.readFixed32()));
            } while (this.f11729a.getTotalBytesRead() < totalBytesRead2);
        } else if (tagWireType2 == 5) {
            do {
                list.add(Integer.valueOf(this.f11729a.readFixed32()));
                if (this.f11729a.isAtEnd()) {
                    return;
                }
                readTag = this.f11729a.readTag();
            } while (readTag == this.b);
            this.d = readTag;
        } else {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.protobuf.q0
    public long z() throws IOException {
        W(0);
        return this.f11729a.readSInt64();
    }
}
