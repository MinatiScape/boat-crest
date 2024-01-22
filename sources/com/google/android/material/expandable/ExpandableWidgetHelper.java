package com.google.android.material.expandable;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
/* loaded from: classes10.dex */
public final class ExpandableWidgetHelper {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final View f10293a;
    public boolean b = false;
    @IdRes
    public int c = 0;

    public ExpandableWidgetHelper(ExpandableWidget expandableWidget) {
        this.f10293a = (View) expandableWidget;
    }

    public final void a() {
        ViewParent parent = this.f10293a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).dispatchDependentViewsChanged(this.f10293a);
        }
    }

    @IdRes
    public int getExpandedComponentIdHint() {
        return this.c;
    }

    public boolean isExpanded() {
        return this.b;
    }

    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        this.b = bundle.getBoolean("expanded", false);
        this.c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.b) {
            a();
        }
    }

    @NonNull
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.b);
        bundle.putInt("expandedComponentIdHint", this.c);
        return bundle;
    }

    public boolean setExpanded(boolean z) {
        if (this.b != z) {
            this.b = z;
            a();
            return true;
        }
        return false;
    }

    public void setExpandedComponentIdHint(@IdRes int i) {
        this.c = i;
    }
}
