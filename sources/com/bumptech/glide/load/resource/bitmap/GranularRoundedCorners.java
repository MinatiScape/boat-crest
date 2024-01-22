package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public final class GranularRoundedCorners extends BitmapTransformation {
    public static final byte[] e = "com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners".getBytes(Key.CHARSET);

    /* renamed from: a  reason: collision with root package name */
    public final float f2462a;
    public final float b;
    public final float c;
    public final float d;

    public GranularRoundedCorners(float f, float f2, float f3, float f4) {
        this.f2462a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof GranularRoundedCorners) {
            GranularRoundedCorners granularRoundedCorners = (GranularRoundedCorners) obj;
            return this.f2462a == granularRoundedCorners.f2462a && this.b == granularRoundedCorners.b && this.c == granularRoundedCorners.c && this.d == granularRoundedCorners.d;
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return Util.hashCode(this.d, Util.hashCode(this.c, Util.hashCode(this.b, Util.hashCode(-2013597734, Util.hashCode(this.f2462a)))));
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    public Bitmap transform(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        return TransformationUtils.roundedCorners(bitmapPool, bitmap, this.f2462a, this.b, this.c, this.d);
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(e);
        messageDigest.update(ByteBuffer.allocate(16).putFloat(this.f2462a).putFloat(this.b).putFloat(this.c).putFloat(this.d).array());
    }
}
