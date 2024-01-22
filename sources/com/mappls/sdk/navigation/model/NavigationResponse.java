package com.mappls.sdk.navigation.model;

import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.util.AuthenticationError;
/* loaded from: classes11.dex */
public class NavigationResponse {
    public AuthenticationError error;
    public Exception exception;

    public NavigationResponse(AuthenticationError authenticationError, Exception exc) {
        this.error = authenticationError;
        this.exception = exc;
    }

    public AuthenticationError getError() {
        return this.error;
    }

    public Exception getException() {
        return this.exception;
    }

    public String toString() {
        StringBuilder a2 = h.a("NavigationResponse{error=");
        a2.append(this.error);
        a2.append(", exception=");
        a2.append(this.exception);
        a2.append('}');
        return a2.toString();
    }
}
