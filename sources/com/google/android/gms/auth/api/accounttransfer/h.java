package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzbb;
/* loaded from: classes6.dex */
public final class h extends m {
    public final /* synthetic */ zzbb f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(AccountTransferClient accountTransferClient, int i, zzbb zzbbVar) {
        super(1609);
        this.f = zzbbVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.k
    public final void a(zzau zzauVar) throws RemoteException {
        zzauVar.zze(this.e, this.f);
    }
}
