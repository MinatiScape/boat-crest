package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class e7 extends a7 {
    public e7() {
        super();
    }

    public static <E> zzxl<E> e(Object obj, long j) {
        return (zzxl) b.z(obj, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    @Override // com.google.android.gms.internal.firebase_ml.a7
    public final <E> void a(Object obj, Object obj2, long j) {
        zzxl<E> e = e(obj, j);
        zzxl<E> e2 = e(obj2, j);
        int size = e.size();
        int size2 = e2.size();
        zzxl<E> zzxlVar = e;
        zzxlVar = e;
        if (size > 0 && size2 > 0) {
            boolean zztl = e.zztl();
            zzxl<E> zzxlVar2 = e;
            if (!zztl) {
                zzxlVar2 = e.zzcv(size2 + size);
            }
            zzxlVar2.addAll(e2);
            zzxlVar = zzxlVar2;
        }
        if (size > 0) {
            e2 = zzxlVar;
        }
        b.g(obj, j, e2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.a7
    public final void b(Object obj, long j) {
        e(obj, j).zztm();
    }
}
