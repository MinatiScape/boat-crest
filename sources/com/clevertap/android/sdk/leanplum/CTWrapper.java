package com.clevertap.android.sdk.leanplum;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Logger;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.r;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CTWrapper {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapProvider f2640a;

    public CTWrapper(@NotNull CleverTapProvider ctProvider) {
        Intrinsics.checkNotNullParameter(ctProvider, "ctProvider");
        this.f2640a = ctProvider;
    }

    public final Object a(Map.Entry<String, ? extends Object> entry) {
        Object value = entry.getValue();
        if (value instanceof Iterable) {
            return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt___CollectionsKt.filterNotNull((Iterable) value), com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, "[", "]", 0, null, null, 56, null);
        }
        return value instanceof Byte ? Integer.valueOf(((Number) value).byteValue()) : value instanceof Short ? Integer.valueOf(((Number) value).shortValue()) : value;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0047, code lost:
        if (r6 == null) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void advanceTo(@org.jetbrains.annotations.Nullable java.lang.String r4, @org.jetbrains.annotations.Nullable java.lang.String r5, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, ? extends java.lang.Object> r6) {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "state_"
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            if (r6 == 0) goto L49
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            int r1 = r6.size()
            int r1 = kotlin.collections.r.mapCapacity(r1)
            r0.<init>(r1)
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L2b:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L43
            java.lang.Object r1 = r6.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.Object r1 = r3.a(r1)
            r0.put(r2, r1)
            goto L2b
        L43:
            java.util.Map r6 = kotlin.collections.s.toMutableMap(r0)
            if (r6 != 0) goto L4e
        L49:
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
        L4e:
            if (r5 == 0) goto L55
            java.lang.String r0 = "info"
            r6.put(r0, r5)
        L55:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "advance(...) will call pushEvent with "
            r5.append(r0)
            r5.append(r4)
            java.lang.String r0 = " and "
            r5.append(r0)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r0, r5)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r5 = r3.f2640a
            com.clevertap.android.sdk.CleverTapAPI r5 = r5.getCleverTap()
            if (r5 == 0) goto L7e
            r5.pushEvent(r4, r6)
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.advanceTo(java.lang.String, java.lang.String, java.util.Map):void");
    }

    public final void setTrafficSourceInfo(@NotNull Map<String, String> info) {
        Intrinsics.checkNotNullParameter(info, "info");
        String str = info.get("publisherName");
        String str2 = info.get("publisherSubPublisher");
        String str3 = info.get("publisherSubCampaign");
        Logger.d("CTWrapper", "setTrafficSourceInfo will call pushInstallReferrer with " + str + ", " + str2 + ", and " + str3);
        CleverTapAPI cleverTap = this.f2640a.getCleverTap();
        if (cleverTap != null) {
            cleverTap.pushInstallReferrer(str, str2, str3);
        }
    }

    public final void setUserAttributes(@Nullable Map<String, ? extends Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(r.mapCapacity(linkedHashMap.size()));
        for (Map.Entry<String, ? extends Object> entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), a(entry2));
        }
        Logger.d("CTWrapper", "setUserAttributes will call pushProfile with " + linkedHashMap2);
        CleverTapAPI cleverTap = this.f2640a.getCleverTap();
        if (cleverTap != null) {
            cleverTap.pushProfile(linkedHashMap2);
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Map.Entry<String, ? extends Object> entry3 : map.entrySet()) {
            if (entry3.getValue() == null) {
                linkedHashMap3.put(entry3.getKey(), entry3.getValue());
            }
        }
        for (Map.Entry entry4 : linkedHashMap3.entrySet()) {
            Logger.d("CTWrapper", "setUserAttributes will call removeValueForKey with " + ((String) entry4.getKey()));
            CleverTapAPI cleverTap2 = this.f2640a.getCleverTap();
            if (cleverTap2 != null) {
                cleverTap2.removeValueForKey((String) entry4.getKey());
            }
        }
    }

    public final void setUserId(@Nullable String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        Map<String, Object> mapOf = r.mapOf(TuplesKt.to("Identity", str));
        Logger.d("CTWrapper", "setUserId will call onUserLogin with " + mapOf);
        CleverTapAPI cleverTap = this.f2640a.getCleverTap();
        if (cleverTap != null) {
            cleverTap.onUserLogin(mapOf);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0036, code lost:
        if (r8 == null) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void track(@org.jetbrains.annotations.Nullable java.lang.String r4, double r5, @org.jetbrains.annotations.Nullable java.lang.String r7, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, ? extends java.lang.Object> r8) {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            if (r8 == 0) goto L38
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            int r1 = r8.size()
            int r1 = kotlin.collections.r.mapCapacity(r1)
            r0.<init>(r1)
            java.util.Set r8 = r8.entrySet()
            java.util.Iterator r8 = r8.iterator()
        L1a:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L32
            java.lang.Object r1 = r8.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.Object r1 = r3.a(r1)
            r0.put(r2, r1)
            goto L1a
        L32:
            java.util.Map r8 = kotlin.collections.s.toMutableMap(r0)
            if (r8 != 0) goto L3d
        L38:
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
            r8.<init>()
        L3d:
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
            java.lang.String r6 = "value"
            r8.put(r6, r5)
            if (r7 == 0) goto L4d
            java.lang.String r5 = "info"
            r8.put(r5, r7)
        L4d:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "track(...) will call pushEvent with "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r6 = " and "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r6, r5)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r5 = r3.f2640a
            com.clevertap.android.sdk.CleverTapAPI r5 = r5.getCleverTap()
            if (r5 == 0) goto L76
            r5.pushEvent(r4, r8)
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.track(java.lang.String, double, java.lang.String, java.util.Map):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
        if (r12 == null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void trackGooglePlayPurchase(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.Nullable java.lang.String r6, double r7, @org.jetbrains.annotations.Nullable java.lang.String r9, @org.jetbrains.annotations.Nullable java.lang.String r10, @org.jetbrains.annotations.Nullable java.lang.String r11, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, ? extends java.lang.Object> r12) {
        /*
            r4 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            if (r12 == 0) goto L3a
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            int r2 = r12.size()
            int r2 = kotlin.collections.r.mapCapacity(r2)
            r1.<init>(r2)
            java.util.Set r12 = r12.entrySet()
            java.util.Iterator r12 = r12.iterator()
        L1c:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L34
            java.lang.Object r2 = r12.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r4.a(r2)
            r1.put(r3, r2)
            goto L1c
        L34:
            java.util.Map r12 = kotlin.collections.s.toMutableMap(r1)
            if (r12 != 0) goto L3f
        L3a:
            java.util.LinkedHashMap r12 = new java.util.LinkedHashMap
            r12.<init>()
        L3f:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>(r12)
            r1.put(r0, r5)
            java.lang.Double r5 = java.lang.Double.valueOf(r7)
            java.lang.String r7 = "value"
            r1.put(r7, r5)
            java.lang.String r5 = "currencyCode"
            r1.put(r5, r9)
            java.lang.String r5 = "googlePlayPurchaseData"
            r1.put(r5, r10)
            java.lang.String r5 = "googlePlayPurchaseDataSignature"
            r1.put(r5, r11)
            java.lang.String r5 = "item"
            r1.put(r5, r6)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "trackGooglePlayPurchase will call pushChargedEvent with "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r7 = " and "
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r7, r6)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r6 = r4.f2640a
            com.clevertap.android.sdk.CleverTapAPI r6 = r6.getCleverTap()
            if (r6 == 0) goto L92
            r6.pushChargedEvent(r1, r5)
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.trackGooglePlayPurchase(java.lang.String, java.lang.String, double, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
        if (r9 == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void trackPurchase(@org.jetbrains.annotations.NotNull java.lang.String r5, double r6, @org.jetbrains.annotations.Nullable java.lang.String r8, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, ? extends java.lang.Object> r9) {
        /*
            r4 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            if (r9 == 0) goto L3a
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            int r2 = r9.size()
            int r2 = kotlin.collections.r.mapCapacity(r2)
            r1.<init>(r2)
            java.util.Set r9 = r9.entrySet()
            java.util.Iterator r9 = r9.iterator()
        L1c:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L34
            java.lang.Object r2 = r9.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r4.a(r2)
            r1.put(r3, r2)
            goto L1c
        L34:
            java.util.Map r9 = kotlin.collections.s.toMutableMap(r1)
            if (r9 != 0) goto L3f
        L3a:
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
            r9.<init>()
        L3f:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>(r9)
            r1.put(r0, r5)
            java.lang.Double r5 = java.lang.Double.valueOf(r6)
            java.lang.String r6 = "value"
            r1.put(r6, r5)
            if (r8 == 0) goto L57
            java.lang.String r5 = "currencyCode"
            r1.put(r5, r8)
        L57:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "trackPurchase will call pushChargedEvent with "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r7 = " and "
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r7, r6)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r6 = r4.f2640a
            com.clevertap.android.sdk.CleverTapAPI r6 = r6.getCleverTap()
            if (r6 == 0) goto L85
            r6.pushChargedEvent(r1, r5)
        L85:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.trackPurchase(java.lang.String, double, java.lang.String, java.util.Map):void");
    }
}
