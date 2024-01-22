package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.ExchangePair;
import org.bouncycastle.pqc.crypto.ExchangePairGenerator;
/* loaded from: classes13.dex */
public class NHExchangePairGenerator implements ExchangePairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final SecureRandom f15307a;

    public NHExchangePairGenerator(SecureRandom secureRandom) {
        this.f15307a = secureRandom;
    }

    @Override // org.bouncycastle.pqc.crypto.ExchangePairGenerator
    public ExchangePair GenerateExchange(AsymmetricKeyParameter asymmetricKeyParameter) {
        return generateExchange(asymmetricKeyParameter);
    }

    @Override // org.bouncycastle.pqc.crypto.ExchangePairGenerator
    public ExchangePair generateExchange(AsymmetricKeyParameter asymmetricKeyParameter) {
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[2048];
        d.i(this.f15307a, bArr, bArr2, ((NHPublicKeyParameters) asymmetricKeyParameter).i);
        return new ExchangePair(new NHPublicKeyParameters(bArr2), bArr);
    }
}
