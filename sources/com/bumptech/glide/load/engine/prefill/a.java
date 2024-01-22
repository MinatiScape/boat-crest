package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class a implements Runnable {
    public static final C0218a p = new C0218a();
    public static final long q = TimeUnit.SECONDS.toMillis(1);
    public final BitmapPool h;
    public final MemoryCache i;
    public final com.bumptech.glide.load.engine.prefill.b j;
    public final C0218a k;
    public final Set<PreFillType> l;
    public final Handler m;
    public long n;
    public boolean o;

    @VisibleForTesting
    /* renamed from: com.bumptech.glide.load.engine.prefill.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0218a {
        public long a() {
            return SystemClock.currentThreadTimeMillis();
        }
    }

    /* loaded from: classes2.dex */
    public static final class b implements Key {
        @Override // com.bumptech.glide.load.Key
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
            throw new UnsupportedOperationException();
        }
    }

    public a(BitmapPool bitmapPool, MemoryCache memoryCache, com.bumptech.glide.load.engine.prefill.b bVar) {
        this(bitmapPool, memoryCache, bVar, p, new Handler(Looper.getMainLooper()));
    }

    @VisibleForTesting
    public boolean a() {
        Bitmap createBitmap;
        long a2 = this.k.a();
        while (!this.j.a() && !e(a2)) {
            PreFillType b2 = this.j.b();
            if (!this.l.contains(b2)) {
                this.l.add(b2);
                createBitmap = this.h.getDirty(b2.d(), b2.b(), b2.a());
            } else {
                createBitmap = Bitmap.createBitmap(b2.d(), b2.b(), b2.a());
            }
            int bitmapByteSize = Util.getBitmapByteSize(createBitmap);
            if (c() >= bitmapByteSize) {
                this.i.put(new b(), BitmapResource.obtain(createBitmap, this.h));
            } else {
                this.h.put(createBitmap);
            }
            if (Log.isLoggable("PreFillRunner", 3)) {
                Log.d("PreFillRunner", "allocated [" + b2.d() + "x" + b2.b() + "] " + b2.a() + " size: " + bitmapByteSize);
            }
        }
        return (this.o || this.j.a()) ? false : true;
    }

    public void b() {
        this.o = true;
    }

    public final long c() {
        return this.i.getMaxSize() - this.i.getCurrentSize();
    }

    public final long d() {
        long j = this.n;
        this.n = Math.min(4 * j, q);
        return j;
    }

    public final boolean e(long j) {
        return this.k.a() - j >= 32;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (a()) {
            this.m.postDelayed(this, d());
        }
    }

    @VisibleForTesting
    public a(BitmapPool bitmapPool, MemoryCache memoryCache, com.bumptech.glide.load.engine.prefill.b bVar, C0218a c0218a, Handler handler) {
        this.l = new HashSet();
        this.n = 40L;
        this.h = bitmapPool;
        this.i = memoryCache;
        this.j = bVar;
        this.k = c0218a;
        this.m = handler;
    }
}
