package com.bumptech.glide.request.transition;

import android.view.View;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes2.dex */
public class ViewPropertyTransition<R> implements Transition<R> {

    /* renamed from: a  reason: collision with root package name */
    public final Animator f2542a;

    /* loaded from: classes2.dex */
    public interface Animator {
        void animate(View view);
    }

    public ViewPropertyTransition(Animator animator) {
        this.f2542a = animator;
    }

    @Override // com.bumptech.glide.request.transition.Transition
    public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
        if (viewAdapter.getView() != null) {
            this.f2542a.animate(viewAdapter.getView());
            return false;
        }
        return false;
    }
}
