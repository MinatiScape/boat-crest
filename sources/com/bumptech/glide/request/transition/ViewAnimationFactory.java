package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewTransition;
/* loaded from: classes2.dex */
public class ViewAnimationFactory<R> implements TransitionFactory<R> {

    /* renamed from: a  reason: collision with root package name */
    public final ViewTransition.a f2538a;
    public Transition<R> b;

    /* loaded from: classes2.dex */
    public static class a implements ViewTransition.a {

        /* renamed from: a  reason: collision with root package name */
        public final Animation f2539a;

        public a(Animation animation) {
            this.f2539a = animation;
        }

        @Override // com.bumptech.glide.request.transition.ViewTransition.a
        public Animation a(Context context) {
            return this.f2539a;
        }
    }

    /* loaded from: classes2.dex */
    public static class b implements ViewTransition.a {

        /* renamed from: a  reason: collision with root package name */
        public final int f2540a;

        public b(int i) {
            this.f2540a = i;
        }

        @Override // com.bumptech.glide.request.transition.ViewTransition.a
        public Animation a(Context context) {
            return AnimationUtils.loadAnimation(context, this.f2540a);
        }
    }

    public ViewAnimationFactory(Animation animation) {
        this(new a(animation));
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        if (dataSource != DataSource.MEMORY_CACHE && z) {
            if (this.b == null) {
                this.b = new ViewTransition(this.f2538a);
            }
            return this.b;
        }
        return NoTransition.get();
    }

    public ViewAnimationFactory(int i) {
        this(new b(i));
    }

    public ViewAnimationFactory(ViewTransition.a aVar) {
        this.f2538a = aVar;
    }
}
