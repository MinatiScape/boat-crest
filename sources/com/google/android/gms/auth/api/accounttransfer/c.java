package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzaz;
/* loaded from: classes6.dex */
public final class c extends m {
    public final /* synthetic */ zzaz f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(AccountTransferClient accountTransferClient, int i, zzaz zzazVar) {
        super(1606);
        this.f = zzazVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.k
    public final void a(zzau zzauVar) throws RemoteException {
        zzauVar.zzh(this.e, this.f);
    }
}
