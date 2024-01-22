package com.google.android.gms.common.api.internal;

import android.app.Dialog;
/* loaded from: classes6.dex */
public final class v0 extends zabw {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Dialog f8290a;
    public final /* synthetic */ w0 b;

    public v0(w0 w0Var, Dialog dialog) {
        this.b = w0Var;
        this.f8290a = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zabw
    public final void zaa() {
        this.b.i.b();
        if (this.f8290a.isShowing()) {
            this.f8290a.dismiss();
        }
    }
}
