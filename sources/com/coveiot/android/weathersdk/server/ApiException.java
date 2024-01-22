package com.coveiot.android.weathersdk.server;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/android/weathersdk/server/ApiException;", "Ljava/lang/RuntimeException;", "", Constants.KEY_MESSAGE, "<init>", "(Ljava/lang/String;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class ApiException extends RuntimeException {
    public ApiException(@Nullable String str) {
        super(str);
    }
}
