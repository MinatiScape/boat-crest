package com.bumptech.glide.signature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public class MediaStoreSignature implements Key {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final String f2547a;
    public final long b;
    public final int c;

    public MediaStoreSignature(@Nullable String str, long j, int i) {
        this.f2547a = str == null ? "" : str;
        this.b = j;
        this.c = i;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaStoreSignature mediaStoreSignature = (MediaStoreSignature) obj;
        return this.b == mediaStoreSignature.b && this.c == mediaStoreSignature.c && this.f2547a.equals(mediaStoreSignature.f2547a);
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        long j = this.b;
        return (((this.f2547a.hashCode() * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.c;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ByteBuffer.allocate(12).putLong(this.b).putInt(this.c).array());
        messageDigest.update(this.f2547a.getBytes(Key.CHARSET));
    }
}
