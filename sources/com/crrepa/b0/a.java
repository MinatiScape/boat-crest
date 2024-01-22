package com.crrepa.b0;

import android.graphics.Bitmap;
import com.crrepa.f.d1;
import com.crrepa.i0.e;
import java.util.Arrays;
/* loaded from: classes9.dex */
public class a extends com.crrepa.a0.a {
    public byte k;

    @Override // com.crrepa.a0.a
    public void a() {
        h();
        a(false);
    }

    public void a(byte b, Bitmap bitmap) {
        this.k = b;
        a(bitmap);
    }

    @Override // com.crrepa.a0.a
    public void a(boolean z) {
        byte[] bArr = new byte[5];
        if (z) {
            f();
        } else {
            Arrays.fill(bArr, (byte) -1);
        }
        bArr[0] = this.k;
        a(d1.a(-13, bArr));
    }

    @Override // com.crrepa.a0.a
    public byte[] b(boolean z, Bitmap[] bitmapArr) {
        if (bitmapArr == null || bitmapArr.length < 1) {
            return null;
        }
        return com.crrepa.h0.a.a(bitmapArr[0], z).f();
    }

    @Override // com.crrepa.a0.a
    public void d(int i) {
        byte[] bArr = new byte[5];
        bArr[0] = this.k;
        byte[] a2 = e.a(i);
        System.arraycopy(a2, 0, bArr, 1, a2.length);
        a(d1.a(-13, bArr));
    }
}
