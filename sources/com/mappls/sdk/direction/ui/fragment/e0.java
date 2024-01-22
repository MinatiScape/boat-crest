package com.mappls.sdk.direction.ui.fragment;

import android.view.View;
/* loaded from: classes11.dex */
public final class e0 implements View.OnClickListener {
    public final /* synthetic */ n h;

    public e0(n nVar) {
        this.h = nVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.h.getParentFragmentManager().popBackStack(this.h.getClass().getName(), 1);
    }
}
