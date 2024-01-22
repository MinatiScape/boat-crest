package com.clevertap.android.sdk.gif;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.gif.a;
/* loaded from: classes2.dex */
public class SimpleBitmapProvider implements a.InterfaceC0226a {
    @Override // com.clevertap.android.sdk.gif.a.InterfaceC0226a
    @NonNull
    public Bitmap obtain(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.clevertap.android.sdk.gif.a.InterfaceC0226a
    public byte[] obtainByteArray(int i) {
        return new byte[i];
    }

    @Override // com.clevertap.android.sdk.gif.a.InterfaceC0226a
    public int[] obtainIntArray(int i) {
        return new int[i];
    }

    public void release(Bitmap bitmap) {
        bitmap.recycle();
    }

    public void release(byte[] bArr) {
    }

    public void release(int[] iArr) {
    }
}
