package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class e extends o0 {
    public final transient Map j;
    public final /* synthetic */ m k;

    public e(m mVar, Map map) {
        this.k = mVar;
        this.j = map;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.o0
    public final Set a() {
        return new c(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map;
        Map map2 = this.j;
        m mVar = this.k;
        map = mVar.zza;
        if (map2 == map) {
            mVar.zzn();
        } else {
            zzbq.a(new d(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return zzcd.b(this.j, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.j.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzcd.a(this.j, obj);
        if (collection == null) {
            return null;
        }
        return this.k.zzb(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.j.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.k.zzq();
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.j.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection zza = this.k.zza();
        zza.addAll(collection);
        m.zzg(this.k, collection.size());
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
