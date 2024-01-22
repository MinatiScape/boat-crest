package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAValidationParameters;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes12.dex */
public class DSAParametersGenerator {
    public static final BigInteger h;
    public static final BigInteger i;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14726a;
    public int b;
    public int c;
    public int d;
    public SecureRandom e;
    public boolean f;
    public int g;

    static {
        BigInteger.valueOf(0L);
        h = BigInteger.valueOf(1L);
        i = BigInteger.valueOf(2L);
    }

    public DSAParametersGenerator() {
        this(DigestFactory.createSHA1());
    }

    public DSAParametersGenerator(Digest digest) {
        this.f14726a = digest;
    }

    public static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger modPow;
        BigInteger divide = bigInteger.subtract(h).divide(bigInteger2);
        BigInteger subtract = bigInteger.subtract(i);
        do {
            modPow = BigIntegers.createRandomInRange(i, subtract, secureRandom).modPow(divide, bigInteger);
        } while (modPow.bitLength() <= 1);
        return modPow;
    }

    public static BigInteger b(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        return a(bigInteger, bigInteger2, secureRandom);
    }

    public static BigInteger c(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, int i2) {
        BigInteger divide = bigInteger.subtract(h).divide(bigInteger2);
        byte[] decode = Hex.decode("6767656E");
        int length = bArr.length + decode.length + 1 + 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(decode, 0, bArr2, bArr.length, decode.length);
        bArr2[length - 3] = (byte) i2;
        byte[] bArr3 = new byte[digest.getDigestSize()];
        for (int i3 = 1; i3 < 65536; i3++) {
            i(bArr2);
            h(digest, bArr2, bArr3, 0);
            BigInteger modPow = new BigInteger(1, bArr3).modPow(divide, bigInteger);
            if (modPow.compareTo(i) >= 0) {
                return modPow;
            }
        }
        return null;
    }

    public static int f(int i2) {
        return i2 > 1024 ? 256 : 160;
    }

    public static int g(int i2) {
        if (i2 <= 1024) {
            return 40;
        }
        return (((i2 - 1) / 1024) * 8) + 48;
    }

    public static void h(Digest digest, byte[] bArr, byte[] bArr2, int i2) {
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, i2);
    }

    public static void i(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte b = (byte) ((bArr[length] + 1) & 255);
            bArr[length] = b;
            if (b != 0) {
                return;
            }
        }
    }

    public final DSAParameters d() {
        int i2 = 20;
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[20];
        byte[] bArr3 = new byte[20];
        byte[] bArr4 = new byte[20];
        int i3 = this.b;
        int i4 = (i3 - 1) / 160;
        int i5 = i3 / 8;
        byte[] bArr5 = new byte[i5];
        if (!(this.f14726a instanceof SHA1Digest)) {
            throw new IllegalStateException("can only use SHA-1 for generating FIPS 186-2 parameters");
        }
        while (true) {
            this.e.nextBytes(bArr);
            h(this.f14726a, bArr, bArr2, 0);
            System.arraycopy(bArr, 0, bArr3, 0, i2);
            i(bArr3);
            h(this.f14726a, bArr3, bArr3, 0);
            for (int i6 = 0; i6 != i2; i6++) {
                bArr4[i6] = (byte) (bArr2[i6] ^ bArr3[i6]);
            }
            bArr4[0] = (byte) (bArr4[0] | Byte.MIN_VALUE);
            bArr4[19] = (byte) (bArr4[19] | 1);
            BigInteger bigInteger = new BigInteger(1, bArr4);
            if (j(bigInteger)) {
                byte[] clone = Arrays.clone(bArr);
                i(clone);
                int i7 = 0;
                while (i7 < 4096) {
                    for (int i8 = 1; i8 <= i4; i8++) {
                        i(clone);
                        h(this.f14726a, clone, bArr5, i5 - (i8 * 20));
                    }
                    int i9 = i5 - (i4 * 20);
                    i(clone);
                    h(this.f14726a, clone, bArr2, 0);
                    System.arraycopy(bArr2, 20 - i9, bArr5, 0, i9);
                    bArr5[0] = (byte) (bArr5[0] | Byte.MIN_VALUE);
                    BigInteger bigInteger2 = new BigInteger(1, bArr5);
                    BigInteger subtract = bigInteger2.subtract(bigInteger2.mod(bigInteger.shiftLeft(1)).subtract(h));
                    if (subtract.bitLength() == this.b && j(subtract)) {
                        return new DSAParameters(subtract, bigInteger, a(subtract, bigInteger, this.e), new DSAValidationParameters(bArr, i7));
                    }
                    i7++;
                    i2 = 20;
                }
                continue;
            }
        }
    }

    public final DSAParameters e() {
        BigInteger bit;
        int i2;
        BigInteger subtract;
        BigInteger c;
        Digest digest = this.f14726a;
        int digestSize = digest.getDigestSize() * 8;
        byte[] bArr = new byte[this.c / 8];
        int i3 = this.b;
        int i4 = (i3 - 1) / digestSize;
        int i5 = (i3 - 1) % digestSize;
        int i6 = i3 / 8;
        byte[] bArr2 = new byte[i6];
        int digestSize2 = digest.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        loop0: while (true) {
            this.e.nextBytes(bArr);
            int i7 = 0;
            h(digest, bArr, bArr3, 0);
            bit = new BigInteger(1, bArr3).mod(h.shiftLeft(this.c - 1)).setBit(0).setBit(this.c - 1);
            if (j(bit)) {
                byte[] clone = Arrays.clone(bArr);
                int i8 = this.b * 4;
                i2 = 0;
                while (i2 < i8) {
                    for (int i9 = 1; i9 <= i4; i9++) {
                        i(clone);
                        h(digest, clone, bArr2, i6 - (i9 * digestSize2));
                    }
                    int i10 = i6 - (i4 * digestSize2);
                    i(clone);
                    h(digest, clone, bArr3, i7);
                    System.arraycopy(bArr3, digestSize2 - i10, bArr2, i7, i10);
                    bArr2[i7] = (byte) (bArr2[i7] | Byte.MIN_VALUE);
                    BigInteger bigInteger = new BigInteger(1, bArr2);
                    subtract = bigInteger.subtract(bigInteger.mod(bit.shiftLeft(1)).subtract(h));
                    if (subtract.bitLength() == this.b && j(subtract)) {
                        break loop0;
                    }
                    i2++;
                    i7 = 0;
                }
                continue;
            }
        }
        int i11 = this.g;
        return (i11 < 0 || (c = c(digest, subtract, bit, bArr, i11)) == null) ? new DSAParameters(subtract, bit, b(subtract, bit, this.e), new DSAValidationParameters(bArr, i2)) : new DSAParameters(subtract, bit, c, new DSAValidationParameters(bArr, i2, this.g));
    }

    public DSAParameters generateParameters() {
        return this.f ? e() : d();
    }

    public void init(int i2, int i3, SecureRandom secureRandom) {
        this.b = i2;
        this.c = f(i2);
        this.d = i3;
        Math.max(g(this.b), (i3 + 1) / 2);
        this.e = secureRandom;
        this.f = false;
        this.g = -1;
    }

    public void init(DSAParameterGenerationParameters dSAParameterGenerationParameters) {
        int l = dSAParameterGenerationParameters.getL();
        int n = dSAParameterGenerationParameters.getN();
        if (l < 1024 || l > 3072 || l % 1024 != 0) {
            throw new IllegalArgumentException("L values must be between 1024 and 3072 and a multiple of 1024");
        }
        if (l == 1024 && n != 160) {
            throw new IllegalArgumentException("N must be 160 for L = 1024");
        }
        if (l == 2048 && n != 224 && n != 256) {
            throw new IllegalArgumentException("N must be 224 or 256 for L = 2048");
        }
        if (l == 3072 && n != 256) {
            throw new IllegalArgumentException("N must be 256 for L = 3072");
        }
        if (this.f14726a.getDigestSize() * 8 < n) {
            throw new IllegalStateException("Digest output size too small for value of N");
        }
        this.b = l;
        this.c = n;
        this.d = dSAParameterGenerationParameters.getCertainty();
        Math.max(g(l), (this.d + 1) / 2);
        this.e = dSAParameterGenerationParameters.getRandom();
        this.f = true;
        this.g = dSAParameterGenerationParameters.getUsageIndex();
    }

    public final boolean j(BigInteger bigInteger) {
        return bigInteger.isProbablePrime(this.d);
    }
}
