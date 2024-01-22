package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzax;
/* loaded from: classes6.dex */
public final class e extends k {
    public final /* synthetic */ zzax e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(AccountTransferClient accountTransferClient, int i, zzax zzaxVar) {
        super(1607, null);
        this.e = zzaxVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.k
    public final void a(zzau zzauVar) throws RemoteException {
        zzauVar.zzg(new d(this, this), this.e);
    }
}
