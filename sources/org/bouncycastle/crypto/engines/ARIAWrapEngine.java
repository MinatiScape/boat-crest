package org.bouncycastle.crypto.engines;
/* loaded from: classes12.dex */
public class ARIAWrapEngine extends RFC3394WrapEngine {
    public ARIAWrapEngine() {
        super(new ARIAEngine());
    }

    public ARIAWrapEngine(boolean z) {
        super(new ARIAEngine(), z);
    }
}
