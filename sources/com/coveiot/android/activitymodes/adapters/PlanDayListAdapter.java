package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.utils.DailyRunStatus;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PlanDayListAdapter extends RecyclerView.Adapter<PlanWeekHolder> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Float> f2781a;
    @NotNull
    public final String b = "PlanDayListAdapter";
    @NotNull
    public List<EntityPreparationDay> c = new ArrayList();
    public int d;
    @Nullable
    public DaySelectionListener e;
    @Nullable
    public Context f;

    /* loaded from: classes2.dex */
    public interface DaySelectionListener {
        void onDaySelected(@NotNull EntityPreparationDay entityPreparationDay);
    }

    /* loaded from: classes2.dex */
    public final class PlanWeekHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f2782a;
        @NotNull
        public final RecyclerView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ProgressBar d;
        @NotNull
        public final TextView e;
        @NotNull
        public final LinearLayout f;
        @NotNull
        public final TextView g;
        @NotNull
        public final TextView h;
        @NotNull
        public final TextView i;
        @NotNull
        public final LinearLayout j;
        @NotNull
        public final ImageView k;
        @NotNull
        public final View l;
        @NotNull
        public final TextView m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlanWeekHolder(@NotNull PlanDayListAdapter planDayListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.day_name);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.day_name)");
            this.f2782a = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.recyclerview_activities);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.….recyclerview_activities)");
            this.b = (RecyclerView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.day_target);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.day_target)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.pb_day);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.pb_day)");
            this.d = (ProgressBar) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.tv_progress_status);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.tv_progress_status)");
            this.e = (TextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.cardview_day);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "itemView.findViewById(R.id.cardview_day)");
            this.f = (LinearLayout) findViewById6;
            View findViewById7 = itemView.findViewById(R.id.day_progress);
            Intrinsics.checkNotNullExpressionValue(findViewById7, "itemView.findViewById(R.id.day_progress)");
            this.g = (TextView) findViewById7;
            View findViewById8 = itemView.findViewById(R.id.tv_km);
            Intrinsics.checkNotNullExpressionValue(findViewById8, "itemView.findViewById(R.id.tv_km)");
            this.h = (TextView) findViewById8;
            View findViewById9 = itemView.findViewById(R.id.target_km);
            Intrinsics.checkNotNullExpressionValue(findViewById9, "itemView.findViewById(R.id.target_km)");
            this.i = (TextView) findViewById9;
            View findViewById10 = itemView.findViewById(R.id.ll_distance_progress);
            Intrinsics.checkNotNullExpressionValue(findViewById10, "itemView.findViewById(R.id.ll_distance_progress)");
            this.j = (LinearLayout) findViewById10;
            View findViewById11 = itemView.findViewById(R.id.iv_target_status);
            Intrinsics.checkNotNullExpressionValue(findViewById11, "itemView.findViewById(R.id.iv_target_status)");
            this.k = (ImageView) findViewById11;
            View findViewById12 = itemView.findViewById(R.id.view_underline);
            Intrinsics.checkNotNullExpressionValue(findViewById12, "itemView.findViewById(R.id.view_underline)");
            this.l = findViewById12;
            View findViewById13 = itemView.findViewById(R.id.tv_start);
            Intrinsics.checkNotNullExpressionValue(findViewById13, "itemView.findViewById(R.id.tv_start)");
            this.m = (TextView) findViewById13;
        }

        @NotNull
        public final LinearLayout getCardviewDay() {
            return this.f;
        }

        @NotNull
        public final TextView getDayName() {
            return this.f2782a;
        }

        @NotNull
        public final TextView getDayProgress() {
            return this.g;
        }

        @NotNull
        public final TextView getDayTarget() {
            return this.c;
        }

        @NotNull
        public final ImageView getIvTargetStatus() {
            return this.k;
        }

        @NotNull
        public final LinearLayout getLlDistanceProgress() {
            return this.j;
        }

        @NotNull
        public final ProgressBar getProgressBar() {
            return this.d;
        }

        @NotNull
        public final RecyclerView getRecyclerViewActivities() {
            return this.b;
        }

        @NotNull
        public final TextView getTvKms() {
            return this.h;
        }

        @NotNull
        public final TextView getTvProgressStatus() {
            return this.e;
        }

        @NotNull
        public final TextView getTvTargetKm() {
            return this.i;
        }

        @NotNull
        public final TextView getTv_start() {
            return this.m;
        }

        @NotNull
        public final View getViewUnderline() {
            return this.l;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DailyRunStatus.values().length];
            try {
                iArr[DailyRunStatus.ACHIEVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DailyRunStatus.MISSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DailyRunStatus.INCOMPLETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DailyRunStatus.OVERTRAINED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DailyRunStatus.REST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void m(PlanDayListAdapter planDayListAdapter, PlanWeekHolder planWeekHolder, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        planDayListAdapter.l(planWeekHolder, z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3, (i & 16) != 0 ? false : z4);
    }

    public static final void n(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.REST);
    }

    public static final void p(PlanDayListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DaySelectionListener daySelectionListener = this$0.e;
        if (daySelectionListener != null) {
            Intrinsics.checkNotNull(daySelectionListener);
            daySelectionListener.onDaySelected(this$0.c.get(i));
            return;
        }
        throw new RuntimeException("Set the DaySelectionListener first");
    }

    public static final void q(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.REST);
    }

    public static final void r(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.MISSED);
    }

    public static final void s(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.ACHIEVED);
    }

    public static final void t(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.INCOMPLETE);
    }

    public static final void u(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.ACHIEVED);
    }

    public static final void v(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.OVERTRAINED);
    }

    public static final void w(PlanDayListAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(DailyRunStatus.OVERTRAINED);
    }

    public static final void y(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    @Nullable
    public final Context getMContext() {
        return this.f;
    }

    @Nullable
    public final DaySelectionListener getMDaySelectionListener() {
        return this.e;
    }

    @NotNull
    public final List<EntityPreparationDay> getPlanDayList() {
        return this.c;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final int getWeekNumber() {
        return this.d;
    }

    public final int k(float f, int i) {
        return (int) ((f / i) * 100.0f);
    }

    public final void l(PlanWeekHolder planWeekHolder, boolean z, boolean z2, boolean z3, boolean z4) {
        if (!z) {
            planWeekHolder.getTvProgressStatus().setVisibility(8);
            planWeekHolder.getRecyclerViewActivities().setVisibility(0);
            if (z3) {
                planWeekHolder.getLlDistanceProgress().setVisibility(8);
            } else {
                planWeekHolder.getLlDistanceProgress().setVisibility(0);
            }
            planWeekHolder.getProgressBar().setVisibility(0);
        } else if (z2) {
            planWeekHolder.getTvProgressStatus().setVisibility(0);
            planWeekHolder.getTvProgressStatus().setText(planWeekHolder.itemView.getContext().getString(R.string.miss));
            planWeekHolder.getLlDistanceProgress().setVisibility(8);
        } else if (z4) {
            planWeekHolder.getTvProgressStatus().setVisibility(8);
            planWeekHolder.getTvProgressStatus().setText(planWeekHolder.itemView.getContext().getString(R.string.start));
            planWeekHolder.getLlDistanceProgress().setVisibility(0);
            planWeekHolder.getTv_start().setVisibility(0);
        } else {
            planWeekHolder.getTvProgressStatus().setVisibility(0);
            planWeekHolder.getTvProgressStatus().setText(planWeekHolder.itemView.getContext().getString(R.string.resting));
            planWeekHolder.getLlDistanceProgress().setVisibility(8);
            planWeekHolder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlanDayListAdapter.n(PlanDayListAdapter.this, view);
                }
            });
        }
    }

    public final boolean o(EntityPreparationDay entityPreparationDay) {
        return kotlin.text.m.equals(entityPreparationDay.getDate(), AppUtils.formatDate(new Date(), "yyyy-MM-dd"), true);
    }

    public final void setDaySelectionListener(@NotNull DaySelectionListener daySelectionListener) {
        Intrinsics.checkNotNullParameter(daySelectionListener, "daySelectionListener");
        this.e = daySelectionListener;
    }

    public final void setDistanceList(@NotNull ArrayList<Float> distanceList) {
        Intrinsics.checkNotNullParameter(distanceList, "distanceList");
        this.f2781a = distanceList;
    }

    public final void setMContext(@Nullable Context context) {
        this.f = context;
    }

    public final void setMDaySelectionListener(@Nullable DaySelectionListener daySelectionListener) {
        this.e = daySelectionListener;
    }

    public final void setPlanDayList(@NotNull List<EntityPreparationDay> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.c = list;
    }

    public final void setWeekNumber(int i) {
        this.d = i;
    }

    public final void x(DailyRunStatus dailyRunStatus) {
        String string;
        if (this.f != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[dailyRunStatus.ordinal()];
            String str = "";
            if (i == 1) {
                Context context = this.f;
                Intrinsics.checkNotNull(context);
                str = context.getString(R.string.congratulations);
                Intrinsics.checkNotNullExpressionValue(str, "mContext!!.getString(R.string.congratulations)");
                Context context2 = this.f;
                Intrinsics.checkNotNull(context2);
                string = context2.getString(R.string.daily_run_achieved_msg);
                Intrinsics.checkNotNullExpressionValue(string, "mContext!!.getString(R.s…g.daily_run_achieved_msg)");
            } else if (i == 2) {
                Context context3 = this.f;
                Intrinsics.checkNotNull(context3);
                str = context3.getString(R.string.missed_activity);
                Intrinsics.checkNotNullExpressionValue(str, "mContext!!.getString(R.string.missed_activity)");
                Context context4 = this.f;
                Intrinsics.checkNotNull(context4);
                string = context4.getString(R.string.missed_activity_msg);
                Intrinsics.checkNotNullExpressionValue(string, "mContext!!.getString(R.string.missed_activity_msg)");
            } else if (i == 3) {
                Context context5 = this.f;
                Intrinsics.checkNotNull(context5);
                str = context5.getString(R.string.well_done);
                Intrinsics.checkNotNullExpressionValue(str, "mContext!!.getString(R.string.well_done)");
                Context context6 = this.f;
                Intrinsics.checkNotNull(context6);
                string = context6.getString(R.string.incomplete_activity_msg);
                Intrinsics.checkNotNullExpressionValue(string, "mContext!!.getString(R.s….incomplete_activity_msg)");
            } else if (i == 4) {
                Context context7 = this.f;
                Intrinsics.checkNotNull(context7);
                str = context7.getString(R.string.alert);
                Intrinsics.checkNotNullExpressionValue(str, "mContext!!.getString(R.string.alert)");
                Context context8 = this.f;
                Intrinsics.checkNotNull(context8);
                string = context8.getString(R.string.overtrained_msg);
                Intrinsics.checkNotNullExpressionValue(string, "mContext!!.getString(R.string.overtrained_msg)");
            } else if (i != 5) {
                string = "";
            } else {
                Context context9 = this.f;
                Intrinsics.checkNotNull(context9);
                str = context9.getString(R.string.resting_day);
                Intrinsics.checkNotNullExpressionValue(str, "mContext!!.getString(R.string.resting_day)");
                Context context10 = this.f;
                Intrinsics.checkNotNull(context10);
                string = context10.getString(R.string.resting_day_msg);
                Intrinsics.checkNotNullExpressionValue(string, "mContext!!.getString(R.string.resting_day_msg)");
            }
            Context context11 = this.f;
            Intrinsics.checkNotNull(context11);
            final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context11, str, string);
            Context context12 = this.f;
            Intrinsics.checkNotNull(context12);
            String string2 = context12.getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "mContext!!.getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlanDayListAdapter.y(BottomSheetDialogOneButtonTitleMessage.this, view);
                }
            });
            if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                return;
            }
            bottomSheetDialogOneButtonTitleMessage.show();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull PlanWeekHolder holder, final int i) {
        Drawable drawable;
        Float valueOf;
        WorkoutUtils workoutUtils;
        Intrinsics.checkNotNullParameter(holder, "holder");
        EntityPreparationDay entityPreparationDay = this.c.get(i);
        TextView dayName = holder.getDayName();
        StringBuilder sb = new StringBuilder();
        Context context = this.f;
        sb.append(context != null ? context.getString(R.string.d) : null);
        sb.append(((this.d - 1) * 7) + entityPreparationDay.getDay_number());
        dayName.setText(sb.toString());
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.f).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(mContext).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            TextView dayTarget = holder.getDayTarget();
            StringBuilder sb2 = new StringBuilder();
            sb2.append((float) WorkoutUtils.INSTANCE.convertKMToMiles(workoutUtils.convertMetersToKmFloat(entityPreparationDay.getTarget())));
            sb2.append(' ');
            Context context2 = this.f;
            sb2.append(context2 != null ? context2.getString(R.string.mil) : null);
            dayTarget.setText(sb2.toString());
        } else {
            TextView dayTarget2 = holder.getDayTarget();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(WorkoutUtils.INSTANCE.convertMetersToKmFloat(entityPreparationDay.getTarget()));
            sb3.append(' ');
            Context context3 = this.f;
            sb3.append(context3 != null ? context3.getString(R.string.km) : null);
            sb3.append(' ');
            dayTarget2.setText(sb3.toString());
        }
        PlanActivityAdapter planActivityAdapter = new PlanActivityAdapter(o(entityPreparationDay));
        List<EntityPreparationActivity> activities = entityPreparationDay.getActivities();
        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity>");
        planActivityAdapter.setPlanActivities((ArrayList) activities);
        Date formatDateToDate = AppUtils.formatDateToDate(new Date(), "yyyy-MM-dd");
        WorkoutUtils workoutUtils2 = WorkoutUtils.INSTANCE;
        int activitiesTotalTarget = workoutUtils2.getActivitiesTotalTarget(entityPreparationDay.getActivities());
        if (o(entityPreparationDay)) {
            View view = holder.itemView;
            int i2 = R.id.cardview_day;
            ((LinearLayout) view.findViewById(i2)).setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.transparent));
            ((LinearLayout) holder.itemView.findViewById(i2)).setBackgroundResource(R.drawable.button_gradiant_bg);
            holder.getTvProgressStatus().setText(holder.itemView.getContext().getString(R.string.start));
            TextView tvProgressStatus = holder.getTvProgressStatus();
            Context context4 = holder.itemView.getContext();
            int i3 = R.color.white;
            tvProgressStatus.setTextColor(ContextCompat.getColor(context4, i3));
            holder.getProgressBar().setBackground(holder.itemView.getContext().getDrawable(R.drawable.circle_shape));
            holder.getProgressBar().setProgressDrawable(holder.itemView.getContext().getDrawable(R.drawable.circular_progress_bar_white));
            holder.getTvKms().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i3));
            holder.getDayProgress().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i3));
            holder.getTvProgressStatus().setVisibility(0);
            TextView dayName2 = holder.getDayName();
            Context context5 = holder.itemView.getContext();
            int i4 = R.color.colorPrimary;
            dayName2.setTextColor(ContextCompat.getColor(context5, i4));
            holder.getDayTarget().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i4));
            holder.getTvTargetKm().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i4));
            if (Build.VERSION.SDK_INT >= 28) {
                ((LinearLayout) holder.itemView.findViewById(i2)).setOutlineAmbientShadowColor(ContextCompat.getColor(holder.itemView.getContext(), i4));
            }
            if (activitiesTotalTarget > 0) {
                holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.v
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PlanDayListAdapter.p(PlanDayListAdapter.this, i, view2);
                    }
                });
            } else {
                holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.q
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PlanDayListAdapter.q(PlanDayListAdapter.this, view2);
                    }
                });
                holder.getDayTarget().setText(holder.itemView.getContext().getString(R.string.resting));
                holder.getTvTargetKm().setVisibility(8);
            }
            drawable = null;
        } else {
            TextView dayProgress = holder.getDayProgress();
            Context context6 = holder.itemView.getContext();
            int i5 = R.color.main_text_color;
            dayProgress.setTextColor(ContextCompat.getColor(context6, i5));
            holder.getTvKms().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i5));
            holder.getViewUnderline().setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), i5));
            holder.getProgressBar().setProgressDrawable(holder.itemView.getContext().getDrawable(R.drawable.circular_progress_bar_white));
            holder.getDayName().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i5));
            holder.getDayTarget().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i5));
            holder.getTvTargetKm().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i5));
            holder.getTvProgressStatus().setTextColor(ContextCompat.getColor(holder.itemView.getContext(), i5));
            ((LinearLayout) holder.itemView.findViewById(R.id.cardview_day)).setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.transparent));
            drawable = null;
            holder.itemView.setOnClickListener(null);
            if (activitiesTotalTarget == 0) {
                holder.getDayTarget().setText(holder.itemView.getContext().getString(R.string.resting));
                holder.getTvTargetKm().setVisibility(8);
            }
        }
        int target = entityPreparationDay.getTarget();
        holder.getRecyclerViewActivities().setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.getRecyclerViewActivities().setAdapter(planActivityAdapter);
        ArrayList<Float> arrayList = this.f2781a;
        if (arrayList == null || (valueOf = arrayList.get(i)) == null) {
            valueOf = Float.valueOf(0.0f);
        }
        float floatValue = valueOf.floatValue();
        if (activitiesTotalTarget <= 0) {
            if (AppUtils.parseDate(entityPreparationDay.getDate(), "yyyy-MM-dd").after(formatDateToDate)) {
                m(this, holder, false, false, true, false, 20, null);
                return;
            }
            if (this.f != null) {
                ProgressBar progressBar = holder.getProgressBar();
                Context context7 = this.f;
                Intrinsics.checkNotNull(context7);
                progressBar.setProgressDrawable(context7.getDrawable(R.drawable.circular_progress_bar_white));
                holder.getProgressBar().setProgress(100);
            }
            if (floatValue > 500.0f) {
                m(this, holder, false, false, false, false, 28, null);
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this.f).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(mContext).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    TextView dayProgress2 = holder.getDayProgress();
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append((float) workoutUtils2.convertKMToMiles(workoutUtils2.convertMetersToKmFloat((int) floatValue)));
                    sb4.append(' ');
                    Context context8 = this.f;
                    sb4.append(context8 != null ? context8.getString(R.string.mil) : null);
                    dayProgress2.setText(sb4.toString());
                } else {
                    TextView dayProgress3 = holder.getDayProgress();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(workoutUtils2.convertMetersToKmFloat((int) floatValue));
                    sb5.append(' ');
                    Context context9 = this.f;
                    sb5.append(context9 != null ? context9.getString(R.string.km) : null);
                    sb5.append(' ');
                    dayProgress3.setText(sb5.toString());
                }
                holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.p
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PlanDayListAdapter.w(PlanDayListAdapter.this, view2);
                    }
                });
                ImageView ivTargetStatus = holder.getIvTargetStatus();
                Context context10 = this.f;
                ivTargetStatus.setImageDrawable(context10 != null ? context10.getDrawable(R.drawable.ic_over_trained) : null);
                return;
            }
            m(this, holder, true, false, false, false, 28, null);
            return;
        }
        if (floatValue == 0.0f) {
            if (AppUtils.parseDate(entityPreparationDay.getDate(), "yyyy-MM-dd").before(formatDateToDate)) {
                m(this, holder, true, true, false, false, 24, null);
                holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.u
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PlanDayListAdapter.r(PlanDayListAdapter.this, view2);
                    }
                });
                return;
            } else if (AppUtils.parseDate(entityPreparationDay.getDate(), "yyyy-MM-dd").after(formatDateToDate)) {
                m(this, holder, false, false, true, false, 16, null);
                return;
            } else {
                return;
            }
        }
        float f = target;
        if (floatValue >= f) {
            if (floatValue >= f) {
                m(this, holder, false, false, false, false, 28, null);
                holder.getProgressBar().setProgressDrawable(holder.itemView.getContext().getDrawable(R.drawable.circular_progress_bar_white));
                holder.getProgressBar().setProgress(100);
                Boolean isDistanceUnitInMile3 = UserDataManager.getInstance(this.f).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile3, "getInstance(mContext).isDistanceUnitInMile");
                if (isDistanceUnitInMile3.booleanValue()) {
                    TextView dayProgress4 = holder.getDayProgress();
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append((float) workoutUtils2.convertKMToMiles(workoutUtils2.convertMetersToKmFloat((int) floatValue)));
                    sb6.append(' ');
                    Context context11 = this.f;
                    sb6.append(context11 != null ? context11.getString(R.string.mil) : null);
                    dayProgress4.setText(sb6.toString());
                } else {
                    TextView dayProgress5 = holder.getDayProgress();
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(workoutUtils2.convertMetersToKmFloat((int) floatValue));
                    sb7.append(' ');
                    Context context12 = this.f;
                    sb7.append(context12 != null ? context12.getString(R.string.km) : null);
                    sb7.append(' ');
                    dayProgress5.setText(sb7.toString());
                }
                if (floatValue - f < 500.0f) {
                    ImageView ivTargetStatus2 = holder.getIvTargetStatus();
                    Context context13 = this.f;
                    ivTargetStatus2.setImageDrawable(context13 != null ? context13.getDrawable(R.drawable.ic_completed_green_tick) : null);
                    holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.r
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            PlanDayListAdapter.u(PlanDayListAdapter.this, view2);
                        }
                    });
                    return;
                }
                ImageView ivTargetStatus3 = holder.getIvTargetStatus();
                Context context14 = this.f;
                ivTargetStatus3.setImageDrawable(context14 != null ? context14.getDrawable(R.drawable.ic_over_trained) : null);
                holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.s
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PlanDayListAdapter.v(PlanDayListAdapter.this, view2);
                    }
                });
                return;
            }
            return;
        }
        holder.getProgressBar().setProgressDrawable(holder.itemView.getContext().getDrawable(R.drawable.circular_progress_bar_white2));
        holder.getProgressBar().setProgress(k(floatValue, target));
        Boolean isDistanceUnitInMile4 = UserDataManager.getInstance(this.f).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile4, "getInstance(mContext).isDistanceUnitInMile");
        if (isDistanceUnitInMile4.booleanValue()) {
            TextView dayProgress6 = holder.getDayProgress();
            StringBuilder sb8 = new StringBuilder();
            sb8.append((float) workoutUtils2.convertKMToMiles(workoutUtils2.convertMetersToKmFloat((int) floatValue)));
            sb8.append(' ');
            Context context15 = this.f;
            sb8.append(context15 != null ? context15.getString(R.string.mil) : drawable);
            dayProgress6.setText(sb8.toString());
        } else {
            TextView dayProgress7 = holder.getDayProgress();
            StringBuilder sb9 = new StringBuilder();
            sb9.append(workoutUtils2.convertMetersToKmFloat((int) floatValue));
            sb9.append(' ');
            Context context16 = this.f;
            sb9.append(context16 != null ? context16.getString(R.string.km) : drawable);
            sb9.append(' ');
            dayProgress7.setText(sb9.toString());
        }
        if (f - floatValue < 200.0f) {
            m(this, holder, false, false, false, false, 28, null);
            ImageView ivTargetStatus4 = holder.getIvTargetStatus();
            Context context17 = this.f;
            if (context17 != null) {
                drawable = context17.getDrawable(R.drawable.ic_completed_green_tick);
            }
            ivTargetStatus4.setImageDrawable(drawable);
            holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.n
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PlanDayListAdapter.s(PlanDayListAdapter.this, view2);
                }
            });
        } else if (!o(entityPreparationDay)) {
            m(this, holder, false, false, false, false, 28, null);
            ImageView ivTargetStatus5 = holder.getIvTargetStatus();
            Context context18 = this.f;
            if (context18 != null) {
                drawable = context18.getDrawable(R.drawable.ic_incomplete_activity);
            }
            ivTargetStatus5.setImageDrawable(drawable);
            holder.getProgressBar().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PlanDayListAdapter.t(PlanDayListAdapter.this, view2);
                }
            });
        } else {
            m(this, holder, true, false, false, true, 12, null);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public PlanWeekHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.f = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_day_details, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new PlanWeekHolder(this, inflate);
    }
}
