package androidx.viewpager2.widget;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;
/* loaded from: classes.dex */
public final class d extends ViewPager2.OnPageChangeCallback {

    /* renamed from: a  reason: collision with root package name */
    public final LinearLayoutManager f1766a;
    public ViewPager2.PageTransformer b;

    public d(LinearLayoutManager linearLayoutManager) {
        this.f1766a = linearLayoutManager;
    }

    public ViewPager2.PageTransformer a() {
        return this.b;
    }

    public void b(@Nullable ViewPager2.PageTransformer pageTransformer) {
        this.b = pageTransformer;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i, float f, int i2) {
        if (this.b == null) {
            return;
        }
        float f2 = -f;
        for (int i3 = 0; i3 < this.f1766a.getChildCount(); i3++) {
            View childAt = this.f1766a.getChildAt(i3);
            if (childAt != null) {
                this.b.transformPage(childAt, (this.f1766a.getPosition(childAt) - i) + f2);
            } else {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i3), Integer.valueOf(this.f1766a.getChildCount())));
            }
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int i) {
    }
}
