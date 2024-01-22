package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes13.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public BigInteger f15120a;
    public BigInteger b;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f15120a = bigInteger;
        this.b = bigInteger2;
    }

    public BigInteger getG() {
        return this.b;
    }

    public BigInteger getP() {
        return this.f15120a;
    }
}
