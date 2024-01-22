package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.collection.LongSparseArray;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
/* loaded from: classes.dex */
public abstract class FragmentStateAdapter extends RecyclerView.Adapter<FragmentViewHolder> implements StatefulAdapter {
    public final Lifecycle h;
    public final FragmentManager i;
    public final LongSparseArray<Fragment> j;
    public final LongSparseArray<Fragment.SavedState> k;
    public final LongSparseArray<Integer> l;
    public FragmentMaxLifecycleEnforcer m;
    public boolean n;
    public boolean o;

    /* loaded from: classes.dex */
    public class FragmentMaxLifecycleEnforcer {

        /* renamed from: a  reason: collision with root package name */
        public ViewPager2.OnPageChangeCallback f1748a;
        public RecyclerView.AdapterDataObserver b;
        public LifecycleEventObserver c;
        public ViewPager2 d;
        public long e = -1;

        /* loaded from: classes.dex */
        public class a extends ViewPager2.OnPageChangeCallback {
            public a() {
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }
        }

        /* loaded from: classes.dex */
        public class b extends d {
            public b() {
                super(null);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                FragmentMaxLifecycleEnforcer.this.d(true);
            }
        }

        public FragmentMaxLifecycleEnforcer() {
        }

        @NonNull
        public final ViewPager2 a(@NonNull RecyclerView recyclerView) {
            ViewParent parent = recyclerView.getParent();
            if (parent instanceof ViewPager2) {
                return (ViewPager2) parent;
            }
            throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
        }

        public void b(@NonNull RecyclerView recyclerView) {
            this.d = a(recyclerView);
            a aVar = new a();
            this.f1748a = aVar;
            this.d.registerOnPageChangeCallback(aVar);
            b bVar = new b();
            this.b = bVar;
            FragmentStateAdapter.this.registerAdapterDataObserver(bVar);
            LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.3
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                    FragmentMaxLifecycleEnforcer.this.d(false);
                }
            };
            this.c = lifecycleEventObserver;
            FragmentStateAdapter.this.h.addObserver(lifecycleEventObserver);
        }

        public void c(@NonNull RecyclerView recyclerView) {
            a(recyclerView).unregisterOnPageChangeCallback(this.f1748a);
            FragmentStateAdapter.this.unregisterAdapterDataObserver(this.b);
            FragmentStateAdapter.this.h.removeObserver(this.c);
            this.d = null;
        }

        public void d(boolean z) {
            int currentItem;
            Fragment fragment;
            if (FragmentStateAdapter.this.m() || this.d.getScrollState() != 0 || FragmentStateAdapter.this.j.isEmpty() || FragmentStateAdapter.this.getItemCount() == 0 || (currentItem = this.d.getCurrentItem()) >= FragmentStateAdapter.this.getItemCount()) {
                return;
            }
            long itemId = FragmentStateAdapter.this.getItemId(currentItem);
            if ((itemId != this.e || z) && (fragment = FragmentStateAdapter.this.j.get(itemId)) != null && fragment.isAdded()) {
                this.e = itemId;
                FragmentTransaction beginTransaction = FragmentStateAdapter.this.i.beginTransaction();
                Fragment fragment2 = null;
                for (int i = 0; i < FragmentStateAdapter.this.j.size(); i++) {
                    long keyAt = FragmentStateAdapter.this.j.keyAt(i);
                    Fragment valueAt = FragmentStateAdapter.this.j.valueAt(i);
                    if (valueAt.isAdded()) {
                        if (keyAt != this.e) {
                            beginTransaction.setMaxLifecycle(valueAt, Lifecycle.State.STARTED);
                        } else {
                            fragment2 = valueAt;
                        }
                        valueAt.setMenuVisibility(keyAt == this.e);
                    }
                }
                if (fragment2 != null) {
                    beginTransaction.setMaxLifecycle(fragment2, Lifecycle.State.RESUMED);
                }
                if (beginTransaction.isEmpty()) {
                    return;
                }
                beginTransaction.commitNow();
            }
        }
    }

    /* loaded from: classes.dex */
    public class a implements View.OnLayoutChangeListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FrameLayout f1751a;
        public final /* synthetic */ FragmentViewHolder b;

        public a(FrameLayout frameLayout, FragmentViewHolder fragmentViewHolder) {
            this.f1751a = frameLayout;
            this.b = fragmentViewHolder;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.f1751a.getParent() != null) {
                this.f1751a.removeOnLayoutChangeListener(this);
                FragmentStateAdapter.this.i(this.b);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends FragmentManager.FragmentLifecycleCallbacks {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Fragment f1752a;
        public final /* synthetic */ FrameLayout b;

        public b(Fragment fragment, FrameLayout frameLayout) {
            this.f1752a = fragment;
            this.b = frameLayout;
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle) {
            if (fragment == this.f1752a) {
                fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                FragmentStateAdapter.this.a(view, this.b);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
            fragmentStateAdapter.n = false;
            fragmentStateAdapter.d();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class d extends RecyclerView.AdapterDataObserver {
        public d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i, int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            onChanged();
        }

        public /* synthetic */ d(a aVar) {
            this();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            onChanged();
        }
    }

    public FragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        this(fragmentActivity.getSupportFragmentManager(), fragmentActivity.getLifecycle());
    }

    @NonNull
    public static String b(@NonNull String str, long j) {
        return str + j;
    }

    public static boolean f(@NonNull String str, @NonNull String str2) {
        return str.startsWith(str2) && str.length() > str2.length();
    }

    public static long h(@NonNull String str, @NonNull String str2) {
        return Long.parseLong(str.substring(str2.length()));
    }

    public void a(@NonNull View view, @NonNull FrameLayout frameLayout) {
        if (frameLayout.getChildCount() <= 1) {
            if (view.getParent() == frameLayout) {
                return;
            }
            if (frameLayout.getChildCount() > 0) {
                frameLayout.removeAllViews();
            }
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            frameLayout.addView(view);
            return;
        }
        throw new IllegalStateException("Design assumption violated.");
    }

    public final void c(int i) {
        long itemId = getItemId(i);
        if (this.j.containsKey(itemId)) {
            return;
        }
        Fragment createFragment = createFragment(i);
        createFragment.setInitialSavedState(this.k.get(itemId));
        this.j.put(itemId, createFragment);
    }

    public boolean containsItem(long j) {
        return j >= 0 && j < ((long) getItemCount());
    }

    @NonNull
    public abstract Fragment createFragment(int i);

    public void d() {
        if (!this.o || m()) {
            return;
        }
        ArraySet<Long> arraySet = new ArraySet();
        for (int i = 0; i < this.j.size(); i++) {
            long keyAt = this.j.keyAt(i);
            if (!containsItem(keyAt)) {
                arraySet.add(Long.valueOf(keyAt));
                this.l.remove(keyAt);
            }
        }
        if (!this.n) {
            this.o = false;
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                long keyAt2 = this.j.keyAt(i2);
                if (!e(keyAt2)) {
                    arraySet.add(Long.valueOf(keyAt2));
                }
            }
        }
        for (Long l : arraySet) {
            j(l.longValue());
        }
    }

    public final boolean e(long j) {
        View view;
        if (this.l.containsKey(j)) {
            return true;
        }
        Fragment fragment = this.j.get(j);
        return (fragment == null || (view = fragment.getView()) == null || view.getParent() == null) ? false : true;
    }

    public final Long g(int i) {
        Long l = null;
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (this.l.valueAt(i2).intValue() == i) {
                if (l == null) {
                    l = Long.valueOf(this.l.keyAt(i2));
                } else {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
            }
        }
        return l;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void i(@NonNull final FragmentViewHolder fragmentViewHolder) {
        Fragment fragment = this.j.get(fragmentViewHolder.getItemId());
        if (fragment != null) {
            FrameLayout d2 = fragmentViewHolder.d();
            View view = fragment.getView();
            if (!fragment.isAdded() && view != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            if (fragment.isAdded() && view == null) {
                l(fragment, d2);
                return;
            } else if (fragment.isAdded() && view.getParent() != null) {
                if (view.getParent() != d2) {
                    a(view, d2);
                    return;
                }
                return;
            } else if (fragment.isAdded()) {
                a(view, d2);
                return;
            } else if (!m()) {
                l(fragment, d2);
                FragmentTransaction beginTransaction = this.i.beginTransaction();
                beginTransaction.add(fragment, "f" + fragmentViewHolder.getItemId()).setMaxLifecycle(fragment, Lifecycle.State.STARTED).commitNow();
                this.m.d(false);
                return;
            } else if (this.i.isDestroyed()) {
                return;
            } else {
                this.h.addObserver(new LifecycleEventObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.2
                    @Override // androidx.lifecycle.LifecycleEventObserver
                    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                        if (FragmentStateAdapter.this.m()) {
                            return;
                        }
                        lifecycleOwner.getLifecycle().removeObserver(this);
                        if (ViewCompat.isAttachedToWindow(fragmentViewHolder.d())) {
                            FragmentStateAdapter.this.i(fragmentViewHolder);
                        }
                    }
                });
                return;
            }
        }
        throw new IllegalStateException("Design assumption violated.");
    }

    public final void j(long j) {
        ViewParent parent;
        Fragment fragment = this.j.get(j);
        if (fragment == null) {
            return;
        }
        if (fragment.getView() != null && (parent = fragment.getView().getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        if (!containsItem(j)) {
            this.k.remove(j);
        }
        if (!fragment.isAdded()) {
            this.j.remove(j);
        } else if (m()) {
            this.o = true;
        } else {
            if (fragment.isAdded() && containsItem(j)) {
                this.k.put(j, this.i.saveFragmentInstanceState(fragment));
            }
            this.i.beginTransaction().remove(fragment).commitNow();
            this.j.remove(j);
        }
    }

    public final void k() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final c cVar = new c();
        this.h.addObserver(new LifecycleEventObserver(this) { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.5
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    handler.removeCallbacks(cVar);
                    lifecycleOwner.getLifecycle().removeObserver(this);
                }
            }
        });
        handler.postDelayed(cVar, 10000L);
    }

    public final void l(Fragment fragment, @NonNull FrameLayout frameLayout) {
        this.i.registerFragmentLifecycleCallbacks(new b(fragment, frameLayout), false);
    }

    public boolean m() {
        return this.i.isStateSaved();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @CallSuper
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Preconditions.checkArgument(this.m == null);
        FragmentMaxLifecycleEnforcer fragmentMaxLifecycleEnforcer = new FragmentMaxLifecycleEnforcer();
        this.m = fragmentMaxLifecycleEnforcer;
        fragmentMaxLifecycleEnforcer.b(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @CallSuper
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        this.m.c(recyclerView);
        this.m = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final boolean onFailedToRecycleView(@NonNull FragmentViewHolder fragmentViewHolder) {
        return true;
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    public final void restoreState(@NonNull Parcelable parcelable) {
        if (this.k.isEmpty() && this.j.isEmpty()) {
            Bundle bundle = (Bundle) parcelable;
            if (bundle.getClassLoader() == null) {
                bundle.setClassLoader(getClass().getClassLoader());
            }
            for (String str : bundle.keySet()) {
                if (f(str, "f#")) {
                    this.j.put(h(str, "f#"), this.i.getFragment(bundle, str));
                } else if (f(str, "s#")) {
                    long h = h(str, "s#");
                    Fragment.SavedState savedState = (Fragment.SavedState) bundle.getParcelable(str);
                    if (containsItem(h)) {
                        this.k.put(h, savedState);
                    }
                } else {
                    throw new IllegalArgumentException("Unexpected key in savedState: " + str);
                }
            }
            if (this.j.isEmpty()) {
                return;
            }
            this.o = true;
            this.n = true;
            d();
            k();
            return;
        }
        throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    @NonNull
    public final Parcelable saveState() {
        Bundle bundle = new Bundle(this.j.size() + this.k.size());
        for (int i = 0; i < this.j.size(); i++) {
            long keyAt = this.j.keyAt(i);
            Fragment fragment = this.j.get(keyAt);
            if (fragment != null && fragment.isAdded()) {
                this.i.putFragment(bundle, b("f#", keyAt), fragment);
            }
        }
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            long keyAt2 = this.k.keyAt(i2);
            if (containsItem(keyAt2)) {
                bundle.putParcelable(b("s#", keyAt2), this.k.get(keyAt2));
            }
        }
        return bundle;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Stable Ids are required for the adapter to function properly, and the adapter takes care of setting the flag.");
    }

    public FragmentStateAdapter(@NonNull Fragment fragment) {
        this(fragment.getChildFragmentManager(), fragment.getLifecycle());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull FragmentViewHolder fragmentViewHolder, int i) {
        long itemId = fragmentViewHolder.getItemId();
        int id = fragmentViewHolder.d().getId();
        Long g = g(id);
        if (g != null && g.longValue() != itemId) {
            j(g.longValue());
            this.l.remove(g.longValue());
        }
        this.l.put(itemId, Integer.valueOf(id));
        c(i);
        FrameLayout d2 = fragmentViewHolder.d();
        if (ViewCompat.isAttachedToWindow(d2)) {
            if (d2.getParent() == null) {
                d2.addOnLayoutChangeListener(new a(d2, fragmentViewHolder));
            } else {
                throw new IllegalStateException("Design assumption violated.");
            }
        }
        d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return FragmentViewHolder.a(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewAttachedToWindow(@NonNull FragmentViewHolder fragmentViewHolder) {
        i(fragmentViewHolder);
        d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewRecycled(@NonNull FragmentViewHolder fragmentViewHolder) {
        Long g = g(fragmentViewHolder.d().getId());
        if (g != null) {
            j(g.longValue());
            this.l.remove(g.longValue());
        }
    }

    public FragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        this.j = new LongSparseArray<>();
        this.k = new LongSparseArray<>();
        this.l = new LongSparseArray<>();
        this.n = false;
        this.o = false;
        this.i = fragmentManager;
        this.h = lifecycle;
        super.setHasStableIds(true);
    }
}
