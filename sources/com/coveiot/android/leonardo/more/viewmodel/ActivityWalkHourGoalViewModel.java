package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetWalkHourTargetRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
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
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityWalkHourGoalViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5166a;
    @NotNull
    public final MutableLiveData<ArrayList<Integer>> b;
    public final String c;
    public ViewModelListener mListener;

    public ActivityWalkHourGoalViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5166a = context;
        this.b = new MutableLiveData<>();
        this.c = ActivityWalkHourGoalViewModel.class.getSimpleName();
    }

    public final void a(CreateFitnessGoalRequest createFitnessGoalRequest) {
        CoveFitness.createFitnessGoal(createFitnessGoalRequest, new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityWalkHourGoalViewModel$createWalkHourTargetOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityWalkHourGoalViewModel.this.getMListener();
                String string = ActivityWalkHourGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
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
                        UserDataManager.getInstance(ActivityWalkHourGoalViewModel.this.getContext()).saveFitnessGoalWalkHour(fitnessGoal);
                    }
                    ActivityWalkHourGoalViewModel activityWalkHourGoalViewModel = ActivityWalkHourGoalViewModel.this;
                    if (activityWalkHourGoalViewModel.mListener != null) {
                        activityWalkHourGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void b(int i, UpdateFitnessGoalRequest updateFitnessGoalRequest) {
        CoveFitness.updateFitnessGoal(Integer.valueOf(i), updateFitnessGoalRequest, new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityWalkHourGoalViewModel$updateWalkHourTargetToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelListener mListener = ActivityWalkHourGoalViewModel.this.getMListener();
                String string = ActivityWalkHourGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
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
                        UserDataManager.getInstance(ActivityWalkHourGoalViewModel.this.getContext()).saveFitnessGoalWalkHour(fitnessGoal);
                    }
                    ActivityWalkHourGoalViewModel activityWalkHourGoalViewModel = ActivityWalkHourGoalViewModel.this;
                    if (activityWalkHourGoalViewModel.mListener != null) {
                        activityWalkHourGoalViewModel.getMListener().onSuccess();
                    }
                }
            }
        });
    }

    public final void createWalkHourGoal(@NotNull final CreateFitnessGoalRequest reqModel, int i) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5166a)) {
            if (BleApiManager.getInstance(this.f5166a).getBleApi().getDeviceSupportedFeatures().isWalkingHourGoalSupported()) {
                if (BleApiManager.getInstance(this.f5166a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    BleApiManager.getInstance(this.f5166a).getBleApi().setUserSettings(new SetWalkHourTargetRequest.Builder().setTarget(i / 60).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityWalkHourGoalViewModel$createWalkHourGoal$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityWalkHourGoalViewModel.this.getMListener();
                            String string = ActivityWalkHourGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityWalkHourGoalViewModel.this.a(reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5166a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            a(reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5166a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }

    @NotNull
    public final Context getContext() {
        return this.f5166a;
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

    @NotNull
    public final MutableLiveData<ArrayList<Integer>> getWalkHourList() {
        return this.b;
    }

    public final void loadWalkHourList() {
        if (this.b.getValue() == null) {
            this.b.setValue(new ArrayList<>());
        }
        int i = 6;
        while (true) {
            int i2 = i + 1;
            ArrayList<Integer> value = this.b.getValue();
            Intrinsics.checkNotNull(value);
            value.add(Integer.valueOf(i));
            if (i2 > 14) {
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

    public final void updateWalkHourGoal(final int i, @NotNull final UpdateFitnessGoalRequest reqModel, int i2) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        if (AppUtils.isNetConnected(this.f5166a)) {
            if (BleApiManager.getInstance(this.f5166a).getBleApi().getDeviceSupportedFeatures().isWalkingHourGoalSupported()) {
                if (BleApiManager.getInstance(this.f5166a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    BleApiManager.getInstance(this.f5166a).getBleApi().setUserSettings(new SetWalkHourTargetRequest.Builder().setTarget(i2 / 60).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityWalkHourGoalViewModel$updateWalkHourGoal$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            ViewModelListener mListener = ActivityWalkHourGoalViewModel.this.getMListener();
                            String string = ActivityWalkHourGoalViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                            mListener.onDataFailure(string);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            ActivityWalkHourGoalViewModel.this.b(i, reqModel);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5166a.getResources().getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.device_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            b(i, reqModel);
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5166a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }
}
