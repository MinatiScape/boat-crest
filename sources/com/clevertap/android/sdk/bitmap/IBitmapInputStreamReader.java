package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.network.DownloadedBitmap;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface IBitmapInputStreamReader {
    @Nullable
    DownloadedBitmap readInputStream(@NotNull InputStream inputStream, @NotNull HttpURLConnection httpURLConnection, long j);
}
