package com.google.android.gms.analytics.ecommerce;

import androidx.annotation.RecentlyNonNull;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.analytics.zzd;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes6.dex */
public class Product {

    /* renamed from: a  reason: collision with root package name */
    public Map<String, String> f8180a = new HashMap();

    public final void a(String str, String str2) {
        Preconditions.checkNotNull(str, "Name should be non-null");
        this.f8180a.put(str, str2);
    }

    @RecentlyNonNull
    public Product setBrand(@RecentlyNonNull String str) {
        a("br", str);
        return this;
    }

    @RecentlyNonNull
    public Product setCategory(@RecentlyNonNull String str) {
        a("ca", str);
        return this;
    }

    @RecentlyNonNull
    public Product setCouponCode(@RecentlyNonNull String str) {
        a("cc", str);
        return this;
    }

    @RecentlyNonNull
    public Product setCustomDimension(int i, @RecentlyNonNull String str) {
        a(zzd.zzc(i), str);
        return this;
    }

    @RecentlyNonNull
    public Product setCustomMetric(int i, int i2) {
        a(zzd.zzf(i), Integer.toString(i2));
        return this;
    }

    @RecentlyNonNull
    public Product setId(@RecentlyNonNull String str) {
        a("id", str);
        return this;
    }

    @RecentlyNonNull
    public Product setName(@RecentlyNonNull String str) {
        a(Constants.NOTIF_MSG, str);
        return this;
    }

    @RecentlyNonNull
    public Product setPosition(int i) {
        a("ps", Integer.toString(i));
        return this;
    }

    @RecentlyNonNull
    public Product setPrice(double d) {
        a(Constants.NOTIF_PRIORITY, Double.toString(d));
        return this;
    }

    @RecentlyNonNull
    public Product setQuantity(int i) {
        a("qt", Integer.toString(i));
        return this;
    }

    @RecentlyNonNull
    public Product setVariant(@RecentlyNonNull String str) {
        a("va", str);
        return this;
    }

    @RecentlyNonNull
    public String toString() {
        return zzj.zzb(this.f8180a);
    }

    @RecentlyNonNull
    public final Map<String, String> zza(@RecentlyNonNull String str) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : this.f8180a.entrySet()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(entry.getKey());
            hashMap.put(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), entry.getValue());
        }
        return hashMap;
    }
}
