package org.bouncycastle.crypto.tls;

import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Shorts;
/* loaded from: classes13.dex */
public class i implements TlsHandshakeHash {

    /* renamed from: a  reason: collision with root package name */
    public TlsContext f14871a;
    public j b;
    public Hashtable c;
    public Short d;

    public i() {
        this.b = new j();
        this.c = new Hashtable();
        this.d = null;
    }

    public i(Short sh, Digest digest) {
        this.b = null;
        Hashtable hashtable = new Hashtable();
        this.c = hashtable;
        this.d = sh;
        hashtable.put(sh, digest);
    }

    public void a() {
        if (this.b == null || this.c.size() > 4) {
            return;
        }
        Enumeration elements = this.c.elements();
        while (elements.hasMoreElements()) {
            this.b.a((Digest) elements.nextElement());
        }
        this.b = null;
    }

    public void b(Short sh) {
        if (this.c.containsKey(sh)) {
            return;
        }
        this.c.put(sh, TlsUtils.createHash(sh.shortValue()));
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public Digest forkPRFHash() {
        a();
        if (this.b != null) {
            Digest createHash = TlsUtils.createHash(this.d.shortValue());
            this.b.a(createHash);
            return createHash;
        }
        return TlsUtils.cloneHash(this.d.shortValue(), (Digest) this.c.get(this.d));
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public byte[] getFinalHash(short s) {
        Digest digest = (Digest) this.c.get(Shorts.valueOf(s));
        if (digest == null) {
            throw new IllegalStateException("HashAlgorithm." + HashAlgorithm.getText(s) + " is not being tracked");
        }
        Digest cloneHash = TlsUtils.cloneHash(s, digest);
        j jVar = this.b;
        if (jVar != null) {
            jVar.a(cloneHash);
        }
        byte[] bArr = new byte[cloneHash.getDigestSize()];
        cloneHash.doFinal(bArr, 0);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void init(TlsContext tlsContext) {
        this.f14871a = tlsContext;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash notifyPRFDetermined() {
        int prfAlgorithm = this.f14871a.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            b bVar = new b();
            bVar.init(this.f14871a);
            this.b.a(bVar);
            return bVar.notifyPRFDetermined();
        }
        Short valueOf = Shorts.valueOf(TlsUtils.getHashAlgorithmForPRFAlgorithm(prfAlgorithm));
        this.d = valueOf;
        b(valueOf);
        return this;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        j jVar = this.b;
        if (jVar != null) {
            jVar.reset();
            return;
        }
        Enumeration elements = this.c.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).reset();
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void sealHashAlgorithms() {
        a();
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash stopTracking() {
        Digest cloneHash = TlsUtils.cloneHash(this.d.shortValue(), (Digest) this.c.get(this.d));
        j jVar = this.b;
        if (jVar != null) {
            jVar.a(cloneHash);
        }
        i iVar = new i(this.d, cloneHash);
        iVar.init(this.f14871a);
        return iVar;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void trackHashAlgorithm(short s) {
        if (this.b == null) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        b(Shorts.valueOf(s));
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        j jVar = this.b;
        if (jVar != null) {
            jVar.write(b);
            return;
        }
        Enumeration elements = this.c.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).update(b);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        j jVar = this.b;
        if (jVar != null) {
            jVar.write(bArr, i, i2);
            return;
        }
        Enumeration elements = this.c.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).update(bArr, i, i2);
        }
    }
}
