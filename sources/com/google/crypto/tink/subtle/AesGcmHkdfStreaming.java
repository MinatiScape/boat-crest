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
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.jwe.SimpleAeadCipher;
import org.jose4j.keys.AesKey;
/* loaded from: classes10.dex */
public final class AesGcmHkdfStreaming extends i {

    /* renamed from: a  reason: collision with root package name */
    public final int f11015a;
    public final int b;
    public final int c;
    public final int d;
    public final String e;
    public final byte[] f;

    /* loaded from: classes10.dex */
    public class a implements StreamSegmentDecrypter {

        /* renamed from: a  reason: collision with root package name */
        public SecretKeySpec f11016a;
        public Cipher b;
        public byte[] c;

        public a() {
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void decryptSegment(ByteBuffer byteBuffer, int i, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            this.b.init(2, this.f11016a, AesGcmHkdfStreaming.i(this.c, i, z));
            this.b.doFinal(byteBuffer, byteBuffer2);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void init(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
            if (byteBuffer.remaining() == AesGcmHkdfStreaming.this.getHeaderLength()) {
                if (byteBuffer.get() == AesGcmHkdfStreaming.this.getHeaderLength()) {
                    this.c = new byte[7];
                    byte[] bArr2 = new byte[AesGcmHkdfStreaming.this.f11015a];
                    byteBuffer.get(bArr2);
                    byteBuffer.get(this.c);
                    this.f11016a = AesGcmHkdfStreaming.this.h(bArr2, bArr);
                    this.b = AesGcmHkdfStreaming.a();
                } else {
                    throw new GeneralSecurityException("Invalid ciphertext");
                }
            } else {
                throw new InvalidAlgorithmParameterException("Invalid header length");
            }
        }
    }

    public AesGcmHkdfStreaming(byte[] bArr, String str, int i, int i2, int i3) throws InvalidAlgorithmParameterException {
        if (bArr.length >= 16 && bArr.length >= i) {
            Validators.validateAesKeySize(i);
            if (i2 > getHeaderLength() + i3 + 16) {
                this.f = Arrays.copyOf(bArr, bArr.length);
                this.e = str;
                this.f11015a = i;
                this.b = i2;
                this.d = i3;
                this.c = i2 - 16;
                return;
            }
            throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
        }
        throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, i));
    }

    public static /* synthetic */ Cipher a() throws GeneralSecurityException {
        return g();
    }

    public static /* synthetic */ byte[] c() {
        return j();
    }

    public static Cipher g() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance(SimpleAeadCipher.GCM_TRANSFORMATION_NAME);
    }

    public static GCMParameterSpec i(byte[] bArr, long j, boolean z) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(12);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.put(bArr);
        SubtleUtil.putAsUnsigedInt(allocate, j);
        allocate.put(z ? (byte) 1 : (byte) 0);
        return new GCMParameterSpec(128, allocate.array());
    }

    public static byte[] j() {
        return Random.randBytes(7);
    }

    public long expectedCiphertextSize(long j) {
        long ciphertextOffset = j + getCiphertextOffset();
        int i = this.c;
        long j2 = (ciphertextOffset / i) * this.b;
        long j3 = ciphertextOffset % i;
        return j3 > 0 ? j2 + j3 + 16 : j2;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getCiphertextOffset() {
        return getHeaderLength() + this.d;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getCiphertextOverhead() {
        return 16;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getCiphertextSegmentSize() {
        return this.b;
    }

    public int getFirstSegmentOffset() {
        return this.d;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getHeaderLength() {
        return this.f11015a + 1 + 7;
    }

    @Override // com.google.crypto.tink.subtle.i
    public int getPlaintextSegmentSize() {
        return this.c;
    }

    public final SecretKeySpec h(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return new SecretKeySpec(Hkdf.computeHkdf(this.e, this.f, bArr, bArr2, this.f11015a), AesKey.ALGORITHM);
    }

    public final byte[] k() {
        return Random.randBytes(this.f11015a);
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

    @Override // com.google.crypto.tink.subtle.i
    public a newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new a();
    }

    @Override // com.google.crypto.tink.subtle.i
    public b newStreamSegmentEncrypter(byte[] bArr) throws GeneralSecurityException {
        return new b(this, bArr);
    }

    /* loaded from: classes10.dex */
    public class b implements StreamSegmentEncrypter {

        /* renamed from: a  reason: collision with root package name */
        public final SecretKeySpec f11017a;
        public final Cipher b = AesGcmHkdfStreaming.a();
        public final byte[] c;
        public final ByteBuffer d;
        public long e;

        public b(AesGcmHkdfStreaming aesGcmHkdfStreaming, byte[] bArr) throws GeneralSecurityException {
            this.e = 0L;
            this.e = 0L;
            byte[] k = aesGcmHkdfStreaming.k();
            byte[] c = AesGcmHkdfStreaming.c();
            this.c = c;
            ByteBuffer allocate = ByteBuffer.allocate(aesGcmHkdfStreaming.getHeaderLength());
            this.d = allocate;
            allocate.put((byte) aesGcmHkdfStreaming.getHeaderLength());
            allocate.put(k);
            allocate.put(c);
            allocate.flip();
            this.f11017a = aesGcmHkdfStreaming.h(k, bArr);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer byteBuffer, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            this.b.init(1, this.f11017a, AesGcmHkdfStreaming.i(this.c, this.e, z));
            this.e++;
            this.b.doFinal(byteBuffer, byteBuffer2);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public ByteBuffer getHeader() {
            return this.d.asReadOnlyBuffer();
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z, ByteBuffer byteBuffer3) throws GeneralSecurityException {
            this.b.init(1, this.f11017a, AesGcmHkdfStreaming.i(this.c, this.e, z));
            this.e++;
            if (byteBuffer2.hasRemaining()) {
                this.b.update(byteBuffer, byteBuffer3);
                this.b.doFinal(byteBuffer2, byteBuffer3);
            } else {
                this.b.doFinal(byteBuffer, byteBuffer3);
            }
        }
    }
}
