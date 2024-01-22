package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class GOST3410KeyParameters extends AsymmetricKeyParameter {
    public GOST3410Parameters i;

    public GOST3410KeyParameters(boolean z, GOST3410Parameters gOST3410Parameters) {
        super(z);
        this.i = gOST3410Parameters;
    }

    public GOST3410Parameters getParameters() {
        return this.i;
    }
}
