package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.resources.MaterialResources;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10390a;
    @NonNull
    public final TextInputLayout b;
    public LinearLayout c;
    public int d;
    public FrameLayout e;
    @Nullable
    public Animator f;
    public final float g;
    public int h;
    public int i;
    @Nullable
    public CharSequence j;
    public boolean k;
    @Nullable
    public TextView l;
    @Nullable
    public CharSequence m;
    public int n;
    @Nullable
    public ColorStateList o;
    public CharSequence p;
    public boolean q;
    @Nullable
    public TextView r;
    public int s;
    @Nullable
    public ColorStateList t;
    public Typeface u;

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ int h;
        public final /* synthetic */ TextView i;
        public final /* synthetic */ int j;
        public final /* synthetic */ TextView k;

        public a(int i, TextView textView, int i2, TextView textView2) {
            this.h = i;
            this.i = textView;
            this.j = i2;
            this.k = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            g.this.h = this.h;
            g.this.f = null;
            TextView textView = this.i;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.j == 1 && g.this.l != null) {
                    g.this.l.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.k;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.k.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.k;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b extends View.AccessibilityDelegate {
        public b() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            EditText editText = g.this.b.getEditText();
            if (editText != null) {
                accessibilityNodeInfo.setLabeledBy(editText);
            }
        }
    }

    public g(@NonNull TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.f10390a = context;
        this.b = textInputLayout;
        this.g = context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
    }

    public boolean A() {
        return this.q;
    }

    public void B(TextView textView, int i) {
        FrameLayout frameLayout;
        if (this.c == null) {
            return;
        }
        if (y(i) && (frameLayout = this.e) != null) {
            frameLayout.removeView(textView);
        } else {
            this.c.removeView(textView);
        }
        int i2 = this.d - 1;
        this.d = i2;
        M(this.c, i2);
    }

    public final void C(int i, int i2) {
        TextView m;
        TextView m2;
        if (i == i2) {
            return;
        }
        if (i2 != 0 && (m2 = m(i2)) != null) {
            m2.setVisibility(0);
            m2.setAlpha(1.0f);
        }
        if (i != 0 && (m = m(i)) != null) {
            m.setVisibility(4);
            if (i == 1) {
                m.setText((CharSequence) null);
            }
        }
        this.h = i2;
    }

    public void D(@Nullable CharSequence charSequence) {
        this.m = charSequence;
        TextView textView = this.l;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void E(boolean z) {
        if (this.k == z) {
            return;
        }
        h();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f10390a);
            this.l = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_error);
            if (Build.VERSION.SDK_INT >= 17) {
                this.l.setTextAlignment(5);
            }
            Typeface typeface = this.u;
            if (typeface != null) {
                this.l.setTypeface(typeface);
            }
            F(this.n);
            G(this.o);
            D(this.m);
            this.l.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.l, 1);
            e(this.l, 0);
        } else {
            v();
            B(this.l, 0);
            this.l = null;
            this.b.i0();
            this.b.v0();
        }
        this.k = z;
    }

    public void F(@StyleRes int i) {
        this.n = i;
        TextView textView = this.l;
        if (textView != null) {
            this.b.U(textView, i);
        }
    }

    public void G(@Nullable ColorStateList colorStateList) {
        this.o = colorStateList;
        TextView textView = this.l;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void H(@StyleRes int i) {
        this.s = i;
        TextView textView = this.r;
        if (textView != null) {
            TextViewCompat.setTextAppearance(textView, i);
        }
    }

    public void I(boolean z) {
        if (this.q == z) {
            return;
        }
        h();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f10390a);
            this.r = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_helper_text);
            int i = Build.VERSION.SDK_INT;
            if (i >= 17) {
                this.r.setTextAlignment(5);
            }
            Typeface typeface = this.u;
            if (typeface != null) {
                this.r.setTypeface(typeface);
            }
            this.r.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.r, 1);
            H(this.s);
            J(this.t);
            e(this.r, 1);
            if (i >= 17) {
                this.r.setAccessibilityDelegate(new b());
            }
        } else {
            w();
            B(this.r, 1);
            this.r = null;
            this.b.i0();
            this.b.v0();
        }
        this.q = z;
    }

    public void J(@Nullable ColorStateList colorStateList) {
        this.t = colorStateList;
        TextView textView = this.r;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public final void K(@Nullable TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    public void L(Typeface typeface) {
        if (typeface != this.u) {
            this.u = typeface;
            K(this.l, typeface);
            K(this.r, typeface);
        }
    }

    public final void M(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            viewGroup.setVisibility(8);
        }
    }

    public final boolean N(@Nullable TextView textView, @NonNull CharSequence charSequence) {
        return ViewCompat.isLaidOut(this.b) && this.b.isEnabled() && !(this.i == this.h && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public void O(CharSequence charSequence) {
        h();
        this.j = charSequence;
        this.l.setText(charSequence);
        int i = this.h;
        if (i != 1) {
            this.i = 1;
        }
        Q(i, this.i, N(this.l, charSequence));
    }

    public void P(CharSequence charSequence) {
        h();
        this.p = charSequence;
        this.r.setText(charSequence);
        int i = this.h;
        if (i != 2) {
            this.i = 2;
        }
        Q(i, this.i, N(this.r, charSequence));
    }

    public final void Q(int i, int i2, boolean z) {
        if (i == i2) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f = animatorSet;
            ArrayList arrayList = new ArrayList();
            i(arrayList, this.q, this.r, 2, i, i2);
            i(arrayList, this.k, this.l, 1, i, i2);
            AnimatorSetCompat.playTogether(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, m(i), i, m(i2)));
            animatorSet.start();
        } else {
            C(i, i2);
        }
        this.b.i0();
        this.b.n0(z);
        this.b.v0();
    }

    public void e(TextView textView, int i) {
        if (this.c == null && this.e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f10390a);
            this.c = linearLayout;
            linearLayout.setOrientation(0);
            this.b.addView(this.c, -1, -2);
            this.e = new FrameLayout(this.f10390a);
            this.c.addView(this.e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.b.getEditText() != null) {
                f();
            }
        }
        if (y(i)) {
            this.e.setVisibility(0);
            this.e.addView(textView);
        } else {
            this.c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.c.setVisibility(0);
        this.d++;
    }

    public void f() {
        if (g()) {
            EditText editText = this.b.getEditText();
            boolean isFontScaleAtLeast1_3 = MaterialResources.isFontScaleAtLeast1_3(this.f10390a);
            LinearLayout linearLayout = this.c;
            int i = R.dimen.material_helper_text_font_1_3_padding_horizontal;
            ViewCompat.setPaddingRelative(linearLayout, u(isFontScaleAtLeast1_3, i, ViewCompat.getPaddingStart(editText)), u(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_top, this.f10390a.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top)), u(isFontScaleAtLeast1_3, i, ViewCompat.getPaddingEnd(editText)), 0);
        }
    }

    public final boolean g() {
        return (this.c == null || this.b.getEditText() == null) ? false : true;
    }

    public void h() {
        Animator animator = this.f;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void i(@NonNull List<Animator> list, boolean z, @Nullable TextView textView, int i, int i2, int i3) {
        if (textView == null || !z) {
            return;
        }
        if (i == i3 || i == i2) {
            list.add(j(textView, i3 == i));
            if (i3 == i) {
                list.add(k(textView));
            }
        }
    }

    public final ObjectAnimator j(TextView textView, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.ALPHA, z ? 1.0f : 0.0f);
        ofFloat.setDuration(167L);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return ofFloat;
    }

    public final ObjectAnimator k(TextView textView) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, -this.g, 0.0f);
        ofFloat.setDuration(217L);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        return ofFloat;
    }

    public boolean l() {
        return x(this.i);
    }

    @Nullable
    public final TextView m(int i) {
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            return this.r;
        }
        return this.l;
    }

    @Nullable
    public CharSequence n() {
        return this.m;
    }

    @Nullable
    public CharSequence o() {
        return this.j;
    }

    @ColorInt
    public int p() {
        TextView textView = this.l;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    @Nullable
    public ColorStateList q() {
        TextView textView = this.l;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence r() {
        return this.p;
    }

    @Nullable
    public View s() {
        return this.r;
    }

    @ColorInt
    public int t() {
        TextView textView = this.r;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public final int u(boolean z, @DimenRes int i, int i2) {
        return z ? this.f10390a.getResources().getDimensionPixelSize(i) : i2;
    }

    public void v() {
        this.j = null;
        h();
        if (this.h == 1) {
            if (this.q && !TextUtils.isEmpty(this.p)) {
                this.i = 2;
            } else {
                this.i = 0;
            }
        }
        Q(this.h, this.i, N(this.l, ""));
    }

    public void w() {
        h();
        int i = this.h;
        if (i == 2) {
            this.i = 0;
        }
        Q(i, this.i, N(this.r, ""));
    }

    public final boolean x(int i) {
        return (i != 1 || this.l == null || TextUtils.isEmpty(this.j)) ? false : true;
    }

    public boolean y(int i) {
        return i == 0 || i == 1;
    }

    public boolean z() {
        return this.k;
    }
}
