package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public class DrawableTransformation implements Transformation<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final Transformation<Bitmap> f2460a;
    public final boolean b;

    public DrawableTransformation(Transformation<Bitmap> transformation, boolean z) {
        this.f2460a = transformation;
        this.b = z;
    }

    public final Resource<Drawable> a(Context context, Resource<Bitmap> resource) {
        return LazyBitmapDrawableResource.obtain(context.getResources(), resource);
    }

    public Transformation<BitmapDrawable> asBitmapDrawable() {
        return this;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof DrawableTransformation) {
            return this.f2460a.equals(((DrawableTransformation) obj).f2460a);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.f2460a.hashCode();
    }

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public Resource<Drawable> transform(@NonNull Context context, @NonNull Resource<Drawable> resource, int i, int i2) {
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Drawable drawable = resource.get();
        Resource<Bitmap> a2 = a.a(bitmapPool, drawable, i, i2);
        if (a2 == null) {
            if (this.b) {
                throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
            }
            return resource;
        }
        Resource<Bitmap> transform = this.f2460a.transform(context, a2, i, i2);
        if (transform.equals(a2)) {
            transform.recycle();
            return resource;
        }
        return a(context, transform);
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.f2460a.updateDiskCacheKey(messageDigest);
    }
}
