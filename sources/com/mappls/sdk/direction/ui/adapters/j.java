package com.mappls.sdk.direction.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionItemStopBinding;
import com.mappls.sdk.direction.ui.model.DirectionOptions;
import com.mappls.sdk.direction.ui.model.StopModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public final class j extends RecyclerView.Adapter<b> implements DraggableItemAdapter<b> {
    public List<StopModel> h = new ArrayList();
    public a i;
    public DirectionOptions j;

    /* loaded from: classes11.dex */
    public interface a {
        void a();

        void a(int i);

        void a(StopModel stopModel);

        void b(int i);
    }

    /* loaded from: classes11.dex */
    public class b extends AbstractDraggableItemViewHolder {
        public MapplsDirectionItemStopBinding i;

        public b(@NonNull MapplsDirectionItemStopBinding mapplsDirectionItemStopBinding) {
            super(mapplsDirectionItemStopBinding.getRoot());
            this.i = mapplsDirectionItemStopBinding;
        }
    }

    public j(DirectionOptions directionOptions) {
        setHasStableIds(true);
        this.j = directionOptions;
    }

    public final void a(a aVar) {
        this.i = aVar;
    }

    public final void a(List<StopModel> list) {
        this.h = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.h.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        if (this.h.size() == 2 && i == 1) {
            return 1;
        }
        if (i != this.h.size() - 1) {
            return (i == this.h.size() - 2 && this.h.get(i + 1).getLocationType() == StopModel.TYPE_BLANK) ? 1 : 2;
        }
        List<StopModel> list = this.h;
        return list.get(list.size() - 1).getLocationType() == StopModel.TYPE_BLANK ? 3 : 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x023e, code lost:
        if (r8.h.get(r10).getLocationType() == com.mappls.sdk.direction.ui.model.StopModel.TYPE_BLANK) goto L60;
     */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBindViewHolder(@androidx.annotation.NonNull com.mappls.sdk.direction.ui.adapters.j.b r9, int r10) {
        /*
            Method dump skipped, instructions count: 626
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.direction.ui.adapters.j.onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder, int):void");
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public final boolean onCheckCanDrop(int i, int i2) {
        return true;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public final boolean onCheckCanStartDrag(@NonNull b bVar, int i, int i2, int i3) {
        return this.h.size() >= 3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new b((MapplsDirectionItemStopBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_item_stop, viewGroup, false));
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    @Nullable
    public final /* bridge */ /* synthetic */ ItemDraggableRange onGetItemDraggableRange(@NonNull b bVar, int i) {
        return null;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public final void onItemDragFinished(int i, int i2, boolean z) {
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public final void onItemDragStarted(int i) {
        notifyDataSetChanged();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public final void onMoveItem(int i, int i2) {
        if (i < i2) {
            while (i < i2) {
                int i3 = i + 1;
                Collections.swap(this.h, i, i3);
                i = i3;
            }
        } else {
            while (i > i2) {
                Collections.swap(this.h, i, i - 1);
                i--;
            }
        }
        notifyDataSetChanged();
    }
}
