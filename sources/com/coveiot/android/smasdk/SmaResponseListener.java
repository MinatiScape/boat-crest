package com.coveiot.android.smasdk;

import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.android.smasdk.error.SmaError;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface SmaResponseListener {
    void onFailure(@NotNull SmaError smaError);

    void onResponse(@NotNull SmaBaseRes smaBaseRes);
}
