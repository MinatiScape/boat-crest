package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGStepData;
/* loaded from: classes12.dex */
public final class r4 extends k4 {
    public final boolean g;
    public final /* synthetic */ com.touchgui.sdk.g h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r4(com.touchgui.sdk.g gVar, boolean z, int i) {
        super(i);
        this.h = gVar;
        this.g = z;
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void a() {
        v8 v8Var = new v8(this.h.f13736a, new b5((byte) (this.g ? 5 : 3)));
        v8Var.f = false;
        v8Var.execute(this.e);
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void b() {
        new v8(this.h.f13736a, new v4((byte) (this.g ? 5 : 3))).execute(this.f);
    }

    @Override // com.touchgui.sdk.internal.k4
    public final void a(Object obj) {
        com.touchgui.sdk.g.a(this.h, (TGStepData) obj, this.g);
    }
}
