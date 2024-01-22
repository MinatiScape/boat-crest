package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGBreathTrain;
/* loaded from: classes12.dex */
public final class h4 extends k4 {
    public final /* synthetic */ com.touchgui.sdk.g g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h4(com.touchgui.sdk.g gVar, int i) {
        super(i);
        this.g = gVar;
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void a() {
        g5 g5Var = new g5(this.g.f13736a, new x4());
        g5Var.f = false;
        g5Var.execute(this.e);
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void b() {
        g5 g5Var = new g5(this.g.f13736a, new y4());
        g5Var.f = false;
        g5Var.execute(this.f);
    }

    @Override // com.touchgui.sdk.internal.k4
    public final void a(Object obj) {
        com.touchgui.sdk.g.a(this.g, (TGBreathTrain) obj, false);
    }
}
