package com.mappls.sdk.maps.location;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes11.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    public static q f12782a;

    public static q a() {
        if (f12782a == null) {
            f12782a = new q();
        }
        return f12782a;
    }

    public void b(@NonNull List<Animator> list, @NonNull Interpolator interpolator, long j) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(list);
        animatorSet.setInterpolator(interpolator);
        animatorSet.setDuration(j);
        animatorSet.start();
    }
}
