package com.google.android.gms.analytics.ecommerce;

import androidx.annotation.RecentlyNonNull;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes6.dex */
public class Promotion {
    @RecentlyNonNull
    public static final String ACTION_CLICK = "click";
    @RecentlyNonNull
    public static final String ACTION_VIEW = "view";

    /* renamed from: a  reason: collision with root package name */
    public Map<String, String> f8182a = new HashMap();

    public final void a(String str, String str2) {
        Preconditions.checkNotNull(str, "Name should be non-null");
        this.f8182a.put(str, str2);
    }

    @RecentlyNonNull
    public Promotion setCreative(@RecentlyNonNull String str) {
        a("cr", str);
        return this;
    }

    @RecentlyNonNull
    public Promotion setId(@RecentlyNonNull String str) {
        a("id", str);
        return this;
    }

    @RecentlyNonNull
    public Promotion setName(@RecentlyNonNull String str) {
        a(Constants.NOTIF_MSG, str);
        return this;
    }

    @RecentlyNonNull
    public Promotion setPosition(@RecentlyNonNull String str) {
        a("ps", str);
        return this;
    }

    @RecentlyNonNull
    public String toString() {
        return zzj.zzb(this.f8182a);
    }

    @RecentlyNonNull
    public final Map<String, String> zza(@RecentlyNonNull String str) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : this.f8182a.entrySet()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(entry.getKey());
            hashMap.put(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), entry.getValue());
        }
        return hashMap;
    }
}
