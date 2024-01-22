package com.yalantis.ucrop.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.model.AspectRatio;
import java.util.Locale;
/* loaded from: classes12.dex */
public class AspectRatioTextView extends TextView {
    public final Rect h;
    public Paint i;
    public int j;
    public float k;
    public String l;
    public float m;
    public float n;

    public AspectRatioTextView(Context context) {
        this(context, null);
    }

    public final void a(@ColorInt int i) {
        Paint paint = this.i;
        if (paint != null) {
            paint.setColor(i);
        }
        setTextColor(new ColorStateList(new int[][]{new int[]{16842913}, new int[]{0}}, new int[]{i, ContextCompat.getColor(getContext(), R.color.ucrop_color_widget)}));
    }

    public final void b(@NonNull TypedArray typedArray) {
        setGravity(1);
        this.l = typedArray.getString(R.styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_title);
        this.m = typedArray.getFloat(R.styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_x, 0.0f);
        float f = typedArray.getFloat(R.styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_y, 0.0f);
        this.n = f;
        float f2 = this.m;
        if (f2 != 0.0f && f != 0.0f) {
            this.k = f2 / f;
        } else {
            this.k = 0.0f;
        }
        this.j = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_size_dot_scale_text_view);
        Paint paint = new Paint(1);
        this.i = paint;
        paint.setStyle(Paint.Style.FILL);
        c();
        a(getResources().getColor(R.color.ucrop_color_widget_active));
        typedArray.recycle();
    }

    public final void c() {
        if (TextUtils.isEmpty(this.l)) {
            setText(String.format(Locale.US, "%d:%d", Integer.valueOf((int) this.m), Integer.valueOf((int) this.n)));
        } else {
            setText(this.l);
        }
    }

    public final void d() {
        if (this.k != 0.0f) {
            float f = this.m;
            float f2 = this.n;
            this.m = f2;
            this.n = f;
            this.k = f2 / f;
        }
    }

    public float getAspectRatio(boolean z) {
        if (z) {
            d();
            c();
        }
        return this.k;
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelected()) {
            canvas.getClipBounds(this.h);
            Rect rect = this.h;
            float f = rect.bottom - (rect.top / 2.0f);
            int i = this.j;
            canvas.drawCircle((rect.right - rect.left) / 2.0f, f - (i * 1.5f), i / 2.0f, this.i);
        }
    }

    public void setActiveColor(@ColorInt int i) {
        a(i);
        invalidate();
    }

    public void setAspectRatio(@NonNull AspectRatio aspectRatio) {
        this.l = aspectRatio.getAspectRatioTitle();
        this.m = aspectRatio.getAspectRatioX();
        float aspectRatioY = aspectRatio.getAspectRatioY();
        this.n = aspectRatioY;
        float f = this.m;
        if (f != 0.0f && aspectRatioY != 0.0f) {
            this.k = f / aspectRatioY;
        } else {
            this.k = 0.0f;
        }
        c();
    }

    public AspectRatioTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AspectRatioTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new Rect();
        b(context.obtainStyledAttributes(attributeSet, R.styleable.ucrop_AspectRatioTextView));
    }

    @TargetApi(21)
    public AspectRatioTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.h = new Rect();
        b(context.obtainStyledAttributes(attributeSet, R.styleable.ucrop_AspectRatioTextView));
    }
}
