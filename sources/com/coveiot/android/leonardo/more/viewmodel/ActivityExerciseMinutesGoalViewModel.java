package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetExerciseMinutesTargetRequest;
import com.coveiot.android.bleabstract.request.SetGoalTargetRequest;
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
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityExerciseMinutesGoalViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5152a;
    @NotNull
    public final MutableLiveData<ArrayList<Integer>> b;
    public final String c;
    public ViewModelListener mListener;

    public ActivityExerciseMinutesGoalViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5152a = context;
        this.b = new MutableLiveData<>();
        this.c = ActivityExerciseMinutesGoalViewModel.class.getSimpleName();
    }

    public final void a(CreateFitnessGoalRequest createFitnessGoalRequest) {
        CoveFitness.createFitnessGoal(createFitnessGoalRequest, new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityExerciseMinutesGoalViewModel$createExerciseMinuteTargetOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityExerciseMinutesGoalViewModel.this.getMListener();
                String string = ActivityExerciseMinutesGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                mListener.onDataFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CreateFitnessGoalResponse createFitnessGoalResponse) {
                if (createFitnessGoalResponse != null) {
                    if (createFitnessGoalResponse.fitnessGoal.activityType.equals(ActivityType.ANY.getActivityType())) {
                        FitnessGoal fitnessGoal = new FitnessGoal();
                        com.coveiot.coveaccess.fitness.common.FitnessGoal fitnessGoal2 = createFitnessGoalResponse.fitnessGoal;
                        fitnessGoal.target = fitnessGoal2.target;
                        fitnessGoal.goalId = fitnessGoal2.goalId;
                        fitnessGoal.activityBaseUnit = fitnessGoal2.activityBaseUnit;
                        fitnessGoal.activityType = fitnessGoal2.activityType;
                        fitnessGoal.createdDate = fitnessGoal2.createdDate;
                        fitnessGoal.modifiedDate = fitnessGoal2.modifiedDate;
                        fitnessGoal.targetAchieved = fitnessGoal2.targetAchieved.toString();
                        UserDataManager.getInstance(ActivityExerciseMinutesGoalViewModel.this.getContext()).saveFitnessGoalExerciseMinutes(fitnessGoal);
                    }
                    ActivityExerciseMinutesGoalViewModel activityExerciseMinutesGoalViewModel = ActivityExerciseMinutesGoalViewModel.this;
                    if (activityExerciseMinutesGoalViewModel.mListener != null) {
                        activityExerciseMinutesGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void b(int i, UpdateFitnessGoalRequest updateFitnessGoalRequest) {
        CoveFitness.updateFitnessGoal(Integer.valueOf(i), updateFitnessGoalRequest, new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityExerciseMinutesGoalViewModel$updateExerciseMinuteTargetToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityExerciseMinutesGoalViewModel.this.getMListener();
                String string = ActivityExerciseMinutesGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                mListener.onDataFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UpdateFitnessGoalResponse updateFitnessGoalResponse) {
                if (updateFitnessGoalResponse != null) {
                    if (updateFitnessGoalResponse.data.activityType.equals(ActivityType.ANY.getActivityType())) {
                        FitnessGoal fitnessGoal = new FitnessGoal();
                        com.coveiot.coveaccess.fitness.common.FitnessGoal fitnessGoal2 = updateFitnessGoalResponse.data;
                        fitnessGoal.target = fitnessGoal2.target;
                        fitnessGoal.goalId = fitnessGoal2.goalId;
                        fitnessGoal.activityBaseUnit = fitnessGoal2.activityBaseUnit;
                        fitnessGoal.activityType = fitnessGoal2.activityType;
                        fitnessGoal.createdDate = fitnessGoal2.createdDate;
                        fitnessGoal.modifiedDate = fitnessGoal2.modifiedDate;
                        fitnessGoal.targetAchieved = fitnessGoal2.targetAchieved.toString();
                        UserDataManager.getInstance(ActivityExerciseMinutesGoalViewModel.this.getContext()).saveFitnessGoalExerciseMinutes(fitnessGoal);
                    }
                    ActivityExerciseMinutesGoalViewModel activityExerciseMinutesGoalViewModel = ActivityExerciseMinutesGoalViewModel.this;
                    if (activityExerciseMinutesGoalViewModel.mListener != null) {
                        activityExerciseMinutesGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void createExerciseMinuteGoal(@NotNull final CreateFitnessGoalRequest reqModel, int i) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5152a)) {
            if (BleApiManager.getInstance(this.f5152a).getBleApi().getDeviceSupportedFeatures().isExerciseMinutesGoalSupported()) {
                if (BleApiManager.getInstance(this.f5152a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5152a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_DISTANCE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_CALORIE_GOAL.getValue());
                        int parseInt4 = Integer.parseInt(AppConstants.DEFAULT_SLEEP_GOAL.getValue());
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5152a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            Integer num = fitnessGoalStepsData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mStepsGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this.f5152a).getFitnessGoalDistanceData();
                        if (fitnessGoalDistanceData != null) {
                            Integer num2 = fitnessGoalDistanceData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mDistanceGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this.f5152a).getFitnessGoalCaloriesData();
                        if (fitnessGoalCaloriesData != null) {
                            Integer num3 = fitnessGoalCaloriesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mCalorieGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this.f5152a).getFitnessGoalSleepData();
                        if (fitnessGoalSleepData != null) {
                            Integer num4 = fitnessGoalSleepData.target;
                            Intrinsics.checkNotNullExpressionValue(num4, "mSleepGoalData.target");
                            parseInt4 = num4.intValue();
                        }
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(parseInt, parseInt2, parseInt3);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(i));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(parseInt4));
                        BleApiManager.getInstance(this.f5152a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityExerciseMinutesGoalViewModel$createExerciseMinuteGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivityExerciseMinutesGoalViewModel.this.getMListener();
                                String string = ActivityExerciseMinutesGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivityExerciseMinutesGoalViewModel.this.a(reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5152a).getBleApi().setUserSettings(new SetExerciseMinutesTargetRequest.Builder().setTarget(i).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityExerciseMinutesGoalViewModel$createExerciseMinuteGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityExerciseMinutesGoalViewModel.this.getMListener();
                            String string = ActivityExerciseMinutesGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityExerciseMinutesGoalViewModel.this.a(reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5152a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            a(reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5152a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }

    @NotNull
    public final Context getContext() {
        return this.f5152a;
    }

    @NotNull
    public final MutableLiveData<ArrayList<Integer>> getExerciseMinutesList() {
        return this.b;
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

    public final String getTAG() {
        return this.c;
    }

    public final void loadExerciseMinutes() {
        if (this.b.getValue() == null) {
            this.b.setValue(new ArrayList<>());
        }
        int i = 5;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(5, 60, 5);
        if (5 <= progressionLastElement) {
            while (true) {
                ArrayList<Integer> value = this.b.getValue();
                Intrinsics.checkNotNull(value);
                value.add(Integer.valueOf(i));
                if (i == progressionLastElement) {
                    break;
                }
                i += 5;
            }
        }
        MutableLiveData<ArrayList<Integer>> mutableLiveData = this.b;
        mutableLiveData.postValue(mutableLiveData.getValue());
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }

    public final void updateExerciseMinuteGoal(final int i, @NotNull final UpdateFitnessGoalRequest reqModel, int i2) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5152a)) {
            if (BleApiManager.getInstance(this.f5152a).getBleApi().getDeviceSupportedFeatures().isExerciseMinutesGoalSupported()) {
                if (BleApiManager.getInstance(this.f5152a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5152a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_DISTANCE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_CALORIE_GOAL.getValue());
                        int parseInt4 = Integer.parseInt(AppConstants.DEFAULT_SLEEP_GOAL.getValue());
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5152a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            Integer num = fitnessGoalStepsData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mStepsGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this.f5152a).getFitnessGoalDistanceData();
                        if (fitnessGoalDistanceData != null) {
                            Integer num2 = fitnessGoalDistanceData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mDistanceGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this.f5152a).getFitnessGoalCaloriesData();
                        if (fitnessGoalCaloriesData != null) {
                            Integer num3 = fitnessGoalCaloriesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mCalorieGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this.f5152a).getFitnessGoalSleepData();
                        if (fitnessGoalSleepData != null) {
                            Integer num4 = fitnessGoalSleepData.target;
                            Intrinsics.checkNotNullExpressionValue(num4, "mSleepGoalData.target");
                            parseInt4 = num4.intValue();
                        }
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(parseInt, parseInt2, parseInt3);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(i2));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(parseInt4));
                        BleApiManager.getInstance(this.f5152a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityExerciseMinutesGoalViewModel$updateExerciseMinuteGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivityExerciseMinutesGoalViewModel.this.getMListener();
                                String string = ActivityExerciseMinutesGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivityExerciseMinutesGoalViewModel.this.b(i, reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5152a).getBleApi().setUserSettings(new SetExerciseMinutesTargetRequest.Builder().setTarget(i2).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityExerciseMinutesGoalViewModel$updateExerciseMinuteGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityExerciseMinutesGoalViewModel.this.getMListener();
                            String string = ActivityExerciseMinutesGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityExerciseMinutesGoalViewModel.this.b(i, reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5152a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            b(i, reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5152a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }
}
