package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class ExchangePair {

    /* renamed from: a  reason: collision with root package name */
    public final AsymmetricKeyParameter f15286a;
    public final byte[] b;

    public ExchangePair(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) {
        this.f15286a = asymmetricKeyParameter;
        this.b = Arrays.clone(bArr);
    }

    public AsymmetricKeyParameter getPublicKey() {
        return this.f15286a;
    }

    public byte[] getSharedValue() {
        return Arrays.clone(this.b);
    }
}
