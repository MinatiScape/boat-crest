package com.mappls.sdk.maps.module.http;

import androidx.annotation.Nullable;
import okhttp3.OkHttpClient;
/* loaded from: classes11.dex */
public class HttpRequestUtil {
    public static void setLogEnabled(boolean z) {
        HttpRequestImpl.enableLog(z);
    }

    public static void setOkHttpClient(@Nullable OkHttpClient okHttpClient) {
        HttpRequestImpl.setOkHttpClient(okHttpClient);
    }

    public static void setPrintRequestUrlOnFailure(boolean z) {
        HttpRequestImpl.enablePrintRequestUrlOnFailure(z);
    }
}
