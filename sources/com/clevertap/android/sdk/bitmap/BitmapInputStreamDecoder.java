package com.clevertap.android.sdk.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BitmapInputStreamDecoder implements IBitmapInputStreamReader {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final GzipBitmapInputStreamReader f2594a;

    public BitmapInputStreamDecoder() {
        this(null, 1, null);
    }

    public BitmapInputStreamDecoder(@Nullable GzipBitmapInputStreamReader gzipBitmapInputStreamReader) {
        this.f2594a = gzipBitmapInputStreamReader;
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapInputStreamReader
    @Nullable
    public DownloadedBitmap readInputStream(@NotNull InputStream inputStream, @NotNull HttpURLConnection connection, long j) {
        DownloadedBitmap readInputStream;
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(connection, "connection");
        Logger.v("reading bitmap input stream in BitmapInputStreamDecoder....");
        GzipBitmapInputStreamReader gzipBitmapInputStreamReader = this.f2594a;
        if (gzipBitmapInputStreamReader == null || (readInputStream = gzipBitmapInputStreamReader.readInputStream(inputStream, connection, j)) == null) {
            DownloadedBitmapFactory downloadedBitmapFactory = DownloadedBitmapFactory.INSTANCE;
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            Intrinsics.checkNotNullExpressionValue(decodeStream, "decodeStream(inputStream)");
            return downloadedBitmapFactory.successBitmap(decodeStream, Utils.getNowInMillis() - j);
        }
        return readInputStream;
    }

    public /* synthetic */ BitmapInputStreamDecoder(GzipBitmapInputStreamReader gzipBitmapInputStreamReader, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : gzipBitmapInputStreamReader);
    }
}
