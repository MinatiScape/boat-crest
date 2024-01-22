package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public class GenericKey {

    /* renamed from: a  reason: collision with root package name */
    public AlgorithmIdentifier f15226a;
    public Object b;

    public GenericKey(Object obj) {
        this.f15226a = null;
        this.b = obj;
    }

    public GenericKey(AlgorithmIdentifier algorithmIdentifier, Object obj) {
        this.f15226a = algorithmIdentifier;
        this.b = obj;
    }

    public GenericKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.f15226a = algorithmIdentifier;
        this.b = bArr;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.f15226a;
    }

    public Object getRepresentation() {
        return this.b;
    }
}
