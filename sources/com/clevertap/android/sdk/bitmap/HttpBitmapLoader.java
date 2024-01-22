package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.google.common.net.HttpHeaders;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.r;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class HttpBitmapLoader {
    @NotNull
    public static final HttpBitmapLoader INSTANCE = new HttpBitmapLoader();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final HttpUrlConnectionParams f2596a = new HttpUrlConnectionParams(1000, 5000, true, true, r.mapOf(TuplesKt.to(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate")));
    @NotNull
    public static final HttpUrlConnectionParams b = new HttpUrlConnectionParams(0, 0, true, true, null, 19, null);

    /* loaded from: classes2.dex */
    public enum HttpBitmapOperation {
        DOWNLOAD_NOTIFICATION_BITMAP,
        DOWNLOAD_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT,
        DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP,
        DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT,
        DOWNLOAD_INAPP_BITMAP
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HttpBitmapOperation.values().length];
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_NOTIFICATION_BITMAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_INAPP_BITMAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    @NotNull
    public static final DownloadedBitmap getHttpBitmap(@NotNull HttpBitmapOperation bitmapOperation, @NotNull BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapOperation, "bitmapOperation");
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        int i = WhenMappings.$EnumSwitchMapping$0[bitmapOperation.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            return new BitmapDownloadRequestHandler(new BitmapDownloader(b, new BitmapInputStreamDecoder(null, 1, null), null, 4, null)).handleRequest(bitmapDownloadRequest);
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    return new BitmapDownloadRequestHandlerWithTimeLimit(new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(f2596a, new BitmapInputStreamDecoder(new GzipBitmapInputStreamReader()), new Pair(Boolean.TRUE, Integer.valueOf(bitmapDownloadRequest.getDownloadSizeLimitInBytes())))))).handleRequest(bitmapDownloadRequest);
                }
                return new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(f2596a, new BitmapInputStreamDecoder(new GzipBitmapInputStreamReader()), new Pair(Boolean.TRUE, Integer.valueOf(bitmapDownloadRequest.getDownloadSizeLimitInBytes()))))).handleRequest(bitmapDownloadRequest);
            }
            return new BitmapDownloadRequestHandlerWithTimeLimit(new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(f2596a, new BitmapInputStreamDecoder(new GzipBitmapInputStreamReader()), null, 4, null)))).handleRequest(bitmapDownloadRequest);
        }
        return new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(f2596a, new BitmapInputStreamDecoder(null, 1, null), null, 4, null))).handleRequest(bitmapDownloadRequest);
    }
}
