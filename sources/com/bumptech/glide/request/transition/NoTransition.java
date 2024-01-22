package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes2.dex */
public class NoTransition<R> implements Transition<R> {

    /* renamed from: a  reason: collision with root package name */
    public static final NoTransition<?> f2537a = new NoTransition<>();
    public static final TransitionFactory<?> b = new NoAnimationFactory();

    /* loaded from: classes2.dex */
    public static class NoAnimationFactory<R> implements TransitionFactory<R> {
        @Override // com.bumptech.glide.request.transition.TransitionFactory
        public Transition<R> build(DataSource dataSource, boolean z) {
            return NoTransition.f2537a;
        }
    }

    public static <R> Transition<R> get() {
        return f2537a;
    }

    public static <R> TransitionFactory<R> getFactory() {
        return (TransitionFactory<R>) b;
    }

    @Override // com.bumptech.glide.request.transition.Transition
    public boolean transition(Object obj, Transition.ViewAdapter viewAdapter) {
        return false;
    }
}
