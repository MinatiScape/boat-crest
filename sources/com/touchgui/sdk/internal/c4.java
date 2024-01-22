package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFunctions;
/* loaded from: classes12.dex */
public final class c4 extends v8 {
    public final /* synthetic */ com.touchgui.sdk.c h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c4(com.touchgui.sdk.c cVar, a0 a0Var, h8 h8Var) {
        super(a0Var, h8Var);
        this.h = cVar;
    }

    @Override // com.touchgui.sdk.internal.d
    public final boolean a() {
        return this.h.f13733a.a(TGFunctions.FUNC_USE_APP_GPS);
    }
}
