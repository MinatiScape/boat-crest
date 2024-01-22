package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.VerificationJwkSelector;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;
/* loaded from: classes13.dex */
public class JwksVerificationKeyResolver implements VerificationKeyResolver {

    /* renamed from: a  reason: collision with root package name */
    public List<JsonWebKey> f15557a;
    public VerificationJwkSelector b = new VerificationJwkSelector();
    public boolean c;

    public JwksVerificationKeyResolver(List<JsonWebKey> list) {
        this.f15557a = list;
    }

    @Override // org.jose4j.keys.resolvers.VerificationKeyResolver
    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException {
        JsonWebKey select;
        try {
            if (this.c) {
                select = this.b.selectWithVerifySignatureDisambiguate(jsonWebSignature, this.f15557a);
            } else {
                select = this.b.select(jsonWebSignature, this.f15557a);
            }
            if (select != null) {
                return select.getKey();
            }
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " from JWKs " + this.f15557a);
        } catch (JoseException e) {
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " due to an unexpected exception (" + e + ") selecting from keys: " + this.f15557a, e);
        }
    }

    public void setDisambiguateWithVerifySignature(boolean z) {
        this.c = z;
    }
}
