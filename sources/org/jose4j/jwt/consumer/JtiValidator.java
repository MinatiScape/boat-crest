package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
/* loaded from: classes13.dex */
public class JtiValidator implements ErrorCodeValidator {
    public static final ErrorCodeValidator.Error b = new ErrorCodeValidator.Error(13, "The JWT ID (jti) claim is not present.");

    /* renamed from: a  reason: collision with root package name */
    public boolean f15540a;

    public JtiValidator(boolean z) {
        this.f15540a = z;
    }

    @Override // org.jose4j.jwt.consumer.ErrorCodeValidator
    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        if (jwtContext.getJwtClaims().getJwtId() == null && this.f15540a) {
            return b;
        }
        return null;
    }
}
