package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes10.dex */
public final class AesCtrHmacStreaming extends i {

    /* renamed from: a  reason: collision with root package name */
    public final int f11010a;
    public final String b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final String g;
    public final byte[] h;

    /* loaded from: classes10.dex */
    public class a implements StreamSegmentDecrypter {

        /* renamed from: a  reason: collision with root package name */
        public SecretKeySpec f11011a;
        public SecretKeySpec b;
        public Cipher c;
        public Mac d;
        public byte[] e;

        public a() {
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void decryptSegment(ByteBuffer byteBuffer, int i, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            int position = byteBuffer.position();
            byte[] p = AesCtrHmacStreaming.this.p(this.e, i, z);
            int remaining = byteBuffer.remaining();
            if (remaining >= AesCtrHmacStreaming.this.c) {
                int i2 = position + (remaining - AesCtrHmacStreaming.this.c);
                ByteBuffer duplicate = byteBuffer.duplicate();
                duplicate.limit(i2);
                ByteBuffer duplicate2 = byteBuffer.duplicate();
                duplicate2.position(i2);
                this.d.init(this.b);
                this.d.update(p);
                this.d.update(duplicate);
                byte[] copyOf = Arrays.copyOf(this.d.doFinal(), AesCtrHmacStreaming.this.c);
                byte[] bArr = new byte[AesCtrHmacStreaming.this.c];
                duplicate2.get(bArr);
                if (Bytes.equal(bArr, copyOf)) {
                    byteBuffer.limit(i2);
                    this.c.init(1, this.f11011a, new IvParameterSpec(p));
                    this.c.doFinal(byteBuffer, byteBuffer2);
                } else {
                    throw new GeneralSecurityException("Tag mismatch");
                }
            } else {
                throw new GeneralSecurityException("Ciphertext too short");
            }
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void init(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
            if (byteBuffer.remaining() == AesCtrHmacStreaming.this.getHeaderLength()) {
                if (byteBuffer.get() == AesCtrHmacStreaming.this.getHeaderLength()) {
                    this.e = new byte[7];
                    byte[] bArr2 = new byte[AesCtrHmacStreaming.this.f11010a];
                    byteBuffer.get(bArr2);
                    byteBuffer.get(this.e);
                    byte[] m = AesCtrHmacStreaming.this.m(bArr2, bArr);
                    this.f11011a = AesCtrHmacStreaming.this.n(m);
                    this.b = AesCtrHmacStreaming.this.l(m);
                    this.c = AesCtrHmacStreaming.a();
                    this.d = AesCtrHmacStreaming.this.o();
                } else {
                    throw new GeneralSecurityException("Invalid ciphertext");
                }
            } else {
                throw new InvalidAlgorithmParameterException("Invalid header length");
            }
        }
    }

    public AesCtrHmacStreaming(byte[] bArr, String str, int i, String str2, int i2, int i3, int i4) throws InvalidAlgorithmParameterException {
        s(bArr.length, i, str2, i2, i3, i4);
        this.h = Arrays.copyOf(bArr, bArr.length);
        this.g = str;
        this.f11010a = i;
        this.b = str2;
        this.c = i2;
        this.d = i3;
        this.f = i4;
        this.e = i3 - i2;
    }

    public static /* synthetic */ Cipher a() throws GeneralSecurityException {
        return k();
    }

    public static Cipher k() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
    }

    public static void s(int i, int i2, String str, int i3, int i4, int i5) throws InvalidAlgorithmParameterException {
        if (i >= 16 && i >= i2) {
            Validators.validateAesKeySize(i2);
            if (i3 >= 10) {
                if ((str.equals("HmacSha1") && i3 > 20) || ((str.equals("HmacSha256") && i3 > 32) || (str.equals("HmacSha512") && i3 > 64))) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                if (((((i4 - i5) - i3) - i2) - 7) - 1 <= 0) {
                    throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
                }
                return;
            }
            throw new InvalidAlgorithmParameterException("tag size too small " + i3);
        }
        throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, i2));
    }

    public long expectedCiphertextSize(long j) {
        long ciphertextOffset = j + getCiphertextOffset();
        int i = this.e;
        long j2 = (ciphertextOffset / i) * this.d;
        long j3 = ciphertextOffset % i;
        return j3 > 0 ? j2 + j3 + this.c : j2;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getCiphertextOffset() {
        return getHeaderLength() + this.f;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getCiphertextOverhead() {
        return this.c;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getCiphertextSegmentSize() {
        return this.d;
    }

    public int getFirstSegmentOffset() {
        return this.f;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getHeaderLength() {
        return this.f11010a + 1 + 7;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getPlaintextSegmentSize() {
        return this.e;
    }

    public final SecretKeySpec l(byte[] bArr) throws GeneralSecurityException {
        return new SecretKeySpec(bArr, this.f11010a, 32, this.b);
    }

    public final byte[] m(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return Hkdf.computeHkdf(this.g, this.h, bArr, bArr2, this.f11010a + 32);
    }

    public final SecretKeySpec n(byte[] bArr) throws GeneralSecurityException {
        return new SecretKeySpec(bArr, 0, this.f11010a, AesKey.ALGORITHM);
    }

    @Override // com.google.crypto.tink.subtle.i, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newDecryptingChannel(readableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.subtle.i, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ InputStream newDecryptingStream(InputStream inputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newDecryptingStream(inputStream, bArr);
    }

    @Override // com.google.crypto.tink.subtle.i, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newEncryptingChannel(writableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.subtle.i, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ OutputStream newEncryptingStream(OutputStream outputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newEncryptingStream(outputStream, bArr);
    }

    @Override // com.google.crypto.tink.subtle.i, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newSeekableDecryptingChannel(seekableByteChannel, bArr);
    }

    public final Mac o() throws GeneralSecurityException {
        return EngineFactory.MAC.getInstance(this.b);
    }

    public final byte[] p(byte[] bArr, long j, boolean z) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.put(bArr);
        SubtleUtil.putAsUnsigedInt(allocate, j);
        allocate.put(z ? (byte) 1 : (byte) 0);
        allocate.putInt(0);
        return allocate.array();
    }

    public final byte[] q() {
        return Random.randBytes(7);
    }

    public final byte[] r() {
        return Random.randBytes(this.f11010a);
    }

    @Override // com.google.crypto.tink.subtle.i
    public a newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new a();
    }

    @Override // com.google.crypto.tink.subtle.i
    public b newStreamSegmentEncrypter(byte[] bArr) throws GeneralSecurityException {
        return new b(bArr);
    }

    /* loaded from: classes10.dex */
    public class b implements StreamSegmentEncrypter {

        /* renamed from: a  reason: collision with root package name */
        public final SecretKeySpec f11012a;
        public final SecretKeySpec b;
        public final Cipher c = AesCtrHmacStreaming.a();
        public final Mac d;
        public final byte[] e;
        public ByteBuffer f;
        public long g;

        public b(byte[] bArr) throws GeneralSecurityException {
            this.g = 0L;
            this.d = AesCtrHmacStreaming.this.o();
            this.g = 0L;
            byte[] r = AesCtrHmacStreaming.this.r();
            byte[] q = AesCtrHmacStreaming.this.q();
            this.e = q;
            ByteBuffer allocate = ByteBuffer.allocate(AesCtrHmacStreaming.this.getHeaderLength());
            this.f = allocate;
            allocate.put((byte) AesCtrHmacStreaming.this.getHeaderLength());
            this.f.put(r);
            this.f.put(q);
            this.f.flip();
            byte[] m = AesCtrHmacStreaming.this.m(r, bArr);
            this.f11012a = AesCtrHmacStreaming.this.n(m);
            this.b = AesCtrHmacStreaming.this.l(m);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer byteBuffer, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            int position = byteBuffer2.position();
            byte[] p = AesCtrHmacStreaming.this.p(this.e, this.g, z);
            this.c.init(1, this.f11012a, new IvParameterSpec(p));
            this.g++;
            this.c.doFinal(byteBuffer, byteBuffer2);
            ByteBuffer duplicate = byteBuffer2.duplicate();
            duplicate.flip();
            duplicate.position(position);
            this.d.init(this.b);
            this.d.update(p);
            this.d.update(duplicate);
            byteBuffer2.put(this.d.doFinal(), 0, AesCtrHmacStreaming.this.c);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public ByteBuffer getHeader() {
            return this.f.asReadOnlyBuffer();
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z, ByteBuffer byteBuffer3) throws GeneralSecurityException {
            int position = byteBuffer3.position();
            byte[] p = AesCtrHmacStreaming.this.p(this.e, this.g, z);
            this.c.init(1, this.f11012a, new IvParameterSpec(p));
            this.g++;
            this.c.update(byteBuffer, byteBuffer3);
            this.c.doFinal(byteBuffer2, byteBuffer3);
            ByteBuffer duplicate = byteBuffer3.duplicate();
            duplicate.flip();
            duplicate.position(position);
            this.d.init(this.b);
            this.d.update(p);
            this.d.update(duplicate);
            byteBuffer3.put(this.d.doFinal(), 0, AesCtrHmacStreaming.this.c);
        }
    }
}
