package org.jose4j.jwk;

import java.util.Collection;
import java.util.List;
import org.jose4j.jws.EcdsaUsingShaAlgorithm;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class VerificationJwkSelector {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f15531a = {"Ed25519", "Ed448"};

    public final boolean a(List<JsonWebKey> list) {
        return list.size() > 1;
    }

    public JsonWebKey select(JsonWebSignature jsonWebSignature, Collection<JsonWebKey> collection) throws JoseException {
        List<JsonWebKey> selectList = selectList(jsonWebSignature, collection);
        if (selectList.isEmpty()) {
            return null;
        }
        return selectList.get(0);
    }

    public List<JsonWebKey> selectList(JsonWebSignature jsonWebSignature, Collection<JsonWebKey> collection) throws JoseException {
        SimpleJwkFilter c = a.c(jsonWebSignature);
        List<JsonWebKey> filter = c.filter(collection);
        if (a(filter)) {
            c.setAlg(jsonWebSignature.getAlgorithmHeaderValue(), SimpleJwkFilter.OMITTED_OKAY);
            filter = c.filter(filter);
        }
        if (a(filter)) {
            String keyType = jsonWebSignature.getKeyType();
            if ("EC".equals(keyType)) {
                c.setCrv(((EcdsaUsingShaAlgorithm) jsonWebSignature.getAlgorithmNoConstraintCheck()).getCurveName(), SimpleJwkFilter.OMITTED_OKAY);
                return c.filter(filter);
            } else if (OctetKeyPairJsonWebKey.KEY_TYPE.equals(keyType)) {
                c.setCrvs(f15531a, SimpleJwkFilter.OMITTED_OKAY);
                return c.filter(filter);
            } else {
                return filter;
            }
        }
        return filter;
    }

    public JsonWebKey selectWithVerifySignatureDisambiguate(JsonWebSignature jsonWebSignature, Collection<JsonWebKey> collection) throws JoseException {
        List<JsonWebKey> selectList = selectList(jsonWebSignature, collection);
        if (selectList.isEmpty()) {
            return null;
        }
        if (selectList.size() == 1) {
            return selectList.get(0);
        }
        for (JsonWebKey jsonWebKey : selectList) {
            jsonWebSignature.setKey(jsonWebKey.getKey());
            if (jsonWebSignature.verifySignature()) {
                return jsonWebKey;
            }
        }
        return null;
    }
}
