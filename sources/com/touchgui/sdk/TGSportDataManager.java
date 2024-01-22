package com.touchgui.sdk;

import androidx.annotation.NonNull;
import com.touchgui.sdk.bean.TGFootballAvgPace;
import com.touchgui.sdk.bean.TGFootballFieldGps;
import com.touchgui.sdk.bean.TGKeepTrack;
import com.touchgui.sdk.bean.TGSportRealTimeData;
import com.touchgui.sdk.bean.TGSportRecord;
import com.touchgui.sdk.bean.TGSyncGps;
import com.touchgui.sdk.bean.TGSyncSwim;
@Deprecated
/* loaded from: classes12.dex */
public interface TGSportDataManager {

    /* loaded from: classes12.dex */
    public interface OnSportDataListener {
        void onCompleted();

        void onError(int i, @NonNull String str);

        void onFootballSportAvgPace(@NonNull TGFootballAvgPace tGFootballAvgPace);

        void onFootballSportFieldGps(@NonNull TGFootballFieldGps tGFootballFieldGps);

        void onGpsData(@NonNull TGSyncGps tGSyncGps);

        void onKeepTrack(TGKeepTrack tGKeepTrack);

        void onProgress(int i);

        void onRealTimeData(TGSportRealTimeData tGSportRealTimeData);

        void onSportRecord(@NonNull TGSportRecord tGSportRecord);

        void onStart();

        void onSwimData(@NonNull TGSyncSwim tGSyncSwim);
    }

    void addOnSportDataListener(OnSportDataListener onSportDataListener);

    void queryAllData();

    void queryFootballFieldGpsData();

    void queryFootballSportAvgPace();

    void queryGpsData();

    void queryKeepTrack();

    void queryRealTimeData();

    void querySportRecord();

    void querySwimData();

    void removeOnSportDataListener(OnSportDataListener onSportDataListener);
}
