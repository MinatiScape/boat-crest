package org.bouncycastle.jce.spec;

import java.security.spec.KeySpec;
/* loaded from: classes13.dex */
public class ECKeySpec implements KeySpec {
    public ECParameterSpec h;

    public ECKeySpec(ECParameterSpec eCParameterSpec) {
        this.h = eCParameterSpec;
    }

    public ECParameterSpec getParams() {
        return this.h;
    }
}
