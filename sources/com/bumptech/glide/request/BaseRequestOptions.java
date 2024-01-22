package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import com.bumptech.glide.load.resource.gif.GifOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class BaseRequestOptions<T extends BaseRequestOptions<T>> implements Cloneable {
    public boolean A;
    @Nullable
    public Resources.Theme B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean G;
    public int h;
    @Nullable
    public Drawable l;
    public int m;
    @Nullable
    public Drawable n;
    public int o;
    public boolean t;
    @Nullable
    public Drawable v;
    public int w;
    public float i = 1.0f;
    @NonNull
    public DiskCacheStrategy j = DiskCacheStrategy.AUTOMATIC;
    @NonNull
    public Priority k = Priority.NORMAL;
    public boolean p = true;
    public int q = -1;
    public int r = -1;
    @NonNull
    public Key s = EmptySignature.obtain();
    public boolean u = true;
    @NonNull
    public Options x = new Options();
    @NonNull
    public Map<Class<?>, Transformation<?>> y = new CachedHashCodeArrayMap();
    @NonNull
    public Class<?> z = Object.class;
    public boolean F = true;

    public static boolean c(int i, int i2) {
        return (i & i2) != 0;
    }

    public boolean a() {
        return this.F;
    }

    @NonNull
    @CheckResult
    public T apply(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        if (this.C) {
            return (T) mo14clone().apply(baseRequestOptions);
        }
        if (c(baseRequestOptions.h, 2)) {
            this.i = baseRequestOptions.i;
        }
        if (c(baseRequestOptions.h, 262144)) {
            this.D = baseRequestOptions.D;
        }
        if (c(baseRequestOptions.h, 1048576)) {
            this.G = baseRequestOptions.G;
        }
        if (c(baseRequestOptions.h, 4)) {
            this.j = baseRequestOptions.j;
        }
        if (c(baseRequestOptions.h, 8)) {
            this.k = baseRequestOptions.k;
        }
        if (c(baseRequestOptions.h, 16)) {
            this.l = baseRequestOptions.l;
            this.m = 0;
            this.h &= -33;
        }
        if (c(baseRequestOptions.h, 32)) {
            this.m = baseRequestOptions.m;
            this.l = null;
            this.h &= -17;
        }
        if (c(baseRequestOptions.h, 64)) {
            this.n = baseRequestOptions.n;
            this.o = 0;
            this.h &= -129;
        }
        if (c(baseRequestOptions.h, 128)) {
            this.o = baseRequestOptions.o;
            this.n = null;
            this.h &= -65;
        }
        if (c(baseRequestOptions.h, 256)) {
            this.p = baseRequestOptions.p;
        }
        if (c(baseRequestOptions.h, 512)) {
            this.r = baseRequestOptions.r;
            this.q = baseRequestOptions.q;
        }
        if (c(baseRequestOptions.h, 1024)) {
            this.s = baseRequestOptions.s;
        }
        if (c(baseRequestOptions.h, 4096)) {
            this.z = baseRequestOptions.z;
        }
        if (c(baseRequestOptions.h, 8192)) {
            this.v = baseRequestOptions.v;
            this.w = 0;
            this.h &= -16385;
        }
        if (c(baseRequestOptions.h, 16384)) {
            this.w = baseRequestOptions.w;
            this.v = null;
            this.h &= -8193;
        }
        if (c(baseRequestOptions.h, 32768)) {
            this.B = baseRequestOptions.B;
        }
        if (c(baseRequestOptions.h, 65536)) {
            this.u = baseRequestOptions.u;
        }
        if (c(baseRequestOptions.h, 131072)) {
            this.t = baseRequestOptions.t;
        }
        if (c(baseRequestOptions.h, 2048)) {
            this.y.putAll(baseRequestOptions.y);
            this.F = baseRequestOptions.F;
        }
        if (c(baseRequestOptions.h, 524288)) {
            this.E = baseRequestOptions.E;
        }
        if (!this.u) {
            this.y.clear();
            int i = this.h & (-2049);
            this.h = i;
            this.t = false;
            this.h = i & (-131073);
            this.F = true;
        }
        this.h |= baseRequestOptions.h;
        this.x.putAll(baseRequestOptions.x);
        return selfOrThrowIfLocked();
    }

    @NonNull
    public T autoClone() {
        if (this.A && !this.C) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.C = true;
        return lock();
    }

    public final boolean b(int i) {
        return c(this.h, i);
    }

    @NonNull
    @CheckResult
    public T centerCrop() {
        return j(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    @NonNull
    @CheckResult
    public T centerInside() {
        return f(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @NonNull
    @CheckResult
    public T circleCrop() {
        return j(DownsampleStrategy.CENTER_INSIDE, new CircleCrop());
    }

    @NonNull
    public final T d(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        return g(downsampleStrategy, transformation, false);
    }

    @NonNull
    @CheckResult
    public T decode(@NonNull Class<?> cls) {
        if (this.C) {
            return (T) mo14clone().decode(cls);
        }
        this.z = (Class) Preconditions.checkNotNull(cls);
        this.h |= 4096;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T disallowHardwareConfig() {
        return set(Downsampler.ALLOW_HARDWARE_CONFIG, Boolean.FALSE);
    }

    @NonNull
    @CheckResult
    public T diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        if (this.C) {
            return (T) mo14clone().diskCacheStrategy(diskCacheStrategy);
        }
        this.j = (DiskCacheStrategy) Preconditions.checkNotNull(diskCacheStrategy);
        this.h |= 4;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T dontAnimate() {
        return set(GifOptions.DISABLE_ANIMATION, Boolean.TRUE);
    }

    @NonNull
    @CheckResult
    public T dontTransform() {
        if (this.C) {
            return (T) mo14clone().dontTransform();
        }
        this.y.clear();
        int i = this.h & (-2049);
        this.h = i;
        this.t = false;
        int i2 = i & (-131073);
        this.h = i2;
        this.u = false;
        this.h = i2 | 65536;
        this.F = true;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return set(DownsampleStrategy.OPTION, Preconditions.checkNotNull(downsampleStrategy));
    }

    @NonNull
    public final T e(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        if (this.C) {
            return (T) mo14clone().e(downsampleStrategy, transformation);
        }
        downsample(downsampleStrategy);
        return i(transformation, false);
    }

    @NonNull
    @CheckResult
    public T encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(compressFormat));
    }

    @NonNull
    @CheckResult
    public T encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(i));
    }

    public boolean equals(Object obj) {
        if (obj instanceof BaseRequestOptions) {
            BaseRequestOptions baseRequestOptions = (BaseRequestOptions) obj;
            return Float.compare(baseRequestOptions.i, this.i) == 0 && this.m == baseRequestOptions.m && Util.bothNullOrEqual(this.l, baseRequestOptions.l) && this.o == baseRequestOptions.o && Util.bothNullOrEqual(this.n, baseRequestOptions.n) && this.w == baseRequestOptions.w && Util.bothNullOrEqual(this.v, baseRequestOptions.v) && this.p == baseRequestOptions.p && this.q == baseRequestOptions.q && this.r == baseRequestOptions.r && this.t == baseRequestOptions.t && this.u == baseRequestOptions.u && this.D == baseRequestOptions.D && this.E == baseRequestOptions.E && this.j.equals(baseRequestOptions.j) && this.k == baseRequestOptions.k && this.x.equals(baseRequestOptions.x) && this.y.equals(baseRequestOptions.y) && this.z.equals(baseRequestOptions.z) && Util.bothNullOrEqual(this.s, baseRequestOptions.s) && Util.bothNullOrEqual(this.B, baseRequestOptions.B);
        }
        return false;
    }

    @NonNull
    @CheckResult
    public T error(@Nullable Drawable drawable) {
        if (this.C) {
            return (T) mo14clone().error(drawable);
        }
        this.l = drawable;
        int i = this.h | 16;
        this.h = i;
        this.m = 0;
        this.h = i & (-33);
        return selfOrThrowIfLocked();
    }

    @NonNull
    public final T f(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        return g(downsampleStrategy, transformation, true);
    }

    @NonNull
    @CheckResult
    public T fallback(@Nullable Drawable drawable) {
        if (this.C) {
            return (T) mo14clone().fallback(drawable);
        }
        this.v = drawable;
        int i = this.h | 8192;
        this.h = i;
        this.w = 0;
        this.h = i & (-16385);
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T fitCenter() {
        return f(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    @NonNull
    @CheckResult
    public T format(@NonNull DecodeFormat decodeFormat) {
        Preconditions.checkNotNull(decodeFormat);
        return (T) set(Downsampler.DECODE_FORMAT, decodeFormat).set(GifOptions.DECODE_FORMAT, decodeFormat);
    }

    @NonNull
    @CheckResult
    public T frame(@IntRange(from = 0) long j) {
        return set(VideoDecoder.TARGET_FRAME, Long.valueOf(j));
    }

    @NonNull
    public final T g(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation, boolean z) {
        T e;
        if (z) {
            e = j(downsampleStrategy, transformation);
        } else {
            e = e(downsampleStrategy, transformation);
        }
        e.F = true;
        return e;
    }

    @NonNull
    public final DiskCacheStrategy getDiskCacheStrategy() {
        return this.j;
    }

    public final int getErrorId() {
        return this.m;
    }

    @Nullable
    public final Drawable getErrorPlaceholder() {
        return this.l;
    }

    @Nullable
    public final Drawable getFallbackDrawable() {
        return this.v;
    }

    public final int getFallbackId() {
        return this.w;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.E;
    }

    @NonNull
    public final Options getOptions() {
        return this.x;
    }

    public final int getOverrideHeight() {
        return this.q;
    }

    public final int getOverrideWidth() {
        return this.r;
    }

    @Nullable
    public final Drawable getPlaceholderDrawable() {
        return this.n;
    }

    public final int getPlaceholderId() {
        return this.o;
    }

    @NonNull
    public final Priority getPriority() {
        return this.k;
    }

    @NonNull
    public final Class<?> getResourceClass() {
        return this.z;
    }

    @NonNull
    public final Key getSignature() {
        return this.s;
    }

    public final float getSizeMultiplier() {
        return this.i;
    }

    @Nullable
    public final Resources.Theme getTheme() {
        return this.B;
    }

    @NonNull
    public final Map<Class<?>, Transformation<?>> getTransformations() {
        return this.y;
    }

    public final boolean getUseAnimationPool() {
        return this.G;
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.D;
    }

    public final T h() {
        return this;
    }

    public int hashCode() {
        return Util.hashCode(this.B, Util.hashCode(this.s, Util.hashCode(this.z, Util.hashCode(this.y, Util.hashCode(this.x, Util.hashCode(this.k, Util.hashCode(this.j, Util.hashCode(this.E, Util.hashCode(this.D, Util.hashCode(this.u, Util.hashCode(this.t, Util.hashCode(this.r, Util.hashCode(this.q, Util.hashCode(this.p, Util.hashCode(this.v, Util.hashCode(this.w, Util.hashCode(this.n, Util.hashCode(this.o, Util.hashCode(this.l, Util.hashCode(this.m, Util.hashCode(this.i)))))))))))))))))))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public T i(@NonNull Transformation<Bitmap> transformation, boolean z) {
        if (this.C) {
            return (T) mo14clone().i(transformation, z);
        }
        DrawableTransformation drawableTransformation = new DrawableTransformation(transformation, z);
        k(Bitmap.class, transformation, z);
        k(Drawable.class, drawableTransformation, z);
        k(BitmapDrawable.class, drawableTransformation.asBitmapDrawable(), z);
        k(GifDrawable.class, new GifDrawableTransformation(transformation), z);
        return selfOrThrowIfLocked();
    }

    public final boolean isAutoCloneEnabled() {
        return this.C;
    }

    public final boolean isDiskCacheStrategySet() {
        return b(4);
    }

    public final boolean isLocked() {
        return this.A;
    }

    public final boolean isMemoryCacheable() {
        return this.p;
    }

    public final boolean isPrioritySet() {
        return b(8);
    }

    public final boolean isSkipMemoryCacheSet() {
        return b(256);
    }

    public final boolean isTransformationAllowed() {
        return this.u;
    }

    public final boolean isTransformationRequired() {
        return this.t;
    }

    public final boolean isTransformationSet() {
        return b(2048);
    }

    public final boolean isValidOverride() {
        return Util.isValidDimensions(this.r, this.q);
    }

    @NonNull
    @CheckResult
    public final T j(@NonNull DownsampleStrategy downsampleStrategy, @NonNull Transformation<Bitmap> transformation) {
        if (this.C) {
            return (T) mo14clone().j(downsampleStrategy, transformation);
        }
        downsample(downsampleStrategy);
        return transform(transformation);
    }

    @NonNull
    public <Y> T k(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation, boolean z) {
        if (this.C) {
            return (T) mo14clone().k(cls, transformation, z);
        }
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(transformation);
        this.y.put(cls, transformation);
        int i = this.h | 2048;
        this.h = i;
        this.u = true;
        int i2 = i | 65536;
        this.h = i2;
        this.F = false;
        if (z) {
            this.h = i2 | 131072;
            this.t = true;
        }
        return selfOrThrowIfLocked();
    }

    @NonNull
    public T lock() {
        this.A = true;
        return h();
    }

    @NonNull
    @CheckResult
    public T onlyRetrieveFromCache(boolean z) {
        if (this.C) {
            return (T) mo14clone().onlyRetrieveFromCache(z);
        }
        this.E = z;
        this.h |= 524288;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T optionalCenterCrop() {
        return e(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    @NonNull
    @CheckResult
    public T optionalCenterInside() {
        return d(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @NonNull
    @CheckResult
    public T optionalCircleCrop() {
        return e(DownsampleStrategy.CENTER_OUTSIDE, new CircleCrop());
    }

    @NonNull
    @CheckResult
    public T optionalFitCenter() {
        return d(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    @NonNull
    @CheckResult
    public T optionalTransform(@NonNull Transformation<Bitmap> transformation) {
        return i(transformation, false);
    }

    @NonNull
    @CheckResult
    public T override(int i, int i2) {
        if (this.C) {
            return (T) mo14clone().override(i, i2);
        }
        this.r = i;
        this.q = i2;
        this.h |= 512;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T placeholder(@Nullable Drawable drawable) {
        if (this.C) {
            return (T) mo14clone().placeholder(drawable);
        }
        this.n = drawable;
        int i = this.h | 64;
        this.h = i;
        this.o = 0;
        this.h = i & (-129);
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T priority(@NonNull Priority priority) {
        if (this.C) {
            return (T) mo14clone().priority(priority);
        }
        this.k = (Priority) Preconditions.checkNotNull(priority);
        this.h |= 8;
        return selfOrThrowIfLocked();
    }

    @NonNull
    public final T selfOrThrowIfLocked() {
        if (!this.A) {
            return h();
        }
        throw new IllegalStateException("You cannot modify locked T, consider clone()");
    }

    @NonNull
    @CheckResult
    public <Y> T set(@NonNull Option<Y> option, @NonNull Y y) {
        if (this.C) {
            return (T) mo14clone().set(option, y);
        }
        Preconditions.checkNotNull(option);
        Preconditions.checkNotNull(y);
        this.x.set(option, y);
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T signature(@NonNull Key key) {
        if (this.C) {
            return (T) mo14clone().signature(key);
        }
        this.s = (Key) Preconditions.checkNotNull(key);
        this.h |= 1024;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.C) {
            return (T) mo14clone().sizeMultiplier(f);
        }
        if (f >= 0.0f && f <= 1.0f) {
            this.i = f;
            this.h |= 2;
            return selfOrThrowIfLocked();
        }
        throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }

    @NonNull
    @CheckResult
    public T skipMemoryCache(boolean z) {
        if (this.C) {
            return (T) mo14clone().skipMemoryCache(true);
        }
        this.p = !z;
        this.h |= 256;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T theme(@Nullable Resources.Theme theme) {
        if (this.C) {
            return (T) mo14clone().theme(theme);
        }
        Preconditions.checkNotNull(theme);
        this.B = theme;
        this.h |= 32768;
        return set(ResourceDrawableDecoder.THEME, theme);
    }

    @NonNull
    @CheckResult
    public T timeout(@IntRange(from = 0) int i) {
        return set(HttpGlideUrlLoader.TIMEOUT, Integer.valueOf(i));
    }

    @NonNull
    @CheckResult
    public T transform(@NonNull Transformation<Bitmap> transformation) {
        return i(transformation, true);
    }

    @NonNull
    @CheckResult
    @Deprecated
    public T transforms(@NonNull Transformation<Bitmap>... transformationArr) {
        return i(new MultiTransformation(transformationArr), true);
    }

    @NonNull
    @CheckResult
    public T useAnimationPool(boolean z) {
        if (this.C) {
            return (T) mo14clone().useAnimationPool(z);
        }
        this.G = z;
        this.h |= 1048576;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T useUnlimitedSourceGeneratorsPool(boolean z) {
        if (this.C) {
            return (T) mo14clone().useUnlimitedSourceGeneratorsPool(z);
        }
        this.D = z;
        this.h |= 262144;
        return selfOrThrowIfLocked();
    }

    @Override // 
    @CheckResult
    /* renamed from: clone */
    public T mo14clone() {
        try {
            T t = (T) super.clone();
            Options options = new Options();
            t.x = options;
            options.putAll(this.x);
            CachedHashCodeArrayMap cachedHashCodeArrayMap = new CachedHashCodeArrayMap();
            t.y = cachedHashCodeArrayMap;
            cachedHashCodeArrayMap.putAll(this.y);
            t.A = false;
            t.C = false;
            return t;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @CheckResult
    public <Y> T optionalTransform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        return k(cls, transformation, false);
    }

    @NonNull
    @CheckResult
    public T transform(@NonNull Transformation<Bitmap>... transformationArr) {
        if (transformationArr.length > 1) {
            return i(new MultiTransformation(transformationArr), true);
        }
        if (transformationArr.length == 1) {
            return transform(transformationArr[0]);
        }
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T override(int i) {
        return override(i, i);
    }

    @NonNull
    @CheckResult
    public <Y> T transform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        return k(cls, transformation, true);
    }

    @NonNull
    @CheckResult
    public T error(@DrawableRes int i) {
        if (this.C) {
            return (T) mo14clone().error(i);
        }
        this.m = i;
        int i2 = this.h | 32;
        this.h = i2;
        this.l = null;
        this.h = i2 & (-17);
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T fallback(@DrawableRes int i) {
        if (this.C) {
            return (T) mo14clone().fallback(i);
        }
        this.w = i;
        int i2 = this.h | 16384;
        this.h = i2;
        this.v = null;
        this.h = i2 & (-8193);
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public T placeholder(@DrawableRes int i) {
        if (this.C) {
            return (T) mo14clone().placeholder(i);
        }
        this.o = i;
        int i2 = this.h | 128;
        this.h = i2;
        this.n = null;
        this.h = i2 & (-65);
        return selfOrThrowIfLocked();
    }
}
