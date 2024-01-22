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
import com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerAdapter;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerLayoutManager;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.ScreenUtils;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityDistanceGoalSettings extends BaseActivity implements Observer<ArrayList<Integer>>, ViewModelListener {
    public ActivityDistanceGoalViewModel p;
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

    public static final void v(ActivityDistanceGoalSettings this$0, View view) {
        Integer goalId;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            this$0.showNoInternetMessage();
            return;
        }
        ArrayList<String> arrayList = this$0.s;
        Intrinsics.checkNotNull(arrayList);
        String str = arrayList.get(this$0.t);
        Intrinsics.checkNotNullExpressionValue(str, "dataset!![selectedDistanceGoalPosition]");
        float parseFloat = Float.parseFloat(str) * 1000;
        Integer num = this$0.q;
        ActivityDistanceGoalViewModel activityDistanceGoalViewModel = null;
        if (num != null && (num == null || num.intValue() != 0)) {
            if (this$0.p != null) {
                String string = this$0.getString(R.string.please_wait);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
                this$0.showProgressWithTitle(string);
                FitnessGoal fitnessGoal = this$0.r;
                if (fitnessGoal == null || (goalId = fitnessGoal.goalId) == null) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(goalId, "goalId");
                int intValue = goalId.intValue();
                ActivityDistanceGoalViewModel activityDistanceGoalViewModel2 = this$0.p;
                if (activityDistanceGoalViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    activityDistanceGoalViewModel = activityDistanceGoalViewModel2;
                }
                if (activityDistanceGoalViewModel != null) {
                    int i = (int) parseFloat;
                    activityDistanceGoalViewModel.updateDistanceGoal(intValue, new UpdateFitnessGoalRequest(1, Integer.valueOf(i)), i);
                    return;
                }
                return;
            }
            return;
        }
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        if (this$0.p != null) {
            String string2 = this$0.getString(R.string.please_wait);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_wait)");
            this$0.showProgressWithTitle(string2);
            ActivityDistanceGoalViewModel activityDistanceGoalViewModel3 = this$0.p;
            if (activityDistanceGoalViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                activityDistanceGoalViewModel = activityDistanceGoalViewModel3;
            }
            int i2 = (int) parseFloat;
            activityDistanceGoalViewModel.createDistanceGoal(new CreateFitnessGoalRequest(ActivityType.WALK, ActivityBaseUnit.METERS, 1, Integer.valueOf(i2), format), i2);
        }
    }

    public static final void w(ActivityDistanceGoalSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityDistanceGoalSettings this$0, View view) {
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
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.your_distance_goal));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDistanceGoalSettings.w(ActivityDistanceGoalSettings.this, view);
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
                float parseFloat = Float.parseFloat(str);
                PayUtils payUtils = PayUtils.INSTANCE;
                FitnessGoal fitnessGoal2 = this.r;
                Intrinsics.checkNotNull(fitnessGoal2);
                if (parseFloat == payUtils.roundOffDecimalTillOnePlace(((float) fitnessGoal2.getTarget().intValue()) / ((float) 1000))) {
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
        u();
        int i = R.id.sign_text;
        ((TextView) _$_findCachedViewById(i)).setText(getString(R.string.kilometers));
        TextView sign_text = (TextView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(sign_text, "sign_text");
        drawableStart(sign_text, R.drawable.ic_distance_home);
        ((ImageView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.ivGoal)).setBackgroundResource(R.drawable.step_goal_bg);
        ((Button) _$_findCachedViewById(R.id.btn_save)).setText(getString(R.string.update_distance_goal));
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
        FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this).getFitnessGoalDistanceData();
        if (fitnessGoalDistanceData != null) {
            this.q = fitnessGoalDistanceData.goalId;
        }
        String string = getResources().getString(R.string.distance_goal_settings_saved);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ance_goal_settings_saved)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDistanceGoalSettings.x(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void setDataset(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    public final void t() {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            showProgress();
            BleApiManager.getInstance(this).getBleApi().getData(new GetCalorieDistanceGoalRequest(GoalType.DISTANCE), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityDistanceGoalSettings$getGoalDataFromBand$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ActivityDistanceGoalSettings.this.dismissProgress();
                    ActivityDistanceGoalSettings activityDistanceGoalSettings = ActivityDistanceGoalSettings.this;
                    String string = activityDistanceGoalSettings.getResources().getString(R.string.failed_message);
                    Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.failed_message)");
                    activityDistanceGoalSettings.onDataFailure(string);
                    ActivityDistanceGoalSettings.this.loadDefaultValues();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    FitnessGoal fitnessGoal;
                    FitnessGoal fitnessGoal2;
                    FitnessGoal fitnessGoal3;
                    FitnessGoal fitnessGoal4;
                    FitnessGoal fitnessGoal5;
                    FitnessGoal fitnessGoal6;
                    PickerAdapter pickerAdapter;
                    FitnessGoal fitnessGoal7;
                    FitnessGoal fitnessGoal8;
                    FitnessGoal fitnessGoal9;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof GetCalorieDistanceGoalResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse");
                        GetCalorieDistanceGoalResponse getCalorieDistanceGoalResponse = (GetCalorieDistanceGoalResponse) responseData;
                        fitnessGoal = ActivityDistanceGoalSettings.this.r;
                        if (fitnessGoal == null) {
                            ActivityDistanceGoalSettings.this.r = new FitnessGoal();
                            fitnessGoal7 = ActivityDistanceGoalSettings.this.r;
                            Intrinsics.checkNotNull(fitnessGoal7);
                            fitnessGoal7.activityType = ActivityType.WALK.getActivityType();
                            fitnessGoal8 = ActivityDistanceGoalSettings.this.r;
                            Intrinsics.checkNotNull(fitnessGoal8);
                            fitnessGoal8.activityBaseUnit = ActivityBaseUnit.METERS.getActivityBaseUnit();
                            fitnessGoal9 = ActivityDistanceGoalSettings.this.r;
                            Intrinsics.checkNotNull(fitnessGoal9);
                            fitnessGoal9.targetedDays = 1;
                        }
                        fitnessGoal2 = ActivityDistanceGoalSettings.this.r;
                        if (fitnessGoal2 != null) {
                            fitnessGoal2.target = Integer.valueOf(getCalorieDistanceGoalResponse.getDistanceGoal());
                        }
                        UserDataManager userDataManager = UserDataManager.getInstance(ActivityDistanceGoalSettings.this);
                        fitnessGoal3 = ActivityDistanceGoalSettings.this.r;
                        userDataManager.saveFitnessGoalDistance(fitnessGoal3);
                        ActivityDistanceGoalSettings.this.dismissProgress();
                        fitnessGoal4 = ActivityDistanceGoalSettings.this.r;
                        if (fitnessGoal4 != null) {
                            ActivityDistanceGoalSettings activityDistanceGoalSettings = ActivityDistanceGoalSettings.this;
                            fitnessGoal5 = activityDistanceGoalSettings.r;
                            Intrinsics.checkNotNull(fitnessGoal5);
                            activityDistanceGoalSettings.q = fitnessGoal5.goalId;
                            ArrayList<String> dataset = ActivityDistanceGoalSettings.this.getDataset();
                            Intrinsics.checkNotNull(dataset);
                            int size = dataset.size();
                            for (int i = 0; i < size; i++) {
                                ArrayList<String> dataset2 = ActivityDistanceGoalSettings.this.getDataset();
                                Intrinsics.checkNotNull(dataset2);
                                String str = dataset2.get(i);
                                Intrinsics.checkNotNullExpressionValue(str, "dataset!![i]");
                                float parseFloat = Float.parseFloat(str);
                                PayUtils payUtils = PayUtils.INSTANCE;
                                fitnessGoal6 = ActivityDistanceGoalSettings.this.r;
                                Intrinsics.checkNotNull(fitnessGoal6);
                                if (parseFloat == payUtils.roundOffDecimalTillOnePlace(((float) fitnessGoal6.getTarget().intValue()) / ((float) 1000))) {
                                    pickerAdapter = ActivityDistanceGoalSettings.this.u;
                                    pickerAdapter.setSelectedItem(i, false);
                                    ((RecyclerView) ActivityDistanceGoalSettings.this._$_findCachedViewById(R.id.goalPicker).findViewById(R.id.rvHorizontalPicker)).smoothScrollToPosition(i);
                                }
                            }
                            return;
                        }
                        ((Button) ActivityDistanceGoalSettings.this._$_findCachedViewById(R.id.btn_save)).setEnabled(true);
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

    public final void u() {
        ActivityDistanceGoalViewModel activityDistanceGoalViewModel = (ActivityDistanceGoalViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityDistanceGoalViewModel.class);
        this.p = activityDistanceGoalViewModel;
        ActivityDistanceGoalViewModel activityDistanceGoalViewModel2 = null;
        if (activityDistanceGoalViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityDistanceGoalViewModel = null;
        }
        activityDistanceGoalViewModel.setMListener(this);
        this.r = UserDataManager.getInstance(this).getFitnessGoalDistanceData();
        ActivityDistanceGoalViewModel activityDistanceGoalViewModel3 = this.p;
        if (activityDistanceGoalViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityDistanceGoalViewModel3 = null;
        }
        activityDistanceGoalViewModel3.getDistanceList().observe(this, this);
        ActivityDistanceGoalViewModel activityDistanceGoalViewModel4 = this.p;
        if (activityDistanceGoalViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityDistanceGoalViewModel4 = null;
        }
        activityDistanceGoalViewModel4.loadDistanceList();
        ActivityDistanceGoalViewModel activityDistanceGoalViewModel5 = this.p;
        if (activityDistanceGoalViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityDistanceGoalViewModel2 = activityDistanceGoalViewModel5;
        }
        ArrayList<Integer> value = activityDistanceGoalViewModel2.getDistanceList().getValue();
        if (value != null) {
            for (Number number : value) {
                int intValue = number.intValue();
                ArrayList<String> arrayList = this.s;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(intValue / 1000)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                arrayList.add(format);
            }
        }
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isCalorieDistanceGoalGetSupported()) {
            t();
        }
        ScreenUtils.Companion companion = ScreenUtils.Companion;
        int screenWidth = (companion.getScreenWidth(this) / 2) - companion.dpToPx(this, 10);
        int i = R.id.goalPicker;
        View _$_findCachedViewById = _$_findCachedViewById(i);
        int i2 = R.id.rvHorizontalPicker;
        ((RecyclerView) _$_findCachedViewById.findViewById(i2)).setPadding(screenWidth, 0, screenWidth, 0);
        PickerAdapter pickerAdapter = this.u;
        pickerAdapter.setData(this.s);
        pickerAdapter.setCallback(new PickerAdapter.Callback() { // from class: com.coveiot.android.leonardo.more.activities.ActivityDistanceGoalSettings$initDistanceGoalPicker$2$1
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerAdapter.Callback
            public void onItemClicked(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                ActivityDistanceGoalSettings activityDistanceGoalSettings = ActivityDistanceGoalSettings.this;
                int i3 = R.id.goalPicker;
                View _$_findCachedViewById2 = activityDistanceGoalSettings._$_findCachedViewById(i3);
                int i4 = R.id.rvHorizontalPicker;
                ((RecyclerView) _$_findCachedViewById2.findViewById(i4)).smoothScrollToPosition(((RecyclerView) ActivityDistanceGoalSettings.this._$_findCachedViewById(i3).findViewById(i4)).getChildLayoutPosition(view));
            }
        });
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).setAdapter(pickerAdapter);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this);
        pickerLayoutManager.setCallback(new PickerLayoutManager.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityDistanceGoalSettings$initDistanceGoalPicker$3$1
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerLayoutManager.OnItemSelectedListener
            public void onItemSelected(int i3) {
                PickerAdapter pickerAdapter2;
                FitnessGoal fitnessGoal;
                int i4;
                FitnessGoal fitnessGoal2;
                int i5;
                FitnessGoal fitnessGoal3;
                Integer target;
                pickerAdapter2 = ActivityDistanceGoalSettings.this.u;
                pickerAdapter2.setSelectedItem(i3, false);
                ActivityDistanceGoalSettings.this.t = i3;
                fitnessGoal = ActivityDistanceGoalSettings.this.r;
                if (fitnessGoal != null) {
                    Button button = (Button) ActivityDistanceGoalSettings.this._$_findCachedViewById(R.id.btn_save);
                    ArrayList<String> dataset = ActivityDistanceGoalSettings.this.getDataset();
                    i4 = ActivityDistanceGoalSettings.this.t;
                    String str = dataset.get(i4);
                    Intrinsics.checkNotNullExpressionValue(str, "dataset[selectedDistanceGoalPosition]");
                    float parseFloat = Float.parseFloat(str);
                    PayUtils payUtils = PayUtils.INSTANCE;
                    fitnessGoal2 = ActivityDistanceGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal2);
                    float f = 1000;
                    button.setEnabled(!(parseFloat == payUtils.roundOffDecimalTillOnePlace(((float) fitnessGoal2.getTarget().intValue()) / f)));
                    ArrayList<String> dataset2 = ActivityDistanceGoalSettings.this.getDataset();
                    i5 = ActivityDistanceGoalSettings.this.t;
                    String str2 = dataset2.get(i5);
                    Intrinsics.checkNotNullExpressionValue(str2, "dataset[selectedDistanceGoalPosition]");
                    float parseFloat2 = Float.parseFloat(str2);
                    fitnessGoal3 = ActivityDistanceGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal3);
                    Intrinsics.checkNotNull(fitnessGoal3.getTarget());
                    if (parseFloat2 > payUtils.roundOffDecimalTillOnePlace(target.intValue() / f)) {
                        ActivityDistanceGoalSettings activityDistanceGoalSettings = ActivityDistanceGoalSettings.this;
                        TextView tvWhoa = (TextView) activityDistanceGoalSettings._$_findCachedViewById(R.id.tvWhoa);
                        Intrinsics.checkNotNullExpressionValue(tvWhoa, "tvWhoa");
                        activityDistanceGoalSettings.visible(tvWhoa);
                        return;
                    }
                    ActivityDistanceGoalSettings activityDistanceGoalSettings2 = ActivityDistanceGoalSettings.this;
                    TextView tvWhoa2 = (TextView) activityDistanceGoalSettings2._$_findCachedViewById(R.id.tvWhoa);
                    Intrinsics.checkNotNullExpressionValue(tvWhoa2, "tvWhoa");
                    activityDistanceGoalSettings2.gone(tvWhoa2);
                }
            }
        });
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).setLayoutManager(pickerLayoutManager);
        ((Button) _$_findCachedViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDistanceGoalSettings.v(ActivityDistanceGoalSettings.this, view);
            }
        });
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
                ArrayList<String> arrayList2 = this.s;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(intValue / 1000)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                arrayList2.add(format);
            }
        }
    }
}
