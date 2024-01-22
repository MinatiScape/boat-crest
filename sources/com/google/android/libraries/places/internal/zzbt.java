package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.internal.zzdc;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
abstract class zzbt<TypeT, RequestT extends zzdc> extends zzaj<TypeT, RequestT> {
    @Nullable
    private final Locale zza;
    private final String zzb;
    private final boolean zzc;
    private final zzdr zzd;

    public zzbt(RequestT requestt, @Nullable Locale locale, String str, boolean z, zzdr zzdrVar) {
        super(requestt);
        this.zza = locale;
        this.zzb = str;
        this.zzc = z;
        this.zzd = zzdrVar;
    }

    public static void zza(Map<String, String> map, String str, @Nullable Object obj, @Nullable Object obj2) {
        String obj3 = obj != null ? obj.toString() : null;
        if (TextUtils.isEmpty(obj3)) {
            return;
        }
        map.put(str, obj3);
    }

    @Override // com.google.android.libraries.places.internal.zzaj
    public final Map<String, String> zzc() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.zzd.zza());
        String str = this.zzc ? ".compat" : "";
        hashMap.put("X-Places-Android-Sdk", str.length() != 0 ? "2.4.0".concat(str) : new String("2.4.0"));
        return hashMap;
    }

    @Override // com.google.android.libraries.places.internal.zzaj
    public final String zzd() {
        return new zzcf(zzf(), this.zzb).zza(this.zza).zza(zze()).zza();
    }

    public abstract Map<String, String> zze();

    public abstract String zzf();
}
