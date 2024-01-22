package com.touchgui.sdk;

import androidx.annotation.NonNull;
import com.touchgui.sdk.TGSportDataManager;
import com.touchgui.sdk.bean.TGFootballAvgPace;
import com.touchgui.sdk.bean.TGFootballFieldGps;
import com.touchgui.sdk.bean.TGKeepTrack;
import com.touchgui.sdk.bean.TGSportRealTimeData;
import com.touchgui.sdk.bean.TGSportRecord;
import com.touchgui.sdk.bean.TGSyncGps;
import com.touchgui.sdk.bean.TGSyncSwim;
@Deprecated
/* loaded from: classes12.dex */
public class TGSimpleSportDataListener implements TGSportDataManager.OnSportDataListener {
    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onCompleted() {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onError(int i, @NonNull String str) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onFootballSportAvgPace(@NonNull TGFootballAvgPace tGFootballAvgPace) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onFootballSportFieldGps(@NonNull TGFootballFieldGps tGFootballFieldGps) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onGpsData(@NonNull TGSyncGps tGSyncGps) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onKeepTrack(TGKeepTrack tGKeepTrack) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onProgress(int i) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onRealTimeData(TGSportRealTimeData tGSportRealTimeData) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onSportRecord(@NonNull TGSportRecord tGSportRecord) {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onStart() {
    }

    @Override // com.touchgui.sdk.TGSportDataManager.OnSportDataListener
    public void onSwimData(@NonNull TGSyncSwim tGSyncSwim) {
    }
}
