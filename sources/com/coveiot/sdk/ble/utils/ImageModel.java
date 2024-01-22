package com.coveiot.sdk.ble.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.logger.RingLogger;
import com.sifli.ezip.sifliEzipUtil;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class ImageModel {

    /* renamed from: a  reason: collision with root package name */
    public int f7588a;
    public boolean b;
    public boolean c;
    public Bitmap d;
    public byte[] e;

    public final short a(List<Short> list, byte b) {
        int shortValue;
        int shortValue2;
        int size = list.size() / b;
        if (size < 1) {
            return (short) 0;
        }
        short s = 1;
        for (int i = 0; i < size - 1; i++) {
            if (b == 3) {
                int i2 = i * 3;
                shortValue = list.get(i2).shortValue() + (list.get(i2 + 1).shortValue() << 8) + (list.get(i2 + 2).shortValue() << 16);
                int i3 = (i + 1) * 3;
                shortValue2 = list.get(i3).shortValue() + (list.get(i3 + 1).shortValue() << 8) + (list.get(i3 + 2).shortValue() << 16);
            } else {
                int i4 = i * 2;
                shortValue = list.get(i4).shortValue() + (list.get(i4 + 1).shortValue() << 8);
                int i5 = (i + 1) * 2;
                shortValue2 = (list.get(i5 + 1).shortValue() << 8) + list.get(i5).shortValue();
            }
            if (shortValue != shortValue2) {
                return s;
            }
            s = (short) (s + 1);
            if (s == 127) {
                return (short) 127;
            }
        }
        return s;
    }

    public final byte[] b(byte[] bArr, int i, byte b) {
        List arrayList = new ArrayList();
        for (byte b2 : bArr) {
            arrayList.add(Short.valueOf((short) (b2 & 255)));
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        while (arrayList.size() > 0) {
            int i2 = i * b;
            List<Short> subList = arrayList.subList(0, i2);
            int i3 = 0;
            while (subList.size() > 0) {
                short a2 = a(subList, b);
                if (a2 > 2) {
                    arrayList3.add(Short.valueOf((short) (a2 + 128)));
                    i3++;
                    for (int i4 = 0; i4 < b; i4++) {
                        arrayList3.add(subList.get(i4));
                        i3++;
                    }
                } else {
                    a2 = c(subList, b);
                    arrayList3.add(Short.valueOf(a2));
                    i3++;
                    for (int i5 = 0; i5 < a2; i5++) {
                        for (int i6 = 0; i6 < b; i6++) {
                            arrayList3.add(subList.get((i5 * b) + i6));
                            i3++;
                        }
                    }
                }
                subList = subList.subList(a2 * b, subList.size());
            }
            arrayList2.add(Short.valueOf((short) i3));
            arrayList = arrayList.subList(i2, arrayList.size());
        }
        arrayList2.addAll(arrayList3);
        int size = arrayList2.size();
        byte[] bArr2 = new byte[size];
        for (int i7 = 0; i7 < size; i7++) {
            bArr2[i7] = (byte) ((Short) arrayList2.get(i7)).shortValue();
        }
        return bArr2;
    }

    public final short c(List<Short> list, byte b) {
        int shortValue;
        int shortValue2;
        int size = list.size() / b;
        if (size <= 2) {
            return (short) size;
        }
        short s = 0;
        short s2 = 1;
        for (int i = 0; i < size - 1; i++) {
            if (b == 3) {
                int i2 = i * 3;
                shortValue = list.get(i2).shortValue() + (list.get(i2 + 1).shortValue() << 8) + (list.get(i2 + 2).shortValue() << 16);
                int i3 = (i + 1) * 3;
                shortValue2 = list.get(i3).shortValue() + (list.get(i3 + 1).shortValue() << 8) + (list.get(i3 + 2).shortValue() << 16);
            } else {
                int i4 = i * 2;
                shortValue = list.get(i4).shortValue() + (list.get(i4 + 1).shortValue() << 8);
                int i5 = (i + 1) * 2;
                shortValue2 = (list.get(i5 + 1).shortValue() << 8) + list.get(i5).shortValue();
            }
            if (shortValue == shortValue2) {
                s = (short) (s + 1);
                if (s >= 2) {
                    return (short) (s2 - 2);
                }
            } else {
                s = 0;
            }
            s2 = (short) (s2 + 1);
            if (s2 == 127) {
                return s2;
            }
        }
        return s2;
    }

    public Bitmap getBitmap() {
        return this.d;
    }

    public byte[] getBuffer() {
        return this.e;
    }

    public int getImageId() {
        return this.f7588a;
    }

    public boolean isCompressed() {
        return this.b;
    }

    public boolean isTransparent() {
        return this.c;
    }

    public void setBitmap(DevicePlatformEnum devicePlatformEnum, Bitmap bitmap, boolean z, boolean z2) throws Exception {
        int width;
        this.b = z;
        this.c = z2;
        Bitmap.Config config = bitmap.getConfig();
        Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
        if (config != config2) {
            Bitmap copy = bitmap.copy(config2, false);
            bitmap.recycle();
            bitmap = copy;
        }
        if (!z2) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            createBitmap.eraseColor(-1);
            new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            bitmap.recycle();
            bitmap = createBitmap;
        }
        if (bitmap != null) {
            this.d = bitmap;
            if (devicePlatformEnum == DevicePlatformEnum.Sifli) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] pngToEzip = sifliEzipUtil.pngToEzip(byteArrayOutputStream.toByteArray(), "rgb565a", 0, 0, 0);
                this.e = pngToEzip;
                if (pngToEzip != null) {
                    LogHelper.i("ImageModel", "Ezip convertion done, %dx%d image converted to %d bytes -- " + bitmap.getWidth() + " -- " + bitmap.getHeight() + " -- " + this.e.length);
                    return;
                }
                throw new Exception("Converting to EZIP failed");
            }
            int width2 = bitmap.getWidth() * bitmap.getHeight();
            if (this.c) {
                this.e = new byte[width2 * 3];
            } else {
                this.e = new byte[width2 * 2];
            }
            for (int i = 0; i < bitmap.getHeight(); i++) {
                for (int i2 = 0; i2 < bitmap.getWidth(); i2++) {
                    int pixel = bitmap.getPixel(i2, i);
                    if (this.c) {
                        width = ((bitmap.getWidth() * i) + i2) * 3;
                    } else {
                        width = ((bitmap.getWidth() * i) + i2) * 2;
                    }
                    if (this.c) {
                        this.e[width] = (byte) Color.alpha(pixel);
                        width++;
                    }
                    int blue = ((Color.blue(pixel) & RingLogger.EVT_UPDATE) >> 3) | ((Color.red(pixel) & RingLogger.EVT_UPDATE) << 8) | ((Color.green(pixel) & 252) << 3);
                    if (devicePlatformEnum == DevicePlatformEnum.Apollo) {
                        byte[] bArr = this.e;
                        bArr[width] = (byte) (blue & 255);
                        bArr[width + 1] = (byte) ((blue >> 8) & 255);
                    } else {
                        byte[] bArr2 = this.e;
                        bArr2[width] = (byte) ((blue >> 8) & 255);
                        bArr2[width + 1] = (byte) (blue & 255);
                    }
                }
            }
            if (this.b) {
                if (this.c) {
                    this.e = b(this.e, this.d.getWidth(), (byte) 3);
                    return;
                } else {
                    this.e = b(this.e, this.d.getWidth(), (byte) 2);
                    return;
                }
            }
            return;
        }
        throw new Exception("Converting to ARGB888 failed");
    }

    public void setImageId(int i) {
        this.f7588a = i;
    }
}
