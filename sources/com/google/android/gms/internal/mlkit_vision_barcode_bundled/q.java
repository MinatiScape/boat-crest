package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Comparator;
/* loaded from: classes8.dex */
public final class q implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzdb zzdbVar = (zzdb) obj;
        zzdb zzdbVar2 = (zzdb) obj2;
        zzcw it = zzdbVar.iterator();
        zzcw it2 = zzdbVar2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compareTo = Integer.valueOf(it.zza() & 255).compareTo(Integer.valueOf(it2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzdbVar.zzd()).compareTo(Integer.valueOf(zzdbVar2.zzd()));
    }
}
