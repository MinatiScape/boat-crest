package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.ListPreloader;
/* loaded from: classes2.dex */
public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f2552a;

    public FixedPreloadSizeProvider(int i, int i2) {
        this.f2552a = new int[]{i, i2};
    }

    @Override // com.bumptech.glide.ListPreloader.PreloadSizeProvider
    @Nullable
    public int[] getPreloadSize(@NonNull T t, int i, int i2) {
        return this.f2552a;
    }
}
