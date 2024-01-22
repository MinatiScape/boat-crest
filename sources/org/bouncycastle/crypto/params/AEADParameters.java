package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class AEADParameters implements CipherParameters {
    public byte[] h;
    public byte[] i;
    public KeyParameter j;
    public int k;

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr) {
        this(keyParameter, i, bArr, null);
    }

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr, byte[] bArr2) {
        this.j = keyParameter;
        this.i = bArr;
        this.k = i;
        this.h = bArr2;
    }

    public byte[] getAssociatedText() {
        return this.h;
    }

    public KeyParameter getKey() {
        return this.j;
    }

    public int getMacSize() {
        return this.k;
    }

    public byte[] getNonce() {
        return this.i;
    }
}
