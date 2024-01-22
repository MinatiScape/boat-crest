package androidx.viewpager2.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
/* loaded from: classes.dex */
public final class b extends ViewPager2.OnPageChangeCallback {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final List<ViewPager2.OnPageChangeCallback> f1764a;

    public b(int i) {
        this.f1764a = new ArrayList(i);
    }

    public void a(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.f1764a.add(onPageChangeCallback);
    }

    public void b(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.f1764a.remove(onPageChangeCallback);
    }

    public final void c(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageChangeCallback : this.f1764a) {
                onPageChangeCallback.onPageScrollStateChanged(i);
            }
        } catch (ConcurrentModificationException e) {
            c(e);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i, float f, @Px int i2) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageChangeCallback : this.f1764a) {
                onPageChangeCallback.onPageScrolled(i, f, i2);
            }
        } catch (ConcurrentModificationException e) {
            c(e);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int i) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageChangeCallback : this.f1764a) {
                onPageChangeCallback.onPageSelected(i);
            }
        } catch (ConcurrentModificationException e) {
            c(e);
        }
    }
}
