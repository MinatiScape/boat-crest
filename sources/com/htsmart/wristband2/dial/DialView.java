package com.htsmart.wristband2.dial;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.htsmart.wristband2.R;
import com.htsmart.wristband2.dial.DialDrawer;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class DialView extends View {
    public static DialViewEngine A;
    public final Paint h;
    public final Paint i;
    public DialDrawer.Shape j;
    public DialDrawer.ScaleType k;
    public DialDrawer.Position l;
    public boolean m;
    public int n;
    public boolean o;
    public int p;
    public int q;
    public final RectF r;
    public final Matrix s;
    public final Matrix t;
    public Bitmap u;
    public Bitmap v;
    public int w;
    public final PorterDuffXfermode x;
    public Uri y;
    public Uri z;

    public DialView(Context context) {
        this(context, null);
    }

    public DialView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DialView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = 800;
        this.x = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        Paint paint = new Paint(7);
        this.h = paint;
        paint.setColor(-1184275);
        Paint paint2 = new Paint(5);
        this.i = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DialView, i, 0);
            this.j = obtainStyledAttributes.getInt(R.styleable.DialView_dv_shape, 0) == 0 ? DialDrawer.Shape.createRectangle(obtainStyledAttributes.getInt(R.styleable.DialView_dv_shape_width, 240), obtainStyledAttributes.getInt(R.styleable.DialView_dv_shape_height, 240), obtainStyledAttributes.getInt(R.styleable.DialView_dv_shape_corners, 0)) : DialDrawer.Shape.createCircle(obtainStyledAttributes.getInt(R.styleable.DialView_dv_shape_width, 240));
            this.k = DialDrawer.ScaleType.fromId(obtainStyledAttributes.getInt(R.styleable.DialView_dv_scale_type, DialDrawer.ScaleType.CENTER.getId()));
            this.l = DialDrawer.Position.fromId(obtainStyledAttributes.getInt(R.styleable.DialView_dv_style_position, DialDrawer.Position.TOP.getId()));
            boolean z = obtainStyledAttributes.getBoolean(R.styleable.DialView_dv_check_enabled, false);
            this.m = z;
            if (z) {
                int i2 = (int) (getResources().getDisplayMetrics().density * 4.0f);
                int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.DialView_dv_check_line_width, i2);
                int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.DialView_dv_check_line_padding, i2);
                int color = obtainStyledAttributes.getColor(R.styleable.DialView_dv_check_line_color, -366842);
                paint2.setStrokeWidth(dimensionPixelOffset);
                paint2.setColor(color);
                this.n = dimensionPixelOffset + dimensionPixelOffset2;
                this.o = obtainStyledAttributes.getBoolean(R.styleable.DialView_dv_checked, false);
            }
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.DialView_dv_background_src);
            if (drawable instanceof BitmapDrawable) {
                this.u = ((BitmapDrawable) drawable).getBitmap();
            }
            Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.DialView_dv_style_src);
            if (drawable2 instanceof BitmapDrawable) {
                this.v = ((BitmapDrawable) drawable2).getBitmap();
            }
            this.w = obtainStyledAttributes.getInt(R.styleable.DialView_dv_style_base_on_width, this.w);
            paint.setColor(obtainStyledAttributes.getColor(R.styleable.DialView_dv_background_none_color, paint.getColor()));
            obtainStyledAttributes.recycle();
        } else {
            this.j = DialDrawer.Shape.createRectangle(240, 240, 0);
            this.k = DialDrawer.ScaleType.CENTER;
            this.l = DialDrawer.Position.TOP;
        }
        this.r = new RectF();
        this.s = new Matrix();
        this.t = new Matrix();
    }

    public static void setEngine(DialViewEngine dialViewEngine) {
        A = dialViewEngine;
    }

    public final Bitmap a(@NonNull Uri uri) {
        try {
            InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
            if (openInputStream != null) {
                openInputStream.close();
            }
            return decodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clearBackgroundBitmap() {
        this.u = null;
        this.y = null;
        invalidate();
    }

    public void clearStyleBitmap() {
        this.v = null;
        this.z = null;
        invalidate();
    }

    public Bitmap createActualBackground() {
        return DialDrawer.createDialBackground(this.u, this.j, this.k);
    }

    public Bitmap createActualPreview(int i, int i2) {
        return DialDrawer.createDialPreview(this.u, this.v, this.j, this.k, this.l, this.w, i, i2);
    }

    public Bitmap getBackgroundBitmap() {
        return this.u;
    }

    public DialDrawer.ScaleType getBackgroundScaleType() {
        return this.k;
    }

    public DialDrawer.Shape getShape() {
        return this.j;
    }

    public int getStyleBaseOnWidth() {
        return this.w;
    }

    public Bitmap getStyleBitmap() {
        return this.v;
    }

    public DialDrawer.Position getStylePosition() {
        return this.l;
    }

    public boolean isChecked() {
        return this.o;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate((getWidth() - this.p) / 2.0f, (getHeight() - this.q) / 2.0f);
        int saveLayer = canvas.saveLayer(this.r, this.h, 31);
        if (this.j.isShapeCircle()) {
            canvas.drawCircle(this.r.centerX(), this.r.centerY(), this.r.width() / 2.0f, this.h);
        } else {
            canvas.drawRoundRect(this.r, this.j.corners(), this.j.corners(), this.h);
        }
        Bitmap bitmap = this.u;
        if (bitmap != null && !bitmap.isRecycled()) {
            float f = this.n;
            canvas.translate(f, f);
            this.h.setXfermode(this.x);
            canvas.drawBitmap(this.u, this.s, this.h);
            this.h.setXfermode(null);
        }
        canvas.restoreToCount(saveLayer);
        Bitmap bitmap2 = this.v;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            int save = canvas.save();
            float f2 = this.n;
            canvas.translate(f2, f2);
            canvas.drawBitmap(this.v, this.t, this.h);
            canvas.restoreToCount(save);
        }
        if (this.m && this.o) {
            float strokeWidth = this.i.getStrokeWidth() / 2.0f;
            if (!this.j.isShapeCircle()) {
                canvas.drawRect(strokeWidth, strokeWidth, this.p - strokeWidth, this.q - strokeWidth, this.i);
                return;
            }
            float f3 = this.p / 2.0f;
            canvas.drawCircle(f3, this.q / 2.0f, f3 - strokeWidth, this.i);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int defaultSize = View.getDefaultSize(getSuggestedMinimumWidth(), i);
        this.p = defaultSize;
        int i4 = defaultSize - (this.n * 2);
        int height = (int) (i4 * (this.j.height() / this.j.width()));
        this.q = (this.n * 2) + height;
        int defaultSize2 = View.getDefaultSize(Integer.MAX_VALUE, i2);
        if (this.q > defaultSize2) {
            this.q = defaultSize2;
            height = defaultSize2 - (this.n * 2);
            i4 = (int) (height * (this.j.width() / this.j.height()));
            this.p = (this.n * 2) + i4;
        }
        RectF rectF = this.r;
        float f = this.n;
        rectF.set(f, f, i4 + i3, i3 + height);
        DialDrawer.a(this.u, this.r.width(), this.r.height(), this.k, this.s);
        DialDrawer.b(this.v, this.w, this.r.width(), this.r.height(), this.l, this.t);
        setMeasuredDimension(this.p, this.q);
    }

    public void setBackgroundBitmap(@NonNull Bitmap bitmap) {
        if (this.u == bitmap) {
            return;
        }
        this.u = bitmap;
        this.y = null;
        DialDrawer.a(bitmap, this.r.width(), this.r.height(), this.k, this.s);
        invalidate();
    }

    public void setBackgroundNoneColor(int i) {
        if (i == this.h.getColor()) {
            return;
        }
        this.h.setColor(i);
        Bitmap bitmap = this.u;
        if (bitmap == null || bitmap.isRecycled()) {
            invalidate();
        }
    }

    public void setBackgroundScaleType(DialDrawer.ScaleType scaleType) {
        if (this.k == scaleType) {
            return;
        }
        this.k = scaleType;
        DialDrawer.a(this.u, this.r.width(), this.r.height(), this.k, this.s);
        invalidate();
    }

    public void setBackgroundSource(@NonNull Uri uri) {
        DialViewEngine dialViewEngine = A;
        if (dialViewEngine != null) {
            dialViewEngine.loadDialBackground(getContext(), this, uri);
        } else if (this.y == uri) {
        } else {
            setBackgroundBitmap(a(uri));
            this.y = uri;
        }
    }

    public void setCheckParams(boolean z, int i, int i2, int i3) {
        this.m = z;
        this.i.setStrokeWidth(i);
        this.i.setColor(i3);
        this.n = z ? i + i2 : 0;
        requestLayout();
    }

    public void setChecked(boolean z) {
        if (this.o == z) {
            return;
        }
        this.o = z;
        invalidate();
    }

    public void setShape(@NonNull DialDrawer.Shape shape) {
        if (this.j.equals(shape)) {
            return;
        }
        this.j = shape;
        requestLayout();
    }

    @Deprecated
    public void setStyleBitmap(@NonNull Bitmap bitmap) {
        if (this.v == bitmap) {
            return;
        }
        this.v = bitmap;
        this.z = null;
        DialDrawer.b(bitmap, this.w, this.r.width(), this.r.height(), this.l, this.t);
        invalidate();
    }

    public void setStyleBitmap(@NonNull Bitmap bitmap, int i) {
        if (this.v == bitmap && this.w == i) {
            return;
        }
        this.v = bitmap;
        this.w = i;
        this.z = null;
        DialDrawer.b(bitmap, i, this.r.width(), this.r.height(), this.l, this.t);
        invalidate();
    }

    public void setStylePosition(DialDrawer.Position position) {
        if (this.l == position) {
            return;
        }
        this.l = position;
        DialDrawer.b(this.v, this.w, this.r.width(), this.r.height(), this.l, this.t);
        invalidate();
    }

    @Deprecated
    public void setStyleSource(@NonNull Uri uri) {
        DialViewEngine dialViewEngine = A;
        if (dialViewEngine != null) {
            dialViewEngine.loadDialStyle(getContext(), this, uri);
        } else if (this.z == uri) {
        } else {
            setStyleBitmap(a(uri));
            this.z = uri;
        }
    }

    public void setStyleSource(@NonNull Uri uri, int i) {
        DialViewEngine dialViewEngine = A;
        if (dialViewEngine != null) {
            this.w = i;
            dialViewEngine.loadDialStyle(getContext(), this, uri, i);
        } else if (this.z == uri && this.w == i) {
        } else {
            setStyleBitmap(a(uri), i);
            this.z = uri;
        }
    }
}
