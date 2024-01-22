package com.clevertap.android.sdk.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BitmapInputStreamReader implements IBitmapInputStreamReader {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final IBitmapInputStreamReader f2595a;
    public final boolean b;

    public BitmapInputStreamReader() {
        this(null, false, 3, null);
    }

    public BitmapInputStreamReader(@Nullable IBitmapInputStreamReader iBitmapInputStreamReader, boolean z) {
        this.f2595a = iBitmapInputStreamReader;
        this.b = z;
    }

    public final DownloadedBitmap a(ByteArrayOutputStream byteArrayOutputStream, long j) {
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        DownloadedBitmapFactory downloadedBitmapFactory = DownloadedBitmapFactory.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return downloadedBitmapFactory.successBitmap(bitmap, Utils.getNowInMillis() - j);
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapInputStreamReader
    @Nullable
    public DownloadedBitmap readInputStream(@NotNull InputStream inputStream, @NotNull HttpURLConnection connection, long j) {
        DownloadedBitmap readInputStream;
        int contentLength;
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(connection, "connection");
        Logger.v("reading bitmap input stream in BitmapInputStreamReader....");
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            i += read;
            byteArrayOutputStream.write(bArr, 0, read);
            Logger.v("Downloaded " + i + " bytes");
        }
        Logger.v("Total download size for bitmap = " + i);
        if (this.b && (contentLength = connection.getContentLength()) != -1 && contentLength != i) {
            Logger.d("File not loaded completely not going forward. URL was: " + connection.getURL());
            return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
        }
        IBitmapInputStreamReader iBitmapInputStreamReader = this.f2595a;
        return (iBitmapInputStreamReader == null || (readInputStream = iBitmapInputStreamReader.readInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), connection, j)) == null) ? a(byteArrayOutputStream, j) : readInputStream;
    }

    public /* synthetic */ BitmapInputStreamReader(IBitmapInputStreamReader iBitmapInputStreamReader, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : iBitmapInputStreamReader, (i & 2) != 0 ? false : z);
    }
}
