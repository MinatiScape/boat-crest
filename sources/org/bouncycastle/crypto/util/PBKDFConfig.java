package org.bouncycastle.crypto.util;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes13.dex */
public abstract class PBKDFConfig {

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14881a;

    public PBKDFConfig(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f14881a = aSN1ObjectIdentifier;
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return this.f14881a;
    }
}
