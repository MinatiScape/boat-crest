package com.bumptech.glide.signature;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public final class EmptySignature implements Key {

    /* renamed from: a  reason: collision with root package name */
    public static final EmptySignature f2546a = new EmptySignature();

    @NonNull
    public static EmptySignature obtain() {
        return f2546a;
    }

    public String toString() {
        return "EmptySignature";
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }
}
