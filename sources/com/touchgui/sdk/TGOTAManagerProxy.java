package com.touchgui.sdk;

import com.touchgui.sdk.internal.l9;
import com.touchgui.sdk.internal.o6;
import java.io.File;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class TGOTAManagerProxy implements TGOTAManager {
    private final com.touchgui.sdk.internal.a0 mClient;

    public TGOTAManagerProxy(com.touchgui.sdk.internal.a0 a0Var) {
        this.mClient = a0Var;
    }

    @Override // com.touchgui.sdk.TGOTAManager
    public void setCallback(TGOTACallback tGOTACallback) {
        com.touchgui.sdk.internal.a0 a0Var = this.mClient;
        if (a0Var.t == null) {
            a0Var.t = new h(a0Var);
        }
        a0Var.t.l = tGOTACallback;
    }

    @Override // com.touchgui.sdk.TGOTAManager
    public void start(String str, boolean z) {
        com.touchgui.sdk.internal.a0 a0Var = this.mClient;
        if (a0Var.t == null) {
            a0Var.t = new h(a0Var);
        }
        h hVar = a0Var.t;
        if (hVar.j.getAndSet(true)) {
            TGLogger.w(hVar.f13737a, "OTA in progress");
            return;
        }
        com.touchgui.sdk.internal.a0 a0Var2 = hVar.f13737a;
        TGLogger.d(a0Var2, "OTA, filePath=" + str);
        TGLogger.d(hVar.f13737a, "start OTA");
        hVar.k = new File(hVar.f13737a.d.getCacheDir(), "TGOTA" + System.currentTimeMillis()).getAbsolutePath();
        if (z) {
            com.touchgui.sdk.internal.a0 a0Var3 = hVar.f13737a;
            if (a0Var3.k == null) {
                a0Var3.k = new l9(a0Var3.j);
            }
            a0Var3.k.getDeviceInfo().execute(new o6(hVar, str));
            return;
        }
        hVar.a(str, (ArrayList) null);
    }
}
