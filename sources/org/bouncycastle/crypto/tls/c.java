package org.bouncycastle.crypto.tls;
/* loaded from: classes13.dex */
public class c {
    public final int b;
    public final TlsCipher c;

    /* renamed from: a  reason: collision with root package name */
    public final h f14863a = new h();
    public long d = 0;

    public c(int i, TlsCipher tlsCipher) {
        if (i < 0) {
            throw new IllegalArgumentException("'epoch' must be >= 0");
        }
        if (tlsCipher == null) {
            throw new IllegalArgumentException("'cipher' cannot be null");
        }
        this.b = i;
        this.c = tlsCipher;
    }

    public long a() {
        long j = this.d;
        this.d = 1 + j;
        return j;
    }

    public TlsCipher b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public h d() {
        return this.f14863a;
    }
}
