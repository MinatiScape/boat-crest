package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public final XMSSOid f15342a;
    public final Digest b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;

    public e(Digest digest) {
        Objects.requireNonNull(digest, "digest == null");
        this.b = digest;
        int digestSize = XMSSUtil.getDigestSize(digest);
        this.c = digestSize;
        this.d = 16;
        int ceil = (int) Math.ceil((digestSize * 8) / XMSSUtil.log2(16));
        this.f = ceil;
        int floor = ((int) Math.floor(XMSSUtil.log2((16 - 1) * ceil) / XMSSUtil.log2(16))) + 1;
        this.g = floor;
        int i = ceil + floor;
        this.e = i;
        d b = d.b(digest.getAlgorithmName(), digestSize, 16, i);
        this.f15342a = b;
        if (b != null) {
            return;
        }
        throw new IllegalArgumentException("cannot find OID for digest algorithm: " + digest.getAlgorithmName());
    }

    public Digest a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return this.d;
    }
}
