package com.google.android.gms.internal.firebase_messaging;

import androidx.annotation.NonNull;
import java.io.OutputStream;
/* loaded from: classes7.dex */
public final class s extends OutputStream {
    public long h = 0;

    public final long a() {
        return this.h;
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.h++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.h += bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(@NonNull byte[] bArr, int i, int i2) {
        int length;
        int i3;
        if (i >= 0 && i <= (length = bArr.length) && i2 >= 0 && (i3 = i + i2) <= length && i3 >= 0) {
            this.h += i2;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}