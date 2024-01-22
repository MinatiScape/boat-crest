package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.decorator.ActivityParamHistoryItemDecorator;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.models.WorkoutUiBean;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityHistoryAdapter extends RecyclerView.Adapter<ActivityHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2763a;
    @NotNull
    public final WorkoutUiBeanProvider.ScreenType b;
    @Nullable
    public AutoDetectionFeedbackInterface c;
    @Nullable
    public List<EntityWorkoutSession> d;

    /* loaded from: classes2.dex */
    public final class ActivityHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f2764a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final RecyclerView f;
        @NotNull
        public final ConstraintLayout g;
        @NotNull
        public final ImageView h;
        @NotNull
        public final ConstraintLayout i;
        @NotNull
        public final TextView j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityHolder(@NotNull ActivityHistoryAdapter activityHistoryAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.activity_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.activity_icon)");
            this.f2764a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.activity_name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.activity_name)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.activity_day);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.activity_day)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.date_text);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.date_text)");
            this.d = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.time_text);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.time_text)");
            this.e = (TextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.activityBeanRecyclerView);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "itemView.findViewById(R.…activityBeanRecyclerView)");
            this.f = (RecyclerView) findViewById6;
            View findViewById7 = itemView.findViewById(R.id.top_cl);
            Intrinsics.checkNotNullExpressionValue(findViewById7, "itemView.findViewById(R.id.top_cl)");
            this.g = (ConstraintLayout) findViewById7;
            View findViewById8 = itemView.findViewById(R.id.activityBestImgV);
            Intrinsics.checkNotNullExpressionValue(findViewById8, "itemView.findViewById(R.id.activityBestImgV)");
            this.h = (ImageView) findViewById8;
            View findViewById9 = itemView.findViewById(R.id.cl_autoActivity);
            Intrinsics.checkNotNullExpressionValue(findViewById9, "itemView.findViewById(R.id.cl_autoActivity)");
            this.i = (ConstraintLayout) findViewById9;
            View findViewById10 = itemView.findViewById(R.id.activity_legend_info);
            Intrinsics.checkNotNullExpressionValue(findViewById10, "itemView.findViewById(R.id.activity_legend_info)");
            this.j = (TextView) findViewById10;
        }

        @NotNull
        public final RecyclerView getActivityBeanRecyclerView() {
            return this.f;
        }

        @NotNull
        public final ImageView getActivityBestImgV() {
            return this.h;
        }

        @NotNull
        public final TextView getActivityDay() {
            return this.c;
        }

        @NotNull
        public final ImageView getActivityIcon() {
            return this.f2764a;
        }

        @NotNull
        public final TextView getActivityLegendInfo() {
            return this.j;
        }

        @NotNull
        public final TextView getActivityName() {
            return this.b;
        }

        @NotNull
        public final ConstraintLayout getAutoDetectionCl() {
            return this.i;
        }

        @NotNull
        public final TextView getDateText() {
            return this.d;
        }

        @NotNull
        public final ConstraintLayout getParentConstraintLayout() {
            return this.g;
        }

        @NotNull
        public final TextView getTimeText() {
            return this.e;
        }
    }

    /* loaded from: classes2.dex */
    public interface AutoDetectionFeedbackInterface {
        void onFeedbackClicked(@NotNull EntityWorkoutSession entityWorkoutSession);
    }

    public ActivityHistoryAdapter(@NotNull Context context, @NotNull WorkoutUiBeanProvider.ScreenType screenType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        this.f2763a = context;
        this.b = screenType;
    }

    public static final void c(ActivityHistoryAdapter this$0, EntityWorkoutSession entityWorkoutSession, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b(entityWorkoutSession);
    }

    public final void b(EntityWorkoutSession entityWorkoutSession) {
        LogHelper.d("ActivityHistoryAdapter", "loadDetailScreen");
        if (new PreferenceManager(this.f2763a).isSampleDataSupported() || DeviceUtils.Companion.isEastApexDevice(this.f2763a)) {
            Intent intent = new Intent(this.f2763a, ActivityDataSummaryDetails.class);
            intent.putExtra(WorkoutConstants.ACTIVITY_MODE, entityWorkoutSession.getActivity_type());
            intent.putExtra(WorkoutConstants.SESSION_ID, entityWorkoutSession.getSession_id());
            this.f2763a.startActivity(intent);
        }
    }

    @Nullable
    public final AutoDetectionFeedbackInterface getAutoDetectFeedbackListener() {
        return this.c;
    }

    @Nullable
    public final List<EntityWorkoutSession> getEntityWorkoutSessions() {
        return this.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<EntityWorkoutSession> list = this.d;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (list.size() <= 2 || this.b != WorkoutUiBeanProvider.ScreenType.ACTIVITY_FITNESS) {
                List<EntityWorkoutSession> list2 = this.d;
                Intrinsics.checkNotNull(list2);
                return list2.size();
            }
            return 2;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public final void setAutoDetectFeedbackListener(@Nullable AutoDetectionFeedbackInterface autoDetectionFeedbackInterface) {
        this.c = autoDetectionFeedbackInterface;
    }

    public final void setData(@NotNull List<EntityWorkoutSession> entityWorkoutSessions) {
        Intrinsics.checkNotNullParameter(entityWorkoutSessions, "entityWorkoutSessions");
        this.d = entityWorkoutSessions;
        notifyDataSetChanged();
    }

    public final void setEntityWorkoutSessions(@Nullable List<EntityWorkoutSession> list) {
        this.d = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityHolder holder, int i) {
        String dayOfWeek;
        Integer fromHAR;
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<EntityWorkoutSession> list = this.d;
        String str = null;
        final EntityWorkoutSession entityWorkoutSession = list != null ? list.get(i) : null;
        if (entityWorkoutSession != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(entityWorkoutSession.getStart_time()));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                dayOfWeek = this.f2763a.getString(R.string.today);
            } else {
                dayOfWeek = WorkoutUtils.INSTANCE.getDayOfWeek(calendar.get(7), this.f2763a);
            }
            Intrinsics.checkNotNullExpressionValue(dayOfWeek, "if (DateUtils.isToday(ca…), context)\n            }");
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            String time = workoutUtils.getTime(calendar, this.f2763a);
            String todayYesterdayString = workoutUtils.getTodayYesterdayString(entityWorkoutSession.getStart_time(), this.f2763a);
            String aMPMValue = workoutUtils.getAMPMValue(entityWorkoutSession.getStart_time());
            ActivityMode activityMode = workoutUtils.getActivityMode(entityWorkoutSession.getActivity_type());
            TextView activityName = holder.getActivityName();
            Context context = this.f2763a;
            Intrinsics.checkNotNull(context);
            activityName.setText(workoutUtils.getActivityModeNames(activityMode, context, entityWorkoutSession));
            Context context2 = this.f2763a;
            if (entityWorkoutSession.getIndoor_outdoor() != null) {
                str = entityWorkoutSession.getIndoor_outdoor();
                Intrinsics.checkNotNull(str);
            }
            WorkoutImageBean imageUiBean = workoutUtils.getImageUiBean(context2, activityMode, str, entityWorkoutSession.getActivityId(), entityWorkoutSession.getCategoryId());
            Object image = imageUiBean.getImage();
            if (image instanceof Integer) {
                holder.getActivityIcon().setImageResource(((Number) imageUiBean.getImage()).intValue());
            } else if (image instanceof String) {
                Context context3 = this.f2763a;
                Intrinsics.checkNotNull(context3);
                RequestBuilder<Drawable> m30load = Glide.with(context3).m30load((String) imageUiBean.getImage());
                ImageView activityIcon = holder.getActivityIcon();
                Intrinsics.checkNotNull(activityIcon);
                m30load.into(activityIcon);
            } else {
                holder.getActivityIcon().setImageResource(R.drawable.activity_running_icon);
            }
            holder.getActivityDay().setText(dayOfWeek + ' ' + time);
            holder.getDateText().setText(String.valueOf(todayYesterdayString));
            holder.getTimeText().setText(String.valueOf(aMPMValue));
            List<WorkoutUiBean> workoutUiBeans = workoutUtils.getWorkoutUiBeans(this.f2763a, entityWorkoutSession, this.b);
            if (workoutUiBeans != null && (!workoutUiBeans.isEmpty())) {
                holder.getActivityBeanRecyclerView().setVisibility(0);
                holder.getActivityBeanRecyclerView().setLayoutManager(new GridLayoutManager(this.f2763a, 3));
                RecyclerView.LayoutManager layoutManager = holder.getActivityBeanRecyclerView().getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.GridLayoutManager");
                ((GridLayoutManager) layoutManager).setInitialPrefetchItemCount(workoutUiBeans.size());
                ActivityParamHistoryAdapter activityParamHistoryAdapter = new ActivityParamHistoryAdapter(this.f2763a, workoutUiBeans, this.b);
                if (DeviceUtils.Companion.isMigratedDevice(this.f2763a)) {
                    int size = workoutUiBeans.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        } else if (kotlin.text.m.equals(workoutUiBeans.get(i2).getValue(), this.f2763a.getResources().getString(R.string.activity_no_data), true)) {
                            holder.getActivityLegendInfo().setVisibility(0);
                            break;
                        } else {
                            holder.getActivityLegendInfo().setVisibility(8);
                            i2++;
                        }
                    }
                }
                holder.getActivityBeanRecyclerView().setAdapter(activityParamHistoryAdapter);
            } else {
                holder.getActivityBeanRecyclerView().setVisibility(8);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityHistoryAdapter.c(ActivityHistoryAdapter.this, entityWorkoutSession, view);
                }
            });
            if (WorkoutUtils.INSTANCE.isBestActivityOfType(this.f2763a, entityWorkoutSession)) {
                holder.getActivityBestImgV().setVisibility(0);
            } else {
                holder.getActivityBestImgV().setVisibility(8);
            }
            if (entityWorkoutSession.getFromHAR() != null && (fromHAR = entityWorkoutSession.getFromHAR()) != null && fromHAR.intValue() == 1) {
                holder.getAutoDetectionCl().setVisibility(0);
            } else {
                holder.getAutoDetectionCl().setVisibility(8);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        ActivityHolder activityHolder = new ActivityHolder(this, inflate);
        activityHolder.getActivityBeanRecyclerView().addItemDecoration(new ActivityParamHistoryItemDecorator(this.f2763a.getResources().getDimensionPixelSize(R.dimen.margin_8dp), 0, 0, 6, null));
        return activityHolder;
    }
}
