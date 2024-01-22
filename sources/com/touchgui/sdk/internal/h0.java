package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFunctions;
/* loaded from: classes12.dex */
public final class h0 extends v8 {
    public final /* synthetic */ d2 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(d2 d2Var, a0 a0Var, h7 h7Var) {
        super(a0Var, h7Var);
        this.h = d2Var;
    }

    @Override // com.touchgui.sdk.internal.d
    public final boolean a() {
        return this.h.f13753a.a(TGFunctions.FUNC_GET_CONFIG_FROM_DEVICE);
    }
}
