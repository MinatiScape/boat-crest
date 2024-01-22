package org.jose4j.jwk;

import java.security.Key;
import java.security.PublicKey;
import java.security.interfaces.EdECKey;
import java.security.interfaces.XECKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.keys.EdDsaKeyUtil;
import org.jose4j.keys.OctetKeyPairUtil;
import org.jose4j.keys.XDHKeyUtil;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UncheckedJoseException;
/* loaded from: classes13.dex */
public class OctetKeyPairJsonWebKey extends PublicJsonWebKey {
    public static final Set<String> APPLICABLE_KEY_ALGORITHMS = new HashSet(Arrays.asList("Ed448", "Ed25519", AlgorithmIdentifiers.EDDSA, "X25519", "X448", "XDH"));
    public static final String KEY_TYPE = "OKP";
    public static final String PRIVATE_KEY_MEMBER_NAME = "d";
    public static final String PUBLIC_KEY_MEMBER_NAME = "x";
    public static final String SUBTYPE_ED25519 = "Ed25519";
    public static final String SUBTYPE_ED448 = "Ed448";
    public static final String SUBTYPE_MEMBER_NAME = "crv";
    public static final String SUBTYPE_X25519 = "X25519";
    public static final String SUBTYPE_X448 = "X448";
    private final String subtype;

    public OctetKeyPairJsonWebKey(PublicKey publicKey) {
        super(publicKey);
        if (XDHKeyUtil.isXECPublicKey(publicKey)) {
            this.subtype = ((XECKey) publicKey).getParams().getName();
        } else if (EdDsaKeyUtil.isEdECPublicKey(publicKey)) {
            this.subtype = ((EdECKey) publicKey).getParams().getName();
        } else {
            throw new UncheckedJoseException("Unable to determine OKP subtype from " + publicKey);
        }
    }

    public static boolean isApplicable(Key key) {
        return APPLICABLE_KEY_ALGORITHMS.contains(key.getAlgorithm());
    }

    @Override // org.jose4j.jwk.PublicJsonWebKey
    public void fillPrivateTypeSpecificParams(Map<String, Object> map) {
        if (this.privateKey != null) {
            map.put("d", Base64Url.encode(subtypeKeyUtil().rawPrivateKey(this.privateKey)));
        }
    }

    @Override // org.jose4j.jwk.PublicJsonWebKey
    public void fillPublicTypeSpecificParams(Map<String, Object> map) {
        byte[] rawPublicKey = subtypeKeyUtil().rawPublicKey(this.key);
        map.put("crv", this.subtype);
        map.put("x", Base64Url.encode(rawPublicKey));
    }

    @Override // org.jose4j.jwk.JsonWebKey
    public String getKeyType() {
        return KEY_TYPE;
    }

    public String getSubtype() {
        return this.subtype;
    }

    @Override // org.jose4j.jwk.JsonWebKey
    public String produceThumbprintHashInput() {
        HashMap hashMap = new HashMap();
        fillPublicTypeSpecificParams(hashMap);
        return String.format("{\"crv\":\"%s\",\"kty\":\"OKP\",\"x\":\"%s\"}", hashMap.get("crv"), hashMap.get("x"));
    }

    public OctetKeyPairUtil subtypeKeyUtil() {
        return OctetKeyPairUtil.getOctetKeyPairUtil(this.subtype, this.jcaProvider, null);
    }

    public OctetKeyPairJsonWebKey(Map<String, Object> map) throws JoseException {
        this(map, null);
    }

    public OctetKeyPairJsonWebKey(Map<String, Object> map, String str) throws JoseException {
        super(map, str);
        String string = JsonWebKey.getString(map, "crv", true);
        this.subtype = string;
        try {
            OctetKeyPairUtil subtypeKeyUtil = subtypeKeyUtil();
            if (subtypeKeyUtil != null) {
                this.key = subtypeKeyUtil.publicKey(Base64Url.decode(JsonWebKey.getString(map, "x", true)), string);
                checkForBareKeyCertMismatch();
                if (map.containsKey("d")) {
                    this.privateKey = subtypeKeyUtil.privateKey(Base64Url.decode(JsonWebKey.getString(map, "d", false)), string);
                }
                removeFromOtherParams("crv", "x", "d");
                return;
            }
            throw new InvalidKeyException("\"" + string + "\" is an unknown or unsupported subtype value for the \"crv\" parameter.");
        } catch (NoClassDefFoundError e) {
            throw new JoseException("Unable to instantiate key for OKP JWK with " + this.subtype + ". " + ExceptionHelp.toStringWithCauses(e));
        }
    }
}
