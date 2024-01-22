package com.coveiot.android.eastapexsdk;

import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.error.EastApexError;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006\t"}, d2 = {"Lcom/coveiot/android/eastapexsdk/EastApexResponseListener;", "", "Lcom/coveiot/android/eastapexsdk/api/EastApexBaseRes;", "baseRes", "", "onResponse", "Lcom/coveiot/android/eastapexsdk/error/EastApexError;", "error", "onFailure", "eastapexSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public interface EastApexResponseListener {
    void onFailure(@NotNull EastApexError eastApexError);

    void onResponse(@NotNull EastApexBaseRes eastApexBaseRes);
}
