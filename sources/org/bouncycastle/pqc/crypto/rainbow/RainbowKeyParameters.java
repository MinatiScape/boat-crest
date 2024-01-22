package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes13.dex */
public class RainbowKeyParameters extends AsymmetricKeyParameter {
    public int i;

    public RainbowKeyParameters(boolean z, int i) {
        super(z);
        this.i = i;
    }

    public int getDocLength() {
        return this.i;
    }
}
