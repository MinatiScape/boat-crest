package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;
/* loaded from: classes13.dex */
public class EmbeddedJwkVerificationKeyResolver implements VerificationKeyResolver {

    /* renamed from: a  reason: collision with root package name */
    public PublicJsonWebKey f15554a;

    public PublicJsonWebKey getJwk() {
        return this.f15554a;
    }

    @Override // org.jose4j.keys.resolvers.VerificationKeyResolver
    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException {
        try {
            PublicJsonWebKey jwkHeader = jsonWebSignature.getJwkHeader();
            this.f15554a = jwkHeader;
            if (jwkHeader != null) {
                return jwkHeader.getKey();
            }
            throw new UnresolvableKeyException("No jwk in JWS header");
        } catch (JoseException e) {
            throw new UnresolvableKeyException("Problem processing jwk from JWS header (" + e.getMessage() + ")", e);
        }
    }
}
