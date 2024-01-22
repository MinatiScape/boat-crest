package org.bouncycastle.crypto.macs;

import com.touchgui.sdk.TGEventListener;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class CMac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14753a;
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public int e;
    public BlockCipher f;
    public int g;
    public byte[] h;
    public byte[] i;

    public CMac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public CMac(BlockCipher blockCipher, int i) {
        if (i % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        if (i > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("MAC size must be less or equal to " + (blockCipher.getBlockSize() * 8));
        }
        this.f = new CBCBlockCipher(blockCipher);
        this.g = i / 8;
        this.f14753a = b(blockCipher.getBlockSize());
        this.c = new byte[blockCipher.getBlockSize()];
        this.d = new byte[blockCipher.getBlockSize()];
        this.b = new byte[blockCipher.getBlockSize()];
        this.e = 0;
    }

    public static byte[] b(int i) {
        int i2 = i * 8;
        int i3 = 135;
        switch (i2) {
            case 64:
            case 320:
                i3 = 27;
                break;
            case 128:
            case 192:
                break;
            case 160:
                i3 = 45;
                break;
            case 224:
                i3 = 777;
                break;
            case 256:
                i3 = 1061;
                break;
            case 384:
                i3 = 4109;
                break;
            case 448:
                i3 = 2129;
                break;
            case 512:
                i3 = TGEventListener.WATCH_FACE_INSTALLED;
                break;
            case 768:
                i3 = 655377;
                break;
            case 1024:
                i3 = 524355;
                break;
            case 2048:
                i3 = 548865;
                break;
            default:
                throw new IllegalArgumentException("Unknown block size for CMAC: " + i2);
        }
        return Pack.intToBigEndian(i3);
    }

    public static int c(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i = 0;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            int i2 = bArr[length] & 255;
            bArr2[length] = (byte) (i | (i2 << 1));
            i = (i2 >>> 7) & 1;
        }
    }

    public final byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = (-c(bArr, bArr2)) & 255;
        int length = bArr.length - 3;
        byte b = bArr2[length];
        byte[] bArr3 = this.f14753a;
        bArr2[length] = (byte) (b ^ (bArr3[1] & i));
        int length2 = bArr.length - 2;
        bArr2[length2] = (byte) ((bArr3[2] & i) ^ bArr2[length2]);
        int length3 = bArr.length - 1;
        bArr2[length3] = (byte) ((i & bArr3[3]) ^ bArr2[length3]);
        return bArr2;
    }

    public void d(CipherParameters cipherParameters) {
        if (cipherParameters != null && !(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("CMac mode only permits key to be set.");
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2;
        if (this.e == this.f.getBlockSize()) {
            bArr2 = this.h;
        } else {
            new ISO7816d4Padding().addPadding(this.d, this.e);
            bArr2 = this.i;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr3 = this.c;
            if (i2 >= bArr3.length) {
                this.f.processBlock(this.d, 0, bArr3, 0);
                System.arraycopy(this.c, 0, bArr, i, this.g);
                reset();
                return this.g;
            }
            byte[] bArr4 = this.d;
            bArr4[i2] = (byte) (bArr4[i2] ^ bArr2[i2]);
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.f.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.g;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        d(cipherParameters);
        this.f.init(true, cipherParameters);
        byte[] bArr = this.b;
        byte[] bArr2 = new byte[bArr.length];
        this.f.processBlock(bArr, 0, bArr2, 0);
        byte[] a2 = a(bArr2);
        this.h = a2;
        this.i = a(a2);
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.d;
            if (i >= bArr.length) {
                this.e = 0;
                this.f.reset();
                return;
            }
            bArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        int i = this.e;
        byte[] bArr = this.d;
        if (i == bArr.length) {
            this.f.processBlock(bArr, 0, this.c, 0);
            this.e = 0;
        }
        byte[] bArr2 = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        bArr2[i2] = b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int blockSize = this.f.getBlockSize();
        int i3 = this.e;
        int i4 = blockSize - i3;
        if (i2 > i4) {
            System.arraycopy(bArr, i, this.d, i3, i4);
            this.f.processBlock(this.d, 0, this.c, 0);
            this.e = 0;
            i2 -= i4;
            i += i4;
            while (i2 > blockSize) {
                this.f.processBlock(bArr, i, this.c, 0);
                i2 -= blockSize;
                i += blockSize;
            }
        }
        System.arraycopy(bArr, i, this.d, this.e, i2);
        this.e += i2;
    }
}
