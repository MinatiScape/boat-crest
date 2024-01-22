package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFFeedbackParameters;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class KDFFeedbackBytesGenerator implements MacDerivationFunction {
    public static final BigInteger j = BigInteger.valueOf(2147483647L);
    public static final BigInteger k = BigInteger.valueOf(2);

    /* renamed from: a  reason: collision with root package name */
    public final Mac f14736a;
    public final int b;
    public byte[] c;
    public int d;
    public byte[] e;
    public byte[] f;
    public boolean g;
    public int h;
    public byte[] i;

    public KDFFeedbackBytesGenerator(Mac mac) {
        this.f14736a = mac;
        int macSize = mac.getMacSize();
        this.b = macSize;
        this.i = new byte[macSize];
    }

    public final void a() {
        if (this.h == 0) {
            Mac mac = this.f14736a;
            byte[] bArr = this.f;
            mac.update(bArr, 0, bArr.length);
        } else {
            Mac mac2 = this.f14736a;
            byte[] bArr2 = this.i;
            mac2.update(bArr2, 0, bArr2.length);
        }
        if (this.g) {
            int i = (this.h / this.b) + 1;
            byte[] bArr3 = this.e;
            int length = bArr3.length;
            if (length != 1) {
                if (length != 2) {
                    if (length != 3) {
                        if (length != 4) {
                            throw new IllegalStateException("Unsupported size of counter i");
                        }
                        bArr3[0] = (byte) (i >>> 24);
                    }
                    bArr3[bArr3.length - 3] = (byte) (i >>> 16);
                }
                bArr3[bArr3.length - 2] = (byte) (i >>> 8);
            }
            bArr3[bArr3.length - 1] = (byte) i;
            this.f14736a.update(bArr3, 0, bArr3.length);
        }
        Mac mac3 = this.f14736a;
        byte[] bArr4 = this.c;
        mac3.update(bArr4, 0, bArr4.length);
        this.f14736a.doFinal(this.i, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        int i3 = this.h;
        int i4 = i3 + i2;
        if (i4 < 0 || i4 >= this.d) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.d + " bytes");
        }
        if (i3 % this.b == 0) {
            a();
        }
        int i5 = this.h;
        int i6 = this.b;
        int i7 = i5 % i6;
        int min = Math.min(i6 - (i5 % i6), i2);
        System.arraycopy(this.i, i7, bArr, i, min);
        this.h += min;
        int i8 = i2 - min;
        while (true) {
            i += min;
            if (i8 <= 0) {
                return i2;
            }
            a();
            min = Math.min(this.b, i8);
            System.arraycopy(this.i, 0, bArr, i, min);
            this.h += min;
            i8 -= min;
        }
    }

    @Override // org.bouncycastle.crypto.MacDerivationFunction
    public Mac getMac() {
        return this.f14736a;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFFeedbackParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFFeedbackParameters kDFFeedbackParameters = (KDFFeedbackParameters) derivationParameters;
        this.f14736a.init(new KeyParameter(kDFFeedbackParameters.getKI()));
        this.c = kDFFeedbackParameters.getFixedInputData();
        int r = kDFFeedbackParameters.getR();
        this.e = new byte[r / 8];
        int i = Integer.MAX_VALUE;
        if (kDFFeedbackParameters.useCounter()) {
            BigInteger multiply = k.pow(r).multiply(BigInteger.valueOf(this.b));
            if (multiply.compareTo(j) != 1) {
                i = multiply.intValue();
            }
        }
        this.d = i;
        this.f = kDFFeedbackParameters.getIV();
        this.g = kDFFeedbackParameters.useCounter();
        this.h = 0;
    }
}
