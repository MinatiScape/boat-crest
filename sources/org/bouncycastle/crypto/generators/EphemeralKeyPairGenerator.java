package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.KeyEncoder;
/* loaded from: classes12.dex */
public class EphemeralKeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public AsymmetricCipherKeyPairGenerator f14730a;
    public KeyEncoder b;

    public EphemeralKeyPairGenerator(AsymmetricCipherKeyPairGenerator asymmetricCipherKeyPairGenerator, KeyEncoder keyEncoder) {
        this.f14730a = asymmetricCipherKeyPairGenerator;
        this.b = keyEncoder;
    }

    public EphemeralKeyPair generate() {
        return new EphemeralKeyPair(this.f14730a.generateKeyPair(), this.b);
    }
}
