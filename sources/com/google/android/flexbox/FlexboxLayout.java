package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.flexbox.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class FlexboxLayout extends ViewGroup implements a {
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    @Nullable
    public Drawable n;
    @Nullable
    public Drawable o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int[] t;
    public SparseIntArray u;
    public b v;
    public List<FlexLine> w;
    public b.C0372b x;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface DividerMode {
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public final boolean a(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (this.w.get(i2).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.u == null) {
            this.u = new SparseIntArray(getChildCount());
        }
        this.t = this.v.n(view, i, layoutParams, this.u);
        super.addView(view, i, layoutParams);
    }

    public final boolean b(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View reorderedChildAt = getReorderedChildAt(i - i3);
            if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    public final void c(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int right;
        int left;
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.w.size();
        for (int i3 = 0; i3 < size; i3++) {
            FlexLine flexLine = this.w.get(i3);
            for (int i4 = 0; i4 < flexLine.h; i4++) {
                int i5 = flexLine.o + i4;
                View reorderedChildAt = getReorderedChildAt(i5);
                if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (g(i5, i4)) {
                        if (z) {
                            left = reorderedChildAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        } else {
                            left = (reorderedChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.s;
                        }
                        f(canvas, left, flexLine.b, flexLine.g);
                    }
                    if (i4 == flexLine.h - 1 && (this.q & 4) > 0) {
                        if (z) {
                            right = (reorderedChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.s;
                        } else {
                            right = reorderedChildAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        }
                        f(canvas, right, flexLine.b, flexLine.g);
                    }
                }
            }
            if (h(i3)) {
                if (z2) {
                    i2 = flexLine.d;
                } else {
                    i2 = flexLine.b - this.r;
                }
                e(canvas, paddingLeft, i2, max);
            }
            if (i(i3) && (this.p & 4) > 0) {
                if (z2) {
                    i = flexLine.b - this.r;
                } else {
                    i = flexLine.d;
                }
                e(canvas, paddingLeft, i, max);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int bottom;
        int top;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.w.size();
        for (int i3 = 0; i3 < size; i3++) {
            FlexLine flexLine = this.w.get(i3);
            for (int i4 = 0; i4 < flexLine.h; i4++) {
                int i5 = flexLine.o + i4;
                View reorderedChildAt = getReorderedChildAt(i5);
                if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (g(i5, i4)) {
                        if (z2) {
                            top = reorderedChildAt.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        } else {
                            top = (reorderedChildAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.r;
                        }
                        e(canvas, flexLine.f8167a, top, flexLine.g);
                    }
                    if (i4 == flexLine.h - 1 && (this.p & 4) > 0) {
                        if (z2) {
                            bottom = (reorderedChildAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.r;
                        } else {
                            bottom = reorderedChildAt.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        }
                        e(canvas, flexLine.f8167a, bottom, flexLine.g);
                    }
                }
            }
            if (h(i3)) {
                if (z) {
                    i2 = flexLine.c;
                } else {
                    i2 = flexLine.f8167a - this.s;
                }
                f(canvas, i2, paddingTop, max);
            }
            if (i(i3) && (this.q & 4) > 0) {
                if (z) {
                    i = flexLine.f8167a - this.s;
                } else {
                    i = flexLine.c;
                }
                f(canvas, i, paddingTop, max);
            }
        }
    }

    public final void e(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.n;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, i3 + i, this.r + i2);
        this.n.draw(canvas);
    }

    public final void f(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.o;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, this.s + i, i3 + i2);
        this.o.draw(canvas);
    }

    public final boolean g(int i, int i2) {
        return b(i, i2) ? isMainAxisDirectionHorizontal() ? (this.q & 1) != 0 : (this.p & 1) != 0 : isMainAxisDirectionHorizontal() ? (this.q & 2) != 0 : (this.p & 2) != 0;
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return this.l;
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.k;
    }

    @Override // com.google.android.flexbox.a
    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthCrossAxis(View view) {
        return 0;
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        int i3;
        int i4;
        if (isMainAxisDirectionHorizontal()) {
            i3 = g(i, i2) ? 0 + this.s : 0;
            if ((this.q & 4) <= 0) {
                return i3;
            }
            i4 = this.s;
        } else {
            i3 = g(i, i2) ? 0 + this.r : 0;
            if ((this.p & 4) <= 0) {
                return i3;
            }
            i4 = this.r;
        }
        return i3 + i4;
    }

    @Nullable
    public Drawable getDividerDrawableHorizontal() {
        return this.n;
    }

    @Nullable
    public Drawable getDividerDrawableVertical() {
        return this.o;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.h;
    }

    @Override // com.google.android.flexbox.a
    public View getFlexItemAt(int i) {
        return getChildAt(i);
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return getChildCount();
    }

    public List<FlexLine> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.w.size());
        for (FlexLine flexLine : this.w) {
            if (flexLine.getItemCountNotGone() != 0) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.a
    public List<FlexLine> getFlexLinesInternal() {
        return this.w;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.i;
    }

    public int getJustifyContent() {
        return this.j;
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        int i = Integer.MIN_VALUE;
        for (FlexLine flexLine : this.w) {
            i = Math.max(i, flexLine.e);
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int getMaxLine() {
        return this.m;
    }

    public View getReorderedChildAt(int i) {
        if (i >= 0) {
            int[] iArr = this.t;
            if (i >= iArr.length) {
                return null;
            }
            return getChildAt(iArr[i]);
        }
        return null;
    }

    @Override // com.google.android.flexbox.a
    public View getReorderedFlexItemAt(int i) {
        return getReorderedChildAt(i);
    }

    public int getShowDividerHorizontal() {
        return this.p;
    }

    public int getShowDividerVertical() {
        return this.q;
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int i;
        int i2;
        int size = this.w.size();
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            FlexLine flexLine = this.w.get(i4);
            if (h(i4)) {
                if (isMainAxisDirectionHorizontal()) {
                    i2 = this.r;
                } else {
                    i2 = this.s;
                }
                i3 += i2;
            }
            if (i(i4)) {
                if (isMainAxisDirectionHorizontal()) {
                    i = this.r;
                } else {
                    i = this.s;
                }
                i3 += i;
            }
            i3 += flexLine.g;
        }
        return i3;
    }

    public final boolean h(int i) {
        if (i < 0 || i >= this.w.size()) {
            return false;
        }
        return a(i) ? isMainAxisDirectionHorizontal() ? (this.p & 1) != 0 : (this.q & 1) != 0 : isMainAxisDirectionHorizontal() ? (this.p & 2) != 0 : (this.q & 2) != 0;
    }

    public final boolean i(int i) {
        if (i < 0 || i >= this.w.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.w.size(); i2++) {
            if (this.w.get(i2).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return isMainAxisDirectionHorizontal() ? (this.p & 4) != 0 : (this.q & 4) != 0;
    }

    @Override // com.google.android.flexbox.a
    public boolean isMainAxisDirectionHorizontal() {
        int i = this.h;
        return i == 0 || i == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(boolean r29, int r30, int r31, int r32, int r33) {
        /*
            Method dump skipped, instructions count: 557
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.j(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(boolean r30, boolean r31, int r32, int r33, int r34, int r35) {
        /*
            Method dump skipped, instructions count: 541
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.k(boolean, boolean, int, int, int, int):void");
    }

    public final void l(int i, int i2) {
        this.w.clear();
        this.x.a();
        this.v.c(this.x, i, i2);
        this.w = this.x.f8172a;
        this.v.p(i, i2);
        if (this.k == 3) {
            for (FlexLine flexLine : this.w) {
                int i3 = Integer.MIN_VALUE;
                for (int i4 = 0; i4 < flexLine.h; i4++) {
                    View reorderedChildAt = getReorderedChildAt(flexLine.o + i4);
                    if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        if (this.i != 2) {
                            i3 = Math.max(i3, reorderedChildAt.getMeasuredHeight() + Math.max(flexLine.l - reorderedChildAt.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                        } else {
                            i3 = Math.max(i3, reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + Math.max((flexLine.l - reorderedChildAt.getMeasuredHeight()) + reorderedChildAt.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin));
                        }
                    }
                }
                flexLine.g = i3;
            }
        }
        this.v.o(i, i2, getPaddingTop() + getPaddingBottom());
        this.v.X();
        n(this.h, i, i2, this.x.b);
    }

    public final void m(int i, int i2) {
        this.w.clear();
        this.x.a();
        this.v.f(this.x, i, i2);
        this.w = this.x.f8172a;
        this.v.p(i, i2);
        this.v.o(i, i2, getPaddingLeft() + getPaddingRight());
        this.v.X();
        n(this.h, i, i2, this.x.b);
    }

    public final void n(int i, int i2, int i3, int i4) {
        int sumOfCrossSize;
        int largestMainSize;
        int resolveSizeAndState;
        int resolveSizeAndState2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (i == 0 || i == 1) {
            sumOfCrossSize = getSumOfCrossSize() + getPaddingTop() + getPaddingBottom();
            largestMainSize = getLargestMainSize();
        } else if (i != 2 && i != 3) {
            throw new IllegalArgumentException("Invalid flex direction: " + i);
        } else {
            sumOfCrossSize = getLargestMainSize();
            largestMainSize = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < largestMainSize) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            } else {
                size = largestMainSize;
            }
            resolveSizeAndState = View.resolveSizeAndState(size, i2, i4);
        } else if (mode == 0) {
            resolveSizeAndState = View.resolveSizeAndState(largestMainSize, i2, i4);
        } else if (mode == 1073741824) {
            if (size < largestMainSize) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            }
            resolveSizeAndState = View.resolveSizeAndState(size, i2, i4);
        } else {
            throw new IllegalStateException("Unknown width mode is set: " + mode);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < sumOfCrossSize) {
                i4 = View.combineMeasuredStates(i4, 256);
            } else {
                size2 = sumOfCrossSize;
            }
            resolveSizeAndState2 = View.resolveSizeAndState(size2, i3, i4);
        } else if (mode2 == 0) {
            resolveSizeAndState2 = View.resolveSizeAndState(sumOfCrossSize, i3, i4);
        } else if (mode2 == 1073741824) {
            if (size2 < sumOfCrossSize) {
                i4 = View.combineMeasuredStates(i4, 256);
            }
            resolveSizeAndState2 = View.resolveSizeAndState(size2, i3, i4);
        } else {
            throw new IllegalStateException("Unknown height mode is set: " + mode2);
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    public final void o() {
        if (this.n == null && this.o == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.o == null && this.n == null) {
            return;
        }
        if (this.p == 0 && this.q == 0) {
            return;
        }
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i = this.h;
        if (i == 0) {
            c(canvas, layoutDirection == 1, this.i == 2);
        } else if (i == 1) {
            c(canvas, layoutDirection != 1, this.i == 2);
        } else if (i == 2) {
            boolean z = layoutDirection == 1;
            if (this.i == 2) {
                z = !z;
            }
            d(canvas, z, false);
        } else if (i != 3) {
        } else {
            boolean z2 = layoutDirection == 1;
            if (this.i == 2) {
                z2 = !z2;
            }
            d(canvas, z2, true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i5 = this.h;
        if (i5 == 0) {
            j(layoutDirection == 1, i, i2, i3, i4);
        } else if (i5 == 1) {
            j(layoutDirection != 1, i, i2, i3, i4);
        } else if (i5 == 2) {
            z2 = layoutDirection == 1;
            k(this.i == 2 ? !z2 : z2, false, i, i2, i3, i4);
        } else if (i5 == 3) {
            z2 = layoutDirection == 1;
            k(this.i == 2 ? !z2 : z2, true, i, i2, i3, i4);
        } else {
            throw new IllegalStateException("Invalid flex direction is set: " + this.h);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.u == null) {
            this.u = new SparseIntArray(getChildCount());
        }
        if (this.v.O(this.u)) {
            this.t = this.v.m(this.u);
        }
        int i3 = this.h;
        if (i3 == 0 || i3 == 1) {
            l(i, i2);
        } else if (i3 != 2 && i3 != 3) {
            throw new IllegalStateException("Invalid value for the flex direction is set: " + this.h);
        } else {
            m(i, i2);
        }
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine) {
        if (g(i, i2)) {
            if (isMainAxisDirectionHorizontal()) {
                int i3 = flexLine.e;
                int i4 = this.s;
                flexLine.e = i3 + i4;
                flexLine.f += i4;
                return;
            }
            int i5 = flexLine.e;
            int i6 = this.r;
            flexLine.e = i5 + i6;
            flexLine.f += i6;
        }
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexLineAdded(FlexLine flexLine) {
        if (isMainAxisDirectionHorizontal()) {
            if ((this.q & 4) > 0) {
                int i = flexLine.e;
                int i2 = this.s;
                flexLine.e = i + i2;
                flexLine.f += i2;
            }
        } else if ((this.p & 4) > 0) {
            int i3 = flexLine.e;
            int i4 = this.r;
            flexLine.e = i3 + i4;
            flexLine.f += i4;
        }
    }

    public void setAlignContent(int i) {
        if (this.l != i) {
            this.l = i;
            requestLayout();
        }
    }

    public void setAlignItems(int i) {
        if (this.k != i) {
            this.k = i;
            requestLayout();
        }
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(@Nullable Drawable drawable) {
        if (drawable == this.n) {
            return;
        }
        this.n = drawable;
        if (drawable != null) {
            this.r = drawable.getIntrinsicHeight();
        } else {
            this.r = 0;
        }
        o();
        requestLayout();
    }

    public void setDividerDrawableVertical(@Nullable Drawable drawable) {
        if (drawable == this.o) {
            return;
        }
        this.o = drawable;
        if (drawable != null) {
            this.s = drawable.getIntrinsicWidth();
        } else {
            this.s = 0;
        }
        o();
        requestLayout();
    }

    public void setFlexDirection(int i) {
        if (this.h != i) {
            this.h = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<FlexLine> list) {
        this.w = list;
    }

    public void setFlexWrap(int i) {
        if (this.i != i) {
            this.i = i;
            requestLayout();
        }
    }

    public void setJustifyContent(int i) {
        if (this.j != i) {
            this.j = i;
            requestLayout();
        }
    }

    public void setMaxLine(int i) {
        if (this.m != i) {
            this.m = i;
            requestLayout();
        }
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.p) {
            this.p = i;
            requestLayout();
        }
    }

    public void setShowDividerVertical(int i) {
        if (i != this.q) {
            this.q = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public void updateViewCache(int i, View view) {
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = -1;
        this.v = new b(this);
        this.w = new ArrayList();
        this.x = new b.C0372b();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout, i, 0);
        this.h = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.i = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.j = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.k = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignItems, 0);
        this.l = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignContent, 0);
        this.m = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_maxLine, -1);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (i2 != 0) {
            this.q = i2;
            this.p = i2;
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (i3 != 0) {
            this.q = i3;
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (i4 != 0) {
            this.p = i4;
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* loaded from: classes6.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new a();
        public int h;
        public float i;
        public float j;
        public int k;
        public float l;
        public int m;
        public int n;
        public int o;
        public int p;
        public boolean q;

        /* loaded from: classes6.dex */
        public class a implements Parcelable.Creator<LayoutParams> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = 1;
            this.i = 0.0f;
            this.j = 1.0f;
            this.k = -1;
            this.l = -1.0f;
            this.m = -1;
            this.n = -1;
            this.o = 16777215;
            this.p = 16777215;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout_Layout);
            this.h = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.i = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.j = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.k = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.l = obtainStyledAttributes.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.m = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, -1);
            this.n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, -1);
            this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.p = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.q = obtainStyledAttributes.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            obtainStyledAttributes.recycle();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getAlignSelf() {
            return this.k;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexBasisPercent() {
            return this.l;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexGrow() {
            return this.i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexShrink() {
            return this.j;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginBottom() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginLeft() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginRight() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginTop() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxHeight() {
            return this.p;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxWidth() {
            return this.o;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinHeight() {
            return this.n;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinWidth() {
            return this.m;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return this.h;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean isWrapBefore() {
            return this.q;
        }

        public void setAlignSelf(int i) {
            this.k = i;
        }

        public void setFlexBasisPercent(float f) {
            this.l = f;
        }

        public void setFlexGrow(float f) {
            this.i = f;
        }

        public void setFlexShrink(float f) {
            this.j = f;
        }

        public void setHeight(int i) {
            ((ViewGroup.MarginLayoutParams) this).height = i;
        }

        public void setMaxHeight(int i) {
            this.p = i;
        }

        public void setMaxWidth(int i) {
            this.o = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinHeight(int i) {
            this.n = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinWidth(int i) {
            this.m = i;
        }

        public void setOrder(int i) {
            this.h = i;
        }

        public void setWidth(int i) {
            ((ViewGroup.MarginLayoutParams) this).width = i;
        }

        public void setWrapBefore(boolean z) {
            this.q = z;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.h);
            parcel.writeFloat(this.i);
            parcel.writeFloat(this.j);
            parcel.writeInt(this.k);
            parcel.writeFloat(this.l);
            parcel.writeInt(this.m);
            parcel.writeInt(this.n);
            parcel.writeInt(this.o);
            parcel.writeInt(this.p);
            parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.h = 1;
            this.i = 0.0f;
            this.j = 1.0f;
            this.k = -1;
            this.l = -1.0f;
            this.m = -1;
            this.n = -1;
            this.o = 16777215;
            this.p = 16777215;
            this.h = layoutParams.h;
            this.i = layoutParams.i;
            this.j = layoutParams.j;
            this.k = layoutParams.k;
            this.l = layoutParams.l;
            this.m = layoutParams.m;
            this.n = layoutParams.n;
            this.o = layoutParams.o;
            this.p = layoutParams.p;
            this.q = layoutParams.q;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.h = 1;
            this.i = 0.0f;
            this.j = 1.0f;
            this.k = -1;
            this.l = -1.0f;
            this.m = -1;
            this.n = -1;
            this.o = 16777215;
            this.p = 16777215;
        }

        public LayoutParams(int i, int i2) {
            super(new ViewGroup.LayoutParams(i, i2));
            this.h = 1;
            this.i = 0.0f;
            this.j = 1.0f;
            this.k = -1;
            this.l = -1.0f;
            this.m = -1;
            this.n = -1;
            this.o = 16777215;
            this.p = 16777215;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.h = 1;
            this.i = 0.0f;
            this.j = 1.0f;
            this.k = -1;
            this.l = -1.0f;
            this.m = -1;
            this.n = -1;
            this.o = 16777215;
            this.p = 16777215;
        }

        public LayoutParams(Parcel parcel) {
            super(0, 0);
            this.h = 1;
            this.i = 0.0f;
            this.j = 1.0f;
            this.k = -1;
            this.l = -1.0f;
            this.m = -1;
            this.n = -1;
            this.o = 16777215;
            this.p = 16777215;
            this.h = parcel.readInt();
            this.i = parcel.readFloat();
            this.j = parcel.readFloat();
            this.k = parcel.readInt();
            this.l = parcel.readFloat();
            this.m = parcel.readInt();
            this.n = parcel.readInt();
            this.o = parcel.readInt();
            this.p = parcel.readInt();
            this.q = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }
}
