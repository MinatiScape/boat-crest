package com.coveiot.android.activitymodes.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.FitnessPlanHistoryAdapter;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessPlanHistoryLayoutBinding;
import com.coveiot.android.activitymodes.models.PlanHistory;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.UserDataManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FitnessPlanHistoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2773a;
    @NotNull
    public final FitnessHistoryClickListener b;
    @NotNull
    public List<PlanHistory> c;

    /* loaded from: classes2.dex */
    public interface FitnessHistoryClickListener {
        void historyClick(@NotNull PlanHistory planHistory);
    }

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemFitnessPlanHistoryLayoutBinding f2774a;
        public final /* synthetic */ FitnessPlanHistoryAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessPlanHistoryAdapter fitnessPlanHistoryAdapter, ListItemFitnessPlanHistoryLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessPlanHistoryAdapter;
            this.f2774a = binding;
        }

        public static final void b(FitnessPlanHistoryAdapter this$0, PlanHistory plan, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(plan, "$plan");
            this$0.getListener().historyClick(plan);
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final PlanHistory plan) {
            String string;
            Intrinsics.checkNotNullParameter(plan, "plan");
            ListItemFitnessPlanHistoryLayoutBinding listItemFitnessPlanHistoryLayoutBinding = this.f2774a;
            final FitnessPlanHistoryAdapter fitnessPlanHistoryAdapter = this.b;
            Boolean bool = Boolean.TRUE;
            listItemFitnessPlanHistoryLayoutBinding.setIsPlanCompleted(bool);
            listItemFitnessPlanHistoryLayoutBinding.setPlanHistoryData(plan);
            listItemFitnessPlanHistoryLayoutBinding.setIsProgressFull(bool);
            TextView textView = listItemFitnessPlanHistoryLayoutBinding.tvTotalDistance;
            StringBuilder sb = new StringBuilder();
            sb.append((int) ThemesUtils.INSTANCE.getDistanceByUnitSelected(fitnessPlanHistoryAdapter.getContext(), plan.getTotalDistance()));
            sb.append(' ');
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(fitnessPlanHistoryAdapter.getContext()).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
            if (isDistanceUnitInMile.booleanValue()) {
                string = fitnessPlanHistoryAdapter.getContext().getString(R.string.mil);
            } else {
                string = fitnessPlanHistoryAdapter.getContext().getString(R.string.km);
            }
            sb.append(string);
            textView.setText(sb.toString());
            listItemFitnessPlanHistoryLayoutBinding.tvViewDetails.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessPlanHistoryAdapter.ViewHolder.b(FitnessPlanHistoryAdapter.this, plan, view);
                }
            });
        }
    }

    public FitnessPlanHistoryAdapter(@NotNull Context context, @NotNull FitnessHistoryClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2773a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f2773a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final FitnessHistoryClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setPlanHistoryList(@NotNull List<PlanHistory> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.c = TypeIntrinsics.asMutableList(list);
        notifyDataSetChanged();
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
        ListItemFitnessPlanHistoryLayoutBinding inflate = ListItemFitnessPlanHistoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
