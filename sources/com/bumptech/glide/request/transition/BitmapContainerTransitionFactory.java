package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes2.dex */
public abstract class BitmapContainerTransitionFactory<R> implements TransitionFactory<R> {

    /* renamed from: a  reason: collision with root package name */
    public final TransitionFactory<Drawable> f2532a;

    /* loaded from: classes2.dex */
    public final class a implements Transition<R> {

        /* renamed from: a  reason: collision with root package name */
        public final Transition<Drawable> f2533a;

        public a(Transition<Drawable> transition) {
            this.f2533a = transition;
        }

        @Override // com.bumptech.glide.request.transition.Transition
        public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
            return this.f2533a.transition(new BitmapDrawable(viewAdapter.getView().getResources(), BitmapContainerTransitionFactory.this.getBitmap(r)), viewAdapter);
        }
    }

    public BitmapContainerTransitionFactory(TransitionFactory<Drawable> transitionFactory) {
        this.f2532a = transitionFactory;
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        return new a(this.f2532a.build(dataSource, z));
    }

    public abstract Bitmap getBitmap(R r);
}
