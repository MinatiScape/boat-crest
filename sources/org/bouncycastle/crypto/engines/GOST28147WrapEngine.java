package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.macs.GOST28147Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.crypto.params.ParametersWithUKM;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class GOST28147WrapEngine implements Wrapper {

    /* renamed from: a  reason: collision with root package name */
    public GOST28147Engine f14679a = new GOST28147Engine();
    public GOST28147Mac b = new GOST28147Mac();

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "GOST28147Wrap";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        ParametersWithUKM parametersWithUKM = (ParametersWithUKM) cipherParameters;
        this.f14679a.init(z, parametersWithUKM.getParameters());
        this.b.init(new ParametersWithIV((KeyParameter) (parametersWithUKM.getParameters() instanceof ParametersWithSBox ? ((ParametersWithSBox) parametersWithUKM.getParameters()).getParameters() : parametersWithUKM.getParameters()), parametersWithUKM.getUKM()));
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        int macSize = i2 - this.b.getMacSize();
        byte[] bArr2 = new byte[macSize];
        this.f14679a.processBlock(bArr, i, bArr2, 0);
        this.f14679a.processBlock(bArr, i + 8, bArr2, 8);
        this.f14679a.processBlock(bArr, i + 16, bArr2, 16);
        this.f14679a.processBlock(bArr, i + 24, bArr2, 24);
        byte[] bArr3 = new byte[this.b.getMacSize()];
        this.b.update(bArr2, 0, macSize);
        this.b.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[this.b.getMacSize()];
        System.arraycopy(bArr, (i + i2) - 4, bArr4, 0, this.b.getMacSize());
        if (Arrays.constantTimeAreEqual(bArr3, bArr4)) {
            return bArr2;
        }
        throw new IllegalStateException("mac mismatch");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i, int i2) {
        this.b.update(bArr, i, i2);
        byte[] bArr2 = new byte[this.b.getMacSize() + i2];
        this.f14679a.processBlock(bArr, i, bArr2, 0);
        this.f14679a.processBlock(bArr, i + 8, bArr2, 8);
        this.f14679a.processBlock(bArr, i + 16, bArr2, 16);
        this.f14679a.processBlock(bArr, i + 24, bArr2, 24);
        this.b.doFinal(bArr2, i2);
        return bArr2;
    }
}
