package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.network.DownloadedBitmap;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public interface IBitmapDownloadRequestHandler {
    @NotNull
    DownloadedBitmap handleRequest(@NotNull BitmapDownloadRequest bitmapDownloadRequest);
}
