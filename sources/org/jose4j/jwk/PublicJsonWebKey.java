package org.jose4j.jwk;

import java.math.BigInteger;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.keys.BigEndianBigInteger;
import org.jose4j.keys.X509Util;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.JsonHelp;
/* loaded from: classes13.dex */
public abstract class PublicJsonWebKey extends JsonWebKey {
    public static final String X509_CERTIFICATE_CHAIN_PARAMETER = "x5c";
    public static final String X509_SHA256_THUMBPRINT_PARAMETER = "x5t#S256";
    public static final String X509_THUMBPRINT_PARAMETER = "x5t";
    public static final String X509_URL_PARAMETER = "x5u";
    private List<X509Certificate> certificateChain;
    public String jcaProvider;
    public PrivateKey privateKey;
    public boolean writeOutPrivateKeyToJson;
    private String x5t;
    private String x5tS256;
    private String x5u;

    public PublicJsonWebKey(PublicKey publicKey) {
        super(publicKey);
    }

    public void checkForBareKeyCertMismatch() {
        X509Certificate leafCertificate = getLeafCertificate();
        if ((leafCertificate == null || leafCertificate.getPublicKey().equals(getPublicKey())) ? false : true) {
            throw new IllegalArgumentException("The key in the first certificate MUST match the bare public key represented by other members of the JWK. Public key = " + getPublicKey() + " cert = " + leafCertificate);
        }
    }

    public abstract void fillPrivateTypeSpecificParams(Map<String, Object> map);

    public abstract void fillPublicTypeSpecificParams(Map<String, Object> map);

    @Override // org.jose4j.jwk.JsonWebKey
    public void fillTypeSpecificParams(Map<String, Object> map, JsonWebKey.OutputControlLevel outputControlLevel) {
        fillPublicTypeSpecificParams(map);
        if (this.certificateChain != null) {
            X509Util x509Util = new X509Util();
            ArrayList arrayList = new ArrayList(this.certificateChain.size());
            for (X509Certificate x509Certificate : this.certificateChain) {
                arrayList.add(x509Util.toBase64(x509Certificate));
            }
            map.put("x5c", arrayList);
        }
        putIfNotNull("x5t", this.x5t, map);
        putIfNotNull("x5t#S256", this.x5tS256, map);
        putIfNotNull("x5u", this.x5u, map);
        if (this.writeOutPrivateKeyToJson || outputControlLevel == JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE) {
            fillPrivateTypeSpecificParams(map);
        }
    }

    public BigInteger getBigIntFromBase64UrlEncodedParam(Map<String, Object> map, String str, boolean z) throws JoseException {
        return BigEndianBigInteger.fromBase64Url(JsonWebKey.getString(map, str, z));
    }

    public List<X509Certificate> getCertificateChain() {
        return this.certificateChain;
    }

    public X509Certificate getLeafCertificate() {
        List<X509Certificate> list = this.certificateChain;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.certificateChain.get(0);
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    @Override // org.jose4j.jwk.JsonWebKey
    public PublicKey getPublicKey() {
        return (PublicKey) this.key;
    }

    public String getX509CertificateSha1Thumbprint() {
        return getX509CertificateSha1Thumbprint(false);
    }

    public String getX509CertificateSha256Thumbprint() {
        return getX509CertificateSha256Thumbprint(false);
    }

    public String getX509Url() {
        return this.x5u;
    }

    public void putBigIntAsBase64UrlEncodedParam(Map<String, Object> map, String str, BigInteger bigInteger) {
        map.put(str, BigEndianBigInteger.toBase64Url(bigInteger));
    }

    public void setCertificateChain(List<X509Certificate> list) {
        checkForBareKeyCertMismatch();
        this.certificateChain = list;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setWriteOutPrivateKeyToJson(boolean z) {
        this.writeOutPrivateKeyToJson = z;
    }

    public void setX509CertificateSha1Thumbprint(String str) {
        this.x5t = str;
    }

    public void setX509CertificateSha256Thumbprint(String str) {
        this.x5tS256 = str;
    }

    public void setX509Url(String str) {
        this.x5u = str;
    }

    public PublicJsonWebKey(Map<String, Object> map) throws JoseException {
        this(map, null);
    }

    public String getX509CertificateSha1Thumbprint(boolean z) {
        X509Certificate leafCertificate;
        String str = this.x5t;
        return (str == null && z && (leafCertificate = getLeafCertificate()) != null) ? X509Util.x5t(leafCertificate) : str;
    }

    public String getX509CertificateSha256Thumbprint(boolean z) {
        X509Certificate leafCertificate;
        String str = this.x5tS256;
        return (str == null && z && (leafCertificate = getLeafCertificate()) != null) ? X509Util.x5tS256(leafCertificate) : str;
    }

    public PublicJsonWebKey(Map<String, Object> map, String str) throws JoseException {
        super(map);
        this.jcaProvider = str;
        if (map.containsKey("x5c")) {
            List<String> stringArray = JsonHelp.getStringArray(map, "x5c");
            this.certificateChain = new ArrayList(stringArray.size());
            X509Util x509Util = X509Util.getX509Util(str);
            for (String str2 : stringArray) {
                this.certificateChain.add(x509Util.fromBase64Der(str2));
            }
        }
        this.x5t = JsonWebKey.getString(map, "x5t");
        this.x5tS256 = JsonWebKey.getString(map, "x5t#S256");
        this.x5u = JsonWebKey.getString(map, "x5u");
        removeFromOtherParams("x5c", "x5t#S256", "x5t", "x5u");
    }

    public void putBigIntAsBase64UrlEncodedParam(Map<String, Object> map, String str, BigInteger bigInteger, int i) {
        map.put(str, BigEndianBigInteger.toBase64Url(bigInteger, i));
    }

    public void setCertificateChain(X509Certificate... x509CertificateArr) {
        setCertificateChain(Arrays.asList(x509CertificateArr));
    }

    /* loaded from: classes13.dex */
    public static class Factory {
        public static PublicJsonWebKey newPublicJwk(Map<String, Object> map, String str) throws JoseException {
            String stringRequired = JsonWebKey.getStringRequired(map, JsonWebKey.KEY_TYPE_PARAMETER);
            stringRequired.hashCode();
            char c = 65535;
            switch (stringRequired.hashCode()) {
                case 2206:
                    if (stringRequired.equals("EC")) {
                        c = 0;
                        break;
                    }
                    break;
                case 78324:
                    if (stringRequired.equals(OctetKeyPairJsonWebKey.KEY_TYPE)) {
                        c = 1;
                        break;
                    }
                    break;
                case 81440:
                    if (stringRequired.equals("RSA")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return new EllipticCurveJsonWebKey(map, str);
                case 1:
                    return new OctetKeyPairJsonWebKey(map, str);
                case 2:
                    return new RsaJsonWebKey(map, str);
                default:
                    throw new JoseException("Unknown key type (for public keys): '" + stringRequired + "'");
            }
        }

        public static PublicJsonWebKey newPublicJwk(Map<String, Object> map) throws JoseException {
            return newPublicJwk(map, (String) null);
        }

        public static PublicJsonWebKey newPublicJwk(Key key) throws JoseException {
            return (PublicJsonWebKey) JsonWebKey.Factory.newJwk(key);
        }

        public static PublicJsonWebKey newPublicJwk(String str) throws JoseException {
            return newPublicJwk(str, (String) null);
        }

        public static PublicJsonWebKey newPublicJwk(String str, String str2) throws JoseException {
            return newPublicJwk(JsonUtil.parseJson(str), str2);
        }
    }
}
