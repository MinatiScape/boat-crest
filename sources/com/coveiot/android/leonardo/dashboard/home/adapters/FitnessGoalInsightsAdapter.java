package com.coveiot.android.leonardo.dashboard.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.CalendarDayItemBinding;
import com.coveiot.android.leonardo.dashboard.health.model.GoalInsightsModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FitnessGoalInsightsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4775a;
    @NotNull
    public final List<GoalInsightsModel> b;
    @NotNull
    public final OnItemClickListener c;
    public int d;

    /* loaded from: classes3.dex */
    public interface OnItemClickListener {
        void onItemClicked(@NotNull GoalInsightsModel goalInsightsModel);
    }

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4776a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessGoalInsightsAdapter fitnessGoalInsightsAdapter, CalendarDayItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tabTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tabTitle");
            this.f4776a = textView;
        }

        @NotNull
        public final TextView getTabTitle() {
            return this.f4776a;
        }
    }

    public FitnessGoalInsightsAdapter(@NotNull Context mContext, @NotNull List<GoalInsightsModel> goalInsightsList, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(goalInsightsList, "goalInsightsList");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f4775a = mContext;
        this.b = goalInsightsList;
        this.c = onItemClickListener;
    }

    public static final void b(FitnessGoalInsightsAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d = i;
        this$0.notifyDataSetChanged();
        this$0.c.onItemClicked(this$0.b.get(i));
    }

    @NotNull
    public final List<GoalInsightsModel> getGoalInsightsList() {
        return this.b;
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
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTabTitle().setText(this.b.get(i).getCalendarType());
        if (this.d == i) {
            holder.getTabTitle().setBackground(this.f4775a.getResources().getDrawable(R.drawable.rounded_red_border_grey_background));
            holder.getTabTitle().setTextColor(this.f4775a.getResources().getColor(R.color.main_text_color));
        } else {
            holder.getTabTitle().setBackground(this.f4775a.getResources().getDrawable(R.drawable.rounded_corner_grey_with_border));
            holder.getTabTitle().setTextColor(this.f4775a.getResources().getColor(R.color.color_999999));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.adapters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessGoalInsightsAdapter.b(FitnessGoalInsightsAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CalendarDayItemBinding inflate = CalendarDayItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
