package com.touchgui.sdk;

import com.touchgui.sdk.bean.TGSyncData;
import com.touchgui.sdk.internal.h4;
import com.touchgui.sdk.internal.q4;
import com.touchgui.sdk.internal.s4;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class e implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f13735a;

    public e(g gVar) {
        this.f13735a = gVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        TGLogger.e(this.f13735a.f13736a, "There was an error synchronizing the blood oxygen data.");
        this.f13735a.a(TGErrorCode.ERROR_HEALTH_DATA_SPO2, th.getMessage());
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        boolean z;
        int i;
        int i2;
        TGSyncData tGSyncData = (TGSyncData) obj;
        if (tGSyncData != null) {
            if (tGSyncData.getSpo2Count() > 0) {
                Iterator it = this.f13735a.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i2 = 0;
                        break;
                    }
                    f fVar = (f) it.next();
                    if (fVar.dataType == 4) {
                        i2 = fVar.dataScope;
                        break;
                    }
                }
                if (i2 == 1 || i2 == 4) {
                    g gVar = this.f13735a;
                    gVar.c.add(new q4(gVar, false));
                }
                if (i2 == 2 || i2 == 4) {
                    g gVar2 = this.f13735a;
                    gVar2.c.add(new q4(gVar2, true));
                }
            }
            if (tGSyncData.getBreathTrainCount() > 0) {
                g gVar3 = this.f13735a;
                gVar3.c.add(new h4(gVar3, tGSyncData.getBreathTrainCount()));
            }
        }
        g gVar4 = this.f13735a;
        Iterator it2 = gVar4.b.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            } else if (((f) it2.next()).dataType == 5) {
                z = true;
                break;
            }
        }
        if (z) {
            Iterator it3 = gVar4.b.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    i = 0;
                    break;
                }
                f fVar2 = (f) it3.next();
                if (fVar2.dataType == 5) {
                    i = fVar2.dataScope;
                    break;
                }
            }
            if (i == 1 || i == 4) {
                gVar4.c.add(new s4(gVar4, false));
            }
            if (i == 2 || i == 4) {
                gVar4.c.add(new s4(gVar4, true));
            }
        }
        gVar4.a(0);
    }
}
