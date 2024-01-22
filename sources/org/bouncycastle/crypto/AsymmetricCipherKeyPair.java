package org.bouncycastle.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes5.dex */
public class AsymmetricCipherKeyPair {

    /* renamed from: a  reason: collision with root package name */
    public AsymmetricKeyParameter f14603a;
    public AsymmetricKeyParameter b;

    public AsymmetricCipherKeyPair(CipherParameters cipherParameters, CipherParameters cipherParameters2) {
        this.f14603a = (AsymmetricKeyParameter) cipherParameters;
        this.b = (AsymmetricKeyParameter) cipherParameters2;
    }

    public AsymmetricCipherKeyPair(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        this.f14603a = asymmetricKeyParameter;
        this.b = asymmetricKeyParameter2;
    }

    public AsymmetricKeyParameter getPrivate() {
        return this.b;
    }

    public AsymmetricKeyParameter getPublic() {
        return this.f14603a;
    }
}
