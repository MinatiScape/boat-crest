package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {
    public boolean h;
    public View i;
    public View j;
    public View k;
    public Drawable l;
    public Drawable m;
    public Drawable n;
    public boolean o;
    public boolean p;
    public int q;

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class a {
        public static void a(ActionBarContainer actionBarContainer) {
            actionBarContainer.invalidateOutline();
        }
    }

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public final int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.l;
        if (drawable != null && drawable.isStateful()) {
            this.l.setState(getDrawableState());
        }
        Drawable drawable2 = this.m;
        if (drawable2 != null && drawable2.isStateful()) {
            this.m.setState(getDrawableState());
        }
        Drawable drawable3 = this.n;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.n.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.l;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.m;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.n;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.j = findViewById(R.id.action_bar);
        this.k = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.h || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        View view = this.i;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = (view == null || view.getVisibility() == 8) ? false : true;
        if (view != null && view.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            int i5 = ((FrameLayout.LayoutParams) view.getLayoutParams()).bottomMargin;
            view.layout(i, (measuredHeight - view.getMeasuredHeight()) - i5, i3, measuredHeight - i5);
        }
        if (this.o) {
            Drawable drawable2 = this.n;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
            z2 = z3;
        } else {
            if (this.l != null) {
                if (this.j.getVisibility() == 0) {
                    this.l.setBounds(this.j.getLeft(), this.j.getTop(), this.j.getRight(), this.j.getBottom());
                } else {
                    View view2 = this.k;
                    if (view2 != null && view2.getVisibility() == 0) {
                        this.l.setBounds(this.k.getLeft(), this.k.getTop(), this.k.getRight(), this.k.getBottom());
                    } else {
                        this.l.setBounds(0, 0, 0, 0);
                    }
                }
                z3 = true;
            }
            this.p = z4;
            if (z4 && (drawable = this.m) != null) {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            z2 = z3;
        }
        if (z2) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int a2;
        int i3;
        if (this.j == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i3 = this.q) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i3, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.j == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        View view = this.i;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        if (!b(this.j)) {
            a2 = a(this.j);
        } else {
            a2 = !b(this.k) ? a(this.k) : 0;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min(a2 + a(this.i), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.l;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.l);
        }
        this.l = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.j;
            if (view != null) {
                this.l.setBounds(view.getLeft(), this.j.getTop(), this.j.getRight(), this.j.getBottom());
            }
        }
        boolean z = true;
        if (!this.o ? this.l != null || this.m != null : this.n != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            a.a(this);
        }
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.n;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.n);
        }
        this.n = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.o && (drawable2 = this.n) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.o ? !(this.l != null || this.m != null) : this.n == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            a.a(this);
        }
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.m;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.m);
        }
        this.m = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.p && (drawable2 = this.m) != null) {
                drawable2.setBounds(this.i.getLeft(), this.i.getTop(), this.i.getRight(), this.i.getBottom());
            }
        }
        boolean z = true;
        if (!this.o ? this.l != null || this.m != null : this.n != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            a.a(this);
        }
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.i;
        if (view != null) {
            removeView(view);
        }
        this.i = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.h = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.l;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.m;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.n;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.l && !this.o) || (drawable == this.m && this.p) || ((drawable == this.n && this.o) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ViewCompat.setBackground(this, new androidx.appcompat.widget.a(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBar);
        this.l = obtainStyledAttributes.getDrawable(R.styleable.ActionBar_background);
        this.m = obtainStyledAttributes.getDrawable(R.styleable.ActionBar_backgroundStacked);
        this.q = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBar_height, -1);
        boolean z = true;
        if (getId() == R.id.split_action_bar) {
            this.o = true;
            this.n = obtainStyledAttributes.getDrawable(R.styleable.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        if (!this.o ? this.l != null || this.m != null : this.n != null) {
            z = false;
        }
        setWillNotDraw(z);
    }
}
