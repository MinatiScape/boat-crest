package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFunctions;
/* loaded from: classes12.dex */
public final class u1 extends g5 {
    public final /* synthetic */ d2 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u1(d2 d2Var, a0 a0Var, rb rbVar) {
        super(a0Var, rbVar);
        this.h = d2Var;
    }

    @Override // com.touchgui.sdk.internal.d
    public final boolean a() {
        return this.h.f13753a.a(TGFunctions.FUNC_WORLD_CLOCK);
    }
}
