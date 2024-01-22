package org.bouncycastle.jcajce.provider.util;

import javax.crypto.BadPaddingException;
/* loaded from: classes13.dex */
public class BadBlockException extends BadPaddingException {
    private final Throwable cause;

    public BadBlockException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
