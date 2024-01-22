package com.mappls.sdk.direction.ui.fragment;

import android.view.View;
/* loaded from: classes11.dex */
public final class r implements View.OnClickListener {
    public final /* synthetic */ i h;

    public r(i iVar) {
        this.h = iVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.h.getParentFragmentManager().popBackStack(this.h.getClass().getName(), 1);
    }
}
