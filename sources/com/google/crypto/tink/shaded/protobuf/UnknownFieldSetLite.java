package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class UnknownFieldSetLite {
    public static final UnknownFieldSetLite f = new UnknownFieldSetLite(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f10965a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    public static boolean c(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean d(Object[] objArr, Object[] objArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!objArr[i2].equals(objArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    public static int e(int[] iArr, int i) {
        int i2 = 17;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + iArr[i3];
        }
        return i2;
    }

    public static int f(Object[] objArr, int i) {
        int i2 = 17;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + objArr[i3].hashCode();
        }
        return i2;
    }

    public static UnknownFieldSetLite getDefaultInstance() {
        return f;
    }

    public static UnknownFieldSetLite k(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        int i = unknownFieldSetLite.f10965a + unknownFieldSetLite2.f10965a;
        int[] copyOf = Arrays.copyOf(unknownFieldSetLite.b, i);
        System.arraycopy(unknownFieldSetLite2.b, 0, copyOf, unknownFieldSetLite.f10965a, unknownFieldSetLite2.f10965a);
        Object[] copyOf2 = Arrays.copyOf(unknownFieldSetLite.c, i);
        System.arraycopy(unknownFieldSetLite2.c, 0, copyOf2, unknownFieldSetLite.f10965a, unknownFieldSetLite2.f10965a);
        return new UnknownFieldSetLite(i, copyOf, copyOf2, true);
    }

    public static UnknownFieldSetLite l() {
        return new UnknownFieldSetLite();
    }

    public static void p(int i, Object obj, Writer writer) throws IOException {
        int tagFieldNumber = WireFormat.getTagFieldNumber(i);
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType == 0) {
            writer.u(tagFieldNumber, ((Long) obj).longValue());
        } else if (tagWireType == 1) {
            writer.s(tagFieldNumber, ((Long) obj).longValue());
        } else if (tagWireType == 2) {
            writer.M(tagFieldNumber, (ByteString) obj);
        } else if (tagWireType != 3) {
            if (tagWireType == 5) {
                writer.c(tagFieldNumber, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(InvalidProtocolBufferException.invalidWireType());
        } else if (writer.t() == Writer.FieldOrder.ASCENDING) {
            writer.x(tagFieldNumber);
            ((UnknownFieldSetLite) obj).writeTo(writer);
            writer.C(tagFieldNumber);
        } else {
            writer.C(tagFieldNumber);
            ((UnknownFieldSetLite) obj).writeTo(writer);
            writer.x(tagFieldNumber);
        }
    }

    public void a() {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
    }

    public final void b() {
        int i = this.f10965a;
        int[] iArr = this.b;
        if (i == iArr.length) {
            int i2 = i + (i < 4 ? 8 : i >> 1);
            this.b = Arrays.copyOf(iArr, i2);
            this.c = Arrays.copyOf(this.c, i2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof UnknownFieldSetLite)) {
            UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
            int i = this.f10965a;
            return i == unknownFieldSetLite.f10965a && c(this.b, unknownFieldSetLite.b, i) && d(this.c, unknownFieldSetLite.c, this.f10965a);
        }
        return false;
    }

    public boolean g(int i, CodedInputStream codedInputStream) throws IOException {
        a();
        int tagFieldNumber = WireFormat.getTagFieldNumber(i);
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType == 0) {
            n(i, Long.valueOf(codedInputStream.readInt64()));
            return true;
        } else if (tagWireType == 1) {
            n(i, Long.valueOf(codedInputStream.readFixed64()));
            return true;
        } else if (tagWireType == 2) {
            n(i, codedInputStream.readBytes());
            return true;
        } else if (tagWireType == 3) {
            UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite();
            unknownFieldSetLite.h(codedInputStream);
            codedInputStream.checkLastTagWas(WireFormat.a(tagFieldNumber, 4));
            n(i, unknownFieldSetLite);
            return true;
        } else if (tagWireType != 4) {
            if (tagWireType == 5) {
                n(i, Integer.valueOf(codedInputStream.readFixed32()));
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        } else {
            return false;
        }
    }

    public int getSerializedSize() {
        int computeUInt64Size;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f10965a; i3++) {
            int i4 = this.b[i3];
            int tagFieldNumber = WireFormat.getTagFieldNumber(i4);
            int tagWireType = WireFormat.getTagWireType(i4);
            if (tagWireType == 0) {
                computeUInt64Size = CodedOutputStream.computeUInt64Size(tagFieldNumber, ((Long) this.c[i3]).longValue());
            } else if (tagWireType == 1) {
                computeUInt64Size = CodedOutputStream.computeFixed64Size(tagFieldNumber, ((Long) this.c[i3]).longValue());
            } else if (tagWireType == 2) {
                computeUInt64Size = CodedOutputStream.computeBytesSize(tagFieldNumber, (ByteString) this.c[i3]);
            } else if (tagWireType == 3) {
                computeUInt64Size = (CodedOutputStream.computeTagSize(tagFieldNumber) * 2) + ((UnknownFieldSetLite) this.c[i3]).getSerializedSize();
            } else if (tagWireType == 5) {
                computeUInt64Size = CodedOutputStream.computeFixed32Size(tagFieldNumber, ((Integer) this.c[i3]).intValue());
            } else {
                throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
            }
            i2 += computeUInt64Size;
        }
        this.d = i2;
        return i2;
    }

    public int getSerializedSizeAsMessageSet() {
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f10965a; i3++) {
            i2 += CodedOutputStream.computeRawMessageSetExtensionSize(WireFormat.getTagFieldNumber(this.b[i3]), (ByteString) this.c[i3]);
        }
        this.d = i2;
        return i2;
    }

    public final UnknownFieldSetLite h(CodedInputStream codedInputStream) throws IOException {
        int readTag;
        do {
            readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            }
        } while (g(readTag, codedInputStream));
        return this;
    }

    public int hashCode() {
        int i = this.f10965a;
        return ((((527 + i) * 31) + e(this.b, i)) * 31) + f(this.c, this.f10965a);
    }

    public UnknownFieldSetLite i(int i, ByteString byteString) {
        a();
        if (i != 0) {
            n(WireFormat.a(i, 2), byteString);
            return this;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    public UnknownFieldSetLite j(int i, int i2) {
        a();
        if (i != 0) {
            n(WireFormat.a(i, 0), Long.valueOf(i2));
            return this;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    public final void m(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f10965a; i2++) {
            z.c(sb, i, String.valueOf(WireFormat.getTagFieldNumber(this.b[i2])), this.c[i2]);
        }
    }

    public void makeImmutable() {
        this.e = false;
    }

    public void n(int i, Object obj) {
        a();
        b();
        int[] iArr = this.b;
        int i2 = this.f10965a;
        iArr[i2] = i;
        this.c[i2] = obj;
        this.f10965a = i2 + 1;
    }

    public void o(Writer writer) throws IOException {
        if (writer.t() == Writer.FieldOrder.DESCENDING) {
            for (int i = this.f10965a - 1; i >= 0; i--) {
                writer.b(WireFormat.getTagFieldNumber(this.b[i]), this.c[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.f10965a; i2++) {
            writer.b(WireFormat.getTagFieldNumber(this.b[i2]), this.c[i2]);
        }
    }

    public void writeAsMessageSetTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.f10965a; i++) {
            codedOutputStream.writeRawMessageSetExtension(WireFormat.getTagFieldNumber(this.b[i]), (ByteString) this.c[i]);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.f10965a; i++) {
            int i2 = this.b[i];
            int tagFieldNumber = WireFormat.getTagFieldNumber(i2);
            int tagWireType = WireFormat.getTagWireType(i2);
            if (tagWireType == 0) {
                codedOutputStream.writeUInt64(tagFieldNumber, ((Long) this.c[i]).longValue());
            } else if (tagWireType == 1) {
                codedOutputStream.writeFixed64(tagFieldNumber, ((Long) this.c[i]).longValue());
            } else if (tagWireType == 2) {
                codedOutputStream.writeBytes(tagFieldNumber, (ByteString) this.c[i]);
            } else if (tagWireType == 3) {
                codedOutputStream.writeTag(tagFieldNumber, 3);
                ((UnknownFieldSetLite) this.c[i]).writeTo(codedOutputStream);
                codedOutputStream.writeTag(tagFieldNumber, 4);
            } else if (tagWireType == 5) {
                codedOutputStream.writeFixed32(tagFieldNumber, ((Integer) this.c[i]).intValue());
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public UnknownFieldSetLite(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f10965a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }

    public void writeTo(Writer writer) throws IOException {
        if (this.f10965a == 0) {
            return;
        }
        if (writer.t() == Writer.FieldOrder.ASCENDING) {
            for (int i = 0; i < this.f10965a; i++) {
                p(this.b[i], this.c[i], writer);
            }
            return;
        }
        for (int i2 = this.f10965a - 1; i2 >= 0; i2--) {
            p(this.b[i2], this.c[i2], writer);
        }
    }
}
