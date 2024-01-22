package com.example.custom_dial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.custom_dial.ImageHeadUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class RGBAPlatformDiffTxtUtils {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7896a;
    public byte[] b;
    public Context d;
    public List<Bitmap> e;
    public Bitmap f;
    public boolean h;
    public Typeface i;
    public boolean j;
    public final String c = getClass().getSimpleName();
    public String[] k = {"240x240", "240x280", "356x400", "368x448", "466x466"};
    public ImageTransformUtils g = new ImageTransformUtils();

    /* loaded from: classes9.dex */
    public class a extends AsyncTask<CustomDiffTxtColorDialParam, Void, String> {

        /* renamed from: a  reason: collision with root package name */
        public CustomDialCallback f7897a;

        public a(CustomDialCallback customDialCallback) {
            this.f7897a = customDialCallback;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public String doInBackground(CustomDiffTxtColorDialParam... customDiffTxtColorDialParamArr) {
            String str;
            CustomDiffTxtColorDialParam customDiffTxtColorDialParam = customDiffTxtColorDialParamArr[0];
            long currentTimeMillis = System.currentTimeMillis();
            Log.e(RGBAPlatformDiffTxtUtils.this.c, "radius:" + customDiffTxtColorDialParam.getCornerRadius());
            Bitmap l = RGBAPlatformDiffTxtUtils.this.l(customDiffTxtColorDialParam.getBackBitmap(), customDiffTxtColorDialParam.getScreenWidth(), customDiffTxtColorDialParam.getScreenHigh(), customDiffTxtColorDialParam.getCornerRadius(), customDiffTxtColorDialParam.getScreenType(), customDiffTxtColorDialParam.getStartX(), customDiffTxtColorDialParam.getStartY(), customDiffTxtColorDialParam.getWx(), customDiffTxtColorDialParam.getWy(), customDiffTxtColorDialParam.getTxtColor(), customDiffTxtColorDialParam.getDateX(), customDiffTxtColorDialParam.getpWidth(), customDiffTxtColorDialParam.getpHigh());
            if (customDiffTxtColorDialParam.getScreenType() == 1) {
                customDiffTxtColorDialParam.setBackBitmap(RGBAPlatformDiffTxtUtils.this.o(customDiffTxtColorDialParam.getBackBitmap(), customDiffTxtColorDialParam.getScreenWidth(), customDiffTxtColorDialParam.getScreenHigh()));
            }
            RGBAPlatformDiffTxtUtils.this.f(l, customDiffTxtColorDialParam.getBackBitmap(), customDiffTxtColorDialParam.getScreenWidth(), customDiffTxtColorDialParam.getScreenHigh(), customDiffTxtColorDialParam.getScreenType() == 1, customDiffTxtColorDialParam.getTxtColor(), customDiffTxtColorDialParam.getStartX(), customDiffTxtColorDialParam.getStartY(), customDiffTxtColorDialParam.h, customDiffTxtColorDialParam.getWy(), customDiffTxtColorDialParam.getDateX());
            byte[] fixHeaderInfo = new ImageHeadUtils().getFixHeaderInfo(true, ImageHeadUtils.a.timeTxt);
            int length = fixHeaderInfo.length;
            RGBAPlatformDiffTxtUtils rGBAPlatformDiffTxtUtils = RGBAPlatformDiffTxtUtils.this;
            byte[] bArr = new byte[length + rGBAPlatformDiffTxtUtils.f7896a.length + rGBAPlatformDiffTxtUtils.b.length];
            System.arraycopy(fixHeaderInfo, 0, bArr, 0, fixHeaderInfo.length);
            byte[] bArr2 = RGBAPlatformDiffTxtUtils.this.f7896a;
            System.arraycopy(bArr2, 0, bArr, fixHeaderInfo.length, bArr2.length);
            RGBAPlatformDiffTxtUtils rGBAPlatformDiffTxtUtils2 = RGBAPlatformDiffTxtUtils.this;
            byte[] bArr3 = rGBAPlatformDiffTxtUtils2.b;
            System.arraycopy(bArr3, 0, bArr, fixHeaderInfo.length + rGBAPlatformDiffTxtUtils2.f7896a.length, bArr3.length);
            if ("mounted".equals(Environment.getExternalStorageState())) {
                str = RGBAPlatformDiffTxtUtils.this.d.getExternalCacheDir().getAbsolutePath() + "/dial.bin";
            } else {
                str = RGBAPlatformDiffTxtUtils.this.d.getCacheDir().getAbsolutePath() + "/dial.bin";
            }
            String str2 = str;
            try {
                RGBAPlatformDiffTxtUtils.this.w(bArr, str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            Log.e(RGBAPlatformDiffTxtUtils.this.c, " 处理数据完成耗时 " + currentTimeMillis2 + " 豪秒");
            RGBAPlatformDiffTxtUtils rGBAPlatformDiffTxtUtils3 = RGBAPlatformDiffTxtUtils.this;
            rGBAPlatformDiffTxtUtils3.b = null;
            rGBAPlatformDiffTxtUtils3.f7896a = null;
            return str2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (this.f7897a != null) {
                String str2 = RGBAPlatformDiffTxtUtils.this.c;
                Log.e(str2, "将bin地址发送给页面:" + str);
                this.f7897a.dialPath(str);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            RGBAPlatformDiffTxtUtils rGBAPlatformDiffTxtUtils = RGBAPlatformDiffTxtUtils.this;
            rGBAPlatformDiffTxtUtils.f7896a = new byte[8192];
            rGBAPlatformDiffTxtUtils.b = null;
        }
    }

    public RGBAPlatformDiffTxtUtils(Context context, boolean z) {
        this.d = context;
        this.h = z;
    }

    public void destroy() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.f7896a = null;
        this.b = null;
        this.g = null;
    }

    public final byte[] e(int i, int i2, int i3, int i4, int i5, boolean z) {
        byte[] imageConvertRgb565 = this.g.imageConvertRgb565(this.f, z, this.h, this.b.length);
        byte[] p = p(this.b.length, i4, i5);
        System.arraycopy(p, 0, this.f7896a, i + i2 + i3, p.length);
        byte[] bArr = this.b;
        if (bArr == null) {
            this.b = imageConvertRgb565;
        } else {
            byte[] bArr2 = new byte[bArr.length + imageConvertRgb565.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(imageConvertRgb565, 0, bArr2, this.b.length, imageConvertRgb565.length);
            this.b = bArr2;
        }
        return p;
    }

    public final void f(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7, int i8) {
        new BitmapFactory.Options().inScaled = false;
        byte[] imageConvertRgb565 = this.g.imageConvertRgb565(bitmap, false, this.h, 0);
        if (this.b == null) {
            this.b = imageConvertRgb565;
        }
        byte[] n = n(bitmap2, imageConvertRgb565.length, i, i2, true);
        List<byte[]> i9 = i(i4, i5, n.length, true);
        int width = this.e.get(0).getWidth();
        int i10 = i4 + width;
        byte[] j = j(n.length, i9.get(0).length, i9.get(1), i10, i5);
        int i11 = i10 + width;
        byte[] e = e(n.length, i9.get(0).length, j.length, i11, i5, true);
        int width2 = i11 + this.f.getWidth();
        byte[] g = g(n.length, i9.get(0).length, j.length, e.length, i9.get(1), width2, i5);
        byte[] h = h(n.length, i9.get(0).length, j.length, e.length, g.length, i9.get(1), width2 + width, i5);
        if (this.j) {
            k(this.f7896a, n.length, i9.get(0).length, j.length, e.length, g.length, h.length, i3, i, z, i6, i7, i8);
        }
    }

    public final byte[] g(int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6) {
        byte[] q = q(i5, i6);
        System.arraycopy(bArr, 0, q, 128 - bArr.length, bArr.length);
        System.arraycopy(q, 0, this.f7896a, i + i2 + i3 + i4, q.length);
        return q;
    }

    public final byte[] h(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6, int i7) {
        byte[] r = r(i6, i7);
        System.arraycopy(bArr, 0, r, 128 - bArr.length, bArr.length);
        System.arraycopy(r, 0, this.f7896a, i + i2 + i3 + i4 + i5, r.length);
        return r;
    }

    public final List<byte[]> i(int i, int i2, int i3, boolean z) {
        byte[] bArr = new byte[120];
        byte[] bArr2 = null;
        for (int i4 = 0; i4 < this.e.size(); i4++) {
            Bitmap bitmap = this.e.get(i4);
            if (bArr2 == null) {
                bArr2 = t(i, i2);
            }
            byte[] imageConvertRgb565 = this.g.imageConvertRgb565(bitmap, z, this.h, this.b.length);
            byte[] bArr3 = this.b;
            if (bArr3 == null) {
                int i5 = i4 * 4;
                bArr[i5] = 0;
                bArr[i5 + 1] = 0;
                bArr[i5 + 2] = 0;
                bArr[i5 + 3] = 0;
                this.b = imageConvertRgb565;
            } else {
                int i6 = i4 * 4;
                bArr[i6] = (byte) (bArr3.length & 268435455);
                bArr[i6 + 1] = (byte) ((bArr3.length & 268435455) >> 8);
                bArr[i6 + 2] = (byte) ((bArr3.length & 268435455) >> 16);
                bArr[i6 + 3] = (byte) ((bArr3.length & 268435455) >> 24);
                byte[] bArr4 = new byte[bArr3.length + imageConvertRgb565.length];
                System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                System.arraycopy(imageConvertRgb565, 0, bArr4, this.b.length, imageConvertRgb565.length);
                this.b = bArr4;
            }
        }
        System.arraycopy(bArr, 0, bArr2, 8, 120);
        System.arraycopy(bArr2, 0, this.f7896a, i3, bArr2.length);
        ArrayList arrayList = new ArrayList();
        arrayList.add(bArr2);
        arrayList.add(bArr);
        return arrayList;
    }

    public final byte[] j(int i, int i2, byte[] bArr, int i3, int i4) {
        byte[] u = u(i3, i4);
        System.arraycopy(bArr, 0, u, 128 - bArr.length, bArr.length);
        System.arraycopy(u, 0, this.f7896a, i + i2, u.length);
        return u;
    }

    public final void k(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, int i9, int i10, int i11) {
        if (i8 == 240) {
            Log.e(this.c, "走240尺寸");
            if (!z) {
                byte[] s = s(9, i9, i10, 40, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
                int i12 = i + i2 + i3 + i4 + i5 + i6;
                System.arraycopy(s, 0, bArr, i12, s.length);
                byte[] s2 = s(8, i11, i10, 40, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
                System.arraycopy(s2, 0, bArr, i12 + s.length, s2.length);
                return;
            }
            byte[] s3 = s(9, i9, i10, 50, 2, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            int i13 = i + i2 + i3 + i4 + i5 + i6;
            System.arraycopy(s3, 0, bArr, i13, s3.length);
            byte[] s4 = s(8, i11, i10, 30, 2, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            System.arraycopy(s4, 0, bArr, i13 + s3.length, s4.length);
        } else if (i8 == 356) {
            Log.e(this.c, "走356尺寸");
            byte[] s5 = s(9, i9, i10, 100, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            int i14 = i + i2 + i3 + i4 + i5 + i6;
            System.arraycopy(s5, 0, bArr, i14, s5.length);
            byte[] s6 = s(8, i11, i10, 100, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            System.arraycopy(s6, 0, bArr, i14 + s5.length, s6.length);
        } else if (i8 == 368) {
            Log.e(this.c, "走368尺寸");
            byte[] s7 = s(9, i9, i10, 125, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            int i15 = i + i2 + i3 + i4 + i5 + i6;
            System.arraycopy(s7, 0, bArr, i15, s7.length);
            byte[] s8 = s(8, i11, i10, 125, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            System.arraycopy(s8, 0, bArr, i15 + s7.length, s8.length);
        } else if (i8 == 466) {
            Log.e(this.c, "走466尺寸");
            byte[] s9 = s(9, i9, i10, 100, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            int i16 = i + i2 + i3 + i4 + i5 + i6;
            System.arraycopy(s9, 0, bArr, i16, s9.length);
            byte[] s10 = s(8, i11, i10, 100, 4, Color.red(i7), Color.blue(i7), Color.green(i7), i8);
            System.arraycopy(s10, 0, bArr, i16 + s9.length, s10.length);
        }
    }

    public final Bitmap l(Bitmap bitmap, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int width;
        try {
            if (this.h) {
                i13 = i11 % 2;
                i14 = i12 % 2;
            } else {
                i13 = 0;
                i14 = 0;
            }
            Bitmap v = v(i, i2, i5, i6, i7, i8, i4, i9, i10);
            int i15 = i11 - i13;
            int i16 = i12 - i14;
            Bitmap createBitmap = Bitmap.createBitmap(i15, i16, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, i15, i16), new Paint());
            canvas.drawBitmap(v, new Rect(0, 0, v.getWidth(), v.getHeight()), new Rect(0, 0, i15, i16), new Paint());
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setAlpha(200);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(-16711681);
            paint.setStrokeWidth(2.0f);
            if (i4 == 1) {
                if (createBitmap.getWidth() > createBitmap.getHeight()) {
                    width = createBitmap.getHeight();
                } else {
                    width = createBitmap.getWidth();
                }
                Bitmap createBitmap2 = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap2);
                canvas2.drawCircle(width / 2, width / 2, (width / 2) - 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas2.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
                return createBitmap2;
            }
            Bitmap createBitmap3 = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas3 = new Canvas(createBitmap3);
            Rect rect = new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
            float f = i3;
            canvas3.drawRoundRect(new RectF(rect), f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas3.drawBitmap(createBitmap, (Rect) null, rect, paint);
            return createBitmap3;
        } catch (Exception unused) {
            return null;
        }
    }

    public final byte[] m(Bitmap bitmap, int i, int i2, int i3) {
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[2];
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i4 = ((i2 - width) / 2) & (-1);
            bArr[1] = (byte) (i4 >> 8);
            bArr[0] = (byte) i4;
            int i5 = ((i3 - height) / 2) & (-1);
            bArr2[1] = (byte) (i5 >> 8);
            bArr2[0] = (byte) i5;
        }
        int i6 = i & (-1);
        byte[] bArr3 = {(byte) i6, (byte) (i6 >> 8), (byte) (i6 >> 16), (byte) (i6 >> 24)};
        byte[] bArr4 = new byte[12];
        bArr4[0] = 1;
        System.arraycopy(bArr, 0, bArr4, 2, 2);
        System.arraycopy(bArr2, 0, bArr4, 4, 2);
        System.arraycopy(bArr3, 0, bArr4, 8, 4);
        return bArr4;
    }

    public final byte[] n(Bitmap bitmap, int i, int i2, int i3, boolean z) {
        byte[] imageConvertRgb565 = this.g.imageConvertRgb565(bitmap, z, this.h, i);
        byte[] bArr = this.b;
        if (bArr == null) {
            this.b = imageConvertRgb565;
        } else {
            byte[] bArr2 = new byte[bArr.length + imageConvertRgb565.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(imageConvertRgb565, 0, bArr2, this.b.length, imageConvertRgb565.length);
            this.b = bArr2;
        }
        byte[] m = m(bitmap, i, i2, i3);
        System.arraycopy(m, 0, this.f7896a, 0, m.length);
        return m;
    }

    public final Bitmap o(Bitmap bitmap, int i, int i2) {
        int i3;
        int i4;
        int i5;
        try {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(-16711681);
            if (i > i2) {
                i = i2;
            }
            int i6 = 0;
            if (this.h) {
                i6 = i % 2;
                i3 = i % 2;
            } else {
                i3 = 0;
            }
            Bitmap createBitmap = Bitmap.createBitmap(i - i6, i - i3, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawCircle(i4 / 2, i5 / 2, (i4 / 2) - 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final byte[] p(int i, int i2, int i3) {
        byte[] bArr = new byte[12];
        bArr[0] = 1;
        bArr[2] = (byte) i2;
        bArr[3] = (byte) (i2 << 8);
        int i4 = i3 & (-1);
        bArr[4] = (byte) i4;
        bArr[5] = (byte) (i4 >> 8);
        System.arraycopy(new byte[]{(byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)}, 0, bArr, 8, 4);
        return bArr;
    }

    public void produceDialBin(@NonNull CustomDiffTxtColorDialParam customDiffTxtColorDialParam, CustomDialCallback customDialCallback) {
        if (customDiffTxtColorDialParam == null || customDiffTxtColorDialParam.getBackBitmap() == null) {
            if (customDialCallback != null) {
                customDialCallback.dialPath(null);
                return;
            }
            return;
        }
        if (Arrays.asList(this.k).contains(customDiffTxtColorDialParam.getScreenWidth() + "x" + customDiffTxtColorDialParam.getScreenHigh())) {
            new a(customDialCallback).execute(customDiffTxtColorDialParam);
        } else if (customDialCallback != null) {
            customDialCallback.dialPath(null);
        }
    }

    public Bitmap produceDialThumbnail(Bitmap bitmap, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        int i11;
        int i12;
        int width;
        if (!Arrays.asList(this.k).contains(i + "x" + i2)) {
            Log.e(this.c, "不支持的屏幕尺寸");
            return null;
        }
        try {
            if (this.h) {
                i11 = i % 2;
                i12 = i2 % 2;
            } else {
                i11 = 0;
                i12 = 0;
            }
            Bitmap v = v(i, i2, i5, i6, i7, i8, i4, i9, i10);
            int i13 = i - i11;
            int i14 = i2 - i12;
            Bitmap createBitmap = Bitmap.createBitmap(i13, i14, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, i13, i14), new Paint());
            canvas.drawBitmap(v, new Rect(0, 0, v.getWidth(), v.getHeight()), new Rect(0, 0, i13, i14), new Paint());
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setAlpha(200);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(-16711681);
            if (i4 == 1) {
                if (createBitmap.getWidth() > createBitmap.getHeight()) {
                    width = createBitmap.getHeight();
                } else {
                    width = createBitmap.getWidth();
                }
                Bitmap createBitmap2 = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap2);
                canvas2.drawCircle(width / 2, width / 2, (width / 2) - 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas2.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
                return createBitmap2;
            }
            Bitmap createBitmap3 = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas3 = new Canvas(createBitmap3);
            Rect rect = new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
            float f = i3;
            canvas3.drawRoundRect(new RectF(rect), f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas3.drawBitmap(createBitmap, (Rect) null, rect, paint);
            return createBitmap3;
        } catch (Exception unused) {
            return null;
        }
    }

    public final byte[] q(int i, int i2) {
        byte[] bArr = new byte[132];
        bArr[0] = 6;
        bArr[1] = 3;
        bArr[2] = (byte) i;
        bArr[3] = (byte) (i >> 8);
        int i3 = i2 & (-1);
        bArr[4] = (byte) i3;
        bArr[5] = (byte) (i3 >> 8);
        return bArr;
    }

    public final byte[] r(int i, int i2) {
        byte[] bArr = new byte[132];
        bArr[0] = 6;
        bArr[1] = 4;
        bArr[2] = (byte) i;
        bArr[3] = (byte) (i >> 8);
        int i3 = i2 & (-1);
        bArr[4] = (byte) i3;
        bArr[5] = (byte) (i3 >> 8);
        return bArr;
    }

    public final byte[] s(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        byte[] bArr = new byte[32];
        bArr[0] = 5;
        bArr[1] = (byte) i;
        int i10 = i2 & (-1);
        bArr[2] = (byte) i10;
        bArr[3] = (byte) (i10 >> 8);
        int i11 = i3 & (-1);
        bArr[4] = (byte) i11;
        bArr[5] = (byte) (i11 >> 8);
        bArr[6] = (byte) (i4 & (-1));
        if (i9 != 240) {
            if (i9 == 356) {
                bArr[4] = 95;
                bArr[5] = 0;
                bArr[6] = 100;
            } else if (i9 == 368) {
                bArr[4] = 95;
                bArr[5] = 0;
                bArr[6] = com.crrepa.c.a.h1;
            }
        }
        bArr[7] = (byte) (i6 & (-1));
        bArr[8] = (byte) (i8 & (-1));
        bArr[9] = (byte) (i7 & (-1));
        bArr[10] = 2;
        bArr[11] = (byte) (i5 & (-1));
        return bArr;
    }

    public void setTypeface(Typeface typeface) {
        this.i = typeface;
    }

    public void showData(boolean z) {
        this.j = z;
    }

    public final byte[] t(int i, int i2) {
        byte[] bArr = new byte[132];
        bArr[0] = 6;
        bArr[1] = 1;
        bArr[2] = (byte) i;
        bArr[3] = (byte) (i >> 8);
        int i3 = i2 & (-1);
        bArr[4] = (byte) i3;
        bArr[5] = (byte) (i3 >> 8);
        return bArr;
    }

    public final byte[] u(int i, int i2) {
        byte[] bArr = new byte[132];
        bArr[0] = 6;
        bArr[1] = 2;
        bArr[2] = (byte) i;
        bArr[3] = (byte) (i >> 8);
        int i3 = i2 & (-1);
        bArr[4] = (byte) i3;
        bArr[5] = (byte) (i3 >> 8);
        return bArr;
    }

    public final Bitmap v(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        if (i2 > 0 && i2 > 0 && i3 >= 0 && i4 >= 0 && i3 < i && i4 < i2 && i5 < i && i6 < i2 && i9 < i) {
            DrawTxtUtils drawTxtUtils = new DrawTxtUtils();
            Map<Integer, Bitmap> drawNumBitmap = drawTxtUtils.drawNumBitmap(i, i8, i7, this.i);
            if (drawNumBitmap == null || drawNumBitmap.isEmpty()) {
                return null;
            }
            this.e = new ArrayList();
            for (Integer num : drawNumBitmap.keySet()) {
                this.e.add(drawNumBitmap.get(num));
            }
            this.f = drawTxtUtils.drawColonBitmap(i, i8, i7, this.i);
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            int width = this.e.get(0).getWidth();
            this.e.get(0).getHeight();
            int width2 = this.f.getWidth();
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            float f = i4;
            canvas.drawBitmap(drawNumBitmap.get(1), i3, f, paint);
            canvas.drawBitmap(drawNumBitmap.get(0), i3 + width, f, paint);
            canvas.drawBitmap(this.f, (width * 2) + i3, f, paint);
            canvas.drawBitmap(drawNumBitmap.get(0), i10 + width2, f, paint);
            canvas.drawBitmap(drawNumBitmap.get(8), i3 + (width * 3) + width2, f, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(i8);
            if (this.j) {
                int i11 = i == 240 ? 16 : (i == 356 || i == 368) ? 40 : i == 466 ? 36 : 0;
                Log.e(this.c, "绘制的文字大小:" + i11);
                paint.setTextSize((float) i11);
                float f2 = (float) (i6 + 18);
                canvas.drawText("WED", (float) i5, f2, paint);
                canvas.drawText(BleConst.GetAutomaticHRMonitoring, i9 + 18, f2, paint);
            }
            drawNumBitmap.clear();
            return createBitmap;
        }
        throw new IllegalArgumentException("参数非法");
    }

    public final void w(byte[] bArr, String str) throws Exception {
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
