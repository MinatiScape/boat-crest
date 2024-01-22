package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.f;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcb implements zzca {
    @NotNull
    public static final zzcb zza = new zzcb();

    private zzcb() {
    }

    private static final List zzc(Object obj) {
        if (obj instanceof byte[]) {
            return ArraysKt___ArraysKt.toList((byte[]) obj);
        }
        if (obj instanceof short[]) {
            return ArraysKt___ArraysKt.toList((short[]) obj);
        }
        if (obj instanceof int[]) {
            return ArraysKt___ArraysKt.toList((int[]) obj);
        }
        if (obj instanceof long[]) {
            return ArraysKt___ArraysKt.toList((long[]) obj);
        }
        if (obj instanceof float[]) {
            return ArraysKt___ArraysKt.toList((float[]) obj);
        }
        if (obj instanceof double[]) {
            return ArraysKt___ArraysKt.toList((double[]) obj);
        }
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 2) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
                if (true != (zza3 instanceof Object)) {
                    zza3 = null;
                }
                if (zza3 != null) {
                    zzbhVar.zze().zzf(i, zzb(zza2, zza3));
                    return;
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }

    @NotNull
    public final Object zzb(@NotNull Object obj, @NotNull Object obj2) throws zzs {
        List<Number> zzc = zzc(obj);
        List<Number> zzc2 = zzc(obj2);
        if (obj instanceof Number) {
            if (obj2 instanceof Number) {
                return Double.valueOf(Math.pow(((Number) obj).doubleValue(), ((Number) obj2).doubleValue()));
            }
            if (zzc2 != null) {
                ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(zzc2, 10));
                for (Number number : zzc2) {
                    arrayList.add(Double.valueOf(Math.pow(number.doubleValue(), ((Number) obj).doubleValue())));
                }
                return (Serializable) arrayList.toArray(new Double[0]);
            }
        }
        if (zzc != null && (obj2 instanceof Number)) {
            ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(zzc, 10));
            for (Number number2 : zzc) {
                arrayList2.add(Double.valueOf(Math.pow(number2.doubleValue(), ((Number) obj2).doubleValue())));
            }
            return (Serializable) arrayList2.toArray(new Double[0]);
        } else if (zzc != null && zzc2 != null) {
            zzbz.zzb(this, zzc.size(), zzc2.size());
            int size = zzc.size();
            Double[] dArr = new Double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = Double.valueOf(Math.pow(((Number) zzc.get(i)).doubleValue(), ((Number) zzc2.get(i)).doubleValue()));
            }
            return (Serializable) dArr;
        } else {
            throw new zzs(4, 5, null);
        }
    }
}
