package com.touchgui.sdk;

import androidx.annotation.NonNull;
import com.touchgui.sdk.bean.TGBreathTrain;
import com.touchgui.sdk.bean.TGHeartRateData;
import com.touchgui.sdk.bean.TGSleepData;
import com.touchgui.sdk.bean.TGStepData;
import com.touchgui.sdk.bean.TGStressData;
import com.touchgui.sdk.bean.TGSyncSpo2;
@Deprecated
/* loaded from: classes12.dex */
public interface TGHealthDataManager {

    @Deprecated
    /* loaded from: classes12.dex */
    public interface OnHealthDataListener extends TGHealthDataCallback {
        default void onBreathTrainData(@NonNull TGBreathTrain tGBreathTrain) {
        }

        @Override // com.touchgui.sdk.TGHealthDataCallback
        void onCompleted();

        @Override // com.touchgui.sdk.TGHealthDataCallback
        void onError(int i, @NonNull String str);

        void onHeartRateData(@NonNull TGHeartRateData tGHeartRateData, boolean z);

        @Override // com.touchgui.sdk.TGHealthDataCallback
        void onProgress(int i);

        void onSleepData(@NonNull TGSleepData tGSleepData, boolean z);

        void onSpo2Data(@NonNull TGSyncSpo2 tGSyncSpo2, boolean z);

        @Override // com.touchgui.sdk.TGHealthDataCallback
        void onStart();

        void onStepData(@NonNull TGStepData tGStepData, boolean z);

        void onStressData(@NonNull TGStressData tGStressData, boolean z);
    }

    void addOnHealthDataListener(TGHealthDataCallback tGHealthDataCallback);

    void queryAllData();

    void queryBreathTrainData();

    void queryHeartRateData();

    void queryHeartRateData(boolean z);

    void querySleepData();

    void querySleepData(boolean z);

    void querySpo2Data();

    void querySpo2Data(boolean z);

    void queryStepData();

    void queryStepData(boolean z);

    void queryStressData();

    void queryStressData(boolean z);

    void removeOnHealthDataListener(TGHealthDataCallback tGHealthDataCallback);

    boolean syncHealthData(TGHealthData... tGHealthDataArr);
}
