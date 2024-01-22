package com.coveiot.repository.datasync.domainlogic;

import com.coveiot.android.bleabstract.api.BleApi;
/* loaded from: classes9.dex */
public interface SyncInterface {
    void clearCommandQueue(BleApi bleApi);

    void fetchDataFromWatchFromLastSync(SyncResultListner syncResultListner, BleApi bleApi, boolean z, SyncCompleteListner syncCompleteListner);

    void fetchThreeMonthsDataFromServer(SyncResultListner syncResultListner, BleApi bleApi);

    void fetchTodaysDataFromWatch(SyncResultListner syncResultListner, BleApi bleApi, boolean z, SyncCompleteListner syncCompleteListner);

    boolean isDataAvailableInDBForCurrentDWatch();

    void readDeviceInformation(SyncResultListner syncResultListner, BleApi bleApi);

    void readSerialNumberFromDevice(SyncResultListner syncResultListner, BleApi bleApi);

    void saveHRVStressInformationToServer(SyncResultListner syncResultListner);

    void saveInformationToServer(SyncResultListner syncResultListner);

    void saveSpo2InformationToServer(SyncResultListner syncResultListner);
}
