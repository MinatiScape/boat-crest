package com.coveiot.android.theme.utils;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
/* loaded from: classes7.dex */
public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    /* renamed from: a  reason: collision with root package name */
    public int f6102a;
    public ImageView b;

    public BottomNavigationViewBehavior(ImageView imageView) {
        this.b = imageView;
    }

    public final void a(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.clearAnimation();
        bottomNavigationView.animate().translationY(this.f6102a).setDuration(200L);
        this.b.clearAnimation();
        this.b.animate().translationY(this.f6102a).setDuration(200L);
    }

    public final void b(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.clearAnimation();
        bottomNavigationView.animate().translationY(0.0f).setDuration(200L);
        this.b.clearAnimation();
        this.b.animate().translationY(0.0f).setDuration(200L);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, BottomNavigationView bottomNavigationView, @NonNull View view, @NonNull View view2, int i, int i2) {
        return i == 2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, BottomNavigationView bottomNavigationView, int i) {
        this.f6102a = bottomNavigationView.getHeight();
        return super.onLayoutChild(coordinatorLayout, (CoordinatorLayout) bottomNavigationView, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView bottomNavigationView, @NonNull View view, int i, int i2, int i3, int i4, int i5) {
        if (i2 > 0) {
            a(bottomNavigationView);
        } else if (i2 < 0) {
            b(bottomNavigationView);
        }
    }
}
