package org.bouncycastle.crypto.tls;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class q implements TlsSession {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14874a;
    public SessionParameters b;

    public q(byte[] bArr, SessionParameters sessionParameters) {
        if (bArr == null) {
            throw new IllegalArgumentException("'sessionID' cannot be null");
        }
        if (bArr.length < 1 || bArr.length > 32) {
            throw new IllegalArgumentException("'sessionID' must have length between 1 and 32 bytes, inclusive");
        }
        this.f14874a = Arrays.clone(bArr);
        this.b = sessionParameters;
    }

    @Override // org.bouncycastle.crypto.tls.TlsSession
    public synchronized SessionParameters exportSessionParameters() {
        SessionParameters sessionParameters;
        sessionParameters = this.b;
        return sessionParameters == null ? null : sessionParameters.copy();
    }

    @Override // org.bouncycastle.crypto.tls.TlsSession
    public synchronized byte[] getSessionID() {
        return this.f14874a;
    }

    @Override // org.bouncycastle.crypto.tls.TlsSession
    public synchronized void invalidate() {
        SessionParameters sessionParameters = this.b;
        if (sessionParameters != null) {
            sessionParameters.clear();
            this.b = null;
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsSession
    public synchronized boolean isResumable() {
        return this.b != null;
    }
}
