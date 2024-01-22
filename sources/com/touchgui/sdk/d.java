package com.touchgui.sdk;

import com.touchgui.sdk.bean.TGSyncHealth;
import com.touchgui.sdk.internal.l4;
import com.touchgui.sdk.internal.p4;
import com.touchgui.sdk.internal.r4;
import com.touchgui.sdk.internal.s4;
import com.touchgui.sdk.internal.v8;
import com.touchgui.sdk.internal.x6;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class d implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f13734a;

    public d(g gVar) {
        this.f13734a = gVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        TGLogger.e(this.f13734a.f13736a, "Failed to start synchronizing health data");
        this.f13734a.a(TGErrorCode.ERROR_HEALTH_DATA_START, th.getMessage());
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        boolean z;
        boolean z2;
        boolean z3;
        int i;
        TGSyncHealth tGSyncHealth = (TGSyncHealth) obj;
        if (tGSyncHealth != null) {
            Iterator it = this.f13734a.b.iterator();
            while (it.hasNext()) {
                f fVar = (f) it.next();
                int i2 = fVar.dataScope;
                if (i2 == 4 || i2 == 2) {
                    if (fVar.dataType == 2 && tGSyncHealth.getStepDay() > 0) {
                        g gVar = this.f13734a;
                        gVar.c.add(new r4(gVar, true, tGSyncHealth.getStepDay()));
                    } else if (fVar.dataType == 3 && tGSyncHealth.getSleepDay() > 0) {
                        g gVar2 = this.f13734a;
                        gVar2.c.add(new p4(gVar2, true, tGSyncHealth.getSleepDay()));
                    } else if (fVar.dataType == 1 && tGSyncHealth.getHeartRateDay() > 0) {
                        g gVar3 = this.f13734a;
                        gVar3.c.add(new l4(gVar3, true, tGSyncHealth.getHeartRateDay()));
                    }
                }
            }
        }
        g gVar4 = this.f13734a;
        Iterator it2 = gVar4.b.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            } else if (((f) it2.next()).dataType == 4) {
                z = true;
                break;
            }
        }
        if (!z) {
            Iterator it3 = gVar4.b.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z2 = false;
                    break;
                } else if (((f) it3.next()).dataType == 6) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                Iterator it4 = gVar4.b.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        z3 = false;
                        break;
                    } else if (((f) it4.next()).dataType == 5) {
                        z3 = true;
                        break;
                    }
                }
                if (z3) {
                    Iterator it5 = gVar4.b.iterator();
                    while (true) {
                        if (!it5.hasNext()) {
                            i = 0;
                            break;
                        }
                        f fVar2 = (f) it5.next();
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
                return;
            }
        }
        new v8(gVar4.f13736a, new x6()).execute(new e(gVar4));
    }
}
