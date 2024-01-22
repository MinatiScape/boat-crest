package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
/* loaded from: classes2.dex */
public class DiskLruCacheFactory implements DiskCache.Factory {

    /* renamed from: a  reason: collision with root package name */
    public final long f2363a;
    public final CacheDirectoryGetter b;

    /* loaded from: classes2.dex */
    public interface CacheDirectoryGetter {
        File getCacheDirectory();
    }

    /* loaded from: classes2.dex */
    public class a implements CacheDirectoryGetter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f2364a;

        public a(String str) {
            this.f2364a = str;
        }

        @Override // com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
        public File getCacheDirectory() {
            return new File(this.f2364a);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements CacheDirectoryGetter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f2365a;
        public final /* synthetic */ String b;

        public b(String str, String str2) {
            this.f2365a = str;
            this.b = str2;
        }

        @Override // com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
        public File getCacheDirectory() {
            return new File(this.f2365a, this.b);
        }
    }

    public DiskLruCacheFactory(String str, long j) {
        this(new a(str), j);
    }

    @Override // com.bumptech.glide.load.engine.cache.DiskCache.Factory
    public DiskCache build() {
        File cacheDirectory = this.b.getCacheDirectory();
        if (cacheDirectory == null) {
            return null;
        }
        if (cacheDirectory.isDirectory() || cacheDirectory.mkdirs()) {
            return DiskLruCacheWrapper.create(cacheDirectory, this.f2363a);
        }
        return null;
    }

    public DiskLruCacheFactory(String str, String str2, long j) {
        this(new b(str, str2), j);
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter, long j) {
        this.f2363a = j;
        this.b = cacheDirectoryGetter;
    }
}
