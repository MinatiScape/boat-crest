package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.clevertap.android.sdk.Constants;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends BaseOnChangeListener<S>, T extends BaseOnSliderTouchListener<S>> extends View {
    public static final String l0 = BaseSlider.class.getSimpleName();
    public static final int m0 = R.style.Widget_MaterialComponents_Slider;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public float I;
    public MotionEvent J;
    public LabelFormatter K;
    public boolean L;
    public float M;
    public float N;
    public ArrayList<Float> O;
    public int P;
    public int Q;
    public float R;
    public float[] S;
    public boolean T;
    public int U;
    public boolean V;
    public boolean W;
    public boolean a0;
    @NonNull
    public ColorStateList b0;
    @NonNull
    public ColorStateList c0;
    @NonNull
    public ColorStateList d0;
    @NonNull
    public ColorStateList e0;
    @NonNull
    public ColorStateList f0;
    @NonNull
    public final MaterialShapeDrawable g0;
    @NonNull
    public final Paint h;
    @Nullable
    public Drawable h0;
    @NonNull
    public final Paint i;
    @NonNull
    public List<Drawable> i0;
    @NonNull
    public final Paint j;
    public float j0;
    @NonNull
    public final Paint k;
    public int k0;
    @NonNull
    public final Paint l;
    @NonNull
    public final Paint m;
    @NonNull
    public final e n;
    public final AccessibilityManager o;
    public BaseSlider<S, L, T>.d p;
    @NonNull
    public final f q;
    @NonNull
    public final List<TooltipDrawable> r;
    @NonNull
    public final List<L> s;
    @NonNull
    public final List<T> t;
    public boolean u;
    public ValueAnimator v;
    public ValueAnimator w;
    public final int x;
    public int y;
    public int z;

    /* loaded from: classes10.dex */
    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new a();
        public float h;
        public float i;
        public ArrayList<Float> j;
        public float k;
        public boolean l;

        /* loaded from: classes10.dex */
        public class a implements Parcelable.Creator<SliderState> {
            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: a */
            public SliderState createFromParcel(@NonNull Parcel parcel) {
                return new SliderState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: b */
            public SliderState[] newArray(int i) {
                return new SliderState[i];
            }
        }

        public /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.h);
            parcel.writeFloat(this.i);
            parcel.writeList(this.j);
            parcel.writeFloat(this.k);
            parcel.writeBooleanArray(new boolean[]{this.l});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        public SliderState(@NonNull Parcel parcel) {
            super(parcel);
            this.h = parcel.readFloat();
            this.i = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.j = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.k = parcel.readFloat();
            this.l = parcel.createBooleanArray()[0];
        }
    }

    /* loaded from: classes10.dex */
    public class a implements f {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AttributeSet f10367a;
        public final /* synthetic */ int b;

        public a(AttributeSet attributeSet, int i) {
            this.f10367a = attributeSet;
            this.b = i;
        }

        @Override // com.google.android.material.slider.BaseSlider.f
        public TooltipDrawable a() {
            TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(BaseSlider.this.getContext(), this.f10367a, R.styleable.Slider, this.b, BaseSlider.m0, new int[0]);
            TooltipDrawable U = BaseSlider.U(BaseSlider.this.getContext(), obtainStyledAttributes);
            obtainStyledAttributes.recycle();
            return U;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            for (TooltipDrawable tooltipDrawable : BaseSlider.this.r) {
                tooltipDrawable.setRevealFraction(floatValue);
            }
            ViewCompat.postInvalidateOnAnimation(BaseSlider.this);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            for (TooltipDrawable tooltipDrawable : BaseSlider.this.r) {
                ViewUtils.getContentViewOverlay(BaseSlider.this).remove(tooltipDrawable);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class e extends ExploreByTouchHelper {
        public final BaseSlider<?, ?, ?> q;
        public final Rect r;

        public e(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.r = new Rect();
            this.q = baseSlider;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float f, float f2) {
            for (int i = 0; i < this.q.getValues().size(); i++) {
                this.q.f0(i, this.r);
                if (this.r.contains((int) f, (int) f2)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> list) {
            for (int i = 0; i < this.q.getValues().size(); i++) {
                list.add(Integer.valueOf(i));
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            if (this.q.isEnabled()) {
                if (i2 == 4096 || i2 == 8192) {
                    float l = this.q.l(20);
                    if (i2 == 8192) {
                        l = -l;
                    }
                    if (this.q.I()) {
                        l = -l;
                    }
                    if (this.q.d0(i, MathUtils.clamp(this.q.getValues().get(i).floatValue() + l, this.q.getValueFrom(), this.q.getValueTo()))) {
                        this.q.g0();
                        this.q.postInvalidate();
                        invalidateVirtualView(i);
                        return true;
                    }
                    return false;
                }
                if (i2 == 16908349 && bundle != null && bundle.containsKey(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE)) {
                    if (this.q.d0(i, bundle.getFloat(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE))) {
                        this.q.g0();
                        this.q.postInvalidate();
                        invalidateVirtualView(i);
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
            List<Float> values = this.q.getValues();
            float floatValue = values.get(i).floatValue();
            float valueFrom = this.q.getValueFrom();
            float valueTo = this.q.getValueTo();
            if (this.q.isEnabled()) {
                if (floatValue > valueFrom) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (floatValue < valueTo) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
            accessibilityNodeInfoCompat.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, valueFrom, valueTo, floatValue));
            accessibilityNodeInfoCompat.setClassName(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.q.getContentDescription() != null) {
                sb.append(this.q.getContentDescription());
                sb.append(Constants.SEPARATOR_COMMA);
            }
            if (values.size() > 1) {
                sb.append(x(i));
                sb.append(this.q.A(floatValue));
            }
            accessibilityNodeInfoCompat.setContentDescription(sb.toString());
            this.q.f0(i, this.r);
            accessibilityNodeInfoCompat.setBoundsInParent(this.r);
        }

        @NonNull
        public final String x(int i) {
            if (i == this.q.getValues().size() - 1) {
                return this.q.getContext().getString(R.string.material_slider_range_end);
            }
            return i == 0 ? this.q.getContext().getString(R.string.material_slider_range_start) : "";
        }
    }

    /* loaded from: classes10.dex */
    public interface f {
        TooltipDrawable a();
    }

    public BaseSlider(@NonNull Context context) {
        this(context, null);
    }

    public static float B(ValueAnimator valueAnimator, float f2) {
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return f2;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        valueAnimator.cancel();
        return floatValue;
    }

    @NonNull
    public static TooltipDrawable U(@NonNull Context context, @NonNull TypedArray typedArray) {
        return TooltipDrawable.createFromAttributes(context, null, 0, typedArray.getResourceId(R.styleable.Slider_labelStyle, R.style.Widget_MaterialComponents_Tooltip));
    }

    public static int V(float[] fArr, float f2) {
        return Math.round(f2 * ((fArr.length / 2) - 1));
    }

    private float[] getActiveRange() {
        float floatValue = ((Float) Collections.max(getValues())).floatValue();
        float floatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.O.size() == 1) {
            floatValue2 = this.M;
        }
        float Q = Q(floatValue2);
        float Q2 = Q(floatValue);
        return I() ? new float[]{Q2, Q} : new float[]{Q, Q2};
    }

    private float getValueOfTouchPosition() {
        double c0 = c0(this.j0);
        if (I()) {
            c0 = 1.0d - c0;
        }
        float f2 = this.N;
        float f3 = this.M;
        return (float) ((c0 * (f2 - f3)) + f3);
    }

    private float getValueOfTouchPositionAbsolute() {
        float f2 = this.j0;
        if (I()) {
            f2 = 1.0f - f2;
        }
        float f3 = this.N;
        float f4 = this.M;
        return (f2 * (f3 - f4)) + f4;
    }

    private void setValuesInternal(@NonNull ArrayList<Float> arrayList) {
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
            if (this.O.size() == arrayList.size() && this.O.equals(arrayList)) {
                return;
            }
            this.O = arrayList;
            this.a0 = true;
            this.Q = 0;
            g0();
            o();
            s();
            postInvalidate();
            return;
        }
        throw new IllegalArgumentException("At least one value must be set");
    }

    public final String A(float f2) {
        if (hasLabelFormatter()) {
            return this.K.getFormattedValue(f2);
        }
        return String.format(((float) ((int) f2)) == f2 ? "%.0f" : "%.2f", Float.valueOf(f2));
    }

    public final float C(int i, float f2) {
        float minSeparation = getMinSeparation();
        if (this.k0 == 0) {
            minSeparation = q(minSeparation);
        }
        if (I()) {
            minSeparation = -minSeparation;
        }
        int i2 = i + 1;
        int i3 = i - 1;
        return MathUtils.clamp(f2, i3 < 0 ? this.M : this.O.get(i3).floatValue() + minSeparation, i2 >= this.O.size() ? this.N : this.O.get(i2).floatValue() - minSeparation);
    }

    @ColorInt
    public final int D(@NonNull ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public final Drawable E(Drawable drawable) {
        Drawable newDrawable = drawable.mutate().getConstantState().newDrawable();
        h(newDrawable);
        return newDrawable;
    }

    public final void F() {
        this.h.setStrokeWidth(this.C);
        this.i.setStrokeWidth(this.C);
        this.l.setStrokeWidth(this.C / 2.0f);
        this.m.setStrokeWidth(this.C / 2.0f);
    }

    public final boolean G() {
        ViewParent parent = getParent();
        while (true) {
            boolean z = false;
            if (!(parent instanceof ViewGroup)) {
                return false;
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) {
                z = true;
            }
            if (z && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
            parent = parent.getParent();
        }
    }

    public final boolean H(float f2) {
        double doubleValue = new BigDecimal(Float.toString(f2)).divide(new BigDecimal(Float.toString(this.R)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(doubleValue)) - doubleValue) < 1.0E-4d;
    }

    public final boolean I() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    public final void J(@NonNull Resources resources) {
        this.A = resources.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.y = dimensionPixelOffset;
        this.D = dimensionPixelOffset;
        this.z = resources.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.E = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_top);
        this.H = resources.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
    }

    public final void K() {
        if (this.R <= 0.0f) {
            return;
        }
        i0();
        int min = Math.min((int) (((this.N - this.M) / this.R) + 1.0f), (this.U / (this.C * 2)) + 1);
        float[] fArr = this.S;
        if (fArr == null || fArr.length != min * 2) {
            this.S = new float[min * 2];
        }
        float f2 = this.U / (min - 1);
        for (int i = 0; i < min * 2; i += 2) {
            float[] fArr2 = this.S;
            fArr2[i] = this.D + ((i / 2) * f2);
            fArr2[i + 1] = m();
        }
    }

    public final void L(@NonNull Canvas canvas, int i, int i2) {
        if (a0()) {
            int Q = (int) (this.D + (Q(this.O.get(this.Q).floatValue()) * i));
            if (Build.VERSION.SDK_INT < 28) {
                int i3 = this.G;
                canvas.clipRect(Q - i3, i2 - i3, Q + i3, i3 + i2, Region.Op.UNION);
            }
            canvas.drawCircle(Q, i2, this.G, this.k);
        }
    }

    public final void M(@NonNull Canvas canvas) {
        if (!this.T || this.R <= 0.0f) {
            return;
        }
        float[] activeRange = getActiveRange();
        int V = V(this.S, activeRange[0]);
        int V2 = V(this.S, activeRange[1]);
        int i = V * 2;
        canvas.drawPoints(this.S, 0, i, this.l);
        int i2 = V2 * 2;
        canvas.drawPoints(this.S, i, i2 - i, this.m);
        float[] fArr = this.S;
        canvas.drawPoints(fArr, i2, fArr.length - i2, this.l);
    }

    public final void N() {
        this.D = this.y + Math.max(this.F - this.z, 0);
        if (ViewCompat.isLaidOut(this)) {
            h0(getWidth());
        }
    }

    public final boolean O(int i) {
        int i2 = this.Q;
        int clamp = (int) MathUtils.clamp(i2 + i, 0L, this.O.size() - 1);
        this.Q = clamp;
        if (clamp == i2) {
            return false;
        }
        if (this.P != -1) {
            this.P = clamp;
        }
        g0();
        postInvalidate();
        return true;
    }

    public final boolean P(int i) {
        if (I()) {
            i = i == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i;
        }
        return O(i);
    }

    public final float Q(float f2) {
        float f3 = this.M;
        float f4 = (f2 - f3) / (this.N - f3);
        return I() ? 1.0f - f4 : f4;
    }

    public final Boolean R(int i, @NonNull KeyEvent keyEvent) {
        if (i != 61) {
            if (i != 66) {
                if (i != 81) {
                    if (i == 69) {
                        O(-1);
                        return Boolean.TRUE;
                    } else if (i != 70) {
                        switch (i) {
                            case 21:
                                P(-1);
                                return Boolean.TRUE;
                            case 22:
                                P(1);
                                return Boolean.TRUE;
                            case 23:
                                break;
                            default:
                                return null;
                        }
                    }
                }
                O(1);
                return Boolean.TRUE;
            }
            this.P = this.Q;
            postInvalidate();
            return Boolean.TRUE;
        } else if (keyEvent.hasNoModifiers()) {
            return Boolean.valueOf(O(1));
        } else {
            if (keyEvent.isShiftPressed()) {
                return Boolean.valueOf(O(-1));
            }
            return Boolean.FALSE;
        }
    }

    public final void S() {
        for (T t : this.t) {
            t.onStartTrackingTouch(this);
        }
    }

    public final void T() {
        for (T t : this.t) {
            t.onStopTrackingTouch(this);
        }
    }

    public final void W(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Slider, i, m0, new int[0]);
        this.M = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueFrom, 0.0f);
        this.N = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.M));
        this.R = obtainStyledAttributes.getFloat(R.styleable.Slider_android_stepSize, 0.0f);
        int i2 = R.styleable.Slider_trackColor;
        boolean hasValue = obtainStyledAttributes.hasValue(i2);
        int i3 = hasValue ? i2 : R.styleable.Slider_trackColorInactive;
        if (!hasValue) {
            i2 = R.styleable.Slider_trackColorActive;
        }
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, i3);
        if (colorStateList == null) {
            colorStateList = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateList);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i2);
        if (colorStateList2 == null) {
            colorStateList2 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateList2);
        this.g0.setFillColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_thumbColor));
        int i4 = R.styleable.Slider_thumbStrokeColor;
        if (obtainStyledAttributes.hasValue(i4)) {
            setThumbStrokeColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, i4));
        }
        setThumbStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_haloColor);
        if (colorStateList3 == null) {
            colorStateList3 = AppCompatResources.getColorStateList(context, R.color.material_slider_halo_color);
        }
        setHaloTintList(colorStateList3);
        this.T = obtainStyledAttributes.getBoolean(R.styleable.Slider_tickVisible, true);
        int i5 = R.styleable.Slider_tickColor;
        boolean hasValue2 = obtainStyledAttributes.hasValue(i5);
        int i6 = hasValue2 ? i5 : R.styleable.Slider_tickColorInactive;
        if (!hasValue2) {
            i5 = R.styleable.Slider_tickColorActive;
        }
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i6);
        if (colorStateList4 == null) {
            colorStateList4 = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateList4);
        ColorStateList colorStateList5 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i5);
        if (colorStateList5 == null) {
            colorStateList5 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateList5);
        setThumbRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbRadius, 0));
        setHaloRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_haloRadius, 0));
        setThumbElevation(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackHeight, 0));
        setLabelBehavior(obtainStyledAttributes.getInt(R.styleable.Slider_labelBehavior, 0));
        if (!obtainStyledAttributes.getBoolean(R.styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        obtainStyledAttributes.recycle();
    }

    public final void X(int i) {
        BaseSlider<S, L, T>.d dVar = this.p;
        if (dVar == null) {
            this.p = new d(this, null);
        } else {
            removeCallbacks(dVar);
        }
        this.p.a(i);
        postDelayed(this.p, 200L);
    }

    public final void Y(TooltipDrawable tooltipDrawable, float f2) {
        tooltipDrawable.setText(A(f2));
        int Q = (this.D + ((int) (Q(f2) * this.U))) - (tooltipDrawable.getIntrinsicWidth() / 2);
        int m = m() - (this.H + this.F);
        tooltipDrawable.setBounds(Q, m - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + Q, m);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, rect);
        tooltipDrawable.setBounds(rect);
        ViewUtils.getContentViewOverlay(this).add(tooltipDrawable);
    }

    public final boolean Z() {
        return this.B == 3;
    }

    public final boolean a0() {
        return this.V || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    public void addOnChangeListener(@NonNull L l) {
        this.s.add(l);
    }

    public void addOnSliderTouchListener(@NonNull T t) {
        this.t.add(t);
    }

    public final boolean b0(float f2) {
        return d0(this.P, f2);
    }

    public final double c0(float f2) {
        float f3 = this.R;
        if (f3 > 0.0f) {
            int i = (int) ((this.N - this.M) / f3);
            return Math.round(f2 * i) / i;
        }
        return f2;
    }

    public void clearOnChangeListeners() {
        this.s.clear();
    }

    public void clearOnSliderTouchListeners() {
        this.t.clear();
    }

    public final boolean d0(int i, float f2) {
        this.Q = i;
        if (Math.abs(f2 - this.O.get(i).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.O.set(i, Float.valueOf(C(i, f2)));
        r(i);
        return true;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        return this.n.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.h.setColor(D(this.f0));
        this.i.setColor(D(this.e0));
        this.l.setColor(D(this.d0));
        this.m.setColor(D(this.c0));
        for (TooltipDrawable tooltipDrawable : this.r) {
            if (tooltipDrawable.isStateful()) {
                tooltipDrawable.setState(getDrawableState());
            }
        }
        if (this.g0.isStateful()) {
            this.g0.setState(getDrawableState());
        }
        this.k.setColor(D(this.b0));
        this.k.setAlpha(63);
    }

    public final boolean e0() {
        return b0(getValueOfTouchPosition());
    }

    public void f0(int i, Rect rect) {
        int Q = this.D + ((int) (Q(getValues().get(i).floatValue()) * this.U));
        int m = m();
        int i2 = this.F;
        rect.set(Q - i2, m - i2, Q + i2, m + i2);
    }

    public final void g0() {
        if (a0() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int Q = (int) ((Q(this.O.get(this.Q).floatValue()) * this.U) + this.D);
            int m = m();
            int i = this.G;
            DrawableCompat.setHotspotBounds(background, Q - i, m - i, Q + i, m + i);
        }
    }

    @Override // android.view.View
    @NonNull
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    @VisibleForTesting
    public final int getAccessibilityFocusedVirtualViewId() {
        return this.n.getAccessibilityFocusedVirtualViewId();
    }

    public int getActiveThumbIndex() {
        return this.P;
    }

    public int getFocusedThumbIndex() {
        return this.Q;
    }

    @Dimension
    public int getHaloRadius() {
        return this.G;
    }

    @NonNull
    public ColorStateList getHaloTintList() {
        return this.b0;
    }

    public int getLabelBehavior() {
        return this.B;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.R;
    }

    public float getThumbElevation() {
        return this.g0.getElevation();
    }

    @Dimension
    public int getThumbRadius() {
        return this.F;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.g0.getStrokeColor();
    }

    public float getThumbStrokeWidth() {
        return this.g0.getStrokeWidth();
    }

    @NonNull
    public ColorStateList getThumbTintList() {
        return this.g0.getFillColor();
    }

    @NonNull
    public ColorStateList getTickActiveTintList() {
        return this.c0;
    }

    @NonNull
    public ColorStateList getTickInactiveTintList() {
        return this.d0;
    }

    @NonNull
    public ColorStateList getTickTintList() {
        if (this.d0.equals(this.c0)) {
            return this.c0;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    @NonNull
    public ColorStateList getTrackActiveTintList() {
        return this.e0;
    }

    @Dimension
    public int getTrackHeight() {
        return this.C;
    }

    @NonNull
    public ColorStateList getTrackInactiveTintList() {
        return this.f0;
    }

    @Dimension
    public int getTrackSidePadding() {
        return this.D;
    }

    @NonNull
    public ColorStateList getTrackTintList() {
        if (this.f0.equals(this.e0)) {
            return this.e0;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    @Dimension
    public int getTrackWidth() {
        return this.U;
    }

    public float getValueFrom() {
        return this.M;
    }

    public float getValueTo() {
        return this.N;
    }

    @NonNull
    public List<Float> getValues() {
        return new ArrayList(this.O);
    }

    public final void h(Drawable drawable) {
        int i = this.F * 2;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, i, i);
            return;
        }
        float max = i / Math.max(intrinsicWidth, intrinsicHeight);
        drawable.setBounds(0, 0, (int) (intrinsicWidth * max), (int) (intrinsicHeight * max));
    }

    public final void h0(int i) {
        this.U = Math.max(i - (this.D * 2), 0);
        K();
    }

    public boolean hasLabelFormatter() {
        return this.K != null;
    }

    public final void i(TooltipDrawable tooltipDrawable) {
        tooltipDrawable.setRelativeToView(ViewUtils.getContentView(this));
    }

    public final void i0() {
        if (this.a0) {
            l0();
            m0();
            k0();
            n0();
            j0();
            q0();
            this.a0 = false;
        }
    }

    public boolean isTickVisible() {
        return this.T;
    }

    public final Float j(int i) {
        float l = this.W ? l(20) : k();
        if (i == 21) {
            if (!I()) {
                l = -l;
            }
            return Float.valueOf(l);
        } else if (i == 22) {
            if (I()) {
                l = -l;
            }
            return Float.valueOf(l);
        } else if (i != 69) {
            if (i == 70 || i == 81) {
                return Float.valueOf(l);
            }
            return null;
        } else {
            return Float.valueOf(-l);
        }
    }

    public final void j0() {
        float minSeparation = getMinSeparation();
        if (minSeparation >= 0.0f) {
            float f2 = this.R;
            if (f2 <= 0.0f || minSeparation <= 0.0f) {
                return;
            }
            if (this.k0 == 1) {
                if (minSeparation < f2 || !H(minSeparation)) {
                    throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)", Float.valueOf(minSeparation), Float.valueOf(this.R), Float.valueOf(this.R)));
                }
                return;
            }
            throw new IllegalStateException(String.format("minSeparation(%s) cannot be set as a dimension when using stepSize(%s)", Float.valueOf(minSeparation), Float.valueOf(this.R)));
        }
        throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal to 0", Float.valueOf(minSeparation)));
    }

    public final float k() {
        float f2 = this.R;
        if (f2 == 0.0f) {
            return 1.0f;
        }
        return f2;
    }

    public final void k0() {
        if (this.R > 0.0f && !o0(this.N)) {
            throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.valueOf(this.R), Float.valueOf(this.M), Float.valueOf(this.N)));
        }
    }

    public final float l(int i) {
        float f2;
        float f3;
        float k = k();
        return (this.N - this.M) / k <= i ? k : Math.round(f2 / f3) * k;
    }

    public final void l0() {
        if (this.M >= this.N) {
            throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.valueOf(this.M), Float.valueOf(this.N)));
        }
    }

    public final int m() {
        int i = this.E;
        int i2 = 0;
        if (this.B == 1 || Z()) {
            i2 = this.r.get(0).getIntrinsicHeight();
        }
        return i + i2;
    }

    public final void m0() {
        if (this.N <= this.M) {
            throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.valueOf(this.N), Float.valueOf(this.M)));
        }
    }

    public final ValueAnimator n(boolean z) {
        TimeInterpolator timeInterpolator;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(B(z ? this.w : this.v, z ? 0.0f : 1.0f), z ? 1.0f : 0.0f);
        ofFloat.setDuration(z ? 83L : 117L);
        if (z) {
            timeInterpolator = AnimationUtils.DECELERATE_INTERPOLATOR;
        } else {
            timeInterpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        }
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.addUpdateListener(new b());
        return ofFloat;
    }

    public final void n0() {
        Iterator<Float> it = this.O.iterator();
        while (it.hasNext()) {
            Float next = it.next();
            if (next.floatValue() >= this.M && next.floatValue() <= this.N) {
                if (this.R > 0.0f && !o0(next.floatValue())) {
                    throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", next, Float.valueOf(this.M), Float.valueOf(this.R), Float.valueOf(this.R)));
                }
            } else {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", next, Float.valueOf(this.M), Float.valueOf(this.N)));
            }
        }
    }

    public final void o() {
        if (this.r.size() > this.O.size()) {
            List<TooltipDrawable> subList = this.r.subList(this.O.size(), this.r.size());
            for (TooltipDrawable tooltipDrawable : subList) {
                if (ViewCompat.isAttachedToWindow(this)) {
                    p(tooltipDrawable);
                }
            }
            subList.clear();
        }
        while (this.r.size() < this.O.size()) {
            TooltipDrawable a2 = this.q.a();
            this.r.add(a2);
            if (ViewCompat.isAttachedToWindow(this)) {
                i(a2);
            }
        }
        int i = this.r.size() == 1 ? 0 : 1;
        for (TooltipDrawable tooltipDrawable2 : this.r) {
            tooltipDrawable2.setStrokeWidth(i);
        }
    }

    public final boolean o0(float f2) {
        return H(f2 - this.M);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (TooltipDrawable tooltipDrawable : this.r) {
            i(tooltipDrawable);
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.d dVar = this.p;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
        this.u = false;
        for (TooltipDrawable tooltipDrawable : this.r) {
            p(tooltipDrawable);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(@NonNull Canvas canvas) {
        if (this.a0) {
            i0();
            K();
        }
        super.onDraw(canvas);
        int m = m();
        u(canvas, this.U, m);
        if (((Float) Collections.max(getValues())).floatValue() > this.M) {
            t(canvas, this.U, m);
        }
        M(canvas);
        if ((this.L || isFocused() || Z()) && isEnabled()) {
            L(canvas, this.U, m);
            if (this.P == -1 && !Z()) {
                y();
            } else {
                x();
            }
        } else {
            y();
        }
        w(canvas, this.U, m);
    }

    @Override // android.view.View
    public void onFocusChanged(boolean z, int i, @Nullable Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (!z) {
            this.P = -1;
            this.n.clearKeyboardFocusForVirtualView(this.Q);
            return;
        }
        z(i);
        this.n.requestKeyboardFocusForVirtualView(this.Q);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.O.size() == 1) {
            this.P = 0;
        }
        if (this.P == -1) {
            Boolean R = R(i, keyEvent);
            return R != null ? R.booleanValue() : super.onKeyDown(i, keyEvent);
        }
        this.W |= keyEvent.isLongPress();
        Float j = j(i);
        if (j != null) {
            if (b0(this.O.get(this.P).floatValue() + j.floatValue())) {
                g0();
                postInvalidate();
            }
            return true;
        }
        if (i != 23) {
            if (i == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return O(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return O(-1);
                }
                return false;
            } else if (i != 66) {
                return super.onKeyDown(i, keyEvent);
            }
        }
        this.P = -1;
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, @NonNull KeyEvent keyEvent) {
        this.W = false;
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.A;
        int i4 = 0;
        if (this.B == 1 || Z()) {
            i4 = this.r.get(0).getIntrinsicHeight();
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3 + i4, 1073741824));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.M = sliderState.h;
        this.N = sliderState.i;
        setValuesInternal(sliderState.j);
        this.R = sliderState.k;
        if (sliderState.l) {
            requestFocus();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.h = this.M;
        sliderState.i = this.N;
        sliderState.j = new ArrayList<>(this.O);
        sliderState.k = this.R;
        sliderState.l = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        h0(i);
        g0();
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (isEnabled()) {
            float x = motionEvent.getX();
            float f2 = (x - this.D) / this.U;
            this.j0 = f2;
            float max = Math.max(0.0f, f2);
            this.j0 = max;
            this.j0 = Math.min(1.0f, max);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.I = x;
                if (!G()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    if (pickActiveThumb()) {
                        requestFocus();
                        this.L = true;
                        e0();
                        g0();
                        invalidate();
                        S();
                    }
                }
            } else if (actionMasked == 1) {
                this.L = false;
                MotionEvent motionEvent2 = this.J;
                if (motionEvent2 != null && motionEvent2.getActionMasked() == 0 && Math.abs(this.J.getX() - motionEvent.getX()) <= this.x && Math.abs(this.J.getY() - motionEvent.getY()) <= this.x && pickActiveThumb()) {
                    S();
                }
                if (this.P != -1) {
                    e0();
                    this.P = -1;
                    T();
                }
                invalidate();
            } else if (actionMasked == 2) {
                if (!this.L) {
                    if (G() && Math.abs(x - this.I) < this.x) {
                        return false;
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                    S();
                }
                if (pickActiveThumb()) {
                    this.L = true;
                    e0();
                    g0();
                    invalidate();
                }
            }
            setPressed(this.L);
            this.J = MotionEvent.obtain(motionEvent);
            return true;
        }
        return false;
    }

    public final void p(TooltipDrawable tooltipDrawable) {
        ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(this);
        if (contentViewOverlay != null) {
            contentViewOverlay.remove(tooltipDrawable);
            tooltipDrawable.detachView(ViewUtils.getContentView(this));
        }
    }

    public final float p0(float f2) {
        return (Q(f2) * this.U) + this.D;
    }

    public boolean pickActiveThumb() {
        if (this.P != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float p0 = p0(valueOfTouchPositionAbsolute);
        this.P = 0;
        float abs = Math.abs(this.O.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i = 1; i < this.O.size(); i++) {
            float abs2 = Math.abs(this.O.get(i).floatValue() - valueOfTouchPositionAbsolute);
            float p02 = p0(this.O.get(i).floatValue());
            if (Float.compare(abs2, abs) > 1) {
                break;
            }
            boolean z = !I() ? p02 - p0 >= 0.0f : p02 - p0 <= 0.0f;
            if (Float.compare(abs2, abs) < 0) {
                this.P = i;
            } else {
                if (Float.compare(abs2, abs) != 0) {
                    continue;
                } else if (Math.abs(p02 - p0) < this.x) {
                    this.P = -1;
                    return false;
                } else if (z) {
                    this.P = i;
                }
            }
            abs = abs2;
        }
        return this.P != -1;
    }

    public final float q(float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f3 = (f2 - this.D) / this.U;
        float f4 = this.M;
        return (f3 * (f4 - this.N)) + f4;
    }

    public final void q0() {
        float f2 = this.R;
        if (f2 == 0.0f) {
            return;
        }
        if (((int) f2) != f2) {
            Log.w(l0, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "stepSize", Float.valueOf(f2)));
        }
        float f3 = this.M;
        if (((int) f3) != f3) {
            Log.w(l0, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueFrom", Float.valueOf(f3)));
        }
        float f4 = this.N;
        if (((int) f4) != f4) {
            Log.w(l0, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueTo", Float.valueOf(f4)));
        }
    }

    public final void r(int i) {
        for (L l : this.s) {
            l.onValueChange(this, this.O.get(i).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.o;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        X(i);
    }

    public void removeOnChangeListener(@NonNull L l) {
        this.s.remove(l);
    }

    public void removeOnSliderTouchListener(@NonNull T t) {
        this.t.remove(t);
    }

    public final void s() {
        for (L l : this.s) {
            Iterator<Float> it = this.O.iterator();
            while (it.hasNext()) {
                l.onValueChange(this, it.next().floatValue(), false);
            }
        }
    }

    public void setActiveThumbIndex(int i) {
        this.P = i;
    }

    public void setCustomThumbDrawable(@DrawableRes int i) {
        setCustomThumbDrawable(getResources().getDrawable(i));
    }

    public void setCustomThumbDrawablesForValues(@NonNull @DrawableRes int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            drawableArr[i] = getResources().getDrawable(iArr[i]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i) {
        if (i >= 0 && i < this.O.size()) {
            this.Q = i;
            this.n.requestKeyboardFocusForVirtualView(i);
            postInvalidate();
            return;
        }
        throw new IllegalArgumentException("index out of range");
    }

    public void setHaloRadius(@IntRange(from = 0) @Dimension int i) {
        if (i == this.G) {
            return;
        }
        this.G = i;
        Drawable background = getBackground();
        if (!a0() && (background instanceof RippleDrawable)) {
            DrawableUtils.setRippleDrawableRadius((RippleDrawable) background, this.G);
        } else {
            postInvalidate();
        }
    }

    public void setHaloRadiusResource(@DimenRes int i) {
        setHaloRadius(getResources().getDimensionPixelSize(i));
    }

    public void setHaloTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.b0)) {
            return;
        }
        this.b0 = colorStateList;
        Drawable background = getBackground();
        if (!a0() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        this.k.setColor(D(colorStateList));
        this.k.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i) {
        if (this.B != i) {
            this.B = i;
            requestLayout();
        }
    }

    public void setLabelFormatter(@Nullable LabelFormatter labelFormatter) {
        this.K = labelFormatter;
    }

    public void setSeparationUnit(int i) {
        this.k0 = i;
        this.a0 = true;
        postInvalidate();
    }

    public void setStepSize(float f2) {
        if (f2 >= 0.0f) {
            if (this.R != f2) {
                this.R = f2;
                this.a0 = true;
                postInvalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.valueOf(f2), Float.valueOf(this.M), Float.valueOf(this.N)));
    }

    public void setThumbElevation(float f2) {
        this.g0.setElevation(f2);
    }

    public void setThumbElevationResource(@DimenRes int i) {
        setThumbElevation(getResources().getDimension(i));
    }

    public void setThumbRadius(@IntRange(from = 0) @Dimension int i) {
        if (i == this.F) {
            return;
        }
        this.F = i;
        N();
        this.g0.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, this.F).build());
        MaterialShapeDrawable materialShapeDrawable = this.g0;
        int i2 = this.F;
        materialShapeDrawable.setBounds(0, 0, i2 * 2, i2 * 2);
        Drawable drawable = this.h0;
        if (drawable != null) {
            h(drawable);
        }
        for (Drawable drawable2 : this.i0) {
            h(drawable2);
        }
        postInvalidate();
    }

    public void setThumbRadiusResource(@DimenRes int i) {
        setThumbRadius(getResources().getDimensionPixelSize(i));
    }

    public void setThumbStrokeColor(@Nullable ColorStateList colorStateList) {
        this.g0.setStrokeColor(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(@ColorRes int i) {
        if (i != 0) {
            setThumbStrokeColor(AppCompatResources.getColorStateList(getContext(), i));
        }
    }

    public void setThumbStrokeWidth(float f2) {
        this.g0.setStrokeWidth(f2);
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(@DimenRes int i) {
        if (i != 0) {
            setThumbStrokeWidth(getResources().getDimension(i));
        }
    }

    public void setThumbTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.g0.getFillColor())) {
            return;
        }
        this.g0.setFillColor(colorStateList);
        invalidate();
    }

    public void setTickActiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.c0)) {
            return;
        }
        this.c0 = colorStateList;
        this.m.setColor(D(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.d0)) {
            return;
        }
        this.d0 = colorStateList;
        this.l.setColor(D(colorStateList));
        invalidate();
    }

    public void setTickTintList(@NonNull ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z) {
        if (this.T != z) {
            this.T = z;
            postInvalidate();
        }
    }

    public void setTrackActiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.e0)) {
            return;
        }
        this.e0 = colorStateList;
        this.i.setColor(D(colorStateList));
        invalidate();
    }

    public void setTrackHeight(@IntRange(from = 0) @Dimension int i) {
        if (this.C != i) {
            this.C = i;
            F();
            postInvalidate();
        }
    }

    public void setTrackInactiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.f0)) {
            return;
        }
        this.f0 = colorStateList;
        this.h.setColor(D(colorStateList));
        invalidate();
    }

    public void setTrackTintList(@NonNull ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f2) {
        this.M = f2;
        this.a0 = true;
        postInvalidate();
    }

    public void setValueTo(float f2) {
        this.N = f2;
        this.a0 = true;
        postInvalidate();
    }

    public void setValues(@NonNull Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final void t(@NonNull Canvas canvas, int i, int i2) {
        float[] activeRange = getActiveRange();
        int i3 = this.D;
        float f2 = i;
        float f3 = i2;
        canvas.drawLine(i3 + (activeRange[0] * f2), f3, i3 + (activeRange[1] * f2), f3, this.i);
    }

    public final void u(@NonNull Canvas canvas, int i, int i2) {
        int i3;
        float[] activeRange = getActiveRange();
        float f2 = i;
        float f3 = this.D + (activeRange[1] * f2);
        if (f3 < i3 + i) {
            float f4 = i2;
            canvas.drawLine(f3, f4, i3 + i, f4, this.h);
        }
        int i4 = this.D;
        float f5 = i4 + (activeRange[0] * f2);
        if (f5 > i4) {
            float f6 = i2;
            canvas.drawLine(i4, f6, f5, f6, this.h);
        }
    }

    public final void v(@NonNull Canvas canvas, int i, int i2, float f2, @NonNull Drawable drawable) {
        canvas.save();
        canvas.translate((this.D + ((int) (Q(f2) * i))) - (drawable.getBounds().width() / 2.0f), i2 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    public final void w(@NonNull Canvas canvas, int i, int i2) {
        for (int i3 = 0; i3 < this.O.size(); i3++) {
            float floatValue = this.O.get(i3).floatValue();
            Drawable drawable = this.h0;
            if (drawable != null) {
                v(canvas, i, i2, floatValue, drawable);
            } else if (i3 < this.i0.size()) {
                v(canvas, i, i2, floatValue, this.i0.get(i3));
            } else {
                if (!isEnabled()) {
                    canvas.drawCircle(this.D + (Q(floatValue) * i), i2, this.F, this.j);
                }
                v(canvas, i, i2, floatValue, this.g0);
            }
        }
    }

    public final void x() {
        if (this.B == 2) {
            return;
        }
        if (!this.u) {
            this.u = true;
            ValueAnimator n = n(true);
            this.v = n;
            this.w = null;
            n.start();
        }
        Iterator<TooltipDrawable> it = this.r.iterator();
        for (int i = 0; i < this.O.size() && it.hasNext(); i++) {
            if (i != this.Q) {
                Y(it.next(), this.O.get(i).floatValue());
            }
        }
        if (it.hasNext()) {
            Y(it.next(), this.O.get(this.Q).floatValue());
            return;
        }
        throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.r.size()), Integer.valueOf(this.O.size())));
    }

    public final void y() {
        if (this.u) {
            this.u = false;
            ValueAnimator n = n(false);
            this.w = n;
            this.v = null;
            n.addListener(new c());
            this.w.start();
        }
    }

    public final void z(int i) {
        if (i == 1) {
            O(Integer.MAX_VALUE);
        } else if (i == 2) {
            O(Integer.MIN_VALUE);
        } else if (i == 17) {
            P(Integer.MAX_VALUE);
        } else if (i != 66) {
        } else {
            P(Integer.MIN_VALUE);
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Runnable {
        public int h;

        public d() {
            this.h = -1;
        }

        public void a(int i) {
            this.h = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.n.sendEventForVirtualView(this.h, 4);
        }

        public /* synthetic */ d(BaseSlider baseSlider, a aVar) {
            this();
        }
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    public void setCustomThumbDrawable(@NonNull Drawable drawable) {
        this.h0 = E(drawable);
        this.i0.clear();
        postInvalidate();
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, m0), attributeSet, i);
        this.r = new ArrayList();
        this.s = new ArrayList();
        this.t = new ArrayList();
        this.u = false;
        this.L = false;
        this.O = new ArrayList<>();
        this.P = -1;
        this.Q = -1;
        this.R = 0.0f;
        this.T = true;
        this.W = false;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.g0 = materialShapeDrawable;
        this.i0 = Collections.emptyList();
        this.k0 = 0;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.h = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.i = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.j = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.k = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.l = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.m = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        J(context2.getResources());
        this.q = new a(attributeSet, i);
        W(context2, attributeSet, i);
        setFocusable(true);
        setClickable(true);
        materialShapeDrawable.setShadowCompatibilityMode(2);
        this.x = ViewConfiguration.get(context2).getScaledTouchSlop();
        e eVar = new e(this);
        this.n = eVar;
        ViewCompat.setAccessibilityDelegate(this, eVar);
        this.o = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(@NonNull List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }

    public void setCustomThumbDrawablesForValues(@NonNull Drawable... drawableArr) {
        this.h0 = null;
        this.i0 = new ArrayList();
        for (Drawable drawable : drawableArr) {
            this.i0.add(E(drawable));
        }
        postInvalidate();
    }
}
