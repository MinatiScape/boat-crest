package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public final class p implements Key {
    public static final LruCache<Class<?>, byte[]> i = new LruCache<>(50);

    /* renamed from: a  reason: collision with root package name */
    public final ArrayPool f2390a;
    public final Key b;
    public final Key c;
    public final int d;
    public final int e;
    public final Class<?> f;
    public final Options g;
    public final Transformation<?> h;

    public p(ArrayPool arrayPool, Key key, Key key2, int i2, int i3, Transformation<?> transformation, Class<?> cls, Options options) {
        this.f2390a = arrayPool;
        this.b = key;
        this.c = key2;
        this.d = i2;
        this.e = i3;
        this.h = transformation;
        this.f = cls;
        this.g = options;
    }

    public final byte[] a() {
        LruCache<Class<?>, byte[]> lruCache = i;
        byte[] bArr = lruCache.get(this.f);
        if (bArr == null) {
            byte[] bytes = this.f.getName().getBytes(Key.CHARSET);
            lruCache.put(this.f, bytes);
            return bytes;
        }
        return bArr;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof p) {
            p pVar = (p) obj;
            return this.e == pVar.e && this.d == pVar.d && Util.bothNullOrEqual(this.h, pVar.h) && this.f.equals(pVar.f) && this.b.equals(pVar.b) && this.c.equals(pVar.c) && this.g.equals(pVar.g);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        int hashCode = (((((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + this.d) * 31) + this.e;
        Transformation<?> transformation = this.h;
        if (transformation != null) {
            hashCode = (hashCode * 31) + transformation.hashCode();
        }
        return (((hashCode * 31) + this.f.hashCode()) * 31) + this.g.hashCode();
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.b + ", signature=" + this.c + ", width=" + this.d + ", height=" + this.e + ", decodedResourceClass=" + this.f + ", transformation='" + this.h + "', options=" + this.g + '}';
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.f2390a.getExact(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.d).putInt(this.e).array();
        this.c.updateDiskCacheKey(messageDigest);
        this.b.updateDiskCacheKey(messageDigest);
        messageDigest.update(bArr);
        Transformation<?> transformation = this.h;
        if (transformation != null) {
            transformation.updateDiskCacheKey(messageDigest);
        }
        this.g.updateDiskCacheKey(messageDigest);
        messageDigest.update(a());
        this.f2390a.put(bArr);
    }
}
