package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class UserKeyingMaterialSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f15079a;

    public UserKeyingMaterialSpec(byte[] bArr) {
        this.f15079a = Arrays.clone(bArr);
    }

    public byte[] getUserKeyingMaterial() {
        return Arrays.clone(this.f15079a);
    }
}
