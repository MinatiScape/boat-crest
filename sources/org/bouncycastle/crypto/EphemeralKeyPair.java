package org.bouncycastle.crypto;
/* loaded from: classes5.dex */
public class EphemeralKeyPair {

    /* renamed from: a  reason: collision with root package name */
    public AsymmetricCipherKeyPair f14606a;
    public KeyEncoder b;

    public EphemeralKeyPair(AsymmetricCipherKeyPair asymmetricCipherKeyPair, KeyEncoder keyEncoder) {
        this.f14606a = asymmetricCipherKeyPair;
        this.b = keyEncoder;
    }

    public byte[] getEncodedPublicKey() {
        return this.b.getEncoded(this.f14606a.getPublic());
    }

    public AsymmetricCipherKeyPair getKeyPair() {
        return this.f14606a;
    }
}
