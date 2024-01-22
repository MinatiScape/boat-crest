package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public class LruBitmapPool implements BitmapPool {
    public static final Bitmap.Config k = Bitmap.Config.ARGB_8888;

    /* renamed from: a  reason: collision with root package name */
    public final e f2354a;
    public final Set<Bitmap.Config> b;
    public final long c;
    public final a d;
    public long e;
    public long f;
    public int g;
    public int h;
    public int i;
    public int j;

    /* loaded from: classes2.dex */
    public interface a {
        void a(Bitmap bitmap);

        void b(Bitmap bitmap);
    }

    /* loaded from: classes2.dex */
    public static final class b implements a {
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.a
        public void a(Bitmap bitmap) {
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.a
        public void b(Bitmap bitmap) {
        }
    }

    public LruBitmapPool(long j, e eVar, Set<Bitmap.Config> set) {
        this.c = j;
        this.e = j;
        this.f2354a = eVar;
        this.b = set;
        this.d = new b();
    }

    @TargetApi(26)
    public static void a(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
    }

    @NonNull
    public static Bitmap b(int i, int i2, @Nullable Bitmap.Config config) {
        if (config == null) {
            config = k;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    @TargetApi(26)
    public static Set<Bitmap.Config> f() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            hashSet.add(null);
        }
        if (i >= 26) {
            hashSet.remove(Bitmap.Config.HARDWARE);
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public static e g() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new SizeConfigStrategy();
        }
        return new com.bumptech.glide.load.engine.bitmap_recycle.b();
    }

    @TargetApi(19)
    public static void i(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            bitmap.setPremultiplied(true);
        }
    }

    public static void j(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        i(bitmap);
    }

    public final void c() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            d();
        }
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        k(0L);
    }

    public final void d() {
        Log.v("LruBitmapPool", "Hits=" + this.g + ", misses=" + this.h + ", puts=" + this.i + ", evictions=" + this.j + ", currentSize=" + this.f + ", maxSize=" + this.e + "\nStrategy=" + this.f2354a);
    }

    public final void e() {
        k(this.e);
    }

    public long evictionCount() {
        return this.j;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @NonNull
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        Bitmap h = h(i, i2, config);
        if (h != null) {
            h.eraseColor(0);
            return h;
        }
        return b(i, i2, config);
    }

    public long getCurrentSize() {
        return this.f;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @NonNull
    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        Bitmap h = h(i, i2, config);
        return h == null ? b(i, i2, config) : h;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public long getMaxSize() {
        return this.e;
    }

    @Nullable
    public final synchronized Bitmap h(int i, int i2, @Nullable Bitmap.Config config) {
        Bitmap bitmap;
        a(config);
        bitmap = this.f2354a.get(i, i2, config != null ? config : k);
        if (bitmap == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Missing bitmap=" + this.f2354a.logBitmap(i, i2, config));
            }
            this.h++;
        } else {
            this.g++;
            this.f -= this.f2354a.getSize(bitmap);
            this.d.a(bitmap);
            j(bitmap);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            Log.v("LruBitmapPool", "Get bitmap=" + this.f2354a.logBitmap(i, i2, config));
        }
        c();
        return bitmap;
    }

    public long hitCount() {
        return this.g;
    }

    public final synchronized void k(long j) {
        while (this.f > j) {
            Bitmap removeLast = this.f2354a.removeLast();
            if (removeLast == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    d();
                }
                this.f = 0L;
                return;
            }
            this.d.a(removeLast);
            this.f -= this.f2354a.getSize(removeLast);
            this.j++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Evicting bitmap=" + this.f2354a.logBitmap(removeLast));
            }
            c();
            removeLast.recycle();
        }
    }

    public long missCount() {
        return this.h;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized void put(Bitmap bitmap) {
        try {
            if (bitmap != null) {
                if (!bitmap.isRecycled()) {
                    if (bitmap.isMutable() && this.f2354a.getSize(bitmap) <= this.e && this.b.contains(bitmap.getConfig())) {
                        int size = this.f2354a.getSize(bitmap);
                        this.f2354a.put(bitmap);
                        this.d.b(bitmap);
                        this.i++;
                        this.f += size;
                        if (Log.isLoggable("LruBitmapPool", 2)) {
                            Log.v("LruBitmapPool", "Put bitmap in pool=" + this.f2354a.logBitmap(bitmap));
                        }
                        c();
                        e();
                        return;
                    }
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        Log.v("LruBitmapPool", "Reject bitmap from pool, bitmap: " + this.f2354a.logBitmap(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.b.contains(bitmap.getConfig()));
                    }
                    bitmap.recycle();
                    return;
                }
                throw new IllegalStateException("Cannot pool recycled bitmap");
            }
            throw new NullPointerException("Bitmap must not be null");
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized void setSizeMultiplier(float f) {
        this.e = Math.round(((float) this.c) * f);
        e();
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "trimMemory, level=" + i);
        }
        if (i >= 40 || (Build.VERSION.SDK_INT >= 23 && i >= 20)) {
            clearMemory();
        } else if (i >= 20 || i == 15) {
            k(getMaxSize() / 2);
        }
    }

    public LruBitmapPool(long j) {
        this(j, g(), f());
    }

    public LruBitmapPool(long j, Set<Bitmap.Config> set) {
        this(j, g(), set);
    }
}
