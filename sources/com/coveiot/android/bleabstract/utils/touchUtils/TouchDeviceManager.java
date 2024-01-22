package com.coveiot.android.bleabstract.utils.touchUtils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.listeners.TGConnectCallback;
import com.coveiot.android.bleabstract.services.TouchELXService;
import com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager;
import com.coveiot.utils.utility.LogHelper;
import com.touchgui.sdk.TGBleClient;
import com.touchgui.sdk.TGClient;
import com.touchgui.sdk.TGCloudDial;
import com.touchgui.sdk.TGCommand;
import com.touchgui.sdk.TGCommandBuilder;
import com.touchgui.sdk.TGConnectionListener;
import com.touchgui.sdk.TGDial;
import com.touchgui.sdk.TGDialManager;
import com.touchgui.sdk.TGEventListener;
import com.touchgui.sdk.TGHealthDataCallback;
import com.touchgui.sdk.TGOTACallback;
import com.touchgui.sdk.TGWorkoutDataCallback;
import com.touchgui.sdk.bean.TGAlarm;
import com.touchgui.sdk.bean.TGBatteryInfo;
import com.touchgui.sdk.bean.TGBindResult;
import com.touchgui.sdk.bean.TGBrightnessConfig;
import com.touchgui.sdk.bean.TGContacts;
import com.touchgui.sdk.bean.TGDevice;
import com.touchgui.sdk.bean.TGDeviceInfo;
import com.touchgui.sdk.bean.TGEventReminder;
import com.touchgui.sdk.bean.TGFindPhoneConfig;
import com.touchgui.sdk.bean.TGFunctions;
import com.touchgui.sdk.bean.TGGpsInfo;
import com.touchgui.sdk.bean.TGGpsStatus;
import com.touchgui.sdk.bean.TGHeartRateMonitoringModeConfig;
import com.touchgui.sdk.bean.TGHeartRateRangeConfig;
import com.touchgui.sdk.bean.TGMessage;
import com.touchgui.sdk.bean.TGMtuInfo;
import com.touchgui.sdk.bean.TGMusicInfo;
import com.touchgui.sdk.bean.TGNightModeConfig;
import com.touchgui.sdk.bean.TGNotDisturbConfig;
import com.touchgui.sdk.bean.TGPhysiologicalCycle;
import com.touchgui.sdk.bean.TGProfile;
import com.touchgui.sdk.bean.TGQuickCard;
import com.touchgui.sdk.bean.TGQuickReply;
import com.touchgui.sdk.bean.TGRaiseWristConfig;
import com.touchgui.sdk.bean.TGRealTimeData;
import com.touchgui.sdk.bean.TGRemindDrinking;
import com.touchgui.sdk.bean.TGSedentaryConfig;
import com.touchgui.sdk.bean.TGSpo2Config;
import com.touchgui.sdk.bean.TGStressConfig;
import com.touchgui.sdk.bean.TGTarget;
import com.touchgui.sdk.bean.TGTargetConfig;
import com.touchgui.sdk.bean.TGUnitConfig;
import com.touchgui.sdk.bean.TGVersionInfo;
import com.touchgui.sdk.bean.TGWeather;
import com.touchgui.sdk.bean.TGWorkoutMode;
import com.touchgui.sdk.bean.TGWorkoutSupportList;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TouchDeviceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    public static TouchDeviceManager g;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4073a;
    @NotNull
    public final TGClient b;
    @NotNull
    public final List<TGConnectCallback> c;
    @NotNull
    public final TGConnectionListener d;
    @NotNull
    public final List<TGOTACallback> e;
    @NotNull
    public final TGOTACallback f;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final Object access$checkNull(Companion companion, Object obj) {
            companion.getClass();
            obj.getClass();
            return obj;
        }

        @Nullable
        public final TouchDeviceManager get(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TouchDeviceManager.g == null) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                TouchDeviceManager.g = new TouchDeviceManager(applicationContext, null);
            }
            return TouchDeviceManager.g;
        }
    }

    public TouchDeviceManager(Context context) {
        this.f4073a = context;
        TGClient build = new TGBleClient.Builder(context).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(context).build()");
        this.b = build;
        this.c = new ArrayList();
        this.d = new TGConnectionListener() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager$connectionListener$1
            @Override // com.touchgui.sdk.TGConnectionListener
            public void onConnectionStateChange(int i, @NotNull String address) {
                Intrinsics.checkNotNullParameter(address, "address");
                TouchDeviceManager.access$notifyConnectionState(TouchDeviceManager.this, address, i);
            }

            @Override // com.touchgui.sdk.TGConnectionListener
            public void onError(int i) {
                LogHelper.e("connect error: code=%s", "" + i);
            }

            @Override // com.touchgui.sdk.TGConnectionListener
            public void onReady(@NotNull TGDevice device) {
                Intrinsics.checkNotNullParameter(device, "device");
                TouchDeviceManager.access$notifyDeviceReady(TouchDeviceManager.this, device);
            }
        };
        this.e = new ArrayList();
        this.f = new TGOTACallback() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager$otaCallback$1
            @Override // com.touchgui.sdk.TGOTACallback
            public void onCompleted() {
                List<TGOTACallback> list;
                list = TouchDeviceManager.this.e;
                for (TGOTACallback tGOTACallback : list) {
                    tGOTACallback.onCompleted();
                }
                ((TGClient) TouchDeviceManager.Companion.access$checkNull(TouchDeviceManager.Companion, TouchDeviceManager.this.getClient())).getOtaManager().setCallback(null);
            }

            @Override // com.touchgui.sdk.TGOTACallback
            public void onError(@NotNull Throwable throwable) {
                List<TGOTACallback> list;
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                list = TouchDeviceManager.this.e;
                for (TGOTACallback tGOTACallback : list) {
                    tGOTACallback.onError(throwable);
                }
                ((TGClient) TouchDeviceManager.Companion.access$checkNull(TouchDeviceManager.Companion, TouchDeviceManager.this.getClient())).getOtaManager().setCallback(null);
            }

            @Override // com.touchgui.sdk.TGOTACallback
            public void onProgress(int i) {
                List<TGOTACallback> list;
                list = TouchDeviceManager.this.e;
                for (TGOTACallback tGOTACallback : list) {
                    tGOTACallback.onProgress(i);
                }
            }
        };
    }

    public /* synthetic */ TouchDeviceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final void access$notifyConnectionState(TouchDeviceManager touchDeviceManager, String str, int i) {
        synchronized (touchDeviceManager) {
            for (TGConnectCallback tGConnectCallback : touchDeviceManager.c) {
                tGConnectCallback.onConnectionState(str, i);
            }
        }
    }

    public static final void access$notifyDeviceReady(TouchDeviceManager touchDeviceManager, TGDevice tGDevice) {
        synchronized (touchDeviceManager) {
            for (TGConnectCallback tGConnectCallback : touchDeviceManager.c) {
                tGConnectCallback.onReady(tGDevice);
            }
        }
    }

    public final boolean a() {
        Object systemService = this.f4073a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE);
        ComponentName componentName = new ComponentName(this.f4073a, TouchELXService.class);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            if (Intrinsics.areEqual(runningServiceInfo.service, componentName) && runningServiceInfo.pid == Process.myPid()) {
                return true;
            }
        }
        return false;
    }

    public final void addEventLister(@Nullable TGEventListener tGEventListener) {
        this.b.addEventListener(tGEventListener);
    }

    public final void addOnHealthDataListener(@Nullable TGHealthDataCallback tGHealthDataCallback) {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).registerHealthDataCallback(tGHealthDataCallback);
    }

    public final void addOnOtaListener(@NotNull TGOTACallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.e.contains(callback)) {
            return;
        }
        this.e.add(callback);
    }

    public final void addOnSyncDialListener(@Nullable TGDialManager.OnSyncDialListener onSyncDialListener) {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).getDialManager().addOnSyncDialListener(onSyncDialListener);
    }

    @Nullable
    public final Observable<Integer> applyDial(@Nullable Integer num) {
        TGDialManager dialManager = ((TGClient) Companion.access$checkNull(Companion, this.b)).getDialManager();
        Intrinsics.checkNotNull(num);
        TGCommand<Integer> applyDial = dialManager.applyDial(num.intValue());
        Intrinsics.checkNotNullExpressionValue(applyDial, "checkNull(client).dialMa…       id!!\n            )");
        return TouchELXUtils.INSTANCE.observeCommand(applyDial);
    }

    @Nullable
    public final Observable<Integer> auth(@Nullable String str) {
        TGCommand<Integer> auth = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().auth(str);
        Intrinsics.checkNotNullExpressionValue(auth, "checkNull(client).commandBuilder.auth(data)");
        return TouchELXUtils.INSTANCE.observeCommand(auth);
    }

    @Nullable
    public final Observable<TGBindResult> bindDevice() {
        TGCommand<TGBindResult> bind = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().bind();
        Intrinsics.checkNotNullExpressionValue(bind, "checkNull(client).commandBuilder.bind()");
        return TouchELXUtils.INSTANCE.observeCommand(bind);
    }

    public final void connect(@Nullable String str) {
        if (a()) {
            this.b.addConnectionListener(this.d);
            TGClient tGClient = this.b;
            Intrinsics.checkNotNull(str);
            tGClient.connect(str);
            return;
        }
        this.b.addConnectionListener(this.d);
        try {
            Intent intent = new Intent(this.f4073a, TouchELXService.class);
            intent.putExtra("address", str);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f4073a.startForegroundService(intent);
            } else {
                this.f4073a.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f4073a);
        }
    }

    @Nullable
    public final Observable<Integer> deleteDial(@Nullable Integer num) {
        TGDialManager dialManager = ((TGClient) Companion.access$checkNull(Companion, this.b)).getDialManager();
        Intrinsics.checkNotNull(num);
        TGCommand<Integer> deleteDial = dialManager.deleteDial(num.intValue());
        Intrinsics.checkNotNullExpressionValue(deleteDial, "checkNull(client).dialMa…       id!!\n            )");
        return TouchELXUtils.INSTANCE.observeCommand(deleteDial);
    }

    public final void disconnect(boolean z) {
        this.b.disconnect();
        if (z) {
            this.b.removeConnectionListener(this.d);
            this.b.release();
            this.f4073a.stopService(new Intent(this.f4073a, TouchELXService.class));
        }
    }

    @NotNull
    public final Completable findDevice(boolean z, int i) {
        TGCommand<Void> findDevice = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().findDevice(z, i);
        Intrinsics.checkNotNullExpressionValue(findDevice, "checkNull(client).comman…ndDevice(enable, timeout)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(findDevice);
    }

    @Nullable
    public final Observable<TGBatteryInfo> getBatteryInfo() {
        TGCommand<TGBatteryInfo> batteryInfo = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getBatteryInfo();
        Intrinsics.checkNotNullExpressionValue(batteryInfo, "checkNull(client).commandBuilder.batteryInfo");
        return TouchELXUtils.INSTANCE.observeCommand(batteryInfo);
    }

    @Nullable
    public final Observable<TGBrightnessConfig> getBrightnessConfig() {
        TGCommand<TGBrightnessConfig> brightnessConfig = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getBrightnessConfig();
        Intrinsics.checkNotNullExpressionValue(brightnessConfig, "checkNull(\n             …dBuilder.brightnessConfig");
        return TouchELXUtils.INSTANCE.observeCommand(brightnessConfig);
    }

    @Nullable
    public final Observable<String> getBtMac() {
        TGCommand<String> btMac = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getBtMac();
        Intrinsics.checkNotNullExpressionValue(btMac, "checkNull(client).commandBuilder.btMac");
        return TouchELXUtils.INSTANCE.observeCommand(btMac);
    }

    @Nullable
    public final Observable<Boolean> getCameraOnOff() {
        TGCommand<Boolean> cameraOnOff = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getCameraOnOff();
        Intrinsics.checkNotNullExpressionValue(cameraOnOff, "checkNull(client).commandBuilder.cameraOnOff");
        return TouchELXUtils.INSTANCE.observeCommand(cameraOnOff);
    }

    @NotNull
    public final TGClient getClient() {
        return this.b;
    }

    @Nullable
    public final Observable<TGDeviceInfo> getDeviceInfo() {
        TGCommand<TGDeviceInfo> deviceInfo = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getDeviceInfo();
        Intrinsics.checkNotNullExpressionValue(deviceInfo, "checkNull(client).commandBuilder.deviceInfo");
        return TouchELXUtils.INSTANCE.observeCommand(deviceInfo);
    }

    @Nullable
    public final Observable<String> getDeviceMac() {
        TGCommand<String> deviceMac = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getDeviceMac();
        Intrinsics.checkNotNullExpressionValue(deviceMac, "checkNull(client).commandBuilder.deviceMac");
        return TouchELXUtils.INSTANCE.observeCommand(deviceMac);
    }

    @Nullable
    public final Observable<String> getDeviceSn() {
        TGCommand<String> deviceSn = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getDeviceSn();
        Intrinsics.checkNotNullExpressionValue(deviceSn, "checkNull(client).commandBuilder.deviceSn");
        return TouchELXUtils.INSTANCE.observeCommand(deviceSn);
    }

    @Nullable
    public final Observable<TGFindPhoneConfig> getFindPhoneConfig() {
        TGCommand<TGFindPhoneConfig> findPhoneConfig = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getFindPhoneConfig();
        Intrinsics.checkNotNullExpressionValue(findPhoneConfig, "checkNull(\n             …ndBuilder.findPhoneConfig");
        return TouchELXUtils.INSTANCE.observeCommand(findPhoneConfig);
    }

    @Nullable
    public final Observable<TGWorkoutMode> getGetConfiguredWorkoutList() {
        TGCommand<TGWorkoutMode> workoutMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getWorkoutMode();
        Intrinsics.checkNotNullExpressionValue(workoutMode, "checkNull(client).commandBuilder.workoutMode");
        return TouchELXUtils.INSTANCE.observeCommand(workoutMode);
    }

    public final int getGetContactCount() {
        return ((TGClient) Companion.access$checkNull(Companion, this.b)).getContactCount();
    }

    @Nullable
    public final Observable<List<TGEventReminder>> getGetEventReminders() {
        TGCommand<List<TGEventReminder>> eventReminders = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getEventReminders();
        Intrinsics.checkNotNullExpressionValue(eventReminders, "checkNull(client).commandBuilder.eventReminders");
        return TouchELXUtils.INSTANCE.observeCommand(eventReminders);
    }

    @Nullable
    public final Observable<List<TGQuickCard>> getGetQuickCards() {
        TGCommand<List<TGQuickCard>> quickCards = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getQuickCards();
        Intrinsics.checkNotNullExpressionValue(quickCards, "checkNull(client).commandBuilder.quickCards");
        return TouchELXUtils.INSTANCE.observeCommand(quickCards);
    }

    @Nullable
    public final Observable<TGWorkoutSupportList> getGetSupportedWorkoutList() {
        TGCommand<TGWorkoutSupportList> workoutSupportList = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getWorkoutSupportList();
        Intrinsics.checkNotNullExpressionValue(workoutSupportList, "checkNull(client).comman…uilder.workoutSupportList");
        return TouchELXUtils.INSTANCE.observeCommand(workoutSupportList);
    }

    @Nullable
    public final Observable<TGGpsInfo> getGpsInfo() {
        TGCommand<TGGpsInfo> gpsInfo = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getGpsInfo();
        Intrinsics.checkNotNullExpressionValue(gpsInfo, "checkNull(client).commandBuilder.gpsInfo");
        return TouchELXUtils.INSTANCE.observeCommand(gpsInfo);
    }

    @Nullable
    public final Observable<TGGpsStatus> getGpsStatus() {
        TGCommand<TGGpsStatus> gpsStatus = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getGpsStatus();
        Intrinsics.checkNotNullExpressionValue(gpsStatus, "checkNull(client).commandBuilder.gpsStatus");
        return TouchELXUtils.INSTANCE.observeCommand(gpsStatus);
    }

    @Nullable
    public final Observable<TGHeartRateMonitoringModeConfig> getHeartRateMonitoringMode() {
        TGCommand<TGHeartRateMonitoringModeConfig> heartRateMonitoringMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getHeartRateMonitoringMode();
        Intrinsics.checkNotNullExpressionValue(heartRateMonitoringMode, "checkNull(\n             …r.heartRateMonitoringMode");
        return TouchELXUtils.INSTANCE.observeCommand(heartRateMonitoringMode);
    }

    @Nullable
    public final Observable<TGHeartRateRangeConfig> getHeartRateRange() {
        TGCommand<TGHeartRateRangeConfig> heartRateRange = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getHeartRateRange();
        Intrinsics.checkNotNullExpressionValue(heartRateRange, "checkNull(\n             …andBuilder.heartRateRange");
        return TouchELXUtils.INSTANCE.observeCommand(heartRateRange);
    }

    @Nullable
    public final Observable<String> getModelName() {
        TGCommand<String> modelName = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getModelName();
        Intrinsics.checkNotNullExpressionValue(modelName, "checkNull(client).commandBuilder.modelName");
        return TouchELXUtils.INSTANCE.observeCommand(modelName);
    }

    @Nullable
    public final Observable<TGMtuInfo> getMtuInfo() {
        TGCommand<TGMtuInfo> mtuInfo = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getMtuInfo();
        Intrinsics.checkNotNullExpressionValue(mtuInfo, "checkNull(client).commandBuilder.mtuInfo");
        return TouchELXUtils.INSTANCE.observeCommand(mtuInfo);
    }

    @Nullable
    public final Observable<Boolean> getMusicOnOff() {
        TGCommand<Boolean> musicOnOff = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getMusicOnOff();
        Intrinsics.checkNotNullExpressionValue(musicOnOff, "checkNull(client).commandBuilder.musicOnOff");
        return TouchELXUtils.INSTANCE.observeCommand(musicOnOff);
    }

    @Nullable
    public final Observable<TGNightModeConfig> getNightMode() {
        TGCommand<TGNightModeConfig> nightMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getNightMode();
        Intrinsics.checkNotNullExpressionValue(nightMode, "checkNull(\n             ….commandBuilder.nightMode");
        return TouchELXUtils.INSTANCE.observeCommand(nightMode);
    }

    @Nullable
    public final Observable<TGNotDisturbConfig> getNotDisturbMode() {
        TGCommand<TGNotDisturbConfig> notDisturbMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getNotDisturbMode();
        Intrinsics.checkNotNullExpressionValue(notDisturbMode, "checkNull(\n             …andBuilder.notDisturbMode");
        return TouchELXUtils.INSTANCE.observeCommand(notDisturbMode);
    }

    @Nullable
    public final Observable<TGProfile> getProfile() {
        TGCommand<TGProfile> profile = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getProfile();
        Intrinsics.checkNotNullExpressionValue(profile, "checkNull(client).commandBuilder.profile");
        return TouchELXUtils.INSTANCE.observeCommand(profile);
    }

    @Nullable
    public final Observable<TGRaiseWristConfig> getRaiseWrist() {
        TGCommand<TGRaiseWristConfig> raiseWrist = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getRaiseWrist();
        Intrinsics.checkNotNullExpressionValue(raiseWrist, "checkNull(\n             …commandBuilder.raiseWrist");
        return TouchELXUtils.INSTANCE.observeCommand(raiseWrist);
    }

    @Nullable
    public final Observable<TGRealTimeData> getRealTimeData() {
        TGCommand<TGRealTimeData> realTimeData = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getRealTimeData();
        Intrinsics.checkNotNullExpressionValue(realTimeData, "checkNull(client).commandBuilder.realTimeData");
        return TouchELXUtils.INSTANCE.observeCommand(realTimeData);
    }

    @Nullable
    public final Observable<TGRemindDrinking> getRemindDrinking() {
        TGCommand<TGRemindDrinking> remindDrinking = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getRemindDrinking();
        Intrinsics.checkNotNullExpressionValue(remindDrinking, "checkNull(\n             …andBuilder.remindDrinking");
        return TouchELXUtils.INSTANCE.observeCommand(remindDrinking);
    }

    @Nullable
    public final Observable<TGSedentaryConfig> getSedentary() {
        TGCommand<TGSedentaryConfig> sedentary = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getSedentary();
        Intrinsics.checkNotNullExpressionValue(sedentary, "checkNull(\n             ….commandBuilder.sedentary");
        return TouchELXUtils.INSTANCE.observeCommand(sedentary);
    }

    @Nullable
    public final Observable<TGSpo2Config> getSpo2MonitoringMode() {
        TGCommand<TGSpo2Config> spo2Config = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getSpo2Config();
        Intrinsics.checkNotNullExpressionValue(spo2Config, "checkNull(client).commandBuilder.spo2Config");
        return TouchELXUtils.INSTANCE.observeCommand(spo2Config);
    }

    @Nullable
    public final Observable<TGStressConfig> getStressMonitoringMode() {
        TGCommand<TGStressConfig> stressConfig = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getStressConfig();
        Intrinsics.checkNotNullExpressionValue(stressConfig, "checkNull(client).commandBuilder.stressConfig");
        return TouchELXUtils.INSTANCE.observeCommand(stressConfig);
    }

    @Nullable
    public final Observable<TGTargetConfig> getTarget() {
        TGCommand<TGTargetConfig> target = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getTarget();
        Intrinsics.checkNotNullExpressionValue(target, "checkNull(client).commandBuilder.target");
        return TouchELXUtils.INSTANCE.observeCommand(target);
    }

    @Nullable
    public final Observable<TGUnitConfig> getUnit() {
        TGCommand<TGUnitConfig> unit = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getUnit();
        Intrinsics.checkNotNullExpressionValue(unit, "checkNull(client).commandBuilder.unit");
        return TouchELXUtils.INSTANCE.observeCommand(unit);
    }

    @Nullable
    public final Observable<TGVersionInfo> getVersionInfo() {
        TGCommand<TGVersionInfo> versionInfo = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getVersionInfo();
        Intrinsics.checkNotNullExpressionValue(versionInfo, "checkNull(client).commandBuilder.versionInfo");
        return TouchELXUtils.INSTANCE.observeCommand(versionInfo);
    }

    @Nullable
    public final Observable<Boolean> getWeatherOnOff() {
        TGCommand<Boolean> weatherOnOff = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().getWeatherOnOff();
        Intrinsics.checkNotNullExpressionValue(weatherOnOff, "checkNull(client).commandBuilder.weatherOnOff");
        return TouchELXUtils.INSTANCE.observeCommand(weatherOnOff);
    }

    public final boolean isAutoSPO2Supported() {
        return this.b.hasFunction(TGFunctions.FUNC_SPO2_CONFIG);
    }

    public final boolean isAutoStressSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_STRESS_CONFIG);
    }

    public final boolean isBTCallingSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_BT);
    }

    public final boolean isConnected() {
        TGClient tGClient = this.b;
        return tGClient != null && tGClient.isConnected();
    }

    public final boolean isEventReminderSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_EVENT_REMINDER);
    }

    public final boolean isFemaleWellnessSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_PHYSIOLOGICAL_CYCLE);
    }

    public final boolean isFindDeviceSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_FIND_DEVICE);
    }

    public final boolean isQuickCardsSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_QUICK_CARD);
    }

    public final boolean isQuickReplySupported() {
        return this.b.hasFunction(33687816);
    }

    public final boolean isSportModeSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_SPORT_MODE);
    }

    public final boolean isStandCountSupported() {
        return this.b.hasFunction(TGFunctions.FUNC_STAND_COUNT);
    }

    public final void otaUpdate(@Nullable String str) {
        Companion companion = Companion;
        ((TGClient) Companion.access$checkNull(companion, this.b)).getOtaManager().setCallback(this.f);
        ((TGClient) Companion.access$checkNull(companion, this.b)).getOtaManager().start(str, false);
    }

    @NotNull
    public final Completable reboot() {
        TGCommand<Void> reboot = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().reboot();
        Intrinsics.checkNotNullExpressionValue(reboot, "checkNull(client).commandBuilder.reboot()");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(reboot);
    }

    public final synchronized void registerConnectCallback(@NotNull TGConnectCallback listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (!this.c.contains(listener)) {
            this.c.add(listener);
        }
    }

    public final void registerWorkoutDataCallback(@Nullable TGWorkoutDataCallback tGWorkoutDataCallback) {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).registerWorkoutDataCallback(tGWorkoutDataCallback);
    }

    @NotNull
    public final Completable remindCall(@Nullable String str, @Nullable String str2) {
        TGCommand<Void> remindCall = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().remindCall(str, str2);
        Intrinsics.checkNotNullExpressionValue(remindCall, "checkNull(client).comman….remindCall(name, number)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(remindCall);
    }

    public final void removeEventListener(@Nullable TGEventListener tGEventListener) {
        this.b.removeEventListener(tGEventListener);
    }

    public final void removeOnHealthDataListener(@Nullable TGHealthDataCallback tGHealthDataCallback) {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).unregisterHealthDataCallback(tGHealthDataCallback);
    }

    public final void removeOnOtaListener(@NotNull TGOTACallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.e.remove(callback);
    }

    public final void removeOnSyncDialListener(@Nullable TGDialManager.OnSyncDialListener onSyncDialListener) {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).getDialManager().removeOnSyncDialListener(onSyncDialListener);
    }

    @NotNull
    public final Completable resetFactory() {
        TGCommand<Void> reset = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().reset();
        Intrinsics.checkNotNullExpressionValue(reset, "checkNull(client).commandBuilder.reset()");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(reset);
    }

    @Nullable
    public final Observable<Integer> setActivityMode(@Nullable TGWorkoutMode tGWorkoutMode) {
        TGCommand<Integer> workoutMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setWorkoutMode(tGWorkoutMode);
        Intrinsics.checkNotNullExpressionValue(workoutMode, "checkNull(client).comman…orkoutMode(tgWorkoutMode)");
        return TouchELXUtils.INSTANCE.observeCommand(workoutMode);
    }

    @Nullable
    public final Observable<Integer> setAlarms(@Nullable List<? extends TGAlarm> list) {
        TGCommand<Integer> alarms = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setAlarms(list);
        Intrinsics.checkNotNullExpressionValue(alarms, "checkNull(client).commandBuilder.setAlarms(alarms)");
        return TouchELXUtils.INSTANCE.observeCommand(alarms);
    }

    @NotNull
    public final Completable setBrightnessConfig(@Nullable TGBrightnessConfig tGBrightnessConfig) {
        TGCommand<Void> brightnessConfig = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setBrightnessConfig(tGBrightnessConfig);
        Intrinsics.checkNotNullExpressionValue(brightnessConfig, "checkNull(client).comman…tBrightnessConfig(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(brightnessConfig);
    }

    @NotNull
    public final Completable setCameraOnOff(boolean z) {
        TGCommand<Void> cameraOnOff = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setCameraOnOff(z);
        Intrinsics.checkNotNullExpressionValue(cameraOnOff, "checkNull(client).comman…er.setCameraOnOff(enable)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(cameraOnOff);
    }

    @NotNull
    public final Completable setCity(@Nullable String str) {
        TGCommandBuilder commandBuilder = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder();
        Intrinsics.checkNotNull(str);
        TGCommand<Void> city = commandBuilder.setCity(str);
        Intrinsics.checkNotNullExpressionValue(city, "checkNull(client).comman… cityName!!\n            )");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(city);
    }

    @Nullable
    public final Observable<Integer> setContacts(@Nullable List<? extends TGContacts> list) {
        TGCommand<Integer> contacts = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setContacts(list);
        Intrinsics.checkNotNullExpressionValue(contacts, "checkNull(client).comman…tContacts(tgContactsList)");
        return TouchELXUtils.INSTANCE.observeCommand(contacts);
    }

    @Nullable
    public final Observable<Integer> setEventReminders(@Nullable List<? extends TGEventReminder> list) {
        TGCommand<Integer> eventReminders = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setEventReminders(list);
        Intrinsics.checkNotNullExpressionValue(eventReminders, "checkNull(client).comman…inders(eventReminderList)");
        return TouchELXUtils.INSTANCE.observeCommand(eventReminders);
    }

    @NotNull
    public final Completable setFindPhoneOnOff(boolean z, int i) {
        TGCommand<Void> findPhoneOnOff = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setFindPhoneOnOff(z, i);
        Intrinsics.checkNotNullExpressionValue(findPhoneOnOff, "checkNull(client).comman…    timeout\n            )");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(findPhoneOnOff);
    }

    @NotNull
    public final Completable setHeartRateMonitoringMode(@Nullable TGHeartRateMonitoringModeConfig tGHeartRateMonitoringModeConfig) {
        TGCommand<Void> heartRateMonitoringMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setHeartRateMonitoringMode(tGHeartRateMonitoringModeConfig);
        Intrinsics.checkNotNullExpressionValue(heartRateMonitoringMode, "checkNull(client).comman…     config\n            )");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(heartRateMonitoringMode);
    }

    @NotNull
    public final Completable setHeartRateRange(@Nullable TGHeartRateRangeConfig tGHeartRateRangeConfig) {
        TGCommand<Void> heartRateRange = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setHeartRateRange(tGHeartRateRangeConfig);
        Intrinsics.checkNotNullExpressionValue(heartRateRange, "checkNull(client).comman…setHeartRateRange(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(heartRateRange);
    }

    @NotNull
    public final Completable setMusicOnOff(boolean z) {
        TGCommand<Void> musicOnOff = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setMusicOnOff(z);
        Intrinsics.checkNotNullExpressionValue(musicOnOff, "checkNull(client).comman…der.setMusicOnOff(enable)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(musicOnOff);
    }

    @NotNull
    public final Completable setNightMode(@Nullable TGNightModeConfig tGNightModeConfig) {
        TGCommand<Void> nightMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setNightMode(tGNightModeConfig);
        Intrinsics.checkNotNullExpressionValue(nightMode, "checkNull(client).comman…lder.setNightMode(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(nightMode);
    }

    @NotNull
    public final Completable setNotDisturbMode(@Nullable TGNotDisturbConfig tGNotDisturbConfig) {
        TGCommand<Void> notDisturbMode = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setNotDisturbMode(tGNotDisturbConfig);
        Intrinsics.checkNotNullExpressionValue(notDisturbMode, "checkNull(client).comman…setNotDisturbMode(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(notDisturbMode);
    }

    @NotNull
    public final Completable setPhysiologicalCycle(@Nullable TGPhysiologicalCycle tGPhysiologicalCycle) {
        TGCommand<Void> physiologicalCycle = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setPhysiologicalCycle(tGPhysiologicalCycle);
        Intrinsics.checkNotNullExpressionValue(physiologicalCycle, "checkNull(client).comman…hysiologicalCycle(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(physiologicalCycle);
    }

    @NotNull
    public final Completable setProfile(@Nullable TGProfile tGProfile) {
        TGCommand<Void> profile = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setProfile(tGProfile);
        Intrinsics.checkNotNullExpressionValue(profile, "checkNull(client).comman…ilder.setProfile(profile)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(profile);
    }

    @NotNull
    public final Completable setQuickCards(@Nullable List<? extends TGQuickCard> list) {
        TGCommand<Void> quickCards = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setQuickCards(list);
        Intrinsics.checkNotNullExpressionValue(quickCards, "checkNull(client).comman…der.setQuickCards(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(quickCards);
    }

    @NotNull
    public final Completable setRaiseWrist(@Nullable TGRaiseWristConfig tGRaiseWristConfig) {
        TGCommand<Void> raiseWrist = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setRaiseWrist(tGRaiseWristConfig);
        Intrinsics.checkNotNullExpressionValue(raiseWrist, "checkNull(client).comman…der.setRaiseWrist(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(raiseWrist);
    }

    @NotNull
    public final Completable setRemindDrinking(@Nullable TGRemindDrinking tGRemindDrinking) {
        TGCommand<Void> remindDrinking = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setRemindDrinking(tGRemindDrinking);
        Intrinsics.checkNotNullExpressionValue(remindDrinking, "checkNull(client).comman…setRemindDrinking(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(remindDrinking);
    }

    @NotNull
    public final Completable setSedentary(@Nullable TGSedentaryConfig tGSedentaryConfig) {
        TGCommand<Void> sedentary = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setSedentary(tGSedentaryConfig);
        Intrinsics.checkNotNullExpressionValue(sedentary, "checkNull(client).comman…lder.setSedentary(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(sedentary);
    }

    @NotNull
    public final Completable setSpo2MonitoringMode(@Nullable TGSpo2Config tGSpo2Config) {
        TGCommand<Void> spo2Config = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setSpo2Config(tGSpo2Config);
        Intrinsics.checkNotNullExpressionValue(spo2Config, "checkNull(client).comman…der.setSpo2Config(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(spo2Config);
    }

    @NotNull
    public final Completable setStressMonitoringMode(@Nullable TGStressConfig tGStressConfig) {
        TGCommand<Void> stressConfig = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setStressConfig(tGStressConfig);
        Intrinsics.checkNotNullExpressionValue(stressConfig, "checkNull(client).comman…r.setStressConfig(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(stressConfig);
    }

    @NotNull
    public final Completable setTarget(int i, int i2) {
        TGCommand<Void> target = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setTarget(i, i2);
        Intrinsics.checkNotNullExpressionValue(target, "checkNull(client).comman…r.setTarget(type, target)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(target);
    }

    @NotNull
    public final Completable setUnit(@Nullable TGUnitConfig tGUnitConfig) {
        TGCommand<Void> unit = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setUnit(tGUnitConfig);
        Intrinsics.checkNotNullExpressionValue(unit, "checkNull(client).commandBuilder.setUnit(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(unit);
    }

    @NotNull
    public final Completable setWeather(@Nullable TGWeather tGWeather) {
        TGCommand<Void> weather = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setWeather(tGWeather);
        Intrinsics.checkNotNullExpressionValue(weather, "checkNull(client).comman…ilder.setWeather(weather)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(weather);
    }

    @NotNull
    public final Completable setWeatherOnOff(boolean z) {
        TGCommand<Void> weatherOnOff = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setWeatherOnOff(z);
        Intrinsics.checkNotNullExpressionValue(weatherOnOff, "checkNull(client).comman…r.setWeatherOnOff(enable)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(weatherOnOff);
    }

    @NotNull
    public final Completable shutdown() {
        TGCommand<Void> shutdown = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().shutdown();
        Intrinsics.checkNotNullExpressionValue(shutdown, "checkNull(client).commandBuilder.shutdown()");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(shutdown);
    }

    @NotNull
    public final Completable stopFindPhone() {
        TGCommand<Void> stopFindPhone = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().stopFindPhone();
        Intrinsics.checkNotNullExpressionValue(stopFindPhone, "checkNull(client).commandBuilder.stopFindPhone()");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(stopFindPhone);
    }

    @Nullable
    public final Observable<List<TGAlarm>> syncAlarms() {
        TGCommand<List<TGAlarm>> syncAlarms = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncAlarms();
        Intrinsics.checkNotNullExpressionValue(syncAlarms, "checkNull(\n             …mmandBuilder.syncAlarms()");
        return TouchELXUtils.INSTANCE.observeCommand(syncAlarms);
    }

    public final void syncAllHealthData() {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).syncHealthData();
    }

    public final void syncBackgroundDial(@Nullable TGDial tGDial) {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).getDialManager().syncDial(tGDial);
    }

    @NotNull
    public final Completable syncCallStatus(int i) {
        TGCommand<Void> syncCallStatus = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncCallStatus(i);
        Intrinsics.checkNotNullExpressionValue(syncCallStatus, "checkNull(client).comman…er.syncCallStatus(status)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(syncCallStatus);
    }

    public final void syncCloudDial(@Nullable Integer num, @Nullable String str) {
        TGDialManager dialManager = ((TGClient) Companion.access$checkNull(Companion, this.b)).getDialManager();
        Intrinsics.checkNotNull(num);
        dialManager.syncDial(new TGCloudDial(num.intValue(), str));
    }

    @Nullable
    public final Observable<Integer> syncMessage(@Nullable String str, @Nullable String str2, @Nullable String str3, int i) {
        TGCommand<Integer> syncMessage = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncMessage(str, str2, str3, i);
        Intrinsics.checkNotNullExpressionValue(syncMessage, "checkNull(client).comman…    msgType\n            )");
        return TouchELXUtils.INSTANCE.observeCommand(syncMessage);
    }

    @Nullable
    public final Observable<Integer> syncMusic(@Nullable String str, boolean z, int i, int i2) {
        TGMusicInfo tGMusicInfo = new TGMusicInfo();
        tGMusicInfo.setMusicName(str);
        tGMusicInfo.setPlaying(z);
        tGMusicInfo.setMaxVol(i);
        tGMusicInfo.setCurVol(i2);
        TGCommand<Integer> syncMusic = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncMusic(tGMusicInfo);
        Intrinsics.checkNotNullExpressionValue(syncMusic, "checkNull(client).comman…lder.syncMusic(musicInfo)");
        return TouchELXUtils.INSTANCE.observeCommand(syncMusic);
    }

    @NotNull
    public final Completable syncQuickReply(@Nullable List<? extends TGQuickReply> list) {
        TGCommand<Void> syncQuickReply = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncQuickReply(list);
        Intrinsics.checkNotNullExpressionValue(syncQuickReply, "checkNull(client).comman…er.syncQuickReply(config)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(syncQuickReply);
    }

    @NotNull
    public final Completable syncTime() {
        TGCommand<Void> syncTime = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncTime();
        Intrinsics.checkNotNullExpressionValue(syncTime, "checkNull(client).commandBuilder.syncTime()");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(syncTime);
    }

    public final void syncWorkoutData() {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).syncWorkoutData();
    }

    @NotNull
    public final Completable unbindDevice() {
        TGCommand<Void> unbind = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().unbind(false);
        Intrinsics.checkNotNullExpressionValue(unbind, "checkNull(client).commandBuilder.unbind(false)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(unbind);
    }

    public final synchronized void unregisterConnectCallback(@NotNull TGConnectCallback listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.remove(listener);
    }

    public final void unregisterWorkoutDataCallback(@Nullable TGWorkoutDataCallback tGWorkoutDataCallback) {
        ((TGClient) Companion.access$checkNull(Companion, this.b)).unregisterWorkoutDataCallback(tGWorkoutDataCallback);
    }

    @NotNull
    public final Completable setTarget(int i, int i2, int i3) {
        TGTarget tGTarget = new TGTarget();
        tGTarget.setStep(i);
        tGTarget.setCalorie(i2);
        tGTarget.setDistance(i3);
        TGCommand<Void> target = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().setTarget(tGTarget);
        Intrinsics.checkNotNullExpressionValue(target, "checkNull(client).commandBuilder.setTarget(target)");
        return TouchELXUtils.INSTANCE.observeCommandNoResult(target);
    }

    @Nullable
    public final Observable<Integer> syncMessage(@Nullable TGMessage tGMessage) {
        TGCommand<Integer> syncMessage = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncMessage(tGMessage);
        Intrinsics.checkNotNullExpressionValue(syncMessage, "checkNull(client).comman…er.syncMessage(tgMessage)");
        return TouchELXUtils.INSTANCE.observeCommand(syncMessage);
    }

    @Nullable
    public final Observable<Integer> syncMusic(@Nullable TGMusicInfo tGMusicInfo) {
        TGCommand<Integer> syncMusic = ((TGClient) Companion.access$checkNull(Companion, this.b)).getCommandBuilder().syncMusic(tGMusicInfo);
        Intrinsics.checkNotNullExpressionValue(syncMusic, "checkNull<TGClient>(clie…lder.syncMusic(musicInfo)");
        return TouchELXUtils.INSTANCE.observeCommand(syncMusic);
    }
}
