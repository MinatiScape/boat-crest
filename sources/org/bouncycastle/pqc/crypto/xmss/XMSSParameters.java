package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public final class XMSSParameters {

    /* renamed from: a  reason: collision with root package name */
    public final c f15334a;
    public final int b;
    public final int c;

    public XMSSParameters(int i, Digest digest) {
        if (i < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        Objects.requireNonNull(digest, "digest == null");
        c cVar = new c(new e(digest));
        this.f15334a = cVar;
        this.b = i;
        this.c = a();
        DefaultXMSSOid.lookup(getDigest().getAlgorithmName(), getDigestSize(), getWinternitzParameter(), cVar.e().c(), i);
    }

    public final int a() {
        int i = 2;
        while (true) {
            int i2 = this.b;
            if (i > i2) {
                throw new IllegalStateException("should never happen...");
            }
            if ((i2 - i) % 2 == 0) {
                return i;
            }
            i++;
        }
    }

    public int b() {
        return this.c;
    }

    public c c() {
        return this.f15334a;
    }

    public Digest getDigest() {
        return this.f15334a.e().a();
    }

    public int getDigestSize() {
        return this.f15334a.e().b();
    }

    public int getHeight() {
        return this.b;
    }

    public int getWinternitzParameter() {
        return this.f15334a.e().f();
    }
}
