package com.htsmart.wristband2.dial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.goodix.ble.libcomx.task.ITask;
import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGEventListener;
import com.veryfit.multi.nativeprotocol.b;
/* loaded from: classes11.dex */
public class DialDrawer {
    public static final int STYLE_BASE_ON_WIDTH = 800;

    /* loaded from: classes11.dex */
    public enum Position {
        TOP(0, "TOP"),
        LEFT(1, FitnessChallengeConstants.LEFT),
        RIGHT(2, "RIGHT"),
        BOTTOM(3, "BOTTOM");
        
        private final int b;
        private final String c;

        Position(int i, String str) {
            this.b = i;
            this.c = str;
        }

        public static Position fromId(int i) {
            return i != 1 ? i != 2 ? i != 3 ? TOP : BOTTOM : RIGHT : LEFT;
        }

        public int getId() {
            return this.b;
        }

        public String getName() {
            return this.c;
        }
    }

    /* loaded from: classes11.dex */
    public enum ScaleType {
        CENTER(0),
        CENTER_CROP(1),
        AUTO_CROP(2);
        
        private final int b;

        ScaleType(int i) {
            this.b = i;
        }

        public static ScaleType fromId(int i) {
            return i != 1 ? i != 2 ? CENTER : AUTO_CROP : CENTER_CROP;
        }

        public int getId() {
            return this.b;
        }
    }

    /* loaded from: classes11.dex */
    public static class Shape implements Parcelable {
        public static final Parcelable.Creator<Shape> CREATOR = new a();
        public static final int SHAPE_CIRCLE = 1;
        public static final int SHAPE_RECTANGLE = 0;
        public final int h;
        public final int i;
        public final int j;
        public int k;

        /* loaded from: classes11.dex */
        public static class a implements Parcelable.Creator<Shape> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public Shape createFromParcel(Parcel parcel) {
                return new Shape(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public Shape[] newArray(int i) {
                return new Shape[i];
            }
        }

        public Shape(int i, int i2, int i3, int i4) {
            this.h = i;
            this.i = i2;
            this.j = i3;
            this.k = i4;
        }

        public Shape(Parcel parcel) {
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
        }

        public static Shape createCircle(int i) {
            return new Shape(1, i, i, 0);
        }

        @Nullable
        @Deprecated
        public static Shape createFromLcd(int i) {
            switch (i) {
                case 0:
                    return createRectangle(240, 240, 0);
                case 1:
                    return createCircle(240);
                case 2:
                    return createRectangle(320, 320, 0);
                case 3:
                    return createCircle(360);
                case 4:
                    return createRectangle(320, 385, 0);
                case 5:
                    return createRectangle(320, 360, 0);
                case 6:
                    return createRectangle(240, DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE, 0);
                case 7:
                    return createRectangle(240, DfuException.ERROR_ENTER_OTA_MODE_FAILED, 0);
                case 8:
                    return createRectangle(348, 442, 0);
                case 9:
                    return createRectangle(DfuException.ERROR_ENTER_OTA_MODE_FAILED, 240, 0);
                case 10:
                    return createRectangle(200, 320, 0);
                case 11:
                    return createRectangle(368, 448, 0);
                case 12:
                    return createRectangle(320, 390, 0);
                case 13:
                    return createRectangle(172, 320, 0);
                case 14:
                    return createCircle(454);
                case 15:
                    return createRectangle(128, 220, 0);
                case 16:
                    return createRectangle(160, 80, 0);
                case 17:
                    return createRectangle(128, 128, 0);
                case 18:
                    return createRectangle(167, 320, 0);
                case 19:
                    return createRectangle(80, 160, 0);
                case 20:
                    return createRectangle(320, 380, 0);
                case 21:
                    return createRectangle(240, 286, 0);
                case 22:
                    return createCircle(466);
                case 23:
                    return createRectangle(240, 296, 0);
                case 24:
                    return createRectangle(b.C1, 502, 0);
                case 25:
                    return createCircle(416);
                case 26:
                    return createRectangle(240, TGEventListener.WORKOUT_START, 0);
                case 27:
                    return createCircle(ITask.EVT_START);
                default:
                    return null;
            }
        }

        public static Shape createRectangle(int i, int i2) {
            return new Shape(0, i, i2, 0);
        }

        public static Shape createRectangle(int i, int i2, int i3) {
            return new Shape(0, i, i2, i3);
        }

        @Deprecated
        public static boolean isLcdSupport(int i) {
            return i >= 0 && i <= 27;
        }

        public int corners() {
            return this.k;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Shape) {
                Shape shape = (Shape) obj;
                return shape.h == this.h && shape.i == this.i && shape.j == this.j && shape.k == this.k;
            }
            return false;
        }

        public int height() {
            return this.j;
        }

        public boolean isShapeCircle() {
            return this.h == 1;
        }

        public boolean isShapeRectangle() {
            return this.h == 0;
        }

        public void setCorners(int i) {
            this.k = i;
        }

        public int shape() {
            return this.h;
        }

        public int width() {
            return this.i;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
        }
    }

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f12010a;

        static {
            int[] iArr = new int[Position.values().length];
            f12010a = iArr;
            try {
                iArr[Position.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12010a[Position.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f12010a[Position.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f12010a[Position.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void a(Bitmap bitmap, float f, float f2, ScaleType scaleType, Matrix matrix) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        matrix.reset();
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        matrix.setTranslate(f3 - (bitmap.getWidth() / 2.0f), f4 - (bitmap.getHeight() / 2.0f));
        if (scaleType == ScaleType.AUTO_CROP && (f > bitmap.getWidth() || f2 > bitmap.getHeight())) {
            scaleType = ScaleType.CENTER_CROP;
        }
        if (scaleType == ScaleType.CENTER_CROP) {
            float max = Math.max(f / bitmap.getWidth(), f2 / bitmap.getHeight());
            matrix.postScale(max, max, f3, f4);
        }
    }

    public static void b(Bitmap bitmap, int i, float f, float f2, Position position, Matrix matrix) {
        float width;
        float f3;
        float height;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        matrix.reset();
        float f4 = f / i;
        float f5 = f * 0.1f;
        float f6 = 0.1f * f2;
        matrix.setScale(f4, f4);
        int i2 = a.f12010a[position.ordinal()];
        if (i2 == 1) {
            matrix.postTranslate((f / 2.0f) - ((f4 * bitmap.getWidth()) / 2.0f), f6);
        } else if (i2 == 2) {
            matrix.postTranslate(f5, (f2 / 2.0f) - ((f4 * bitmap.getHeight()) / 2.0f));
        } else {
            if (i2 != 3) {
                width = (f / 2.0f) - ((bitmap.getWidth() * f4) / 2.0f);
                f3 = f2 - f6;
                height = f4 * bitmap.getHeight();
            } else {
                width = (f - f5) - (bitmap.getWidth() * f4);
                f3 = f2 / 2.0f;
                height = (f4 * bitmap.getHeight()) / 2.0f;
            }
            matrix.postTranslate(width, f3 - height);
        }
    }

    public static void c(Canvas canvas, Shape shape, Paint paint, Matrix matrix, Bitmap bitmap) {
        RectF rectF = new RectF(0.0f, 0.0f, shape.i, shape.j);
        int saveLayer = canvas.saveLayer(rectF, paint, 31);
        if (shape.isShapeCircle()) {
            float f = shape.i / 2.0f;
            canvas.drawCircle(f, f, f, paint);
        } else {
            canvas.drawRoundRect(rectF, shape.corners(), shape.corners(), paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, matrix, paint);
        canvas.restoreToCount(saveLayer);
        paint.setXfermode(null);
    }

    @Nullable
    public static Bitmap createDialBackground(Bitmap bitmap, Shape shape, ScaleType scaleType) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(shape.i, shape.j, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Matrix matrix = new Matrix();
        Paint paint = new Paint(7);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        a(bitmap, shape.i, shape.j, scaleType, matrix);
        c(canvas, shape, paint, matrix, bitmap);
        return createBitmap;
    }

    public static Bitmap createDialPreview(Bitmap bitmap, Bitmap bitmap2, Shape shape, ScaleType scaleType, Position position, int i, int i2, int i3) {
        Bitmap bitmap3 = null;
        if (bitmap != null && !bitmap.isRecycled() && bitmap2 != null && !bitmap2.isRecycled()) {
            bitmap3 = Bitmap.createBitmap(i2, i3, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap3);
            float f = i2;
            float f2 = f / shape.i;
            float f3 = i3;
            float f4 = f3 / shape.j;
            if (f2 != f4) {
                Log.w("DialDrawer", "createDialPreview:Shape and Output size not match");
            }
            float max = Math.max(f2, f4);
            canvas.scale(max, max);
            canvas.translate((f - (shape.i * max)) / 2.0f, (f3 - (max * shape.j)) / 2.0f);
            Matrix matrix = new Matrix();
            Paint paint = new Paint(7);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            a(bitmap, shape.i, shape.j, scaleType, matrix);
            c(canvas, shape, paint, matrix, bitmap);
            b(bitmap2, i, shape.i, shape.j, position, matrix);
            canvas.drawBitmap(bitmap2, matrix, paint);
        }
        return bitmap3;
    }
}
