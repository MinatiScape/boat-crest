package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class BitmapDownloader {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final HttpUrlConnectionParams f2593a;
    @NotNull
    public final IBitmapInputStreamReader b;
    @NotNull
    public final Pair<Boolean, Integer> c;
    public long d;
    public HttpURLConnection e;

    public BitmapDownloader(@NotNull HttpUrlConnectionParams httpUrlConnectionParams, @NotNull IBitmapInputStreamReader bitmapInputStreamReader, @NotNull Pair<Boolean, Integer> sizeConstrainedPair) {
        Intrinsics.checkNotNullParameter(httpUrlConnectionParams, "httpUrlConnectionParams");
        Intrinsics.checkNotNullParameter(bitmapInputStreamReader, "bitmapInputStreamReader");
        Intrinsics.checkNotNullParameter(sizeConstrainedPair, "sizeConstrainedPair");
        this.f2593a = httpUrlConnectionParams;
        this.b = bitmapInputStreamReader;
        this.c = sizeConstrainedPair;
    }

    public final HttpURLConnection a(URL url) {
        URLConnection openConnection = url.openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setConnectTimeout(this.f2593a.getConnectTimeout());
        httpURLConnection.setReadTimeout(this.f2593a.getReadTimeout());
        httpURLConnection.setUseCaches(this.f2593a.getUseCaches());
        httpURLConnection.setDoInput(this.f2593a.getDoInput());
        for (Map.Entry<String, String> entry : this.f2593a.getRequestMap().entrySet()) {
            httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        return httpURLConnection;
    }

    @NotNull
    public final DownloadedBitmap downloadBitmap(@NotNull String srcUrl) {
        Intrinsics.checkNotNullParameter(srcUrl, "srcUrl");
        Logger.v("initiating bitmap download in BitmapDownloader....");
        this.d = Utils.getNowInMillis();
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection a2 = a(new URL(srcUrl));
            this.e = a2;
            if (a2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                a2 = null;
            }
            a2.connect();
            if (a2.getResponseCode() != 200) {
                Logger.d("File not loaded completely not going forward. URL was: " + srcUrl);
                DownloadedBitmap nullBitmapWithStatus = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
                HttpURLConnection httpURLConnection2 = this.e;
                if (httpURLConnection2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("connection");
                } else {
                    httpURLConnection = httpURLConnection2;
                }
                httpURLConnection.disconnect();
                return nullBitmapWithStatus;
            }
            Logger.v("Downloading " + srcUrl + "....");
            int contentLength = a2.getContentLength();
            Pair<Boolean, Integer> pair = this.c;
            boolean booleanValue = pair.component1().booleanValue();
            int intValue = pair.component2().intValue();
            if (booleanValue && contentLength > intValue) {
                Logger.v("Image size is larger than " + intValue + " bytes. Cancelling download!");
                DownloadedBitmap nullBitmapWithStatus2 = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.SIZE_LIMIT_EXCEEDED);
                HttpURLConnection httpURLConnection3 = this.e;
                if (httpURLConnection3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("connection");
                } else {
                    httpURLConnection = httpURLConnection3;
                }
                httpURLConnection.disconnect();
                return nullBitmapWithStatus2;
            }
            IBitmapInputStreamReader iBitmapInputStreamReader = this.b;
            InputStream inputStream = a2.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "inputStream");
            DownloadedBitmap readInputStream = iBitmapInputStreamReader.readInputStream(inputStream, a2, this.d);
            if (readInputStream == null) {
                readInputStream = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
            }
            HttpURLConnection httpURLConnection4 = this.e;
            if (httpURLConnection4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
            } else {
                httpURLConnection = httpURLConnection4;
            }
            httpURLConnection.disconnect();
            return readInputStream;
        } catch (Throwable th) {
            try {
                Logger.v("Couldn't download the notification icon. URL was: " + srcUrl);
                th.printStackTrace();
                return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
            } finally {
                try {
                    HttpURLConnection httpURLConnection5 = this.e;
                    if (httpURLConnection5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("connection");
                    } else {
                        httpURLConnection = httpURLConnection5;
                    }
                    httpURLConnection.disconnect();
                } catch (Throwable th2) {
                    Logger.v("Couldn't close connection!", th2);
                }
            }
        }
    }

    public /* synthetic */ BitmapDownloader(HttpUrlConnectionParams httpUrlConnectionParams, IBitmapInputStreamReader iBitmapInputStreamReader, Pair pair, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpUrlConnectionParams, iBitmapInputStreamReader, (i & 4) != 0 ? new Pair(Boolean.FALSE, 0) : pair);
    }
}
