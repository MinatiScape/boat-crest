package com.touchgui.sdk;

import androidx.annotation.NonNull;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.util.List;
/* loaded from: classes12.dex */
public interface TGWorkoutDataCallback {
    void onCompleted(@NonNull List<TGWorkoutRecord> list);

    void onError(int i, @NonNull String str);

    void onProgress(int i);

    void onStart();
}
