package org.jose4j.jwx;

import java.util.LinkedHashMap;
import java.util.Map;
import org.jose4j.base64url.Base64Url;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.JsonHelp;
/* loaded from: classes13.dex */
public class Headers {
    public String b;
    public String c;
    public Base64Url base64url = new Base64Url();

    /* renamed from: a  reason: collision with root package name */
    public Map<String, Object> f15549a = new LinkedHashMap();

    public void a(String str) throws JoseException {
        this.c = str;
        String base64UrlDecodeToUtf8String = this.base64url.base64UrlDecodeToUtf8String(str);
        this.b = base64UrlDecodeToUtf8String;
        this.f15549a = JsonUtil.parseJson(base64UrlDecodeToUtf8String);
    }

    public String getEncodedHeader() {
        if (this.c == null) {
            this.c = this.base64url.base64UrlEncodeUtf8ByteRepresentation(getFullHeaderAsJsonString());
        }
        return this.c;
    }

    public String getFullHeaderAsJsonString() {
        if (this.b == null) {
            this.b = JsonUtil.toJson(this.f15549a);
        }
        return this.b;
    }

    @Deprecated
    public JsonWebKey getJwkHeaderValue(String str) throws JoseException {
        return getPublicJwkHeaderValue(str, null);
    }

    public Long getLongHeaderValue(String str) {
        return JsonHelp.getLong(this.f15549a, str);
    }

    public Object getObjectHeaderValue(String str) {
        return this.f15549a.get(str);
    }

    public PublicJsonWebKey getPublicJwkHeaderValue(String str, String str2) throws JoseException {
        Map map = (Map) getObjectHeaderValue(str);
        if (map != null) {
            PublicJsonWebKey newPublicJwk = PublicJsonWebKey.Factory.newPublicJwk(map, str2);
            if (newPublicJwk.getPrivateKey() == null) {
                return newPublicJwk;
            }
            throw new JoseException(str + " header contains a private key, which it most definitely should not.");
        }
        return null;
    }

    public String getStringHeaderValue(String str) {
        return JsonHelp.getString(this.f15549a, str);
    }

    public void setFullHeaderAsJsonString(String str) throws JoseException {
        this.c = null;
        this.b = str;
        this.f15549a = JsonUtil.parseJson(str);
    }

    public void setJwkHeaderValue(String str, JsonWebKey jsonWebKey) {
        setObjectHeaderValue(str, jsonWebKey.toParams(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
    }

    public void setObjectHeaderValue(String str, Object obj) {
        this.f15549a.put(str, obj);
        this.b = null;
        this.c = null;
    }

    public void setStringHeaderValue(String str, String str2) {
        setObjectHeaderValue(str, str2);
    }
}
