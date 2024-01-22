package com.clevertap.android.sdk.bitmap;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.Task;
import java.util.concurrent.Callable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class BitmapDownloadRequestHandlerWithTimeLimit implements IBitmapDownloadRequestHandler {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final IBitmapDownloadRequestHandler f2592a;

    public BitmapDownloadRequestHandlerWithTimeLimit(@NotNull IBitmapDownloadRequestHandler iBitmapDownloadRequestHandler) {
        Intrinsics.checkNotNullParameter(iBitmapDownloadRequestHandler, "iBitmapDownloadRequestHandler");
        this.f2592a = iBitmapDownloadRequestHandler;
    }

    public static final DownloadedBitmap b(BitmapDownloadRequestHandlerWithTimeLimit this$0, BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "$bitmapDownloadRequest");
        return this$0.f2592a.handleRequest(bitmapDownloadRequest);
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapDownloadRequestHandler
    @NotNull
    public DownloadedBitmap handleRequest(@NotNull final BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        Logger.v("handling bitmap download request in BitmapDownloadRequestHandlerWithTimeLimit....");
        boolean component2 = bitmapDownloadRequest.component2();
        Context component3 = bitmapDownloadRequest.component3();
        CleverTapInstanceConfig component4 = bitmapDownloadRequest.component4();
        long component5 = bitmapDownloadRequest.component5();
        if (component4 != null && component5 != -1) {
            Task ioTask = CTExecutorFactory.executors(component4).ioTask();
            Intrinsics.checkNotNullExpressionValue(ioTask, "executors(instanceConfig).ioTask()");
            DownloadedBitmap downloadedBitmap = (DownloadedBitmap) ioTask.submitAndGetResult("getNotificationBitmap", new Callable() { // from class: com.clevertap.android.sdk.bitmap.a
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    DownloadedBitmap b;
                    b = BitmapDownloadRequestHandlerWithTimeLimit.b(BitmapDownloadRequestHandlerWithTimeLimit.this, bitmapDownloadRequest);
                    return b;
                }
            }, component5);
            if (downloadedBitmap == null) {
                downloadedBitmap = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
            }
            DownloadedBitmap downloadedBitmapPostFallbackIconCheck = Utils.getDownloadedBitmapPostFallbackIconCheck(component2, component3, downloadedBitmap);
            Intrinsics.checkNotNullExpressionValue(downloadedBitmapPostFallbackIconCheck, "getDownloadedBitmapPostF…ontext, downloadedBitmap)");
            return downloadedBitmapPostFallbackIconCheck;
        }
        Logger.v("either config is null or downloadTimeLimitInMillis is negative.");
        Logger.v("will download bitmap without time limit");
        return this.f2592a.handleRequest(bitmapDownloadRequest);
    }
}
