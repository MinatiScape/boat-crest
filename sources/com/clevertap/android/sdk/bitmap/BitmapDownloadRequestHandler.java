package com.clevertap.android.sdk.bitmap;

import android.content.Context;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import com.clevertap.android.sdk.network.NetworkManager;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public class BitmapDownloadRequestHandler implements IBitmapDownloadRequestHandler {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final BitmapDownloader f2591a;

    public BitmapDownloadRequestHandler(@NotNull BitmapDownloader bitmapDownloader) {
        Intrinsics.checkNotNullParameter(bitmapDownloader, "bitmapDownloader");
        this.f2591a = bitmapDownloader;
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapDownloadRequestHandler
    @NotNull
    public DownloadedBitmap handleRequest(@NotNull BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        Logger.v("handling bitmap download request in BitmapDownloadRequestHandler....");
        String bitmapPath = bitmapDownloadRequest.getBitmapPath();
        Context context = bitmapDownloadRequest.getContext();
        if (bitmapPath == null || m.isBlank(bitmapPath)) {
            return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.NO_IMAGE);
        }
        String replace$default = m.replace$default(m.replace$default(m.replace$default(m.replace$default(bitmapPath, "///", MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 4, (Object) null), "//", MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 4, (Object) null), "http:/", "http://", false, 4, (Object) null), "https:/", "https://", false, 4, (Object) null);
        if (context != null && !NetworkManager.isNetworkOnline(context)) {
            Logger.v("Network connectivity unavailable. Not downloading bitmap. URL was: " + replace$default);
            return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.NO_NETWORK);
        }
        return this.f2591a.downloadBitmap(replace$default);
    }
}
