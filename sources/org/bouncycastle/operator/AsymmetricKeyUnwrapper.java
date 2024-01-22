package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public abstract class AsymmetricKeyUnwrapper implements KeyUnwrapper {

    /* renamed from: a  reason: collision with root package name */
    public AlgorithmIdentifier f15219a;

    public AsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier) {
        this.f15219a = algorithmIdentifier;
    }

    @Override // org.bouncycastle.operator.KeyUnwrapper
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.f15219a;
    }
}
