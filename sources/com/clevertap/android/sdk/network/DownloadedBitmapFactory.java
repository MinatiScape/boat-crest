package com.clevertap.android.sdk.network;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class DownloadedBitmapFactory {
    @NotNull
    public static final DownloadedBitmapFactory INSTANCE = new DownloadedBitmapFactory();

    @NotNull
    public final DownloadedBitmap nullBitmapWithStatus(@NotNull DownloadedBitmap.Status status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new DownloadedBitmap(null, status, -1L);
    }

    @NotNull
    public final DownloadedBitmap successBitmap(@NotNull Bitmap bitmap, long j) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return new DownloadedBitmap(bitmap, DownloadedBitmap.Status.SUCCESS, j);
    }
}
