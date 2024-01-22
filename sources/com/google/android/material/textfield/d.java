package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout;
/* loaded from: classes10.dex */
public class d extends com.google.android.material.textfield.e {
    public static final boolean t;
    public final TextWatcher e;
    public final View.OnFocusChangeListener f;
    public final TextInputLayout.AccessibilityDelegate g;
    public final TextInputLayout.OnEditTextAttachedListener h;
    @SuppressLint({"ClickableViewAccessibility"})
    public final TextInputLayout.OnEndIconChangedListener i;
    public final View.OnAttachStateChangeListener j;
    public final AccessibilityManagerCompat.TouchExplorationStateChangeListener k;
    public boolean l;
    public boolean m;
    public long n;
    public StateListDrawable o;
    public MaterialShapeDrawable p;
    @Nullable
    public AccessibilityManager q;
    public ValueAnimator r;
    public ValueAnimator s;

    /* loaded from: classes10.dex */
    public class a extends TextWatcherAdapter {

        /* renamed from: com.google.android.material.textfield.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0436a implements Runnable {
            public final /* synthetic */ AutoCompleteTextView h;

            public RunnableC0436a(AutoCompleteTextView autoCompleteTextView) {
                this.h = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean isPopupShowing = this.h.isPopupShowing();
                d.this.J(isPopupShowing);
                d.this.l = isPopupShowing;
            }
        }

        public a() {
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            AutoCompleteTextView C = d.C(d.this.f10389a.getEditText());
            if (d.this.q.isTouchExplorationEnabled() && d.H(C) && !d.this.c.hasFocus()) {
                C.dismissDropDown();
            }
            C.post(new RunnableC0436a(C));
        }
    }

    /* loaded from: classes10.dex */
    public class b implements AutoCompleteTextView.OnDismissListener {
        public b() {
        }

        @Override // android.widget.AutoCompleteTextView.OnDismissListener
        public void onDismiss() {
            d.this.N();
            d.this.J(false);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d dVar = d.this;
            dVar.c.setChecked(dVar.m);
            d.this.s.start();
        }
    }

    /* renamed from: com.google.android.material.textfield.d$d  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0437d implements ValueAnimator.AnimatorUpdateListener {
        public C0437d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            d.this.c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* loaded from: classes10.dex */
    public class e implements View.OnFocusChangeListener {
        public e() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            d.this.f10389a.setEndIconActivated(z);
            if (z) {
                return;
            }
            d.this.J(false);
            d.this.l = false;
        }
    }

    /* loaded from: classes10.dex */
    public class f extends TextInputLayout.AccessibilityDelegate {
        public f(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.AccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (!d.H(d.this.f10389a.getEditText())) {
                accessibilityNodeInfoCompat.setClassName(Spinner.class.getName());
            }
            if (accessibilityNodeInfoCompat.isShowingHintText()) {
                accessibilityNodeInfoCompat.setHintText(null);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View view, @NonNull AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            AutoCompleteTextView C = d.C(d.this.f10389a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.q.isEnabled() && !d.H(d.this.f10389a.getEditText())) {
                d.this.M(C);
                d.this.N();
            }
        }
    }

    /* loaded from: classes10.dex */
    public class g implements TextInputLayout.OnEditTextAttachedListener {
        public g() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        public void onEditTextAttached(@NonNull TextInputLayout textInputLayout) {
            AutoCompleteTextView C = d.C(textInputLayout.getEditText());
            d.this.K(C);
            d.this.y(C);
            d.this.L(C);
            C.setThreshold(0);
            C.removeTextChangedListener(d.this.e);
            C.addTextChangedListener(d.this.e);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            if (!d.H(C) && d.this.q.isTouchExplorationEnabled()) {
                ViewCompat.setImportantForAccessibility(d.this.c, 2);
            }
            textInputLayout.setTextInputAccessibilityDelegate(d.this.g);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* loaded from: classes10.dex */
    public class h implements TextInputLayout.OnEndIconChangedListener {

        /* loaded from: classes10.dex */
        public class a implements Runnable {
            public final /* synthetic */ AutoCompleteTextView h;

            public a(AutoCompleteTextView autoCompleteTextView) {
                this.h = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.removeTextChangedListener(d.this.e);
            }
        }

        public h() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener
        public void onEndIconChanged(@NonNull TextInputLayout textInputLayout, int i) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView != null && i == 3) {
                autoCompleteTextView.post(new a(autoCompleteTextView));
                if (autoCompleteTextView.getOnFocusChangeListener() == d.this.f) {
                    autoCompleteTextView.setOnFocusChangeListener(null);
                }
                autoCompleteTextView.setOnTouchListener(null);
                if (d.t) {
                    autoCompleteTextView.setOnDismissListener(null);
                }
            }
            if (i == 3) {
                textInputLayout.removeOnAttachStateChangeListener(d.this.j);
                d.this.I();
            }
        }
    }

    /* loaded from: classes10.dex */
    public class i implements View.OnAttachStateChangeListener {
        public i() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            d.this.B();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            d.this.I();
        }
    }

    /* loaded from: classes10.dex */
    public class j implements AccessibilityManagerCompat.TouchExplorationStateChangeListener {
        public j() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            AutoCompleteTextView autoCompleteTextView;
            TextInputLayout textInputLayout = d.this.f10389a;
            if (textInputLayout == null || (autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText()) == null || d.H(autoCompleteTextView)) {
                return;
            }
            ViewCompat.setImportantForAccessibility(d.this.c, z ? 2 : 1);
        }
    }

    /* loaded from: classes10.dex */
    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.M((AutoCompleteTextView) d.this.f10389a.getEditText());
        }
    }

    /* loaded from: classes10.dex */
    public class l implements View.OnTouchListener {
        public final /* synthetic */ AutoCompleteTextView h;

        public l(AutoCompleteTextView autoCompleteTextView) {
            this.h = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(@NonNull View view, @NonNull MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.G()) {
                    d.this.l = false;
                }
                d.this.M(this.h);
                d.this.N();
            }
            return false;
        }
    }

    static {
        t = Build.VERSION.SDK_INT >= 21;
    }

    public d(@NonNull TextInputLayout textInputLayout, @DrawableRes int i2) {
        super(textInputLayout, i2);
        this.e = new a();
        this.f = new e();
        this.g = new f(this.f10389a);
        this.h = new g();
        this.i = new h();
        this.j = new i();
        this.k = new j();
        this.l = false;
        this.m = false;
        this.n = Long.MAX_VALUE;
    }

    @NonNull
    public static AutoCompleteTextView C(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public static boolean H(@NonNull EditText editText) {
        return editText.getKeyListener() != null;
    }

    public final void A(@NonNull AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, @NonNull MaterialShapeDrawable materialShapeDrawable) {
        LayerDrawable layerDrawable;
        int color = MaterialColors.getColor(autoCompleteTextView, R.attr.colorSurface);
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
        int layer = MaterialColors.layer(i2, color, 0.1f);
        materialShapeDrawable2.setFillColor(new ColorStateList(iArr, new int[]{layer, 0}));
        if (t) {
            materialShapeDrawable2.setTint(color);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{layer, color});
            MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
            materialShapeDrawable3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, materialShapeDrawable2, materialShapeDrawable3), materialShapeDrawable});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable});
        }
        ViewCompat.setBackground(autoCompleteTextView, layerDrawable);
    }

    public final void B() {
        TextInputLayout textInputLayout;
        if (this.q == null || (textInputLayout = this.f10389a) == null || !ViewCompat.isAttachedToWindow(textInputLayout)) {
            return;
        }
        AccessibilityManagerCompat.addTouchExplorationStateChangeListener(this.q, this.k);
    }

    public final ValueAnimator D(int i2, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ofFloat.setDuration(i2);
        ofFloat.addUpdateListener(new C0437d());
        return ofFloat;
    }

    public final MaterialShapeDrawable E(float f2, float f3, float f4, int i2) {
        ShapeAppearanceModel build = ShapeAppearanceModel.builder().setTopLeftCornerSize(f2).setTopRightCornerSize(f2).setBottomLeftCornerSize(f3).setBottomRightCornerSize(f3).build();
        MaterialShapeDrawable createWithElevationOverlay = MaterialShapeDrawable.createWithElevationOverlay(this.b, f4);
        createWithElevationOverlay.setShapeAppearanceModel(build);
        createWithElevationOverlay.setPadding(0, i2, 0, i2);
        return createWithElevationOverlay;
    }

    public final void F() {
        this.s = D(67, 0.0f, 1.0f);
        ValueAnimator D = D(50, 1.0f, 0.0f);
        this.r = D;
        D.addListener(new c());
    }

    public final boolean G() {
        long currentTimeMillis = System.currentTimeMillis() - this.n;
        return currentTimeMillis < 0 || currentTimeMillis > 300;
    }

    public final void I() {
        AccessibilityManager accessibilityManager = this.q;
        if (accessibilityManager != null) {
            AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(accessibilityManager, this.k);
        }
    }

    public final void J(boolean z) {
        if (this.m != z) {
            this.m = z;
            this.s.cancel();
            this.r.start();
        }
    }

    public final void K(@NonNull AutoCompleteTextView autoCompleteTextView) {
        if (t) {
            int boxBackgroundMode = this.f10389a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.p);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.o);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void L(@NonNull AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new l(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(this.f);
        if (t) {
            autoCompleteTextView.setOnDismissListener(new b());
        }
    }

    public final void M(@Nullable AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            return;
        }
        if (G()) {
            this.l = false;
        }
        if (!this.l) {
            if (t) {
                J(!this.m);
            } else {
                this.m = !this.m;
                this.c.toggle();
            }
            if (this.m) {
                autoCompleteTextView.requestFocus();
                autoCompleteTextView.showDropDown();
                return;
            }
            autoCompleteTextView.dismissDropDown();
            return;
        }
        this.l = false;
    }

    public final void N() {
        this.l = true;
        this.n = System.currentTimeMillis();
    }

    public void O(@NonNull AutoCompleteTextView autoCompleteTextView) {
        if (!H(autoCompleteTextView) && this.f10389a.getBoxBackgroundMode() == 2 && (autoCompleteTextView.getBackground() instanceof LayerDrawable)) {
            y(autoCompleteTextView);
        }
    }

    @Override // com.google.android.material.textfield.e
    public void a() {
        float dimensionPixelOffset = this.b.getResources().getDimensionPixelOffset(R.dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.b.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.b.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        MaterialShapeDrawable E = E(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        MaterialShapeDrawable E2 = E(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.p = E;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.o = stateListDrawable;
        stateListDrawable.addState(new int[]{16842922}, E);
        this.o.addState(new int[0], E2);
        int i2 = this.d;
        if (i2 == 0) {
            i2 = t ? R.drawable.mtrl_dropdown_arrow : R.drawable.mtrl_ic_arrow_drop_down;
        }
        this.f10389a.setEndIconDrawable(i2);
        TextInputLayout textInputLayout = this.f10389a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.exposed_dropdown_menu_content_description));
        this.f10389a.setEndIconOnClickListener(new k());
        this.f10389a.addOnEditTextAttachedListener(this.h);
        this.f10389a.addOnEndIconChangedListener(this.i);
        F();
        this.q = (AccessibilityManager) this.b.getSystemService("accessibility");
        this.f10389a.addOnAttachStateChangeListener(this.j);
        B();
    }

    @Override // com.google.android.material.textfield.e
    public boolean b(int i2) {
        return i2 != 0;
    }

    @Override // com.google.android.material.textfield.e
    public boolean d() {
        return true;
    }

    public final void y(@NonNull AutoCompleteTextView autoCompleteTextView) {
        if (H(autoCompleteTextView)) {
            return;
        }
        int boxBackgroundMode = this.f10389a.getBoxBackgroundMode();
        MaterialShapeDrawable boxBackground = this.f10389a.getBoxBackground();
        int color = MaterialColors.getColor(autoCompleteTextView, R.attr.colorControlHighlight);
        int[][] iArr = {new int[]{16842919}, new int[0]};
        if (boxBackgroundMode == 2) {
            A(autoCompleteTextView, color, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            z(autoCompleteTextView, color, iArr, boxBackground);
        }
    }

    public final void z(@NonNull AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, @NonNull MaterialShapeDrawable materialShapeDrawable) {
        int boxBackgroundColor = this.f10389a.getBoxBackgroundColor();
        int[] iArr2 = {MaterialColors.layer(i2, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (t) {
            ViewCompat.setBackground(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), materialShapeDrawable, materialShapeDrawable));
            return;
        }
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
        materialShapeDrawable2.setFillColor(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{materialShapeDrawable, materialShapeDrawable2});
        int paddingStart = ViewCompat.getPaddingStart(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        ViewCompat.setBackground(autoCompleteTextView, layerDrawable);
        ViewCompat.setPaddingRelative(autoCompleteTextView, paddingStart, paddingTop, paddingEnd, paddingBottom);
    }
}
