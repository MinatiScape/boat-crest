package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class MotionTelltales extends MockView {
    public Paint r;
    public MotionLayout s;
    public float[] t;
    public Matrix u;
    public int v;
    public int w;
    public float x;

    public MotionTelltales(Context context) {
        super(context);
        this.r = new Paint();
        this.t = new float[2];
        this.u = new Matrix();
        this.v = 0;
        this.w = -65281;
        this.x = 0.25f;
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MotionTelltales);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.MotionTelltales_telltales_tailColor) {
                    this.w = obtainStyledAttributes.getColor(index, this.w);
                } else if (index == R.styleable.MotionTelltales_telltales_velocityMode) {
                    this.v = obtainStyledAttributes.getInt(index, this.v);
                } else if (index == R.styleable.MotionTelltales_telltales_tailScale) {
                    this.x = obtainStyledAttributes.getFloat(index, this.x);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.r.setColor(this.w);
        this.r.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMatrix().invert(this.u);
        if (this.s == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.s = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        float[] fArr = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        for (int i = 0; i < 5; i++) {
            float f = fArr[i];
            for (int i2 = 0; i2 < 5; i2++) {
                float f2 = fArr[i2];
                this.s.getViewVelocity(this, f2, f, this.t, this.v);
                this.u.mapVectors(this.t);
                float f3 = width * f2;
                float f4 = height * f;
                float[] fArr2 = this.t;
                float f5 = fArr2[0];
                float f6 = this.x;
                float f7 = f4 - (fArr2[1] * f6);
                this.u.mapVectors(fArr2);
                canvas.drawLine(f3, f4, f3 - (f5 * f6), f7, this.r);
            }
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = new Paint();
        this.t = new float[2];
        this.u = new Matrix();
        this.v = 0;
        this.w = -65281;
        this.x = 0.25f;
        a(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = new Paint();
        this.t = new float[2];
        this.u = new Matrix();
        this.v = 0;
        this.w = -65281;
        this.x = 0.25f;
        a(context, attributeSet);
    }
}
