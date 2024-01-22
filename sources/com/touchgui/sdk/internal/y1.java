package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGWorkoutSupportList;
import java.util.List;
/* loaded from: classes12.dex */
public final class y1 extends v8 {
    public final /* synthetic */ d2 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y1(d2 d2Var, a0 a0Var, t7 t7Var) {
        super(a0Var, t7Var);
        this.h = d2Var;
    }

    @Override // com.touchgui.sdk.internal.d
    public final boolean a() {
        return this.h.f13753a.c > 777;
    }

    @Override // com.touchgui.sdk.internal.d
    public final void b(Object obj) {
        List<Integer> items;
        int indexOf;
        TGWorkoutSupportList tGWorkoutSupportList = (TGWorkoutSupportList) obj;
        if (this.h.f13753a.d() == 534 && (indexOf = (items = tGWorkoutSupportList.getItems()).indexOf(47)) != -1) {
            items.remove(indexOf);
            items.add(150);
        }
        super.a(tGWorkoutSupportList);
    }
}
