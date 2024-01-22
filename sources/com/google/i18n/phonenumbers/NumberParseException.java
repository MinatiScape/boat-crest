package com.google.i18n.phonenumbers;
/* loaded from: classes10.dex */
public class NumberParseException extends Exception {
    private ErrorType errorType;
    private String message;

    /* loaded from: classes10.dex */
    public enum ErrorType {
        INVALID_COUNTRY_CODE,
        NOT_A_NUMBER,
        TOO_SHORT_AFTER_IDD,
        TOO_SHORT_NSN,
        TOO_LONG
    }

    public NumberParseException(ErrorType errorType, String str) {
        super(str);
        this.message = str;
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String valueOf = String.valueOf(this.errorType);
        String valueOf2 = String.valueOf(this.message);
        StringBuilder sb = new StringBuilder(valueOf.length() + 14 + valueOf2.length());
        sb.append("Error type: ");
        sb.append(valueOf);
        sb.append(". ");
        sb.append(valueOf2);
        return sb.toString();
    }
}
