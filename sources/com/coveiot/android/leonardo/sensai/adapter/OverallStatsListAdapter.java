package com.coveiot.android.leonardo.sensai.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.OverallStatsItemBinding;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class OverallStatsListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5359a;
    @NotNull
    public final List<String> b;
    @NotNull
    public final OnItemClickListener c;
    public int d;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClicked(int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5360a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull OverallStatsListAdapter overallStatsListAdapter, OverallStatsItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tabTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tabTitle");
            this.f5360a = textView;
        }

        @NotNull
        public final TextView getTabTitle() {
            return this.f5360a;
        }
    }

    public OverallStatsListAdapter(@NotNull Context mContext, @NotNull List<String> rangeList, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(rangeList, "rangeList");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5359a = mContext;
        this.b = rangeList;
        this.c = onItemClickListener;
    }

    public static final void b(OverallStatsListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d = i;
        this$0.notifyDataSetChanged();
        this$0.c.onItemClicked(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, @SuppressLint({"RecyclerView"}) final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTabTitle().setText(this.b.get(i));
        if (this.d == i) {
            holder.getTabTitle().setBackground(this.f5359a.getResources().getDrawable(R.drawable.tab_selected_bg));
            holder.getTabTitle().setTextColor(this.f5359a.getResources().getColor(R.color.main_text_color));
        } else {
            holder.getTabTitle().setBackground(this.f5359a.getResources().getDrawable(R.drawable.activity_share_button_bg));
            holder.getTabTitle().setTextColor(this.f5359a.getResources().getColor(R.color.white_opacity_50_color));
        }
        holder.getTabTitle().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OverallStatsListAdapter.b(OverallStatsListAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        OverallStatsItemBinding inflate = OverallStatsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
