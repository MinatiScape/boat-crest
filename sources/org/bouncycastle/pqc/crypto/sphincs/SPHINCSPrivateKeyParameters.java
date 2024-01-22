package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class SPHINCSPrivateKeyParameters extends AsymmetricKeyParameter {
    public final byte[] i;

    public SPHINCSPrivateKeyParameters(byte[] bArr) {
        super(true);
        this.i = Arrays.clone(bArr);
    }

    public byte[] getKeyData() {
        return Arrays.clone(this.i);
    }
}
