package com.coveiot.android.qrtray.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.coveiot.android.qrtray.model.QRCodeDataApp;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.QRTrayCodeData;
import com.coveiot.utils.utility.FileUtil;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.r;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class QRCodeUtils {
    @NotNull
    public static final QRCodeUtils INSTANCE = new QRCodeUtils();

    public final int findEarliestDatePosition(@NotNull List<QRCodeDataApp> qrDataList) {
        Intrinsics.checkNotNullParameter(qrDataList, "qrDataList");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (qrDataList.isEmpty()) {
            return -1;
        }
        int i = 0;
        Date parse = simpleDateFormat.parse(qrDataList.get(0).getLastAppliedDate());
        Date date = parse;
        int i2 = 0;
        for (QRCodeDataApp qRCodeDataApp : qrDataList) {
            int i3 = i2 + 1;
            Date parse2 = simpleDateFormat.parse(qRCodeDataApp.getLastAppliedDate());
            if (parse2 == null || !(date == null || parse2.before(date))) {
                i2 = i3;
            } else {
                i = i2;
                i2 = i3;
                date = parse2;
            }
        }
        return i;
    }

    public final void getAvailableQRCodeImageId(@NotNull Context context, @NotNull ArrayList<QRCodeDataApp> qrCodeData, @NotNull Function1<? super Integer, Unit> imageIdCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(qrCodeData, "qrCodeData");
        Intrinsics.checkNotNullParameter(imageIdCallback, "imageIdCallback");
        QRTrayCodeData qRTrayCodeDetails = SessionManager.getInstance(context).getQRTrayCodeDetails();
        Integer maxAllowed = qRTrayCodeDetails != null ? qRTrayCodeDetails.getMaxAllowed() : null;
        int intValue = maxAllowed == null ? 5 : maxAllowed.intValue();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 59000;
        for (int i2 = 0; i2 < intValue; i2++) {
            arrayList2.add(Integer.valueOf(i));
            i++;
        }
        Iterator<QRCodeDataApp> it = qrCodeData.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().getImageId()));
        }
        Set subtract = CollectionsKt___CollectionsKt.subtract(arrayList2, CollectionsKt___CollectionsKt.toSet(arrayList));
        if (subtract.isEmpty()) {
            imageIdCallback.invoke(0);
        } else {
            imageIdCallback.invoke(CollectionsKt___CollectionsKt.first((List<? extends Object>) CollectionsKt___CollectionsKt.toList(subtract)));
        }
    }

    @NotNull
    public final Bitmap getBitmapFromUrl(@NotNull String imgUrl) {
        Intrinsics.checkNotNullParameter(imgUrl, "imgUrl");
        Bitmap decodeStream = BitmapFactory.decodeStream(new URL(imgUrl).openConnection().getInputStream());
        Intrinsics.checkNotNullExpressionValue(decodeStream, "decodeStream(URL(imgUrl)…ction().getInputStream())");
        return decodeStream;
    }

    @Nullable
    public final String getCategoryIconURL(@Nullable String str, @NotNull List<? extends QRTrayCategoriesRes.QRItem> categories) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        String str2 = "";
        for (QRTrayCategoriesRes.QRItem qRItem : categories) {
            if (Intrinsics.areEqual(qRItem.getCategoryId(), str)) {
                str2 = qRItem.getIconUrl();
                Intrinsics.checkNotNullExpressionValue(str2, "it.iconUrl");
            }
        }
        return str2;
    }

    @NotNull
    public final QRCodeDataApp getFirstUploadedQRData(@NotNull List<QRCodeDataApp> qrCodes) {
        Intrinsics.checkNotNullParameter(qrCodes, "qrCodes");
        return qrCodes.get(findEarliestDatePosition(qrCodes));
    }

    @Nullable
    public final String getImageFormat(@NotNull Context context, @NotNull Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            byte[] bArr = new byte[12];
            if (openInputStream != null) {
                openInputStream.read(bArr);
            }
            if (openInputStream != null) {
                openInputStream.close();
            }
            if (bArr[0] == -1 && bArr[1] == -40 && bArr[2] == -1) {
                return "jpg";
            }
            if (bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71) {
                return FileUtil.Format.PNG;
            }
            if (bArr[0] == 0 && bArr[1] == 0 && bArr[2] == 0 && bArr[3] == 24 && bArr[4] == 102 && bArr[5] == 116 && bArr[6] == 121 && bArr[7] == 112 && bArr[8] == 104 && bArr[9] == 101 && bArr[10] == 105) {
                if (bArr[11] == 99) {
                    return "heic";
                }
                return null;
            }
            return null;
        } catch (IOException e) {
            Log.e("getImageFormat", "Error: " + e.getMessage());
            return null;
        }
    }

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
        BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, 600, 600, r.mapOf(TuplesKt.to(EncodeHintType.MARGIN, 1)));
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

    public final boolean isMaxedFromAlreadyAppliedQRs(@NotNull List<? extends QRTrayCodesRes.QRTrayCodeData> qrCodes) {
        Integer imageRefId;
        Intrinsics.checkNotNullParameter(qrCodes, "qrCodes");
        ArrayList arrayList = new ArrayList();
        for (QRTrayCodesRes.QRTrayCodeData qRTrayCodeData : qrCodes) {
            if (!qRTrayCodeData.getApplied().booleanValue() && ((imageRefId = qRTrayCodeData.getImageRefId()) == null || imageRefId.intValue() != 0)) {
                arrayList.add(qRTrayCodeData);
            }
        }
        return arrayList.size() > 0;
    }

    @NotNull
    public final Bitmap resizeBitmap(@NotNull Bitmap originalBitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(originalBitmap, "originalBitmap");
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(originalBitmap, i, i2, true);
        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(origi…ewWidth, newHeight, true)");
        return createScaledBitmap;
    }

    @Nullable
    public final File saveBitmapAsJPEG(@NotNull Context context, @NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        File file = new File(context.getFilesDir(), "qrCodeImage.jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            CloseableKt.closeFinally(fileOutputStream, null);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
