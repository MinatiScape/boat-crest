package org.bouncycastle.crypto.prng;
/* loaded from: classes13.dex */
public interface EntropySource {
    int entropySize();

    byte[] getEntropy();

    boolean isPredictionResistant();
}
