package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzmf implements zzmc {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final List f9791a;

    public zzmf(Context context, zzme zzmeVar) {
        ArrayList arrayList = new ArrayList();
        this.f9791a = arrayList;
        if (zzmeVar.zzc()) {
            arrayList.add(new zzmp(context, zzmeVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmc
    public final void zza(zzmb zzmbVar) {
        for (zzmc zzmcVar : this.f9791a) {
            zzmcVar.zza(zzmbVar);
        }
    }
}
