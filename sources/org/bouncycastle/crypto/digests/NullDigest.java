package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes12.dex */
public class NullDigest implements Digest {

    /* renamed from: a  reason: collision with root package name */
    public ByteArrayOutputStream f14643a = new ByteArrayOutputStream();

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        byte[] byteArray = this.f14643a.toByteArray();
        System.arraycopy(byteArray, 0, bArr, i, byteArray.length);
        reset();
        return byteArray.length;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "NULL";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.f14643a.size();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.f14643a.reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        this.f14643a.write(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        this.f14643a.write(bArr, i, i2);
    }
}
