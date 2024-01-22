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
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.GoalType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetCalorieDistanceGoalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel;
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
import com.coveiot.utils.utility.LogHelper;
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
public final class ActivityCaloriesGoalSettings extends BaseActivity implements Observer<ArrayList<Integer>>, ViewModelListener {
    public ActivityCalorieGoalViewModel p;
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
            if (!AppUtils.isNetConnected(ActivityCaloriesGoalSettings.this)) {
                ActivityCaloriesGoalSettings.this.showNoInternetMessage();
                return;
            }
            ArrayList<String> dataset = ActivityCaloriesGoalSettings.this.getDataset();
            Intrinsics.checkNotNull(dataset);
            String str = dataset.get(ActivityCaloriesGoalSettings.this.t);
            Intrinsics.checkNotNullExpressionValue(str, "dataset!![selectedCalorieGoalPosition]");
            int parseInt = Integer.parseInt(str);
            ActivityCalorieGoalViewModel activityCalorieGoalViewModel = null;
            if (ActivityCaloriesGoalSettings.this.q != null && ((num = ActivityCaloriesGoalSettings.this.q) == null || num.intValue() != 0)) {
                if (ActivityCaloriesGoalSettings.this.p != null) {
                    ActivityCaloriesGoalSettings activityCaloriesGoalSettings = ActivityCaloriesGoalSettings.this;
                    String string = activityCaloriesGoalSettings.getString(R.string.please_wait);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
                    activityCaloriesGoalSettings.showProgressWithTitle(string);
                    FitnessGoal fitnessGoal = ActivityCaloriesGoalSettings.this.r;
                    if (fitnessGoal != null) {
                        ActivityCaloriesGoalSettings activityCaloriesGoalSettings2 = ActivityCaloriesGoalSettings.this;
                        Integer goalId = fitnessGoal.goalId;
                        if (goalId != null) {
                            Intrinsics.checkNotNullExpressionValue(goalId, "goalId");
                            int intValue = goalId.intValue();
                            ActivityCalorieGoalViewModel activityCalorieGoalViewModel2 = activityCaloriesGoalSettings2.p;
                            if (activityCalorieGoalViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            } else {
                                activityCalorieGoalViewModel = activityCalorieGoalViewModel2;
                            }
                            if (activityCalorieGoalViewModel != null) {
                                activityCalorieGoalViewModel.updateCaloriesGoal(intValue, new UpdateFitnessGoalRequest(1, Integer.valueOf(parseInt)), parseInt);
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
            if (ActivityCaloriesGoalSettings.this.p != null) {
                ActivityCaloriesGoalSettings activityCaloriesGoalSettings3 = ActivityCaloriesGoalSettings.this;
                String string2 = activityCaloriesGoalSettings3.getString(R.string.please_wait);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_wait)");
                activityCaloriesGoalSettings3.showProgressWithTitle(string2);
                ActivityCalorieGoalViewModel activityCalorieGoalViewModel3 = ActivityCaloriesGoalSettings.this.p;
                if (activityCalorieGoalViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    activityCalorieGoalViewModel = activityCalorieGoalViewModel3;
                }
                activityCalorieGoalViewModel.createCalorieGoal(new CreateFitnessGoalRequest(ActivityType.ANY, ActivityBaseUnit.CALORIES, 1, Integer.valueOf(parseInt), format), parseInt);
            }
        }
    }

    public static final void u(ActivityCaloriesGoalSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityCaloriesGoalSettings this$0, View view) {
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
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.your_calories_goal));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCaloriesGoalSettings.u(ActivityCaloriesGoalSettings.this, view);
            }
        });
    }

    public final void loadDefaultValues() {
        FitnessGoal fitnessGoal = this.r;
        if (fitnessGoal != null) {
            Intrinsics.checkNotNull(fitnessGoal);
            this.q = fitnessGoal.goalId;
            ArrayList<String> arrayList = this.s;
            Intrinsics.checkNotNull(arrayList);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ArrayList<String> arrayList2 = this.s;
                Intrinsics.checkNotNull(arrayList2);
                String str = arrayList2.get(i);
                Intrinsics.checkNotNullExpressionValue(str, "dataset!![i]");
                int parseInt = Integer.parseInt(str);
                FitnessGoal fitnessGoal2 = this.r;
                Intrinsics.checkNotNull(fitnessGoal2);
                Integer target = fitnessGoal2.getTarget();
                if (target != null && parseInt == target.intValue()) {
                    this.u.setSelectedItem(i, false);
                    ((RecyclerView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.rvHorizontalPicker)).smoothScrollToPosition(i);
                }
            }
            return;
        }
        this.u.setSelectedItem(0, false);
        ((RecyclerView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.rvHorizontalPicker)).smoothScrollToPosition(0);
        ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(true);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_steps_goal_settings);
        initToolbar();
        t();
        int i = R.id.sign_text;
        ((TextView) _$_findCachedViewById(i)).setText(getString(R.string.calories));
        TextView sign_text = (TextView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(sign_text, "sign_text");
        drawableStart(sign_text, R.drawable.ic_calorie_pink);
        ((ImageView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.ivGoal)).setBackgroundResource(R.drawable.step_goal_bg);
        ((Button) _$_findCachedViewById(R.id.btn_save)).setText(getString(R.string.update_calories_goal));
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
        FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this).getFitnessGoalCaloriesData();
        if (fitnessGoalCaloriesData != null) {
            this.q = fitnessGoalCaloriesData.goalId;
        }
        String string = getResources().getString(R.string.calorie_goal_settings_saved);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…orie_goal_settings_saved)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCaloriesGoalSettings.v(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void s() {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            showProgress();
            BleApiManager.getInstance(this).getBleApi().getData(new GetCalorieDistanceGoalRequest(GoalType.CALORIE), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityCaloriesGoalSettings$getGoalDataFromBand$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ActivityCaloriesGoalSettings.this.dismissProgress();
                    ActivityCaloriesGoalSettings activityCaloriesGoalSettings = ActivityCaloriesGoalSettings.this;
                    String string = activityCaloriesGoalSettings.getResources().getString(R.string.failed_message);
                    Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.failed_message)");
                    activityCaloriesGoalSettings.onDataFailure(string);
                    ActivityCaloriesGoalSettings.this.loadDefaultValues();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    PickerAdapter pickerAdapter;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof GetCalorieDistanceGoalResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse");
                        GetCalorieDistanceGoalResponse getCalorieDistanceGoalResponse = (GetCalorieDistanceGoalResponse) responseData;
                        if (ActivityCaloriesGoalSettings.this.r == null) {
                            ActivityCaloriesGoalSettings.this.r = new FitnessGoal();
                            FitnessGoal fitnessGoal = ActivityCaloriesGoalSettings.this.r;
                            Intrinsics.checkNotNull(fitnessGoal);
                            fitnessGoal.activityType = ActivityType.ANY.getActivityType();
                            FitnessGoal fitnessGoal2 = ActivityCaloriesGoalSettings.this.r;
                            Intrinsics.checkNotNull(fitnessGoal2);
                            fitnessGoal2.activityBaseUnit = ActivityBaseUnit.CALORIES.getActivityBaseUnit();
                            FitnessGoal fitnessGoal3 = ActivityCaloriesGoalSettings.this.r;
                            Intrinsics.checkNotNull(fitnessGoal3);
                            fitnessGoal3.targetedDays = 1;
                        }
                        FitnessGoal fitnessGoal4 = ActivityCaloriesGoalSettings.this.r;
                        if (fitnessGoal4 != null) {
                            fitnessGoal4.target = Integer.valueOf(getCalorieDistanceGoalResponse.getCalorieGoal());
                        }
                        UserDataManager.getInstance(ActivityCaloriesGoalSettings.this).saveFitnessGoalCalories(ActivityCaloriesGoalSettings.this.r);
                        ActivityCaloriesGoalSettings.this.dismissProgress();
                        if (ActivityCaloriesGoalSettings.this.r != null) {
                            ActivityCaloriesGoalSettings activityCaloriesGoalSettings = ActivityCaloriesGoalSettings.this;
                            FitnessGoal fitnessGoal5 = activityCaloriesGoalSettings.r;
                            Intrinsics.checkNotNull(fitnessGoal5);
                            activityCaloriesGoalSettings.q = fitnessGoal5.goalId;
                            ArrayList<String> dataset = ActivityCaloriesGoalSettings.this.getDataset();
                            Intrinsics.checkNotNull(dataset);
                            int size = dataset.size();
                            for (int i = 0; i < size; i++) {
                                ArrayList<String> dataset2 = ActivityCaloriesGoalSettings.this.getDataset();
                                Intrinsics.checkNotNull(dataset2);
                                String str = dataset2.get(i);
                                Intrinsics.checkNotNullExpressionValue(str, "dataset!![i]");
                                int parseInt = Integer.parseInt(str);
                                FitnessGoal fitnessGoal6 = ActivityCaloriesGoalSettings.this.r;
                                Intrinsics.checkNotNull(fitnessGoal6);
                                Integer target = fitnessGoal6.getTarget();
                                if (target != null && parseInt == target.intValue()) {
                                    pickerAdapter = ActivityCaloriesGoalSettings.this.u;
                                    pickerAdapter.setSelectedItem(i, false);
                                    ((RecyclerView) ActivityCaloriesGoalSettings.this._$_findCachedViewById(R.id.goalPicker).findViewById(R.id.rvHorizontalPicker)).smoothScrollToPosition(i);
                                }
                            }
                            return;
                        }
                        ((Button) ActivityCaloriesGoalSettings.this._$_findCachedViewById(R.id.btn_save)).setEnabled(true);
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        String string = getResources().getString(R.string.device_disconnected);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.device_disconnected)");
        onDataFailure(string);
        loadDefaultValues();
    }

    public final void setDataset(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    public final void t() {
        ActivityCalorieGoalViewModel activityCalorieGoalViewModel = (ActivityCalorieGoalViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityCalorieGoalViewModel.class);
        this.p = activityCalorieGoalViewModel;
        ActivityCalorieGoalViewModel activityCalorieGoalViewModel2 = null;
        if (activityCalorieGoalViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityCalorieGoalViewModel = null;
        }
        activityCalorieGoalViewModel.setMListener(this);
        this.r = UserDataManager.getInstance(this).getFitnessGoalCaloriesData();
        ActivityCalorieGoalViewModel activityCalorieGoalViewModel3 = this.p;
        if (activityCalorieGoalViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityCalorieGoalViewModel3 = null;
        }
        activityCalorieGoalViewModel3.getCaloriesList().observe(this, this);
        ActivityCalorieGoalViewModel activityCalorieGoalViewModel4 = this.p;
        if (activityCalorieGoalViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityCalorieGoalViewModel4 = null;
        }
        activityCalorieGoalViewModel4.loadCaloriesList();
        ActivityCalorieGoalViewModel activityCalorieGoalViewModel5 = this.p;
        if (activityCalorieGoalViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityCalorieGoalViewModel2 = activityCalorieGoalViewModel5;
        }
        ArrayList<Integer> value = activityCalorieGoalViewModel2.getCaloriesList().getValue();
        if (value != null) {
            for (Number number : value) {
                this.s.add(String.valueOf(number.intValue()));
            }
        }
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isCalorieDistanceGoalGetSupported()) {
            s();
        }
        ScreenUtils.Companion companion = ScreenUtils.Companion;
        int screenWidth = (companion.getScreenWidth(this) / 2) - companion.dpToPx(this, 10);
        int i = R.id.goalPicker;
        View _$_findCachedViewById = _$_findCachedViewById(i);
        int i2 = R.id.rvHorizontalPicker;
        ((RecyclerView) _$_findCachedViewById.findViewById(i2)).setPadding(screenWidth, 0, screenWidth, 0);
        PickerAdapter pickerAdapter = this.u;
        pickerAdapter.setData(this.s);
        pickerAdapter.setCallback(new PickerAdapter.Callback() { // from class: com.coveiot.android.leonardo.more.activities.ActivityCaloriesGoalSettings$initCalorieGoalPicker$2$1
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerAdapter.Callback
            public void onItemClicked(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                ActivityCaloriesGoalSettings activityCaloriesGoalSettings = ActivityCaloriesGoalSettings.this;
                int i3 = R.id.goalPicker;
                View _$_findCachedViewById2 = activityCaloriesGoalSettings._$_findCachedViewById(i3);
                int i4 = R.id.rvHorizontalPicker;
                ((RecyclerView) _$_findCachedViewById2.findViewById(i4)).smoothScrollToPosition(((RecyclerView) ActivityCaloriesGoalSettings.this._$_findCachedViewById(i3).findViewById(i4)).getChildLayoutPosition(view));
            }
        });
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).setAdapter(pickerAdapter);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this);
        pickerLayoutManager.setCallback(new PickerLayoutManager.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityCaloriesGoalSettings$initCalorieGoalPicker$3$1
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerLayoutManager.OnItemSelectedListener
            public void onItemSelected(int i3) {
                PickerAdapter pickerAdapter2;
                pickerAdapter2 = ActivityCaloriesGoalSettings.this.u;
                boolean z = false;
                pickerAdapter2.setSelectedItem(i3, false);
                ActivityCaloriesGoalSettings.this.t = i3;
                if (ActivityCaloriesGoalSettings.this.r != null) {
                    Button button = (Button) ActivityCaloriesGoalSettings.this._$_findCachedViewById(R.id.btn_save);
                    String str = ActivityCaloriesGoalSettings.this.getDataset().get(ActivityCaloriesGoalSettings.this.t);
                    Intrinsics.checkNotNullExpressionValue(str, "dataset[selectedCalorieGoalPosition]");
                    int parseInt = Integer.parseInt(str);
                    FitnessGoal fitnessGoal = ActivityCaloriesGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal);
                    Integer target = fitnessGoal.getTarget();
                    button.setEnabled((target == null || parseInt != target.intValue()) ? true : true);
                    String str2 = ActivityCaloriesGoalSettings.this.getDataset().get(ActivityCaloriesGoalSettings.this.t);
                    Intrinsics.checkNotNullExpressionValue(str2, "dataset[selectedCalorieGoalPosition]");
                    int parseInt2 = Integer.parseInt(str2);
                    FitnessGoal fitnessGoal2 = ActivityCaloriesGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal2);
                    Integer target2 = fitnessGoal2.getTarget();
                    Intrinsics.checkNotNullExpressionValue(target2, "mCalorieGoalData!!.getTarget()");
                    if (parseInt2 > target2.intValue()) {
                        ActivityCaloriesGoalSettings activityCaloriesGoalSettings = ActivityCaloriesGoalSettings.this;
                        TextView tvWhoa = (TextView) activityCaloriesGoalSettings._$_findCachedViewById(R.id.tvWhoa);
                        Intrinsics.checkNotNullExpressionValue(tvWhoa, "tvWhoa");
                        activityCaloriesGoalSettings.visible(tvWhoa);
                        return;
                    }
                    ActivityCaloriesGoalSettings activityCaloriesGoalSettings2 = ActivityCaloriesGoalSettings.this;
                    TextView tvWhoa2 = (TextView) activityCaloriesGoalSettings2._$_findCachedViewById(R.id.tvWhoa);
                    Intrinsics.checkNotNullExpressionValue(tvWhoa2, "tvWhoa");
                    activityCaloriesGoalSettings2.gone(tvWhoa2);
                }
            }
        });
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).setLayoutManager(pickerLayoutManager);
        Button btn_save = (Button) _$_findCachedViewById(R.id.btn_save);
        Intrinsics.checkNotNullExpressionValue(btn_save, "btn_save");
        ViewUtilsKt.setSafeOnClickListener(btn_save, new a());
        loadDefaultValues();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<Integer> arrayList) {
        LogHelper.d("Calorie onChanged data", String.valueOf(arrayList));
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
