package com.coveiot.android.activitymodes.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.FitnessPlanWeekAdapter;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessWeekLayoutBinding;
import com.coveiot.android.activitymodes.models.WeekInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FitnessPlanWeekAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2777a;
    @NotNull
    public final FitnessWeekClickListener b;
    @NotNull
    public List<WeekInfo> c;
    public int d;
    public int e;

    /* loaded from: classes2.dex */
    public interface FitnessWeekClickListener {
        void weekClick(@NotNull WeekInfo weekInfo, int i);
    }

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemFitnessWeekLayoutBinding f2778a;
        public final /* synthetic */ FitnessPlanWeekAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessPlanWeekAdapter fitnessPlanWeekAdapter, ListItemFitnessWeekLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessPlanWeekAdapter;
            this.f2778a = binding;
        }

        public static final void b(FitnessPlanWeekAdapter this$0, ViewHolder this$1, WeekInfo weekData, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(weekData, "$weekData");
            this$0.e = this$0.d;
            this$0.d = this$1.getAbsoluteAdapterPosition();
            this$0.notifyItemChanged(this$0.e);
            this$0.notifyItemChanged(this$0.d);
            this$0.getListener().weekClick(weekData, this$1.getAbsoluteAdapterPosition());
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final WeekInfo weekData) {
            Intrinsics.checkNotNullParameter(weekData, "weekData");
            ListItemFitnessWeekLayoutBinding listItemFitnessWeekLayoutBinding = this.f2778a;
            final FitnessPlanWeekAdapter fitnessPlanWeekAdapter = this.b;
            listItemFitnessWeekLayoutBinding.setWeekCount(fitnessPlanWeekAdapter.getContext().getString(R.string.week) + ' ' + (getAbsoluteAdapterPosition() + 1));
            if (fitnessPlanWeekAdapter.d == getAbsoluteAdapterPosition()) {
                listItemFitnessWeekLayoutBinding.tvWeek.setBackgroundResource(R.drawable.red_gradient_bottom_border_background);
            } else {
                listItemFitnessWeekLayoutBinding.tvWeek.setBackgroundResource(R.color.transparent);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.m
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessPlanWeekAdapter.ViewHolder.b(FitnessPlanWeekAdapter.this, this, weekData, view);
                }
            });
        }
    }

    public FitnessPlanWeekAdapter(@NotNull Context context, @NotNull FitnessWeekClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2777a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f2777a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final FitnessWeekClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setWeekList(@NotNull List<WeekInfo> weeks) {
        Intrinsics.checkNotNullParameter(weeks, "weeks");
        this.c = TypeIntrinsics.asMutableList(weeks);
        notifyDataSetChanged();
    }

    public final void setWeekSelectedPosition(int i) {
        this.d = i;
        this.e = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemFitnessWeekLayoutBinding inflate = ListItemFitnessWeekLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
