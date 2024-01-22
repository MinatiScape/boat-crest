package com.google.android.libraries.places.internal;
/* loaded from: classes10.dex */
final class zztd extends zzsy {
    private zztd() {
        super();
    }

    private static <E> zzsp<E> zzb(Object obj, long j) {
        return (zzsp) zzvc.zzf(obj, j);
    }

    @Override // com.google.android.libraries.places.internal.zzsy
    public final void zza(Object obj, long j) {
        zzb(obj, j).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    @Override // com.google.android.libraries.places.internal.zzsy
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzsp<E> zzb = zzb(obj, j);
        zzsp<E> zzb2 = zzb(obj2, j);
        int size = zzb.size();
        int size2 = zzb2.size();
        zzsp<E> zzspVar = zzb;
        zzspVar = zzb;
        if (size > 0 && size2 > 0) {
            boolean zza = zzb.zza();
            zzsp<E> zzspVar2 = zzb;
            if (!zza) {
                zzspVar2 = zzb.zzb(size2 + size);
            }
            zzspVar2.addAll(zzb2);
            zzspVar = zzspVar2;
        }
        if (size > 0) {
            zzb2 = zzspVar;
        }
        zzvc.zza(obj, j, zzb2);
    }
}
