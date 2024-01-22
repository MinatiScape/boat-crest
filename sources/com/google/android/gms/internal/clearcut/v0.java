package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public final class v0 extends s0 {
    public v0() {
        super();
    }

    public static <E> zzcn<E> e(Object obj, long j) {
        return (zzcn) n2.M(obj, j);
    }

    @Override // com.google.android.gms.internal.clearcut.s0
    public final void a(Object obj, long j) {
        e(obj, j).zzv();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    @Override // com.google.android.gms.internal.clearcut.s0
    public final <E> void b(Object obj, Object obj2, long j) {
        zzcn<E> e = e(obj, j);
        zzcn<E> e2 = e(obj2, j);
        int size = e.size();
        int size2 = e2.size();
        zzcn<E> zzcnVar = e;
        zzcnVar = e;
        if (size > 0 && size2 > 0) {
            boolean zzu = e.zzu();
            zzcn<E> zzcnVar2 = e;
            if (!zzu) {
                zzcnVar2 = e.zzi(size2 + size);
            }
            zzcnVar2.addAll(e2);
            zzcnVar = zzcnVar2;
        }
        if (size > 0) {
            e2 = zzcnVar;
        }
        n2.i(obj, j, e2);
    }
}
