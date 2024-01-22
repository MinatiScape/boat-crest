package com.airbnb.lottie;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.os.TraceCompat;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import java.io.File;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class L {
    public static boolean DBG = false;
    public static final String TAG = "LOTTIE";

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1973a = false;
    public static boolean b = true;
    public static boolean c = true;
    public static String[] d;
    public static long[] e;
    public static int f;
    public static int g;
    public static LottieNetworkFetcher h;
    public static LottieNetworkCacheProvider i;
    public static volatile NetworkFetcher j;
    public static volatile NetworkCache k;

    /* loaded from: classes.dex */
    public class a implements LottieNetworkCacheProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f1974a;

        public a(Context context) {
            this.f1974a = context;
        }

        @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
        @NonNull
        public File getCacheDir() {
            return new File(this.f1974a.getCacheDir(), "lottie_network_cache");
        }
    }

    public static void beginSection(String str) {
        if (f1973a) {
            int i2 = f;
            if (i2 == 20) {
                g++;
                return;
            }
            d[i2] = str;
            e[i2] = System.nanoTime();
            TraceCompat.beginSection(str);
            f++;
        }
    }

    public static float endSection(String str) {
        int i2 = g;
        if (i2 > 0) {
            g = i2 - 1;
            return 0.0f;
        } else if (f1973a) {
            int i3 = f - 1;
            f = i3;
            if (i3 != -1) {
                if (str.equals(d[i3])) {
                    TraceCompat.endSection();
                    return ((float) (System.nanoTime() - e[f])) / 1000000.0f;
                }
                throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + d[f] + ".");
            }
            throw new IllegalStateException("Can't end trace section. There are none.");
        } else {
            return 0.0f;
        }
    }

    public static boolean getDisablePathInterpolatorCache() {
        return c;
    }

    @Nullable
    public static NetworkCache networkCache(@NonNull Context context) {
        if (b) {
            Context applicationContext = context.getApplicationContext();
            NetworkCache networkCache = k;
            if (networkCache == null) {
                synchronized (NetworkCache.class) {
                    networkCache = k;
                    if (networkCache == null) {
                        LottieNetworkCacheProvider lottieNetworkCacheProvider = i;
                        if (lottieNetworkCacheProvider == null) {
                            lottieNetworkCacheProvider = new a(applicationContext);
                        }
                        networkCache = new NetworkCache(lottieNetworkCacheProvider);
                        k = networkCache;
                    }
                }
            }
            return networkCache;
        }
        return null;
    }

    @NonNull
    public static NetworkFetcher networkFetcher(@NonNull Context context) {
        NetworkFetcher networkFetcher = j;
        if (networkFetcher == null) {
            synchronized (NetworkFetcher.class) {
                networkFetcher = j;
                if (networkFetcher == null) {
                    NetworkCache networkCache = networkCache(context);
                    LottieNetworkFetcher lottieNetworkFetcher = h;
                    if (lottieNetworkFetcher == null) {
                        lottieNetworkFetcher = new DefaultLottieNetworkFetcher();
                    }
                    networkFetcher = new NetworkFetcher(networkCache, lottieNetworkFetcher);
                    j = networkFetcher;
                }
            }
        }
        return networkFetcher;
    }

    public static void setCacheProvider(LottieNetworkCacheProvider lottieNetworkCacheProvider) {
        i = lottieNetworkCacheProvider;
    }

    public static void setDisablePathInterpolatorCache(boolean z) {
        c = z;
    }

    public static void setFetcher(LottieNetworkFetcher lottieNetworkFetcher) {
        h = lottieNetworkFetcher;
    }

    public static void setNetworkCacheEnabled(boolean z) {
        b = z;
    }

    public static void setTraceEnabled(boolean z) {
        if (f1973a == z) {
            return;
        }
        f1973a = z;
        if (z) {
            d = new String[20];
            e = new long[20];
        }
    }
}
