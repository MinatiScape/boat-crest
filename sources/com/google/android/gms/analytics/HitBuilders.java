package com.google.android.gms.analytics;

import android.text.TextUtils;
import androidx.annotation.RecentlyNonNull;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzfa;
import com.google.android.gms.internal.gtm.zzfs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.android.service.MqttServiceConstants;
@VisibleForTesting
/* loaded from: classes6.dex */
public class HitBuilders {

    @VisibleForTesting
    @Deprecated
    /* loaded from: classes6.dex */
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ AppViewBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder> {
        public ExceptionBuilder() {
            set("&t", MqttServiceConstants.TRACE_EXCEPTION);
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @RecentlyNonNull
        public ExceptionBuilder setDescription(@RecentlyNonNull String str) {
            set("&exd", str);
            return this;
        }

        @RecentlyNonNull
        public ExceptionBuilder setFatal(boolean z) {
            set("&exf", zzfs.zzc(z));
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ExceptionBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    public static class HitBuilder<T extends HitBuilder> {
        public ProductAction b;

        /* renamed from: a  reason: collision with root package name */
        public Map<String, String> f8177a = new HashMap();
        public Map<String, List<Product>> c = new HashMap();
        public List<Promotion> d = new ArrayList();
        public List<Product> e = new ArrayList();

        public final T a(String str, String str2) {
            if (str2 != null) {
                this.f8177a.put(str, str2);
            }
            return this;
        }

        @RecentlyNonNull
        public T addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            if (product == null) {
                zzfa.zze("product should be non-null");
                return this;
            }
            if (str == null) {
                str = "";
            }
            if (!this.c.containsKey(str)) {
                this.c.put(str, new ArrayList());
            }
            this.c.get(str).add(product);
            return this;
        }

        @RecentlyNonNull
        public T addProduct(@RecentlyNonNull Product product) {
            if (product == null) {
                zzfa.zze("product should be non-null");
                return this;
            }
            this.e.add(product);
            return this;
        }

        @RecentlyNonNull
        public T addPromotion(@RecentlyNonNull Promotion promotion) {
            if (promotion == null) {
                zzfa.zze("promotion should be non-null");
                return this;
            }
            this.d.add(promotion);
            return this;
        }

        @RecentlyNonNull
        public Map<String, String> build() {
            HashMap hashMap = new HashMap(this.f8177a);
            ProductAction productAction = this.b;
            if (productAction != null) {
                hashMap.putAll(productAction.zza());
            }
            int i = 1;
            for (Promotion promotion : this.d) {
                hashMap.putAll(promotion.zza(zzd.zzl(i)));
                i++;
            }
            int i2 = 1;
            for (Product product : this.e) {
                hashMap.putAll(product.zza(zzd.zzj(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry<String, List<Product>> entry : this.c.entrySet()) {
                String zzg = zzd.zzg(i3);
                int i4 = 1;
                for (Product product2 : entry.getValue()) {
                    String valueOf = String.valueOf(zzg);
                    String valueOf2 = String.valueOf(zzd.zzi(i4));
                    hashMap.putAll(product2.zza(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty(entry.getKey())) {
                    hashMap.put(String.valueOf(zzg).concat(Constants.NOTIF_MSG), entry.getKey());
                }
                i3++;
            }
            return hashMap;
        }

        @RecentlyNonNull
        @VisibleForTesting
        public String get(@RecentlyNonNull String str) {
            return this.f8177a.get(str);
        }

        @RecentlyNonNull
        public final T set(@RecentlyNonNull String str, @RecentlyNonNull String str2) {
            if (str != null) {
                this.f8177a.put(str, str2);
            } else {
                zzfa.zze("HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        @RecentlyNonNull
        public final T setAll(@RecentlyNonNull Map<String, String> map) {
            if (map == null) {
                return this;
            }
            this.f8177a.putAll(new HashMap(map));
            return this;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
            if (r15.contains("=") == false) goto L30;
         */
        @androidx.annotation.RecentlyNonNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public T setCampaignParamsFromUrl(@androidx.annotation.RecentlyNonNull java.lang.String r15) {
            /*
                Method dump skipped, instructions count: 299
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.HitBuilders.HitBuilder.setCampaignParamsFromUrl(java.lang.String):com.google.android.gms.analytics.HitBuilders$HitBuilder");
        }

        @RecentlyNonNull
        public T setCustomDimension(int i, @RecentlyNonNull String str) {
            set(zzd.zza(i), str);
            return this;
        }

        @RecentlyNonNull
        public T setCustomMetric(int i, float f) {
            set(zzd.zzd(i), Float.toString(f));
            return this;
        }

        @RecentlyNonNull
        public T setHitType(@RecentlyNonNull String str) {
            set("&t", str);
            return this;
        }

        @RecentlyNonNull
        public T setNewSession() {
            set("&sc", "start");
            return this;
        }

        @RecentlyNonNull
        public T setNonInteraction(boolean z) {
            set("&ni", zzfs.zzc(z));
            return this;
        }

        @RecentlyNonNull
        public T setProductAction(@RecentlyNonNull ProductAction productAction) {
            this.b = productAction;
            return this;
        }

        @RecentlyNonNull
        public T setPromotionAction(@RecentlyNonNull String str) {
            this.f8177a.put("&promoa", str);
            return this;
        }
    }

    @VisibleForTesting
    @Deprecated
    /* loaded from: classes6.dex */
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            set("&t", com.clevertap.android.sdk.leanplum.Constants.IAP_ITEM_PARAM);
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @RecentlyNonNull
        public ItemBuilder setCategory(@RecentlyNonNull String str) {
            set("&iv", str);
            return this;
        }

        @RecentlyNonNull
        public ItemBuilder setCurrencyCode(@RecentlyNonNull String str) {
            set("&cu", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @RecentlyNonNull
        public ItemBuilder setName(@RecentlyNonNull String str) {
            set("&in", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @RecentlyNonNull
        public ItemBuilder setPrice(double d) {
            set("&ip", Double.toString(d));
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ItemBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }

        @RecentlyNonNull
        public ItemBuilder setQuantity(long j) {
            set("&iq", Long.toString(j));
            return this;
        }

        @RecentlyNonNull
        public ItemBuilder setSku(@RecentlyNonNull String str) {
            set("&ic", str);
            return this;
        }

        @RecentlyNonNull
        public ItemBuilder setTransactionId(@RecentlyNonNull String str) {
            set("&ti", str);
            return this;
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            set("&t", "screenview");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ ScreenViewBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    public static class SocialBuilder extends HitBuilder<SocialBuilder> {
        public SocialBuilder() {
            set("&t", NotificationCompat.CATEGORY_SOCIAL);
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @RecentlyNonNull
        public SocialBuilder setAction(@RecentlyNonNull String str) {
            set("&sa", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @RecentlyNonNull
        public SocialBuilder setNetwork(@RecentlyNonNull String str) {
            set("&sn", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ SocialBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }

        @RecentlyNonNull
        public SocialBuilder setTarget(@RecentlyNonNull String str) {
            set("&st", str);
            return this;
        }
    }

    @VisibleForTesting
    @Deprecated
    /* loaded from: classes6.dex */
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            set("&t", "transaction");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @RecentlyNonNull
        public TransactionBuilder setAffiliation(@RecentlyNonNull String str) {
            set("&ta", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @RecentlyNonNull
        public TransactionBuilder setCurrencyCode(@RecentlyNonNull String str) {
            set("&cu", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TransactionBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }

        @RecentlyNonNull
        public TransactionBuilder setRevenue(double d) {
            set("&tr", Double.toString(d));
            return this;
        }

        @RecentlyNonNull
        public TransactionBuilder setShipping(double d) {
            set("&ts", Double.toString(d));
            return this;
        }

        @RecentlyNonNull
        public TransactionBuilder setTax(double d) {
            set("&tt", Double.toString(d));
            return this;
        }

        @RecentlyNonNull
        public TransactionBuilder setTransactionId(@RecentlyNonNull String str) {
            set("&ti", str);
            return this;
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @RecentlyNonNull
        public EventBuilder setAction(@RecentlyNonNull String str) {
            set("&ea", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @RecentlyNonNull
        public EventBuilder setCategory(@RecentlyNonNull String str) {
            set("&ec", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @RecentlyNonNull
        public EventBuilder setLabel(@RecentlyNonNull String str) {
            set("&el", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ EventBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }

        @RecentlyNonNull
        public EventBuilder setValue(long j) {
            set("&ev", Long.toString(j));
            return this;
        }

        public EventBuilder(@RecentlyNonNull String str, @RecentlyNonNull String str2) {
            this();
            setCategory(str);
            setAction(str2);
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    public static class TimingBuilder extends HitBuilder<TimingBuilder> {
        public TimingBuilder() {
            set("&t", "timing");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder addImpression(@RecentlyNonNull Product product, @RecentlyNonNull String str) {
            super.addImpression(product, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder addProduct(@RecentlyNonNull Product product) {
            super.addProduct(product);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder addPromotion(@RecentlyNonNull Promotion promotion) {
            super.addPromotion(promotion);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder setCampaignParamsFromUrl(@RecentlyNonNull String str) {
            super.setCampaignParamsFromUrl(str);
            return this;
        }

        @RecentlyNonNull
        public TimingBuilder setCategory(@RecentlyNonNull String str) {
            set("&utc", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder setCustomDimension(int i, @RecentlyNonNull String str) {
            super.setCustomDimension(i, str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder setCustomMetric(int i, float f) {
            super.setCustomMetric(i, f);
            return this;
        }

        @RecentlyNonNull
        public TimingBuilder setLabel(@RecentlyNonNull String str) {
            set("&utl", str);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder setNewSession() {
            super.setNewSession();
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder setNonInteraction(boolean z) {
            super.setNonInteraction(z);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder setProductAction(@RecentlyNonNull ProductAction productAction) {
            super.setProductAction(productAction);
            return this;
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        @RecentlyNonNull
        public final /* bridge */ /* synthetic */ TimingBuilder setPromotionAction(@RecentlyNonNull String str) {
            super.setPromotionAction(str);
            return this;
        }

        @RecentlyNonNull
        public TimingBuilder setValue(long j) {
            set("&utt", Long.toString(j));
            return this;
        }

        @RecentlyNonNull
        public TimingBuilder setVariable(@RecentlyNonNull String str) {
            set("&utv", str);
            return this;
        }

        public TimingBuilder(@RecentlyNonNull String str, @RecentlyNonNull String str2, long j) {
            this();
            setVariable(str2);
            setValue(j);
            setCategory(str);
        }
    }
}
