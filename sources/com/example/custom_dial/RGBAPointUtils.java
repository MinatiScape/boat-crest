package com.example.custom_dial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.example.custom_dial.ImageHeadUtils;
import java.io.File;
import java.io.FileOutputStream;
/* loaded from: classes9.dex */
public class RGBAPointUtils {
    public byte[] b;
    public byte[] c;
    public Context d;
    public boolean f;

    /* renamed from: a  reason: collision with root package name */
    public final String f7898a = getClass().getSimpleName();
    public ImageTransformUtils e = new ImageTransformUtils();

    /* loaded from: classes9.dex */
    public class a extends AsyncTask<CustomPointDialParam, Void, String> {

        /* renamed from: a  reason: collision with root package name */
        public CustomDialCallback f7899a;

        public a(CustomDialCallback customDialCallback) {
            this.f7899a = customDialCallback;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public String doInBackground(CustomPointDialParam... customPointDialParamArr) {
            String str;
            CustomPointDialParam customPointDialParam = customPointDialParamArr[0];
            long currentTimeMillis = System.currentTimeMillis();
            Bitmap j = RGBAPointUtils.this.j(customPointDialParam.getBackBitmap(), customPointDialParam.getCornerRadius(), customPointDialParam.getScreenType(), customPointDialParam.getPreviewWidth(), customPointDialParam.getPreviewHigh(), customPointDialParam.getPreviewBitmap());
            byte[] fixHeaderInfo = new ImageHeadUtils().getFixHeaderInfo(RGBAPointUtils.this.f, ImageHeadUtils.a.point);
            if (customPointDialParam.getScreenType() == 1) {
                customPointDialParam.setBackBitmap(RGBAPointUtils.this.l(customPointDialParam.getBackBitmap(), customPointDialParam.getScreenWidth(), customPointDialParam.getScreenHigh()));
            }
            RGBAPointUtils.this.e(j, customPointDialParam.getBackBitmap(), customPointDialParam.getScreenWidth(), customPointDialParam.getScreenHigh(), customPointDialParam.getHourBitmap(), customPointDialParam.getMinuteBitmap(), customPointDialParam.getSecondBitmap(), customPointDialParam.gethTop(), customPointDialParam.getmTop(), customPointDialParam.getsTop());
            int length = fixHeaderInfo.length;
            RGBAPointUtils rGBAPointUtils = RGBAPointUtils.this;
            byte[] bArr = new byte[length + rGBAPointUtils.b.length + rGBAPointUtils.c.length];
            System.arraycopy(fixHeaderInfo, 0, bArr, 0, fixHeaderInfo.length);
            byte[] bArr2 = RGBAPointUtils.this.b;
            System.arraycopy(bArr2, 0, bArr, fixHeaderInfo.length, bArr2.length);
            RGBAPointUtils rGBAPointUtils2 = RGBAPointUtils.this;
            byte[] bArr3 = rGBAPointUtils2.c;
            System.arraycopy(bArr3, 0, bArr, fixHeaderInfo.length + rGBAPointUtils2.b.length, bArr3.length);
            if ("mounted".equals(Environment.getExternalStorageState())) {
                str = RGBAPointUtils.this.d.getExternalCacheDir().getAbsolutePath() + "/dial.bin";
            } else {
                str = RGBAPointUtils.this.d.getCacheDir().getAbsolutePath() + "/dial.bin";
            }
            String str2 = str;
            try {
                RGBAPointUtils.this.q(bArr, str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(RGBAPointUtils.this.f7898a, " 处理数据完成耗时 " + (System.currentTimeMillis() - currentTimeMillis) + " 豪秒");
            RGBAPointUtils rGBAPointUtils3 = RGBAPointUtils.this;
            rGBAPointUtils3.c = null;
            rGBAPointUtils3.b = null;
            return str2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (this.f7899a != null) {
                String str2 = RGBAPointUtils.this.f7898a;
                Log.e(str2, "将bin地址发送给页面:" + str);
                this.f7899a.dialPath(str);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            RGBAPointUtils rGBAPointUtils = RGBAPointUtils.this;
            rGBAPointUtils.b = new byte[8192];
            rGBAPointUtils.c = null;
        }
    }

    public RGBAPointUtils(Context context, boolean z) {
        this.d = context;
        this.f = z;
    }

    public void destroy() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    public final void e(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2, int i, int i2, @NonNull Bitmap bitmap3, @NonNull Bitmap bitmap4, @NonNull Bitmap bitmap5, int i3, int i4, int i5) {
        new BitmapFactory.Options().inScaled = false;
        byte[] imageConvertRgb565 = this.e.imageConvertRgb565(bitmap, false, this.f, 0);
        if (this.c == null) {
            this.c = imageConvertRgb565;
        }
        String str = this.f7898a;
        Log.e(str, "最终背景图的宽:" + bitmap2.getWidth() + ",高:" + bitmap2.getHeight());
        byte[] i6 = i(bitmap2, imageConvertRgb565, i, i2);
        byte[] f = f(bitmap3, i6, i, i2, i3);
        h(bitmap5, i6, f, g(bitmap4, i6, f, i, i2, i4), i, i2, i5);
    }

    public final byte[] f(Bitmap bitmap, byte[] bArr, int i, int i2, int i3) {
        Bitmap m = m(bitmap);
        byte[] n = n(this.c.length, (i - m.getWidth()) / 2, i3, m.getWidth(), m.getHeight(), m.getWidth() / 2, (i2 / 2) - i3);
        System.arraycopy(n, 0, this.b, bArr.length, n.length);
        byte[] imageConvertRgb565 = this.e.imageConvertRgb565(m, true, this.f, this.c.length);
        byte[] bArr2 = this.c;
        if (bArr2 == null) {
            this.c = imageConvertRgb565;
        } else {
            byte[] bArr3 = new byte[bArr2.length + imageConvertRgb565.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(imageConvertRgb565, 0, bArr3, this.c.length, imageConvertRgb565.length);
            this.c = bArr3;
        }
        return n;
    }

    public final byte[] g(Bitmap bitmap, byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        Bitmap m = m(bitmap);
        byte[] o = o(this.c.length, (i - m.getWidth()) / 2, i3, m.getWidth(), m.getHeight(), m.getWidth() / 2, (i2 / 2) - i3);
        System.arraycopy(o, 0, this.b, bArr.length + bArr2.length, o.length);
        byte[] imageConvertRgb565 = this.e.imageConvertRgb565(m, true, this.f, this.c.length);
        byte[] bArr3 = this.c;
        if (bArr3 == null) {
            this.c = imageConvertRgb565;
        } else {
            byte[] bArr4 = new byte[bArr3.length + imageConvertRgb565.length];
            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
            System.arraycopy(imageConvertRgb565, 0, bArr4, this.c.length, imageConvertRgb565.length);
            this.c = bArr4;
        }
        return o;
    }

    public final void h(Bitmap bitmap, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, int i3) {
        Bitmap m = m(bitmap);
        byte[] p = p(this.c.length, (i - m.getWidth()) / 2, i3, m.getWidth(), m.getHeight(), m.getWidth() / 2, (i2 / 2) - i3);
        System.arraycopy(p, 0, this.b, bArr.length + bArr2.length + bArr3.length, p.length);
        byte[] imageConvertRgb565 = this.e.imageConvertRgb565(m, true, this.f, this.c.length);
        byte[] bArr4 = this.c;
        if (bArr4 == null) {
            this.c = imageConvertRgb565;
            return;
        }
        byte[] bArr5 = new byte[bArr4.length + imageConvertRgb565.length];
        System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
        System.arraycopy(imageConvertRgb565, 0, bArr5, this.c.length, imageConvertRgb565.length);
        this.c = bArr5;
    }

    public final byte[] i(Bitmap bitmap, byte[] bArr, int i, int i2) {
        byte[] imageConvertRgb565 = this.e.imageConvertRgb565(bitmap, true, this.f, bArr.length);
        byte[] k = k(bitmap, bArr.length, i, i2);
        byte[] bArr2 = this.c;
        if (bArr2 == null) {
            this.c = imageConvertRgb565;
        } else {
            byte[] bArr3 = new byte[bArr2.length + imageConvertRgb565.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(imageConvertRgb565, 0, bArr3, this.c.length, imageConvertRgb565.length);
            this.c = bArr3;
        }
        System.arraycopy(k, 0, this.b, 0, k.length);
        return k;
    }

    public final Bitmap j(Bitmap bitmap, int i, int i2, int i3, int i4, Bitmap bitmap2) {
        int i5;
        int i6;
        int width;
        try {
            if (this.f) {
                i5 = i3 % 2;
                i6 = i4 % 2;
            } else {
                i5 = 0;
                i6 = 0;
            }
            Log.e(this.f7898a, "缩略图宽:" + i3 + ",缩略图高:" + i4);
            int i7 = i3 - i5;
            int i8 = i4 - i6;
            Bitmap createBitmap = Bitmap.createBitmap(i7, i8, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, i7, i8), paint);
            canvas.drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), new Rect(0, 0, i7, i8), paint);
            paint.setAlpha(200);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(-16711681);
            paint.setStrokeWidth(2.0f);
            if (i2 == 1) {
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
                Log.e(this.f7898a, "画缩略图");
                return createBitmap2;
            }
            Bitmap createBitmap3 = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas3 = new Canvas(createBitmap3);
            Rect rect = new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
            float f = i;
            canvas3.drawRoundRect(new RectF(rect), f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas3.drawBitmap(createBitmap, (Rect) null, rect, paint);
            return createBitmap3;
        } catch (Exception unused) {
            return null;
        }
    }

    public final byte[] k(Bitmap bitmap, int i, int i2, int i3) {
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[2];
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i4 = (i2 - width) / 2;
            int i5 = i4 & (-1);
            bArr[1] = (byte) (i5 >> 8);
            bArr[0] = (byte) i5;
            int i6 = (i3 - height) / 2;
            int i7 = i6 & (-1);
            bArr2[1] = (byte) (i7 >> 8);
            bArr2[0] = (byte) i7;
            String str = this.f7898a;
            Log.e(str, "背景图开始地址:" + i4 + Constants.SEPARATOR_COMMA + i6);
        }
        int i8 = i & (-1);
        byte[] bArr3 = {(byte) i8, (byte) (i8 >> 8), (byte) (i8 >> 16), (byte) (i8 >> 24)};
        byte[] bArr4 = new byte[12];
        bArr4[0] = 1;
        System.arraycopy(bArr, 0, bArr4, 2, 2);
        System.arraycopy(bArr2, 0, bArr4, 4, 2);
        System.arraycopy(bArr3, 0, bArr4, 8, 4);
        return bArr4;
    }

    public final Bitmap l(Bitmap bitmap, int i, int i2) {
        try {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(-16711681);
            if (i > i2) {
                i = i2;
            }
            int i3 = this.f ? i % 2 : 0;
            String str = this.f7898a;
            Log.e(str, "圆形直径:" + i);
            int i4 = i - i3;
            Bitmap createBitmap = Bitmap.createBitmap(i4, i4, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawCircle(i4 / 2, i4 / 2, (i4 / 2) - 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final Bitmap m(@NonNull Bitmap bitmap) {
        int width = bitmap.getWidth() % 2;
        int height = bitmap.getHeight() % 2;
        if (!(height == 0 && width == 0) && this.f) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() - width, bitmap.getHeight() - height, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight()), new Paint());
            return createBitmap;
        }
        return bitmap;
    }

    public final byte[] n(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i2 & (-1);
        int i9 = i3 & (-1);
        int i10 = i5 & (-1);
        int i11 = i4 & (-1);
        int i12 = i6 & (-1);
        int i13 = i7 & (-1);
        byte[] bArr = {2, 1, (byte) i8, (byte) (i8 >> 8), (byte) i9, (byte) (i9 >> 8), 0, 0, 0, 0, 0, 0, (byte) i10, (byte) (i10 >> 8), (byte) i11, (byte) (i11 >> 8), 0, 0, 0, 0, (byte) i12, (byte) (i12 >> 8), (byte) i13, (byte) (i13 >> 8)};
        bArr[13] = 0;
        bArr[15] = 0;
        bArr[16] = 0;
        bArr[17] = 0;
        bArr[18] = 104;
        bArr[19] = 1;
        int i14 = i & (-1);
        System.arraycopy(new byte[]{(byte) i14, (byte) (i14 >> 8), (byte) (i14 >> 16), (byte) (i14 >> 24)}, 0, bArr, 8, 4);
        return bArr;
    }

    public final byte[] o(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i2 & (-1);
        int i9 = i3 & (-1);
        int i10 = i5 & (-1);
        int i11 = i4 & (-1);
        int i12 = i6 & (-1);
        int i13 = i7 & (-1);
        byte[] bArr = {2, 2, (byte) i8, (byte) (i8 >> 8), (byte) i9, (byte) (i9 >> 8), 0, 0, 0, 0, 0, 0, (byte) i10, (byte) (i10 >> 8), (byte) i11, (byte) (i11 >> 8), 0, 0, 104, 1, (byte) i12, (byte) (i12 >> 8), (byte) i13, (byte) (i13 >> 8)};
        int i14 = i & (-1);
        System.arraycopy(new byte[]{(byte) i14, (byte) (i14 >> 8), (byte) (i14 >> 16), (byte) (i14 >> 24)}, 0, bArr, 8, 4);
        return bArr;
    }

    public final byte[] p(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i2 & (-1);
        int i9 = i3 & (-1);
        int i10 = i5 & (-1);
        int i11 = i4 & (-1);
        int i12 = i6 & (-1);
        int i13 = i7 & (-1);
        byte[] bArr = {2, 3, (byte) i8, (byte) (i8 >> 8), (byte) i9, (byte) (i9 >> 8), 0, 0, 0, 0, 0, 0, (byte) i10, (byte) (i10 >> 8), (byte) i11, (byte) (i11 >> 8), 0, 0, 104, 1, (byte) i12, (byte) (i12 >> 8), (byte) i13, (byte) (i13 >> 8)};
        int i14 = i & (-1);
        System.arraycopy(new byte[]{(byte) i14, (byte) (i14 >> 8), (byte) (i14 >> 16), (byte) (i14 >> 24)}, 0, bArr, 8, 4);
        return bArr;
    }

    public void produceDialBin(@NonNull CustomPointDialParam customPointDialParam, CustomDialCallback customDialCallback) {
        if (customPointDialParam != null && customPointDialParam.getBackBitmap() != null) {
            new a(customDialCallback).execute(customPointDialParam);
        } else if (customDialCallback != null) {
            customDialCallback.dialPath(null);
        }
    }

    public Bitmap produceDialThumbnail(Bitmap bitmap, int i, int i2, int i3, int i4, Bitmap bitmap2) {
        int i5;
        int i6;
        int width;
        try {
            if (this.f) {
                i5 = i % 2;
                i6 = i2 % 2;
            } else {
                i5 = 0;
                i6 = 0;
            }
            int i7 = i - i5;
            int i8 = i2 - i6;
            Bitmap createBitmap = Bitmap.createBitmap(i7, i8, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, i7, i8), paint);
            canvas.drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), new Rect(0, 0, i7, i8), paint);
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

    public final void q(byte[] bArr, String str) throws Exception {
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
