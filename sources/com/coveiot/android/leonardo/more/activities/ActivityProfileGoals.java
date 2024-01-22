package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityProfileGoalsBinding;
import com.coveiot.android.leonardo.more.adapters.ProfileGoalsHorizontalAdapter;
import com.coveiot.android.leonardo.more.models.GoalsModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityProfileLandingViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityProfileGoals extends BaseActivity implements ProfileGoalsHorizontalAdapter.OnGoalsItemClickListener {
    public ActivityProfileGoalsBinding p;
    @NotNull
    public ActivityResultLauncher<Intent> s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final ProfileGoalsHorizontalAdapter q = new ProfileGoalsHorizontalAdapter(this, this);
    @NotNull
    public ArrayList<GoalsModel> r = new ArrayList<>();

    public ActivityProfileGoals() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.more.activities.hg
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityProfileGoals.t(ActivityProfileGoals.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…etGoals()\n        }\n    }");
        this.s = registerForActivityResult;
    }

    public static final void s(ActivityProfileGoals this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void t(ActivityProfileGoals this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.u();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void initToolbar() {
        ActivityProfileGoalsBinding activityProfileGoalsBinding = this.p;
        ActivityProfileGoalsBinding activityProfileGoalsBinding2 = null;
        if (activityProfileGoalsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileGoalsBinding = null;
        }
        ((TextView) activityProfileGoalsBinding.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.my_goals));
        ActivityProfileGoalsBinding activityProfileGoalsBinding3 = this.p;
        if (activityProfileGoalsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileGoalsBinding2 = activityProfileGoalsBinding3;
        }
        ((TextView) activityProfileGoalsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.gg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfileGoals.s(ActivityProfileGoals.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityProfileGoalsBinding inflate = ActivityProfileGoalsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityProfileGoalsBinding activityProfileGoalsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActivityProfileLandingViewModel activityProfileLandingViewModel = (ActivityProfileLandingViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityProfileLandingViewModel.class);
        initToolbar();
        ActivityProfileGoalsBinding activityProfileGoalsBinding2 = this.p;
        if (activityProfileGoalsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileGoalsBinding = activityProfileGoalsBinding2;
        }
        activityProfileGoalsBinding.rvGoals.setLayoutManager(new LinearLayoutManager(this, 1, false));
        u();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ProfileGoalsHorizontalAdapter.OnGoalsItemClickListener
    public void onGoalsItemClicked(@NotNull GoalsModel goalsItem) {
        Intrinsics.checkNotNullParameter(goalsItem, "goalsItem");
        if (goalsItem.getGoalsName().equals(getString(R.string.steps_goal))) {
            this.s.launch(new Intent(this, ActivityStepsGoalSettings.class));
        } else if (goalsItem.getGoalsName().equals(getString(R.string.sleep_goal))) {
            this.s.launch(new Intent(this, ActivitySleepGoalSettings.class));
        } else if (goalsItem.getGoalsName().equals(getString(R.string.calories))) {
            this.s.launch(new Intent(this, ActivityCaloriesGoalSettings.class));
        } else if (goalsItem.getGoalsName().equals(getString(R.string.distance))) {
            this.s.launch(new Intent(this, ActivityDistanceGoalSettings.class));
        } else if (goalsItem.getGoalsName().equals(getString(R.string.walk_hour_goal))) {
            this.s.launch(new Intent(this, ActivityWalkHourGoalSettings.class));
        } else if (goalsItem.getGoalsName().equals(getString(R.string.exercise_min_goal))) {
            this.s.launch(new Intent(this, ActivityExerciseMinutesGoalSettings.class));
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        u();
    }

    public final void u() {
        Integer num;
        Integer num2;
        Drawable drawable = getDrawable(R.drawable.ic_steps_profile);
        Intrinsics.checkNotNull(drawable);
        String string = getString(R.string.steps_goal);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.steps_goal)");
        Integer num3 = UserDataManager.getInstance(this).getFitnessGoalStepsData().target;
        GoalsModel goalsModel = new GoalsModel(drawable, 0, string, num3 == null ? 10000 : num3.intValue());
        Drawable drawable2 = getDrawable(R.drawable.ic_sleep);
        Intrinsics.checkNotNull(drawable2);
        String string2 = getString(R.string.sleep_goal);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sleep_goal)");
        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this).getFitnessGoalSleepData();
        GoalsModel goalsModel2 = new GoalsModel(drawable2, 0, string2, ((fitnessGoalSleepData == null || (num2 = fitnessGoalSleepData.target) == null) ? 480 : num2.intValue()) / 60);
        ArrayList<GoalsModel> arrayList = new ArrayList<>();
        this.r = arrayList;
        arrayList.add(goalsModel);
        this.r.add(goalsModel2);
        ActivityProfileGoalsBinding activityProfileGoalsBinding = null;
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isCalorieGoalSupported()) {
            Drawable drawable3 = getDrawable(R.drawable.ic_calories);
            Intrinsics.checkNotNull(drawable3);
            String string3 = getString(R.string.calories);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.calories)");
            FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this).getFitnessGoalCaloriesData();
            Integer num4 = fitnessGoalCaloriesData != null ? fitnessGoalCaloriesData.target : null;
            this.r.add(new GoalsModel(drawable3, 0, string3, num4 == null ? 150 : num4.intValue()));
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isDistanceGoalSupported()) {
            Drawable drawable4 = getDrawable(R.drawable.ic_disatnce);
            Intrinsics.checkNotNull(drawable4);
            String string4 = getString(R.string.distance);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.distance)");
            FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this).getFitnessGoalDistanceData();
            Integer num5 = fitnessGoalDistanceData != null ? fitnessGoalDistanceData.target : null;
            this.r.add(new GoalsModel(drawable4, 0, string4, num5 == null ? 1000 : num5.intValue()));
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isExerciseMinutesGoalSupported()) {
            Drawable drawable5 = getDrawable(R.drawable.ic_exercise);
            Intrinsics.checkNotNull(drawable5);
            String string5 = getString(R.string.exercise_min_goal);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.exercise_min_goal)");
            FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this).getFitnessGoalExerciseMinutesData();
            Integer num6 = fitnessGoalExerciseMinutesData != null ? fitnessGoalExerciseMinutesData.target : null;
            this.r.add(new GoalsModel(drawable5, 0, string5, num6 == null ? 30 : num6.intValue()));
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isWalkingHourGoalSupported()) {
            Drawable drawable6 = getDrawable(R.drawable.ic_walk_hour_goal);
            Intrinsics.checkNotNull(drawable6);
            String string6 = getString(R.string.walk_hour_goal);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.walk_hour_goal)");
            FitnessGoal fitnessGoalWalkHourData = UserDataManager.getInstance(this).getFitnessGoalWalkHourData();
            this.r.add(new GoalsModel(drawable6, 0, string6, ((fitnessGoalWalkHourData == null || (num = fitnessGoalWalkHourData.target) == null) ? 720 : num.intValue()) / 60));
        }
        this.q.setProfileGoalsList(this.r);
        ActivityProfileGoalsBinding activityProfileGoalsBinding2 = this.p;
        if (activityProfileGoalsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileGoalsBinding = activityProfileGoalsBinding2;
        }
        activityProfileGoalsBinding.rvGoals.setAdapter(this.q);
    }
}
