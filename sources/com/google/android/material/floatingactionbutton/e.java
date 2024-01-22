package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import com.google.android.material.R;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
@RequiresApi(21)
/* loaded from: classes10.dex */
public class e extends d {

    /* loaded from: classes10.dex */
    public static class a extends MaterialShapeDrawable {
        public a(ShapeAppearanceModel shapeAppearanceModel) {
            super(shapeAppearanceModel);
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public e(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        super(floatingActionButton, shadowViewDelegate);
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void A() {
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void C() {
        i0();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void E(int[] iArr) {
        if (Build.VERSION.SDK_INT == 21) {
            if (this.w.isEnabled()) {
                this.w.setElevation(this.h);
                if (this.w.isPressed()) {
                    this.w.setTranslationZ(this.j);
                    return;
                } else if (!this.w.isFocused() && !this.w.isHovered()) {
                    this.w.setTranslationZ(0.0f);
                    return;
                } else {
                    this.w.setTranslationZ(this.i);
                    return;
                }
            }
            this.w.setElevation(0.0f);
            this.w.setTranslationZ(0.0f);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void F(float f, float f2, float f3) {
        int i = Build.VERSION.SDK_INT;
        if (i == 21) {
            this.w.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(d.E, m0(f, f3));
            stateListAnimator.addState(d.F, m0(f, f2));
            stateListAnimator.addState(d.G, m0(f, f2));
            stateListAnimator.addState(d.H, m0(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.w, "elevation", f).setDuration(0L));
            if (i >= 22 && i <= 24) {
                FloatingActionButton floatingActionButton = this.w;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.w, View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(d.D);
            stateListAnimator.addState(d.I, animatorSet);
            stateListAnimator.addState(d.J, m0(0.0f, 0.0f));
            this.w.setStateListAnimator(stateListAnimator);
        }
        if (c0()) {
            i0();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public boolean N() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void Y(@Nullable ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(RippleUtils.sanitizeRippleDrawableColor(colorStateList));
        } else {
            super.Y(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public boolean c0() {
        return this.x.isCompatPaddingEnabled() || !e0();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void g0() {
    }

    @Override // com.google.android.material.floatingactionbutton.d
    @NonNull
    public MaterialShapeDrawable l() {
        return new a((ShapeAppearanceModel) Preconditions.checkNotNull(this.f10306a));
    }

    @NonNull
    public c l0(int i, ColorStateList colorStateList) {
        Context context = this.w.getContext();
        c cVar = new c((ShapeAppearanceModel) Preconditions.checkNotNull(this.f10306a));
        cVar.e(ContextCompat.getColor(context, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_outer_color));
        cVar.d(i);
        cVar.c(colorStateList);
        return cVar;
    }

    @NonNull
    public final Animator m0(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.w, "elevation", f).setDuration(0L)).with(ObjectAnimator.ofFloat(this.w, View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(d.D);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public float n() {
        return this.w.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void s(@NonNull Rect rect) {
        if (this.x.isCompatPaddingEnabled()) {
            super.s(rect);
        } else if (!e0()) {
            int sizeDimension = (this.k - this.w.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        } else {
            rect.set(0, 0, 0, 0);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void x(ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable drawable;
        MaterialShapeDrawable l = l();
        this.b = l;
        l.setTintList(colorStateList);
        if (mode != null) {
            this.b.setTintMode(mode);
        }
        this.b.initializeElevationOverlay(this.w.getContext());
        if (i > 0) {
            this.d = l0(i, colorStateList);
            drawable = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.checkNotNull(this.d), (Drawable) Preconditions.checkNotNull(this.b)});
        } else {
            this.d = null;
            drawable = this.b;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(colorStateList2), drawable, null);
        this.c = rippleDrawable;
        this.e = rippleDrawable;
    }
}
