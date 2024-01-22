package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class DSAKeyParameters extends AsymmetricKeyParameter {
    public DSAParameters i;

    public DSAKeyParameters(boolean z, DSAParameters dSAParameters) {
        super(z);
        this.i = dSAParameters;
    }

    public DSAParameters getParameters() {
        return this.i;
    }
}
