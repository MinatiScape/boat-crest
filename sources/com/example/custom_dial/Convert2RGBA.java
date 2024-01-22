package com.example.custom_dial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes9.dex */
public class Convert2RGBA {

    /* renamed from: a  reason: collision with root package name */
    public final String f7889a = getClass().getSimpleName();
    public Context b;
    public byte[] c;
    public byte[] d;

    /* loaded from: classes9.dex */
    public class a extends AsyncTask<RGBAParam, Void, String> {

        /* renamed from: a  reason: collision with root package name */
        public CustomDialCallback f7890a;

        public a(CustomDialCallback customDialCallback) {
            this.f7890a = customDialCallback;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public String doInBackground(RGBAParam... rGBAParamArr) {
            String sb;
            long j;
            byte[] bArr;
            byte b;
            int j2;
            byte b2 = 0;
            RGBAParam rGBAParam = rGBAParamArr[0];
            long currentTimeMillis = System.currentTimeMillis();
            byte[] n = Convert2RGBA.this.n(rGBAParam.filePath);
            if (n == null) {
                return null;
            }
            List h = Convert2RGBA.this.h(n);
            byte[] k = Convert2RGBA.this.k(n);
            byte[] m = Convert2RGBA.this.m(n, h, k, rGBAParam.pWidth, rGBAParam.pHigh);
            Convert2RGBA.this.d = m;
            int i = 0;
            byte[] bArr2 = null;
            while (i < h.size()) {
                ImageAddressContent imageAddressContent = (ImageAddressContent) h.get(i);
                byte[] bArr3 = imageAddressContent.f7894a;
                if (bArr3[b2] == 1 || bArr3[b2] == 2) {
                    j = currentTimeMillis;
                    int j3 = Convert2RGBA.this.j(h, imageAddressContent.b);
                    int i2 = imageAddressContent.b;
                    Log.e(Convert2RGBA.this.f7889a, "单图或指针图片开始地址:" + i2 + ",图片结束地址:" + j3 + ",所有的图片大小:" + k.length);
                    Convert2RGBA convert2RGBA = Convert2RGBA.this;
                    int length = convert2RGBA.d.length;
                    if (j3 == 0) {
                        j3 = k.length;
                    }
                    byte[] o = convert2RGBA.o(k, length, i2, j3);
                    byte[] bArr4 = Convert2RGBA.this.d;
                    byte[] bArr5 = new byte[bArr4.length + o.length];
                    System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
                    System.arraycopy(o, 0, bArr5, Convert2RGBA.this.d.length, o.length);
                    Convert2RGBA.this.d = bArr5;
                    bArr3[8] = (byte) m.length;
                    bArr3[9] = (byte) (m.length >> 8);
                    bArr3[10] = (byte) (m.length >> 16);
                    bArr3[11] = (byte) (m.length >> 24);
                    if (bArr2 != null) {
                        bArr = new byte[bArr2.length + bArr3.length];
                        b = 0;
                        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
                        System.arraycopy(bArr3, 0, bArr, bArr2.length, bArr3.length);
                        bArr2 = bArr;
                    }
                    bArr2 = bArr3;
                    b = 0;
                } else if (bArr3[b2] == 6) {
                    byte[] bArr6 = new byte[120];
                    System.arraycopy(bArr3, 8, bArr6, b2, 120);
                    int i3 = b2;
                    for (int i4 = 120; i3 < i4; i4 = 120) {
                        int i5 = bArr6[i3] | (bArr6[i3 + 1] << 8) | (bArr6[i3 + 2] << 16) | (bArr6[i3 + 3] << 24);
                        if (i3 >= i4) {
                            j2 = Convert2RGBA.this.j(h, i5);
                        } else {
                            j2 = bArr6[i3 + 4] | (bArr6[i3 + 5] << 8) | (bArr6[i3 + 6] << 16) | (bArr6[i3 + 7] << 24);
                        }
                        Convert2RGBA convert2RGBA2 = Convert2RGBA.this;
                        byte[] bArr7 = bArr6;
                        int length2 = convert2RGBA2.d.length;
                        if (j2 == 0) {
                            j2 = k.length;
                        }
                        byte[] o2 = convert2RGBA2.o(k, length2, i5, j2);
                        byte[] bArr8 = Convert2RGBA.this.d;
                        byte[] bArr9 = new byte[bArr8.length + o2.length];
                        System.arraycopy(bArr8, 0, bArr9, 0, bArr8.length);
                        System.arraycopy(o2, 0, bArr9, Convert2RGBA.this.d.length, o2.length);
                        Convert2RGBA.this.d = bArr9;
                        int i6 = i3 + 8;
                        bArr3[i6] = (byte) bArr9.length;
                        bArr3[i6 + 1] = (byte) bArr9.length;
                        bArr3[i6 + 2] = (byte) bArr9.length;
                        bArr3[i6 + 3] = (byte) bArr9.length;
                        i3 += 4;
                        bArr6 = bArr7;
                        currentTimeMillis = currentTimeMillis;
                    }
                    j = currentTimeMillis;
                    if (bArr2 != null) {
                        bArr = new byte[bArr2.length + bArr3.length];
                        b = 0;
                        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
                        System.arraycopy(bArr3, 0, bArr, bArr2.length, bArr3.length);
                        bArr2 = bArr;
                    }
                    bArr2 = bArr3;
                    b = 0;
                } else {
                    j = currentTimeMillis;
                    byte b3 = b2;
                    if (bArr3[b3] == 3 || bArr3[b3] == 4 || bArr3[b3] == 5) {
                        if (bArr2 != null) {
                            bArr = new byte[bArr2.length + bArr3.length];
                            b = 0;
                            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
                            System.arraycopy(bArr3, 0, bArr, bArr2.length, bArr3.length);
                            bArr2 = bArr;
                        }
                        bArr2 = bArr3;
                    }
                    b = 0;
                }
                i++;
                b2 = b;
                currentTimeMillis = j;
            }
            long j4 = currentTimeMillis;
            byte b4 = b2;
            if (bArr2 != null) {
                System.arraycopy(bArr2, b4, Convert2RGBA.this.c, b4, bArr2.length);
                byte[] bArr10 = new byte[184];
                System.arraycopy(n, b4, bArr10, b4, 184);
                bArr10[b4] = b4;
                bArr10[1] = b4;
                bArr10[2] = b4;
                bArr10[3] = b4;
                byte[] bytes = "5526".getBytes(Charset.forName("UTF-8"));
                bArr10[18] = bytes[b4];
                bArr10[19] = bytes[1];
                bArr10[20] = bytes[2];
                bArr10[21] = bytes[3];
                bArr10[140] = 0;
                bArr10[141] = 0;
                bArr10[142] = 0;
                bArr10[143] = 0;
                Convert2RGBA convert2RGBA3 = Convert2RGBA.this;
                byte[] bArr11 = new byte[convert2RGBA3.c.length + 184 + convert2RGBA3.d.length];
                System.arraycopy(bArr10, 0, bArr11, 0, 184);
                byte[] bArr12 = Convert2RGBA.this.c;
                System.arraycopy(bArr12, 0, bArr11, 184, bArr12.length);
                Convert2RGBA convert2RGBA4 = Convert2RGBA.this;
                byte[] bArr13 = convert2RGBA4.d;
                System.arraycopy(bArr13, 0, bArr11, 184 + convert2RGBA4.c.length, bArr13.length);
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(Convert2RGBA.this.b.getExternalCacheDir().getAbsolutePath());
                    sb2.append("/5526_");
                    String str = rGBAParam.filePath;
                    sb2.append(str.substring(str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR), rGBAParam.filePath.length()));
                    sb = sb2.toString();
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(Convert2RGBA.this.b.getCacheDir().getAbsolutePath());
                    sb3.append("/5526_");
                    String str2 = rGBAParam.filePath;
                    sb3.append(str2.substring(str2.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR), rGBAParam.filePath.length()));
                    sb = sb3.toString();
                }
                String str3 = sb;
                try {
                    Convert2RGBA.this.p(bArr11, str3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e(Convert2RGBA.this.f7889a, " 处理数据完成耗时 " + (System.currentTimeMillis() - j4) + " 豪秒");
                Convert2RGBA convert2RGBA5 = Convert2RGBA.this;
                convert2RGBA5.d = null;
                convert2RGBA5.c = null;
                return str3;
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (this.f7890a != null) {
                String str2 = Convert2RGBA.this.f7889a;
                Log.e(str2, "将bin地址发送给页面:" + str);
                this.f7890a.dialPath(str);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            Convert2RGBA convert2RGBA = Convert2RGBA.this;
            convert2RGBA.c = new byte[8192];
            convert2RGBA.d = null;
        }
    }

    public Convert2RGBA(Context context) {
        this.b = context;
    }

    public void destroy() {
        this.b = null;
        this.d = null;
        this.c = null;
    }

    public void file2RGBA(RGBAParam rGBAParam, CustomDialCallback customDialCallback) {
        if (rGBAParam != null && !TextUtils.isEmpty(rGBAParam.filePath)) {
            new a(customDialCallback).execute(rGBAParam);
        } else if (customDialCallback != null) {
            customDialCallback.dialPath(null);
        }
    }

    public final List<ImageAddressContent> h(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        i(bArr, 0, arrayList);
        return arrayList;
    }

    public final void i(byte[] bArr, int i, List<ImageAddressContent> list) {
        byte[] bArr2;
        int i2 = i + 184;
        byte b = bArr[i2];
        String str = this.f7889a;
        Log.e(str, "传过来的步长:" + i);
        if (b == 1) {
            bArr2 = new byte[12];
            Log.e(this.f7889a, "步长:12,类型为单图");
        } else if (b == 2) {
            bArr2 = new byte[24];
            Log.e(this.f7889a, "步长:24,类型为指针");
        } else if (b == 3) {
            bArr2 = new byte[16];
            Log.e(this.f7889a, "步长:16,类型为圆形进度条");
        } else if (b == 5) {
            bArr2 = new byte[32];
            Log.e(this.f7889a, "步长:32,类型为文字");
        } else if (b == 6) {
            bArr2 = new byte[132];
            Log.e(this.f7889a, "步长:132,类型为多图");
        } else if (b == 7) {
            bArr2 = new byte[400];
            Log.e(this.f7889a, "步长:400,类型为gif");
        } else {
            bArr2 = null;
        }
        if (bArr2 != null) {
            int i3 = 0;
            System.arraycopy(bArr, i2, bArr2, 0, bArr2.length);
            if (b == 1 || b == 2 || b == 6 || b == 7) {
                String str2 = this.f7889a;
                Log.e(str2, "第8个字节:" + Integer.toHexString(bArr2[8] & 255) + ",第9个字节:" + Integer.toHexString(bArr2[9] & 255) + ",第10个字节:" + Integer.toHexString(bArr2[10] & 255) + ",第11个字节:" + Integer.toHexString(bArr2[11] & 255));
                i3 = (bArr2[8] & 255) | ((bArr2[9] & 255) << 8) | ((bArr2[10] & 255) << 16) | ((bArr2[11] & 255) << 24);
                String str3 = this.f7889a;
                StringBuilder sb = new StringBuilder();
                sb.append("开始地址:");
                sb.append(i3);
                Log.e(str3, sb.toString());
            }
            ImageAddressContent imageAddressContent = new ImageAddressContent();
            imageAddressContent.f7894a = bArr2;
            imageAddressContent.b = i3;
            list.add(imageAddressContent);
            i(bArr, i + bArr2.length, list);
        }
    }

    public final int j(List<ImageAddressContent> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            ImageAddressContent imageAddressContent = list.get(i2);
            int i3 = imageAddressContent.b;
            if (i3 != 0 && i3 > i && imageAddressContent.f7894a != null) {
                return i3;
            }
        }
        return 0;
    }

    public final byte[] k(byte[] bArr) {
        int length = (bArr.length - 184) - 8192;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 8376, bArr2, 0, length);
        return bArr2;
    }

    public final byte[] l(byte[] bArr, int i, int i2, boolean z, int i3) {
        int i4 = i2 << 21;
        int i5 = i << 10;
        int i6 = z ? i4 | i5 | 0 | 4 : i4 | i5 | 0 | 21;
        String str = this.f7889a;
        Log.e(str, "图片信息头部值:" + i6);
        byte[] bArr2 = {(byte) i6, (byte) (i6 >> 8), (byte) (i6 >> 16), (byte) (i6 >> 24)};
        String str2 = this.f7889a;
        Log.e(str2, "头部第一个字节:" + Integer.toHexString(bArr2[0] & 255) + ",头部第二个字节:" + Integer.toHexString(bArr2[1] & 255) + ",头部第三个字节:" + Integer.toHexString(bArr2[2] & 255) + ",头部第四个字节" + Integer.toHexString(bArr2[3] & 255));
        byte[] bArr3 = new byte[4];
        int i7 = z ? i2 * i * 4 : i2 * i * 2;
        bArr3[0] = (byte) i7;
        bArr3[1] = (byte) (i7 >> 8);
        bArr3[2] = (byte) (i7 >> 16);
        bArr3[3] = (byte) (i7 >> 24);
        String str3 = this.f7889a;
        Log.e(str3, "图片大小第一个字节:" + Integer.toHexString(bArr3[0] & 255) + ",图片大小第二个字节:" + Integer.toHexString(bArr3[1] & 255) + ",图片大小第三个字节:" + Integer.toHexString(bArr3[2] & 255) + ",图片大小第四个字节" + Integer.toHexString(bArr3[3] & 255));
        int i8 = i3 + 20;
        byte[] bArr4 = {(byte) i8, (byte) (i8 >> 8), (byte) (i8 >> 16), (byte) (i8 >> 24)};
        String str4 = this.f7889a;
        Log.e(str4, "偏移第一个字节:" + Integer.toHexString(bArr4[0] & 255) + ",偏移第二个字节:" + Integer.toHexString(bArr4[1] & 255) + ",偏移第三个字节:" + Integer.toHexString(bArr4[2] & 255) + ",偏移第四个字节" + Integer.toHexString(bArr4[3] & 255));
        byte[] bArr5 = new byte[20];
        System.arraycopy(bArr2, 0, bArr5, 0, 4);
        System.arraycopy(bArr3, 0, bArr5, 4, 4);
        System.arraycopy(bArr4, 0, bArr5, 8, 4);
        byte[] bArr6 = new byte[bArr.length + 20];
        System.arraycopy(bArr5, 0, bArr6, 0, 20);
        System.arraycopy(bArr, 0, bArr6, 20, bArr.length);
        return bArr6;
    }

    public final byte[] m(byte[] bArr, List<ImageAddressContent> list, byte[] bArr2, int i, int i2) {
        byte[] bArr3;
        int i3 = ((bArr[143] & 255) << 24) | (bArr[140] & 255) | ((bArr[141] & 255) << 8) | ((bArr[142] & 255) << 16);
        int j = j(list, i3);
        int i4 = 0;
        if (j == 0) {
            bArr3 = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        } else {
            int i5 = j - i3;
            byte[] bArr4 = new byte[i5];
            System.arraycopy(bArr2, i3, bArr4, 0, i5);
            bArr3 = bArr4;
        }
        byte[] bArr5 = new byte[i * i * 2];
        if (bArr3.length == i2 * i * 2) {
            int i6 = 0;
            while (i4 < bArr3.length) {
                bArr5[i6] = bArr3[i4 + 1];
                bArr5[i6 + 1] = bArr3[i4];
                i6 += 2;
                i4 += 2;
            }
        } else {
            int i7 = 0;
            while (i4 < bArr3.length) {
                bArr5[i7] = bArr3[i4 + 1];
                bArr5[i7 + 1] = bArr3[i4];
                i7 += 2;
                i4 += 3;
            }
        }
        return l(bArr5, i, i2, false, 0);
    }

    public final byte[] n(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] bArr = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        fileInputStream.close();
                        byteArrayOutputStream.close();
                        return byteArray;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final byte[] o(byte[] bArr, int i, int i2, int i3) {
        int i4 = i3 - i2;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i2, bArr2, 0, i4);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, i4);
        if (decodeByteArray != null) {
            byte[] bArr3 = new byte[decodeByteArray.getWidth() * decodeByteArray.getWidth() * 4];
            int width = decodeByteArray.getWidth() * decodeByteArray.getHeight();
            int[] iArr = new int[width];
            decodeByteArray.getPixels(iArr, 0, decodeByteArray.getWidth(), 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight());
            int i5 = 0;
            for (int i6 = 0; i6 < width; i6++) {
                int i7 = iArr[i6];
                bArr3[i5] = (byte) ((((63488 & i7) >> 11) << 3) | ((i7 & 896) >> 11));
                bArr3[i5 + 1] = (byte) ((((i7 & 126) >> 5) << 2) | ((i7 & 96) >> 5));
                bArr3[i5 + 2] = (byte) (((i7 & 31) << 3) | (i7 & 7));
                bArr3[i5 + 3] = (byte) Color.alpha(i7);
                i5 += 4;
            }
            return l(bArr3, decodeByteArray.getWidth(), decodeByteArray.getHeight(), true, i);
        }
        Log.e(this.f7889a, "数组生成的bitmap不存在");
        return null;
    }

    public final void p(byte[] bArr, String str) throws Exception {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str), true);
        fileOutputStream.write(bArr);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
