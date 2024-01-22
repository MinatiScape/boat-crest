package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import java.io.File;
/* loaded from: classes2.dex */
public final class ExternalPreferredCacheDiskCacheFactory extends DiskLruCacheFactory {

    /* loaded from: classes2.dex */
    public class a implements DiskLruCacheFactory.CacheDirectoryGetter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f2368a;
        public final /* synthetic */ String b;

        public a(Context context, String str) {
            this.f2368a = context;
            this.b = str;
        }

        @Nullable
        public final File a() {
            File cacheDir = this.f2368a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.b != null ? new File(cacheDir, this.b) : cacheDir;
        }

        @Override // com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
        public File getCacheDirectory() {
            File externalCacheDir;
            File a2 = a();
            return ((a2 == null || !a2.exists()) && (externalCacheDir = this.f2368a.getExternalCacheDir()) != null && externalCacheDir.canWrite()) ? this.b != null ? new File(externalCacheDir, this.b) : externalCacheDir : a2;
        }
    }

    public ExternalPreferredCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, 262144000L);
    }

    public ExternalPreferredCacheDiskCacheFactory(Context context, long j) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, j);
    }

    public ExternalPreferredCacheDiskCacheFactory(Context context, String str, long j) {
        super(new a(context, str), j);
    }
}
