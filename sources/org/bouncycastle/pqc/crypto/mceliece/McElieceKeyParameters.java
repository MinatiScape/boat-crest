package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes13.dex */
public class McElieceKeyParameters extends AsymmetricKeyParameter {
    public McElieceParameters i;

    public McElieceKeyParameters(boolean z, McElieceParameters mcElieceParameters) {
        super(z);
        this.i = mcElieceParameters;
    }

    public McElieceParameters getParameters() {
        return this.i;
    }
}
