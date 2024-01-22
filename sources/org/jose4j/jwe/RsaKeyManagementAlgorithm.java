package org.jose4j.jwe;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
/* loaded from: classes13.dex */
public class RsaKeyManagementAlgorithm extends WrappingKeyManagementAlgorithm {

    /* loaded from: classes13.dex */
    public static class Rsa1_5 extends RsaKeyManagementAlgorithm {
        public Rsa1_5() {
            super("RSA/ECB/PKCS1Padding", KeyManagementAlgorithmIdentifiers.RSA1_5);
        }
    }

    /* loaded from: classes13.dex */
    public static class RsaOaep extends RsaKeyManagementAlgorithm {
        public RsaOaep() {
            super("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", KeyManagementAlgorithmIdentifiers.RSA_OAEP);
        }
    }

    /* loaded from: classes13.dex */
    public static class RsaOaep256 extends RsaKeyManagementAlgorithm {
        public RsaOaep256() {
            super("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            setAlgorithmParameterSpec(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        }

        @Override // org.jose4j.jwe.RsaKeyManagementAlgorithm, org.jose4j.jwa.Algorithm
        public boolean isAvailable() {
            try {
                return manageForEncrypt(JsonWebKey.Factory.newJwk("{\"kty\":\"RSA\",\"n\":\"sXchDaQebHnPiGvyDOAT4saGEUetSyo9MKLOoWFsueri23bOdgWp4Dy1WlUzewbgBHod5pcM9H95GQRV3JDXboIRROSBigeC5yjU1hGzHHyXss8UDprecbAYxknTcQkhslANGRUZmdTOQ5qTRsLAt6BTYuyvVRdhS8exSZEy_c4gs_7svlJJQ4H9_NxsiIoLwAEk7-Q3UXERGYw_75IDrGA84-lA_-Ct4eTlXHBIY2EaV7t7LjJaynVJCpkv4LKjTTAumiGUIuQhrNhZLuF_RJLqHpM2kgWFLU7-VTdL1VbC2tejvcI2BlMkEpk1BzBZI0KQB0GaDWFLN-aEAw3vRw\",\"e\":\"AQAB\"}").getKey(), new ContentEncryptionKeyDescriptor(16, AesKey.ALGORITHM), null, null, new ProviderContext()) != null;
            } catch (JoseException e) {
                Logger logger = ((WrappingKeyManagementAlgorithm) this).log;
                logger.debug(getAlgorithmIdentifier() + " is not available due to " + ExceptionHelp.toStringWithCauses(e));
                return false;
            }
        }
    }

    public RsaKeyManagementAlgorithm(String str, String str2) {
        super(str, str2);
        setKeyType("RSA");
        setKeyPersuasion(KeyPersuasion.ASYMMETRIC);
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        try {
            return CipherUtil.a(getJavaAlgorithm(), null) != null;
        } catch (JoseException unused) {
            return false;
        }
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize((PrivateKey) KeyValidationSupport.castKey(key, PrivateKey.class));
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize((PublicKey) KeyValidationSupport.castKey(key, PublicKey.class));
    }
}
