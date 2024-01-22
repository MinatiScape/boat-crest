package org.jose4j.jws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmFactoryFactory;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwx.CompactSerializer;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.IntegrityException;
import org.jose4j.lang.InvalidAlgorithmException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;
/* loaded from: classes13.dex */
public class JsonWebSignature extends JsonWebStructure {
    public static final short COMPACT_SERIALIZATION_PARTS = 3;
    public byte[] g;
    public String h = "UTF-8";
    public String i;
    public Boolean j;
    public CryptoPrimitive k;

    public JsonWebSignature() {
        if (Boolean.getBoolean("org.jose4j.jws.default-allow-none")) {
            return;
        }
        setAlgorithmConstraints(AlgorithmConstraints.DISALLOW_NONE);
    }

    public final CryptoPrimitive a() throws JoseException {
        JsonWebSignatureAlgorithm algorithm = getAlgorithm();
        Key key = getKey();
        if (isDoKeyValidation()) {
            algorithm.validateSigningKey(key);
        }
        return algorithm.prepareForSign(key, getProviderCtx());
    }

    public final JsonWebSignatureAlgorithm b(boolean z) throws InvalidAlgorithmException {
        String algorithmHeaderValue = getAlgorithmHeaderValue();
        if (algorithmHeaderValue != null) {
            if (z) {
                getAlgorithmConstraints().checkConstraint(algorithmHeaderValue);
            }
            return AlgorithmFactoryFactory.getInstance().getJwsAlgorithmFactory().getAlgorithm(algorithmHeaderValue);
        }
        throw new InvalidAlgorithmException("Signature algorithm header (alg) not set.");
    }

    public final byte[] c() throws JoseException {
        if (!isRfc7797UnencodedPayload()) {
            return StringUtil.getBytesAscii(CompactSerializer.serialize(getEncodedHeader(), getEncodedPayload()));
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(StringUtil.getBytesAscii(getEncodedHeader()));
            byteArrayOutputStream.write(46);
            byteArrayOutputStream.write(this.g);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new JoseException("This should never happen from a ByteArrayOutputStream", e);
        }
    }

    public final String d() {
        return StringUtil.newString(this.g, this.h);
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public String getCompactSerialization() throws JoseException {
        String encodedPayload;
        sign();
        if (isRfc7797UnencodedPayload()) {
            encodedPayload = d();
            if (encodedPayload.contains(".")) {
                throw new JoseException("per https://tools.ietf.org/html/rfc7797#section-5.2 when using the JWS Compact Serialization, unencoded non-detached payloads using period ('.') characters would cause parsing errors; such payloads MUST NOT be used with the JWS Compact Serialization.");
            }
        } else {
            encodedPayload = getEncodedPayload();
        }
        return CompactSerializer.serialize(getEncodedHeader(), encodedPayload, getEncodedSignature());
    }

    public String getDetachedContentCompactSerialization() throws JoseException {
        sign();
        return CompactSerializer.serialize(getEncodedHeader(), "", getEncodedSignature());
    }

    public String getEncodedPayload() {
        String str = this.i;
        return str != null ? str : this.base64url.base64UrlEncode(this.g);
    }

    public String getEncodedSignature() {
        return this.base64url.base64UrlEncode(getSignature());
    }

    public KeyPersuasion getKeyPersuasion() throws InvalidAlgorithmException {
        return getAlgorithmNoConstraintCheck().getKeyPersuasion();
    }

    public String getKeyType() throws InvalidAlgorithmException {
        return getAlgorithmNoConstraintCheck().getKeyType();
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public String getPayload() throws JoseException {
        if (!Boolean.getBoolean("org.jose4j.jws.getPayload-skip-verify") && !verifySignature()) {
            throw new IntegrityException("JWS signature is invalid.");
        }
        return d();
    }

    public byte[] getPayloadBytes() throws JoseException {
        if (verifySignature()) {
            return this.g;
        }
        throw new IntegrityException("JWS signature is invalid.");
    }

    public String getPayloadCharEncoding() {
        return this.h;
    }

    public byte[] getSignature() {
        return getIntegrity();
    }

    public String getUnverifiedPayload() {
        return d();
    }

    public byte[] getUnverifiedPayloadBytes() {
        return this.g;
    }

    public boolean isRfc7797UnencodedPayload() {
        Object objectHeaderValue = this.headers.getObjectHeaderValue(HeaderParameterNames.BASE64URL_ENCODE_PAYLOAD);
        return (objectHeaderValue == null || !(objectHeaderValue instanceof Boolean) || ((Boolean) objectHeaderValue).booleanValue()) ? false : true;
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public boolean isSupportedCriticalHeader(String str) {
        return HeaderParameterNames.BASE64URL_ENCODE_PAYLOAD.equals(str);
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public void onNewKey() {
        this.j = null;
    }

    public CryptoPrimitive prepareSigningPrimitive() throws JoseException {
        CryptoPrimitive a2 = a();
        this.k = a2;
        return a2;
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public void setCompactSerializationParts(String[] strArr) throws JoseException {
        if (strArr.length == 3) {
            setEncodedHeader(strArr[0]);
            if (isRfc7797UnencodedPayload()) {
                setPayload(strArr[1]);
            } else {
                setEncodedPayload(strArr[1]);
            }
            setSignature(this.base64url.base64UrlDecode(strArr[2]));
            return;
        }
        throw new JoseException("A JWS Compact Serialization must have exactly 3 parts separated by period ('.') characters");
    }

    public void setEncodedPayload(String str) {
        this.i = str;
        this.g = this.base64url.base64UrlDecode(str);
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public void setPayload(String str) {
        this.g = StringUtil.getBytesUnchecked(str, this.h);
        this.i = null;
    }

    public void setPayloadBytes(byte[] bArr) {
        this.g = bArr;
    }

    public void setPayloadCharEncoding(String str) {
        this.h = str;
    }

    public void setSignature(byte[] bArr) {
        setIntegrity(bArr);
    }

    public void sign() throws JoseException {
        CryptoPrimitive cryptoPrimitive = this.k;
        if (cryptoPrimitive == null) {
            cryptoPrimitive = a();
        }
        setSignature(getAlgorithm().sign(cryptoPrimitive, c()));
    }

    public boolean verifySignature() throws JoseException {
        JsonWebSignatureAlgorithm algorithm = getAlgorithm();
        Key key = getKey();
        if (isDoKeyValidation()) {
            algorithm.validateVerificationKey(key);
        }
        if (this.j == null) {
            checkCrit();
            this.j = Boolean.valueOf(algorithm.verifySignature(getSignature(), key, c(), getProviderCtx()));
        }
        return this.j.booleanValue();
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public JsonWebSignatureAlgorithm getAlgorithm() throws InvalidAlgorithmException {
        return b(true);
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public JsonWebSignatureAlgorithm getAlgorithmNoConstraintCheck() throws InvalidAlgorithmException {
        return b(false);
    }
}
