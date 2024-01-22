package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class m0 extends o0 {
    public static final Class c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public /* synthetic */ m0(zzex zzexVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.o0
    public final void a(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) g2.k(obj, j);
        if (list instanceof zzew) {
            unmodifiableList = ((zzew) list).zze();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof c1) && (list instanceof zzel)) {
                zzel zzelVar = (zzel) list;
                if (zzelVar.zzc()) {
                    zzelVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        g2.x(obj, j, unmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.o0
    public final void b(Object obj, Object obj2, long j) {
        zzev zzevVar;
        List list = (List) g2.k(obj2, j);
        int size = list.size();
        List list2 = (List) g2.k(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzew) {
                list2 = new zzev(size);
            } else if ((list2 instanceof c1) && (list2 instanceof zzel)) {
                list2 = ((zzel) list2).zzd(size);
            } else {
                list2 = new ArrayList(size);
            }
            g2.x(obj, j, list2);
        } else {
            if (c.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + size);
                arrayList.addAll(list2);
                g2.x(obj, j, arrayList);
                zzevVar = arrayList;
            } else if (list2 instanceof zzhd) {
                zzev zzevVar2 = new zzev(list2.size() + size);
                zzevVar2.addAll(zzevVar2.size(), (zzhd) list2);
                g2.x(obj, j, zzevVar2);
                zzevVar = zzevVar2;
            } else if ((list2 instanceof c1) && (list2 instanceof zzel)) {
                zzel zzelVar = (zzel) list2;
                if (!zzelVar.zzc()) {
                    list2 = zzelVar.zzd(list2.size() + size);
                    g2.x(obj, j, list2);
                }
            }
            list2 = zzevVar;
        }
        int size2 = list2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list);
        }
        if (size2 > 0) {
            list = list2;
        }
        g2.x(obj, j, list);
    }
}
