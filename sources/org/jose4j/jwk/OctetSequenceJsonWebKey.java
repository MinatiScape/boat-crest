package org.jose4j.jwk;

import java.security.Key;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class OctetSequenceJsonWebKey extends JsonWebKey {
    public static final String KEY_TYPE = "oct";
    public static final String KEY_VALUE_MEMBER_NAME = "k";
    private byte[] octetSequence;

    public OctetSequenceJsonWebKey(Key key) {
        super(key);
        this.octetSequence = key.getEncoded();
    }

    private String getEncoded() {
        return Base64Url.encode(this.octetSequence);
    }

    @Override // org.jose4j.jwk.JsonWebKey
    public void fillTypeSpecificParams(Map<String, Object> map, JsonWebKey.OutputControlLevel outputControlLevel) {
        if (JsonWebKey.OutputControlLevel.INCLUDE_SYMMETRIC.compareTo(outputControlLevel) >= 0) {
            map.put(KEY_VALUE_MEMBER_NAME, getEncoded());
        }
    }

    @Override // org.jose4j.jwk.JsonWebKey
    public String getKeyType() {
        return KEY_TYPE;
    }

    public byte[] getOctetSequence() {
        return this.octetSequence;
    }

    @Override // org.jose4j.jwk.JsonWebKey
    public String produceThumbprintHashInput() {
        return String.format("{\"k\":\"%s\",\"kty\":\"oct\"}", getEncoded());
    }

    public OctetSequenceJsonWebKey(Map<String, Object> map) throws JoseException {
        super(map);
        this.octetSequence = new Base64Url().base64UrlDecode(JsonWebKey.getStringRequired(map, KEY_VALUE_MEMBER_NAME));
        this.key = new SecretKeySpec(this.octetSequence, AesKey.ALGORITHM);
        removeFromOtherParams(KEY_VALUE_MEMBER_NAME);
    }
}
