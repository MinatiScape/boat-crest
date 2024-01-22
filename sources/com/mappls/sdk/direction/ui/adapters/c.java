package com.mappls.sdk.direction.ui.adapters;

import android.view.View;
import com.mappls.sdk.direction.ui.adapters.d;
import java.util.List;
/* loaded from: classes11.dex */
public final class c implements View.OnClickListener {
    public final /* synthetic */ com.mappls.sdk.direction.ui.model.d h;
    public final /* synthetic */ d i;

    public c(d dVar, com.mappls.sdk.direction.ui.model.d dVar2) {
        this.i = dVar;
        this.h = dVar2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        List<com.mappls.sdk.direction.ui.model.d> list;
        d.b bVar;
        d.b bVar2;
        list = this.i.f12570a;
        for (com.mappls.sdk.direction.ui.model.d dVar : list) {
            if (dVar == this.h) {
                dVar.a(Boolean.TRUE);
                bVar = this.i.b;
                if (bVar != null) {
                    bVar2 = this.i.b;
                    bVar2.a(dVar.a());
                }
            } else {
                dVar.a(Boolean.FALSE);
            }
        }
        this.i.notifyDataSetChanged();
    }
}
