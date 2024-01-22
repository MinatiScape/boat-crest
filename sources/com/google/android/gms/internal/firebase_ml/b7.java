package com.google.android.gms.internal.firebase_ml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes7.dex */
public final class b7 extends a7 {
    public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public b7() {
        super();
    }

    public static <E> List<E> e(Object obj, long j) {
        return (List) b.z(obj, j);
    }

    @Override // com.google.android.gms.internal.firebase_ml.a7
    public final <E> void a(Object obj, Object obj2, long j) {
        ArrayList arrayList;
        List e = e(obj2, j);
        int size = e.size();
        List e2 = e(obj, j);
        if (e2.isEmpty()) {
            if (e2 instanceof zzxv) {
                e2 = new zzxs(size);
            } else if ((e2 instanceof v7) && (e2 instanceof zzxl)) {
                e2 = ((zzxl) e2).zzcv(size);
            } else {
                e2 = new ArrayList(size);
            }
            b.g(obj, j, e2);
        } else {
            if (c.isAssignableFrom(e2.getClass())) {
                ArrayList arrayList2 = new ArrayList(e2.size() + size);
                arrayList2.addAll(e2);
                b.g(obj, j, arrayList2);
                arrayList = arrayList2;
            } else if (e2 instanceof zzaab) {
                List zzxsVar = new zzxs(e2.size() + size);
                zzxsVar.addAll((zzaab) e2);
                b.g(obj, j, zzxsVar);
                arrayList = zzxsVar;
            } else if ((e2 instanceof v7) && (e2 instanceof zzxl)) {
                zzxl zzxlVar = (zzxl) e2;
                if (!zzxlVar.zztl()) {
                    e2 = zzxlVar.zzcv(e2.size() + size);
                    b.g(obj, j, e2);
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
        b.g(obj, j, e);
    }

    @Override // com.google.android.gms.internal.firebase_ml.a7
    public final void b(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) b.z(obj, j);
        if (list instanceof zzxv) {
            unmodifiableList = ((zzxv) list).zzvo();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof v7) && (list instanceof zzxl)) {
                zzxl zzxlVar = (zzxl) list;
                if (zzxlVar.zztl()) {
                    zzxlVar.zztm();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        b.g(obj, j, unmodifiableList);
    }
}
