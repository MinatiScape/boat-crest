package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class NHPrivateKeyParameters extends AsymmetricKeyParameter {
    public final short[] i;

    public NHPrivateKeyParameters(short[] sArr) {
        super(true);
        this.i = Arrays.clone(sArr);
    }

    public short[] getSecData() {
        return Arrays.clone(this.i);
    }
}
