package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes7.dex */
public final class y0 extends OutputStream {
    public long h;

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.h += i2;
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        this.h++;
    }
}
