package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public class b implements TlsHandshakeHash {

    /* renamed from: a  reason: collision with root package name */
    public TlsContext f14862a;
    public Digest b;
    public Digest c;

    public b() {
        this.b = TlsUtils.createHash((short) 1);
        this.c = TlsUtils.createHash((short) 2);
    }

    public b(b bVar) {
        this.f14862a = bVar.f14862a;
        this.b = TlsUtils.cloneHash((short) 1, bVar.b);
        this.c = TlsUtils.cloneHash((short) 2, bVar.c);
    }

    public void a(Digest digest, byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = this.f14862a.getSecurityParameters().f;
        digest.update(bArr3, 0, bArr3.length);
        digest.update(bArr, 0, i);
        int digestSize = digest.getDigestSize();
        byte[] bArr4 = new byte[digestSize];
        digest.doFinal(bArr4, 0);
        digest.update(bArr3, 0, bArr3.length);
        digest.update(bArr2, 0, i);
        digest.update(bArr4, 0, digestSize);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        TlsContext tlsContext = this.f14862a;
        if (tlsContext != null && TlsUtils.isSSL(tlsContext)) {
            Digest digest = this.b;
            byte[] bArr2 = SSL3Mac.d;
            byte[] bArr3 = SSL3Mac.e;
            a(digest, bArr2, bArr3, 48);
            a(this.c, bArr2, bArr3, 40);
        }
        int doFinal = this.b.doFinal(bArr, i);
        return doFinal + this.c.doFinal(bArr, i + doFinal);
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public Digest forkPRFHash() {
        return new b(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.b.getAlgorithmName() + " and " + this.c.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.b.getDigestSize() + this.c.getDigestSize();
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public byte[] getFinalHash(short s) {
        throw new IllegalStateException("CombinedHash doesn't support multiple hashes");
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void init(TlsContext tlsContext) {
        this.f14862a = tlsContext;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash notifyPRFDetermined() {
        return this;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.b.reset();
        this.c.reset();
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void sealHashAlgorithms() {
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash stopTracking() {
        return new b(this);
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void trackHashAlgorithm(short s) {
        throw new IllegalStateException("CombinedHash only supports calculating the legacy PRF for handshake hash");
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        this.b.update(b);
        this.c.update(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        this.b.update(bArr, i, i2);
        this.c.update(bArr, i, i2);
    }
}
