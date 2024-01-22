package org.jose4j.jwk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class JsonWebKeySet {
    public static final String JWK_SET_MEMBER_NAME = "keys";
    public static final Logger b = LoggerFactory.getLogger(JsonWebKeySet.class);

    /* renamed from: a  reason: collision with root package name */
    public List<JsonWebKey> f15527a;

    public JsonWebKeySet(String str) throws JoseException {
        List<Map> list = (List) JsonUtil.parseJson(str).get(JWK_SET_MEMBER_NAME);
        if (list != null) {
            this.f15527a = new ArrayList(list.size());
            for (Map map : list) {
                try {
                    this.f15527a.add(JsonWebKey.Factory.newJwk(map));
                } catch (Exception e) {
                    b.debug("Ignoring an individual JWK in a JWKS due to a problem processing it ({}). JWK params: {} and the full JWKS content: {}.", ExceptionHelp.toStringWithCauses(e), map, str);
                }
            }
            return;
        }
        throw new JoseException("The JSON JWKS content does not include the keys member.");
    }

    public void addJsonWebKey(JsonWebKey jsonWebKey) {
        this.f15527a.add(jsonWebKey);
    }

    public JsonWebKey findJsonWebKey(String str, String str2, String str3, String str4) {
        List<JsonWebKey> findJsonWebKeys = findJsonWebKeys(str, str2, str3, str4);
        if (findJsonWebKeys.isEmpty()) {
            return null;
        }
        return findJsonWebKeys.iterator().next();
    }

    public List<JsonWebKey> findJsonWebKeys(String str, String str2, String str3, String str4) {
        ArrayList arrayList = new ArrayList();
        for (JsonWebKey jsonWebKey : this.f15527a) {
            boolean equals = str != null ? str.equals(jsonWebKey.getKeyId()) : true;
            if (str3 != null) {
                equals &= str3.equals(jsonWebKey.getUse());
            }
            if (str2 != null) {
                equals &= str2.equals(jsonWebKey.getKeyType());
            }
            if (str4 != null) {
                equals &= str4.equals(jsonWebKey.getAlgorithm());
            }
            if (equals) {
                arrayList.add(jsonWebKey);
            }
        }
        return arrayList;
    }

    public List<JsonWebKey> getJsonWebKeys() {
        return this.f15527a;
    }

    public String toJson() {
        return toJson(JsonWebKey.OutputControlLevel.INCLUDE_SYMMETRIC);
    }

    public String toJson(JsonWebKey.OutputControlLevel outputControlLevel) {
        ArrayList arrayList = new ArrayList(this.f15527a.size());
        for (JsonWebKey jsonWebKey : this.f15527a) {
            arrayList.add(jsonWebKey.toParams(outputControlLevel));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(JWK_SET_MEMBER_NAME, arrayList);
        return JsonUtil.toJson(linkedHashMap);
    }

    public JsonWebKeySet(JsonWebKey... jsonWebKeyArr) {
        this(Arrays.asList(jsonWebKeyArr));
    }

    public JsonWebKeySet(List<? extends JsonWebKey> list) {
        this.f15527a = new ArrayList(list.size());
        for (JsonWebKey jsonWebKey : list) {
            this.f15527a.add(jsonWebKey);
        }
    }
}
