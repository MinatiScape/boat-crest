package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzaa {

    /* renamed from: a  reason: collision with root package name */
    public String f8939a;
    public final long b;
    public final Map<String, Object> c;

    public zzaa(String str, long j, Map<String, Object> map) {
        this.f8939a = str;
        this.b = j;
        HashMap hashMap = new HashMap();
        this.c = hashMap;
        if (map != null) {
            hashMap.putAll(map);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzaa) {
            zzaa zzaaVar = (zzaa) obj;
            if (this.b == zzaaVar.b && this.f8939a.equals(zzaaVar.f8939a)) {
                return this.c.equals(zzaaVar.c);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f8939a.hashCode();
        long j = this.b;
        return (((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.c.hashCode();
    }

    public final String toString() {
        String str = this.f8939a;
        long j = this.b;
        String valueOf = String.valueOf(this.c);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 55 + valueOf.length());
        sb.append("Event{name='");
        sb.append(str);
        sb.append("', timestamp=");
        sb.append(j);
        sb.append(", params=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }

    public final long zza() {
        return this.b;
    }

    /* renamed from: zzb */
    public final zzaa clone() {
        return new zzaa(this.f8939a, this.b, new HashMap(this.c));
    }

    public final Object zzc(String str) {
        if (this.c.containsKey(str)) {
            return this.c.get(str);
        }
        return null;
    }

    public final String zzd() {
        return this.f8939a;
    }

    public final Map<String, Object> zze() {
        return this.c;
    }

    public final void zzf(String str) {
        this.f8939a = str;
    }

    public final void zzg(String str, Object obj) {
        if (obj == null) {
            this.c.remove(str);
        } else {
            this.c.put(str, obj);
        }
    }
}
