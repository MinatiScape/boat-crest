package com.coveiot.repository.walk.datasources.ble;

import android.content.Context;
import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BpDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BpDayData;
import com.coveiot.android.bleabstract.response.BpResponse;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleformator.BleFormator;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.bp.datasources.db.write.BpDBWrite;
import com.coveiot.repository.heartrate.datasources.db.write.HeartRateDBWrite;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.repository.sleep.datasources.db.write.SleepDBWrite;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.repository.walk.datasources.db.write.WalkDBWrite;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class BleInteractor {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void getBloodPressureData(@NotNull Calendar calendar, @NotNull final Context context, @NotNull final MutableLiveData<ResponseResult> responseResult) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(responseResult, "responseResult");
            BpDataRequest.Builder builder = new BpDataRequest.Builder();
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "calendar.time");
            BpDataRequest.Builder startDate = builder.setStartDate(time);
            Date time2 = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time2, "calendar.time");
            BleApiManager.getInstance(context).getBleApi().getData(startDate.setEndDate(time2).build(), new DataResultListener() { // from class: com.coveiot.repository.walk.datasources.ble.BleInteractor$Companion$getBloodPressureData$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = error.getErrorMsg();
                    responseResult.postValue(responseResult2);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof BpResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BpResponse");
                        BpResponse bpResponse = (BpResponse) responseData;
                        BpDBWrite bpDBWrite = BpDBWrite.getInstance(context);
                        BleFormator.Companion companion = BleFormator.Companion;
                        BpDayData bpDayData = bpResponse.getBpDayData();
                        Intrinsics.checkNotNull(bpDayData);
                        bpDBWrite.insert(companion.getDailyBpData(bpDayData));
                        BpDBWrite bpDBWrite2 = BpDBWrite.getInstance(context);
                        BpDayData bpDayData2 = bpResponse.getBpDayData();
                        Intrinsics.checkNotNull(bpDayData2);
                        bpDBWrite2.insert(companion.getHourlyBPData(bpDayData2));
                        ResponseResult responseResult2 = new ResponseResult();
                        responseResult2.isSuccess = true;
                        responseResult.postValue(responseResult2);
                        return;
                    }
                    ResponseResult responseResult3 = new ResponseResult();
                    responseResult3.isSuccess = true;
                    responseResult.postValue(responseResult3);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        }

        public final void getHeartRateData(@NotNull Calendar calendar, @NotNull final Context context, @NotNull final MutableLiveData<ResponseResult> responseResult) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(responseResult, "responseResult");
            HeartRateDataRequest.Builder builder = new HeartRateDataRequest.Builder();
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "calendar.time");
            HeartRateDataRequest.Builder startDate = builder.setStartDate(time);
            Date time2 = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time2, "calendar.time");
            BleApiManager.getInstance(context).getBleApi().getData(startDate.setEndDate(time2).build(), new DataResultListener() { // from class: com.coveiot.repository.walk.datasources.ble.BleInteractor$Companion$getHeartRateData$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = error.getErrorMsg();
                    responseResult.postValue(responseResult2);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof HeartRateResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.HeartRateResponse");
                        HeartRateResponse heartRateResponse = (HeartRateResponse) responseData;
                        HeartRateDBWrite heartRateDBWrite = HeartRateDBWrite.getInstance(context);
                        BleFormator.Companion companion = BleFormator.Companion;
                        HeartRateDayData heartRateData = heartRateResponse.getHeartRateData();
                        Intrinsics.checkNotNull(heartRateData);
                        heartRateDBWrite.insertDailyHeartRate(companion.getDailyHeartRateData(heartRateData));
                        HeartRateDBWrite heartRateDBWrite2 = HeartRateDBWrite.getInstance(context);
                        HeartRateDayData heartRateData2 = heartRateResponse.getHeartRateData();
                        Intrinsics.checkNotNull(heartRateData2);
                        heartRateDBWrite2.inserHeartRateDataList(companion.getHourlyHeartRateData(heartRateData2));
                        ResponseResult responseResult2 = new ResponseResult();
                        responseResult2.isSuccess = true;
                        responseResult.postValue(responseResult2);
                        return;
                    }
                    ResponseResult responseResult3 = new ResponseResult();
                    responseResult3.isSuccess = true;
                    responseResult.postValue(responseResult3);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        }

        public final void getSleepData(@NotNull Calendar calendar, @NotNull final Context context, @NotNull final MutableLiveData<ResponseResult> responseResult) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(responseResult, "responseResult");
            SleepDataRequest SleepDataRequest = new SleepDataRequest.Builder().setStartDate(calendar.getTime()).setEndDate(calendar.getTime()).build();
            BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
            Intrinsics.checkNotNullExpressionValue(SleepDataRequest, "SleepDataRequest");
            bleApi.getData(SleepDataRequest, new DataResultListener() { // from class: com.coveiot.repository.walk.datasources.ble.BleInteractor$Companion$getSleepData$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = error.getErrorMsg();
                    responseResult.postValue(responseResult2);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof SleepResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.SleepResponse");
                        SleepResponse sleepResponse = (SleepResponse) responseData;
                        BleFormator.Companion companion = BleFormator.Companion;
                        SleepDayData sleepDayData = sleepResponse.getSleepDayData();
                        Intrinsics.checkNotNull(sleepDayData);
                        DailySleepData dailySleepData = companion.getDailySleepData(sleepDayData);
                        dailySleepData.setSleepTarget(ProfileDBRead.getInstance(context).getLatestProfileData().sleepTarget);
                        SleepDBWrite.getInstance(context).insertDailySleepData(dailySleepData);
                        SleepDBWrite sleepDBWrite = SleepDBWrite.getInstance(context);
                        SleepDayData sleepDayData2 = sleepResponse.getSleepDayData();
                        Intrinsics.checkNotNull(sleepDayData2);
                        sleepDBWrite.insertHourlySleepData(companion.getHourlySleepData(sleepDayData2));
                        ResponseResult responseResult2 = new ResponseResult();
                        responseResult2.isSuccess = true;
                        responseResult.postValue(responseResult2);
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        }

        public final void getWalkData(@NotNull Calendar calendar, @NotNull final Context context, @NotNull final String serial_no, @NotNull final MutableLiveData<ResponseResult> responseResult) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(serial_no, "serial_no");
            Intrinsics.checkNotNullParameter(responseResult, "responseResult");
            StepsDataRequest stepsDataRequest = new StepsDataRequest.Builder().setStartDate(calendar.getTime()).setEndDate(calendar.getTime()).setStartHour(0).setEndHour(23).build();
            BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
            Intrinsics.checkNotNullExpressionValue(stepsDataRequest, "stepsDataRequest");
            bleApi.getData(stepsDataRequest, new DataResultListener() { // from class: com.coveiot.repository.walk.datasources.ble.BleInteractor$Companion$getWalkData$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = error.getErrorMsg();
                    responseResult.postValue(responseResult2);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof StepsResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.StepsResponse");
                        StepsResponse stepsResponse = (StepsResponse) responseData;
                        if (stepsResponse.getStepsDayData() != null) {
                            BleFormator.Companion companion = BleFormator.Companion;
                            StepsDayData stepsDayData = stepsResponse.getStepsDayData();
                            Intrinsics.checkNotNull(stepsDayData);
                            final DailyWalkData dailyWalkData = companion.getDailyWalkData(stepsDayData);
                            WalkDBWrite walkDBWrite = WalkDBWrite.getInstance(context);
                            StepsDayData stepsDayData2 = stepsResponse.getStepsDayData();
                            Intrinsics.checkNotNull(stepsDayData2);
                            walkDBWrite.insertHourlyStepsData(companion.getHourlySleepData(stepsDayData2));
                            dailyWalkData.setStepsTarget(ProfileDBRead.getInstance(context).getLatestProfileData().stepsTarget);
                            Handler handler = new Handler();
                            final Context context2 = context;
                            final String str = serial_no;
                            final MutableLiveData<ResponseResult> mutableLiveData = responseResult;
                            handler.postDelayed(new Runnable() { // from class: com.coveiot.repository.walk.datasources.ble.BleInteractor$Companion$getWalkData$1$onDataResponse$1
                                @Override // java.lang.Runnable
                                public void run() {
                                    DailyWalkData.this.setValue(WalkDBRead.getInstance(context2).getTotalSteps(DailyWalkData.this.mDate, str));
                                    if (BleApiManager.getInstance(context2) != null && BleApiManager.getInstance(context2).getDeviceType() == DeviceType.CZ0) {
                                        DailyWalkData dailyWalkData2 = DailyWalkData.this;
                                        dailyWalkData2.setCalories(RepositoryUtils.calculateCaloriesForCZ(dailyWalkData2.getValue(), ProfileDBRead.getInstance(context2).getLatestProfileData().height, (int) ProfileDBRead.getInstance(context2).getLatestProfileData().weight));
                                    } else {
                                        DailyWalkData dailyWalkData3 = DailyWalkData.this;
                                        dailyWalkData3.setCalories(RepositoryUtils.caluclateCaloreis(dailyWalkData3.getValue(), ProfileDBRead.getInstance(context2).getLatestProfileData().height, (int) ProfileDBRead.getInstance(context2).getLatestProfileData().weight));
                                    }
                                    DailyWalkData dailyWalkData4 = DailyWalkData.this;
                                    dailyWalkData4.setMeters(RepositoryUtils.calculateDistance(dailyWalkData4.getValue(), ProfileDBRead.getInstance(context2).getLatestProfileData().height));
                                    WalkDBWrite.getInstance(context2).insertDailyData(DailyWalkData.this);
                                    ResponseResult responseResult2 = new ResponseResult();
                                    responseResult2.isSuccess = true;
                                    mutableLiveData.postValue(responseResult2);
                                }
                            }, 1000L);
                            return;
                        }
                        ResponseResult responseResult2 = new ResponseResult();
                        responseResult2.isSuccess = true;
                        responseResult.postValue(responseResult2);
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        }
    }
}
