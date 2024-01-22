package com.touchgui.sdk;

import com.touchgui.sdk.TGSportDataManager;
import com.touchgui.sdk.internal.gb;
import com.touchgui.sdk.internal.ja;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class o implements TGSportDataManager {

    /* renamed from: a  reason: collision with root package name */
    public final gb f13859a;
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();

    public o(com.touchgui.sdk.internal.a0 a0Var) {
        ja jaVar = new ja(this);
        gb gbVar = new gb(a0Var);
        this.f13859a = gbVar;
        gbVar.a(jaVar);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void addOnSportDataListener(TGSportDataManager.OnSportDataListener onSportDataListener) {
        if (this.b.contains(onSportDataListener)) {
            return;
        }
        this.b.add(onSportDataListener);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void queryAllData() {
        this.f13859a.c(0);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void queryFootballFieldGpsData() {
        gb gbVar = this.f13859a;
        boolean z = true;
        if (!gbVar.f13767a.a(33689120) && gbVar.f13767a.d() != 1) {
            z = false;
        }
        if (z) {
            gbVar.c(4);
        } else {
            gbVar.a(TGErrorCode.ERROR_WORKOUT_NOT_SUPPORT, "Football is not supported");
        }
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void queryFootballSportAvgPace() {
        gb gbVar = this.f13859a;
        boolean z = true;
        if (!gbVar.f13767a.a(33689120) && gbVar.f13767a.d() != 1) {
            z = false;
        }
        if (z) {
            gbVar.c(5);
        } else {
            gbVar.a(TGErrorCode.ERROR_WORKOUT_NOT_SUPPORT, "Football is not supported");
        }
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void queryGpsData() {
        this.f13859a.c(2);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void queryKeepTrack() {
        this.f13859a.c(7);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void queryRealTimeData() {
        this.f13859a.c(6);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void querySportRecord() {
        this.f13859a.c(1);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void querySwimData() {
        this.f13859a.c(3);
    }

    @Override // com.touchgui.sdk.TGSportDataManager
    public final void removeOnSportDataListener(TGSportDataManager.OnSportDataListener onSportDataListener) {
        this.b.remove(onSportDataListener);
    }
}
