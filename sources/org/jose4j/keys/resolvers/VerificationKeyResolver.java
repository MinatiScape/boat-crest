package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.UnresolvableKeyException;
/* loaded from: classes13.dex */
public interface VerificationKeyResolver {
    Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException;
}
