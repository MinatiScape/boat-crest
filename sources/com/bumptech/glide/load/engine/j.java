package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
import java.util.Map;
/* loaded from: classes2.dex */
public class j implements Key {

    /* renamed from: a  reason: collision with root package name */
    public final Object f2388a;
    public final int b;
    public final int c;
    public final Class<?> d;
    public final Class<?> e;
    public final Key f;
    public final Map<Class<?>, Transformation<?>> g;
    public final Options h;
    public int i;

    public j(Object obj, Key key, int i, int i2, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options) {
        this.f2388a = Preconditions.checkNotNull(obj);
        this.f = (Key) Preconditions.checkNotNull(key, "Signature must not be null");
        this.b = i;
        this.c = i2;
        this.g = (Map) Preconditions.checkNotNull(map);
        this.d = (Class) Preconditions.checkNotNull(cls, "Resource class must not be null");
        this.e = (Class) Preconditions.checkNotNull(cls2, "Transcode class must not be null");
        this.h = (Options) Preconditions.checkNotNull(options);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof j) {
            j jVar = (j) obj;
            return this.f2388a.equals(jVar.f2388a) && this.f.equals(jVar.f) && this.c == jVar.c && this.b == jVar.b && this.g.equals(jVar.g) && this.d.equals(jVar.d) && this.e.equals(jVar.e) && this.h.equals(jVar.h);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        if (this.i == 0) {
            int hashCode = this.f2388a.hashCode();
            this.i = hashCode;
            int hashCode2 = (hashCode * 31) + this.f.hashCode();
            this.i = hashCode2;
            int i = (hashCode2 * 31) + this.b;
            this.i = i;
            int i2 = (i * 31) + this.c;
            this.i = i2;
            int hashCode3 = (i2 * 31) + this.g.hashCode();
            this.i = hashCode3;
            int hashCode4 = (hashCode3 * 31) + this.d.hashCode();
            this.i = hashCode4;
            int hashCode5 = (hashCode4 * 31) + this.e.hashCode();
            this.i = hashCode5;
            this.i = (hashCode5 * 31) + this.h.hashCode();
        }
        return this.i;
    }

    public String toString() {
        return "EngineKey{model=" + this.f2388a + ", width=" + this.b + ", height=" + this.c + ", resourceClass=" + this.d + ", transcodeClass=" + this.e + ", signature=" + this.f + ", hashCode=" + this.i + ", transformations=" + this.g + ", options=" + this.h + '}';
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
