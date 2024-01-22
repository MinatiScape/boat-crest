package com.h6ah4i.android.widget.advrecyclerview.composedadapter;

import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.adapter.AdapterPathSegment;
import com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemIdComposer;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemViewTypeComposer;
import com.h6ah4i.android.widget.advrecyclerview.adapter.UnwrapPositionResult;
import com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrappedAdapterUtils;
import java.util.List;
/* loaded from: classes11.dex */
public class ComposedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements WrapperAdapter<RecyclerView.ViewHolder>, BridgeAdapterDataObserver.Subscriber {
    public static long NO_SEGMENTED_POSITION = a.f;
    public a h;
    public c i;
    public d j;

    public ComposedAdapter() {
        a aVar = new a(this);
        this.h = aVar;
        this.i = new c(aVar);
        this.j = new d();
        setHasStableIds(true);
    }

    public static int extractSegmentOffsetPart(long j) {
        return a.d(j);
    }

    public static int extractSegmentPart(long j) {
        return a.c(j);
    }

    @NonNull
    public ComposedChildAdapterTag addAdapter(@NonNull RecyclerView.Adapter adapter) {
        return addAdapter(adapter, getChildAdapterCount());
    }

    public int getChildAdapterCount() {
        return this.h.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.i.f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        long segmentedPosition = getSegmentedPosition(i);
        int c = a.c(segmentedPosition);
        int d = a.d(segmentedPosition);
        RecyclerView.Adapter e = this.h.e(c);
        int itemViewType = e.getItemViewType(d);
        return ItemIdComposer.composeSegment(ItemViewTypeComposer.extractSegmentPart(this.j.d(c, itemViewType)), e.getItemId(d));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        long segmentedPosition = getSegmentedPosition(i);
        int c = a.c(segmentedPosition);
        return this.j.d(c, this.h.e(c).getItemViewType(a.d(segmentedPosition)));
    }

    public int getSegment(@NonNull ComposedChildAdapterTag composedChildAdapterTag) {
        return this.h.f(composedChildAdapterTag);
    }

    public long getSegmentedPosition(int i) {
        return this.i.e(i);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public void getWrappedAdapters(@NonNull List<RecyclerView.Adapter> list) {
        a aVar = this.h;
        if (aVar != null) {
            list.addAll(aVar.i());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        List<RecyclerView.Adapter> i = this.h.i();
        for (int i2 = 0; i2 < i.size(); i2++) {
            i.get(i2).onAttachedToRecyclerView(recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        long segmentedPosition = getSegmentedPosition(i);
        int c = a.c(segmentedPosition);
        this.h.e(c).onBindViewHolder(viewHolder, a.d(segmentedPosition));
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public void onBridgedAdapterChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj) {
        onHandleWrappedAdapterChanged(adapter, (List) obj);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public void onBridgedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2) {
        onHandleWrappedAdapterItemRangeChanged(adapter, (List) obj, i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public void onBridgedAdapterItemRangeInserted(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2) {
        onHandleWrappedAdapterItemRangeInserted(adapter, (List) obj, i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public void onBridgedAdapterItemRangeRemoved(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2) {
        onHandleWrappedAdapterItemRangeRemoved(adapter, (List) obj, i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public void onBridgedAdapterRangeMoved(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2, int i3) {
        onHandleWrappedAdapterRangeMoved(adapter, (List) obj, i, i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        long c = this.j.c(i);
        int b = d.b(c);
        return this.h.e(b).onCreateViewHolder(viewGroup, d.a(c));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        List<RecyclerView.Adapter> i = this.h.i();
        for (int i2 = 0; i2 < i.size(); i2++) {
            i.get(i2).onDetachedFromRecyclerView(recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder viewHolder) {
        return onFailedToRecycleView(viewHolder, viewHolder.getItemViewType());
    }

    public void onHandleWrappedAdapterChanged(@NonNull RecyclerView.Adapter adapter, @NonNull List<ComposedChildAdapterTag> list) {
        this.i.g();
        notifyDataSetChanged();
    }

    public void onHandleWrappedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @NonNull List<ComposedChildAdapterTag> list, int i, int i2) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            notifyItemRangeChanged(this.i.b(this.h.f(list.get(i3)), i), i2);
        }
    }

    public void onHandleWrappedAdapterItemRangeInserted(@NonNull RecyclerView.Adapter adapter, @NonNull List<ComposedChildAdapterTag> list, int i, int i2) {
        if (i2 <= 0) {
            return;
        }
        int size = list.size();
        if (size == 1) {
            int f = this.h.f(list.get(0));
            this.i.h(f);
            notifyItemRangeInserted(this.i.b(f, i), i2);
            return;
        }
        for (int i3 = 0; i3 < size; i3++) {
            this.i.h(this.h.f(list.get(i3)));
        }
        notifyDataSetChanged();
    }

    public void onHandleWrappedAdapterItemRangeRemoved(@NonNull RecyclerView.Adapter adapter, @NonNull List<ComposedChildAdapterTag> list, int i, int i2) {
        if (i2 <= 0) {
            return;
        }
        int size = list.size();
        if (size == 1) {
            int f = this.h.f(list.get(0));
            this.i.h(f);
            notifyItemRangeRemoved(this.i.b(f, i), i2);
            return;
        }
        for (int i3 = 0; i3 < size; i3++) {
            this.i.h(this.h.f(list.get(i3)));
        }
        notifyDataSetChanged();
    }

    public void onHandleWrappedAdapterRangeMoved(@NonNull RecyclerView.Adapter adapter, @NonNull List<ComposedChildAdapterTag> list, int i, int i2, int i3) {
        if (i3 == 1) {
            if (list.size() == 1) {
                int f = this.h.f(list.get(0));
                notifyItemMoved(this.i.b(f, i), this.i.b(f, i2));
                return;
            }
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("itemCount should be always 1  (actual: " + i3 + ")");
    }

    @CallSuper
    public void onRelease() {
        a aVar = this.h;
        if (aVar != null) {
            aVar.j();
            this.h = null;
        }
        c cVar = this.i;
        if (cVar != null) {
            cVar.i();
            this.i = null;
        }
        this.j = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
        onViewAttachedToWindow(viewHolder, viewHolder.getItemViewType());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
        onViewDetachedFromWindow(viewHolder, viewHolder.getItemViewType());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        onViewRecycled(viewHolder, viewHolder.getItemViewType());
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public void release() {
        onRelease();
    }

    public boolean removeAdapter(@NonNull ComposedChildAdapterTag composedChildAdapterTag) {
        int f = this.h.f(composedChildAdapterTag);
        if (f < 0) {
            return false;
        }
        this.h.k(composedChildAdapterTag);
        this.i.h(f);
        notifyDataSetChanged();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void setHasStableIds(boolean z) {
        if (z && !hasStableIds()) {
            int g = this.h.g();
            for (int i = 0; i < g; i++) {
                if (!this.h.e(i).hasStableIds()) {
                    throw new IllegalStateException("All child adapters must support stable IDs");
                }
            }
        }
        super.setHasStableIds(z);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public void unwrapPosition(@NonNull UnwrapPositionResult unwrapPositionResult, int i) {
        long e = this.i.e(i);
        if (e != a.f) {
            int c = a.c(e);
            int d = a.d(e);
            unwrapPositionResult.adapter = this.h.e(c);
            unwrapPositionResult.position = d;
            unwrapPositionResult.tag = this.h.h(c);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public int wrapPosition(@NonNull AdapterPathSegment adapterPathSegment, int i) {
        Object obj = adapterPathSegment.tag;
        if (obj != null) {
            return this.i.b(this.h.f((ComposedChildAdapterTag) obj), i);
        }
        return -1;
    }

    @NonNull
    public ComposedChildAdapterTag addAdapter(@NonNull RecyclerView.Adapter adapter, int i) {
        if (hasObservers() && hasStableIds() && !adapter.hasStableIds()) {
            throw new IllegalStateException("Wrapped child adapter must has stable IDs");
        }
        ComposedChildAdapterTag a2 = this.h.a(adapter, i);
        this.i.h(this.h.f(a2));
        notifyDataSetChanged();
        return a2;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public void onBridgedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2, Object obj2) {
        onHandleWrappedAdapterItemRangeChanged(adapter, (List) obj, i, i2, obj2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        long c = this.j.c(i);
        int b = d.b(c);
        return WrappedAdapterUtils.invokeOnFailedToRecycleView(this.h.e(b), viewHolder, d.a(c));
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        long c = this.j.c(i);
        int b = d.b(c);
        WrappedAdapterUtils.invokeOnViewAttachedToWindow(this.h.e(b), viewHolder, d.a(c));
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        long c = this.j.c(i);
        int b = d.b(c);
        WrappedAdapterUtils.invokeOnViewDetachedFromWindow(this.h.e(b), viewHolder, d.a(c));
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        long c = this.j.c(i);
        int b = d.b(c);
        WrappedAdapterUtils.invokeOnViewRecycled(this.h.e(b), viewHolder, d.a(c));
    }

    public void onHandleWrappedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @NonNull List<ComposedChildAdapterTag> list, int i, int i2, Object obj) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            notifyItemRangeChanged(this.i.b(this.h.f(list.get(i3)), i), i2, obj);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull List<Object> list) {
        long segmentedPosition = getSegmentedPosition(i);
        int c = a.c(segmentedPosition);
        this.h.e(c).onBindViewHolder(viewHolder, a.d(segmentedPosition), list);
    }
}
