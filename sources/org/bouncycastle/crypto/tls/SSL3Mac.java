package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class SSL3Mac implements Mac {
    public static final byte[] d = a((byte) 54, 48);
    public static final byte[] e = a((byte) 92, 48);

    /* renamed from: a  reason: collision with root package name */
    public Digest f14852a;
    public int b;
    public byte[] c;

    public SSL3Mac(Digest digest) {
        this.f14852a = digest;
        this.b = digest.getDigestSize() == 20 ? 40 : 48;
    }

    public static byte[] a(byte b, int i) {
        byte[] bArr = new byte[i];
        Arrays.fill(bArr, b);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        int digestSize = this.f14852a.getDigestSize();
        byte[] bArr2 = new byte[digestSize];
        this.f14852a.doFinal(bArr2, 0);
        Digest digest = this.f14852a;
        byte[] bArr3 = this.c;
        digest.update(bArr3, 0, bArr3.length);
        this.f14852a.update(e, 0, this.b);
        this.f14852a.update(bArr2, 0, digestSize);
        int doFinal = this.f14852a.doFinal(bArr, i);
        reset();
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.f14852a.getAlgorithmName() + "/SSL3MAC";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.f14852a.getDigestSize();
    }

    public Digest getUnderlyingDigest() {
        return this.f14852a;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        this.c = Arrays.clone(((KeyParameter) cipherParameters).getKey());
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.f14852a.reset();
        Digest digest = this.f14852a;
        byte[] bArr = this.c;
        digest.update(bArr, 0, bArr.length);
        this.f14852a.update(d, 0, this.b);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        this.f14852a.update(b);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        this.f14852a.update(bArr, i, i2);
    }
}
