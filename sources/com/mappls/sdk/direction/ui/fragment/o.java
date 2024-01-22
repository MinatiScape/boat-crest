package com.mappls.sdk.direction.ui.fragment;

import android.view.View;
/* loaded from: classes11.dex */
public final class o implements View.OnClickListener {
    public final /* synthetic */ b h;

    public o(b bVar) {
        this.h = bVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.h.getParentFragmentManager().popBackStack(this.h.getClass().getName(), 1);
    }
}
