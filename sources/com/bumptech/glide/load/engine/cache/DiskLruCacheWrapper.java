package com.bumptech.glide.load.engine.cache;

import android.util.Log;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
import java.io.IOException;
/* loaded from: classes2.dex */
public class DiskLruCacheWrapper implements DiskCache {
    public static DiskLruCacheWrapper f;
    public final File b;
    public final long c;
    public DiskLruCache e;
    public final a d = new a();

    /* renamed from: a  reason: collision with root package name */
    public final SafeKeyGenerator f2366a = new SafeKeyGenerator();

    @Deprecated
    public DiskLruCacheWrapper(File file, long j) {
        this.b = file;
        this.c = j;
    }

    public static DiskCache create(File file, long j) {
        return new DiskLruCacheWrapper(file, j);
    }

    @Deprecated
    public static synchronized DiskCache get(File file, long j) {
        DiskLruCacheWrapper diskLruCacheWrapper;
        synchronized (DiskLruCacheWrapper.class) {
            if (f == null) {
                f = new DiskLruCacheWrapper(file, j);
            }
            diskLruCacheWrapper = f;
        }
        return diskLruCacheWrapper;
    }

    public final synchronized DiskLruCache a() throws IOException {
        if (this.e == null) {
            this.e = DiskLruCache.open(this.b, 1, 1, this.c);
        }
        return this.e;
    }

    public final synchronized void b() {
        this.e = null;
    }

    @Override // com.bumptech.glide.load.engine.cache.DiskCache
    public synchronized void clear() {
        try {
            a().delete();
        } catch (IOException e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to clear disk cache or disk cache cleared externally", e);
            }
        }
        b();
    }

    @Override // com.bumptech.glide.load.engine.cache.DiskCache
    public void delete(Key key) {
        try {
            a().remove(this.f2366a.getSafeKey(key));
        } catch (IOException e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to delete from disk cache", e);
            }
        }
    }

    @Override // com.bumptech.glide.load.engine.cache.DiskCache
    public void put(Key key, DiskCache.Writer writer) {
        DiskLruCache a2;
        String safeKey = this.f2366a.getSafeKey(key);
        this.d.a(safeKey);
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + safeKey + " for for Key: " + key);
            }
            try {
                a2 = a();
            } catch (IOException e) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e);
                }
            }
            if (a2.get(safeKey) != null) {
                return;
            }
            DiskLruCache.Editor edit = a2.edit(safeKey);
            if (edit != null) {
                try {
                    if (writer.write(edit.getFile(0))) {
                        edit.commit();
                    }
                    edit.abortUnlessCommitted();
                    return;
                } catch (Throwable th) {
                    edit.abortUnlessCommitted();
                    throw th;
                }
            }
            throw new IllegalStateException("Had two simultaneous puts for: " + safeKey);
        } finally {
            this.d.b(safeKey);
        }
    }

    @Override // com.bumptech.glide.load.engine.cache.DiskCache
    public File get(Key key) {
        String safeKey = this.f2366a.getSafeKey(key);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + safeKey + " for for Key: " + key);
        }
        try {
            DiskLruCache.Value value = a().get(safeKey);
            if (value != null) {
                return value.getFile(0);
            }
            return null;
        } catch (IOException e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e);
                return null;
            }
            return null;
        }
    }
}
