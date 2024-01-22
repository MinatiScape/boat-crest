package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
@Deprecated
/* loaded from: classes2.dex */
public class BitmapDrawableTransformation implements Transformation<BitmapDrawable> {

    /* renamed from: a  reason: collision with root package name */
    public final Transformation<Drawable> f2446a;

    public BitmapDrawableTransformation(Transformation<Bitmap> transformation) {
        this.f2446a = (Transformation) Preconditions.checkNotNull(new DrawableTransformation(transformation, false));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Resource<BitmapDrawable> a(Resource<Drawable> resource) {
        if (resource.get() instanceof BitmapDrawable) {
            return resource;
        }
        throw new IllegalArgumentException("Wrapped transformation unexpectedly returned a non BitmapDrawable resource: " + resource.get());
    }

    public static Resource<Drawable> b(Resource<BitmapDrawable> resource) {
        return resource;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof BitmapDrawableTransformation) {
            return this.f2446a.equals(((BitmapDrawableTransformation) obj).f2446a);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.f2446a.hashCode();
    }

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public Resource<BitmapDrawable> transform(@NonNull Context context, @NonNull Resource<BitmapDrawable> resource, int i, int i2) {
        return a(this.f2446a.transform(context, b(resource), i, i2));
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.f2446a.updateDiskCacheKey(messageDigest);
    }
}
