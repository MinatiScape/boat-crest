package com.airbnb.lottie.model;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.LruCache;
import com.airbnb.lottie.LottieComposition;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class LottieCompositionCache {
    public static final LottieCompositionCache b = new LottieCompositionCache();

    /* renamed from: a  reason: collision with root package name */
    public final LruCache<String, LottieComposition> f2027a = new LruCache<>(20);

    public static LottieCompositionCache getInstance() {
        return b;
    }

    public void clear() {
        this.f2027a.evictAll();
    }

    @Nullable
    public LottieComposition get(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return this.f2027a.get(str);
    }

    public void put(@Nullable String str, LottieComposition lottieComposition) {
        if (str == null) {
            return;
        }
        this.f2027a.put(str, lottieComposition);
    }

    public void resize(int i) {
        this.f2027a.resize(i);
    }
}
