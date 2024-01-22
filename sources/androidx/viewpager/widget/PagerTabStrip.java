package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class PagerTabStrip extends PagerTitleStrip {
    public int A;
    public int B;
    public int C;
    public final Paint D;
    public final Rect E;
    public int F;
    public boolean G;
    public boolean H;
    public int I;
    public boolean J;
    public float K;
    public float L;
    public int M;
    public int x;
    public int y;
    public int z;

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.h;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.h;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(@NonNull Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void c(int i, float f, boolean z) {
        Rect rect = this.E;
        int height = getHeight();
        int left = this.j.getLeft() - this.C;
        int right = this.j.getRight() + this.C;
        int i2 = height - this.y;
        rect.set(left, i2, right, height);
        super.c(i, f, z);
        this.F = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        rect.union(this.j.getLeft() - this.C, i2, this.j.getRight() + this.C, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.G;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.B);
    }

    @ColorInt
    public int getTabIndicatorColor() {
        return this.x;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.j.getLeft() - this.C;
        int right = this.j.getRight() + this.C;
        this.D.setColor((this.F << 24) | (this.x & 16777215));
        float f = height;
        canvas.drawRect(left, height - this.y, right, f, this.D);
        if (this.G) {
            this.D.setColor((-16777216) | (this.x & 16777215));
            canvas.drawRect(getPaddingLeft(), height - this.I, getWidth() - getPaddingRight(), f, this.D);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 || !this.J) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (action == 0) {
                this.K = x;
                this.L = y;
                this.J = false;
            } else if (action != 1) {
                if (action == 2 && (Math.abs(x - this.K) > this.M || Math.abs(y - this.L) > this.M)) {
                    this.J = true;
                }
            } else if (x < this.j.getLeft() - this.C) {
                ViewPager viewPager = this.h;
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            } else if (x > this.j.getRight() + this.C) {
                ViewPager viewPager2 = this.h;
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i) {
        super.setBackgroundColor(i);
        if (this.H) {
            return;
        }
        this.G = (i & ViewCompat.MEASURED_STATE_MASK) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.H) {
            return;
        }
        this.G = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.H) {
            return;
        }
        this.G = i == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.G = z;
        this.H = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.z;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(@ColorInt int i) {
        this.x = i;
        this.D.setColor(i);
        invalidate();
    }

    public void setTabIndicatorColorResource(@ColorRes int i) {
        setTabIndicatorColor(ContextCompat.getColor(getContext(), i));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.A;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public PagerTabStrip(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.D = paint;
        this.E = new Rect();
        this.F = 255;
        this.G = false;
        this.H = false;
        int i = this.u;
        this.x = i;
        paint.setColor(i);
        float f = context.getResources().getDisplayMetrics().density;
        this.y = (int) ((3.0f * f) + 0.5f);
        this.z = (int) ((6.0f * f) + 0.5f);
        this.A = (int) (64.0f * f);
        this.C = (int) ((16.0f * f) + 0.5f);
        this.I = (int) ((1.0f * f) + 0.5f);
        this.B = (int) ((f * 32.0f) + 0.5f);
        this.M = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.i.setFocusable(true);
        this.i.setOnClickListener(new a());
        this.k.setFocusable(true);
        this.k.setOnClickListener(new b());
        if (getBackground() == null) {
            this.G = true;
        }
    }
}
