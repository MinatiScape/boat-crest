package com.h6ah4i.android.widget.advrecyclerview.headerfooter;

import android.view.ViewGroup;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.adapter.AdapterPathSegment;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemIdComposer;
import com.h6ah4i.android.widget.advrecyclerview.composedadapter.ComposedAdapter;
import com.h6ah4i.android.widget.advrecyclerview.composedadapter.ComposedChildAdapterTag;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class AbstractHeaderFooterWrapperAdapter<HeaderVH extends RecyclerView.ViewHolder, FooterVH extends RecyclerView.ViewHolder> extends ComposedAdapter {
    public static final int SEGMENT_TYPE_FOOTER = 2;
    public static final int SEGMENT_TYPE_HEADER = 0;
    public static final int SEGMENT_TYPE_NORMAL = 1;
    public RecyclerView.Adapter k;
    public RecyclerView.Adapter l;
    public RecyclerView.Adapter m;
    public ComposedChildAdapterTag n;
    public ComposedChildAdapterTag o;
    public ComposedChildAdapterTag p;

    /* loaded from: classes11.dex */
    public static class BaseFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public AbstractHeaderFooterWrapperAdapter mHolder;

        public BaseFooterAdapter(AbstractHeaderFooterWrapperAdapter abstractHeaderFooterWrapperAdapter) {
            this.mHolder = abstractHeaderFooterWrapperAdapter;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mHolder.getFooterItemCount();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            return this.mHolder.getFooterItemId(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return this.mHolder.getFooterItemViewType(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            throw new IllegalStateException();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return this.mHolder.onCreateFooterItemViewHolder(viewGroup, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull List<Object> list) {
            this.mHolder.onBindFooterItemViewHolder(viewHolder, i, list);
        }
    }

    /* loaded from: classes11.dex */
    public static class BaseHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public AbstractHeaderFooterWrapperAdapter mHolder;

        public BaseHeaderAdapter(AbstractHeaderFooterWrapperAdapter abstractHeaderFooterWrapperAdapter) {
            this.mHolder = abstractHeaderFooterWrapperAdapter;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mHolder.getHeaderItemCount();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            return this.mHolder.getHeaderItemId(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return this.mHolder.getHeaderItemViewType(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            throw new IllegalStateException();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return this.mHolder.onCreateHeaderItemViewHolder(viewGroup, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull List<Object> list) {
            this.mHolder.onBindHeaderItemViewHolder(viewHolder, i, list);
        }
    }

    @Nullable
    public RecyclerView.Adapter getFooterAdapter() {
        return this.m;
    }

    public abstract int getFooterItemCount();

    @IntRange(from = ItemIdComposer.MIN_WRAPPED_ID, to = ItemIdComposer.MAX_WRAPPED_ID)
    public long getFooterItemId(int i) {
        if (hasStableIds()) {
            return -1L;
        }
        return i;
    }

    @IntRange(from = -8388608, to = 8388607)
    public int getFooterItemViewType(int i) {
        return 0;
    }

    @NonNull
    public AdapterPathSegment getFooterSegment() {
        return new AdapterPathSegment(this.m, this.p);
    }

    @Nullable
    public RecyclerView.Adapter getHeaderAdapter() {
        return this.k;
    }

    public abstract int getHeaderItemCount();

    @IntRange(from = ItemIdComposer.MIN_WRAPPED_ID, to = ItemIdComposer.MAX_WRAPPED_ID)
    public long getHeaderItemId(int i) {
        if (hasStableIds()) {
            return -1L;
        }
        return i;
    }

    @IntRange(from = -8388608, to = 8388607)
    public int getHeaderItemViewType(int i) {
        return 0;
    }

    @NonNull
    public AdapterPathSegment getHeaderSegment() {
        return new AdapterPathSegment(this.k, this.n);
    }

    @Nullable
    public RecyclerView.Adapter getWrappedAdapter() {
        return this.l;
    }

    @NonNull
    public AdapterPathSegment getWrappedAdapterSegment() {
        return new AdapterPathSegment(this.l, this.o);
    }

    public abstract void onBindFooterItemViewHolder(@NonNull FooterVH footervh, int i);

    public void onBindFooterItemViewHolder(@NonNull FooterVH footervh, int i, List<Object> list) {
        onBindFooterItemViewHolder(footervh, i);
    }

    public abstract void onBindHeaderItemViewHolder(@NonNull HeaderVH headervh, int i);

    public void onBindHeaderItemViewHolder(@NonNull HeaderVH headervh, int i, List<Object> list) {
        onBindHeaderItemViewHolder(headervh, i);
    }

    @NonNull
    public RecyclerView.Adapter onCreateFooterAdapter() {
        return new BaseFooterAdapter(this);
    }

    @NonNull
    public abstract FooterVH onCreateFooterItemViewHolder(@NonNull ViewGroup viewGroup, int i);

    @NonNull
    public RecyclerView.Adapter onCreateHeaderAdapter() {
        return new BaseHeaderAdapter(this);
    }

    @NonNull
    public abstract HeaderVH onCreateHeaderItemViewHolder(@NonNull ViewGroup viewGroup, int i);

    @Override // com.h6ah4i.android.widget.advrecyclerview.composedadapter.ComposedAdapter
    public void onRelease() {
        super.onRelease();
        this.n = null;
        this.o = null;
        this.p = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    @NonNull
    public AbstractHeaderFooterWrapperAdapter setAdapter(@NonNull RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        if (this.l == null) {
            this.l = adapter;
            this.k = onCreateHeaderAdapter();
            this.m = onCreateFooterAdapter();
            boolean hasStableIds = adapter.hasStableIds();
            this.k.setHasStableIds(hasStableIds);
            this.m.setHasStableIds(hasStableIds);
            setHasStableIds(hasStableIds);
            this.n = addAdapter(this.k);
            this.o = addAdapter(this.l);
            this.p = addAdapter(this.m);
            return this;
        }
        throw new IllegalStateException("setAdapter() can call only once");
    }
}
