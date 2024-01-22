package com.coveiot.android.watchfaceui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import com.blankj.utilcode.util.FileIOUtils;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KHWatchFaceModifier {
    @NotNull
    public static final KHWatchFaceModifier INSTANCE = new KHWatchFaceModifier();

    public final byte[] a(Bitmap bitmap) throws Exception {
        Intrinsics.checkNotNull(bitmap);
        Bitmap.Config config = bitmap.getConfig();
        Bitmap.Config config2 = Bitmap.Config.RGB_565;
        if (config != config2) {
            bitmap = bitmap.copy(config2, true);
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap.copy(Bitmap.Config.RGB_565, true)");
        }
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        bitmap.copyPixelsToBuffer(allocate);
        allocate.rewind();
        int remaining = allocate.remaining();
        byte[] bArr = new byte[remaining];
        allocate.get(bArr, 0, allocate.remaining());
        int i = remaining / 2;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            byte b = bArr[i3];
            int i4 = i3 + 1;
            bArr[i3] = bArr[i4];
            bArr[i4] = b;
        }
        return bArr;
    }

    public final byte[] b(File file) throws Exception {
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
        Intrinsics.checkNotNull(decodeFile);
        Bitmap.Config config = decodeFile.getConfig();
        Bitmap.Config config2 = Bitmap.Config.RGB_565;
        if (config != config2) {
            decodeFile = decodeFile.copy(config2, true);
        }
        if (decodeFile != null) {
            ByteBuffer allocate = ByteBuffer.allocate(decodeFile.getByteCount());
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            decodeFile.copyPixelsToBuffer(allocate);
            allocate.rewind();
            int remaining = allocate.remaining();
            byte[] bArr = new byte[remaining];
            allocate.get(bArr, 0, allocate.remaining());
            int i = remaining / 2;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * 2;
                byte b = bArr[i3];
                int i4 = i3 + 1;
                bArr[i3] = bArr[i4];
                bArr[i4] = b;
            }
            return bArr;
        }
        throw new Exception("Converting to RGB565 failed");
    }

    public final void generateNewBinFile(@NotNull String binFilePath, @NotNull String imagePath, @Nullable Bitmap bitmap, @NotNull File outputBinFile) {
        int i;
        byte[] readFile2BytesByStream;
        long j;
        int i2;
        int i3;
        byte[] bArr;
        Intrinsics.checkNotNullParameter(binFilePath, "binFilePath");
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        Intrinsics.checkNotNullParameter(outputBinFile, "outputBinFile");
        try {
            i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                readFile2BytesByStream = Files.readAllBytes(Paths.get(binFilePath, new String[0]));
            } else {
                readFile2BytesByStream = FileIOUtils.readFile2BytesByStream(new File(binFilePath));
            }
            j = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, readFile2BytesByStream[27], readFile2BytesByStream[26], readFile2BytesByStream[25], readFile2BytesByStream[24]}).getLong();
            long j2 = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, readFile2BytesByStream[15], readFile2BytesByStream[14], readFile2BytesByStream[13], readFile2BytesByStream[12]}).getLong();
            i2 = ByteBuffer.wrap(new byte[]{0, 0, readFile2BytesByStream[29], readFile2BytesByStream[28]}).getInt();
            i3 = ByteBuffer.wrap(new byte[]{0, 0, readFile2BytesByStream[31], readFile2BytesByStream[30]}).getInt();
            int i4 = ByteBuffer.wrap(new byte[]{0, 0, readFile2BytesByStream[17], readFile2BytesByStream[16]}).getInt();
            int i5 = ByteBuffer.wrap(new byte[]{0, 0, readFile2BytesByStream[19], readFile2BytesByStream[18]}).getInt();
            bArr = (byte[]) readFile2BytesByStream.clone();
            if (bitmap != null) {
                byte[] a2 = INSTANCE.a(bitmap);
                if (a2.length <= i4 * i5 * 2) {
                    System.arraycopy(a2, 0, bArr, (int) j2, a2.length);
                } else {
                    throw new RuntimeException("Incorrect image size");
                }
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            byte[] b = b(new File(imagePath));
            if (b.length <= i2 * i3 * 2) {
                System.arraycopy(b, 0, bArr, (int) j, b.length);
                if (outputBinFile.exists()) {
                    if (i >= 26) {
                        Files.write(Paths.get(outputBinFile.getAbsolutePath(), new String[0]), bArr, new OpenOption[0]);
                        return;
                    } else {
                        FileIOUtils.writeFileFromBytesByStream(new File(outputBinFile.getAbsolutePath()), bArr);
                        return;
                    }
                } else if (!outputBinFile.createNewFile()) {
                    throw new RuntimeException("Unable to create output bin file");
                } else {
                    if (i >= 26) {
                        Files.write(Paths.get(outputBinFile.getAbsolutePath(), new String[0]), bArr, new OpenOption[0]);
                        return;
                    } else {
                        FileIOUtils.writeFileFromBytesByStream(new File(outputBinFile.getAbsolutePath()), bArr);
                        return;
                    }
                }
            }
            throw new RuntimeException("Incorrect image size");
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            throw e;
        }
    }

    @Nullable
    public final Bitmap putOverlay(@NotNull Bitmap bgBitMap, @NotNull Bitmap fgBitMap) {
        Intrinsics.checkNotNullParameter(bgBitMap, "bgBitMap");
        Intrinsics.checkNotNullParameter(fgBitMap, "fgBitMap");
        Bitmap createBitmap = Bitmap.createBitmap(bgBitMap.getWidth(), bgBitMap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bgBitMap, new Matrix(), null);
        canvas.drawBitmap(fgBitMap, new Matrix(), null);
        return createBitmap;
    }
}
