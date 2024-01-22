package org.jose4j.jwt.consumer;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
import org.jose4j.lang.Maths;
/* loaded from: classes13.dex */
public class NumericDateValidator implements ErrorCodeValidator {
    public static final ErrorCodeValidator.Error i = new ErrorCodeValidator.Error(2, "No Expiration Time (exp) claim present.");
    public static final ErrorCodeValidator.Error j = new ErrorCodeValidator.Error(3, "No Issued At (iat) claim present.");
    public static final ErrorCodeValidator.Error k = new ErrorCodeValidator.Error(4, "No Not Before (nbf) claim present.");

    /* renamed from: a  reason: collision with root package name */
    public boolean f15544a;
    public boolean b;
    public boolean c;
    public NumericDate d;
    public int e = 0;
    public int f = 0;
    public Integer g;
    public Integer h;

    public final String a() {
        if (this.e > 0) {
            return " (even when providing " + this.e + " seconds of leeway to account for clock skew).";
        }
        return ".";
    }

    public void setAllowedClockSkewSeconds(int i2) {
        this.e = i2;
    }

    public void setEvaluationTime(NumericDate numericDate) {
        this.d = numericDate;
    }

    public void setIatAllowedSecondsInTheFuture(int i2) {
        this.g = Integer.valueOf(i2);
    }

    public void setIatAllowedSecondsInThePast(int i2) {
        this.h = Integer.valueOf(i2);
    }

    public void setMaxFutureValidityInMinutes(int i2) {
        this.f = i2;
    }

    public void setRequireExp(boolean z) {
        this.f15544a = z;
    }

    public void setRequireIat(boolean z) {
        this.b = z;
    }

    public void setRequireNbf(boolean z) {
        this.c = z;
    }

    @Override // org.jose4j.jwt.consumer.ErrorCodeValidator
    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        JwtClaims jwtClaims = jwtContext.getJwtClaims();
        NumericDate expirationTime = jwtClaims.getExpirationTime();
        NumericDate issuedAt = jwtClaims.getIssuedAt();
        NumericDate notBefore = jwtClaims.getNotBefore();
        if (this.f15544a && expirationTime == null) {
            return i;
        }
        if (this.b && issuedAt == null) {
            return j;
        }
        if (this.c && notBefore == null) {
            return k;
        }
        NumericDate numericDate = this.d;
        if (numericDate == null) {
            numericDate = NumericDate.now();
        }
        if (expirationTime != null) {
            if (Maths.subtract(numericDate.getValue(), this.e) >= expirationTime.getValue()) {
                return new ErrorCodeValidator.Error(1, "The JWT is no longer valid - the evaluation time " + numericDate + " is on or after the Expiration Time (exp=" + expirationTime + ") claim value" + a());
            } else if (issuedAt != null && expirationTime.isBefore(issuedAt)) {
                return new ErrorCodeValidator.Error(17, "The Expiration Time (exp=" + expirationTime + ") claim value cannot be before the Issued At (iat=" + issuedAt + ") claim value.");
            } else if (notBefore != null && expirationTime.isBefore(notBefore)) {
                return new ErrorCodeValidator.Error(17, "The Expiration Time (exp=" + expirationTime + ") claim value cannot be before the Not Before (nbf=" + notBefore + ") claim value.");
            } else if (this.f > 0 && Maths.subtract(Maths.subtract(expirationTime.getValue(), this.e), numericDate.getValue()) > this.f * 60) {
                return new ErrorCodeValidator.Error(5, "The Expiration Time (exp=" + expirationTime + ") claim value cannot be more than " + this.f + " minutes in the future relative to the evaluation time " + numericDate + a());
            }
        }
        if (notBefore != null && Maths.add(numericDate.getValue(), this.e) < notBefore.getValue()) {
            return new ErrorCodeValidator.Error(6, "The JWT is not yet valid as the evaluation time " + numericDate + " is before the Not Before (nbf=" + notBefore + ") claim time" + a());
        } else if (issuedAt != null) {
            if (this.g != null && Maths.subtract(Maths.subtract(issuedAt.getValue(), numericDate.getValue()), this.e) > this.g.intValue()) {
                return new ErrorCodeValidator.Error(23, "iat " + issuedAt + " is more than " + this.g + " second(s) ahead of now " + numericDate + a());
            } else if (this.h == null || Maths.subtract(Maths.subtract(numericDate.getValue(), issuedAt.getValue()), this.e) <= this.h.intValue()) {
                return null;
            } else {
                return new ErrorCodeValidator.Error(24, "As of now " + numericDate + " iat " + issuedAt + " is more than " + this.h + " second(s) in the past" + a());
            }
        } else {
            return null;
        }
    }
}
