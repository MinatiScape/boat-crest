package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.coveiot.android.bleabstract.request.LiveStepsControlRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.dashboard2.listener.StepsDataModelListener;
import com.coveiot.android.dashboard2.util.StepsDataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.repository.walk.datasources.db.write.WalkDBWrite;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.bean.data.TodayTotalData;
import java.util.Calendar;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class StepsDataViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4286a;
    @NotNull
    public final String b;
    @NotNull
    public final MutableLiveData<Boolean> c;

    public StepsDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4286a = context;
        this.b = "StepsDataViewModel";
        this.c = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f4286a;
    }

    public final void getPreviousDayStepsData(@NotNull Calendar calendar, @NotNull Context context, @Nullable StepsDataModelListener stepsDataModelListener) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(context, "context");
        StepsDataModel stepsDataModel = new StepsDataModel();
        stepsDataModel.setTimeStamp(System.currentTimeMillis());
        DailyWalkData walkDataPreviousDate = StepsDataHelper.INSTANCE.getWalkDataPreviousDate(context, calendar);
        if (walkDataPreviousDate != null) {
            stepsDataModel.setSteps(walkDataPreviousDate.getValue());
            stepsDataModel.setDistance(walkDataPreviousDate.getMeters());
            stepsDataModel.setCalories(Double.valueOf(walkDataPreviousDate.getCalories()));
        }
        if (stepsDataModelListener != null) {
            stepsDataModelListener.onData(stepsDataModel);
        }
    }

    @NotNull
    public final MutableLiveData<Boolean> getStepsControlCommandLiveData() {
        return this.c;
    }

    public final void getTodayStepsData(@NotNull Context context, @Nullable StepsDataModelListener stepsDataModelListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        StepsDataModel liveStepsData = UserDataManager.getInstance(context).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(context).getBleApi().getMacAddress());
        liveStepsData.setTimeStamp(System.currentTimeMillis());
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isIDODevice(context) && !companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context)) {
            if (companion.isMatrixDevice(context)) {
                TodayTotalData todayTotalData = PreferenceManagerMatrix.getInstance(context).getTodayTotalData();
                if (todayTotalData != null) {
                    liveStepsData.setSteps(todayTotalData.getStep());
                    liveStepsData.setCalories(Double.valueOf(todayTotalData.getCalorie() / 1000));
                    liveStepsData.setDistance(todayTotalData.getDistance());
                    liveStepsData.setTimeStamp(todayTotalData.getTimeStamp());
                }
            } else if (companion.isSmaDevice(context)) {
                DailyWalkData walkDataCurrentDate = StepsDataHelper.INSTANCE.getWalkDataCurrentDate(context);
                if (walkDataCurrentDate != null) {
                    liveStepsData.setSteps(walkDataCurrentDate.getValue());
                    liveStepsData.setDistance(walkDataCurrentDate.getMeters());
                    liveStepsData.setCalories(Double.valueOf(walkDataCurrentDate.getCalories()));
                    liveStepsData.setTimeStamp(System.currentTimeMillis());
                }
            } else {
                UserDataManager userDataManager = UserDataManager.getInstance(context);
                Calendar calendar = Calendar.getInstance();
                BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
                StepsDataModel liveStepsData2 = userDataManager.getLiveStepsData(calendar, bleApi != null ? bleApi.getMacAddress() : null);
                if (liveStepsData2 != null && liveStepsData2.getTimeStamp() > 0) {
                    WalkDBWrite walkDBWrite = WalkDBWrite.getInstance(context);
                    StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                    walkDBWrite.insertDailyData(stepsDataHelper.getConvertedDailyWalkData(context, liveStepsData2));
                    if (liveStepsData2.getSteps() != 0 && (Intrinsics.areEqual(liveStepsData2.getCalories(), 0.0d) || liveStepsData2.getDistance() == 0)) {
                        Pair<Double, Integer> caloriesAndDistanceBySteps = stepsDataHelper.getCaloriesAndDistanceBySteps(context, liveStepsData2.getSteps());
                        double doubleValue = caloriesAndDistanceBySteps.component1().doubleValue();
                        int intValue = caloriesAndDistanceBySteps.component2().intValue();
                        liveStepsData2.setCalories(Double.valueOf(doubleValue));
                        liveStepsData2.setDistance(intValue);
                    }
                    liveStepsData = liveStepsData2;
                }
            }
        } else {
            DailyWalkData walkDataCurrentDate2 = StepsDataHelper.INSTANCE.getWalkDataCurrentDate(context);
            if (walkDataCurrentDate2 != null) {
                liveStepsData.setSteps(walkDataCurrentDate2.getValue());
                liveStepsData.setDistance(walkDataCurrentDate2.getMeters());
                liveStepsData.setCalories(Double.valueOf(walkDataCurrentDate2.getCalories()));
            }
        }
        UserDataManager userDataManager2 = UserDataManager.getInstance(context);
        Calendar calendar2 = Calendar.getInstance();
        BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
        userDataManager2.saveLiveStepsData(liveStepsData, calendar2, bleApi2 != null ? bleApi2.getMacAddress() : null);
        if (stepsDataModelListener != null) {
            stepsDataModelListener.onData(liveStepsData);
        }
    }

    public final void sendLiveStepsControlRequest(boolean z) {
        String str = this.b;
        LogHelper.d(str, "liveStepsControlRequest " + z);
        LiveStepsControlRequest liveStepsControlRequest = new LiveStepsControlRequest.Builder(z).build();
        BleApi bleApi = BleApiManager.getInstance(this.f4286a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(liveStepsControlRequest, "liveStepsControlRequest");
        bleApi.setUserSettings(liveStepsControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.StepsDataViewModel$sendLiveStepsControlRequest$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str2;
                MutableLiveData mutableLiveData;
                Intrinsics.checkNotNullParameter(error, "error");
                str2 = StepsDataViewModel.this.b;
                LogHelper.d(str2, "liveStepsControlRequest failure");
                mutableLiveData = StepsDataViewModel.this.c;
                if (mutableLiveData != null) {
                    mutableLiveData.postValue(Boolean.FALSE);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str2;
                MutableLiveData mutableLiveData;
                Intrinsics.checkNotNullParameter(response, "response");
                str2 = StepsDataViewModel.this.b;
                LogHelper.d(str2, "liveStepsControlRequest success");
                mutableLiveData = StepsDataViewModel.this.c;
                if (mutableLiveData != null) {
                    mutableLiveData.postValue(Boolean.TRUE);
                }
            }
        });
    }
}
