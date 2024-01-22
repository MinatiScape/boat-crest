package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* loaded from: classes10.dex */
public abstract class c implements IndCpaCipher {
    public static final int[] c = k(new byte[]{101, 120, com.htsmart.wristband2.a.a.a.J1, 97, 110, 100, 32, 51, 50, 45, 98, com.htsmart.wristband2.a.a.a.U1, 116, 101, 32, 107});

    /* renamed from: a  reason: collision with root package name */
    public int[] f11050a;
    public final int b;

    public c(byte[] bArr, int i) throws InvalidKeyException {
        if (bArr.length == 32) {
            this.f11050a = k(bArr);
            this.b = i;
            return;
        }
        throw new InvalidKeyException("The key length in bytes must be 32.");
    }

    public static void g(int[] iArr, int i, int i2, int i3, int i4) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = h(iArr[i4] ^ iArr[i], 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = h(iArr[i2] ^ iArr[i3], 12);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = h(iArr[i] ^ iArr[i4], 8);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = h(iArr[i2] ^ iArr[i3], 7);
    }

    public static int h(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public static void i(int[] iArr, int[] iArr2) {
        int[] iArr3 = c;
        System.arraycopy(iArr3, 0, iArr, 0, iArr3.length);
        System.arraycopy(iArr2, 0, iArr, iArr3.length, 8);
    }

    public static void j(int[] iArr) {
        for (int i = 0; i < 10; i++) {
            g(iArr, 0, 4, 8, 12);
            g(iArr, 1, 5, 9, 13);
            g(iArr, 2, 6, 10, 14);
            g(iArr, 3, 7, 11, 15);
            g(iArr, 0, 5, 10, 15);
            g(iArr, 1, 6, 11, 12);
            g(iArr, 2, 7, 8, 13);
            g(iArr, 3, 4, 9, 14);
        }
    }

    public static int[] k(byte[] bArr) {
        IntBuffer asIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[asIntBuffer.remaining()];
        asIntBuffer.get(iArr);
        return iArr;
    }

    public ByteBuffer a(byte[] bArr, int i) {
        int[] b = b(k(bArr), i);
        int[] iArr = (int[]) b.clone();
        j(iArr);
        for (int i2 = 0; i2 < b.length; i2++) {
            b[i2] = b[i2] + iArr[i2];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(b, 0, 16);
        return order;
    }

    public abstract int[] b(int[] iArr, int i);

    public byte[] c(ByteBuffer byteBuffer) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= e()) {
            byte[] bArr = new byte[e()];
            byteBuffer.get(bArr);
            ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
            f(bArr, allocate, byteBuffer);
            return allocate.array();
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    public void d(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
        if (byteBuffer.remaining() - e() >= bArr.length) {
            byte[] randBytes = Random.randBytes(e());
            byteBuffer.put(randBytes);
            f(randBytes, byteBuffer, ByteBuffer.wrap(bArr));
            return;
        }
        throw new IllegalArgumentException("Given ByteBuffer output is too small");
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] bArr) throws GeneralSecurityException {
        return c(ByteBuffer.wrap(bArr));
    }

    public abstract int e();

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length <= Integer.MAX_VALUE - e()) {
            ByteBuffer allocate = ByteBuffer.allocate(e() + bArr.length);
            d(allocate, bArr);
            return allocate.array();
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    public final void f(byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws GeneralSecurityException {
        int remaining = byteBuffer2.remaining();
        int i = (remaining / 64) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer a2 = a(bArr, this.b + i2);
            if (i2 == i - 1) {
                Bytes.xor(byteBuffer, byteBuffer2, a2, remaining % 64);
            } else {
                Bytes.xor(byteBuffer, byteBuffer2, a2, 64);
            }
        }
    }
}
