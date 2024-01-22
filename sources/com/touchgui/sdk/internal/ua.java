package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFootballAvgPace;
import com.touchgui.sdk.bean.TGFootballFieldGps;
import com.touchgui.sdk.bean.TGSportRealTimeData;
import com.touchgui.sdk.bean.TGSportRecord;
import com.touchgui.sdk.bean.TGSyncGps;
import com.touchgui.sdk.bean.TGSyncSwim;
/* loaded from: classes12.dex */
public interface ua {
    void onCompleted();

    void onError(int i, String str);

    void onFootballSportAvgPace(TGFootballAvgPace tGFootballAvgPace);

    void onFootballSportFieldGps(TGFootballFieldGps tGFootballFieldGps);

    void onGpsData(TGSyncGps tGSyncGps);

    void onProgress(int i);

    void onRealTimeData(TGSportRealTimeData tGSportRealTimeData);

    void onSportRecord(TGSportRecord tGSportRecord);

    void onStart();

    void onSwimData(TGSyncSwim tGSyncSwim);
}
