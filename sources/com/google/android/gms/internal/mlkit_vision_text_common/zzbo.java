package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public abstract class zzbo implements Map, Serializable {
    @CheckForNull
    private transient zzbp zza;
    @CheckForNull
    private transient zzbp zzb;
    @CheckForNull
    private transient zzbh zzc;

    public static zzbo zzc(Object obj, Object obj2) {
        q.b("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return u0.zzg(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, null);
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
        return zzcn.a(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzbp zzbpVar = this.zzb;
        if (zzbpVar == null) {
            zzbp zze = zze();
            this.zzb = zze;
            return zze;
        }
        return zzbpVar;
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
        q.a(size, "size");
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

    public abstract zzbh zza();

    @Override // java.util.Map
    /* renamed from: zzb */
    public final zzbh values() {
        zzbh zzbhVar = this.zzc;
        if (zzbhVar == null) {
            zzbh zza = zza();
            this.zzc = zza;
            return zza;
        }
        return zzbhVar;
    }

    public abstract zzbp zzd();

    public abstract zzbp zze();

    @Override // java.util.Map
    /* renamed from: zzf */
    public final zzbp entrySet() {
        zzbp zzbpVar = this.zza;
        if (zzbpVar == null) {
            zzbp zzd = zzd();
            this.zza = zzd;
            return zzd;
        }
        return zzbpVar;
    }
}
