package com.coveiot.android.leonardo.sensai.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ItemSensAiSortByBinding;
import com.coveiot.android.leonardo.sensai.model.SensAISortByModel;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SensAISortByListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final OnItemClickListener f5379a;
    public int b;
    @Nullable
    public List<SensAISortByModel> c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClicked(@NotNull SensAISortByModel sensAISortByModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5380a;
        @NotNull
        public final View b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAISortByListAdapter sensAISortByListAdapter, ItemSensAiSortByBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tvSortByTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tvSortByTitle");
            this.f5380a = textView;
            View view = itemView.view1;
            Intrinsics.checkNotNullExpressionValue(view, "itemView.view1");
            this.b = view;
        }

        @NotNull
        public final TextView getTvSortByTitle() {
            return this.f5380a;
        }

        @NotNull
        public final View getView1() {
            return this.b;
        }
    }

    public SensAISortByListAdapter(@NotNull Context mContext, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5379a = onItemClickListener;
    }

    public static final void b(SensAISortByListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<SensAISortByModel> list = this$0.c;
        Intrinsics.checkNotNull(list);
        list.get(i).setSelected(true);
        this$0.b = i;
        this$0.notifyDataSetChanged();
        OnItemClickListener onItemClickListener = this$0.f5379a;
        List<SensAISortByModel> list2 = this$0.c;
        Intrinsics.checkNotNull(list2);
        onItemClickListener.onItemClicked(list2.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SensAISortByModel> list = this.c;
        if (list != null) {
            Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 0) {
                List<SensAISortByModel> list2 = this.c;
                Integer valueOf2 = list2 != null ? Integer.valueOf(list2.size()) : null;
                Intrinsics.checkNotNull(valueOf2);
                return valueOf2.intValue();
            }
            return 0;
        }
        return 0;
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.f5379a;
    }

    public final int getSelectedPosition() {
        return this.b;
    }

    @Nullable
    public final List<SensAISortByModel> getSortByList() {
        return this.c;
    }

    public final void setData(@NotNull List<SensAISortByModel> sortByList) {
        Intrinsics.checkNotNullParameter(sortByList, "sortByList");
        this.c = sortByList;
        notifyDataSetChanged();
    }

    public final void setSelectedPosition(int i) {
        this.b = i;
    }

    public final void setSortByList(@Nullable List<SensAISortByModel> list) {
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, @SuppressLint({"RecyclerView"}) final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<SensAISortByModel> list = this.c;
        if (list != null && CollectionsKt__CollectionsKt.getLastIndex(list) == i) {
            holder.getView1().setVisibility(8);
        } else {
            holder.getView1().setVisibility(0);
        }
        TextView tvSortByTitle = holder.getTvSortByTitle();
        List<SensAISortByModel> list2 = this.c;
        Intrinsics.checkNotNull(list2);
        tvSortByTitle.setText(list2.get(i).getName());
        List<SensAISortByModel> list3 = this.c;
        Intrinsics.checkNotNull(list3);
        if (list3.get(i).isSelected() && this.b == i) {
            holder.getTvSortByTitle().setTextAppearance(R.style.bold);
        } else {
            holder.getTvSortByTitle().setTextAppearance(R.style.regular);
        }
        holder.getTvSortByTitle().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SensAISortByListAdapter.b(SensAISortByListAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSensAiSortByBinding inflate = ItemSensAiSortByBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
