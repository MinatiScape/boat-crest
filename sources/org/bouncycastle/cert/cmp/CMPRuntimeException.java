package org.bouncycastle.cert.cmp;
/* loaded from: classes12.dex */
public class CMPRuntimeException extends RuntimeException {
    private Throwable cause;

    public CMPRuntimeException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
