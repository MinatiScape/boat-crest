package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetDistanceTargetRequest;
import com.coveiot.android.bleabstract.request.SetGoalTargetRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.devicemodels.DeviceUtils;
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
public final class ActivityDistanceGoalViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5149a;
    @NotNull
    public final MutableLiveData<ArrayList<Integer>> b;
    public final String c;
    public ViewModelListener mListener;

    public ActivityDistanceGoalViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5149a = context;
        this.b = new MutableLiveData<>();
        this.c = ActivityDistanceGoalViewModel.class.getSimpleName();
    }

    public final void a(CreateFitnessGoalRequest createFitnessGoalRequest) {
        CoveFitness.createFitnessGoal(createFitnessGoalRequest, new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel$createDistanceTargetOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityDistanceGoalViewModel.this.getMListener();
                String string = ActivityDistanceGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                mListener.onDataFailure(string);
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
                        UserDataManager.getInstance(ActivityDistanceGoalViewModel.this.getContext()).saveFitnessGoalDistance(fitnessGoal);
                    }
                    ActivityDistanceGoalViewModel activityDistanceGoalViewModel = ActivityDistanceGoalViewModel.this;
                    if (activityDistanceGoalViewModel.mListener != null) {
                        activityDistanceGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void b(int i, UpdateFitnessGoalRequest updateFitnessGoalRequest) {
        CoveFitness.updateFitnessGoal(Integer.valueOf(i), updateFitnessGoalRequest, new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel$updateDistanceTargetToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityDistanceGoalViewModel.this.getMListener();
                String string = ActivityDistanceGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
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
                        UserDataManager.getInstance(ActivityDistanceGoalViewModel.this.getContext()).saveFitnessGoalDistance(fitnessGoal);
                    }
                    ActivityDistanceGoalViewModel activityDistanceGoalViewModel = ActivityDistanceGoalViewModel.this;
                    if (activityDistanceGoalViewModel.mListener != null) {
                        activityDistanceGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void createDistanceGoal(@NotNull final CreateFitnessGoalRequest reqModel, int i) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5149a)) {
            if (BleApiManager.getInstance(this.f5149a).getBleApi().getDeviceSupportedFeatures().isDistanceGoalSupported()) {
                if (BleApiManager.getInstance(this.f5149a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5149a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_CALORIE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_EXCERSIE_DURATION_GOAL.getValue());
                        int parseInt4 = Integer.parseInt(AppConstants.DEFAULT_SLEEP_GOAL.getValue());
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5149a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            Integer num = fitnessGoalStepsData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mStepsGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this.f5149a).getFitnessGoalCaloriesData();
                        if (fitnessGoalCaloriesData != null) {
                            Integer num2 = fitnessGoalCaloriesData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mCalorieGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this.f5149a).getFitnessGoalExerciseMinutesData();
                        if (fitnessGoalExerciseMinutesData != null) {
                            Integer num3 = fitnessGoalExerciseMinutesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mExcerciseGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this.f5149a).getFitnessGoalSleepData();
                        if (fitnessGoalSleepData != null) {
                            Integer num4 = fitnessGoalSleepData.target;
                            Intrinsics.checkNotNullExpressionValue(num4, "mSleepGoalData.target");
                            parseInt4 = num4.intValue();
                        }
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(parseInt, i, parseInt2);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(parseInt3));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(parseInt4));
                        BleApiManager.getInstance(this.f5149a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel$createDistanceGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivityDistanceGoalViewModel.this.getMListener();
                                String string = ActivityDistanceGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivityDistanceGoalViewModel.this.a(reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5149a).getBleApi().setUserSettings(new SetDistanceTargetRequest.Builder().setTarget(i).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel$createDistanceGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityDistanceGoalViewModel.this.getMListener();
                            String string = ActivityDistanceGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityDistanceGoalViewModel.this.a(reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5149a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            a(reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5149a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }

    @NotNull
    public final Context getContext() {
        return this.f5149a;
    }

    @NotNull
    public final MutableLiveData<ArrayList<Integer>> getDistanceList() {
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

    public final void loadDistanceList() {
        if (this.b.getValue() == null) {
            this.b.setValue(new ArrayList<>());
        }
        ArrayList<Integer> value = this.b.getValue();
        Intrinsics.checkNotNull(value);
        value.clear();
        int i = 1000;
        if (DeviceUtils.Companion.isSmaJieieDevice(this.f5149a)) {
            int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(1000, 199000, 500);
            if (1000 <= progressionLastElement) {
                while (true) {
                    ArrayList<Integer> value2 = this.b.getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.add(Integer.valueOf(i));
                    if (i == progressionLastElement) {
                        break;
                    }
                    i += 500;
                }
            }
        } else {
            int progressionLastElement2 = ProgressionUtilKt.getProgressionLastElement(1000, 25000, 1000);
            if (1000 <= progressionLastElement2) {
                while (true) {
                    ArrayList<Integer> value3 = this.b.getValue();
                    Intrinsics.checkNotNull(value3);
                    value3.add(Integer.valueOf(i));
                    if (i == progressionLastElement2) {
                        break;
                    }
                    i += 1000;
                }
            }
        }
        MutableLiveData<ArrayList<Integer>> mutableLiveData = this.b;
        mutableLiveData.postValue(mutableLiveData.getValue());
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }

    public final void updateDistanceGoal(final int i, @NotNull final UpdateFitnessGoalRequest reqModel, int i2) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5149a)) {
            if (BleApiManager.getInstance(this.f5149a).getBleApi().getDeviceSupportedFeatures().isDistanceGoalSupported()) {
                if (BleApiManager.getInstance(this.f5149a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5149a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_CALORIE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_EXCERSIE_DURATION_GOAL.getValue());
                        int parseInt4 = Integer.parseInt(AppConstants.DEFAULT_SLEEP_GOAL.getValue());
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5149a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            Integer num = fitnessGoalStepsData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mStepsGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalCaloriesData = UserDataManager.getInstance(this.f5149a).getFitnessGoalCaloriesData();
                        if (fitnessGoalCaloriesData != null) {
                            Integer num2 = fitnessGoalCaloriesData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mCalorieGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this.f5149a).getFitnessGoalExerciseMinutesData();
                        if (fitnessGoalExerciseMinutesData != null) {
                            Integer num3 = fitnessGoalExerciseMinutesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mExcerciseGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this.f5149a).getFitnessGoalSleepData();
                        if (fitnessGoalSleepData != null) {
                            Integer num4 = fitnessGoalSleepData.target;
                            Intrinsics.checkNotNullExpressionValue(num4, "mSleepGoalData.target");
                            parseInt4 = num4.intValue();
                        }
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(parseInt, i2, parseInt2);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(parseInt3));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(parseInt4));
                        BleApiManager.getInstance(this.f5149a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel$updateDistanceGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivityDistanceGoalViewModel.this.getMListener();
                                String string = ActivityDistanceGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivityDistanceGoalViewModel.this.b(i, reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5149a).getBleApi().setUserSettings(new SetDistanceTargetRequest.Builder().setTarget(i2).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel$updateDistanceGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityDistanceGoalViewModel.this.getMListener();
                            String string = ActivityDistanceGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityDistanceGoalViewModel.this.b(i, reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5149a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            b(i, reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5149a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }
}
