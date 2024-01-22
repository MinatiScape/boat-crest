package org.bouncycastle.pqc.jcajce.provider.newhope;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.newhope.NHKeyPairGenerator;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
/* loaded from: classes13.dex */
public class NHKeyPairGeneratorSpi extends KeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public NHKeyPairGenerator f15349a;
    public SecureRandom b;
    public boolean c;

    public NHKeyPairGeneratorSpi() {
        super("NH");
        this.f15349a = new NHKeyPairGenerator();
        this.b = new SecureRandom();
        this.c = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.c) {
            this.f15349a.init(new KeyGenerationParameters(this.b, 1024));
            this.c = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.f15349a.generateKeyPair();
        return new KeyPair(new BCNHPublicKey((NHPublicKeyParameters) generateKeyPair.getPublic()), new BCNHPrivateKey((NHPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        if (i != 1024) {
            throw new IllegalArgumentException("strength must be 1024 bits");
        }
        this.f15349a.init(new KeyGenerationParameters(secureRandom, 1024));
        this.c = true;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("parameter object not recognised");
    }
}
