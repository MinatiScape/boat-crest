package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGSportDataManager;
import com.touchgui.sdk.bean.TGFootballAvgPace;
import com.touchgui.sdk.bean.TGFootballFieldGps;
import com.touchgui.sdk.bean.TGSportRealTimeData;
import com.touchgui.sdk.bean.TGSportRecord;
import com.touchgui.sdk.bean.TGSyncGps;
import com.touchgui.sdk.bean.TGSyncSwim;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class ja implements ua {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.o f13784a;

    public ja(com.touchgui.sdk.o oVar) {
        this.f13784a = oVar;
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onCompleted() {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onCompleted();
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onError(int i, String str) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onError(i, str);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onFootballSportAvgPace(TGFootballAvgPace tGFootballAvgPace) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onFootballSportAvgPace(tGFootballAvgPace);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onFootballSportFieldGps(TGFootballFieldGps tGFootballFieldGps) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onFootballSportFieldGps(tGFootballFieldGps);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onGpsData(TGSyncGps tGSyncGps) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onGpsData(tGSyncGps);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onProgress(int i) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onProgress(i);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onRealTimeData(TGSportRealTimeData tGSportRealTimeData) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onRealTimeData(tGSportRealTimeData);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onSportRecord(TGSportRecord tGSportRecord) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onSportRecord(tGSportRecord);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onStart() {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onStart();
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onSwimData(TGSyncSwim tGSyncSwim) {
        Iterator it = this.f13784a.b.iterator();
        while (it.hasNext()) {
            ((TGSportDataManager.OnSportDataListener) it.next()).onSwimData(tGSyncSwim);
        }
    }
}
