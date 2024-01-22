package org.bouncycastle.crypto;
/* loaded from: classes5.dex */
public class Commitment {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14605a;
    public final byte[] b;

    public Commitment(byte[] bArr, byte[] bArr2) {
        this.f14605a = bArr;
        this.b = bArr2;
    }

    public byte[] getCommitment() {
        return this.b;
    }

    public byte[] getSecret() {
        return this.f14605a;
    }
}
