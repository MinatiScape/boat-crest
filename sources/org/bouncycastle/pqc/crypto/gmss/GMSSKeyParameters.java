package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes13.dex */
public class GMSSKeyParameters extends AsymmetricKeyParameter {
    public GMSSParameters i;

    public GMSSKeyParameters(boolean z, GMSSParameters gMSSParameters) {
        super(z);
        this.i = gMSSParameters;
    }

    public GMSSParameters getParameters() {
        return this.i;
    }
}
