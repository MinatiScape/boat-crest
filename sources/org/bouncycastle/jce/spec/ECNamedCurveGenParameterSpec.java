package org.bouncycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes13.dex */
public class ECNamedCurveGenParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public String f15116a;

    public ECNamedCurveGenParameterSpec(String str) {
        this.f15116a = str;
    }

    public String getName() {
        return this.f15116a;
    }
}
