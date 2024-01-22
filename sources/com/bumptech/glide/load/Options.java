package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;
/* loaded from: classes.dex */
public final class Options implements Key {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayMap<Option<?>, Object> f2331a = new CachedHashCodeArrayMap();

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void a(@NonNull Option<T> option, @NonNull Object obj, @NonNull MessageDigest messageDigest) {
        option.update(obj, messageDigest);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof Options) {
            return this.f2331a.equals(((Options) obj).f2331a);
        }
        return false;
    }

    @Nullable
    public <T> T get(@NonNull Option<T> option) {
        return this.f2331a.containsKey(option) ? (T) this.f2331a.get(option) : option.getDefaultValue();
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.f2331a.hashCode();
    }

    public void putAll(@NonNull Options options) {
        this.f2331a.putAll((SimpleArrayMap<? extends Option<?>, ? extends Object>) options.f2331a);
    }

    @NonNull
    public <T> Options set(@NonNull Option<T> option, @NonNull T t) {
        this.f2331a.put(option, t);
        return this;
    }

    public String toString() {
        return "Options{values=" + this.f2331a + '}';
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        for (int i = 0; i < this.f2331a.size(); i++) {
            a(this.f2331a.keyAt(i), this.f2331a.valueAt(i), messageDigest);
        }
    }
}
