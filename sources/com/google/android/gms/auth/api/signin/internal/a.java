package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class a extends zba {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f8210a;

    public a(b bVar) {
        this.f8210a = bVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zba, com.google.android.gms.auth.api.signin.internal.zbr
    public final void zbd(GoogleSignInAccount googleSignInAccount, Status status) throws RemoteException {
        if (googleSignInAccount != null) {
            zbn.zbc(this.f8210a.c).zbe(this.f8210a.d, googleSignInAccount);
        }
        this.f8210a.setResult((b) new GoogleSignInResult(googleSignInAccount, status));
    }
}
