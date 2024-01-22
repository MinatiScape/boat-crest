package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzbi {
    @NotNull
    private final Map zza;
    @NotNull
    private final Set zzb;
    @NotNull
    private final Map zzc;

    public zzbi() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zza = linkedHashMap;
        this.zzb = new LinkedHashSet();
        this.zzc = linkedHashMap;
    }

    private final List zzi(List list) {
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(zza((zznl) it.next()));
        }
        return arrayList;
    }

    @Nullable
    public final Object zza(@NotNull zznl zznlVar) throws zzs {
        int zzN = zznlVar.zzN();
        int i = zzN - 1;
        if (zzN != 0) {
            switch (i) {
                case 0:
                    return this.zza.get(Integer.valueOf(zznlVar.zzi()));
                case 1:
                    return Boolean.valueOf(zznlVar.zzL());
                case 2:
                    byte[] zzo = zznlVar.zzH().zzo();
                    if (zzo.length == 1) {
                        return Byte.valueOf(zzo[0]);
                    }
                    throw new zzs(4, 6, null);
                case 3:
                    String zzJ = zznlVar.zzJ();
                    if (zzJ.length() == 1) {
                        return Character.valueOf(zzJ.charAt(0));
                    }
                    throw new zzs(4, 6, null);
                case 4:
                    int zzj = zznlVar.zzj();
                    if (zzj >= -32768 && zzj <= 32767) {
                        return Short.valueOf((short) zzj);
                    }
                    throw new zzs(4, 6, null);
                case 5:
                    return Integer.valueOf(zznlVar.zzk());
                case 6:
                case 8:
                    throw new zzs(4, 6, null);
                case 7:
                    return Long.valueOf(zznlVar.zzG());
                case 9:
                    return Float.valueOf(zznlVar.zzg());
                case 10:
                    return Double.valueOf(zznlVar.zzf());
                case 11:
                    return zznlVar.zzK();
                case 12:
                    return null;
                default:
                    throw new zzs(4, 5, null);
            }
        }
        throw null;
    }

    @Nullable
    public final Object zzb(int i) {
        return this.zza.remove(Integer.valueOf(i));
    }

    @NotNull
    public final Map zzc() {
        return this.zzc;
    }

    public final void zzd() {
        this.zza.clear();
    }

    public final void zze(int i, @Nullable Object obj) {
        zzf(173, obj);
        this.zzb.add(173);
    }

    public final void zzf(int i, @Nullable Object obj) {
        this.zza.put(Integer.valueOf(i), obj);
    }

    @NotNull
    public final Class[] zzg(@NotNull List list) {
        List<Object> zzi = zzi(list);
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(zzi, 10));
        for (Object obj : zzi) {
            arrayList.add(zzbg.zza(obj));
        }
        return (Class[]) arrayList.toArray(new Class[0]);
    }

    @NotNull
    public final Object[] zzh(@NotNull List list) {
        return zzi(list).toArray(new Object[0]);
    }
}
