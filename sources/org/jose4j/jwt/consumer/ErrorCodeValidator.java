package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;
/* loaded from: classes13.dex */
public interface ErrorCodeValidator {

    /* loaded from: classes13.dex */
    public static class Error {

        /* renamed from: a  reason: collision with root package name */
        public int f15537a;
        public String b;

        public Error(int i, String str) {
            this.f15537a = i;
            this.b = str;
        }

        public int getErrorCode() {
            return this.f15537a;
        }

        public String getErrorMessage() {
            return this.b;
        }

        public String toString() {
            return "[" + this.f15537a + "] " + this.b;
        }
    }

    Error validate(JwtContext jwtContext) throws MalformedClaimException;
}
