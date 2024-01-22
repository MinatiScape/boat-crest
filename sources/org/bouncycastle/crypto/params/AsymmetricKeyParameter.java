package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class AsymmetricKeyParameter implements CipherParameters {
    public boolean h;

    public AsymmetricKeyParameter(boolean z) {
        this.h = z;
    }

    public boolean isPrivate() {
        return this.h;
    }
}
