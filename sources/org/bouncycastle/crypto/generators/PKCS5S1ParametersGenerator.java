package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes12.dex */
public class PKCS5S1ParametersGenerator extends PBEParametersGenerator {

    /* renamed from: a  reason: collision with root package name */
    public Digest f14742a;

    public PKCS5S1ParametersGenerator(Digest digest) {
        this.f14742a = digest;
    }

    public final byte[] a() {
        int digestSize = this.f14742a.getDigestSize();
        byte[] bArr = new byte[digestSize];
        Digest digest = this.f14742a;
        byte[] bArr2 = this.password;
        digest.update(bArr2, 0, bArr2.length);
        Digest digest2 = this.f14742a;
        byte[] bArr3 = this.salt;
        digest2.update(bArr3, 0, bArr3.length);
        this.f14742a.doFinal(bArr, 0);
        for (int i = 1; i < this.iterationCount; i++) {
            this.f14742a.update(bArr, 0, digestSize);
            this.f14742a.doFinal(bArr, 0);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i) {
        return generateDerivedParameters(i);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        if (i2 <= this.f14742a.getDigestSize()) {
            return new KeyParameter(a(), 0, i2);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + i2 + " bytes long.");
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        int i5 = i3 + i4;
        if (i5 <= this.f14742a.getDigestSize()) {
            byte[] a2 = a();
            return new ParametersWithIV(new KeyParameter(a2, 0, i3), a2, i3, i4);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + i5 + " bytes long.");
    }
}
