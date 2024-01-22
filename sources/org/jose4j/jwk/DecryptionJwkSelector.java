package org.jose4j.jwk;

import java.security.Key;
import java.util.Collection;
import java.util.List;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class DecryptionJwkSelector {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f15524a = LoggerFactory.getLogger(DecryptionJwkSelector.class);

    public JsonWebKey attemptDecryptDisambiguate(JsonWebEncryption jsonWebEncryption, List<JsonWebKey> list) {
        Key key;
        for (JsonWebKey jsonWebKey : list) {
            if (jsonWebKey instanceof PublicJsonWebKey) {
                key = ((PublicJsonWebKey) jsonWebKey).getPrivateKey();
            } else {
                key = jsonWebKey.getKey();
            }
            if (key != null) {
                jsonWebEncryption.setKey(key);
                try {
                    if (jsonWebEncryption.getPlaintextBytes() != null) {
                        return jsonWebKey;
                    }
                } catch (JoseException e) {
                    f15524a.debug("Not using key (kid={}) b/c attempt to decrypt failed trying to disambiguate ({}).", jsonWebKey.getKeyId(), ExceptionHelp.toStringWithCauses(e));
                }
            }
        }
        return null;
    }

    public JsonWebKey select(JsonWebEncryption jsonWebEncryption, Collection<JsonWebKey> collection) throws JoseException {
        List<JsonWebKey> selectList = selectList(jsonWebEncryption, collection);
        if (selectList.isEmpty()) {
            return null;
        }
        return selectList.get(0);
    }

    public List<JsonWebKey> selectList(JsonWebEncryption jsonWebEncryption, Collection<JsonWebKey> collection) throws JoseException {
        return a.b(jsonWebEncryption).filter(collection);
    }
}
