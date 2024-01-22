package org.jose4j.jwx;

import java.security.Key;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.Algorithm;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.keys.X509Util;
import org.jose4j.lang.InvalidAlgorithmException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public abstract class JsonWebStructure {
    public static final ProviderContext f = new ProviderContext();

    /* renamed from: a  reason: collision with root package name */
    public byte[] f15550a;
    public Key b;
    public String rawCompactSerialization;
    public Base64Url base64url = new Base64Url();
    public Headers headers = new Headers();
    public boolean doKeyValidation = true;
    public AlgorithmConstraints c = AlgorithmConstraints.NO_CONSTRAINTS;
    public Set<String> d = Collections.emptySet();
    public ProviderContext e = f;

    public static JsonWebStructure fromCompactSerialization(String str) throws JoseException {
        JsonWebStructure jsonWebSignature;
        String[] deserialize = CompactSerializer.deserialize(str);
        if (deserialize.length == 5) {
            jsonWebSignature = new JsonWebEncryption();
        } else if (deserialize.length == 3) {
            jsonWebSignature = new JsonWebSignature();
        } else {
            throw new JoseException("Invalid JOSE Compact Serialization. Expecting either 3 or 5 parts for JWS or JWE respectively but was " + deserialize.length + ".");
        }
        jsonWebSignature.setCompactSerializationParts(deserialize);
        jsonWebSignature.rawCompactSerialization = str;
        return jsonWebSignature;
    }

    public void checkCrit() throws JoseException {
        List<String> asList;
        Object objectHeaderValue = this.headers.getObjectHeaderValue(HeaderParameterNames.CRITICAL);
        if (objectHeaderValue != null) {
            if (objectHeaderValue instanceof List) {
                asList = (List) objectHeaderValue;
            } else if (objectHeaderValue instanceof String[]) {
                asList = Arrays.asList((String[]) objectHeaderValue);
            } else {
                throw new JoseException("crit header value not an array (" + objectHeaderValue.getClass() + ").");
            }
            for (String str : asList) {
                if (!this.d.contains(str) && !isSupportedCriticalHeader(str)) {
                    throw new JoseException("Unrecognized header '" + str + "' marked as critical.");
                }
            }
        }
    }

    public void checkNotEmptyPart(String str, String str2) throws JoseException {
        if (str == null || str.length() == 0) {
            throw new JoseException("The " + str2 + " cannot be empty.");
        }
    }

    public abstract Algorithm getAlgorithm() throws InvalidAlgorithmException;

    public AlgorithmConstraints getAlgorithmConstraints() {
        return this.c;
    }

    public String getAlgorithmHeaderValue() {
        return getHeader("alg");
    }

    public abstract Algorithm getAlgorithmNoConstraintCheck() throws InvalidAlgorithmException;

    public List<X509Certificate> getCertificateChainHeaderValue() throws JoseException {
        Object objectHeaderValue = this.headers.getObjectHeaderValue("x5c");
        if (objectHeaderValue instanceof List) {
            List<String> list = (List) objectHeaderValue;
            ArrayList arrayList = new ArrayList(list.size());
            X509Util x509Util = new X509Util();
            for (String str : list) {
                arrayList.add(x509Util.fromBase64Der(str));
            }
            return arrayList;
        }
        return null;
    }

    public abstract String getCompactSerialization() throws JoseException;

    public String getContentTypeHeaderValue() {
        return getHeader(HeaderParameterNames.CONTENT_TYPE);
    }

    public String getEncodedHeader() {
        return this.headers.getEncodedHeader();
    }

    public String getHeader() {
        return getHeaders().getFullHeaderAsJsonString();
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public byte[] getIntegrity() {
        return this.f15550a;
    }

    public PublicJsonWebKey getJwkHeader() throws JoseException {
        return this.headers.getPublicJwkHeaderValue(HeaderParameterNames.JWK, null);
    }

    public Key getKey() {
        return this.b;
    }

    public String getKeyIdHeaderValue() {
        return getHeader("kid");
    }

    public X509Certificate getLeafCertificateHeaderValue() throws JoseException {
        List<X509Certificate> certificateChainHeaderValue = getCertificateChainHeaderValue();
        if (certificateChainHeaderValue == null || certificateChainHeaderValue.isEmpty()) {
            return null;
        }
        return certificateChainHeaderValue.get(0);
    }

    public Object getObjectHeader(String str) {
        return this.headers.getObjectHeaderValue(str);
    }

    public abstract String getPayload() throws JoseException;

    public ProviderContext getProviderCtx() {
        return this.e;
    }

    public String getX509CertSha1ThumbprintHeaderValue() {
        return getHeader("x5t");
    }

    public String getX509CertSha256ThumbprintHeaderValue() {
        return getHeader("x5t#S256");
    }

    public boolean isDoKeyValidation() {
        return this.doKeyValidation;
    }

    public boolean isSupportedCriticalHeader(String str) {
        return false;
    }

    public void onNewKey() {
    }

    public void setAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.c = algorithmConstraints;
    }

    public void setAlgorithmHeaderValue(String str) {
        setHeader("alg", str);
    }

    public void setCertificateChainHeaderValue(X509Certificate... x509CertificateArr) {
        ArrayList arrayList = new ArrayList();
        X509Util x509Util = new X509Util();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            arrayList.add(x509Util.toBase64(x509Certificate));
        }
        this.headers.setObjectHeaderValue("x5c", arrayList);
    }

    public void setCompactSerialization(String str) throws JoseException {
        setCompactSerializationParts(CompactSerializer.deserialize(str));
        this.rawCompactSerialization = str;
    }

    public abstract void setCompactSerializationParts(String[] strArr) throws JoseException;

    public void setContentTypeHeaderValue(String str) {
        setHeader(HeaderParameterNames.CONTENT_TYPE, str);
    }

    public void setCriticalHeaderNames(String... strArr) {
        this.headers.setObjectHeaderValue(HeaderParameterNames.CRITICAL, strArr);
    }

    public void setDoKeyValidation(boolean z) {
        this.doKeyValidation = z;
    }

    public void setEncodedHeader(String str) throws JoseException {
        checkNotEmptyPart(str, "Encoded Header");
        this.headers.a(str);
    }

    public void setHeader(String str, String str2) {
        this.headers.setStringHeaderValue(str, str2);
    }

    public void setIntegrity(byte[] bArr) {
        this.f15550a = bArr;
    }

    public void setJwkHeader(PublicJsonWebKey publicJsonWebKey) {
        this.headers.setJwkHeaderValue(HeaderParameterNames.JWK, publicJsonWebKey);
    }

    public void setKey(Key key) {
        boolean z = true;
        Key key2 = this.b;
        if (key != null ? key2 == null || !key.equals(key2) : key2 != null) {
            z = false;
        }
        if (!z) {
            onNewKey();
        }
        this.b = key;
    }

    public void setKeyIdHeaderValue(String str) {
        setHeader("kid", str);
    }

    public void setKnownCriticalHeaders(String... strArr) {
        this.d = new HashSet(Arrays.asList(strArr));
    }

    public abstract void setPayload(String str);

    public void setProviderContext(ProviderContext providerContext) {
        this.e = providerContext;
    }

    public void setX509CertSha1ThumbprintHeaderValue(String str) {
        setHeader("x5t", str);
    }

    public void setX509CertSha256ThumbprintHeaderValue(String str) {
        setHeader("x5t#S256", str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(getHeaders().getFullHeaderAsJsonString());
        if (this.rawCompactSerialization != null) {
            sb.append("->");
            sb.append(this.rawCompactSerialization);
        }
        return sb.toString();
    }

    public String getHeader(String str) {
        return this.headers.getStringHeaderValue(str);
    }

    public void setHeader(String str, Object obj) {
        this.headers.setObjectHeaderValue(str, obj);
    }

    public void setX509CertSha1ThumbprintHeaderValue(X509Certificate x509Certificate) {
        setX509CertSha1ThumbprintHeaderValue(X509Util.x5t(x509Certificate));
    }

    public void setX509CertSha256ThumbprintHeaderValue(X509Certificate x509Certificate) {
        setX509CertSha256ThumbprintHeaderValue(X509Util.x5tS256(x509Certificate));
    }
}
