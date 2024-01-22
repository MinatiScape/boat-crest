package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class SM2ParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f15076a;

    public SM2ParameterSpec(byte[] bArr) {
        Objects.requireNonNull(bArr, "id string cannot be null");
        this.f15076a = Arrays.clone(bArr);
    }

    public byte[] getID() {
        return Arrays.clone(this.f15076a);
    }
}
