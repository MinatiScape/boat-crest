package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.util.Times;
/* loaded from: classes13.dex */
public abstract class a implements TlsContext {
    public static long h = Times.nanoTime();

    /* renamed from: a  reason: collision with root package name */
    public RandomGenerator f14861a;
    public SecureRandom b;
    public SecurityParameters c;
    public ProtocolVersion d = null;
    public ProtocolVersion e = null;
    public TlsSession f = null;
    public Object g = null;

    public a(SecureRandom secureRandom, SecurityParameters securityParameters) {
        Digest createHash = TlsUtils.createHash((short) 4);
        byte[] bArr = new byte[createHash.getDigestSize()];
        secureRandom.nextBytes(bArr);
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(createHash);
        this.f14861a = digestRandomGenerator;
        digestRandomGenerator.addSeedMaterial(a());
        this.f14861a.addSeedMaterial(Times.nanoTime());
        this.f14861a.addSeedMaterial(bArr);
        this.b = secureRandom;
        this.c = securityParameters;
    }

    public static synchronized long a() {
        long j;
        synchronized (a.class) {
            j = h + 1;
            h = j;
        }
        return j;
    }

    public void b(ProtocolVersion protocolVersion) {
        this.d = protocolVersion;
    }

    public void c(TlsSession tlsSession) {
        this.f = tlsSession;
    }

    public void d(ProtocolVersion protocolVersion) {
        this.e = protocolVersion;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public byte[] exportKeyingMaterial(String str, byte[] bArr, int i) {
        if (bArr == null || TlsUtils.isValidUint16(bArr.length)) {
            SecurityParameters securityParameters = getSecurityParameters();
            byte[] clientRandom = securityParameters.getClientRandom();
            byte[] serverRandom = securityParameters.getServerRandom();
            int length = clientRandom.length + serverRandom.length;
            if (bArr != null) {
                length += bArr.length + 2;
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(clientRandom, 0, bArr2, 0, clientRandom.length);
            int length2 = clientRandom.length + 0;
            System.arraycopy(serverRandom, 0, bArr2, length2, serverRandom.length);
            int length3 = length2 + serverRandom.length;
            if (bArr != null) {
                TlsUtils.writeUint16(bArr.length, bArr2, length3);
                int i2 = length3 + 2;
                System.arraycopy(bArr, 0, bArr2, i2, bArr.length);
                length3 = i2 + bArr.length;
            }
            if (length3 == length) {
                return TlsUtils.PRF(this, securityParameters.getMasterSecret(), str, bArr2, i);
            }
            throw new IllegalStateException("error in calculation of seed for export");
        }
        throw new IllegalArgumentException("'context_value' must have length less than 2^16 (or be null)");
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public ProtocolVersion getClientVersion() {
        return this.d;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public RandomGenerator getNonceRandomGenerator() {
        return this.f14861a;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public TlsSession getResumableSession() {
        return this.f;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public SecureRandom getSecureRandom() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public SecurityParameters getSecurityParameters() {
        return this.c;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public ProtocolVersion getServerVersion() {
        return this.e;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public Object getUserObject() {
        return this.g;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public void setUserObject(Object obj) {
        this.g = obj;
    }
}
