package com.coveiot.android.jstyle1860Sdk;

import com.coveiot.android.jstyle1860Sdk.api.JstyleBaseRes;
import com.coveiot.android.jstyle1860Sdk.error.JstyleError;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006\t"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/JstyleResponseListener;", "", "Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseRes;", "baseRes", "", "onResponse", "Lcom/coveiot/android/jstyle1860Sdk/error/JstyleError;", "error", "onFailure", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public interface JstyleResponseListener {
    void onFailure(@NotNull JstyleError jstyleError);

    void onResponse(@NotNull JstyleBaseRes jstyleBaseRes);
}
