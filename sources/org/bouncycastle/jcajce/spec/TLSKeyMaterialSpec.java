package org.bouncycastle.jcajce.spec;

import java.security.spec.KeySpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class TLSKeyMaterialSpec implements KeySpec {
    public static final String KEY_EXPANSION = "key expansion";
    public static final String MASTER_SECRET = "master secret";
    public final byte[] h;
    public final String i;
    public final int j;
    public final byte[] k;

    public TLSKeyMaterialSpec(byte[] bArr, String str, int i, byte[]... bArr2) {
        this.h = Arrays.clone(bArr);
        this.i = str;
        this.j = i;
        this.k = Arrays.concatenate(bArr2);
    }

    public String getLabel() {
        return this.i;
    }

    public int getLength() {
        return this.j;
    }

    public byte[] getSecret() {
        return Arrays.clone(this.h);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.k);
    }
}
