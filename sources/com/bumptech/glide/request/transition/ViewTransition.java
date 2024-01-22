package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes2.dex */
public class ViewTransition<R> implements Transition<R> {

    /* renamed from: a  reason: collision with root package name */
    public final a f2543a;

    /* loaded from: classes2.dex */
    public interface a {
        Animation a(Context context);
    }

    public ViewTransition(a aVar) {
        this.f2543a = aVar;
    }

    @Override // com.bumptech.glide.request.transition.Transition
    public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
        View view = viewAdapter.getView();
        if (view != null) {
            view.clearAnimation();
            view.startAnimation(this.f2543a.a(view.getContext()));
            return false;
        }
        return false;
    }
}
