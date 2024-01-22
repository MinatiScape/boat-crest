package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
/* loaded from: classes13.dex */
public class SubValidator implements ErrorCodeValidator {
    public static final ErrorCodeValidator.Error c = new ErrorCodeValidator.Error(14, "No Subject (sub) claim is present.");

    /* renamed from: a  reason: collision with root package name */
    public boolean f15545a;
    public String b;

    public SubValidator(boolean z) {
        this.f15545a = z;
    }

    @Override // org.jose4j.jwt.consumer.ErrorCodeValidator
    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        String subject = jwtContext.getJwtClaims().getSubject();
        if (subject == null && this.f15545a) {
            return c;
        }
        String str = this.b;
        if (str == null || str.equals(subject)) {
            return null;
        }
        return new ErrorCodeValidator.Error(15, "Subject (sub) claim value (" + subject + ") doesn't match expected value of " + this.b);
    }

    public SubValidator(String str) {
        this(true);
        this.b = str;
    }
}
