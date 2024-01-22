package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;
/* loaded from: classes10.dex */
public interface f {
    void a();

    @Nullable
    MotionSpec b();

    boolean c();

    void d(@NonNull Animator.AnimatorListener animatorListener);

    void e();

    @AnimatorRes
    int f();

    void g(@NonNull Animator.AnimatorListener animatorListener);

    List<Animator.AnimatorListener> getListeners();

    void h(@Nullable MotionSpec motionSpec);

    AnimatorSet i();

    void j(@Nullable ExtendedFloatingActionButton.OnChangedCallback onChangedCallback);

    void onAnimationEnd();

    void onAnimationStart(Animator animator);
}
