package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class b implements f {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10302a;
    @NonNull
    public final ExtendedFloatingActionButton b;
    public final ArrayList<Animator.AnimatorListener> c = new ArrayList<>();
    public final com.google.android.material.floatingactionbutton.a d;
    @Nullable
    public MotionSpec e;
    @Nullable
    public MotionSpec f;

    /* loaded from: classes10.dex */
    public class a extends Property<ExtendedFloatingActionButton, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(ExtendedFloatingActionButton extendedFloatingActionButton) {
            return Float.valueOf(AnimationUtils.lerp(0.0f, 1.0f, (Color.alpha(extendedFloatingActionButton.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton.originalTextCsl.getColorForState(extendedFloatingActionButton.getDrawableState(), b.this.b.originalTextCsl.getDefaultColor()))));
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(ExtendedFloatingActionButton extendedFloatingActionButton, Float f) {
            int colorForState = extendedFloatingActionButton.originalTextCsl.getColorForState(extendedFloatingActionButton.getDrawableState(), b.this.b.originalTextCsl.getDefaultColor());
            ColorStateList valueOf = ColorStateList.valueOf(Color.argb((int) (AnimationUtils.lerp(0.0f, Color.alpha(colorForState) / 255.0f, f.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
            if (f.floatValue() == 1.0f) {
                extendedFloatingActionButton.silentlyUpdateTextColor(extendedFloatingActionButton.originalTextCsl);
            } else {
                extendedFloatingActionButton.silentlyUpdateTextColor(valueOf);
            }
        }
    }

    public b(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton, com.google.android.material.floatingactionbutton.a aVar) {
        this.b = extendedFloatingActionButton;
        this.f10302a = extendedFloatingActionButton.getContext();
        this.d = aVar;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @Nullable
    public MotionSpec b() {
        return this.f;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void d(@NonNull Animator.AnimatorListener animatorListener) {
        this.c.remove(animatorListener);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @CallSuper
    public void e() {
        this.d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void g(@NonNull Animator.AnimatorListener animatorListener) {
        this.c.add(animatorListener);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @NonNull
    public final List<Animator.AnimatorListener> getListeners() {
        return this.c;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void h(@Nullable MotionSpec motionSpec) {
        this.f = motionSpec;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public AnimatorSet i() {
        return l(m());
    }

    @NonNull
    public AnimatorSet l(@NonNull MotionSpec motionSpec) {
        ArrayList arrayList = new ArrayList();
        if (motionSpec.hasPropertyValues("opacity")) {
            arrayList.add(motionSpec.getAnimator("opacity", this.b, View.ALPHA));
        }
        if (motionSpec.hasPropertyValues("scale")) {
            arrayList.add(motionSpec.getAnimator("scale", this.b, View.SCALE_Y));
            arrayList.add(motionSpec.getAnimator("scale", this.b, View.SCALE_X));
        }
        if (motionSpec.hasPropertyValues(com.mappls.sdk.maps.style.layers.Property.ICON_TEXT_FIT_WIDTH)) {
            arrayList.add(motionSpec.getAnimator(com.mappls.sdk.maps.style.layers.Property.ICON_TEXT_FIT_WIDTH, this.b, ExtendedFloatingActionButton.O));
        }
        if (motionSpec.hasPropertyValues(com.mappls.sdk.maps.style.layers.Property.ICON_TEXT_FIT_HEIGHT)) {
            arrayList.add(motionSpec.getAnimator(com.mappls.sdk.maps.style.layers.Property.ICON_TEXT_FIT_HEIGHT, this.b, ExtendedFloatingActionButton.P));
        }
        if (motionSpec.hasPropertyValues("paddingStart")) {
            arrayList.add(motionSpec.getAnimator("paddingStart", this.b, ExtendedFloatingActionButton.Q));
        }
        if (motionSpec.hasPropertyValues("paddingEnd")) {
            arrayList.add(motionSpec.getAnimator("paddingEnd", this.b, ExtendedFloatingActionButton.R));
        }
        if (motionSpec.hasPropertyValues("labelOpacity")) {
            arrayList.add(motionSpec.getAnimator("labelOpacity", this.b, new a(Float.class, "LABEL_OPACITY_PROPERTY")));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    public final MotionSpec m() {
        MotionSpec motionSpec = this.f;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.e == null) {
            this.e = MotionSpec.createFromResource(this.f10302a, f());
        }
        return (MotionSpec) Preconditions.checkNotNull(this.e);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @CallSuper
    public void onAnimationEnd() {
        this.d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @CallSuper
    public void onAnimationStart(Animator animator) {
        this.d.c(animator);
    }
}
