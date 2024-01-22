package com.google.android.material.tabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;
/* loaded from: classes10.dex */
public final class TabLayoutMediator {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final TabLayout f10379a;
    @NonNull
    public final ViewPager2 b;
    public final boolean c;
    public final boolean d;
    public final TabConfigurationStrategy e;
    @Nullable
    public RecyclerView.Adapter<?> f;
    public boolean g;
    @Nullable
    public b h;
    @Nullable
    public TabLayout.OnTabSelectedListener i;
    @Nullable
    public RecyclerView.AdapterDataObserver j;

    /* loaded from: classes10.dex */
    public interface TabConfigurationStrategy {
        void onConfigureTab(@NonNull TabLayout.Tab tab, int i);
    }

    /* loaded from: classes10.dex */
    public class a extends RecyclerView.AdapterDataObserver {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            TabLayoutMediator.this.a();
        }
    }

    /* loaded from: classes10.dex */
    public static class b extends ViewPager2.OnPageChangeCallback {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<TabLayout> f10381a;
        public int b;
        public int c;

        public b(TabLayout tabLayout) {
            this.f10381a = new WeakReference<>(tabLayout);
            a();
        }

        public void a() {
            this.c = 0;
            this.b = 0;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int i) {
            this.b = this.c;
            this.c = i;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrolled(int i, float f, int i2) {
            TabLayout tabLayout = this.f10381a.get();
            if (tabLayout != null) {
                int i3 = this.c;
                boolean z = false;
                boolean z2 = i3 != 2 || this.b == 1;
                if (i3 != 2 || this.b != 0) {
                    z = true;
                }
                tabLayout.setScrollPosition(i, f, z2, z);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int i) {
            TabLayout tabLayout = this.f10381a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.c;
            tabLayout.selectTab(tabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.b == 0));
        }
    }

    /* loaded from: classes10.dex */
    public static class c implements TabLayout.OnTabSelectedListener {

        /* renamed from: a  reason: collision with root package name */
        public final ViewPager2 f10382a;
        public final boolean b;

        public c(ViewPager2 viewPager2, boolean z) {
            this.f10382a = viewPager2;
            this.b = z;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(@NonNull TabLayout.Tab tab) {
            this.f10382a.setCurrentItem(tab.getPosition(), this.b);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout, @NonNull ViewPager2 viewPager2, @NonNull TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager2, true, tabConfigurationStrategy);
    }

    public void a() {
        this.f10379a.removeAllTabs();
        RecyclerView.Adapter<?> adapter = this.f;
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            for (int i = 0; i < itemCount; i++) {
                TabLayout.Tab newTab = this.f10379a.newTab();
                this.e.onConfigureTab(newTab, i);
                this.f10379a.addTab(newTab, false);
            }
            if (itemCount > 0) {
                int min = Math.min(this.b.getCurrentItem(), this.f10379a.getTabCount() - 1);
                if (min != this.f10379a.getSelectedTabPosition()) {
                    TabLayout tabLayout = this.f10379a;
                    tabLayout.selectTab(tabLayout.getTabAt(min));
                }
            }
        }
    }

    public void attach() {
        if (!this.g) {
            RecyclerView.Adapter<?> adapter = this.b.getAdapter();
            this.f = adapter;
            if (adapter != null) {
                this.g = true;
                b bVar = new b(this.f10379a);
                this.h = bVar;
                this.b.registerOnPageChangeCallback(bVar);
                c cVar = new c(this.b, this.d);
                this.i = cVar;
                this.f10379a.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) cVar);
                if (this.c) {
                    a aVar = new a();
                    this.j = aVar;
                    this.f.registerAdapterDataObserver(aVar);
                }
                a();
                this.f10379a.setScrollPosition(this.b.getCurrentItem(), 0.0f, true);
                return;
            }
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        throw new IllegalStateException("TabLayoutMediator is already attached");
    }

    public void detach() {
        RecyclerView.Adapter<?> adapter;
        if (this.c && (adapter = this.f) != null) {
            adapter.unregisterAdapterDataObserver(this.j);
            this.j = null;
        }
        this.f10379a.removeOnTabSelectedListener(this.i);
        this.b.unregisterOnPageChangeCallback(this.h);
        this.i = null;
        this.h = null;
        this.f = null;
        this.g = false;
    }

    public boolean isAttached() {
        return this.g;
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout, @NonNull ViewPager2 viewPager2, boolean z, @NonNull TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager2, z, true, tabConfigurationStrategy);
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout, @NonNull ViewPager2 viewPager2, boolean z, boolean z2, @NonNull TabConfigurationStrategy tabConfigurationStrategy) {
        this.f10379a = tabLayout;
        this.b = viewPager2;
        this.c = z;
        this.d = z2;
        this.e = tabConfigurationStrategy;
    }
}
