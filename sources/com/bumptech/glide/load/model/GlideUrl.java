package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;
/* loaded from: classes2.dex */
public class GlideUrl implements Key {

    /* renamed from: a  reason: collision with root package name */
    public final Headers f2404a;
    @Nullable
    public final URL b;
    @Nullable
    public final String c;
    @Nullable
    public String d;
    @Nullable
    public URL e;
    @Nullable
    public volatile byte[] f;
    public int g;

    public GlideUrl(URL url) {
        this(url, Headers.DEFAULT);
    }

    public final byte[] a() {
        if (this.f == null) {
            this.f = getCacheKey().getBytes(Key.CHARSET);
        }
        return this.f;
    }

    public final String b() {
        if (TextUtils.isEmpty(this.d)) {
            String str = this.c;
            if (TextUtils.isEmpty(str)) {
                str = ((URL) Preconditions.checkNotNull(this.b)).toString();
            }
            this.d = Uri.encode(str, "@#&=*+-_.,:!?()/~'%;$");
        }
        return this.d;
    }

    public final URL c() throws MalformedURLException {
        if (this.e == null) {
            this.e = new URL(b());
        }
        return this.e;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof GlideUrl) {
            GlideUrl glideUrl = (GlideUrl) obj;
            return getCacheKey().equals(glideUrl.getCacheKey()) && this.f2404a.equals(glideUrl.f2404a);
        }
        return false;
    }

    public String getCacheKey() {
        String str = this.c;
        return str != null ? str : ((URL) Preconditions.checkNotNull(this.b)).toString();
    }

    public Map<String, String> getHeaders() {
        return this.f2404a.getHeaders();
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        if (this.g == 0) {
            int hashCode = getCacheKey().hashCode();
            this.g = hashCode;
            this.g = (hashCode * 31) + this.f2404a.hashCode();
        }
        return this.g;
    }

    public String toString() {
        return getCacheKey();
    }

    public String toStringUrl() {
        return b();
    }

    public URL toURL() throws MalformedURLException {
        return c();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(a());
    }

    public GlideUrl(String str) {
        this(str, Headers.DEFAULT);
    }

    public GlideUrl(URL url, Headers headers) {
        this.b = (URL) Preconditions.checkNotNull(url);
        this.c = null;
        this.f2404a = (Headers) Preconditions.checkNotNull(headers);
    }

    public GlideUrl(String str, Headers headers) {
        this.b = null;
        this.c = Preconditions.checkNotEmpty(str);
        this.f2404a = (Headers) Preconditions.checkNotNull(headers);
    }
}
