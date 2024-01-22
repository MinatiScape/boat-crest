package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.textfield.TextInputLayout;
/* loaded from: classes10.dex */
public class a extends com.google.android.material.textfield.e {
    public final TextWatcher e;
    public final View.OnFocusChangeListener f;
    public final TextInputLayout.OnEditTextAttachedListener g;
    public final TextInputLayout.OnEndIconChangedListener h;
    public AnimatorSet i;
    public ValueAnimator j;

    /* renamed from: com.google.android.material.textfield.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0434a implements TextWatcher {
        public C0434a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(@NonNull Editable editable) {
            if (a.this.f10389a.getSuffixText() != null) {
                return;
            }
            a aVar = a.this;
            aVar.i(aVar.m());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* loaded from: classes10.dex */
    public class b implements View.OnFocusChangeListener {
        public b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            a aVar = a.this;
            aVar.i(aVar.m());
        }
    }

    /* loaded from: classes10.dex */
    public class c implements TextInputLayout.OnEditTextAttachedListener {
        public c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        public void onEditTextAttached(@NonNull TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(a.this.m());
            editText.setOnFocusChangeListener(a.this.f);
            a aVar = a.this;
            aVar.c.setOnFocusChangeListener(aVar.f);
            editText.removeTextChangedListener(a.this.e);
            editText.addTextChangedListener(a.this.e);
        }
    }

    /* loaded from: classes10.dex */
    public class d implements TextInputLayout.OnEndIconChangedListener {

        /* renamed from: com.google.android.material.textfield.a$d$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0435a implements Runnable {
            public final /* synthetic */ EditText h;

            public RunnableC0435a(EditText editText) {
                this.h = editText;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.removeTextChangedListener(a.this.e);
                a.this.i(true);
            }
        }

        public d() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener
        public void onEndIconChanged(@NonNull TextInputLayout textInputLayout, int i) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i != 2) {
                return;
            }
            editText.post(new RunnableC0435a(editText));
            if (editText.getOnFocusChangeListener() == a.this.f) {
                editText.setOnFocusChangeListener(null);
            }
            if (a.this.c.getOnFocusChangeListener() == a.this.f) {
                a.this.c.setOnFocusChangeListener(null);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Editable text = a.this.f10389a.getEditText().getText();
            if (text != null) {
                text.clear();
            }
            a.this.f10389a.refreshEndIconDrawableState();
        }
    }

    /* loaded from: classes10.dex */
    public class f extends AnimatorListenerAdapter {
        public f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.f10389a.setEndIconVisible(true);
        }
    }

    /* loaded from: classes10.dex */
    public class g extends AnimatorListenerAdapter {
        public g() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f10389a.setEndIconVisible(false);
        }
    }

    /* loaded from: classes10.dex */
    public class h implements ValueAnimator.AnimatorUpdateListener {
        public h() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            a.this.c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* loaded from: classes10.dex */
    public class i implements ValueAnimator.AnimatorUpdateListener {
        public i() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            a.this.c.setScaleX(floatValue);
            a.this.c.setScaleY(floatValue);
        }
    }

    public a(@NonNull TextInputLayout textInputLayout, @DrawableRes int i2) {
        super(textInputLayout, i2);
        this.e = new C0434a();
        this.f = new b();
        this.g = new c();
        this.h = new d();
    }

    @Override // com.google.android.material.textfield.e
    public void a() {
        TextInputLayout textInputLayout = this.f10389a;
        int i2 = this.d;
        if (i2 == 0) {
            i2 = R.drawable.mtrl_ic_cancel;
        }
        textInputLayout.setEndIconDrawable(i2);
        TextInputLayout textInputLayout2 = this.f10389a;
        textInputLayout2.setEndIconContentDescription(textInputLayout2.getResources().getText(R.string.clear_text_end_icon_content_description));
        this.f10389a.setEndIconCheckable(false);
        this.f10389a.setEndIconOnClickListener(new e());
        this.f10389a.addOnEditTextAttachedListener(this.g);
        this.f10389a.addOnEndIconChangedListener(this.h);
        l();
    }

    @Override // com.google.android.material.textfield.e
    public void c(boolean z) {
        if (this.f10389a.getSuffixText() == null) {
            return;
        }
        i(z);
    }

    public final void i(boolean z) {
        boolean z2 = this.f10389a.isEndIconVisible() == z;
        if (z && !this.i.isRunning()) {
            this.j.cancel();
            this.i.start();
            if (z2) {
                this.i.end();
            }
        } else if (z) {
        } else {
            this.i.cancel();
            this.j.start();
            if (z2) {
                this.j.end();
            }
        }
    }

    public final ValueAnimator j(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ofFloat.setDuration(100L);
        ofFloat.addUpdateListener(new h());
        return ofFloat;
    }

    public final ValueAnimator k() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        ofFloat.setDuration(150L);
        ofFloat.addUpdateListener(new i());
        return ofFloat;
    }

    public final void l() {
        ValueAnimator k = k();
        ValueAnimator j = j(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.i = animatorSet;
        animatorSet.playTogether(k, j);
        this.i.addListener(new f());
        ValueAnimator j2 = j(1.0f, 0.0f);
        this.j = j2;
        j2.addListener(new g());
    }

    public final boolean m() {
        EditText editText = this.f10389a.getEditText();
        return editText != null && (editText.hasFocus() || this.c.hasFocus()) && editText.getText().length() > 0;
    }
}
