package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
final class zzhz extends zzid {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzhz() {
        super(null);
    }

    public /* synthetic */ zzhz(zzhy zzhyVar) {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static List zzf(Object obj, long j, int i) {
        zzhw zzhwVar;
        List arrayList;
        List list = (List) zzkg.zzf(obj, j);
        if (list.isEmpty()) {
            if (list instanceof zzhx) {
                arrayList = new zzhw(i);
            } else if ((list instanceof zzix) && (list instanceof zzhm)) {
                arrayList = ((zzhm) list).zzd(i);
            } else {
                arrayList = new ArrayList(i);
            }
            zzkg.zzs(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList2 = new ArrayList(list.size() + i);
            arrayList2.addAll(list);
            zzkg.zzs(obj, j, arrayList2);
            zzhwVar = arrayList2;
        } else if (!(list instanceof zzkb)) {
            if ((list instanceof zzix) && (list instanceof zzhm)) {
                zzhm zzhmVar = (zzhm) list;
                if (zzhmVar.zzc()) {
                    return list;
                }
                zzhm zzd = zzhmVar.zzd(list.size() + i);
                zzkg.zzs(obj, j, zzd);
                return zzd;
            }
            return list;
        } else {
            zzhw zzhwVar2 = new zzhw(list.size() + i);
            zzhwVar2.addAll(zzhwVar2.size(), (zzkb) list);
            zzkg.zzs(obj, j, zzhwVar2);
            zzhwVar = zzhwVar2;
        }
        return zzhwVar;
    }

    @Override // com.google.android.recaptcha.internal.zzid
    public final List zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    @Override // com.google.android.recaptcha.internal.zzid
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzkg.zzf(obj, j);
        if (list instanceof zzhx) {
            unmodifiableList = ((zzhx) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzix) && (list instanceof zzhm)) {
                zzhm zzhmVar = (zzhm) list;
                if (zzhmVar.zzc()) {
                    zzhmVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzkg.zzs(obj, j, unmodifiableList);
    }

    @Override // com.google.android.recaptcha.internal.zzid
    public final void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzkg.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzkg.zzs(obj, j, list);
    }
}
