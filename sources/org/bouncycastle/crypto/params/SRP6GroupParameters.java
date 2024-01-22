package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class SRP6GroupParameters {

    /* renamed from: a  reason: collision with root package name */
    public BigInteger f14807a;
    public BigInteger b;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f14807a = bigInteger;
        this.b = bigInteger2;
    }

    public BigInteger getG() {
        return this.b;
    }

    public BigInteger getN() {
        return this.f14807a;
    }
}
