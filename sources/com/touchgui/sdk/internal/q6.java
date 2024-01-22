package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGOTACallback;
import java.io.File;
/* loaded from: classes12.dex */
public final class q6 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.h f13817a;

    public q6(com.touchgui.sdk.h hVar) {
        this.f13817a = hVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13817a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r3 = (Void) obj;
        com.touchgui.sdk.h hVar = this.f13817a;
        TGLogger.d(hVar.f13737a, "OTA completed");
        String str = hVar.k;
        if (str != null) {
            v3.b(new File(str));
        }
        hVar.j.set(false);
        TGOTACallback tGOTACallback = hVar.l;
        if (tGOTACallback != null) {
            tGOTACallback.onCompleted();
        }
    }
}
