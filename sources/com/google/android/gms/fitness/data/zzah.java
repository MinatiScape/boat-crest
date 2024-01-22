package com.google.android.gms.fitness.data;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@ShowFirstParty
/* loaded from: classes6.dex */
public final class zzah {
    public static final double c;
    public static final double d;
    public static final double e;
    public static final double f;
    public static final zzah g;
    public static final Set<String> zzom;

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Map<String, zzaj>> f8434a;
    public final Map<String, zzaj> b;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        c = 10.0d / timeUnit.toNanos(1L);
        d = 1000.0d / timeUnit.toNanos(1L);
        e = 2000.0d / TimeUnit.HOURS.toNanos(1L);
        f = 100.0d / timeUnit.toNanos(1L);
        zzom = Collections.unmodifiableSet(new HashSet(Arrays.asList(SavingTrackHelper.TRACK_COL_ALTITUDE, "duration", "food_item", "meal_type", "repetitions", "resistance", "resistance_type")));
        g = new zzah();
    }

    public zzah() {
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", new zzaj(-90.0d, 90.0d));
        hashMap.put("longitude", new zzaj(-180.0d, 180.0d));
        hashMap.put("accuracy", new zzaj(0.0d, 10000.0d));
        hashMap.put("bpm", new zzaj(0.0d, 1000.0d));
        hashMap.put(SavingTrackHelper.TRACK_COL_ALTITUDE, new zzaj(-100000.0d, 100000.0d));
        hashMap.put("percentage", new zzaj(0.0d, 100.0d));
        hashMap.put("confidence", new zzaj(0.0d, 100.0d));
        hashMap.put("duration", new zzaj(0.0d, 9.223372036854776E18d));
        hashMap.put(Property.ICON_TEXT_FIT_HEIGHT, new zzaj(0.0d, 3.0d));
        hashMap.put("weight", new zzaj(0.0d, 1000.0d));
        hashMap.put("speed", new zzaj(0.0d, 11000.0d));
        this.b = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("com.google.step_count.delta", a("steps", new zzaj(0.0d, c)));
        hashMap2.put("com.google.calories.consumed", a("calories", new zzaj(0.0d, d)));
        hashMap2.put("com.google.calories.expended", a("calories", new zzaj(0.0d, e)));
        hashMap2.put("com.google.distance.delta", a("distance", new zzaj(0.0d, f)));
        this.f8434a = Collections.unmodifiableMap(hashMap2);
    }

    public static <K, V> Map<K, V> a(K k, V v) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        return hashMap;
    }

    public static zzah zzt() {
        return g;
    }

    @Nullable
    public final zzaj zza(String str, String str2) {
        Map<String, zzaj> map = this.f8434a.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    @Nullable
    public final zzaj zzi(String str) {
        return this.b.get(str);
    }
}
