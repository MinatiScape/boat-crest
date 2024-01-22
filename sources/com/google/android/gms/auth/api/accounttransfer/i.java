package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzav;
/* loaded from: classes6.dex */
public final class i extends m {
    public final /* synthetic */ zzav f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(AccountTransferClient accountTransferClient, int i, zzav zzavVar) {
        super(1610);
        this.f = zzavVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.k
    public final void a(zzau zzauVar) throws RemoteException {
        zzauVar.zzf(this.e, this.f);
    }
}
