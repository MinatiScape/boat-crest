package org.bouncycastle.jce.interfaces;

import java.security.PublicKey;
/* loaded from: classes13.dex */
public interface MQVPublicKey extends PublicKey {
    PublicKey getEphemeralKey();

    PublicKey getStaticKey();
}
