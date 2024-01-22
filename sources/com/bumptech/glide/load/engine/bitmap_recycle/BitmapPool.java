package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public interface BitmapPool {
    void clearMemory();

    @NonNull
    Bitmap get(int i, int i2, Bitmap.Config config);

    @NonNull
    Bitmap getDirty(int i, int i2, Bitmap.Config config);

    long getMaxSize();

    void put(Bitmap bitmap);

    void setSizeMultiplier(float f);

    void trimMemory(int i);
}