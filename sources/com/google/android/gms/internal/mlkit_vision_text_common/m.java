package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public abstract class m extends o implements Serializable {
    private transient Map zza;
    private transient int zzb;

    public m(Map map) {
        if (map.isEmpty()) {
            this.zza = map;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static /* synthetic */ int zzd(m mVar) {
        int i = mVar.zzb;
        mVar.zzb = i + 1;
        return i;
    }

    public static /* synthetic */ int zze(m mVar) {
        int i = mVar.zzb;
        mVar.zzb = i - 1;
        return i;
    }

    public static /* synthetic */ int zzf(m mVar, int i) {
        int i2 = mVar.zzb + i;
        mVar.zzb = i2;
        return i2;
    }

    public static /* synthetic */ int zzg(m mVar, int i) {
        int i2 = mVar.zzb - i;
        mVar.zzb = i2;
        return i2;
    }

    public static /* synthetic */ Map zzj(m mVar) {
        return mVar.zza;
    }

    public static /* synthetic */ void zzm(m mVar, Object obj) {
        Object obj2;
        Map map = mVar.zza;
        Objects.requireNonNull(map);
        try {
            obj2 = map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            mVar.zzb -= size;
        }
    }

    public abstract Collection zza();

    public Collection zzb(Object obj, Collection collection) {
        throw null;
    }

    public final Collection zzh(Object obj) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            collection = zza();
        }
        return zzb(obj, collection);
    }

    public final List zzi(Object obj, List list, @CheckForNull j jVar) {
        if (list instanceof RandomAccess) {
            return new h(this, obj, list, jVar);
        }
        return new l(this, obj, list, jVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.o
    public final Map zzk() {
        return new e(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.o
    public final Set zzl() {
        return new g(this, this.zza);
    }

    public final void zzn() {
        for (Collection collection : this.zza.values()) {
            collection.clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.o, com.google.android.gms.internal.mlkit_vision_text_common.zzce
    public final boolean zzo(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            Collection zza = zza();
            if (zza.add(obj2)) {
                this.zzb++;
                this.zza.put(obj, zza);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (collection.add(obj2)) {
            this.zzb++;
            return true;
        } else {
            return false;
        }
    }
}
