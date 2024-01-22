package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class TweakableBlockCipherParameters implements CipherParameters {
    public final byte[] h;
    public final KeyParameter i;

    public TweakableBlockCipherParameters(KeyParameter keyParameter, byte[] bArr) {
        this.i = keyParameter;
        this.h = Arrays.clone(bArr);
    }

    public KeyParameter getKey() {
        return this.i;
    }

    public byte[] getTweak() {
        return this.h;
    }
}
