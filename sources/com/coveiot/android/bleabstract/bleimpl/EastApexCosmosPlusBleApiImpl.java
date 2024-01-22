package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.apex.bluetooth.model.EABleFeatures;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.services.EastapexBleService;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.error.EastApexError;
import com.coveiot.sdk.ble.CloveBleState;
import com.squareup.otto.Subscribe;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastApexCosmosPlusBleApiImpl extends EastApexBaseBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public final int s;
    @Nullable
    public DeviceSupportedFeatures t;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexCosmosPlusBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.EastApexCosmosPlusBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexCosmosPlusBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f2954a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexCosmosPlusBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexCosmosPlusBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexCosmosPlusBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f2954a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EastApexCosmosPlusBleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.s = 1;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            EastapexBleService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                super.getData(request, listener);
                return;
            }
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        EABleFeatures devSupfeatures;
        EABleFeatures devSupfeatures2;
        EABleFeatures devSupfeatures3;
        EABleFeatures devSupfeatures4;
        EABleFeatures devSupfeatures5;
        EABleFeatures devSupfeatures6;
        EABleFeatures devSupfeatures7;
        EABleFeatures devSupfeatures8;
        EABleFeatures devSupfeatures9;
        EABleFeatures devSupfeatures10;
        EABleFeatures devSupfeatures11;
        EABleFeatures devSupfeatures12;
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.t = deviceSupportedFeatures;
        deviceSupportedFeatures.setStepsSupported(true);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.t;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.t;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.t;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setTemparatureHistorySupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.t;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setManualBpSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.t;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setManualSpo2SupportedOnBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.t;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setManualStressMeasurementSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.t;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.t;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.t;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.t;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.t;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.t;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.t;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.t;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setPhoneFinderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.t;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.t;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.t;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.t;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.t;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.t;
        if (deviceSupportedFeatures21 != null) {
            EastapexBleService bleService = getBleService();
            deviceSupportedFeatures21.setDistanceUnitSettingsSupported((bleService == null || (devSupfeatures12 = bleService.getDevSupfeatures()) == null || devSupfeatures12.getUnit_settings() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.t;
        if (deviceSupportedFeatures22 != null) {
            EastapexBleService bleService2 = getBleService();
            deviceSupportedFeatures22.setLiftWristToViewSettingsSupported((bleService2 == null || (devSupfeatures11 = bleService2.getDevSupfeatures()) == null || devSupfeatures11.getGestures_wake_up_settings() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.t;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setTemperatureUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.t;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.t;
        if (deviceSupportedFeatures25 != null) {
            EastapexBleService bleService3 = getBleService();
            deviceSupportedFeatures25.setAutoHrSettingsSupported((bleService3 == null || (devSupfeatures10 = bleService3.getDevSupfeatures()) == null || devSupfeatures10.getHr_monitoring() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.t;
        if (deviceSupportedFeatures26 != null) {
            EastapexBleService bleService4 = getBleService();
            deviceSupportedFeatures26.setAutoStressSettingsSupported((bleService4 == null || (devSupfeatures9 = bleService4.getDevSupfeatures()) == null || devSupfeatures9.getPressure_monitoring() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.t;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.t;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.t;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.t;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setSampleDataSupportedInSportMode(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.t;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setSyncBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.t;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.t;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.t;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.t;
        if (deviceSupportedFeatures35 != null) {
            EastapexBleService bleService5 = getBleService();
            deviceSupportedFeatures35.setAutoTemperatureSettingsSupported((bleService5 == null || (devSupfeatures8 = bleService5.getDevSupfeatures()) == null || devSupfeatures8.getTemperature_monitoring() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.t;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.t;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setScheduledDndSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.t;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setMusicMetaDataChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.t;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setMusicPlaybackStateChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.t;
        if (deviceSupportedFeatures40 != null) {
            deviceSupportedFeatures40.setMusicVolumeChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures41 = this.t;
        if (deviceSupportedFeatures41 != null) {
            deviceSupportedFeatures41.setMaxAlarmSupportedOnBand(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures42 = this.t;
        if (deviceSupportedFeatures42 != null) {
            deviceSupportedFeatures42.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures43 = this.t;
        if (deviceSupportedFeatures43 != null) {
            EastapexBleService bleService6 = getBleService();
            deviceSupportedFeatures43.setSedentaryReminderSupported((bleService6 == null || (devSupfeatures7 = bleService6.getDevSupfeatures()) == null || devSupfeatures7.getSedentary_monitoring() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures44 = this.t;
        if (deviceSupportedFeatures44 != null) {
            EastapexBleService bleService7 = getBleService();
            deviceSupportedFeatures44.setRepeatDaysSupportedInSedentary((bleService7 == null || (devSupfeatures6 = bleService7.getDevSupfeatures()) == null || devSupfeatures6.getSedentary_monitoring() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures45 = this.t;
        if (deviceSupportedFeatures45 != null) {
            EastapexBleService bleService8 = getBleService();
            deviceSupportedFeatures45.setDndSupported((bleService8 == null || (devSupfeatures5 = bleService8.getDevSupfeatures()) == null || devSupfeatures5.getDisturb_setting() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures46 = this.t;
        if (deviceSupportedFeatures46 != null) {
            deviceSupportedFeatures46.setShortcutMenuShowHideCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures47 = this.t;
        if (deviceSupportedFeatures47 != null) {
            EastapexBleService bleService9 = getBleService();
            deviceSupportedFeatures47.setFemaleWellnessSupported((bleService9 == null || (devSupfeatures4 = bleService9.getDevSupfeatures()) == null || devSupfeatures4.getMenstrual_setting() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures48 = this.t;
        if (deviceSupportedFeatures48 != null) {
            EastapexBleService bleService10 = getBleService();
            deviceSupportedFeatures48.setContactSyncSupported((bleService10 == null || (devSupfeatures3 = bleService10.getDevSupfeatures()) == null || devSupfeatures3.getPhone_contact() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures49 = this.t;
        if (deviceSupportedFeatures49 != null) {
            EastapexBleService bleService11 = getBleService();
            deviceSupportedFeatures49.setDrinkingReminderSupported((bleService11 == null || (devSupfeatures2 = bleService11.getDevSupfeatures()) == null || devSupfeatures2.getMonitor_reminder() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures50 = this.t;
        if (deviceSupportedFeatures50 != null) {
            deviceSupportedFeatures50.setFindMyBandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures51 = this.t;
        if (deviceSupportedFeatures51 != null) {
            EastapexBleService bleService12 = getBleService();
            deviceSupportedFeatures51.setGpsSupported((bleService12 == null || (devSupfeatures = bleService12.getDevSupfeatures()) == null || devSupfeatures.getGps_setting() != this.s) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures52 = this.t;
        if (deviceSupportedFeatures52 != null) {
            deviceSupportedFeatures52.setWeatherEnableCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures53 = this.t;
        if (deviceSupportedFeatures53 != null) {
            deviceSupportedFeatures53.setWeatherSupportedInBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures54 = this.t;
        if (deviceSupportedFeatures54 != null) {
            deviceSupportedFeatures54.setGoalSettingSupportedInSingleCommand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures55 = this.t;
        if (deviceSupportedFeatures55 != null) {
            deviceSupportedFeatures55.setBTCallingSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures56 = this.t;
        if (deviceSupportedFeatures56 != null) {
            deviceSupportedFeatures56.setMaxContactsInOneRequest(20);
        }
        DeviceSupportedFeatures deviceSupportedFeatures57 = this.t;
        if (deviceSupportedFeatures57 != null) {
            deviceSupportedFeatures57.setGenericActivityDataSampleSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures58 = this.t;
        if (deviceSupportedFeatures58 != null) {
            deviceSupportedFeatures58.setMusicDataSupportInSingleCommand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures59 = this.t;
        if (deviceSupportedFeatures59 != null) {
            deviceSupportedFeatures59.setSleepTargetSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures60 = this.t;
        if (deviceSupportedFeatures60 != null) {
            deviceSupportedFeatures60.setStressHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures61 = this.t;
        if (deviceSupportedFeatures61 != null) {
            deviceSupportedFeatures61.setCalorieGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures62 = this.t;
        if (deviceSupportedFeatures62 != null) {
            deviceSupportedFeatures62.setDistanceGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures63 = this.t;
        if (deviceSupportedFeatures63 != null) {
            deviceSupportedFeatures63.setExerciseMinutesGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures64 = this.t;
        if (deviceSupportedFeatures64 != null) {
            deviceSupportedFeatures64.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures65 = this.t;
        if (deviceSupportedFeatures65 != null) {
            deviceSupportedFeatures65.setBandVolumeControlSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures66 = this.t;
        Intrinsics.checkNotNull(deviceSupportedFeatures66);
        return deviceSupportedFeatures66;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Subscribe
    public final void onConnectionStateChanged(@Nullable CloveBleState cloveBleState) {
        super.onConnectionStateChangedHandler(cloveBleState);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl, com.coveiot.android.eastapexsdk.EastApexResponseListener
    public void onFailure(@NotNull EastApexError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onFailure(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl, com.coveiot.android.eastapexsdk.EastApexResponseListener
    public void onResponse(@NotNull EastApexBaseRes response) {
        Intrinsics.checkNotNullParameter(response, "response");
        super.onResponse(response);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            EastapexBleService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                super.setUserSettings(request, listener);
                return;
            }
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }
}
