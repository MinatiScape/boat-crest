package androidx.recyclerview.widget;

import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StableIdStorage;
import androidx.recyclerview.widget.ViewTypeStorage;
import androidx.recyclerview.widget.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class c implements i.b {

    /* renamed from: a  reason: collision with root package name */
    public final ConcatAdapter f1637a;
    public final ViewTypeStorage b;
    public List<WeakReference<RecyclerView>> c = new ArrayList();
    public final IdentityHashMap<RecyclerView.ViewHolder, i> d = new IdentityHashMap<>();
    public List<i> e = new ArrayList();
    public a f = new a();
    @NonNull
    public final ConcatAdapter.Config.StableIdMode g;
    public final StableIdStorage h;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public i f1638a;
        public int b;
        public boolean c;
    }

    public c(ConcatAdapter concatAdapter, ConcatAdapter.Config config) {
        this.f1637a = concatAdapter;
        if (config.isolateViewTypes) {
            this.b = new ViewTypeStorage.IsolatedViewTypeStorage();
        } else {
            this.b = new ViewTypeStorage.SharedIdRangeViewTypeStorage();
        }
        ConcatAdapter.Config.StableIdMode stableIdMode = config.stableIdMode;
        this.g = stableIdMode;
        if (stableIdMode == ConcatAdapter.Config.StableIdMode.NO_STABLE_IDS) {
            this.h = new StableIdStorage.NoStableIdStorage();
        } else if (stableIdMode == ConcatAdapter.Config.StableIdMode.ISOLATED_STABLE_IDS) {
            this.h = new StableIdStorage.IsolatedStableIdStorage();
        } else if (stableIdMode == ConcatAdapter.Config.StableIdMode.SHARED_STABLE_IDS) {
            this.h = new StableIdStorage.SharedPoolStableIdStorage();
        } else {
            throw new IllegalArgumentException("unknown stable id mode");
        }
    }

    public boolean A(RecyclerView.ViewHolder viewHolder) {
        i iVar = this.d.get(viewHolder);
        if (iVar != null) {
            boolean onFailedToRecycleView = iVar.c.onFailedToRecycleView(viewHolder);
            this.d.remove(viewHolder);
            return onFailedToRecycleView;
        }
        throw new IllegalStateException("Cannot find wrapper for " + viewHolder + ", seems like it is not bound by this adapter: " + this);
    }

    public void B(RecyclerView.ViewHolder viewHolder) {
        s(viewHolder).c.onViewAttachedToWindow(viewHolder);
    }

    public void C(RecyclerView.ViewHolder viewHolder) {
        s(viewHolder).c.onViewDetachedFromWindow(viewHolder);
    }

    public void D(RecyclerView.ViewHolder viewHolder) {
        i iVar = this.d.get(viewHolder);
        if (iVar != null) {
            iVar.c.onViewRecycled(viewHolder);
            this.d.remove(viewHolder);
            return;
        }
        throw new IllegalStateException("Cannot find wrapper for " + viewHolder + ", seems like it is not bound by this adapter: " + this);
    }

    public final void E(a aVar) {
        aVar.c = false;
        aVar.f1638a = null;
        aVar.b = -1;
        this.f = aVar;
    }

    public boolean F(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        int u = u(adapter);
        if (u == -1) {
            return false;
        }
        i iVar = this.e.get(u);
        int k = k(iVar);
        this.e.remove(u);
        this.f1637a.notifyItemRangeRemoved(k, iVar.b());
        for (WeakReference<RecyclerView> weakReference : this.c) {
            RecyclerView recyclerView = weakReference.get();
            if (recyclerView != null) {
                adapter.onDetachedFromRecyclerView(recyclerView);
            }
        }
        iVar.a();
        i();
        return true;
    }

    @Override // androidx.recyclerview.widget.i.b
    public void a(@NonNull i iVar, int i, int i2, @Nullable Object obj) {
        this.f1637a.notifyItemRangeChanged(i + k(iVar), i2, obj);
    }

    @Override // androidx.recyclerview.widget.i.b
    public void b(@NonNull i iVar, int i, int i2) {
        this.f1637a.notifyItemRangeInserted(i + k(iVar), i2);
    }

    @Override // androidx.recyclerview.widget.i.b
    public void c(@NonNull i iVar, int i, int i2) {
        int k = k(iVar);
        this.f1637a.notifyItemMoved(i + k, i2 + k);
    }

    @Override // androidx.recyclerview.widget.i.b
    public void d(i iVar) {
        i();
    }

    @Override // androidx.recyclerview.widget.i.b
    public void e(@NonNull i iVar) {
        this.f1637a.notifyDataSetChanged();
        i();
    }

    @Override // androidx.recyclerview.widget.i.b
    public void f(@NonNull i iVar, int i, int i2) {
        this.f1637a.notifyItemRangeRemoved(i + k(iVar), i2);
    }

    public boolean g(int i, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        if (i >= 0 && i <= this.e.size()) {
            if (t()) {
                Preconditions.checkArgument(adapter.hasStableIds(), "All sub adapters must have stable ids when stable id mode is ISOLATED_STABLE_IDS or SHARED_STABLE_IDS");
            } else if (adapter.hasStableIds()) {
                Log.w("ConcatAdapter", "Stable ids in the adapter will be ignored as the ConcatAdapter is configured not to have stable ids");
            }
            if (m(adapter) != null) {
                return false;
            }
            i iVar = new i(adapter, this, this.b, this.h.createStableIdLookup());
            this.e.add(i, iVar);
            for (WeakReference<RecyclerView> weakReference : this.c) {
                RecyclerView recyclerView = weakReference.get();
                if (recyclerView != null) {
                    adapter.onAttachedToRecyclerView(recyclerView);
                }
            }
            if (iVar.b() > 0) {
                this.f1637a.notifyItemRangeInserted(k(iVar), iVar.b());
            }
            i();
            return true;
        }
        throw new IndexOutOfBoundsException("Index must be between 0 and " + this.e.size() + ". Given:" + i);
    }

    public boolean h(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        return g(this.e.size(), adapter);
    }

    public final void i() {
        RecyclerView.Adapter.StateRestorationPolicy j = j();
        if (j != this.f1637a.getStateRestorationPolicy()) {
            this.f1637a.a(j);
        }
    }

    public final RecyclerView.Adapter.StateRestorationPolicy j() {
        for (i iVar : this.e) {
            RecyclerView.Adapter.StateRestorationPolicy stateRestorationPolicy = iVar.c.getStateRestorationPolicy();
            RecyclerView.Adapter.StateRestorationPolicy stateRestorationPolicy2 = RecyclerView.Adapter.StateRestorationPolicy.PREVENT;
            if (stateRestorationPolicy == stateRestorationPolicy2) {
                return stateRestorationPolicy2;
            }
            if (stateRestorationPolicy == RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY && iVar.b() == 0) {
                return stateRestorationPolicy2;
            }
        }
        return RecyclerView.Adapter.StateRestorationPolicy.ALLOW;
    }

    public final int k(i iVar) {
        i next;
        Iterator<i> it = this.e.iterator();
        int i = 0;
        while (it.hasNext() && (next = it.next()) != iVar) {
            i += next.b();
        }
        return i;
    }

    @NonNull
    public final a l(int i) {
        a aVar = this.f;
        if (aVar.c) {
            aVar = new a();
        } else {
            aVar.c = true;
        }
        Iterator<i> it = this.e.iterator();
        int i2 = i;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            i next = it.next();
            if (next.b() > i2) {
                aVar.f1638a = next;
                aVar.b = i2;
                break;
            }
            i2 -= next.b();
        }
        if (aVar.f1638a != null) {
            return aVar;
        }
        throw new IllegalArgumentException("Cannot find wrapper for " + i);
    }

    @Nullable
    public final i m(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        int u = u(adapter);
        if (u == -1) {
            return null;
        }
        return this.e.get(u);
    }

    public List<RecyclerView.Adapter<? extends RecyclerView.ViewHolder>> n() {
        if (this.e.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(this.e.size());
        for (i iVar : this.e) {
            arrayList.add(iVar.c);
        }
        return arrayList;
    }

    public long o(int i) {
        a l = l(i);
        long c = l.f1638a.c(l.b);
        E(l);
        return c;
    }

    public int p(int i) {
        a l = l(i);
        int d = l.f1638a.d(l.b);
        E(l);
        return d;
    }

    public int q(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter, RecyclerView.ViewHolder viewHolder, int i) {
        i iVar = this.d.get(viewHolder);
        if (iVar == null) {
            return -1;
        }
        int k = i - k(iVar);
        int itemCount = iVar.c.getItemCount();
        if (k >= 0 && k < itemCount) {
            return iVar.c.findRelativeAdapterPositionIn(adapter, viewHolder, k);
        }
        throw new IllegalStateException("Detected inconsistent adapter updates. The local position of the view holder maps to " + k + " which is out of bounds for the adapter with size " + itemCount + ".Make sure to immediately call notify methods in your adapter when you change the backing dataviewHolder:" + viewHolder + "adapter:" + adapter);
    }

    public int r() {
        int i = 0;
        for (i iVar : this.e) {
            i += iVar.b();
        }
        return i;
    }

    @NonNull
    public final i s(RecyclerView.ViewHolder viewHolder) {
        i iVar = this.d.get(viewHolder);
        if (iVar != null) {
            return iVar;
        }
        throw new IllegalStateException("Cannot find wrapper for " + viewHolder + ", seems like it is not bound by this adapter: " + this);
    }

    public boolean t() {
        return this.g != ConcatAdapter.Config.StableIdMode.NO_STABLE_IDS;
    }

    public final int u(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            if (this.e.get(i).c == adapter) {
                return i;
            }
        }
        return -1;
    }

    public final boolean v(RecyclerView recyclerView) {
        for (WeakReference<RecyclerView> weakReference : this.c) {
            if (weakReference.get() == recyclerView) {
                return true;
            }
        }
        return false;
    }

    public void w(RecyclerView recyclerView) {
        if (v(recyclerView)) {
            return;
        }
        this.c.add(new WeakReference<>(recyclerView));
        for (i iVar : this.e) {
            iVar.c.onAttachedToRecyclerView(recyclerView);
        }
    }

    public void x(RecyclerView.ViewHolder viewHolder, int i) {
        a l = l(i);
        this.d.put(viewHolder, l.f1638a);
        l.f1638a.e(viewHolder, l.b);
        E(l);
    }

    public RecyclerView.ViewHolder y(ViewGroup viewGroup, int i) {
        return this.b.getWrapperForGlobalType(i).f(viewGroup, i);
    }

    public void z(RecyclerView recyclerView) {
        int size = this.c.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            WeakReference<RecyclerView> weakReference = this.c.get(size);
            if (weakReference.get() == null) {
                this.c.remove(size);
            } else if (weakReference.get() == recyclerView) {
                this.c.remove(size);
                break;
            }
            size--;
        }
        for (i iVar : this.e) {
            iVar.c.onDetachedFromRecyclerView(recyclerView);
        }
    }
}
