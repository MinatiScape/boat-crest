package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class p extends c {
    public p(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    public static int[] l(int[] iArr, int[] iArr2) {
        c.i(r0, iArr);
        int[] iArr3 = {0, 0, 0, 0, iArr3[12], iArr3[13], iArr3[14], iArr3[15], 0, 0, 0, 0, iArr2[0], iArr2[1], iArr2[2], iArr2[3]};
        c.j(iArr3);
        return Arrays.copyOf(iArr3, 8);
    }

    @Override // com.google.crypto.tink.subtle.c
    public int[] b(int[] iArr, int i) {
        if (iArr.length == e() / 4) {
            int[] iArr2 = new int[16];
            c.i(iArr2, l(this.f11050a, iArr));
            iArr2[12] = i;
            iArr2[13] = 0;
            iArr2[14] = iArr[4];
            iArr2[15] = iArr[5];
            return iArr2;
        }
        throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(iArr.length * 32)));
    }

    @Override // com.google.crypto.tink.subtle.c
    public int e() {
        return 24;
    }
}
