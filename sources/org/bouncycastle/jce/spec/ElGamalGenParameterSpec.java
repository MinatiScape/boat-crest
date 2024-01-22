package org.bouncycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes13.dex */
public class ElGamalGenParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public int f15119a;

    public ElGamalGenParameterSpec(int i) {
        this.f15119a = i;
    }

    public int getPrimeSize() {
        return this.f15119a;
    }
}
