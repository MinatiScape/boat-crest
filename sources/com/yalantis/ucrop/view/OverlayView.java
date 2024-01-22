package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import com.yalantis.ucrop.util.RectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes12.dex */
public class OverlayView extends View {
    public static final boolean DEFAULT_CIRCLE_DIMMED_LAYER = false;
    public static final int DEFAULT_CROP_GRID_COLUMN_COUNT = 2;
    public static final int DEFAULT_CROP_GRID_ROW_COUNT = 2;
    public static final int DEFAULT_FREESTYLE_CROP_MODE = 0;
    public static final boolean DEFAULT_SHOW_CROP_FRAME = true;
    public static final boolean DEFAULT_SHOW_CROP_GRID = true;
    public static final int FREESTYLE_CROP_MODE_DISABLE = 0;
    public static final int FREESTYLE_CROP_MODE_ENABLE = 1;
    public static final int FREESTYLE_CROP_MODE_ENABLE_WITH_PASS_THROUGH = 2;
    public int A;
    public int B;
    public int C;
    public OverlayViewChangeListener D;
    public boolean E;
    public final RectF h;
    public final RectF i;
    public int j;
    public int k;
    public float l;
    public float[] m;
    public float[] mCropGridCenter;
    public float[] mCropGridCorners;
    public int mThisHeight;
    public int mThisWidth;
    public boolean n;
    public boolean o;
    public boolean p;
    public int q;
    public Path r;
    public Paint s;
    public Paint t;
    public Paint u;
    public Paint v;
    public int w;
    public float x;
    public float y;
    public int z;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface FreestyleMode {
    }

    public OverlayView(Context context) {
        this(context, null);
    }

    public final int a(float f, float f2) {
        double d = this.A;
        int i = -1;
        for (int i2 = 0; i2 < 8; i2 += 2) {
            double sqrt = Math.sqrt(Math.pow(f - this.mCropGridCorners[i2], 2.0d) + Math.pow(f2 - this.mCropGridCorners[i2 + 1], 2.0d));
            if (sqrt < d) {
                i = i2 / 2;
                d = sqrt;
            }
        }
        if (this.w == 1 && i < 0 && this.h.contains(f, f2)) {
            return 4;
        }
        return i;
    }

    public final void b(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_frame_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width));
        int color = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_frame_color, getResources().getColor(R.color.ucrop_color_default_crop_frame));
        this.u.setStrokeWidth(dimensionPixelSize);
        this.u.setColor(color);
        this.u.setStyle(Paint.Style.STROKE);
        this.v.setStrokeWidth(dimensionPixelSize * 3);
        this.v.setColor(color);
        this.v.setStyle(Paint.Style.STROKE);
    }

    public final void c(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_grid_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width));
        int color = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_grid_color, getResources().getColor(R.color.ucrop_color_default_crop_grid));
        this.t.setStrokeWidth(dimensionPixelSize);
        this.t.setColor(color);
        this.j = typedArray.getInt(R.styleable.ucrop_UCropView_ucrop_grid_row_count, 2);
        this.k = typedArray.getInt(R.styleable.ucrop_UCropView_ucrop_grid_column_count, 2);
    }

    public final void d(float f, float f2) {
        this.i.set(this.h);
        int i = this.z;
        if (i == 0) {
            RectF rectF = this.i;
            RectF rectF2 = this.h;
            rectF.set(f, f2, rectF2.right, rectF2.bottom);
        } else if (i == 1) {
            RectF rectF3 = this.i;
            RectF rectF4 = this.h;
            rectF3.set(rectF4.left, f2, f, rectF4.bottom);
        } else if (i == 2) {
            RectF rectF5 = this.i;
            RectF rectF6 = this.h;
            rectF5.set(rectF6.left, rectF6.top, f, f2);
        } else if (i == 3) {
            RectF rectF7 = this.i;
            RectF rectF8 = this.h;
            rectF7.set(f, rectF8.top, rectF8.right, f2);
        } else if (i == 4) {
            this.i.offset(f - this.x, f2 - this.y);
            if (this.i.left <= getLeft() || this.i.top <= getTop() || this.i.right >= getRight() || this.i.bottom >= getBottom()) {
                return;
            }
            this.h.set(this.i);
            e();
            postInvalidate();
            return;
        }
        boolean z = this.i.height() >= ((float) this.B);
        boolean z2 = this.i.width() >= ((float) this.B);
        RectF rectF9 = this.h;
        rectF9.set(z2 ? this.i.left : rectF9.left, z ? this.i.top : rectF9.top, z2 ? this.i.right : rectF9.right, z ? this.i.bottom : rectF9.bottom);
        if (z || z2) {
            e();
            postInvalidate();
        }
    }

    public void drawCropGrid(@NonNull Canvas canvas) {
        int i;
        int i2;
        if (this.o) {
            if (this.m == null && !this.h.isEmpty()) {
                this.m = new float[(this.j * 4) + (this.k * 4)];
                int i3 = 0;
                for (int i4 = 0; i4 < this.j; i4++) {
                    float[] fArr = this.m;
                    int i5 = i3 + 1;
                    RectF rectF = this.h;
                    fArr[i3] = rectF.left;
                    int i6 = i5 + 1;
                    float f = i4 + 1.0f;
                    float height = rectF.height() * (f / (this.j + 1));
                    RectF rectF2 = this.h;
                    fArr[i5] = height + rectF2.top;
                    float[] fArr2 = this.m;
                    int i7 = i6 + 1;
                    fArr2[i6] = rectF2.right;
                    i3 = i7 + 1;
                    fArr2[i7] = (rectF2.height() * (f / (this.j + 1))) + this.h.top;
                }
                for (int i8 = 0; i8 < this.k; i8++) {
                    float[] fArr3 = this.m;
                    int i9 = i3 + 1;
                    float f2 = i8 + 1.0f;
                    float width = this.h.width() * (f2 / (this.k + 1));
                    RectF rectF3 = this.h;
                    fArr3[i3] = width + rectF3.left;
                    float[] fArr4 = this.m;
                    int i10 = i9 + 1;
                    fArr4[i9] = rectF3.top;
                    int i11 = i10 + 1;
                    float width2 = rectF3.width() * (f2 / (this.k + 1));
                    RectF rectF4 = this.h;
                    fArr4[i10] = width2 + rectF4.left;
                    i3 = i11 + 1;
                    this.m[i11] = rectF4.bottom;
                }
            }
            float[] fArr5 = this.m;
            if (fArr5 != null) {
                canvas.drawLines(fArr5, this.t);
            }
        }
        if (this.n) {
            canvas.drawRect(this.h, this.u);
        }
        if (this.w != 0) {
            canvas.save();
            this.i.set(this.h);
            this.i.inset(this.C, -i);
            canvas.clipRect(this.i, Region.Op.DIFFERENCE);
            this.i.set(this.h);
            this.i.inset(-i2, this.C);
            canvas.clipRect(this.i, Region.Op.DIFFERENCE);
            canvas.drawRect(this.h, this.v);
            canvas.restore();
        }
    }

    public void drawDimmedLayer(@NonNull Canvas canvas) {
        canvas.save();
        if (this.p) {
            canvas.clipPath(this.r, Region.Op.DIFFERENCE);
        } else {
            canvas.clipRect(this.h, Region.Op.DIFFERENCE);
        }
        canvas.drawColor(this.q);
        canvas.restore();
        if (this.p) {
            canvas.drawCircle(this.h.centerX(), this.h.centerY(), Math.min(this.h.width(), this.h.height()) / 2.0f, this.s);
        }
    }

    public final void e() {
        this.mCropGridCorners = RectUtils.getCornersFromRect(this.h);
        this.mCropGridCenter = RectUtils.getCenterFromRect(this.h);
        this.m = null;
        this.r.reset();
        this.r.addCircle(this.h.centerX(), this.h.centerY(), Math.min(this.h.width(), this.h.height()) / 2.0f, Path.Direction.CW);
    }

    @NonNull
    public RectF getCropViewRect() {
        return this.h;
    }

    public int getFreestyleCropMode() {
        return this.w;
    }

    public OverlayViewChangeListener getOverlayViewChangeListener() {
        return this.D;
    }

    public void init() {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    @Deprecated
    public boolean isFreestyleCropEnabled() {
        return this.w == 1;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDimmedLayer(canvas);
        drawCropGrid(canvas);
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            this.mThisWidth = (getWidth() - getPaddingRight()) - paddingLeft;
            this.mThisHeight = (getHeight() - getPaddingBottom()) - paddingTop;
            if (this.E) {
                this.E = false;
                setTargetAspectRatio(this.l);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.h.isEmpty() && this.w != 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if ((motionEvent.getAction() & 255) == 0) {
                int a2 = a(x, y);
                this.z = a2;
                boolean z = a2 != -1;
                if (!z) {
                    this.x = -1.0f;
                    this.y = -1.0f;
                } else if (this.x < 0.0f) {
                    this.x = x;
                    this.y = y;
                }
                return z;
            } else if ((motionEvent.getAction() & 255) == 2 && motionEvent.getPointerCount() == 1 && this.z != -1) {
                float min = Math.min(Math.max(x, getPaddingLeft()), getWidth() - getPaddingRight());
                float min2 = Math.min(Math.max(y, getPaddingTop()), getHeight() - getPaddingBottom());
                d(min, min2);
                this.x = min;
                this.y = min2;
                return true;
            } else if ((motionEvent.getAction() & 255) == 1) {
                this.x = -1.0f;
                this.y = -1.0f;
                this.z = -1;
                OverlayViewChangeListener overlayViewChangeListener = this.D;
                if (overlayViewChangeListener != null) {
                    overlayViewChangeListener.onCropRectUpdated(this.h);
                }
            }
        }
        return false;
    }

    public void processStyledAttributes(@NonNull TypedArray typedArray) {
        this.p = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_circle_dimmed_layer, false);
        int color = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_dimmed_color, getResources().getColor(R.color.ucrop_color_default_dimmed));
        this.q = color;
        this.s.setColor(color);
        this.s.setStyle(Paint.Style.STROKE);
        this.s.setStrokeWidth(1.0f);
        b(typedArray);
        this.n = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_frame, true);
        c(typedArray);
        this.o = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_grid, true);
    }

    public void setCircleDimmedLayer(boolean z) {
        this.p = z;
    }

    public void setCropFrameColor(@ColorInt int i) {
        this.u.setColor(i);
    }

    public void setCropFrameStrokeWidth(@IntRange(from = 0) int i) {
        this.u.setStrokeWidth(i);
    }

    public void setCropGridColor(@ColorInt int i) {
        this.t.setColor(i);
    }

    public void setCropGridColumnCount(@IntRange(from = 0) int i) {
        this.k = i;
        this.m = null;
    }

    public void setCropGridRowCount(@IntRange(from = 0) int i) {
        this.j = i;
        this.m = null;
    }

    public void setCropGridStrokeWidth(@IntRange(from = 0) int i) {
        this.t.setStrokeWidth(i);
    }

    public void setDimmedColor(@ColorInt int i) {
        this.q = i;
    }

    @Deprecated
    public void setFreestyleCropEnabled(boolean z) {
        this.w = z ? 1 : 0;
    }

    public void setFreestyleCropMode(int i) {
        this.w = i;
        postInvalidate();
    }

    public void setOverlayViewChangeListener(OverlayViewChangeListener overlayViewChangeListener) {
        this.D = overlayViewChangeListener;
    }

    public void setShowCropFrame(boolean z) {
        this.n = z;
    }

    public void setShowCropGrid(boolean z) {
        this.o = z;
    }

    public void setTargetAspectRatio(float f) {
        this.l = f;
        if (this.mThisWidth > 0) {
            setupCropBounds();
            postInvalidate();
            return;
        }
        this.E = true;
    }

    public void setupCropBounds() {
        int i = this.mThisWidth;
        float f = this.l;
        int i2 = (int) (i / f);
        int i3 = this.mThisHeight;
        if (i2 > i3) {
            int i4 = (int) (i3 * f);
            int i5 = (i - i4) / 2;
            this.h.set(getPaddingLeft() + i5, getPaddingTop(), getPaddingLeft() + i4 + i5, getPaddingTop() + this.mThisHeight);
        } else {
            int i6 = (i3 - i2) / 2;
            this.h.set(getPaddingLeft(), getPaddingTop() + i6, getPaddingLeft() + this.mThisWidth, getPaddingTop() + i2 + i6);
        }
        OverlayViewChangeListener overlayViewChangeListener = this.D;
        if (overlayViewChangeListener != null) {
            overlayViewChangeListener.onCropRectUpdated(this.h);
        }
        e();
    }

    public OverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new RectF();
        this.i = new RectF();
        this.m = null;
        this.r = new Path();
        this.s = new Paint(1);
        this.t = new Paint(1);
        this.u = new Paint(1);
        this.v = new Paint(1);
        this.w = 0;
        this.x = -1.0f;
        this.y = -1.0f;
        this.z = -1;
        this.A = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_threshold);
        this.B = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_min_size);
        this.C = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_area_line_length);
        init();
    }
}
