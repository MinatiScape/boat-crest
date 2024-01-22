package org.jose4j.jwk;

import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f15532a = {KeyOperations.VERIFY};
    public static final String[] b = {KeyOperations.DECRYPT, KeyOperations.DERIVE_KEY, KeyOperations.UNWRAP_KEY};

    public static SimpleJwkFilter a(JsonWebStructure jsonWebStructure) throws JoseException {
        SimpleJwkFilter simpleJwkFilter = new SimpleJwkFilter();
        String keyIdHeaderValue = jsonWebStructure.getKeyIdHeaderValue();
        if (keyIdHeaderValue != null) {
            simpleJwkFilter.setKid(keyIdHeaderValue, SimpleJwkFilter.VALUE_REQUIRED);
        }
        String x509CertSha1ThumbprintHeaderValue = jsonWebStructure.getX509CertSha1ThumbprintHeaderValue();
        String x509CertSha256ThumbprintHeaderValue = jsonWebStructure.getX509CertSha256ThumbprintHeaderValue();
        simpleJwkFilter.setAllowFallbackDeriveFromX5cForX5Thumbs(true);
        if (x509CertSha1ThumbprintHeaderValue != null) {
            simpleJwkFilter.setX5t(x509CertSha1ThumbprintHeaderValue, SimpleJwkFilter.OMITTED_OKAY);
        }
        if (x509CertSha256ThumbprintHeaderValue != null) {
            simpleJwkFilter.setX5tS256(x509CertSha256ThumbprintHeaderValue, SimpleJwkFilter.OMITTED_OKAY);
        }
        simpleJwkFilter.setKty(jsonWebStructure.getAlgorithmNoConstraintCheck().getKeyType());
        return simpleJwkFilter;
    }

    public static SimpleJwkFilter b(JsonWebEncryption jsonWebEncryption) throws JoseException {
        SimpleJwkFilter a2 = a(jsonWebEncryption);
        a2.setUse("enc", SimpleJwkFilter.OMITTED_OKAY);
        a2.setKeyOperations(b, SimpleJwkFilter.OMITTED_OKAY);
        return a2;
    }

    public static SimpleJwkFilter c(JsonWebSignature jsonWebSignature) throws JoseException {
        SimpleJwkFilter a2 = a(jsonWebSignature);
        a2.setUse(Use.SIGNATURE, SimpleJwkFilter.OMITTED_OKAY);
        a2.setKeyOperations(f15532a, SimpleJwkFilter.OMITTED_OKAY);
        return a2;
    }
}
