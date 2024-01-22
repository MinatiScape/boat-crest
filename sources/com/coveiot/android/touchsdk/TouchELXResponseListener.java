package com.coveiot.android.touchsdk;

import com.coveiot.android.touchsdk.api.TouchELXBaseRes;
import com.coveiot.android.touchsdk.error.TouchELXError;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public interface TouchELXResponseListener {
    void onFailure(@NotNull TouchELXError touchELXError);

    void onResponse(@NotNull TouchELXBaseRes touchELXBaseRes);
}
