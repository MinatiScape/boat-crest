package com.coveiot.android.leonardo.utils;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class QRCodeUtils {
    @NotNull
    public static final QRCodeUtils INSTANCE = new QRCodeUtils();

    @Nullable
    public final String getMetaDataFromQRCode(@NotNull Bitmap qrCodeBitmap) {
        Intrinsics.checkNotNullParameter(qrCodeBitmap, "qrCodeBitmap");
        int[] iArr = new int[qrCodeBitmap.getWidth() * qrCodeBitmap.getHeight()];
        qrCodeBitmap.getPixels(iArr, 0, qrCodeBitmap.getWidth(), 0, 0, qrCodeBitmap.getWidth(), qrCodeBitmap.getHeight());
        Result decode = new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(qrCodeBitmap.getWidth(), qrCodeBitmap.getHeight(), iArr))));
        Intrinsics.checkNotNullExpressionValue(decode, "reader.decode(bitmap)");
        return decode.getText();
    }

    @Nullable
    public final Bitmap getQRCodeBitmapFromMetaData(@Nullable String str) throws WriterException {
        BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, 600, 600, kotlin.collections.r.mapOf(TuplesKt.to(EncodeHintType.MARGIN, 1)));
        int width = encode.getWidth();
        int height = encode.getHeight();
        int[] iArr = new int[width * height];
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                iArr[(i * width) + i2] = encode.get(i2, i) ? ViewCompat.MEASURED_STATE_MASK : -1;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }
}
