package com.bumptech.glide.load.engine.cache;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes2.dex */
public class SafeKeyGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final LruCache<Key, String> f2374a = new LruCache<>(1000);
    public final Pools.Pool<b> b = FactoryPools.threadSafe(10, new a(this));

    /* loaded from: classes2.dex */
    public class a implements FactoryPools.Factory<b> {
        public a(SafeKeyGenerator safeKeyGenerator) {
        }

        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        /* renamed from: a */
        public b create() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class b implements FactoryPools.Poolable {
        public final MessageDigest h;
        public final StateVerifier i = StateVerifier.newInstance();

        public b(MessageDigest messageDigest) {
            this.h = messageDigest;
        }

        @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
        @NonNull
        public StateVerifier getVerifier() {
            return this.i;
        }
    }

    public final String a(Key key) {
        b bVar = (b) Preconditions.checkNotNull(this.b.acquire());
        try {
            key.updateDiskCacheKey(bVar.h);
            return Util.sha256BytesToHex(bVar.h.digest());
        } finally {
            this.b.release(bVar);
        }
    }

    public String getSafeKey(Key key) {
        String str;
        synchronized (this.f2374a) {
            str = this.f2374a.get(key);
        }
        if (str == null) {
            str = a(key);
        }
        synchronized (this.f2374a) {
            this.f2374a.put(key, str);
        }
        return str;
    }
}
