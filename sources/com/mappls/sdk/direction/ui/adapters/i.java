package com.mappls.sdk.direction.ui.adapters;

import android.view.View;
import com.mappls.sdk.direction.ui.adapters.j;
/* loaded from: classes11.dex */
public final class i implements View.OnClickListener {
    public final /* synthetic */ int h;
    public final /* synthetic */ j i;

    public i(j jVar, int i) {
        this.i = jVar;
        this.h = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        j.a aVar;
        aVar = this.i.i;
        aVar.b(this.h);
    }
}
