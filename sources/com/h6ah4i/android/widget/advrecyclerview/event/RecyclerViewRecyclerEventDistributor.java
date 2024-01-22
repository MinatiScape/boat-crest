package com.h6ah4i.android.widget.advrecyclerview.event;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.List;
/* loaded from: classes11.dex */
public class RecyclerViewRecyclerEventDistributor extends BaseRecyclerViewEventDistributor<RecyclerView.RecyclerListener> {

    /* renamed from: a  reason: collision with root package name */
    public a f11912a = new a(this);

    /* loaded from: classes11.dex */
    public static class a implements RecyclerView.RecyclerListener {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<RecyclerViewRecyclerEventDistributor> f11913a;

        public a(@NonNull RecyclerViewRecyclerEventDistributor recyclerViewRecyclerEventDistributor) {
            this.f11913a = new WeakReference<>(recyclerViewRecyclerEventDistributor);
        }

        public void a() {
            this.f11913a.clear();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.RecyclerListener
        public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
            RecyclerViewRecyclerEventDistributor recyclerViewRecyclerEventDistributor = this.f11913a.get();
            if (recyclerViewRecyclerEventDistributor != null) {
                recyclerViewRecyclerEventDistributor.a(viewHolder);
            }
        }
    }

    public void a(@NonNull RecyclerView.ViewHolder viewHolder) {
        List<T> list = this.mListeners;
        if (list == 0) {
            return;
        }
        for (T t : list) {
            t.onViewRecycled(viewHolder);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.event.BaseRecyclerViewEventDistributor
    public void onRecyclerViewAttached(@NonNull RecyclerView recyclerView) {
        super.onRecyclerViewAttached(recyclerView);
        recyclerView.setRecyclerListener(this.f11912a);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.event.BaseRecyclerViewEventDistributor
    public void onRelease() {
        super.onRelease();
        a aVar = this.f11912a;
        if (aVar != null) {
            aVar.a();
            this.f11912a = null;
        }
    }
}
