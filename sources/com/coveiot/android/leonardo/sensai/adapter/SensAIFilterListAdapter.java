package com.coveiot.android.leonardo.sensai.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ItemSensAiFilterBinding;
import com.coveiot.android.leonardo.sensai.model.SensAIFilterModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SensAIFilterListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5371a;
    @NotNull
    public final OnItemClickListener b;
    @Nullable
    public List<SensAIFilterModel> c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClicked(@NotNull SensAIFilterModel sensAIFilterModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5372a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAIFilterListAdapter sensAIFilterListAdapter, ItemSensAiFilterBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tvFilterTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tvFilterTitle");
            this.f5372a = textView;
        }

        @NotNull
        public final TextView getTvFilterTitle() {
            return this.f5372a;
        }
    }

    public SensAIFilterListAdapter(@NotNull Context mContext, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5371a = mContext;
        this.b = onItemClickListener;
    }

    public static final void b(SensAIFilterListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<SensAIFilterModel> list = this$0.c;
        Intrinsics.checkNotNull(list);
        List<SensAIFilterModel> list2 = this$0.c;
        Intrinsics.checkNotNull(list2);
        list.get(i).setSelected(!list2.get(i).isSelected());
        this$0.notifyDataSetChanged();
        OnItemClickListener onItemClickListener = this$0.b;
        List<SensAIFilterModel> list3 = this$0.c;
        Intrinsics.checkNotNull(list3);
        onItemClickListener.onItemClicked(list3.get(i));
    }

    @Nullable
    public final List<SensAIFilterModel> getFilterList() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SensAIFilterModel> list = this.c;
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        return valueOf.intValue();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.b;
    }

    public final void setData(@NotNull List<SensAIFilterModel> filterList) {
        Intrinsics.checkNotNullParameter(filterList, "filterList");
        this.c = filterList;
        notifyDataSetChanged();
    }

    public final void setFilterList(@Nullable List<SensAIFilterModel> list) {
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, @SuppressLint({"RecyclerView"}) final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView tvFilterTitle = holder.getTvFilterTitle();
        List<SensAIFilterModel> list = this.c;
        Intrinsics.checkNotNull(list);
        tvFilterTitle.setText(list.get(i).getName());
        List<SensAIFilterModel> list2 = this.c;
        Intrinsics.checkNotNull(list2);
        if (list2.get(i).isSelected()) {
            holder.getTvFilterTitle().setBackground(this.f5371a.getResources().getDrawable(R.drawable.tab_selected_bg));
            holder.getTvFilterTitle().setTextColor(this.f5371a.getResources().getColor(R.color.main_text_color));
        } else {
            holder.getTvFilterTitle().setBackground(this.f5371a.getResources().getDrawable(R.drawable.rounded_corner_grey_with_border));
            holder.getTvFilterTitle().setTextColor(this.f5371a.getResources().getColor(R.color.main_text_color));
        }
        holder.getTvFilterTitle().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SensAIFilterListAdapter.b(SensAIFilterListAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSensAiFilterBinding inflate = ItemSensAiFilterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
