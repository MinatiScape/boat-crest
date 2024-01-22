package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.f;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbq implements zzca {
    @NotNull
    public static final zzbq zza = new zzbq();

    private zzbq() {
    }

    private static final boolean zzb(List list) {
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((zznl) it.next()).zzM()));
        }
        return !arrayList.contains(Boolean.FALSE);
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zzb(ArraysKt___ArraysKt.toList(zznlVarArr))) {
            for (zznl zznlVar : zznlVarArr) {
                zzbhVar.zze().zzb(zznlVar.zzi());
            }
            return;
        }
        throw new zzs(4, 5, null);
    }
}
