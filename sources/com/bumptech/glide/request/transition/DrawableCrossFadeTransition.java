package com.bumptech.glide.request.transition;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes2.dex */
public class DrawableCrossFadeTransition implements Transition<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    public final int f2536a;
    public final boolean b;

    public DrawableCrossFadeTransition(int i, boolean z) {
        this.f2536a = i;
        this.b = z;
    }

    @Override // com.bumptech.glide.request.transition.Transition
    public boolean transition(Drawable drawable, Transition.ViewAdapter viewAdapter) {
        Drawable currentDrawable = viewAdapter.getCurrentDrawable();
        if (currentDrawable == null) {
            currentDrawable = new ColorDrawable(0);
        }
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{currentDrawable, drawable});
        transitionDrawable.setCrossFadeEnabled(this.b);
        transitionDrawable.startTransition(this.f2536a);
        viewAdapter.setDrawable(transitionDrawable);
        return true;
    }
}
