package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.GOST3410KeyGenerationParameters;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.bouncycastle.crypto.params.GOST3410PublicKeyParameters;
import org.bouncycastle.math.ec.WNafUtil;
/* loaded from: classes12.dex */
public class GOST3410KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public GOST3410KeyGenerationParameters f14731a;

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        GOST3410Parameters parameters = this.f14731a.getParameters();
        SecureRandom random = this.f14731a.getRandom();
        BigInteger q = parameters.getQ();
        BigInteger p = parameters.getP();
        BigInteger a2 = parameters.getA();
        while (true) {
            BigInteger bigInteger = new BigInteger(256, random);
            if (bigInteger.signum() >= 1 && bigInteger.compareTo(q) < 0 && WNafUtil.getNafWeight(bigInteger) >= 64) {
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new GOST3410PublicKeyParameters(a2.modPow(bigInteger, p), parameters), (AsymmetricKeyParameter) new GOST3410PrivateKeyParameters(bigInteger, parameters));
            }
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.f14731a = (GOST3410KeyGenerationParameters) keyGenerationParameters;
    }
}
