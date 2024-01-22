package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    public ECDomainParameters i;

    public ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        this.i = eCDomainParameters;
    }

    public ECDomainParameters getParameters() {
        return this.i;
    }
}
