package org.bouncycastle.jcajce.provider.asymmetric.dh;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.DHBasicKeyPairGenerator;
import org.bouncycastle.crypto.generators.DHParametersGenerator;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public static Hashtable f = new Hashtable();
    public static Object g = new Object();

    /* renamed from: a  reason: collision with root package name */
    public DHKeyGenerationParameters f14936a;
    public DHBasicKeyPairGenerator b;
    public int c;
    public SecureRandom d;
    public boolean e;

    public KeyPairGeneratorSpi() {
        super("DH");
        this.b = new DHBasicKeyPairGenerator();
        this.c = 2048;
        this.d = new SecureRandom();
        this.e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        DHKeyGenerationParameters dHKeyGenerationParameters;
        if (!this.e) {
            Integer valueOf = Integers.valueOf(this.c);
            if (f.containsKey(valueOf)) {
                dHKeyGenerationParameters = (DHKeyGenerationParameters) f.get(valueOf);
            } else {
                DHParameterSpec dHDefaultParameters = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(this.c);
                if (dHDefaultParameters != null) {
                    dHKeyGenerationParameters = new DHKeyGenerationParameters(this.d, new DHParameters(dHDefaultParameters.getP(), dHDefaultParameters.getG(), null, dHDefaultParameters.getL()));
                } else {
                    synchronized (g) {
                        if (f.containsKey(valueOf)) {
                            this.f14936a = (DHKeyGenerationParameters) f.get(valueOf);
                        } else {
                            DHParametersGenerator dHParametersGenerator = new DHParametersGenerator();
                            int i = this.c;
                            dHParametersGenerator.init(i, PrimeCertaintyCalculator.getDefaultCertainty(i), this.d);
                            DHKeyGenerationParameters dHKeyGenerationParameters2 = new DHKeyGenerationParameters(this.d, dHParametersGenerator.generateParameters());
                            this.f14936a = dHKeyGenerationParameters2;
                            f.put(valueOf, dHKeyGenerationParameters2);
                        }
                    }
                    this.b.init(this.f14936a);
                    this.e = true;
                }
            }
            this.f14936a = dHKeyGenerationParameters;
            this.b.init(this.f14936a);
            this.e = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.b.generateKeyPair();
        return new KeyPair(new BCDHPublicKey((DHPublicKeyParameters) generateKeyPair.getPublic()), new BCDHPrivateKey((DHPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        this.c = i;
        this.d = secureRandom;
        this.e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec");
        }
        DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
        try {
            DHKeyGenerationParameters dHKeyGenerationParameters = new DHKeyGenerationParameters(secureRandom, new DHParameters(dHParameterSpec.getP(), dHParameterSpec.getG(), null, dHParameterSpec.getL()));
            this.f14936a = dHKeyGenerationParameters;
            this.b.init(dHKeyGenerationParameters);
            this.e = true;
        } catch (IllegalArgumentException e) {
            throw new InvalidAlgorithmParameterException(e.getMessage(), e);
        }
    }
}
