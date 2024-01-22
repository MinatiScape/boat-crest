package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.DecryptionJwkSelector;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;
/* loaded from: classes13.dex */
public class JwksDecryptionKeyResolver implements DecryptionKeyResolver {

    /* renamed from: a  reason: collision with root package name */
    public final List<JsonWebKey> f15556a;
    public final DecryptionJwkSelector b = new DecryptionJwkSelector();
    public boolean c;

    public JwksDecryptionKeyResolver(List<JsonWebKey> list) {
        this.f15556a = list;
    }

    @Override // org.jose4j.keys.resolvers.DecryptionKeyResolver
    public Key resolveKey(JsonWebEncryption jsonWebEncryption, List<JsonWebStructure> list) throws UnresolvableKeyException {
        JsonWebKey jsonWebKey;
        try {
            List<JsonWebKey> selectList = this.b.selectList(jsonWebEncryption, this.f15556a);
            if (selectList.isEmpty()) {
                jsonWebKey = null;
            } else {
                if (selectList.size() != 1 && this.c) {
                    jsonWebKey = this.b.attemptDecryptDisambiguate(jsonWebEncryption, selectList);
                    if (jsonWebKey == null) {
                        throw new UnresolvableKeyException("Unable to find a suitable key for JWE w/ header " + jsonWebEncryption.getHeaders().getFullHeaderAsJsonString() + " using attempted decryption to disambiguate from filtered candidate JWKs " + this.f15556a);
                    }
                }
                jsonWebKey = selectList.get(0);
            }
            if (jsonWebKey != null) {
                return jsonWebKey instanceof PublicJsonWebKey ? ((PublicJsonWebKey) jsonWebKey).getPrivateKey() : jsonWebKey.getKey();
            }
            throw new UnresolvableKeyException("Unable to find a suitable key for JWE w/ header " + jsonWebEncryption.getHeaders().getFullHeaderAsJsonString() + " from JWKs " + this.f15556a);
        } catch (JoseException e) {
            throw new UnresolvableKeyException("Unable to find a suitable key for JWE w/ header " + jsonWebEncryption.getHeaders().getFullHeaderAsJsonString() + " due to an unexpected exception (" + e + ") selecting from keys: " + this.f15556a, e);
        }
    }

    public void setDisambiguateWithAttemptDecrypt(boolean z) {
        this.c = z;
    }
}
