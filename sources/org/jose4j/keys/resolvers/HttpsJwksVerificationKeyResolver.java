package org.jose4j.keys.resolvers;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.VerificationJwkSelector;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class HttpsJwksVerificationKeyResolver implements VerificationKeyResolver {
    public static final Logger d = LoggerFactory.getLogger(HttpsJwksVerificationKeyResolver.class);

    /* renamed from: a  reason: collision with root package name */
    public VerificationJwkSelector f15555a = new VerificationJwkSelector();
    public HttpsJwks b;
    public boolean c;

    public HttpsJwksVerificationKeyResolver(HttpsJwks httpsJwks) {
        this.b = httpsJwks;
    }

    @Override // org.jose4j.keys.resolvers.VerificationKeyResolver
    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException {
        try {
            List<JsonWebKey> jsonWebKeys = this.b.getJsonWebKeys();
            JsonWebKey select = select(jsonWebSignature, jsonWebKeys);
            if (select == null) {
                d.debug("Refreshing JWKs from {} as no suitable verification key for JWS w/ header {} was found in {}", this.b.getLocation(), jsonWebSignature.getHeaders().getFullHeaderAsJsonString(), jsonWebKeys);
                this.b.refresh();
                jsonWebKeys = this.b.getJsonWebKeys();
                select = select(jsonWebSignature, jsonWebKeys);
            }
            if (select != null) {
                return select.getKey();
            }
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " from JWKs " + jsonWebKeys + " obtained from " + this.b.getLocation());
        } catch (IOException | JoseException e) {
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " due to an unexpected exception (" + e + ") while obtaining or using keys from JWKS endpoint at " + this.b.getLocation(), e);
        }
    }

    public JsonWebKey select(JsonWebSignature jsonWebSignature, List<JsonWebKey> list) throws JoseException {
        if (this.c) {
            return this.f15555a.selectWithVerifySignatureDisambiguate(jsonWebSignature, list);
        }
        return this.f15555a.select(jsonWebSignature, list);
    }

    public void setDisambiguateWithVerifySignature(boolean z) {
        this.c = z;
    }
}
