package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerAdapter;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerLayoutManager;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.ScreenUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalRequest;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySleepGoalSettings extends BaseActivity implements Observer<ArrayList<Integer>>, ViewModelListener {
    public ActivitySleepGoalViewModel p;
    @Nullable
    public FitnessGoal r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public Integer q = 0;
    @NotNull
    public ArrayList<String> s = new ArrayList<>();
    public int t = -1;
    @NotNull
    public final PickerAdapter u = new PickerAdapter();

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Integer num;
            Intrinsics.checkNotNullParameter(it, "it");
            if (AppUtils.isNetConnected(ActivitySleepGoalSettings.this)) {
                String str = ActivitySleepGoalSettings.this.getDataset().get(ActivitySleepGoalSettings.this.t);
                Intrinsics.checkNotNullExpressionValue(str, "dataset[selectedSleepGoalPosition]");
                int parseInt = Integer.parseInt(str) * 60;
                ActivitySleepGoalViewModel activitySleepGoalViewModel = null;
                if (ActivitySleepGoalSettings.this.q != null && ((num = ActivitySleepGoalSettings.this.q) == null || num.intValue() != 0)) {
                    if (ActivitySleepGoalSettings.this.p != null) {
                        ActivitySleepGoalSettings activitySleepGoalSettings = ActivitySleepGoalSettings.this;
                        String string = activitySleepGoalSettings.getString(R.string.please_wait);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
                        activitySleepGoalSettings.showProgressWithTitle(string);
                        FitnessGoal fitnessGoal = ActivitySleepGoalSettings.this.r;
                        if (fitnessGoal != null) {
                            ActivitySleepGoalSettings activitySleepGoalSettings2 = ActivitySleepGoalSettings.this;
                            Integer goalId = fitnessGoal.goalId;
                            if (goalId != null) {
                                Intrinsics.checkNotNullExpressionValue(goalId, "goalId");
                                int intValue = goalId.intValue();
                                ActivitySleepGoalViewModel activitySleepGoalViewModel2 = activitySleepGoalSettings2.p;
                                if (activitySleepGoalViewModel2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                } else {
                                    activitySleepGoalViewModel = activitySleepGoalViewModel2;
                                }
                                if (activitySleepGoalViewModel != null) {
                                    activitySleepGoalViewModel.updateSleepGoal(intValue, new UpdateFitnessGoalRequest(1, Integer.valueOf(parseInt)), parseInt);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
                if (ActivitySleepGoalSettings.this.p != null) {
                    ActivitySleepGoalSettings activitySleepGoalSettings3 = ActivitySleepGoalSettings.this;
                    String string2 = activitySleepGoalSettings3.getString(R.string.please_wait);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_wait)");
                    activitySleepGoalSettings3.showProgressWithTitle(string2);
                    ActivitySleepGoalViewModel activitySleepGoalViewModel3 = ActivitySleepGoalSettings.this.p;
                    if (activitySleepGoalViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        activitySleepGoalViewModel = activitySleepGoalViewModel3;
                    }
                    activitySleepGoalViewModel.createSleepGoal(new CreateFitnessGoalRequest(ActivityType.SLEEP, ActivityBaseUnit.MINUTES, 1, Integer.valueOf(parseInt), format), parseInt);
                    return;
                }
                return;
            }
            ActivitySleepGoalSettings.this.showNoInternetMessage();
        }
    }

    public static final void t(ActivitySleepGoalSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void u(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivitySleepGoalSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.setResult(-1, this$0.getIntent());
        this$0.finish();
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

    @NotNull
    public final ArrayList<String> getDataset() {
        return this.s;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.your_sleep_goal));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.si
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySleepGoalSettings.t(ActivitySleepGoalSettings.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_steps_goal_settings);
        initToolbar();
        s();
        int i = R.id.sign_text;
        ((TextView) _$_findCachedViewById(i)).setText(getString(R.string.hours));
        TextView sign_text = (TextView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(sign_text, "sign_text");
        drawableStart(sign_text, R.drawable.ic_time_blue_2);
        ((ImageView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.ivGoal)).setBackgroundResource(R.drawable.sleep_goal_bg);
        ((Button) _$_findCachedViewById(R.id.btn_save)).setText(getString(R.string.update_sleep_goal));
        String string = getString(R.string.woah_now_that_s_a_good_number);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.woah_now_that_s_a_good_number)");
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new StyleSpan(1), 0, 5, 17);
        ((TextView) _$_findCachedViewById(R.id.tvWhoa)).setText(spannableString);
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        dismissProgress();
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        dismissProgress();
        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this).getFitnessGoalSleepData();
        if (fitnessGoalSleepData != null) {
            this.q = fitnessGoalSleepData.goalId;
        }
        String string = getResources().getString(R.string.sleep_goal_settings_saved);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…leep_goal_settings_saved)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ti
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySleepGoalSettings.u(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void s() {
        ActivitySleepGoalViewModel activitySleepGoalViewModel = (ActivitySleepGoalViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivitySleepGoalViewModel.class);
        this.p = activitySleepGoalViewModel;
        ActivitySleepGoalViewModel activitySleepGoalViewModel2 = null;
        if (activitySleepGoalViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activitySleepGoalViewModel = null;
        }
        activitySleepGoalViewModel.setMListener(this);
        ActivitySleepGoalViewModel activitySleepGoalViewModel3 = this.p;
        if (activitySleepGoalViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activitySleepGoalViewModel3 = null;
        }
        activitySleepGoalViewModel3.getSleepHourList().observe(this, this);
        ActivitySleepGoalViewModel activitySleepGoalViewModel4 = this.p;
        if (activitySleepGoalViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activitySleepGoalViewModel4 = null;
        }
        activitySleepGoalViewModel4.load24Hours();
        ActivitySleepGoalViewModel activitySleepGoalViewModel5 = this.p;
        if (activitySleepGoalViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activitySleepGoalViewModel2 = activitySleepGoalViewModel5;
        }
        ArrayList<Integer> value = activitySleepGoalViewModel2.getSleepHourList().getValue();
        if (value != null) {
            for (Number number : value) {
                this.s.add(String.valueOf(number.intValue()));
            }
        }
        ScreenUtils.Companion companion = ScreenUtils.Companion;
        int screenWidth = (companion.getScreenWidth(this) / 2) - companion.dpToPx(this, 10);
        int i = R.id.goalPicker;
        View _$_findCachedViewById = _$_findCachedViewById(i);
        int i2 = R.id.rvHorizontalPicker;
        ((RecyclerView) _$_findCachedViewById.findViewById(i2)).setPadding(screenWidth, 0, screenWidth, 0);
        PickerAdapter pickerAdapter = this.u;
        pickerAdapter.setData(this.s);
        pickerAdapter.setIfSleepData(true);
        pickerAdapter.setCallback(new PickerAdapter.Callback() { // from class: com.coveiot.android.leonardo.more.activities.ActivitySleepGoalSettings$initSleepGoalPicker$2$1
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerAdapter.Callback
            public void onItemClicked(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                ActivitySleepGoalSettings activitySleepGoalSettings = ActivitySleepGoalSettings.this;
                int i3 = R.id.goalPicker;
                View _$_findCachedViewById2 = activitySleepGoalSettings._$_findCachedViewById(i3);
                int i4 = R.id.rvHorizontalPicker;
                ((RecyclerView) _$_findCachedViewById2.findViewById(i4)).smoothScrollToPosition(((RecyclerView) ActivitySleepGoalSettings.this._$_findCachedViewById(i3).findViewById(i4)).getChildLayoutPosition(view));
            }
        });
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).setAdapter(pickerAdapter);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this);
        pickerLayoutManager.setCallback(new PickerLayoutManager.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivitySleepGoalSettings$initSleepGoalPicker$3$1
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerLayoutManager.OnItemSelectedListener
            public void onItemSelected(int i3) {
                PickerAdapter pickerAdapter2;
                pickerAdapter2 = ActivitySleepGoalSettings.this.u;
                pickerAdapter2.setSelectedItem(i3, false);
                ActivitySleepGoalSettings.this.t = i3;
                if (ActivitySleepGoalSettings.this.r != null) {
                    Button button = (Button) ActivitySleepGoalSettings.this._$_findCachedViewById(R.id.btn_save);
                    String str = ActivitySleepGoalSettings.this.getDataset().get(ActivitySleepGoalSettings.this.t);
                    Intrinsics.checkNotNullExpressionValue(str, "dataset[selectedSleepGoalPosition]");
                    int parseInt = Integer.parseInt(str);
                    FitnessGoal fitnessGoal = ActivitySleepGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal);
                    button.setEnabled(parseInt != fitnessGoal.getTarget().intValue() / 60);
                    String str2 = ActivitySleepGoalSettings.this.getDataset().get(ActivitySleepGoalSettings.this.t);
                    Intrinsics.checkNotNullExpressionValue(str2, "dataset[selectedSleepGoalPosition]");
                    int parseInt2 = Integer.parseInt(str2);
                    FitnessGoal fitnessGoal2 = ActivitySleepGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal2);
                    if (parseInt2 > fitnessGoal2.getTarget().intValue() / 60) {
                        ActivitySleepGoalSettings activitySleepGoalSettings = ActivitySleepGoalSettings.this;
                        TextView tvWhoa = (TextView) activitySleepGoalSettings._$_findCachedViewById(R.id.tvWhoa);
                        Intrinsics.checkNotNullExpressionValue(tvWhoa, "tvWhoa");
                        activitySleepGoalSettings.visible(tvWhoa);
                        return;
                    }
                    ActivitySleepGoalSettings activitySleepGoalSettings2 = ActivitySleepGoalSettings.this;
                    TextView tvWhoa2 = (TextView) activitySleepGoalSettings2._$_findCachedViewById(R.id.tvWhoa);
                    Intrinsics.checkNotNullExpressionValue(tvWhoa2, "tvWhoa");
                    activitySleepGoalSettings2.gone(tvWhoa2);
                }
            }
        });
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).setLayoutManager(pickerLayoutManager);
        int i3 = R.id.btn_save;
        Button btn_save = (Button) _$_findCachedViewById(i3);
        Intrinsics.checkNotNullExpressionValue(btn_save, "btn_save");
        ViewUtilsKt.setSafeOnClickListener(btn_save, new a());
        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this).getFitnessGoalSleepData();
        this.r = fitnessGoalSleepData;
        if (fitnessGoalSleepData != null) {
            Intrinsics.checkNotNull(fitnessGoalSleepData);
            this.q = fitnessGoalSleepData.goalId;
            int size = this.s.size();
            for (int i4 = 0; i4 < size; i4++) {
                String str = this.s.get(i4);
                Intrinsics.checkNotNullExpressionValue(str, "dataset[i]");
                int parseInt = Integer.parseInt(str);
                FitnessGoal fitnessGoal = this.r;
                Intrinsics.checkNotNull(fitnessGoal);
                if (parseInt == fitnessGoal.getTarget().intValue() / 60) {
                    this.u.setSelectedItem(i4, true);
                    ((RecyclerView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.rvHorizontalPicker)).smoothScrollToPosition(i4);
                }
            }
            return;
        }
        this.u.setSelectedItem(3, false);
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).smoothScrollToPosition(3);
        ((Button) _$_findCachedViewById(i3)).setEnabled(true);
    }

    public final void setDataset(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<Integer> arrayList) {
        this.s.clear();
        if (arrayList != null) {
            for (Number number : arrayList) {
                int intValue = number.intValue();
                String.valueOf(intValue);
                this.s.add(String.valueOf(intValue));
            }
        }
    }
}
