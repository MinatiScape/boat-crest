package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.ActivityParamHistoryAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.models.WorkoutUiBean;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.UntouchableRecyclerView;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentActivityDashboard$onViewCreated$8 implements Observer<EntityWorkoutSession> {
    public final /* synthetic */ FragmentActivityDashboard h;

    public FragmentActivityDashboard$onViewCreated$8(FragmentActivityDashboard fragmentActivityDashboard) {
        this.h = fragmentActivityDashboard;
    }

    public static final void b(FragmentActivityDashboard this$0, EntityWorkoutSession entityWorkoutSession, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.F(entityWorkoutSession);
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable final EntityWorkoutSession entityWorkoutSession) {
        String dayOfWeek;
        String str;
        this.h.setEntityWSession(entityWorkoutSession);
        if (entityWorkoutSession != null) {
            ((CardView) this.h._$_findCachedViewById(R.id.view_activity_here_card)).setVisibility(8);
            FragmentActivityDashboard fragmentActivityDashboard = this.h;
            int i = R.id.recentActivityCard;
            fragmentActivityDashboard._$_findCachedViewById(i).setVisibility(0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(entityWorkoutSession.getStart_time()));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                FragmentActivity activity = this.h.getActivity();
                Intrinsics.checkNotNull(activity);
                dayOfWeek = activity.getString(R.string.today);
            } else {
                WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                int i2 = calendar.get(7);
                FragmentActivity activity2 = this.h.getActivity();
                Intrinsics.checkNotNull(activity2);
                dayOfWeek = workoutUtils.getDayOfWeek(i2, activity2);
            }
            Intrinsics.checkNotNullExpressionValue(dayOfWeek, "if (DateUtils.isToday(ca…                        }");
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
            FragmentActivity activity5 = this.h.getActivity();
            Intrinsics.checkNotNull(activity5);
            ((TextView) this.h._$_findCachedViewById(R.id.activity_name)).setText(workoutUtils2.getActivityModeNames(activityMode, activity5, entityWorkoutSession));
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
                ((ImageView) this.h._$_findCachedViewById(R.id.activity_icon)).setImageResource(((Number) imageUiBean.getImage()).intValue());
            } else if (image instanceof String) {
                Context context2 = this.h.getContext();
                Intrinsics.checkNotNull(context2);
                RequestBuilder<Drawable> m30load = Glide.with(context2).m30load((String) imageUiBean.getImage());
                ImageView imageView = (ImageView) this.h._$_findCachedViewById(R.id.activity_icon);
                Intrinsics.checkNotNull(imageView);
                m30load.into(imageView);
            } else {
                ((ImageView) this.h._$_findCachedViewById(R.id.activity_icon)).setImageResource(R.drawable.activity_running_icon);
            }
            ((TextView) this.h._$_findCachedViewById(R.id.activity_day)).setText(dayOfWeek + ' ' + time);
            ((TextView) this.h._$_findCachedViewById(R.id.date_text)).setText(String.valueOf(todayYesterdayString));
            ((TextView) this.h._$_findCachedViewById(R.id.time_text)).setText(String.valueOf(aMPMValue));
            View _$_findCachedViewById = this.h._$_findCachedViewById(i);
            final FragmentActivityDashboard fragmentActivityDashboard2 = this.h;
            _$_findCachedViewById.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.p
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentActivityDashboard$onViewCreated$8.b(FragmentActivityDashboard.this, entityWorkoutSession, view);
                }
            });
            FragmentActivity activity6 = this.h.getActivity();
            Intrinsics.checkNotNull(activity6);
            List<WorkoutUiBean> workoutUiBeans = workoutUtils2.getWorkoutUiBeans(activity6, entityWorkoutSession, WorkoutUiBeanProvider.ScreenType.ACTIVITY_HOME);
            if (workoutUiBeans == null || !(!workoutUiBeans.isEmpty())) {
                return;
            }
            FragmentActivityDashboard fragmentActivityDashboard3 = this.h;
            int i3 = R.id.activityBeanRecyclerView;
            FragmentActivity activity7 = this.h.getActivity();
            Intrinsics.checkNotNull(activity7);
            ((UntouchableRecyclerView) fragmentActivityDashboard3._$_findCachedViewById(i3)).setLayoutManager(new GridLayoutManager(activity7, 3));
            FragmentActivity activity8 = this.h.getActivity();
            Intrinsics.checkNotNull(activity8);
            ((UntouchableRecyclerView) this.h._$_findCachedViewById(i3)).setAdapter(new ActivityParamHistoryAdapter(activity8, workoutUiBeans, WorkoutUiBeanProvider.ScreenType.ACTIVITY_DETAIL));
            return;
        }
        ((CardView) this.h._$_findCachedViewById(R.id.view_activity_here_card)).setVisibility(0);
        this.h._$_findCachedViewById(R.id.recentActivityCard).setVisibility(8);
    }
}
