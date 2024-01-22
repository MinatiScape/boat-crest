package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public final class t2 extends r2 {
    public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public t2() {
        super();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <L> List<L> f(Object obj, long j, int i) {
        zzhk zzhkVar;
        List<L> arrayList;
        List<L> g = g(obj, j);
        if (g.isEmpty()) {
            if (g instanceof zzhj) {
                arrayList = new zzhk(i);
            } else if ((g instanceof l3) && (g instanceof zzgz)) {
                arrayList = ((zzgz) g).zzah(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            j4.f(obj, j, arrayList);
            return arrayList;
        }
        if (c.isAssignableFrom(g.getClass())) {
            ArrayList arrayList2 = new ArrayList(g.size() + i);
            arrayList2.addAll(g);
            j4.f(obj, j, arrayList2);
            zzhkVar = arrayList2;
        } else if (g instanceof zzjo) {
            zzhk zzhkVar2 = new zzhk(g.size() + i);
            zzhkVar2.addAll((zzjo) g);
            j4.f(obj, j, zzhkVar2);
            zzhkVar = zzhkVar2;
        } else if ((g instanceof l3) && (g instanceof zzgz)) {
            zzgz zzgzVar = (zzgz) g;
            if (zzgzVar.zzdo()) {
                return g;
            }
            zzgz zzah = zzgzVar.zzah(g.size() + i);
            j4.f(obj, j, zzah);
            return zzah;
        } else {
            return g;
        }
        return zzhkVar;
    }

    public static <E> List<E> g(Object obj, long j) {
        return (List) j4.G(obj, j);
    }

    @Override // com.google.android.gms.internal.vision.r2
    public final <L> List<L> a(Object obj, long j) {
        return f(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.vision.r2
    public final <E> void b(Object obj, Object obj2, long j) {
        List g = g(obj2, j);
        List f = f(obj, j, g.size());
        int size = f.size();
        int size2 = g.size();
        if (size > 0 && size2 > 0) {
            f.addAll(g);
        }
        if (size > 0) {
            g = f;
        }
        j4.f(obj, j, g);
    }

    @Override // com.google.android.gms.internal.vision.r2
    public final void c(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) j4.G(obj, j);
        if (list instanceof zzhj) {
            unmodifiableList = ((zzhj) list).zzgy();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof l3) && (list instanceof zzgz)) {
                zzgz zzgzVar = (zzgz) list;
                if (zzgzVar.zzdo()) {
                    zzgzVar.zzdp();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        j4.f(obj, j, unmodifiableList);
    }
}
