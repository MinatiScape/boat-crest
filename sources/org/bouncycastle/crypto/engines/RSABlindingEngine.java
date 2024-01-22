package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
/* loaded from: classes12.dex */
public class RSABlindingEngine implements AsymmetricBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public a f14701a = new a();
    public RSAKeyParameters b;
    public BigInteger c;
    public boolean d;

    public final BigInteger a(BigInteger bigInteger) {
        return bigInteger.multiply(this.c.modPow(this.b.getExponent(), this.b.getModulus())).mod(this.b.getModulus());
    }

    public final BigInteger b(BigInteger bigInteger) {
        BigInteger modulus = this.b.getModulus();
        return bigInteger.multiply(this.c.modInverse(modulus)).mod(modulus);
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.f14701a.c();
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.f14701a.d();
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        RSABlindingParameters rSABlindingParameters = (RSABlindingParameters) cipherParameters;
        this.f14701a.e(z, rSABlindingParameters.getPublicKey());
        this.d = z;
        this.b = rSABlindingParameters.getPublicKey();
        this.c = rSABlindingParameters.getBlindingFactor();
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) {
        BigInteger a2 = this.f14701a.a(bArr, i, i2);
        return this.f14701a.b(this.d ? a(a2) : b(a2));
    }
}
