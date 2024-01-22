package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
/* loaded from: classes10.dex */
public class c<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* renamed from: a  reason: collision with root package name */
    public d f10217a;
    public int b;
    public int c;

    public c() {
        this.b = 0;
        this.c = 0;
    }

    public int getLeftAndRightOffset() {
        d dVar = this.f10217a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    public int getTopAndBottomOffset() {
        d dVar = this.f10217a;
        if (dVar != null) {
            return dVar.d();
        }
        return 0;
    }

    public boolean isHorizontalOffsetEnabled() {
        d dVar = this.f10217a;
        return dVar != null && dVar.e();
    }

    public boolean isVerticalOffsetEnabled() {
        d dVar = this.f10217a;
        return dVar != null && dVar.f();
    }

    public void layoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i) {
        coordinatorLayout.onLayoutChild(v, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i) {
        layoutChild(coordinatorLayout, v, i);
        if (this.f10217a == null) {
            this.f10217a = new d(v);
        }
        this.f10217a.g();
        this.f10217a.a();
        int i2 = this.b;
        if (i2 != 0) {
            this.f10217a.j(i2);
            this.b = 0;
        }
        int i3 = this.c;
        if (i3 != 0) {
            this.f10217a.i(i3);
            this.c = 0;
            return true;
        }
        return true;
    }

    public void setHorizontalOffsetEnabled(boolean z) {
        d dVar = this.f10217a;
        if (dVar != null) {
            dVar.h(z);
        }
    }

    public boolean setLeftAndRightOffset(int i) {
        d dVar = this.f10217a;
        if (dVar != null) {
            return dVar.i(i);
        }
        this.c = i;
        return false;
    }

    public boolean setTopAndBottomOffset(int i) {
        d dVar = this.f10217a;
        if (dVar != null) {
            return dVar.j(i);
        }
        this.b = i;
        return false;
    }

    public void setVerticalOffsetEnabled(boolean z) {
        d dVar = this.f10217a;
        if (dVar != null) {
            dVar.k(z);
        }
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.c = 0;
    }
}
