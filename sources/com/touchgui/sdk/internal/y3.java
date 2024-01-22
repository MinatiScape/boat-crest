package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogger;
import java.nio.ByteBuffer;
import java.util.HashMap;
/* loaded from: classes12.dex */
public final class y3 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.c f13846a;

    public y3(com.touchgui.sdk.c cVar) {
        this.f13846a = cVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13846a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r6 = (Void) obj;
        com.touchgui.sdk.c cVar = this.f13846a;
        cVar.m = 1;
        a0 a0Var = cVar.f13733a;
        TGLogger.d(a0Var, "Number of polling device status for write AGPS file: " + cVar.m);
        a0 a0Var2 = cVar.f13733a;
        HashMap hashMap = u8.f13829a;
        m8 m8Var = new m8();
        ByteBuffer b = m8Var.b(18);
        b.put((byte) 2);
        b.put((byte) 3);
        new v8(a0Var2, m8Var).execute(new z3(cVar));
    }
}
