package com.google.android.recaptcha.internal;

import java.util.Comparator;
/* loaded from: classes10.dex */
final class zzfa implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzfi zzfiVar = (zzfi) obj;
        zzfi zzfiVar2 = (zzfi) obj2;
        zzez zzezVar = new zzez(zzfiVar);
        zzez zzezVar2 = new zzez(zzfiVar2);
        while (zzezVar.hasNext() && zzezVar2.hasNext()) {
            int compareTo = Integer.valueOf(zzezVar.zza() & 255).compareTo(Integer.valueOf(zzezVar2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzfiVar.zzd()).compareTo(Integer.valueOf(zzfiVar2.zzd()));
    }
}
