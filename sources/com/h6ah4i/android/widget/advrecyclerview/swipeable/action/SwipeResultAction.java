package com.h6ah4i.android.widget.advrecyclerview.swipeable.action;
/* loaded from: classes11.dex */
public abstract class SwipeResultAction {

    /* renamed from: a  reason: collision with root package name */
    public final int f11925a;

    public SwipeResultAction(int i) {
        this.f11925a = i;
    }

    public int getResultActionType() {
        return this.f11925a;
    }

    public void onCleanUp() {
    }

    public void onPerformAction() {
    }

    public void onSlideAnimationEnd() {
    }

    public final void performAction() {
        onPerformAction();
    }

    public final void slideAnimationEnd() {
        onSlideAnimationEnd();
        onCleanUp();
    }
}
