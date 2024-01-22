package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
/* loaded from: classes.dex */
public final class MarginPageTransformer implements ViewPager2.PageTransformer {

    /* renamed from: a  reason: collision with root package name */
    public final int f1754a;

    public MarginPageTransformer(@Px int i) {
        Preconditions.checkArgumentNonnegative(i, "Margin must be non-negative");
        this.f1754a = i;
    }

    public final ViewPager2 a(@NonNull View view) {
        ViewParent parent = view.getParent();
        ViewParent parent2 = parent.getParent();
        if ((parent instanceof RecyclerView) && (parent2 instanceof ViewPager2)) {
            return (ViewPager2) parent2;
        }
        throw new IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.");
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(@NonNull View view, float f) {
        ViewPager2 a2 = a(view);
        float f2 = this.f1754a * f;
        if (a2.getOrientation() == 0) {
            if (a2.c()) {
                f2 = -f2;
            }
            view.setTranslationX(f2);
            return;
        }
        view.setTranslationY(f2);
    }
}
