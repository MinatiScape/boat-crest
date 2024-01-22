package com.coveiot.android.activitymodes.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessSelectedDayGoalLayoutBinding;
import com.coveiot.android.activitymodes.models.ActivityInfo;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.covepreferences.UserDataManager;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessPlanSelectedDayGoalAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2775a;
    @NotNull
    public List<ActivityInfo> b;

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemFitnessSelectedDayGoalLayoutBinding f2776a;
        public final /* synthetic */ FitnessPlanSelectedDayGoalAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessPlanSelectedDayGoalAdapter fitnessPlanSelectedDayGoalAdapter, ListItemFitnessSelectedDayGoalLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessPlanSelectedDayGoalAdapter;
            this.f2776a = binding;
        }

        public final void bind(@NotNull ActivityInfo goalData) {
            String string;
            Intrinsics.checkNotNullParameter(goalData, "goalData");
            Log.d("goalData", "bind: " + new Gson().toJson(goalData));
            ListItemFitnessSelectedDayGoalLayoutBinding listItemFitnessSelectedDayGoalLayoutBinding = this.f2776a;
            FitnessPlanSelectedDayGoalAdapter fitnessPlanSelectedDayGoalAdapter = this.b;
            String title = goalData.getTitle();
            boolean z = false;
            if (((title == null || title.length() == 0) ? 1 : null) == null) {
                string = goalData.getTitle();
            } else {
                String activityType = goalData.getActivityType();
                if (activityType == null || activityType.length() == 0) {
                    z = true;
                }
                if (!z) {
                    string = goalData.getActivityType();
                } else {
                    string = fitnessPlanSelectedDayGoalAdapter.getContext().getString(R.string.dash_dash);
                }
            }
            listItemFitnessSelectedDayGoalLayoutBinding.setTitle(string);
            String activityCode = goalData.getActivityCode();
            if (activityCode != null) {
                int hashCode = activityCode.hashCode();
                if (hashCode != -821411934) {
                    if (hashCode != 81515) {
                        if (hashCode == 2656713 && activityCode.equals("WALK")) {
                            listItemFitnessSelectedDayGoalLayoutBinding.ivTodayGoal.setImageResource(R.drawable.ic_walk_circular_background_grey);
                        }
                    } else if (activityCode.equals(CoveApiConstants.RUN)) {
                        listItemFitnessSelectedDayGoalLayoutBinding.ivTodayGoal.setImageResource(R.drawable.ic_run_circular_background_grey);
                    }
                } else if (activityCode.equals("LIGHT_RUN")) {
                    listItemFitnessSelectedDayGoalLayoutBinding.ivTodayGoal.setImageResource(R.drawable.ic_run_circular_background_grey);
                }
            }
            TextView textView = listItemFitnessSelectedDayGoalLayoutBinding.tvTodayGoalValue;
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Context context = fitnessPlanSelectedDayGoalAdapter.getContext();
            String target = goalData.getTarget();
            Intrinsics.checkNotNull(target);
            int parseInt = Integer.parseInt(target);
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(fitnessPlanSelectedDayGoalAdapter.getContext()).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
            textView.setText(themesUtils.getDistanceWithUnit(context, parseInt, isDistanceUnitInMile.booleanValue() ? 1 : 0));
        }
    }

    public FitnessPlanSelectedDayGoalAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2775a = context;
        this.b = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f2775a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setSelectedDayData(@Nullable ArrayList<ActivityInfo> arrayList) {
        Intrinsics.checkNotNull(arrayList, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.android.activitymodes.models.ActivityInfo>");
        this.b = TypeIntrinsics.asMutableList(arrayList);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemFitnessSelectedDayGoalLayoutBinding inflate = ListItemFitnessSelectedDayGoalLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
