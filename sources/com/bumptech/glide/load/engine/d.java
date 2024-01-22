package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public final class d implements Key {

    /* renamed from: a  reason: collision with root package name */
    public final Key f2378a;
    public final Key b;

    public d(Key key, Key key2) {
        this.f2378a = key;
        this.b = key2;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            return this.f2378a.equals(dVar.f2378a) && this.b.equals(dVar.b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return (this.f2378a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.f2378a + ", signature=" + this.b + '}';
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.f2378a.updateDiskCacheKey(messageDigest);
        this.b.updateDiskCacheKey(messageDigest);
    }
}
