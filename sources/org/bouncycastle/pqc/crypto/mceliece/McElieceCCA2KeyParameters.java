package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes13.dex */
public class McElieceCCA2KeyParameters extends AsymmetricKeyParameter {
    public String i;

    public McElieceCCA2KeyParameters(boolean z, String str) {
        super(z);
        this.i = str;
    }

    public String getDigest() {
        return this.i;
    }
}
