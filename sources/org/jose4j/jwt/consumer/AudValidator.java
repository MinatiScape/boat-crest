package org.jose4j.jwt.consumer;

import java.util.List;
import java.util.Set;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
/* loaded from: classes13.dex */
public class AudValidator implements ErrorCodeValidator {
    public static final ErrorCodeValidator.Error c = new ErrorCodeValidator.Error(7, "No Audience (aud) claim present.");

    /* renamed from: a  reason: collision with root package name */
    public Set<String> f15536a;
    public boolean b;

    public AudValidator(Set<String> set, boolean z) {
        this.f15536a = set;
        this.b = z;
    }

    @Override // org.jose4j.jwt.consumer.ErrorCodeValidator
    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        JwtClaims jwtClaims = jwtContext.getJwtClaims();
        if (!jwtClaims.hasAudience()) {
            if (this.b) {
                return c;
            }
            return null;
        }
        List<String> audience = jwtClaims.getAudience();
        boolean z = false;
        for (String str : audience) {
            if (this.f15536a.contains(str)) {
                z = true;
            }
        }
        if (z) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Audience (aud) claim ");
        sb.append(audience);
        if (this.f15536a.isEmpty()) {
            sb.append(" present in the JWT but no expected audience value(s) were provided to the JWT Consumer.");
        } else {
            sb.append(" doesn't contain an acceptable identifier.");
        }
        sb.append(" Expected ");
        if (this.f15536a.size() == 1) {
            sb.append(this.f15536a.iterator().next());
        } else {
            sb.append("one of ");
            sb.append(this.f15536a);
        }
        sb.append(" as an aud value.");
        return new ErrorCodeValidator.Error(8, sb.toString());
    }
}
