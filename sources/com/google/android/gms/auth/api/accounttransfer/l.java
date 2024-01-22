package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.zzan;
/* loaded from: classes6.dex */
public final class l extends zzan {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ m f8193a;

    public l(m mVar) {
        this.f8193a = mVar;
    }

    @Override // com.google.android.gms.internal.auth.zzan, com.google.android.gms.internal.auth.zzat
    public final void zzd(Status status) {
        this.f8193a.d.setException(new AccountTransferException(status));
    }

    @Override // com.google.android.gms.internal.auth.zzan, com.google.android.gms.internal.auth.zzat
    public final void zze() {
        this.f8193a.d.setResult(null);
    }
}
