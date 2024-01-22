package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
final class zzta extends zzsy {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzta() {
        super();
    }

    private static <E> List<E> zzb(Object obj, long j) {
        return (List) zzvc.zzf(obj, j);
    }

    @Override // com.google.android.libraries.places.internal.zzsy
    public final void zza(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzvc.zzf(obj, j);
        if (list instanceof zzsz) {
            unmodifiableList = ((zzsz) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzub) && (list instanceof zzsp)) {
                zzsp zzspVar = (zzsp) list;
                if (zzspVar.zza()) {
                    zzspVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzvc.zza(obj, j, unmodifiableList);
    }

    @Override // com.google.android.libraries.places.internal.zzsy
    public final <E> void zza(Object obj, Object obj2, long j) {
        ArrayList arrayList;
        List zzb = zzb(obj2, j);
        int size = zzb.size();
        List zzb2 = zzb(obj, j);
        if (zzb2.isEmpty()) {
            if (zzb2 instanceof zzsz) {
                zzb2 = new zzsw(size);
            } else if ((zzb2 instanceof zzub) && (zzb2 instanceof zzsp)) {
                zzb2 = ((zzsp) zzb2).zzb(size);
            } else {
                zzb2 = new ArrayList(size);
            }
            zzvc.zza(obj, j, zzb2);
        } else {
            if (zza.isAssignableFrom(zzb2.getClass())) {
                ArrayList arrayList2 = new ArrayList(zzb2.size() + size);
                arrayList2.addAll(zzb2);
                zzvc.zza(obj, j, arrayList2);
                arrayList = arrayList2;
            } else if (zzb2 instanceof zzvb) {
                List zzswVar = new zzsw(zzb2.size() + size);
                zzswVar.addAll((zzvb) zzb2);
                zzvc.zza(obj, j, zzswVar);
                arrayList = zzswVar;
            } else if ((zzb2 instanceof zzub) && (zzb2 instanceof zzsp)) {
                zzsp zzspVar = (zzsp) zzb2;
                if (!zzspVar.zza()) {
                    zzb2 = zzspVar.zzb(zzb2.size() + size);
                    zzvc.zza(obj, j, zzb2);
                }
            }
            zzb2 = arrayList;
        }
        int size2 = zzb2.size();
        int size3 = zzb.size();
        if (size2 > 0 && size3 > 0) {
            zzb2.addAll(zzb);
        }
        if (size2 > 0) {
            zzb = zzb2;
        }
        zzvc.zza(obj, j, zzb);
    }
}
