package com.google.android.gms.analytics.ecommerce;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes6.dex */
public class ProductAction {
    @RecentlyNonNull
    public static final String ACTION_ADD = "add";
    @RecentlyNonNull
    public static final String ACTION_CHECKOUT = "checkout";
    @RecentlyNonNull
    public static final String ACTION_CHECKOUT_OPTION = "checkout_option";
    @RecentlyNonNull
    @Deprecated
    public static final String ACTION_CHECKOUT_OPTIONS = "checkout_options";
    @RecentlyNonNull
    public static final String ACTION_CLICK = "click";
    @RecentlyNonNull
    public static final String ACTION_DETAIL = "detail";
    @RecentlyNonNull
    public static final String ACTION_PURCHASE = "purchase";
    @RecentlyNonNull
    public static final String ACTION_REFUND = "refund";
    @RecentlyNonNull
    public static final String ACTION_REMOVE = "remove";

    /* renamed from: a  reason: collision with root package name */
    public Map<String, String> f8181a = new HashMap();

    public ProductAction(@RecentlyNonNull String str) {
        a("&pa", str);
    }

    public final void a(String str, String str2) {
        Preconditions.checkNotNull(str, "Name should be non-null");
        this.f8181a.put(str, str2);
    }

    @RecentlyNonNull
    public ProductAction setCheckoutOptions(@RecentlyNonNull String str) {
        a("&col", str);
        return this;
    }

    @RecentlyNonNull
    public ProductAction setCheckoutStep(int i) {
        a("&cos", Integer.toString(i));
        return this;
    }

    @RecentlyNonNull
    public ProductAction setProductActionList(@RecentlyNonNull String str) {
        a("&pal", str);
        return this;
    }

    @RecentlyNonNull
    public ProductAction setProductListSource(@RecentlyNonNull String str) {
        a("&pls", str);
        return this;
    }

    @RecentlyNonNull
    public ProductAction setTransactionAffiliation(@RecentlyNonNull String str) {
        a("&ta", str);
        return this;
    }

    @RecentlyNonNull
    public ProductAction setTransactionCouponCode(@RecentlyNonNull String str) {
        a("&tcc", str);
        return this;
    }

    @RecentlyNonNull
    public ProductAction setTransactionId(@RecentlyNonNull String str) {
        a("&ti", str);
        return this;
    }

    @RecentlyNonNull
    public ProductAction setTransactionRevenue(double d) {
        a("&tr", Double.toString(d));
        return this;
    }

    @RecentlyNonNull
    public ProductAction setTransactionShipping(double d) {
        a("&ts", Double.toString(d));
        return this;
    }

    @RecentlyNonNull
    public ProductAction setTransactionTax(double d) {
        a("&tt", Double.toString(d));
        return this;
    }

    @RecentlyNonNull
    public String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : this.f8181a.entrySet()) {
            if (entry.getKey().startsWith("&")) {
                hashMap.put(entry.getKey().substring(1), entry.getValue());
            } else {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return zzj.zzb(hashMap);
    }

    @RecentlyNonNull
    @VisibleForTesting
    public final Map<String, String> zza() {
        return new HashMap(this.f8181a);
    }
}
