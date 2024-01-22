package org.bouncycastle.jcajce.spec;

import java.security.spec.KeySpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class ScryptKeySpec implements KeySpec {
    public final char[] h;
    public final byte[] i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;

    public ScryptKeySpec(char[] cArr, byte[] bArr, int i, int i2, int i3, int i4) {
        this.h = cArr;
        this.i = Arrays.clone(bArr);
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.m = i4;
    }

    public int getBlockSize() {
        return this.k;
    }

    public int getCostParameter() {
        return this.j;
    }

    public int getKeyLength() {
        return this.m;
    }

    public int getParallelizationParameter() {
        return this.l;
    }

    public char[] getPassword() {
        return this.h;
    }

    public byte[] getSalt() {
        return Arrays.clone(this.i);
    }
}
