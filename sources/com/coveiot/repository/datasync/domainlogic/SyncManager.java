package com.coveiot.repository.datasync.domainlogic;

import a.a.a.a.a.c;
import a.a.a.a.a.d;
import a.a.a.a.a.e;
import a.a.a.a.a.f;
import a.a.a.a.a.g;
import a.a.a.a.a.h;
import a.a.a.a.a.i;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.work.PeriodicWorkRequest;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.repository.Error;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.BleSyncUtils;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.ServerSyncUtils;
import com.coveiot.repository.sedentary.datasource.SedentarySettings;
import java.util.Calendar;
import java.util.LinkedHashMap;
/* loaded from: classes9.dex */
public class SyncManager {
    public static SyncManager f;

    /* renamed from: a  reason: collision with root package name */
    public SyncInterface f7384a;
    public Context b;
    public boolean c = false;
    public long d = -1;
    public SyncState e;

    /* loaded from: classes9.dex */
    public class a implements SyncResultListner {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SyncCompleteListner f7385a;

        public a(SyncManager syncManager, SyncCompleteListner syncCompleteListner) {
            this.f7385a = syncCompleteListner;
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
            this.f7385a.onDataSyncComplete();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements SyncResultListner {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SyncCompleteListner f7386a;

        public b(SyncManager syncManager, SyncCompleteListner syncCompleteListner) {
            this.f7386a = syncCompleteListner;
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
            this.f7386a.onDataSyncComplete();
        }
    }

    public SyncManager(SyncInterface syncInterface, Context context) {
        this.f7384a = syncInterface;
        this.b = context;
    }

    public static void a(SyncManager syncManager, Error error, SyncCompleteListner syncCompleteListner) {
        syncManager.getClass();
        SyncState syncState = new SyncState(false, System.currentTimeMillis());
        syncManager.e = syncState;
        syncState.setErrorCode(error.getCode().intValue());
        syncManager.e.setErrorMsg(error.getMessage());
        syncManager.c = false;
        syncCompleteListner.onFailure(error.getMessage(), error);
    }

    public static SyncManager getInstance() {
        SyncManager syncManager = f;
        if (syncManager != null) {
            return syncManager;
        }
        throw new RuntimeException("First call initialize on SyncManager");
    }

    public static void initialze(SyncInterface syncInterface, Context context) {
        if (f == null) {
            f = new SyncManager(syncInterface, context);
        }
    }

    public void clearBleSyncUtilsInstance() {
        BleSyncUtils.Companion.clearInstance();
    }

    public void getSedentaryAlertsFromServer(Calendar calendar, Calendar calendar2) {
        ServerSyncUtils.Companion.getInstance(this.b).fetchSedentaryAlertsData(calendar, calendar2);
    }

    public SyncState getSyncState() {
        return this.e;
    }

    public boolean isSyncInProgress() {
        if (this.c && System.currentTimeMillis() - this.d > PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
            this.c = false;
        }
        return this.c;
    }

    public void resetSyncProgress() {
        this.c = false;
    }

    public void syncData(SyncCompleteListner syncCompleteListner, boolean z) {
        boolean z2 = false;
        if (!z) {
            if (BleApiManager.getInstance(this.b).getDeviceType() == DeviceType.v7 || RepositoryUtils.isJStyleDevice(this.b) || RepositoryUtils.isMoyangDevice(this.b) || RepositoryUtils.isMatrixDevice(this.b)) {
                int i = Calendar.getInstance().get(11);
                if (!RepositoryUtils.isMoyangDevice(this.b)) {
                }
                this.d = System.currentTimeMillis();
                BleApi bleApi = BleApiManager.getInstance(this.b).getBleApi();
                this.f7384a.clearCommandQueue(bleApi);
                this.c = true;
                this.f7384a.readSerialNumberFromDevice(new a.a.a.a.a.a(this, syncCompleteListner, bleApi, z2), bleApi);
            }
        }
        z2 = true;
        this.d = System.currentTimeMillis();
        BleApi bleApi2 = BleApiManager.getInstance(this.b).getBleApi();
        this.f7384a.clearCommandQueue(bleApi2);
        this.c = true;
        this.f7384a.readSerialNumberFromDevice(new a.a.a.a.a.a(this, syncCompleteListner, bleApi2, z2), bleApi2);
    }

    public void syncDynamicHrData(SyncCompleteListner syncCompleteListner) {
        BleSyncUtils.Companion.getInstance(this.b, "").syncDynamicHeartRate(new c(this), BleApiManager.getInstance(this.b).getBleApi(), syncCompleteListner);
    }

    public void syncECGData(LinkedHashMap<String, String> linkedHashMap, Bitmap bitmap, Bitmap bitmap2, SyncCompleteListner syncCompleteListner) {
        BleSyncUtils.Companion.getInstance(this.b, "").getECGDataFromDevice(linkedHashMap, bitmap, bitmap2, new d(this, syncCompleteListner), BleApiManager.getInstance(this.b).getBleApi());
    }

    public void syncManualBpData(SyncResultListner syncResultListner) {
        BleApi bleApi = BleApiManager.getInstance(this.b).getBleApi();
        BleSyncUtils.Companion.getInstance(this.b, bleApi.getMacAddress()).syncManualBpData(new e(this, syncResultListner), bleApi);
    }

    public void syncManualHRVData(SyncCompleteListner syncCompleteListner) {
        BleApi bleApi = BleApiManager.getInstance(this.b).getBleApi();
        BleSyncUtils.Companion.getInstance(this.b, bleApi.getMacAddress()).syncManualHRVData(new i(this, syncCompleteListner), bleApi, syncCompleteListner);
    }

    public void syncManualSpo2Data(SyncCompleteListner syncCompleteListner) {
        BleApi bleApi = BleApiManager.getInstance(this.b).getBleApi();
        BleSyncUtils.Companion.getInstance(this.b, bleApi.getMacAddress()).syncManualSpo2Data(new f(this, syncCompleteListner), bleApi, syncCompleteListner);
    }

    public void syncManualStressData(SyncCompleteListner syncCompleteListner) {
        BleApiManager.getInstance(this.b).getBleApi().getMacAddress();
        this.f7384a.saveHRVStressInformationToServer(new a.a.a.a.a.b(this));
        syncCompleteListner.onDataSyncComplete();
    }

    public void syncRawAccelerometerHistoryData(SyncCompleteListner syncCompleteListner) {
        BleSyncUtils.Companion.getInstance(this.b, BleApiManager.getInstance(this.b).getBleApi().getMacAddress()).syncRawAccelerometerHistoryData(new b(this, syncCompleteListner), BleApiManager.getInstance(this.b).getBleApi());
    }

    public void syncRawPPGHistoryData(SyncCompleteListner syncCompleteListner) {
        BleSyncUtils.Companion.getInstance(this.b, BleApiManager.getInstance(this.b).getBleApi().getMacAddress()).syncRawPPGHistoryData(new a(this, syncCompleteListner), BleApiManager.getInstance(this.b).getBleApi());
    }

    public void syncSedentaryData(SyncCompleteListner syncCompleteListner, SedentarySettings sedentarySettings) {
        BleApi bleApi = BleApiManager.getInstance(this.b).getBleApi();
        BleSyncUtils.Companion.getInstance(this.b, bleApi.getMacAddress()).syncSedentaryData(new g(this, syncCompleteListner), bleApi, sedentarySettings);
    }

    public void syncSensAISummaryData(SyncCompleteListner syncCompleteListner) {
        BleApi bleApi = BleApiManager.getInstance(this.b).getBleApi();
        BleSyncUtils.Companion.getInstance(this.b, bleApi.getMacAddress()).syncSensAISummaryData(new h(this, syncCompleteListner), bleApi, syncCompleteListner);
    }

    public void syncECGData(LinkedHashMap<String, String> linkedHashMap, Bitmap bitmap, Bitmap bitmap2, SyncCompleteListner syncCompleteListner, DeviceType deviceType) {
        BleSyncUtils.Companion.getInstance(this.b, "").getECGDataFromDevice(linkedHashMap, bitmap, bitmap2, new d(this, syncCompleteListner), BleApiManager.getInstance(this.b).getSecondaryBleImpl(deviceType));
    }

    public void syncManualBpData(SyncResultListner syncResultListner, DeviceType deviceType) {
        BleApi secondaryBleImpl = BleApiManager.getInstance(this.b).getSecondaryBleImpl(deviceType);
        BleSyncUtils.Companion.getInstance(this.b, secondaryBleImpl.getMacAddress()).syncManualBpData(new e(this, syncResultListner), secondaryBleImpl);
    }

    public void syncManualSpo2Data(SyncCompleteListner syncCompleteListner, DeviceType deviceType) {
        BleApi secondaryBleImpl = BleApiManager.getInstance(this.b).getSecondaryBleImpl(deviceType);
        BleSyncUtils.Companion.getInstance(this.b, secondaryBleImpl.getMacAddress()).syncManualSpo2Data(new f(this, syncCompleteListner), secondaryBleImpl, syncCompleteListner);
    }

    public void syncData(SyncCompleteListner syncCompleteListner, DeviceType deviceType, boolean z) {
        BleApi secondaryBleImpl = BleApiManager.getInstance(this.b).getSecondaryBleImpl(deviceType);
        this.f7384a.clearCommandQueue(secondaryBleImpl);
        this.c = true;
        this.f7384a.readSerialNumberFromDevice(new a.a.a.a.a.a(this, syncCompleteListner, secondaryBleImpl, z), secondaryBleImpl);
    }
}
