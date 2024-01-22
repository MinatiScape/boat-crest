package com.viewpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
/* loaded from: classes12.dex */
public class a extends LinearLayout {
    public static final int[] m = {16843049, 16843561, 16843562};
    public Drawable h;
    public int i;
    public int j;
    public int k;
    public int l;

    public a(Context context, int i) {
        super(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, m, i, 0);
        setDividerDrawable(obtainStyledAttributes.getDrawable(0));
        this.l = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.k = obtainStyledAttributes.getInteger(1, 0);
        obtainStyledAttributes.recycle();
    }

    public final void a(Canvas canvas) {
        int right;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8 && e(i)) {
                d(canvas, childAt.getLeft() - ((LinearLayout.LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
        }
        if (e(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                right = (getWidth() - getPaddingRight()) - this.i;
            } else {
                right = childAt2.getRight();
            }
            d(canvas, right);
        }
    }

    public final void b(Canvas canvas) {
        int bottom;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8 && e(i)) {
                c(canvas, childAt.getTop() - ((LinearLayout.LayoutParams) childAt.getLayoutParams()).topMargin);
            }
        }
        if (e(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.j;
            } else {
                bottom = childAt2.getBottom();
            }
            c(canvas, bottom);
        }
    }

    public final void c(Canvas canvas, int i) {
        this.h.setBounds(getPaddingLeft() + this.l, i, (getWidth() - getPaddingRight()) - this.l, this.j + i);
        this.h.draw(canvas);
    }

    public final void d(Canvas canvas, int i) {
        this.h.setBounds(i, getPaddingTop() + this.l, this.i + i, (getHeight() - getPaddingBottom()) - this.l);
        this.h.draw(canvas);
    }

    public final boolean e(int i) {
        if (i == 0 || i == getChildCount() || (this.k & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (e(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.j;
            } else {
                layoutParams.leftMargin = this.i;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && e(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.j;
            } else {
                layoutParams.rightMargin = this.i;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        if (this.h != null) {
            if (getOrientation() == 1) {
                b(canvas);
            } else {
                a(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.LinearLayout
    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.h) {
            return;
        }
        this.h = drawable;
        if (drawable != null) {
            this.i = drawable.getIntrinsicWidth();
            this.j = drawable.getIntrinsicHeight();
        } else {
            this.i = 0;
            this.j = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }
}
