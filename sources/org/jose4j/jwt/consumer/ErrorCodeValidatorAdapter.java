package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
/* loaded from: classes13.dex */
public class ErrorCodeValidatorAdapter implements ErrorCodeValidator {

    /* renamed from: a  reason: collision with root package name */
    public Validator f15538a;

    public ErrorCodeValidatorAdapter(Validator validator) {
        this.f15538a = validator;
    }

    @Override // org.jose4j.jwt.consumer.ErrorCodeValidator
    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        String validate = this.f15538a.validate(jwtContext);
        if (validate == null) {
            return null;
        }
        return new ErrorCodeValidator.Error(17, validate);
    }
}
