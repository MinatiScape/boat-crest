package com.htsmart.wristband2.dial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.htsmart.wristband2.dial.DialDrawer;
import com.htsmart.wristband2.dial.DialWriter;
import com.htsmart.wristband2.utils.WristbandLog;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class WriteGUI {

    /* renamed from: a  reason: collision with root package name */
    public static byte[] f12014a = new byte[512];
    public static int[] b = new int[256];
    public static int[] c = new int[256];
    public static int[] d = new int[256];
    public static int[] e = new int[256];
    public static int[] f = new int[256];
    public static int[] g = new int[256];
    public static int[] h = new int[256];
    public static int[] i = new int[256];
    public static int[] j = new int[256];

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public short f12015a;
        public short b;
        public int c;
        public int d;
        public int e;
        public int f;

        public static a b(byte[] bArr, int i) {
            a aVar = new a();
            aVar.f12015a = DialWriter.g(bArr, i);
            int i2 = i + 1;
            aVar.b = DialWriter.g(bArr, i2);
            int i3 = i2 + 1;
            aVar.c = DialWriter.h(bArr, i3);
            int i4 = i3 + 2;
            aVar.d = DialWriter.h(bArr, i4);
            int i5 = i4 + 2;
            aVar.e = DialWriter.h(bArr, i5);
            aVar.f = DialWriter.h(bArr, i5 + 2);
            return aVar;
        }

        public String toString() {
            return "Module_t{enable=" + ((int) this.f12015a) + ", style_number=" + ((int) this.b) + ", x=" + this.c + ", y=" + this.d + ", width=" + this.e + ", height=" + this.f + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public short f12016a;
        public short b;
        public short c;
        public short d;
        public short e;
        public short f;
        public short g;
        public short h;
        public int i;
        public short j;
        public short k;
        public short l;
        public short m;
        public short n;
        public short o;
        public int[] p;

        public static b b(RandomAccessFile randomAccessFile) throws IOException {
            b bVar = new b();
            byte[] bArr = new byte[32];
            randomAccessFile.read(bArr);
            bVar.f12016a = DialWriter.g(bArr, 0);
            bVar.b = DialWriter.g(bArr, 1);
            bVar.c = DialWriter.g(bArr, 2);
            bVar.d = DialWriter.g(bArr, 3);
            bVar.e = (short) DialWriter.h(bArr, 4);
            bVar.f = (short) DialWriter.h(bArr, 6);
            bVar.g = (short) DialWriter.h(bArr, 8);
            bVar.h = (short) DialWriter.h(bArr, 10);
            bVar.i = DialWriter.h(bArr, 12);
            bVar.j = DialWriter.g(bArr, 14);
            bVar.k = DialWriter.g(bArr, 15);
            bVar.l = DialWriter.g(bArr, 16);
            bVar.m = DialWriter.g(bArr, 17);
            bVar.n = DialWriter.g(bArr, 18);
            bVar.o = DialWriter.g(bArr, 19);
            int[] iArr = new int[6];
            bVar.p = iArr;
            iArr[0] = DialWriter.h(bArr, 20);
            bVar.p[1] = DialWriter.h(bArr, 22);
            bVar.p[2] = DialWriter.h(bArr, 24);
            bVar.p[3] = DialWriter.h(bArr, 26);
            bVar.p[4] = DialWriter.h(bArr, 28);
            bVar.p[5] = DialWriter.h(bArr, 30);
            return bVar;
        }

        public String toString() {
            return "WatchFaceControl_t{index=" + ((int) this.f12016a) + ", control=" + ((int) this.b) + ", enable=" + ((int) this.c) + ", type=" + ((int) this.d) + ", x=" + ((int) this.e) + ", y=" + ((int) this.f) + ", union_x0_width_x_add=" + ((int) this.g) + ", union_y0_height_y_add=" + ((int) this.h) + ", Imageid=" + this.i + ", align=" + ((int) this.j) + ", union_time_style_number_language=" + ((int) this.k) + ", number=" + ((int) this.l) + ", module=" + ((int) this.m) + ", style=" + ((int) this.n) + ", reserved=" + ((int) this.o) + ", styleId=" + Arrays.toString(this.p) + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public long f12017a;
        public int b;
        public int c;
        public long d;
        public int e;
        public int f;
        public long g;
        public int h;
        public int i;
        public int j;
        public int k;
        public long l;
        public int m;
        public short n;
        public short o;
        public a[] p;

        public static c b(RandomAccessFile randomAccessFile) throws IOException {
            c cVar = new c();
            byte[] bArr = new byte[76];
            randomAccessFile.read(bArr);
            cVar.f12017a = DialWriter.b(bArr, 0);
            cVar.b = DialWriter.h(bArr, 4);
            cVar.c = DialWriter.h(bArr, 6);
            cVar.d = DialWriter.b(bArr, 8);
            cVar.e = DialWriter.h(bArr, 12);
            cVar.f = DialWriter.h(bArr, 14);
            cVar.g = DialWriter.b(bArr, 16);
            cVar.h = DialWriter.h(bArr, 20);
            cVar.i = DialWriter.h(bArr, 22);
            cVar.j = DialWriter.h(bArr, 24);
            cVar.k = DialWriter.h(bArr, 26);
            cVar.l = DialWriter.b(bArr, 28);
            cVar.m = DialWriter.h(bArr, 32);
            cVar.n = DialWriter.g(bArr, 34);
            cVar.o = DialWriter.g(bArr, 35);
            cVar.p = new a[]{a.b(bArr, 36), a.b(bArr, 46), a.b(bArr, 56), a.b(bArr, 66)};
            return cVar;
        }

        public String toString() {
            return "WatchFaceOffset_t{control_offset=" + this.f12017a + ", control_number=" + this.b + ", control_size=" + this.c + ", bitmap_index_offset=" + this.d + ", bitmap_index_number=" + this.e + ", bitmap_index_size=" + this.f + ", bm_font_index_offset=" + this.g + ", bm_font_index_number=" + this.h + ", bm_font_ascii_number=" + this.i + ", bm_font_index_size=" + this.j + ", reserved=" + this.k + ", bitmap_data_offset=" + this.l + ", preview_id=" + this.m + ", is_have_module=" + ((int) this.n) + ", module_number=" + ((int) this.o) + '}';
        }
    }

    public static int a(int i2, byte b2, byte b3, byte b4) {
        if (i2 > 256) {
            int i3 = b2 & 255;
            int i4 = (i3 >> 3) << 16;
            int i5 = b3 & 255;
            int i6 = b4 & 255;
            int i7 = i6 >> 3;
            int i8 = ((i5 >> 2) << 8) | i4 | i7;
            for (int i9 = 0; i9 < 256; i9++) {
                if (i8 == b[i9]) {
                    return i9;
                }
            }
            int i10 = (i5 >> 3) << 8;
            int i11 = i4 | i10 | i7;
            for (int i12 = 0; i12 < 256; i12++) {
                if (i11 == c[i12]) {
                    return i12;
                }
            }
            int i13 = (i3 >> 4) << 16;
            int i14 = i6 >> 4;
            int i15 = i10 | i13 | i14;
            for (int i16 = 0; i16 < 256; i16++) {
                if (i15 == d[i16]) {
                    return i16;
                }
            }
            int i17 = (i5 >> 4) << 8;
            int i18 = i13 | i17 | i14;
            for (int i19 = 0; i19 < 256; i19++) {
                if (i18 == e[i19]) {
                    return i19;
                }
            }
            int i20 = (i3 >> 5) << 16;
            int i21 = i6 >> 5;
            int i22 = i17 | i20 | i21;
            for (int i23 = 0; i23 < 256; i23++) {
                if (i22 == f[i23]) {
                    return i23;
                }
            }
            int i24 = (i5 >> 5) << 8;
            int i25 = i20 | i24 | i21;
            for (int i26 = 0; i26 < 256; i26++) {
                if (i25 == g[i26]) {
                    return i26;
                }
            }
            int i27 = (i3 >> 6) << 16;
            int i28 = i6 >> 6;
            int i29 = i24 | i27 | i28;
            for (int i30 = 0; i30 < 256; i30++) {
                if (i29 == h[i30]) {
                    return i30;
                }
            }
            int i31 = i27 | ((i5 >> 6) << 8) | i28;
            for (int i32 = 0; i32 < 256; i32++) {
                if (i31 == i[i32]) {
                    return i32;
                }
            }
            int i33 = ((i3 >> 7) << 16) | ((i5 >> 7) << 8) | (i6 >> 7);
            for (int i34 = 0; i34 < 256; i34++) {
                if (i33 == j[i34]) {
                    return i34;
                }
            }
        } else {
            int i35 = (((b2 & 255) >> 3) << 16) | (((b3 & 255) >> 2) << 8) | ((b4 & 255) >> 3);
            for (int i36 = 0; i36 < i2; i36++) {
                if (i35 == b[i36]) {
                    return i36;
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x00f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(android.graphics.Bitmap r26) throws com.htsmart.wristband2.dial.DialWriter.WriterException {
        /*
            Method dump skipped, instructions count: 927
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.dial.WriteGUI.b(android.graphics.Bitmap):int");
    }

    public static void c(RandomAccessFile randomAccessFile, Bitmap bitmap, Bitmap bitmap2, DialDrawer.Position position, boolean z, boolean z2, int i2) throws IOException, DialWriter.WriterException {
        int i3;
        int i4;
        int i5;
        a aVar;
        int i6;
        DialWriter.a aVar2;
        String str;
        Bitmap bitmap3;
        boolean z3;
        DialWriter.a aVar3;
        c cVar;
        int i7;
        long j2;
        int i8;
        int i9;
        Bitmap createBitmap;
        Canvas canvas;
        Paint paint;
        int i10;
        int i11;
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        byte b6;
        byte b7;
        DialDrawer.Position position2 = position;
        int i12 = i2;
        byte[] bArr = new byte[64];
        randomAccessFile.seek(1552L);
        randomAccessFile.read(bArr, 0, 4);
        long b8 = DialWriter.b(bArr, 0);
        randomAccessFile.seek(1568L);
        randomAccessFile.read(bArr, 0, 4);
        char c2 = 2;
        WristbandLog.i("addr_offset:" + b8 + "  lcd_width:" + DialWriter.h(bArr, 0) + "  lcd_height:" + DialWriter.h(bArr, 2), new Object[0]);
        long j3 = 1536;
        long j4 = b8 + 1536;
        randomAccessFile.seek(j4);
        c b9 = c.b(randomAccessFile);
        WristbandLog.i(b9.toString(), new Object[0]);
        WristbandLog.i(b9.p[0].toString(), new Object[0]);
        short s = 1;
        WristbandLog.i(b9.p[1].toString(), new Object[0]);
        WristbandLog.i(b9.p[2].toString(), new Object[0]);
        WristbandLog.i(b9.p[3].toString(), new Object[0]);
        int i13 = 0;
        int i14 = 0;
        while (i13 < b9.b) {
            int i15 = i14;
            long j5 = b9.f12017a + (b9.c * i13) + j3;
            randomAccessFile.seek(j5);
            b b10 = b.b(randomAccessFile);
            WristbandLog.i(b10.toString(), new Object[0]);
            short s2 = b10.b;
            if (s2 == 0) {
                if (b10.m == 0) {
                    aVar2 = DialWriter.a.BACKGROUND;
                    str = "background";
                    bitmap3 = bitmap;
                    z3 = z;
                    aVar3 = aVar2;
                }
                str = "";
                bitmap3 = null;
                aVar3 = null;
                z3 = false;
            } else {
                if (s2 == 6) {
                    if (b10.d == s) {
                        aVar2 = DialWriter.a.PREVIEW;
                        str = "preview";
                        bitmap3 = bitmap2;
                        z3 = z2;
                        aVar3 = aVar2;
                    }
                } else if (s2 == 9 && b9.o == s) {
                    if (position2 == DialDrawer.Position.TOP) {
                        a[] aVarArr = b9.p;
                        i5 = aVarArr[s].c;
                        aVar = aVarArr[s];
                    } else if (position2 == DialDrawer.Position.BOTTOM) {
                        a[] aVarArr2 = b9.p;
                        i4 = aVarArr2[0].c;
                        i6 = aVarArr2[0].d;
                        i3 = i6;
                        b10.e = (short) i4;
                        b10.f = (short) i3;
                        a[] aVarArr3 = b9.p;
                        aVarArr3[0].c = i4;
                        aVarArr3[0].d = i3;
                        bArr[0] = (byte) i4;
                        bArr[1] = (byte) (i4 >> 8);
                        bArr[2] = (byte) i3;
                        bArr[3] = (byte) (i3 >> 8);
                        randomAccessFile.seek(38 + j4);
                        randomAccessFile.write(bArr, 0, 4);
                        randomAccessFile.seek(j5 + 4);
                        randomAccessFile.write(bArr, 0, 4);
                    } else if (position2 == DialDrawer.Position.LEFT) {
                        a[] aVarArr4 = b9.p;
                        i5 = aVarArr4[c2].c;
                        aVar = aVarArr4[c2];
                    } else {
                        if (position2 == DialDrawer.Position.RIGHT) {
                            a[] aVarArr5 = b9.p;
                            i4 = (short) aVarArr5[3].c;
                            i3 = (short) aVarArr5[3].d;
                        } else {
                            i3 = 0;
                            i4 = 0;
                        }
                        b10.e = (short) i4;
                        b10.f = (short) i3;
                        a[] aVarArr32 = b9.p;
                        aVarArr32[0].c = i4;
                        aVarArr32[0].d = i3;
                        bArr[0] = (byte) i4;
                        bArr[1] = (byte) (i4 >> 8);
                        bArr[2] = (byte) i3;
                        bArr[3] = (byte) (i3 >> 8);
                        randomAccessFile.seek(38 + j4);
                        randomAccessFile.write(bArr, 0, 4);
                        randomAccessFile.seek(j5 + 4);
                        randomAccessFile.write(bArr, 0, 4);
                    }
                    i4 = i5;
                    i6 = aVar.d;
                    i3 = i6;
                    b10.e = (short) i4;
                    b10.f = (short) i3;
                    a[] aVarArr322 = b9.p;
                    aVarArr322[0].c = i4;
                    aVarArr322[0].d = i3;
                    bArr[0] = (byte) i4;
                    bArr[1] = (byte) (i4 >> 8);
                    bArr[2] = (byte) i3;
                    bArr[3] = (byte) (i3 >> 8);
                    randomAccessFile.seek(38 + j4);
                    randomAccessFile.write(bArr, 0, 4);
                    randomAccessFile.seek(j5 + 4);
                    randomAccessFile.write(bArr, 0, 4);
                }
                str = "";
                bitmap3 = null;
                aVar3 = null;
                z3 = false;
            }
            long j6 = j4;
            if (bitmap3 != null) {
                i7 = i13;
                randomAccessFile.seek(b9.d + (b10.i * 12) + 2 + 1536);
                randomAccessFile.read(bArr, 0, 10);
                short g2 = DialWriter.g(bArr, 0);
                int h2 = DialWriter.h(bArr, 2);
                int h3 = DialWriter.h(bArr, 4);
                long b11 = DialWriter.b(bArr, 6);
                WristbandLog.i("bitmap_bpp=%d bitmap_width=%d bitmap_height=%d bitmap_address=%d", Short.valueOf(g2), Integer.valueOf(h2), Integer.valueOf(h3), Long.valueOf(b11));
                int width = bitmap3.getWidth();
                int height = bitmap3.getHeight();
                WristbandLog.i("modifyBitmap[%s] bitmap size[%d,%d]", str, Integer.valueOf(width), Integer.valueOf(height));
                boolean z4 = h2 == width && h3 == height;
                Bitmap.Config config = bitmap3.getConfig();
                cVar = b9;
                Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
                boolean z5 = config == config2;
                if (!z4 || !z5) {
                    if (z5 && !z3) {
                        throw new DialWriter.WriterException(aVar3 == DialWriter.a.BACKGROUND ? 3 : 5);
                    }
                    try {
                        createBitmap = Bitmap.createBitmap(h2, h3, config2);
                        canvas = new Canvas(createBitmap);
                        float f2 = h2;
                        float f3 = width;
                        float f4 = h3;
                        float f5 = height;
                        float max = Math.max(f2 / f3, f4 / f5);
                        canvas.scale(max, max);
                        canvas.translate((f2 - (f3 * max)) / 2.0f, (f4 - (max * f5)) / 2.0f);
                        paint = new Paint();
                    } catch (Exception e2) {
                        e = e2;
                        i8 = 4;
                        i9 = 1;
                    }
                    try {
                        paint.setFilterBitmap(true);
                        canvas.drawBitmap(bitmap3, new Matrix(), paint);
                        bitmap3 = createBitmap;
                    } catch (Exception e3) {
                        e = e3;
                        i8 = 4;
                        i9 = 1;
                        Object[] objArr = new Object[i9];
                        objArr[0] = str;
                        WristbandLog.w(e, "Scale Bitmap[%s] failed", objArr);
                        throw new DialWriter.WriterException(aVar3 == DialWriter.a.BACKGROUND ? 2 : i8);
                    }
                }
                if (g2 == 8) {
                    i11 = b(bitmap3);
                    WristbandLog.i("color_number=%d", Integer.valueOf(i11));
                    randomAccessFile.seek(b11 + ((h3 + 1) * 4) + 1536);
                    int i16 = i11 > 256 ? 256 : i11;
                    bArr[0] = (byte) (i16 & 255);
                    bArr[1] = (byte) ((i16 >> 8) & 255);
                    bArr[2] = 0;
                    bArr[3] = 0;
                    randomAccessFile.write(bArr, 0, 4);
                    randomAccessFile.write(f12014a, 0, i16 * 2);
                    i10 = 4;
                    WristbandLog.i("buff={%d,%d,%d,%d,%d}", Integer.valueOf(i16), Integer.valueOf(bArr[0] & 255), Integer.valueOf(bArr[1] & 255), Integer.valueOf(bArr[2] & 255), Integer.valueOf(bArr[3] & 255));
                } else {
                    i10 = 4;
                    i11 = i15;
                }
                byte[] bArr2 = new byte[i10];
                byte[] bArr3 = new byte[2000];
                if (g2 == 1) {
                    for (int i17 = 0; i17 < h3; i17++) {
                        for (int i18 = 0; i18 < h2; i18++) {
                            int pixel = bitmap3.getPixel(i18, i17);
                            bArr2[0] = (byte) (pixel & 255);
                            bArr2[1] = (byte) ((pixel >> 8) & 255);
                            bArr2[2] = (byte) ((pixel >> 16) & 255);
                            bArr2[3] = (byte) ((pixel >> 24) & 255);
                            if ((bArr2[3] & 255) > 90) {
                                byte b12 = bArr2[0];
                                byte b13 = bArr2[1];
                                b7 = b12;
                                b6 = bArr2[2];
                                b5 = b13;
                            } else {
                                b5 = 0;
                                b6 = 0;
                                b7 = 0;
                            }
                            int i19 = i18 * 2;
                            bArr3[i19] = (byte) (((b5 & 255) >> 5) | (b6 & 248));
                            bArr3[i19 + 1] = (byte) (((b5 << 3) & 224) | ((b7 & 255) >> 3));
                        }
                        randomAccessFile.seek(b11 + (i17 * 4) + 1536);
                        randomAccessFile.read(bArr, 0, 4);
                        randomAccessFile.seek(DialWriter.b(bArr, 0) + 1536);
                        randomAccessFile.write(bArr3, 0, h2 * 2);
                    }
                } else if (g2 == 8) {
                    for (int i20 = 0; i20 < h3; i20++) {
                        for (int i21 = 0; i21 < h2; i21++) {
                            int pixel2 = bitmap3.getPixel(i21, i20);
                            bArr2[0] = (byte) (pixel2 & 255);
                            bArr2[1] = (byte) ((pixel2 >> 8) & 255);
                            bArr2[2] = (byte) ((pixel2 >> 16) & 255);
                            bArr2[3] = (byte) ((pixel2 >> 24) & 255);
                            if ((bArr2[3] & 255) > 90) {
                                b3 = bArr2[0];
                                byte b14 = bArr2[1];
                                b4 = bArr2[2];
                                b2 = b14;
                            } else {
                                b2 = 0;
                                b3 = 0;
                                b4 = 0;
                            }
                            bArr3[i21] = (byte) a(i11, b3, b2, b4);
                        }
                        randomAccessFile.seek(b11 + (i20 * 4) + 1536);
                        randomAccessFile.read(bArr, 0, 4);
                        randomAccessFile.seek(DialWriter.b(bArr, 0) + 1536);
                        randomAccessFile.write(bArr3, 0, h2);
                    }
                }
                j2 = 1536;
                i14 = i11;
            } else {
                cVar = b9;
                i7 = i13;
                j2 = 1536;
                i14 = i15;
            }
            i13 = i7 + 1;
            position2 = position;
            i12 = i2;
            j3 = j2;
            b9 = cVar;
            j4 = j6;
            c2 = 2;
            s = 1;
        }
        int i22 = i12;
        if (i22 > 0) {
            randomAccessFile.seek(1544L);
            bArr[0] = (byte) (i22 & 255);
            bArr[1] = (byte) ((i22 >> 8) & 255);
            bArr[2] = (byte) ((i22 >> 16) & 255);
            randomAccessFile.write(bArr, 0, 3);
        }
        DialWriter.d(randomAccessFile);
    }
}
