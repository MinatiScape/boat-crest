package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public abstract class zzcx implements Map, Serializable {
    @CheckForNull
    private transient zzcy zza;
    @CheckForNull
    private transient zzcy zzb;
    @CheckForNull
    private transient zzcq zzc;

    public static zzcx zzc(Object obj, Object obj2) {
        b0.b("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return d1.zzg(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, null);
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @Override // java.util.Map
    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzdv.a(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzcy zzcyVar = this.zzb;
        if (zzcyVar == null) {
            zzcy zze = zze();
            this.zzb = zze;
            return zze;
        }
        return zzcyVar;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        b0.a(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    public abstract zzcq zza();

    @Override // java.util.Map
    /* renamed from: zzb */
    public final zzcq values() {
        zzcq zzcqVar = this.zzc;
        if (zzcqVar == null) {
            zzcq zza = zza();
            this.zzc = zza;
            return zza;
        }
        return zzcqVar;
    }

    public abstract zzcy zzd();

    public abstract zzcy zze();

    @Override // java.util.Map
    /* renamed from: zzf */
    public final zzcy entrySet() {
        zzcy zzcyVar = this.zza;
        if (zzcyVar == null) {
            zzcy zzd = zzd();
            this.zza = zzd;
            return zzd;
        }
        return zzcyVar;
    }
}
