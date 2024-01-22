package com.google.android.gms.internal.gtm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzvu extends zzvy {
    public static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public /* synthetic */ zzvu(zzvt zzvtVar) {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <L> List<L> zzf(Object obj, long j, int i) {
        zzvr zzvrVar;
        List<L> arrayList;
        List<L> list = (List) zzxy.zzf(obj, j);
        if (list.isEmpty()) {
            if (list instanceof zzvs) {
                arrayList = new zzvr(i);
            } else if ((list instanceof zzws) && (list instanceof zzvh)) {
                arrayList = ((zzvh) list).zzd(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzxy.zzs(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList2 = new ArrayList(list.size() + i);
            arrayList2.addAll(list);
            zzxy.zzs(obj, j, arrayList2);
            zzvrVar = arrayList2;
        } else if (list instanceof zzxt) {
            zzvr zzvrVar2 = new zzvr(list.size() + i);
            zzvrVar2.addAll(zzvrVar2.size(), (zzxt) list);
            zzxy.zzs(obj, j, zzvrVar2);
            zzvrVar = zzvrVar2;
        } else if ((list instanceof zzws) && (list instanceof zzvh)) {
            zzvh zzvhVar = (zzvh) list;
            if (zzvhVar.zzc()) {
                return list;
            }
            zzvh zzd = zzvhVar.zzd(list.size() + i);
            zzxy.zzs(obj, j, zzd);
            return zzd;
        } else {
            return list;
        }
        return zzvrVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzvy
    public final <L> List<L> zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.gtm.zzvy
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzxy.zzf(obj, j);
        if (list instanceof zzvs) {
            unmodifiableList = ((zzvs) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzws) && (list instanceof zzvh)) {
                zzvh zzvhVar = (zzvh) list;
                if (zzvhVar.zzc()) {
                    zzvhVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzxy.zzs(obj, j, unmodifiableList);
    }

    @Override // com.google.android.gms.internal.gtm.zzvy
    public final <E> void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzxy.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzxy.zzs(obj, j, list);
    }
}
