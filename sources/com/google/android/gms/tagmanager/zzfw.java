package com.google.android.gms.tagmanager;

import android.content.Context;
import com.clevertap.android.sdk.leanplum.Constants;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@ShowFirstParty
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzfw extends zzft {
    public static final String zza = com.google.android.gms.internal.gtm.zza.UNIVERSAL_ANALYTICS.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.ACCOUNT.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.ANALYTICS_PASS_THROUGH.toString();
    public static final String zzd = com.google.android.gms.internal.gtm.zzb.ENABLE_ECOMMERCE.toString();
    public static final String zze = com.google.android.gms.internal.gtm.zzb.ECOMMERCE_USE_DATA_LAYER.toString();
    public static final String zzf = com.google.android.gms.internal.gtm.zzb.ECOMMERCE_MACRO_DATA.toString();
    public static final String zzg = com.google.android.gms.internal.gtm.zzb.ANALYTICS_FIELDS.toString();
    public static final String zzh = com.google.android.gms.internal.gtm.zzb.TRACK_TRANSACTION.toString();
    public static final String zzi = com.google.android.gms.internal.gtm.zzb.TRANSACTION_DATALAYER_MAP.toString();
    public static final String zzj = com.google.android.gms.internal.gtm.zzb.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    public static final List<String> zzk = Arrays.asList(ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, "purchase", "refund");
    public static final Pattern zzl = Pattern.compile("dimension(\\d+)");
    public static final Pattern zzm = Pattern.compile("metric(\\d+)");
    public static Map<String, String> zzn;
    public static Map<String, String> zzo;
    public final Set<String> zzp;
    public final zzfs zzq;
    public final DataLayer zzr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfw(Context context, DataLayer dataLayer) {
        super(zza, new String[0]);
        zzfs zzfsVar = new zzfs(context);
        this.zzr = dataLayer;
        this.zzq = zzfsVar;
        HashSet hashSet = new HashSet();
        this.zzp = hashSet;
        hashSet.add("");
        hashSet.add(BleConst.GetDeviceTime);
        hashSet.add("false");
    }

    public static final void zzi(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    public static final boolean zzj(Map<String, com.google.android.gms.internal.gtm.zzak> map, String str) {
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(str);
        if (zzakVar == null) {
            return false;
        }
        return zzfv.zzg(zzfv.zzl(zzakVar)).booleanValue();
    }

    public static final Double zzk(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf) : new String("Cannot convert the object to Double: "));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf2) : new String("Cannot convert the object to Double: "));
        }
    }

    public static final Integer zzl(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf) : new String("Cannot convert the object to Integer: "));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf2) : new String("Cannot convert the object to Integer: "));
        }
    }

    public static final Map<String, String> zzm(com.google.android.gms.internal.gtm.zzak zzakVar) {
        Object zzl2 = zzfv.zzl(zzakVar);
        if (zzl2 instanceof Map) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : ((Map) zzl2).entrySet()) {
                linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
            }
            return linkedHashMap;
        }
        return null;
    }

    public static final Product zzn(Map<String, Object> map) {
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get(AppMeasurementSdk.ConditionalUserProperty.NAME);
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get(SavingTrackHelper.POINT_COL_CATEGORY);
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get(DeviceKey.position);
        if (obj7 != null) {
            product.setPosition(zzl(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.PRICE);
        if (obj8 != null) {
            product.setPrice(zzk(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.Param.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzl(obj9).intValue());
        }
        for (String str : map.keySet()) {
            Matcher matcher = zzl.matcher(str);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str)));
                } catch (NumberFormatException unused) {
                    String valueOf = String.valueOf(str);
                    zzdh.zzc(valueOf.length() != 0 ? "illegal number in custom dimension value: ".concat(valueOf) : new String("illegal number in custom dimension value: "));
                }
            } else {
                Matcher matcher2 = zzm.matcher(str);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzl(map.get(str)).intValue());
                    } catch (NumberFormatException unused2) {
                        String valueOf2 = String.valueOf(str);
                        zzdh.zzc(valueOf2.length() != 0 ? "illegal number in custom metric value: ".concat(valueOf2) : new String("illegal number in custom metric value: "));
                    }
                }
            }
        }
        return product;
    }

    @Override // com.google.android.gms.tagmanager.zzft, com.google.android.gms.tagmanager.zzbu
    public final /* bridge */ /* synthetic */ boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzft
    public final void zzc(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        Map<String, String> map2;
        Map<String, String> map3;
        Map map4;
        ProductAction productAction;
        Tracker zza2 = this.zzq.zza("_GTM_DEFAULT_TRACKER_");
        zza2.enableAdvertisingIdCollection(zzj(map, "collect_adid"));
        List<Map> list = null;
        if (zzj(map, zzd)) {
            HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
            Map<String, String> zzh2 = zzh(map.get(zzg));
            screenViewBuilder.setAll(zzh2);
            if (zzj(map, zze)) {
                Object obj = this.zzr.get("ecommerce");
                if (obj instanceof Map) {
                    map4 = (Map) obj;
                }
                map4 = null;
            } else {
                Object zzl2 = zzfv.zzl(map.get(zzf));
                if (zzl2 instanceof Map) {
                    map4 = (Map) zzl2;
                }
                map4 = null;
            }
            if (map4 != null) {
                String str = zzh2.get("&cu");
                if (str == null) {
                    str = (String) map4.get(Constants.CURRENCY_CODE_PARAM);
                }
                if (str != null) {
                    screenViewBuilder.set("&cu", str);
                }
                Object obj2 = map4.get("impressions");
                if (obj2 instanceof List) {
                    for (Map map5 : (List) obj2) {
                        try {
                            screenViewBuilder.addImpression(zzn(map5), (String) map5.get("list"));
                        } catch (RuntimeException e) {
                            String valueOf = String.valueOf(e.getMessage());
                            zzdh.zza(valueOf.length() != 0 ? "Failed to extract a product from DataLayer. ".concat(valueOf) : new String("Failed to extract a product from DataLayer. "));
                        }
                    }
                }
                if (map4.containsKey("promoClick")) {
                    list = (List) ((Map) map4.get("promoClick")).get("promotions");
                } else if (map4.containsKey("promoView")) {
                    list = (List) ((Map) map4.get("promoView")).get("promotions");
                }
                if (list != null) {
                    for (Map map6 : list) {
                        try {
                            Promotion promotion = new Promotion();
                            String str2 = (String) map6.get("id");
                            if (str2 != null) {
                                promotion.setId(str2);
                            }
                            String str3 = (String) map6.get(AppMeasurementSdk.ConditionalUserProperty.NAME);
                            if (str3 != null) {
                                promotion.setName(str3);
                            }
                            String str4 = (String) map6.get("creative");
                            if (str4 != null) {
                                promotion.setCreative(str4);
                            }
                            String str5 = (String) map6.get(DeviceKey.position);
                            if (str5 != null) {
                                promotion.setPosition(str5);
                            }
                            screenViewBuilder.addPromotion(promotion);
                        } catch (RuntimeException e2) {
                            String valueOf2 = String.valueOf(e2.getMessage());
                            zzdh.zza(valueOf2.length() != 0 ? "Failed to extract a promotion from DataLayer. ".concat(valueOf2) : new String("Failed to extract a promotion from DataLayer. "));
                        }
                    }
                    if (map4.containsKey("promoClick")) {
                        screenViewBuilder.set("&promoa", "click");
                    } else {
                        screenViewBuilder.set("&promoa", Promotion.ACTION_VIEW);
                    }
                }
                Iterator<String> it = zzk.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (map4.containsKey(next)) {
                        Map map7 = (Map) map4.get(next);
                        List<Map> list2 = (List) map7.get("products");
                        if (list2 != null) {
                            for (Map map8 : list2) {
                                try {
                                    screenViewBuilder.addProduct(zzn(map8));
                                } catch (RuntimeException e3) {
                                    String valueOf3 = String.valueOf(e3.getMessage());
                                    zzdh.zza(valueOf3.length() != 0 ? "Failed to extract a product from DataLayer. ".concat(valueOf3) : new String("Failed to extract a product from DataLayer. "));
                                }
                            }
                        }
                        try {
                            if (map7.containsKey("actionField")) {
                                Map map9 = (Map) map7.get("actionField");
                                productAction = new ProductAction(next);
                                Object obj3 = map9.get("id");
                                if (obj3 != null) {
                                    productAction.setTransactionId(String.valueOf(obj3));
                                }
                                Object obj4 = map9.get(FirebaseAnalytics.Param.AFFILIATION);
                                if (obj4 != null) {
                                    productAction.setTransactionAffiliation(String.valueOf(obj4));
                                }
                                Object obj5 = map9.get(FirebaseAnalytics.Param.COUPON);
                                if (obj5 != null) {
                                    productAction.setTransactionCouponCode(String.valueOf(obj5));
                                }
                                Object obj6 = map9.get("list");
                                if (obj6 != null) {
                                    productAction.setProductActionList(String.valueOf(obj6));
                                }
                                Object obj7 = map9.get("option");
                                if (obj7 != null) {
                                    productAction.setCheckoutOptions(String.valueOf(obj7));
                                }
                                Object obj8 = map9.get("revenue");
                                if (obj8 != null) {
                                    productAction.setTransactionRevenue(zzk(obj8).doubleValue());
                                }
                                Object obj9 = map9.get(FirebaseAnalytics.Param.TAX);
                                if (obj9 != null) {
                                    productAction.setTransactionTax(zzk(obj9).doubleValue());
                                }
                                Object obj10 = map9.get(FirebaseAnalytics.Param.SHIPPING);
                                if (obj10 != null) {
                                    productAction.setTransactionShipping(zzk(obj10).doubleValue());
                                }
                                Object obj11 = map9.get(DeviceKey.Step);
                                if (obj11 != null) {
                                    productAction.setCheckoutStep(zzl(obj11).intValue());
                                }
                            } else {
                                productAction = new ProductAction(next);
                            }
                            screenViewBuilder.setProductAction(productAction);
                        } catch (RuntimeException e4) {
                            String valueOf4 = String.valueOf(e4.getMessage());
                            zzdh.zza(valueOf4.length() != 0 ? "Failed to extract a product action from DataLayer. ".concat(valueOf4) : new String("Failed to extract a product action from DataLayer. "));
                        }
                    }
                }
            }
            zza2.send(screenViewBuilder.build());
        } else if (zzj(map, zzc)) {
            zza2.send(zzh(map.get(zzg)));
        } else if (zzj(map, zzh)) {
            String zzd2 = zzd("transactionId");
            if (zzd2 == null) {
                zzdh.zza("Cannot find transactionId in data layer.");
                return;
            }
            ArrayList<Map<String, String>> arrayList = new ArrayList();
            try {
                Map<String, String> zzh3 = zzh(map.get(zzg));
                zzh3.put("&t", "transaction");
                com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzi);
                if (zzakVar != null) {
                    map2 = zzm(zzakVar);
                } else {
                    if (zzn == null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("transactionId", "&ti");
                        hashMap.put("transactionAffiliation", "&ta");
                        hashMap.put("transactionTax", "&tt");
                        hashMap.put("transactionShipping", "&ts");
                        hashMap.put("transactionTotal", "&tr");
                        hashMap.put("transactionCurrency", "&cu");
                        zzn = hashMap;
                    }
                    map2 = zzn;
                }
                for (Map.Entry<String, String> entry : map2.entrySet()) {
                    zzi(zzh3, entry.getValue(), zzd(entry.getKey()));
                }
                arrayList.add(zzh3);
                Object obj12 = this.zzr.get("transactionProducts");
                if (obj12 != null) {
                    if (obj12 instanceof List) {
                        for (Object obj13 : (List) obj12) {
                            if (!(obj13 instanceof Map)) {
                                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                            }
                        }
                        list = (List) obj12;
                    } else {
                        throw new IllegalArgumentException("transactionProducts should be of type List.");
                    }
                }
                if (list != null) {
                    for (Map map10 : list) {
                        if (map10.get(AppMeasurementSdk.ConditionalUserProperty.NAME) == null) {
                            zzdh.zza("Unable to send transaction item hit due to missing 'name' field.");
                            return;
                        }
                        Map<String, String> zzh4 = zzh(map.get(zzg));
                        zzh4.put("&t", Constants.IAP_ITEM_PARAM);
                        zzh4.put("&ti", zzd2);
                        com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzj);
                        if (zzakVar2 != null) {
                            map3 = zzm(zzakVar2);
                        } else {
                            if (zzo == null) {
                                HashMap hashMap2 = new HashMap();
                                hashMap2.put(AppMeasurementSdk.ConditionalUserProperty.NAME, "&in");
                                hashMap2.put("sku", "&ic");
                                hashMap2.put(SavingTrackHelper.POINT_COL_CATEGORY, "&iv");
                                hashMap2.put(FirebaseAnalytics.Param.PRICE, "&ip");
                                hashMap2.put(FirebaseAnalytics.Param.QUANTITY, "&iq");
                                hashMap2.put(FirebaseAnalytics.Param.CURRENCY, "&cu");
                                zzo = hashMap2;
                            }
                            map3 = zzo;
                        }
                        for (Map.Entry<String, String> entry2 : map3.entrySet()) {
                            zzi(zzh4, entry2.getValue(), (String) map10.get(entry2.getKey()));
                        }
                        arrayList.add(zzh4);
                    }
                }
                for (Map<String, String> map11 : arrayList) {
                    zza2.send(map11);
                }
            } catch (IllegalArgumentException e5) {
                zzdh.zzb("Unable to send transaction", e5);
            }
        } else {
            zzdh.zzc("Ignoring unknown tag.");
        }
    }

    public final String zzd(String str) {
        Object obj = this.zzr.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public final Map<String, String> zzh(com.google.android.gms.internal.gtm.zzak zzakVar) {
        if (zzakVar == null) {
            return new HashMap();
        }
        Map<String, String> zzm2 = zzm(zzakVar);
        if (zzm2 == null) {
            return new HashMap();
        }
        String str = zzm2.get("&aip");
        if (str != null && this.zzp.contains(str.toLowerCase())) {
            zzm2.remove("&aip");
        }
        return zzm2;
    }
}
