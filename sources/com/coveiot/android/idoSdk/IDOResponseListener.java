package com.coveiot.android.idoSdk;

import com.coveiot.android.idoSdk.api.IDOBaseRes;
import com.coveiot.android.idoSdk.error.IDOError;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006\t"}, d2 = {"Lcom/coveiot/android/idoSdk/IDOResponseListener;", "", "Lcom/coveiot/android/idoSdk/api/IDOBaseRes;", "response", "", "onResponse", "Lcom/coveiot/android/idoSdk/error/IDOError;", "error", "onFailure", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public interface IDOResponseListener {
    void onFailure(@NotNull IDOError iDOError);

    void onResponse(@NotNull IDOBaseRes iDOBaseRes);
}
