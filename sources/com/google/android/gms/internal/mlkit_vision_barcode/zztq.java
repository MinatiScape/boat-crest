package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class zztq implements zztn {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final List f9575a;

    public zztq(Context context, zztp zztpVar) {
        ArrayList arrayList = new ArrayList();
        this.f9575a = arrayList;
        if (zztpVar.zzc()) {
            arrayList.add(new zzuf(context, zztpVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztn
    public final void zza(zztm zztmVar) {
        for (zztn zztnVar : this.f9575a) {
            zztnVar.zza(zztmVar);
        }
    }
}
