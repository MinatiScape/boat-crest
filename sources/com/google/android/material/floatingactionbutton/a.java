package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public class a {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Animator f10301a;

    public void a() {
        Animator animator = this.f10301a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f10301a = null;
    }

    public void c(Animator animator) {
        a();
        this.f10301a = animator;
    }
}
