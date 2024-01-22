package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public final class f1 extends h1 {
    public static final Class c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public /* synthetic */ f1(zzff zzffVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.auth.h1
    public final void a(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) u2.f(obj, j);
        if (list instanceof zzfe) {
            unmodifiableList = ((zzfe) list).zze();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof v1) && (list instanceof zzey)) {
                zzey zzeyVar = (zzey) list;
                if (zzeyVar.zzc()) {
                    zzeyVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        u2.p(obj, j, unmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.auth.h1
    public final void b(Object obj, Object obj2, long j) {
        zzfd zzfdVar;
        List list = (List) u2.f(obj2, j);
        int size = list.size();
        List list2 = (List) u2.f(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzfe) {
                list2 = new zzfd(size);
            } else if ((list2 instanceof v1) && (list2 instanceof zzey)) {
                list2 = ((zzey) list2).zzd(size);
            } else {
                list2 = new ArrayList(size);
            }
            u2.p(obj, j, list2);
        } else {
            if (c.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + size);
                arrayList.addAll(list2);
                u2.p(obj, j, arrayList);
                zzfdVar = arrayList;
            } else if (list2 instanceof zzhd) {
                zzfd zzfdVar2 = new zzfd(list2.size() + size);
                zzfdVar2.addAll(zzfdVar2.size(), (zzhd) list2);
                u2.p(obj, j, zzfdVar2);
                zzfdVar = zzfdVar2;
            } else if ((list2 instanceof v1) && (list2 instanceof zzey)) {
                zzey zzeyVar = (zzey) list2;
                if (!zzeyVar.zzc()) {
                    list2 = zzeyVar.zzd(list2.size() + size);
                    u2.p(obj, j, list2);
                }
            }
            list2 = zzfdVar;
        }
        int size2 = list2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list);
        }
        if (size2 > 0) {
            list = list2;
        }
        u2.p(obj, j, list);
    }
}
