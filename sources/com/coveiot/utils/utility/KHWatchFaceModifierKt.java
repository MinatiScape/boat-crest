package com.coveiot.utils.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class KHWatchFaceModifierKt {
    public static final byte[] a(File file) throws Exception {
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

    @RequiresApi(26)
    public static final void generateNewBinFile(@NotNull String binFilePath, @NotNull String imagePath, @NotNull File outputBinFile) {
        Intrinsics.checkNotNullParameter(binFilePath, "binFilePath");
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        Intrinsics.checkNotNullParameter(outputBinFile, "outputBinFile");
        try {
            byte[] readAllBytes = Files.readAllBytes(Paths.get(binFilePath, new String[0]));
            long j = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, readAllBytes[27], readAllBytes[26], readAllBytes[25], readAllBytes[24]}).getLong();
            int i = ByteBuffer.wrap(new byte[]{0, 0, readAllBytes[29], readAllBytes[28]}).getInt();
            int i2 = ByteBuffer.wrap(new byte[]{0, 0, readAllBytes[31], readAllBytes[30]}).getInt();
            byte[] a2 = a(new File(imagePath));
            if (a2.length <= i * i2 * 2) {
                byte[] bArr = (byte[]) readAllBytes.clone();
                System.arraycopy(a2, 0, bArr, (int) j, a2.length);
                if (outputBinFile.exists()) {
                    Files.write(Paths.get(outputBinFile.getAbsolutePath(), new String[0]), bArr, new OpenOption[0]);
                    return;
                } else if (outputBinFile.createNewFile()) {
                    Files.write(Paths.get(outputBinFile.getAbsolutePath(), new String[0]), bArr, new OpenOption[0]);
                    return;
                } else {
                    throw new RuntimeException("Unable to create output bin file");
                }
            }
            throw new RuntimeException("Incorrect image size");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
