package com.clevertap.android.sdk.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import com.facebook.stetho.inspector.network.DecompressionHelper;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GzipBitmapInputStreamReader implements IBitmapInputStreamReader {
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
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(connection, "connection");
        Logger.v("reading bitmap input stream in GzipBitmapInputStreamReader....");
        String contentEncoding = connection.getContentEncoding();
        if (!(contentEncoding != null ? StringsKt__StringsKt.contains$default((CharSequence) contentEncoding, (CharSequence) DecompressionHelper.GZIP_ENCODING, false, 2, (Object) null) : false)) {
            return null;
        }
        GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = gZIPInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                Logger.v("Total decompressed download size for bitmap from output stream = " + byteArrayOutputStream.size());
                return a(byteArrayOutputStream, j);
            }
        }
    }
}
