package com.coveiot.android.dashboard2.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityHistory;
import com.coveiot.android.activitymodes.adapters.ActivityParamHistoryAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.models.WorkoutUiBean;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.UntouchableRecyclerView;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.CleverTapConstants;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FragmentHome$getLastActivityData$1 implements Observer<EntityWorkoutSession> {
    public final /* synthetic */ FragmentHome h;

    public FragmentHome$getLastActivityData$1(FragmentHome fragmentHome) {
        this.h = fragmentHome;
    }

    public static final void c(FragmentHome this$0, EntityWorkoutSession entityWorkoutSession, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B1(entityWorkoutSession);
    }

    public static final void d(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_VIEW_ALL_ACTIVITIES_TAPPED.getValue(), null);
        this$0.startActivity(new Intent(this$0.requireContext(), ActivityHistory.class));
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable final EntityWorkoutSession entityWorkoutSession) {
        String dayOfWeek;
        String str;
        if (entityWorkoutSession == null) {
            this.h.q0().setShowActivityHistory(Boolean.FALSE);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(entityWorkoutSession.getStart_time()));
        if (DateUtils.isToday(calendar.getTimeInMillis())) {
            FragmentActivity activity = this.h.getActivity();
            Intrinsics.checkNotNull(activity);
            dayOfWeek = activity.getString(R.string.today);
        } else {
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            int i = calendar.get(7);
            FragmentActivity activity2 = this.h.getActivity();
            Intrinsics.checkNotNull(activity2);
            dayOfWeek = workoutUtils.getDayOfWeek(i, activity2);
        }
        Intrinsics.checkNotNullExpressionValue(dayOfWeek, "if (DateUtils.isToday(ca…  )\n                    }");
        WorkoutUtils workoutUtils2 = WorkoutUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        FragmentActivity activity3 = this.h.getActivity();
        Intrinsics.checkNotNull(activity3);
        String time = workoutUtils2.getTime(calendar, activity3);
        long start_time = entityWorkoutSession.getStart_time();
        FragmentActivity activity4 = this.h.getActivity();
        Intrinsics.checkNotNull(activity4);
        String todayYesterdayString = workoutUtils2.getTodayYesterdayString(start_time, activity4);
        String aMPMValue = workoutUtils2.getAMPMValue(entityWorkoutSession.getStart_time());
        ActivityMode activityMode = workoutUtils2.getActivityMode(entityWorkoutSession.getActivity_type());
        TextView textView = this.h.q0().activtyHistory.activityName;
        FragmentActivity activity5 = this.h.getActivity();
        Intrinsics.checkNotNull(activity5);
        textView.setText(workoutUtils2.getActivityModeNames(activityMode, activity5, entityWorkoutSession));
        Context context = this.h.getContext();
        Intrinsics.checkNotNull(context);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            str = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(str);
        } else {
            str = null;
        }
        WorkoutImageBean imageUiBean = workoutUtils2.getImageUiBean(context, activityMode, str, entityWorkoutSession.getActivityId(), entityWorkoutSession.getCategoryId());
        Object image = imageUiBean.getImage();
        if (image instanceof Integer) {
            ImageView imageView = this.h.q0().activtyHistory.activityIcon;
            Object image2 = imageUiBean.getImage();
            Intrinsics.checkNotNull(image2, "null cannot be cast to non-null type kotlin.Int");
            imageView.setImageResource(((Integer) image2).intValue());
        } else if (!(image instanceof String)) {
            this.h.q0().activtyHistory.activityIcon.setImageResource(R.drawable.activity_running_icon);
        } else {
            Context context2 = this.h.getContext();
            Intrinsics.checkNotNull(context2);
            RequestManager with = Glide.with(context2);
            Object image3 = imageUiBean.getImage();
            Intrinsics.checkNotNull(image3, "null cannot be cast to non-null type kotlin.String");
            RequestBuilder<Drawable> m30load = with.m30load((String) image3);
            ImageView imageView2 = this.h.q0().activtyHistory.activityIcon;
            Intrinsics.checkNotNull(imageView2);
            m30load.into(imageView2);
        }
        this.h.q0().activtyHistory.activityDay.setText(dayOfWeek + ' ' + time);
        this.h.q0().activtyHistory.dateText.setText(String.valueOf(todayYesterdayString));
        this.h.q0().activtyHistory.timeText.setText(String.valueOf(aMPMValue));
        this.h.q0().activtyHistory.clAutoActivity.setVisibility(8);
        ConstraintLayout constraintLayout = this.h.q0().activtyHistory.topCl;
        final FragmentHome fragmentHome = this.h;
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome$getLastActivityData$1.c(FragmentHome.this, entityWorkoutSession, view);
            }
        });
        this.h.q0().viewActivityHistoryHeader.tvTitle.setText(this.h.getString(com.coveiot.android.dashboard2.R.string.view_all_activities));
        ConstraintLayout constraintLayout2 = this.h.q0().viewActivityHistoryHeader.clRoundedTitle;
        final FragmentHome fragmentHome2 = this.h;
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome$getLastActivityData$1.d(FragmentHome.this, view);
            }
        });
        FragmentActivity activity6 = this.h.getActivity();
        Intrinsics.checkNotNull(activity6);
        List<WorkoutUiBean> workoutUiBeans = workoutUtils2.getWorkoutUiBeans(activity6, entityWorkoutSession, WorkoutUiBeanProvider.ScreenType.ACTIVITY_HOME);
        if (workoutUiBeans != null && (!workoutUiBeans.isEmpty())) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = this.h.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (companion.isMigratedDevice(requireContext)) {
                int size = workoutUiBeans.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    } else if (kotlin.text.m.equals(workoutUiBeans.get(i2).getValue(), this.h.requireContext().getResources().getString(R.string.activity_no_data), true)) {
                        this.h.q0().activtyHistory.activityLegendInfo.setVisibility(0);
                        break;
                    } else {
                        this.h.q0().activtyHistory.activityLegendInfo.setVisibility(8);
                        i2++;
                    }
                }
            }
            UntouchableRecyclerView untouchableRecyclerView = this.h.q0().activtyHistory.activityBeanRecyclerView;
            FragmentActivity activity7 = this.h.getActivity();
            Intrinsics.checkNotNull(activity7);
            untouchableRecyclerView.setLayoutManager(new GridLayoutManager(activity7, 3));
            FragmentActivity activity8 = this.h.getActivity();
            Intrinsics.checkNotNull(activity8);
            this.h.q0().activtyHistory.activityBeanRecyclerView.setAdapter(new ActivityParamHistoryAdapter(activity8, workoutUiBeans, WorkoutUiBeanProvider.ScreenType.ACTIVITY_HOME));
        }
        this.h.q0().setShowActivityHistory(Boolean.TRUE);
    }
}
