package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class d3 extends f3 {
    public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public /* synthetic */ d3(zzkp zzkpVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.f3
    public final void a(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) t4.k(obj, j);
        if (list instanceof zzko) {
            unmodifiableList = ((zzko) list).zze();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof t3) && (list instanceof zzkg)) {
                zzkg zzkgVar = (zzkg) list;
                if (zzkgVar.zzc()) {
                    zzkgVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        t4.x(obj, j, unmodifiableList);
    }

    @Override // com.google.android.gms.internal.measurement.f3
    public final <E> void b(Object obj, Object obj2, long j) {
        ArrayList arrayList;
        List list = (List) t4.k(obj2, j);
        int size = list.size();
        List list2 = (List) t4.k(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzko) {
                list2 = new zzkn(size);
            } else if ((list2 instanceof t3) && (list2 instanceof zzkg)) {
                list2 = ((zzkg) list2).zzd(size);
            } else {
                list2 = new ArrayList(size);
            }
            t4.x(obj, j, list2);
        } else {
            if (c.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList2 = new ArrayList(list2.size() + size);
                arrayList2.addAll(list2);
                t4.x(obj, j, arrayList2);
                arrayList = arrayList2;
            } else if (list2 instanceof zzmn) {
                zzkn zzknVar = new zzkn(list2.size() + size);
                zzknVar.addAll(zzknVar.size(), (zzmn) list2);
                t4.x(obj, j, zzknVar);
                arrayList = zzknVar;
            } else if ((list2 instanceof t3) && (list2 instanceof zzkg)) {
                zzkg zzkgVar = (zzkg) list2;
                if (!zzkgVar.zzc()) {
                    list2 = zzkgVar.zzd(list2.size() + size);
                    t4.x(obj, j, list2);
                }
            }
            list2 = arrayList;
        }
        int size2 = list2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list);
        }
        if (size2 > 0) {
            list = list2;
        }
        t4.x(obj, j, list);
    }
}
