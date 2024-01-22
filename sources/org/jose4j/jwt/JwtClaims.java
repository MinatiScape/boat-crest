package org.jose4j.jwt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jose4j.base64url.Base64Url;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class JwtClaims {

    /* renamed from: a  reason: collision with root package name */
    public Map<String, Object> f15534a;
    public String b;

    public JwtClaims() {
        this.f15534a = new LinkedHashMap();
    }

    public static JwtClaims parse(String str, JwtContext jwtContext) throws InvalidJwtException {
        return new JwtClaims(str, jwtContext);
    }

    public final String a(ClassCastException classCastException, Object obj) {
        return "(" + obj + " - " + classCastException.getMessage() + ")";
    }

    public final void b(String str, String str2, Object obj, Map<String, List<Object>> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(str == null ? "" : str + ".");
        sb.append(str2);
        String sb2 = sb.toString();
        if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (List) obj) {
                if (obj2 instanceof Map) {
                    for (Map.Entry entry : ((Map) obj2).entrySet()) {
                        b(sb2, entry.getKey().toString(), entry.getValue(), map);
                    }
                } else {
                    arrayList.add(obj2);
                }
            }
            map.put(sb2, arrayList);
        } else if (obj instanceof Map) {
            for (Map.Entry entry2 : ((Map) obj).entrySet()) {
                b(sb2, entry2.getKey().toString(), entry2.getValue(), map);
            }
        } else {
            map.put(sb2, Collections.singletonList(obj));
        }
    }

    public final NumericDate c(float f) {
        NumericDate now = NumericDate.now();
        now.addSeconds(f * 60.0f);
        return now;
    }

    public final List<String> d(List list, String str) throws MalformedClaimException {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            try {
                arrayList.add((String) obj);
            } catch (ClassCastException e) {
                throw new MalformedClaimException("The array value of the '" + str + "' claim contains non string values " + a(e, obj), e);
            }
        }
        return arrayList;
    }

    public Map<String, List<Object>> flattenClaims() {
        return flattenClaims(null);
    }

    public List<String> getAudience() throws MalformedClaimException {
        Object obj = this.f15534a.get(ReservedClaimNames.AUDIENCE);
        if (obj instanceof String) {
            return Collections.singletonList((String) obj);
        }
        if (!(obj instanceof List) && obj != null) {
            throw new MalformedClaimException("The value of the 'aud' claim is not an array of strings or a single string value.");
        }
        return d((List) obj, ReservedClaimNames.AUDIENCE);
    }

    public Collection<String> getClaimNames(Set<String> set) {
        return getClaimsMap(set).keySet();
    }

    public <T> T getClaimValue(String str, Class<T> cls) throws MalformedClaimException {
        Object obj = this.f15534a.get(str);
        try {
            return cls.cast(obj);
        } catch (ClassCastException e) {
            throw new MalformedClaimException("The value of the '" + str + "' claim is not the expected type " + a(e, obj), e);
        }
    }

    public String getClaimValueAsString(String str) {
        Object claimValue = getClaimValue(str);
        if (claimValue != null) {
            return claimValue.toString();
        }
        return null;
    }

    public Map<String, Object> getClaimsMap(Set<String> set) {
        if (set == null) {
            set = Collections.emptySet();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.f15534a);
        for (String str : set) {
            linkedHashMap.remove(str);
        }
        return linkedHashMap;
    }

    public NumericDate getExpirationTime() throws MalformedClaimException {
        return getNumericDateClaimValue(ReservedClaimNames.EXPIRATION_TIME);
    }

    public NumericDate getIssuedAt() throws MalformedClaimException {
        return getNumericDateClaimValue(ReservedClaimNames.ISSUED_AT);
    }

    public String getIssuer() throws MalformedClaimException {
        return (String) getClaimValue(ReservedClaimNames.ISSUER, String.class);
    }

    public String getJwtId() throws MalformedClaimException {
        return (String) getClaimValue(ReservedClaimNames.JWT_ID, String.class);
    }

    public NumericDate getNotBefore() throws MalformedClaimException {
        return getNumericDateClaimValue(ReservedClaimNames.NOT_BEFORE);
    }

    public NumericDate getNumericDateClaimValue(String str) throws MalformedClaimException {
        Number number = (Number) getClaimValue(str, Number.class);
        if (!(number instanceof BigInteger)) {
            if (number != null) {
                return NumericDate.fromSeconds(number.longValue());
            }
            return null;
        }
        throw new MalformedClaimException(number + " is unreasonable for a NumericDate");
    }

    public String getRawJson() {
        return this.b;
    }

    public String getStringClaimValue(String str) throws MalformedClaimException {
        return (String) getClaimValue(str, String.class);
    }

    public List<String> getStringListClaimValue(String str) throws MalformedClaimException {
        return d((List) getClaimValue(str, List.class), str);
    }

    public String getSubject() throws MalformedClaimException {
        return (String) getClaimValue(ReservedClaimNames.SUBJECT, String.class);
    }

    public boolean hasAudience() {
        return hasClaim(ReservedClaimNames.AUDIENCE);
    }

    public boolean hasClaim(String str) {
        return getClaimValue(str) != null;
    }

    public boolean isClaimValueOfType(String str, Class cls) {
        try {
            return getClaimValue(str, cls) != null;
        } catch (MalformedClaimException unused) {
            return false;
        }
    }

    public boolean isClaimValueString(String str) {
        return isClaimValueOfType(str, String.class);
    }

    public boolean isClaimValueStringList(String str) {
        try {
            if (hasClaim(str)) {
                return getStringListClaimValue(str) != null;
            }
            return false;
        } catch (MalformedClaimException unused) {
            return false;
        }
    }

    public void setAudience(String str) {
        this.f15534a.put(ReservedClaimNames.AUDIENCE, str);
    }

    public void setClaim(String str, Object obj) {
        this.f15534a.put(str, obj);
    }

    public void setExpirationTime(NumericDate numericDate) {
        setNumericDateClaim(ReservedClaimNames.EXPIRATION_TIME, numericDate);
    }

    public void setExpirationTimeMinutesInTheFuture(float f) {
        setExpirationTime(c(f));
    }

    public void setGeneratedJwtId(int i) {
        setJwtId(Base64Url.encode(ByteUtil.randomBytes(i)));
    }

    public void setIssuedAt(NumericDate numericDate) {
        setNumericDateClaim(ReservedClaimNames.ISSUED_AT, numericDate);
    }

    public void setIssuedAtToNow() {
        setIssuedAt(NumericDate.now());
    }

    public void setIssuer(String str) {
        this.f15534a.put(ReservedClaimNames.ISSUER, str);
    }

    public void setJwtId(String str) {
        this.f15534a.put(ReservedClaimNames.JWT_ID, str);
    }

    public void setNotBefore(NumericDate numericDate) {
        setNumericDateClaim(ReservedClaimNames.NOT_BEFORE, numericDate);
    }

    public void setNotBeforeMinutesInThePast(float f) {
        setNotBefore(c(f * (-1.0f)));
    }

    public void setNumericDateClaim(String str, NumericDate numericDate) {
        this.f15534a.put(str, numericDate != null ? Long.valueOf(numericDate.getValue()) : null);
    }

    public void setStringClaim(String str, String str2) {
        this.f15534a.put(str, str2);
    }

    public void setStringListClaim(String str, List<String> list) {
        this.f15534a.put(str, list);
    }

    public void setSubject(String str) {
        this.f15534a.put(ReservedClaimNames.SUBJECT, str);
    }

    public String toJson() {
        return JsonUtil.toJson(this.f15534a);
    }

    public String toString() {
        return "JWT Claims Set:" + this.f15534a;
    }

    public void unsetClaim(String str) {
        this.f15534a.remove(str);
    }

    public static JwtClaims parse(String str) throws InvalidJwtException {
        return new JwtClaims(str, null);
    }

    public Map<String, List<Object>> flattenClaims(Set<String> set) {
        if (set == null) {
            set = Collections.emptySet();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : this.f15534a.entrySet()) {
            String key = entry.getKey();
            if (!set.contains(key)) {
                b(null, key, entry.getValue(), linkedHashMap);
            }
        }
        return linkedHashMap;
    }

    public Collection<String> getClaimNames() {
        return getClaimNames(null);
    }

    public void setAudience(String... strArr) {
        setAudience(Arrays.asList(strArr));
    }

    public void setStringListClaim(String str, String... strArr) {
        this.f15534a.put(str, Arrays.asList(strArr));
    }

    public JwtClaims(String str, JwtContext jwtContext) throws InvalidJwtException {
        this.b = str;
        try {
            this.f15534a = new LinkedHashMap(JsonUtil.parseJson(str));
        } catch (JoseException e) {
            throw new InvalidJwtException("Unable to parse what was expected to be the JWT Claim Set JSON: \"" + str + "\"", new ErrorCodeValidator.Error(16, "Invalid JSON."), e, jwtContext);
        }
    }

    public void setAudience(List<String> list) {
        if (list.size() == 1) {
            setAudience(list.get(0));
        } else {
            this.f15534a.put(ReservedClaimNames.AUDIENCE, list);
        }
    }

    public Object getClaimValue(String str) {
        return this.f15534a.get(str);
    }

    public void setGeneratedJwtId() {
        setGeneratedJwtId(16);
    }

    public Map<String, Object> getClaimsMap() {
        return getClaimsMap(null);
    }
}
