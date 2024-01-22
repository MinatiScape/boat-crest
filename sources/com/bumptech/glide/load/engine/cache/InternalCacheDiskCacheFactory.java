package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import java.io.File;
/* loaded from: classes2.dex */
public final class InternalCacheDiskCacheFactory extends DiskLruCacheFactory {

    /* loaded from: classes2.dex */
    public class a implements DiskLruCacheFactory.CacheDirectoryGetter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f2369a;
        public final /* synthetic */ String b;

        public a(Context context, String str) {
            this.f2369a = context;
            this.b = str;
        }

        @Override // com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
        public File getCacheDirectory() {
            File cacheDir = this.f2369a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.b != null ? new File(cacheDir, this.b) : cacheDir;
        }
    }

    public InternalCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, 262144000L);
    }

    public InternalCacheDiskCacheFactory(Context context, long j) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, j);
    }

    public InternalCacheDiskCacheFactory(Context context, String str, long j) {
        super(new a(context, str), j);
    }
}
