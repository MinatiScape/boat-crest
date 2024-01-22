package com.coveiot.sdk.ble.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/* loaded from: classes9.dex */
public class BleFileUtils {
    public static byte[] convertFileToByteArray(File file) {
        byte[] bArr = new byte[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                return bArr;
            } catch (Exception unused) {
                return bArr;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public static byte[] getImageBuffer(File file, boolean z, boolean z2, DevicePlatformEnum devicePlatformEnum) throws Exception {
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
        ImageModel imageModel = new ImageModel();
        imageModel.setBitmap(devicePlatformEnum, decodeFile, z, z2);
        return imageModel.getBuffer();
    }
}
