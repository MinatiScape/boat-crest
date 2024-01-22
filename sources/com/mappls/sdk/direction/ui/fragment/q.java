package com.mappls.sdk.direction.ui.fragment;

import android.view.View;
/* loaded from: classes11.dex */
public final class q implements View.OnClickListener {
    public final /* synthetic */ d h;

    public q(d dVar) {
        this.h = dVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.h.getParentFragmentManager().popBackStack(this.h.getClass().getName(), 1);
    }
}
