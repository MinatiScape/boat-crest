package com.h6ah4i.android.widget.advrecyclerview.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
public class BridgeAdapterDataObserver extends RecyclerView.AdapterDataObserver {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference<Subscriber> f11890a;
    public final WeakReference<RecyclerView.Adapter> b;
    public final Object c;

    /* loaded from: classes11.dex */
    public interface Subscriber {
        void onBridgedAdapterChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj);

        void onBridgedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2);

        void onBridgedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2, @Nullable Object obj2);

        void onBridgedAdapterItemRangeInserted(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2);

        void onBridgedAdapterItemRangeRemoved(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2);

        void onBridgedAdapterRangeMoved(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2, int i3);
    }

    public BridgeAdapterDataObserver(@NonNull Subscriber subscriber, @NonNull RecyclerView.Adapter adapter, @Nullable Object obj) {
        this.f11890a = new WeakReference<>(subscriber);
        this.b = new WeakReference<>(adapter);
        this.c = obj;
    }

    @Nullable
    public Object getTag() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onChanged() {
        Subscriber subscriber = this.f11890a.get();
        RecyclerView.Adapter adapter = this.b.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterChanged(adapter, this.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeChanged(int i, int i2) {
        Subscriber subscriber = this.f11890a.get();
        RecyclerView.Adapter adapter = this.b.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeChanged(adapter, this.c, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeInserted(int i, int i2) {
        Subscriber subscriber = this.f11890a.get();
        RecyclerView.Adapter adapter = this.b.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeInserted(adapter, this.c, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeMoved(int i, int i2, int i3) {
        Subscriber subscriber = this.f11890a.get();
        RecyclerView.Adapter adapter = this.b.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterRangeMoved(adapter, this.c, i, i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeRemoved(int i, int i2) {
        Subscriber subscriber = this.f11890a.get();
        RecyclerView.Adapter adapter = this.b.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeRemoved(adapter, this.c, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
        Subscriber subscriber = this.f11890a.get();
        RecyclerView.Adapter adapter = this.b.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeChanged(adapter, this.c, i, i2, obj);
    }
}
