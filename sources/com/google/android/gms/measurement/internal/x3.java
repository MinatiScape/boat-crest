package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
/* loaded from: classes10.dex */
public final class x3 implements a4 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzkn f10135a;

    public x3(zzkn zzknVar) {
        this.f10135a = zzknVar;
    }

    @Override // com.google.android.gms.measurement.internal.a4
    public final void a(String str, String str2, Bundle bundle) {
        zzfs zzfsVar;
        zzfs zzfsVar2;
        if (!TextUtils.isEmpty(str)) {
            this.f10135a.zzaz().zzp(new w3(this, str, "_err", bundle));
            return;
        }
        zzfsVar = this.f10135a.l;
        if (zzfsVar != null) {
            zzfsVar2 = this.f10135a.l;
            zzfsVar2.zzay().zzd().zzb("AppId not known when logging event", "_err");
        }
    }
}
