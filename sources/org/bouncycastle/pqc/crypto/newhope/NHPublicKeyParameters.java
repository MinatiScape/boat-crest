package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class NHPublicKeyParameters extends AsymmetricKeyParameter {
    public final byte[] i;

    public NHPublicKeyParameters(byte[] bArr) {
        super(false);
        this.i = Arrays.clone(bArr);
    }

    public byte[] getPubData() {
        return Arrays.clone(this.i);
    }
}
