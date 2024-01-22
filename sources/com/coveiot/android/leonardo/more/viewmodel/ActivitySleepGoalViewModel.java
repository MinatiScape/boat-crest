package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetGoalTargetRequest;
import com.coveiot.android.bleabstract.request.SetSleepTargetRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveFitness;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalResponse;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.GoalSettingsData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySleepGoalViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5161a;
    @NotNull
    public final MutableLiveData<ArrayList<Integer>> b;
    public final String c;
    public ViewModelListener mListener;

    public ActivitySleepGoalViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5161a = context;
        this.b = new MutableLiveData<>();
        this.c = ActivitySleepGoalViewModel.class.getSimpleName();
    }

    public final void a(CreateFitnessGoalRequest createFitnessGoalRequest) {
        CoveFitness.createFitnessGoal(createFitnessGoalRequest, new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel$createSleepTargetOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivitySleepGoalViewModel.this.getMListener();
                String string = ActivitySleepGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                mListener.onDataFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CreateFitnessGoalResponse createFitnessGoalResponse) {
                if (createFitnessGoalResponse != null) {
                    if (createFitnessGoalResponse.fitnessGoal.activityType.equals(ActivityType.SLEEP.getActivityType())) {
                        FitnessGoal fitnessGoal = new FitnessGoal();
                        com.coveiot.coveaccess.fitness.common.FitnessGoal fitnessGoal2 = createFitnessGoalResponse.fitnessGoal;
                        fitnessGoal.target = fitnessGoal2.target;
                        fitnessGoal.goalId = fitnessGoal2.goalId;
                        fitnessGoal.activityBaseUnit = fitnessGoal2.activityBaseUnit;
                        fitnessGoal.activityType = fitnessGoal2.activityType;
                        fitnessGoal.createdDate = fitnessGoal2.createdDate;
                        fitnessGoal.modifiedDate = fitnessGoal2.modifiedDate;
                        fitnessGoal.targetAchieved = fitnessGoal2.targetAchieved.toString();
                        UserDataManager.getInstance(ActivitySleepGoalViewModel.this.getContext()).saveFitnessGoalSleep(fitnessGoal);
                        ProfileRepository profileRepository = ProfileRepository.getInstance();
                        Context context = ActivitySleepGoalViewModel.this.getContext();
                        Integer num = fitnessGoal2.target;
                        Intrinsics.checkNotNullExpressionValue(num, "datum.target");
                        profileRepository.updateSleepTarget(context, num.intValue());
                        GoalSettingsData goalSettingsData = GoalSettingsData.getInstance();
                        Integer num2 = fitnessGoal2.target;
                        Intrinsics.checkNotNullExpressionValue(num2, "datum.target");
                        goalSettingsData.setSleep_hours(num2.intValue());
                        UserDataManager.getInstance(ActivitySleepGoalViewModel.this.getContext()).saveGoalSettings(goalSettingsData);
                    }
                    ActivitySleepGoalViewModel activitySleepGoalViewModel = ActivitySleepGoalViewModel.this;
                    if (activitySleepGoalViewModel.mListener != null) {
                        activitySleepGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void b(int i, UpdateFitnessGoalRequest updateFitnessGoalRequest) {
        CoveFitness.updateFitnessGoal(Integer.valueOf(i), updateFitnessGoalRequest, new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel$updateSleepTargetToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivitySleepGoalViewModel.this.getMListener();
                String string = ActivitySleepGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                mListener.onDataFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UpdateFitnessGoalResponse updateFitnessGoalResponse) {
                if (updateFitnessGoalResponse != null) {
                    if (updateFitnessGoalResponse.data.activityType.equals(ActivityType.SLEEP.getActivityType())) {
                        FitnessGoal fitnessGoal = new FitnessGoal();
                        com.coveiot.coveaccess.fitness.common.FitnessGoal fitnessGoal2 = updateFitnessGoalResponse.data;
                        fitnessGoal.target = fitnessGoal2.target;
                        fitnessGoal.goalId = fitnessGoal2.goalId;
                        fitnessGoal.activityBaseUnit = fitnessGoal2.activityBaseUnit;
                        fitnessGoal.activityType = fitnessGoal2.activityType;
                        fitnessGoal.createdDate = fitnessGoal2.createdDate;
                        fitnessGoal.modifiedDate = fitnessGoal2.modifiedDate;
                        fitnessGoal.targetAchieved = fitnessGoal2.targetAchieved.toString();
                        UserDataManager.getInstance(ActivitySleepGoalViewModel.this.getContext()).saveFitnessGoalSleep(fitnessGoal);
                        ProfileRepository profileRepository = ProfileRepository.getInstance();
                        Context context = ActivitySleepGoalViewModel.this.getContext();
                        Integer num = fitnessGoal2.target;
                        Intrinsics.checkNotNullExpressionValue(num, "datum.target");
                        profileRepository.updateSleepTarget(context, num.intValue());
                    }
                    ActivitySleepGoalViewModel activitySleepGoalViewModel = ActivitySleepGoalViewModel.this;
                    if (activitySleepGoalViewModel.mListener != null) {
                        activitySleepGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void createSleepGoal(@NotNull final CreateFitnessGoalRequest reqModel, int i) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5161a)) {
            if (BleApiManager.getInstance(this.f5161a).getBleApi().getDeviceSupportedFeatures().isSleepTargetSupported()) {
                if (BleApiManager.getInstance(this.f5161a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5161a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_DISTANCE_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_CALORIE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_EXCERSIE_DURATION_GOAL.getValue());
                        Integer valueOf = Integer.valueOf(Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue()));
                        FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this.f5161a).getFitnessGoalDistanceData();
                        if (fitnessGoalDistanceData != null) {
                            Integer num = fitnessGoalDistanceData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mDistanceGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this.f5161a).getFitnessGoalCaloriesData();
                        if (fitnessGoalCaloriesData != null) {
                            Integer num2 = fitnessGoalCaloriesData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mCalorieGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this.f5161a).getFitnessGoalExerciseMinutesData();
                        if (fitnessGoalExerciseMinutesData != null) {
                            Integer num3 = fitnessGoalExerciseMinutesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mExcerciseGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5161a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            valueOf = fitnessGoalStepsData.target;
                        }
                        Intrinsics.checkNotNull(valueOf);
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(valueOf.intValue(), parseInt, parseInt2);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(parseInt3));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(i));
                        BleApiManager.getInstance(this.f5161a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel$createSleepGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivitySleepGoalViewModel.this.getMListener();
                                String string = ActivitySleepGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivitySleepGoalViewModel.this.a(reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5161a).getBleApi().setUserSettings(new SetSleepTargetRequest.Builder().setTarget(i).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel$createSleepGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivitySleepGoalViewModel.this.getMListener();
                            String string = ActivitySleepGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivitySleepGoalViewModel.this.a(reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5161a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            a(reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5161a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }

    @NotNull
    public final Context getContext() {
        return this.f5161a;
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
    public final MutableLiveData<ArrayList<Integer>> getSleepHourList() {
        return this.b;
    }

    public final String getTAG() {
        return this.c;
    }

    public final void load24Hours() {
        if (this.b.getValue() == null) {
            this.b.setValue(new ArrayList<>());
        }
        int i = 4;
        while (true) {
            int i2 = i + 1;
            ArrayList<Integer> value = this.b.getValue();
            Intrinsics.checkNotNull(value);
            value.add(Integer.valueOf(i));
            if (i2 > 12) {
                MutableLiveData<ArrayList<Integer>> mutableLiveData = this.b;
                mutableLiveData.postValue(mutableLiveData.getValue());
                return;
            }
            i = i2;
        }
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }

    public final void updateSleepGoal(final int i, @NotNull final UpdateFitnessGoalRequest reqModel, int i2) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5161a)) {
            if (BleApiManager.getInstance(this.f5161a).getBleApi().getDeviceSupportedFeatures().isSleepTargetSupported()) {
                if (BleApiManager.getInstance(this.f5161a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5161a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_DISTANCE_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_CALORIE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_EXCERSIE_DURATION_GOAL.getValue());
                        Integer valueOf = Integer.valueOf(Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue()));
                        FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this.f5161a).getFitnessGoalDistanceData();
                        if (fitnessGoalDistanceData != null) {
                            Integer num = fitnessGoalDistanceData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mDistanceGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this.f5161a).getFitnessGoalCaloriesData();
                        if (fitnessGoalCaloriesData != null) {
                            Integer num2 = fitnessGoalCaloriesData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mCalorieGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this.f5161a).getFitnessGoalExerciseMinutesData();
                        if (fitnessGoalExerciseMinutesData != null) {
                            Integer num3 = fitnessGoalExerciseMinutesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mExcerciseGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5161a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            valueOf = fitnessGoalStepsData.target;
                        }
                        Intrinsics.checkNotNull(valueOf);
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(valueOf.intValue(), parseInt, parseInt2);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(parseInt3));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(i2));
                        BleApiManager.getInstance(this.f5161a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel$updateSleepGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivitySleepGoalViewModel.this.getMListener();
                                String string = ActivitySleepGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivitySleepGoalViewModel.this.b(i, reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5161a).getBleApi().setUserSettings(new SetSleepTargetRequest.Builder().setTarget(i2).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel$updateSleepGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivitySleepGoalViewModel.this.getMListener();
                            String string = ActivitySleepGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivitySleepGoalViewModel.this.b(i, reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5161a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            b(i, reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5161a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }
}
