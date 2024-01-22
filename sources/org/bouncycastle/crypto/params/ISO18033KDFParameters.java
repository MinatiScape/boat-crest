package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
/* loaded from: classes13.dex */
public class ISO18033KDFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14801a;

    public ISO18033KDFParameters(byte[] bArr) {
        this.f14801a = bArr;
    }

    public byte[] getSeed() {
        return this.f14801a;
    }
}
