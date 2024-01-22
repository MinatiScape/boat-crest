package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.TGHealthDataCallback;
import com.touchgui.sdk.TGLogger;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class e4 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.g f13758a;

    public e4(com.touchgui.sdk.g gVar) {
        this.f13758a = gVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        TGLogger.d(this.f13758a.f13736a, "Failed to finish synchronizing health data");
        this.f13758a.a(TGErrorCode.ERROR_HEALTH_DATA_COMPLETED, th.getMessage());
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r5 = (Void) obj;
        TGLogger.d(this.f13758a.f13736a, "Synchronization of health data is complete");
        com.touchgui.sdk.g gVar = this.f13758a;
        int size = gVar.c.size();
        int size2 = gVar.e.get() ? gVar.c.size() : 0;
        if (size2 > 0) {
            int i = (size * 100) / size2;
            ba baVar = gVar.f;
            if (baVar != null) {
                Iterator it = baVar.f13747a.b.iterator();
                while (it.hasNext()) {
                    ((TGHealthDataCallback) it.next()).onProgress(i);
                }
            }
        }
        ba baVar2 = gVar.f;
        if (baVar2 != null) {
            Iterator it2 = baVar2.f13747a.b.iterator();
            while (it2.hasNext()) {
                ((TGHealthDataCallback) it2.next()).onCompleted();
            }
        }
        gVar.e.set(false);
    }
}
