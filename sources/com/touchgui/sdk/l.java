package com.touchgui.sdk;

import com.touchgui.sdk.bean.TGFunctions;
import com.touchgui.sdk.internal.ba;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class l implements TGHealthDataManager {

    /* renamed from: a  reason: collision with root package name */
    public final g f13856a;
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();

    public l(com.touchgui.sdk.internal.a0 a0Var) {
        ba baVar = new ba(this);
        g gVar = new g(a0Var);
        this.f13856a = gVar;
        gVar.a(baVar);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void addOnHealthDataListener(TGHealthDataCallback tGHealthDataCallback) {
        if (this.b.contains(tGHealthDataCallback)) {
            return;
        }
        this.b.add(tGHealthDataCallback);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryAllData() {
        g gVar = this.f13856a;
        if (gVar.e.getAndSet(true)) {
            TGLogger.w(gVar.f13736a, "Synchronization is in progress. Please do not call repeatedly");
            return;
        }
        gVar.b.clear();
        gVar.b.add(new f(1, 4));
        gVar.b.add(new f(2, 4));
        gVar.b.add(new f(3, 4));
        gVar.b.add(new f(4, 4));
        if (gVar.f13736a.a(TGFunctions.FUNC_STRESS)) {
            gVar.b.add(new f(5, 4));
        }
        if (gVar.f13736a.i()) {
            gVar.b.add(new f(6, 4));
        }
        gVar.a();
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryBreathTrainData() {
        this.f13856a.a(6, 4);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryHeartRateData() {
        this.f13856a.a(1, 4);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void querySleepData() {
        this.f13856a.a(3, 4);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void querySpo2Data() {
        this.f13856a.a(4, 4);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryStepData() {
        this.f13856a.a(2, 4);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryStressData() {
        this.f13856a.a(5, 4);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void removeOnHealthDataListener(TGHealthDataCallback tGHealthDataCallback) {
        this.b.remove(tGHealthDataCallback);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final boolean syncHealthData(TGHealthData... tGHealthDataArr) {
        g gVar = this.f13856a;
        gVar.b.clear();
        for (TGHealthData tGHealthData : tGHealthDataArr) {
            gVar.b.add(new f(tGHealthData.dataType, tGHealthData.dataScope));
        }
        gVar.a();
        return true;
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryHeartRateData(boolean z) {
        g gVar = this.f13856a;
        gVar.getClass();
        gVar.a(1, z ? 2 : 1);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void querySleepData(boolean z) {
        g gVar = this.f13856a;
        gVar.getClass();
        gVar.a(3, z ? 2 : 1);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void querySpo2Data(boolean z) {
        g gVar = this.f13856a;
        gVar.getClass();
        gVar.a(4, z ? 2 : 1);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryStepData(boolean z) {
        g gVar = this.f13856a;
        gVar.getClass();
        gVar.a(2, z ? 2 : 1);
    }

    @Override // com.touchgui.sdk.TGHealthDataManager
    public final void queryStressData(boolean z) {
        g gVar = this.f13856a;
        gVar.getClass();
        gVar.a(5, z ? 2 : 1);
    }
}
