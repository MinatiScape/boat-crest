package com.coveiot.android.activitymodes.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.FitnessPlanDayAdapter;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessDaysLayoutBinding;
import com.coveiot.android.activitymodes.models.DayInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FitnessPlanDayAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2771a;
    @NotNull
    public final FitnessDayClickListener b;
    @NotNull
    public List<DayInfo> c;
    public int d;
    public int e;

    /* loaded from: classes2.dex */
    public interface FitnessDayClickListener {
        void dayClick(@NotNull DayInfo dayInfo);
    }

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemFitnessDaysLayoutBinding f2772a;
        public final /* synthetic */ FitnessPlanDayAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessPlanDayAdapter fitnessPlanDayAdapter, ListItemFitnessDaysLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessPlanDayAdapter;
            this.f2772a = binding;
        }

        public static final void b(FitnessPlanDayAdapter this$0, ViewHolder this$1, DayInfo dayData, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(dayData, "$dayData");
            this$0.e = this$0.d;
            this$0.d = this$1.getAbsoluteAdapterPosition();
            this$0.notifyItemChanged(this$0.e);
            this$0.notifyItemChanged(this$0.d);
            this$0.getListener().dayClick(dayData);
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final DayInfo dayData) {
            Intrinsics.checkNotNullParameter(dayData, "dayData");
            ListItemFitnessDaysLayoutBinding listItemFitnessDaysLayoutBinding = this.f2772a;
            final FitnessPlanDayAdapter fitnessPlanDayAdapter = this.b;
            listItemFitnessDaysLayoutBinding.setDayCount(String.valueOf(getAbsoluteAdapterPosition() + 1));
            if (fitnessPlanDayAdapter.d == getAbsoluteAdapterPosition()) {
                listItemFitnessDaysLayoutBinding.clDay.setBackgroundResource(R.drawable.rounded_corner_grey_with_red_border_fitness);
            } else {
                listItemFitnessDaysLayoutBinding.clDay.setBackgroundResource(R.drawable.rounded_corner_grey_fitness);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessPlanDayAdapter.ViewHolder.b(FitnessPlanDayAdapter.this, this, dayData, view);
                }
            });
        }
    }

    public FitnessPlanDayAdapter(@NotNull Context context, @NotNull FitnessDayClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2771a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f2771a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final FitnessDayClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setDayList(@NotNull List<DayInfo> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.c = TypeIntrinsics.asMutableList(list);
        notifyDataSetChanged();
    }

    public final void setDaySelectedPosition(int i) {
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
        ListItemFitnessDaysLayoutBinding inflate = ListItemFitnessDaysLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
