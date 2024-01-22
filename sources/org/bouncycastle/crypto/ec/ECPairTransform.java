package org.bouncycastle.crypto.ec;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes12.dex */
public interface ECPairTransform {
    void init(CipherParameters cipherParameters);

    ECPair transform(ECPair eCPair);
}
