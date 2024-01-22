package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public abstract class AsymmetricKeyWrapper implements KeyWrapper {

    /* renamed from: a  reason: collision with root package name */
    public AlgorithmIdentifier f15220a;

    public AsymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier) {
        this.f15220a = algorithmIdentifier;
    }

    @Override // org.bouncycastle.operator.KeyWrapper
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.f15220a;
    }
}
