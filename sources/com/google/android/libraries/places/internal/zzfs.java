package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzfs {
    private final zzfd zza;
    private final boolean zzb;
    private final zzfw zzc;
    private final int zzd;

    private zzfs(zzfw zzfwVar) {
        this(zzfwVar, false, zzfh.zza, Integer.MAX_VALUE);
    }

    public static zzfs zza(zzfd zzfdVar) {
        zzft.zza(zzfdVar);
        return new zzfs(new zzfv(zzfdVar));
    }

    private zzfs(zzfw zzfwVar, boolean z, zzfd zzfdVar, int i) {
        this.zzc = zzfwVar;
        this.zzb = false;
        this.zza = zzfdVar;
        this.zzd = Integer.MAX_VALUE;
    }

    public final List<String> zza(CharSequence charSequence) {
        zzft.zza(charSequence);
        Iterator<String> zza = this.zzc.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza.hasNext()) {
            arrayList.add(zza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
