package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class zzny implements zzof {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final List f9950a;

    public zzny(Context context, zznx zznxVar) {
        ArrayList arrayList = new ArrayList();
        this.f9950a = arrayList;
        if (zznxVar.zzc()) {
            arrayList.add(new zzoo(context, zznxVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzof
    public final void zza(zznv zznvVar) {
        for (zzof zzofVar : this.f9950a) {
            zzofVar.zza(zznvVar);
        }
    }
}
