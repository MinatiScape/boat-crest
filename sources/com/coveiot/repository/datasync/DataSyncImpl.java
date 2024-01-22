package com.coveiot.repository.datasync;

import android.content.Context;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.repository.Error;
import com.coveiot.repository.R;
import com.coveiot.repository.RepositoryModuleNames;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.bp.datasources.db.read.BpDBRead;
import com.coveiot.repository.datasync.BleSyncUtils;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncInterface;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.repository.heartrate.datasources.db.read.HeartRateDBRead;
import com.coveiot.repository.periodicspo2.PeriodicSpo2DBRead;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.repository.temperature.datasources.db.read.TemperatureDBRead;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.utility.LogHelper;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class DataSyncImpl implements SyncInterface {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7351a;
    @Nullable
    public final String b;
    public String serial_no;

    public DataSyncImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7351a = context;
        this.b = "DataSyncImpl";
    }

    public static final void access$setLastSyncTimeToCurrent(DataSyncImpl dataSyncImpl, BleApi bleApi) {
        LogHelper.d(dataSyncImpl.b, "setLastSyncTimeToCurrent", RepositoryModuleNames.REPOSITORY.getModuleName());
        String macAddress = bleApi.getMacAddress();
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(dataSyncImpl.f7351a).getDeviceInfoBy(macAddress);
        if (deviceInfoBy == null) {
            deviceInfoBy = new EntityDeviceInfo();
            deviceInfoBy.setMacAddress(macAddress);
        }
        deviceInfoBy.setActive(true);
        deviceInfoBy.setLastSyncDateWalk(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLastSyncDateSleep(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLastSyncDateHr(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLastSyncDateBp(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLastSyncDateRr(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLastSyncDateTemperature(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLastSyncDateSpo2(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLastSyncDatePeriodicSpo2(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        deviceInfoBy.setLasySyncTime(System.currentTimeMillis());
        DeviceInfoRepository.getInstance(dataSyncImpl.f7351a).insertDeviceInfo(deviceInfoBy);
    }

    public static final void access$setLastSyncTimeToZero(DataSyncImpl dataSyncImpl, BleApi bleApi) {
        dataSyncImpl.getClass();
        String macAddress = bleApi.getMacAddress();
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(dataSyncImpl.f7351a).getDeviceInfoBy(macAddress);
        if (deviceInfoBy == null) {
            deviceInfoBy = new EntityDeviceInfo();
            deviceInfoBy.setMacAddress(macAddress);
        }
        deviceInfoBy.setActive(true);
        deviceInfoBy.setLastSyncDateWalk(null);
        deviceInfoBy.setLastSyncDateSleep(null);
        deviceInfoBy.setLastSyncDateHr(null);
        deviceInfoBy.setLastSyncDateBp(null);
        deviceInfoBy.setLastSyncDateRr(null);
        deviceInfoBy.setLastSyncDateTemperature(null);
        deviceInfoBy.setLastSyncDatePeriodicSpo2(null);
        deviceInfoBy.setLastSyncDateSpo2(null);
        deviceInfoBy.setLastSyncDateDistanceData(null);
        deviceInfoBy.setLastSyncDateCalorieData(null);
        deviceInfoBy.setLasySyncTime(0L);
        DeviceInfoRepository.getInstance(dataSyncImpl.f7351a).insertDeviceInfo(deviceInfoBy);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void clearCommandQueue(@NotNull BleApi bleApi) {
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        bleApi.clearCommandQueue();
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void fetchDataFromWatchFromLastSync(@NotNull final SyncResultListner syncResultListner, @NotNull final BleApi bleApi, boolean z, @NotNull SyncCompleteListner syncCompleteListner) {
        int i;
        int i2;
        Date date;
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        LogHelper.d(this.b, "fetchDataFromWatchFromLastSync", RepositoryModuleNames.REPOSITORY.getModuleName());
        if (bleApi.getConnectionStatus() != ConnectionStatus.CONNECTED) {
            String string = this.f7351a.getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
            syncResultListner.onFailure(new Error(string, Integer.valueOf(CommandError.COMMAND_FAILED.value), "steps"));
            return;
        }
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(this.f7351a).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfStepsDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand();
        int findDateDifference = (deviceInfoBy == null || deviceInfoBy.getLastSyncDateWalk() == null || RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateWalk()) > maxDaysOfStepsDataOnBand) ? maxDaysOfStepsDataOnBand : RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateWalk());
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -findDateDifference);
        Date startDateWalk = calendar.getTime();
        Date endDateWalk = Calendar.getInstance().getTime();
        int maxDaysOfSleepDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfSleepDataOnBand();
        if (deviceInfoBy == null || deviceInfoBy.getLastSyncDateSleep() == null) {
            maxDaysOfSleepDataOnBand = maxDaysOfStepsDataOnBand;
        } else if (RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSleep()) <= maxDaysOfSleepDataOnBand) {
            maxDaysOfSleepDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSleep());
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(6, -maxDaysOfSleepDataOnBand);
        Date startDateSleep = calendar2.getTime();
        Date endDateSleep = Calendar.getInstance().getTime();
        int maxDaysOfHeartRateDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand();
        if (deviceInfoBy == null || deviceInfoBy.getLastSyncDateHr() == null) {
            maxDaysOfHeartRateDataOnBand = maxDaysOfStepsDataOnBand;
        } else if (RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateHr()) <= maxDaysOfHeartRateDataOnBand) {
            maxDaysOfHeartRateDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateHr());
        }
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(6, -maxDaysOfHeartRateDataOnBand);
        Date startDateHr = calendar3.getTime();
        Date endDateHr = Calendar.getInstance().getTime();
        int maxDaysOfBpDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfBpDataOnBand();
        if (deviceInfoBy != null && deviceInfoBy.getLastSyncDateBp() != null) {
            maxDaysOfStepsDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateHr()) <= maxDaysOfBpDataOnBand ? RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateBp()) : maxDaysOfBpDataOnBand;
        }
        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(6, -maxDaysOfStepsDataOnBand);
        Date startDateBp = calendar4.getTime();
        Date endDateBp = Calendar.getInstance().getTime();
        int maxDaysOfRrDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfRrDataOnBand();
        if (deviceInfoBy == null || deviceInfoBy.getLastSyncDateRr() == null || RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateRr()) > maxDaysOfRrDataOnBand) {
            i = maxDaysOfRrDataOnBand;
            i2 = i;
        } else {
            i = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateRr());
            i2 = maxDaysOfRrDataOnBand;
        }
        Calendar calendar5 = Calendar.getInstance();
        calendar5.add(6, -i);
        Date startDateRr = calendar5.getTime();
        Date endDateRr = Calendar.getInstance().getTime();
        int findDateDifference2 = (deviceInfoBy == null || deviceInfoBy.getLastSyncDateTemperature() == null || RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateTemperature()) > bleApi.getDeviceSupportedFeatures().getMaxDaysOfRrDataOnBand()) ? i2 : RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateTemperature());
        Calendar calendar6 = Calendar.getInstance();
        calendar6.add(6, -findDateDifference2);
        Date startDateTemp = calendar6.getTime();
        Date endDateTemp = Calendar.getInstance().getTime();
        int maxDaysOfRrDataOnBand2 = bleApi.getDeviceSupportedFeatures().getMaxDaysOfRrDataOnBand();
        if (deviceInfoBy == null || deviceInfoBy.getLastSyncDatePeriodicSpo2() == null) {
            maxDaysOfRrDataOnBand2 = i2;
        } else if (RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDatePeriodicSpo2()) <= maxDaysOfRrDataOnBand2) {
            maxDaysOfRrDataOnBand2 = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDatePeriodicSpo2());
        }
        Calendar calendar7 = Calendar.getInstance();
        calendar7.add(6, -maxDaysOfRrDataOnBand2);
        Date startDateSpo2 = calendar7.getTime();
        Date time = Calendar.getInstance().getTime();
        int maxDaysOfStepsDataOnBand2 = bleApi.getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand();
        if (deviceInfoBy == null || deviceInfoBy.getLastSyncDateDistanceData() == null) {
            date = time;
        } else {
            date = time;
            if (RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateDistanceData()) <= maxDaysOfStepsDataOnBand2) {
                maxDaysOfStepsDataOnBand2 = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateDistanceData());
            }
        }
        Calendar calendar8 = Calendar.getInstance();
        calendar8.add(6, -maxDaysOfStepsDataOnBand2);
        Date startDateDistance = calendar8.getTime();
        Date endDateDistance = Calendar.getInstance().getTime();
        BleSyncUtils companion = BleSyncUtils.Companion.getInstance(this.f7351a, getSerial_no());
        Intrinsics.checkNotNull(companion);
        Intrinsics.checkNotNullExpressionValue(startDateWalk, "startDateWalk");
        Intrinsics.checkNotNullExpressionValue(endDateWalk, "endDateWalk");
        Intrinsics.checkNotNullExpressionValue(startDateSleep, "startDateSleep");
        Intrinsics.checkNotNullExpressionValue(endDateSleep, "endDateSleep");
        Intrinsics.checkNotNullExpressionValue(startDateHr, "startDateHr");
        Intrinsics.checkNotNullExpressionValue(endDateHr, "endDateHr");
        Intrinsics.checkNotNullExpressionValue(startDateBp, "startDateBp");
        Intrinsics.checkNotNullExpressionValue(endDateBp, "endDateBp");
        Intrinsics.checkNotNullExpressionValue(startDateRr, "startDateRr");
        Intrinsics.checkNotNullExpressionValue(endDateRr, "endDateRr");
        Intrinsics.checkNotNullExpressionValue(startDateTemp, "startDateTemp");
        Intrinsics.checkNotNullExpressionValue(endDateTemp, "endDateTemp");
        Intrinsics.checkNotNullExpressionValue(startDateSpo2, "startDateSpo2");
        Date endDateSpo2 = date;
        Intrinsics.checkNotNullExpressionValue(endDateSpo2, "endDateSpo2");
        Intrinsics.checkNotNullExpressionValue(startDateDistance, "startDateDistance");
        Intrinsics.checkNotNullExpressionValue(endDateDistance, "endDateDistance");
        companion.syncData(startDateWalk, endDateWalk, startDateSleep, endDateSleep, startDateHr, endDateHr, startDateBp, endDateBp, startDateRr, endDateRr, startDateTemp, endDateTemp, startDateSpo2, date, startDateDistance, endDateDistance, new SyncResultListner() { // from class: com.coveiot.repository.datasync.DataSyncImpl$fetchDataFromWatchFromLastSync$1
            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(@NotNull Error message) {
                String str;
                Intrinsics.checkNotNullParameter(message, "message");
                syncResultListner.onFailure(message);
                new AnalyticsLog();
                str = DataSyncImpl.this.b;
                LogHelper.d(str, "fetchDataFromWatchFromLastSync failure", RepositoryModuleNames.REPOSITORY.getModuleName());
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                syncResultListner.onProgressUpdate(progressDataBean);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                String str;
                DataSyncImpl.access$setLastSyncTimeToCurrent(DataSyncImpl.this, bleApi);
                syncResultListner.onSuccess();
                str = DataSyncImpl.this.b;
                LogHelper.d(str, "fetchDataFromWatchFromLastSync success", RepositoryModuleNames.REPOSITORY.getModuleName());
            }
        }, bleApi, z, syncCompleteListner);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void fetchThreeMonthsDataFromServer(@NotNull final SyncResultListner syncResultListner, @NotNull final BleApi bleApi) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        LogHelper.d(this.b, "fetchThreeMonthsDataFromServer", RepositoryModuleNames.REPOSITORY.getModuleName());
        ServerSyncUtils.Companion.getInstance(this.f7351a).fetchThreeMonthsDataFromServer(getSerial_no(), new SyncResultListner() { // from class: com.coveiot.repository.datasync.DataSyncImpl$fetchThreeMonthsDataFromServer$1
            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(@NotNull Error message) {
                Intrinsics.checkNotNullParameter(message, "message");
                DataSyncImpl.access$setLastSyncTimeToZero(this, bleApi);
                SyncResultListner.this.onFailure(message);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                SyncResultListner.this.onProgressUpdate(progressDataBean);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                SyncResultListner.this.onSuccess();
            }
        });
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void fetchTodaysDataFromWatch(@NotNull final SyncResultListner syncResultListner, @NotNull final BleApi bleApi, boolean z, @NotNull SyncCompleteListner syncCompleteListner) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        Date startDate = Calendar.getInstance().getTime();
        Date endDate = Calendar.getInstance().getTime();
        if (bleApi.getConnectionStatus() != ConnectionStatus.CONNECTED) {
            String string = this.f7351a.getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
            syncResultListner.onFailure(new Error(string, Integer.valueOf(CommandError.COMMAND_FAILED.value), "steps"));
            return;
        }
        BleSyncUtils.Companion companion = BleSyncUtils.Companion;
        companion.clearInstance();
        LogHelper.d(this.b, "syncData", RepositoryModuleNames.REPOSITORY.getModuleName());
        BleSyncUtils companion2 = companion.getInstance(this.f7351a, getSerial_no());
        Intrinsics.checkNotNull(companion2);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        companion2.syncData(startDate, endDate, startDate, endDate, startDate, endDate, startDate, endDate, startDate, endDate, startDate, endDate, startDate, endDate, startDate, endDate, new SyncResultListner() { // from class: com.coveiot.repository.datasync.DataSyncImpl$fetchTodaysDataFromWatch$1
            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(@NotNull Error message) {
                String str;
                Intrinsics.checkNotNullParameter(message, "message");
                syncResultListner.onFailure(message);
                str = DataSyncImpl.this.b;
                LogHelper.d(str, "fetchTodaysDataFromWatch failed", RepositoryModuleNames.REPOSITORY.getModuleName());
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                syncResultListner.onProgressUpdate(progressDataBean);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                String str;
                DataSyncImpl.access$setLastSyncTimeToZero(DataSyncImpl.this, bleApi);
                str = DataSyncImpl.this.b;
                LogHelper.d(str, "fetchTodaysDataFromWatch success", RepositoryModuleNames.REPOSITORY.getModuleName());
                syncResultListner.onSuccess();
            }
        }, bleApi, z, syncCompleteListner);
    }

    @NotNull
    public final Context getContext() {
        return this.f7351a;
    }

    @NotNull
    public final String getSerial_no() {
        String str = this.serial_no;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("serial_no");
        return null;
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public boolean isDataAvailableInDBForCurrentDWatch() {
        return WalkDBRead.getInstance(this.f7351a).getRowCount(getSerial_no()) > 0 || SleepDBRead.getInstance(this.f7351a).getRowCount(getSerial_no()) > 0 || HeartRateDBRead.getInstance(this.f7351a).getRowCount(getSerial_no()) > 0 || TemperatureDBRead.getInstance(this.f7351a).getRowCount(getSerial_no()) > 0 || BpDBRead.getInstance(this.f7351a).getRowCount(getSerial_no()) > 0 || PeriodicSpo2DBRead.getInstance(this.f7351a).getRowCount(getSerial_no()) > 0;
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void readDeviceInformation(@NotNull SyncResultListner syncResultListner, @NotNull BleApi bleApi) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        syncResultListner.onSuccess();
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void readSerialNumberFromDevice(@NotNull SyncResultListner syncResultListner, @NotNull BleApi bleApi) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        new DeviceInfoRequest.Builder().setIsSerialNo(true).build();
        setSerial_no(bleApi.getMacAddress());
        syncResultListner.onSuccess();
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void saveHRVStressInformationToServer(@NotNull final SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        LogHelper.d(this.b, "saveHRVStressInformationToServer");
        ServerSyncUtils.Companion.getInstance(this.f7351a).saveHRVStressDataToServer(this.f7351a, new SyncResultListner() { // from class: com.coveiot.repository.datasync.DataSyncImpl$saveHRVStressInformationToServer$1
            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(@Nullable Error error) {
                String str;
                SyncResultListner.this.onFailure(error);
                str = this.b;
                LogHelper.d(str, "saveHRVStressInformationToServer failure");
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                String str;
                SyncResultListner.this.onSuccess();
                str = this.b;
                LogHelper.d(str, "saveHRVStressInformationToServer success");
            }
        });
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void saveInformationToServer(@NotNull final SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        LogHelper.d(this.b, "saveInformationToServer", RepositoryModuleNames.REPOSITORY.getModuleName());
        ServerSyncUtils.Companion.getInstance(this.f7351a).saveDataToServer(getSerial_no(), new SyncResultListner() { // from class: com.coveiot.repository.datasync.DataSyncImpl$saveInformationToServer$1
            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(@Nullable Error error) {
                String str;
                SyncResultListner.this.onFailure(error);
                str = this.b;
                LogHelper.d(str, "saveInformationToServer success", RepositoryModuleNames.REPOSITORY.getModuleName());
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                SyncResultListner.this.onProgressUpdate(progressDataBean);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                String str;
                SyncResultListner.this.onSuccess();
                str = this.b;
                LogHelper.d(str, "saveInformationToServer success", RepositoryModuleNames.REPOSITORY.getModuleName());
            }
        });
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncInterface
    public void saveSpo2InformationToServer(@NotNull final SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        LogHelper.d(this.b, "saveSpo2InformationToServer", RepositoryModuleNames.REPOSITORY.getModuleName());
        ServerSyncUtils.Companion.getInstance(this.f7351a).saveSPO2DataToServer(this.f7351a, new SyncResultListner() { // from class: com.coveiot.repository.datasync.DataSyncImpl$saveSpo2InformationToServer$1
            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(@Nullable Error error) {
                String str;
                SyncResultListner.this.onFailure(error);
                str = this.b;
                LogHelper.d(str, "saveSpo2InformationToServer failure", RepositoryModuleNames.REPOSITORY.getModuleName());
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                String str;
                SyncResultListner.this.onSuccess();
                str = this.b;
                LogHelper.d(str, "saveSpo2InformationToServer success", RepositoryModuleNames.REPOSITORY.getModuleName());
            }
        });
    }

    public final void setSerial_no(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.serial_no = str;
    }
}
