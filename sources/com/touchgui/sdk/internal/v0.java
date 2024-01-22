package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFunctions;
import com.touchgui.sdk.bean.TGWorkoutMode;
import java.util.List;
/* loaded from: classes12.dex */
public final class v0 extends g5 {
    public final /* synthetic */ d2 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v0(d2 d2Var, a0 a0Var, t8 t8Var) {
        super(a0Var, t8Var);
        this.h = d2Var;
    }

    @Override // com.touchgui.sdk.internal.d
    public final boolean a() {
        return this.h.f13753a.a(TGFunctions.FUNC_SPORT_MODE);
    }

    @Override // com.touchgui.sdk.internal.d
    public final void b(Object obj) {
        List<Integer> workouts;
        int indexOf;
        TGWorkoutMode tGWorkoutMode = (TGWorkoutMode) obj;
        if (this.h.f13753a.d() == 534 && (workouts = tGWorkoutMode.getWorkouts()) != null && (indexOf = workouts.indexOf(47)) != -1) {
            workouts.remove(indexOf);
            workouts.add(indexOf, 150);
        }
        super.a(tGWorkoutMode);
    }
}
