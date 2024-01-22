package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class PKCS5S2ParametersGenerator extends PBEParametersGenerator {

    /* renamed from: a  reason: collision with root package name */
    public Mac f14743a;
    public byte[] b;

    public PKCS5S2ParametersGenerator() {
        this(DigestFactory.createSHA1());
    }

    public PKCS5S2ParametersGenerator(Digest digest) {
        HMac hMac = new HMac(digest);
        this.f14743a = hMac;
        this.b = new byte[hMac.getMacSize()];
    }

    public final void a(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2) {
        if (i == 0) {
            throw new IllegalArgumentException("iteration count must be at least 1.");
        }
        if (bArr != null) {
            this.f14743a.update(bArr, 0, bArr.length);
        }
        this.f14743a.update(bArr2, 0, bArr2.length);
        this.f14743a.doFinal(this.b, 0);
        byte[] bArr4 = this.b;
        System.arraycopy(bArr4, 0, bArr3, i2, bArr4.length);
        for (int i3 = 1; i3 < i; i3++) {
            Mac mac = this.f14743a;
            byte[] bArr5 = this.b;
            mac.update(bArr5, 0, bArr5.length);
            this.f14743a.doFinal(this.b, 0);
            int i4 = 0;
            while (true) {
                byte[] bArr6 = this.b;
                if (i4 != bArr6.length) {
                    int i5 = i2 + i4;
                    bArr3[i5] = (byte) (bArr6[i4] ^ bArr3[i5]);
                    i4++;
                }
            }
        }
    }

    public final byte[] b(int i) {
        int i2;
        int macSize = this.f14743a.getMacSize();
        int i3 = ((i + macSize) - 1) / macSize;
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[i3 * macSize];
        this.f14743a.init(new KeyParameter(this.password));
        int i4 = 0;
        for (int i5 = 1; i5 <= i3; i5++) {
            while (true) {
                byte b = (byte) (bArr[i2] + 1);
                bArr[i2] = b;
                i2 = b == 0 ? i2 - 1 : 3;
            }
            a(this.salt, this.iterationCount, bArr, bArr2, i4);
            i4 += macSize;
        }
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i) {
        return generateDerivedParameters(i);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(Arrays.copyOfRange(b(i2), 0, i2), 0, i2);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] b = b(i3 + i4);
        return new ParametersWithIV(new KeyParameter(b, 0, i3), b, i3, i4);
    }
}
