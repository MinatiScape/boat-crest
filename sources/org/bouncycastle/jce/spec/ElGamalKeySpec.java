package org.bouncycastle.jce.spec;

import java.security.spec.KeySpec;
/* loaded from: classes13.dex */
public class ElGamalKeySpec implements KeySpec {
    public ElGamalParameterSpec h;

    public ElGamalKeySpec(ElGamalParameterSpec elGamalParameterSpec) {
        this.h = elGamalParameterSpec;
    }

    public ElGamalParameterSpec getParams() {
        return this.h;
    }
}
