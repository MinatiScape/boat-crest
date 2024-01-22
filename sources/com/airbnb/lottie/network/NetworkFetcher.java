package com.airbnb.lottie.network;

import android.content.Context;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import no.nordicsemi.android.dfu.DfuBaseService;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class NetworkFetcher {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final NetworkCache f2060a;
    @NonNull
    public final LottieNetworkFetcher b;

    public NetworkFetcher(@Nullable NetworkCache networkCache, @NonNull LottieNetworkFetcher lottieNetworkFetcher) {
        this.f2060a = networkCache;
        this.b = lottieNetworkFetcher;
    }

    @Nullable
    @WorkerThread
    public final LottieComposition a(Context context, @NonNull String str, @Nullable String str2) {
        NetworkCache networkCache;
        Pair<FileExtension, InputStream> a2;
        LottieResult<LottieComposition> fromJsonInputStreamSync;
        if (str2 == null || (networkCache = this.f2060a) == null || (a2 = networkCache.a(str)) == null) {
            return null;
        }
        FileExtension fileExtension = (FileExtension) a2.first;
        InputStream inputStream = (InputStream) a2.second;
        if (fileExtension == FileExtension.ZIP) {
            fromJsonInputStreamSync = LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), str2);
        } else {
            fromJsonInputStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, str2);
        }
        if (fromJsonInputStreamSync.getValue() != null) {
            return fromJsonInputStreamSync.getValue();
        }
        return null;
    }

    @NonNull
    @WorkerThread
    public final LottieResult<LottieComposition> b(Context context, @NonNull String str, @Nullable String str2) {
        Logger.debug("Fetching " + str);
        Closeable closeable = null;
        try {
            try {
                LottieFetchResult fetchSync = this.b.fetchSync(str);
                if (fetchSync.isSuccessful()) {
                    LottieResult<LottieComposition> c = c(context, str, fetchSync.bodyByteStream(), fetchSync.contentType(), str2);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Completed fetch from network. Success: ");
                    sb.append(c.getValue() != null);
                    Logger.debug(sb.toString());
                    try {
                        fetchSync.close();
                    } catch (IOException e) {
                        Logger.warning("LottieFetchResult close failed ", e);
                    }
                    return c;
                }
                LottieResult<LottieComposition> lottieResult = new LottieResult<>(new IllegalArgumentException(fetchSync.error()));
                try {
                    fetchSync.close();
                } catch (IOException e2) {
                    Logger.warning("LottieFetchResult close failed ", e2);
                }
                return lottieResult;
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        closeable.close();
                    } catch (IOException e3) {
                        Logger.warning("LottieFetchResult close failed ", e3);
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            LottieResult<LottieComposition> lottieResult2 = new LottieResult<>(e4);
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (IOException e5) {
                    Logger.warning("LottieFetchResult close failed ", e5);
                }
            }
            return lottieResult2;
        }
    }

    @NonNull
    public final LottieResult<LottieComposition> c(Context context, @NonNull String str, @NonNull InputStream inputStream, @Nullable String str2, @Nullable String str3) throws IOException {
        LottieResult<LottieComposition> e;
        FileExtension fileExtension;
        NetworkCache networkCache;
        if (str2 == null) {
            str2 = "application/json";
        }
        if (!str2.contains(DfuBaseService.MIME_TYPE_ZIP) && !str2.contains("application/x-zip") && !str2.contains("application/x-zip-compressed") && !str.split("\\?")[0].endsWith(".lottie")) {
            Logger.debug("Received json response.");
            fileExtension = FileExtension.JSON;
            e = d(str, inputStream, str3);
        } else {
            Logger.debug("Handling zip response.");
            FileExtension fileExtension2 = FileExtension.ZIP;
            e = e(context, str, inputStream, str3);
            fileExtension = fileExtension2;
        }
        if (str3 != null && e.getValue() != null && (networkCache = this.f2060a) != null) {
            networkCache.e(str, fileExtension);
        }
        return e;
    }

    @NonNull
    public final LottieResult<LottieComposition> d(@NonNull String str, @NonNull InputStream inputStream, @Nullable String str2) throws IOException {
        NetworkCache networkCache;
        if (str2 != null && (networkCache = this.f2060a) != null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(networkCache.f(str, inputStream, FileExtension.JSON).getAbsolutePath()), str);
        }
        return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, null);
    }

    @NonNull
    public final LottieResult<LottieComposition> e(Context context, @NonNull String str, @NonNull InputStream inputStream, @Nullable String str2) throws IOException {
        NetworkCache networkCache;
        if (str2 != null && (networkCache = this.f2060a) != null) {
            return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(new FileInputStream(networkCache.f(str, inputStream, FileExtension.ZIP))), str);
        }
        return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), null);
    }

    @NonNull
    @WorkerThread
    public LottieResult<LottieComposition> fetchSync(Context context, @NonNull String str, @Nullable String str2) {
        LottieComposition a2 = a(context, str, str2);
        if (a2 != null) {
            return new LottieResult<>(a2);
        }
        Logger.debug("Animation for " + str + " not found in cache. Fetching from network.");
        return b(context, str, str2);
    }
}
