package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFCounterParameters;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class KDFCounterBytesGenerator implements MacDerivationFunction {
    public static final BigInteger i = BigInteger.valueOf(2147483647L);
    public static final BigInteger j = BigInteger.valueOf(2);

    /* renamed from: a  reason: collision with root package name */
    public final Mac f14734a;
    public final int b;
    public byte[] c;
    public byte[] d;
    public int e;
    public byte[] f;
    public int g;
    public byte[] h;

    public KDFCounterBytesGenerator(Mac mac) {
        this.f14734a = mac;
        int macSize = mac.getMacSize();
        this.b = macSize;
        this.h = new byte[macSize];
    }

    public final void a() {
        int i2 = (this.g / this.b) + 1;
        byte[] bArr = this.f;
        int length = bArr.length;
        if (length != 1) {
            if (length != 2) {
                if (length != 3) {
                    if (length != 4) {
                        throw new IllegalStateException("Unsupported size of counter i");
                    }
                    bArr[0] = (byte) (i2 >>> 24);
                }
                bArr[bArr.length - 3] = (byte) (i2 >>> 16);
            }
            bArr[bArr.length - 2] = (byte) (i2 >>> 8);
        }
        bArr[bArr.length - 1] = (byte) i2;
        Mac mac = this.f14734a;
        byte[] bArr2 = this.c;
        mac.update(bArr2, 0, bArr2.length);
        Mac mac2 = this.f14734a;
        byte[] bArr3 = this.f;
        mac2.update(bArr3, 0, bArr3.length);
        Mac mac3 = this.f14734a;
        byte[] bArr4 = this.d;
        mac3.update(bArr4, 0, bArr4.length);
        this.f14734a.doFinal(this.h, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i2, int i3) throws DataLengthException, IllegalArgumentException {
        int i4 = this.g;
        int i5 = i4 + i3;
        if (i5 < 0 || i5 >= this.e) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.e + " bytes");
        }
        if (i4 % this.b == 0) {
            a();
        }
        int i6 = this.g;
        int i7 = this.b;
        int i8 = i6 % i7;
        int min = Math.min(i7 - (i6 % i7), i3);
        System.arraycopy(this.h, i8, bArr, i2, min);
        this.g += min;
        int i9 = i3 - min;
        while (true) {
            i2 += min;
            if (i9 <= 0) {
                return i3;
            }
            a();
            min = Math.min(this.b, i9);
            System.arraycopy(this.h, 0, bArr, i2, min);
            this.g += min;
            i9 -= min;
        }
    }

    @Override // org.bouncycastle.crypto.MacDerivationFunction
    public Mac getMac() {
        return this.f14734a;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFCounterParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFCounterParameters kDFCounterParameters = (KDFCounterParameters) derivationParameters;
        this.f14734a.init(new KeyParameter(kDFCounterParameters.getKI()));
        this.c = kDFCounterParameters.getFixedInputDataCounterPrefix();
        this.d = kDFCounterParameters.getFixedInputDataCounterSuffix();
        int r = kDFCounterParameters.getR();
        this.f = new byte[r / 8];
        BigInteger multiply = j.pow(r).multiply(BigInteger.valueOf(this.b));
        this.e = multiply.compareTo(i) == 1 ? Integer.MAX_VALUE : multiply.intValue();
        this.g = 0;
    }
}
