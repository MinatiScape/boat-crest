package com.coveiot.repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.repository.bp.datasources.db.read.BpDBRead;
import com.coveiot.repository.bp.datasources.db.write.BpDBWrite;
import com.coveiot.repository.bp.datasources.server.BpApiHandler;
import com.coveiot.repository.heartrate.datasources.db.read.HeartRateDBRead;
import com.coveiot.repository.heartrate.datasources.db.write.HeartRateDBWrite;
import com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.repository.rr.datasources.db.read.RrDbRead;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.repository.sleep.datasources.db.write.SleepDBWrite;
import com.coveiot.repository.sleep.datasources.server.SleepApiHandler;
import com.coveiot.repository.walk.datasources.ble.BleInteractor;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.repository.walk.datasources.db.write.WalkDBWrite;
import com.coveiot.repository.walk.datasources.server.WalkApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class FlowValidator {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7296a;
    public MutableLiveData<ResponseResult> responseResult;

    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            ActivityType.values();
            int[] iArr = new int[16];
            try {
                ActivityType activityType = ActivityType.WALK;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                ActivityType activityType2 = ActivityType.SLEEP;
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                ActivityType activityType3 = ActivityType.HEARTRATE;
                iArr[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                ActivityType activityType4 = ActivityType.BP;
                iArr[3] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                ActivityType activityType5 = ActivityType.RR_HISTORY;
                iArr[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FlowValidator(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7296a = context;
    }

    public final void a(Calendar calendar, final String str, ActivityType activityType) {
        if (!RepositoryUtils.isNetConnected(this.f7296a)) {
            ResponseResult responseResult = new ResponseResult();
            responseResult.isSuccess = false;
            responseResult.errorMessage = this.f7296a.getString(R.string.check_internet);
            getResponseResult().postValue(responseResult);
            return;
        }
        int ordinal = activityType.ordinal();
        if (ordinal == 0) {
            WalkApiHandler.Companion.fetchDatafor(calendar, new WalkApiHandler.WalkDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$fetchWalkDataFromServer$1
                @Override // com.coveiot.repository.walk.datasources.server.WalkApiHandler.WalkDataApiResultListner
                public void onError(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = message;
                    this.getResponseResult().postValue(responseResult2);
                }

                @Override // com.coveiot.repository.walk.datasources.server.WalkApiHandler.WalkDataApiResultListner
                public void onWalkDataReceived(@NotNull DailyWalkData dailyWalkData, @NotNull ArrayList<HourlyWalkData> hourlyWalkData, boolean z) {
                    Intrinsics.checkNotNullParameter(dailyWalkData, "dailyWalkData");
                    Intrinsics.checkNotNullParameter(hourlyWalkData, "hourlyWalkData");
                    dailyWalkData.mac_address = str;
                    int size = hourlyWalkData.size();
                    if (size >= 0) {
                        int i = 0;
                        while (true) {
                            hourlyWalkData.get(i).mac_address = str;
                            if (i == size) {
                                break;
                            }
                            i++;
                        }
                    }
                    WalkDBWrite.getInstance(this.getContext()).insertDailyData(dailyWalkData);
                    WalkDBWrite.getInstance(this.getContext()).insertHourlyStepsData(hourlyWalkData);
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = true;
                    if (z) {
                        this.getResponseResult().postValue(responseResult2);
                    }
                }
            });
        } else if (ordinal == 1) {
            SleepApiHandler.Companion.fetchDatafor(calendar, new SleepApiHandler.SleepDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$fetchSleepDataFromServer$1
                @Override // com.coveiot.repository.sleep.datasources.server.SleepApiHandler.SleepDataApiResultListner
                public void onError(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = message;
                    this.getResponseResult().postValue(responseResult2);
                }

                @Override // com.coveiot.repository.sleep.datasources.server.SleepApiHandler.SleepDataApiResultListner
                public void onSleepDataReceived(@NotNull DailySleepData dailySleepData, @NotNull ArrayList<HourlySleepData> hourlySleepData, boolean z) {
                    Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
                    Intrinsics.checkNotNullParameter(hourlySleepData, "hourlySleepData");
                    dailySleepData.mac_address = str;
                    int size = hourlySleepData.size();
                    if (size >= 0) {
                        int i = 0;
                        while (true) {
                            hourlySleepData.get(i).mac_address = str;
                            if (i == size) {
                                break;
                            }
                            i++;
                        }
                    }
                    SleepDBWrite.getInstance(this.getContext()).insertDailySleepData(dailySleepData);
                    SleepDBWrite.getInstance(this.getContext()).insertHourlySleepData(hourlySleepData);
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = true;
                    if (z) {
                        this.getResponseResult().postValue(responseResult2);
                    }
                }
            });
        } else if (ordinal == 2) {
            HeartRateApiHandler.Companion.fetchDatafor(calendar, new HeartRateApiHandler.HeartRateDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$fetchHeartRateDataFromServer$1
                @Override // com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler.HeartRateDataApiResultListner
                public void onError(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = message;
                    this.getResponseResult().postValue(responseResult2);
                }

                @Override // com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler.HeartRateDataApiResultListner
                public void onHeartRateDataReceived(@NotNull EntityDailyHeartRateData dailyHeartRateData, @NotNull ArrayList<EntityHourlyHeartRateData> hourlyHeartRateData, boolean z) {
                    Intrinsics.checkNotNullParameter(dailyHeartRateData, "dailyHeartRateData");
                    Intrinsics.checkNotNullParameter(hourlyHeartRateData, "hourlyHeartRateData");
                    dailyHeartRateData.setSerialNo(str);
                    int size = hourlyHeartRateData.size();
                    if (size >= 0) {
                        int i = 0;
                        while (true) {
                            hourlyHeartRateData.get(i).serial_no = str;
                            if (i == size) {
                                break;
                            }
                            i++;
                        }
                    }
                    HeartRateDBWrite.getInstance(this.getContext()).insertDailyHeartRate(dailyHeartRateData);
                    HeartRateDBWrite.getInstance(this.getContext()).inserHeartRateDataList(hourlyHeartRateData);
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = true;
                    if (z) {
                        this.getResponseResult().postValue(responseResult2);
                    }
                }
            });
        } else if (ordinal != 3) {
            a();
        } else {
            BpApiHandler.Companion.fetchDatafor(calendar, new BpApiHandler.BPDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$fetchBPDataFromServer$1
                @Override // com.coveiot.repository.bp.datasources.server.BpApiHandler.BPDataApiResultListner
                public void onBPDataReceived(@NotNull EntityDailyBp dailyBpData, @NotNull ArrayList<EntityHourlyBp> hourlyBpData, boolean z) {
                    Intrinsics.checkNotNullParameter(dailyBpData, "dailyBpData");
                    Intrinsics.checkNotNullParameter(hourlyBpData, "hourlyBpData");
                    dailyBpData.serial_no = str;
                    int size = hourlyBpData.size();
                    if (size >= 0) {
                        int i = 0;
                        while (true) {
                            hourlyBpData.get(i).serial_no = str;
                            if (i == size) {
                                break;
                            }
                            i++;
                        }
                    }
                    BpDBWrite.getInstance(this.getContext()).insert(dailyBpData);
                    BpDBWrite.getInstance(this.getContext()).insert(hourlyBpData);
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = true;
                    if (z) {
                        this.getResponseResult().postValue(responseResult2);
                    }
                }

                @Override // com.coveiot.repository.bp.datasources.server.BpApiHandler.BPDataApiResultListner
                public void onError(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    ResponseResult responseResult2 = new ResponseResult();
                    responseResult2.isSuccess = false;
                    responseResult2.errorMessage = message;
                    this.getResponseResult().postValue(responseResult2);
                }
            });
        }
    }

    public final void b(Calendar calendar, String str, ActivityType activityType) {
        int ordinal = activityType.ordinal();
        if (ordinal == 0) {
            BleInteractor.Companion.getWalkData(calendar, this.f7296a, str, getResponseResult());
        } else if (ordinal == 1) {
            BleInteractor.Companion.getSleepData(calendar, this.f7296a, getResponseResult());
        } else if (ordinal == 2) {
            BleInteractor.Companion.getHeartRateData(calendar, this.f7296a, getResponseResult());
        } else if (ordinal != 3) {
        } else {
            BleInteractor.Companion.getBloodPressureData(calendar, this.f7296a, getResponseResult());
        }
    }

    public final boolean c(Calendar calendar, String str, ActivityType activityType) {
        return activityType == ActivityType.WALK ? WalkDBRead.getInstance(this.f7296a).getRowCountForDailyData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), str) > 0 : activityType == ActivityType.SLEEP ? SleepDBRead.getInstance(this.f7296a).getRowCountForDailyData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), str) > 0 : activityType == ActivityType.HEARTRATE ? HeartRateDBRead.getInstance(this.f7296a).getRowCountForDailyData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), str) > 0 : activityType == ActivityType.BP ? BpDBRead.getInstance(this.f7296a).getRowCountForDailyData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), str) > 0 : activityType == ActivityType.RR_HISTORY && RrDbRead.getInstance(this.f7296a).getRowCountForDailyData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), str) > 0;
    }

    @NotNull
    public final Context getContext() {
        return this.f7296a;
    }

    @NotNull
    public final MutableLiveData<ResponseResult> getResponseResult() {
        MutableLiveData<ResponseResult> mutableLiveData = this.responseResult;
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("responseResult");
        return null;
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.responseResult = mutableLiveData;
    }

    public final void validateAndProceed(@NotNull Calendar calendar, @NotNull String serial_no, @NotNull ActivityType type, @NotNull MutableLiveData<ResponseResult> responseResult) {
        int maxDaysOfStepsDataOnBand;
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        setResponseResult(responseResult);
        if (RepositoryUtils.isToday(calendar)) {
            if (BleApiManager.getInstance(this.f7296a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                b(calendar, serial_no, type);
                return;
            } else {
                a();
                return;
            }
        }
        int ordinal = type.ordinal();
        if (ordinal == 0) {
            maxDaysOfStepsDataOnBand = BleApiManager.getInstance(this.f7296a).getBleApi().getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand();
        } else if (ordinal == 1) {
            maxDaysOfStepsDataOnBand = BleApiManager.getInstance(this.f7296a).getBleApi().getDeviceSupportedFeatures().getMaxDaysOfSleepDataOnBand();
        } else if (ordinal == 2) {
            maxDaysOfStepsDataOnBand = BleApiManager.getInstance(this.f7296a).getBleApi().getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand();
        } else if (ordinal != 3) {
            maxDaysOfStepsDataOnBand = ordinal != 4 ? -1 : BleApiManager.getInstance(this.f7296a).getBleApi().getDeviceSupportedFeatures().getMaxDaysOfRrDataOnBand();
        } else {
            maxDaysOfStepsDataOnBand = BleApiManager.getInstance(this.f7296a).getBleApi().getDeviceSupportedFeatures().getMaxDaysOfBpDataOnBand();
        }
        if (RepositoryUtils.findDateDifference(calendar, Calendar.getInstance()) < maxDaysOfStepsDataOnBand) {
            if (BleApiManager.getInstance(this.f7296a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                Calendar calendar2 = Calendar.getInstance();
                EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(this.f7296a).getDeviceInfoBy(serial_no);
                if (deviceInfoBy != null) {
                    calendar2.setTimeInMillis(deviceInfoBy.getLasySyncTime());
                }
                if (RepositoryUtils.findDateDifference(calendar2, calendar) < 0 && deviceInfoBy != null) {
                    a();
                } else {
                    b(calendar, serial_no, type);
                }
            } else if (c(calendar, serial_no, type)) {
                a();
            } else {
                a(calendar, serial_no, type);
            }
        } else if (c(calendar, serial_no, type)) {
            a();
        } else {
            a(calendar, serial_no, type);
        }
    }

    public final void a() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.isSuccess = true;
        getResponseResult().postValue(responseResult);
    }

    public final void validateAndProceed(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final String serial_no, @NotNull ActivityType type, @NotNull final MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        setResponseResult(responseResult);
        if (toDate.get(6) == Calendar.getInstance().get(6)) {
            toDate.add(6, -1);
        }
        if (c(fromDate, serial_no, type)) {
            a();
        } else if (!RepositoryUtils.isNetConnected(this.f7296a)) {
            ResponseResult responseResult2 = new ResponseResult();
            responseResult2.isSuccess = false;
            responseResult2.errorMessage = this.f7296a.getString(R.string.check_internet);
            responseResult.postValue(responseResult2);
        } else {
            int ordinal = type.ordinal();
            if (ordinal == 0) {
                WalkApiHandler.Companion.fetchDatafor(fromDate, toDate, new WalkApiHandler.WalkDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$validateAndProceed$1
                    @Override // com.coveiot.repository.walk.datasources.server.WalkApiHandler.WalkDataApiResultListner
                    public void onError(@NotNull String message) {
                        Intrinsics.checkNotNullParameter(message, "message");
                        ResponseResult responseResult3 = new ResponseResult();
                        responseResult3.isSuccess = false;
                        responseResult3.errorMessage = message;
                        responseResult.postValue(responseResult3);
                    }

                    @Override // com.coveiot.repository.walk.datasources.server.WalkApiHandler.WalkDataApiResultListner
                    public void onWalkDataReceived(@NotNull DailyWalkData dailyWalkData, @NotNull ArrayList<HourlyWalkData> hourlyWalkData, boolean z) {
                        Intrinsics.checkNotNullParameter(dailyWalkData, "dailyWalkData");
                        Intrinsics.checkNotNullParameter(hourlyWalkData, "hourlyWalkData");
                        dailyWalkData.mac_address = serial_no;
                        dailyWalkData.setStepsTarget(ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().stepsTarget);
                        int size = hourlyWalkData.size() - 1;
                        if (size >= 0) {
                            int i = 0;
                            while (true) {
                                hourlyWalkData.get(i).mac_address = serial_no;
                                if (i == size) {
                                    break;
                                }
                                i++;
                            }
                        }
                        WalkDBWrite.getInstance(this.getContext()).insertDailyData(dailyWalkData);
                        WalkDBWrite.getInstance(this.getContext()).insertHourlyStepsData(hourlyWalkData);
                        ResponseResult responseResult3 = new ResponseResult();
                        responseResult3.isSuccess = true;
                        if (z) {
                            responseResult.postValue(responseResult3);
                        }
                    }
                });
            } else if (ordinal == 1) {
                SleepApiHandler.Companion.fetchDatafor(fromDate, toDate, new SleepApiHandler.SleepDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$validateAndProceed$2
                    @Override // com.coveiot.repository.sleep.datasources.server.SleepApiHandler.SleepDataApiResultListner
                    public void onError(@NotNull String message) {
                        Intrinsics.checkNotNullParameter(message, "message");
                    }

                    @Override // com.coveiot.repository.sleep.datasources.server.SleepApiHandler.SleepDataApiResultListner
                    public void onSleepDataReceived(@NotNull DailySleepData dailySleepData, @NotNull ArrayList<HourlySleepData> hourlySleepData, boolean z) {
                        Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
                        Intrinsics.checkNotNullParameter(hourlySleepData, "hourlySleepData");
                        dailySleepData.mac_address = serial_no;
                        dailySleepData.setSleepTarget(ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().stepsTarget);
                        int size = hourlySleepData.size() - 1;
                        if (size >= 0) {
                            int i = 0;
                            while (true) {
                                hourlySleepData.get(i).mac_address = serial_no;
                                if (i == size) {
                                    break;
                                }
                                i++;
                            }
                        }
                        SleepDBWrite.getInstance(this.getContext()).insertDailySleepData(dailySleepData);
                        SleepDBWrite.getInstance(this.getContext()).insertHourlySleepData(hourlySleepData);
                        ResponseResult responseResult3 = new ResponseResult();
                        responseResult3.isSuccess = true;
                        if (z) {
                            responseResult.postValue(responseResult3);
                        }
                    }
                });
            } else if (ordinal == 2) {
                HeartRateApiHandler.Companion.fetchDatafor(fromDate, toDate, new HeartRateApiHandler.HeartRateDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$validateAndProceed$3
                    @Override // com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler.HeartRateDataApiResultListner
                    public void onError(@NotNull String message) {
                        Intrinsics.checkNotNullParameter(message, "message");
                        ResponseResult responseResult3 = new ResponseResult();
                        responseResult3.isSuccess = false;
                        responseResult3.errorMessage = message;
                        responseResult.postValue(responseResult3);
                    }

                    @Override // com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler.HeartRateDataApiResultListner
                    public void onHeartRateDataReceived(@NotNull EntityDailyHeartRateData dailyHeartRateData, @NotNull ArrayList<EntityHourlyHeartRateData> hourlyHeartRateData, boolean z) {
                        Intrinsics.checkNotNullParameter(dailyHeartRateData, "dailyHeartRateData");
                        Intrinsics.checkNotNullParameter(hourlyHeartRateData, "hourlyHeartRateData");
                        dailyHeartRateData.setSerialNo(serial_no);
                        int size = hourlyHeartRateData.size() - 1;
                        if (size >= 0) {
                            int i = 0;
                            while (true) {
                                hourlyHeartRateData.get(i).serial_no = serial_no;
                                if (i == size) {
                                    break;
                                }
                                i++;
                            }
                        }
                        HeartRateDBWrite.getInstance(this.getContext()).insertDailyHeartRate(dailyHeartRateData);
                        HeartRateDBWrite.getInstance(this.getContext()).inserHeartRateDataList(hourlyHeartRateData);
                        ResponseResult responseResult3 = new ResponseResult();
                        responseResult3.isSuccess = true;
                        if (z) {
                            responseResult.postValue(responseResult3);
                        }
                    }
                });
            } else if (ordinal != 3) {
                a();
            } else {
                BpApiHandler.Companion.fetchDatafor(fromDate, toDate, new BpApiHandler.BPDataApiResultListner() { // from class: com.coveiot.repository.FlowValidator$validateAndProceed$4
                    @Override // com.coveiot.repository.bp.datasources.server.BpApiHandler.BPDataApiResultListner
                    public void onBPDataReceived(@NotNull EntityDailyBp dailyBpData, @NotNull ArrayList<EntityHourlyBp> hourlyBpData, boolean z) {
                        Intrinsics.checkNotNullParameter(dailyBpData, "dailyBpData");
                        Intrinsics.checkNotNullParameter(hourlyBpData, "hourlyBpData");
                        dailyBpData.serial_no = serial_no;
                        int size = hourlyBpData.size() - 1;
                        if (size >= 0) {
                            int i = 0;
                            while (true) {
                                hourlyBpData.get(i).serial_no = serial_no;
                                if (i == size) {
                                    break;
                                }
                                i++;
                            }
                        }
                        BpDBWrite.getInstance(this.getContext()).insert(dailyBpData);
                        BpDBWrite.getInstance(this.getContext()).insert(hourlyBpData);
                        ResponseResult responseResult3 = new ResponseResult();
                        responseResult3.isSuccess = true;
                        if (z) {
                            responseResult.postValue(responseResult3);
                        }
                    }

                    @Override // com.coveiot.repository.bp.datasources.server.BpApiHandler.BPDataApiResultListner
                    public void onError(@NotNull String message) {
                        Intrinsics.checkNotNullParameter(message, "message");
                        ResponseResult responseResult3 = new ResponseResult();
                        responseResult3.isSuccess = false;
                        responseResult3.errorMessage = message;
                        responseResult.postValue(responseResult3);
                    }
                });
            }
        }
    }
}
