package com.google.android.gms.internal.mlkit_code_scanner;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class zznu implements zznr {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final List f9147a;

    public zznu(Context context, zznt zzntVar) {
        ArrayList arrayList = new ArrayList();
        this.f9147a = arrayList;
        if (zzntVar.zzc()) {
            arrayList.add(new zzog(context, zzntVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznr
    public final void zza(zznq zznqVar) {
        for (zznr zznrVar : this.f9147a) {
            zznrVar.zza(zznqVar);
        }
    }
}
