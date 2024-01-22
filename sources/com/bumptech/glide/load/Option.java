package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
/* loaded from: classes.dex */
public final class Option<T> {
    public static final CacheKeyUpdater<Object> e = new a();

    /* renamed from: a  reason: collision with root package name */
    public final T f2330a;
    public final CacheKeyUpdater<T> b;
    public final String c;
    public volatile byte[] d;

    /* loaded from: classes.dex */
    public interface CacheKeyUpdater<T> {
        void update(@NonNull byte[] bArr, @NonNull T t, @NonNull MessageDigest messageDigest);
    }

    /* loaded from: classes.dex */
    public class a implements CacheKeyUpdater<Object> {
        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(@NonNull byte[] bArr, @NonNull Object obj, @NonNull MessageDigest messageDigest) {
        }
    }

    public Option(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        this.c = Preconditions.checkNotEmpty(str);
        this.f2330a = t;
        this.b = (CacheKeyUpdater) Preconditions.checkNotNull(cacheKeyUpdater);
    }

    @NonNull
    public static <T> CacheKeyUpdater<T> a() {
        return (CacheKeyUpdater<T>) e;
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String str, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, null, cacheKeyUpdater);
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String str) {
        return new Option<>(str, null, a());
    }

    @NonNull
    public final byte[] b() {
        if (this.d == null) {
            this.d = this.c.getBytes(Key.CHARSET);
        }
        return this.d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Option) {
            return this.c.equals(((Option) obj).c);
        }
        return false;
    }

    @Nullable
    public T getDefaultValue() {
        return this.f2330a;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public String toString() {
        return "Option{key='" + this.c + "'}";
    }

    public void update(@NonNull T t, @NonNull MessageDigest messageDigest) {
        this.b.update(b(), t, messageDigest);
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, t, cacheKeyUpdater);
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String str, @NonNull T t) {
        return new Option<>(str, t, a());
    }
}
