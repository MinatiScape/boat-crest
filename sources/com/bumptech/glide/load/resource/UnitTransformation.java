package com.bumptech.glide.load.resource;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public final class UnitTransformation<T> implements Transformation<T> {

    /* renamed from: a  reason: collision with root package name */
    public static final Transformation<?> f2443a = new UnitTransformation();

    @NonNull
    public static <T> UnitTransformation<T> get() {
        return (UnitTransformation) f2443a;
    }

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public Resource<T> transform(@NonNull Context context, @NonNull Resource<T> resource, int i, int i2) {
        return resource;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }
}
