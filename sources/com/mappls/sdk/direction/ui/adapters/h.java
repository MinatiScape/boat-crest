package com.mappls.sdk.direction.ui.adapters;

import android.view.View;
import com.mappls.sdk.direction.ui.adapters.j;
import com.mappls.sdk.direction.ui.model.DirectionOptions;
import com.mappls.sdk.direction.ui.model.StopModel;
import java.util.List;
/* loaded from: classes11.dex */
public final class h implements View.OnClickListener {
    public final /* synthetic */ int h;
    public final /* synthetic */ j i;

    public h(j jVar, int i) {
        this.i = jVar;
        this.h = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        List list;
        DirectionOptions directionOptions;
        List list2;
        List list3;
        List list4;
        j.a aVar;
        List list5;
        j.a aVar2;
        j.a aVar3;
        List list6;
        List list7;
        j.a aVar4;
        j.a aVar5;
        if (this.i.getItemViewType(this.h) == 0) {
            list7 = this.i.h;
            if (list7.size() < 3) {
                aVar4 = this.i.i;
                if (aVar4 != null) {
                    aVar5 = this.i.i;
                    aVar5.a();
                }
            }
        }
        list = this.i.h;
        if (list.size() > 2) {
            list5 = this.i.h;
            if (((StopModel) list5.get(this.h)).getLocationType() != StopModel.TYPE_BLANK) {
                aVar2 = this.i.i;
                if (aVar2 != null) {
                    aVar3 = this.i.i;
                    list6 = this.i.h;
                    aVar3.a((StopModel) list6.get(this.h));
                }
            }
        }
        directionOptions = this.i.j;
        if (directionOptions.showAddWaypointOption().booleanValue() && this.i.getItemViewType(this.h) == 1) {
            list2 = this.i.h;
            if (list2.size() == 2) {
                list3 = this.i.h;
                if (((StopModel) list3.get(this.h)).getLocationType() != StopModel.TYPE_BLANK) {
                    list4 = this.i.h;
                    if (((StopModel) list4.get(0)).getLocationType() != StopModel.TYPE_BLANK) {
                        aVar = this.i.i;
                        aVar.a(this.h);
                    }
                }
            }
        }
    }
}
