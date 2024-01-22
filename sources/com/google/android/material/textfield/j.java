package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.GravityCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes10.dex */
public class j extends LinearLayout {
    public final TextInputLayout h;
    public final TextView i;
    @Nullable
    public CharSequence j;
    public final CheckableImageButton k;
    public ColorStateList l;
    public PorterDuff.Mode m;
    public View.OnLongClickListener n;
    public boolean o;

    public j(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        super(textInputLayout.getContext());
        this.h = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.START));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, (ViewGroup) this, false);
        this.k = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.i = appCompatTextView;
        g(tintTypedArray);
        f(tintTypedArray);
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    @Nullable
    public CharSequence a() {
        return this.j;
    }

    @Nullable
    public ColorStateList b() {
        return this.i.getTextColors();
    }

    @NonNull
    public TextView c() {
        return this.i;
    }

    @Nullable
    public CharSequence d() {
        return this.k.getContentDescription();
    }

    @Nullable
    public Drawable e() {
        return this.k.getDrawable();
    }

    public final void f(TintTypedArray tintTypedArray) {
        this.i.setVisibility(8);
        this.i.setId(R.id.textinput_prefix_text);
        this.i.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        ViewCompat.setAccessibilityLiveRegion(this.i, 1);
        m(tintTypedArray.getResourceId(R.styleable.TextInputLayout_prefixTextAppearance, 0));
        int i = R.styleable.TextInputLayout_prefixTextColor;
        if (tintTypedArray.hasValue(i)) {
            n(tintTypedArray.getColorStateList(i));
        }
        l(tintTypedArray.getText(R.styleable.TextInputLayout_prefixText));
    }

    public final void g(TintTypedArray tintTypedArray) {
        if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
            MarginLayoutParamsCompat.setMarginEnd((ViewGroup.MarginLayoutParams) this.k.getLayoutParams(), 0);
        }
        r(null);
        s(null);
        int i = R.styleable.TextInputLayout_startIconTint;
        if (tintTypedArray.hasValue(i)) {
            this.l = MaterialResources.getColorStateList(getContext(), tintTypedArray, i);
        }
        int i2 = R.styleable.TextInputLayout_startIconTintMode;
        if (tintTypedArray.hasValue(i2)) {
            this.m = ViewUtils.parseTintMode(tintTypedArray.getInt(i2, -1), null);
        }
        int i3 = R.styleable.TextInputLayout_startIconDrawable;
        if (tintTypedArray.hasValue(i3)) {
            q(tintTypedArray.getDrawable(i3));
            int i4 = R.styleable.TextInputLayout_startIconContentDescription;
            if (tintTypedArray.hasValue(i4)) {
                p(tintTypedArray.getText(i4));
            }
            o(tintTypedArray.getBoolean(R.styleable.TextInputLayout_startIconCheckable, true));
        }
    }

    public boolean h() {
        return this.k.isCheckable();
    }

    public boolean i() {
        return this.k.getVisibility() == 0;
    }

    public void j(boolean z) {
        this.o = z;
        y();
    }

    public void k() {
        f.c(this.h, this.k, this.l);
    }

    public void l(@Nullable CharSequence charSequence) {
        this.j = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.i.setText(charSequence);
        y();
    }

    public void m(@StyleRes int i) {
        TextViewCompat.setTextAppearance(this.i, i);
    }

    public void n(@NonNull ColorStateList colorStateList) {
        this.i.setTextColor(colorStateList);
    }

    public void o(boolean z) {
        this.k.setCheckable(z);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        x();
    }

    public void p(@Nullable CharSequence charSequence) {
        if (d() != charSequence) {
            this.k.setContentDescription(charSequence);
        }
    }

    public void q(@Nullable Drawable drawable) {
        this.k.setImageDrawable(drawable);
        if (drawable != null) {
            f.a(this.h, this.k, this.l, this.m);
            v(true);
            k();
            return;
        }
        v(false);
        r(null);
        s(null);
        p(null);
    }

    public void r(@Nullable View.OnClickListener onClickListener) {
        f.e(this.k, onClickListener, this.n);
    }

    public void s(@Nullable View.OnLongClickListener onLongClickListener) {
        this.n = onLongClickListener;
        f.f(this.k, onLongClickListener);
    }

    public void t(@Nullable ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            f.a(this.h, this.k, colorStateList, this.m);
        }
    }

    public void u(@Nullable PorterDuff.Mode mode) {
        if (this.m != mode) {
            this.m = mode;
            f.a(this.h, this.k, this.l, mode);
        }
    }

    public void v(boolean z) {
        if (i() != z) {
            this.k.setVisibility(z ? 0 : 8);
            x();
            y();
        }
    }

    public void w(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.i.getVisibility() == 0) {
            accessibilityNodeInfoCompat.setLabelFor(this.i);
            accessibilityNodeInfoCompat.setTraversalAfter(this.i);
            return;
        }
        accessibilityNodeInfoCompat.setTraversalAfter(this.k);
    }

    public void x() {
        EditText editText = this.h.l;
        if (editText == null) {
            return;
        }
        ViewCompat.setPaddingRelative(this.i, i() ? 0 : ViewCompat.getPaddingStart(editText), editText.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), editText.getCompoundPaddingBottom());
    }

    public final void y() {
        int i = (this.j == null || this.o) ? 8 : 0;
        setVisibility(this.k.getVisibility() == 0 || i == 0 ? 0 : 8);
        this.i.setVisibility(i);
        this.h.h0();
    }
}
