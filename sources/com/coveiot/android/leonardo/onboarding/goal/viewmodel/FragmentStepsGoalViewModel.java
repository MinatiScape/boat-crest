package com.coveiot.android.leonardo.onboarding.goal.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetGoalTargetRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.PreferenceNames;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveFitness;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalResponse;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.GoalSettingsData;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentStepsGoalViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5213a;
    @NotNull
    public UserDataManager b;
    @NotNull
    public GoalSettingsData c;
    public final String d;
    @NotNull
    public final MutableLiveData<ArrayList<String>> e;
    public ViewModelListener mListener;

    public FragmentStepsGoalViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5213a = context;
        UserDataManager userDataManager = UserDataManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(userDataManager, "getInstance(context)");
        this.b = userDataManager;
        this.d = FragmentStepsGoalViewModel.class.getSimpleName();
        GoalSettingsData goalSttingsData = this.b.getGoalSttingsData();
        Intrinsics.checkNotNullExpressionValue(goalSttingsData, "userDataManager.goalSttingsData");
        this.c = goalSttingsData;
        this.e = new MutableLiveData<>();
    }

    public final void c(String str) {
        Context context = this.f5213a;
        Intrinsics.checkNotNull(context);
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(context).getFitnessGoalStepsData();
        if (fitnessGoalStepsData != null) {
            Integer num = fitnessGoalStepsData.target;
            int parseInt = Integer.parseInt(str);
            if (num != null && num.intValue() == parseInt) {
                return;
            }
            PreferenceManager.savePreference(this.f5213a, PreferenceNames.IS_STEP_GOAL_CHANGED, Boolean.TRUE);
        }
    }

    public final void createFitnessGoal(@NotNull CreateFitnessGoalRequest reqModel) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5213a)) {
            CoveFitness.createFitnessGoal(reqModel, new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel$createFitnessGoal$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ViewModelListener mListener = FragmentStepsGoalViewModel.this.getMListener();
                    String string = FragmentStepsGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                    mListener.onDataFailure(string);
                    LogHelper.e(FragmentStepsGoalViewModel.this.getTAG(), FragmentStepsGoalViewModel.this.getContext().getString(R.string.somethings_went_wrong));
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessGoalResponse createFitnessGoalResponse) {
                    if (createFitnessGoalResponse != null) {
                        if (createFitnessGoalResponse.fitnessGoal.activityType.equals(ActivityType.WALK.getActivityType())) {
                            FitnessGoal fitnessGoal = new FitnessGoal();
                            com.coveiot.coveaccess.fitness.common.FitnessGoal fitnessGoal2 = createFitnessGoalResponse.fitnessGoal;
                            fitnessGoal.target = fitnessGoal2.target;
                            fitnessGoal.goalId = fitnessGoal2.goalId;
                            fitnessGoal.activityBaseUnit = fitnessGoal2.activityBaseUnit;
                            fitnessGoal.activityType = fitnessGoal2.activityType;
                            fitnessGoal.createdDate = fitnessGoal2.createdDate;
                            fitnessGoal.modifiedDate = fitnessGoal2.modifiedDate;
                            fitnessGoal.targetAchieved = fitnessGoal2.targetAchieved.toString();
                            UserDataManager.getInstance(FragmentStepsGoalViewModel.this.getContext()).saveFitnessGoalSteps(fitnessGoal);
                            GoalSettingsData goalSettingsData = GoalSettingsData.getInstance();
                            Integer num = fitnessGoal2.target;
                            Intrinsics.checkNotNullExpressionValue(num, "datum.target");
                            goalSettingsData.setSteps(num.intValue());
                            UserDataManager.getInstance(FragmentStepsGoalViewModel.this.getContext()).saveGoalSettings(goalSettingsData);
                        }
                        FragmentStepsGoalViewModel fragmentStepsGoalViewModel = FragmentStepsGoalViewModel.this;
                        if (fragmentStepsGoalViewModel.mListener != null) {
                            fragmentStepsGoalViewModel.getMListener().onSuccess();
                        }
                    }
                }
            });
            return;
        }
        ViewModelListener mListener = getMListener();
        String string = this.f5213a.getResources().getString(R.string.noconnection);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.noconnection)");
        mListener.onDataFailure(string);
        LogHelper.e(this.d, this.f5213a.getString(R.string.noconnection));
    }

    public final void d(Integer num, String str) {
        if (num != null && num.intValue() != 0) {
            updateFitnessGoal(num.intValue(), new UpdateFitnessGoalRequest(1, Integer.valueOf(Integer.parseInt(str)), AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(Calendar.getInstance().getTimeInMillis()))));
            return;
        }
        createFitnessGoal(new CreateFitnessGoalRequest(ActivityType.WALK, ActivityBaseUnit.STEPS, 1, Integer.valueOf(Integer.parseInt(str)), AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(Calendar.getInstance().getTimeInMillis()))));
    }

    public final void e(final Integer num, final String str) {
        SetFitnessInfoRequest builder;
        ProfileData userDetails = SessionManager.getInstance(this.f5213a).getUserDetails();
        double d = ProfileRepository.getInstance().getLatestProfileData(this.f5213a) != null ? ProfileRepository.getInstance().getLatestProfileData(this.f5213a).walkStrideLength : -1.0d;
        if (d == -1.0d) {
            String height = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
            d = Double.parseDouble(height) * 0.413d;
        }
        int i = 30;
        if (TextUtils.isEmpty(userDetails.getGender())) {
            String height2 = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height2, "profileData.height");
            int parseInt = Integer.parseInt(height2);
            String weight = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
            SetFitnessInfoRequest.Builder builder2 = new SetFitnessInfoRequest.Builder(parseInt, Double.parseDouble(weight));
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.f5213a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
            SetFitnessInfoRequest.Builder unitType = builder2.setUnitType(isDistanceUnitInMile.booleanValue() ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC);
            if (userDetails.getDob() != null) {
                PayUtils payUtils = PayUtils.INSTANCE;
                String dob = userDetails.getDob();
                Intrinsics.checkNotNullExpressionValue(dob, "profileData.dob");
                i = payUtils.getAge(dob);
            }
            builder = unitType.setAge(i).setStride(d).builder();
            Intrinsics.checkNotNullExpressionValue(builder, "Builder(\n               …lkStrideLength).builder()");
        } else {
            String height3 = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height3, "profileData.height");
            int parseInt2 = Integer.parseInt(height3);
            String weight2 = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight2, "profileData.weight");
            SetFitnessInfoRequest.Builder stride = new SetFitnessInfoRequest.Builder(parseInt2, Double.parseDouble(weight2)).setStride(d);
            Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this.f5213a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(context).isDistanceUnitInMile");
            SetFitnessInfoRequest.Builder male = stride.setUnitType(isDistanceUnitInMile2.booleanValue() ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC).setMale(m.equals(userDetails.getGender(), AppConstants.MALE.getValue(), true));
            if (userDetails.getDob() != null) {
                PayUtils payUtils2 = PayUtils.INSTANCE;
                String dob2 = userDetails.getDob();
                Intrinsics.checkNotNullExpressionValue(dob2, "profileData.dob");
                i = payUtils2.getAge(dob2);
            }
            builder = male.setAge(i).builder();
            Intrinsics.checkNotNullExpressionValue(builder, "Builder(\n               …               .builder()");
        }
        if (BleApiManager.getInstance(this.f5213a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.f5213a).getBleApi().setUserSettings(builder, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel$updateHeightWeightToBand$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(FragmentStepsGoalViewModel.this.getTAG(), "setFitnessInfoRequest Failure");
                    ViewModelListener mListener = FragmentStepsGoalViewModel.this.getMListener();
                    String string = FragmentStepsGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                    mListener.onDataFailure(string);
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(FragmentStepsGoalViewModel.this.getTAG(), "setFitnessInfoRequest Success");
                    FragmentStepsGoalViewModel.this.d(num, str);
                }
            });
        } else {
            d(num, str);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f5213a;
    }

    @NotNull
    public final GoalSettingsData getGoalSettingsData() {
        return this.c;
    }

    @NotNull
    public final ViewModelListener getMListener() {
        ViewModelListener viewModelListener = this.mListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    @NotNull
    public final MutableLiveData<ArrayList<String>> getStepsGoalList() {
        return this.e;
    }

    public final String getTAG() {
        return this.d;
    }

    @NotNull
    public final UserDataManager getUserDataManager() {
        return this.b;
    }

    public final void onGoalSelected(@NotNull final String selectedItem, @Nullable final Integer num) {
        Intrinsics.checkNotNullParameter(selectedItem, "selectedItem");
        c(selectedItem);
        StepsTargetRequest setWalkTargetReq = new StepsTargetRequest.Builder().setTarget(Integer.parseInt(selectedItem)).build();
        if (BleApiManager.getInstance(this.f5213a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (BleApiManager.getInstance(this.f5213a).getBleApi().getDeviceSupportedFeatures().isStepGoalSupported()) {
                if (BleApiManager.getInstance(this.f5213a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                    int parseInt = Integer.parseInt(AppConstants.DEFAULT_DISTANCE_GOAL.getValue());
                    int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_CALORIE_GOAL.getValue());
                    int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_EXCERSIE_DURATION_GOAL.getValue());
                    int parseInt4 = Integer.parseInt(AppConstants.DEFAULT_SLEEP_GOAL.getValue());
                    FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this.f5213a).getFitnessGoalDistanceData();
                    if (fitnessGoalDistanceData != null) {
                        Integer num2 = fitnessGoalDistanceData.target;
                        Intrinsics.checkNotNullExpressionValue(num2, "mDistanceGoalData.target");
                        parseInt = num2.intValue();
                    }
                    FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this.f5213a).getFitnessGoalCaloriesData();
                    if (fitnessGoalCaloriesData != null) {
                        Integer num3 = fitnessGoalCaloriesData.target;
                        Intrinsics.checkNotNullExpressionValue(num3, "mCalorieGoalData.target");
                        parseInt2 = num3.intValue();
                    }
                    FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this.f5213a).getFitnessGoalSleepData();
                    if (fitnessGoalSleepData != null) {
                        Integer num4 = fitnessGoalSleepData.target;
                        Intrinsics.checkNotNullExpressionValue(num4, "mSleepGoalData.target");
                        parseInt4 = num4.intValue();
                    }
                    FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this.f5213a).getFitnessGoalExerciseMinutesData();
                    if (fitnessGoalExerciseMinutesData != null) {
                        Integer num5 = fitnessGoalExerciseMinutesData.target;
                        Intrinsics.checkNotNullExpressionValue(num5, "mExcerciseGoalData.target");
                        parseInt3 = num5.intValue();
                    }
                    SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(Integer.parseInt(selectedItem), parseInt, parseInt2);
                    setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(parseInt3));
                    setGoalTargetRequest.setSleepTarget(Integer.valueOf(parseInt4));
                    BleApiManager.getInstance(this.f5213a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel$onGoalSelected$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = FragmentStepsGoalViewModel.this.getMListener();
                            String string = FragmentStepsGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            FragmentStepsGoalViewModel.this.e(num, selectedItem);
                            ProfileRepository.getInstance().updateStepsTarget(FragmentStepsGoalViewModel.this.getContext(), Integer.parseInt(selectedItem));
                        }
                    });
                    return;
                }
                BleApi bleApi = BleApiManager.getInstance(this.f5213a).getBleApi();
                Intrinsics.checkNotNullExpressionValue(setWalkTargetReq, "setWalkTargetReq");
                bleApi.setUserSettings(setWalkTargetReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel$onGoalSelected$2
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ViewModelListener mListener = FragmentStepsGoalViewModel.this.getMListener();
                        String string = FragmentStepsGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                        mListener.onDataFailure(string);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        FragmentStepsGoalViewModel.this.e(num, selectedItem);
                        ProfileRepository.getInstance().updateStepsTarget(FragmentStepsGoalViewModel.this.getContext(), Integer.parseInt(selectedItem));
                    }
                });
                return;
            }
            e(num, selectedItem);
            ProfileRepository.getInstance().updateStepsTarget(this.f5213a, Integer.parseInt(selectedItem));
            return;
        }
        ProfileRepository.getInstance().updateStepsTarget(this.f5213a, Integer.parseInt(selectedItem));
        d(num, selectedItem);
    }

    public final void prepareStepsGoalData() {
        if (this.e.getValue() == null) {
            this.e.setValue(new ArrayList<>());
        }
        int i = 1000;
        if (DeviceUtils.Companion.isSmaJieieDevice(this.f5213a)) {
            int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(1000, 99000, 1000);
            if (1000 <= progressionLastElement) {
                while (true) {
                    ArrayList<String> value = this.e.getValue();
                    Intrinsics.checkNotNull(value);
                    value.add(String.valueOf(i));
                    if (i == progressionLastElement) {
                        break;
                    }
                    i += 1000;
                }
            }
        } else {
            int progressionLastElement2 = ProgressionUtilKt.getProgressionLastElement(1000, 30000, 1000);
            if (1000 <= progressionLastElement2) {
                while (true) {
                    ArrayList<String> value2 = this.e.getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.add(String.valueOf(i));
                    if (i == progressionLastElement2) {
                        break;
                    }
                    i += 1000;
                }
            }
        }
        MutableLiveData<ArrayList<String>> mutableLiveData = this.e;
        mutableLiveData.postValue(mutableLiveData.getValue());
    }

    public final void setGoalSettingsData(@NotNull GoalSettingsData goalSettingsData) {
        Intrinsics.checkNotNullParameter(goalSettingsData, "<set-?>");
        this.c = goalSettingsData;
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }

    public final void setUserDataManager(@NotNull UserDataManager userDataManager) {
        Intrinsics.checkNotNullParameter(userDataManager, "<set-?>");
        this.b = userDataManager;
    }

    public final void updateFitnessGoal(int i, @NotNull UpdateFitnessGoalRequest reqModel) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5213a)) {
            CoveFitness.updateFitnessGoal(Integer.valueOf(i), reqModel, new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel$updateFitnessGoal$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(FragmentStepsGoalViewModel.this.getTAG(), FragmentStepsGoalViewModel.this.getContext().getString(R.string.somethings_went_wrong));
                    ViewModelListener mListener = FragmentStepsGoalViewModel.this.getMListener();
                    String string = FragmentStepsGoalViewModel.this.getContext().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.somethings_went_wrong)");
                    mListener.onDataFailure(string);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable UpdateFitnessGoalResponse updateFitnessGoalResponse) {
                    if (updateFitnessGoalResponse != null) {
                        if (updateFitnessGoalResponse.data.activityType.equals(ActivityType.WALK.getActivityType())) {
                            FitnessGoal fitnessGoal = new FitnessGoal();
                            com.coveiot.coveaccess.fitness.common.FitnessGoal fitnessGoal2 = updateFitnessGoalResponse.data;
                            fitnessGoal.target = fitnessGoal2.target;
                            fitnessGoal.goalId = fitnessGoal2.goalId;
                            fitnessGoal.activityBaseUnit = fitnessGoal2.activityBaseUnit;
                            fitnessGoal.activityType = fitnessGoal2.activityType;
                            fitnessGoal.createdDate = fitnessGoal2.createdDate;
                            fitnessGoal.modifiedDate = fitnessGoal2.modifiedDate;
                            fitnessGoal.targetAchieved = fitnessGoal2.targetAchieved.toString();
                            UserDataManager.getInstance(FragmentStepsGoalViewModel.this.getContext()).saveFitnessGoalSteps(fitnessGoal);
                        }
                        FragmentStepsGoalViewModel fragmentStepsGoalViewModel = FragmentStepsGoalViewModel.this;
                        if (fragmentStepsGoalViewModel.mListener != null) {
                            fragmentStepsGoalViewModel.getMListener().onSuccess();
                        }
                    }
                }
            });
            return;
        }
        ViewModelListener mListener = getMListener();
        String string = this.f5213a.getString(R.string.noconnection);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.noconnection)");
        mListener.onDataFailure(string);
        LogHelper.e(this.d, this.f5213a.getString(R.string.noconnection));
    }
}
