package com.bumptech.glide.signature;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public final class ObjectKey implements Key {

    /* renamed from: a  reason: collision with root package name */
    public final Object f2548a;

    public ObjectKey(@NonNull Object obj) {
        this.f2548a = Preconditions.checkNotNull(obj);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof ObjectKey) {
            return this.f2548a.equals(((ObjectKey) obj).f2548a);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.f2548a.hashCode();
    }

    public String toString() {
        return "ObjectKey{object=" + this.f2548a + '}';
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(this.f2548a.toString().getBytes(Key.CHARSET));
    }
}
