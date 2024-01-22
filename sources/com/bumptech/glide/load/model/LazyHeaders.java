package com.bumptech.glide.load.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public final class LazyHeaders implements Headers {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, List<LazyHeaderFactory>> f2405a;
    public volatile Map<String, String> b;

    /* loaded from: classes2.dex */
    public static final class Builder {
        public static final String d;
        public static final Map<String, List<LazyHeaderFactory>> e;

        /* renamed from: a  reason: collision with root package name */
        public boolean f2406a = true;
        public Map<String, List<LazyHeaderFactory>> b = e;
        public boolean c = true;

        static {
            String d2 = d();
            d = d2;
            HashMap hashMap = new HashMap(2);
            if (!TextUtils.isEmpty(d2)) {
                hashMap.put(HttpHeaders.USER_AGENT, Collections.singletonList(new a(d2)));
            }
            e = Collections.unmodifiableMap(hashMap);
        }

        @VisibleForTesting
        public static String d() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i = 0; i < length; i++) {
                char charAt = property.charAt(i);
                if ((charAt > 31 || charAt == '\t') && charAt < 127) {
                    sb.append(charAt);
                } else {
                    sb.append(org.apache.commons.codec.net.a.SEP);
                }
            }
            return sb.toString();
        }

        public final Map<String, List<LazyHeaderFactory>> a() {
            HashMap hashMap = new HashMap(this.b.size());
            for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.b.entrySet()) {
                hashMap.put(entry.getKey(), new ArrayList(entry.getValue()));
            }
            return hashMap;
        }

        public Builder addHeader(@NonNull String str, @NonNull String str2) {
            return addHeader(str, new a(str2));
        }

        public final void b() {
            if (this.f2406a) {
                this.f2406a = false;
                this.b = a();
            }
        }

        public LazyHeaders build() {
            this.f2406a = true;
            return new LazyHeaders(this.b);
        }

        public final List<LazyHeaderFactory> c(String str) {
            List<LazyHeaderFactory> list = this.b.get(str);
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                this.b.put(str, arrayList);
                return arrayList;
            }
            return list;
        }

        public Builder setHeader(@NonNull String str, @Nullable String str2) {
            return setHeader(str, str2 == null ? null : new a(str2));
        }

        public Builder addHeader(@NonNull String str, @NonNull LazyHeaderFactory lazyHeaderFactory) {
            if (this.c && HttpHeaders.USER_AGENT.equalsIgnoreCase(str)) {
                return setHeader(str, lazyHeaderFactory);
            }
            b();
            c(str).add(lazyHeaderFactory);
            return this;
        }

        public Builder setHeader(@NonNull String str, @Nullable LazyHeaderFactory lazyHeaderFactory) {
            b();
            if (lazyHeaderFactory == null) {
                this.b.remove(str);
            } else {
                List<LazyHeaderFactory> c = c(str);
                c.clear();
                c.add(lazyHeaderFactory);
            }
            if (this.c && HttpHeaders.USER_AGENT.equalsIgnoreCase(str)) {
                this.c = false;
            }
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public static final class a implements LazyHeaderFactory {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final String f2407a;

        public a(@NonNull String str) {
            this.f2407a = str;
        }

        @Override // com.bumptech.glide.load.model.LazyHeaderFactory
        public String buildHeader() {
            return this.f2407a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return this.f2407a.equals(((a) obj).f2407a);
            }
            return false;
        }

        public int hashCode() {
            return this.f2407a.hashCode();
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.f2407a + "'}";
        }
    }

    public LazyHeaders(Map<String, List<LazyHeaderFactory>> map) {
        this.f2405a = Collections.unmodifiableMap(map);
    }

    @NonNull
    public final String a(@NonNull List<LazyHeaderFactory> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String buildHeader = list.get(i).buildHeader();
            if (!TextUtils.isEmpty(buildHeader)) {
                sb.append(buildHeader);
                if (i != list.size() - 1) {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    public final Map<String, String> b() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.f2405a.entrySet()) {
            String a2 = a(entry.getValue());
            if (!TextUtils.isEmpty(a2)) {
                hashMap.put(entry.getKey(), a2);
            }
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LazyHeaders) {
            return this.f2405a.equals(((LazyHeaders) obj).f2405a);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.model.Headers
    public Map<String, String> getHeaders() {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    this.b = Collections.unmodifiableMap(b());
                }
            }
        }
        return this.b;
    }

    public int hashCode() {
        return this.f2405a.hashCode();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.f2405a + '}';
    }
}
