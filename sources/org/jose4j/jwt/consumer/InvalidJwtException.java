package org.jose4j.jwt.consumer;

import java.util.Collections;
import java.util.List;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
/* loaded from: classes13.dex */
public class InvalidJwtException extends Exception {
    private List<ErrorCodeValidator.Error> details;
    private JwtContext jwtContext;

    public InvalidJwtException(String str, List<ErrorCodeValidator.Error> list, JwtContext jwtContext) {
        super(str);
        this.details = Collections.emptyList();
        this.details = list;
        this.jwtContext = jwtContext;
    }

    public List<ErrorCodeValidator.Error> getErrorDetails() {
        return this.details;
    }

    public JwtContext getJwtContext() {
        return this.jwtContext;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getMessage());
        if (!this.details.isEmpty()) {
            sb.append(" Additional details: ");
            sb.append(this.details);
        }
        return sb.toString();
    }

    public String getOriginalMessage() {
        return super.getMessage();
    }

    public boolean hasErrorCode(int i) {
        for (ErrorCodeValidator.Error error : this.details) {
            if (i == error.getErrorCode()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasExpired() {
        return hasErrorCode(1);
    }

    public InvalidJwtException(String str, ErrorCodeValidator.Error error, Throwable th, JwtContext jwtContext) {
        super(str, th);
        this.details = Collections.emptyList();
        this.jwtContext = jwtContext;
        this.details = Collections.singletonList(error);
    }
}
