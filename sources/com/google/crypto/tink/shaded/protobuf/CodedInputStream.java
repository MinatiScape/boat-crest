package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class CodedInputStream {

    /* renamed from: a  reason: collision with root package name */
    public int f10944a;
    public int b;
    public int c;
    public g d;

    /* loaded from: classes10.dex */
    public static final class b extends CodedInputStream {
        public final byte[] e;
        public final boolean f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public boolean l;
        public int m;

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.k != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public long d() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= (readRawByte & Byte.MAX_VALUE) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void e() {
            int i = this.g + this.h;
            this.g = i;
            int i2 = i - this.j;
            int i3 = this.m;
            if (i2 > i3) {
                int i4 = i2 - i3;
                this.h = i4;
                this.g = i - i4;
                return;
            }
            this.h = 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.l = z;
        }

        public final void f() throws IOException {
            if (this.g - this.i >= 10) {
                g();
            } else {
                h();
            }
        }

        public final void g() throws IOException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.e;
                int i2 = this.i;
                this.i = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.m;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.k;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.i - this.j;
        }

        public final void h() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.i == this.g;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.m = i;
            e();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.m;
                if (totalBytesRead <= i2) {
                    this.m = totalBytesRead;
                    e();
                    return i2;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            ByteBuffer wrap;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.g;
                int i2 = this.i;
                if (readRawVarint32 <= i - i2) {
                    if (!this.f && this.l) {
                        wrap = ByteBuffer.wrap(this.e, i2, readRawVarint32).slice();
                    } else {
                        wrap = ByteBuffer.wrap(Arrays.copyOfRange(this.e, i2, i2 + readRawVarint32));
                    }
                    this.i += readRawVarint32;
                    return wrap;
                }
            }
            if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            ByteString copyFrom;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.g;
                int i2 = this.i;
                if (readRawVarint32 <= i - i2) {
                    if (this.f && this.l) {
                        copyFrom = ByteString.wrap(this.e, i2, readRawVarint32);
                    } else {
                        copyFrom = ByteString.copyFrom(this.e, i2, readRawVarint32);
                    }
                    this.i += readRawVarint32;
                    return copyFrom;
                }
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            return ByteString.wrap(readRawBytes(readRawVarint32));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            int i = this.i;
            if (i != this.g) {
                byte[] bArr = this.e;
                this.i = i + 1;
                return bArr[i];
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws IOException {
            if (i > 0) {
                int i2 = this.g;
                int i3 = this.i;
                if (i <= i2 - i3) {
                    int i4 = i + i3;
                    this.i = i4;
                    return Arrays.copyOfRange(this.e, i3, i4);
                }
            }
            if (i <= 0) {
                if (i == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            int i = this.i;
            if (this.g - i >= 4) {
                byte[] bArr = this.e;
                this.i = i + 4;
                return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            int i = this.i;
            if (this.g - i >= 8) {
                byte[] bArr = this.e;
                this.i = i + 8;
                return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.i
                int r1 = r5.g
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.e
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.i = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.d()
                int r0 = (int) r0
                return r0
            L70:
                r5.i = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.CodedInputStream.b.readRawVarint32():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
            if (r2[r0] < 0) goto L42;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long readRawVarint64() throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 192
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.CodedInputStream.b.readRawVarint64():long");
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.g;
                int i2 = this.i;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.e, i2, readRawVarint32, Internal.f10957a);
                    this.i += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.g;
                int i2 = this.i;
                if (readRawVarint32 <= i - i2) {
                    String h = v0.h(this.e, i2, readRawVarint32);
                    this.i += readRawVarint32;
                    return h;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.k = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.k = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.k;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.j = this.i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                f();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.a(WireFormat.getTagFieldNumber(i), 4));
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws IOException {
            if (i >= 0) {
                int i2 = this.g;
                int i3 = this.i;
                if (i <= i2 - i3) {
                    this.i = i3 + i;
                    return;
                }
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public b(byte[] bArr, int i, int i2, boolean z) {
            super();
            this.m = Integer.MAX_VALUE;
            this.e = bArr;
            this.g = i2 + i;
            this.i = i;
            this.j = i;
            this.f = z;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int a2 = WireFormat.a(WireFormat.getTagFieldNumber(i), 4);
                checkLastTagWas(a2);
                codedOutputStream.writeRawVarint32(a2);
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends CodedInputStream {
        public Iterable<ByteBuffer> e;
        public Iterator<ByteBuffer> f;
        public ByteBuffer g;
        public boolean h;
        public boolean i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;
        public long p;
        public long q;
        public long r;
        public long s;

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.m != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public final long d() {
            return this.s - this.p;
        }

        public final void e() throws InvalidProtocolBufferException {
            if (this.f.hasNext()) {
                l();
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.i = z;
        }

        public final void f(byte[] bArr, int i, int i2) throws IOException {
            if (i2 < 0 || i2 > i()) {
                if (i2 > 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                if (i2 != 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                return;
            }
            int i3 = i2;
            while (i3 > 0) {
                if (d() == 0) {
                    e();
                }
                int min = Math.min(i3, (int) d());
                long j = min;
                u0.n(this.p, bArr, (i2 - i3) + i, j);
                i3 -= min;
                this.p += j;
            }
        }

        public long g() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= (readRawByte & Byte.MAX_VALUE) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.l;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.m;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int) (((this.n - this.o) + this.p) - this.q);
        }

        public final void h() {
            int i = this.j + this.k;
            this.j = i;
            int i2 = i - this.o;
            int i3 = this.l;
            if (i2 > i3) {
                int i4 = i2 - i3;
                this.k = i4;
                this.j = i - i4;
                return;
            }
            this.k = 0;
        }

        public final int i() {
            return (int) (((this.j - this.n) - this.p) + this.q);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return (((long) this.n) + this.p) - this.q == ((long) this.j);
        }

        public final void j() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final ByteBuffer k(int i, int i2) throws IOException {
            int position = this.g.position();
            int limit = this.g.limit();
            try {
                try {
                    this.g.position(i);
                    this.g.limit(i2);
                    return this.g.slice();
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } finally {
                this.g.position(position);
                this.g.limit(limit);
            }
        }

        public final void l() {
            ByteBuffer next = this.f.next();
            this.g = next;
            this.n += (int) (this.p - this.q);
            long position = next.position();
            this.p = position;
            this.q = position;
            this.s = this.g.limit();
            long i = u0.i(this.g);
            this.r = i;
            this.p += i;
            this.q += i;
            this.s += i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.l = i;
            h();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.l;
                if (totalBytesRead <= i2) {
                    this.l = totalBytesRead;
                    h();
                    return i2;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = readRawVarint32;
                if (j <= d()) {
                    if (!this.h && this.i) {
                        long j2 = this.p + j;
                        this.p = j2;
                        long j3 = this.r;
                        return k((int) ((j2 - j3) - j), (int) (j2 - j3));
                    }
                    byte[] bArr = new byte[readRawVarint32];
                    u0.n(this.p, bArr, 0L, j);
                    this.p += j;
                    return ByteBuffer.wrap(bArr);
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= i()) {
                byte[] bArr2 = new byte[readRawVarint32];
                f(bArr2, 0, readRawVarint32);
                return ByteBuffer.wrap(bArr2);
            } else if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = readRawVarint32;
                long j2 = this.s;
                long j3 = this.p;
                if (j <= j2 - j3) {
                    if (this.h && this.i) {
                        int i = (int) (j3 - this.r);
                        ByteString wrap = ByteString.wrap(k(i, readRawVarint32 + i));
                        this.p += j;
                        return wrap;
                    }
                    byte[] bArr = new byte[readRawVarint32];
                    u0.n(j3, bArr, 0L, j);
                    this.p += j;
                    return ByteString.wrap(bArr);
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= i()) {
                byte[] bArr2 = new byte[readRawVarint32];
                f(bArr2, 0, readRawVarint32);
                return ByteString.wrap(bArr2);
            } else if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            if (d() == 0) {
                e();
            }
            long j = this.p;
            this.p = 1 + j;
            return u0.v(j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws IOException {
            if (i >= 0) {
                long j = i;
                if (j <= d()) {
                    byte[] bArr = new byte[i];
                    u0.n(this.p, bArr, 0L, j);
                    this.p += j;
                    return bArr;
                }
            }
            if (i >= 0 && i <= i()) {
                byte[] bArr2 = new byte[i];
                f(bArr2, 0, i);
                return bArr2;
            } else if (i <= 0) {
                if (i == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            if (d() >= 4) {
                long j = this.p;
                this.p = 4 + j;
                return ((u0.v(j + 3) & 255) << 24) | (u0.v(j) & 255) | ((u0.v(1 + j) & 255) << 8) | ((u0.v(2 + j) & 255) << 16);
            }
            return (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            long readRawByte;
            byte readRawByte2;
            if (d() >= 8) {
                long j = this.p;
                this.p = 8 + j;
                readRawByte = (u0.v(j) & 255) | ((u0.v(1 + j) & 255) << 8) | ((u0.v(2 + j) & 255) << 16) | ((u0.v(3 + j) & 255) << 24) | ((u0.v(4 + j) & 255) << 32) | ((u0.v(5 + j) & 255) << 40) | ((u0.v(6 + j) & 255) << 48);
                readRawByte2 = u0.v(j + 7);
            } else {
                readRawByte = (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24) | ((readRawByte() & 255) << 32) | ((readRawByte() & 255) << 40) | ((readRawByte() & 255) << 48);
                readRawByte2 = readRawByte();
            }
            return ((readRawByte2 & 255) << 56) | readRawByte;
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0088, code lost:
            if (com.google.crypto.tink.shaded.protobuf.u0.v(r4) < 0) goto L34;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r10 = this;
                long r0 = r10.p
                long r2 = r10.s
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto La
                goto L8a
            La:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = com.google.crypto.tink.shaded.protobuf.u0.v(r0)
                if (r0 < 0) goto L1a
                long r4 = r10.p
                long r4 = r4 + r2
                r10.p = r4
                return r0
            L1a:
                long r6 = r10.s
                long r8 = r10.p
                long r6 = r6 - r8
                r8 = 10
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L26
                goto L8a
            L26:
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L34
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L90
            L34:
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L43
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L41:
                r6 = r4
                goto L90
            L43:
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L53
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L90
            L53:
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L41
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                if (r1 >= 0) goto L90
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                if (r1 >= 0) goto L41
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                if (r1 >= 0) goto L90
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                if (r1 >= 0) goto L41
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                if (r1 >= 0) goto L90
            L8a:
                long r0 = r10.g()
                int r0 = (int) r0
                return r0
            L90:
                r10.p = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.CodedInputStream.c.readRawVarint32():int");
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long v;
            long j;
            long j2;
            int i;
            long j3 = this.p;
            if (this.s != j3) {
                long j4 = j3 + 1;
                byte v2 = u0.v(j3);
                if (v2 >= 0) {
                    this.p++;
                    return v2;
                } else if (this.s - this.p >= 10) {
                    long j5 = j4 + 1;
                    int v3 = v2 ^ (u0.v(j4) << 7);
                    if (v3 >= 0) {
                        long j6 = j5 + 1;
                        int v4 = v3 ^ (u0.v(j5) << 14);
                        if (v4 >= 0) {
                            v = v4 ^ 16256;
                        } else {
                            j5 = j6 + 1;
                            int v5 = v4 ^ (u0.v(j6) << 21);
                            if (v5 < 0) {
                                i = v5 ^ (-2080896);
                            } else {
                                j6 = j5 + 1;
                                long v6 = v5 ^ (u0.v(j5) << 28);
                                if (v6 < 0) {
                                    long j7 = j6 + 1;
                                    long v7 = v6 ^ (u0.v(j6) << 35);
                                    if (v7 < 0) {
                                        j = -34093383808L;
                                    } else {
                                        j6 = j7 + 1;
                                        v6 = v7 ^ (u0.v(j7) << 42);
                                        if (v6 >= 0) {
                                            j2 = 4363953127296L;
                                        } else {
                                            j7 = j6 + 1;
                                            v7 = v6 ^ (u0.v(j6) << 49);
                                            if (v7 < 0) {
                                                j = -558586000294016L;
                                            } else {
                                                j6 = j7 + 1;
                                                v = (v7 ^ (u0.v(j7) << 56)) ^ 71499008037633920L;
                                                if (v < 0) {
                                                    long j8 = 1 + j6;
                                                    if (u0.v(j6) >= 0) {
                                                        j5 = j8;
                                                        this.p = j5;
                                                        return v;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    v = v7 ^ j;
                                    j5 = j7;
                                    this.p = j5;
                                    return v;
                                }
                                j2 = 266354560;
                                v = v6 ^ j2;
                            }
                        }
                        j5 = j6;
                        this.p = j5;
                        return v;
                    }
                    i = v3 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                    v = i;
                    this.p = j5;
                    return v;
                }
            }
            return g();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = readRawVarint32;
                long j2 = this.s;
                long j3 = this.p;
                if (j <= j2 - j3) {
                    byte[] bArr = new byte[readRawVarint32];
                    u0.n(j3, bArr, 0L, j);
                    String str = new String(bArr, Internal.f10957a);
                    this.p += j;
                    return str;
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= i()) {
                byte[] bArr2 = new byte[readRawVarint32];
                f(bArr2, 0, readRawVarint32);
                return new String(bArr2, Internal.f10957a);
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = readRawVarint32;
                long j2 = this.s;
                long j3 = this.p;
                if (j <= j2 - j3) {
                    String g = v0.g(this.g, (int) (j3 - this.q), readRawVarint32);
                    this.p += j;
                    return g;
                }
            }
            if (readRawVarint32 >= 0 && readRawVarint32 <= i()) {
                byte[] bArr = new byte[readRawVarint32];
                f(bArr, 0, readRawVarint32);
                return v0.h(bArr, 0, readRawVarint32);
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.m = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.m = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.m;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.o = (int) ((this.n + this.p) - this.q);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                j();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.a(WireFormat.getTagFieldNumber(i), 4));
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws IOException {
            if (i < 0 || i > ((this.j - this.n) - this.p) + this.q) {
                if (i < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            while (i > 0) {
                if (d() == 0) {
                    e();
                }
                int min = Math.min(i, (int) d());
                i -= min;
                this.p += min;
            }
        }

        public c(Iterable<ByteBuffer> iterable, int i, boolean z) {
            super();
            this.l = Integer.MAX_VALUE;
            this.j = i;
            this.e = iterable;
            this.f = iterable.iterator();
            this.h = z;
            this.n = 0;
            this.o = 0;
            if (i == 0) {
                this.g = Internal.EMPTY_BYTE_BUFFER;
                this.p = 0L;
                this.q = 0L;
                this.s = 0L;
                this.r = 0L;
                return;
            }
            l();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int a2 = WireFormat.a(WireFormat.getTagFieldNumber(i), 4);
                checkLastTagWas(a2);
                codedOutputStream.writeRawVarint32(a2);
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends CodedInputStream {
        public final InputStream e;
        public final byte[] f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public a m;

        /* loaded from: classes10.dex */
        public interface a {
            void a();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.j != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public final ByteString d(int i) throws IOException {
            byte[] f = f(i);
            if (f != null) {
                return ByteString.copyFrom(f);
            }
            int i2 = this.i;
            int i3 = this.g;
            int i4 = i3 - i2;
            this.k += i3;
            this.i = 0;
            this.g = 0;
            List<byte[]> g = g(i - i4);
            byte[] bArr = new byte[i];
            System.arraycopy(this.f, i2, bArr, 0, i4);
            for (byte[] bArr2 : g) {
                System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
                i4 += bArr2.length;
            }
            return ByteString.wrap(bArr);
        }

        public final byte[] e(int i, boolean z) throws IOException {
            byte[] f = f(i);
            if (f != null) {
                return z ? (byte[]) f.clone() : f;
            }
            int i2 = this.i;
            int i3 = this.g;
            int i4 = i3 - i2;
            this.k += i3;
            this.i = 0;
            this.g = 0;
            List<byte[]> g = g(i - i4);
            byte[] bArr = new byte[i];
            System.arraycopy(this.f, i2, bArr, 0, i4);
            for (byte[] bArr2 : g) {
                System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
                i4 += bArr2.length;
            }
            return bArr;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
        }

        public final byte[] f(int i) throws IOException {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i >= 0) {
                int i2 = this.k;
                int i3 = this.i;
                int i4 = i2 + i3 + i;
                if (i4 - this.c <= 0) {
                    int i5 = this.l;
                    if (i4 <= i5) {
                        int i6 = this.g - i3;
                        int i7 = i - i6;
                        if (i7 < 4096 || i7 <= this.e.available()) {
                            byte[] bArr = new byte[i];
                            System.arraycopy(this.f, this.i, bArr, 0, i6);
                            this.k += this.g;
                            this.i = 0;
                            this.g = 0;
                            while (i6 < i) {
                                int read = this.e.read(bArr, i6, i - i6);
                                if (read != -1) {
                                    this.k += read;
                                    i6 += read;
                                } else {
                                    throw InvalidProtocolBufferException.truncatedMessage();
                                }
                            }
                            return bArr;
                        }
                        return null;
                    }
                    skipRawBytes((i5 - i2) - i3);
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public final List<byte[]> g(int i) throws IOException {
            ArrayList arrayList = new ArrayList();
            while (i > 0) {
                int min = Math.min(i, 4096);
                byte[] bArr = new byte[min];
                int i2 = 0;
                while (i2 < min) {
                    int read = this.e.read(bArr, i2, min - i2);
                    if (read != -1) {
                        this.k += read;
                        i2 += read;
                    } else {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                }
                i -= min;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.l;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - (this.k + this.i);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.j;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.k + this.i;
        }

        public long h() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= (readRawByte & Byte.MAX_VALUE) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void i() {
            int i = this.g + this.h;
            this.g = i;
            int i2 = this.k + i;
            int i3 = this.l;
            if (i2 > i3) {
                int i4 = i2 - i3;
                this.h = i4;
                this.g = i - i4;
                return;
            }
            this.h = 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.i == this.g && !o(1);
        }

        public final void j(int i) throws IOException {
            if (o(i)) {
                return;
            }
            if (i > (this.c - this.k) - this.i) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public final void k(int i) throws IOException {
            if (i >= 0) {
                int i2 = this.k;
                int i3 = this.i;
                int i4 = i2 + i3 + i;
                int i5 = this.l;
                if (i4 <= i5) {
                    int i6 = 0;
                    if (this.m == null) {
                        this.k = i2 + i3;
                        this.g = 0;
                        this.i = 0;
                        i6 = this.g - i3;
                        while (i6 < i) {
                            try {
                                long j = i - i6;
                                long skip = this.e.skip(j);
                                int i7 = (skip > 0L ? 1 : (skip == 0L ? 0 : -1));
                                if (i7 < 0 || skip > j) {
                                    throw new IllegalStateException(this.e.getClass() + "#skip returned invalid result: " + skip + "\nThe InputStream implementation is buggy.");
                                } else if (i7 == 0) {
                                    break;
                                } else {
                                    i6 += (int) skip;
                                }
                            } finally {
                                this.k += i6;
                                i();
                            }
                        }
                    }
                    if (i6 >= i) {
                        return;
                    }
                    int i8 = this.g;
                    int i9 = i8 - this.i;
                    this.i = i8;
                    j(1);
                    while (true) {
                        int i10 = i - i9;
                        int i11 = this.g;
                        if (i10 > i11) {
                            i9 += i11;
                            this.i = i11;
                            j(1);
                        } else {
                            this.i = i10;
                            return;
                        }
                    }
                } else {
                    skipRawBytes((i5 - i2) - i3);
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public final void l() throws IOException {
            if (this.g - this.i >= 10) {
                m();
            } else {
                n();
            }
        }

        public final void m() throws IOException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.f;
                int i2 = this.i;
                this.i = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void n() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final boolean o(int i) throws IOException {
            int i2 = this.i;
            if (i2 + i > this.g) {
                int i3 = this.c;
                int i4 = this.k;
                if (i <= (i3 - i4) - i2 && i4 + i2 + i <= this.l) {
                    a aVar = this.m;
                    if (aVar != null) {
                        aVar.a();
                    }
                    int i5 = this.i;
                    if (i5 > 0) {
                        int i6 = this.g;
                        if (i6 > i5) {
                            byte[] bArr = this.f;
                            System.arraycopy(bArr, i5, bArr, 0, i6 - i5);
                        }
                        this.k += i5;
                        this.g -= i5;
                        this.i = 0;
                    }
                    InputStream inputStream = this.e;
                    byte[] bArr2 = this.f;
                    int i7 = this.g;
                    int read = inputStream.read(bArr2, i7, Math.min(bArr2.length - i7, (this.c - this.k) - i7));
                    if (read == 0 || read < -1 || read > this.f.length) {
                        throw new IllegalStateException(this.e.getClass() + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                    } else if (read > 0) {
                        this.g += read;
                        i();
                        if (this.g >= i) {
                            return true;
                        }
                        return o(i);
                    } else {
                        return false;
                    }
                }
                return false;
            }
            throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.l = i;
            i();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int i2 = i + this.k + this.i;
                int i3 = this.l;
                if (i2 <= i3) {
                    this.l = i2;
                    i();
                    return i3;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.g;
            int i2 = this.i;
            if (readRawVarint32 <= i - i2 && readRawVarint32 > 0) {
                byte[] copyOfRange = Arrays.copyOfRange(this.f, i2, i2 + readRawVarint32);
                this.i += readRawVarint32;
                return copyOfRange;
            }
            return e(readRawVarint32, false);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.g;
            int i2 = this.i;
            if (readRawVarint32 > i - i2 || readRawVarint32 <= 0) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                return ByteBuffer.wrap(e(readRawVarint32, true));
            }
            ByteBuffer wrap = ByteBuffer.wrap(Arrays.copyOfRange(this.f, i2, i2 + readRawVarint32));
            this.i += readRawVarint32;
            return wrap;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.g;
            int i2 = this.i;
            if (readRawVarint32 > i - i2 || readRawVarint32 <= 0) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                return d(readRawVarint32);
            }
            ByteString copyFrom = ByteString.copyFrom(this.f, i2, readRawVarint32);
            this.i += readRawVarint32;
            return copyFrom;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            if (this.i == this.g) {
                j(1);
            }
            byte[] bArr = this.f;
            int i = this.i;
            this.i = i + 1;
            return bArr[i];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws IOException {
            int i2 = this.i;
            if (i <= this.g - i2 && i > 0) {
                int i3 = i + i2;
                this.i = i3;
                return Arrays.copyOfRange(this.f, i2, i3);
            }
            return e(i, false);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            int i = this.i;
            if (this.g - i < 4) {
                j(4);
                i = this.i;
            }
            byte[] bArr = this.f;
            this.i = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            int i = this.i;
            if (this.g - i < 8) {
                j(8);
                i = this.i;
            }
            byte[] bArr = this.f;
            this.i = i + 8;
            return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.i
                int r1 = r5.g
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.f
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.i = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.h()
                int r0 = (int) r0
                return r0
            L70:
                r5.i = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.CodedInputStream.d.readRawVarint32():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
            if (r2[r0] < 0) goto L42;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long readRawVarint64() throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 192
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.CodedInputStream.d.readRawVarint64():long");
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.g;
                int i2 = this.i;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.f, i2, readRawVarint32, Internal.f10957a);
                    this.i += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= this.g) {
                j(readRawVarint32);
                String str2 = new String(this.f, this.i, readRawVarint32, Internal.f10957a);
                this.i += readRawVarint32;
                return str2;
            }
            return new String(e(readRawVarint32, false), Internal.f10957a);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            byte[] e;
            int readRawVarint32 = readRawVarint32();
            int i = this.i;
            int i2 = this.g;
            if (readRawVarint32 <= i2 - i && readRawVarint32 > 0) {
                e = this.f;
                this.i = i + readRawVarint32;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= i2) {
                    j(readRawVarint32);
                    e = this.f;
                    this.i = readRawVarint32 + 0;
                } else {
                    e = e(readRawVarint32, false);
                }
                i = 0;
            }
            return v0.h(e, i, readRawVarint32);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.j = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.j = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.j;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.k = -this.i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                l();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.a(WireFormat.getTagFieldNumber(i), 4));
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws IOException {
            int i2 = this.g;
            int i3 = this.i;
            if (i <= i2 - i3 && i >= 0) {
                this.i = i3 + i;
            } else {
                k(i);
            }
        }

        public d(InputStream inputStream, int i) {
            super();
            this.l = Integer.MAX_VALUE;
            this.m = null;
            Internal.b(inputStream, "input");
            this.e = inputStream;
            this.f = new byte[i];
            this.g = 0;
            this.i = 0;
            this.k = 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int a2 = WireFormat.a(WireFormat.getTagFieldNumber(i), 4);
                checkLastTagWas(a2);
                codedOutputStream.writeRawVarint32(a2);
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class e extends CodedInputStream {
        public final ByteBuffer e;
        public final boolean f;
        public final long g;
        public long h;
        public long i;
        public long j;
        public int k;
        public int l;
        public boolean m;
        public int n;

        public static boolean e() {
            return u0.I();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.l != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public final int d(long j) {
            return (int) (j - this.g);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.m = z;
        }

        public long f() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= (readRawByte & Byte.MAX_VALUE) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void g() {
            long j = this.h + this.k;
            this.h = j;
            int i = (int) (j - this.j);
            int i2 = this.n;
            if (i > i2) {
                int i3 = i - i2;
                this.k = i3;
                this.h = j - i3;
                return;
            }
            this.k = 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.n;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.l;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int) (this.i - this.j);
        }

        public final int h() {
            return (int) (this.h - this.i);
        }

        public final void i() throws IOException {
            if (h() >= 10) {
                j();
            } else {
                k();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.i == this.h;
        }

        public final void j() throws IOException {
            for (int i = 0; i < 10; i++) {
                long j = this.i;
                this.i = 1 + j;
                if (u0.v(j) >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final void k() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public final ByteBuffer l(long j, long j2) throws IOException {
            int position = this.e.position();
            int limit = this.e.limit();
            try {
                try {
                    this.e.position(d(j));
                    this.e.limit(d(j2));
                    return this.e.slice();
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } finally {
                this.e.position(position);
                this.e.limit(limit);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.n = i;
            g();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.n;
                if (totalBytesRead <= i2) {
                    this.n = totalBytesRead;
                    g();
                    return i2;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > h()) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (!this.f && this.m) {
                long j = this.i;
                long j2 = readRawVarint32;
                ByteBuffer l = l(j, j + j2);
                this.i += j2;
                return l;
            } else {
                byte[] bArr = new byte[readRawVarint32];
                long j3 = readRawVarint32;
                u0.n(this.i, bArr, 0L, j3);
                this.i += j3;
                return ByteBuffer.wrap(bArr);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > h()) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.f && this.m) {
                long j = this.i;
                long j2 = readRawVarint32;
                ByteBuffer l = l(j, j + j2);
                this.i += j2;
                return ByteString.wrap(l);
            } else {
                byte[] bArr = new byte[readRawVarint32];
                long j3 = readRawVarint32;
                u0.n(this.i, bArr, 0L, j3);
                this.i += j3;
                return ByteString.wrap(bArr);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                builder.mergeFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            long j = this.i;
            if (j != this.h) {
                this.i = 1 + j;
                return u0.v(j);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws IOException {
            if (i < 0 || i > h()) {
                if (i <= 0) {
                    if (i == 0) {
                        return Internal.EMPTY_BYTE_ARRAY;
                    }
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = new byte[i];
            long j = this.i;
            long j2 = i;
            l(j, j + j2).get(bArr);
            this.i += j2;
            return bArr;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            long j = this.i;
            if (this.h - j >= 4) {
                this.i = 4 + j;
                return ((u0.v(j + 3) & 255) << 24) | (u0.v(j) & 255) | ((u0.v(1 + j) & 255) << 8) | ((u0.v(2 + j) & 255) << 16);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            long j = this.i;
            if (this.h - j >= 8) {
                this.i = 8 + j;
                return ((u0.v(j + 7) & 255) << 56) | (u0.v(j) & 255) | ((u0.v(1 + j) & 255) << 8) | ((u0.v(2 + j) & 255) << 16) | ((u0.v(3 + j) & 255) << 24) | ((u0.v(4 + j) & 255) << 32) | ((u0.v(5 + j) & 255) << 40) | ((u0.v(6 + j) & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
            if (com.google.crypto.tink.shaded.protobuf.u0.v(r4) < 0) goto L34;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r10 = this;
                long r0 = r10.i
                long r2 = r10.h
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto La
                goto L85
            La:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = com.google.crypto.tink.shaded.protobuf.u0.v(r0)
                if (r0 < 0) goto L17
                r10.i = r4
                return r0
            L17:
                long r6 = r10.h
                long r6 = r6 - r4
                r8 = 9
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L21
                goto L85
            L21:
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L2f
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L8b
            L2f:
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3e
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L3c:
                r6 = r4
                goto L8b
            L3e:
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L4e
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L8b
            L4e:
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.u0.v(r4)
                if (r1 >= 0) goto L8b
            L85:
                long r0 = r10.f()
                int r0 = (int) r0
                return r0
            L8b:
                r10.i = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.CodedInputStream.e.readRawVarint32():int");
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long v;
            long j;
            long j2;
            int i;
            long j3 = this.i;
            if (this.h != j3) {
                long j4 = j3 + 1;
                byte v2 = u0.v(j3);
                if (v2 >= 0) {
                    this.i = j4;
                    return v2;
                } else if (this.h - j4 >= 9) {
                    long j5 = j4 + 1;
                    int v3 = v2 ^ (u0.v(j4) << 7);
                    if (v3 >= 0) {
                        long j6 = j5 + 1;
                        int v4 = v3 ^ (u0.v(j5) << 14);
                        if (v4 >= 0) {
                            v = v4 ^ 16256;
                        } else {
                            j5 = j6 + 1;
                            int v5 = v4 ^ (u0.v(j6) << 21);
                            if (v5 < 0) {
                                i = v5 ^ (-2080896);
                            } else {
                                j6 = j5 + 1;
                                long v6 = v5 ^ (u0.v(j5) << 28);
                                if (v6 < 0) {
                                    long j7 = j6 + 1;
                                    long v7 = v6 ^ (u0.v(j6) << 35);
                                    if (v7 < 0) {
                                        j = -34093383808L;
                                    } else {
                                        j6 = j7 + 1;
                                        v6 = v7 ^ (u0.v(j7) << 42);
                                        if (v6 >= 0) {
                                            j2 = 4363953127296L;
                                        } else {
                                            j7 = j6 + 1;
                                            v7 = v6 ^ (u0.v(j6) << 49);
                                            if (v7 < 0) {
                                                j = -558586000294016L;
                                            } else {
                                                j6 = j7 + 1;
                                                v = (v7 ^ (u0.v(j7) << 56)) ^ 71499008037633920L;
                                                if (v < 0) {
                                                    long j8 = 1 + j6;
                                                    if (u0.v(j6) >= 0) {
                                                        j5 = j8;
                                                        this.i = j5;
                                                        return v;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    v = v7 ^ j;
                                    j5 = j7;
                                    this.i = j5;
                                    return v;
                                }
                                j2 = 266354560;
                                v = v6 ^ j2;
                            }
                        }
                        j5 = j6;
                        this.i = j5;
                        return v;
                    }
                    i = v3 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                    v = i;
                    this.i = j5;
                    return v;
                }
            }
            return f();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > h()) {
                if (readRawVarint32 == 0) {
                    return "";
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = new byte[readRawVarint32];
            long j = readRawVarint32;
            u0.n(this.i, bArr, 0L, j);
            String str = new String(bArr, Internal.f10957a);
            this.i += j;
            return str;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= h()) {
                String g = v0.g(this.e, d(this.i), readRawVarint32);
                this.i += readRawVarint32;
                return g;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.l = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.l = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.l;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.j = this.i;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                i();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.a(WireFormat.getTagFieldNumber(i), 4));
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws IOException {
            if (i >= 0 && i <= h()) {
                this.i += i;
            } else if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public e(ByteBuffer byteBuffer, boolean z) {
            super();
            this.n = Integer.MAX_VALUE;
            this.e = byteBuffer;
            long i = u0.i(byteBuffer);
            this.g = i;
            this.h = byteBuffer.limit() + i;
            long position = i + byteBuffer.position();
            this.i = position;
            this.j = position;
            this.f = z;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i2 = this.f10944a;
            if (i2 < this.b) {
                this.f10944a = i2 + 1;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(WireFormat.a(i, 4));
                this.f10944a--;
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.f10944a < this.b) {
                int pushLimit = pushLimit(readRawVarint32);
                this.f10944a++;
                T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.f10944a--;
                popLimit(pushLimit);
                return parsePartialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int a2 = WireFormat.a(WireFormat.getTagFieldNumber(i), 4);
                checkLastTagWas(a2);
                codedOutputStream.writeRawVarint32(a2);
                return true;
            } else if (tagWireType != 4) {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            } else {
                return false;
            }
        }
    }

    public static CodedInputStream a(Iterable<ByteBuffer> iterable, boolean z) {
        boolean z2 = false;
        int i = 0;
        for (ByteBuffer byteBuffer : iterable) {
            i += byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                z2 |= true;
            } else {
                z2 = byteBuffer.isDirect() ? z2 | true : z2 | true;
            }
        }
        if (z2) {
            return new c(iterable, i, z);
        }
        return newInstance(new q(iterable));
    }

    public static CodedInputStream b(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return c(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && e.e()) {
            return new e(byteBuffer, z);
        }
        int remaining = byteBuffer.remaining();
        byte[] bArr = new byte[remaining];
        byteBuffer.duplicate().get(bArr);
        return c(bArr, 0, remaining, true);
    }

    public static CodedInputStream c(byte[] bArr, int i, int i2, boolean z) {
        b bVar = new b(bArr, i, i2, z);
        try {
            bVar.pushLimit(i2);
            return bVar;
        } catch (InvalidProtocolBufferException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, 4096);
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            i2 |= (read & 127) << i3;
            if ((read & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if ((read2 & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public abstract void checkLastTagWas(int i) throws InvalidProtocolBufferException;

    public abstract void enableAliasing(boolean z);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd() throws IOException;

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i) throws InvalidProtocolBufferException;

    public abstract boolean readBool() throws IOException;

    public abstract byte[] readByteArray() throws IOException;

    public abstract ByteBuffer readByteBuffer() throws IOException;

    public abstract ByteString readBytes() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract int readEnum() throws IOException;

    public abstract int readFixed32() throws IOException;

    public abstract long readFixed64() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract int readInt32() throws IOException;

    public abstract long readInt64() throws IOException;

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract byte readRawByte() throws IOException;

    public abstract byte[] readRawBytes(int i) throws IOException;

    public abstract int readRawLittleEndian32() throws IOException;

    public abstract long readRawLittleEndian64() throws IOException;

    public abstract int readRawVarint32() throws IOException;

    public abstract long readRawVarint64() throws IOException;

    public abstract int readSFixed32() throws IOException;

    public abstract long readSFixed64() throws IOException;

    public abstract int readSInt32() throws IOException;

    public abstract long readSInt64() throws IOException;

    public abstract String readString() throws IOException;

    public abstract String readStringRequireUtf8() throws IOException;

    public abstract int readTag() throws IOException;

    public abstract int readUInt32() throws IOException;

    public abstract long readUInt64() throws IOException;

    @Deprecated
    public abstract void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException;

    public abstract void resetSizeCounter();

    public final int setRecursionLimit(int i) {
        if (i >= 0) {
            int i2 = this.b;
            this.b = i;
            return i2;
        }
        throw new IllegalArgumentException("Recursion limit cannot be negative: " + i);
    }

    public final int setSizeLimit(int i) {
        if (i >= 0) {
            int i2 = this.c;
            this.c = i;
            return i2;
        }
        throw new IllegalArgumentException("Size limit cannot be negative: " + i);
    }

    public abstract boolean skipField(int i) throws IOException;

    @Deprecated
    public abstract boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipMessage() throws IOException;

    public abstract void skipMessage(CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipRawBytes(int i) throws IOException;

    public CodedInputStream() {
        this.b = 100;
        this.c = Integer.MAX_VALUE;
    }

    public static CodedInputStream newInstance(InputStream inputStream, int i) {
        if (i > 0) {
            if (inputStream == null) {
                return newInstance(Internal.EMPTY_BYTE_ARRAY);
            }
            return new d(inputStream, i);
        }
        throw new IllegalArgumentException("bufferSize must be > 0");
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable) {
        if (!e.e()) {
            return newInstance(new q(iterable));
        }
        return a(iterable, false);
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return c(bArr, i, i2, false);
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return b(byteBuffer, false);
    }
}
