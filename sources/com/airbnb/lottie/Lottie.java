package com.airbnb.lottie;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class Lottie {
    public static void initialize(@NonNull LottieConfig lottieConfig) {
        L.setFetcher(lottieConfig.f1979a);
        L.setCacheProvider(lottieConfig.b);
        L.setTraceEnabled(lottieConfig.c);
        L.setNetworkCacheEnabled(lottieConfig.d);
        L.setNetworkCacheEnabled(lottieConfig.d);
        L.setDisablePathInterpolatorCache(lottieConfig.e);
    }
}
