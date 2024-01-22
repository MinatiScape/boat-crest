package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    public float i;
    public float j;
    public float k;
    public ConstraintLayout l;
    public float m;
    public float mComputedCenterX;
    public float mComputedCenterY;
    public float mComputedMaxX;
    public float mComputedMaxY;
    public float mComputedMinX;
    public float mComputedMinY;
    public float n;
    public boolean o;
    public View[] p;
    public float q;
    public float r;
    public boolean s;
    public boolean t;

    public Layer(Context context) {
        super(context);
        this.i = Float.NaN;
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.m = 1.0f;
        this.n = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.o = true;
        this.p = null;
        this.q = 0.0f;
        this.r = 0.0f;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout) {
        applyLayoutFeatures(constraintLayout);
    }

    public void calcCenters() {
        if (this.l == null) {
            return;
        }
        if (this.o || Float.isNaN(this.mComputedCenterX) || Float.isNaN(this.mComputedCenterY)) {
            if (!Float.isNaN(this.i) && !Float.isNaN(this.j)) {
                this.mComputedCenterY = this.j;
                this.mComputedCenterX = this.i;
                return;
            }
            View[] views = getViews(this.l);
            int left = views[0].getLeft();
            int top = views[0].getTop();
            int right = views[0].getRight();
            int bottom = views[0].getBottom();
            for (int i = 0; i < this.mCount; i++) {
                View view = views[i];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.mComputedMaxX = right;
            this.mComputedMaxY = bottom;
            this.mComputedMinX = left;
            this.mComputedMinY = top;
            if (Float.isNaN(this.i)) {
                this.mComputedCenterX = (left + right) / 2;
            } else {
                this.mComputedCenterX = this.i;
            }
            if (Float.isNaN(this.j)) {
                this.mComputedCenterY = (top + bottom) / 2;
            } else {
                this.mComputedCenterY = this.j;
            }
        }
    }

    public final void g() {
        int i;
        if (this.l == null || (i = this.mCount) == 0) {
            return;
        }
        View[] viewArr = this.p;
        if (viewArr == null || viewArr.length != i) {
            this.p = new View[i];
        }
        for (int i2 = 0; i2 < this.mCount; i2++) {
            this.p[i2] = this.l.getViewById(this.mIds[i2]);
        }
    }

    public final void h() {
        if (this.l == null) {
            return;
        }
        if (this.p == null) {
            g();
        }
        calcCenters();
        double radians = Float.isNaN(this.k) ? 0.0d : Math.toRadians(this.k);
        float sin = (float) Math.sin(radians);
        float cos = (float) Math.cos(radians);
        float f = this.m;
        float f2 = f * cos;
        float f3 = this.n;
        float f4 = (-f3) * sin;
        float f5 = f * sin;
        float f6 = f3 * cos;
        for (int i = 0; i < this.mCount; i++) {
            View view = this.p[i];
            float left = ((view.getLeft() + view.getRight()) / 2) - this.mComputedCenterX;
            float top = ((view.getTop() + view.getBottom()) / 2) - this.mComputedCenterY;
            view.setTranslationX((((f2 * left) + (f4 * top)) - left) + this.q);
            view.setTranslationY((((left * f5) + (f6 * top)) - top) + this.r);
            view.setScaleY(this.n);
            view.setScaleX(this.m);
            if (!Float.isNaN(this.k)) {
                view.setRotation(this.k);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mUseViewMeasure = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_android_visibility) {
                    this.s = true;
                } else if (index == R.styleable.ConstraintLayout_Layout_android_elevation) {
                    this.t = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.l = (ConstraintLayout) getParent();
        if (this.s || this.t) {
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i = 0; i < this.mCount; i++) {
                View viewById = this.l.getViewById(this.mIds[i]);
                if (viewById != null) {
                    if (this.s) {
                        viewById.setVisibility(visibility);
                    }
                    if (this.t && elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public void setPivotX(float f) {
        this.i = f;
        h();
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        this.j = f;
        h();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.k = f;
        h();
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.m = f;
        h();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.n = f;
        h();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.q = f;
        h();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.r = f;
        h();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        applyLayoutFeatures();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout) {
        g();
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        ConstraintWidget constraintWidget = ((ConstraintLayout.LayoutParams) getLayoutParams()).getConstraintWidget();
        constraintWidget.setWidth(0);
        constraintWidget.setHeight(0);
        calcCenters();
        layout(((int) this.mComputedMinX) - getPaddingLeft(), ((int) this.mComputedMinY) - getPaddingTop(), ((int) this.mComputedMaxX) + getPaddingRight(), ((int) this.mComputedMaxY) + getPaddingBottom());
        h();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePreDraw(ConstraintLayout constraintLayout) {
        this.l = constraintLayout;
        float rotation = getRotation();
        if (rotation == 0.0f) {
            if (Float.isNaN(this.k)) {
                return;
            }
            this.k = rotation;
            return;
        }
        this.k = rotation;
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = Float.NaN;
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.m = 1.0f;
        this.n = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.o = true;
        this.p = null;
        this.q = 0.0f;
        this.r = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = Float.NaN;
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.m = 1.0f;
        this.n = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.o = true;
        this.p = null;
        this.q = 0.0f;
        this.r = 0.0f;
    }
}
