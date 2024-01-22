package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import androidx.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes6.dex */
public final class w0 implements Runnable {
    public final u0 h;
    public final /* synthetic */ zap i;

    public w0(zap zapVar, u0 u0Var) {
        this.i = zapVar;
        this.h = u0Var;
    }

    @Override // java.lang.Runnable
    @MainThread
    public final void run() {
        if (this.i.zaa) {
            ConnectionResult b = this.h.b();
            if (b.hasResolution()) {
                zap zapVar = this.i;
                zapVar.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(zapVar.getActivity(), (PendingIntent) Preconditions.checkNotNull(b.getResolution()), this.h.a(), false), 1);
                return;
            }
            zap zapVar2 = this.i;
            if (zapVar2.zac.getErrorResolutionIntent(zapVar2.getActivity(), b.getErrorCode(), null) != null) {
                zap zapVar3 = this.i;
                zapVar3.zac.zag(zapVar3.getActivity(), this.i.mLifecycleFragment, b.getErrorCode(), 2, this.i);
            } else if (b.getErrorCode() == 18) {
                zap zapVar4 = this.i;
                Dialog zab = zapVar4.zac.zab(zapVar4.getActivity(), this.i);
                zap zapVar5 = this.i;
                zapVar5.zac.zac(zapVar5.getActivity().getApplicationContext(), new v0(this, zab));
            } else {
                this.i.a(b, this.h.a());
            }
        }
    }
}
