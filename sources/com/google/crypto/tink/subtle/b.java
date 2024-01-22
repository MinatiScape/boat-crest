package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;
/* loaded from: classes10.dex */
public class b extends c {
    public b(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    @Override // com.google.crypto.tink.subtle.c
    public int[] b(int[] iArr, int i) {
        if (iArr.length == e() / 4) {
            int[] iArr2 = new int[16];
            c.i(iArr2, this.f11050a);
            iArr2[12] = i;
            System.arraycopy(iArr, 0, iArr2, 13, iArr.length);
            return iArr2;
        }
        throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", Integer.valueOf(iArr.length * 32)));
    }

    @Override // com.google.crypto.tink.subtle.c
    public int e() {
        return 12;
    }
}
