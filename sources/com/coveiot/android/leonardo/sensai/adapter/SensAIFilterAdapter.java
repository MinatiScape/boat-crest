package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.SensAiFilterItemBinding;
import com.coveiot.android.leonardo.sensai.model.SensAIFilter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SensAIFilterAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5369a;
    @NotNull
    public final List<SensAIFilter> b;
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
        public final TextView f5370a;
        @NotNull
        public final View b;
        @NotNull
        public final ConstraintLayout c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAIFilterAdapter sensAIFilterAdapter, SensAiFilterItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.filterName;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.filterName");
            this.f5370a = textView;
            View view = itemView.selectedDiv;
            Intrinsics.checkNotNullExpressionValue(view, "itemView.selectedDiv");
            this.b = view;
            ConstraintLayout constraintLayout = itemView.clFilter;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.clFilter");
            this.c = constraintLayout;
        }

        @NotNull
        public final ConstraintLayout getClFilter() {
            return this.c;
        }

        @NotNull
        public final TextView getFilterName() {
            return this.f5370a;
        }

        @NotNull
        public final View getSelectedDiv() {
            return this.b;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SensAIFilterAdapter(@NotNull Context mContext, @NotNull List<? extends SensAIFilter> filterOptions, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(filterOptions, "filterOptions");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5369a = mContext;
        this.b = filterOptions;
        this.c = onItemClickListener;
    }

    public static final void b(SensAIFilterAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d = i;
        this$0.c.onItemClicked(i);
        this$0.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    public final int getSelectedIndex() {
        return this.d;
    }

    public final void setSelectedIndex(int i) {
        this.d = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getClFilter().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SensAIFilterAdapter.b(SensAIFilterAdapter.this, i, view);
            }
        });
        if (this.d == i) {
            holder.getSelectedDiv().setVisibility(0);
            holder.getFilterName().setTextColor(this.f5369a.getColor(R.color.text_color_primary));
        } else {
            holder.getSelectedDiv().setVisibility(8);
            holder.getFilterName().setTextColor(this.f5369a.getColor(R.color.secondary_text_color));
        }
        holder.getFilterName().setText(this.b.get(i).getName());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SensAiFilterItemBinding inflate = SensAiFilterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
