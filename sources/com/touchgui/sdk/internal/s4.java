package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGStressData;
/* loaded from: classes12.dex */
public final class s4 extends o4 {
    public final boolean e;
    public final /* synthetic */ com.touchgui.sdk.g f;

    public s4(com.touchgui.sdk.g gVar, boolean z) {
        this.f = gVar;
        this.e = z;
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void a() {
        g5 g5Var = new g5(this.f.f13736a, new w5(this.e));
        g5Var.f = false;
        g5Var.execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void b() {
        new g5(this.f.f13736a, new x5(this.e)).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.o4
    public final boolean a(Object obj) {
        TGStressData tGStressData = (TGStressData) obj;
        com.touchgui.sdk.g.a(this.f, tGStressData, this.e);
        return this.e && tGStressData.isHaveMoreData();
    }
}
