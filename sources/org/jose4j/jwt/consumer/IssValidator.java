package org.jose4j.jwt.consumer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
/* loaded from: classes13.dex */
public class IssValidator implements ErrorCodeValidator {

    /* renamed from: a  reason: collision with root package name */
    public Set<String> f15539a;
    public boolean b;

    public IssValidator(String str, boolean z) {
        if (str != null) {
            this.f15539a = Collections.singleton(str);
        }
        this.b = z;
    }

    public final String a() {
        if (this.f15539a.size() == 1) {
            return this.f15539a.iterator().next();
        }
        return "one of " + this.f15539a;
    }

    @Override // org.jose4j.jwt.consumer.ErrorCodeValidator
    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        String issuer = jwtContext.getJwtClaims().getIssuer();
        if (issuer == null) {
            if (this.b) {
                return new ErrorCodeValidator.Error(11, "No Issuer (iss) claim present.");
            }
            return null;
        }
        Set<String> set = this.f15539a;
        if (set == null || set.contains(issuer)) {
            return null;
        }
        return new ErrorCodeValidator.Error(12, "Issuer (iss) claim value (" + issuer + ") doesn't match expected value of " + a());
    }

    public IssValidator(boolean z, String... strArr) {
        this.b = z;
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        HashSet hashSet = new HashSet();
        this.f15539a = hashSet;
        Collections.addAll(hashSet, strArr);
    }
}
