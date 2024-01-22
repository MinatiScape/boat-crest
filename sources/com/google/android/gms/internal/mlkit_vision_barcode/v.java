package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public abstract class v extends z implements Serializable {
    private final transient Map zza;
    private transient int zzb;

    public v(Map map) {
        zzbc.zzc(map.isEmpty());
        this.zza = map;
    }

    public static /* bridge */ /* synthetic */ int zzg(v vVar) {
        return vVar.zzb;
    }

    public static /* bridge */ /* synthetic */ Map zzn(v vVar) {
        return vVar.zza;
    }

    public static /* bridge */ /* synthetic */ void zzq(v vVar, int i) {
        vVar.zzb = i;
    }

    public static /* bridge */ /* synthetic */ void zzr(v vVar, Object obj) {
        Object obj2;
        try {
            obj2 = vVar.zza.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            vVar.zzb -= size;
        }
    }

    public abstract Collection zza();

    public Collection zzb() {
        throw null;
    }

    public Collection zzc(Collection collection) {
        throw null;
    }

    public Collection zzd(Object obj, Collection collection) {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzdj
    public final int zzh() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z
    public final Collection zzi() {
        return this instanceof zzdt ? new y(this) : new x(this);
    }

    public final Collection zzj(Object obj) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            collection = zza();
        }
        return zzd(obj, collection);
    }

    public final Collection zzk(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zza.remove(obj);
        if (collection == null) {
            return zzb();
        }
        Collection zza = zza();
        zza.addAll(collection);
        this.zzb -= collection.size();
        collection.clear();
        return zzc(zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z
    public final Iterator zzl() {
        return new j(this);
    }

    public final List zzm(Object obj, List list, @CheckForNull s sVar) {
        if (list instanceof RandomAccess) {
            return new q(this, obj, list, sVar);
        }
        return new u(this, obj, list, sVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z
    public final Map zzo() {
        return new m(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z
    public final Set zzp() {
        return new p(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzdj
    public final void zzs() {
        for (Collection collection : this.zza.values()) {
            collection.clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.z, com.google.android.gms.internal.mlkit_vision_barcode.zzdj
    public final boolean zzt(Object obj, Object obj2) {
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
