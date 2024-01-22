package com.coveiot.android.activitymodes;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.databinding.ActivityAutoRecogSettingsWithOnekBindingImpl;
import com.coveiot.android.activitymodes.databinding.ActivityHistoryBeanLayoutBindingImpl;
import com.coveiot.android.activitymodes.databinding.ActivityWorkoutVideosBindingImpl;
import com.coveiot.android.activitymodes.databinding.ActivityWorkoutVideosItemBindingImpl;
import com.coveiot.android.activitymodes.databinding.ActivityYoutubePlayerNewBindingImpl;
import com.coveiot.android.activitymodes.databinding.CustomTabWorkoutVideosBindingImpl;
import com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanHistoryBindingImpl;
import com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBindingImpl;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessDaysLayoutBindingImpl;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessPlanHistoryLayoutBindingImpl;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessSelectedDayGoalLayoutBindingImpl;
import com.coveiot.android.activitymodes.databinding.ListItemFitnessWeekLayoutBindingImpl;
import com.coveiot.android.activitymodes.databinding.WorkoutVideosFeedbackItemBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes2.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYAUTORECOGSETTINGSWITHONEK = 1;
    private static final int LAYOUT_ACTIVITYHISTORYBEANLAYOUT = 2;
    private static final int LAYOUT_ACTIVITYWORKOUTVIDEOS = 3;
    private static final int LAYOUT_ACTIVITYWORKOUTVIDEOSITEM = 4;
    private static final int LAYOUT_ACTIVITYYOUTUBEPLAYERNEW = 5;
    private static final int LAYOUT_CUSTOMTABWORKOUTVIDEOS = 6;
    private static final int LAYOUT_FRAGMENTFITNESSPLANHISTORY = 7;
    private static final int LAYOUT_FRAGMENTFITNESSPLANWEEKDAYINFO = 8;
    private static final int LAYOUT_LISTITEMFITNESSDAYSLAYOUT = 9;
    private static final int LAYOUT_LISTITEMFITNESSPLANHISTORYLAYOUT = 10;
    private static final int LAYOUT_LISTITEMFITNESSSELECTEDDAYGOALLAYOUT = 11;
    private static final int LAYOUT_LISTITEMFITNESSWEEKLAYOUT = 12;
    private static final int LAYOUT_WORKOUTVIDEOSFEEDBACKITEM = 13;

    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f2700a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(42);
            f2700a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "activitiesCompleted");
            sparseArray.put(2, "bindingDataModel1");
            sparseArray.put(3, "completedCalories");
            sparseArray.put(4, "completedDistance");
            sparseArray.put(5, "dayCount");
            sparseArray.put(6, "dayProgress");
            sparseArray.put(7, "dayProgressMax");
            sparseArray.put(8, "distanceCovered");
            sparseArray.put(9, "firstCardType");
            sparseArray.put(10, "fitnessType");
            sparseArray.put(11, "futureDayInfo");
            sparseArray.put(12, "goalCategory");
            sparseArray.put(13, "healthInfo");
            sparseArray.put(14, "isDataAvailable");
            sparseArray.put(15, "isFirstCardDataAvailable");
            sparseArray.put(16, "isFutureDay");
            sparseArray.put(17, "isHistoryPlan");
            sparseArray.put(18, "isPlanCompleted");
            sparseArray.put(19, "isPlanStartsTomorrow");
            sparseArray.put(20, "isProgressFull");
            sparseArray.put(21, "isRestDay");
            sparseArray.put(22, "planBg");
            sparseArray.put(23, "planHeader");
            sparseArray.put(24, "planHistoryData");
            sparseArray.put(25, "planTitle");
            sparseArray.put(26, "progress");
            sparseArray.put(27, "progressText");
            sparseArray.put(28, "showAlexaConnect");
            sparseArray.put(29, "showFitnessPlan");
            sparseArray.put(30, "showSOSSettings");
            sparseArray.put(31, "showSportScores");
            sparseArray.put(32, "showTapAndPay");
            sparseArray.put(33, "showWellnessCrew");
            sparseArray.put(34, "stepsDataModel");
            sparseArray.put(35, "stepsGoal");
            sparseArray.put(36, "tipsData");
            sparseArray.put(37, "title");
            sparseArray.put(38, "totalActivities");
            sparseArray.put(39, "totalActivitiesAndDistance");
            sparseArray.put(40, "watchName");
            sparseArray.put(41, "weekCount");
        }
    }

    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f2701a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(13);
            f2701a = hashMap;
            hashMap.put("layout/activity_auto_recog_settings_with_onek_0", Integer.valueOf(R.layout.activity_auto_recog_settings_with_onek));
            hashMap.put("layout/activity_history_bean_layout_0", Integer.valueOf(R.layout.activity_history_bean_layout));
            hashMap.put("layout/activity_workout_videos_0", Integer.valueOf(R.layout.activity_workout_videos));
            hashMap.put("layout/activity_workout_videos_item_0", Integer.valueOf(R.layout.activity_workout_videos_item));
            hashMap.put("layout/activity_youtube_player_new_0", Integer.valueOf(R.layout.activity_youtube_player_new));
            hashMap.put("layout/custom_tab_workout_videos_0", Integer.valueOf(R.layout.custom_tab_workout_videos));
            hashMap.put("layout/fragment_fitness_plan_history_0", Integer.valueOf(R.layout.fragment_fitness_plan_history));
            hashMap.put("layout/fragment_fitness_plan_week_day_info_0", Integer.valueOf(R.layout.fragment_fitness_plan_week_day_info));
            hashMap.put("layout/list_item_fitness_days_layout_0", Integer.valueOf(R.layout.list_item_fitness_days_layout));
            hashMap.put("layout/list_item_fitness_plan_history_layout_0", Integer.valueOf(R.layout.list_item_fitness_plan_history_layout));
            hashMap.put("layout/list_item_fitness_selected_day_goal_layout_0", Integer.valueOf(R.layout.list_item_fitness_selected_day_goal_layout));
            hashMap.put("layout/list_item_fitness_week_layout_0", Integer.valueOf(R.layout.list_item_fitness_week_layout));
            hashMap.put("layout/workout_videos_feedback_item_0", Integer.valueOf(R.layout.workout_videos_feedback_item));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(13);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_auto_recog_settings_with_onek, 1);
        sparseIntArray.put(R.layout.activity_history_bean_layout, 2);
        sparseIntArray.put(R.layout.activity_workout_videos, 3);
        sparseIntArray.put(R.layout.activity_workout_videos_item, 4);
        sparseIntArray.put(R.layout.activity_youtube_player_new, 5);
        sparseIntArray.put(R.layout.custom_tab_workout_videos, 6);
        sparseIntArray.put(R.layout.fragment_fitness_plan_history, 7);
        sparseIntArray.put(R.layout.fragment_fitness_plan_week_day_info, 8);
        sparseIntArray.put(R.layout.list_item_fitness_days_layout, 9);
        sparseIntArray.put(R.layout.list_item_fitness_plan_history_layout, 10);
        sparseIntArray.put(R.layout.list_item_fitness_selected_day_goal_layout, 11);
        sparseIntArray.put(R.layout.list_item_fitness_week_layout, 12);
        sparseIntArray.put(R.layout.workout_videos_feedback_item, 13);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f2700a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_auto_recog_settings_with_onek_0".equals(tag)) {
                            return new ActivityAutoRecogSettingsWithOnekBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_auto_recog_settings_with_onek is invalid. Received: " + tag);
                    case 2:
                        if ("layout/activity_history_bean_layout_0".equals(tag)) {
                            return new ActivityHistoryBeanLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_history_bean_layout is invalid. Received: " + tag);
                    case 3:
                        if ("layout/activity_workout_videos_0".equals(tag)) {
                            return new ActivityWorkoutVideosBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_workout_videos is invalid. Received: " + tag);
                    case 4:
                        if ("layout/activity_workout_videos_item_0".equals(tag)) {
                            return new ActivityWorkoutVideosItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_workout_videos_item is invalid. Received: " + tag);
                    case 5:
                        if ("layout/activity_youtube_player_new_0".equals(tag)) {
                            return new ActivityYoutubePlayerNewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_youtube_player_new is invalid. Received: " + tag);
                    case 6:
                        if ("layout/custom_tab_workout_videos_0".equals(tag)) {
                            return new CustomTabWorkoutVideosBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for custom_tab_workout_videos is invalid. Received: " + tag);
                    case 7:
                        if ("layout/fragment_fitness_plan_history_0".equals(tag)) {
                            return new FragmentFitnessPlanHistoryBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_fitness_plan_history is invalid. Received: " + tag);
                    case 8:
                        if ("layout/fragment_fitness_plan_week_day_info_0".equals(tag)) {
                            return new FragmentFitnessPlanWeekDayInfoBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_fitness_plan_week_day_info is invalid. Received: " + tag);
                    case 9:
                        if ("layout/list_item_fitness_days_layout_0".equals(tag)) {
                            return new ListItemFitnessDaysLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_fitness_days_layout is invalid. Received: " + tag);
                    case 10:
                        if ("layout/list_item_fitness_plan_history_layout_0".equals(tag)) {
                            return new ListItemFitnessPlanHistoryLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_fitness_plan_history_layout is invalid. Received: " + tag);
                    case 11:
                        if ("layout/list_item_fitness_selected_day_goal_layout_0".equals(tag)) {
                            return new ListItemFitnessSelectedDayGoalLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_fitness_selected_day_goal_layout is invalid. Received: " + tag);
                    case 12:
                        if ("layout/list_item_fitness_week_layout_0".equals(tag)) {
                            return new ListItemFitnessWeekLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_fitness_week_layout is invalid. Received: " + tag);
                    case 13:
                        if ("layout/workout_videos_feedback_item_0".equals(tag)) {
                            return new WorkoutVideosFeedbackItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for workout_videos_feedback_item is invalid. Received: " + tag);
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f2701a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
