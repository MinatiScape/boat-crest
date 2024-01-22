package com.clevertap.android.sdk.bitmap;

import android.content.Context;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class NotificationBitmapDownloadRequestHandler implements IBitmapDownloadRequestHandler {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final IBitmapDownloadRequestHandler f2598a;

    public NotificationBitmapDownloadRequestHandler(@NotNull IBitmapDownloadRequestHandler iBitmapDownloadRequestHandler) {
        Intrinsics.checkNotNullParameter(iBitmapDownloadRequestHandler, "iBitmapDownloadRequestHandler");
        this.f2598a = iBitmapDownloadRequestHandler;
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapDownloadRequestHandler
    @NotNull
    public DownloadedBitmap handleRequest(@NotNull BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        Logger.v("handling bitmap download request in NotificationBitmapDownloadRequestHandler....");
        String component1 = bitmapDownloadRequest.component1();
        boolean component2 = bitmapDownloadRequest.component2();
        Context component3 = bitmapDownloadRequest.component3();
        if (component1 == null || m.isBlank(component1)) {
            DownloadedBitmap downloadedBitmapPostFallbackIconCheck = Utils.getDownloadedBitmapPostFallbackIconCheck(component2, component3, DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.NO_IMAGE));
            Intrinsics.checkNotNullExpressionValue(downloadedBitmapPostFallbackIconCheck, "getDownloadedBitmapPostF…s(NO_IMAGE)\n            )");
            return downloadedBitmapPostFallbackIconCheck;
        }
        if (!m.startsWith$default(component1, "http", false, 2, null)) {
            bitmapDownloadRequest.setBitmapPath("http://static.wizrocket.com/android/ico//" + component1);
        }
        DownloadedBitmap downloadedBitmapPostFallbackIconCheck2 = Utils.getDownloadedBitmapPostFallbackIconCheck(component2, component3, this.f2598a.handleRequest(bitmapDownloadRequest));
        Intrinsics.checkNotNullExpressionValue(downloadedBitmapPostFallbackIconCheck2, "getDownloadedBitmapPostF…ontext, downloadedBitmap)");
        return downloadedBitmapPostFallbackIconCheck2;
    }
}
