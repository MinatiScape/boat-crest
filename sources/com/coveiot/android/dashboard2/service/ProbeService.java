package com.coveiot.android.dashboard2.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.PeriodicWorkRequest;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.LastDataHelper;
import com.coveiot.android.dashboard2.StatusApiDataHelper;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weather.weather.WeatherUtils;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.RemoteConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ProbeService extends JobService {
    public final String h = ProbeService.class.getSimpleName();
    public long i = -1;
    @Nullable
    public SyncManager j;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.service.ProbeService$checkAndCallWeatherAPI$1", f = "ProbeService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (((int) ((System.currentTimeMillis() - UserDataManager.getInstance(ProbeService.this).getLastUpdateWeatherTimeStamp()) / 60000)) >= 150) {
                    new WeatherUtils(ProbeService.this).getLocationAndCallWeatherAPI(ProbeService.this);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void e(final FirebaseRemoteConfig remoteConfig, final ProbeService this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r4 = (Void) task.getResult();
            new RemoteConfig();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.service.b
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    ProbeService.f(FirebaseRemoteConfig.this, this$0, r4, task2);
                }
            });
            return;
        }
        LogHelper.e(this$0.h, "Remote Config Failed");
    }

    public static final void f(FirebaseRemoteConfig remoteConfig, ProbeService this$0, Void r3, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(Dashboard2Constants.SHIPBOOK_STATE);
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig\n           …Constants.SHIPBOOK_STATE)");
        if (string.equals(Dashboard2Constants.ALL_ON)) {
            LogHelper.initialize(true);
            LogsHelper.init(true);
        } else {
            LogHelper.initialize(false);
            LogsHelper.init(false);
        }
        String str = this$0.h;
        LogHelper.d(str, "Config params updated: " + r3 + string);
    }

    public final void c() {
        String[] unGrantedPermissions;
        try {
            if (Build.VERSION.SDK_INT < 29) {
                unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"});
            } else {
                unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"});
            }
            Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
            if ((unGrantedPermissions.length == 0) && AppUtils.isNetConnected(this)) {
                WeatherAppPreferenceManager companion = WeatherAppPreferenceManager.Companion.getInstance(this);
                if (companion != null ? Intrinsics.areEqual(companion.isWeatherEnabled(), Boolean.TRUE) : false) {
                    e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new a(null), 3, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void d() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Dashboard2Constants.Companion.getACTION_LOW_BATTERY_NOTIFICATION_DISMISS()));
    }

    public final void g() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Dashboard2Constants.Companion.getACTION_LOW_BATTERY_NOTIFICATION_SHOW()));
    }

    @Nullable
    public final SyncManager getSyncManager() {
        return this.j;
    }

    public final void h() {
        if (this.j == null) {
            this.j = SyncManager.getInstance();
        }
        SyncManager syncManager = this.j;
        Intrinsics.checkNotNull(syncManager);
        if (!syncManager.isSyncInProgress()) {
            LogHelper.d("ProbeService", "Sync started in probe");
            this.i = System.currentTimeMillis();
            SyncManager syncManager2 = this.j;
            if (syncManager2 != null) {
                syncManager2.syncData(new SyncCompleteListner() { // from class: com.coveiot.android.dashboard2.service.ProbeService$syncDataAndUpdateLastMeasuredValues$1
                    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                    public void onDataSyncComplete() {
                        long j;
                        Dashboard2PreferenceManager.Companion.getInstance(ProbeService.this).saveLastCompleteDataSyncTimestamp(System.currentTimeMillis());
                        ProbeService.this.sendBroadcast(new Intent(Dashboard2Constants.ACTION_SYNC_COMPLETE));
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_DATA_SYNC.getValue());
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put(FirebaseEventParams.MetaData.CV_BT_STATE.getValue(), AppUtils.isBluetoothEnabled(ProbeService.this) ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                        String value = FirebaseEventParams.MetaData.CV_TIME_SPENT_MILLS.getValue();
                        long currentTimeMillis = System.currentTimeMillis();
                        j = ProbeService.this.i;
                        hashMap.put(value, String.valueOf(currentTimeMillis - j));
                        hashMap.put(FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue(), Dashboard2Utils.Companion.getPhoneBatteryLevel(ProbeService.this));
                        hashMap.put(FirebaseEventParams.MetaData.CV_DVC_BATTERY_LEVEL.getValue(), String.valueOf(UserDataManager.getInstance(ProbeService.this).getBatteryLevelData().batteryLevel));
                        hashMap.put(FirebaseEventParams.MetaData.CV_EVT_TRIGGER_TYPE.getValue(), "auto");
                        hashMap.put(FirebaseEventParams.MetaData.CV_APP_PROCESS_STATUS.getValue(), "background");
                        hashMap.put(FirebaseEventParams.MetaData.CV_DVC_CONN_STATUS.getValue(), BleApiManager.getInstance(ProbeService.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED ? "connected" : "disconnected");
                        hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "ok");
                        analyticsLog.setMapData(hashMap);
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        if (BleApiManager.getInstance(ProbeService.this) != null && BleApiManager.getInstance(ProbeService.this).getBleApi() != null && BleApiManager.getInstance(ProbeService.this).getBleApi().getDeviceSupportedFeatures().isWeatherSupportedInBand()) {
                            WeatherAppPreferenceManager companion = WeatherAppPreferenceManager.Companion.getInstance(ProbeService.this);
                            Intrinsics.checkNotNull(companion);
                            Boolean isWeatherEnabled = companion.isWeatherEnabled();
                            Intrinsics.checkNotNull(isWeatherEnabled);
                            if (isWeatherEnabled.booleanValue()) {
                                ProbeService.this.c();
                            }
                        }
                        BleApiManager.getInstance(ProbeService.this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported();
                        StatusApiDataHelper.Companion companion2 = StatusApiDataHelper.Companion;
                        StatusApiDataHelper.saveStatusDataToServer$default(companion2.getInstance(ProbeService.this), false, 1, null);
                        LastDataHelper.Companion companion3 = LastDataHelper.Companion;
                        companion3.getInstance(ProbeService.this).sendSleepDataToBand(new LastDataHelper.UploadCompletionListner() { // from class: com.coveiot.android.dashboard2.service.ProbeService$syncDataAndUpdateLastMeasuredValues$1$onDataSyncComplete$1
                            @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                            public void onDataUploadeComplete() {
                            }

                            @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                            public void onUploadFailed() {
                            }
                        });
                        StatusApiDataHelper.saveStatusDataToServer$default(companion2.getInstance(ProbeService.this), false, 1, null);
                        if (AppUtils.isNetConnected(ProbeService.this)) {
                            LogHelper.d("ProbeService", "Sync completed sending info to server");
                            final ProbeService probeService = ProbeService.this;
                            companion3.getInstance(ProbeService.this).saveLastDataInfoToServer(new LastDataHelper.UploadCompletionListner() { // from class: com.coveiot.android.dashboard2.service.ProbeService$syncDataAndUpdateLastMeasuredValues$1$onDataSyncComplete$2
                                @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                                public void onDataUploadeComplete() {
                                    ProbeService.this.stopSelf();
                                }

                                @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                                public void onUploadFailed() {
                                    ProbeService.this.stopSelf();
                                }
                            });
                            return;
                        }
                        ProbeService.this.stopSelf();
                        LogHelper.d("ProbeService", "Sync completed but no internet to send info to server");
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:21:0x0122, code lost:
                        if (kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r8, (java.lang.CharSequence) "busy", false, 2, (java.lang.Object) null) == false) goto L22;
                     */
                    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void onFailure(@org.jetbrains.annotations.Nullable java.lang.String r8, @org.jetbrains.annotations.Nullable com.coveiot.repository.Error r9) {
                        /*
                            Method dump skipped, instructions count: 311
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.service.ProbeService$syncDataAndUpdateLastMeasuredValues$1.onFailure(java.lang.String, com.coveiot.repository.Error):void");
                    }

                    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                    public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                    }
                }, false);
                return;
            }
            return;
        }
        LogHelper.d("ProbeService", "Sync attempted, but sync already in progress ");
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(@Nullable JobParameters jobParameters) {
        EntityDeviceInfo deviceInfoBy;
        LogHelper.d("ProbeService", "onStartJob Called");
        if (System.currentTimeMillis() - SessionManager.getInstance(this).getShipbookConfigUpdatedTimestamp() < TimeUnit.HOURS.toMillis(24L)) {
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.fetch(1000L).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.service.a
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ProbeService.e(FirebaseRemoteConfig.this, this, task);
                }
            });
            Dashboard2Utils.Companion.loadScanDeviceOnDisconnectConfiguration(this);
            SessionManager.getInstance(this).setShipbookConfigUpdatedTimestamp(System.currentTimeMillis());
        }
        if (jobParameters != null && jobParameters.getExtras().getBoolean(Dashboard2Constants.IS_FROM_SCHEDULER, false) && jobParameters.getExtras().getBoolean(Dashboard2Constants.IS_FROM_SCHEDULER) && BleApiManager.getInstance(this).getBleApi() != null && (deviceInfoBy = DeviceInfoRepository.getInstance(this).getDeviceInfoBy(BleApiManager.getInstance(this).getBleApi().getMacAddress())) != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(deviceInfoBy.getLasySyncTime());
            calendar.set(13, 0);
            if (System.currentTimeMillis() - calendar.getTimeInMillis() < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
                LogHelper.d("ProbeService", "Time not passed.");
                return true;
            }
        }
        if (BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            LogHelper.d("ProbeService", "BatteryLevelRequest sent");
            BleApiManager.getInstance(this).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.dashboard2.service.ProbeService$onStartJob$2
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.e("ProbeService", "BatteryLevelRequest error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof BatteryLevelResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                        BatteryLevelResponse batteryLevelResponse = (BatteryLevelResponse) responseData;
                        UserDataManager userDataManager = UserDataManager.getInstance(ProbeService.this);
                        Integer batteryLevel = batteryLevelResponse.getBatteryLevel();
                        Intrinsics.checkNotNull(batteryLevel);
                        userDataManager.saveBatteryLevelData(new BatteryLevelData(batteryLevel.intValue(), System.currentTimeMillis()));
                        if (DeviceUtils.Companion.isCurrentFirmwareHasIssueWithBatteryPercentage(ProbeService.this)) {
                            Integer batteryLevel2 = batteryLevelResponse.getBatteryLevel();
                            Intrinsics.checkNotNull(batteryLevel2);
                            if (batteryLevel2.intValue() <= 0) {
                                ProbeService.this.d();
                                LogHelper.d("ProbeService", "BatteryLevelRequest response");
                                ProbeService.this.h();
                            }
                        }
                        Integer batteryLevel3 = batteryLevelResponse.getBatteryLevel();
                        Intrinsics.checkNotNull(batteryLevel3);
                        if (batteryLevel3.intValue() <= 15) {
                            ProbeService.this.g();
                        } else {
                            ProbeService.this.d();
                        }
                        LogHelper.d("ProbeService", "BatteryLevelRequest response");
                        ProbeService.this.h();
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        } else {
            LogHelper.d("ProbeService", "Device is not connected");
            StatusApiDataHelper.saveStatusDataToServer$default(StatusApiDataHelper.Companion.getInstance(this), false, 1, null);
        }
        Dashboard2Utils.Companion.scheduleJob(this);
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(@Nullable JobParameters jobParameters) {
        return true;
    }

    public final void setSyncManager(@Nullable SyncManager syncManager) {
        this.j = syncManager;
    }
}
