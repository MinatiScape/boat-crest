package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
/* loaded from: classes2.dex */
public class ViewPropertyAnimationFactory<R> implements TransitionFactory<R> {

    /* renamed from: a  reason: collision with root package name */
    public final ViewPropertyTransition.Animator f2541a;
    public ViewPropertyTransition<R> b;

    public ViewPropertyAnimationFactory(ViewPropertyTransition.Animator animator) {
        this.f2541a = animator;
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        if (dataSource != DataSource.MEMORY_CACHE && z) {
            if (this.b == null) {
                this.b = new ViewPropertyTransition<>(this.f2541a);
            }
            return this.b;
        }
        return NoTransition.get();
    }
}
