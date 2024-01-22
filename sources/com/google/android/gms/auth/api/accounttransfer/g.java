package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzaq;
import com.google.android.gms.internal.auth.zzau;
/* loaded from: classes6.dex */
public final class g extends k {
    public final /* synthetic */ zzaq e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(AccountTransferClient accountTransferClient, int i, zzaq zzaqVar) {
        super(1608, null);
        this.e = zzaqVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.k
    public final void a(zzau zzauVar) throws RemoteException {
        zzauVar.zzd(new f(this, this), this.e);
    }
}
