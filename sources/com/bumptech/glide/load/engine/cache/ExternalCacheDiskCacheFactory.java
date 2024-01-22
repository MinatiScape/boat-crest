package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import java.io.File;
@Deprecated
/* loaded from: classes2.dex */
public final class ExternalCacheDiskCacheFactory extends DiskLruCacheFactory {

    /* loaded from: classes2.dex */
    public class a implements DiskLruCacheFactory.CacheDirectoryGetter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f2367a;
        public final /* synthetic */ String b;

        public a(Context context, String str) {
            this.f2367a = context;
            this.b = str;
        }

        @Override // com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
        public File getCacheDirectory() {
            File externalCacheDir = this.f2367a.getExternalCacheDir();
            if (externalCacheDir == null) {
                return null;
            }
            return this.b != null ? new File(externalCacheDir, this.b) : externalCacheDir;
        }
    }

    public ExternalCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE);
    }

    public ExternalCacheDiskCacheFactory(Context context, int i) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, i);
    }

    public ExternalCacheDiskCacheFactory(Context context, String str, int i) {
        super(new a(context, str), i);
    }
}
