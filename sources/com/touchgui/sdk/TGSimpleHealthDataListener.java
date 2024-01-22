package com.touchgui.sdk;

import androidx.annotation.NonNull;
import com.touchgui.sdk.TGHealthDataManager;
import com.touchgui.sdk.bean.TGHeartRateData;
import com.touchgui.sdk.bean.TGSleepData;
import com.touchgui.sdk.bean.TGStepData;
import com.touchgui.sdk.bean.TGStressData;
import com.touchgui.sdk.bean.TGSyncSpo2;
@Deprecated
/* loaded from: classes12.dex */
public class TGSimpleHealthDataListener implements TGHealthDataManager.OnHealthDataListener {
    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener, com.touchgui.sdk.TGHealthDataCallback
    public void onCompleted() {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener, com.touchgui.sdk.TGHealthDataCallback
    public void onError(int i, @NonNull String str) {
    }

    @Override // com.touchgui.sdk.TGHealthDataCallback
    public void onHealthData(@NonNull Object obj) {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener
    public void onHeartRateData(@NonNull TGHeartRateData tGHeartRateData, boolean z) {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener, com.touchgui.sdk.TGHealthDataCallback
    public void onProgress(int i) {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener
    public void onSleepData(@NonNull TGSleepData tGSleepData, boolean z) {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener
    public void onSpo2Data(@NonNull TGSyncSpo2 tGSyncSpo2, boolean z) {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener, com.touchgui.sdk.TGHealthDataCallback
    public void onStart() {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener
    public void onStepData(@NonNull TGStepData tGStepData, boolean z) {
    }

    @Override // com.touchgui.sdk.TGHealthDataManager.OnHealthDataListener
    public void onStressData(@NonNull TGStressData tGStressData, boolean z) {
    }
}
