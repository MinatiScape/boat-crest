package org.bouncycastle.crypto.prng;

import android.support.v4.media.session.PlaybackStateCompat;
import org.bouncycastle.crypto.BlockCipher;
/* loaded from: classes13.dex */
public class X931RNG {

    /* renamed from: a  reason: collision with root package name */
    public final BlockCipher f14820a;
    public final EntropySource b;
    public final byte[] c;
    public final byte[] d;
    public final byte[] e;
    public byte[] f;
    public long g = 1;

    public X931RNG(BlockCipher blockCipher, byte[] bArr, EntropySource entropySource) {
        this.f14820a = blockCipher;
        this.b = entropySource;
        byte[] bArr2 = new byte[blockCipher.getBlockSize()];
        this.c = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.d = new byte[blockCipher.getBlockSize()];
        this.e = new byte[blockCipher.getBlockSize()];
    }

    public static boolean d(byte[] bArr, int i) {
        return bArr != null && bArr.length > i;
    }

    public int a(byte[] bArr, boolean z) {
        if (this.e.length == 8) {
            if (this.g > PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) {
                return -1;
            }
            if (d(bArr, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else if (this.g > 8388608) {
            return -1;
        } else {
            if (d(bArr, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (z || this.f == null) {
            byte[] entropy = this.b.getEntropy();
            this.f = entropy;
            if (entropy.length != this.f14820a.getBlockSize()) {
                throw new IllegalStateException("Insufficient entropy returned");
            }
        }
        int length = bArr.length / this.e.length;
        for (int i = 0; i < length; i++) {
            this.f14820a.processBlock(this.c, 0, this.d, 0);
            e(this.e, this.d, this.f);
            e(this.f, this.e, this.d);
            byte[] bArr2 = this.e;
            System.arraycopy(bArr2, 0, bArr, bArr2.length * i, bArr2.length);
            c(this.c);
        }
        int length2 = bArr.length - (this.e.length * length);
        if (length2 > 0) {
            this.f14820a.processBlock(this.c, 0, this.d, 0);
            e(this.e, this.d, this.f);
            e(this.f, this.e, this.d);
            byte[] bArr3 = this.e;
            System.arraycopy(bArr3, 0, bArr, length * bArr3.length, length2);
            c(this.c);
        }
        this.g++;
        return bArr.length;
    }

    public EntropySource b() {
        return this.b;
    }

    public final void c(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte b = (byte) (bArr[length] + 1);
            bArr[length] = b;
            if (b != 0) {
                return;
            }
        }
    }

    public final void e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) (bArr2[i] ^ bArr3[i]);
        }
        this.f14820a.processBlock(bArr, 0, bArr, 0);
    }

    public void f() {
        byte[] entropy = this.b.getEntropy();
        this.f = entropy;
        if (entropy.length != this.f14820a.getBlockSize()) {
            throw new IllegalStateException("Insufficient entropy returned");
        }
        this.g = 1L;
    }
}
