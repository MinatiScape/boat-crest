package androidx.recyclerview.widget;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StableIdStorage;
import androidx.recyclerview.widget.ViewTypeStorage;
/* loaded from: classes.dex */
public class i {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewTypeStorage.ViewTypeLookup f1649a;
    @NonNull
    public final StableIdStorage.StableIdLookup b;
    public final RecyclerView.Adapter<RecyclerView.ViewHolder> c;
    public final b d;
    public int e;
    public RecyclerView.AdapterDataObserver f = new a();

    /* loaded from: classes.dex */
    public class a extends RecyclerView.AdapterDataObserver {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            i iVar = i.this;
            iVar.e = iVar.c.getItemCount();
            i iVar2 = i.this;
            iVar2.d.e(iVar2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            i iVar = i.this;
            iVar.d.a(iVar, i, i2, null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            i iVar = i.this;
            iVar.e += i2;
            iVar.d.b(iVar, i, i2);
            i iVar2 = i.this;
            if (iVar2.e <= 0 || iVar2.c.getStateRestorationPolicy() != RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                return;
            }
            i iVar3 = i.this;
            iVar3.d.d(iVar3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            Preconditions.checkArgument(i3 == 1, "moving more than 1 item is not supported in RecyclerView");
            i iVar = i.this;
            iVar.d.c(iVar, i, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            i iVar = i.this;
            iVar.e -= i2;
            iVar.d.f(iVar, i, i2);
            i iVar2 = i.this;
            if (iVar2.e >= 1 || iVar2.c.getStateRestorationPolicy() != RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                return;
            }
            i iVar3 = i.this;
            iVar3.d.d(iVar3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onStateRestorationPolicyChanged() {
            i iVar = i.this;
            iVar.d.d(iVar);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            i iVar = i.this;
            iVar.d.a(iVar, i, i2, obj);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(@NonNull i iVar, int i, int i2, @Nullable Object obj);

        void b(@NonNull i iVar, int i, int i2);

        void c(@NonNull i iVar, int i, int i2);

        void d(i iVar);

        void e(@NonNull i iVar);

        void f(@NonNull i iVar, int i, int i2);
    }

    public i(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, b bVar, ViewTypeStorage viewTypeStorage, StableIdStorage.StableIdLookup stableIdLookup) {
        this.c = adapter;
        this.d = bVar;
        this.f1649a = viewTypeStorage.createViewTypeWrapper(this);
        this.b = stableIdLookup;
        this.e = adapter.getItemCount();
        adapter.registerAdapterDataObserver(this.f);
    }

    public void a() {
        this.c.unregisterAdapterDataObserver(this.f);
        this.f1649a.dispose();
    }

    public int b() {
        return this.e;
    }

    public long c(int i) {
        return this.b.localToGlobal(this.c.getItemId(i));
    }

    public int d(int i) {
        return this.f1649a.localToGlobal(this.c.getItemViewType(i));
    }

    public void e(RecyclerView.ViewHolder viewHolder, int i) {
        this.c.bindViewHolder(viewHolder, i);
    }

    public RecyclerView.ViewHolder f(ViewGroup viewGroup, int i) {
        return this.c.onCreateViewHolder(viewGroup, this.f1649a.globalToLocal(i));
    }
}
