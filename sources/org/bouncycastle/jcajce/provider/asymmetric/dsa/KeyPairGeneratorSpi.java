package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.util.Hashtable;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.DSAKeyPairGenerator;
import org.bouncycastle.crypto.generators.DSAParametersGenerator;
import org.bouncycastle.crypto.params.DSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Properties;
/* loaded from: classes13.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public static Hashtable f = new Hashtable();
    public static Object g = new Object();

    /* renamed from: a  reason: collision with root package name */
    public DSAKeyGenerationParameters f14938a;
    public DSAKeyPairGenerator b;
    public int c;
    public SecureRandom d;
    public boolean e;

    public KeyPairGeneratorSpi() {
        super("DSA");
        this.b = new DSAKeyPairGenerator();
        this.c = 2048;
        this.d = new SecureRandom();
        this.e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        DSAParametersGenerator dSAParametersGenerator;
        int i;
        SecureRandom secureRandom;
        if (!this.e) {
            Integer valueOf = Integers.valueOf(this.c);
            if (f.containsKey(valueOf)) {
                this.f14938a = (DSAKeyGenerationParameters) f.get(valueOf);
            } else {
                synchronized (g) {
                    if (f.containsKey(valueOf)) {
                        this.f14938a = (DSAKeyGenerationParameters) f.get(valueOf);
                    } else {
                        int defaultCertainty = PrimeCertaintyCalculator.getDefaultCertainty(this.c);
                        int i2 = this.c;
                        if (i2 == 1024) {
                            dSAParametersGenerator = new DSAParametersGenerator();
                            if (Properties.isOverrideSet("org.bouncycastle.dsa.FIPS186-2for1024bits")) {
                                i = this.c;
                                secureRandom = this.d;
                                dSAParametersGenerator.init(i, defaultCertainty, secureRandom);
                                DSAKeyGenerationParameters dSAKeyGenerationParameters = new DSAKeyGenerationParameters(this.d, dSAParametersGenerator.generateParameters());
                                this.f14938a = dSAKeyGenerationParameters;
                                f.put(valueOf, dSAKeyGenerationParameters);
                            } else {
                                dSAParametersGenerator.init(new DSAParameterGenerationParameters(1024, 160, defaultCertainty, this.d));
                                DSAKeyGenerationParameters dSAKeyGenerationParameters2 = new DSAKeyGenerationParameters(this.d, dSAParametersGenerator.generateParameters());
                                this.f14938a = dSAKeyGenerationParameters2;
                                f.put(valueOf, dSAKeyGenerationParameters2);
                            }
                        } else if (i2 > 1024) {
                            DSAParameterGenerationParameters dSAParameterGenerationParameters = new DSAParameterGenerationParameters(i2, 256, defaultCertainty, this.d);
                            dSAParametersGenerator = new DSAParametersGenerator(new SHA256Digest());
                            dSAParametersGenerator.init(dSAParameterGenerationParameters);
                            DSAKeyGenerationParameters dSAKeyGenerationParameters22 = new DSAKeyGenerationParameters(this.d, dSAParametersGenerator.generateParameters());
                            this.f14938a = dSAKeyGenerationParameters22;
                            f.put(valueOf, dSAKeyGenerationParameters22);
                        } else {
                            dSAParametersGenerator = new DSAParametersGenerator();
                            i = this.c;
                            secureRandom = this.d;
                            dSAParametersGenerator.init(i, defaultCertainty, secureRandom);
                            DSAKeyGenerationParameters dSAKeyGenerationParameters222 = new DSAKeyGenerationParameters(this.d, dSAParametersGenerator.generateParameters());
                            this.f14938a = dSAKeyGenerationParameters222;
                            f.put(valueOf, dSAKeyGenerationParameters222);
                        }
                    }
                }
            }
            this.b.init(this.f14938a);
            this.e = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.b.generateKeyPair();
        return new KeyPair(new BCDSAPublicKey((DSAPublicKeyParameters) generateKeyPair.getPublic()), new BCDSAPrivateKey((DSAPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        if (i < 512 || i > 4096 || ((i < 1024 && i % 64 != 0) || (i >= 1024 && i % 1024 != 0))) {
            throw new InvalidParameterException("strength must be from 512 - 4096 and a multiple of 1024 above 1024");
        }
        this.c = i;
        this.d = secureRandom;
        this.e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof DSAParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DSAParameterSpec");
        }
        DSAParameterSpec dSAParameterSpec = (DSAParameterSpec) algorithmParameterSpec;
        DSAKeyGenerationParameters dSAKeyGenerationParameters = new DSAKeyGenerationParameters(secureRandom, new DSAParameters(dSAParameterSpec.getP(), dSAParameterSpec.getQ(), dSAParameterSpec.getG()));
        this.f14938a = dSAKeyGenerationParameters;
        this.b.init(dSAKeyGenerationParameters);
        this.e = true;
    }
}
