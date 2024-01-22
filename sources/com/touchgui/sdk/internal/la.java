package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGWorkoutDataCallback;
import com.touchgui.sdk.bean.TGFootballAvgPace;
import com.touchgui.sdk.bean.TGFootballFieldGps;
import com.touchgui.sdk.bean.TGSportRealTimeData;
import com.touchgui.sdk.bean.TGSportRecord;
import com.touchgui.sdk.bean.TGSyncGps;
import com.touchgui.sdk.bean.TGSyncSwim;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class la implements ua {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ma f13790a;

    public la(ma maVar) {
        this.f13790a = maVar;
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onCompleted() {
        ma maVar = this.f13790a;
        Iterator it = maVar.b.iterator();
        while (it.hasNext()) {
            ((TGWorkoutDataCallback) it.next()).onCompleted(maVar.c);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onError(int i, String str) {
        Iterator it = this.f13790a.b.iterator();
        while (it.hasNext()) {
            ((TGWorkoutDataCallback) it.next()).onError(i, str);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onFootballSportAvgPace(TGFootballAvgPace tGFootballAvgPace) {
        ma maVar = this.f13790a;
        maVar.getClass();
        TGWorkoutRecord a2 = maVar.a(tGFootballAvgPace.getDate());
        if (a2 != null) {
            a2.setFootballAvgPace(tGFootballAvgPace);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onFootballSportFieldGps(TGFootballFieldGps tGFootballFieldGps) {
        ma maVar = this.f13790a;
        maVar.getClass();
        TGWorkoutRecord a2 = maVar.a(tGFootballFieldGps.getDate());
        if (a2 != null) {
            a2.setFootballFieldGpsData(tGFootballFieldGps.getItems());
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onGpsData(TGSyncGps tGSyncGps) {
        ma maVar = this.f13790a;
        maVar.getClass();
        TGWorkoutRecord a2 = maVar.a(tGSyncGps.getDate());
        if (a2 != null) {
            a2.setGpsData(tGSyncGps.getItems());
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onProgress(int i) {
        Iterator it = this.f13790a.b.iterator();
        while (it.hasNext()) {
            ((TGWorkoutDataCallback) it.next()).onProgress(i);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onRealTimeData(TGSportRealTimeData tGSportRealTimeData) {
        ma maVar = this.f13790a;
        maVar.getClass();
        TGWorkoutRecord a2 = maVar.a(tGSportRealTimeData.getDate());
        if (a2 != null) {
            a2.setRealTimeData(tGSportRealTimeData);
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onSportRecord(TGSportRecord tGSportRecord) {
        ma maVar = this.f13790a;
        maVar.getClass();
        TGWorkoutRecord tGWorkoutRecord = new TGWorkoutRecord();
        tGWorkoutRecord.setSummary(tGSportRecord);
        maVar.c.add(tGWorkoutRecord);
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onStart() {
        Iterator it = this.f13790a.b.iterator();
        while (it.hasNext()) {
            ((TGWorkoutDataCallback) it.next()).onStart();
        }
    }

    @Override // com.touchgui.sdk.internal.ua
    public final void onSwimData(TGSyncSwim tGSyncSwim) {
        ma maVar = this.f13790a;
        maVar.getClass();
        TGWorkoutRecord a2 = maVar.a(tGSyncSwim.getDate());
        if (a2 != null) {
            a2.setSwimData(tGSyncSwim);
        }
    }

    public /* synthetic */ la(ma maVar, int i) {
        this(maVar);
    }
}
