package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes7.dex */
public final class u0 extends s0 {
    public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public u0() {
        super();
    }

    public static <E> List<E> e(Object obj, long j) {
        return (List) n2.M(obj, j);
    }

    @Override // com.google.android.gms.internal.clearcut.s0
    public final void a(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) n2.M(obj, j);
        if (list instanceof zzcx) {
            unmodifiableList = ((zzcx) list).zzbu();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            unmodifiableList = Collections.unmodifiableList(list);
        }
        n2.i(obj, j, unmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.clearcut.s0
    public final <E> void b(Object obj, Object obj2, long j) {
        zzcw zzcwVar;
        List e = e(obj2, j);
        int size = e.size();
        List e2 = e(obj, j);
        if (e2.isEmpty()) {
            e2 = e2 instanceof zzcx ? new zzcw(size) : new ArrayList(size);
            n2.i(obj, j, e2);
        } else {
            if (c.isAssignableFrom(e2.getClass())) {
                ArrayList arrayList = new ArrayList(e2.size() + size);
                arrayList.addAll(e2);
                zzcwVar = arrayList;
            } else if (e2 instanceof zzfa) {
                zzcw zzcwVar2 = new zzcw(e2.size() + size);
                zzcwVar2.addAll((zzfa) e2);
                zzcwVar = zzcwVar2;
            }
            n2.i(obj, j, zzcwVar);
            e2 = zzcwVar;
        }
        int size2 = e2.size();
        int size3 = e.size();
        if (size2 > 0 && size3 > 0) {
            e2.addAll(e);
        }
        if (size2 > 0) {
            e = e2;
        }
        n2.i(obj, j, e);
    }
}
