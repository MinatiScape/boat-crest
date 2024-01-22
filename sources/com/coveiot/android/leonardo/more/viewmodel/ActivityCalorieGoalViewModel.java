package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetCalorieTargetRequest;
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
public final class ActivityCalorieGoalViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5142a;
    @NotNull
    public final MutableLiveData<ArrayList<Integer>> b;
    public final String c;
    public ViewModelListener mListener;

    public ActivityCalorieGoalViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5142a = context;
        this.b = new MutableLiveData<>();
        this.c = ActivityCalorieGoalViewModel.class.getSimpleName();
    }

    public final void a(CreateFitnessGoalRequest createFitnessGoalRequest) {
        CoveFitness.createFitnessGoal(createFitnessGoalRequest, new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel$createCalorieTargetOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityCalorieGoalViewModel.this.getMListener();
                String string = ActivityCalorieGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
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
                        UserDataManager.getInstance(ActivityCalorieGoalViewModel.this.getContext()).saveFitnessGoalCalories(fitnessGoal);
                    }
                    ActivityCalorieGoalViewModel activityCalorieGoalViewModel = ActivityCalorieGoalViewModel.this;
                    if (activityCalorieGoalViewModel.mListener != null) {
                        activityCalorieGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void b(int i, UpdateFitnessGoalRequest updateFitnessGoalRequest) {
        CoveFitness.updateFitnessGoal(Integer.valueOf(i), updateFitnessGoalRequest, new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel$updateCalorieTargetToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityCalorieGoalViewModel.this.getMListener();
                String string = ActivityCalorieGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
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
                        UserDataManager.getInstance(ActivityCalorieGoalViewModel.this.getContext()).saveFitnessGoalCalories(fitnessGoal);
                    }
                    ActivityCalorieGoalViewModel activityCalorieGoalViewModel = ActivityCalorieGoalViewModel.this;
                    if (activityCalorieGoalViewModel.mListener != null) {
                        activityCalorieGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void createCalorieGoal(@NotNull final CreateFitnessGoalRequest reqModel, int i) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5142a)) {
            if (BleApiManager.getInstance(this.f5142a).getBleApi().getDeviceSupportedFeatures().isCalorieGoalSupported()) {
                if (BleApiManager.getInstance(this.f5142a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5142a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_DISTANCE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_EXCERSIE_DURATION_GOAL.getValue());
                        int parseInt4 = Integer.parseInt(AppConstants.DEFAULT_SLEEP_GOAL.getValue());
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5142a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            Integer num = fitnessGoalStepsData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mStepsGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this.f5142a).getFitnessGoalDistanceData();
                        if (fitnessGoalDistanceData != null) {
                            Integer num2 = fitnessGoalDistanceData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mDistanceGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this.f5142a).getFitnessGoalExerciseMinutesData();
                        if (fitnessGoalExerciseMinutesData != null) {
                            Integer num3 = fitnessGoalExerciseMinutesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mExcerciseGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this.f5142a).getFitnessGoalSleepData();
                        if (fitnessGoalSleepData != null) {
                            Integer num4 = fitnessGoalSleepData.target;
                            Intrinsics.checkNotNullExpressionValue(num4, "mSleepGoalData.target");
                            parseInt4 = num4.intValue();
                        }
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(parseInt, parseInt2, i);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(parseInt3));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(parseInt4));
                        BleApiManager.getInstance(this.f5142a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel$createCalorieGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivityCalorieGoalViewModel.this.getMListener();
                                String string = ActivityCalorieGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivityCalorieGoalViewModel.this.a(reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5142a).getBleApi().setUserSettings(new SetCalorieTargetRequest.Builder().setTarget(i).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel$createCalorieGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityCalorieGoalViewModel.this.getMListener();
                            String string = ActivityCalorieGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityCalorieGoalViewModel.this.a(reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5142a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            a(reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5142a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }

    @NotNull
    public final MutableLiveData<ArrayList<Integer>> getCaloriesList() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f5142a;
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

    public final void loadCaloriesList() {
        if (this.b.getValue() == null) {
            this.b.setValue(new ArrayList<>());
        }
        if (DeviceUtils.Companion.isSmaJieieDevice(this.f5142a)) {
            int i = 20;
            int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(20, 9980, 20);
            if (20 <= progressionLastElement) {
                while (true) {
                    ArrayList<Integer> value = this.b.getValue();
                    Intrinsics.checkNotNull(value);
                    value.add(Integer.valueOf(i));
                    if (i == progressionLastElement) {
                        break;
                    }
                    i += 20;
                }
            }
        } else {
            int i2 = 150;
            int progressionLastElement2 = ProgressionUtilKt.getProgressionLastElement(150, 800, 10);
            if (150 <= progressionLastElement2) {
                while (true) {
                    ArrayList<Integer> value2 = this.b.getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.add(Integer.valueOf(i2));
                    if (i2 == progressionLastElement2) {
                        break;
                    }
                    i2 += 10;
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

    public final void updateCaloriesGoal(final int i, @NotNull final UpdateFitnessGoalRequest reqModel, int i2) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5142a)) {
            if (BleApiManager.getInstance(this.f5142a).getBleApi().getDeviceSupportedFeatures().isCalorieGoalSupported()) {
                if (BleApiManager.getInstance(this.f5142a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(this.f5142a).getBleApi().getDeviceSupportedFeatures().isGoalSettingSupportedInSingleCommand()) {
                        int parseInt = Integer.parseInt(AppConstants.DEFAULT_STEPS_GOAL.getValue());
                        int parseInt2 = Integer.parseInt(AppConstants.DEFAULT_DISTANCE_GOAL.getValue());
                        int parseInt3 = Integer.parseInt(AppConstants.DEFAULT_EXCERSIE_DURATION_GOAL.getValue());
                        int parseInt4 = Integer.parseInt(AppConstants.DEFAULT_SLEEP_GOAL.getValue());
                        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f5142a).getFitnessGoalStepsData();
                        if (fitnessGoalStepsData != null) {
                            Integer num = fitnessGoalStepsData.target;
                            Intrinsics.checkNotNullExpressionValue(num, "mStepsGoalData.target");
                            parseInt = num.intValue();
                        }
                        FitnessGoal fitnessGoalDistanceData = UserDataManager.getInstance(this.f5142a).getFitnessGoalDistanceData();
                        if (fitnessGoalDistanceData != null) {
                            Integer num2 = fitnessGoalDistanceData.target;
                            Intrinsics.checkNotNullExpressionValue(num2, "mDistanceGoalData.target");
                            parseInt2 = num2.intValue();
                        }
                        FitnessGoal fitnessGoalExerciseMinutesData = UserDataManager.getInstance(this.f5142a).getFitnessGoalExerciseMinutesData();
                        if (fitnessGoalExerciseMinutesData != null) {
                            Integer num3 = fitnessGoalExerciseMinutesData.target;
                            Intrinsics.checkNotNullExpressionValue(num3, "mExcerciseGoalData.target");
                            parseInt3 = num3.intValue();
                        }
                        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(this.f5142a).getFitnessGoalSleepData();
                        if (fitnessGoalSleepData != null) {
                            Integer num4 = fitnessGoalSleepData.target;
                            Intrinsics.checkNotNullExpressionValue(num4, "mSleepGoalData.target");
                            parseInt4 = num4.intValue();
                        }
                        SetGoalTargetRequest setGoalTargetRequest = new SetGoalTargetRequest(parseInt, parseInt2, i2);
                        setGoalTargetRequest.setExceriseDurationTarget(Integer.valueOf(parseInt3));
                        setGoalTargetRequest.setSleepTarget(Integer.valueOf(parseInt4));
                        BleApiManager.getInstance(this.f5142a).getBleApi().setUserSettings(setGoalTargetRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel$updateCaloriesGoal$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                ViewModelListener mListener = ActivityCalorieGoalViewModel.this.getMListener();
                                String string = ActivityCalorieGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                                mListener.onDataFailure(string);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                ActivityCalorieGoalViewModel.this.b(i, reqModel);
                            }
                        });
                        return;
                    }
                    BleApiManager.getInstance(this.f5142a).getBleApi().setUserSettings(new SetCalorieTargetRequest.Builder().setTarget(i2).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel$updateCaloriesGoal$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityCalorieGoalViewModel.this.getMListener();
                            String string = ActivityCalorieGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityCalorieGoalViewModel.this.b(i, reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5142a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            b(i, reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5142a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }
}
