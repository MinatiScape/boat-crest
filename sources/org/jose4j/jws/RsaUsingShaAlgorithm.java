package org.jose4j.jws;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.lang.InvalidKeyException;
/* loaded from: classes13.dex */
public class RsaUsingShaAlgorithm extends BaseSignatureAlgorithm {
    public static final String RSASSA_PSS = "RSASSA-PSS";

    /* loaded from: classes13.dex */
    public static class RsaPssSha256 extends RsaUsingShaAlgorithm {
        public RsaPssSha256() {
            super(AlgorithmIdentifiers.RSA_PSS_USING_SHA256, RsaUsingShaAlgorithm.f("SHA256withRSAandMGF1"));
            if (getJavaAlgorithm().equals(RsaUsingShaAlgorithm.RSASSA_PSS)) {
                MGF1ParameterSpec mGF1ParameterSpec = MGF1ParameterSpec.SHA256;
                setAlgorithmParameterSpec(new PSSParameterSpec(mGF1ParameterSpec.getDigestAlgorithm(), "MGF1", mGF1ParameterSpec, 32, 1));
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class RsaPssSha384 extends RsaUsingShaAlgorithm {
        public RsaPssSha384() {
            super(AlgorithmIdentifiers.RSA_PSS_USING_SHA384, RsaUsingShaAlgorithm.f("SHA384withRSAandMGF1"));
            if (getJavaAlgorithm().equals(RsaUsingShaAlgorithm.RSASSA_PSS)) {
                MGF1ParameterSpec mGF1ParameterSpec = MGF1ParameterSpec.SHA384;
                setAlgorithmParameterSpec(new PSSParameterSpec(mGF1ParameterSpec.getDigestAlgorithm(), "MGF1", mGF1ParameterSpec, 48, 1));
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class RsaPssSha512 extends RsaUsingShaAlgorithm {
        public RsaPssSha512() {
            super(AlgorithmIdentifiers.RSA_PSS_USING_SHA512, RsaUsingShaAlgorithm.f("SHA512withRSAandMGF1"));
            if (getJavaAlgorithm().equals(RsaUsingShaAlgorithm.RSASSA_PSS)) {
                MGF1ParameterSpec mGF1ParameterSpec = MGF1ParameterSpec.SHA512;
                setAlgorithmParameterSpec(new PSSParameterSpec(mGF1ParameterSpec.getDigestAlgorithm(), "MGF1", mGF1ParameterSpec, 64, 1));
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class RsaSha256 extends RsaUsingShaAlgorithm {
        public RsaSha256() {
            super(AlgorithmIdentifiers.RSA_USING_SHA256, "SHA256withRSA");
        }
    }

    /* loaded from: classes13.dex */
    public static class RsaSha384 extends RsaUsingShaAlgorithm {
        public RsaSha384() {
            super(AlgorithmIdentifiers.RSA_USING_SHA384, "SHA384withRSA");
        }
    }

    /* loaded from: classes13.dex */
    public static class RsaSha512 extends RsaUsingShaAlgorithm {
        public RsaSha512() {
            super(AlgorithmIdentifiers.RSA_USING_SHA512, "SHA512withRSA");
        }
    }

    public RsaUsingShaAlgorithm(String str, String str2) {
        super(str, str2, "RSA");
    }

    public static String f(String str) {
        return (!Security.getAlgorithms("Signature").contains(RSASSA_PSS) || Boolean.getBoolean("org.jose4j.jws.use-legacy-rsapss-alg-names")) ? str : RSASSA_PSS;
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm
    public void validatePrivateKey(PrivateKey privateKey) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize(privateKey);
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm
    public void validatePublicKey(PublicKey publicKey) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize(publicKey);
    }
}
