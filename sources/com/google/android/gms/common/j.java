package com.google.android.gms.common;

import java.util.Arrays;
/* loaded from: classes6.dex */
public final class j extends i {
    public final byte[] b;

    public j(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.b = bArr;
    }

    @Override // com.google.android.gms.common.i
    public final byte[] t() {
        return this.b;
    }
}
