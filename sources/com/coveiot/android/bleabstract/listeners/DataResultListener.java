package com.coveiot.android.bleabstract.listeners;

import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public interface DataResultListener extends BaseListener {
    void onDataError(@NotNull BleBaseError bleBaseError);

    void onDataResponse(@NotNull BleBaseResponse bleBaseResponse);

    void onProgressUpdate(@NotNull ProgressData progressData);
}
