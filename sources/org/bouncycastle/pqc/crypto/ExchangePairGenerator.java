package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes13.dex */
public interface ExchangePairGenerator {
    ExchangePair GenerateExchange(AsymmetricKeyParameter asymmetricKeyParameter);

    ExchangePair generateExchange(AsymmetricKeyParameter asymmetricKeyParameter);
}
