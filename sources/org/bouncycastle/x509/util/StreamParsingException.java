package org.bouncycastle.x509.util;
/* loaded from: classes13.dex */
public class StreamParsingException extends Exception {
    public Throwable _e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this._e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this._e;
    }
}
