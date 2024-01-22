package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzpu implements zzpr {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final List f9355a;

    public zzpu(Context context, zzpt zzptVar) {
        ArrayList arrayList = new ArrayList();
        this.f9355a = arrayList;
        if (zzptVar.zzc()) {
            arrayList.add(new zzqh(context, zzptVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpr
    public final void zza(zzpq zzpqVar) {
        for (zzpr zzprVar : this.f9355a) {
            zzprVar.zza(zzpqVar);
        }
    }
}
