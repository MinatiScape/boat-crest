package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class m extends w0 {
    public final transient Map j;
    public final /* synthetic */ v k;

    public m(v vVar, Map map) {
        this.k = vVar;
        this.j = map;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.w0
    public final Set a() {
        return new k(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    /* renamed from: b */
    public final Collection get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzdi.a(this.j, obj);
        if (collection == null) {
            return null;
        }
        return this.k.zzd(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map;
        Map map2 = this.j;
        v vVar = this.k;
        map = vVar.zza;
        if (map2 == map) {
            vVar.zzs();
        } else {
            zzda.a(new l(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return zzdi.b(this.j, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.j.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.j.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.k.zzw();
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj) {
        int i;
        Collection collection = (Collection) this.j.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection zza = this.k.zza();
        zza.addAll(collection);
        v vVar = this.k;
        i = vVar.zzb;
        vVar.zzb = i - collection.size();
        collection.clear();
        return zza;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.j.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.j.toString();
    }
}
