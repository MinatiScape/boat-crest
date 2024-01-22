package com.coveiot.android.matrixsdk;

import com.coveiot.android.matrixsdk.api.MatrixBaseRes;
import com.coveiot.android.matrixsdk.error.MatrixError;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface MatrixResponseListener {
    void onFailure(@NotNull MatrixError matrixError);

    void onResponse(@NotNull MatrixBaseRes matrixBaseRes);
}
