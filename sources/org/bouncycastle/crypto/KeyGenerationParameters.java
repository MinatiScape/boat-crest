package org.bouncycastle.crypto;

import java.security.SecureRandom;
/* loaded from: classes5.dex */
public class KeyGenerationParameters {

    /* renamed from: a  reason: collision with root package name */
    public SecureRandom f14607a;
    public int b;

    public KeyGenerationParameters(SecureRandom secureRandom, int i) {
        this.f14607a = secureRandom;
        this.b = i;
    }

    public SecureRandom getRandom() {
        return this.f14607a;
    }

    public int getStrength() {
        return this.b;
    }
}
