package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes12.dex */
public class RSAEngine implements AsymmetricBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public a f14702a;

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.f14702a.c();
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.f14702a.d();
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (this.f14702a == null) {
            this.f14702a = new a();
        }
        this.f14702a.e(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) {
        a aVar = this.f14702a;
        if (aVar != null) {
            return aVar.b(aVar.f(aVar.a(bArr, i, i2)));
        }
        throw new IllegalStateException("RSA engine not initialised");
    }
}
