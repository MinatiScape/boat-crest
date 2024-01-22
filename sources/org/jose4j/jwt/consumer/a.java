package org.jose4j.jwt.consumer;

import java.security.Key;
import java.util.List;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.keys.resolvers.DecryptionKeyResolver;
import org.jose4j.keys.resolvers.VerificationKeyResolver;
/* loaded from: classes13.dex */
public class a implements VerificationKeyResolver, DecryptionKeyResolver {

    /* renamed from: a  reason: collision with root package name */
    public Key f15548a;

    public a(Key key) {
        this.f15548a = key;
    }

    @Override // org.jose4j.keys.resolvers.DecryptionKeyResolver
    public Key resolveKey(JsonWebEncryption jsonWebEncryption, List<JsonWebStructure> list) {
        return this.f15548a;
    }

    @Override // org.jose4j.keys.resolvers.VerificationKeyResolver
    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) {
        return this.f15548a;
    }
}
