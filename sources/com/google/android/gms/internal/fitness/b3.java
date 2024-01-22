package com.google.android.gms.internal.fitness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class b3 extends z2 {
    public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public b3() {
        super();
    }

    public static <E> List<E> e(Object obj, long j) {
        return (List) s4.G(obj, j);
    }

    @Override // com.google.android.gms.internal.fitness.z2
    public final void a(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) s4.G(obj, j);
        if (list instanceof zzhr) {
            unmodifiableList = ((zzhr) list).zzci();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof s3) && (list instanceof zzhh)) {
                zzhh zzhhVar = (zzhh) list;
                if (zzhhVar.zzaq()) {
                    zzhhVar.zzar();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        s4.g(obj, j, unmodifiableList);
    }

    @Override // com.google.android.gms.internal.fitness.z2
    public final <E> void b(Object obj, Object obj2, long j) {
        ArrayList arrayList;
        List e = e(obj2, j);
        int size = e.size();
        List e2 = e(obj, j);
        if (e2.isEmpty()) {
            if (e2 instanceof zzhr) {
                e2 = new zzhs(size);
            } else if ((e2 instanceof s3) && (e2 instanceof zzhh)) {
                e2 = ((zzhh) e2).zzae(size);
            } else {
                e2 = new ArrayList(size);
            }
            s4.g(obj, j, e2);
        } else {
            if (c.isAssignableFrom(e2.getClass())) {
                ArrayList arrayList2 = new ArrayList(e2.size() + size);
                arrayList2.addAll(e2);
                s4.g(obj, j, arrayList2);
                arrayList = arrayList2;
            } else if (e2 instanceof zzjt) {
                List zzhsVar = new zzhs(e2.size() + size);
                zzhsVar.addAll((zzjt) e2);
                s4.g(obj, j, zzhsVar);
                arrayList = zzhsVar;
            } else if ((e2 instanceof s3) && (e2 instanceof zzhh)) {
                zzhh zzhhVar = (zzhh) e2;
                if (!zzhhVar.zzaq()) {
                    e2 = zzhhVar.zzae(e2.size() + size);
                    s4.g(obj, j, e2);
                }
            }
            e2 = arrayList;
        }
        int size2 = e2.size();
        int size3 = e.size();
        if (size2 > 0 && size3 > 0) {
            e2.addAll(e);
        }
        if (size2 > 0) {
            e = e2;
        }
        s4.g(obj, j, e);
    }
}
