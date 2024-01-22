package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;
/* loaded from: classes10.dex */
class ClockFaceView extends c implements ClockHandView.OnRotateListener {
    public final ClockHandView k;
    public final Rect l;
    public final RectF m;
    public final SparseArray<TextView> n;
    public final AccessibilityDelegateCompat o;
    public final int[] p;
    public final float[] q;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public String[] v;
    public float w;
    public final ColorStateList x;

    /* loaded from: classes10.dex */
    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (ClockFaceView.this.isShown()) {
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ClockFaceView.this.c(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.k.g()) - ClockFaceView.this.r);
                return true;
            }
            return true;
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AccessibilityDelegateCompat {
        public b() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            int intValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
            if (intValue > 0) {
                accessibilityNodeInfoCompat.setTraversalAfter((View) ClockFaceView.this.n.get(intValue - 1));
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, intValue, 1, false, view.isSelected()));
            accessibilityNodeInfoCompat.setClickable(true);
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i == 16) {
                long uptimeMillis = SystemClock.uptimeMillis();
                float x = view.getX() + (view.getWidth() / 2.0f);
                float height = (view.getHeight() / 2.0f) + view.getY();
                ClockFaceView.this.k.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, x, height, 0));
                ClockFaceView.this.k.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 1, x, height, 0));
                return true;
            }
            return super.performAccessibilityAction(view, i, bundle);
        }
    }

    public ClockFaceView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    public static float l(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    @Override // com.google.android.material.timepicker.c
    public void c(int i) {
        if (i != b()) {
            super.c(i);
            this.k.k(b());
        }
    }

    public final void j() {
        RectF d = this.k.d();
        for (int i = 0; i < this.n.size(); i++) {
            TextView textView = this.n.get(i);
            if (textView != null) {
                textView.getDrawingRect(this.l);
                offsetDescendantRectToMyCoords(textView, this.l);
                textView.setSelected(d.contains(this.l.centerX(), this.l.centerY()));
                textView.getPaint().setShader(k(d, this.l, textView));
                textView.invalidate();
            }
        }
    }

    @Nullable
    public final RadialGradient k(RectF rectF, Rect rect, TextView textView) {
        this.m.set(rect);
        this.m.offset(textView.getPaddingLeft(), textView.getPaddingTop());
        if (RectF.intersects(rectF, this.m)) {
            return new RadialGradient(rectF.centerX() - this.m.left, rectF.centerY() - this.m.top, rectF.width() * 0.5f, this.p, this.q, Shader.TileMode.CLAMP);
        }
        return null;
    }

    public void m(String[] strArr, @StringRes int i) {
        this.v = strArr;
        n(i);
    }

    public final void n(@StringRes int i) {
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = this.n.size();
        for (int i2 = 0; i2 < Math.max(this.v.length, size); i2++) {
            TextView textView = this.n.get(i2);
            if (i2 >= this.v.length) {
                removeView(textView);
                this.n.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(R.layout.material_clockface_textview, (ViewGroup) this, false);
                    this.n.put(i2, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.v[i2]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i2));
                ViewCompat.setAccessibilityDelegate(textView, this.o);
                textView.setTextColor(this.x);
                if (i != 0) {
                    textView.setContentDescription(getResources().getString(i, this.v[i2]));
                }
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.v.length, false, 1));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        j();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int l = (int) (this.u / l(this.s / displayMetrics.heightPixels, this.t / displayMetrics.widthPixels, 1.0f));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(l, 1073741824);
        setMeasuredDimension(l, l);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float f, boolean z) {
        if (Math.abs(this.w - f) > 0.001f) {
            this.w = f;
            j();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = new Rect();
        this.m = new RectF();
        this.n = new SparseArray<>();
        this.q = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockFaceView, i, R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.ClockFaceView_clockNumberTextColor);
        this.x = colorStateList;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.k = clockHandView;
        this.r = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = colorStateList.getColorForState(new int[]{16842913}, colorStateList.getDefaultColor());
        this.p = new int[]{colorForState, colorForState, colorStateList.getDefaultColor()};
        clockHandView.b(this);
        int defaultColor = AppCompatResources.getColorStateList(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateList2 != null ? colorStateList2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new a());
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.o = new b();
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        m(strArr, 0);
        this.s = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.t = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.u = resources.getDimensionPixelSize(R.dimen.material_clock_size);
    }
}
