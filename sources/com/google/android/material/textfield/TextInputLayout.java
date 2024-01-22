package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;
/* loaded from: classes10.dex */
public class TextInputLayout extends LinearLayout {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    public static final int P0 = R.style.Widget_Design_TextInputLayout;
    public TextView A;
    @ColorInt
    public int A0;
    @Nullable
    public ColorStateList B;
    @ColorInt
    public int B0;
    public int C;
    public ColorStateList C0;
    @Nullable
    public Fade D;
    @ColorInt
    public int D0;
    @Nullable
    public Fade E;
    @ColorInt
    public int E0;
    @Nullable
    public ColorStateList F;
    @ColorInt
    public int F0;
    @Nullable
    public ColorStateList G;
    @ColorInt
    public int G0;
    @Nullable
    public CharSequence H;
    @ColorInt
    public int H0;
    @NonNull
    public final TextView I;
    public boolean I0;
    public boolean J;
    public final CollapsingTextHelper J0;
    public CharSequence K;
    public boolean K0;
    public boolean L;
    public boolean L0;
    @Nullable
    public MaterialShapeDrawable M;
    public ValueAnimator M0;
    @Nullable
    public MaterialShapeDrawable N;
    public boolean N0;
    @Nullable
    public MaterialShapeDrawable O;
    public boolean O0;
    @NonNull
    public ShapeAppearanceModel P;
    public boolean Q;
    public final int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    @ColorInt
    public int a0;
    @ColorInt
    public int b0;
    public final Rect c0;
    public final Rect d0;
    public final RectF e0;
    public Typeface f0;
    @Nullable
    public Drawable g0;
    @NonNull
    public final FrameLayout h;
    public int h0;
    @NonNull
    public final j i;
    public final LinkedHashSet<OnEditTextAttachedListener> i0;
    @NonNull
    public final LinearLayout j;
    public int j0;
    @NonNull
    public final FrameLayout k;
    public final SparseArray<e> k0;
    public EditText l;
    @NonNull
    public final CheckableImageButton l0;
    public CharSequence m;
    public final LinkedHashSet<OnEndIconChangedListener> m0;
    public int n;
    public ColorStateList n0;
    public int o;
    public PorterDuff.Mode o0;
    public int p;
    @Nullable
    public Drawable p0;
    public int q;
    public int q0;
    public final g r;
    public Drawable r0;
    public boolean s;
    public View.OnLongClickListener s0;
    public int t;
    public View.OnLongClickListener t0;
    public boolean u;
    @NonNull
    public final CheckableImageButton u0;
    @Nullable
    public TextView v;
    public ColorStateList v0;
    public int w;
    public PorterDuff.Mode w0;
    public int x;
    public ColorStateList x0;
    public CharSequence y;
    public ColorStateList y0;
    public boolean z;
    @ColorInt
    public int z0;

    /* loaded from: classes10.dex */
    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        public final TextInputLayout d;

        public AccessibilityDelegate(@NonNull TextInputLayout textInputLayout) {
            this.d = textInputLayout;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View s;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            EditText editText = this.d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.d.getHint();
            CharSequence error = this.d.getError();
            CharSequence placeholderText = this.d.getPlaceholderText();
            int counterMaxLength = this.d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.d.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !this.d.J();
            boolean z4 = !TextUtils.isEmpty(error);
            boolean z5 = z4 || !TextUtils.isEmpty(counterOverflowDescription);
            String charSequence = z2 ? hint.toString() : "";
            this.d.i.w(accessibilityNodeInfoCompat);
            if (z) {
                accessibilityNodeInfoCompat.setText(text);
            } else if (!TextUtils.isEmpty(charSequence)) {
                accessibilityNodeInfoCompat.setText(charSequence);
                if (z3 && placeholderText != null) {
                    accessibilityNodeInfoCompat.setText(charSequence + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                accessibilityNodeInfoCompat.setText(placeholderText);
            }
            if (!TextUtils.isEmpty(charSequence)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    accessibilityNodeInfoCompat.setHintText(charSequence);
                } else {
                    if (z) {
                        charSequence = ((Object) text) + ", " + charSequence;
                    }
                    accessibilityNodeInfoCompat.setText(charSequence);
                }
                accessibilityNodeInfoCompat.setShowingHintText(!z);
            }
            accessibilityNodeInfoCompat.setMaxTextLength((text == null || text.length() != counterMaxLength) ? -1 : -1);
            if (z5) {
                if (!z4) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.setError(error);
            }
            if (Build.VERSION.SDK_INT < 17 || (s = this.d.r.s()) == null) {
                return;
            }
            accessibilityNodeInfoCompat.setLabelFor(s);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface BoxBackgroundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface EndIconMode {
    }

    /* loaded from: classes10.dex */
    public interface OnEditTextAttachedListener {
        void onEditTextAttached(@NonNull TextInputLayout textInputLayout);
    }

    /* loaded from: classes10.dex */
    public interface OnEndIconChangedListener {
        void onEndIconChanged(@NonNull TextInputLayout textInputLayout, int i);
    }

    /* loaded from: classes10.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        @Nullable
        public CharSequence i;
        public boolean j;
        @Nullable
        public CharSequence k;
        @Nullable
        public CharSequence l;
        @Nullable
        public CharSequence m;

        /* loaded from: classes10.dex */
        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            @Nullable
            /* renamed from: a */
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            @NonNull
            /* renamed from: b */
            public SavedState createFromParcel(@NonNull Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: c */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @NonNull
        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.i) + " hint=" + ((Object) this.k) + " helperText=" + ((Object) this.l) + " placeholderText=" + ((Object) this.m) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.i, parcel, i);
            parcel.writeInt(this.j ? 1 : 0);
            TextUtils.writeToParcel(this.k, parcel, i);
            TextUtils.writeToParcel(this.l, parcel, i);
            TextUtils.writeToParcel(this.m, parcel, i);
        }

        public SavedState(@NonNull Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.j = parcel.readInt() == 1;
            this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    /* loaded from: classes10.dex */
    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(@NonNull Editable editable) {
            TextInputLayout textInputLayout = TextInputLayout.this;
            textInputLayout.n0(!textInputLayout.O0);
            TextInputLayout textInputLayout2 = TextInputLayout.this;
            if (textInputLayout2.s) {
                textInputLayout2.d0(editable.length());
            }
            if (TextInputLayout.this.z) {
                TextInputLayout.this.r0(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.l0.performClick();
            TextInputLayout.this.l0.jumpDrawablesToCurrentState();
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.l.requestLayout();
        }
    }

    /* loaded from: classes10.dex */
    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            TextInputLayout.this.J0.setExpansionFraction(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public TextInputLayout(@NonNull Context context) {
        this(context, null);
    }

    public static void O(@NonNull ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                O((ViewGroup) childAt, z);
            }
        }
    }

    public static void R(@NonNull CheckableImageButton checkableImageButton, @Nullable View.OnLongClickListener onLongClickListener) {
        boolean hasOnClickListeners = ViewCompat.hasOnClickListeners(checkableImageButton);
        boolean z = false;
        boolean z2 = onLongClickListener != null;
        if (hasOnClickListeners || z2) {
            z = true;
        }
        checkableImageButton.setFocusable(z);
        checkableImageButton.setClickable(hasOnClickListeners);
        checkableImageButton.setPressable(hasOnClickListeners);
        checkableImageButton.setLongClickable(z2);
        ViewCompat.setImportantForAccessibility(checkableImageButton, z ? 1 : 2);
    }

    public static void S(@NonNull CheckableImageButton checkableImageButton, @Nullable View.OnClickListener onClickListener, @Nullable View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        R(checkableImageButton, onLongClickListener);
    }

    public static void T(@NonNull CheckableImageButton checkableImageButton, @Nullable View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        R(checkableImageButton, onLongClickListener);
    }

    public static void e0(@NonNull Context context, @NonNull TextView textView, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i3 = R.string.character_counter_overflowed_content_description;
        } else {
            i3 = R.string.character_counter_content_description;
        }
        textView.setContentDescription(context.getString(i3, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    private e getEndIconDelegate() {
        e eVar = this.k0.get(this.j0);
        return eVar != null ? eVar : this.k0.get(0);
    }

    @Nullable
    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.u0.getVisibility() == 0) {
            return this.u0;
        }
        if (G() && isEndIconVisible()) {
            return this.l0;
        }
        return null;
    }

    private void setEditText(EditText editText) {
        if (this.l == null) {
            if (this.j0 != 3 && !(editText instanceof TextInputEditText)) {
                Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.l = editText;
            int i = this.n;
            if (i != -1) {
                setMinEms(i);
            } else {
                setMinWidth(this.p);
            }
            int i2 = this.o;
            if (i2 != -1) {
                setMaxEms(i2);
            } else {
                setMaxWidth(this.q);
            }
            L();
            setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
            this.J0.setTypefaces(this.l.getTypeface());
            this.J0.setExpandedTextSize(this.l.getTextSize());
            if (Build.VERSION.SDK_INT >= 21) {
                this.J0.setExpandedLetterSpacing(this.l.getLetterSpacing());
            }
            int gravity = this.l.getGravity();
            this.J0.setCollapsedTextGravity((gravity & (-113)) | 48);
            this.J0.setExpandedTextGravity(gravity);
            this.l.addTextChangedListener(new a());
            if (this.x0 == null) {
                this.x0 = this.l.getHintTextColors();
            }
            if (this.J) {
                if (TextUtils.isEmpty(this.K)) {
                    CharSequence hint = this.l.getHint();
                    this.m = hint;
                    setHint(hint);
                    this.l.setHint((CharSequence) null);
                }
                this.L = true;
            }
            if (this.v != null) {
                d0(this.l.getText().length());
            }
            i0();
            this.r.f();
            this.i.bringToFront();
            this.j.bringToFront();
            this.k.bringToFront();
            this.u0.bringToFront();
            z();
            t0();
            if (!isEnabled()) {
                editText.setEnabled(false);
            }
            o0(false, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.K)) {
            return;
        }
        this.K = charSequence;
        this.J0.setText(charSequence);
        if (this.I0) {
            return;
        }
        M();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.z == z) {
            return;
        }
        if (z) {
            g();
        } else {
            P();
            this.A = null;
        }
        this.z = z;
    }

    public final void A(int i) {
        Iterator<OnEndIconChangedListener> it = this.m0.iterator();
        while (it.hasNext()) {
            it.next().onEndIconChanged(this, i);
        }
    }

    public final void B(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable;
        if (this.O == null || (materialShapeDrawable = this.N) == null) {
            return;
        }
        materialShapeDrawable.draw(canvas);
        if (this.l.isFocused()) {
            Rect bounds = this.O.getBounds();
            Rect bounds2 = this.N.getBounds();
            float expansionFraction = this.J0.getExpansionFraction();
            int centerX = bounds2.centerX();
            bounds.left = AnimationUtils.lerp(centerX, bounds2.left, expansionFraction);
            bounds.right = AnimationUtils.lerp(centerX, bounds2.right, expansionFraction);
            this.O.draw(canvas);
        }
    }

    public final void C(@NonNull Canvas canvas) {
        if (this.J) {
            this.J0.draw(canvas);
        }
    }

    public final void D(boolean z) {
        ValueAnimator valueAnimator = this.M0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.M0.cancel();
        }
        if (z && this.L0) {
            i(0.0f);
        } else {
            this.J0.setExpansionFraction(0.0f);
        }
        if (y() && ((com.google.android.material.textfield.c) this.M).y()) {
            v();
        }
        this.I0 = true;
        H();
        this.i.j(true);
        u0();
    }

    public final int E(int i, boolean z) {
        int compoundPaddingLeft = i + this.l.getCompoundPaddingLeft();
        return (getPrefixText() == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - getPrefixTextView().getMeasuredWidth()) + getPrefixTextView().getPaddingLeft();
    }

    public final int F(int i, boolean z) {
        int compoundPaddingRight = i - this.l.getCompoundPaddingRight();
        return (getPrefixText() == null || !z) ? compoundPaddingRight : compoundPaddingRight + (getPrefixTextView().getMeasuredWidth() - getPrefixTextView().getPaddingRight());
    }

    public final boolean G() {
        return this.j0 != 0;
    }

    public final void H() {
        TextView textView = this.A;
        if (textView == null || !this.z) {
            return;
        }
        textView.setText((CharSequence) null);
        TransitionManager.beginDelayedTransition(this.h, this.E);
        this.A.setVisibility(4);
    }

    public final boolean I() {
        return this.u0.getVisibility() == 0;
    }

    public final boolean J() {
        return this.I0;
    }

    public final boolean K() {
        return this.S == 1 && (Build.VERSION.SDK_INT < 16 || this.l.getMinLines() <= 1);
    }

    public final void L() {
        m();
        Q();
        v0();
        a0();
        h();
        if (this.S != 0) {
            m0();
        }
    }

    public final void M() {
        if (y()) {
            RectF rectF = this.e0;
            this.J0.getCollapsedTextActualBounds(rectF, this.l.getWidth(), this.l.getGravity());
            l(rectF);
            rectF.offset(-getPaddingLeft(), ((-getPaddingTop()) - (rectF.height() / 2.0f)) + this.U);
            ((com.google.android.material.textfield.c) this.M).B(rectF);
        }
    }

    public final void N() {
        if (!y() || this.I0) {
            return;
        }
        v();
        M();
    }

    public final void P() {
        TextView textView = this.A;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public final void Q() {
        if (X()) {
            ViewCompat.setBackground(this.l, this.M);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r3.getTextColors().getDefaultColor() == (-65281)) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void U(@androidx.annotation.NonNull android.widget.TextView r3, @androidx.annotation.StyleRes int r4) {
        /*
            r2 = this;
            r0 = 1
            androidx.core.widget.TextViewCompat.setTextAppearance(r3, r4)     // Catch: java.lang.Exception -> L1a
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L1a
            r1 = 23
            if (r4 < r1) goto L18
            android.content.res.ColorStateList r4 = r3.getTextColors()     // Catch: java.lang.Exception -> L1a
            int r4 = r4.getDefaultColor()     // Catch: java.lang.Exception -> L1a
            r1 = -65281(0xffffffffffff00ff, float:NaN)
            if (r4 != r1) goto L18
            goto L1a
        L18:
            r4 = 0
            r0 = r4
        L1a:
            if (r0 == 0) goto L2e
            int r4 = com.google.android.material.R.style.TextAppearance_AppCompat_Caption
            androidx.core.widget.TextViewCompat.setTextAppearance(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = com.google.android.material.R.color.design_error
            int r4 = androidx.core.content.ContextCompat.getColor(r4, r0)
            r3.setTextColor(r4)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.U(android.widget.TextView, int):void");
    }

    public final boolean V() {
        return (this.u0.getVisibility() == 0 || ((G() && isEndIconVisible()) || this.H != null)) && this.j.getMeasuredWidth() > 0;
    }

    public final boolean W() {
        return (getStartIconDrawable() != null || (getPrefixText() != null && getPrefixTextView().getVisibility() == 0)) && this.i.getMeasuredWidth() > 0;
    }

    public final boolean X() {
        EditText editText = this.l;
        return (editText == null || this.M == null || editText.getBackground() != null || this.S == 0) ? false : true;
    }

    public final void Y() {
        if (this.A == null || !this.z || TextUtils.isEmpty(this.y)) {
            return;
        }
        this.A.setText(this.y);
        TransitionManager.beginDelayedTransition(this.h, this.D);
        this.A.setVisibility(0);
        this.A.bringToFront();
        if (Build.VERSION.SDK_INT >= 16) {
            announceForAccessibility(this.y);
        }
    }

    public final void Z(boolean z) {
        if (z && getEndIconDrawable() != null) {
            Drawable mutate = DrawableCompat.wrap(getEndIconDrawable()).mutate();
            DrawableCompat.setTint(mutate, this.r.p());
            this.l0.setImageDrawable(mutate);
            return;
        }
        f.a(this, this.l0, this.n0, this.o0);
    }

    public final void a0() {
        if (this.S == 1) {
            if (MaterialResources.isFontScaleAtLeast2_0(getContext())) {
                this.T = getResources().getDimensionPixelSize(R.dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
                this.T = getResources().getDimensionPixelSize(R.dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
    }

    public void addOnEditTextAttachedListener(@NonNull OnEditTextAttachedListener onEditTextAttachedListener) {
        this.i0.add(onEditTextAttachedListener);
        if (this.l != null) {
            onEditTextAttachedListener.onEditTextAttached(this);
        }
    }

    public void addOnEndIconChangedListener(@NonNull OnEndIconChangedListener onEndIconChangedListener) {
        this.m0.add(onEndIconChangedListener);
    }

    @Override // android.view.ViewGroup
    public void addView(@NonNull View view, int i, @NonNull ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
            this.h.addView(view, layoutParams2);
            this.h.setLayoutParams(layoutParams);
            m0();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    public final void b0(@NonNull Rect rect) {
        MaterialShapeDrawable materialShapeDrawable = this.N;
        if (materialShapeDrawable != null) {
            int i = rect.bottom;
            materialShapeDrawable.setBounds(rect.left, i - this.V, rect.right, i);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.O;
        if (materialShapeDrawable2 != null) {
            int i2 = rect.bottom;
            materialShapeDrawable2.setBounds(rect.left, i2 - this.W, rect.right, i2);
        }
    }

    public final void c0() {
        if (this.v != null) {
            EditText editText = this.l;
            d0(editText == null ? 0 : editText.getText().length());
        }
    }

    public void clearOnEditTextAttachedListeners() {
        this.i0.clear();
    }

    public void clearOnEndIconChangedListeners() {
        this.m0.clear();
    }

    public void d0(int i) {
        boolean z = this.u;
        int i2 = this.t;
        if (i2 == -1) {
            this.v.setText(String.valueOf(i));
            this.v.setContentDescription(null);
            this.u = false;
        } else {
            this.u = i > i2;
            e0(getContext(), this.v, i, this.t, this.u);
            if (z != this.u) {
                f0();
            }
            this.v.setText(BidiFormatter.getInstance().unicodeWrap(getContext().getString(R.string.character_counter_pattern, Integer.valueOf(i), Integer.valueOf(this.t))));
        }
        if (this.l == null || z == this.u) {
            return;
        }
        n0(false);
        v0();
        i0();
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(26)
    public void dispatchProvideAutofillStructure(@NonNull ViewStructure viewStructure, int i) {
        EditText editText = this.l;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.m != null) {
            boolean z = this.L;
            this.L = false;
            CharSequence hint = editText.getHint();
            this.l.setHint(this.m);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.l.setHint(hint);
                this.L = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i);
        onProvideAutofillVirtualStructure(viewStructure, i);
        viewStructure.setChildCount(this.h.getChildCount());
        for (int i2 = 0; i2 < this.h.getChildCount(); i2++) {
            View childAt = this.h.getChildAt(i2);
            ViewStructure newChild = viewStructure.newChild(i2);
            childAt.dispatchProvideAutofillStructure(newChild, i);
            if (childAt == this.l) {
                newChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(@NonNull SparseArray<Parcelable> sparseArray) {
        this.O0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.O0 = false;
    }

    @Override // android.view.View
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        C(canvas);
        B(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.N0) {
            return;
        }
        boolean z = true;
        this.N0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        CollapsingTextHelper collapsingTextHelper = this.J0;
        boolean state = collapsingTextHelper != null ? collapsingTextHelper.setState(drawableState) | false : false;
        if (this.l != null) {
            if (!ViewCompat.isLaidOut(this) || !isEnabled()) {
                z = false;
            }
            n0(z);
        }
        i0();
        v0();
        if (state) {
            invalidate();
        }
        this.N0 = false;
    }

    public final void f0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.v;
        if (textView != null) {
            U(textView, this.u ? this.w : this.x);
            if (!this.u && (colorStateList2 = this.F) != null) {
                this.v.setTextColor(colorStateList2);
            }
            if (!this.u || (colorStateList = this.G) == null) {
                return;
            }
            this.v.setTextColor(colorStateList);
        }
    }

    public final void g() {
        TextView textView = this.A;
        if (textView != null) {
            this.h.addView(textView);
            this.A.setVisibility(0);
        }
    }

    public final void g0() {
        if (this.j0 == 3 && this.S == 2) {
            ((com.google.android.material.textfield.d) this.k0.get(3)).O((AutoCompleteTextView) this.l);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.l;
        if (editText != null) {
            return editText.getBaseline() + getPaddingTop() + s();
        }
        return super.getBaseline();
    }

    @NonNull
    public MaterialShapeDrawable getBoxBackground() {
        int i = this.S;
        if (i != 1 && i != 2) {
            throw new IllegalStateException();
        }
        return this.M;
    }

    public int getBoxBackgroundColor() {
        return this.b0;
    }

    public int getBoxBackgroundMode() {
        return this.S;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.T;
    }

    public float getBoxCornerRadiusBottomEnd() {
        if (ViewUtils.isLayoutRtl(this)) {
            return this.P.getBottomLeftCornerSize().getCornerSize(this.e0);
        }
        return this.P.getBottomRightCornerSize().getCornerSize(this.e0);
    }

    public float getBoxCornerRadiusBottomStart() {
        if (ViewUtils.isLayoutRtl(this)) {
            return this.P.getBottomRightCornerSize().getCornerSize(this.e0);
        }
        return this.P.getBottomLeftCornerSize().getCornerSize(this.e0);
    }

    public float getBoxCornerRadiusTopEnd() {
        if (ViewUtils.isLayoutRtl(this)) {
            return this.P.getTopLeftCornerSize().getCornerSize(this.e0);
        }
        return this.P.getTopRightCornerSize().getCornerSize(this.e0);
    }

    public float getBoxCornerRadiusTopStart() {
        if (ViewUtils.isLayoutRtl(this)) {
            return this.P.getTopRightCornerSize().getCornerSize(this.e0);
        }
        return this.P.getTopLeftCornerSize().getCornerSize(this.e0);
    }

    public int getBoxStrokeColor() {
        return this.B0;
    }

    @Nullable
    public ColorStateList getBoxStrokeErrorColor() {
        return this.C0;
    }

    public int getBoxStrokeWidth() {
        return this.V;
    }

    public int getBoxStrokeWidthFocused() {
        return this.W;
    }

    public int getCounterMaxLength() {
        return this.t;
    }

    @Nullable
    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.s && this.u && (textView = this.v) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    @Nullable
    public ColorStateList getCounterOverflowTextColor() {
        return this.F;
    }

    @Nullable
    public ColorStateList getCounterTextColor() {
        return this.F;
    }

    @Nullable
    public ColorStateList getDefaultHintTextColor() {
        return this.x0;
    }

    @Nullable
    public EditText getEditText() {
        return this.l;
    }

    @Nullable
    public CharSequence getEndIconContentDescription() {
        return this.l0.getContentDescription();
    }

    @Nullable
    public Drawable getEndIconDrawable() {
        return this.l0.getDrawable();
    }

    public int getEndIconMode() {
        return this.j0;
    }

    @NonNull
    public CheckableImageButton getEndIconView() {
        return this.l0;
    }

    @Nullable
    public CharSequence getError() {
        if (this.r.z()) {
            return this.r.o();
        }
        return null;
    }

    @Nullable
    public CharSequence getErrorContentDescription() {
        return this.r.n();
    }

    @ColorInt
    public int getErrorCurrentTextColors() {
        return this.r.p();
    }

    @Nullable
    public Drawable getErrorIconDrawable() {
        return this.u0.getDrawable();
    }

    @VisibleForTesting
    public final int getErrorTextCurrentColor() {
        return this.r.p();
    }

    @Nullable
    public CharSequence getHelperText() {
        if (this.r.A()) {
            return this.r.r();
        }
        return null;
    }

    @ColorInt
    public int getHelperTextCurrentTextColor() {
        return this.r.t();
    }

    @Nullable
    public CharSequence getHint() {
        if (this.J) {
            return this.K;
        }
        return null;
    }

    @VisibleForTesting
    public final float getHintCollapsedTextHeight() {
        return this.J0.getCollapsedTextHeight();
    }

    @VisibleForTesting
    public final int getHintCurrentCollapsedTextColor() {
        return this.J0.getCurrentCollapsedTextColor();
    }

    @Nullable
    public ColorStateList getHintTextColor() {
        return this.y0;
    }

    public int getMaxEms() {
        return this.o;
    }

    @Px
    public int getMaxWidth() {
        return this.q;
    }

    public int getMinEms() {
        return this.n;
    }

    @Px
    public int getMinWidth() {
        return this.p;
    }

    @Nullable
    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.l0.getContentDescription();
    }

    @Nullable
    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.l0.getDrawable();
    }

    @Nullable
    public CharSequence getPlaceholderText() {
        if (this.z) {
            return this.y;
        }
        return null;
    }

    @StyleRes
    public int getPlaceholderTextAppearance() {
        return this.C;
    }

    @Nullable
    public ColorStateList getPlaceholderTextColor() {
        return this.B;
    }

    @Nullable
    public CharSequence getPrefixText() {
        return this.i.a();
    }

    @Nullable
    public ColorStateList getPrefixTextColor() {
        return this.i.b();
    }

    @NonNull
    public TextView getPrefixTextView() {
        return this.i.c();
    }

    @Nullable
    public CharSequence getStartIconContentDescription() {
        return this.i.d();
    }

    @Nullable
    public Drawable getStartIconDrawable() {
        return this.i.e();
    }

    @Nullable
    public CharSequence getSuffixText() {
        return this.H;
    }

    @Nullable
    public ColorStateList getSuffixTextColor() {
        return this.I.getTextColors();
    }

    @NonNull
    public TextView getSuffixTextView() {
        return this.I;
    }

    @Nullable
    public Typeface getTypeface() {
        return this.f0;
    }

    public final void h() {
        if (this.l == null || this.S != 1) {
            return;
        }
        if (MaterialResources.isFontScaleAtLeast2_0(getContext())) {
            EditText editText = this.l;
            ViewCompat.setPaddingRelative(editText, ViewCompat.getPaddingStart(editText), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_top), ViewCompat.getPaddingEnd(this.l), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_bottom));
        } else if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
            EditText editText2 = this.l;
            ViewCompat.setPaddingRelative(editText2, ViewCompat.getPaddingStart(editText2), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_top), ViewCompat.getPaddingEnd(this.l), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
        }
    }

    public boolean h0() {
        boolean z;
        if (this.l == null) {
            return false;
        }
        boolean z2 = true;
        if (W()) {
            int measuredWidth = this.i.getMeasuredWidth() - this.l.getPaddingLeft();
            if (this.g0 == null || this.h0 != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.g0 = colorDrawable;
                this.h0 = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.l);
            Drawable drawable = compoundDrawablesRelative[0];
            Drawable drawable2 = this.g0;
            if (drawable != drawable2) {
                TextViewCompat.setCompoundDrawablesRelative(this.l, drawable2, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.g0 != null) {
                Drawable[] compoundDrawablesRelative2 = TextViewCompat.getCompoundDrawablesRelative(this.l);
                TextViewCompat.setCompoundDrawablesRelative(this.l, null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
                this.g0 = null;
                z = true;
            }
            z = false;
        }
        if (V()) {
            int measuredWidth2 = this.I.getMeasuredWidth() - this.l.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] compoundDrawablesRelative3 = TextViewCompat.getCompoundDrawablesRelative(this.l);
            Drawable drawable3 = this.p0;
            if (drawable3 != null && this.q0 != measuredWidth2) {
                this.q0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                TextViewCompat.setCompoundDrawablesRelative(this.l, compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], this.p0, compoundDrawablesRelative3[3]);
            } else {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.p0 = colorDrawable2;
                    this.q0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = compoundDrawablesRelative3[2];
                Drawable drawable5 = this.p0;
                if (drawable4 != drawable5) {
                    this.r0 = compoundDrawablesRelative3[2];
                    TextViewCompat.setCompoundDrawablesRelative(this.l, compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], drawable5, compoundDrawablesRelative3[3]);
                } else {
                    z2 = z;
                }
            }
        } else if (this.p0 == null) {
            return z;
        } else {
            Drawable[] compoundDrawablesRelative4 = TextViewCompat.getCompoundDrawablesRelative(this.l);
            if (compoundDrawablesRelative4[2] == this.p0) {
                TextViewCompat.setCompoundDrawablesRelative(this.l, compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.r0, compoundDrawablesRelative4[3]);
            } else {
                z2 = z;
            }
            this.p0 = null;
        }
        return z2;
    }

    @VisibleForTesting
    public void i(float f) {
        if (this.J0.getExpansionFraction() == f) {
            return;
        }
        if (this.M0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.M0 = valueAnimator;
            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.M0.setDuration(167L);
            this.M0.addUpdateListener(new d());
        }
        this.M0.setFloatValues(this.J0.getExpansionFraction(), f);
        this.M0.start();
    }

    public void i0() {
        Drawable background;
        TextView textView;
        EditText editText = this.l;
        if (editText == null || this.S != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (DrawableUtils.canSafelyMutateDrawable(background)) {
            background = background.mutate();
        }
        if (this.r.l()) {
            background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.r.p(), PorterDuff.Mode.SRC_IN));
        } else if (this.u && (textView = this.v) != null) {
            background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            DrawableCompat.clearColorFilter(background);
            this.l.refreshDrawableState();
        }
    }

    public boolean isCounterEnabled() {
        return this.s;
    }

    public boolean isEndIconCheckable() {
        return this.l0.isCheckable();
    }

    public boolean isEndIconVisible() {
        return this.k.getVisibility() == 0 && this.l0.getVisibility() == 0;
    }

    public boolean isErrorEnabled() {
        return this.r.z();
    }

    public boolean isExpandedHintEnabled() {
        return this.K0;
    }

    public boolean isHelperTextEnabled() {
        return this.r.A();
    }

    public boolean isHintAnimationEnabled() {
        return this.L0;
    }

    public boolean isHintEnabled() {
        return this.J;
    }

    @Deprecated
    public boolean isPasswordVisibilityToggleEnabled() {
        return this.j0 == 1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isProvidingHint() {
        return this.L;
    }

    public boolean isStartIconCheckable() {
        return this.i.h();
    }

    public boolean isStartIconVisible() {
        return this.i.i();
    }

    public final void j() {
        MaterialShapeDrawable materialShapeDrawable = this.M;
        if (materialShapeDrawable == null) {
            return;
        }
        ShapeAppearanceModel shapeAppearanceModel = materialShapeDrawable.getShapeAppearanceModel();
        ShapeAppearanceModel shapeAppearanceModel2 = this.P;
        if (shapeAppearanceModel != shapeAppearanceModel2) {
            this.M.setShapeAppearanceModel(shapeAppearanceModel2);
            g0();
        }
        if (t()) {
            this.M.setStroke(this.U, this.a0);
        }
        int n = n();
        this.b0 = n;
        this.M.setFillColor(ColorStateList.valueOf(n));
        if (this.j0 == 3) {
            this.l.getBackground().invalidateSelf();
        }
        k();
        invalidate();
    }

    public final boolean j0() {
        int max;
        if (this.l != null && this.l.getMeasuredHeight() < (max = Math.max(this.j.getMeasuredHeight(), this.i.getMeasuredHeight()))) {
            this.l.setMinimumHeight(max);
            return true;
        }
        return false;
    }

    public final void k() {
        ColorStateList valueOf;
        if (this.N == null || this.O == null) {
            return;
        }
        if (u()) {
            MaterialShapeDrawable materialShapeDrawable = this.N;
            if (this.l.isFocused()) {
                valueOf = ColorStateList.valueOf(this.z0);
            } else {
                valueOf = ColorStateList.valueOf(this.a0);
            }
            materialShapeDrawable.setFillColor(valueOf);
            this.O.setFillColor(ColorStateList.valueOf(this.a0));
        }
        invalidate();
    }

    public final void k0() {
        this.k.setVisibility((this.l0.getVisibility() != 0 || I()) ? 8 : 0);
        this.j.setVisibility(isEndIconVisible() || I() || !((this.H == null || J()) ? true : false) ? 0 : 8);
    }

    public final void l(@NonNull RectF rectF) {
        float f = rectF.left;
        int i = this.R;
        rectF.left = f - i;
        rectF.right += i;
    }

    public final void l0() {
        this.u0.setVisibility(getErrorIconDrawable() != null && this.r.z() && this.r.l() ? 0 : 8);
        k0();
        t0();
        if (G()) {
            return;
        }
        h0();
    }

    public final void m() {
        int i = this.S;
        if (i == 0) {
            this.M = null;
            this.N = null;
            this.O = null;
        } else if (i == 1) {
            this.M = new MaterialShapeDrawable(this.P);
            this.N = new MaterialShapeDrawable();
            this.O = new MaterialShapeDrawable();
        } else if (i == 2) {
            if (this.J && !(this.M instanceof com.google.android.material.textfield.c)) {
                this.M = new com.google.android.material.textfield.c(this.P);
            } else {
                this.M = new MaterialShapeDrawable(this.P);
            }
            this.N = null;
            this.O = null;
        } else {
            throw new IllegalArgumentException(this.S + " is illegal; only @BoxBackgroundMode constants are supported.");
        }
    }

    public final void m0() {
        if (this.S != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
            int s = s();
            if (s != layoutParams.topMargin) {
                layoutParams.topMargin = s;
                this.h.requestLayout();
            }
        }
    }

    public final int n() {
        return this.S == 1 ? MaterialColors.layer(MaterialColors.getColor(this, R.attr.colorSurface, 0), this.b0) : this.b0;
    }

    public void n0(boolean z) {
        o0(z, false);
    }

    @NonNull
    public final Rect o(@NonNull Rect rect) {
        if (this.l != null) {
            Rect rect2 = this.d0;
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            rect2.bottom = rect.bottom;
            int i = this.S;
            if (i == 1) {
                rect2.left = E(rect.left, isLayoutRtl);
                rect2.top = rect.top + this.T;
                rect2.right = F(rect.right, isLayoutRtl);
                return rect2;
            } else if (i != 2) {
                rect2.left = E(rect.left, isLayoutRtl);
                rect2.top = getPaddingTop();
                rect2.right = F(rect.right, isLayoutRtl);
                return rect2;
            } else {
                rect2.left = rect.left + this.l.getPaddingLeft();
                rect2.top = rect.top - s();
                rect2.right = rect.right - this.l.getPaddingRight();
                return rect2;
            }
        }
        throw new IllegalStateException();
    }

    public final void o0(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        int i;
        boolean isEnabled = isEnabled();
        EditText editText = this.l;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.l;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean l = this.r.l();
        ColorStateList colorStateList2 = this.x0;
        if (colorStateList2 != null) {
            this.J0.setCollapsedTextColor(colorStateList2);
            this.J0.setExpandedTextColor(this.x0);
        }
        if (!isEnabled) {
            ColorStateList colorStateList3 = this.x0;
            if (colorStateList3 != null) {
                i = colorStateList3.getColorForState(new int[]{-16842910}, this.H0);
            } else {
                i = this.H0;
            }
            this.J0.setCollapsedTextColor(ColorStateList.valueOf(i));
            this.J0.setExpandedTextColor(ColorStateList.valueOf(i));
        } else if (l) {
            this.J0.setCollapsedTextColor(this.r.q());
        } else if (this.u && (textView = this.v) != null) {
            this.J0.setCollapsedTextColor(textView.getTextColors());
        } else if (z4 && (colorStateList = this.y0) != null) {
            this.J0.setCollapsedTextColor(colorStateList);
        }
        if (!z3 && this.K0 && (!isEnabled() || !z4)) {
            if (z2 || !this.I0) {
                D(z);
            }
        } else if (z2 || this.I0) {
            w(z);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.J0.maybeUpdateFontWeightAdjustment(configuration);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.l;
        if (editText != null) {
            Rect rect = this.c0;
            DescendantOffsetUtils.getDescendantRect(this, editText, rect);
            b0(rect);
            if (this.J) {
                this.J0.setExpandedTextSize(this.l.getTextSize());
                int gravity = this.l.getGravity();
                this.J0.setCollapsedTextGravity((gravity & (-113)) | 48);
                this.J0.setExpandedTextGravity(gravity);
                this.J0.setCollapsedBounds(o(rect));
                this.J0.setExpandedBounds(r(rect));
                this.J0.recalculate();
                if (!y() || this.I0) {
                    return;
                }
                M();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        boolean j0 = j0();
        boolean h0 = h0();
        if (j0 || h0) {
            this.l.post(new c());
        }
        p0();
        t0();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(@Nullable Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setError(savedState.i);
        if (savedState.j) {
            this.l0.post(new b());
        }
        setHint(savedState.k);
        setHelperText(savedState.l);
        setPlaceholderText(savedState.m);
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        boolean z = false;
        boolean z2 = i == 1;
        boolean z3 = this.Q;
        if (z2 != z3) {
            if (z2 && !z3) {
                z = true;
            }
            float cornerSize = this.P.getTopLeftCornerSize().getCornerSize(this.e0);
            float cornerSize2 = this.P.getTopRightCornerSize().getCornerSize(this.e0);
            float cornerSize3 = this.P.getBottomLeftCornerSize().getCornerSize(this.e0);
            float cornerSize4 = this.P.getBottomRightCornerSize().getCornerSize(this.e0);
            float f = z ? cornerSize : cornerSize2;
            if (z) {
                cornerSize = cornerSize2;
            }
            float f2 = z ? cornerSize3 : cornerSize4;
            if (z) {
                cornerSize3 = cornerSize4;
            }
            setBoxCornerRadii(f, cornerSize, f2, cornerSize3);
        }
    }

    @Override // android.view.View
    @Nullable
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.r.l()) {
            savedState.i = getError();
        }
        savedState.j = G() && this.l0.isChecked();
        savedState.k = getHint();
        savedState.l = getHelperText();
        savedState.m = getPlaceholderText();
        return savedState;
    }

    public final int p(@NonNull Rect rect, @NonNull Rect rect2, float f) {
        if (K()) {
            return (int) (rect2.top + f);
        }
        return rect.bottom - this.l.getCompoundPaddingBottom();
    }

    public final void p0() {
        EditText editText;
        if (this.A == null || (editText = this.l) == null) {
            return;
        }
        this.A.setGravity(editText.getGravity());
        this.A.setPadding(this.l.getCompoundPaddingLeft(), this.l.getCompoundPaddingTop(), this.l.getCompoundPaddingRight(), this.l.getCompoundPaddingBottom());
    }

    @Deprecated
    public void passwordVisibilityToggleRequested(boolean z) {
        if (this.j0 == 1) {
            this.l0.performClick();
            if (z) {
                this.l0.jumpDrawablesToCurrentState();
            }
        }
    }

    public final int q(@NonNull Rect rect, float f) {
        if (K()) {
            return (int) (rect.centerY() - (f / 2.0f));
        }
        return rect.top + this.l.getCompoundPaddingTop();
    }

    public final void q0() {
        EditText editText = this.l;
        r0(editText == null ? 0 : editText.getText().length());
    }

    @NonNull
    public final Rect r(@NonNull Rect rect) {
        if (this.l != null) {
            Rect rect2 = this.d0;
            float expandedTextHeight = this.J0.getExpandedTextHeight();
            rect2.left = rect.left + this.l.getCompoundPaddingLeft();
            rect2.top = q(rect, expandedTextHeight);
            rect2.right = rect.right - this.l.getCompoundPaddingRight();
            rect2.bottom = p(rect, rect2, expandedTextHeight);
            return rect2;
        }
        throw new IllegalStateException();
    }

    public final void r0(int i) {
        if (i == 0 && !this.I0) {
            Y();
        } else {
            H();
        }
    }

    public void refreshEndIconDrawableState() {
        f.c(this, this.l0, this.n0);
    }

    public void refreshErrorIconDrawableState() {
        f.c(this, this.u0, this.v0);
    }

    public void refreshStartIconDrawableState() {
        this.i.k();
    }

    public void removeOnEditTextAttachedListener(@NonNull OnEditTextAttachedListener onEditTextAttachedListener) {
        this.i0.remove(onEditTextAttachedListener);
    }

    public void removeOnEndIconChangedListener(@NonNull OnEndIconChangedListener onEndIconChangedListener) {
        this.m0.remove(onEndIconChangedListener);
    }

    public final int s() {
        float collapsedTextHeight;
        if (this.J) {
            int i = this.S;
            if (i == 0) {
                collapsedTextHeight = this.J0.getCollapsedTextHeight();
            } else if (i != 2) {
                return 0;
            } else {
                collapsedTextHeight = this.J0.getCollapsedTextHeight() / 2.0f;
            }
            return (int) collapsedTextHeight;
        }
        return 0;
    }

    public final void s0(boolean z, boolean z2) {
        int defaultColor = this.C0.getDefaultColor();
        int colorForState = this.C0.getColorForState(new int[]{16843623, 16842910}, defaultColor);
        int colorForState2 = this.C0.getColorForState(new int[]{16843518, 16842910}, defaultColor);
        if (z) {
            this.a0 = colorForState2;
        } else if (z2) {
            this.a0 = colorForState;
        } else {
            this.a0 = defaultColor;
        }
    }

    public void setBoxBackgroundColor(@ColorInt int i) {
        if (this.b0 != i) {
            this.b0 = i;
            this.D0 = i;
            this.F0 = i;
            this.G0 = i;
            j();
        }
    }

    public void setBoxBackgroundColorResource(@ColorRes int i) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i));
    }

    public void setBoxBackgroundColorStateList(@NonNull ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.D0 = defaultColor;
        this.b0 = defaultColor;
        this.E0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.F0 = colorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        this.G0 = colorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
        j();
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.S) {
            return;
        }
        this.S = i;
        if (this.l != null) {
            L();
        }
    }

    public void setBoxCollapsedPaddingTop(int i) {
        this.T = i;
    }

    public void setBoxCornerRadii(float f, float f2, float f3, float f4) {
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        this.Q = isLayoutRtl;
        float f5 = isLayoutRtl ? f2 : f;
        if (!isLayoutRtl) {
            f = f2;
        }
        float f6 = isLayoutRtl ? f4 : f3;
        if (!isLayoutRtl) {
            f3 = f4;
        }
        MaterialShapeDrawable materialShapeDrawable = this.M;
        if (materialShapeDrawable != null && materialShapeDrawable.getTopLeftCornerResolvedSize() == f5 && this.M.getTopRightCornerResolvedSize() == f && this.M.getBottomLeftCornerResolvedSize() == f6 && this.M.getBottomRightCornerResolvedSize() == f3) {
            return;
        }
        this.P = this.P.toBuilder().setTopLeftCornerSize(f5).setTopRightCornerSize(f).setBottomLeftCornerSize(f6).setBottomRightCornerSize(f3).build();
        j();
    }

    public void setBoxCornerRadiiResources(@DimenRes int i, @DimenRes int i2, @DimenRes int i3, @DimenRes int i4) {
        setBoxCornerRadii(getContext().getResources().getDimension(i), getContext().getResources().getDimension(i2), getContext().getResources().getDimension(i4), getContext().getResources().getDimension(i3));
    }

    public void setBoxStrokeColor(@ColorInt int i) {
        if (this.B0 != i) {
            this.B0 = i;
            v0();
        }
    }

    public void setBoxStrokeColorStateList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.z0 = colorStateList.getDefaultColor();
            this.H0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.A0 = colorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
            this.B0 = colorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        } else if (this.B0 != colorStateList.getDefaultColor()) {
            this.B0 = colorStateList.getDefaultColor();
        }
        v0();
    }

    public void setBoxStrokeErrorColor(@Nullable ColorStateList colorStateList) {
        if (this.C0 != colorStateList) {
            this.C0 = colorStateList;
            v0();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.V = i;
        v0();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.W = i;
        v0();
    }

    public void setBoxStrokeWidthFocusedResource(@DimenRes int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(@DimenRes int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.s != z) {
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.v = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_counter);
                Typeface typeface = this.f0;
                if (typeface != null) {
                    this.v.setTypeface(typeface);
                }
                this.v.setMaxLines(1);
                this.r.e(this.v, 2);
                MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) this.v.getLayoutParams(), getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
                f0();
                c0();
            } else {
                this.r.B(this.v, 2);
                this.v = null;
            }
            this.s = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.t != i) {
            if (i > 0) {
                this.t = i;
            } else {
                this.t = -1;
            }
            if (this.s) {
                c0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.w != i) {
            this.w = i;
            f0();
        }
    }

    public void setCounterOverflowTextColor(@Nullable ColorStateList colorStateList) {
        if (this.G != colorStateList) {
            this.G = colorStateList;
            f0();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.x != i) {
            this.x = i;
            f0();
        }
    }

    public void setCounterTextColor(@Nullable ColorStateList colorStateList) {
        if (this.F != colorStateList) {
            this.F = colorStateList;
            f0();
        }
    }

    public void setDefaultHintTextColor(@Nullable ColorStateList colorStateList) {
        this.x0 = colorStateList;
        this.y0 = colorStateList;
        if (this.l != null) {
            n0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        O(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.l0.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.l0.setCheckable(z);
    }

    public void setEndIconContentDescription(@StringRes int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconDrawable(@DrawableRes int i) {
        setEndIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setEndIconMode(int i) {
        int i2 = this.j0;
        if (i2 == i) {
            return;
        }
        this.j0 = i;
        A(i2);
        setEndIconVisible(i != 0);
        if (getEndIconDelegate().b(this.S)) {
            getEndIconDelegate().a();
            f.a(this, this.l0, this.n0, this.o0);
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.S + " is not supported by the end icon mode " + i);
    }

    public void setEndIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        S(this.l0, onClickListener, this.s0);
    }

    public void setEndIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.s0 = onLongClickListener;
        T(this.l0, onLongClickListener);
    }

    public void setEndIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.n0 != colorStateList) {
            this.n0 = colorStateList;
            f.a(this, this.l0, colorStateList, this.o0);
        }
    }

    public void setEndIconTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.o0 != mode) {
            this.o0 = mode;
            f.a(this, this.l0, this.n0, mode);
        }
    }

    public void setEndIconVisible(boolean z) {
        if (isEndIconVisible() != z) {
            this.l0.setVisibility(z ? 0 : 8);
            k0();
            t0();
            h0();
        }
    }

    public void setError(@Nullable CharSequence charSequence) {
        if (!this.r.z()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            }
            setErrorEnabled(true);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.r.O(charSequence);
        } else {
            this.r.v();
        }
    }

    public void setErrorContentDescription(@Nullable CharSequence charSequence) {
        this.r.D(charSequence);
    }

    public void setErrorEnabled(boolean z) {
        this.r.E(z);
    }

    public void setErrorIconDrawable(@DrawableRes int i) {
        setErrorIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
        refreshErrorIconDrawableState();
    }

    public void setErrorIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        S(this.u0, onClickListener, this.t0);
    }

    public void setErrorIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.t0 = onLongClickListener;
        T(this.u0, onLongClickListener);
    }

    public void setErrorIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.v0 != colorStateList) {
            this.v0 = colorStateList;
            f.a(this, this.u0, colorStateList, this.w0);
        }
    }

    public void setErrorIconTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.w0 != mode) {
            this.w0 = mode;
            f.a(this, this.u0, this.v0, mode);
        }
    }

    public void setErrorTextAppearance(@StyleRes int i) {
        this.r.F(i);
    }

    public void setErrorTextColor(@Nullable ColorStateList colorStateList) {
        this.r.G(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.K0 != z) {
            this.K0 = z;
            n0(false);
        }
    }

    public void setHelperText(@Nullable CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (isHelperTextEnabled()) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!isHelperTextEnabled()) {
            setHelperTextEnabled(true);
        }
        this.r.P(charSequence);
    }

    public void setHelperTextColor(@Nullable ColorStateList colorStateList) {
        this.r.J(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.r.I(z);
    }

    public void setHelperTextTextAppearance(@StyleRes int i) {
        this.r.H(i);
    }

    public void setHint(@Nullable CharSequence charSequence) {
        if (this.J) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.L0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.J) {
            this.J = z;
            if (!z) {
                this.L = false;
                if (!TextUtils.isEmpty(this.K) && TextUtils.isEmpty(this.l.getHint())) {
                    this.l.setHint(this.K);
                }
                setHintInternal(null);
            } else {
                CharSequence hint = this.l.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.K)) {
                        setHint(hint);
                    }
                    this.l.setHint((CharSequence) null);
                }
                this.L = true;
            }
            if (this.l != null) {
                m0();
            }
        }
    }

    public void setHintTextAppearance(@StyleRes int i) {
        this.J0.setCollapsedTextAppearance(i);
        this.y0 = this.J0.getCollapsedTextColor();
        if (this.l != null) {
            n0(false);
            m0();
        }
    }

    public void setHintTextColor(@Nullable ColorStateList colorStateList) {
        if (this.y0 != colorStateList) {
            if (this.x0 == null) {
                this.J0.setCollapsedTextColor(colorStateList);
            }
            this.y0 = colorStateList;
            if (this.l != null) {
                n0(false);
            }
        }
    }

    public void setMaxEms(int i) {
        this.o = i;
        EditText editText = this.l;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxEms(i);
    }

    public void setMaxWidth(@Px int i) {
        this.q = i;
        EditText editText = this.l;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxWidth(i);
    }

    public void setMaxWidthResource(@DimenRes int i) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    public void setMinEms(int i) {
        this.n = i;
        EditText editText = this.l;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinEms(i);
    }

    public void setMinWidth(@Px int i) {
        this.p = i;
        EditText editText = this.l;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinWidth(i);
    }

    public void setMinWidthResource(@DimenRes int i) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@StringRes int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.j0 != 1) {
            setEndIconMode(1);
        } else if (z) {
        } else {
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList colorStateList) {
        this.n0 = colorStateList;
        f.a(this, this.l0, colorStateList, this.o0);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(@Nullable PorterDuff.Mode mode) {
        this.o0 = mode;
        f.a(this, this.l0, this.n0, mode);
    }

    public void setPlaceholderText(@Nullable CharSequence charSequence) {
        if (this.A == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.A = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_placeholder);
            ViewCompat.setImportantForAccessibility(this.A, 2);
            Fade x = x();
            this.D = x;
            x.setStartDelay(67L);
            this.E = x();
            setPlaceholderTextAppearance(this.C);
            setPlaceholderTextColor(this.B);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.z) {
                setPlaceholderTextEnabled(true);
            }
            this.y = charSequence;
        }
        q0();
    }

    public void setPlaceholderTextAppearance(@StyleRes int i) {
        this.C = i;
        TextView textView = this.A;
        if (textView != null) {
            TextViewCompat.setTextAppearance(textView, i);
        }
    }

    public void setPlaceholderTextColor(@Nullable ColorStateList colorStateList) {
        if (this.B != colorStateList) {
            this.B = colorStateList;
            TextView textView = this.A;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(@Nullable CharSequence charSequence) {
        this.i.l(charSequence);
    }

    public void setPrefixTextAppearance(@StyleRes int i) {
        this.i.m(i);
    }

    public void setPrefixTextColor(@NonNull ColorStateList colorStateList) {
        this.i.n(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.i.o(z);
    }

    public void setStartIconContentDescription(@StringRes int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconDrawable(@DrawableRes int i) {
        setStartIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setStartIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.i.r(onClickListener);
    }

    public void setStartIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.i.s(onLongClickListener);
    }

    public void setStartIconTintList(@Nullable ColorStateList colorStateList) {
        this.i.t(colorStateList);
    }

    public void setStartIconTintMode(@Nullable PorterDuff.Mode mode) {
        this.i.u(mode);
    }

    public void setStartIconVisible(boolean z) {
        this.i.v(z);
    }

    public void setSuffixText(@Nullable CharSequence charSequence) {
        this.H = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.I.setText(charSequence);
        u0();
    }

    public void setSuffixTextAppearance(@StyleRes int i) {
        TextViewCompat.setTextAppearance(this.I, i);
    }

    public void setSuffixTextColor(@NonNull ColorStateList colorStateList) {
        this.I.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(@Nullable AccessibilityDelegate accessibilityDelegate) {
        EditText editText = this.l;
        if (editText != null) {
            ViewCompat.setAccessibilityDelegate(editText, accessibilityDelegate);
        }
    }

    public void setTypeface(@Nullable Typeface typeface) {
        if (typeface != this.f0) {
            this.f0 = typeface;
            this.J0.setTypefaces(typeface);
            this.r.L(typeface);
            TextView textView = this.v;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public final boolean t() {
        return this.S == 2 && u();
    }

    public final void t0() {
        if (this.l == null) {
            return;
        }
        ViewCompat.setPaddingRelative(this.I, getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), this.l.getPaddingTop(), (isEndIconVisible() || I()) ? 0 : ViewCompat.getPaddingEnd(this.l), this.l.getPaddingBottom());
    }

    public final boolean u() {
        return this.U > -1 && this.a0 != 0;
    }

    public final void u0() {
        int visibility = this.I.getVisibility();
        int i = (this.H == null || J()) ? 8 : 0;
        if (visibility != i) {
            getEndIconDelegate().c(i == 0);
        }
        k0();
        this.I.setVisibility(i);
        h0();
    }

    public final void v() {
        if (y()) {
            ((com.google.android.material.textfield.c) this.M).z();
        }
    }

    public void v0() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.M == null || this.S == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.l) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.l) != null && editText.isHovered())) {
            z = true;
        }
        if (!isEnabled()) {
            this.a0 = this.H0;
        } else if (this.r.l()) {
            if (this.C0 != null) {
                s0(z2, z);
            } else {
                this.a0 = this.r.p();
            }
        } else if (!this.u || (textView = this.v) == null) {
            if (z2) {
                this.a0 = this.B0;
            } else if (z) {
                this.a0 = this.A0;
            } else {
                this.a0 = this.z0;
            }
        } else if (this.C0 != null) {
            s0(z2, z);
        } else {
            this.a0 = textView.getCurrentTextColor();
        }
        l0();
        refreshErrorIconDrawableState();
        refreshStartIconDrawableState();
        refreshEndIconDrawableState();
        if (getEndIconDelegate().d()) {
            Z(this.r.l());
        }
        if (this.S == 2) {
            int i = this.U;
            if (z2 && isEnabled()) {
                this.U = this.W;
            } else {
                this.U = this.V;
            }
            if (this.U != i) {
                N();
            }
        }
        if (this.S == 1) {
            if (!isEnabled()) {
                this.b0 = this.E0;
            } else if (z && !z2) {
                this.b0 = this.G0;
            } else if (z2) {
                this.b0 = this.F0;
            } else {
                this.b0 = this.D0;
            }
        }
        j();
    }

    public final void w(boolean z) {
        ValueAnimator valueAnimator = this.M0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.M0.cancel();
        }
        if (z && this.L0) {
            i(1.0f);
        } else {
            this.J0.setExpansionFraction(1.0f);
        }
        this.I0 = false;
        if (y()) {
            M();
        }
        q0();
        this.i.j(false);
        u0();
    }

    public final Fade x() {
        Fade fade = new Fade();
        fade.setDuration(87L);
        fade.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return fade;
    }

    public final boolean y() {
        return this.J && !TextUtils.isEmpty(this.K) && (this.M instanceof com.google.android.material.textfield.c);
    }

    public final void z() {
        Iterator<OnEditTextAttachedListener> it = this.i0.iterator();
        while (it.hasNext()) {
            it.next().onEditTextAttached(this);
        }
    }

    public TextInputLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textInputStyle);
    }

    public void setEndIconContentDescription(@Nullable CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.l0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(@Nullable Drawable drawable) {
        this.l0.setImageDrawable(drawable);
        if (drawable != null) {
            f.a(this, this.l0, this.n0, this.o0);
            refreshEndIconDrawableState();
        }
    }

    public void setStartIconContentDescription(@Nullable CharSequence charSequence) {
        this.i.p(charSequence);
    }

    public void setStartIconDrawable(@Nullable Drawable drawable) {
        this.i.q(drawable);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v109 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v50, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextInputLayout(@androidx.annotation.NonNull android.content.Context r27, @androidx.annotation.Nullable android.util.AttributeSet r28, int r29) {
        /*
            Method dump skipped, instructions count: 1386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setErrorIconDrawable(@Nullable Drawable drawable) {
        this.u0.setImageDrawable(drawable);
        l0();
        f.a(this, this.u0, this.v0, this.w0);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        this.l0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        this.l0.setImageDrawable(drawable);
    }

    public void setHint(@StringRes int i) {
        setHint(i != 0 ? getResources().getText(i) : null);
    }
}
