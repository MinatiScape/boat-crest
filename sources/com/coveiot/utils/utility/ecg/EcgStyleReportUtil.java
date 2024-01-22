package com.coveiot.utils.utility.ecg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.coveiot.utils.R;
import com.coveiot.utils.utility.FileUtil;
import com.coveiot.utils.utility.ScreenUtil;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class EcgStyleReportUtil {

    /* renamed from: a  reason: collision with root package name */
    public static float f7629a = 20.0f;
    public static float e;
    public static float j;
    public static float k;
    public static Float l;
    public static int m;
    public static int n;
    public static Float o;
    public static Float b = Float.valueOf(1070.0f);
    public static int c = 35;
    public static float d = 1035;
    public static int f = 100;
    public static int g = 300;
    public static int h = 50;
    public static int i = 300;
    public static String p = "dd MMMM yyyy hh:mm a";

    /* loaded from: classes9.dex */
    public interface UserInfoKey {
        public static final String AGE = "Age";
        public static final String BP = "BP";
        public static final String DOB = "DOB";
        public static final String GENDER = "Gender";
        public static final String HEIGHT = "Height";
        public static final String HR = "HR";
        public static final String HRV = "HRV";
        public static final String NAME = "Name";
        public static final String STRESS = "Stress";
        public static final String WEIGHT = "Weight";
    }

    public static void a(Path path, Canvas canvas, Paint paint, float f2) {
        paint.setColor(Color.rgb(243, 119, 99));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(n);
        paint.setAlpha(200);
        int i2 = 0;
        for (int i3 = 0; i3 < 51; i3++) {
            float f3 = i3;
            path.moveTo((l.floatValue() * f3) + c, e);
            path.lineTo((f3 * l.floatValue()) + c, j);
        }
        while (true) {
            float f4 = i2;
            if (f4 <= 5.0f * f2) {
                path.moveTo(c - (m / 2), (k * f4) + e);
                path.lineTo(d + (m / 2), (f4 * k) + e);
                i2++;
            } else {
                canvas.drawPath(path, paint);
                path.reset();
                return;
            }
        }
    }

    public static void b(Context context, Path path, Canvas canvas, Paint paint, float f2, List<Integer> list) {
        int size = list.size();
        paint.setStrokeWidth(n);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        int i2 = 0;
        loop0: while (true) {
            float f3 = i2;
            if (f3 >= f2) {
                break;
            }
            int i3 = i2 * 5120;
            for (int i4 = 0; i4 < 5120; i4++) {
                int i5 = i3 + i4;
                if (i5 >= size) {
                    break loop0;
                }
                Integer num = list.get(i5);
                if (i4 == 0) {
                    path.moveTo(c + 10, (k * f3 * 5.0f) + f(num.intValue(), context));
                }
                float f4 = c;
                path.lineTo(f4 + ((i4 * (d - f4)) / 5120.0f), (k * f3 * 5.0f) + f(num.intValue(), context));
            }
            i2++;
        }
        canvas.drawPath(path, paint);
    }

    public static void c(Context context, Canvas canvas, Bitmap bitmap) {
        if (bitmap != null) {
            canvas.drawBitmap(g(bitmap, i, h, false), (b.floatValue() / 2.0f) - 150.0f, j + bitmap.getHeight() + ScreenUtil.dip2px(context, 16.0f), (Paint) null);
        }
    }

    public static void d(Context context, Canvas canvas, Paint paint, String str, Map<String, String> map, Bitmap bitmap) {
        float f2;
        int height;
        int height2;
        if (bitmap != null) {
            f2 = f + ScreenUtil.dip2px(context, 8.0f);
            canvas.drawBitmap(g(bitmap, g, f, false), (b.floatValue() / 2.0f) - 150.0f, f2, (Paint) null);
        } else {
            f2 = 0.0f;
        }
        paint.setTextSize(ScreenUtil.dip2px(context, 18.0f));
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(true);
        String str2 = context.getString(R.string.ecg_report) + "(" + new SimpleDateFormat(p).format(new Date()) + ")";
        Rect rect = new Rect();
        paint.getTextBounds(str2, 0, str2.length(), rect);
        float height3 = rect.height();
        float dip2px = (f2 * 2.0f) + height3 + ScreenUtil.dip2px(context, 8.0f);
        float dip2px2 = ScreenUtil.dip2px(context, 8.0f);
        ScreenUtil.dip2px(context, 8.0f);
        canvas.drawText(str2, ScreenUtil.dip2px(context, 24.0f), dip2px, paint);
        paint.setTextSize(ScreenUtil.dip2px(context, 14.0f));
        float f3 = dip2px + height3 + dip2px2;
        if (map != null && map.size() > 0) {
            float f4 = f3;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("Name")) {
                    String str3 = "Name: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                    paint.getTextBounds(str3, 0, str3.length(), rect);
                    canvas.drawText(str3, ScreenUtil.dip2px(context, 24.0f), f3, paint);
                    height = rect.height();
                } else {
                    if (entry.getKey().equalsIgnoreCase(UserInfoKey.GENDER)) {
                        String str4 = "Gender: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str4, 0, str4.length(), rect);
                        canvas.drawText(str4, b.floatValue() / 2.0f, f4, paint);
                        height2 = rect.height();
                    } else if (entry.getKey().equalsIgnoreCase("Age")) {
                        String str5 = "Age: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str5, 0, str5.length(), rect);
                        canvas.drawText(str5, b.floatValue() - (rect.width() + 50), f4, paint);
                        rect.height();
                    } else if (entry.getKey().equalsIgnoreCase(UserInfoKey.HEIGHT)) {
                        String str6 = "Height: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str6, 0, str6.length(), rect);
                        canvas.drawText(str6, ScreenUtil.dip2px(context, 24.0f), f3, paint);
                        height = rect.height();
                    } else if (entry.getKey().equalsIgnoreCase(UserInfoKey.WEIGHT)) {
                        String str7 = "Weight: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str7, 0, str7.length(), rect);
                        canvas.drawText(str7, b.floatValue() / 2.0f, f4, paint);
                        height2 = rect.height();
                    } else if (entry.getKey().equalsIgnoreCase(UserInfoKey.HR) && entry.getValue() != null && !entry.getValue().isEmpty() && !entry.getValue().equals(BleConst.GetDeviceTime)) {
                        String str8 = "HR: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str8, 0, str8.length(), rect);
                        canvas.drawText(str8, ScreenUtil.dip2px(context, 24.0f), f3, paint);
                        height = rect.height();
                    } else if (entry.getKey().equalsIgnoreCase(UserInfoKey.BP) && entry.getValue() != null && !entry.getValue().isEmpty() && !entry.getValue().equals(BleConst.GetDeviceTime)) {
                        String str9 = "BP: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str9, 0, str9.length(), rect);
                        canvas.drawText(str9, b.floatValue() / 2.0f, f4, paint);
                        height2 = rect.height();
                    } else if (entry.getKey().equalsIgnoreCase("HRV") && entry.getValue() != null && !entry.getValue().isEmpty() && !entry.getValue().equals(BleConst.GetDeviceTime)) {
                        String str10 = "HRV: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str10, 0, str10.length(), rect);
                        canvas.drawText(str10, ScreenUtil.dip2px(context, 24.0f), f3, paint);
                        height = rect.height();
                    } else if (entry.getKey().equalsIgnoreCase(UserInfoKey.STRESS) && entry.getValue() != null && !entry.getValue().isEmpty() && !entry.getValue().equals(BleConst.GetDeviceTime)) {
                        String str11 = "Stress: " + entry.getValue() + HexStringBuilder.DEFAULT_SEPARATOR;
                        paint.getTextBounds(str11, 0, str11.length(), rect);
                        canvas.drawText(str11, b.floatValue() / 2.0f, f4, paint);
                        height2 = rect.height();
                    }
                    f4 = f4 + height2 + dip2px2;
                }
                f3 = f3 + height + dip2px2;
            }
        }
        e = f3 + dip2px2;
    }

    public static void e(Path path, Canvas canvas, Paint paint) {
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStrokeWidth(m);
        paint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        for (int i2 = 0; i2 < 11; i2++) {
            float f2 = i2 * 5;
            path.moveTo((l.floatValue() * f2) + c, e);
            path.lineTo((l.floatValue() * f2) + c, j + 20.0f);
            String str = i2 + "s";
            paint.getTextBounds(str, 0, str.length(), rect);
            canvas.drawText(str, ((f2 * l.floatValue()) + c) - (rect.width() / 2), j + 50.0f, paint);
        }
        paint.setColor(Color.rgb(255, 119, 99));
        paint.setAlpha(255);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path.reset();
    }

    public static float f(double d2, Context context) {
        return ScreenUtil.dip2px(context, (float) ((8000.0d - d2) * 0.0031250000465661287d)) + e;
    }

    public static Bitmap g(Bitmap bitmap, int i2, int i3, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i2 / width, i3 / height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        if (!z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static boolean generateImageReport(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull List<Integer> list, @NonNull String str3, @NonNull String str4, Map<String, String> map, Bitmap bitmap, Bitmap bitmap2) {
        int size = list.size();
        int i2 = size % 5120;
        int i3 = size / 5120;
        if (i2 != 0) {
            i3++;
        }
        float f2 = i3;
        f7629a = ScreenUtil.dip2px(context, 10.0f);
        k = ScreenUtil.dip2px(context, 15.0f);
        l = Float.valueOf(f7629a);
        int dip2px = ScreenUtil.dip2px(context, 20.0f);
        c = dip2px;
        float f3 = dip2px;
        float f4 = (f7629a * 50.0f) + f3;
        d = f4;
        b = Float.valueOf(f4 + f3);
        o = Float.valueOf((k * f2 * 5.0f) + ScreenUtil.dip2px(context, 400.0f));
        Bitmap createBitmap = Bitmap.createBitmap(b.intValue(), o.intValue(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        Paint paint = new Paint();
        m = ScreenUtil.dip2px(context, 1.5f);
        n = ScreenUtil.dip2px(context, 1.0f);
        if (map != null) {
            d(context, canvas, paint, str3, map, bitmap);
        }
        Path path = new Path();
        j = e + (k * f2 * 5.0f);
        paint.setTextSize(ScreenUtil.dip2px(context, 15.0f));
        a(path, canvas, paint, f2);
        e(path, canvas, paint);
        b(context, path, canvas, paint, f2, list);
        c(context, canvas, bitmap2);
        File createFile = FileUtil.createFile(context, str, str2);
        if (createFile != null) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(createFile);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            try {
                try {
                    try {
                        if (str4.equalsIgnoreCase(FileUtil.Format.PNG)) {
                            createBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        } else if (str4.equalsIgnoreCase(FileUtil.Format.JPEG)) {
                            createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                        }
                        if (createBitmap != null) {
                            createBitmap.recycle();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        if (createBitmap != null) {
                            createBitmap.recycle();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th) {
                    if (createBitmap != null) {
                        createBitmap.recycle();
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        }
        return true;
    }

    public static boolean generatePdfReport(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull List<Integer> list, @NonNull String str3, Map<String, String> map, Bitmap bitmap, Bitmap bitmap2) {
        int size = list.size();
        int i2 = size % 5120;
        int i3 = size / 5120;
        if (i2 != 0) {
            i3++;
        }
        float f2 = i3;
        f7629a = ScreenUtil.dip2px(context, 10.0f);
        k = ScreenUtil.dip2px(context, 15.0f);
        l = Float.valueOf(f7629a);
        int dip2px = ScreenUtil.dip2px(context, 20.0f);
        c = dip2px;
        float f3 = dip2px;
        float f4 = (f7629a * 50.0f) + f3;
        d = f4;
        b = Float.valueOf(f4 + f3);
        o = Float.valueOf((k * f2 * 5.0f) + ScreenUtil.dip2px(context, 400.0f));
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(b.intValue(), o.intValue(), 1).create());
        Canvas canvas = startPage.getCanvas();
        Paint paint = new Paint();
        m = ScreenUtil.dip2px(context, 1.5f);
        n = ScreenUtil.dip2px(context, 1.0f);
        if (map != null) {
            d(context, canvas, paint, str3, map, bitmap);
        }
        Path path = new Path();
        j = e + (k * f2 * 5.0f);
        paint.setTextSize(ScreenUtil.dip2px(context, 15.0f));
        a(path, canvas, paint, f2);
        e(path, canvas, paint);
        b(context, path, canvas, paint, f2, list);
        c(context, canvas, bitmap2);
        pdfDocument.finishPage(startPage);
        File createFile = FileUtil.createFile(context, str, str2);
        if (createFile != null) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(createFile);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            try {
                pdfDocument.writeTo(fileOutputStream);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return true;
    }
}
